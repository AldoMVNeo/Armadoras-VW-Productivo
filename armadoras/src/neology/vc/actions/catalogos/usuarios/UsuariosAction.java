package neology.vc.actions.catalogos.usuarios;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neology.modelo.dto.EstadoCatalogo;
import neology.modelo.dto.Perfil;
import neology.modelo.dto.Usuario;
import neology.modelo.dto.Entidad;
import neology.modelo.negocio.daos.EstadoCatalogoDAO;
import neology.modelo.negocio.daos.PerfilDAO;
import neology.modelo.negocio.daos.UsuarioDAO;
import neology.modelo.negocio.daos.EntidadDAO;

import neology.modelo.negocio.servicios.DAOFactory;

import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.util.formato.ConvertidorFolio;
import neology.vc.actions.catalogos.tipoFormato.TipoFormatoAction;
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

public class UsuariosAction extends DispatchAction {
	/**
	 * Carga las entidades, tipos de formatos para realizar el cambio de
	 * estadode los formatos
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward cargar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		ActionMessages errores = new ActionMessages();
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction cargar - INICIO***"); 
		try{
		UsuariosForm formulario = (UsuariosForm) form;
		UsuarioDAO usuariosDAO = DAOFactory.crearUsuarioDAO();
		PerfilDAO perfilesDAO = DAOFactory.crearPerfilDAO();
		EstadoCatalogoDAO estadoCatalogoDAO = DAOFactory.crearEstadoCatalogoDAO(); 
		formulario.setPerfiles(perfilesDAO.buscarPerfiles());
		Usuario usuario=(Usuario)request.getSession().getAttribute("usuarioLog");
		request.setAttribute("usuarios", usuariosDAO.buscarUsuariosPantalla(usuario));
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction cargar - FIN***"); 
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return mapping.findForward("cargar");
		
		}catch(Exception e){
		
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo UsuariosAction cargar", e);
			loggerError.error(FechaUtil.getHoraActual()+"_UsuariosAction cargar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("texto.etiqueta.error.log"));
			saveErrors(request, errores);
			return mapping.findForward("cargar");
		}
	}

	public ActionForward nuevo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		ActionMessages errores = new ActionMessages();
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction nuevo - INICIO***"); 
		try{
		UsuariosForm formulario = (UsuariosForm) form;
		UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
		EntidadDAO entidadesDAO = DAOFactory.crearEntidadDAO();
		PerfilDAO perfilesDAO = DAOFactory.crearPerfilDAO();
		EstadoCatalogoDAO estadoCatalogoDAO = DAOFactory.crearEstadoCatalogoDAO(); 
		Usuario usuario=(Usuario)request.getSession().getAttribute("usuarioLog");
		
		if(usuario.getPerfil().getIdPerfil()==1){	
			
		 formulario.setPerfiles(perfilesDAO.buscarPerfiles());
		 formulario.setEntidades(entidadesDAO.buscarEntidades());
		 formulario.setEstadosCatalogos(estadoCatalogoDAO.obtenerEstados(true));
		 
		  }else{
			
		 formulario.setPerfiles(perfilesDAO.buscarPerfilesPantalla(usuario));
		 formulario.setEntidades(entidadesDAO.buscarEntidadesPantallaAlta(usuario));
 
		}
		loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction nuevo - FIN***"); 
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return mapping.findForward("nuevo");
		
		}catch(Exception e){
			
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo UsuariosAction nuevo", e);
			loggerError.error(FechaUtil.getHoraActual()+"_UsuariosAction nuevo - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("texto.etiqueta.error.log"));
			saveErrors(request, errores);
			return mapping.findForward("nuevo");
		}
	}

	public ActionForward realizarAlta(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		ActionMessages mensaje = new ActionMessages();
		try{
			
			loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction realizarAlta - INICIO***"); 
		
			if(this.isCancelled(request)){	
			loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction realizarAlta - FIN***"); 
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return(mapping.findForward("cancelar"));
		}	
		ActionMessages errores = new ActionMessages();
		UsuariosForm formulario = (UsuariosForm) form;

		// Datos del Usuario
		String nombre = formulario.getNombre();
		String apellidoPaterno = formulario.getApellidoPaterno();
		String apellidoMaterno = formulario.getApellidoMaterno();
		String nombreUsuario = formulario.getUsuario();
		String contrasena = formulario.getContrasena();

		Long idPerfil = formulario.getIdPerfil();
		UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
		EntidadDAO entidadDAO = DAOFactory.crearEntidadDAO();
		PerfilDAO perfilDAO = DAOFactory.crearPerfilDAO();
		Entidad entidad = entidadDAO.buscarPorId(new Long(1));
		Perfil perfil = perfilDAO.buscarPorId(idPerfil);
		EstadoCatalogoDAO estadoCatalogoDAO = DAOFactory.crearEstadoCatalogoDAO();
		EstadoCatalogo estadoCatalogo= estadoCatalogoDAO.buscarPorId(EstadoCatalogo.ACTIVO);	
		

		// Se realiza la validacion que no exista otro nombre de usuario
		List usuarios = usuarioDAO.buscarPorLogin(nombreUsuario);
		if (usuarios != null && usuarios.size() > 0) {
			errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.usuario.nombreUsuario.existe", nombreUsuario));
			loggerError.error(FechaUtil.getHoraActual()+"_UsuariosAction realizarAlta - FIN***"); 
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			saveErrors(request, errores);
			return mapping.findForward("mensaje");
		}

		// Si existe algun error se regresa a la pagina de alta de usuarios
		if(usuarios.size()>0){
			
			mensaje.add("mensaje", new ActionMessage(
						"errors.usuario.nombreUsuario.existe", nombreUsuario));
			request.setAttribute("mensaje", mensaje);
			loggerError.error(FechaUtil.getHoraActual()+"_UsuariosAction realizarAlta - FIN***"); 
			
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("mensaje");
				
			}

		// Se guarda en la base de datos el usuario creado
		Usuario usuario = new Usuario();
		usuario.setEntidad(entidad);
		usuario.setNombre(nombre);
		usuario.setApellidoPaterno(apellidoPaterno);
		usuario.setApellidoMaterno(apellidoMaterno);
		usuario.setPerfil(perfil);		
		usuario.setUsuario(nombreUsuario);
		usuario.setContrasena(contrasena);
		usuario.setEstatus(estadoCatalogo.getEstado()); 
		usuario.setFechaAlta(FechaUtil.getFechaYHoraActual());
		loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction inicio realizarAlta usuario: "+nombreUsuario); 
		boolean exito = usuarioDAO.guardar(usuario);
		loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction fin realizarAlta usuario: "+nombreUsuario); 
		if (exito)
			mensaje.add("mensaje", new ActionMessage(
					"mensaje.guardar.exitoso.Usuario", nombreUsuario));
		else
			mensaje.add("mensaje", new ActionMessage(
					"errors.catalogos.guardar.usuario", nombreUsuario));
		loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction realizarAlta - FIN***"); 
		request.setAttribute("mensaje", mensaje);
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return mapping.findForward("mensaje");
		}catch(Exception e){
			
			mensaje.add("mensaje", new ActionMessage(
					"texto.etiqueta.error.log"));
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo UsuariosAction realizarAlta", e);
			loggerError.error(FechaUtil.getHoraActual()+"_UsuariosAction realizarAlta - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("mensaje");
		}
	}

	public ActionForward modificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		ActionMessages mensaje = new ActionMessages();
		
		try{
			
			    loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction modificar - INICIO***"); 
				UsuarioDAO usuariosDAO = DAOFactory.crearUsuarioDAO();
				EntidadDAO entidadesDAO = DAOFactory.crearEntidadDAO();
				PerfilDAO perfilesDAO = DAOFactory.crearPerfilDAO();
				EstadoCatalogoDAO estadoCatalogoDAO = DAOFactory.crearEstadoCatalogoDAO(); 
				Usuario usuario=(Usuario)request.getSession().getAttribute("usuarioLog");
				
				UsuariosForm formulario = (UsuariosForm) form;

				UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
				Long idUsuario = Long.valueOf(request.getParameter("idUsuario"));
				Usuario usuarios = usuarioDAO.buscarPorId(idUsuario);
				formulario.setIdUsuario(idUsuario);
				formulario.setNombre(usuarios.getNombre());
				formulario.setApellidoPaterno(usuarios.getApellidoPaterno());
				formulario.setApellidoMaterno(usuarios.getApellidoMaterno());
				formulario.setIdPerfil(usuarios.getPerfil().getIdPerfil());
				formulario.setIdEntidad(usuarios.getEntidad().getIdEntidad());
				formulario.setEstatus(usuarios.getEstatus());
				formulario.setPerfiles(perfilesDAO.buscarPerfilesPantalla(usuarios));
				formulario.setEntidades(entidadesDAO.buscarEntidadesPantalla(usuarios));
				formulario.setEstadosCatalogos(estadoCatalogoDAO.obtenerEstadosPantalla(usuarios,true));
				
				loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction modificar - FIN***"); 
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				return mapping.findForward("modificar");
				}catch(Exception e){
					mensaje.add("mensaje", new ActionMessage(
							"texto.etiqueta.error.log"));
					loggerError.error(FechaUtil.getHoraActual()+"_Fallo UsuariosAction modificar", e);
					loggerError.error(FechaUtil.getHoraActual()+"_UsuariosAction modificar - FIN***");
					loggerInfo.removeAllAppenders();
					loggerError.removeAllAppenders();
					return mapping.findForward("modificar");
				}
	}

	public ActionForward actualizar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		ActionMessages mensaje = new ActionMessages();
		
		try{
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction actualizar - INICIO***"); 
		Boolean exito = false;
		UsuariosForm formulario = (UsuariosForm) form;
		UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
		PerfilDAO perfilDAO = DAOFactory.crearPerfilDAO();

		Long idUsuario = formulario.getIdUsuario();
		String nombre = formulario.getNombre();
		String apellidoPaterno = formulario.getApellidoPaterno();
		String apellidoMaterno = formulario.getApellidoMaterno();
		String usuario = formulario.getUsuario();
		String contrasena = formulario.getContrasena();
		Integer estatus = formulario.getEstatus();
		Long idPerfil = formulario.getIdPerfil();
		Long idEntidad = formulario.getIdEntidad();
		
		// Se realiza la validacion que no exista otro nombre de usuario
		loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction inicio actualizar validar usuario: "+usuario); 
		List us = usuarioDAO.buscarUsuarios();
		
		for(int i=0;i<us.size();i++){
			
			Usuario u= (Usuario)us.get(i);
			
			if(u.getUsuario().equals(usuario)){
				
				if(idUsuario!=Long.valueOf(u.getIdUsuario())){
					
				mensaje.add("mensaje", new ActionMessage(
							"errors.usuario.nombreUsuario.existe", usuario));
				request.setAttribute("mensaje", mensaje);
				
				loggerError.error(FechaUtil.getHoraActual()+"_UsuariosAction actualizar - FIN***"); 
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				return mapping.findForward("mensaje");
					
				}
				
			}
			
		}
		loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction fin actualizar validar usuario: "+usuario);
		Usuario usuarios = usuarioDAO.buscarPorId(idUsuario);
		usuarios.setIdUsuario(idUsuario);
		usuarios.setNombre(nombre);
		usuarios.setApellidoPaterno(apellidoPaterno);
		usuarios.setApellidoMaterno(apellidoMaterno);
		usuarios.setEstatus(estatus);
		usuarios.setIdPerfil(idPerfil);
		usuarios.setPerfil(perfilDAO.buscarPorId(idPerfil));
		usuarios.setEntidad(new Entidad(idEntidad));
		usuarios.setUsuario(usuario);
		loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction inicio actualizar usuario: "+usuario);
		exito = usuarioDAO.actualizar(usuarios);
		
		if (exito)
			mensaje.add("mensaje", new ActionMessage(
					"mensaje.catalogos.exitosoActualizacion", "Usuario"));
		else
			mensaje.add("mensaje", new ActionMessage(
					"errors.catalogos.actualizar", "Usuarios"));
		request.setAttribute("mensaje", mensaje);
		loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction fin actualizar usuario: "+usuario);
		loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction actualizar - FIN***");
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return mapping.findForward("mensaje");
		}catch (Exception e){
			
			mensaje.add("mensaje", new ActionMessage("texto.etiqueta.error.log"));
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo UsuariosAction actualizar", e);
			loggerError.error(FechaUtil.getHoraActual()+"_UsuariosAction actualizar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("mensaje");
		}
	}

	public ActionForward validar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		return mapping.findForward("cargar");
	}

	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		ActionMessages mensaje = new ActionMessages();
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction eliminar - INICIO***");
		try{
		Boolean exito = false;
		Usuario usuario=(Usuario)request.getSession().getAttribute("usuarioLog");
		UsuariosForm formulario = (UsuariosForm) form;
		UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
		EstadoCatalogoDAO estadoCatalogoDAO = DAOFactory.crearEstadoCatalogoDAO();

		Long idUsuario = Long.valueOf(request.getParameter("idUsuario"));
		
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction inicio eliminar validar usuario: "+idUsuario);
		//No se puede eliminar el Usuario central
		if(idUsuario==1){
			mensaje.add("mensaje", new ActionMessage(
					"mensaje.ordenes.eliminacionAdminCentral", request.getParameter("idUsuario")));
			request.setAttribute("mensaje", mensaje);
			loggerError.error(FechaUtil.getHoraActual()+"_UsuariosAction eliminar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("mensaje");
			
		}
		
		//No se puede eliminar el Usuario logueado
				if(idUsuario==usuario.getIdUsuario()){
					mensaje.add("mensaje", new ActionMessage(
							"mensaje.ordenes.eliminacionLogin",usuario.getUsuario()));
					request.setAttribute("mensaje", mensaje);
					loggerError.error(FechaUtil.getHoraActual()+"_UsuariosAction eliminar - FIN***");
					loggerInfo.removeAllAppenders();
					loggerError.removeAllAppenders();
					return mapping.findForward("mensaje");
					
				}
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction fin eliminar validar usuario: "+idUsuario);
		Usuario usuarios = usuarioDAO.buscarPorId(idUsuario);
		usuarios.setIdUsuario(idUsuario);
		usuarios.setEstatus(3);
		loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction inicio eliminar usuario: "+idUsuario);
		exito = usuarioDAO.actualizar(usuarios);
		loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction fin eliminar usuario: "+idUsuario);
		mensaje = new ActionMessages();
		if (exito){
						
			mensaje.add("mensaje", new ActionMessage(
					"mensaje.catalogos.exitosaEliminacion", "Usuario"));
		}
		else
			mensaje.add("mensaje", new ActionMessage(
					"errors.catalogos.actualizar", "Usuario"));
		loggerInfo.info(FechaUtil.getHoraActual()+"_UsuariosAction eliminar - FIN***");
		request.setAttribute("mensaje", mensaje);
		
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return mapping.findForward("mensaje");
		}catch(Exception e){
			mensaje.add("mensaje", new ActionMessage("texto.etiqueta.error.log"));
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo UsuariosAction eliminar", e);
			loggerError.error(FechaUtil.getHoraActual()+"_UsuariosAction eliminar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("mensaje");
		}
	}
	
		public Boolean habilitaComboPerfil(HttpServletRequest request){
		
		Usuario usuario=(Usuario)request.getSession().getAttribute("usuarioLog");
		
		if(usuario.getIdUsuario()==1){
			return true;
		}else{
			return false;
		}
		
		
	}
}
