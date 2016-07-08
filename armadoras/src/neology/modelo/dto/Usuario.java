package neology.modelo.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;

import neology.util.StringUtil;


public  class Usuario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6030325264079301147L;
	// Campos

	private long idUsuario;
	private long idPerfil;
	private Entidad entidad;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private DateTime fechaAlta;
	private String usuario;
	private String contrasena;
	private Integer estatus;
	private Integer numeroCaja;
	private String nombres;
	private Perfil perfil;
	private Set formatos = new HashSet(0);
	private Set ordenesImpresion = new HashSet(0);
	private Set formatosGrabacion = new HashSet(0);

	// Constructores

	
	


	/** constructor por default */
	public Usuario() {
		super();
	}

	/** constructor minimo */
	public Usuario(long idPerfil, Entidad entidad,
			String apellidoPaterno, String apellidoMaterno, String usuario,
			String contrasena) {
		super();
		this.idPerfil = idPerfil;
		this.entidad = entidad;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.usuario = usuario;
		this.contrasena = contrasena;
	}

	/** full constructor */
	public Usuario(long idPerfil, Entidad entidad, String nombre,
			String apellidoPaterno, String apellidoMaterno, DateTime fechaAlta,
			String usuario, String contrasena, Integer estatus, Integer numeroCaja,
			Set formatos, Set ordenesImpresions) {
		super();
		this.idPerfil = idPerfil;
		this.entidad = entidad;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.fechaAlta = fechaAlta;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.estatus = estatus;
		this.numeroCaja = numeroCaja;
		this.formatos = formatos;
		this.ordenesImpresion = ordenesImpresions;
	}

	// Metodos

	public long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public long getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
	}
	

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public DateTime getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(DateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}	

	public Integer getNumeroCaja() {
		return this.numeroCaja;
	}

	public void setNumeroCaja(Integer numeroCaja) {
		this.numeroCaja = numeroCaja;
	}

	public Set getFormatos() {
		return this.formatos;
	}

	public void setFormatos(Set formatos) {
		this.formatos = formatos;
	}

	public Set getOrdenesImpresion() {
		return this.ordenesImpresion;
	}

	public void setOrdenesImpresion(Set ordenesImpresion) {
		this.ordenesImpresion = ordenesImpresion;
	}

   

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}
	

	public Set getFormatosGrabacion() {
		return formatosGrabacion;
	}

	public void setFormatosGrabacion(Set formatosGrabacion) {
		this.formatosGrabacion = formatosGrabacion;
	}

	public String getNombres() {
		nombres = StringUtil.primeraMayuscula(nombre + " " + apellidoPaterno + " " + apellidoMaterno);
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
}
