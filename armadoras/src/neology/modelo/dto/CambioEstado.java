package neology.modelo.dto;




public class CambioEstado implements java.io.Serializable
{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Campos
	private Long idCambioEstado;
	private String de;
	private String a;
	

	/** constructor por default */
	public CambioEstado()
	{}

	

	/** constructor completo */
	public CambioEstado(String de, String a)
	{
		this.de = de;
		this.a = a;
	}



	public String getA()
	{
		return a;
	}



	public void setA(String a)
	{
		this.a = a;
	}



	public String getDe()
	{
		return de;
	}



	public void setDe(String de)
	{
		this.de = de;
	}



	public Long getIdCambioEstado() {
		return idCambioEstado;
	}



	public void setIdCambioEstado(Long idCambioEstado) {
		this.idCambioEstado = idCambioEstado;
	}

	
}