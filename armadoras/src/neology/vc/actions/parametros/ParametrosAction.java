package neology.vc.actions.parametros;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neology.modelo.dto.Parametros;
import neology.modelo.dto.Usuario;
import neology.modelo.negocio.daos.ParametrosDAO;
import neology.modelo.negocio.daos.UsuarioDAO;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.vc.actions.login.LoginAction;
import neology.vc.forms.parametros.*;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;


public class ParametrosAction extends DispatchAction {
    /** 
     * Carga las entidades, tipos de formatos para realizar el cambio de estadode los formatos
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     */
    public ActionForward cargar(ActionMapping mapping, ActionForm form, 
                                HttpServletRequest request, 
                                HttpServletResponse response) throws IOException, 
                                                                     ServletException {
        
    	Logger loggerInfo = Utilidades.loggerInfo();
    	
		loggerInfo.info(FechaUtil.getHoraActual()+"_ParametrosAction cargar - INICIO***");
        ParametrosForm formulario = (ParametrosForm)form;    
       
        ParametrosDAO parametrosDAO = DAOFactory.crearParametrosDAO();
        Parametros parametros = parametrosDAO.buscarPorId(new Long(1));
        formulario.setHorarioOficina(parametros.getHorarioOficina());
        formulario.setHoraInicialOficina(parametros.getHoraInicialOficina());
        formulario.setHoraFinalOficina(parametros.getHoraFinalOficina());
        formulario.setDotarMaximoOficina(parametros.getDotarMaximoOficina());
        formulario.setValorDotarMaximoOficina(parametros.getValorDotarMaximoOficina());
        formulario.setDotarMaximoFueraOficina(parametros.getDotarMaximoFueraOficina());
        formulario.setValorDotarMaximoFueraOficina(parametros.getValorDotarMaximoFueraOficina());
        formulario.setTransferirMaximoOficina(parametros.getTransferirMaximoOficina());
        formulario.setValorTransferirMaximoOficina(parametros.getValorTransferirMaximoOficina());
        formulario.setTransferirMaximoFueraOficina(parametros.getTransferirMaximoFueraOficina());
        formulario.setValorTransferirMaximoFueraOficina(parametros.getValorTransferirMaximoFueraOficina());
        formulario.setCambiarMaximoOficina(parametros.getCambiarMaximoOficina());
        formulario.setValorCambiarMaximoOficina(parametros.getValorCambiarMaximoOficina());
        formulario.setCambiarMaximoFueraOficina(parametros.getCambiarMaximoFueraOficina());
        formulario.setValorCambiarMaximoFueraOficina(parametros.getValorCambiarMaximoFueraOficina());
        formulario.setTspMinimoBodega(parametros.getTspMinimoBodega());
        formulario.setValorTspMinimoBodega(parametros.getValorTspMinimoBodega());
        formulario.setTspMinimoPau(parametros.getTspMinimoPau());
        formulario.setValorMinimoTspPau(parametros.getValorMinimoTspPau());
        formulario.setReimpresionMaximoDias(parametros.getReimpresionMaximoDias());
        formulario.setValorMaximoReimpresionDias(parametros.getValorMaximoReimpresionDias());
        formulario.setReimpresionMaximoTsp(parametros.getReimpresionMaximoTsp());
        formulario.setValorMaximoReimpresionTsp(parametros.getValorMaximoReimpresionTsp());
        loggerInfo.info(FechaUtil.getHoraActual()+"_ParametrosAction cargar - FIN***");
        loggerInfo.removeAllAppenders();
        return mapping.findForward("cargar");
    }
        
    public ActionForward cambiar(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response) throws IOException, 
                                                                      ServletException {
    	
    	Logger loggerInfo = Utilidades.loggerInfo();
    	Logger loggerError = Utilidades.loggerError();
    	loggerInfo.info(FechaUtil.getHoraActual()+"_ParametrosAction cambiar - INICIO***");
        //Datos iniciales
        ParametrosForm formulario = (ParametrosForm)form;        
        long horaInicialOficina = formulario.getHoraInicialOficina();
        long horaFinalOficina = formulario.getHoraFinalOficina();        
        long valorDotarMaximoOficina = formulario.getValorDotarMaximoOficina();
        long valorDotarMaximoFueraOficina = formulario.getValorDotarMaximoFueraOficina();
        long valorTransferirMaximoOficina = formulario.getValorTransferirMaximoOficina();
        long valorTransferirMaximoFueraOficina = formulario.getValorTransferirMaximoFueraOficina();
        long valorCambiarMaximoOficina = formulario.getValorCambiarMaximoOficina();
        long valorCambiarMaximoFueraOficina = formulario.getValorCambiarMaximoFueraOficina();
        long valorTspMinimoBodega = formulario.getValorTspMinimoBodega();
        long valorMinimoTspPau = formulario.getValorMinimoTspPau();
        long valorMaximoReimpresionDias = formulario.getValorMaximoReimpresionDias();
        long valorMaximoReimpresionTsp = formulario.getValorMaximoReimpresionTsp();

        try {
            //Se obtienen los Daos correspondientes
            ParametrosDAO parametrosDAO = DAOFactory.crearParametrosDAO();            

            //Se obtiene el usuario de sesion
            Usuario usuario = (Usuario)request.getSession().getAttribute("usuarioLog");            
            //Se actualizan los parametros            
            Parametros parametros = parametrosDAO.buscarPorId(new Long(1));
            if (parametros != null) {            
                parametros.setHoraInicialOficina(horaInicialOficina);
                parametros.setHoraFinalOficina(horaFinalOficina);
                parametros.setValorDotarMaximoOficina(valorDotarMaximoOficina);
                parametros.setValorDotarMaximoFueraOficina(valorDotarMaximoFueraOficina);
                parametros.setValorTransferirMaximoOficina(valorTransferirMaximoOficina);
                parametros.setValorTransferirMaximoFueraOficina(valorTransferirMaximoFueraOficina);
                parametros.setValorCambiarMaximoOficina(valorCambiarMaximoOficina);
                parametros.setValorCambiarMaximoFueraOficina(valorCambiarMaximoFueraOficina);
                parametros.setValorTspMinimoBodega(valorTspMinimoBodega);
                parametros.setValorMinimoTspPau(valorMinimoTspPau);
                parametros.setValorMaximoReimpresionDias(valorMaximoReimpresionDias);
                parametros.setValorMaximoReimpresionTsp(valorMaximoReimpresionTsp);
                parametrosDAO.guardar(parametros);
            }            
        }catch (Exception e) {
            e.printStackTrace(); 
            ActionMessages mensaje = new ActionMessages();        
            mensaje.add("mensaje", new ActionMessage("texto.etiqueta.error.log"));    
            loggerError.error(FechaUtil.getHoraActual()+"_ParametrosAction cambiar Fallo", e);
			loggerError.error(FechaUtil.getHoraActual()+"_ParametrosAction cambiar - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			request.setAttribute("mensaje", mensaje);    
			return mapping.findForward("cargar");
        }
        //Se generan los mensajes correspondientes
        ActionMessages mensaje = new ActionMessages();        
        mensaje.add("mensaje", new ActionMessage("mensaje.parametros.exitoso"));                
        request.setAttribute("mensaje", mensaje);    
        loggerInfo.info(FechaUtil.getHoraActual()+"_ParametrosAction cambiar - FIN***");
        loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
        return mapping.findForward("cargar");
    }    
}
