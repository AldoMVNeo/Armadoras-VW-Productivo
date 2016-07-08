package neology.vc.actions.emision.impresion;


import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neology.vc.actions.catalogos.usuarios.UsuariosAction;
import neology.vc.forms.emision.impresion.ImpresionForm;

import neology.modelo.negocio.daos.OrdenImpresionDAO;

import neology.modelo.negocio.servicios.DAOFactory;
import neology.hibernate.sesion.HibernateSessionFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.util.VariablesGlobales;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

public class ImpresionFormatosAction extends Action {
   
    private static String reporteTituloConChip = "reporteTituloConChip";

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
    	
		loggerInfo.info(FechaUtil.getHoraActual()+"_ImpresionFormatosAction execute - INICIO***");
    	String nombre = request.getParameter("nombreReporte")!=null?request.getParameter("nombreReporte"):(String)request.getAttribute("nombreReporte");
        Map parametros =new HashMap();
        MessageResources mensajes = getResources(request);      
        
        //Verifica que el tipo de reporte sea un Titulo de Propiedad
        if (nombre.startsWith(reporteTituloConChip)) {
        OrdenImpresionDAO dao=DAOFactory.crearOrdenImpresionDAO();
          Long idOrden=Long.parseLong(request.getParameter("ordenId"));
          parametros.put("idOrdenImpresion",idOrden);                 
          request.setAttribute("reporteEntrada",VariablesGlobales.getReporteTituloPropiedadConChip());
          request.setAttribute("tipoReporte","application/pdf");
          request.setAttribute("reporteSalida",mensajes.getMessage("reporte.tituloPropiedadConChip.pdf")); 
          
        }
        request.setAttribute("parametros",parametros);
        loggerInfo.info(FechaUtil.getHoraActual()+"_ImpresionFormatosAction execute - FIN***");
        loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
        return mapping.findForward("generarPDF");
    }
}
