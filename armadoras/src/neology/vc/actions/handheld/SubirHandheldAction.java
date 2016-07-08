package neology.vc.actions.handheld;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neology.modelo.dto.Estado;
import neology.modelo.dto.EstadoOI;
import neology.modelo.dto.Formato;
import neology.modelo.dto.FormatoHistorico;
import neology.modelo.dto.OrdenImpresion;
import neology.modelo.dto.OrdenesHistorico;
import neology.modelo.dto.Perfil;
import neology.modelo.dto.TipoFormato;
import neology.modelo.dto.Usuario;
import neology.modelo.dto.id.FormatoId;
import neology.modelo.negocio.daos.EstadoDAO;
import neology.modelo.negocio.daos.FormatoDAO;
import neology.modelo.negocio.daos.FormatoHistoricoDAO;
import neology.modelo.negocio.daos.OrdenImpresionDAO;
import neology.modelo.negocio.daos.OrdenesHistoricoDAO;
import neology.modelo.negocio.daos.TipoFormatoDAO;
import neology.modelo.negocio.daos.UsuarioDAO;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.seguridad.Cipher;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.vc.forms.handheld.SubirHandheldForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.joda.time.DateTime;

public class SubirHandheldAction extends DispatchAction
{

	private static Logger log = Logger.getLogger(SubirHandheldAction.class);

	private String estado;
	private DateTime fechaRegistro;
	private String folio;
	private FormatoDAO formatoDAO = DAOFactory.crearFormatoDAO();
	private int idUsuario;
	private OrdenImpresionDAO ordenImpresionDAO = DAOFactory.crearOrdenImpresionDAO();
	private StringBuffer resultadoLinea = new StringBuffer("");
	private String vin;

	/**
	 * Solo llama al tile que incluye subirHandheldUpload.jsp
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward inicio(ActionMapping mapping, @SuppressWarnings("unused")
	ActionForm form, @SuppressWarnings("unused")
	HttpServletRequest request, @SuppressWarnings("unused")
	HttpServletResponse response) throws Exception
	{
		Logger loggerInfo = Utilidades.loggerInfo();
		loggerInfo.info(FechaUtil.getHoraActual()+"_SubirHandheldAction inicio - INICIO***"); 
		loggerInfo.info(FechaUtil.getHoraActual()+"_SubirHandheldAction inicio - FIN***"); 
		loggerInfo.removeAllAppenders();
		return mapping.findForward("subir");
	}

	/**
	 * Carga el archivo de la hand held
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward subir(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, @SuppressWarnings("unused")
			HttpServletResponse response) throws Exception
	{
	
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		try{
		loggerInfo.info(FechaUtil.getHoraActual()+"_SubirHandheldAction subir - INICIO***");
		SubirHandheldForm myForm = (SubirHandheldForm) form;
		FormFile myFile = myForm.getTheFile();
		File file = subeArchivo(request, myFile);
		if (file != null) procesaArchivo(request, file);
		}catch(Exception e){
			loggerError.error(FechaUtil.getHoraActual()+"_SubirHandheldAction subir fallo",e);
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
		}
		loggerInfo.info(FechaUtil.getHoraActual()+"_SubirHandheldAction subir - FIN***");
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return mapping.findForward("exito");
	}

	private boolean creaFile(HttpServletRequest request, File file,
			StringBuffer contenido, StringBuffer resultado)
	{
		
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		loggerInfo.info(FechaUtil.getHoraActual()+"_SubirHandheldAction creaFile - INICIO***");
		if (contenido == null || contenido.length() == 0)
		{
			resultado.append("<p>No fué necesario generar el archivo de salida <strong>"
					+ file.getName() + "</strong>.</p>");
			loggerInfo.info(FechaUtil.getHoraActual()+"_SubirHandheldAction creaFile - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return true;
		}
		else
		{
			BufferedWriter bufferedWriter;
			try
			{
				bufferedWriter = new BufferedWriter(new FileWriter(file));
				bufferedWriter.write(contenido.toString());
				bufferedWriter.close();
				resultado.append("<p>Se generó el archivo de salida <a href='"
						+ request.getContextPath() + "/upload/" + file.getName()
						+ "' target='_blank'>" + file.getName() + "</a>.</p>");
				loggerInfo.info(FechaUtil.getHoraActual()+"_SubirHandheldAction creaFile - FIN***");
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				return true;
			} catch (IOException e)
			{
				e.printStackTrace();
				resultado.append("<p>El archivo de salida <strong>" + file.getName()
						+ "</strong> no se pudo crear.</p>");
				loggerInfo.info(FechaUtil.getHoraActual()+"_SubirHandheldAction creaFile fallo",e); 
				loggerInfo.info(FechaUtil.getHoraActual()+"_SubirHandheldAction creaFile - FIN***");
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				return false;
			}
		}
	}

	private boolean estadoValido(String s)
	{	
		if (s == null || s.length() != 1 || !s.equals("8"))
		{
			resultadoLinea.append("ERROR: Estado inválido. Debe ser un "
					+ "número del 0 al 9 o la letra A.");
			return false;
		}
		else
		{
			estado = s;
			return true;
		}
	}

	private boolean fechaValida(String fecha)
	{
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		fechaRegistro = null;
		try
		{
			date = sdf.parse(fecha);
		
			fechaRegistro = new DateTime(date.getTime());
		} catch (ParseException e)
		{
			resultadoLinea
					.append("ERROR: Formato de fecha inválido. Se esperaba dd-MM-yyyy HH:mm:ss");
			return false;
		}
		if (!sdf.format(date).equals(fecha))
		{
			resultadoLinea.append("ERROR: Fecha inválida. Posiblemente el día, "
					+ "mes o año está fuera de rango. Formato = dd-MM-yyyy HH:mm:ss");
			return false;
		}
		return true;
	}

	private boolean guardaLinea(Usuario usuario)
	{
		boolean guardado = false;
		UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
		FormatoHistoricoDAO formatoHistoricoDAO = DAOFactory.crearFormatoHistoricoDAO();
		OrdenesHistoricoDAO ordenHistoricoDAO =DAOFactory.crearOrdenesHistoricoDAO();
		OrdenImpresion orden = ordenImpresionDAO.existeVinFolio(vin, folio);
		TipoFormatoDAO tipo = DAOFactory.crearTipoFormatoDAO();
		
		if (orden == null) resultadoLinea.append("ERROR: No existe un VIN " + vin
				+ " asociado al folio " + folio + " en la base de datos.");
		else
		{
			if(orden.getEstado().getEstado().equals(EstadoOI.GRABADO) || orden.getEstado().getEstado().equals(EstadoOI.REGRABADO) )
			{
			orden.setVin(vin);
			orden.setFolio(folio);			
			orden.getEstado().setEstado(estado);
			orden.setFechaRegistro(FechaUtil.getFechaYHoraActual());
			orden.getUsuario().setIdUsuario(idUsuario);
			FormatoId formatoId = new FormatoId(folio,TipoFormato.REPUVE);// Tipo Formato 1 = REPUVE
			Formato formato = formatoDAO.buscarPorId(formatoId);
			if (formato == null) resultadoLinea.append("ERROR: No existe el folio "
					+ folio + " en la base de datos.");
			else if(!formato.getEstado().getEstado().equals(Estado.GRABADO)) 
				resultadoLinea.append("ERROR: No se puede actualizar el formato con estado "+formato.getEstado().getDescripcion());
			else{				
				formato.getEstado().setEstado(estado);
				formato.setFechaRegistro(FechaUtil.getFechaYHoraActual());
				if (ordenImpresionDAO.guardarOrdenYFormato(orden, formato) && formatoDAO.actualizar(formato))
			
			{
				//Guardar Historicos
				FormatoHistorico formatoHistorico= new FormatoHistorico();
				formatoHistorico.setIdUsuarioModifico(Long.valueOf(idUsuario));
				formatoHistorico.setFechaHistorico(FechaUtil.getFechaYHoraActual());
				formatoHistorico.setFolio(folio);
				formatoHistorico.setIdTipoFormato(1);
				formatoHistorico.setIdEntidad(usuario.getEntidad().getIdEntidad());
				formatoHistorico.setIdUsuarioActual(Long.valueOf(idUsuario));
				formatoHistorico.setEstado(estado);
				formatoHistorico.setFechaRegistro(FechaUtil.getFechaYHoraActual());
				formatoHistorico.setIdOrdenImpresion(orden.getIdOrdenImpresion());
				
				OrdenesHistorico ordenHistorico = new OrdenesHistorico();
				ordenHistorico.setAnnoModelo(orden.getAnnoModelo());
				ordenHistorico.setBarco(orden.getBarco());
				ordenHistorico.setCapacidad(orden.getCapacidad());
				ordenHistorico.setCilindraje(orden.getCilindraje());
				ordenHistorico.setColor(orden.getColor());
				ordenHistorico.setCombustible(orden.getCombustible());
				ordenHistorico.setEntidad(usuario.getEntidad().getNombreEntidad());
				ordenHistorico.setEstado(orden.getEstado());
				ordenHistorico.setFechaImpresion(orden.getFechaImpresion());
				ordenHistorico.setFechaRegistro(FechaUtil.getFechaYHoraActual());
				ordenHistorico.setFolio(folio);
				ordenHistorico.setFormatos(orden.getFormatos());
				ordenHistorico.setGrupo(orden.getGrupo());
				ordenHistorico.setIdEntidad(usuario.getEntidad().getIdEntidad());
				ordenHistorico.setIdPropietario(orden.getIdPropietario());
				ordenHistorico.setImprime(orden.getImprime());
				ordenHistorico.setMarca(orden.getMarca());
				ordenHistorico.setModelo(orden.getModelo());
				ordenHistorico.setNumeroChip(orden.getNumeroChip());
				ordenHistorico.setNumeroGrabaciones(orden.getNumeroGrabaciones());
				ordenHistorico.setNumeroMotor(orden.getNumeroMotor());
				ordenHistorico.setNumeroTramite(orden.getNumeroTramite());
				ordenHistorico.setPlaca(orden.getPlaca());
				ordenHistorico.setTipo(orden.getTipo());
				ordenHistorico.setUso(orden.getUso());
				ordenHistorico.setVin(vin);
				ordenHistorico.setTipoFormato(tipo.buscarPorId((long) 1));
				ordenHistorico.setIdOrdenImpresion(orden.getIdOrdenImpresion());
				ordenHistorico.setIdUsuarioModifico(usuario);
				ordenHistorico.setIdUsuario(usuario);
				
				if (formatoHistoricoDAO.guardar(formatoHistorico) && ordenHistoricoDAO.guardar(ordenHistorico)) {
					
					resultadoLinea.append("OK");
					guardado = true;
					
				}else{
					resultadoLinea.append("ERROR: No se pudo guardar el "
							+ "registro en la base de datos.");
				}
				
			}
			else resultadoLinea.append("ERROR: No se pudo guardar el "
					+ "registro en la base de datos.");
			}
			}
			else
				resultadoLinea.append("ERROR: El VIN " + vin
						+ " no puede actualizarse con el estado "+orden.getEstado().getDescripcion());
		}
		return guardado;
	}

	private boolean lineaValida(String line)
	{
		line = line.trim();
		if (line.length() == 0) return false;
		else {			
			String[] tokens = line.split("\\|", -1);
			resultadoLinea.append(tokens[0] +" " +tokens[1]+" "+tokens[3]+" ");}
		String[] tokens = line.split("\\|", -1);
		if (tokens.length < 5)
		{
			resultadoLinea.append("ERROR: Se esperaban 5 campos.");
			return false;
		}
		else
		{
			vin = tokens[0].trim();
			folio = tokens[1].trim();
			if (!estadoValido(tokens[2].trim())) return false;
			//if (!fechaValida(tokens[3].trim())) return false;
			if (!usuarioValido(tokens[4].trim())) return false;
		}
		return true;
	}

	private void procesaArchivo(HttpServletRequest request, File file)
	{
		String salida = getServlet().getServletContext().getRealPath("/") + "upload";
		String nl = System.getProperty("line.separator");
		StringBuffer ok = new StringBuffer();
		StringBuffer error = new StringBuffer();
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");
		StringBuffer resultado = new StringBuffer(
				"<p>Se subió el archivo de entrada <strong>" +file.getName() + "</strong></p>");
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(file));
			String line = null;
			int i = 0;
			while ((line = input.readLine()) != null)
			{
				resultadoLinea = new StringBuffer("");
				if (lineaValida(Cipher.desencriptar(line)) && guardaLinea(usuario)) ok.append(resultadoLinea + nl);
				else error.append(resultadoLinea + nl);
				i++;
			}
			creaFile(request, new File(salida, "OK-" + file.getName()), ok, resultado);
			creaFile(request, new File(salida, "Error-" + file.getName()), error,
					resultado);
			input.close();
			// file.delete();
			request.setAttribute("resultado", resultado.toString());
		} catch (IOException ex)
		{
			ex.printStackTrace();
			request.setAttribute("resultado", "El archivo de entrada <strong>"
					+ file.getName() + "</strong> no se pudo leer.");
		}

	}

	private File subeArchivo(HttpServletRequest request, FormFile myFile)
	{
		String fileName = myFile.getFileName();
		// obtiene el path físico en el servidor del directorio de upload
		String filePath = getServlet().getServletContext().getRealPath("/") + "upload";
		// salva el archivo en el servidor (toda la validación ya la debió hacer la forma)
		File file = new File(filePath, fileName);
		FileOutputStream fileOutStream;
		try
		{
			fileOutStream = new FileOutputStream(file);
			fileOutStream.write(myFile.getFileData());
			fileOutStream.flush();
			fileOutStream.close();
			return file;
		} catch (IOException e)
		{
			e.printStackTrace();
			request.setAttribute("resultado", "No se pudo guardar el archivo <strong>"
					+ fileName + "</strong> en el servidor.");
			return null;

		}
	}

	private boolean usuarioValido(String usuario_id)
	{
		boolean resultado=false;
		try
		{
		
			idUsuario = Integer.parseInt(usuario_id, 10);
			UsuarioDAO usuarioDAO=DAOFactory.crearUsuarioDAO();
			Usuario usuario=usuarioDAO.buscarPorId(new Long(idUsuario));
			if(usuario!=null && usuario.getPerfil().getIdPerfil().compareTo(Perfil.HANDHELD)==0)
				resultado=true;
			else
				resultadoLinea.append("ERROR: No existe el usuario. ");
		} catch (NumberFormatException e)
		{
			resultadoLinea.append("ERROR: Formato de número inválido. "
					+ "La columna usuario debe ser un número");
			resultado=false;
		}
		return resultado;
	}

}
