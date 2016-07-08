package neology.modelo.dto;



import java.io.Serializable;

/**
 * This is an object that contains data related to the Menu table. Do not modify
 * this class because it will be overwritten if the configuration file related
 * to this class is modified.
 * 
 * @hibernate.class table="Menu"
 */

public  class Menu implements Serializable {	

	// Constructores
	
	public Menu() {
		super();	
	}
	/**
	 * Constructor for Llave primaria
	 */
	public Menu(Long idMenu) {
		this.setIdMenu(idMenu);	
	}

	
	private int hashCode = Integer.MIN_VALUE;

	// Llave primaria
	private Long idMenu;

	// Campos
	private Long idPadre;

	private String nombreMenu;

	private String url;

	private String icono;

	private boolean activo;

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
		hashCode = Integer.MIN_VALUE;
	}

	public Long getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(Long idPadre) {
		this.idPadre = idPadre;
	}

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof Menu))
			return false;
		else {
			Menu menu = (Menu) obj;
			if (null == this.getIdMenu() || null == menu.getIdMenu())
				return false;
			else
				return (this.getIdMenu().equals(menu.getIdMenu()));
		}
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getIdMenu())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getIdMenu().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	public String getNombreMenu() {
		return nombreMenu;
	}

	public void setNombreMenu(String nombreMenu) {
		this.nombreMenu = nombreMenu;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	

}