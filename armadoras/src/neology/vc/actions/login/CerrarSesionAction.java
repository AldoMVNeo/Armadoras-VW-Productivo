package neology.vc.actions.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.vc.actions.handheld.usuarios.GenerarUsuariosAction;

public class CerrarSesionAction extends Action{
		
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response) throws IOException, 
                                                 ServletException {
		
	Logger loggerInfo = Utilidades.loggerInfo();
	loggerInfo.info(FechaUtil.getHoraActual()+"   CerrarSesionAction execute - INICIO***");
	request.getSession().removeAttribute("usuarioLog");
	loggerInfo.info(FechaUtil.getHoraActual()+"   CerrarSesionAction execute - FIN***");
	loggerInfo.removeAllAppenders();
	return mapping.findForward("cerrarSesion");
	}

}
