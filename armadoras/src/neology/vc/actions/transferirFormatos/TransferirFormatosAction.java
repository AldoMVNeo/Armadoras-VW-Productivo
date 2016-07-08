package neology.vc.actions.transferirFormatos;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neology.modelo.dto.Entidad;
import neology.modelo.dto.Estado;
import neology.modelo.dto.Formato;
import neology.modelo.dto.TipoFormato;
import neology.modelo.dto.Usuario;
import neology.modelo.dto.id.FormatoId;
import neology.modelo.negocio.daos.EntidadDAO;
import neology.modelo.negocio.daos.EstadoDAO;
import neology.modelo.negocio.daos.FormatoDAO;
import neology.modelo.negocio.daos.ParametrosDAO;
import neology.modelo.negocio.daos.TipoFormatoDAO;
import neology.modelo.negocio.daos.UsuarioDAO;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.util.formato.ConvertidorFolio;
import neology.vc.actions.login.CerrarSesionAction;
import neology.vc.forms.transferirFormatos.*;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

public class TransferirFormatosAction extends DispatchAction {
	/**
	 * This is the main action called from the Struts framework.
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance.
	 * @param form
	 *            The optional ActionForm bean for this request.
	 * @param request
	 *            The HTTP Request we are processing.
	 * @param response
	 *            The HTTP Response we are processing.
	 */

	public ActionForward cargar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_TransferirFormatosAction cargar - INICIO***");
		try {
			TransferirFormatosForm formulario = (TransferirFormatosForm) form;
			UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
			EntidadDAO entidadDAO = DAOFactory.crearEntidadDAO();
			EstadoDAO estadoDAO = DAOFactory.crearEstadoDAO();
			TipoFormatoDAO tipoDAO = DAOFactory.crearTipoFormatoDAO();
			formulario.setTipoFormatos(tipoDAO.buscarTipoFormatos());
			formulario.setEntidades(entidadDAO.buscarEntidades());
			formulario.setEstados(estadoDAO.obtenerEstados(Estado.IMPRESO));
			loggerInfo.info(FechaUtil.getHoraActual()+"_TransferirFormatosAction cargar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("cargar");
		} catch (Exception e) {
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo TransferirFormatosAction cargar", e);
			loggerError.error(FechaUtil.getHoraActual()+"_TransferirFormatosAction cargar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("cargar");
		}
		
	}

	public ActionForward cambiar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		ActionMessages mensaje = new ActionMessages();
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_TransferirFormatosAction cambiar - INICIO***");
		// Datos iniciales
		try {
			TransferirFormatosForm formulario = (TransferirFormatosForm) form;
			String folioInicial = formulario.getFolioInicial();
			String folioInicialr = folioInicial;
			String folioFinal = formulario.getFolioFinal();
			long idTipoFormato = formulario.getIdTipoFormato();
			long idEntidad = formulario.getIdEntidad();
			// String estado = formulario.getEstado();
			List noProcedeActualizacion = new ArrayList();
			List erroneos = new ArrayList();
			int numeroFolios = 0;
			int totalFormatos = 0;
			long cuentaFormatos = 0;
			long numeroFormatos = 0;
			Boolean valHor = false;
			Boolean exito = false;
			try {
				// Se obtienen los Daos correspondientes
				EntidadDAO entidadDAO = DAOFactory.crearEntidadDAO();
				TipoFormatoDAO tipoDAO = DAOFactory.crearTipoFormatoDAO();
				FormatoDAO formatoDAO = DAOFactory.crearFormatoDAO();
				EstadoDAO estadoDAO = DAOFactory.crearEstadoDAO();
				ParametrosDAO parametrosDAO = DAOFactory.crearParametrosDAO();

				TipoFormato tipoFormato = tipoDAO.buscarPorId(idTipoFormato);
				Entidad entidad = entidadDAO.buscarPorId(idEntidad);

				// Se obtiene el usuario de sesion
				Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");

				// Se valida el formato de los folios
				ActionMessages errores = new ActionMessages();
				if (!Utilidades.isFolioValido(tipoFormato.getFormatoFolio(), folioInicial)) {
					errores.add(ActionMessages.GLOBAL_MESSAGE,
							new ActionMessage("errors.formato.formatoFolio.invalido", "Folio Inicial"));
					loggerError.error(FechaUtil.getHoraActual()
							+"  Fallo cambiar cargar");
				}
				if (folioFinal != null && folioFinal.trim().length() >= 1) {
					if (!Utilidades.isFolioValido(tipoFormato.getFormatoFolio(), folioFinal)) {
						errores.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("errors.formato.formatoFolio.invalido", "Folio Final"));
						loggerError.error(FechaUtil.getHoraActual()
								+"  Fallo cambiar cargar");
					}
				} else
					folioFinal = folioInicial;
				if (errores.size() > 0) {
					saveErrors(request, errores);
					loggerError.error(FechaUtil.getHoraActual()+"_TransferirFormatosAction cambiar - FIN***");
					loggerInfo.removeAllAppenders();
					loggerError.removeAllAppenders();
					return mapping.getInputForward();
				}

				// Se obtienen los rangos para los folios de los tsP
				ConvertidorFolio convertidor = new ConvertidorFolio(tipoFormato.getFormatoFolio());
				int folioI = convertidor.getRango(folioInicial);
				int folioF = convertidor.getRango(folioFinal);
				if (folioF == 0)
					folioF = folioI;
				totalFormatos = ((folioF + 1) - folioI);

				loggerInfo.info(FechaUtil.getHoraActual()+"_TransferirFormatosAction inicio cambiar validar");
				// Validamos contra parametros del sistema
				// numeroFormatos =
				// parametrosDAO.buscarPorModulo("TRANSFERIR_FORMATOS");
				// if (numeroFormatos >= ((folioF+1)-folioI)){
				valHor = true;
				// obtenemos el estado del primer formato a transferir
				Formato primerFormato = formatoDAO.buscarPorId(new FormatoId(folioInicial, idTipoFormato));

				noProcedeActualizacion = formatoDAO.validaPorRangoFolios(convertidor.getFolio(folioI),
						convertidor.getFolio(folioF), idTipoFormato, usuario.getEntidad().getIdEntidad(),
						primerFormato.getEstado().getEstado(), totalFormatos);
				// }
				loggerInfo.info(FechaUtil.getHoraActual()+"_TransferirFormatosAction fin cambiar validar");
				loggerInfo.info(FechaUtil.getHoraActual()+"_TransferirFormatosAction inicio cambiar guardar");
				// Se actualizan los tsP
				if (noProcedeActualizacion == null && valHor) {
					exito = formatoDAO.transferirEstadoRango(idTipoFormato, convertidor.getFolio(folioI),
							convertidor.getFolio(folioF), idEntidad, new Long(usuario.getIdUsuario()));

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
							loggerError.error(FechaUtil.getHoraActual()+"_TransferirFormatosAction cambiar - FIN***");
						} else
							errores2.add("noProcedeActualizacion", new ActionMessage(
									"errors.formato.cambiarEstado.noCambiado", noProcedeActualizacion.size()));
						loggerError.error(FechaUtil.getHoraActual()+"_TransferirFormatosAction cambiar - FIN***");
						// errores2.add("noProcedeActualizacion", new
						// ActionMessage("errors.formato.transferirFormatos.noCambiado"));
						loggerInfo.removeAllAppenders();
						loggerError.removeAllAppenders();
						saveErrors(request, errores2);

					}
				}
			} catch (Exception e) {
				numeroFolios = 0;
				loggerError.error(FechaUtil.getHoraActual()+"_TransferirFormatosAction cambiar - FIN***");
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
			}
			// Se generan los mensajes correspondientes
			loggerInfo.info(FechaUtil.getHoraActual()+"_TransferirFormatosAction fin cambiar guardar");
			
			ActionMessages erroneo = new ActionMessages();
			if (exito)
				mensaje.add("mensaje", new ActionMessage("mensaje.formato.transferirFormatos.exitoso", totalFormatos,
						folioInicialr, folioFinal));
			else
				mensaje.add("mensaje", new ActionMessage("errors.formato.dotacion.guardar.general"));
			if (erroneos.size() > 0) {
				
				loggerError.error(FechaUtil.getHoraActual()+"_TransferirFormatosAction cambiar - FIN***");
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				erroneo.add("erroneo", new ActionMessage("errors.formato.dotacion.guardar"));
				saveErrors(request, erroneo);
			}
			if (!valHor) {
				loggerError.error(FechaUtil.getHoraActual()+"_TransferirFormatosAction cambiar - FIN***");
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				mensaje.add("mensaje", new ActionMessage("errors.formato.excede.numFormatosMax", "Transferir",
						numeroFormatos, totalFormatos));
			}
			request.setAttribute("erroneos", erroneos);
			request.setAttribute("noProcedeActualizacion", noProcedeActualizacion);
			request.setAttribute("mensaje", mensaje);
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			loggerInfo.info(FechaUtil.getHoraActual()+"_TransferirFormatosAction cambiar - FIN***");
			return mapping.findForward("exito");
		} catch (Exception e) {
			
			mensaje.add("mensaje", new ActionMessage("texto.etiqueta.error.log"));
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo TransferirFormatosAction cambiar", e);
			loggerError.error(FechaUtil.getHoraActual()+"_TransferirFormatosAction cambiar - FIN***");	
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			request.setAttribute("mensaje", mensaje);
			return mapping.findForward("exito");
		}
	}
}
