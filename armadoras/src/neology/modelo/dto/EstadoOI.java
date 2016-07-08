package neology.modelo.dto;

import java.util.HashSet;
import java.util.Set;

public class EstadoOI implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4890348117728875809L;

	// Campos
	private String estado;

	private String descripcion;

	private Set ordenes = new HashSet(0);
	public static final String CARGADO= "0";

	public static final String APARTADO = "A";

	public static final String IMPRESO = "1";
	
	public static final String REGRABADO ="4";
	
	public static final String CANCELADO ="6";
	
	public static final String GRABADO= "2";
	
	public static final String INFORMADO_VERIFICADO= "9";
	
	public static final String REVISADO= "8";

	// Constructores
	public EstadoOI() {
	}

	// Metodos
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setOrdenes(Set ordenes) {
		this.ordenes = ordenes;
	}

	public Set getOrdenes() {
		return ordenes;
	}
}
