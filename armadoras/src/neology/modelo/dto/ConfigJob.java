package neology.modelo.dto;

import java.util.Date;

public class ConfigJob implements java.io.Serializable {
	
	private Long idParametro;
	private Long noDias;
	private Usuario usuario;
	private String horaConfig;
	private String minutosConfig;
	private Date fechaConfig;
	private Long estatus;
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
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

}
