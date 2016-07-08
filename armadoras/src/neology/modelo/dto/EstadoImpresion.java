package neology.modelo.dto;

public class EstadoImpresion implements java.io.Serializable {

	/**
	 * 
	 */
	// Campos
	private static final long serialVersionUID = 9178129919176368244L;

	private int idEstadoImpresion;

	private String descripcionEstadoImpresion;
	
	public static final int CANCELADOERRORIMPRESION=1;
	
	public static final int CANCELADOOTRASCAUSAS=12;

	public static final EstadoImpresion[] listaEstadosImpresion = {
			new EstadoImpresion(0, "La impresión fué exitosa"),
			new EstadoImpresion(1,
					"Error en la impresión (caidas, tinta, etc...)"),
			new EstadoImpresion(2, "No fué exitosa por otras causas (especifique)") };

	// Constructores
	public EstadoImpresion(int idEstadoImpresion,
			String descripcionEstadoImpresion) {
		super();
		this.idEstadoImpresion = idEstadoImpresion;
		this.descripcionEstadoImpresion = descripcionEstadoImpresion;
	}

	// Metodos
	public String getDescripcionEstadoImpresion() {
		return descripcionEstadoImpresion;
	}

	public void setDescripcionEstadoImpresion(String descripcionEstadoImpresion) {
		this.descripcionEstadoImpresion = descripcionEstadoImpresion;
	}

	public int getIdEstadoImpresion() {
		return idEstadoImpresion;
	}

	public void setIdEstadoImpresion(int idEstadoImpresion) {
		this.idEstadoImpresion = idEstadoImpresion;
	}

}
