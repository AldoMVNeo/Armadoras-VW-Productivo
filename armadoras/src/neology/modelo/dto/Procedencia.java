 package neology.modelo.dto;


public class Procedencia implements java.io.Serializable {
    // Campos
    private long idProcedencia;
    private String descripcion;
    
    /** constructor por default */
    public Procedencia() {
    }
    
    /** constructor minimo */
    public Procedencia(long idProcedencia){
        this.idProcedencia=idProcedencia;
    }
    
    /** constructor completo */
    public Procedencia(long idProcedencia, String descripcion){
        this.idProcedencia=idProcedencia;
        this.descripcion=descripcion; 
    }
        
   

	public String getDescripcion()
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	public long getIdProcedencia()
	{
		return idProcedencia;
	}

	public void setIdProcedencia(long idProcedencia)
	{
		this.idProcedencia = idProcedencia;
	}

   













}
