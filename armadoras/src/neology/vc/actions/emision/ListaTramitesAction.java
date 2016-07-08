package neology.vc.actions.emision;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neology.modelo.dto.Usuario;
import neology.vc.actions.catalogos.usuarios.UsuariosAction;
import neology.vc.forms.emision.ListaTramitesForm;

import neology.modelo.negocio.daos.OrdenImpresionDAO;

import neology.modelo.negocio.daos.UsuarioDAO;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

public class ListaTramitesAction extends Action {
    /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */	
	
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response) throws IOException, 
                                                                      ServletException {
    	
    	Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
    	
	   loggerInfo.info(FechaUtil.getHoraActual()+"_ListaTramitesAction execute - INICIO***"); 
       ActionMessages errores = (ActionMessages)request.getAttribute("errores");
       ListaTramitesForm formulario=(ListaTramitesForm )form;
       Usuario usuario = 
            (Usuario)request.getSession().getAttribute("usuarioLog");
        OrdenImpresionDAO ordenDAO=DAOFactory.crearOrdenImpresionDAO();        
        formulario.setOrdenes(ordenDAO.buscarPorEntidad(usuario.getIdUsuario()));
        saveErrors(request, errores);
        loggerInfo.info(FechaUtil.getHoraActual()+"_ListaTramitesAction execute - FIN***"); 
        loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
        return mapping.findForward( "cargar");
    }
}
