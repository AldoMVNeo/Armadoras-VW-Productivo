package neology.modelo.dto;

import java.util.HashSet;
import java.util.Set;

public class Perfil implements java.io.Serializable {

	/**
	 * Perfil de Usuarios
	 */
	private static final long serialVersionUID = 4512471680057355670L;

	private Long idPerfil;

	private String nombrePerfil;

	private Set permisosMenus = new HashSet(0);

	private Set usuarios = new HashSet(0);
	
	private String estatus;
	
	public static Long HANDHELD =new Long(3);

	public Perfil() {

		// TODO Auto-generated constructor stub
	}

	public Perfil(Long idPerfil, String nombrePerfil, Set usuarios) {
		this.idPerfil = idPerfil;
		this.nombrePerfil = nombrePerfil;
		this.usuarios = usuarios;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Set getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set usuarios) {
		this.usuarios = usuarios;
	}

	public String getNombrePerfil() {
		return nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}

	public Set getPermisosMenus() {
		return permisosMenus;
	}

	public void setPermisosMenus(Set permisosMenus) {
		this.permisosMenus = permisosMenus;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
}
