package neology.vc.actions.dotacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neology.modelo.dto.Entidad;
import neology.modelo.dto.Estado;
import neology.modelo.dto.Formato;
import neology.modelo.dto.FormatoHistorico;
import neology.modelo.dto.TipoFormato;
import neology.modelo.dto.Usuario;
import neology.modelo.dto.id.FormatoId;
import neology.modelo.negocio.daos.EntidadDAO;
import neology.modelo.negocio.daos.EstadoDAO;
import neology.modelo.negocio.daos.FormatoDAO;
import neology.modelo.negocio.daos.FormatoHistoricoDAO;
import neology.modelo.negocio.daos.ParametrosDAO;
import neology.modelo.negocio.daos.TipoFormatoDAO;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.util.formato.ConvertidorFolio;
import neology.vc.actions.catalogos.usuarios.UsuariosAction;
import neology.vc.forms.dotacion.DotacionForm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

public class DotacionAction extends DispatchAction
{
	/**
	 * Carga las entidades, tipos de formatos para realizar la dotación
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */

	public ActionForward inicio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException,
			ServletException
	{
		
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		ActionMessages errores2 = new ActionMessages();
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_DotacionAction inicio - INICIO***"); 
		try{
		Usuario usuario=(Usuario)request.getSession().getAttribute("usuarioLog");
		DotacionForm formulario = (DotacionForm) form;		
		TipoFormatoDAO tipoDAO = DAOFactory.crearTipoFormatoDAO();
		EntidadDAO entidadDAO=DAOFactory.crearEntidadDAO();
		Entidad entidad = entidadDAO.buscarPorId(Long.parseLong(Entidad.BODEGACENTRAL));
		formulario.setIdEntidad((int) entidad.getIdEntidad());
		formulario.setDescripcionEntidad(entidad.getNombreEntidad());
		formulario.setTipoFormatos(tipoDAO.buscarTipoFormatos());
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_DotacionAction inicio - FIN***"); 
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return mapping.findForward("cargar");
		}catch(Exception e){
			
			errores2.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"texto.etiqueta.error.log"));
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo DotacionAction inicio", e);
			loggerError.error(FechaUtil.getHoraActual()+"_DotacionAction inicio - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			saveErrors(request, errores2);
			return mapping.findForward("cargar");
		}
	}

	/**
	 * Realiza la dotación Formatos
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */

	public ActionForward dotar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException,
			ServletException
	{
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
	
		try{
		loggerInfo.info(FechaUtil.getHoraActual()+"_DotacionAction dotar - INICIO***"); 
		DotacionForm formulario = (DotacionForm) form;
		String folioInicial = formulario.getFolioInicial();
		String folioFinal = formulario.getFolioFinal();
		long idTipoFormato = formulario.getIdTipoFormato();
		long idEntidad = formulario.getIdEntidad();
		int numeroFolios = 0;
		List repetidos = new ArrayList();
		List erroneos = new ArrayList();
		TipoFormato tipoFormato = null;
		try
		{

			// Se obtienen los Daos correspondientes
			EntidadDAO entidadDAO = DAOFactory.crearEntidadDAO();
			TipoFormatoDAO tipoDAO = DAOFactory.crearTipoFormatoDAO();
			FormatoDAO formatoDAO = DAOFactory.crearFormatoDAO();
			EstadoDAO estadoDAO = DAOFactory.crearEstadoDAO();
			ParametrosDAO parametroDAO = DAOFactory.crearParametrosDAO();
			FormatoHistoricoDAO formatoHistoricoDAO = DAOFactory.crearFormatoHistoricoDAO();
			tipoFormato = tipoDAO.buscarPorId(idTipoFormato);
			Entidad entidad = entidadDAO.buscarPorId(idEntidad);

			loggerInfo.info(FechaUtil.getHoraActual()+"_DotacionAction inicio dotar validar folios: "+folioInicial+" - "+folioFinal);
			// Se valida el formato de los folios
			ActionMessages errores = new ActionMessages();
			if (!Utilidades.isFolioValido(tipoFormato.getFormatoFolio(), folioInicial))
			{
				errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.formato.formatoFolio.invalido", "Folio Inicial"));
			}
			if (folioFinal != null && folioFinal.trim().length() >= 1)
			{

				if (!Utilidades.isFolioValido(tipoFormato.getFormatoFolio(), folioFinal))
				{
					errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.formato.formatoFolio.invalido", "Folio Final"));

				}
			}
			else folioFinal = folioInicial;
			// Se obtienen los rangos para los folios de los formatos
			ConvertidorFolio convertidor = new ConvertidorFolio(tipoFormato
					.getFormatoFolio());
			int folioI = 0;
			int folioF = 0;
			if (errores.size() == 0)
			{

				folioI = convertidor.getRango(folioInicial);
				folioF = convertidor.getRango(folioFinal);

				if (folioF == 0) folioF = folioI;
				// Se verifica que el numero de formatos a dotar no exceda el maximo
				// permitido
				int numeroFormatos = (folioF + 1) - folioI;
				// long numeroMaximo=parametroDAO.buscarPorModulo("DOTAR");

				/*
				 * if(numeroFormatos>numeroMaximo)
				 * errores.add(ActionMessages.GLOBAL_MESSAGE, new
				 * ActionMessage("errors.formato.excede.numFormatosMax",
				 * "Dotar",numeroMaximo,numeroFormatos));
				 */
				if (folioI > folioF)
					errores.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.formato.folio.inicial.mayor"));
			}
			if (errores.size() > 0)
			{
				saveErrors(request, errores);
				loggerInfo.error(FechaUtil.getHoraActual()+"_DotacionAction dotar - FIN*** fallo"); 
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				return mapping.getInputForward();
			}

			// Se obtiene el usuario de sesion
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");

			// Se obtiene el estado de disponible para inicializar el formato
			Estado estado = estadoDAO.buscarPorId(Estado.DOTADO);

			// Buscar que no se encuentre ningun folio repetido
			formatoDAO.setNumeroMaximo(1000);
			repetidos = formatoDAO.buscarPorRangoFolios(convertidor.getFolio(folioI),
					convertidor.getFolio(folioF), tipoFormato.getIdTipoFormato());
			
			loggerInfo.info(FechaUtil.getHoraActual()+"_DotacionAction fin dotar validar folios: "+folioInicial+" - "+folioFinal);

			// Se insertan los formatos aqui
			loggerInfo.info(FechaUtil.getHoraActual()+"_DotacionAction inicio dotar folios: "+folioInicial+" - "+folioFinal);
			Formato formatoEliminado;
			if (repetidos.size() == 0)
			{
				for (int i = folioI; i <= folioF; i++)
				{
					formatoEliminado = formatoDAO.buscarPorId(new FormatoId(convertidor.getFolio(i),tipoFormato.getIdTipoFormato()));
					if(formatoEliminado != null) 
					{
						formatoEliminado.setEstado(estadoDAO.buscarPorId(Estado.DOTADO));
						formatoEliminado.setFechaRegistro(FechaUtil.getFechaYHoraActual());
						if (formatoDAO.actualizar(formatoEliminado)) numeroFolios++;
						else erroneos.add(formatoEliminado);
					}
					else
					{
						Formato formato = new Formato();
						formato.setFormatoId(new FormatoId(convertidor.getFolio(i),
								tipoFormato.getIdTipoFormato()));
						formato.setTipoFormato(tipoFormato);
						formato.setEntidad(entidad);
						formato.setUsuarioModifico(usuario);
						formato.setEstado(estado);
						formato.setFechaRegistro(FechaUtil.getFechaYHoraActual());
						if (formatoDAO.guardar(formato)) {					
							numeroFolios++;
							//Agregar Historico
							FormatoHistorico formatoHistorico= new FormatoHistorico();
							formatoHistorico.setIdUsuarioModifico(usuario.getIdUsuario());
							formatoHistorico.setFechaHistorico(FechaUtil.getFechaYHoraActual());
							formatoHistorico.setFolio(convertidor.getFolio(i));
							formatoHistorico.setIdTipoFormato(1);
							formatoHistorico.setIdEntidad(entidad.getIdEntidad());
							formatoHistorico.setIdUsuarioActual(usuario.getIdUsuario());
							formatoHistorico.setEstado(estado.getEstado());
							formatoHistorico.setFechaRegistro(FechaUtil.getFechaYHoraActual());
							formatoHistorico.setIdOrdenImpresion(null);
							loggerInfo.info(FechaUtil.getHoraActual()+"_DotacionAction folio guardado: "+convertidor.getFolio(i));
							
							if (!formatoHistoricoDAO.guardar(formatoHistorico)) {								
								erroneos.add(formato);								
							}
							
						} else {
							erroneos.add(formato);
						}
						;
					}
				}
			}
			else
			{
				ActionMessages errores2 = new ActionMessages();
				errores2.add("repetidos", new ActionMessage(
						"errors.formato.folio.repetido", repetidos.size()));
				loggerError.error(FechaUtil.getHoraActual()+"_Fallo DotacionAction dotar");
				loggerInfo.removeAllAppenders();
				loggerError.removeAllAppenders();
				saveErrors(request, errores2);
			}
		} catch (Exception e)
		{
			numeroFolios = 0;
			ActionMessages errores2 = new ActionMessages();
			errores2.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"texto.etiqueta.error.log"));
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo DotacionAction dotar", e);
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			saveErrors(request, errores2);
			return mapping.findForward("cargar");
		}
		loggerInfo.info(FechaUtil.getHoraActual()+"_DotacionAction fin dotar folios: "+folioInicial+" - "+folioFinal);
		// Se generan los mensajes correspondientes
		ActionMessages mensaje = new ActionMessages();
		ActionMessages erroneo = new ActionMessages();
		if (numeroFolios > 0) mensaje.add("mensaje", new ActionMessage(
				"mensaje.formato.dotacion.exitosa", numeroFolios, tipoFormato
						.getDescripcion(), folioInicial, folioFinal));

		else mensaje.add("mensaje", new ActionMessage(
				"errors.formato.dotacion.guardar.general"));
		if (erroneos.size() > 0)
		{
			erroneo.add("erroneo", new ActionMessage("errors.formato.dotacion.guardar"));
			loggerError.error(FechaUtil.getHoraActual()+"_DotacionAction dotar fallo- FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			saveErrors(request, erroneo);
		}
		request.setAttribute("erroneos", erroneos);
		request.setAttribute("repetidos", repetidos);
		request.setAttribute("mensaje", mensaje);
		loggerInfo.info(FechaUtil.getHoraActual()+"_DotacionAction dotar - FIN***"); 
		return mapping.findForward("exito");
		}catch(Exception e){
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo DotacionAction dotar", e);
			loggerError.error(FechaUtil.getHoraActual()+"_DotacionAction dotar - FIN***");
			ActionMessages errores2 = new ActionMessages();
			errores2.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"texto.etiqueta.error.log"));
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo DotacionAction dotar", e);
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			saveErrors(request, errores2);
			return mapping.findForward("cargar");
		}
	}
}
