package neology.vc.actions.excepciones;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

import neology.util.FechaUtil;
import neology.util.Utilidades;

public class ServletExceptionHandler extends ExceptionHandler
{
	private static final Log log = LogFactory.getLog(ServletExceptionHandler.class);

	/**
	 * This is the main action called from the Struts framework.
	 * 
	 * @param mapping The ActionMapping used to select this instance.
	 * @param form The optional ActionForm bean for this request.
	 * @param request The HTTP Request we are processing.
	 * @param response The HTTP Response we are processing.
	 */
	public ActionForward execute(Exception e, ExceptionConfig eConf,
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ServletException
	{
		
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		loggerInfo.info(FechaUtil.getHoraActual()+"_ServletExceptionHandler execute - INICIO***");
		// Informamos mediante logging acerca de la excepción ocurrida
		loggerError.error(e.getMessage(), e);
		loggerInfo.info(FechaUtil.getHoraActual()+"_ServletExceptionHandler execute - FIN***");
		request.setAttribute("ServletException", e.getStackTrace());
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return new ActionForward(eConf.getPath());
	}

}
