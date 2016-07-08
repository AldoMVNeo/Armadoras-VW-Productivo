package neology.modelo.dto;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;

public class OrdenImpresion implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5846657371988149685L;
	// Campos

	private long idOrdenImpresion;
	private Usuario usuario;
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
	// Constructores

	
	public Set getFormatos()
	{
		return formatos;
	}

	public void setFormatos(Set formatos)
	{
		this.formatos = formatos;
	}

	/** constructor default */
	public OrdenImpresion()
	{}

	/** constructor minimo */
	public OrdenImpresion(String capacidad, String folio, String numeroChip)
	{
		this.capacidad = capacidad;
		this.folio = folio;
		this.numeroChip = numeroChip;
	}

	/** constructor para SubirHandheldAction */
	public OrdenImpresion(long idOrdenImpresion, String vin, String folio,
			EstadoOI estado, DateTime fechaRegistro, Usuario usuario)
	{
		this.idOrdenImpresion = idOrdenImpresion;
		this.usuario = usuario;
		this.vin = vin;
		this.fechaRegistro = fechaRegistro;
		this.folio = folio;
		this.estado = estado;
	}

	/** constructor completo */
	public OrdenImpresion(Usuario usuario, long numeroTramite, String vin, String placa,
			String barco, String idPropietario, String marca, String modelo,
			Integer annoModelo, char tipo, String uso, String color, String numeroMotor,
			Integer cilindraje, String combustible, String capacidad, String entidad,
			DateTime fechaRegistro, String folio, String numeroChip, EstadoOI estado,
			Long idEntidad)
	{
		this.usuario = usuario;
		this.numeroTramite = numeroTramite;
		this.vin = vin;
		this.placa = placa;
		this.barco = barco;
		this.marca = marca;
		this.idPropietario = idPropietario;
		this.modelo = modelo;
		this.annoModelo = annoModelo;
		this.tipo = tipo;
		this.uso = uso;
		this.color = color;
		this.numeroMotor = numeroMotor;
		this.cilindraje = cilindraje;
		this.combustible = combustible;
		this.capacidad = capacidad;
		this.entidad = entidad;
		this.fechaRegistro = fechaRegistro;
		this.folio = folio;
		this.numeroChip = numeroChip;
		this.estado = estado;
		this.idEntidad = idEntidad;
	}

	// Metodos

	public void setIdOrdenImpresion(long idOrdenImpresion)
	{
		this.idOrdenImpresion = idOrdenImpresion;
	}

	public long getIdOrdenImpresion()
	{
		return idOrdenImpresion;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setNumeroTramite(Long numeroTramite)
	{
		this.numeroTramite = numeroTramite;
	}

	public Long getNumeroTramite()
	{
		return numeroTramite;
	}

	public void setVin(String vin)
	{
		this.vin = vin;
	}

	public String getVin()
	{
		return vin;
	}

	public void setPlaca(String placa)
	{
		this.placa = placa;
	}

	public String getPlaca()
	{
		return placa;
	}

	public void setBarco(String barco)
	{
		this.barco = barco;
	}

	public String getBarco()
	{
		return barco;
	}

	public void setIdPropietario(String idPropietario)
	{
		this.idPropietario = idPropietario;
	}

	public String getIdPropietario()
	{
		return idPropietario;
	}

	public void setMarca(String marca)
	{
		this.marca = marca;
	}

	public String getMarca()
	{
		return marca;
	}

	public void setModelo(String modelo)
	{
		this.modelo = modelo;
	}

	public String getModelo()
	{
		return modelo;
	}

	public void setTipo(char tipo)
	{
		this.tipo = tipo;
	}

	public char getTipo()
	{
		return tipo;
	}

	public void setUso(String uso)
	{
		this.uso = uso;
	}

	public String getUso()
	{
		return uso;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public String getColor()
	{
		return color;
	}

	public void setNumeroMotor(String numeroMotor)
	{
		this.numeroMotor = numeroMotor;
	}

	public String getNumeroMotor()
	{
		return numeroMotor;
	}

	public void setCilindraje(Integer cilindraje)
	{
		this.cilindraje = cilindraje;
	}

	public Integer getCilindraje()
	{
		return cilindraje;
	}

	public void setCombustible(String combustible)
	{
		this.combustible = combustible;
	}

	public String getCombustible()
	{
		return combustible;
	}

	public void setCapacidad(String capacidad)
	{
		this.capacidad = capacidad;
	}

	public String getCapacidad()
	{
		return capacidad;
	}

	public void setEntidad(String entidad)
	{
		this.entidad = entidad;
	}

	public String getEntidad()
	{
		return entidad;
	}

	public void setFechaRegistro(DateTime fechaRegistro)
	{
		this.fechaRegistro = fechaRegistro;
	}

	public DateTime getFechaRegistro()
	{
		return fechaRegistro;
	}

	public void setFolio(String folio)
	{
		this.folio = folio;
	}

	public String getFolio()
	{
		return folio;
	}

	public void setNumeroChip(String numeroChip)
	{
		this.numeroChip = numeroChip;
	}

	public String getNumeroChip()
	{
		return numeroChip;
	}

	public void setEstado(EstadoOI estado)
	{
		this.estado = estado;
	}

	public EstadoOI getEstado()
	{
		return estado;
	}

	public void setIdEntidad(Long idEntidad)
	{
		this.idEntidad = idEntidad;
	}

	public Long getIdEntidad()
	{
		return idEntidad;
	}

	public void setTipoFormato(TipoFormato tipoFormato)
	{
		this.tipoFormato = tipoFormato;
	}

	public TipoFormato getTipoFormato()
	{
		return tipoFormato;
	}

	public void setAnnoModelo(Integer annoModelo)
	{
		this.annoModelo = annoModelo;
	}

	public Integer getAnnoModelo()
	{
		return annoModelo;
	}

	public Integer getNumeroGrabaciones()
	{
		return numeroGrabaciones;
	}

	public void setNumeroGrabaciones(Integer numeroGrabaciones)
	{
		this.numeroGrabaciones = numeroGrabaciones;
	}

	public String getImprime()
	{
		return imprime;
	}

	public void setImprime(String imprime)
	{
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

}
