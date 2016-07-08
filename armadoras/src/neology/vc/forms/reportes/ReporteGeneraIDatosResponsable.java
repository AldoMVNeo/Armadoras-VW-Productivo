package neology.vc.forms.reportes;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;

import neology.modelo.dto.Entidad;
import neology.modelo.dto.EstadoCatalogo;
import neology.modelo.dto.Perfil;

public class ReporteGeneraIDatosResponsable implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private long idUsuario;
	private long idPerfil;
	private Entidad entidad;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private DateTime fechaAlta;
	private String usuario;
	private String contrasena;
	private EstadoCatalogo estadoCatalogo;
	private Integer numeroCaja;
	private String nombres;
	private Perfil perfil;
	private Set formatos = new HashSet(0);
	private Set ordenesImpresion = new HashSet(0);
	private Set formatosGrabacion = new HashSet(0);
	private boolean usuarioHandheld;
	private String strNombreCompleto;
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public long getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
	}
	public Entidad getEntidad() {
		return entidad;
	}
	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public DateTime getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(DateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public EstadoCatalogo getEstadoCatalogo() {
		return estadoCatalogo;
	}
	public void setEstadoCatalogo(EstadoCatalogo estadoCatalogo) {
		this.estadoCatalogo = estadoCatalogo;
	}
	public Integer getNumeroCaja() {
		return numeroCaja;
	}
	public void setNumeroCaja(Integer numeroCaja) {
		this.numeroCaja = numeroCaja;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public Set getFormatos() {
		return formatos;
	}
	public void setFormatos(Set formatos) {
		this.formatos = formatos;
	}
	public Set getOrdenesImpresion() {
		return ordenesImpresion;
	}
	public void setOrdenesImpresion(Set ordenesImpresion) {
		this.ordenesImpresion = ordenesImpresion;
	}
	public Set getFormatosGrabacion() {
		return formatosGrabacion;
	}
	public void setFormatosGrabacion(Set formatosGrabacion) {
		this.formatosGrabacion = formatosGrabacion;
	}
	public boolean isUsuarioHandheld() {
		return usuarioHandheld;
	}
	public void setUsuarioHandheld(boolean usuarioHandheld) {
		this.usuarioHandheld = usuarioHandheld;
	}
	public String getStrNombreCompleto() {
		return strNombreCompleto;
	}
	public void setStrNombreCompleto(String strNombreCompleto) {
		this.strNombreCompleto = strNombreCompleto;
	}	

}
