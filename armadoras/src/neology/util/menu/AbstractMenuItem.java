package neology.util.menu;

import org.jdom.Element;

import neology.util.StringPrintWriter;

public abstract class AbstractMenuItem {
	private Long idMenu;

	private String titulo;

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
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public abstract void renderToXML(StringPrintWriter sb);

	public abstract void generarArchivoXMLArbol(Element element);
}
