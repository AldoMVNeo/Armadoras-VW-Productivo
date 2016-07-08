package neology.vc.actions.busqueda;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.modelo.dto.Entidad;
import neology.modelo.dto.Estado;
import neology.modelo.dto.Formato;
import neology.modelo.dto.FormatoHistorico;
import neology.modelo.dto.OrdenImpresion;
import neology.modelo.dto.OrdenesHistorico;
import neology.modelo.dto.Parametros;
import neology.modelo.dto.TipoFormato;
import neology.modelo.dto.Usuario;
import neology.modelo.dto.id.FormatoId;
import neology.modelo.negocio.daos.EntidadDAO;
import neology.modelo.negocio.daos.EstadoDAO;
import neology.modelo.negocio.daos.FormatoDAO;
import neology.modelo.negocio.daos.CambioEstadoDAO;
import neology.modelo.negocio.daos.OrdenImpresionDAO;
import neology.modelo.negocio.daos.OrdenesHistoricoDAO;
import neology.modelo.negocio.daos.ParametrosDAO;
import neology.modelo.negocio.daos.TipoFormatoDAO;
import neology.modelo.negocio.daos.UsuarioDAO;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.util.formato.ConvertidorFolio;
import neology.vc.forms.busqueda.BusquedaNivForm;
import neology.vc.forms.cambiarEstado.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

public class BusquedaNivAction extends DispatchAction {
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
	
	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();

		BusquedaNivForm formulario = (BusquedaNivForm) form;

		loggerInfo.info(FechaUtil.getHoraActual() + "_ActionForward inicio - INICIO***");
		loggerInfo.removeAllAppenders();
		return mapping.findForward("inicio");
	}

	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		ActionMessages errores = new ActionMessages();

		try {

			BusquedaNivForm formulario = (BusquedaNivForm) form;

			String vin = formulario.getVin().toUpperCase();
			OrdenImpresionDAO ordenDAO = DAOFactory.crearOrdenImpresionDAO();
			EstadoDAO estadoDAO = DAOFactory.crearEstadoDAO();
			OrdenesHistoricoDAO ordenHistoricoDAO = DAOFactory.crearOrdenesHistoricoDAO();
			EntidadDAO entidadDAO = DAOFactory.crearEntidadDAO();
			UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
			
			if(!formulario.getConsultaBD()){
				
			loggerInfo.info(FechaUtil.getHoraActual() + "_BusquedaNivAction busqueda niv: " + vin);
			List ordenes = ordenDAO.listarVin(vin);
			loggerError.removeAllAppenders();	
			List<OrdenesHistorico> ordenHistorial= ordenHistoricoDAO.buscarPorNiv(vin);
			loggerError.removeAllAppenders();	
			
			List listaHistorial = new ArrayList();

			// Se asignan datos al historico
			for (OrdenesHistorico ordenesHistorico : ordenHistorial) {

				// Estado
				// estado=estadoDAO.buscarPorId(ordenesHistorico.getEstado().getEstado());

				if (ordenesHistorico.getFolio() == null) {

					ordenesHistorico.setFolio("");

				} else {
					ordenesHistorico.setFolio(ordenesHistorico.getFolio());
				}

				if (ordenesHistorico.getNumeroChip() == null) {

					ordenesHistorico.setNumeroChip("");

				} else {
					ordenesHistorico.setNumeroChip(ordenesHistorico.getNumeroChip());
				}

				ordenesHistorico.setVin(ordenesHistorico.getVin());
				ordenesHistorico.setEstado(ordenesHistorico.getEstado());
				ordenesHistorico.setFechaRegistro(ordenesHistorico.getFechaRegistro());
				ordenesHistorico.setIdUsuarioModifico(ordenesHistorico.getIdUsuarioModifico());

				listaHistorial.add(ordenesHistorico);
			}
			
			formulario.setDatos(ordenes);
			request.setAttribute("listaHistorial", listaHistorial);
			

			if (ordenes == null || ordenes.size() == 0)
				errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.reportes.nodatos"));
			if (errores.size() > 0) {

				loggerError.error(FechaUtil.getHoraActual() + "_BusquedaNivAction buscar - FIN***");
				
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				saveErrors(request, errores);
				return mapping.findForward("resultado");
			}
			}else{

				loggerError.removeAllAppenders();	
				List ordenes = ordenDAO.listarOrdenResp(vin,"");
				loggerError.removeAllAppenders();	
				List<OrdenesHistorico> ordenHistorial= ordenHistoricoDAO.listarVinHistoricoResp(vin);
				
				List listaHistorial = new ArrayList();

				if(ordenHistorial.size()>0){
				// Se asignan datos al historico
				for (OrdenesHistorico ordenesHistorico : ordenHistorial) {

					// Estado
					// estado=estadoDAO.buscarPorId(ordenesHistorico.getEstado().getEstado());

					if (ordenesHistorico.getFolio() == null) {

						ordenesHistorico.setFolio("");

					} else {
						ordenesHistorico.setFolio(ordenesHistorico.getFolio());
					}

					if (ordenesHistorico.getNumeroChip() == null) {

						ordenesHistorico.setNumeroChip("");

					} else {
						ordenesHistorico.setNumeroChip(ordenesHistorico.getNumeroChip());
					}

					ordenesHistorico.setVin(ordenesHistorico.getVin());
					ordenesHistorico.setEstado(ordenesHistorico.getEstado());
					ordenesHistorico.setFechaRegistro(ordenesHistorico.getFechaRegistro());
					ordenesHistorico.setIdUsuarioModifico(ordenesHistorico.getIdUsuarioModifico());

					listaHistorial.add(ordenesHistorico);
				}
				}else{
					errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.reportes.nodatos"));
					if (errores.size() > 0) {

						loggerError.error(FechaUtil.getHoraActual() + "_BusquedaNivAction buscar - FIN***");
						loggerInfo.removeAllAppenders();
						loggerError.removeAllAppenders();
						saveErrors(request, errores);
						return mapping.findForward("resultado");
					}
				}
				
				formulario.setDatos(ordenes);
				request.setAttribute("listaHistorial", listaHistorial);
				

				if (ordenes == null || ordenes.size() == 0)
					errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.reportes.nodatos"));
				if (errores.size() > 0) {

					loggerError.error(FechaUtil.getHoraActual() + "_BusquedaNivAction buscar - FIN***");
					loggerInfo.removeAllAppenders();
					loggerError.removeAllAppenders();
					saveErrors(request, errores);
					return mapping.findForward("resultado");
				}
				
			}
			loggerInfo.info(FechaUtil.getHoraActual() + "_BusquedaNivAction buscar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("resultado");

		} catch (Exception e) {

			loggerError.error(FechaUtil.getHoraActual() + "_Fallo BusquedaNivAction buscar", e);
			loggerError.error(FechaUtil.getHoraActual() + "_BusquedaNivAction buscar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("texto.etiqueta.error.log"));
			
			saveErrors(request, errores);
			return mapping.findForward("resultado");
		}
	}

	public String isAdmin(HttpServletRequest request){
		
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");
		String isAdmin="hidden";
		
		if(usuario.getIdUsuario()==1){
			 isAdmin="visible";
		}
		
		return isAdmin;
		
	}
	
}
