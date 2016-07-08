package neology.vc.actions.catalogos.tipoFormato;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import neology.modelo.dto.TipoFormato;
import neology.modelo.negocio.daos.TipoFormatoDAO;

import neology.modelo.negocio.servicios.DAOFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.util.formato.ConvertidorFolio;
import neology.vc.actions.busqueda.BusquedaNivAction;
import neology.vc.forms.catalogos.tipoFormato.TipoFormatoForm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;




public class TipoFormatoAction extends DispatchAction {
    /** 
     * Carga las entidades, tipos de formatos para realizar el cambio de estadode los formatos
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     */

    public ActionForward cargar(ActionMapping mapping, ActionForm form, 
                                HttpServletRequest request, 
                                HttpServletResponse response) throws IOException, 
                                                                     ServletException {
        
    	Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
    	
		loggerInfo.info(FechaUtil.getHoraActual()+"   TipoFormatoAction cargar - INICIO***"); 
        TipoFormatoForm formulario = (TipoFormatoForm) form;
        TipoFormatoDAO tipoFormatoDAO = DAOFactory.crearTipoFormatoDAO();        		
		request.setAttribute("tipoFormatos", tipoFormatoDAO.buscarTipoFormatos());
		
		loggerInfo.info(FechaUtil.getHoraActual()+"   TipoFormatoAction cargar - FIN***"); 
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return mapping.findForward("cargar");
    }
    
    public ActionForward nuevo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, 
			HttpServletResponse response)throws IOException, 
												ServletException {
    	log.debug(FechaUtil.getHoraActual()+"   TipoFormatoAction nuevo - INICIO***"); 
    	TipoFormatoForm formulario = (TipoFormatoForm)form;	
    	log.debug(FechaUtil.getHoraActual()+"   TipoFormatoAction nuevo - FIN***"); 
		return mapping.findForward("nuevo");
}
    
    
    public ActionForward realizarAlta(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, 
			HttpServletResponse response)throws IOException, 
												ServletException {
    	log.debug(FechaUtil.getHoraActual()+"   TipoFormatoAction realizarAlta - INICIO***"); 
    	Boolean exito = false; 
    	TipoFormatoForm formulario = (TipoFormatoForm)form;	        
    	TipoFormatoDAO tipoFormatoDAO = DAOFactory.crearTipoFormatoDAO();
				
		String descripcion = formulario.getDescripcion();
		String formatoFolio= formulario.getFormatoFolio();
		
		TipoFormato tipoFormato = new TipoFormato();
		tipoFormato.setIdTipoFormato(0);
		tipoFormato.setDescripcion(descripcion);
		tipoFormato.setFormatoFolio(formatoFolio);
		tipoFormato.setEstatus("T");
		
		exito = tipoFormatoDAO.guardar(tipoFormato);
		ActionMessages mensaje = new ActionMessages();
		if (exito)
            mensaje.add("mensaje", new ActionMessage("mensaje.catalogos.exitoso", "Tipo de Formato"));
        else
            mensaje.add("mensaje", new ActionMessage("errors.catalogos.guardar", "Tipo de Formato"));
		log.debug(FechaUtil.getHoraActual()+"   TipoFormatoAction realizarAlta - FIN***"); 
		request.setAttribute("mensaje", mensaje);
		return mapping.findForward("mensaje");
}

    public ActionForward modificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, 
			HttpServletResponse response)throws IOException, 
												ServletException {
    	log.debug(FechaUtil.getHoraActual()+"   TipoFormatoAction modificar - INICIO***");
    	TipoFormatoForm formulario = (TipoFormatoForm)form;
    	TipoFormatoDAO tipoFormatoDAO = DAOFactory.crearTipoFormatoDAO();
    	Long idTipoFormato = Long.valueOf(request.getParameter("idTipoFormato"));
    	TipoFormato tipoFormato = tipoFormatoDAO.buscarPorId(idTipoFormato);
    	formulario.setIdTipoFormato(idTipoFormato);
    	formulario.setDescripcion(tipoFormato.getDescripcion());
    	formulario.setFormatoFolio(tipoFormato.getFormatoFolio());
    	log.debug(FechaUtil.getHoraActual()+"   TipoFormatoAction modificar - FIN***");
		return mapping.findForward("modificar");
}
    
    public ActionForward actualizar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, 
			HttpServletResponse response)throws IOException, 
												ServletException {
    	log.debug(FechaUtil.getHoraActual()+"   TipoFormatoAction actualizar - INICIO***");
    	Boolean exito = false;
    	TipoFormatoForm formulario = (TipoFormatoForm)form;	        
    	TipoFormatoDAO tipoFormatoDAO = DAOFactory.crearTipoFormatoDAO();
				
		Long idTipoFormato = formulario.getIdTipoFormato();
    	String descripcion = formulario.getDescripcion();
		String formatoFolio= formulario.getFormatoFolio();
		
		TipoFormato tipoFormato = tipoFormatoDAO.buscarPorId(idTipoFormato);
		tipoFormato.setIdTipoFormato(idTipoFormato);
		tipoFormato.setDescripcion(descripcion);
		tipoFormato.setFormatoFolio(formatoFolio);
		
		exito = tipoFormatoDAO.actualizar(tipoFormato);
		ActionMessages mensaje = new ActionMessages();
		if (exito)
            mensaje.add("mensaje", new ActionMessage("mensaje.catalogos.exitosoActualizacion", "Tipo de Formato"));
        else
            mensaje.add("mensaje", new ActionMessage("errors.catalogos.actualizar", "Tipo de Formato"));
		
		request.setAttribute("mensaje", mensaje);
		log.debug(FechaUtil.getHoraActual()+"   TipoFormatoAction actualizar - FIN***");
		return mapping.findForward("mensaje");
}
    
    public ActionForward validar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, 
			HttpServletResponse response)throws IOException, 
												ServletException {
		// 	TODO Auto-generated method stub
    	
		return mapping.findForward("cargar");
}
    
    public ActionForward eliminar (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, 
			HttpServletResponse response)throws IOException, 
												ServletException {
    	log.debug(FechaUtil.getHoraActual()+"   TipoFormatoAction eliminar - INICIO***");
    	Boolean exito = false;
    	TipoFormatoForm formulario = (TipoFormatoForm)form;	        
    	TipoFormatoDAO tipoFormatoDAO = DAOFactory.crearTipoFormatoDAO();
				
		Long idTipoFormato = Long.valueOf(request.getParameter("idTipoFormato"));
    	
		
		TipoFormato tipoFormato = tipoFormatoDAO.buscarPorId(idTipoFormato);
		tipoFormato.setIdTipoFormato(idTipoFormato);
		tipoFormato.setEstatus("F");		
		exito = tipoFormatoDAO.actualizar(tipoFormato);
		ActionMessages mensaje = new ActionMessages();
		if (exito)
            mensaje.add("mensaje", new ActionMessage("mensaje.catalogos.exitosaEliminacion", "Tipo de Formato"));
        else
            mensaje.add("mensaje", new ActionMessage("errors.catalogos.eliminar", "Tipo de Formato"));
		request.setAttribute("mensaje", mensaje);
		log.debug(FechaUtil.getHoraActual()+"   TipoFormatoAction eliminar - FIN***");
		return mapping.findForward("mensaje");
}
    
}
