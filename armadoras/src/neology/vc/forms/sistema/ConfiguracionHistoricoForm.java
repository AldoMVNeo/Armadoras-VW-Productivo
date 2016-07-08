package neology.vc.forms.sistema;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import neology.modelo.dto.ConfigJob;
import neology.vc.actions.sistema.DiaConfigResp;
import neology.vc.actions.sistema.NoDiasConfigResp;
import neology.vc.actions.sistema.NoDiasResp;
import neology.vc.actions.sistema.NoMesesResp;

public class ConfiguracionHistoricoForm extends  ActionForm{
	
	private static final long serialVersionUID = 6073787335477863627L;
	
	private Long noDias;
	private Long idUsuario;
	private String horaConfig;
	private String strFechaConfig;
	private String strEstatus;
	private String ejecutaJob;
	private String strFechaCalculo;
	private Integer intCantidadTotal;
	
	private List listaConfigs;
	
	public Long getNoDias() {
		return noDias;
	}
	public void setNoDias(Long noDias) {
		this.noDias = noDias;
	}
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);

		this.setNoDias(null);
	    this.setHoraConfig(null);
	    this.setIdUsuario(null);
	    this.setHoraConfig(null);
	    this.setStrFechaConfig(null);
	    this.setStrEstatus(null);
	    this.setEjecutaJob(null);
	    this.setStrFechaCalculo(null);
	    this.setIntCantidadTotal(null);
		
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getHoraConfig() {
		return horaConfig;
	}
	public void setHoraConfig(String horaConfig) {
		this.horaConfig = horaConfig;
	}
	public String getStrFechaConfig() {
		return strFechaConfig;
	}
	public void setStrFechaConfig(String strFechaConfig) {
		this.strFechaConfig = strFechaConfig;
	}
	public String getStrEstatus() {
		return strEstatus;
	}
	public void setStrEstatus(String strEstatus) {
		this.strEstatus = strEstatus;
	}
	public List getListaConfigs() {
		return listaConfigs;
	}
	public void setListaConfigs(List listaConfigs) {
		this.listaConfigs = listaConfigs;
	}
	public String getEjecutaJob() {
		return ejecutaJob;
	}
	public void setEjecutaJob(String ejecutaJob) {
		this.ejecutaJob = ejecutaJob;
	}
	public String getStrFechaCalculo() {
		return strFechaCalculo;
	}
	public void setStrFechaCalculo(String strFechaCalculo) {
		this.strFechaCalculo = strFechaCalculo;
	}
	public Integer getIntCantidadTotal() {
		return intCantidadTotal;
	}
	public void setIntCantidadTotal(Integer intCantidadTotal) {
		this.intCantidadTotal = intCantidadTotal;
	}
	
}
