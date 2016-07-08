package neology.vc.forms.dotacion;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import neology.modelo.dto.TipoFormato;
import neology.vc.forms.catalogos.tipoFormato.TipoFormatoForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

public class DotacionForm extends ValidatorForm
{

	private static final long serialVersionUID = 3819582977803284577L;
	private static Logger log = Logger.getLogger(DotacionForm.class);

	// Campos
	private int idEntidad;
	private String descripcionEntidad;
	private long idTipoFormato;
	private String folioInicial;
	private String folioFinal;
	private List<TipoFormato> tipoFormatos;
	private boolean dotar;
	private String rango;
	private String boton;

	/**
	 * Reset all properties to their default values.
	 * 
	 * @param mapping The ActionMapping used to select this instance.
	 * @param request The HTTP Request we are processing.
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
		dotar = false;
		folioInicial = "";
		folioFinal = "";
		idEntidad = -1;
		idTipoFormato = -1;
		rango = "";
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
		log.debug("REPUVE ***************************** DotacionForm validate - INICIO");
		ActionErrors errores = new ActionErrors();
		if (dotar)
		{
			if (idTipoFormato <= 0)
				errores.add("tipoFormato", new ActionMessage("errors.requerido",
						"Tipo Formato"));
			if (idEntidad <= 0)
				errores.add("entidad", new ActionMessage("errors.requerido", "Entidad"));
			if (folioInicial == null || folioInicial.trim().length() < 1)
				errores.add("folioInicial", new ActionMessage("errors.requerido",
						"Folio Inicial"));

		}
		log.debug("REPUVE ***************************** DotacionForm validate - FIN");
		return errores;
	}

	// Metodos

	public void setIdEntidad(int idEntidad)
	{
		this.idEntidad = idEntidad;
	}

	public int getIdEntidad()
	{
		return idEntidad;
	}

	public void setFolioInicial(String folioInicial)
	{
		this.folioInicial = folioInicial;
	}

	public String getFolioInicial()
	{
		return folioInicial;
	}

	public void setFolioFinal(String folioFinal)
	{
		this.folioFinal = folioFinal;
	}

	public String getFolioFinal()
	{
		return folioFinal;
	}

	public void setIdTipoFormato(long idTipoFormato)
	{
		this.idTipoFormato = idTipoFormato;
	}

	public long getIdTipoFormato()
	{
		return idTipoFormato;
	}

	public void setDotar(boolean dotar)
	{
		this.dotar = dotar;
	}

	public boolean isDotar()
	{
		return dotar;
	}

	public void setTipoFormatos(List<TipoFormato> tipoFormatos)
	{
		this.tipoFormatos = tipoFormatos;
	}

	public List<TipoFormato> getTipoFormatos()
	{
		return tipoFormatos;
	}

	public void setDescripcionEntidad(String descripcionEntidad)
	{
		this.descripcionEntidad = descripcionEntidad;
	}

	public String getDescripcionEntidad()
	{
		return descripcionEntidad;
	}

	public String getRango()
	{
		return rango;
	}

	public void setRango(String rango)
	{
		this.rango = rango;
	}

	public String getBoton()
	{
		return boton;
	}

	public void setBoton(String boton)
	{
		this.boton = boton;
	}
}
