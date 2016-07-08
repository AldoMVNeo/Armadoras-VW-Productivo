package neology.vc.actions.reportes;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import neology.modelo.dto.ConfigJob;
import neology.modelo.dto.Entidad;
import neology.modelo.dto.Estado;
import neology.modelo.dto.Formato;
import neology.modelo.dto.TipoFormato;
import neology.modelo.dto.Usuario;
import neology.modelo.dto.id.FormatoId;
import neology.modelo.negocio.daos.ConfiguracionHistoricoDAO;
import neology.modelo.negocio.daos.EntidadDAO;
import neology.modelo.negocio.daos.EstadoDAO;
import neology.modelo.negocio.daos.FormatoDAO;
import neology.modelo.negocio.daos.RespaldoHistoricoDAO;
import neology.modelo.negocio.daos.UsuarioDAO;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.recursos.GetProperties;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.util.VariablesGlobales;
import neology.vc.forms.reportes.ReporteGeneralDatosForm;
import neology.vc.forms.reportes.ReporteGeneralDatosFormGeneric;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Servlet implementation class ReporteArchivo
 */
@WebServlet("/reporte")
public class ReporteArchivo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String strNombreArchivo = "";
	GetProperties prop = new GetProperties();
	String strTitulo = "";
	String strTituloFiltros = "";
	String strTituloFechas;
	String strNombreArchivoBackup;
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
	ArrayList<String> listQuerys = new ArrayList<String>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReporteArchivo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();

		loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteArchivo doGet - INICIO***");

		BufferedReader reader = request.getReader();
		String strData = "";
		JSONObject obj;
		String strOption1 = "";
		String strOption2 = "";
		String strOption3 = "";
		String strOption4 = "";
		String strOption5 = "";
		String strOption6 = "";
		String strColumnFolio = "";
		String strColumnFecha = "";
		String strColumnTID = "";
		String strColumnObservaciones = "";
		String strColumnEstadoActual = "";
		String strColumnTipo = "";
		String strColumnResponsable = "";
		String strColumnChasis = "";
		String strColumnEntidad = "";
		String strColumnEvento = "";
		String consultaBD = "";
		String strEstado = "";
		String fechaInicial = "";
		String fechaFinal = "";
		String folioInicial = "";
		String folioFinal = "";
		String strResponsable = "";
		String strEstadoImportados = "";
		String strEntidad = "";
		String strMessage = "";
		String strTituloEstados = "";
		String strGeneralQueryBackup;
		String ultimoEvento = "";

		strNombreArchivo = "";
		prop = new GetProperties();
		strTitulo = "";
		strTituloFiltros = "";
		strTituloFechas = "";
		strNombreArchivoBackup = "";
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
		listQuerys = new ArrayList<String>();

		JSONObject json = new JSONObject();
		Map parametros = new HashMap();
		EstadoDAO estadoDAO = DAOFactory.crearEstadoDAO();
		FormatoDAO formDAO = DAOFactory.crearFormatoDAO();
		List<Estado> estados = estadoDAO.obtenerEstados();
		JRXlsExporter exporter = new JRXlsExporter();
		List<ReporteGeneralDatosForm> reportList = new ArrayList<ReporteGeneralDatosForm>();
		List<ReporteGeneralDatosForm> reportListUltimoEvento = new ArrayList<ReporteGeneralDatosForm>();
		List<ReporteGeneralDatosFormGeneric> reportListFinal = new ArrayList<ReporteGeneralDatosFormGeneric>();
		String strQuerys[] = new String[1];
		String strQueryEstado = "";
		ReporteConstanciasIAction rep = new ReporteConstanciasIAction();
		FormatoDAO formatoDAO = DAOFactory.crearFormatoDAO();
		Estado estadoDTO = null;
		Boolean isNotEmpty = false;
		String path = "";

		String strGeneralQuery = "";
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");

		try {
			String line;
			while ((line = reader.readLine()) != null) {
				strData += line + "\n";
			}
		} finally {
			reader.close();
		}

		try {

			strOption1 = request.getParameter("strOptionHidden1");
			strOption2 = request.getParameter("strOptionHidden2");
			strOption3 = request.getParameter("strOptionHidden3");
			strOption4 = request.getParameter("strOptionHidden4");
			strOption5 = request.getParameter("strOptionHidden5");
			strOption6 = request.getParameter("strOptionHidden6");
			strColumnFolio = request.getParameter("strColumnFolio");
			strColumnFecha = request.getParameter("strColumnFecha");
			strColumnTID = request.getParameter("strColumnTID");
			strColumnObservaciones = request.getParameter("strColumnObservaciones");
			strColumnEstadoActual = request.getParameter("strColumnEstadoActual");
			strColumnTipo = request.getParameter("strColumnTipo");
			strColumnResponsable = request.getParameter("strColumnResponsable");
			strColumnChasis = request.getParameter("strColumnChasis");
			strColumnEntidad = request.getParameter("strColumnEntidad");
			strColumnEvento = request.getParameter("strColumnEvento");
			consultaBD = request.getParameter("consultaBD");
			strEstado = request.getParameter("strEstado");
			fechaInicial = request.getParameter("fechaInicial");
			fechaFinal = request.getParameter("fechaFinal");
			folioInicial = request.getParameter("folioInicial");
			folioFinal = request.getParameter("folioFinal");
			strResponsable = request.getParameter("strResponsable");
			strEstadoImportados = request.getParameter("strEstadoImportados");
			strEntidad = request.getParameter("strEntidad");
			ultimoEvento = request.getParameter("ultimoEvento");

			if (!strOption1.equals("on") || !strOption3.equals("on")) {

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
						strMessage = prop.strMessage("etiqueta.constancias.reporte.fechas.error_1");

					}

					if (dtfinishDate.compareTo(new Date()) > 0) {
						strMessage = prop.strMessage("etiqueta.constancias.reporte.fechas.error_1fin");
					}

					// Comparar que la fecha inicial sea menor a la fecha final
					if (dtstartDate.compareTo(dtfinishDate) > 0) {
						strMessage = prop.strMessage("etiqueta.constancias.reporte.fechas.error_2");
					}

					// Máximo 30 dias antes
					if (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) > Integer
							.valueOf(VariablesGlobales.getStrRangoFechas())) {
						strMessage = prop.strMessage("etiqueta.constancias.reporte.fechas.error_3_Servlet") + " "
								+ VariablesGlobales.getStrRangoFechas();
					}

				}

				if (folioInicial.equals("on") && folioFinal.equals("on")) {

					if (folioInicial.trim().equals("") || folioFinal.trim().equals("")) {
						strMessage = prop.strMessage("etiqueta.constancias.reporte.folios.error_2");
					}

					if (Integer.valueOf(folioInicial) > Integer.valueOf(folioFinal)) {
						strMessage = prop.strMessage("etiqueta.constancias.reporte.folios.error_1");

					}

				}
			}

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

			if (consultaBD.equals("0")) {
				strGeneralQuery = buildQuery(strOption1, strOption2, strOption3, strOption4, strOption5, strOption6,
						fechaInicial, fechaFinal, folioInicial, folioFinal, strResponsable, strEstado,
						strEstadoImportados, strEntidad, usuario, strColumnFolio, strColumnFecha, strColumnTID,
						strColumnObservaciones, strColumnEstadoActual, strColumnTipo, strColumnResponsable,
						strColumnChasis, strColumnEntidad, strColumnEvento, loggerInfo, loggerError, consultaBD,
						ultimoEvento);
			} else {

				strGeneralQuery = buildQueryRespaldo(strOption1, strOption2, strOption3, strOption4, strOption5,
						strOption6, fechaInicial, fechaFinal, folioInicial, folioFinal, strResponsable, strEstado,
						strEstadoImportados, strEntidad, usuario, strColumnFolio, strColumnFecha, strColumnTID,
						strColumnObservaciones, strColumnEstadoActual, strColumnTipo, strColumnResponsable,
						strColumnChasis, strColumnEntidad, strColumnEvento, loggerInfo, loggerError, consultaBD,
						ultimoEvento);
			}

			if (!strGeneralQuery.equals("")) {

				strGeneralQueryBackup = strGeneralQuery;

				if (!strGeneralQueryBackup.contains("+")) {

					strGeneralQuery = strGeneralQueryBackup;
					strQuerys[0] = strGeneralQuery;

				} else {

					strQuerys = strGeneralQuery.split("[+]");

				}
				strNombreArchivo = strNombreArchivoBackup;

				loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteArchivo doGet Inicio Generar Reporte");
				if (strOption5 == null) {

					strOption5 = "0";
				}

				if (strEstado.equals("Z") && !strEstado.equals("999")) {

					String inEstados = "";

					if (!strOption5.equals("1")) {

						for (int i = 0; i < estados.size(); i++) {

							inEstados += "'" + estados.get(i).getEstado() + "'";

							if (i + 1 < estados.size()) {

								inEstados += ",";
							}

							strQueryEstado = "AND formato.ESTADO in (" + inEstados + ")";

						}

					} else {

						String strEstadoResp = "";
						List<ReporteGeneralDatosForm> reportListTemp = new ArrayList<ReporteGeneralDatosForm>();
						List<ReporteGeneralDatosForm> reportListTempResp = new ArrayList<ReporteGeneralDatosForm>();
						ArrayList<String> ordenEstados = rep.OrdenEstados(estados, loggerInfo, loggerError);
						String strOrder = "";
						String strSelectUsuario = "";

						if (ultimoEvento.equals("false")) {

							strSelectUsuario = "hist.ESTADO";
							strOrder = "FECHA_HISTORICO";

						} else {

							strSelectUsuario = "formato.ESTADO";
							strOrder = "formato.folio";

						}

						for (int i = 0; i < ordenEstados.size(); i++) {

							strEstadoResp = "AND " + strSelectUsuario + "='" + ordenEstados.get(i) + "'";
							reportListTemp = new ArrayList<ReporteGeneralDatosForm>();
							reportListTempResp = new ArrayList<ReporteGeneralDatosForm>();

							for (int x = 0; x < strQuerys.length; x++) {

								reportListTempResp = formatoDAO.listReporteGeneral(
										strQuerys[x] + strEstadoResp + " ORDER BY " + strOrder + " asc");

								for (int z = 0; z < reportListTempResp.size(); z++) {

									reportListTemp.add(reportListTempResp.get(z));

								}

							}

							for (int j = 0; j < reportListTemp.size(); j++) {

								reportList.add(reportListTemp.get(j));

							}

						}

					}
				}

				if (!strEstado.equals("Z") && !strEstado.equals("999")) {

					strQueryEstado = "AND formato.ESTADO = '" + strEstado + "'";

					if (strOption5.equals("1")) {

						strQueryEstado = " AND hist.ESTADO='" + strEstado + "'";

					}

					estadoDTO = estadoDAO.buscarPorId(strEstado);

				}

				if (strEstado.equals("Z") || strEstado.equals("999")) {

					String inEstados = "";
					for (int i = 0; i < estados.size(); i++) {

						inEstados += "'" + estados.get(i).getEstado() + "'";

						if (i + 1 < estados.size()) {

							inEstados += ",";
						}

						strQueryEstado = "AND formato.ESTADO in (" + inEstados + ")";

					}

					if (strEstado.equals("Z")) {
						strTituloEstados += prop.strMessage("etiqueta.estado.header") + " "
								+ prop.strMessage("combo.opcion.todos");
					}
				}

				if (reportList.size() == 0) {

					if (strOption5.equals("1")) {

						String strOrder = "";

						if (ultimoEvento.equals("false")) {

							strOrder = "FECHA_HISTORICO";

						} else {

							strOrder = "formato.folio";

						}

						List<ReporteGeneralDatosForm> reportListTempResp = new ArrayList<ReporteGeneralDatosForm>();

						for (int i = 0; i < strQuerys.length; i++) {

							reportListTempResp = formatoDAO.listReporteGeneral(
									strQuerys[i] + strQueryEstado + "ORDER BY " + strOrder + " asc");

							for (int y = 0; y < reportListTempResp.size(); y++) {

								reportList.add(reportListTempResp.get(y));

							}

						}

					} else {

						List<ReporteGeneralDatosForm> reportListTempResp = new ArrayList<ReporteGeneralDatosForm>();

						for (int i = 0; i < strQuerys.length; i++) {

							reportListTempResp = formatoDAO
									.listReporteGeneral(strQuerys[i] + strQueryEstado + "ORDER BY formato.FOLIO asc");

							for (int y = 0; y < reportListTempResp.size(); y++) {

								reportList.add(reportListTempResp.get(y));

							}

						}

						// reportList = formatoDAO
						// .listReporteGeneral(strGeneralQuery + strQueryEstado
						// + "
						// ORDER BY formato.FOLIO asc");
					}

				}
				if (reportList.size() > 0) {

					isNotEmpty = true;

				} else {

					strMessage = prop.strMessage("errors.reportes.nodatos");
					json.put("listRegistros", reportListFinal);
				}

				if (reportList.size() > 0 && reportList != null) {

					for (ReporteGeneralDatosForm reportObj : reportList) {

						ReporteGeneralDatosFormGeneric finalObj = new ReporteGeneralDatosFormGeneric();

						reportListFinal.add(finalObj);

					}
					
					Integer totalEncontrados =0;
					
					if (reportListFinal.size() > 0) {
						
						totalEncontrados = reportListFinal.size();

						strMessage = prop.strMessage("reportes.datos");

						if (consultaBD.equals("0")) {

							// Si es una consulta por fecha a produccion,
							// verificar que rangos de fecha se encuentran
							// respaldados

							ConfiguracionHistoricoDAO configuracionHistoricoDAO = DAOFactory
									.crearConfiguracionHistoricoDAO();
							List configs = configuracionHistoricoDAO.buscarPorEstatus("2");

							if (configs.size() > 0) {

								DateFormat format = new SimpleDateFormat("dd/MM/yy");
								Date fechaIn = format.parse(fechaInicial);
								ConfigJob cj = (ConfigJob) configs.get(configs.size() - 1);
								Date fechaFin = format.parse(fechaFinal);
								Date fechaResp = cj.getFechaConfig();

								// Rango de busqueda antes de una fecha de
								// respaldo

								if (strOption1.equals("on")) {

									if (fechaResp.after(fechaIn) && (fechaResp.after(fechaFin))) {

										strMessage = "Descargando reporte: Se encontraron "+totalEncontrados+" registros entre las fechas "
												+ new SimpleDateFormat("MM/dd/yy").format(fechaIn) + " y "
												+ new SimpleDateFormat("MM/dd/yy").format(fechaResp)
												+ ", consultar Respaldo de Historicos";
									}

									// Fecha de respaldo entre rango de busqueda
									if (fechaIn.before(fechaResp) && (fechaFin.after(fechaResp))) {

										strMessage = "Descargando reporte: Se encontraron "+totalEncontrados+" registros entre las fechas "
												+ new SimpleDateFormat("MM/dd/yy").format(fechaIn) + " y "
												+ new SimpleDateFormat("MM/dd/yy").format(fechaResp)
												+ ", consultar Respaldo de Historicos";
									}

									// Fecha de busqueda igual a una fecha de
									// respaldo ejecutada
									if (fechaIn.equals(fechaResp) || fechaFin.equals(fechaResp)) {

										strMessage = "Descargando reporte: Se encontraron "+totalEncontrados+" registros entre las fechas "
												+ new SimpleDateFormat("MM/dd/yy").format(fechaIn) + " y "
												+ new SimpleDateFormat("MM/dd/yy").format(fechaResp)
												+ ", consultar Respaldo de Historicos";
									}

								}

								if (consultaBD.equals("0") && strOption3.equals("on") && !strOption5.equals("1")) {

									// Si es una consulta a produccion,
									// verificar
									// cuantos se encontraron, enviar indicacion
									// para
									// consultar respaldo

									String fi = Integer.valueOf(folioInicial).toString();
									String ff = Integer.valueOf(folioFinal).toString();
									Integer totalFolios = Integer.valueOf(ff) - Integer.valueOf(fi);
									

									if (totalEncontrados > 0) {

										if (totalEncontrados < totalFolios) {

											strMessage = "Descargando Reporte: Se encontraron " + totalEncontrados
													+ " registros de " + totalFolios
													+ ", consultar Respaldo de Historicos";
										}

									}

								}
							}
						}

					} else {

						strMessage = prop.strMessage("errors.reportes.nodatos");

					}
				}

				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
			}

			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			json.put("strMessage", strMessage);
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(json.toString());
			out.close();

		} catch (JSONException e) {
			e.printStackTrace();
			
			loggerError.error(FechaUtil.getHoraActual() + "_Fallo ReporteArchivo doGet" + e);
			loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteArchivo doGet - FIN***");
			
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			loggerError.error(FechaUtil.getHoraActual() + "_Fallo ReporteArchivo doGet " + e);
			loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteArchivo doGet - FIN***");
			
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = "";
		List<ReporteGeneralDatosFormGeneric> reportListFinal = new ArrayList<ReporteGeneralDatosFormGeneric>();

		try {

			Map parametros = new HashMap();
			JRXlsExporter exporter = new JRXlsExporter();
			parametros.put("strTituloEstado", request.getParameter("strTitulo"));
			parametros.put("strTituloFiltros", request.getParameter("strTituloFiltros"));
			parametros.put("strTituloFechas", request.getParameter("strTituloFechas"));
			parametros.put("strColumn1", request.getParameter("strColumn1"));
			parametros.put("strColumn2", request.getParameter("strColumn2"));
			parametros.put("strColumn3", request.getParameter("strColumn3"));
			parametros.put("strColumn4", request.getParameter("strColumn4"));
			parametros.put("strColumn5", request.getParameter("strColumn5"));
			parametros.put("strColumn6", request.getParameter("strColumn6"));
			parametros.put("strColumn7", request.getParameter("strColumn7"));
			parametros.put("strColumn8", request.getParameter("strColumn8"));
			parametros.put("strColumn9", request.getParameter("strColumn9"));
			parametros.put("strColumn10", request.getParameter("strColumn10"));
			JSONArray jsonArray = new JSONArray(request.getParameter("listRegistros"));

			for (int i = 0; i < jsonArray.length(); i++) {

				ReporteGeneralDatosFormGeneric form = new ReporteGeneralDatosFormGeneric();
				JSONObject obj = (JSONObject) jsonArray.get(i);
				form.setStrColumn1(obj.get("strColumn1").toString());
				form.setStrColumn2(obj.get("strColumn2").toString());
				form.setStrColumn3(obj.get("strColumn3").toString());
				form.setStrColumn4(obj.get("strColumn4").toString());
				form.setStrColumn5(obj.get("strColumn5").toString());
				form.setStrColumn6(obj.get("strColumn6").toString());
				form.setStrColumn7(obj.get("strColumn7").toString());
				form.setStrColumn8(obj.get("strColumn8").toString());
				form.setStrColumn9(obj.get("strColumn9").toString());
				form.setStrColumn10(obj.get("strColumn10").toString());
				reportListFinal.add(i, form);
			}

			JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(reportListFinal);
			strNombreArchivo = request.getParameter("strNombreArchivo");

			if (!strNombreArchivo.contains(".xls")) {
				strNombreArchivo += ".xls";
			}

			path = getServletContext().getRealPath(VariablesGlobales.getReporteDetalleConstanciaFoliosREPUVE());

			File reportFile = new File(path);
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parametros, data);

			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment; filename=" + strNombreArchivo);
			ServletOutputStream out = response.getOutputStream();

			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.exportReport();
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String buildQuery(String strOption1, String strOption2, String strOption3, String strOption4,
			String strOption5, String strOption6, String fechaInicial, String fechaFinal, String folioInicial,
			String folioFinal, String strResponsable, String strEstado, String strEstadoImportados, String strEntidad,
			Usuario usuario, String strColumnFolio, String strColumnFecha, String strColumnTID,
			String strColumnObservaciones, String strColumnEstadoActual, String strColumnTipo,
			String strColumnResponsable, String strColumnChasis, String strColumnEntidad, String strColumnEvento,
			Logger loggerInfo, Logger loggerError, String consultaBD, String ultimoEvento) throws Exception {

		loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteArchivo buildQuery Inicio Construir Consulta");
		String strQuery = "";

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

			strNombreArchivo = "reporteCI_";
			strNombreArchivo += new SimpleDateFormat("ddMMyy").format(new Date())
					+ new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());

			if (strOption5.equals("1") && ultimoEvento.equals("false")) {

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

			String strSelectUsuario = "";

			if (strOption5.equals("1")) {

				// FILTRO POR RESPONSABLES
				if (!strResponsable.equals("-1")) {

					strSelectUsuario = "";

					if (ultimoEvento.equals("false")) {

						strSelectUsuario = "hist.ID_USUARIO_ACTUAL";

					} else {

						strSelectUsuario = "formato.ID_USUARIO";

					}

					strQuery = strQuery + "AND " + strSelectUsuario + "=" + strResponsable + " ";
					strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.responsable");
					reporteAgregaResponsable = true;
					reporteAgregaEvento = true;
					strNombreArchivo += "_re" + strResponsable;
					UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
					Usuario usu = usuarioDAO.buscarPorId(Long.valueOf(strResponsable));
					strTituloFechas += prop.strMessage("etiqueta.constancias.reporte.responsableheader")
							+ usu.getNombre() + " " + usu.getApellidoPaterno() + " " + usu.getApellidoMaterno() + " - ";

				} else {

					if (ultimoEvento.equals("false")) {

						strSelectUsuario = "hist.ID_USUARIO_ACTUAL";

					} else {

						strSelectUsuario = "formato.ID_USUARIO";

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

						strQueryEntidades = "AND " + strSelectUsuario + " in (" + intResponsables + ")";

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

			loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteArchivo buildQuery - FIN***");
			return strQuery;
		} catch (Exception e) {
			loggerError.error(FechaUtil.getHoraActual() + "_ReporteArchivo fallo buildQuery  " + e);
			loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteArchivo buildQuery - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
		}
		loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteArchivo buildQuery - FIN***");
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return strQuery;
	}

	public String buildQueryRespaldo(String strOption1, String strOption2, String strOption3, String strOption4,
			String strOption5, String strOption6, String fechaInicial, String fechaFinal, String folioInicial,
			String folioFinal, String strResponsable, String strEstado, String strEstadoImportados, String strEntidad,
			Usuario usuario, String strColumnFolio, String strColumnFecha, String strColumnTID,
			String strColumnObservaciones, String strColumnEstadoActual, String strColumnTipo,
			String strColumnResponsable, String strColumnChasis, String strColumnEntidad, String strColumnEvento,
			Logger loggerInfo, Logger loggerError, String consultaBD, String ultimoEvento) throws Exception {

		loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteArchivo buildQueryRespaldo Inicio Construir Consulta");
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

			if (strOption5.equals("1") && ultimoEvento.equals("false")) {

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
						+ esquemaProd + ".USUARIOS us WHERE us.ID_USUARIO=formato.ID_USUARIO) as responsable,"
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
				loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteArchivo buildQueryRespaldo - FIN***");
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

			String strSelectUsuario = "";

			if (strOption5.equals("1")) {

				// FILTRO POR RESPONSABLES
				if (!strResponsable.equals("-1")) {

					strSelectUsuario = "";

					if (ultimoEvento.equals("false")) {

						strSelectUsuario = "hist.ID_USUARIO_ACTUAL";

					} else {

						strSelectUsuario = "formato.ID_USUARIO";

					}

					strQuery = strQuery + "AND " + strSelectUsuario + "=" + strResponsable + " ";
					strTituloFiltros += prop.strMessage("etiqueta.constancias.reporte.responsable");
					reporteAgregaResponsable = true;
					reporteAgregaEvento = true;
					strNombreArchivo += "_re" + strResponsable;
					UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
					Usuario usu = usuarioDAO.buscarPorId(Long.valueOf(strResponsable));
					strTituloFechas += prop.strMessage("etiqueta.constancias.reporte.responsableheader")
							+ usu.getNombre() + " " + usu.getApellidoPaterno() + " " + usu.getApellidoMaterno() + " - ";

				} else {

					if (ultimoEvento.equals("false")) {

						strSelectUsuario = "hist.ID_USUARIO_ACTUAL";

					} else {

						strSelectUsuario = "formato.ID_USUARIO";

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

						strQueryEntidades = "AND " + strSelectUsuario + " in (" + intResponsables + ")";

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

			loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteArchivo buildQueryRespaldo - FIN***");
			return strQuery;
		} catch (Exception e) {
			loggerError
					.error(FechaUtil.getHoraActual() + "_ReporteArchivo Fallo buildQueryRespaldo  " + e);
			loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteArchivo buildQueryRespaldo - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
		}
		loggerInfo.info(FechaUtil.getHoraActual() + "_ReporteArchivo buildQueryRespaldo - FIN***");
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return strQuery;
	}

}
