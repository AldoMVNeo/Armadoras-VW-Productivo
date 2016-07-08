package neology.modelo.dto.impresion;

public class Seleccionados implements java.io.Serializable {

	private String vin;
	private Boolean activo;
	
	public Seleccionados(String vin, Boolean activo) {
		super();
		this.vin = vin;
		this.activo = activo;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
}
