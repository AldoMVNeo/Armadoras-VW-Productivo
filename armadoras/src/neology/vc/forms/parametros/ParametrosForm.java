package neology.vc.forms.parametros;



import javax.servlet.http.HttpServletRequest;


import neology.util.Utilidades;
import neology.vc.actions.login.CerrarSesionAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;



public class ParametrosForm extends ActionForm {
    
   /**
	 * 
	 */
	private static final long serialVersionUID = -7816838696177828293L;
//Campos    
    private long idParametro;
    private String horarioOficina;
    private long horaInicialOficina;
    private long horaFinalOficina;
    private String dotarMaximoOficina;
    private long valorDotarMaximoOficina;
    private String dotarMaximoFueraOficina;
    private long valorDotarMaximoFueraOficina;
    private String transferirMaximoOficina;
    private long valorTransferirMaximoOficina;
    private String transferirMaximoFueraOficina;
    private long valorTransferirMaximoFueraOficina;
    private String cambiarMaximoOficina;
    private long valorCambiarMaximoOficina;
    private String cambiarMaximoFueraOficina;
    private long valorCambiarMaximoFueraOficina;
    private String tspMinimoBodega;
    private long valorTspMinimoBodega;
    private String tspMinimoPau;
    private long valorMinimoTspPau;
    private String reimpresionMaximoDias;
    private long valorMaximoReimpresionDias;
    private String reimpresionMaximoTsp;
    private long valorMaximoReimpresionTsp;
    private boolean cambiar;
    
    private static Logger log = Logger.getLogger(ParametrosForm.class);
    
    /**Reset all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     */
   
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset( mapping, request);
    }

    /**Validate all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return ActionErrors A list of all errors found.
     */
    
    public ActionErrors validate(ActionMapping mapping, 
                                 HttpServletRequest request) {
        ActionErrors errores = new ActionErrors();
        log.debug("REPUVE ***************************** ParametrosForm validate - INICIO");
        if (cambiar) {
            if(!Utilidades.isParametroValido(String.valueOf(horaInicialOficina)) && horaInicialOficina > 0)
                errores.add("parametros", new ActionMessage("errors.parametro.valido", "Hora Inicial"));
            if(!Utilidades.isParametroValido(String.valueOf(horaFinalOficina)))
                errores.add("parametros", new ActionMessage("errors.parametro.valido", "Hora Final"));
            if(!Utilidades.isParametroValido(String.valueOf(valorDotarMaximoOficina)))
                errores.add("parametros", new ActionMessage("errors.parametro.valido", "Número máximo de TsP a dotar en horas de oficina"));
            if(!Utilidades.isParametroValido(String.valueOf(valorDotarMaximoFueraOficina)))
                errores.add("parametros", new ActionMessage("errors.parametro.valido", "Número máximo de TsP a dotar fuera de horas de oficina"));
            if(!Utilidades.isParametroValido(String.valueOf(valorTransferirMaximoOficina)))
                errores.add("parametros", new ActionMessage("errors.parametro.valido", "Número máximo de TsP a cambiar estado en horas de oficina"));
            if(!Utilidades.isParametroValido(String.valueOf(valorTransferirMaximoFueraOficina)))
                errores.add("parametros", new ActionMessage("errors.parametro.valido", "Número máximo de TsP a transferir fuera de horas de oficina"));
            if(!Utilidades.isParametroValido(String.valueOf(valorCambiarMaximoOficina)))
                errores.add("parametros", new ActionMessage("errors.parametro.valido", "Número máximo de TsP a cambiar estado en horas de oficina"));
            if(!Utilidades.isParametroValido(String.valueOf(valorCambiarMaximoFueraOficina)))
                errores.add("parametros", new ActionMessage("errors.parametro.valido", "Número máximo de TsP a cambiar estado fuera de horas de oficina"));
            if(!Utilidades.isParametroValido(String.valueOf(valorTspMinimoBodega)))
                errores.add("parametros", new ActionMessage("errors.parametro.valido", "Número mínimo de TsP en bodega central"));
            if(!Utilidades.isParametroValido(String.valueOf(valorMinimoTspPau)))
                errores.add("parametros", new ActionMessage("errors.parametro.valido", "Número mínimo de TsP en PAU"));
            if(!Utilidades.isParametroValido(String.valueOf(valorMaximoReimpresionDias)))
                errores.add("parametros", new ActionMessage("errors.parametro.valido", "Número máximo de días para reimpresión (reproceso) de un trámite"));
            if(!Utilidades.isParametroValido(String.valueOf(valorMaximoReimpresionTsp)))
                errores.add("parametros", new ActionMessage("errors.parametro.valido", "Límite máximo de reimpresiones para el mismo trámite"));
        }
        log.debug("REPUVE ***************************** ParametrosForm validate - FIN");
        return errores;

    }

    public void setIdParametro(long idParametro) {
        this.idParametro = idParametro;
    }

    public long getIdParametro() {
        return idParametro;
    }

    public void setHorarioOficina(String horarioOficina) {
        this.horarioOficina = horarioOficina;
    }

    public String getHorarioOficina() {
        return horarioOficina;
    }

    public void setHoraInicialOficina(long horaInicialOficina) {
        this.horaInicialOficina = horaInicialOficina;
    }

    public long getHoraInicialOficina() {
        return horaInicialOficina;
    }

    public void setHoraFinalOficina(long horaFinalOficina) {
        this.horaFinalOficina = horaFinalOficina;
    }

    public long getHoraFinalOficina() {
        return horaFinalOficina;
    }

    public void setDotarMaximoOficina(String dotarMaximoOficina) {
        this.dotarMaximoOficina = dotarMaximoOficina;
    }

    public String getDotarMaximoOficina() {
        return dotarMaximoOficina;
    }

    public void setValorDotarMaximoOficina(long valorDotarMaximoOficina) {
        this.valorDotarMaximoOficina = valorDotarMaximoOficina;
    }

    public long getValorDotarMaximoOficina() {
        return valorDotarMaximoOficina;
    }

    public void setDotarMaximoFueraOficina(String dotarMaximoFueraOficina) {
        this.dotarMaximoFueraOficina = dotarMaximoFueraOficina;
    }

    public String getDotarMaximoFueraOficina() {
        return dotarMaximoFueraOficina;
    }

    public void setValorDotarMaximoFueraOficina(long valorDotarMaximoFueraOficina) {
        this.valorDotarMaximoFueraOficina = valorDotarMaximoFueraOficina;
    }

    public long getValorDotarMaximoFueraOficina() {
        return valorDotarMaximoFueraOficina;
    }

    public void setTransferirMaximoOficina(String transferirMaximoOficina) {
        this.transferirMaximoOficina = transferirMaximoOficina;
    }

    public String getTransferirMaximoOficina() {
        return transferirMaximoOficina;
    }

    public void setValorTransferirMaximoOficina(long valorTransferirMaximoOficina) {
        this.valorTransferirMaximoOficina = valorTransferirMaximoOficina;
    }

    public long getValorTransferirMaximoOficina() {
        return valorTransferirMaximoOficina;
    }

    public void setTransferirMaximoFueraOficina(String transferirMaximoFueraOficina) {
        this.transferirMaximoFueraOficina = transferirMaximoFueraOficina;
    }

    public String getTransferirMaximoFueraOficina() {
        return transferirMaximoFueraOficina;
    }

    public void setValorTransferirMaximoFueraOficina(long valorTransferirMaximoFueraOficina) {
        this.valorTransferirMaximoFueraOficina = valorTransferirMaximoFueraOficina;
    }

    public long getValorTransferirMaximoFueraOficina() {
        return valorTransferirMaximoFueraOficina;
    }

    public void setCambiarMaximoOficina(String cambiarMaximoOficina) {
        this.cambiarMaximoOficina = cambiarMaximoOficina;
    }

    public String getCambiarMaximoOficina() {
        return cambiarMaximoOficina;
    }

    public void setValorCambiarMaximoOficina(long valorCambiarMaximoOficina) {
        this.valorCambiarMaximoOficina = valorCambiarMaximoOficina;
    }

    public long getValorCambiarMaximoOficina() {
        return valorCambiarMaximoOficina;
    }

    public void setCambiarMaximoFueraOficina(String cambiarMaximoFueraOficina) {
        this.cambiarMaximoFueraOficina = cambiarMaximoFueraOficina;
    }

    public String getCambiarMaximoFueraOficina() {
        return cambiarMaximoFueraOficina;
    }

    public void setValorCambiarMaximoFueraOficina(long valorCambiarMaximoFueraOficina) {
        this.valorCambiarMaximoFueraOficina = valorCambiarMaximoFueraOficina;
    }

    public long getValorCambiarMaximoFueraOficina() {
        return valorCambiarMaximoFueraOficina;
    }

    public void setTspMinimoBodega(String tspMinimoBodega) {
        this.tspMinimoBodega = tspMinimoBodega;
    }

    public String getTspMinimoBodega() {
        return tspMinimoBodega;
    }

    public void setValorTspMinimoBodega(long valorTspMinimoBodega) {
        this.valorTspMinimoBodega = valorTspMinimoBodega;
    }

    public long getValorTspMinimoBodega() {
        return valorTspMinimoBodega;
    }

    public void setTspMinimoPau(String tspMinimoPau) {
        this.tspMinimoPau = tspMinimoPau;
    }

    public String getTspMinimoPau() {
        return tspMinimoPau;
    }

    public void setValorMinimoTspPau(long valorMinimoTspPau) {
        this.valorMinimoTspPau = valorMinimoTspPau;
    }

    public long getValorMinimoTspPau() {
        return valorMinimoTspPau;
    }

    public void setReimpresionMaximoDias(String reimpresionMaximoDias) {
        this.reimpresionMaximoDias = reimpresionMaximoDias;
    }

    public String getReimpresionMaximoDias() {
        return reimpresionMaximoDias;
    }

    public void setValorMaximoReimpresionDias(long valorMaximoReimpresionDias) {
        this.valorMaximoReimpresionDias = valorMaximoReimpresionDias;
    }

    public long getValorMaximoReimpresionDias() {
        return valorMaximoReimpresionDias;
    }

    public void setReimpresionMaximoTsp(String reimpresionMaximoTsp) {
        this.reimpresionMaximoTsp = reimpresionMaximoTsp;
    }

    public String getReimpresionMaximoTsp() {
        return reimpresionMaximoTsp;
    }

    public void setValorMaximoReimpresionTsp(long valorMaximoReimpresionTsp) {
        this.valorMaximoReimpresionTsp = valorMaximoReimpresionTsp;
    }

    public long getValorMaximoReimpresionTsp() {
        return valorMaximoReimpresionTsp;
    }

    public void setCambiar(boolean cambiar) {
        this.cambiar = cambiar;
    }

    public boolean isCambiar() {
        return cambiar;
    }

  
}


