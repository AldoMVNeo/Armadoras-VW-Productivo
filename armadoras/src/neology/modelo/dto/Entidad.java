package neology.modelo.dto;

public  class Entidad implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6348895701953678823L;
	// Campos

	private long idEntidad;
	private String nombreEntidad;
	private String numDocumento;
	private String estatus;
	private String certificadoPropiedad;
        private Long idTipoEntidad;
         public  static final String BODEGACENTRAL="1";  
	// Constructores

	/** constructor por default */
	public Entidad() {
	}

	/** constructor minimo*/
	public Entidad(String nombreentidad, String numdocumento) {
		this.nombreEntidad = nombreentidad;
		this.numDocumento = numdocumento;
	}
	
	public Entidad(Long idEntidad) {
		this.idEntidad=idEntidad;
		// TODO Auto-generated constructor stub
	}

	/** constructor completo */
	public Entidad(String nombreentidad, String numdocumento,
			String estatus, String certificadopropiedad) {
		this.nombreEntidad = nombreentidad;
		this.numDocumento = numdocumento;
		this.estatus = estatus;
		this.certificadoPropiedad = certificadopropiedad;
	}

	// Metodos


    public void setIdEntidad(long idEntidad) {
        this.idEntidad = idEntidad;
    }

    public long getIdEntidad() {
        return idEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setCertificadoPropiedad(String certificadoPropiedad) {
        this.certificadoPropiedad = certificadoPropiedad;
    }

    public String getCertificadoPropiedad() {
        return certificadoPropiedad;
    }

    public void setIdTipoEntidad(Long idTipoEntidad) {
        this.idTipoEntidad = idTipoEntidad;
    }

    public Long getIdTipoEntidad() {
        return idTipoEntidad;
    }

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idEntidad ^ (idEntidad >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Entidad other = (Entidad) obj;
		if (idEntidad != other.idEntidad)
			return false;
		return true;
	}
}
