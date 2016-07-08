package neology.modelo.dto.id;

public class FormatoId implements java.io.Serializable
{

	private static final long serialVersionUID = -523289882424173516L;

	// Campos
	private String folio;
	private long idTipoFormato;

	// Constructores

	/** constructor por default */
	public FormatoId(){
		
	}
	
	public FormatoId(String folio, long idTipoFormato)
	{
		this.folio = folio;
		this.idTipoFormato = idTipoFormato;
	}

	// Metodos

	public void setFolio(String folio)
	{
		this.folio = folio;
	}

	public String getFolio()
	{
		return folio;
	}

	public long getIdTipoFormato()
	{
		return this.idTipoFormato;
	}

	public void setIdTipoFormato(long idTipoFormato)
	{
		this.idTipoFormato = idTipoFormato;
	}

	public boolean equals(Object other)
	{
		if ((this == other)) return true;
		if ((other == null)) return false;
		if (!(other instanceof FormatoId)) return false;
		FormatoId castOther = (FormatoId) other;

		return ((this.getFolio() == castOther.getFolio()) || (this.getFolio() != null
				&& castOther.getFolio() != null && this.getFolio().equals(
				castOther.getFolio())))
				&& (this.getIdTipoFormato() == castOther.getIdTipoFormato());
	}

	public int hashCode()
	{
		int result = 17;
		result = 37 * result + (getFolio() == null ? 0 : this.getFolio().hashCode());
		result = 37 * result + (int) this.getIdTipoFormato();
		return result;
	}
	
	public String toString(){
		return folio;
	}

}
