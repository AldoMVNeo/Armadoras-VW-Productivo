package neology.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.util.Utilidades;
import neology.util.VariablesGlobales;



public class InicioServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7448575086655038891L;
	private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);        

        Logger loggerInfoDAO = Utilidades.loggerInfo();
		Logger loggerErrorDAO = Utilidades.loggerError();
        
        try{
        //Se obtienen los parametros de configuracion
        loggerInfoDAO.info("_InicioServlet - INICIO***");
        String reporteTituloConChip=config.getInitParameter("reporteTituloConChip");
        String reporteConstanciaREPUVE=config.getInitParameter("reporteConstanciaREPUVE");
        String reporteDetalleConstanciaREPUVE=config.getInitParameter("reporteDetalleConstanciaREPUVE");
        boolean useCache = Boolean.parseBoolean(config.getInitParameter("usePdfCache"));
        String pdfPath = config.getInitParameter("pdfPath");
        String pdfDir = config.getInitParameter("pdfDir");
        String reporteDetalleConstanciaFoliosREPUVE=config.getInitParameter("reporteDetalleConstanciaFoliosREPUVE");
        String camposVaciosReporte=config.getInitParameter("camposVaciosReporte");
        String strRangoFechas=config.getInitParameter("strRangoFechas");
        String noDiasResp=config.getInitParameter("noDiasResp");
        String noMesesResp=config.getInitParameter("noMesesResp");
        String noDiasConfigResp=config.getInitParameter("noDiasConfigResp");
        String diaConfigResp=config.getInitParameter("diaConfigResp");
        String dirLogs=config.getInitParameter("dirLogs");
        String esquemaProd=config.getInitParameter("esquemaProd");
        String esquemaRespaldo=config.getInitParameter("esquemaRespaldo");
        String diasRespaldar=config.getInitParameter("diasRespaldar");
        
        //Se asignan los valores anteriores a las Variables globales del Sistema
        VariablesGlobales.setPdfCached(useCache);
        VariablesGlobales.setReporteTituloPropiedadConChip(reporteTituloConChip);
        VariablesGlobales.setReporteConstanciaInscripcionREPUVE(reporteConstanciaREPUVE);
        VariablesGlobales.setReporteDetalleConstanciaREPUVE(reporteDetalleConstanciaREPUVE);
        VariablesGlobales.setPdfDir(pdfDir);
        VariablesGlobales.setPdfPath(pdfPath);   
        VariablesGlobales.setReporteDetalleConstanciaFoliosREPUVE(reporteDetalleConstanciaFoliosREPUVE);
        VariablesGlobales.setCamposVaciosReporte(camposVaciosReporte);
        VariablesGlobales.setStrRangoFechas(strRangoFechas);
        VariablesGlobales.setDirLogs(dirLogs);
        VariablesGlobales.setEsquemaProd(esquemaProd);
        VariablesGlobales.setEsquemaRespaldo(esquemaRespaldo);
        VariablesGlobales.setDiasRespaldar(diasRespaldar);
        
        loggerInfoDAO.info("_InicioServlet - FIN***");
        loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
        }
        catch (Exception e){
        	e.printStackTrace();
        	loggerErrorDAO.error("_Fallo InicioServlet ",e);
        	loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
        }
    }   
}
