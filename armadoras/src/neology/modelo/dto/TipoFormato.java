package neology.modelo.dto;

import java.util.HashSet;
import java.util.Set;


public class TipoFormato implements java.io.Serializable {

	// Campos

	private long idTipoFormato;
	private String claveTipoFormato;
	private String descripcion;
	private String formatoFolio;
	private String estatus;
    private String descripcionConFormato;    
	private Set formatos = new HashSet(0);
	public static  final long REPUVE=1;
	// Constructores

	/** constructor por default */
	public TipoFormato() {
	}

	/** constructor completo*/
	public TipoFormato(String claveTipoFormato, String descripcion,
			String formatoFolio, String estatus, Set formatos) {
		this.claveTipoFormato = claveTipoFormato;
		this.descripcion = descripcion;
		this.formatoFolio = formatoFolio;
		this.estatus = estatus;
		this.formatos = formatos;
	}

	// Metodos

	public long getIdTipoFormato() {
		return this.idTipoFormato;
	}

	public void setIdTipoFormato(long idTipoFormato) {
		this.idTipoFormato = idTipoFormato;
	}

	public String getClaveTipoFormato() {
		return this.claveTipoFormato;
	}

	public void setClaveTipoFormato(String claveTipoFormato) {
		this.claveTipoFormato = claveTipoFormato;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFormatoFolio() {
		return this.formatoFolio;
	}

	public void setFormatoFolio(String formatoFolio) {
		this.formatoFolio = formatoFolio;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}


    public void setFormatos(Set formatos) {
        this.formatos = formatos;
    }
 
    public Set getFormatos() {
        return formatos;
    }   

	public String getDescripcionConFormato() {
		descripcionConFormato=descripcion+"      "+formatoFolio;
		return descripcionConFormato;
	}

	public void setDescripcionConFormato(String descripcionConFormato) {
		this.descripcionConFormato = descripcionConFormato;
	}
}
