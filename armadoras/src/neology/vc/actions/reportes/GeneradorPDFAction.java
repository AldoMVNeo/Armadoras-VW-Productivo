package neology.vc.actions.reportes;

import java.io.IOException;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.sql.Connection;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neology.vc.actions.emision.grabacion.GrabacionChipAction;

import neology.vc.forms.emision.impresion.ImpresionForm;

import neology.hibernate.sesion.HibernateSessionFactory;

import neology.modelo.negocio.daos.BaseHibernateDAO;
import neology.modelo.negocio.daos.OrdenImpresionDAO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import net.sf.jasperreports.engine.export.JRPdfExporter;

import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.struts.util.MessageResources;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import neology.modelo.negocio.interfaces.IBaseHibernateDAO;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;

public class GeneradorPDFAction extends Action {
    private static final Log log = LogFactory.getLog(GeneradorPDFAction.class);

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
		loggerInfo.info(FechaUtil.getHoraActual()+"_GeneradorPDFAction execute - INICIO***");
        Map parametros = (Map)request.getAttribute("parametros");
        String reporteEntrada = (String)request.getAttribute("reporteEntrada");
        String nombreReporte=(String)request.getAttribute("reporteSalida");
        String tipoReporte = (String)request.getAttribute("tipoReporte");
        String forward=(String)(request.getAttribute("tipoReporte")==null?null:request.getAttribute("forward"));
       // List datos=(List)request.getAttribute("datosReporte");
        Session session = null;
        try {
        	session=HibernateSessionFactory.getSession();
            String path = 
                this.getServlet().getServletContext().getRealPath(reporteEntrada);          
                                    
                                    // Javascript para impresión directa del documento PDF
                                    StringBuffer javascript = new StringBuffer();
                                    javascript.append("pp = this.getPrintParams();fv = pp.constants.flagValues; pp.flags |= (fv.suppressCenter | fv.suppressRotate);");
                                    javascript.append("app.alert({cMsg: 'Coloque las Constancias de Inscripción REPUVE en la impresora y presione OK',cTitle: 'Impresión automática', nIcon: 1, nType: 0}); ");
                                    //javascript.append("this.print({printParams: \" & pp &\", bUI: false, bSilent: true, nStart: 0, nEnd: 0, bShrinkToFit: false}); ");
                                    javascript.append("this.print({printParams: \" & pp &\", bUI: false, bSilent: true,  bShrinkToFit: false}); ");
                                    javascript.append("app.beep(3);");
            JRPdfExporter exporter = new JRPdfExporter();
                                    exporter.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT, javascript.toString()); 

               parametros.put("HIBERNATE_SESSION",session); 
               //JasperReport report = JasperCompileManager.compileReport(path);
                                    //JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(datos); 
               File reportFile = new File(path);
               JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
               JasperPrint print = JasperFillManager.fillReport(jasperReport, parametros);
//            JasperPrint print = JasperFillManager.fillReport(report,parametros,session.connection());
            
            response.setContentType(tipoReporte);          
            response.setHeader("Content-disposition", "inline; filename="+nombreReporte+".pdf");
            OutputStream out = response.getOutputStream();
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
           // byte[] bytes = JasperExportManager.exportReportToPdf(print);
            //response.setContentLength(bytes.length);          
            //out.write(bytes);          
            exporter.exportReport();
            
            out.flush();         
            out.close();
            
           
            //response.setContentLength(bytes.length); 
                                   

          // byte[] bytes = JasperExportManager.exportReportToPdf(print);  
        //  response.setContentType(tipoReporte);
          //  response.setHeader("Content-disposition", "inline; filename="+nombreReporte+".pdf");
            //JasperExportManager.exportReportToPdfStream(print,out);
           // response.setHeader("Content-disposition", "attachment; filename="+nombreReporte);
            //response.setContentType(tipoReporte);       
            
           // response.setContentLength(bytes.length);           
           // out.write(bytes);           
            //out.flush();          
            //out.close(); 
            //JasperRunManager.runReportToPdf(path,parametros,ds);
           /* session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            Connection conexion = 
                HibernateSessionFactory.getSession().connection();
            parametros.put("HIBERNATE_SESSION", session);
            String path = 
                this.getServlet().getServletContext().getRealPath(reporteEntrada);
            JasperReport reporte = JasperCompileManager.compileReport(path);
            JasperPrint impresion = 
                JasperFillManager.fillReport(reporte, parametros, conexion);
            OutputStream salida = response.getOutputStream();
            byte[] bytes = JasperExportManager.exportReportToPdf(impresion);
            response.setHeader("Content-disposition", "attachment; filename="+nombreReporte);
            response.setContentType(tipoReporte);
            response.setContentLength(bytes.length);
            salida.write(bytes);
            salida.flush();
            salida.close();*/
        } catch (JRException e) {
            // Muestra stack trace en el navegador 
        	loggerError.error(FechaUtil.getHoraActual()+"_Fallo GeneradorPDFAction execute", e);
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            response.setContentType("text/plain");
            response.getOutputStream().print(stringWriter.toString());
            loggerInfo.removeAllAppenders();
    		loggerError.removeAllAppenders();
    		return null;
        } 
        finally {
        	if(session!=null)
            session.close();
        }
        loggerInfo.info(FechaUtil.getHoraActual()+"_GeneradorPDFAction execute - FIN***");
        loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
        return null;
    }
}
