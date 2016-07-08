package neology.modelo.dto;

import java.util.HashSet;
import java.util.Set;

public class Estado implements java.io.Serializable
{
	public static final String CANCELADO= "6";	
	public static final String DISPONIBLE = "D";
	public static final String DOTADO = "0";
	public static final String GRABADO = "2";
	public static final String IMPRESO = "1";
	public static final String REVISADO = "8";
	public static final String ELIMINADO= "5";
	public static final String INFORMADOCANCELADO= "A";
	private static final long serialVersionUID = 1L;

	// Campos
	private String descripcion;
	private String estado;
	private Set formatos = new HashSet(0);

	/** constructor por default */
	public Estado()
	{}

	/** constructor minimo */
	public Estado(String descripcion)
	{
		this.descripcion = descripcion;
	}

	/** constructor completo */
	public Estado(String descripcion, Set formatos)
	{
		this.descripcion = descripcion;
		this.formatos = formatos;
	}

	// Metodos

	public String getDescripcion()
	{
		return this.descripcion;
	}

	public String getEstado()
	{
		return this.estado;
	}

	public Set getFormatos()
	{
		return this.formatos;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	public void setEstado(String estado)
	{
		this.estado = estado;
	}

	public void setFormatos(Set formatos)
	{
		this.formatos = formatos;
	}
	
	public String toString(){
		return estado;
	}

}