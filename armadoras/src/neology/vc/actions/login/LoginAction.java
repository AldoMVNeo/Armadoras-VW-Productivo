package neology.vc.actions.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neology.modelo.dto.Usuario;
import neology.modelo.negocio.daos.UsuarioDAO;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.recursos.GetProperties;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.vc.forms.login.LoginForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;


public class LoginAction extends Action
{	
	GetProperties prop;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException,
			ServletException
	{
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		loggerError.removeAllAppenders();
		prop = new GetProperties();
		ActionMessages errores = new ActionMessages();
		
		try{
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_LoginAction execute - INICIO***");
		request.getSession().removeAttribute("usuarioLog");
		LoginForm formulario = (LoginForm) form;
		String login = formulario.getLogin();
		String contrasena = formulario.getContrasena();
		UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
		List usuario = usuarioDAO.buscarPorLogin(login);
		Usuario usuarioLogin = null;
		formulario.reset(mapping, request);
		if (login == null || contrasena == null || login.trim().equals("")
				|| contrasena.trim().equals("") ) errores.add(
				ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.login.incorrecto"));
		else
		{
			if (usuario != null && usuario.size() > 0)
			{
				usuarioLogin = (Usuario) usuario.get(0);
				if (!usuarioLogin.getContrasena().equals(contrasena)){
					errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.login.incorrecto"));
					loggerError.error(FechaUtil.getHoraActual()+"  "
							+ "_Fallo LoginAction execute "+prop.strMessage("errors.login.incorrecto"));
				
				}
				if(usuarioLogin.getEstatus().compareTo(new Integer(0))!=0){
					errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.login.incorrecto"));
				loggerError.error(FechaUtil.getHoraActual()+"  "
							+ "_Fallo LoginAction execute "+prop.strMessage("errors.login.incorrecto"));	
				}
			}
			else {
				errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.login.incorrecto"));
				loggerError.error(FechaUtil.getHoraActual()+"  "
						+ "_Fallo LoginAction execute "+prop.strMessage("errors.login.incorrecto"));	
			}
		}
		if (errores.size() > 0)
		{
			saveErrors(request, errores);
			loggerError.error(FechaUtil.getHoraActual()+"_LoginAction execute - fallo");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.getInputForward();
		}
		else{ 
			loggerInfo.info(FechaUtil.getHoraActual()+"_LoginAction execute - FIN***");
			request.getSession().setAttribute("usuarioLog", usuarioLogin);
			}
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return mapping.findForward("cargar");
		}catch(Exception e){
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo LoginAction execute", e);
			loggerError.error(FechaUtil.getHoraActual()+"_LoginAction execute - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"texto.etiqueta.error.log"));
			saveErrors(request, errores);
			return mapping.findForward("cargar");
		}
	}
}
