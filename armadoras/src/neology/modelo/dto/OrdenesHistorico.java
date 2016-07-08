package neology.modelo.dto;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;

public class OrdenesHistorico implements java.io.Serializable {
	
	private static final long serialVersionUID = 5846657371988149685L;
	
	private long idOrdenesHistorico;
	private Usuario idUsuarioModifico;
	private Usuario idUsuario;
	private long idOrdenImpresion;
	private Long numeroTramite;
	private String vin;
	private String placa;
	private String barco;
	private String idPropietario;
	private String marca;
	private String modelo;
	private Integer annoModelo;
	private char tipo;
	private String uso;
	private String color;
	private String numeroMotor;
	private Integer cilindraje;
	private String combustible;
	private String capacidad;
	private String entidad;
	private DateTime fechaRegistro;
	private String folio;
	private String numeroChip;
	private EstadoOI estado;
	private Long idEntidad;
	private TipoFormato tipoFormato;
	private Integer numeroGrabaciones;
	private Set formatos = new HashSet(0);
	private String imprime;
	private DateTime fechaImpresion;
	private Integer grupo;
	
	public long getIdOrdenesHistorico() {
		return idOrdenesHistorico;
	}
	public void setIdOrdenesHistorico(long idOrdenesHistorico) {
		this.idOrdenesHistorico = idOrdenesHistorico;
	}
	
	public Usuario getIdUsuarioModifico() {
		return idUsuarioModifico;
	}
	public void setIdUsuarioModifico(Usuario idUsuarioModifico) {
		this.idUsuarioModifico = idUsuarioModifico;
	}
	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getNumeroTramite() {
		return numeroTramite;
	}
	public void setNumeroTramite(Long numeroTramite) {
		this.numeroTramite = numeroTramite;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getBarco() {
		return barco;
	}
	public void setBarco(String barco) {
		this.barco = barco;
	}
	public String getIdPropietario() {
		return idPropietario;
	}
	public void setIdPropietario(String idPropietario) {
		this.idPropietario = idPropietario;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Integer getAnnoModelo() {
		return annoModelo;
	}
	public void setAnnoModelo(Integer annoModelo) {
		this.annoModelo = annoModelo;
	}
	public char getTipo() {
		return tipo;
	}
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	public String getUso() {
		return uso;
	}
	public void setUso(String uso) {
		this.uso = uso;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getNumeroMotor() {
		return numeroMotor;
	}
	public void setNumeroMotor(String numeroMotor) {
		this.numeroMotor = numeroMotor;
	}
	public Integer getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
	}
	public String getCombustible() {
		return combustible;
	}
	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}
	public String getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
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
	public String getNumeroChip() {
		return numeroChip;
	}
	public void setNumeroChip(String numeroChip) {
		this.numeroChip = numeroChip;
	}
	
	public EstadoOI getEstado() {
		return estado;
	}
	public void setEstado(EstadoOI estado) {
		this.estado = estado;
	}
	public Long getIdEntidad() {
		return idEntidad;
	}
	public void setIdEntidad(Long idEntidad) {
		this.idEntidad = idEntidad;
	}
	public TipoFormato getTipoFormato() {
		return tipoFormato;
	}
	public void setTipoFormato(TipoFormato tipoFormato) {
		this.tipoFormato = tipoFormato;
	}
	public Integer getNumeroGrabaciones() {
		return numeroGrabaciones;
	}
	public void setNumeroGrabaciones(Integer numeroGrabaciones) {
		this.numeroGrabaciones = numeroGrabaciones;
	}
	public Set getFormatos() {
		return formatos;
	}
	public void setFormatos(Set formatos) {
		this.formatos = formatos;
	}
	public String getImprime() {
		return imprime;
	}
	public void setImprime(String imprime) {
		this.imprime = imprime;
	}
	public DateTime getFechaImpresion() {
		return fechaImpresion;
	}
	public void setFechaImpresion(DateTime fechaImpresion) {
		this.fechaImpresion = fechaImpresion;
	}
	public Integer getGrupo() {
		return grupo;
	}
	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}
	public long getIdOrdenImpresion() {
		return idOrdenImpresion;
	}
	public void setIdOrdenImpresion(long idOrdenImpresion) {
		this.idOrdenImpresion = idOrdenImpresion;
	}
	public Usuario getIdUsuario() {
		return idUsuario;
	}
	
	
}
