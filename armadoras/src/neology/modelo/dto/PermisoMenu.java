package neology.modelo.dto;

public class PermisoMenu implements java.io.Serializable,Comparable<PermisoMenu>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -19347172977031691L;
private Long idPermiso;
private Perfil perfil;
private Menu menu;
private boolean activo;

public PermisoMenu() {
	super();
	// TODO Auto-generated constructor stub
}
public PermisoMenu(Long idPermiso, Perfil perfil, Menu menu) {
	super();
	this.idPermiso = idPermiso;
	this.perfil = perfil;
	this.menu = menu;
}
public Long getIdPermiso() {
	return idPermiso;
}
public void setIdPermiso(Long idPermiso) {
	this.idPermiso = idPermiso;
}
public Menu getMenu() {
	return menu;
}
public void setMenu(Menu menu) {
	this.menu = menu;
}
public Perfil getPerfil() {
	return perfil;
}
public void setPerfil(Perfil perfil) {
	this.perfil = perfil;
}
public int compareTo(PermisoMenu o) {
	return getMenu().getIdMenu().compareTo(o.getMenu().getIdMenu());
}
public boolean isActivo() {
	return activo;
}

public void setActivo(boolean activo) {
	this.activo = activo;
	if(getMenu()!=null)
		getMenu().setActivo(activo);
}




}
