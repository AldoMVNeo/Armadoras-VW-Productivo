package neology.modelo.dto;

import java.util.HashSet;
import java.util.Set;


public  class EstadoCatalogo implements java.io.Serializable {

	// Campos

	private Integer estado;
	private String descripcion;
	
    public static final Integer ACTIVO=0;
    public static final Integer INACTIVO=1;
    public static final Integer ELIMINADO=2;
    
	// Constructores

	/** constructor por default*/
	public EstadoCatalogo() {
	}

	/** constructor minimo */
	public EstadoCatalogo(String descripcion) {
		this.descripcion = descripcion;
	}

	/** constructor completo */
	
	// Metodos

	

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public static Integer getACTIVO() {
		return ACTIVO;
	}

	

	public static Integer getELIMINADO() {
		return ELIMINADO;
	}


	public static Integer getINACTIVO() {
		return INACTIVO;
	}

	

	
}
