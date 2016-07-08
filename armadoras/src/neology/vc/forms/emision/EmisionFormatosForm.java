package neology.vc.forms.emision;

import javax.servlet.http.HttpServletRequest;

import neology.modelo.dto.EstadoImpresion;
import neology.modelo.dto.Formato;
import neology.modelo.dto.OrdenImpresion;
import neology.modelo.dto.Usuario;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class EmisionFormatosForm extends ActionForm
{
	private Long numeroTramite;
	private Long idOrdenImpresion;
	private String vin;
	private String placa;
	private Integer annoModelo;
	private char tipo;
	private String marca;
	private String modelo;
	private String barco;
	private boolean valido;
	private OrdenImpresion ordenImpresion;
	private String folio;
	private String numeroChip;
	private Formato formato;
	private Usuario usuario;
	private String nombreReporte;
	private EstadoImpresion[] estadoImpresion;
	private Integer numeroImpresiones;

	public Integer getNumeroImpresiones()
	{
		return numeroImpresiones;
	}

	public void setNumeroImpresiones(Integer numeroImpresiones)
	{
		this.numeroImpresiones = numeroImpresiones;
	}

	public EstadoImpresion[] getEstadoImpresion()
	{
		return estadoImpresion;
	}

	public void setEstadoImpresion(EstadoImpresion[] estadoImpresion)
	{
		this.estadoImpresion = estadoImpresion;
	}

	public String getNombreReporte()
	{
		return nombreReporte;
	}

	public void setNombreReporte(String nombreReporte)
	{
		this.nombreReporte = nombreReporte;
	}

	public String getFolio()
	{
		return folio;
	}

	public void setFolio(String folio)
	{
		this.folio = folio;
	}

	public Formato getFormato()
	{
		return formato;
	}

	public void setFormato(Formato formato)
	{
		this.formato = formato;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public OrdenImpresion getOrdenImpresion()
	{
		return ordenImpresion;
	}

	public void setOrdenImpresion(OrdenImpresion ordenImpresion)
	{
		this.ordenImpresion = ordenImpresion;
	}

	public boolean isValido()
	{
		return valido;
	}

	public void setValido(boolean valido)
	{
		this.valido = valido;
	}

	/**
	 * Reset all properties to their default values.
	 * 
	 * @param mapping The ActionMapping used to select this instance.
	 * @param request The HTTP Request we are processing.
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);

	}

	/**
	 * Reinician todos los valores.
	 */
	public void reiniciar()
	{
		this.setAnnoModelo(null);
		this.setIdOrdenImpresion(null);
		this.setMarca(null);
		this.setModelo(null);
		this.setNumeroTramite(null);
		this.setPlaca(null);
		this.setBarco(null);
		this.setTipo('\0');
		this.setVin(null);
		this.setEstadoImpresion(null);
		this.setFolio(null);
		this.setFormato(null);
		this.setNombreReporte(null);
		this.setNumeroChip(null);
		this.setOrdenImpresion(null);
		this.setUsuario(null);
	}

	/**
	 * Validate all properties to their default values.
	 * 
	 * @param mapping The ActionMapping used to select this instance.
	 * @param request The HTTP Request we are processing.
	 * @return ActionErrors A list of all errors found.
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		return super.validate(mapping, request);
	}

	public void setPlaca(String placa)
	{
		this.placa = placa;
	}

	public String getPlaca()
	{
		return placa;
	}

	public void setVin(String vin)
	{
		this.vin = vin;
	}

	public String getVin()
	{
		return vin;
	}

	public void setAnnoModelo(Integer annoModelo)
	{
		this.annoModelo = annoModelo;
	}

	public Integer getAnnoModelo()
	{
		return annoModelo;
	}

	public void setTipo(char tipo)
	{
		this.tipo = tipo;
	}

	public char getTipo()
	{
		return tipo;
	}

	public void setMarca(String marca)
	{
		this.marca = marca;
	}

	public String getMarca()
	{
		return marca;
	}

	public void setModelo(String modelo)
	{
		this.modelo = modelo;
	}

	public String getModelo()
	{
		return modelo;
	}

	public void setBarco(String barco)
	{
		this.barco = barco;
	}

	public String getBarco()
	{
		return barco;
	}

	public void setNumeroTramite(Long numeroTramite)
	{
		this.numeroTramite = numeroTramite;
	}

	public Long getNumeroTramite()
	{
		return numeroTramite;
	}

	public Long getIdOrdenImpresion()
	{
		return idOrdenImpresion;
	}

	public void setIdOrdenImpresion(Long idOrdenImpresion)
	{
		this.idOrdenImpresion = idOrdenImpresion;
	}

	public String getNumeroChip()
	{
		return numeroChip;
	}

	public void setNumeroChip(String numeroChip)
	{
		this.numeroChip = numeroChip;
	}

}
