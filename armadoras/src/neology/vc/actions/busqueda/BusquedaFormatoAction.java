package neology.vc.actions.busqueda;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.modelo.dto.Estado;
import neology.modelo.dto.Formato;
import neology.modelo.dto.FormatoHistorico;
import neology.modelo.dto.OrdenImpresion;
import neology.modelo.dto.OrdenesHistorico;
import neology.modelo.dto.TipoFormato;
import neology.modelo.dto.Usuario;
import neology.modelo.dto.id.FormatoId;
import neology.modelo.negocio.daos.EstadoDAO;
import neology.modelo.negocio.daos.FormatoDAO;
import neology.modelo.negocio.daos.FormatoHistoricoDAO;
import neology.modelo.negocio.daos.OrdenImpresionDAO;
import neology.modelo.negocio.daos.UsuarioDAO;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.vc.forms.busqueda.BusquedaFormatoForm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

public class BusquedaFormatoAction extends DispatchAction {

	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Logger loggerInfo = Utilidades.loggerInfo();

		loggerInfo.info(FechaUtil.getHoraActual() + "_BusquedaFormatoAction inicio - INICIO***");
		loggerInfo.removeAllAppenders();
		BusquedaFormatoForm formulario = (BusquedaFormatoForm) form;
		formulario.setConsultaBD(false);

		return mapping.findForward("buscar");
	}

	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		ActionMessages errores = new ActionMessages();

		try {

			loggerInfo.info(FechaUtil.getHoraActual() + "_BusquedaFormatoAction buscar - INICIO***");
			BusquedaFormatoForm formulario = (BusquedaFormatoForm) form;		
			String folio = formulario.getFolio();
			FormatoDAO formatoDAO = DAOFactory.crearFormatoDAO();
			FormatoHistoricoDAO fhDAO = DAOFactory.crearFormatoHistoricoDAO();
			loggerInfo.info(FechaUtil.getHoraActual() + "_BusquedaFormatoAction buscar folio: " + folio);
			
			if(folio.trim().equals("") || folio==null){
				
				loggerError.error(FechaUtil.getHoraActual() + "_Fallo BusquedaFormatoAction inicio");
				loggerError.error(FechaUtil.getHoraActual() + "_BusquedaFormatoAction buscar - FIN***");
				errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.requerido", "folio"));
				if (errores.size() > 0) {
					loggerError.error(FechaUtil.getHoraActual() + "_BusquedaFormatoAction buscar - FIN***");
					loggerInfo.removeAllAppenders();
					loggerError.removeAllAppenders();
					saveErrors(request, errores);
					return mapping.findForward("buscar");
			}}

			if (!formulario.getConsultaBD()) {
				Formato formato = formatoDAO.buscarPorId(new FormatoId(folio, TipoFormato.REPUVE));
				List<FormatoHistorico> formatoHistorial = fhDAO.buscarPorFolio(folio);
				loggerInfo.info(FechaUtil.getHoraActual() + "_BusquedaFormatoAction finalizo busqueda folio: " + folio);
				List formatoActual = new ArrayList();
				List listaHistorial = new ArrayList();
				EstadoDAO estadoDAO = DAOFactory.crearEstadoDAO();
				OrdenImpresionDAO ordenDAO = DAOFactory.crearOrdenImpresionDAO();
				UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();

				// Se asigna estado,usuario, niv, tagid al historico
				for (FormatoHistorico formatoHistorico : formatoHistorial) {
					Estado estado = estadoDAO.buscarPorId(formatoHistorico.getEstado());
					Usuario usuario = usuarioDAO.buscarPorId(formatoHistorico.getIdUsuarioActual());
					if (formatoHistorico.getIdOrdenImpresion() != null) {
						OrdenImpresion orden = ordenDAO.buscarPorId(formatoHistorico.getIdOrdenImpresion());
						formatoHistorico.setNiv(orden.getVin());
						formatoHistorico.setNumeroChip(orden.getNumeroChip());
					}
					formatoHistorico.setDescripcionEstado(estado.getDescripcion());
					formatoHistorico.setNombreUsuario(usuario.getNombres());
					listaHistorial.add(formatoHistorico);
				}

				if (formato != null) {
					if (formato.getOrdenImpresion() == null)
						formato.setOrdenImpresion(new OrdenImpresion());
					formatoActual.add(formato);
				} else
					errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.reportes.nodatos"));
				if (errores.size() > 0) {
					loggerError.error(FechaUtil.getHoraActual() + "_BusquedaFormatoAction buscar - FIN***");
					loggerInfo.removeAllAppenders();
					loggerError.removeAllAppenders();
					saveErrors(request, errores);
				}
				request.setAttribute("formatoActual", formatoActual);
				request.setAttribute("formatoHistorial", listaHistorial);
				loggerInfo.info(FechaUtil.getHoraActual() + "_BusquedaFormatoAction buscar - FIN***");
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();

				return mapping.findForward("buscar");
			} else {
				
				Formato formato = formatoDAO.listarFormatoResp(folio);
				List<FormatoHistorico> formatoHistorial = fhDAO.listarFormatoHistoricoResp(folio);
				loggerInfo.info(FechaUtil.getHoraActual() + "_BusquedaFormatoAction finalizo busqueda folio: " + folio);
				List formatoActual = new ArrayList();
				List listaHistorial = new ArrayList();
				EstadoDAO estadoDAO = DAOFactory.crearEstadoDAO();
				OrdenImpresionDAO ordenDAO = DAOFactory.crearOrdenImpresionDAO();
				UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();

				// Se asigna estado,usuario, niv, tagid al historico
				if(formatoHistorial.size()>0 && formato.getFormatoId()!=null){
				for (FormatoHistorico formatoHistorico : formatoHistorial) {
					Estado estado = estadoDAO.buscarPorId(formatoHistorico.getEstado());
					Usuario usuario = usuarioDAO.buscarPorId(formatoHistorico.getIdUsuarioActual());
					formatoHistorico.setDescripcionEstado(estado.getDescripcion());
					formatoHistorico.setNombreUsuario(usuario.getNombres());
					listaHistorial.add(formatoHistorico);
				}
				
				}else{
					errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.reportes.nodatos"));
					if (errores.size() > 0) {
						loggerError.error(FechaUtil.getHoraActual() + "_BusquedaFormatoAction buscar - FIN***");
						loggerInfo.removeAllAppenders();
						loggerError.removeAllAppenders();
						saveErrors(request, errores);
						return mapping.findForward("buscar");
					}
				}

				if (formato != null) {
					if (formato.getOrdenImpresion() == null)
						formato.setOrdenImpresion(new OrdenImpresion());
					formatoActual.add(formato);
				} else
					errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.reportes.nodatos"));
				if (errores.size() > 0) {
					loggerError.error(FechaUtil.getHoraActual() + "_BusquedaFormatoAction buscar - FIN***");
					loggerInfo.removeAllAppenders();
					loggerError.removeAllAppenders();
					saveErrors(request, errores);
				}
				request.setAttribute("formatoActual", formatoActual);
				request.setAttribute("formatoHistorial", listaHistorial);
				loggerInfo.info(FechaUtil.getHoraActual() + "_BusquedaFormatoAction buscar - FIN***");
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();

				return mapping.findForward("buscar");
				
			}
		} catch (Exception e) {

			loggerError.error(FechaUtil.getHoraActual() + "_Fallo BusquedaFormatoAction inicio", e);
			loggerError.error(FechaUtil.getHoraActual() + "_BusquedaFormatoAction buscar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("texto.etiqueta.error.log"));
			
			saveErrors(request, errores);
			return mapping.findForward("buscar");
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
}
