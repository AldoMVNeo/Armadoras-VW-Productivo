package neology.vc.actions.sistema;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.joda.time.DateTime;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import neology.modelo.dto.ConfigJob;
import neology.modelo.dto.Perfil;
import neology.modelo.dto.Usuario;
import neology.modelo.negocio.daos.ConfiguracionHistoricoDAO;
import neology.modelo.negocio.daos.UsuarioDAO;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.recursos.GetProperties;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.util.VariablesGlobales;
import neology.vc.actions.reportes.GeneradorPDFAction;
import neology.vc.forms.sistema.ConfiguracionHistoricoForm;

public class ConfiguracionHistoricoAction extends DispatchAction {

	GetProperties prop;

	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		prop = new GetProperties();
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		ActionMessages mensaje = new ActionMessages();
		ConfiguracionHistoricoForm formulario = (ConfiguracionHistoricoForm) form;
		loggerInfo.info(FechaUtil.getHoraActual() + "_ConfiguracionHistoricoAction inicio - INICIO***");
		Integer noDias = Integer.valueOf(VariablesGlobales.getDiasRespaldar());

		try {

			UsuarioDAO usuariosDAO = DAOFactory.crearUsuarioDAO();
			ConfiguracionHistoricoDAO configuracionHistoricoDAO = DAOFactory.crearConfiguracionHistoricoDAO();
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");
			List configs = configuracionHistoricoDAO.listConfig();

			Date date = new Date();
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(date);

			if (configs != null && configs.size()>0) {
				formulario.setListaConfigs(configs);
			} else {
				formulario.setListaConfigs(null);
			}
			
			
			formulario.setStrFechaConfig(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			formulario.setStrFechaCalculo(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			formulario.setHoraConfig(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + " : "
					+ String.valueOf(calendar.get(Calendar.MINUTE)));
			formulario.setNoDias((long) noDias);
			formulario.setEjecutaJob("off");
			request.setAttribute("listaConfigs", configs);

			loggerInfo.info(FechaUtil.getHoraActual() + "_ConfiguracionHistoricoAction inicio - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("inicio");
		} catch (Exception e) {
			loggerError.error(FechaUtil.getHoraActual() + "_Fallo ConfiguracionHistoricoAction inicio ", e);
			loggerError.error(FechaUtil.getHoraActual() + "_ConfiguracionHistoricoAction inicio - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			mensaje.add("mensaje", new ActionMessage("texto.etiqueta.configuracion.error"));
			request.setAttribute("mensaje", mensaje);
			return mapping.findForward("inicio");
		}

	}

	public ActionForward configuracionHistoricoGuardar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();

		ActionMessages mensaje = new ActionMessages();

		loggerInfo.info(
				FechaUtil.getHoraActual() + "_ConfiguracionHistoricoAction configuracionHistoricoGuardar - INICIO***");
		try {
			ConfiguracionHistoricoForm formulario = (ConfiguracionHistoricoForm) form;
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");
			String noDias = request.getParameter("noDias");
			String fullHoraConfig = request.getParameter("horaConfig").trim();
			String horaConfigArr[] = fullHoraConfig.split(":");
			String horaConfig = horaConfigArr[0].trim();
			String minutosConfig = horaConfigArr[1].trim();
			String strFechaConfig = request.getParameter("strFechaConfig");
			String ejecutaJob = request.getParameter("ejecutaJob");
			Boolean horaFallo = false;

			// Validar días para respaldar

			if (Integer.valueOf(noDias) < Integer.valueOf(VariablesGlobales.getDiasRespaldar())) {

				mensaje.add("mensaje", new ActionMessage("texto.etiqueta.configuracion.error.dias"));
				request.setAttribute("mensaje", mensaje);
				loggerError.error(FechaUtil.getHoraActual()
						+ "_ConfiguracionHistoricoAction configuracionHistoricoGuardar - FIN*** "
						+ prop.strMessage("texto.etiqueta.configuracion.error.dias"));
				return mapping.findForward("mensaje");

			}

			// Validar fecha anterior al día de hoy
			String strFechaConfigSplit[] = strFechaConfig.split("/");
			String cDate = strFechaConfigSplit[2] + "/" + strFechaConfigSplit[1] + "/" + strFechaConfigSplit[0];
			Date dtConfig = new Date(cDate);

			int intCompare = dtConfig.compareTo(new Date());
			long diff = dtConfig.getTime() - new Date().getTime();

			if ((TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) == 0)) {
				intCompare = 1;
			}

			// No guardar configuraciones antes de la hora actual, solo
			// configuracion programada

			if (intCompare < 0) {

				mensaje.add("mensaje", new ActionMessage("texto.etiqueta.configJob.errorFecha"));
				request.setAttribute("mensaje", mensaje);
				loggerError.error(FechaUtil.getHoraActual()
						+ "_ConfiguracionHistoricoAction configuracionHistoricoGuardar - FIN*** "
						+ prop.strMessage("texto.etiqueta.configuracion.error.dias"));
				return mapping.findForward("mensaje");

			}

			ConfiguracionHistoricoDAO configuracionHistoricoDAO = DAOFactory.crearConfiguracionHistoricoDAO();

			ConfigJob configJob = new ConfigJob();

			configJob.setNoDias(Long.valueOf(noDias));
			configJob.setUsuario(usuario);

			if (ejecutaJob != null) {

				// Ejecutar inmediatamente, hora y fechas actuales
				Date dt = new Date();
				String strDate = new SimpleDateFormat("dd/MM/yyyy").format(dt);
				String fechaSplit[] = strDate.split("/");
				String finalDate = fechaSplit[1] + "/" + fechaSplit[0] + "/" + fechaSplit[2];
				configJob.setFechaConfig(new Date(finalDate));
				configJob.setEstatus((long) 4); // INMEDIATO

				Calendar calendar = GregorianCalendar.getInstance();
				calendar.setTime(dt);

				Integer hora = calendar.get(Calendar.HOUR_OF_DAY);
				Integer minutos = calendar.get(Calendar.MINUTE);
				Boolean ready = false;

				if (minutos == 57) {
					minutos = 00;
					hora++;
					ready = true;
				}

				if (!ready) {

					if (minutos == 58) {
						minutos = 02;
						hora++;
						ready = true;
					}
				}

				if (!ready) {

					if (minutos == 59) {
						minutos = 03;
						hora++;
						ready = true;
					}
				}

				if (!ready) {
					minutos = minutos + 3;
				}

				configJob.setHoraConfig(hora.toString());
				configJob.setMinutosConfig(minutos.toString());

			} else {

				// No guardar configuraciones antes de la hora actual, solo
				// configuracion programada
				int horaActual = new Date().getHours();
				int minutosActual = new Date().getMinutes();
				// Comparacion de horas
				if ((Integer.valueOf(horaConfig) < horaActual)) {

					mensaje.add("mensaje", new ActionMessage("texto.etiqueta.configJob.errorFecha"));
					request.setAttribute("mensaje", mensaje);
					loggerError.error(FechaUtil.getHoraActual()
							+ "_ConfiguracionHistoricoAction configuracionHistoricoGuardar - FIN*** "
							+ prop.strMessage("texto.etiqueta.configuracion.error.dias"));
					return mapping.findForward("mensaje");

				}

				// Comparacion de minutos cuando las horas son iguales
				if ((Integer.valueOf(horaConfig) == horaActual)) {

					if ((Integer.valueOf(minutosConfig) < minutosActual)) {
						mensaje.add("mensaje", new ActionMessage("texto.etiqueta.configJob.errorFecha"));
						request.setAttribute("mensaje", mensaje);
						loggerError.error(FechaUtil.getHoraActual()
								+ "_ConfiguracionHistoricoAction configuracionHistoricoGuardar - FIN*** "
								+ prop.strMessage("texto.etiqueta.configuracion.error.dias"));
						return mapping.findForward("mensaje");
					}

				}

				configJob.setHoraConfig(horaConfig);
				configJob.setMinutosConfig(minutosConfig);
				String fechaSplit[] = strFechaConfig.split("/");
				String finalDate = fechaSplit[1] + "/" + fechaSplit[0] + "/" + fechaSplit[2];
				configJob.setFechaConfig(new Date(finalDate));
				configJob.setEstatus((long) 1);
			}

			loggerInfo.info(FechaUtil.getHoraActual()
					+ "_ConfiguracionHistoricoAction inicio configuracionHistoricoGuardar guardar");
			if (configuracionHistoricoDAO.guardar(configJob)) {

				if (configuracionHistoricoDAO.ejecutaJob()) {
					loggerInfo.info(FechaUtil.getHoraActual()
							+ "_ConfiguracionHistoricoAction fin configuracionHistoricoGuardar ejecutaJob");
				} else {
					loggerError.error(FechaUtil.getHoraActual()
							+ "_ConfiguracionHistoricoAction configuracionHistoricoGuardar - FIN*** "
							+ prop.strMessage("texto.etiqueta.configuracion.error"));
				}

				mensaje.add("mensaje", new ActionMessage("texto.etiqueta.configuracion.ok"));
				request.setAttribute("mensaje", mensaje);
				loggerInfo.info(FechaUtil.getHoraActual()
						+ "_ConfiguracionHistoricoAction fin configuracionHistoricoGuardar guardar");
				loggerInfo.info(FechaUtil.getHoraActual()
						+ "_ConfiguracionHistoricoAction configuracionHistoricoGuardar - FIN***");
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				request.setAttribute("mensaje", mensaje);
				return mapping.findForward("mensaje");

			} else {

				mensaje.add("mensaje", new ActionMessage("texto.etiqueta.configuracion.error"));
				request.setAttribute("mensaje", mensaje);
				loggerError.error(FechaUtil.getHoraActual()
						+ "_ConfiguracionHistoricoAction configuracionHistoricoGuardar - FIN*** "
						+ prop.strMessage("texto.etiqueta.configuracion.error"));
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				mensaje.add("mensaje", new ActionMessage("texto.etiqueta.configuracion.error"));
				request.setAttribute("mensaje", mensaje);
				return mapping.findForward("mensaje");

			}

		} catch (Exception e) {
			e.printStackTrace();
			loggerError.error(
					FechaUtil.getHoraActual() + "_Fallo ConfiguracionHistoricoAction configuracionHistoricoGuardar ",
					e);
			loggerError.error(
					FechaUtil.getHoraActual() + "_ConfiguracionHistoricoAction configuracionHistoricoGuardar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			mensaje.add("mensaje", new ActionMessage("texto.etiqueta.configuracion.error"));
			request.setAttribute("mensaje", mensaje);
			return mapping.findForward("mensaje");

		}

	}

	public ActionForward configuracionHistoricoEliminar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();

		ActionMessages mensaje = new ActionMessages();

		loggerInfo.info(
				FechaUtil.getHoraActual() + "_ConfiguracionHistoricoAction configuracionHistoricoEliminar - INICIO***");
		try {
			ConfiguracionHistoricoForm formulario = (ConfiguracionHistoricoForm) form;
			String idParametro = request.getParameter("idParametro");

			ConfiguracionHistoricoDAO configuracionHistoricoDAO = DAOFactory.crearConfiguracionHistoricoDAO();

			List configJoblist = configuracionHistoricoDAO.buscarPorParametro(idParametro);
			ConfigJob configJob = (ConfigJob) configJoblist.get(0);

			if (configJob.getEstatus() != 1) {

				mensaje.add("mensaje", new ActionMessage("texto.etiqueta.configuracion.error.eliminar"));
				request.setAttribute("mensaje", mensaje);
				loggerInfo.info(FechaUtil.getHoraActual()
						+ "  ConfiguracionHistoricoAction fin configuracionHistoricoEliminar guardar");
				loggerInfo.info(FechaUtil.getHoraActual()
						+ "_ConfiguracionHistoricoAction configuracionHistoricoEliminar - FIN***");
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				request.setAttribute("mensaje", mensaje);
				return mapping.findForward("mensaje");
			}

			loggerInfo.info(FechaUtil.getHoraActual()
					+ "_ConfiguracionHistoricoAction inicio configuracionHistoricoEliminar guardar");
			if (configuracionHistoricoDAO.eliminar(configJob)) {

				if (configuracionHistoricoDAO.ejecutaJob()) {
					loggerInfo.info(FechaUtil.getHoraActual()
							+ "_ConfiguracionHistoricoAction fin configuracionHistoricoEliminar ejecutaJob");
				} else {
					loggerError.error(FechaUtil.getHoraActual()
							+ "_ConfiguracionHistoricoAction configuracionHistoricoEliminar - FIN*** "
							+ prop.strMessage("texto.etiqueta.configuracion.error"));
				}

				mensaje.add("mensaje", new ActionMessage("texto.etiqueta.configuracion.ok"));
				request.setAttribute("mensaje", mensaje);
				loggerInfo.info(FechaUtil.getHoraActual()
						+ "  ConfiguracionHistoricoAction fin configuracionHistoricoEliminar guardar");
				loggerInfo.info(FechaUtil.getHoraActual()
						+ "_ConfiguracionHistoricoAction configuracionHistoricoEliminar - FIN***");
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				request.setAttribute("mensaje", mensaje);
				return mapping.findForward("mensaje");

			} else {

				mensaje.add("mensaje", new ActionMessage("texto.etiqueta.configuracion.error"));
				request.setAttribute("mensaje", mensaje);
				loggerError.error(FechaUtil.getHoraActual()
						+ "_ConfiguracionHistoricoAction configuracionHistoricoModificar - FIN*** "
						+ prop.strMessage("texto.etiqueta.configuracion.error"));
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				mensaje.add("mensaje", new ActionMessage("texto.etiqueta.configuracion.error"));
				request.setAttribute("mensaje", mensaje);
				return mapping.findForward("mensaje");

			}

		} catch (Exception e) {
			e.printStackTrace();
			loggerError.error(
					FechaUtil.getHoraActual() + "_Fallo ConfiguracionHistoricoAction configuracionHistoricoModificar ",
					e);
			loggerError.error(FechaUtil.getHoraActual()
					+ "_ConfiguracionHistoricoAction configuracionHistoricoModificar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			mensaje.add("mensaje", new ActionMessage("texto.etiqueta.configuracion.error"));
			request.setAttribute("mensaje", mensaje);
			return mapping.findForward("mensaje");

		}

	}

	public String strEstatus(String strEstatus) {

		if (strEstatus.equals("1")) {
			strEstatus = "PENDIENTE";
		}

		if (strEstatus.equals("2")) {
			strEstatus = "EJECUTADO";
		}

		if (strEstatus.equals("3")) {
			strEstatus = "EN EJECUCION";
		}

		if (strEstatus.equals("4")) {
			strEstatus = "INMEDIATO";
		}

		return strEstatus;

	}
	
	public String strNombreUsuario(String idUsuario) {
		
		Usuario usuario = new Usuario();
		UsuarioDAO usuarioDAO= new UsuarioDAO();
		usuario=usuarioDAO.buscarPorId(Long.parseLong(idUsuario));

		return usuario.getNombre()+" "+usuario.getApellidoPaterno()+" "+usuario.getApellidoMaterno();

	}

	public String strCalculoFecha(String strCalculoFecha, String noDias) {

		Date date = new Date(strCalculoFecha);
		Date daysAgo = new DateTime(date).minusDays(Integer.valueOf(noDias)).toDate();

		return new SimpleDateFormat("dd/MM/yyyy").format(daysAgo);

	}

	public String strEliminarVisible(String strEstatus, String activo) {

		if (strEstatus.equals("2") || activo.equals("2")) {
			strEstatus = "visible";
		} else {
			strEstatus = "hidden";
		}

		return strEstatus;

	}

}
