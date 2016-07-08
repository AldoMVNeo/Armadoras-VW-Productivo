package neology.vc.forms.emision.impresion;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ImpresionForm extends ActionForm {
    private String rutaReporteEntrada;
    private String nombreReporteSalida;
    private Map parametros = new HashMap();
    private String tipoReporte;
    private List datos= new ArrayList();

    /**Reset all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
    }

    /**Validate all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return ActionErrors A list of all errors found.
     */
    public ActionErrors validate(ActionMapping mapping, 
                                 HttpServletRequest request) {
        return super.validate(mapping, request);
    }  

    public void setParametros(Map parametros) {
        this.parametros = parametros;
    }

    public Map getParametros() {
        return parametros;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setRutaReporteEntrada(String rutaReporteEntrada) {
        this.rutaReporteEntrada = rutaReporteEntrada;
    }

    public String getRutaReporteEntrada() {
        return rutaReporteEntrada;
    }


    public void setNombreReporteSalida(String nombreReporteSalida) {
        this.nombreReporteSalida = nombreReporteSalida;
    }

    public String getNombreReporteSalida() {
        return nombreReporteSalida;
    }

    public void setDatos(List datos) {
        this.datos = datos;
    }

    public List getDatos() {
        return datos;
    }
}
