package neology.vc.actions.reportes;

import java.io.File;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.modelo.dto.ConfigJob;
import neology.modelo.dto.Entidad;
import neology.modelo.dto.Estado;
import neology.modelo.dto.EstadoOI;
import neology.modelo.dto.Formato;
import neology.modelo.dto.OrdenImpresion;
import neology.modelo.dto.TipoFormato;
import neology.modelo.dto.Usuario;
import neology.modelo.dto.id.FormatoId;
import neology.modelo.negocio.daos.ConfiguracionHistoricoDAO;
import neology.modelo.negocio.daos.EntidadDAO;
import neology.modelo.negocio.daos.EstadoDAO;
import neology.modelo.negocio.daos.FormatoDAO;
import neology.modelo.negocio.daos.OrdenImpresionDAO;
import neology.modelo.negocio.daos.RespaldoHistoricoDAO;
import neology.modelo.negocio.daos.TipoFormatoDAO;
import neology.modelo.negocio.daos.UsuarioDAO;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.recursos.GetProperties;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.util.VariablesGlobales;
import neology.util.formato.ConvertidorFolio;
import neology.vc.forms.reportes.ReporteConstanciasIForm;
import neology.vc.forms.reportes.ReporteGeneraIDatosResponsable;
import neology.vc.forms.reportes.ReporteGeneralDatosForm;
import neology.vc.forms.reportes.ReporteGeneralDatosFormGeneric;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import neology.vc.forms.reportes.ReportesRepuveForm;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.SimpleLayout;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.Session;
import org.joda.time.DateTime;
import org.joda.time.Days;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class ReporteConstanciasIAction extends DispatchAction {

	Boolean isNotEmpty = false;
	String strGeneralQuery = "";
	String strGeneralQueryBackup = "";
	String strTitulo = "";
	String strTituloFiltros = "";
	String strTituloFechas = "";
	String strColumn1 = "";
	String strColumn2 = "";
	String strColumn3 = "";
	String strColumn4 = "";
	String strColumn5 = "";
	String strColumn6 = "";
	String strColumn7 = "";
	String strColumn8 = "";
	String strColumn9 = "";
	String strColumn10 = "";
	String strNombreArchivo = "";
	String strNombreArchivoBackup = "";
	String strMensajeRangoBD = "";
	ArrayList<String> strOrden = new ArrayList<String>();
	GetProperties prop;
	public static String strFolderWarnLogs = "";
	public static String strErrLogs = "";
	public static String strDebugLogs = "";
	public static String strInfoLogs = "";
	ArrayList<String> listQuerys = new ArrayList<String>();
	String ultimoEvento = "";
	List<ReporteGeneralDatosForm> reportListUltimoEvento = new ArrayList<ReporteGeneralDatosForm>();

	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();

		loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction inicio - INICIO***");

		try {
			ReporteConstanciasIForm formulario = (ReporteConstanciasIForm) form;
			EntidadDAO entidadDAO = DAOFactory.crearEntidadDAO();
			UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
			formulario.setFechaInicial(FechaUtil.getFechaActual());
			formulario.setFechaFinal(FechaUtil.getFechaActual());
			EstadoDAO estadoDAO = DAOFactory.crearEstadoDAO();

			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");
			formulario.setEstados(estadoDAO.obtenerEstados());
			formulario.setIsReporteFolios(false);
			formulario.setIsFullReport("0");
			formulario.setEstadoImportados("0");

			if (usuario.getPerfil().getIdPerfil() == 1) {

				formulario.setEntidades(entidadDAO.buscarEntidadesReportesConstancias());

			} else {

				formulario.setEntidades(entidadDAO.buscarEntidadesPantallaAlta(usuario));

			}

			isNotEmpty = false;
			strGeneralQuery = "";
			strTitulo = "";
			listQuerys = new ArrayList<String>();
			List usuarios = usuarioDAO.buscarUsuariosPantallaConstrasena(usuario);
			ArrayList<ReporteGeneraIDatosResponsable> usuariosView = new ArrayList<>();
			formulario.setUsuarios(usuarios);
			prop = new GetProperties();
			strMensajeRangoBD = "";

			for (int i = 0; i < usuarios.size(); i++) {

				ReporteGeneraIDatosResponsable resp = new ReporteGeneraIDatosResponsable();
				Usuario usuList = (Usuario) usuarios.get(i);
				resp.setApellidoMaterno(usuList.getApellidoMaterno());
				resp.setApellidoPaterno(usuList.getApellidoPaterno());
				resp.setContrasena(usuList.getContrasena());
				resp.setEntidad(usuList.getEntidad());
				resp.setEstadoCatalogo(null);
				resp.setFechaAlta(usuList.getFechaAlta());
				resp.setFormatos(usuList.getFormatos());
				resp.setFormatosGrabacion(usuList.getFormatosGrabacion());
				resp.setIdPerfil(usuList.getIdPerfil());
				resp.setIdUsuario(usuList.getIdUsuario());
				resp.setNombre(usuList.getNombre());
				resp.setNombres(usuList.getNombres());
				resp.setNumeroCaja(usuList.getNumeroCaja());
				resp.setOrdenesImpresion(usuList.getOrdenesImpresion());
				resp.setPerfil(usuList.getPerfil());
				resp.setUsuario(usuList.getUsuario());
				resp.setUsuarioHandheld(false);
				resp.setStrNombreCompleto(resp.getIdUsuario() + " " + resp.getNombre() + " " + resp.getApellidoPaterno()
						+ " " + resp.getApellidoMaterno());
				usuariosView.add(resp);
			}

			formulario.setUsuariosView(usuariosView);
			loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction inicio - FIN***");

			// Limpiar logs
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("inicio");
		} catch (Exception e) {
			loggerError.error(FechaUtil.getHoraActual() + "_Fallo ReporteConstanciasIAction inicio " + e);
			loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction inicio - FIN***");

			// Limpiar logs
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
		}

		// Limpiar logs
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return mapping.findForward("inicio");
	}

	public ActionForward generarTotalesConstancias(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// Metodo inicial y final, recibe los datos y lanza la respuesta despues
		// del procesamiento de datos
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();

		loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction generarTotalesConstancias - INICIO***");

		try {
			ReporteConstanciasIForm formulario = (ReporteConstanciasIForm) form;
			String fechaInicial = request.getParameter("fechaInicial");
			String fechaFinal = request.getParameter("fechaFinal");
			String folioInicial = request.getParameter("folioInicial");
			String folioFinal = request.getParameter("folioFinal");
			String strOption1 = request.getParameter("strOptionHidden1");
			String strOption2 = request.getParameter("strOptionHidden2");
			String strOption3 = request.getParameter("strOptionHidden3");
			String strOption4 = request.getParameter("strOptionHidden4");
			String strOption5 = request.getParameter("strOptionHidden5");
			String strOption6 = request.getParameter("strOptionHidden6");
			String strColumnFolio = request.getParameter("strColumnFolio");
			String strColumnFecha = request.getParameter("strColumnFecha");
			String strColumnTID = request.getParameter("strColumnTID");
			String strColumnObservaciones = request.getParameter("strColumnObservaciones");
			String strColumnEstadoActual = request.getParameter("strColumnEstadoActual");
			String strColumnTipo = request.getParameter("strColumnTipo");
			String strColumnResponsable = request.getParameter("strColumnResponsable");
			String strColumnChasis = request.getParameter("strColumnChasis");
			String strColumnEntidad = request.getParameter("strColumnEntidad");
			String strColumnEvento = request.getParameter("strColumnEvento");
			String consultaBD = request.getParameter("consultaBD");
			String strOrdenColumnas = request.getParameter("strOrdenColumnas");
			String strOrdenColumnasSplit[] = strOrdenColumnas.split("\\|");
			ultimoEvento = request.getParameter("strColumnUltimo");
			listQuerys = new ArrayList<String>();
			reportListUltimoEvento = new ArrayList<ReporteGeneralDatosForm>();

			strTitulo = "";
			strTituloFiltros = "";
			strTituloFechas = "";
			strColumn1 = "";
			strColumn2 = "";
			strColumn3 = "";
			strColumn4 = "";
			strColumn5 = "";
			strColumn6 = "";
			strColumn7 = "";
			strColumn8 = "";
			strColumn9 = "";
			strColumn10 = "";
			isNotEmpty = false;

			if (strOption1 == null) {

				strOption1 = "0";
			}

			if (strOption2 == null) {

				strOption2 = "0";
			}

			if (strOption3 == null) {

				strOption3 = "0";
			}

			if (strOption4 == null) {

				strOption4 = "0";
			}

			if (strOption5 == null) {

				strOption5 = "0";
			}

			if (strOption6 == null) {

				strOption6 = "0";
			}

			if (strOrdenColumnasSplit.length == 2) {
					strColumn1 = strOrdenColumnasSplit[1];
			}
			
			if (strOrdenColumnasSplit.length == 3) {
				strColumn1 = strOrdenColumnasSplit[1];
				strColumn2 = strOrdenColumnasSplit[2];
			}
			
			if (strOrdenColumnasSplit.length == 4) {
				strColumn1 = strOrdenColumnasSplit[1];
				strColumn2 = strOrdenColumnasSplit[2];
				strColumn3 = strOrdenColumnasSplit[3];
			}
			
			if (strOrdenColumnasSplit.length == 5) {
				strColumn1 = strOrdenColumnasSplit[1];
				strColumn2 = strOrdenColumnasSplit[2];
				strColumn3 = strOrdenColumnasSplit[3];
				strColumn4 = strOrdenColumnasSplit[4];
			}
			
			if (strOrdenColumnasSplit.length == 6) {
				strColumn1 = strOrdenColumnasSplit[1];
				strColumn2 = strOrdenColumnasSplit[2];
				strColumn3 = strOrdenColumnasSplit[3];
				strColumn4 = strOrdenColumnasSplit[4];
				strColumn5 = strOrdenColumnasSplit[5];
			}
			
			if (strOrdenColumnasSplit.length == 7) {
				strColumn1 = strOrdenColumnasSplit[1];
				strColumn2 = strOrdenColumnasSplit[2];
				strColumn3 = strOrdenColumnasSplit[3];
				strColumn4 = strOrdenColumnasSplit[4];
				strColumn5 = strOrdenColumnasSplit[5];
				strColumn6 = strOrdenColumnasSplit[6];
			}
			
			if (strOrdenColumnasSplit.length == 8) {
				strColumn1 = strOrdenColumnasSplit[1];
				strColumn2 = strOrdenColumnasSplit[2];
				strColumn3 = strOrdenColumnasSplit[3];
				strColumn4 = strOrdenColumnasSplit[4];
				strColumn5 = strOrdenColumnasSplit[5];
				strColumn6 = strOrdenColumnasSplit[6];
				strColumn7 = strOrdenColumnasSplit[7];
			}
			
			if (strOrdenColumnasSplit.length == 9) {
				strColumn1 = strOrdenColumnasSplit[1];
				strColumn2 = strOrdenColumnasSplit[2];
				strColumn3 = strOrdenColumnasSplit[3];
				strColumn4 = strOrdenColumnasSplit[4];
				strColumn5 = strOrdenColumnasSplit[5];
				strColumn6 = strOrdenColumnasSplit[6];
				strColumn7 = strOrdenColumnasSplit[7];
				strColumn8 = strOrdenColumnasSplit[8];
			}
			
			if (strOrdenColumnasSplit.length == 10) {
				strColumn1 = strOrdenColumnasSplit[1];
				strColumn2 = strOrdenColumnasSplit[2];
				strColumn3 = strOrdenColumnasSplit[3];
				strColumn4 = strOrdenColumnasSplit[4];
				strColumn5 = strOrdenColumnasSplit[5];
				strColumn6 = strOrdenColumnasSplit[6];
				strColumn7 = strOrdenColumnasSplit[7];
				strColumn8 = strOrdenColumnasSplit[8];
				strColumn9 = strOrdenColumnasSplit[9];
			}
			
			if (strOrdenColumnasSplit.length == 11) {
				strColumn1 = strOrdenColumnasSplit[1];
				strColumn2 = strOrdenColumnasSplit[2];
				strColumn3 = strOrdenColumnasSplit[3];
				strColumn4 = strOrdenColumnasSplit[4];
				strColumn5 = strOrdenColumnasSplit[5];
				strColumn6 = strOrdenColumnasSplit[6];
				strColumn7 = strOrdenColumnasSplit[7];
				strColumn8 = strOrdenColumnasSplit[8];
				strColumn9 = strOrdenColumnasSplit[9];
				strColumn10 = strOrdenColumnasSplit[10];
			}


			if (consultaBD == null) {

				consultaBD = "0";
			}

			formulario.setIsReporteFolios(false);

			// Validar rango de fechas o rango de folios
			loggerInfo.info(FechaUtil.getHoraActual()
					+ "_ReporteConstanciasIAction generarTotalesConstancias Inicio Validacion");
			if (!strOption1.equals("0") || !strOption3.equals("0")) {

				if (fechaInicial != null && fechaInicial != null) {

					String fechaInicialSplit[] = fechaInicial.split("/");
					String fechaFinalSplit[] = fechaFinal.split("/");
					String startDate = fechaInicialSplit[2] + "/" + fechaInicialSplit[1] + "/" + fechaInicialSplit[0];
					String finishDate = fechaFinalSplit[2] + "/" + fechaFinalSplit[1] + "/" + fechaFinalSplit[0];
					Date dtstartDate = new Date(startDate);
					Date dtfinishDate = new Date(finishDate);
					long diff = dtfinishDate.getTime() - dtstartDate.getTime();

					// Comparar si las fechas no sobrepasan el día de hoy
					if (dtstartDate.compareTo(new Date()) > 0) {
						ActionMessages errores = new ActionMessages();
						errores.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("etiqueta.constancias.reporte.fechas.error_1"));
						saveErrors(request, errores);
						loggerError.error(FechaUtil.getHoraActual()
								+ "_ReporteConstanciasIAction generarTotalesConstancias - FIN*** "
								+ prop.strMessage("etiqueta.constancias.reporte.fechas.error_1"));
						loggerInfo.info(FechaUtil.getHoraActual()
								+ "_ReporteConstanciasIAction generarTotalesConstancias FIN*** generarTotalesConstancias ");

						// Limpiar logs
						loggerInfo.removeAllAppenders();
						loggerError.removeAllAppenders();
						return mapping.findForward("totales");
					}

					if (dtfinishDate.compareTo(new Date()) > 0) {
						ActionMessages errores = new ActionMessages();
						errores.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("etiqueta.constancias.reporte.fechas.error_1fin"));
						saveErrors(request, errores);
						loggerError.error(FechaUtil.getHoraActual()
								+ "_ReporteConstanciasIAction generarTotalesConstancias - FIN*** "
								+ prop.strMessage("etiqueta.constancias.reporte.fechas.error_1fin"));

						loggerInfo.info(FechaUtil.getHoraActual()
								+ "_ReporteConstanciasIAction generarTotalesConstancias FIN*** generarTotalesConstancias ");
						// Limpiar logs
						loggerInfo.removeAllAppenders();
						loggerError.removeAllAppenders();
						return mapping.findForward("totales");
					}

					// Comparar que la fecha inicial sea menor a la fecha final
					if (dtstartDate.compareTo(dtfinishDate) > 0) {
						ActionMessages errores = new ActionMessages();
						errores.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("etiqueta.constancias.reporte.fechas.error_2"));
						saveErrors(request, errores);
						loggerError.error(FechaUtil.getHoraActual()
								+ "_ReporteConstanciasIAction generarTotalesConstancias - FIN*** "
								+ prop.strMessage("etiqueta.constancias.reporte.fechas.error_2"));

						loggerInfo.info(FechaUtil.getHoraActual()
								+ "_ReporteConstanciasIAction generarTotalesConstancias FIN*** generarTotalesConstancias ");
						// Limpiar logs
						loggerInfo.removeAllAppenders();
						loggerError.removeAllAppenders();
						return mapping.findForward("totales");
					}

					// Máximo 360 dias antes
					if (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) > Integer
							.valueOf(VariablesGlobales.getStrRangoFechas())) {
						ActionMessages errores = new ActionMessages();
						errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
								"etiqueta.constancias.reporte.fechas.error_3", VariablesGlobales.getStrRangoFechas()));
						saveErrors(request, errores);
						loggerError.error(FechaUtil.getHoraActual()
								+ "_ReporteConstanciasIAction generarTotalesConstancias - FIN*** "
								+ prop.strMessage("etiqueta.constancias.reporte.fechas.error_3"));

						loggerInfo.info(FechaUtil.getHoraActual()
								+ "_ReporteConstanciasIAction generarTotalesConstancias FIN*** generarTotalesConstancias ");
						// Limpiar logs
						loggerInfo.removeAllAppenders();
						loggerError.removeAllAppenders();
						return mapping.findForward("totales");
					}

				}

				if (folioInicial != null && folioFinal != null) {

					if (folioInicial.trim().equals("") || folioFinal.trim().equals("")) {
						ActionMessages errores = new ActionMessages();
						errores.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("etiqueta.constancias.reporte.folios.error_2"));
						saveErrors(request, errores);
						loggerError.error(FechaUtil.getHoraActual()
								+ "_ReporteConstanciasIAction generarTotalesConstancias - FIN*** "
								+ prop.strMessage("etiqueta.constancias.reporte.folios.error_2"));

						loggerInfo.info(FechaUtil.getHoraActual()
								+ "_ReporteConstanciasIAction generarTotalesConstancias FIN*** generarTotalesConstancias ");
						// Limpiar logs
						loggerInfo.removeAllAppenders();
						loggerError.removeAllAppenders();
						return mapping.findForward("totales");
					}

					if (Integer.valueOf(folioInicial) > Integer.valueOf(folioFinal)) {
						ActionMessages errores = new ActionMessages();
						errores.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("etiqueta.constancias.reporte.folios.error_1"));
						saveErrors(request, errores);
						loggerError.error(FechaUtil.getHoraActual()
								+ "_ReporteConstanciasIAction generarTotalesConstancias - FIN*** "
								+ prop.strMessage("etiqueta.constancias.reporte.folios.error_1"));

						loggerInfo.info(FechaUtil.getHoraActual()
								+ "_ReporteConstanciasIAction generarTotalesConstancias FIN*** generarTotalesConstancias ");

						// Limpiar logs
						loggerInfo.removeAllAppenders();
						loggerError.removeAllAppenders();
						return mapping.findForward("totales");
					}

				}

			}
			loggerInfo.info(
					FechaUtil.getHoraActual() + "_ReporteConstanciasIAction generarTotalesConstancias Fin Validacion");

			genReporteUniversal(mapping, form, request, response, strOption1, strOption2, strOption3, strOption4,
					strOption5, strOption6, strColumnFolio, strColumnFecha, strColumnTID, strColumnObservaciones,
					strColumnEstadoActual, strColumnTipo, strColumnResponsable, strColumnChasis, strColumnEntidad,
					strColumnEvento, loggerInfo, loggerError, consultaBD);

			if (formulario.getIsFullReport().equals("1")) {

				if (isNotEmpty) {
					loggerInfo.info(FechaUtil.getHoraActual()
							+ "_ReporteConstanciasIAction generarTotalesConstancias - FIN***");

					// Limpiar logs
					loggerInfo.removeAllAppenders();
					loggerError.removeAllAppenders();
					return null;

				} else {
					// Devolver errores
					ActionMessages errores = new ActionMessages();
					errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.reportes.nodatos"));
					saveErrors(request, errores);
					loggerInfo.info(FechaUtil.getHoraActual()
							+ "_ReporteConstanciasIAction generarTotalesConstancias - FIN***");

					// Limpiar logs
					loggerInfo.removeAllAppenders();
					loggerError.removeAllAppenders();
					return mapping.findForward("totales");
				}

			} else {
				loggerInfo.info(
						FechaUtil.getHoraActual() + "_ReporteConstanciasIAction generarTotalesConstancias - FIN***");

				// Limpiar logs
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				return mapping.findForward("totales");
			}
		} catch (Exception e) {
			loggerError.error(FechaUtil.getHoraActual() + "_Fallo ReporteConstanciasIAction generarTotalesConstancias "
					+ e);
			loggerInfo
					.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction generarTotalesConstancias - FIN***");

			// Limpiar logs
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
		}
		// Limpiar logs
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return mapping.findForward("totales");
	}

	public ActionForward genDetalleTotales(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();

		loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction genDetalleTotales - INICIO***");

		try {
			Map parametros = new HashMap();
			ReporteConstanciasIForm formulario = (ReporteConstanciasIForm) form;
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");
			String path = "";
			String tipoReporte = "";
			String nombreArchivo = "";
			String actualizar = "";
			String fechaInicial = "";
			String fechaFinal = "";
			String folioInicial = "";
			String folioFinal = "";
			String strEstado = "";
			String descripcionEstado = "";
			String estadoImportados = "";
			String strResponsable = "";
			String strEstadoImportados = "";
			String strQueryEstado = "";
			String strTituloEstados = "";

			JRXlsExporter exporter = new JRXlsExporter();
			EstadoDAO estadoDAO = null;
			Estado estadoDTO = null;
			List<Estado> estados = formulario.getEstados();
			estadoDAO = DAOFactory.crearEstadoDAO();

			tipoReporte = "application/octet-stream";
			strEstado = formulario.getEstado();
			fechaInicial = formulario.getFechaInicial();
			fechaFinal = formulario.getFechaFinal();
			folioInicial = formulario.getFolioInicial();
			folioFinal = formulario.getFolioFinal();
			strResponsable = formulario.getStrResponsable();
			strEstadoImportados = formulario.getEstadoImportados();
			descripcionEstado = "ReporteGeneral";
			Integer total = 0;
			FormatoDAO formatoDAO = DAOFactory.crearFormatoDAO();
			List<ReporteGeneralDatosForm> reportList = new ArrayList<ReporteGeneralDatosForm>();
			List<ReporteGeneralDatosFormGeneric> reportListFinal = new ArrayList<ReporteGeneralDatosFormGeneric>();
			String strOption5 = request.getParameter("strOptionHidden5");
			String strQuerys[] = new String[1];
			String strSelectUsuario="";
			String strOrderBy="";
			
			if(strOption5==null){
				strOption5="0";
			}
			
			if(ultimoEvento==null && strOption5.equals("1")){
				
				strSelectUsuario="hist.ESTADO";
				strOrderBy="FECHA_HISTORICO";
				
			}
			
			if(ultimoEvento!=null && strOption5.equals("1")){
				
				strSelectUsuario="formato.ESTADO";
				strOrderBy="formato.folio";
				
			}
			
			if(ultimoEvento==null && !strOption5.equals("1")){
				
				strSelectUsuario="formato.ESTADO";
				strOrderBy="formato.folio";
				
			}

			if (!strGeneralQueryBackup.contains("+")) {

				strGeneralQuery = strGeneralQueryBackup;
				strQuerys[0] = strGeneralQuery;

			} else {

				strQuerys = strGeneralQuery.split("[+]");

			}
			strNombreArchivo = strNombreArchivoBackup;

			loggerInfo.info(
					FechaUtil.getHoraActual() + "_ReporteConstanciasIAction genDetalleTotales Inicio Generar Reporte");
			if (strOption5 == null) {

				strOption5 = "0";
			}

			// Consultar por filtrado
			if (!formulario.getIsFullReport().equals("1")) {

				// Recalcular hora
				String strNewDate[] = strNombreArchivo.split("_");
				String newStrNombewArchivo = "";

				newStrNombewArchivo = "reporteCI_";

				if (strNombreArchivo.contains("RespaldoHistorico")) {
					newStrNombewArchivo = "reporteCI_RespaldoHistorico_";
				}

				newStrNombewArchivo += new SimpleDateFormat("ddMMyy").format(new Date())
						+ new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime()) + "_";

				for (int i = 2; i < strNewDate.length; i++) {
					newStrNombewArchivo += strNewDate[i] + "_";
				}

				strNombreArchivo = newStrNombewArchivo;
				

				if (!strEstado.equals("Z")) {

					strQueryEstado = "AND formato.ESTADO='" + strEstado + "'";

					if (strOption5.equals("1")) {

						strQueryEstado = " AND "+strSelectUsuario+"='" + strEstado + "'";

					}

					strTitulo = "";
					estadoDTO = estadoDAO.buscarPorId(strEstado);
					strTitulo = prop.strMessage("etiqueta.constancias.estado.header") + estadoDTO.getDescripcion();
					strTituloEstados += prop.strMessage("etiqueta.estado.header") + estadoDTO.getDescripcion();

					if (strNombreArchivo.contains("_es")) {

						if (!estadoDTO.getEstado().equals("9") && !estadoDTO.getEstado().equals("A")) {

							strNombreArchivo = strNombreArchivo.replace("_es",
									"_es" + estadoDTO.getDescripcion().substring(0, 3).toUpperCase());
						}

						if (estadoDTO.getEstado().equals("9") || estadoDTO.getEstado().equals("A")) {

							if (estadoDTO.getEstado().equals("9")) {

								strNombreArchivo = strNombreArchivo.replace("_es", "_esINV");

							}

							if (estadoDTO.getEstado().equals("A")) {

								strNombreArchivo = strNombreArchivo.replace("_es", "_esINC");

							}

						}

					} else {

						if (!estadoDTO.getEstado().equals("9") && !estadoDTO.getEstado().equals("A")) {

							strNombreArchivo += "_es" + estadoDTO.getDescripcion().substring(0, 3).toUpperCase();
						}

						if (estadoDTO.getEstado().equals("9") || estadoDTO.getEstado().equals("A")) {

							if (estadoDTO.getEstado().equals("9")) {

								strNombreArchivo += "_esINV";

							}

							if (estadoDTO.getEstado().equals("A")) {

								strNombreArchivo += "_esINC";

							}

						}

					}

				}

			} else {

				if (strEstado.equals("Z") && !strEstado.equals("999")) {

					String inEstados = "";

					if (!strOption5.equals("1")) {

						for (int i = 0; i < estados.size(); i++) {

							inEstados += "'" + estados.get(i).getEstado() + "'";

							if (i + 1 < estados.size()) {

								inEstados += ",";
							}

							strQueryEstado = "AND "+strSelectUsuario+" in (" + inEstados + ")";

						}

					} else {

						String strEstadoResp = "";
						List<ReporteGeneralDatosForm> reportListTemp = new ArrayList<ReporteGeneralDatosForm>();
						List<ReporteGeneralDatosForm> reportListTempResp = new ArrayList<ReporteGeneralDatosForm>();
						ArrayList<String> ordenEstados = this.OrdenEstados(estados, loggerInfo, loggerError);

						for (int i = 0; i < ordenEstados.size(); i++) {

							strEstadoResp = "AND "+strSelectUsuario+"='" + ordenEstados.get(i) + "'";
							reportListTemp = new ArrayList<ReporteGeneralDatosForm>();
							reportListTempResp = new ArrayList<ReporteGeneralDatosForm>();

							for (int x = 0; x < strQuerys.length; x++) {

								reportListTempResp = formatoDAO.listReporteGeneral(
										strQuerys[x] + strEstadoResp + " ORDER BY "+strOrderBy+" asc");

								for (int z = 0; z < reportListTempResp.size(); z++) {

									reportListTemp.add(reportListTempResp.get(z));

								}

							}

							for (int j = 0; j < reportListTemp.size(); j++) {

								reportList.add(reportListTemp.get(j));

							}

						}

					}

					strTitulo = "";
					strTitulo = prop.strMessage("etiqueta.constancias.estado_TOD.header");

					if (strNombreArchivo.contains("_es")) {

						strNombreArchivo = strNombreArchivo.replace("_es", "_esTOD");

					} else {

						strNombreArchivo += "_esTOD";

					}

				}

				if (!strEstado.equals("Z") && !strEstado.equals("999")) {

					strQueryEstado = "AND "+strSelectUsuario+" = '" + strEstado + "'";

					if (strOption5.equals("1")) {

						strQueryEstado = " AND "+strSelectUsuario+"='" + strEstado + "'";

					}

					strTitulo = "";
					estadoDTO = estadoDAO.buscarPorId(strEstado);
					strTitulo = prop.strMessage("etiqueta.constancias.estado.header") + estadoDTO.getDescripcion();
					strTituloEstados += prop.strMessage("etiqueta.estado.header") + estadoDTO.getDescripcion();

					if (strNombreArchivo.contains("_es")) {

						if (!estadoDTO.getEstado().equals("9") && !estadoDTO.getEstado().equals("A")) {

							strNombreArchivo = strNombreArchivo.replace("_es",
									"_es" + estadoDTO.getDescripcion().substring(0, 3).toUpperCase());
						}

						if (estadoDTO.getEstado().equals("9") || estadoDTO.getEstado().equals("A")) {

							if (estadoDTO.getEstado().equals("9")) {

								strNombreArchivo = strNombreArchivo.replace("_es", "_esINV");

							}

							if (estadoDTO.getEstado().equals("A")) {

								strNombreArchivo = strNombreArchivo.replace("_es", "_esINC");

							}

						}

					} else {

						if (!estadoDTO.getEstado().equals("9") && !estadoDTO.getEstado().equals("A")) {

							strNombreArchivo += "_es" + estadoDTO.getDescripcion().substring(0, 3).toUpperCase();
						}

						if (estadoDTO.getEstado().equals("9") || estadoDTO.getEstado().equals("A")) {

							if (estadoDTO.getEstado().equals("9")) {

								strNombreArchivo += "_esINV";

							}

							if (estadoDTO.getEstado().equals("A")) {

								strNombreArchivo += "_esINC";

							}

						}

					}

				}

				if (strEstado.equals("Z") || strEstado.equals("999")) {

					String inEstados = "";
					for (int i = 0; i < estados.size(); i++) {

						inEstados += "'" + estados.get(i).getEstado() + "'";

						if (i + 1 < estados.size()) {

							inEstados += ",";
						}

						strQueryEstado = "AND "+strSelectUsuario+" in (" + inEstados + ")";

					}
					strTitulo = prop.strMessage("etiqueta.constancias.reporte.general");

					if (strEstado.equals("Z")) {
						strTituloEstados += prop.strMessage("etiqueta.estado.header") + " "
								+ prop.strMessage("combo.opcion.todos");
					}
				}

			}
			
			if (reportList.size() == 0) {

				if (strOption5.equals("1")) {

					List<ReporteGeneralDatosForm> reportListTempResp = new ArrayList<ReporteGeneralDatosForm>();

					for (int i = 0; i < strQuerys.length; i++) {

						reportListTempResp = formatoDAO
								.listReporteGeneral(strQuerys[i] + strQueryEstado + "ORDER BY "+strOrderBy+" asc");

						for (int y = 0; y < reportListTempResp.size(); y++) {

							reportList.add(reportListTempResp.get(y));

						}

					}

				} else {

					List<ReporteGeneralDatosForm> reportListTempResp = new ArrayList<ReporteGeneralDatosForm>();

					for (int i = 0; i < strQuerys.length; i++) {

						reportListTempResp = formatoDAO
								.listReporteGeneral(strQuerys[i] + strQueryEstado + "ORDER BY "+strOrderBy+" asc");

						for (int y = 0; y < reportListTempResp.size(); y++) {

							reportList.add(reportListTempResp.get(y));

						}

					}

					// reportList = formatoDAO
					// .listReporteGeneral(strGeneralQuery + strQueryEstado + "
					// ORDER BY formato.FOLIO asc");
				}

			}
			if (reportList.size() > 0) {

				isNotEmpty = true;

			}

			// Espacios en fechas
			//
			// for(int i=0;i<reportList.size();i++){
			//
			// reportList.get(i).setFecha(reportList.get(i).getFecha().trim);
			//
			// }

			// Quitar ultimo _ generado en el nombre del archivo
			String strLast = strNombreArchivo.substring(strNombreArchivo.length() - 1, (strNombreArchivo.length()));

			if (strLast.equals("_")) {

				strNombreArchivo = strNombreArchivo.substring(0, strNombreArchivo.length() - 1);
			}

			// Eliminar dobles guiones bajos

			if (strNombreArchivo.contains("__")) {

				strNombreArchivo = strNombreArchivo.replace("__", "_");
			}

			if (reportList.size() > 0 && reportList != null) {

				// Agregar eventos si el reporte es por responsable
				for (ReporteGeneralDatosForm reportObj : reportList) {

					if (reportObj.getEstadoActual().equals("Dotado")) {

						reportObj.setEvento(prop.strMessage("etiqueta.constancias.reporte.eventoDot"));
						reportObj.setNiv("");
						reportObj.setTagId("");
						reportObj.setTipo("");

					}

					if (reportObj.getEstadoActual().equals("Impreso")) {

						reportObj.setEvento(prop.strMessage("etiqueta.constancias.reporte.eventoImp"));

					}

					if (reportObj.getEstadoActual().equals("Grabado")) {

						reportObj.setEvento(prop.strMessage("etiqueta.constancias.reporte.eventoGra"));

					}

					if (reportObj.getEstadoActual().equals("Reimpreso")) {

						reportObj.setEvento(prop.strMessage("etiqueta.constancias.reporte.eventoReImp"));

					}

					if (reportObj.getEstadoActual().equals("Regrabado")) {

						reportObj.setEvento(prop.strMessage("etiqueta.constancias.reporte.eventoReGra"));

					}

					if (reportObj.getEstadoActual().equals("Eliminado")) {

						reportObj.setEvento(prop.strMessage("etiqueta.constancias.reporte.eventoElim"));
						reportObj.setNiv("");
						reportObj.setTagId("");
						reportObj.setTipo("");

					}

					if (reportObj.getEstadoActual().equals("Cancelado")) {

						reportObj.setEvento(prop.strMessage("etiqueta.constancias.reporte.eventoCan"));
						reportObj.setNiv("");
						reportObj.setTagId("");
						reportObj.setTipo("");

					}

					if (reportObj.getEstadoActual().equals("Pegado")) {

						reportObj.setEvento(prop.strMessage("etiqueta.constancias.reporte.eventoPeg"));

					}

					if (reportObj.getEstadoActual().equals("Revisado")) {

						reportObj.setEvento(prop.strMessage("etiqueta.constancias.reporte.eventoRev"));

					}

					if (reportObj.getEstadoActual().equals("Informado Verificado")) {

						reportObj.setEvento(prop.strMessage("etiqueta.constancias.reporte.eventoINV"));

					}

					if (reportObj.getEstadoActual().equals("Informado Cancelado")) {

						reportObj.setEvento(prop.strMessage("etiqueta.constancias.reporte.eventoINC"));

					}

				}
				// Comparar objetos de folio para obtener eventos de
				// transferencia
				// entre oficinas
				for (int i = 0; i < reportList.size(); i++) {

					if (i + 1 < reportList.size()) {

						// Validar si pertenecen al mismo folio
						if (reportList.get(i).getFolio().equals(reportList.get(i + 1).getFolio())) {

							// Agregar evento de transferencia al siguiente
							// registro
							// en caso de que la entidad haya cambiado
							if (!reportList.get(i).getEntidad().equals(reportList.get(i + 1).getEntidad())) {

								reportList.get(i + 1)
										.setEvento(prop.strMessage("etiqueta.constancias.reporte.eventoTrans"));

							}

						}

					}

				}
				//

				for (ReporteGeneralDatosForm reportObj : reportList) {

					ReporteGeneralDatosFormGeneric finalObj = new ReporteGeneralDatosFormGeneric();

					if (strColumn1.equals("Folio")) {
						finalObj.setStrColumn1(reportObj.getFolio());
					}
					if (strColumn1.equals("Fecha")) {

						String fechaSplit[] = reportObj.getFecha().replace("0:0:0. 0", "00:00:00").split("-");
						String diaSplit[] = fechaSplit[2].split("\\s+");

						if (diaSplit[0].length() == 1) {
							diaSplit[0] = "0" + diaSplit[0];
						}

						if (fechaSplit[1].length() == 1) {
							fechaSplit[1] = "0" + fechaSplit[1];
						}

						String finalDate = diaSplit[0] + "/" + fechaSplit[1] + "/" + fechaSplit[0] + " " + diaSplit[1];
						reportObj.setFecha(finalDate);
						finalObj.setStrColumn1(reportObj.getFecha());
					}
					if (strColumn1.equals("TID")) {

						finalObj.setStrColumn1(reportObj.getTagId());
					}
					if (strColumn1.equals("Observaciones")) {
						finalObj.setStrColumn1(reportObj.getObservaciones());
					}
					if (strColumn1.equals("Estado Actual")) {
						finalObj.setStrColumn1(reportObj.getEstadoActual());
					}
					if (strColumn1.equals("Tipo")) {

						if (reportObj.getFolio() != null && reportObj.getTagId() != null
								&& !reportObj.getNiv().equals("") && reportObj.getNiv() != null) {
							finalObj.setStrColumn1(reportObj.getFolio() + "|" + reportObj.getTagId());
						} else {
							finalObj.setStrColumn1("");
						}

					}
					if (strColumn1.equals("Responsable")) {
						finalObj.setStrColumn1(reportObj.getResponsable());

					}
					if (strColumn1.equals("Chasis")) {
						finalObj.setStrColumn1(reportObj.getNiv());
					}
					if (strColumn1.equals("Entidad")) {
						finalObj.setStrColumn1(reportObj.getEntidad());
					}
					if (strColumn1.equals("Evento")) {
						finalObj.setStrColumn1(reportObj.getEvento());
					}

					if (strColumn2.equals("Folio")) {
						finalObj.setStrColumn2(reportObj.getFolio());
					}
					if (strColumn2.equals("Fecha")) {
						String fechaSplit[] = reportObj.getFecha().replace("0:0:0. 0", "00:00:00").split("-");
						String diaSplit[] = fechaSplit[2].split("\\s+");

						if (diaSplit[0].length() == 1) {
							diaSplit[0] = "0" + diaSplit[0];
						}

						if (fechaSplit[1].length() == 1) {
							fechaSplit[1] = "0" + fechaSplit[1];
						}

						String finalDate = diaSplit[0] + "/" + fechaSplit[1] + "/" + fechaSplit[0] + " " + diaSplit[1];
						reportObj.setFecha(finalDate);
						finalObj.setStrColumn2(reportObj.getFecha());
					}
					if (strColumn2.equals("TID")) {
						finalObj.setStrColumn2(reportObj.getTagId());
					}
					if (strColumn2.equals("Observaciones")) {
						finalObj.setStrColumn2(reportObj.getObservaciones());
					}
					if (strColumn2.equals("Estado Actual")) {
						finalObj.setStrColumn2(reportObj.getEstadoActual());
					}
					if (strColumn2.equals("Tipo")) {
						if (reportObj.getFolio() != null && reportObj.getTagId() != null
								&& !reportObj.getNiv().equals("") && reportObj.getNiv() != null) {
							finalObj.setStrColumn2(reportObj.getFolio() + "|" + reportObj.getTagId());
						} else {
							finalObj.setStrColumn2("");
						}
					}
					if (strColumn2.equals("Responsable")) {
						finalObj.setStrColumn2(reportObj.getResponsable());

					}
					if (strColumn2.equals("Chasis")) {
						finalObj.setStrColumn2(reportObj.getNiv());
					}

					if (strColumn2.equals("Entidad")) {
						finalObj.setStrColumn2(reportObj.getEntidad());
					}

					if (strColumn2.equals("Evento")) {
						finalObj.setStrColumn2(reportObj.getEvento());
					}

					if (strColumn3.equals("Folio")) {
						finalObj.setStrColumn3(reportObj.getFolio());
					}
					if (strColumn3.equals("Fecha")) {
						String fechaSplit[] = reportObj.getFecha().replace("0:0:0. 0", "00:00:00").split("-");
						String diaSplit[] = fechaSplit[2].split("\\s+");

						if (diaSplit[0].length() == 1) {
							diaSplit[0] = "0" + diaSplit[0];
						}

						if (fechaSplit[1].length() == 1) {
							fechaSplit[1] = "0" + fechaSplit[1];
						}

						String finalDate = diaSplit[0] + "/" + fechaSplit[1] + "/" + fechaSplit[0] + " " + diaSplit[1];
						reportObj.setFecha(finalDate);
						finalObj.setStrColumn3(reportObj.getFecha());
					}
					if (strColumn3.equals("TID")) {
						finalObj.setStrColumn3(reportObj.getTagId());
					}
					if (strColumn3.equals("Observaciones")) {
						finalObj.setStrColumn3(reportObj.getObservaciones());
					}
					if (strColumn3.equals("Estado Actual")) {
						finalObj.setStrColumn3(reportObj.getEstadoActual());
					}
					if (strColumn3.equals("Tipo")) {
						if (reportObj.getFolio() != null && reportObj.getTagId() != null
								&& !reportObj.getNiv().equals("") && reportObj.getNiv() != null) {
							finalObj.setStrColumn3(reportObj.getFolio() + "|" + reportObj.getTagId());
						} else {
							finalObj.setStrColumn3("");
						}
					}
					if (strColumn3.equals("Responsable")) {
						finalObj.setStrColumn3(reportObj.getResponsable());

					}
					if (strColumn3.equals("Chasis")) {
						finalObj.setStrColumn3(reportObj.getNiv());
					}

					if (strColumn3.equals("Entidad")) {
						finalObj.setStrColumn3(reportObj.getEntidad());
					}

					if (strColumn3.equals("Evento")) {
						finalObj.setStrColumn3(reportObj.getEvento());
					}

					if (strColumn4.equals("Folio")) {
						finalObj.setStrColumn4(reportObj.getFolio());
					}
					if (strColumn4.equals("Fecha")) {
						String fechaSplit[] = reportObj.getFecha().replace("0:0:0. 0", "00:00:00").split("-");
						String diaSplit[] = fechaSplit[2].split("\\s+");

						if (diaSplit[0].length() == 1) {
							diaSplit[0] = "0" + diaSplit[0];
						}

						if (fechaSplit[1].length() == 1) {
							fechaSplit[1] = "0" + fechaSplit[1];
						}

						String finalDate = diaSplit[0] + "/" + fechaSplit[1] + "/" + fechaSplit[0] + " " + diaSplit[1];
						reportObj.setFecha(finalDate);
						finalObj.setStrColumn4(reportObj.getFecha());
					}
					if (strColumn4.equals("TID")) {
						finalObj.setStrColumn4(reportObj.getTagId());
					}
					if (strColumn4.equals("Observaciones")) {
						finalObj.setStrColumn4(reportObj.getObservaciones());
					}
					if (strColumn4.equals("Estado Actual")) {
						finalObj.setStrColumn4(reportObj.getEstadoActual());
					}
					if (strColumn4.equals("Tipo")) {
						if (reportObj.getFolio() != null && reportObj.getTagId() != null
								&& !reportObj.getNiv().equals("") && reportObj.getNiv() != null) {
							finalObj.setStrColumn4(reportObj.getFolio() + "|" + reportObj.getTagId());
						} else {
							finalObj.setStrColumn4("");
						}
					}
					if (strColumn4.equals("Responsable")) {
						finalObj.setStrColumn4(reportObj.getResponsable());

					}
					if (strColumn4.equals("Chasis")) {
						finalObj.setStrColumn4(reportObj.getNiv());
					}

					if (strColumn4.equals("Entidad")) {
						finalObj.setStrColumn4(reportObj.getEntidad());
					}

					if (strColumn4.equals("Evento")) {
						finalObj.setStrColumn4(reportObj.getEvento());
					}

					if (strColumn5.equals("Folio")) {
						finalObj.setStrColumn5(reportObj.getFolio());
					}
					if (strColumn5.equals("Fecha")) {
						String fechaSplit[] = reportObj.getFecha().replace("0:0:0. 0", "00:00:00").split("-");
						String diaSplit[] = fechaSplit[2].split("\\s+");

						if (diaSplit[0].length() == 1) {
							diaSplit[0] = "0" + diaSplit[0];
						}

						if (fechaSplit[1].length() == 1) {
							fechaSplit[1] = "0" + fechaSplit[1];
						}

						String finalDate = diaSplit[0] + "/" + fechaSplit[1] + "/" + fechaSplit[0] + " " + diaSplit[1];
						reportObj.setFecha(finalDate);
						finalObj.setStrColumn5(reportObj.getFecha());
					}
					if (strColumn5.equals("TID")) {
						finalObj.setStrColumn5(reportObj.getTagId());
					}
					if (strColumn5.equals("Observaciones")) {
						finalObj.setStrColumn5(reportObj.getObservaciones());
					}
					if (strColumn5.equals("Estado Actual")) {
						finalObj.setStrColumn5(reportObj.getEstadoActual());
					}
					if (strColumn5.equals("Tipo")) {
						if (reportObj.getFolio() != null && reportObj.getTagId() != null
								&& !reportObj.getNiv().equals("") && reportObj.getNiv() != null) {
							finalObj.setStrColumn5(reportObj.getFolio() + "|" + reportObj.getTagId());
						} else {
							finalObj.setStrColumn5("");
						}
					}
					if (strColumn5.equals("Responsable")) {
						finalObj.setStrColumn5(reportObj.getResponsable());

					}
					if (strColumn5.equals("Chasis")) {
						finalObj.setStrColumn5(reportObj.getNiv());
					}
					if (strColumn5.equals("Entidad")) {
						finalObj.setStrColumn5(reportObj.getEntidad());
					}
					if (strColumn5.equals("Evento")) {
						finalObj.setStrColumn5(reportObj.getEvento());
					}

					if (strColumn6.equals("Folio")) {
						finalObj.setStrColumn6(reportObj.getFolio());
					}
					if (strColumn6.equals("Fecha")) {
						String fechaSplit[] = reportObj.getFecha().replace("0:0:0. 0", "00:00:00").split("-");
						String diaSplit[] = fechaSplit[2].split("\\s+");

						if (diaSplit[0].length() == 1) {
							diaSplit[0] = "0" + diaSplit[0];
						}

						if (fechaSplit[1].length() == 1) {
							fechaSplit[1] = "0" + fechaSplit[1];
						}

						String finalDate = diaSplit[0] + "/" + fechaSplit[1] + "/" + fechaSplit[0] + " " + diaSplit[1];
						reportObj.setFecha(finalDate);
						finalObj.setStrColumn6(reportObj.getFecha());
					}
					if (strColumn6.equals("TID")) {
						finalObj.setStrColumn6(reportObj.getTagId());
					}
					if (strColumn6.equals("Observaciones")) {
						finalObj.setStrColumn6(reportObj.getObservaciones());
					}
					if (strColumn6.equals("Estado Actual")) {
						finalObj.setStrColumn6(reportObj.getEstadoActual());
					}
					if (strColumn6.equals("Tipo")) {
						if (reportObj.getFolio() != null && reportObj.getTagId() != null
								&& !reportObj.getNiv().equals("") && reportObj.getNiv() != null) {
							finalObj.setStrColumn6(reportObj.getFolio() + "|" + reportObj.getTagId());
						} else {
							finalObj.setStrColumn6("");
						}
					}
					if (strColumn6.equals("Responsable")) {
						finalObj.setStrColumn6(reportObj.getResponsable());

					}
					if (strColumn6.equals("Chasis")) {
						finalObj.setStrColumn6(reportObj.getNiv());
					}

					if (strColumn6.equals("Entidad")) {
						finalObj.setStrColumn6(reportObj.getEntidad());
					}
					if (strColumn6.equals("Evento")) {
						finalObj.setStrColumn6(reportObj.getEvento());
					}

					if (strColumn7.equals("Folio")) {
						finalObj.setStrColumn7(reportObj.getFolio());
					}
					if (strColumn7.equals("Fecha")) {
						String fechaSplit[] = reportObj.getFecha().replace("0:0:0. 0", "00:00:00").split("-");
						String diaSplit[] = fechaSplit[2].split("\\s+");

						if (diaSplit[0].length() == 1) {
							diaSplit[0] = "0" + diaSplit[0];
						}

						if (fechaSplit[1].length() == 1) {
							fechaSplit[1] = "0" + fechaSplit[1];
						}

						String finalDate = diaSplit[0] + "/" + fechaSplit[1] + "/" + fechaSplit[0] + " " + diaSplit[1];
						reportObj.setFecha(finalDate);
						finalObj.setStrColumn7(reportObj.getFecha());
					}
					if (strColumn7.equals("TID")) {
						finalObj.setStrColumn7(reportObj.getTagId());
					}
					if (strColumn7.equals("Observaciones")) {
						finalObj.setStrColumn7(reportObj.getObservaciones());
					}
					if (strColumn7.equals("Estado Actual")) {
						finalObj.setStrColumn7(reportObj.getEstadoActual());
					}
					if (strColumn7.equals("Tipo")) {
						if (reportObj.getFolio() != null && reportObj.getTagId() != null
								&& !reportObj.getNiv().equals("") && reportObj.getNiv() != null) {
							finalObj.setStrColumn7(reportObj.getFolio() + "|" + reportObj.getTagId());
						} else {
							finalObj.setStrColumn7("");
						}
					}
					if (strColumn7.equals("Responsable")) {
						finalObj.setStrColumn7(reportObj.getResponsable());

					}
					if (strColumn7.equals("Chasis")) {
						finalObj.setStrColumn7(reportObj.getNiv());
					}

					if (strColumn7.equals("Entidad")) {
						finalObj.setStrColumn7(reportObj.getEntidad());
					}
					if (strColumn7.equals("Evento")) {
						finalObj.setStrColumn7(reportObj.getEvento());
					}
					if (strColumn8.equals("Folio")) {
						finalObj.setStrColumn8(reportObj.getFolio());
					}
					if (strColumn8.equals("Fecha")) {
						String fechaSplit[] = reportObj.getFecha().replace("0:0:0. 0", "00:00:00").split("-");
						String diaSplit[] = fechaSplit[2].split("\\s+");

						if (diaSplit[0].length() == 1) {
							diaSplit[0] = "0" + diaSplit[0];
						}

						if (fechaSplit[1].length() == 1) {
							fechaSplit[1] = "0" + fechaSplit[1];
						}

						String finalDate = diaSplit[0] + "/" + fechaSplit[1] + "/" + fechaSplit[0] + " " + diaSplit[1];
						reportObj.setFecha(finalDate);
						finalObj.setStrColumn8(reportObj.getFecha());
					}
					if (strColumn8.equals("TID")) {
						finalObj.setStrColumn8(reportObj.getTagId());
					}
					if (strColumn8.equals("Observaciones")) {
						finalObj.setStrColumn8(reportObj.getObservaciones());
					}
					if (strColumn8.equals("Estado Actual")) {
						finalObj.setStrColumn8(reportObj.getEstadoActual());
					}
					if (strColumn8.equals("Tipo")) {
						if (reportObj.getFolio() != null && reportObj.getTagId() != null
								&& !reportObj.getNiv().equals("") && reportObj.getNiv() != null) {
							finalObj.setStrColumn8(reportObj.getFolio() + "|" + reportObj.getTagId());
						} else {
							finalObj.setStrColumn8("");
						}
					}
					if (strColumn8.equals("Responsable")) {
						finalObj.setStrColumn8(reportObj.getResponsable());

					}
					if (strColumn8.equals("Chasis")) {
						finalObj.setStrColumn8(reportObj.getNiv());
					}

					if (strColumn8.equals("Entidad")) {
						finalObj.setStrColumn8(reportObj.getEntidad());
					}
					if (strColumn8.equals("Evento")) {
						finalObj.setStrColumn8(reportObj.getEvento());
					}
					if (strColumn9.equals("Folio")) {
						finalObj.setStrColumn9(reportObj.getFolio());
					}
					if (strColumn9.equals("Fecha")) {
						String fechaSplit[] = reportObj.getFecha().replace("0:0:0. 0", "00:00:00").split("-");
						String diaSplit[] = fechaSplit[2].split("\\s+");

						if (diaSplit[0].length() == 1) {
							diaSplit[0] = "0" + diaSplit[0];
						}

						if (fechaSplit[1].length() == 1) {
							fechaSplit[1] = "0" + fechaSplit[1];
						}

						String finalDate = diaSplit[0] + "/" + fechaSplit[1] + "/" + fechaSplit[0] + " " + diaSplit[1];
						reportObj.setFecha(finalDate);
						finalObj.setStrColumn9(reportObj.getFecha());
					}
					if (strColumn9.equals("TID")) {
						finalObj.setStrColumn9(reportObj.getTagId());
					}
					if (strColumn9.equals("Observaciones")) {
						finalObj.setStrColumn9(reportObj.getObservaciones());
					}
					if (strColumn9.equals("Estado Actual")) {
						finalObj.setStrColumn9(reportObj.getEstadoActual());
					}
					if (strColumn9.equals("Tipo")) {
						if (reportObj.getFolio() != null && reportObj.getTagId() != null
								&& !reportObj.getNiv().equals("") && reportObj.getNiv() != null) {
							finalObj.setStrColumn9(reportObj.getFolio() + "|" + reportObj.getTagId());
						} else {
							finalObj.setStrColumn9("");
						}
					}
					if (strColumn9.equals("Responsable")) {
						finalObj.setStrColumn9(reportObj.getResponsable());

					}
					if (strColumn9.equals("Chasis")) {
						finalObj.setStrColumn9(reportObj.getNiv());
					}
					if (strColumn9.equals("Entidad")) {
						finalObj.setStrColumn9(reportObj.getEntidad());
					}
					if (strColumn9.equals("Evento")) {
						finalObj.setStrColumn9(reportObj.getEvento());
					}

					if (strColumn10.equals("Folio")) {
						finalObj.setStrColumn10(reportObj.getFolio());
					}
					if (strColumn10.equals("Fecha")) {
						String fechaSplit[] = reportObj.getFecha().replace("0:0:0. 0", "00:00:00").split("-");
						String diaSplit[] = fechaSplit[2].split("\\s+");

						if (diaSplit[0].length() == 1) {
							diaSplit[0] = "0" + diaSplit[0];
						}

						if (fechaSplit[1].length() == 1) {
							fechaSplit[1] = "0" + fechaSplit[1];
						}

						String finalDate = diaSplit[0] + "/" + fechaSplit[1] + "/" + fechaSplit[0] + " " + diaSplit[1];
						reportObj.setFecha(finalDate);
						finalObj.setStrColumn10(reportObj.getFecha());
					}
					if (strColumn10.equals("TID")) {
						finalObj.setStrColumn9(reportObj.getTagId());
					}
					if (strColumn10.equals("Observaciones")) {
						finalObj.setStrColumn10(reportObj.getObservaciones());
					}
					if (strColumn10.equals("Estado Actual")) {
						finalObj.setStrColumn10(reportObj.getEstadoActual());
					}
					if (strColumn10.equals("Tipo")) {
						if (reportObj.getFolio() != null && reportObj.getTagId() != null
								&& !reportObj.getNiv().equals("") && reportObj.getNiv() != null) {
							finalObj.setStrColumn10(reportObj.getFolio() + "|" + reportObj.getTagId());
						} else {
							finalObj.setStrColumn10("");
						}
					}
					if (strColumn10.equals("Responsable")) {
						finalObj.setStrColumn10(reportObj.getResponsable());

					}
					if (strColumn10.equals("Chasis")) {
						finalObj.setStrColumn10(reportObj.getNiv());
					}
					if (strColumn10.equals("Entidad")) {
						finalObj.setStrColumn10(reportObj.getEntidad());
					}
					if (strColumn10.equals("Evento")) {
						finalObj.setStrColumn10(reportObj.getEvento());
					}

					// Eliminar nulos
					if (finalObj.getStrColumn1() == null || finalObj.getStrColumn1().trim().equals("")) {
						finalObj.setStrColumn1(VariablesGlobales.getCamposVaciosReporte());
					}
					if (finalObj.getStrColumn2() == null || finalObj.getStrColumn2().trim().equals("")) {
						finalObj.setStrColumn2(VariablesGlobales.getCamposVaciosReporte());
					}
					if (finalObj.getStrColumn3() == null || finalObj.getStrColumn3().trim().equals("")) {
						finalObj.setStrColumn3(VariablesGlobales.getCamposVaciosReporte());
					}
					if (finalObj.getStrColumn4() == null || finalObj.getStrColumn4().trim().equals("")) {
						finalObj.setStrColumn4(VariablesGlobales.getCamposVaciosReporte());
					}
					if (finalObj.getStrColumn5() == null || finalObj.getStrColumn5().trim().equals("")) {
						finalObj.setStrColumn5(VariablesGlobales.getCamposVaciosReporte());
					}
					if (finalObj.getStrColumn6() == null || finalObj.getStrColumn6().trim().equals("")) {
						finalObj.setStrColumn6(VariablesGlobales.getCamposVaciosReporte());
					}
					if (finalObj.getStrColumn7() == null || finalObj.getStrColumn7().trim().equals("")) {
						finalObj.setStrColumn7(VariablesGlobales.getCamposVaciosReporte());
					}
					if (finalObj.getStrColumn8() == null || finalObj.getStrColumn8().trim().equals("")) {
						finalObj.setStrColumn8(VariablesGlobales.getCamposVaciosReporte());
					}

					if (finalObj.getStrColumn9() == null || finalObj.getStrColumn9().trim().equals("")) {
						finalObj.setStrColumn9(VariablesGlobales.getCamposVaciosReporte());
					}

					if (finalObj.getStrColumn10() == null || finalObj.getStrColumn10().trim().equals("")) {
						finalObj.setStrColumn10(VariablesGlobales.getCamposVaciosReporte());
					}

					// No mostrar nada cuando el titulo de la columna se
					// encuentre
					// vacia
					if (strColumn1.trim().equals("")) {
						finalObj.setStrColumn1("");
					}
					if (strColumn2.trim().equals("")) {
						finalObj.setStrColumn2("");
					}
					if (strColumn3.trim().equals("")) {
						finalObj.setStrColumn3("");
					}
					if (strColumn4.trim().equals("")) {
						finalObj.setStrColumn4("");
					}
					if (strColumn5.trim().equals("")) {
						finalObj.setStrColumn5("");
					}
					if (strColumn6.trim().equals("")) {
						finalObj.setStrColumn6("");
					}
					if (strColumn7.trim().equals("")) {
						finalObj.setStrColumn7("");
					}
					if (strColumn8.trim().equals("")) {
						finalObj.setStrColumn8("");
					}

					if (strColumn9.trim().equals("")) {
						finalObj.setStrColumn9("");
					}

					if (strColumn10.trim().equals("")) {
						finalObj.setStrColumn10("");
					}

					reportListFinal.add(finalObj);

				}

				parametros.put("strTituloEstado", strTitulo);
				parametros.put("strTituloFiltros", strTituloFiltros);
				parametros.put("strTituloFechas", strTituloFechas + strTituloEstados);
				parametros.put("strColumn1", strColumn1);
				parametros.put("strColumn2", strColumn2);
				parametros.put("strColumn3", strColumn3);
				parametros.put("strColumn4", strColumn4);
				parametros.put("strColumn5", strColumn5);
				parametros.put("strColumn6", strColumn6);
				parametros.put("strColumn7", strColumn7);
				parametros.put("strColumn8", strColumn8);
				parametros.put("strColumn9", strColumn9);
				parametros.put("strColumn10", strColumn10);

				if (isNotEmpty = true) {
					JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(reportListFinal);

					if (!strNombreArchivo.contains(".xls")) {
						strNombreArchivo += ".xls";
					}

					path = this.getServlet().getServletContext()
							.getRealPath(VariablesGlobales.getReporteDetalleConstanciaFoliosREPUVE());

					File reportFile = new File(path);
					JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
					JasperPrint print = JasperFillManager.fillReport(jasperReport, parametros, data);

					response.setContentType(tipoReporte);
					response.setHeader("Content-disposition", "attachment; filename=" + strNombreArchivo);
					OutputStream out = response.getOutputStream();

					// exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
					// Boolean.TRUE);
					loggerInfo.info(FechaUtil.getHoraActual()
							+ "   ReporteConstanciasIAction genDetalleTotales Fin Generar Reporte");
					exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
					exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
					exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
					exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
					exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
					exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
							Boolean.TRUE);
					exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
					exporter.exportReport();
					out.flush();
					out.close();
				}
			}

			loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction genDetalleTotales - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return null;
		} catch (Exception e) {

			loggerError.error(
					FechaUtil.getHoraActual() + "_Fallo ReporteConstanciasIAction genDetalleTotales  " + e);
			loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction genDetalleTotales - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
		}
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return null;
	}

	public ActionForward genReporteUniversal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String strOption1, String strOption2, String strOption3, String strOption4,
			String strOption5, String strOption6, String strColumnFolio, String strColumnFecha, String strColumnTID,
			String strColumnObservaciones, String strColumnEstadoActual, String strColumnTipo,
			String strColumnResponsable, String strColumnChasis, String strColumnEntidad, String strColumnEvento,
			Logger loggerInfo, Logger loggerError, String consultaBD) throws Exception {

		// Preparacion de la consulta dependiendo de los parámetros
		// seleccionados

		loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction genReporteUniversal - INICIO***");
		String[] arrQuerys = null;

		try {
			ReporteConstanciasIForm formulario = (ReporteConstanciasIForm) form;
			String strQueryEstado = "";

			FormatoDAO formatoDAO = DAOFactory.crearFormatoDAO();
			List<Estado> estados = formulario.getEstados();
			List listaTotales = new ArrayList();
			String strEstado = formulario.getEstado();
			String fechaInicial = formulario.getFechaInicial();
			String fechaFinal = formulario.getFechaFinal();
			String folioInicial = formulario.getFolioInicial();
			String folioFinal = formulario.getFolioFinal();
			String strResponsable = formulario.getStrResponsable();
			String strEstadoImportados = formulario.getEstadoImportados();
			String strEntidad = formulario.getStrEntidad();
			Integer totalEncontrados = 0;
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");

			if (consultaBD.equals("0")) {
				strGeneralQuery = buildQuery(strOption1, strOption2, strOption3, strOption4, strOption5, strOption6,
						fechaInicial, fechaFinal, folioInicial, folioFinal, strResponsable, strEstado,
						strEstadoImportados, strEntidad, usuario, strColumnFolio, strColumnFecha, strColumnTID,
						strColumnObservaciones, strColumnEstadoActual, strColumnTipo, strColumnResponsable,
						strColumnChasis, strColumnEntidad, strColumnEvento, loggerInfo, loggerError, consultaBD);
			} else {

				strGeneralQuery = buildQueryRespaldo(strOption1, strOption2, strOption3, strOption4, strOption5,
						strOption6, fechaInicial, fechaFinal, folioInicial, folioFinal, strResponsable, strEstado,
						strEstadoImportados, strEntidad, usuario, strColumnFolio, strColumnFecha, strColumnTID,
						strColumnObservaciones, strColumnEstadoActual, strColumnTipo, strColumnResponsable,
						strColumnChasis, strColumnEntidad, strColumnEvento, loggerInfo, loggerError, consultaBD);

			}

			if (strGeneralQuery.equals("")) {

				ActionMessages errores = new ActionMessages();
				errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.reportes.nodatos"));
				saveErrors(request, errores);
				loggerError.error(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction genReporteUniversal - FIN*** "
						+ prop.strMessage("errors.reportes.nodatos"));
				// Limpiar logs
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				return mapping.findForward("totales");

			}
			
			String strSelectUsuario="";
			String strOrderBy="";		
				
				if(ultimoEvento==null && strOption5.equals("1")){
					
					strSelectUsuario="hist.ESTADO";
					strOrderBy="FECHA_HISTORICO";
					
				}
				
				if(ultimoEvento!=null && strOption5.equals("1")){
					
					strSelectUsuario="formato.ESTADO";
					strOrderBy="formato.folio";
					
				}
				
				if(ultimoEvento==null && !strOption5.equals("1")){
					
					strSelectUsuario="formato.ESTADO";
					strOrderBy="formato.folio";
					
				}

			arrQuerys = strGeneralQuery.split("[+]");

			if (arrQuerys.length == 1) {

				strGeneralQueryBackup = strGeneralQuery;
				listQuerys.add(strGeneralQuery.replace("+", ""));

			} else {

				strGeneralQueryBackup = strGeneralQuery;

				for (int i = 0; i < arrQuerys.length; i++) {

					listQuerys.add(arrQuerys[i]);

				}

			}

			// Reporte segmentado en lista
			if (!formulario.getIsFullReport().equals("1")) {

				if (strEstado.equals("Z") || strEstado.equals("999")) {

					strTitulo = prop.strMessage("etiqueta.constancias.estado_TOD.header");

					for (Estado est : estados) {

						strQueryEstado = "AND "+strSelectUsuario+"='" + est.getEstado() + "'";

						// Si es por responsable, se agrega un filtro para traer
						// el
						// usuario que ejecuto el estado solcitado
						if (strOption5.equals("1")) {

							strQueryEstado = " AND "+strSelectUsuario+"='" + est.getEstado() + "'";

						}

						int total = formatoDAO.intReporteGeneral(listQuerys,
								strQueryEstado + " ORDER BY "+strOrderBy+" asc");
						listaTotales.add(new Formato(est, total, null, null));
						totalEncontrados += total;
					}
				} else {
					for (Estado est : estados) {
						if (strEstado.equals(est.getEstado())) {

							strTitulo += " - " + est.getDescripcion();
							strQueryEstado = "AND "+strSelectUsuario+"='" + est.getEstado() + "'";

							if (strOption5.equals("1")) {

								strQueryEstado = " AND "+strSelectUsuario+"='" + est.getEstado() + "'";

							}

							int total = formatoDAO.intReporteGeneral(listQuerys,
									strQueryEstado + " ORDER BY "+strOrderBy+" asc");
							listaTotales.add(new Formato(est, total, null, null));
							totalEncontrados += total;
							break;
						}
					}
				}

				if (listaTotales.size() > 0) {
					isNotEmpty = true;
				}

				if (consultaBD.equals("0")) {

					// Si es una consulta por fecha a produccion, verificar que
					// rangos de fecha se encuentran respaldados
					ActionMessages mensajeFecha = new ActionMessages();

					ConfiguracionHistoricoDAO configuracionHistoricoDAO = DAOFactory.crearConfiguracionHistoricoDAO();
					List configs = configuracionHistoricoDAO.buscarPorEstatus("2");

					if (configs.size() > 0) {

						DateFormat format = new SimpleDateFormat("dd/MM/yy");
						Date fechaIn = format.parse(fechaInicial);
						ConfigJob cj = (ConfigJob) configs.get(configs.size() - 1);
						Date fechaFin = format.parse(fechaFinal);
						Date fechaResp = cj.getFechaConfig();
						
						if(strOption1.equals("on")){

						// Rango de busqueda antes de una fecha de respaldo
						if (fechaResp.after(fechaIn) && (fechaResp.after(fechaFin))) {

							mensajeFecha.add("",
									new ActionMessage("texto.etiqueta.reportes.strMensajeRangoBDFecha",
											new SimpleDateFormat("MM/dd/yy").format(fechaIn),
											new SimpleDateFormat("MM/dd/yy").format(fechaResp),
											totalEncontrados));
						}

						// Fecha de respaldo entre rango de busqueda
						if (fechaIn.before(fechaResp) && (fechaFin.after(fechaResp))) {

							mensajeFecha.add("",
									new ActionMessage("texto.etiqueta.reportes.strMensajeRangoBDFecha",
											new SimpleDateFormat("MM/dd/yy").format(fechaIn),
											new SimpleDateFormat("MM/dd/yy").format(fechaResp),
											totalEncontrados));
						}

						// Fecha de busqueda inicial o final igual a una fecha de respaldo
						// ejecutada
						if (fechaIn.equals(fechaResp) || fechaFin.equals(fechaResp)) {

							mensajeFecha.add("",
									new ActionMessage("texto.etiqueta.reportes.strMensajeRangoBDFecha",
											new SimpleDateFormat("MM/dd/yy").format(fechaIn),
											new SimpleDateFormat("MM/dd/yy").format(fechaResp),
											totalEncontrados));
						}
						
						
						}
						
						if (consultaBD.equals("0") && strOption3.equals("on") && !strOption5.equals("1")) {

							// Si es una consulta a produccion, verificar cuantos se
							// encontraron, enviar indicacion para consultar respaldo
							ActionMessages mensaje = new ActionMessages();

							String fi = Integer.valueOf(folioInicial).toString();
							String ff = Integer.valueOf(folioFinal).toString();
							Integer totalFolios = Integer.valueOf(ff) - Integer.valueOf(fi);

							if (totalEncontrados > 0) {

								if (totalEncontrados < totalFolios) {

									mensaje.add("", new ActionMessage("texto.etiqueta.reportes.strMensajeRangoBD",
											totalEncontrados, String.valueOf(totalFolios)));
									request.setAttribute("mensaje", mensaje);
								}

							} else {

								mensaje.add("", new ActionMessage("texto.etiqueta.reportes.strMensajeRangoBD", "0",
										String.valueOf(totalFolios)));
								request.setAttribute("mensaje", mensaje);
							}

						}

						request.setAttribute("mensajeFecha", mensajeFecha);

					}

				}

				request.setAttribute("totales", listaTotales);
				loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction genReporteUniversal - FIN***");
				return mapping.findForward("totales");

			} else {

				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				genDetalleTotales(mapping, form, request, response);
				loggerInfo = Utilidades.loggerInfo();
				loggerError = Utilidades.loggerError();

			}

			loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction genReporteUniversal - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return null;
		} catch (Exception e) {
			loggerError.error(FechaUtil.getHoraActual() + "_Fallo ReporteConstanciasIAction genReporteUniversal  "
					+ e);
			loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction genReporteUniversal - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
		}
		return null;
	}

	public String buildQuery(String strOption1, String strOption2, String strOption3, String strOption4,
			String strOption5, String strOption6, String fechaInicial, String fechaFinal, String folioInicial,
			String folioFinal, String strResponsable, String strEstado, String strEstadoImportados, String strEntidad,
			Usuario usuario, String strColumnFolio, String strColumnFecha, String strColumnTID,
			String strColumnObservaciones, String strColumnEstadoActual, String strColumnTipo,
			String strColumnResponsable, String strColumnChasis, String strColumnEntidad, String strColumnEvento,
			Logger loggerInfo, Logger loggerError, String consultaBD) throws Exception {

		loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction buildQuery Inicio Construir Consulta");
		String strQuery = "";

		try {

			prop = new GetProperties();
			String fechaInicialSplit[] = fechaInicial.split("/");
			String fechaFinalSplit[] = fechaFinal.split("/");
			String startDate = fechaInicialSplit[2] + "/" + fechaInicialSplit[1] + "/" + fechaInicialSplit[0];
			String finishDate = fechaFinalSplit[2] + "/" + fechaFinalSplit[1] + "/" + fechaFinalSplit[0];

			String startDateTitle = fechaInicialSplit[0] + "/" + fechaInicialSplit[1] + "/" + fechaInicialSplit[2];
			String finishDateTitle = fechaFinalSplit[0] + "/" + fechaFinalSplit[1] + "/" + fechaFinalSplit[2];

			String startDateFileName = fechaInicialSplit[0] + "/" + fechaInicialSplit[1] + "/"
					+ fechaInicialSplit[2].substring(2, 4);
			String finishDateFileName = fechaFinalSplit[0] + "/" + fechaFinalSplit[1] + "/"
					+ fechaFinalSplit[2].substring(2, 4);

			strNombreArchivo = "reporteCI_";
			strNombreArchivo += new SimpleDateFormat("ddMMyy").format(new Date())
					+ new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());

			if (strOption5.equals("1") && ultimoEvento==null) {

				strQuery = "SELECT hist.FOLIO as folio," + "hist.fecha_registro as fecha," + "ord.VIN as niv,"
						+ "(SELECT est.DESCRIPCION FROM"
						+ " ESTADOS_TP est WHERE est.ESTADO=hist.ESTADO) as estadoActual,"
						+ "(SELECT usuario.NOMBRE|| ' ' ||usuario.APELLIDO_PATERNO||' '||usuario.APELLIDO_MATERNO"
						+ " FROM USUARIOS usuario WHERE usuario.ID_USUARIO=hist.ID_USUARIO_ACTUAL) as responsable,"
						+ "ord.numero_chip as tagid," + "formato.OBSERVACIONES as observaciones,"
						+ "(SELECT ent.NOMBRE_ENTIDAD from ENTIDADES ent WHERE ent.ID_ENTIDAD=hist.ID_ENTIDAD) as nombreEntidad"
						+ " FROM FORMATOS_HISTORICO hist LEFT JOIN FORMATOS formato ON hist.FOLIO=formato.FOLIO "
						+ "LEFT JOIN ORDENES_IMPRESION ord ON formato.ID_ORDEN_IMPRESION=ord.ID_ORDEN_IMPRESION "
						+ "WHERE 1=1 ";

			} else {
				
				strQuery = "SELECT DISTINCT formato.FOLIO AS folio," + " formato.fecha_registro fecha,"
			            + " (SELECT us.NOMBRE || ' ' || us.APELLIDO_PATERNO || ' ' || us.APELLIDO_MATERNO FROM"
						+ " USUARIOS us WHERE us.ID_USUARIO=formato.ID_USUARIO) as responsable,"
						+ " ord.VIN as niv,est.DESCRIPCION as estadoActual" + " ," + " ord.numero_chip as tagid,"
						+ " formato.OBSERVACIONES as observaciones," + " ent.NOMBRE_ENTIDAD as nombreEntidad"
						+ " FROM FORMATOS formato LEFT JOIN ESTADOS_TP est"
						+ " ON est.ESTADO=formato.ESTADO LEFT JOIN ORDENES_IMPRESION ord"
						+ " ON formato.ID_ORDEN_IMPRESION=ord.ID_ORDEN_IMPRESION LEFT JOIN ENTIDADES ent"
						+ " ON formato.ID_ENTIDAD=ent.ID_ENTIDAD" + " WHERE 1=1 ";
			}
			strTitulo += "";
			strTitulo = prop.strMessage("etiqueta.constancias.estado.header");
			strTituloFiltros = prop.strMessage("etiqueta.constancias.reporte.por");

			Boolean reporteAgregaFechas = false;
			Boolean reporteAgregaEstado = false;
			Boolean reporteAgregaFolios = false;
			Boolean reporteAgregaImportados = false;
			Boolean reporteAgregaResponsable = false;
			Boolean reporteAgregaEntidad = false;
			Boolean reporteAgregaEvento = false;
			Boolean folioOk = false;
			Boolean fechaOk = false;
			Boolean tidOk = false;
			Boolean observacionesOk = false;
			Boolean chasisOk = false;
			Boolean estadoOk = false;
			Boolean responsableOk = false;
			Boolean tipoOk = false;
			Boolean entidadOk = false;
			Boolean eventoOk = false;

			if (strOption1.equals("on")) {

				// FILTRO FECHA
				// CONVERTIR A AÑO/MES/DIA
				reporteAgregaFechas = true;

				strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.por.fecha");
				strTituloFechas += prop.strMessage("etiqueta.constancias.reporte.rangos.fecha") + startDateTitle
						+ " al " + finishDateTitle + " - ";

				strQuery = strQuery + "AND formato.fecha_registro " + "BETWEEN to_timestamp('" + startDate
						+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss') " + "AND to_timestamp('" + finishDate
						+ " 23:59:59', 'yyyy-mm-dd hh24:mi:ss') ";

				strNombreArchivo += "_fe" + startDateFileName.replace("/", "") + "-"
						+ finishDateFileName.replace("/", "");

			}

			if (strOption3.equals("on") && (!folioInicial.trim().equals("") && !folioFinal.trim().equals(""))) {

				// FILTRO POR FOLIOS
				strQuery = strQuery + "AND formato.FOLIO BETWEEN " + folioInicial + " AND " + folioFinal + " ";
				strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.folios");
				reporteAgregaFolios = true;
				strNombreArchivo += "_fo" + folioInicial + "-" + folioFinal;
				strTituloFechas += prop.strMessage("etiqueta.constancias.reporte.rangos.folios") + folioInicial + " al "
						+ folioFinal + " - ";
			}

			if (strOption4.equals("on") && strEstadoImportados.equals("0")) {

				// FILTRO POR ESTADO DE IMPORTACION - IMPORTADOS
				strQuery = strQuery + "AND ((ord.VIN NOT LIKE '3VW%' AND ord.VIN NOT LIKE '3VV%') OR ord.GRUPO=2) ";
				strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.estadosImp");
				reporteAgregaImportados = true;
				strNombreArchivo += "_IMP";
				strTituloFechas += prop.strMessage("etiqueta.constancias.reporte.estadosImpHeader")
						+ prop.strMessage("etiqueta.constancias.reporte.estadosImpImp");
			}

			if (strOption4.equals("on") && strEstadoImportados.equals("1")) {

				// FILTRO POR ESTADO DE IMPORTACION - NACIONALES
				strQuery = strQuery + "AND ((ord.VIN LIKE '3VW%' OR ord.VIN LIKE '3VV%') OR ord.GRUPO=1) ";
				strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.estadosImp");
				strNombreArchivo += "_NAC";
				strTituloFechas += prop.strMessage("etiqueta.constancias.reporte.estadosImpHeader")
						+ prop.strMessage("etiqueta.constancias.reporte.estadosImpNac");
			}
			
			String strSelectUsuario="";

			if (strOption5.equals("1")) {

				// FILTRO POR RESPONSABLES
				if (!strResponsable.equals("-1")) {
					
					strSelectUsuario="";
					
					if(ultimoEvento==null){
						
						strSelectUsuario="hist.ID_USUARIO_ACTUAL";
						
					}else{
						
						strSelectUsuario="formato.ID_USUARIO";
						
					}

					strQuery = strQuery + "AND "+strSelectUsuario+"=" + strResponsable + " ";
					strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.responsable");
					reporteAgregaResponsable = true;
					reporteAgregaEvento = true;
					strNombreArchivo += "_re" + strResponsable;
					UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
					Usuario usu = usuarioDAO.buscarPorId(Long.valueOf(strResponsable));
					strTituloFechas += prop.strMessage("etiqueta.constancias.reporte.responsableheader")
							+ usu.getNombre() + " " + usu.getApellidoPaterno() + " " + usu.getApellidoMaterno() + " - ";

				} else {
					
					if(ultimoEvento==null){
						
						strSelectUsuario="hist.ID_USUARIO_ACTUAL";
						
					}else{
						
						strSelectUsuario="formato.ID_USUARIO";
						
					}

					String strQueryEntidades = "";
					String intResponsables = "";
					UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
					List<Usuario> usuarios = usuarioDAO.buscarUsuariosPantallaConstrasena(usuario);

					for (int i = 0; i < usuarios.size(); i++) {

						intResponsables += "'" + usuarios.get(i).getIdUsuario() + "'";

						if (i + 1 < usuarios.size()) {

							intResponsables += ",";
						}

						strQueryEntidades = "AND "+strSelectUsuario+" in (" + intResponsables + ")";

					}

					strQuery = strQuery + strQueryEntidades;
					strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.responsable");
					reporteAgregaResponsable = true;
					reporteAgregaEvento = true;
					strNombreArchivo += "_reTOD";
					strTituloFechas += prop.strMessage("etiqueta.constancias.reporte.responsableheader") + " Todos - ";

				}

			}

			if (strOption6.equals("on")) {

				// FILTRO POR ENTIDADES
				if (!strEntidad.equals("-1")) {

					strQuery = strQuery + "and formato.ID_ENTIDAD=" + strEntidad + " ";
					strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.entidad");
					reporteAgregaEntidad = true;
					strNombreArchivo += "_ent" + strEntidad;
					EntidadDAO entidadDAO = DAOFactory.crearEntidadDAO();
					strTituloFechas += prop.strMessage("etiqueta.constancias.reporte.entidadheader")
							+ entidadDAO.buscarPorId(Long.valueOf(strEntidad)).getNombreEntidad() + " - ";

				} else {

					String strQueryEntidades = "";
					String intEntidades = "";
					EntidadDAO entidadDAO = DAOFactory.crearEntidadDAO();
					List<Entidad> ent = null;
					reporteAgregaEntidad = true;

					if (usuario.getPerfil().getIdPerfil() == 1) {

						ent = entidadDAO.buscarEntidadesReportesConstancias();

					} else {

						ent = entidadDAO.buscarEntidadesPantallaAlta(usuario);

					}

					for (int i = 0; i < ent.size(); i++) {

						intEntidades += "'" + ent.get(i).getIdEntidad() + "'";

						if (i + 1 < ent.size()) {

							intEntidades += ",";
						}

						strQueryEntidades = "AND formato.ID_ENTIDAD in (" + intEntidades + ")";

					}

					strQuery = strQuery + strQueryEntidades;
					strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.entidad");
					reporteAgregaEntidad = true;
					strNombreArchivo += "_entTOD";
					strTituloFechas += prop.strMessage("etiqueta.constancias.reporte.entidadheader") + " Todas - ";

				}
			}

			if (strOption2.equals("on")) {

				// FILTRO POR TIPOS

				strEstado = "Z";
				reporteAgregaEstado = true;
				reporteAgregaFolios = true;
				strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.estados");
				strNombreArchivo += "_es";
			}

			strNombreArchivoBackup = strNombreArchivo;
			
			loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction buildQuery - FIN***");
			return strQuery;
		} catch (Exception e) {
			loggerError.error(
					FechaUtil.getHoraActual() + "_Fallo ReporteConstanciasIAction buildQuery  " + e);
			loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction buildQuery - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
		}
		return strQuery;
	}

	public ArrayList<String> OrdenEstados(List<Estado> estados, Logger logInfo, Logger logError) {

		ArrayList<String> ordenEstFinal = new ArrayList<String>();

		try {

			logInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction OrdenEstados - INICIO***");
			ArrayList<Integer> ordenEst = new ArrayList<Integer>();

			for (int i = 0; i < estados.size(); i++) {

				if (!estados.get(i).getEstado().equals("A")) {
					ordenEst.add(Integer.valueOf(estados.get(i).getEstado()));
				}
			}

			Collections.sort(ordenEst);

			for (int i = 0; i < ordenEst.size(); i++) {

				ordenEstFinal.add(String.valueOf(ordenEst.get(i)));
			}

			ordenEstFinal.add("A");

			logInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction OrdenEstados - FIN***");
			return ordenEstFinal;
		} catch (Exception e) {
			logError.error(
					FechaUtil.getHoraActual() + "_Fallo ReporteConstanciasIAction OrdenEstados  " + e);
			logInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction OrdenEstados - FIN***");
		}
		return ordenEstFinal;
	}

	public String todasEntidades(HttpServletRequest request) {

		Boolean todasEntidades = false;
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");

		if (usuario.getPerfil().getIdPerfil() == 1) {

			return "1";

		} else {

			return "0";

		}

	}

	public String isAdmin(HttpServletRequest request) {

		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");
		String isAdmin = "hidden";

		if (usuario.getIdUsuario() == 1) {
			isAdmin = "visible";
		}

		return isAdmin;

	}

	public String buildQueryRespaldo(String strOption1, String strOption2, String strOption3, String strOption4,
			String strOption5, String strOption6, String fechaInicial, String fechaFinal, String folioInicial,
			String folioFinal, String strResponsable, String strEstado, String strEstadoImportados, String strEntidad,
			Usuario usuario, String strColumnFolio, String strColumnFecha, String strColumnTID,
			String strColumnObservaciones, String strColumnEstadoActual, String strColumnTipo,
			String strColumnResponsable, String strColumnChasis, String strColumnEntidad, String strColumnEvento,
			Logger loggerInfo, Logger loggerError, String consultaBD) throws Exception {

		loggerInfo.info(
				FechaUtil.getHoraActual() + "_ReporteConstanciasIAction buildQueryRespaldo Inicio Construir Consulta");
		String strQuery = "";
		RespaldoHistoricoDAO respaldoHistoricoDAO = DAOFactory.crearRespaldoHistoricoDAO();
		listQuerys = new ArrayList<String>();

		try {
			String fechaInicialSplit[] = fechaInicial.split("/");
			String fechaFinalSplit[] = fechaFinal.split("/");
			String startDate = fechaInicialSplit[2] + "/" + fechaInicialSplit[1] + "/" + fechaInicialSplit[0];
			String finishDate = fechaFinalSplit[2] + "/" + fechaFinalSplit[1] + "/" + fechaFinalSplit[0];

			String startDateTitle = fechaInicialSplit[0] + "/" + fechaInicialSplit[1] + "/" + fechaInicialSplit[2];
			String finishDateTitle = fechaFinalSplit[0] + "/" + fechaFinalSplit[1] + "/" + fechaFinalSplit[2];

			String startDateFileName = fechaInicialSplit[0] + "/" + fechaInicialSplit[1] + "/"
					+ fechaInicialSplit[2].substring(2, 4);
			String finishDateFileName = fechaFinalSplit[0] + "/" + fechaFinalSplit[1] + "/"
					+ fechaFinalSplit[2].substring(2, 4);

			String esquemaProd = VariablesGlobales.getEsquemaProd();
			String esquemaRespaldo = VariablesGlobales.getEsquemaRespaldo();

			strNombreArchivo = "reporteCI_RespaldoHistorico_";
			strNombreArchivo += new SimpleDateFormat("ddMMyy").format(new Date())
					+ new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());

			if (strOption5.equals("1") && ultimoEvento==null) {

				strQuery = "SELECT hist.FOLIO as folio," + "hist.fecha_registro as fecha," + "ord.VIN as niv,"
						+ "(SELECT est.DESCRIPCION FROM " + esquemaProd
						+ ".ESTADOS_TP est WHERE est.ESTADO=hist.ESTADO) as estadoActual,"
						+ "(SELECT usuario.NOMBRE|| ' ' ||usuario.APELLIDO_PATERNO||' '||usuario.APELLIDO_MATERNO"
						+ " FROM " + esquemaProd
						+ ".USUARIOS usuario WHERE usuario.ID_USUARIO=hist.ID_USUARIO_ACTUAL) as responsable,"
						+ "ord.numero_chip as tagid," + "formato.OBSERVACIONES as observaciones,"
						+ "(SELECT ent.NOMBRE_ENTIDAD from " + esquemaProd
						+ ".ENTIDADES ent WHERE ent.ID_ENTIDAD=hist.ID_ENTIDAD) as nombreEntidad" + " FROM "
						+ esquemaRespaldo + ".FORMATOSHISTORICO hist LEFT JOIN " + esquemaRespaldo
						+ ".TABLAFOLIOS formato ON hist.FOLIO=formato.FOLIO " + "LEFT JOIN " + esquemaRespaldo
						+ ".ORDENESIMPRESION ord ON formato.ID_ORDEN_IMPRESION=ord.ID_ORDEN_IMPRESION " + "WHERE 1=1 ";

			} else {
				strQuery = "SELECT DISTINCT formato.FOLIO AS folio," + " formato.fecha_registro fecha,"
						+ " (SELECT us.NOMBRE || ' ' || us.APELLIDO_PATERNO || ' ' || us.APELLIDO_MATERNO FROM "
						+ esquemaProd+".USUARIOS us WHERE us.ID_USUARIO=formato.ID_USUARIO) as responsable,"
						+ " ord.VIN as niv,est.DESCRIPCION as estadoActual" + " ," + " ord.numero_chip as tagid,"
						+ " formato.OBSERVACIONES as observaciones," + " ent.NOMBRE_ENTIDAD as nombreEntidad" + " FROM "
						+ esquemaRespaldo + ".TABLAFOLIOS formato LEFT JOIN " + esquemaProd + ".ESTADOS_TP est"
						+ " ON est.ESTADO=formato.ESTADO LEFT JOIN " + esquemaRespaldo + ".ORDENESIMPRESION ord"
						+ " ON formato.ID_ORDEN_IMPRESION=ord.ID_ORDEN_IMPRESION LEFT JOIN " + esquemaProd
						+ ".ENTIDADES ent" + " ON formato.ID_ENTIDAD=ent.ID_ENTIDAD" + " WHERE 1=1 ";

			}
			strTitulo += "";
			strTitulo = prop.strMessage("etiqueta.constancias.estado.header");
			strTituloFiltros = prop.strMessage("etiqueta.constancias.reporte.por");

			Boolean reporteAgregaFechas = false;
			Boolean reporteAgregaEstado = false;
			Boolean reporteAgregaFolios = false;
			Boolean reporteAgregaImportados = false;
			Boolean reporteAgregaResponsable = false;
			Boolean reporteAgregaEntidad = false;
			Boolean reporteAgregaEvento = false;
			Boolean folioOk = false;
			Boolean fechaOk = false;
			Boolean tidOk = false;
			Boolean observacionesOk = false;
			Boolean chasisOk = false;
			Boolean estadoOk = false;
			Boolean responsableOk = false;
			Boolean tipoOk = false;
			Boolean entidadOk = false;
			Boolean eventoOk = false;
			ArrayList<String> listTablasResp = new ArrayList<String>();

			// REVISAR QUE EXISTAN TABLAS EN BD DE RESPALDO
			listTablasResp = respaldoHistoricoDAO.existenTablasFormatos(strOption1, startDate, finishDate, strOption5);
			if (listTablasResp == null || listTablasResp.size() == 0) {
				loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction buildQueryRespaldo - FIN***");
				return "";
			}

			if (strOption3.equals("on") && (!folioInicial.trim().equals("") && !folioFinal.trim().equals(""))) {

				// FILTRO POR FOLIOS
				strQuery = strQuery + "AND formato.FOLIO BETWEEN " + folioInicial + " AND " + folioFinal + " ";
				strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.folios");
				reporteAgregaFolios = true;
				strNombreArchivo += "_fo" + folioInicial + "-" + folioFinal;
				strTituloFechas += prop.strMessage("etiqueta.constancias.reporte.rangos.folios") + folioInicial + " al "
						+ folioFinal + " - ";
			}

			if (strOption4.equals("on") && strEstadoImportados.equals("0")) {

				// FILTRO POR ESTADO DE IMPORTACION - IMPORTADOS
				strQuery = strQuery + "AND ((ord.VIN NOT LIKE '3VW%' AND ord.VIN NOT LIKE '3VV%') OR ord.GRUPO=2) ";
				strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.estadosImp");
				reporteAgregaImportados = true;
				strNombreArchivo += "_IMP";
				strTituloFechas += prop.strMessage("etiqueta.constancias.reporte.estadosImpHeader")
						+ prop.strMessage("etiqueta.constancias.reporte.estadosImpImp");
			}

			if (strOption4.equals("on") && strEstadoImportados.equals("1")) {

				// FILTRO POR ESTADO DE IMPORTACION - NACIONALES
				strQuery = strQuery + "AND ((ord.VIN LIKE '3VW%' OR ord.VIN LIKE '3VV%') OR ord.GRUPO=1) ";
				strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.estadosImp");
				strNombreArchivo += "_NAC";
				strTituloFechas += prop.strMessage("etiqueta.constancias.reporte.estadosImpHeader")
						+ prop.strMessage("etiqueta.constancias.reporte.estadosImpNac");
			}

			String strSelectUsuario="";
			
			if (strOption5.equals("1")) {

				// FILTRO POR RESPONSABLES
				if (!strResponsable.equals("-1")) {
					
					strSelectUsuario="";
					
					if(ultimoEvento==null){
						
						strSelectUsuario="hist.ID_USUARIO_ACTUAL";
						
					}else{
						
						strSelectUsuario="formato.ID_USUARIO";
						
					}
					

					strQuery = strQuery + "AND "+strSelectUsuario+"=" + strResponsable + " ";
					strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.responsable");
					reporteAgregaResponsable = true;
					reporteAgregaEvento = true;
					strNombreArchivo += "_re" + strResponsable;
					UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
					Usuario usu = usuarioDAO.buscarPorId(Long.valueOf(strResponsable));
					strTituloFechas += prop.strMessage("etiqueta.constancias.reporte.responsableheader")
							+ usu.getNombre() + " " + usu.getApellidoPaterno() + " " + usu.getApellidoMaterno() + " - ";

				} else {
					
					if(ultimoEvento==null){
						
						strSelectUsuario="hist.ID_USUARIO_ACTUAL";
						
					}else{
						
						strSelectUsuario="formato.ID_USUARIO";
						
					}

					String strQueryEntidades = "";
					String intResponsables = "";
					UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
					List<Usuario> usuarios = usuarioDAO.buscarUsuariosPantallaConstrasena(usuario);

					for (int i = 0; i < usuarios.size(); i++) {

						intResponsables += "'" + usuarios.get(i).getIdUsuario() + "'";

						if (i + 1 < usuarios.size()) {

							intResponsables += ",";
						}

						strQueryEntidades = "AND "+strSelectUsuario+" in (" + intResponsables + ")";

					}

					strQuery = strQuery + strQueryEntidades;
					strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.responsable");
					reporteAgregaResponsable = true;
					reporteAgregaEvento = true;
					strNombreArchivo += "_reTOD";
					strTituloFechas += prop.strMessage("etiqueta.constancias.reporte.responsableheader") + " Todos - ";

				}

			}

			if (strOption6.equals("on")) {

				// FILTRO POR ENTIDADES
				if (!strEntidad.equals("-1")) {

					strQuery = strQuery + "and formato.ID_ENTIDAD=" + strEntidad + " ";
					strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.entidad");
					reporteAgregaEntidad = true;
					strNombreArchivo += "_ent" + strEntidad;
					EntidadDAO entidadDAO = DAOFactory.crearEntidadDAO();
					strTituloFechas += prop.strMessage("etiqueta.constancias.reporte.entidadheader")
							+ entidadDAO.buscarPorId(Long.valueOf(strEntidad)).getNombreEntidad() + " - ";

				} else {

					String strQueryEntidades = "";
					String intEntidades = "";
					EntidadDAO entidadDAO = DAOFactory.crearEntidadDAO();
					List<Entidad> ent = null;
					reporteAgregaEntidad = true;

					if (usuario.getPerfil().getIdPerfil() == 1) {

						ent = entidadDAO.buscarEntidadesReportesConstancias();

					} else {

						ent = entidadDAO.buscarEntidadesPantallaAlta(usuario);

					}

					for (int i = 0; i < ent.size(); i++) {

						intEntidades += "'" + ent.get(i).getIdEntidad() + "'";

						if (i + 1 < ent.size()) {

							intEntidades += ",";
						}

						strQueryEntidades = "AND formato.ID_ENTIDAD in (" + intEntidades + ")";

					}

					strQuery = strQuery + strQueryEntidades;
					strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.entidad");
					reporteAgregaEntidad = true;
					strNombreArchivo += "_entTOD";
					strTituloFechas += prop.strMessage("etiqueta.constancias.reporte.entidadheader") + " Todas - ";

				}
			}

			if (strOption2.equals("on")) {

				// FILTRO POR TIPOS

				strEstado = "Z";
				reporteAgregaEstado = true;
				reporteAgregaFolios = true;
				strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.estados");
				strNombreArchivo += "_es";
			}

			if (strOption1.equals("on")) {

				strQuery = strQuery + "AND formato.fecha_registro " + "BETWEEN to_timestamp('" + startDate
						+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss') " + "AND to_timestamp('" + finishDate
						+ " 23:59:59', 'yyyy-mm-dd hh24:mi:ss')";

				// FILTRO FECHA
				// CONVERTIR A AÑO/MES/DIA
				reporteAgregaFechas = true;

				strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.por.fecha");
				strTituloFechas += prop.strMessage("etiqueta.constancias.reporte.rangos.fecha") + startDateTitle
						+ " al " + finishDateTitle + " - ";

				strNombreArchivo += "_fe" + startDateFileName.replace("/", "") + "-"
						+ finishDateFileName.replace("/", "");

			}

			// Generar uno o varios querys dependiendo de los filtros y las
			// tablas existentes en respaldo

			// Filtro por fecha
			if (strOption1.equals("on")) {

				// Años iguales = una sola tabla, un solo query
				if (fechaFinalSplit[2].equals(fechaInicialSplit[2])) {

					strQuery = strQuery.replace("TABLAFOLIOS", "FORMATOS_" + fechaInicialSplit[2].substring(2, 4));
					strQuery = strQuery.replace("ORDENESIMPRESION",
							"ORDENES_IMPRESION_" + fechaInicialSplit[2].substring(2, 4));
					strQuery = strQuery.replace("FORMATOSHISTORICO",
							"FORMATOS_HISTORICO_" + fechaInicialSplit[2].substring(2, 4));

					strQuery = strQuery + "AND formato.fecha_registro " + "BETWEEN to_timestamp('" + startDate
							+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss') " + "AND to_timestamp('" + finishDate
							+ " 23:59:59', 'yyyy-mm-dd hh24:mi:ss')";
				}

				// Años diferentes = dos tablas, dos querys
				if (!fechaFinalSplit[2].equals(fechaInicialSplit[2])) {

					String strQueryTemp = strQuery;

					strQuery = strQuery.replace("TABLAFOLIOS", "FORMATOS_" + fechaInicialSplit[2].substring(2, 4));
					strQuery = strQuery.replace("ORDENESIMPRESION",
							"ORDENES_IMPRESION_" + fechaInicialSplit[2].substring(2, 4));
					strQuery = strQuery.replace("FORMATOSHISTORICO",
							"FORMATOS_HISTORICO_" + fechaInicialSplit[2].substring(2, 4));

					strQuery = strQuery + "AND formato.fecha_registro " + "BETWEEN to_timestamp('" + startDate
							+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss') " + "AND to_timestamp('" + finishDate
							+ " 23:59:59', 'yyyy-mm-dd hh24:mi:ss')+";

					strQueryTemp = strQueryTemp.replace("FORMATOS", "FORMATOS_" + fechaFinalSplit[2].substring(2, 4));
					strQueryTemp = strQueryTemp.replace("ORDENESIMPRESION",
							"ORDENES_IMPRESION_" + fechaFinalSplit[2].substring(2, 4));
					strQueryTemp = strQueryTemp.replace("FH", "FORMATOSHISTORICO" + fechaFinalSplit[2].substring(2, 4));

					strQueryTemp = strQueryTemp + "AND formato.fecha_registro " + "BETWEEN to_timestamp('" + startDate
							+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss') " + "AND to_timestamp('" + finishDate
							+ " 23:59:59', 'yyyy-mm-dd hh24:mi:ss') ";

					strQuery = strQuery + strQueryTemp;

				}

			}
			// Otros filtros: Cuando no se utiliza fecha, es necesario buscar en
			// todas las tablas existentes.

			String strQueryResp = strQuery;
			String newQuery = "";

			if (!strOption1.equals("on")) {

				// Llenar nombres de tablas formatos, como referencia para
				// construir querys

				for (int i = 0; i < listTablasResp.size(); i++) {

					strQueryResp = strQueryResp.replace("TABLAFOLIOS", listTablasResp.get(i));

					strQueryResp = strQueryResp.replace("FORMATOSHISTORICO",
							"FORMATOS_HISTORICO" + listTablasResp.get(i).substring(8, 11));

					strQueryResp = strQueryResp.replace("ORDENESIMPRESION",
							"ORDENES_IMPRESION" + listTablasResp.get(i).substring(8, 11));

					newQuery = newQuery + strQueryResp + "+";
					strQueryResp = strQuery;
				}
				strQuery = newQuery;
			}

			strNombreArchivoBackup = strNombreArchivo;

			loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction buildQuery - FIN***");
			return strQuery;
		} catch (Exception e) {
			loggerError.error(
					FechaUtil.getHoraActual() + "_Fallo ReporteConstanciasIAction buildQuery  " + e);
			loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteConstanciasIAction buildQuery - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
		}
		return strQuery;
	}

}
