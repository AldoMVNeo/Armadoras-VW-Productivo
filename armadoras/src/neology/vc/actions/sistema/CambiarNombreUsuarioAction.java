package neology.vc.actions.sistema;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neology.modelo.dto.Usuario;
import neology.modelo.negocio.daos.UsuarioDAO;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.recursos.GetProperties;
import neology.seguridad.Cipher;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.vc.actions.seguridad.permisos.PermisosFiltro;
import neology.vc.forms.sistema.CambiarNombreUsuarioForm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

public class CambiarNombreUsuarioAction extends DispatchAction {
	/**
	 * Carga los datos de inicio del usuario
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */

	GetProperties prop;

	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		prop = new GetProperties();

		loggerInfo.info(FechaUtil.getHoraActual()
				+ "_CambiarNombreUsuarioAction inicio - INICIO***");
		try {
			CambiarNombreUsuarioForm formulario = (CambiarNombreUsuarioForm) form;
			UsuarioDAO usuariosDAO = DAOFactory.crearUsuarioDAO();
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");
			formulario.setUsuarios(usuariosDAO.buscarUsuariosPantallaConstrasena(usuario));
			loggerInfo.info(FechaUtil.getHoraActual()
					+ "_CambiarNombreUsuarioAction inicio - FIN***");

			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("inicio");
		} catch (Exception e) {
			loggerError.error(FechaUtil.getHoraActual()
					+ "_Fallo CambiarNombreUsuarioAction inicio", e);
			loggerError.error(FechaUtil.getHoraActual()
					+ "_CambiarNombreUsuarioAction inicio - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("inicio");
		}
		
	}

	/**
	 * Actualiza el usuario
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */

	public ActionForward actualizar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		ActionMessages mensaje = new ActionMessages();
		loggerInfo.info(FechaUtil.getHoraActual()
				+ "_CambiarNombreUsuarioAction actualizar - INICIO***");

		try {
			if (this.isCancelled(request)) {
				loggerInfo.info(FechaUtil.getHoraActual()
						+ "_CambiarNombreUsuarioAction actualizar - FIN***");
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				return (mapping.findForward("cancelar"));
			}
			CambiarNombreUsuarioForm formulario = (CambiarNombreUsuarioForm) form;
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");
			UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
			
			String strUsuarioSeleccionado = request.getParameter("nombreUsuario");

			loggerInfo.info(FechaUtil.getHoraActual()
					+ "_CambiarNombreUsuarioAction inicio actualizar validar claves "+strUsuarioSeleccionado);
			if (strUsuarioSeleccionado == null || strUsuarioSeleccionado.trim().length() < 1) {
				mensaje.add("Usuario", new ActionMessage("errors.requerido", "Usuario"));
				request.setAttribute("mensaje", mensaje);
				loggerError.error(FechaUtil.getHoraActual()
						+ "_CambiarNombreUsuarioAction actualizar - FIN*** fallo");
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				return mapping.findForward("mensaje");
			} else {

				Pattern patron = Pattern.compile("[^A-Za-z0-9\\.\\_\\-]+");
				Matcher m = patron.matcher(strUsuarioSeleccionado);
				if (m.find()) {
					mensaje.add("usuario", new ActionMessage("errors.campo.caracteres.invalidos", "nombre de usuario"));
					request.setAttribute("mensaje", mensaje);
					loggerError.error(FechaUtil.getHoraActual()
							+ "_CambiarNombreUsuarioAction actualizar - FIN*** fallo");
					loggerInfo.removeAllAppenders();
					loggerError.removeAllAppenders();
					return mapping.findForward("mensaje");
				}
			}

			if (formulario.getContrasenaNueva() == null || formulario.getContrasenaNueva().trim().length() < 1) {
				mensaje.add("contrasena", new ActionMessage("errors.requerido", "contraseña nueva"));
				request.setAttribute("mensaje", mensaje);
				loggerError.error(FechaUtil.getHoraActual()
						+ "_CambiarNombreUsuarioAction actualizar - FIN*** fallo");
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				return mapping.findForward("mensaje");
			} else {
				Pattern patron = Pattern.compile("[^A-Za-z0-9\\.\\_\\-]+");
				Matcher m = patron.matcher(formulario.getContrasenaNueva());
				if (m.find()) {
					mensaje.add("contrasena",
							new ActionMessage("errors.campo.caracteres.invalidos", "contraseña nueva"));
					request.setAttribute("mensaje", mensaje);
					loggerError.error(FechaUtil.getHoraActual()
							+ "_CambiarNombreUsuarioAction actualizar - FIN*** fallo");
					loggerInfo.removeAllAppenders();
					loggerError.removeAllAppenders();
					return mapping.findForward("mensaje");
				} else if (!formulario.getContrasenaNueva().equals(formulario.getRepetirContrasena())) {
					mensaje.add("contrasena", new ActionMessage("errors.catalogos.usuario.repetir.contrasena"));
					request.setAttribute("mensaje", mensaje);
					loggerError.error(FechaUtil.getHoraActual()
							+ "_CambiarNombreUsuarioAction actualizar - FIN*** fallo");
					loggerInfo.removeAllAppenders();
					loggerError.removeAllAppenders();
					return mapping.findForward("mensaje");
				}
			}

			// if
			// (usuarioDAO.exiteUsuario(formulario.getNombreUsuario(),usuario.getIdUsuario()))
			// {
			// mensaje.add("mensaje", new ActionMessage(
			// "errors.usuario.nombreUsuario.existe",
			// formulario.getNombreUsuario()));
			//
			// request.setAttribute("mensaje", mensaje);
			// return mapping.findForward("mensaje");
			//
			// }

			// Si existe algun error se regresa a la pagina de alta de usuarios
			// if (errores.size() > 0) {
			// saveErrors(request, errores);
			// return mapping.getInputForward();
			// }
			// Usuario a editar
			loggerInfo.info(FechaUtil.getHoraActual()
					+ "_CambiarNombreUsuarioAction fin actualizar validar claves "+strUsuarioSeleccionado);
			loggerInfo.info(FechaUtil.getHoraActual()
					+ "_CambiarNombreUsuarioAction actualizar inicio "+strUsuarioSeleccionado);
			List<Usuario> usuList = usuarioDAO.buscarPorLogin(strUsuarioSeleccionado);
			Usuario usuEdit = usuList.get(0);
			usuEdit.setUsuario(strUsuarioSeleccionado);
			usuEdit.setContrasena(formulario.getContrasenaNueva());
			boolean exitoso = usuarioDAO.actualizar(usuEdit);
			mensaje = new ActionMessages();
			if (exitoso) {

				loggerInfo.info(FechaUtil.getHoraActual()
						+ "_CambiarNombreUsuarioAction actualizar fin "+strUsuarioSeleccionado);
				mensaje.add("mensaje", new ActionMessage("mensaje.catalogos.exitosoActualizacion", "Usuario"));
			} else
				mensaje.add("mensaje", new ActionMessage("errors.catalogos.actualizar", "Usuario"));
			request.setAttribute("mensaje", mensaje);
			loggerInfo.info(FechaUtil.getHoraActual()
					+ "_CambiarNombreUsuarioAction actualizar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			request.setAttribute("mensaje", mensaje);
			return mapping.findForward("mensaje");
		} catch (Exception e) {
			mensaje.add("mensaje", new ActionMessage("texto.etiqueta.error.log"));
			loggerError.error(FechaUtil.getHoraActual()
					+ "_Fallo CambiarNombreUsuarioAction actualizar", e);
			loggerError.error(FechaUtil.getHoraActual()
					+ "_CambiarNombreUsuarioAction actualizar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			request.setAttribute("mensaje", mensaje);
			return mapping.findForward("mensaje");
		}
		
	}

}
