package neology.vc.actions.impresion;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.PrintJob;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neology.modelo.dto.Entidad;
import neology.modelo.dto.EstadoOI;
import neology.modelo.dto.OrdenImpresion;
import neology.modelo.dto.OrdenesHistorico;
import neology.modelo.dto.Usuario;
import neology.modelo.dto.impresion.Seleccionados;
import neology.modelo.negocio.daos.EntidadDAO;
import neology.modelo.negocio.daos.EstadoDAO;
import neology.modelo.negocio.daos.EstadoOIDAO;
import neology.modelo.negocio.daos.FormatoDAO;
import neology.modelo.negocio.daos.OrdenImpresionDAO;
import neology.modelo.negocio.daos.OrdenesHistoricoDAO;
import neology.modelo.negocio.daos.TipoFormatoDAO;
import neology.modelo.negocio.daos.UsuarioDAO;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.util.FechaUtil;
import neology.util.VariablesGlobales;
import neology.vc.actions.handheld.usuarios.GenerarUsuariosAction;
import neology.vc.forms.catalogos.entidades.EntidadesForm;
import neology.vc.forms.impresion.ImpresionesForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import java.util.ArrayList;
import java.util.List;

import neology.util.Utilidades;

public class ImpresionFormatosAction extends DispatchAction {

	private static String reporteTituloConChip = "reporteTituloConChip";
	/**
	 * This is the main action called from the Struts framework.
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance.
	 * @param form
	 *            The optional ActionForm bean for this request.
	 * @param request
	 *            The HTTP Request we are processing.
	 * @param response
	 *            The HTTP Response we are processing.
	 */
	public ActionForward imprimir(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_ImpresionFormatosAction imprimir - INICIO***");
		try {
			String nombre = request.getParameter("nombreReporte") != null ? request.getParameter("nombreReporte")
					: (String) request.getAttribute("nombreReporte");
			Map parametros = new HashMap();
			MessageResources mensajes = getResources(request);

			// Verifica que el tipo de reporte sea un Titulo de Propiedad
			if (nombre.startsWith(reporteTituloConChip)) {
				OrdenImpresionDAO dao = DAOFactory.crearOrdenImpresionDAO();
				Long idOrden = Long.parseLong(request.getParameter("ordenId"));
				parametros.put("idOrdenImpresion", idOrden);
				request.setAttribute("reporteEntrada", VariablesGlobales.getReporteTituloPropiedadConChip());
				request.setAttribute("tipoReporte", "application/pdf");
				request.setAttribute("reporteSalida", mensajes.getMessage("reporte.tituloPropiedadConChip.pdf"));

			}
			request.setAttribute("parametros", parametros);
			loggerInfo.info(FechaUtil.getHoraActual()+"_ImpresionFormatosAction imprimir - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("generarPDF");
		} catch (Exception e) {
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo ImpresionFormatosAction imprimir", e);
		  	loggerError.error(FechaUtil.getHoraActual()+"_ImpresionFormatosAction imprimir - FIN***");
		  	loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("generarPDF");
		}
	}

	public ActionForward listar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_ImpresionFormatosAction listar - INICIO***");
		try {
			ActionMessages errores = (ActionMessages) request.getAttribute("errores");
			ImpresionesForm formulario = (ImpresionesForm) form;
			OrdenImpresionDAO ordenDAO = DAOFactory.crearOrdenImpresionDAO();
			FormatoDAO formatoDAO = DAOFactory.crearFormatoDAO();
			UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");

			loggerInfo.info(FechaUtil.getHoraActual()+"_ImpresionFormatosAction inicio listar buscarOrdenes");
			List ordenes = ordenDAO.buscarOrdenesImpresionDotados();
			formulario.setTotalVins(ordenes.size());

			/*
			 * for(int i=0; i< ordenes.size();i++){
			 * formulario.setImpresiones(null); }
			 */
			formulario.setTotalFormatos(formatoDAO.totalFormatos(usuario.getEntidad().getIdEntidad()));
			// request.setAttribute("impresiones",
			// ordenDAO.buscarOrdenesImpresion(usuario.getEntidad().getIdEntidad()));
			formulario.setDatos(ordenes);
			// saveErrors(request, errores);
			loggerInfo.info(FechaUtil.getHoraActual()+"_ImpresionFormatosActionfin listar buscarOrdenes");
			loggerInfo.info(FechaUtil.getHoraActual()+"_ImpresionFormatosAction listar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("listar");
		} catch (Exception e) {
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo ImpresionFormatosAction listar", e);
			loggerError.error(FechaUtil.getHoraActual()+"_ImpresionFormatosAction listar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("listar");
		}
	}

	public ActionForward seleccionados(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();

		loggerInfo.info(FechaUtil.getHoraActual()+"_ImpresionFormatosAction seleccionados - INICIO***");
		try {
			ImpresionesForm formulario = (ImpresionesForm) form;
			OrdenImpresionDAO ordenDAO = DAOFactory.crearOrdenImpresionDAO();
			String seleccionados = request.getParameter("vin") == null ? null : request.getParameter("vin");
			String pivote = null;
			// Creamos un Frame para obtener un objeto PrintJob
			Frame f = new Frame("Imprimir");
			f.pack();

			if (seleccionados != null) {
				while (seleccionados.length() > 1) {
					pivote = seleccionados.substring(0, seleccionados.indexOf(","));
					seleccionados = seleccionados.substring(seleccionados.indexOf(",") + 1, seleccionados.length());
					OrdenImpresion orden = ordenDAO.buscarPorVin(pivote);
					if (orden != null) {
						orden.setColor("I");
						ordenDAO.actualizar(orden);

					}
				}
				/*
				 * // response.encodeRedirectURL(
				 * "/neology/paginas/impresion/listImpresion.jsp"); //
				 * Utilidades.escribirXML(response,pivote); PrintJob pjob =
				 * f.getToolkit().getPrintJob(f,"Impresion del Registro",null);
				 * // Se obtiene el objeto graphics sobre el que se va pintar
				 * Graphics pg = pjob.getGraphics(); pg.setFont(new Font
				 * ("c39hrp24dhtt",Font.PLAIN,45)); // Se escribe el texto a
				 * imprimir pg.drawString("SALUDOS",100,100); // Se finaliza la
				 * pagina pg.dispose(); // Se hace que la impresora termine el
				 * trabajo pjob.end();
				 */

				/*
				 * class ObjetoAImprimir implements Printable { public int
				 * imprimir(Graphics g, PageFormat f, int pageIndex, String
				 * cadena) { Graphics2D g3 = (Graphics2D) g; Rectangle2D rect =
				 * new Rectangle2D.Double(f.getImageableX(), f.getImageableY(),
				 * f.getImageableWidth(), f.getImageableHeight()); Ellipse2D
				 * circle = new Ellipse2D.Double(100,100,100,100); switch
				 * (pageIndex) { case 0 : //Página 2: Circunferencia y
				 * rectángulo g3.setColor(Color.red); g3.setFont(new Font
				 * ("c39hrp24dhtt",Font.PLAIN,40)); g3.drawString(cadena, 100,
				 * 100); return PAGE_EXISTS; //La página 2 existe y se imprimirá
				 * default: return NO_SUCH_PAGE; //No se imprimirán más páginas
				 * }
				 * 
				 * 
				 * } public int print (Graphics g, PageFormat f, int pageIndex)
				 * { // Creamos un objeto 2D para dibujar en el Graphics2D g2 =
				 * (Graphics2D) g; Graphics2D g3 = (Graphics2D) g; // Este
				 * código imprime 2 páginas una con un cuadrado o marco // y una
				 * segunda con un circulo en la esquina superior izquierda //
				 * Creamos el rectángulo // getImagebleX() coge la parte de la
				 * hoja donde podemos // imprimir quitando los bordes. Si no
				 * hiciesemos // esto así y tuviesemos bordes definidos en la
				 * impresión // lo que dibujasemos fuera de los bordes no lo //
				 * imprimiría aunque cupiese en la hoja físicamente. Rectangle2D
				 * rect = new Rectangle2D.Double(f.getImageableX(),
				 * f.getImageableY(), f.getImageableWidth(),
				 * f.getImageableHeight());
				 * 
				 * 
				 * // Creamos la circunferencia Ellipse2D circle = new
				 * Ellipse2D.Double(100,100,100,100); // pageIndex indica el
				 * número de la página que se imprime // cuando es 0 primera
				 * página a imprimir, es un rectángulo // cuando es 1 segunda
				 * página a imprimir, es una circunferencia // En otro caso se
				 * devulve que no hay más páginas a imprimir switch (pageIndex)
				 * { case 0 : //Página 2: Circunferencia y rectángulo
				 * g3.setColor(Color.red); g3.setFont(new Font
				 * ("c39hrp24dhtt",Font.PLAIN,40));
				 * g3.drawString("31FGYTRHJ654KJHGFD", 100, 100); return
				 * PAGE_EXISTS; //La página 2 existe y se imprimirá default:
				 * return NO_SUCH_PAGE; //No se imprimirán más páginas } }
				 * 
				 * }
				 * 
				 * PrinterJob job = PrinterJob.getPrinterJob();
				 * job.setPrintable(new ObjetoAImprimir());
				 * 
				 * System.out.println("Información job: " + job.toString()); //
				 * Abre el cuadro de diálogo de la impresora, si queremos que
				 * imprima // directamente sin cuadro de diálogo quitamos el
				 * if... // if (job.printDialog()) //{ // Imprime, llama a la
				 * función print del objeto a imprimir // en nuestro caso el
				 * Objeto ObjetoAImprimir
				 * 
				 * try { job.print(); } catch (PrinterException e) {
				 * System.out.println("Error de impresión: " + e); } //}
				 * 
				 */

				PrintJob pjob = f.getToolkit().getPrintJob(f, "Iertry del Registro", null);
				// PrinterJob pjob = PrinterJob.getPrinterJob();
				// Se obtiene el objeto graphics sobre el que se va pintar
				// Graphics pg = pjob.getGraphics();
				Graphics pg = pjob.getGraphics();
				pg.setFont(new Font("c39hrp24dhtt", Font.PLAIN, 45));
				// Se escribe el texto a imprimir
				pg.drawString("154FGHFGHDEF", 100, 100);
				// Se finaliza la pagina
				pg.dispose();
				// Se hace que la impresora termine el trabajo
				// pjob.end();
				pjob.end();

			}

			// formulario.setImpresiones(ordenDAO.buscarOrdenesImpresion());
			// saveErrors(request, errores);
			loggerInfo.info(FechaUtil.getHoraActual()+"_ImpresionFormatosAction seleccionados - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("listar");
		} catch (Exception e) {
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo ImpresionFormatosAction seleccionados", e);
			loggerError.error(FechaUtil.getHoraActual()+"_ImpresionFormatosAction seleccionados - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("listar");
		}
	}

	public ActionForward guardar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_ImpresionFormatosAction guardar - INICIO***");
		try {
			OrdenImpresionDAO ordenDAO = DAOFactory.crearOrdenImpresionDAO();
			OrdenesHistoricoDAO ordenHistoricoDAO = DAOFactory.crearOrdenesHistoricoDAO();
			TipoFormatoDAO tipoDAO = DAOFactory.crearTipoFormatoDAO();
			ImpresionesForm formulario = (ImpresionesForm) form;
			MessageResources mensajes = getResources(request);
			Boolean pdf = false;
			String de = formulario.getDe();
			String hasta = formulario.getHasta();
			List datos = new ArrayList();
			datos = formulario.getDatos();
			String impresiones[] = formulario.getImpresiones();
			OrdenImpresion ordenImpresion;
			EstadoOIDAO estadoOIDAO = DAOFactory.crearEstadoOIDAO();
			EstadoOI estadoOI = estadoOIDAO.buscarPorId(EstadoOI.IMPRESO);
			EstadoDAO estado=DAOFactory.crearEstadoDAO();
			String vin;
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");
			Long numImpresion = ordenDAO.ObtenerMaximoImpresion(usuario.getEntidad().getIdEntidad());
			loggerInfo.info(FechaUtil.getHoraActual()+"_ImpresionFormatosAction inicio guardar formato");
			Map parametros = new HashMap();
			if (numImpresion == null)
				numImpresion = new Long(0);
			numImpresion++;
			formulario.setNumImpresion(numImpresion);
			if (de != null && !de.equals("0")) {
				if (hasta == null || hasta == "0")
					hasta = de;
				ordenDAO.actualizarRango(Integer.parseInt(de), Integer.parseInt(hasta), numImpresion,
						usuario.getEntidad().getIdEntidad(), new Long(usuario.getIdUsuario()));
				pdf = true;
			} else if (impresiones != null) {
				for (int i = 0; impresiones.length > i; i++) {
					ordenImpresion = (OrdenImpresion) datos.get(Integer.parseInt(impresiones[i]));
					vin = ordenImpresion.getVin();
					OrdenImpresion orden = ordenDAO.buscarPorVin(vin);
					orden.setEstado(estadoOI);
					orden.setNumeroTramite(numImpresion);
					orden.setFechaImpresion(FechaUtil.getFechaYHoraActual());
					orden.setUsuario(usuario);

					if (ordenDAO.actualizar(orden)) {
						// Guardar Histórico					
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
						ordenHistorico.setFolio(orden.getFolio());
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
						ordenHistorico.setVin(orden.getVin());
						ordenHistorico.setTipoFormato(tipoDAO.buscarPorId(new Long(1)));
						ordenHistorico.setIdOrdenImpresion(orden.getIdOrdenImpresion());
						ordenHistorico.setIdUsuarioModifico(usuario);
						ordenHistorico.setIdUsuario(usuario);
						ordenHistoricoDAO.guardar(ordenHistorico);
						loggerInfo.info(FechaUtil.getHoraActual()+"_ImpresionFormatosAction orden guardada "+orden.getVin());
					}

				}
				loggerInfo.info(FechaUtil.getHoraActual()+"_ImpresionFormatosAction fin guardar formato");
				pdf = true;
			}
			if (pdf) {
				loggerInfo.info(FechaUtil.getHoraActual()+"_ImpresionFormatosAction guardar - FIN***");
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				return mapping.findForward("mostrar");
			} else {
				loggerInfo.info(FechaUtil.getHoraActual()+"_ImpresionFormatosAction guardar - FIN***");
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				return mapping.findForward("todos");
			}
		} catch (Exception e) {
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo ImpresionFormatosAction guardar", e);
			loggerError.error(FechaUtil.getHoraActual()+"_ImpresionFormatosAction guardar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("mostrar");
		}
	}

	public ActionForward imprimePDF(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_ImpresionFormatosAction imprimePDF - INICIO***");
		try{
		OrdenImpresionDAO ordenDAO = DAOFactory.crearOrdenImpresionDAO();
		MessageResources mensajes = getResources(request);
		ImpresionesForm formulario = (ImpresionesForm) form;
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");
		// Long numImpresion =
		// ordenDAO.ObtenerMaximoImpresion(usuario.getEntidad().getIdEntidad());
		Long numImpresion = formulario.getNumImpresion();
		Map parametros = new HashMap();
		parametros.put("numImpresion", numImpresion);
		request.setAttribute("reporteEntrada", VariablesGlobales.getReporteConstanciaInscripcionREPUVE());
		request.setAttribute("tipoReporte", "application/pdf");
		request.setAttribute("reporteSalida", mensajes.getMessage("reporte.constanciaInscripcion.pdf"));
		request.setAttribute("parametros", parametros);
		loggerInfo.info(FechaUtil.getHoraActual()+"_ImpresionFormatosAction imprimePDF - FIN***");
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return mapping.findForward("generarPDF");
		} catch (Exception e) {
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo ImpresionFormatosAction imprimePDF", e);
			loggerError.error(FechaUtil.getHoraActual()+"_ImpresionFormatosAction imprimePDF - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("generarPDF");
		}
	}

}
