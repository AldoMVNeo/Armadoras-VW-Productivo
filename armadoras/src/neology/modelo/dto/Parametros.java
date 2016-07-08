 package neology.modelo.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;


public class Parametros implements java.io.Serializable {
    // Campos
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
    
    /** constructor por default */
    public Parametros() {
    }
    
    /** constructor minimo */
    public Parametros(long idParametro){
        this.idParametro=idParametro;
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
}
