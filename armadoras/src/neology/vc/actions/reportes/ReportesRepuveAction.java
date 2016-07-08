package neology.vc.actions.reportes;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.PrintJob;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.*;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.modelo.dto.Entidad;
import neology.modelo.dto.EstadoOI;
import neology.modelo.dto.FormatoHistorico;
import neology.modelo.dto.OrdenImpresion;
import neology.modelo.dto.OrdenesHistorico;
import neology.modelo.dto.TipoFormato;
import neology.modelo.dto.Usuario;
import neology.modelo.dto.id.FormatoId;
import neology.modelo.dto.impresion.Seleccionados;
import neology.modelo.negocio.daos.EntidadDAO;
import neology.modelo.negocio.daos.EstadoDAO;
import neology.modelo.negocio.daos.EstadoOIDAO;
import neology.modelo.negocio.daos.FormatoDAO;
import neology.modelo.negocio.daos.FormatoHistoricoDAO;
import neology.modelo.negocio.daos.OrdenImpresionDAO;
import neology.modelo.negocio.daos.OrdenesHistoricoDAO;
import neology.modelo.negocio.daos.TipoFormatoDAO;
import neology.modelo.negocio.daos.UsuarioDAO;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.recursos.GetProperties;
import neology.util.FechaUtil;
import neology.util.VariablesGlobales;
import neology.util.formato.ConvertidorFolio;
import neology.vc.forms.catalogos.entidades.EntidadesForm;
import neology.vc.forms.impresion.ImpresionesForm;

import neology.vc.forms.reportes.ReportesRepuveForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;
import org.hibernate.Session;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import neology.util.Utilidades;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportesRepuveAction extends DispatchAction {
   
GetProperties prop;
   
    public ActionForward inicio(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
    	    	
    	
    	Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		loggerInfo.info(FechaUtil.getHoraActual()+"_ReportesRepuveAction inicio - INICIO***");
    	prop= new GetProperties();
		
    	try{
    	ReportesRepuveForm formulario=(ReportesRepuveForm)form;
    	formulario.setFechaInicial(FechaUtil.getFechaActual());    	    	    	    	       
    	formulario.setFechaFinal(FechaUtil.getFechaActual());
    	loggerInfo.info(FechaUtil.getHoraActual()+"_ReportesRepuveAction inicio - FIN***");
    	loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
    	return mapping.findForward("inicio");
    	} catch (Exception e) {
    		loggerError.error(FechaUtil.getHoraActual()+"_Fallo ReportesRepuveAction inicio", e);
    		loggerError.error(FechaUtil.getHoraActual()+"_ReportesRepuveAction inicio - FIN***");
    		loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("inicio");
    	}
    }
    
   
    
    public ActionForward generarReporteGrupos(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response) throws IOException, 
                                                 ServletException, JRException {
    	
    	
    	Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		loggerInfo.info(FechaUtil.getHoraActual()+"_ReportesRepuveAction generarReporteGrupos - INICIO***");
    	try{
    	ReportesRepuveForm formulario=(ReportesRepuveForm)form;
    	Map parametros = new HashMap();
    	Session session = null;
    	int grupo=formulario.getGrupo();
    	int estado=formulario.getEstado(); 
    	String fechaDesde=formulario.getFechaInicial();
    	String fechaHasta=formulario.getFechaFinal();
    	OrdenImpresionDAO ordenDAO=DAOFactory.crearOrdenImpresionDAO();
    	OrdenesHistoricoDAO ordenHistoricoDAO=DAOFactory.crearOrdenesHistoricoDAO();
    	TipoFormatoDAO tipoDAO=DAOFactory.crearTipoFormatoDAO();
    	FormatoDAO formatoDAO = DAOFactory.crearFormatoDAO();
    	FormatoHistoricoDAO historicoDAO=DAOFactory.crearFormatoHistoricoDAO();
    	EstadoDAO estDAO =DAOFactory.crearEstadoDAO();
    	EstadoOIDAO estadoOIDAO=DAOFactory.crearEstadoOIDAO();
    	ActionMessages errores = new ActionMessages();
    	Usuario usuario = (Usuario)request.getSession().getAttribute("usuarioLog");
    	loggerInfo.info(FechaUtil.getHoraActual()+"_ReportesRepuveAction generarReporteGrupos inicia reporte inicio");
    	if(grupo==1){
    		List<OrdenImpresion> ordenes=ordenDAO.buscarReportePorGrupo(fechaDesde,fechaHasta,grupo,estado);
    		if(ordenes!=null && ordenes.size()>0){
    			String reporteEntrada = "WEB-INF/reportes/grupo1.jasper";
    			String tipoReporte = "application/octet-stream";
    			session=HibernateSessionFactory.getSession();
    			String path = this.getServlet().getServletContext().getRealPath(reporteEntrada);
    			JRXlsExporter exporter = new JRXlsExporter();
    			parametros.put("fechaDesde",fechaDesde); 
    			parametros.put("fechaHasta",fechaHasta);
    			parametros.put("estado",estado);
    			parametros.put("HIBERNATE_SESSION",session); 
    			File reportFile = new File(path);
    			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
    			JasperPrint print = JasperFillManager.fillReport(jasperReport, parametros);
    			response.setContentType(tipoReporte);
    			response.setHeader("Content-disposition", "inline; filename="+grupo+".xls");
    			OutputStream out = response.getOutputStream();
    			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
    			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
    			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
    			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.exportReport();                
                out.flush();         
                out.close();
                
                if(estado == 8)
                {
                	
                	formatoDAO.ActualizarGrupo(fechaDesde, fechaHasta, grupo, estado, usuario.getIdUsuario());
                	ordenDAO.actualizarGrupo(fechaDesde, fechaHasta, grupo, estado);
                	//Histórico por cada folio
                	for(int i=0;i<ordenes.size();i++){
                	FormatoHistorico formatoHistorico= new FormatoHistorico();
					formatoHistorico.setIdUsuarioModifico(usuario.getIdUsuario());
					formatoHistorico.setFechaHistorico(FechaUtil.getFechaYHoraActual());
					formatoHistorico.setFolio(ordenes.get(i).getFolio());
					formatoHistorico.setIdTipoFormato(1);
					formatoHistorico.setIdEntidad(usuario.getEntidad().getIdEntidad());
					formatoHistorico.setIdUsuarioActual(usuario.getIdUsuario());
					formatoHistorico.setEstado("9");
					formatoHistorico.setFechaRegistro(FechaUtil.getFechaYHoraActual());
					formatoHistorico.setIdOrdenImpresion(ordenes.get(i).getIdOrdenImpresion());
					historicoDAO.guardar(formatoHistorico);
					
					loggerInfo.info(FechaUtil.getHoraActual()+"_ReportesRepuveAction "
							+ "generarReporteGrupos formato historico guardado "+ordenes.get(i).getFolio());
					
					OrdenesHistorico ordenHistorico= new OrdenesHistorico();
    				ordenHistorico.setAnnoModelo(ordenes.get(i).getAnnoModelo());
    				ordenHistorico.setBarco(ordenes.get(i).getBarco());
    				ordenHistorico.setCapacidad(ordenes.get(i).getCapacidad());
    				ordenHistorico.setCilindraje(ordenes.get(i).getCilindraje());
    				ordenHistorico.setColor(ordenes.get(i).getColor());
    				ordenHistorico.setCombustible(ordenes.get(i).getCombustible());
    				ordenHistorico.setEntidad(usuario.getEntidad().getNombreEntidad());
    				ordenHistorico.setEstado(estadoOIDAO.buscarPorId("9"));
    				ordenHistorico.setFechaImpresion(ordenes.get(i).getFechaImpresion());
    				ordenHistorico.setFechaRegistro(FechaUtil.getFechaYHoraActual());
    				ordenHistorico.setFolio(ordenes.get(i).getFolio());
    				ordenHistorico.setFormatos(ordenes.get(i).getFormatos());
    				ordenHistorico.setGrupo(ordenes.get(i).getGrupo());
    				ordenHistorico.setIdEntidad(usuario.getEntidad().getIdEntidad());
    				ordenHistorico.setIdPropietario(ordenes.get(i).getIdPropietario());
    				ordenHistorico.setImprime(ordenes.get(i).getImprime());
    				ordenHistorico.setMarca(ordenes.get(i).getMarca());
    				ordenHistorico.setModelo(ordenes.get(i).getModelo());
    				ordenHistorico.setNumeroChip(ordenes.get(i).getNumeroChip());
    				ordenHistorico.setNumeroGrabaciones(ordenes.get(i).getNumeroGrabaciones());
    				ordenHistorico.setNumeroMotor(ordenes.get(i).getNumeroMotor());
    				ordenHistorico.setNumeroTramite(ordenes.get(i).getNumeroTramite());
    				ordenHistorico.setPlaca(ordenes.get(i).getPlaca());
    				ordenHistorico.setTipo(ordenes.get(i).getTipo());
    				ordenHistorico.setUso(ordenes.get(i).getUso());
    				ordenHistorico.setVin(ordenes.get(i).getVin());
    				ordenHistorico.setTipoFormato(tipoDAO.buscarPorId(new Long(1)));
    				ordenHistorico.setIdOrdenImpresion(ordenes.get(i).getIdOrdenImpresion());
    				ordenHistorico.setIdUsuarioModifico(usuario);
    				ordenHistorico.setIdUsuario(usuario);
    				ordenHistoricoDAO.guardar(ordenHistorico);
    				
    				loggerInfo.info(FechaUtil.getHoraActual()+"_ReportesRepuveAction "
    						+ "generarReporteGrupos orden guardada "+ordenes.get(i).getIdOrdenImpresion());
                	}
                }
    		}
    		else
        		errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.reportes.nodatos"));
    		    loggerError.error(FechaUtil.getHoraActual()+"_ReportesRepuveAction generarReporteGrupos fallo");
    	}
    	else
    	if(grupo==2){
    	List<OrdenImpresion> ordenes=ordenDAO.buscarReportePorGrupo(fechaDesde,fechaHasta,grupo,estado);
    	if(ordenes!=null && ordenes.size()>0){   	
    	    String fechaActual= FechaUtil.getFechaYHoraActual("dd-MM-yyyy HH:mm:ss");
    		String nombreArchivo = "grupo2_"+fechaActual.replace(":","_")+".txt";
    		File archivo = new File(nombreArchivo);
    		BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
    		String nueva_linea = System.getProperty("line.separator");
    		for(OrdenImpresion orden:ordenes){
    			bw.write(orden.getVin()+"\t"+orden.getFolio()+"|"+orden.getNumeroChip()+nueva_linea);
    			if(estado==Integer.parseInt(EstadoOI.REVISADO)){
    		   	orden.getEstado().setEstado(EstadoOI.INFORMADO_VERIFICADO);
    			ordenDAO.actualizar(orden);
    			orden.getEstado().setEstado(EstadoOI.INFORMADO_VERIFICADO);
    			
    			if(ordenDAO.actualizar(orden)){
    				
    				OrdenesHistorico ordenHistorico= new OrdenesHistorico();
    				ordenHistorico.setAnnoModelo(orden.getAnnoModelo());
    				ordenHistorico.setBarco(orden.getBarco());
    				ordenHistorico.setCapacidad(orden.getCapacidad());
    				ordenHistorico.setCilindraje(orden.getCilindraje());
    				ordenHistorico.setColor(orden.getColor());
    				ordenHistorico.setCombustible(orden.getCombustible());
    				ordenHistorico.setEntidad(usuario.getEntidad().getNombreEntidad());
    				ordenHistorico.setEstado(estadoOIDAO.buscarPorId("9"));
    				ordenHistorico.setFechaImpresion(orden.getFechaImpresion());
    				ordenHistorico.setFechaRegistro(FechaUtil.getFechaYHoraActual());
    				ordenHistorico.setFolio(orden.getFolio());
    				ordenHistorico.setFormatos(orden.getFormatos());
    				ordenHistorico.setGrupo(orden.getGrupo());
    				ordenHistorico.setIdEntidad(usuario.getEntidad().getIdEntidad());
    				ordenHistorico.setIdPropietario(orden.getIdPropietario());
    				ordenHistorico.setImprime(orden.getImprime());
    				ordenHistorico.setMarca(orden.getMarca());
    				ordenHistorico.setModelo(orden.getModelo());
    				ordenHistorico.setNumeroChip(orden.getNumeroChip());
    				ordenHistorico.setNumeroGrabaciones(orden.getNumeroGrabaciones());
    				ordenHistorico.setNumeroMotor(orden.getNumeroMotor());
    				ordenHistorico.setNumeroTramite(orden.getNumeroTramite());
    				ordenHistorico.setPlaca(orden.getPlaca());
    				ordenHistorico.setTipo(orden.getTipo());
    				ordenHistorico.setUso(orden.getUso());
    				ordenHistorico.setVin(orden.getVin());
    				ordenHistorico.setTipoFormato(tipoDAO.buscarPorId(new Long(1)));
    				ordenHistorico.setIdOrdenImpresion(orden.getIdOrdenImpresion());
    				ordenHistorico.setIdUsuarioModifico(usuario);
    				ordenHistorico.setIdUsuario(usuario);
    				ordenHistoricoDAO.guardar(ordenHistorico);
    				
    				loggerInfo.info(FechaUtil.getHoraActual()+"_ReportesRepuveAction "
    						+ "generarReporteGrupos orden guardada "+orden.getIdOrdenImpresion());
    				
    			}
    			
    			}
    		}
    		
    		if(formatoDAO.ActualizarGrupo(fechaDesde, fechaHasta, grupo, estado, usuario.getIdUsuario())){
    			
    			for(OrdenImpresion orden:ordenes){
    			FormatoHistorico formatoHistorico= new FormatoHistorico();
				formatoHistorico.setIdUsuarioModifico(usuario.getIdUsuario());
				formatoHistorico.setFechaHistorico(FechaUtil.getFechaYHoraActual());
				formatoHistorico.setFolio(orden.getFolio());
				formatoHistorico.setIdTipoFormato(1);
				formatoHistorico.setIdEntidad(usuario.getEntidad().getIdEntidad());
				formatoHistorico.setIdUsuarioActual(usuario.getIdUsuario());
				formatoHistorico.setEstado("9");
				formatoHistorico.setFechaRegistro(FechaUtil.getFechaYHoraActual());
				formatoHistorico.setIdOrdenImpresion(orden.getIdOrdenImpresion());
				historicoDAO.guardar(formatoHistorico);
				
				loggerInfo.info(FechaUtil.getHoraActual()+"_ReportesRepuveAction "
						+ "generarReporteGrupos formato historico guardado "+orden.getFolio());
    			}
    		}
    		
    		
    		
    		bw.close();
    		if(archivo.length()>0){
    		response.setContentType("application/octet-stream");
    		response.setHeader("Content-disposition",
    				"attachment;filename="+nombreArchivo);
    		OutputStream out = response.getOutputStream();
    		byte[] bytes = new byte[1000];
    		int read = 0;
    		FileInputStream fis = new FileInputStream(archivo);
    		while ((read = fis.read(bytes)) != -1) {
    			out.write(bytes, 0, read);
    		}
    		loggerInfo.info(FechaUtil.getHoraActual()+"_ReportesRepuveAction generarReporteGrupos fin genera reporte ");
    		out.flush();
    		out.close();
    		}
    		
    	}
    	   	
    	else
    		errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.reportes.nodatos"));
    	loggerError.error(FechaUtil.getHoraActual()+"_ReportesRepuveAction generarReporteGrupos - FIN*** " + prop.strMessage("errors.reportes.nodatos"));
    	}
    	if (errores.size() > 0)
		{
			saveErrors(request, errores);
			loggerError.error(FechaUtil.getHoraActual()+"_ReportesRepuveAction generarReporteGrupos - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.getInputForward();
		}   	
    	 
    	loggerInfo.info(FechaUtil.getHoraActual()+"_ReportesRepuveAction generarReporteGrupos - FIN***");
    	loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
    	return null;
    	} catch (Exception e) {
    		loggerError.error(FechaUtil.getHoraActual()+"_Fallo ReportesRepuveAction generarReporteGrupos", e);
    		loggerError.error(FechaUtil.getHoraActual()+"_ReportesRepuveAction generarReporteGrupos - FIN***");
    		loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return null;
    	}
    }
   
    
}
