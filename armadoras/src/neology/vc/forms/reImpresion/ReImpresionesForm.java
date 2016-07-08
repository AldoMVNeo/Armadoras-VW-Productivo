package neology.vc.forms.reImpresion;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import neology.modelo.dto.OrdenImpresion;
import neology.modelo.dto.impresion.Seleccionados;
import neology.modelo.dto.reImpresion.Paquetes;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class ReImpresionesForm extends ActionForm {
    private String rutaReporteEntrada;
    private String nombreReporteSalida;
    private Map parametros = new HashMap();
    private String tipoReporte;
    private List datos= new ArrayList();
    private String impresiones[];
    private String vin;
    private Seleccionados seleccionados;
    private boolean activo;
    private OrdenImpresion orden;
    private String cadena;
    private String propietario;
    private String fechaRegistro;
    private String de;
    private String hasta;
    private Integer totalFormatos;
    private Long numImpresion;
    private boolean cambiar;
    private Paquetes paquetes;
    private List bloques;
    private Long numeroTramite;
    private Integer totalRegistros;
    private Integer totalVins;

    

	public Integer getTotalVins()
	{
		return totalVins;
	}

	public void setTotalVins(Integer totalVins)
	{
		this.totalVins = totalVins;
	}

	public List getBloques()
	{
		return bloques;
	}

	public void setBloques(List bloques)
	{
		this.bloques = bloques;
	}

	public Long getNumImpresion() {
		return numImpresion;
	}

	public void setNumImpresion(Long numImpresion) {
		this.numImpresion = numImpresion;
	}

	public Integer getTotalFormatos() {
		return totalFormatos;
	}

	public void setTotalFormatos(Integer totalFormatos) {
		this.totalFormatos = totalFormatos;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public OrdenImpresion getOrden() {
		return orden;
	}

	public void setOrden(OrdenImpresion orden) {
		this.orden = orden;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	/**Reset all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        de = "0";
        hasta = "0";
        impresiones = null;
        vin="";
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
	

	public Seleccionados getSeleccionados() {
		return seleccionados;
	}

	public void setSeleccionados(Seleccionados seleccionados) {
		this.seleccionados = seleccionados;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getHasta() {
		return hasta;
	}

	public void setHasta(String hasta) {
		this.hasta = hasta;
	}

	public String[] getImpresiones() {
		return impresiones;
	}

	public void setImpresiones(String[] impresiones) {
		this.impresiones = impresiones;
	}

	public Paquetes getPaquetes()
	{
		return paquetes;
	}

	public void setPaquetes(Paquetes paquetes)
	{
		this.paquetes = paquetes;
	}

	public Long getNumeroTramite()
	{
		return numeroTramite;
	}

	public void setNumeroTramite(Long numeroTramite)
	{
		this.numeroTramite = numeroTramite;
	}

	public Integer getTotalRegistros()
	{
		return totalRegistros;
	}

	public void setTotalRegistros(Integer totalRegistros)
	{
		this.totalRegistros = totalRegistros;
	}

	

	
}
