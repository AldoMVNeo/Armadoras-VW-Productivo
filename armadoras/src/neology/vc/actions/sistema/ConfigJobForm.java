package neology.vc.actions.sistema;

import java.util.Date;

import neology.modelo.dto.Usuario;

public class ConfigJobForm {
	
	private Long idParametro;
	private Long noDias;
	private Long idUsuario;
	private String horaConfig;
	private String minutosConfig;
	private Date fechaConfig;
	private Long estatus;
	private Integer intCantidadTotal;
	
	public Long getIdParametro() {
		return idParametro;
	}
	public void setIdParametro(Long idParametro) {
		this.idParametro = idParametro;
	}
	public Long getNoDias() {
		return noDias;
	}
	public void setNoDias(Long noDias) {
		this.noDias = noDias;
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
	public String getMinutosConfig() {
		return minutosConfig;
	}
	public void setMinutosConfig(String minutosConfig) {
		this.minutosConfig = minutosConfig;
	}
	public Date getFechaConfig() {
		return fechaConfig;
	}
	public void setFechaConfig(Date fechaConfig) {
		this.fechaConfig = fechaConfig;
	}
	public Long getEstatus() {
		return estatus;
	}
	public void setEstatus(Long estatus) {
		this.estatus = estatus;
	}
	public Integer getIntCantidadTotal() {
		return intCantidadTotal;
	}
	public void setIntCantidadTotal(Integer intCantidadTotal) {
		this.intCantidadTotal = intCantidadTotal;
	}

}
