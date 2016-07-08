package neology.vc.actions.cambiarEstado;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neology.modelo.dto.CambioEstado;
import neology.modelo.dto.Entidad;
import neology.modelo.dto.Estado;
import neology.modelo.dto.Formato;
import neology.modelo.dto.FormatoHistorico;
import neology.modelo.dto.Parametros;
import neology.modelo.dto.TipoFormato;
import neology.modelo.dto.Usuario;
import neology.modelo.dto.id.FormatoId;
import neology.modelo.negocio.daos.EntidadDAO;
import neology.modelo.negocio.daos.EstadoDAO;
import neology.modelo.negocio.daos.FormatoDAO;
import neology.modelo.negocio.daos.FormatoHistoricoDAO;
import neology.modelo.negocio.daos.CambioEstadoDAO;
import neology.modelo.negocio.daos.ParametrosDAO;
import neology.modelo.negocio.daos.TipoFormatoDAO;
import neology.modelo.negocio.daos.UsuarioDAO;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.util.formato.ConvertidorFolio;
import neology.vc.actions.busqueda.BusquedaFormatoAction;
import neology.vc.forms.cambiarEstado.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

public class CambiarEstadoAction extends DispatchAction {
	/**
	 * Carga las entidades, tipos de formatos para realizar el cambio de
	 * estadode los formatos
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward cargar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		ActionMessages errores = new ActionMessages();
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_CambiarEstadoAction cargar - INICIO***");
		
		try {
	
			
			CambiarEstadoForm formulario = (CambiarEstadoForm) form;
			UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");
			EntidadDAO entidadDAO = DAOFactory.crearEntidadDAO();
			EstadoDAO estadoDAO = DAOFactory.crearEstadoDAO();
			CambioEstadoDAO cambioEstadoDAO = DAOFactory.crearCambioEstadoDAO();
			TipoFormatoDAO tipoDAO = DAOFactory.crearTipoFormatoDAO();
			List<Estado> estadoNuevo = null;
			List<Estado> estadosPermitidos = new ArrayList<Estado>();
			estadoNuevo = new ArrayList<Estado>();
			ArrayList<String> strCombinaciones = new ArrayList<String>();
			formulario.setTipoFormatos(tipoDAO.buscarTipoFormatos());
			formulario.setEntidades(entidadDAO.buscarEntidades());
			formulario.setEstados2(estadoDAO.obtenerEstados("5"));
			formulario.setEstados(estadoNuevo);
			// formulario.setEstados(cambioEstadoDAO.BuscarEstados("0"));
			// Solo agregar estados que se encuentren permitidos en la tabla
			// cambio de estados
			List<CambioEstado> cambioEstados = cambioEstadoDAO.BuscarCambioEstadosTodos();
			ArrayList<String> strEstadosDe = new ArrayList<String>();
			ArrayList<String> strEstadosA = new ArrayList<String>();
			List<Estado> estadoHidden = new ArrayList<Estado>();
			for (int i = 0; i < cambioEstados.size(); i++) {

				strEstadosDe.add(cambioEstados.get(i).getDe());
				strEstadosA.add(cambioEstados.get(i).getA());
				Estado est = new Estado();
				est.setDescripcion(cambioEstados.get(i).getDe() + "-" + cambioEstados.get(i).getA());
				est.setEstado(cambioEstados.get(i).getDe() + "-" + cambioEstados.get(i).getA());
				est.setFormatos(null);
				estadoHidden.add(est);
			}

			Set<String> listFinalRegsDe = new HashSet<String>(strEstadosDe);
			Set<String> listFinalRegsA = new HashSet<String>(strEstadosA);

			if (!listFinalRegsDe.isEmpty() && !listFinalRegsA.isEmpty()) {

				Iterator itDe = listFinalRegsDe.iterator();
				Iterator itA = listFinalRegsA.iterator();
				Integer intCount = 0;
				strEstadosDe = new ArrayList<String>();
				strEstadosA = new ArrayList<String>();

				while (itDe.hasNext()) {
					String vehDup = (String) itDe.next();
					strEstadosDe.add(vehDup);
				}

				intCount = 0;
				while (itA.hasNext()) {
					String vehDup = (String) itA.next();
					strEstadosA.add(vehDup);
				}

			}
			for (int i = 0; i < strEstadosDe.size(); i++) {
				estadosPermitidos.add(estadoDAO.obtenerEstadosPorEstado(strEstadosDe.get(i)));
			}
			formulario.setEstados2(estadosPermitidos);
			estadosPermitidos = new ArrayList<Estado>();

			for (int i = 0; i < strEstadosA.size(); i++) {
				estadosPermitidos.add(estadoDAO.obtenerEstadosPorEstado(strEstadosA.get(i)));
			}
			
			formulario.setEstados(estadosPermitidos);
			formulario.setEstados3(estadoHidden);
			loggerInfo.info(FechaUtil.getHoraActual()+"_CambiarEstadoAction finalizo cargar - FIN***");
			
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("cargar");

		} catch (Exception e) {

			loggerError.error(FechaUtil.getHoraActual()+"_Fallo CambiarEstadoAction cargar", e);
			loggerError.error(FechaUtil.getHoraActual()+"_CambiarEstadoAction cargar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("texto.etiqueta.error.log"));
			saveErrors(request, errores);
			return mapping.findForward("cargar");
			
		}

	}

	public ActionForward cambiar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		ActionMessages errores = new ActionMessages();
		loggerInfo.info(FechaUtil.getHoraActual()+"_CambiarEstadoAction cambiar - INICIO***");
		
		try{
		// Datos iniciales
		CambiarEstadoForm formulario = (CambiarEstadoForm) form;
		String folioInicial = formulario.getFolioInicial();
		String folioInicialr = folioInicial;
		String folioFinal = formulario.getFolioFinal();
		// Se obtiene el usuario de sesion
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");
		long idTipoFormato = formulario.getIdTipoFormato();
		long idEntidad = usuario.getEntidad().getIdEntidad();
		String estado = formulario.getEstado();
		String estadoNuevo = formulario.getEstadoNuevo();
		List noProcedeActualizacion = new ArrayList();
		List erroneos = new ArrayList();
		int numeroFolios = 0;
		int folioI = 0;
		int folioF = 0;
		long numeroFormatos = 0;
		long cuentaFormatos = 0;
		Boolean valHor = false;
		Boolean exito = false;
		int totalFormatos = 0;
		try {
			// Se obtienen los Daos correspondientes
			EntidadDAO entidadDAO = DAOFactory.crearEntidadDAO();
			TipoFormatoDAO tipoDAO = DAOFactory.crearTipoFormatoDAO();
			FormatoDAO formatoDAO = DAOFactory.crearFormatoDAO();
			EstadoDAO estadoDAO = DAOFactory.crearEstadoDAO();
			ParametrosDAO parametrosDAO = DAOFactory.crearParametrosDAO();
			FormatoHistoricoDAO formatoHistoricoDAO = DAOFactory.crearFormatoHistoricoDAO();

			TipoFormato tipoFormato = tipoDAO.buscarPorId(idTipoFormato);
			Entidad entidad = entidadDAO.buscarPorId(idEntidad);
			Estado estados = estadoDAO.buscarPorId(estado);

			// Se valida el formato de los folios
			if (!Utilidades.isFolioValido(tipoFormato.getFormatoFolio(), folioInicial)) {
				errores.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage("errors.formato.formatoFolio.invalido", "Folio Inicial"));
			}
			if (folioFinal != null && folioFinal.trim().length() >= 1) {
				if (!Utilidades.isFolioValido(tipoFormato.getFormatoFolio(), folioFinal)) {
					errores.add(ActionMessages.GLOBAL_MESSAGE,
							new ActionMessage("errors.formato.formatoFolio.invalido", "Folio Final"));
				}
			} else
				folioFinal = folioInicial;

			/*
			 * if (errores.size() > 0) { saveErrors(request, errores); return
			 * mapping.getInputForward(); }
			 */
			// Se obtienen los rangos para los folios de los tsP
			ConvertidorFolio convertidor = new ConvertidorFolio(tipoFormato.getFormatoFolio());

			if (errores.size() == 0) {
				folioI = convertidor.getRango(folioInicial);
				folioF = convertidor.getRango(folioFinal);
				if (folioF == 0)
					folioF = folioI;
				totalFormatos = ((folioF + 1) - folioI);

				// validamos contra parametros del sistema
				// numeroFormatos =
				// parametrosDAO.buscarPorModulo("CAMBIAR_ESTADO");
				// if (numeroFormatos >= ((folioF+1)-folioI)){
				valHor = true;

				// }
				// else
				// errores.add(ActionMessages.GLOBAL_MESSAGE,
				// new ActionMessage("errors.formato.excede.numFormatosMax",
				// "Cambiar de Estado",numeroFormatos,totalFormatos));
				if (folioI > folioF)
					errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.formato.folio.inicial.mayor"));
			}
			if (errores.size() > 0) {
				
				loggerError.error(FechaUtil.getHoraActual()+"_CambiarEstadoAction cambiar - FIN***");
				errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("texto.etiqueta.error.log"));
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				saveErrors(request, errores);	
				return mapping.getInputForward();
			}

			// Buscar que todos los folios sean de la misma oficina, que tengan
			// el mismo estado y que sean del mismo tipoFormato
			loggerInfo.info(FechaUtil.getHoraActual()+"_CambiarEstadoAction validando rango: " + folioI +" - "+folioF);
			noProcedeActualizacion = formatoDAO.validaPorRangoFolios(convertidor.getFolio(folioI),
					convertidor.getFolio(folioF), idTipoFormato, usuario.getEntidad().getIdEntidad(), estado,
					totalFormatos);
			loggerInfo.info(FechaUtil.getHoraActual()+"_CambiarEstadoAction validando rango fin: " + folioI +" - "+folioF);
			// Se actualizan los tsP
			if (noProcedeActualizacion == null && valHor) {

				loggerInfo.info(FechaUtil.getHoraActual()+" CambiarEstadoAction actualizando rango: " + folioI +" - "+folioF);
				exito = formatoDAO.cambiarEstadoRango(idTipoFormato, convertidor.getFolio(folioI),
						convertidor.getFolio(folioF), estadoNuevo, new Long(usuario.getIdUsuario()));
				loggerInfo.info(FechaUtil.getHoraActual()+"_CambiarEstadoAction fin actualizando rango: " + folioI +" - "+folioF);
				// Agregar Historico, por cada folio cambiado
				int i = Integer.valueOf(folioFinal);

				do {
					loggerInfo.info(FechaUtil.getHoraActual()+"_CambiarEstadoAction actualizando historico rango: " + folioI +" - "+folioF);
					FormatoHistorico formatoHistorico = new FormatoHistorico();
					FormatoHistorico formatoHistoricoLast = new FormatoHistorico();
					formatoHistorico.setIdUsuarioModifico(usuario.getIdUsuario());
					formatoHistorico.setFechaHistorico(FechaUtil.getFechaYHoraActual());
					formatoHistorico.setFolio(convertidor.getFolio(i));
					formatoHistorico.setIdTipoFormato(1);
					formatoHistorico.setIdEntidad(usuario.getEntidad().getIdEntidad());
					formatoHistorico.setIdUsuarioActual(usuario.getIdUsuario());
					formatoHistorico.setEstado(estadoNuevo);
					formatoHistorico.setFechaRegistro(FechaUtil.getFechaYHoraActual());

					// Agrega orden de impresion si se encuentra
					List lastEvent = formatoHistoricoDAO.buscarUltimoEvento(String.valueOf(i));

					if (lastEvent.size() > 0) {

						formatoHistoricoLast = (FormatoHistorico) lastEvent.get(0);

					}

					if (formatoHistoricoLast.getIdOrdenImpresion() != null) {

						formatoHistorico.setIdOrdenImpresion(formatoHistoricoLast.getIdOrdenImpresion());

					} else {

						formatoHistorico.setIdOrdenImpresion(null);

					}

					formatoHistoricoDAO.guardar(formatoHistorico);
					i--;

				loggerInfo.info(FechaUtil.getHoraActual()+"_CambiarEstadoAction folio guardado: "+convertidor.getFolio(i));
				} while (i >= Integer.valueOf(folioInicial));
				loggerInfo.info(FechaUtil.getHoraActual()+"_CambiarEstadoAction fin actualizando historico rango: " + folioI +" - "+folioF);

			} else {
				if (noProcedeActualizacion.size() > 0) {
					ActionMessages errores2 = new ActionMessages();
					String sinFolios = null;
					if (noProcedeActualizacion.size() == 1) {
						Iterator iter = noProcedeActualizacion.iterator();
						while (iter.hasNext()) {
							sinFolios = (String) iter.next();
							break;
						}
					}
					if (sinFolios == "error") {
						errores2.add("noProcedeActualizacion", new ActionMessage(
								"errors.formato.cambiarEstado.noExistenFormatos", "Cambiar de Estado"));
						noProcedeActualizacion = null;
						loggerInfo.removeAllAppenders();
						loggerError.removeAllAppenders();
						saveErrors(request, errores2);
						return mapping.findForward("exito");
					} else{
						errores2.add("noProcedeActualizacion", new ActionMessage(
								"errors.formato.cambiarEstado.noCambiado", noProcedeActualizacion.size()));
					saveErrors(request, errores2);
					loggerInfo.removeAllAppenders();
					loggerError.removeAllAppenders();
					saveErrors(request, errores2);
					return mapping.findForward("exito");
					}
				}
			}
		} catch (Exception e) {
			numeroFolios = 0;
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo CambiarEstadoAction cambiar", e);
			loggerError.error(FechaUtil.getHoraActual()+"_CambiarEstadoAction cambiar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("texto.etiqueta.error.log"));
			saveErrors(request, errores);
			return mapping.findForward("exito");
		}
		// Se generan los mensajes correspondientes
		ActionMessages mensaje = new ActionMessages();
		ActionMessages erroneo = new ActionMessages();
		if (exito)
			mensaje.add("mensaje", new ActionMessage("mensaje.formato.cambiarEstado.exitoso", totalFormatos,
					folioInicialr, folioFinal));
		else
			mensaje.add("mensaje", new ActionMessage("errors.formato.dotacion.guardar.general"));
		if (erroneos.size() > 0) {
			erroneo.add("erroneo", new ActionMessage("errors.formato.dotacion.guardar"));
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			saveErrors(request, erroneo);
			return mapping.findForward("exito");
		}
		if (!valHor) {
			mensaje.add("mensaje", new ActionMessage("errors.formato.excede.numFormatosMax", "Cambiar de Estado",
					numeroFormatos, totalFormatos));
		}

		request.setAttribute("erroneos", erroneos);
		request.setAttribute("noProcedeActualizacion", noProcedeActualizacion);
		request.setAttribute("mensaje", mensaje);
		loggerInfo.info(FechaUtil.getHoraActual()+"_CambiarEstadoAction cambiar - FIN***");
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		saveErrors(request, erroneo);
		return mapping.findForward("exito");
		
		} catch (Exception e) {

			loggerError.error(FechaUtil.getHoraActual()+"_Fallo CambiarEstadoAction cambiar", e);
			loggerError.error(FechaUtil.getHoraActual()+"_CambiarEstadoAction cambiar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("texto.etiqueta.error.log"));
			saveErrors(request, errores);
			return mapping.findForward("exito");
		}
	}

	public ActionForward mostrarCambiosPermitidos(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		ActionMessages errores = new ActionMessages();
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_CambiarEstadoAction mostrarCambiosPermitidos - INICIO***");
		
		try{
		CambioEstadoDAO cambioEstadoDAO = DAOFactory.crearCambioEstadoDAO();

		String estado = (String) request.getParameter("estado");

		String nuevoEstado = "-1|Seleccione un nuevo Estado;";
		if (estado != "-1") {
			List<Estado> pivote = cambioEstadoDAO.BuscarEstados(estado);
			if (pivote != null && pivote.size() != 0) {
				for (Estado est : pivote) {
					nuevoEstado = nuevoEstado + (String) est.getEstado() + "|" + est.getDescripcion() + ";";
				}
			}
		}
		if (nuevoEstado.endsWith(";")) {
			nuevoEstado = nuevoEstado.substring(0, nuevoEstado.lastIndexOf(";"));
		}
		Utilidades.escribirXML(response, nuevoEstado);
		loggerInfo.info(FechaUtil.getHoraActual()+"_CambiarEstadoAction mostrarCambiosPermitidos - FIN***");
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return null;
		} catch (Exception e) {

			loggerError.error(FechaUtil.getHoraActual()+"_Fallo CambiarEstadoAction mostrarCambiosPermitidos", e);
			loggerError.error(FechaUtil.getHoraActual()+"_CambiarEstadoAction mostrarCambiosPermitidos - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("texto.etiqueta.error.log"));
			saveErrors(request, errores);
			return mapping.findForward("exito");
		}
	}

}
