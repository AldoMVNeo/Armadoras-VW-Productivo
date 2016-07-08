package neology.vc.actions.reImpresion;

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

import java.io.IOException;
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

import neology.modelo.dto.Entidad;
import neology.modelo.dto.Estado;
import neology.modelo.dto.EstadoOI;
import neology.modelo.dto.Formato;
import neology.modelo.dto.FormatoHistorico;
import neology.modelo.dto.OrdenImpresion;
import neology.modelo.dto.OrdenesHistorico;
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
import neology.util.FechaUtil;
import neology.util.VariablesGlobales;
import neology.vc.actions.parametros.ParametrosAction;
import neology.vc.forms.catalogos.entidades.EntidadesForm;
import neology.vc.forms.reImpresion.ReImpresionesForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import java.util.ArrayList;
import java.util.List;

import neology.util.Utilidades;

public class ReImpresionFormatosAction extends DispatchAction {

	private static String reporteTituloConChip = "reporteTituloConChip";

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

		try {
			loggerInfo.info(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction cargar - INICIO***");
			ReImpresionesForm formulario = (ReImpresionesForm) form;
			OrdenImpresionDAO ordenDAO = DAOFactory.crearOrdenImpresionDAO();
			UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");
			formulario.setBloques(ordenDAO.ObtenerPaquetes(usuario.getEntidad().getIdEntidad()));
			loggerInfo.info(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction cargar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("cargar");
		} catch (Exception e) {
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo ReImpresionFormatosAction cargar", e);
			loggerError.error(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction cargar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("cargar");
		}

	}

	public ActionForward mostrar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction mostrar - INICIO***");

		try {
			ReImpresionesForm formulario = (ReImpresionesForm) form;
			OrdenImpresionDAO ordenDAO = DAOFactory.crearOrdenImpresionDAO();
			Long numeroTramite = formulario.getNumeroTramite();
			List ordenes = ordenDAO.buscarOrdenesReImpresion(numeroTramite);
			formulario.setDatos(ordenes);
			formulario.setTotalVins(ordenes.size());
			loggerInfo.info(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction mostrar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("mostrar");
		} catch (Exception e) {
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo ReImpresionFormatosAction mostrar", e);
			loggerError.error(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction mostrar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("cargar");
		}

	}

	public ActionForward listar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction listar - INICIO***");
		try {
			ActionMessages errores = (ActionMessages) request.getAttribute("errores");
			ReImpresionesForm formulario = (ReImpresionesForm) form;
			OrdenImpresionDAO ordenDAO = DAOFactory.crearOrdenImpresionDAO();
			UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");

			List ordenes = ordenDAO.buscarOrdenesImpresion(usuario.getEntidad().getIdEntidad());
			ordenes.size();
			/*
			 * for(int i=0; i< ordenes.size();i++){
			 * formulario.setImpresiones(null); }
			 */
			// request.setAttribute("impresiones",
			// ordenDAO.buscarOrdenesImpresion(usuario.getEntidad().getIdEntidad()));
			formulario.setDatos(ordenes);
			// saveErrors(request, errores);
			loggerInfo.info(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction listar - FIN***");
			return mapping.findForward("listar");
		} catch (Exception e) {
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo ReImpresionFormatosAction listar", e);
			loggerError.error(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction listar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("listar");
		}
	}

	public ActionForward guardar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction guardar - INICIAR");
		
		try {
			OrdenImpresionDAO ordenDAO = DAOFactory.crearOrdenImpresionDAO();
			ReImpresionesForm formulario = (ReImpresionesForm) form;
			MessageResources mensajes = getResources(request);
			Boolean pdf = false;
			FormatoDAO formatoDAO = DAOFactory.crearFormatoDAO();
			OrdenesHistoricoDAO ordenHistoricoDAO = DAOFactory.crearOrdenesHistoricoDAO();
			List datos = new ArrayList();
			datos = formulario.getDatos();
			String impresiones[] = formulario.getImpresiones();
			String vinR = formulario.getVin();
			OrdenImpresion ordenImpresion;
			EstadoDAO estadoDAO = DAOFactory.crearEstadoDAO();
			EstadoOIDAO estadoOIDAO = DAOFactory.crearEstadoOIDAO();
			TipoFormatoDAO tipoDAO = DAOFactory.crearTipoFormatoDAO();
			EstadoOI estadoOI = estadoOIDAO.buscarPorId(EstadoOI.IMPRESO);
			FormatoHistoricoDAO formatoHistoricoDAO = DAOFactory.crearFormatoHistoricoDAO();
			Estado estadoCancelado = estadoDAO.buscarPorId("6");
			String vin;
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");
			Long numImpresion = ordenDAO.ObtenerMaximoImpresion(usuario.getEntidad().getIdEntidad());
			Map parametros = new HashMap();
			if (numImpresion == null)
				numImpresion = new Long(0);
			numImpresion++;
			formulario.setNumImpresion(numImpresion);
			loggerInfo.info(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction inicio reimpresion guardar ");
			if (vinR != null && vinR != "") {
				OrdenImpresion orden = ordenDAO.buscarPorVin(vinR);
				if (orden != null) {
					if (orden.getFolio() != null && orden.getFolio() != "") {
						Formato formato = formatoDAO.buscarPorId(
								new FormatoId(orden.getFolio(), orden.getTipoFormato().getIdTipoFormato()));
						formato.setEstado(estadoCancelado);
						formato.setUsuarioModifico(usuario);
						formato.setFechaRegistro(FechaUtil.getFechaYHoraActual());
						formato.setObservaciones("CANCELADO POR EL SISTEMA DE REIMPRESION");
						loggerInfo.info(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction folio guardado "+orden.getFolio());
						formatoDAO.actualizar(formato);

						// Agregar Historico
						FormatoHistorico formatoHistorico = new FormatoHistorico();
						formatoHistorico.setIdUsuarioModifico(usuario.getIdUsuario());
						formatoHistorico.setFechaHistorico(FechaUtil.getFechaYHoraActual());
						formatoHistorico.setFolio(orden.getFolio());
						formatoHistorico.setIdTipoFormato(1);
						formatoHistorico.setIdEntidad(usuario.getEntidad().getIdEntidad());
						formatoHistorico.setIdUsuarioActual(usuario.getIdUsuario());
						formatoHistorico.setEstado(estadoCancelado.getEstado());
						formatoHistorico.setFechaRegistro(FechaUtil.getFechaYHoraActual());
						formatoHistorico.setIdOrdenImpresion(orden.getIdOrdenImpresion());
						formatoHistoricoDAO.guardar(formatoHistorico);
						loggerInfo.info(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction orden guardada "+orden.getIdOrdenImpresion());
						
					}
					orden.setEstado(estadoOI);
					orden.setNumeroTramite(numImpresion);
					orden.setFechaImpresion(FechaUtil.getFechaYHoraActual());
					orden.setFolio(null);
					orden.setNumeroChip(null);
					orden.setUsuario(usuario);
					ordenDAO.actualizar(orden);

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
					ordenHistoricoDAO.guardar(ordenHistorico);
					loggerInfo.info(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction orden guardada "+orden.getIdOrdenImpresion());

					pdf = true;
				}
			} else if (impresiones != null) {
				for (int i = 0; impresiones.length > i; i++) {
					ordenImpresion = (OrdenImpresion) datos.get(Integer.parseInt(impresiones[i]));
					vin = ordenImpresion.getVin();
					OrdenImpresion orden = ordenDAO.buscarPorVin(vin);
					if (orden.getFolio() != null && orden.getFolio() != "") {
						Formato formato = formatoDAO.buscarPorId(
								new FormatoId(orden.getFolio(), orden.getTipoFormato().getIdTipoFormato()));
						formato.setEstado(estadoCancelado);
						formato.setUsuarioModifico(usuario);
						formato.setFechaRegistro(FechaUtil.getFechaYHoraActual());
						formatoDAO.actualizar(formato);

						// Agregar Historico
						FormatoHistorico formatoHistorico = new FormatoHistorico();
						formatoHistorico.setIdUsuarioModifico(usuario.getIdUsuario());
						formatoHistorico.setFechaHistorico(FechaUtil.getFechaYHoraActual());
						formatoHistorico.setFolio(orden.getFolio());
						formatoHistorico.setIdTipoFormato(1);
						formatoHistorico.setIdEntidad(usuario.getEntidad().getIdEntidad());
						formatoHistorico.setIdUsuarioActual(usuario.getIdUsuario());
						formatoHistorico.setEstado(estadoCancelado.getEstado());
						formatoHistorico.setFechaRegistro(FechaUtil.getFechaYHoraActual());
						formatoHistorico.setIdOrdenImpresion(orden.getIdOrdenImpresion());
						formatoHistoricoDAO.guardar(formatoHistorico);
						loggerInfo.info(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction folio guardado "+orden.getFolio());

					}
					orden.setEstado(estadoOI);
					orden.setNumeroTramite(numImpresion);
					orden.setFechaImpresion(FechaUtil.getFechaYHoraActual());
					orden.setFolio(null);
					orden.setNumeroChip(null);
					orden.setUsuario(usuario);
					ordenDAO.actualizar(orden);

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
					ordenHistoricoDAO.guardar(ordenHistorico);
					loggerInfo.info(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction orden guardada "+orden.getIdOrdenImpresion());

				}
				pdf = true;
				loggerInfo.info(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction fin reimpresion guardar");
			}
			if (pdf) {
				loggerInfo.info(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction guardar - FIN***");
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				return mapping.findForward("imprimir");
			} else {
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				return mapping.findForward("todos");
			}
		} catch (Exception e) {
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo ReImpresionFormatosAction guardar", e);
			loggerError.error(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction guardar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("todos");
		}
	}

	public ActionForward imprimePDF(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		
		try{
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction imprimePDF - INICIO***");
		OrdenImpresionDAO ordenDAO = DAOFactory.crearOrdenImpresionDAO();
		MessageResources mensajes = getResources(request);
		ReImpresionesForm formulario = (ReImpresionesForm) form;
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");
		// Long numImpresion =
		// ordenDAO.ObtenerMaximoImpresion(usuario.getEntidad().getIdEntidad());
		Long numImpresion = formulario.getNumImpresion();
		Map parametros = new HashMap();
		parametros.put("numImpresion", numImpresion);
		request.setAttribute("reporteEntrada", VariablesGlobales.getReporteConstanciaInscripcionREPUVE());
		request.setAttribute("tipoReporte", "application/pdf");
		request.setAttribute("reporteSalida", mensajes.getMessage("reporte.constanciaInscripcion.pdf"));
		request.setAttribute("parametros", parametros);
		loggerInfo.info(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction imprimePDF - FIN***");
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return mapping.findForward("generarPDF");
		} catch (Exception e) {
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo ReImpresionFormatosAction imprimePDF", e);
			loggerError.error(FechaUtil.getHoraActual()+"_ReImpresionFormatosAction imprimePDF - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("generarPDF");
		}

	}

}
