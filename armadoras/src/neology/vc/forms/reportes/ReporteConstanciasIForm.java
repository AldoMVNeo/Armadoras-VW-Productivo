package neology.vc.forms.reportes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class ReporteConstanciasIForm extends ActionForm {
	private String fechaInicial;

	private String fechaFinal;

	private List estados;
	
	private List entidades;

	private String estado;

	private boolean cambiar;

	private String folioInicial;
	
	private String folioFinal;
	
	private Boolean isReporteFolios;

	private String isFullReport;
	
	private String estadoImportados;
	
	private String strResponsable;
	
	private String strEntidad;
	
	private List usuarios;
	
	private List usuariosView;
	
	private Boolean strOptionHidden1;
	
	private Boolean strOptionHidden2;
	
	private Boolean strOptionHidden3;

	private Boolean strOptionHidden4;
	
	private Boolean strOptionHidden5;
	
	private Boolean strOptionHidden6;
	
	private Boolean strColumnFolio;
	
	private Boolean strColumnFecha;
	
	private Boolean strColumnTID;
	
	private Boolean strColumnObservaciones;
	
	private Boolean strColumnEstadoActual;
	
	private Boolean strColumnTipo;
	
	private Boolean strColumnResponsable;
	
	private Boolean strColumnChasis;
	
	private Boolean strColumnEntidad;

	private Boolean strColumnEvento;
	
	private Boolean strColumnUltimo;
	
	private Boolean consultaBD;
	
	private String strOrdenColumnas;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		estado = "999";
		cambiar = false;		
		this.setStrOptionHidden1(null);
		this.setStrOptionHidden2(null);
		this.setStrOptionHidden3(null);
		this.setStrOptionHidden4(null);
		this.setStrOptionHidden5(null);
		this.setStrOptionHidden6(null);
		this.setStrColumnChasis(null);
		this.setStrColumnEntidad(null);
		this.setStrColumnEstadoActual(null);
		this.setStrColumnEvento(null);
	    this.setStrColumnFecha(null);
	    this.setStrColumnFolio(null);
	    this.setStrColumnObservaciones(null);
	    this.setStrColumnResponsable(null);
	    this.setStrColumnTID(null);
	    this.setStrColumnEntidad(null);
	    this.setStrColumnEvento(null);
	    this.setStrColumnTipo(null);
	    this.setConsultaBD(null);
	    this.setStrOrdenColumnas(null);
	    this.setStrColumnUltimo(null);
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errores = new ActionErrors();
//		if (cambiar) {
//			if (estado.equals("-1"))
//				errores.add("estado", new ActionMessage("errors.requerido",
//						"Estado Actual"));
//		}
		return errores;
	}

	// Metodos
	
	

	public boolean isCambiar() {
		return cambiar;
	}

	public void setCambiar(boolean cambiar) {
		this.cambiar = cambiar;
	}


	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public String getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public List getEstados() {
		return estados;
	}

	public void setEstados(List estados) {
		this.estados = estados;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List getEntidades() {
		return entidades;
	}

	public void setEntidades(List entidades) {
		this.entidades = entidades;
	}

	public String getFolioInicial() {
		return folioInicial;
	}

	public void setFolioInicial(String folioInicial) {
		this.folioInicial = folioInicial;
	}

	public String getFolioFinal() {
		return folioFinal;
	}

	public void setFolioFinal(String folioFinal) {
		this.folioFinal = folioFinal;
	}

	public Boolean getIsReporteFolios() {
		return isReporteFolios;
	}

	public void setIsReporteFolios(Boolean isReporteFolios) {
		this.isReporteFolios = isReporteFolios;
	}

	public String getIsFullReport() {
		return isFullReport;
	}

	public void setIsFullReport(String isFullReport) {
		this.isFullReport = isFullReport;
	}

	public String getEstadoImportados() {
		return estadoImportados;
	}

	public void setEstadoImportados(String estadoImportados) {
		this.estadoImportados = estadoImportados;
	}

	public String getStrResponsable() {
		return strResponsable;
	}

	public void setStrResponsable(String strResponsable) {
		this.strResponsable = strResponsable;
	}

	public List getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List usuarios) {
		this.usuarios = usuarios;
	}


	public Boolean getStrOptionHidden1() {
		return strOptionHidden1;
	}

	public void setStrOptionHidden1(Boolean strOptionHidden1) {
		this.strOptionHidden1 = strOptionHidden1;
	}

	public Boolean getStrOptionHidden2() {
		return strOptionHidden2;
	}

	public void setStrOptionHidden2(Boolean strOptionHidden2) {
		this.strOptionHidden2 = strOptionHidden2;
	}

	public Boolean getStrOptionHidden3() {
		return strOptionHidden3;
	}

	public void setStrOptionHidden3(Boolean strOptionHidden3) {
		this.strOptionHidden3 = strOptionHidden3;
	}

	public Boolean getStrOptionHidden4() {
		return strOptionHidden4;
	}

	public void setStrOptionHidden4(Boolean strOptionHidden4) {
		this.strOptionHidden4 = strOptionHidden4;
	}

	public Boolean getStrOptionHidden5() {
		return strOptionHidden5;
	}

	public void setStrOptionHidden5(Boolean strOptionHidden5) {
		this.strOptionHidden5 = strOptionHidden5;
	}

	public List getUsuariosView() {
		return usuariosView;
	}

	public void setUsuariosView(List usuariosView) {
		this.usuariosView = usuariosView;
	}

	public Boolean getStrOptionHidden6() {
		return strOptionHidden6;
	}

	public void setStrOptionHidden6(Boolean strOptionHidden6) {
		this.strOptionHidden6 = strOptionHidden6;
	}

	public String getStrEntidad() {
		return strEntidad;
	}

	public void setStrEntidad(String strEntidad) {
		this.strEntidad = strEntidad;
	}

	public Boolean getStrColumnFolio() {
		return strColumnFolio;
	}

	public void setStrColumnFolio(Boolean strColumnFolio) {
		this.strColumnFolio = strColumnFolio;
	}

	public Boolean getStrColumnFecha() {
		return strColumnFecha;
	}

	public void setStrColumnFecha(Boolean strColumnFecha) {
		this.strColumnFecha = strColumnFecha;
	}

	public Boolean getStrColumnTID() {
		return strColumnTID;
	}

	public void setStrColumnTID(Boolean strColumnTID) {
		this.strColumnTID = strColumnTID;
	}

	public Boolean getStrColumnObservaciones() {
		return strColumnObservaciones;
	}

	public void setStrColumnObservaciones(Boolean strColumnObservaciones) {
		this.strColumnObservaciones = strColumnObservaciones;
	}

	public Boolean getStrColumnEstadoActual() {
		return strColumnEstadoActual;
	}

	public void setStrColumnEstadoActual(Boolean strColumnEstadoActual) {
		this.strColumnEstadoActual = strColumnEstadoActual;
	}

	public Boolean getStrColumnTipo() {
		return strColumnTipo;
	}

	public void setStrColumnTipo(Boolean strColumnTipo) {
		this.strColumnTipo = strColumnTipo;
	}

	public Boolean getStrColumnResponsable() {
		return strColumnResponsable;
	}

	public void setStrColumnResponsable(Boolean strColumnResponsable) {
		this.strColumnResponsable = strColumnResponsable;
	}

	public Boolean getStrColumnChasis() {
		return strColumnChasis;
	}

	public void setStrColumnChasis(Boolean strColumnChasis) {
		this.strColumnChasis = strColumnChasis;
	}

	public Boolean getStrColumnEntidad() {
		return strColumnEntidad;
	}

	public void setStrColumnEntidad(Boolean strColumnEntidad) {
		this.strColumnEntidad = strColumnEntidad;
	}

	public Boolean getStrColumnEvento() {
		return strColumnEvento;
	}

	public void setStrColumnEvento(Boolean strColumnEvento) {
		this.strColumnEvento = strColumnEvento;
	}

	public Boolean getConsultaBD() {
		return consultaBD;
	}

	public void setConsultaBD(Boolean consultaBD) {
		this.consultaBD = consultaBD;
	}

	public String getStrOrdenColumnas() {
		return strOrdenColumnas;
	}

	public void setStrOrdenColumnas(String strOrdenColumnas) {
		this.strOrdenColumnas = strOrdenColumnas;
	}

	public Boolean getStrColumnUltimo() {
		return strColumnUltimo;
	}

	public void setStrColumnUltimo(Boolean strColumnUltimo) {
		this.strColumnUltimo = strColumnUltimo;
	}

	
	
}
