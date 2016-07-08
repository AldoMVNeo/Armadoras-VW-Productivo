package neology.vc.actions.emision.grabacion;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neology.modelo.dto.Entidad;
import neology.modelo.dto.Estado;
import neology.modelo.dto.EstadoImpresion;
import neology.modelo.dto.EstadoOI;
import neology.modelo.dto.Formato;
import neology.modelo.dto.FormatoHistorico;
import neology.modelo.dto.OrdenImpresion;
import neology.modelo.dto.OrdenesHistorico;
import neology.modelo.dto.TipoFormato;
import neology.modelo.dto.Usuario;
import neology.modelo.dto.id.FormatoId;

import neology.modelo.negocio.daos.EstadoDAO;
import neology.modelo.negocio.daos.EstadoOIDAO;
import neology.modelo.negocio.daos.FormatoDAO;
import neology.modelo.negocio.daos.FormatoHistoricoDAO;
import neology.modelo.negocio.daos.OrdenImpresionDAO;
import neology.modelo.negocio.daos.OrdenesHistoricoDAO;
import neology.modelo.negocio.daos.TipoFormatoDAO;
import neology.modelo.negocio.daos.UsuarioDAO;

import neology.modelo.negocio.servicios.DAOFactory;

import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.vc.forms.emision.EmisionFormatosForm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class GrabacionChipAction extends DispatchAction {
	/**
	 * Inicio.
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
	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		/*
		 * Long ordenId = Long.parseLong(request.getParameter("ordenId")); Long
		 * tipoFormatoId = Long.parseLong(request
		 * .getParameter("tipoFormatoId")); request.setAttribute("ordenId",
		 * ordenId); request.setAttribute("tipoFormatoId", tipoFormatoId);
		 * request.setAttribute("accion", "|L|");
		 * request.setAttribute("usuarioId", usuario.getIdUsuario());
		 */
		return mapping.findForward("grabar");
	}

	/**
	 * actualizarFormato Cuando la grabacion fue exitosa se actualizan valores
	 * de la forma y el niv
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public ActionForward actualizaFormato(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		
		try {
			loggerInfo.info(FechaUtil.getHoraActual()
					+ "_GrabacionChipAction actualizaFormato - INICIO***");

			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");
			response.setContentType("text/html; charset=iso-8859-1");
			PrintWriter out = response.getWriter();
			String datos = request.getParameter("datosChip");
			String[] arreglo = datos.split("\\|");
			TipoFormato tipoFormato = null;
			EstadoOI estadoOI = null;
			Estado estadoFormato = null;
			loggerInfo.info(FechaUtil.getHoraActual()
					+ "_GrabacionChipAction inicio actualizaFormato actualizar");
			FormatoDAO formatoDAO = DAOFactory.crearFormatoDAO();
			EstadoOIDAO estadoOIDAO = DAOFactory.crearEstadoOIDAO();
			EstadoDAO estadoDAO = DAOFactory.crearEstadoDAO();
			TipoFormatoDAO tipoDAO = DAOFactory.crearTipoFormatoDAO();
			OrdenImpresionDAO ordenDAO = DAOFactory.crearOrdenImpresionDAO();
			FormatoHistoricoDAO formatoHistoricoDAO = DAOFactory.crearFormatoHistoricoDAO();
			OrdenesHistoricoDAO ordenHistoricoDAO = DAOFactory.crearOrdenesHistoricoDAO();
			tipoFormato = tipoDAO.buscarPorId(Formato.FORMA_REPUVE);
			// Sea actualizan valores y se asigna una forma al niv
			Formato formato = formatoDAO.buscarPorId(new FormatoId(arreglo[1], tipoFormato.getIdTipoFormato()));
			OrdenImpresion orden = (OrdenImpresion) ordenDAO.buscarPorNIV(arreglo[2]).get(0);
			orden.setFolio(formato.getFormatoId().getFolio());
			orden.setNumeroChip(arreglo[3]);
			orden.setUsuario(usuario);
			Integer ng = orden.getNumeroGrabaciones();
			if (ng == null)
				ng = new Integer(0);
			if (ng == 0)
				estadoOI = estadoOIDAO.buscarPorId(EstadoOI.GRABADO);
			else if (ng > 0)
				estadoOI = estadoOIDAO.buscarPorId(EstadoOI.REGRABADO);

			estadoFormato = estadoDAO.buscarPorId(Estado.GRABADO);
			orden.setNumeroGrabaciones(ng + 1);
			orden.setEstado(estadoOI);
			boolean guardarOrden = ordenDAO.guardar(orden);
			formato.setOrdenImpresion(orden);
			formato.setFechaRegistro(FechaUtil.getFechaYHoraActual());
			formato.setEstado(estadoFormato);
			formato.setUsuarioModifico(usuario);
			boolean actualizarFormato = formatoDAO.actualizar(formato);
			String respuesta = "";
			if (guardarOrden && actualizarFormato) {

				respuesta = "OK";
				// Guarda historico formatos y ordenes de impresion

				FormatoHistorico formatoHistorico = new FormatoHistorico();
				formatoHistorico.setIdUsuarioModifico(usuario.getIdUsuario());
				formatoHistorico.setFechaHistorico(FechaUtil.getFechaYHoraActual());
				formatoHistorico.setFolio(formato.getFormatoId().getFolio());
				formatoHistorico.setIdTipoFormato(1);
				formatoHistorico.setIdEntidad(usuario.getEntidad().getIdEntidad());
				formatoHistorico.setIdUsuarioActual(usuario.getIdUsuario());
				formatoHistorico.setEstado(estadoFormato.getEstado());
				formatoHistorico.setFechaRegistro(FechaUtil.getFechaYHoraActual());
				formatoHistorico.setIdOrdenImpresion(orden.getIdOrdenImpresion());

				OrdenesHistorico ordenHistorico = new OrdenesHistorico();
				ordenHistorico.setAnnoModelo(orden.getAnnoModelo());
				ordenHistorico.setBarco(orden.getBarco());
				ordenHistorico.setCapacidad(orden.getCapacidad());
				ordenHistorico.setCilindraje(orden.getCilindraje());
				ordenHistorico.setColor(orden.getColor());
				ordenHistorico.setCombustible(orden.getCombustible());
				ordenHistorico.setEntidad(usuario.getEntidad().getNombreEntidad());
				ordenHistorico.setEstado(orden.getEstado());
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

				if (!formatoHistoricoDAO.guardar(formatoHistorico) || !ordenHistoricoDAO.guardar(ordenHistorico)) {
					respuesta = "ERROR: CONSTANCIA GRABADA-HISTORICO NO GUARDADO";
					loggerError.error(FechaUtil.getHoraActual()
							+ "   ERROR: CONSTANCIA GRABADA-HISTORICO NO GUARDADO");
				}
			} else {
				respuesta = "ERROR";
				loggerError.error(FechaUtil.getHoraActual()
						+ "   ERROR");
			}
			out.println(respuesta);
			out.close();
			loggerInfo.info(FechaUtil.getHoraActual()
					+ "_GrabacionChipAction fin actualizaFormato actualizar");
			loggerInfo.info(FechaUtil.getHoraActual()
					+ "_GrabacionChipAction actualizaFormato - FIN***");
			
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward(null);
		} catch (Exception e) {
			loggerError.error(FechaUtil.getHoraActual()
					+ "_Fallo GrabacionChipAction actualizaFormato", e);
			loggerError.error(FechaUtil.getHoraActual()
					+ "_GrabacionChipAction actualizaFormato - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward(null);
		}
	}

}
