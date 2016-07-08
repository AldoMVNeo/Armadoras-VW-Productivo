package neology.modelo.dto;

import org.joda.time.DateTime;

public class FormatoHistorico implements java.io.Serializable {

	/**
	 * Historico de Formatos
	 */
	private static final long serialVersionUID = 69710136944254112L;

	private Long idFormatoHistorico;

	private String folio;

	private Integer idTipoFormato;

	private Long idEntidad;

	private Long idUsuarioActual;

	private Long idUsuarioModifico;

	private String estado;

	private DateTime fechaRegistro;

	private String observaciones;

	private Long idOrdenImpresion;
	
	private DateTime fechaHistorico;
	
	private String descripcionEstado;
	
	private String nombreUsuario;
	
	private String niv;
	
	private String numeroChip;

	
	
	
	// Metodos
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public DateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(DateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public Long getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(Long idEntidad) {
		this.idEntidad = idEntidad;
	}
	
	public Long getIdFormatoHistorico() {
		return idFormatoHistorico;
	}

	public void setIdFormatoHistorico(Long idFormatoHistorico) {
		this.idFormatoHistorico = idFormatoHistorico;
	}

	public Long getIdOrdenImpresion() {
		return idOrdenImpresion;
	}

	public void setIdOrdenImpresion(Long idOrdenImpresion) {
		this.idOrdenImpresion = idOrdenImpresion;
	}

	public Integer getIdTipoFormato() {
		return idTipoFormato;
	}

	public void setIdTipoFormato(Integer idTipoFormato) {
		this.idTipoFormato = idTipoFormato;
	}

	public Long getIdUsuarioActual() {
		return idUsuarioActual;
	}

	public void setIdUsuarioActual(Long idUsuarioActual) {
		this.idUsuarioActual = idUsuarioActual;
	}

	public Long getIdUsuarioModifico() {
		return idUsuarioModifico;
	}

	public void setIdUsuarioModifico(Long idUsuarioModifico) {
		this.idUsuarioModifico = idUsuarioModifico;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public DateTime getFechaHistorico() {
		return fechaHistorico;
	}

	public void setFechaHistorico(DateTime fechaHistorico) {
		this.fechaHistorico = fechaHistorico;
	}
	
	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getNiv() {
		return niv;
	}

	public void setNiv(String niv) {
		this.niv = niv;
	}

	public String getNumeroChip() {
		return numeroChip;
	}

	public void setNumeroChip(String numeroChip) {
		this.numeroChip = numeroChip;
	}



}
