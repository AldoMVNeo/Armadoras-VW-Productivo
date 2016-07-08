package neology.vc.actions.emision;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neology.modelo.dto.Estado;
import neology.modelo.dto.EstadoImpresion;
import neology.modelo.dto.EstadoOI;
import neology.modelo.dto.Formato;
import neology.modelo.dto.OrdenImpresion;
import neology.modelo.dto.TipoFormato;
import neology.modelo.dto.Usuario;
import neology.modelo.dto.id.FormatoId;
import neology.vc.actions.catalogos.usuarios.UsuariosAction;
import neology.vc.forms.emision.EmisionFormatosForm;

import neology.modelo.negocio.daos.EstadoDAO;
import neology.modelo.negocio.daos.EstadoOIDAO;
import neology.modelo.negocio.daos.FormatoDAO;
import neology.modelo.negocio.daos.OrdenImpresionDAO;
import neology.modelo.negocio.daos.TipoFormatoDAO;

import neology.modelo.negocio.daos.UsuarioDAO;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

public class EmisionFormatosAction extends DispatchAction
{
	/**
	 * Inicio.
	 * 
	 * @param mapping The ActionMapping used to select this instance.
	 * @param form The optional ActionForm bean for this request.
	 * @param request The HTTP Request we are processing.
	 * @param response The HTTP Response we are processing.
	 */
	
	public ActionForward inicio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException,
			ServletException
	{

		return mapping.findForward("cargar");
	}

	/**
	 * Asigna el Siguiente tramite de acuerdo al orde cronologico estricto
	 * 
	 * @param mapping The ActionMapping used to select this instance
	 * @param form The optional ActionForm bean for this request.
	 * @param request The HTTP Request we are processing.
	 * @param response The HTTP Response we are processing.
	 * @return forward
	 * @throws IOException
	 * @throws ServletException
	 */
	public ActionForward asignarTramite(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException,
			ServletException
	{
		
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();

		try{
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_EmisionFormatosAction asignarTramite - INICIO***"); 
		Long idOrden = request.getParameter("idOrden") == null ? null : Long
				.parseLong(request.getParameter("idOrden"));
		OrdenImpresion orden = null;
		OrdenImpresionDAO ordenDAO = DAOFactory.crearOrdenImpresionDAO();
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");
		EmisionFormatosForm formulario = (EmisionFormatosForm) form;

		// En caso de Reimpresion
		if (idOrden != null) orden = ordenDAO.buscarPorId(idOrden);
		// Se obtiene la orden de impresion de acuerdo al orden coronologico
		// estricto
		else orden = ordenDAO.buscarOrdenImpresionSiguiente(usuario.getEntidad()
				.getIdEntidad());

		if (orden == null)
		{
			Utilidades.escribirXML(response, "-1");
		}

		else
		{
			// Se aparta la orden de impresion para que ningun otro usuario
			// pueda utilizarla
			EstadoOIDAO estadoDAO = DAOFactory.crearEstadoOIDAO();
			EstadoOI estado = estadoDAO.buscarPorId(EstadoOI.APARTADO);
			TipoFormatoDAO tipoDAO = DAOFactory.crearTipoFormatoDAO();
			orden.setEstado(estado);
			loggerInfo.info(FechaUtil.getHoraActual()+"_EmisionFormatosAction asignarTramite inicia actualizar orden ");
			ordenDAO.actualizar(orden);
			loggerInfo.info(FechaUtil.getHoraActual()+"_EmisionFormatosAction asignarTramite fin actualizar orden ");
			formulario.setOrdenImpresion(orden);
			formulario.setUsuario(usuario);
			formulario.setValido(true);
			formulario.setIdOrdenImpresion(orden.getIdOrdenImpresion());
			formulario.setVin(orden.getVin());
			formulario.setPlaca(orden.getPlaca());
			formulario.setMarca(orden.getMarca());
			formulario.setModelo(orden.getModelo());
			formulario.setNumeroTramite(orden.getNumeroTramite());
			formulario.setBarco(orden.getBarco());
			formulario.setTipo(orden.getTipo());
			formulario.setAnnoModelo(orden.getAnnoModelo());

			// Obtiene el tipo de Formato que se requiere
			TipoFormato tipo = tipoDAO.buscarPorId(orden.getTipoFormato()
					.getIdTipoFormato());
			String imagen = "desconocido.swf";
			if (tipo.getIdTipoFormato() == 1) imagen = "vehiculo.swf";
			if (tipo.getIdTipoFormato() == 2) imagen = "moto.swf";
			Utilidades.escribirXML(response, orden.getIdOrdenImpresion() + "||" + imagen
					+ "||" + tipo.getDescripcion() + "||");

		}
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_EmisionFormatosAction asignarTramite asignarTramite - FIN***"); 
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return null;
		}catch(Exception e){
			
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo EmisionFormatosAction asignarTramite", e);
			loggerError.error(FechaUtil.getHoraActual()+"_EmisionFormatosAction asignarTramite - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
		}
		
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return null;
	}

	

	

	public ActionForward imprimirCB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException,
			ServletException
	{
		// Integer numero=Integer.parseInt(request.getParameter("numeroImpresiones"));
		
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		
		try{
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_EmisionFormatosAction imprimirCB - INICIO***");
		String vin = request.getParameter("vin");
		OrdenImpresionDAO ordenDAO = DAOFactory.crearOrdenImpresionDAO();
		EstadoOIDAO estadoDAO = DAOFactory.crearEstadoOIDAO();

		EstadoOI estadoOI = estadoDAO.buscarPorId(EstadoOI.IMPRESO);

		// List <OrdenImpresion>ordenes=ordenDAO.buscarOrdenImpresionDisponible(numero);
		// EstadoOI estado = estadoDAO.buscarPorId(EstadoOI.IMPRESO);
		String resultado = new String();
		OrdenImpresion orden = ordenDAO.buscarPorVin(vin);
		if (orden != null)
		{
			orden.setEstado(estadoOI);
			ordenDAO.actualizar(orden);
		}
		// for(OrdenImpresion orden:ordenes){
		// orden.setEstado(estado);
		// ordenDAO.actualizar(orden);
		resultado = vin;
		// }

		Utilidades.escribirXML(response, resultado);
		loggerInfo.info(FechaUtil.getHoraActual()+"_EmisionFormatosAction imprimirCB - FIN***");
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return null;
		}catch(Exception e){
			loggerError.error(FechaUtil.getHoraActual()+"_EmisionFormatosAction imprimirCB Fallo ", e);
			loggerError.error(FechaUtil.getHoraActual()+"_EmisionFormatosAction imprimirCB - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
		}
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return null;
	}

	public ActionForward validarVinFormato(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException,
			ServletException
	{
		
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_EmisionFormatosAction validarVinFormato imprimirCB - INICIO***");
		try{
		PrintWriter out = response.getWriter();
		String respuesta = "OK";
		String niv = request.getParameter("niv");
		String folio = request.getParameter("folio");
		OrdenImpresionDAO ordenDAO = DAOFactory.crearOrdenImpresionDAO();
		FormatoDAO formatoDAO = DAOFactory.crearFormatoDAO();
		List orden = ordenDAO.buscarPorNIV(niv);
		Formato formato = formatoDAO
				.buscarPorId(new FormatoId(folio, TipoFormato.REPUVE));
		if (orden == null || orden.size() == 0) {
			respuesta = "No se encontró un registro con el NIV "
				+ niv + " en la Base de Datos";
			loggerError.error(FechaUtil.getHoraActual()+"_EmisionFormatosAction fallo validarVinFormato "+ respuesta);
		}else
		{
			OrdenImpresion ordenImpresion = (OrdenImpresion) orden.get(0);
			if (!EstadoOI.IMPRESO.equals(ordenImpresion.getEstado().getEstado())){
				respuesta = "El registro del NIV tiene un estado de "
						+ ordenImpresion.getEstado().getDescripcion();
				loggerError.error(FechaUtil.getHoraActual()+"_EmisionFormatosAction fallo validarVinFormato "+ respuesta);
				}
			if(ordenImpresion.getFolio()!=null && ordenImpresion.getFolio().length()>0){
				respuesta= "El NIV ya fue asociado a la Constancia con folio "+ordenImpresion.getFolio();
				loggerError.error(FechaUtil.getHoraActual()+"_EmisionFormatosAction fallo validarVinFormato "+ respuesta);
			}
		}

		if (formato == null){
			respuesta = "Debe realizar una Dotacion de Constancias con el folio " + folio;
			loggerError.error(FechaUtil.getHoraActual()+"_EmisionFormatosAction fallo validarVinFormato "+ respuesta);		
		}
		
		  else{ 
			  if(formato.getEstado().getEstado().equals(Estado.ELIMINADO)){
				  respuesta = "Debe realizar una Dotacion de Constancias con el folio " + folio;
				  loggerError.error(FechaUtil.getHoraActual()+"_EmisionFormatosAction fallo validarVinFormato "+ respuesta);  
			  }
			  else
			  if(!formato.getEstado().getEstado().equals(Estado.DOTADO)) 
				  
				  respuesta="La Constancia de Inscripción tiene un estado de "+ formato.getEstado().getDescripcion();
			  loggerError.error(FechaUtil.getHoraActual()+"_EmisionFormatosAction fallo validarVinFormato "+ respuesta);
		  }
		out.println(respuesta);
		out.close();
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_EmisionFormatosAction validarVinFormato imprimirCB - FIN***");
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return null;
		}catch(Exception e){
			loggerError.error(FechaUtil.getHoraActual()+"_EmisionFormatosAction fallo validarVinFormato", e);
			loggerError.error(FechaUtil.getHoraActual()+"_EmisionFormatosAction validarVinFormato - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
		}
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return null;
	}

}
