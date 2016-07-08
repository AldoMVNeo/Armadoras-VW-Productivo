package neology.vc.actions.catalogos.entidades;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import neology.modelo.dto.Usuario;
import neology.modelo.dto.Entidad;
import neology.modelo.dto.EstadoCatalogo;
import neology.modelo.negocio.daos.UsuarioDAO;
import neology.modelo.negocio.daos.EntidadDAO;

import neology.modelo.negocio.servicios.DAOFactory;
import neology.util.FechaUtil;
import neology.util.StringUtil;
import neology.util.Utilidades;
import neology.util.formato.ConvertidorFolio;
import neology.vc.actions.busqueda.BusquedaNivAction;
import neology.vc.forms.catalogos.entidades.EntidadesForm;
import neology.vc.forms.catalogos.usuarios.UsuariosForm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;




public class EntidadesAction extends DispatchAction {
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
		ActionMessages errores = new ActionMessages();
    	try{
    		
    	loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction cargar - INICIO***");
        EntidadesForm formulario = (EntidadesForm) form;
        EntidadDAO entidadesDAO = DAOFactory.crearEntidadDAO();        		
		
        request.setAttribute("entidades", entidadesDAO.buscarEntidades());
		
        loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction cargar - FIN***");	
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return mapping.findForward("cargar");
		
    	}catch(Exception e){
    		
    		errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("texto.etiqueta.error.log"));
    		loggerError.error(FechaUtil.getHoraActual()+"_Fallo EntidadesAction cargar", e);
    		loggerError.error(FechaUtil.getHoraActual()+"_EntidadesAction cargar - FIN***");
    		loggerInfo.info(FechaUtil.getHoraActual()+"_CambiarEstadoAction cambiar - FIN***");
    		loggerInfo.removeAllAppenders();
    		loggerError.removeAllAppenders();
    		saveErrors(request, errores);
    		return mapping.findForward("cargar");
    	}
    }
    
    public ActionForward nuevo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, 
			HttpServletResponse response)throws IOException, 
												ServletException {
    	
    	Logger loggerInfo = Utilidades.loggerInfo();
    	
		loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction cargar - INICIO***");
    	EntidadesForm formulario = (EntidadesForm)form;
    	loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction cargar - FIN***");
    	loggerInfo.removeAllAppenders();
    	return mapping.findForward("nuevo");
}
    
    
    public ActionForward realizarAlta(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, 
			HttpServletResponse response)throws IOException, 
												ServletException {
    	
    	Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		ActionMessages errores = new ActionMessages();
    	try{
    		
    	loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction realizarAlta - INICIO***");
    	Boolean exito = false; 
    	EntidadesForm formulario = (EntidadesForm)form;	            	
		EntidadDAO entidadDAO =DAOFactory.crearEntidadDAO();	
		ActionMessages mensaje = new ActionMessages();
		String nombreEntidad = StringUtil.primeraMayuscula(formulario.getNombreEntidad().trim());
		String alfa = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 "; 
		
		loggerInfo.info(FechaUtil.getHoraActual()+"  BusquedaNivAction validando entidad: " + nombreEntidad);
		 if (nombreEntidad == null || nombreEntidad.trim().length() < 1){
             mensaje.add("nombreEntidad", new ActionMessage("errors.requerido", "nombre de la oficina"));
             loggerError.error(FechaUtil.getHoraActual()+"_EntidadesAction fin realizarAlta error");
             loggerInfo.removeAllAppenders();
	    	 loggerError.removeAllAppenders();
	         request.setAttribute("mensaje", mensaje);
	         return mapping.findForward("mensaje");
		 
		 }
     	else
     	{
     		for (int i = 0; i < nombreEntidad.length(); i++)
     		{        			
     			if (alfa.indexOf(nombreEntidad.charAt(i)) == - 1)
     			{ 
     				mensaje.add("nombreEntidad", new ActionMessage("errors.noValido", nombreEntidad.charAt(i)));
     				request.setAttribute("mensaje", mensaje);
     				loggerError.error(FechaUtil.getHoraActual()+"_EntidadesAction realizarAlta - FIN***");
     				loggerInfo.removeAllAppenders();
     				loggerError.removeAllAppenders();
     				return mapping.findForward("mensaje");
     			}
     		} // end for
     	}       
		 
		 if(entidadDAO.existeEntidad(nombreEntidad.toLowerCase()))
				errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.existe.registro","El nombre de la Entidad "+nombreEntidad));
			if (errores.size() > 0) {
				saveErrors(request, errores);
				loggerError.error(FechaUtil.getHoraActual()+"_EntidadesAction realizarAlta - FIN***");
				loggerInfo.removeAllAppenders();
 				loggerError.removeAllAppenders();
				return mapping.getInputForward();
			}
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction fin validando entidad: " + nombreEntidad);
		Entidad entidad = new Entidad();
		entidad.setIdEntidad(0);
		entidad.setNombreEntidad(nombreEntidad);
		entidad.setIdTipoEntidad(new Long(1));
		entidad.setNumDocumento("1");
		entidad.setEstatus("0");
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction guardando entidad: " + nombreEntidad);
		exito = entidadDAO.guardar(entidad);
		loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction fin guardando entidad: " + nombreEntidad);
		mensaje = new ActionMessages();
		if (exito){
            mensaje.add("mensaje", new ActionMessage("mensaje.catalogos.exitoso", nombreEntidad));
		}else{
            mensaje.add("mensaje", new ActionMessage("errors.catalogos.guardar", nombreEntidad));}
		loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction realizarAlta - FIN***");
		request.setAttribute("mensaje", mensaje);
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return mapping.findForward("mensaje");
		
		}catch(Exception e){
			errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("texto.etiqueta.error.log"));
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo EntidadesAction realizarAlta", e);
			loggerError.error(FechaUtil.getHoraActual()+"_EntidadesAction realizarAlta - FIN***");
			loggerInfo.removeAllAppenders();
    		loggerError.removeAllAppenders();
    		saveErrors(request, errores);
    		return mapping.findForward("cargar");
		}
}

    public ActionForward modificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, 
			HttpServletResponse response)throws IOException, 
												ServletException {
    	
    	Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		ActionMessages errores = new ActionMessages();
    	try{
    		
    	loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction modificar - INICIO***");
    	EntidadesForm formulario = (EntidadesForm)form;
    	EntidadDAO entidadDAO = DAOFactory.crearEntidadDAO();
    	Long idEntidad = Long.valueOf(request.getParameter("idEntidad"));
    	Entidad entidad = entidadDAO.buscarPorId(idEntidad);
    	formulario.setIdEntidad(idEntidad);
    	formulario.setNombreEntidad(entidad.getNombreEntidad());    	
    	
    	loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction modificar - FIN***");
    	
    	loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
    	return mapping.findForward("modificar");
    	
    	}catch(Exception e){
    		errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("texto.etiqueta.error.log"));
    		loggerError.error(FechaUtil.getHoraActual()+"_Fallo EntidadesAction modificar", e);
    		loggerError.error(FechaUtil.getHoraActual()+"_EntidadesAction modificar - FIN***");
    		loggerInfo.removeAllAppenders();
    		loggerError.removeAllAppenders();
    		saveErrors(request, errores);
    		return mapping.findForward("modificar");
    	}
}
    
    public ActionForward actualizar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, 
			HttpServletResponse response)throws IOException, 
												ServletException {
    	
    	Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		ActionMessages mensaje = new ActionMessages();
    	try{
    	
    	loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction actualizar - INICIO***");
    	if(this.isCancelled(request)){	
    		
    		loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction actualizar - FIN***");
    		loggerInfo.removeAllAppenders();
    		loggerError.removeAllAppenders();
    		return(mapping.findForward("cancelar"));
    		
		}
    	Boolean exito = false;
    	EntidadesForm formulario = (EntidadesForm)form;	        
    	EntidadDAO entidadDAO = DAOFactory.crearEntidadDAO();
    	ActionMessages errores = new ActionMessages();		
		Long idEntidad = formulario.getIdEntidad();
    	String nombreEntidad = StringUtil.primeraMayuscula(formulario.getNombreEntidad().trim());
    	
    	loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction validando entidad: " + nombreEntidad);
    	
    	  String alfa = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 "; 
	      if (nombreEntidad == null || nombreEntidad.trim().length() < 1 || nombreEntidad.equals("")){
	    	  loggerError.error(FechaUtil.getHoraActual()+"_EntidadesAction fin actualizar error");
				loggerInfo.removeAllAppenders();
	    		loggerError.removeAllAppenders();
	            mensaje.add("nombreEntidad", new ActionMessage("errors.requerido", "nombre de la oficina"));
	            request.setAttribute("mensaje", mensaje);
	            return mapping.findForward("mensaje");
	      }
	        	else
	        	{
	        		for (int i = 0; i < nombreEntidad.length(); i++)
	        		{        			
	        			if (alfa.indexOf(nombreEntidad.charAt(i)) == - 1)
	        			{ 
	        				mensaje.add("nombreEntidad", new ActionMessage("errors.noValido", nombreEntidad.charAt(i)));
	        				request.setAttribute("mensaje", mensaje);
	        				loggerError.error(FechaUtil.getHoraActual()+"_EntidadesAction fin actualizar error");
	        				loggerInfo.removeAllAppenders();
	        	    		loggerError.removeAllAppenders();
	        				return mapping.findForward("mensaje");
	        			}
	        		} // end for
	        	}      
    	
    	if(entidadDAO.existeEntidad(idEntidad,nombreEntidad.toLowerCase())){
    		
    	 mensaje.add("mensaje", new ActionMessage("errors.existe.registro", "El nombre de la Entidad "+nombreEntidad));
    	 request.setAttribute("mensaje", mensaje);
    	 loggerError.error(FechaUtil.getHoraActual()+"_EntidadesAction fin actualizar error");
    	 loggerInfo.removeAllAppenders();
 		 loggerError.removeAllAppenders();
    	 return mapping.findForward("mensaje");
    	}
		Entidad entidad = entidadDAO.buscarPorId(idEntidad);
		entidad.setIdEntidad(idEntidad);
		entidad.setNombreEntidad(nombreEntidad);
		loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction fin validando entidad: " + nombreEntidad);		
		loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction actualizando entidad: " + nombreEntidad);
		
		exito = entidadDAO.actualizar(entidad);
		loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction fin actualizando entidad: " + nombreEntidad);
		if (exito)
            mensaje.add("mensaje", new ActionMessage("mensaje.catalogos.exitosoActualizacion", "registro de Oficina"));
        else
            mensaje.add("mensaje", new ActionMessage("errors.catalogos.actualizar", "registro de Oficina"));
		

		loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction actualizar - FIN***");

		loggerInfo.removeAllAppenders();
 		loggerError.removeAllAppenders();
		request.setAttribute("mensaje", mensaje);
		return mapping.findForward("mensaje");
		
    	}catch(Exception e){
    		mensaje.add("mensaje", new ActionMessage("texto.etiqueta.error.log"));
    		loggerError.error(FechaUtil.getHoraActual()+"_Fallo EntidadesAction actualizar", e);
    		loggerError.error(FechaUtil.getHoraActual()+"_EntidadesAction actualizar - FIN***");
    		loggerInfo.removeAllAppenders();
    		loggerError.removeAllAppenders();
    		request.setAttribute("mensaje", mensaje);
    		return mapping.findForward("mensaje");
    	}
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
    	
    	Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction eliminar - INICIO***");
		ActionMessages mensaje = new ActionMessages();
		try{
    	Boolean exito = false;
    	EntidadesForm formulario = (EntidadesForm)form;	        
    	EntidadDAO entidadDAO = DAOFactory.crearEntidadDAO();
    	List entidades =entidadDAO.buscarEntidades();
				
		Long idEntidad = Long.valueOf(request.getParameter("idEntidad"));
    	
		//No eliminar entidad 1 o ultima entidad
		if(idEntidad==1 || entidades.size()==1){
			
			loggerError.error(FechaUtil.getHoraActual()+"_EntidadesAction fin eliminar error");
			 mensaje.add("mensaje", new ActionMessage("errors.ordenes.eliminacionMasiva"));
			 loggerInfo.removeAllAppenders();
		 	 loggerError.removeAllAppenders();
			 request.setAttribute("mensaje", mensaje);
			 return mapping.findForward("mensaje");
		}
		
		Entidad entidad = entidadDAO.buscarPorId(idEntidad);
		entidad.setIdEntidad(idEntidad);
		entidad.setEstatus(String.valueOf(EstadoCatalogo.ELIMINADO));		
		loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction inicia eliminar entidad: " + entidad.getNombreEntidad());
		exito = entidadDAO.actualizar(entidad);
		loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction fin eliminar entidad:"+ entidad.getNombreEntidad());
		
		if (exito)
            mensaje.add("mensaje", new ActionMessage("mensaje.catalogos.exitosaEliminacion", "registro de Oficina"));
        else
            mensaje.add("mensaje", new ActionMessage("errors.catalogos.eliminar", "registro de Oficina"));
		loggerError.error(FechaUtil.getHoraActual()+"_EntidadesAction fin eliminar error");
		request.setAttribute("mensaje", mensaje);
		loggerInfo.info(FechaUtil.getHoraActual()+"_EntidadesAction eliminar - FIN***");
		
		loggerInfo.removeAllAppenders();
 		loggerError.removeAllAppenders();
		return mapping.findForward("mensaje");
    	
		}catch(Exception e){
			mensaje.add("mensaje", new ActionMessage("texto.etiqueta.error.log"));
    		loggerError.error(FechaUtil.getHoraActual()+"_Fallo EntidadesAction eliminar", e);
    		loggerError.error(FechaUtil.getHoraActual()+"_EntidadesAction eliminar - FIN***");
    		loggerInfo.removeAllAppenders();
    		loggerError.removeAllAppenders();
    		request.setAttribute("mensaje", mensaje);
    		return mapping.findForward("mensaje");
    	}
}
    
}
