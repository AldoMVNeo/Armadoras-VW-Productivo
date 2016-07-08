package neology.vc.forms.catalogos.tipoFormato;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import neology.modelo.dto.TipoFormato;
import neology.vc.forms.cambiarEstado.CambiarEstadoForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

public class TipoFormatoForm extends ActionForm {
    
   //Campos
    
    private long idTipoFormato;
    private String claveTipoFormato;
    private String descripcion;
    private String formatoFolio;
    private String estatus;   
    private boolean cambiar;
    private static Logger log = Logger.getLogger(TipoFormatoForm.class);

    public boolean isCambiar() {
		return cambiar;
	}

	public void setCambiar(boolean cambiar) {
		this.cambiar = cambiar;
	}

	/**Reset all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     */
   

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset( mapping, request);
        cambiar = false;
        descripcion = "";
        formatoFolio ="";
    }

    /**Validate all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return ActionErrors A list of all errors found.
     */
    public ActionErrors validate(ActionMapping mapping, 
                                 HttpServletRequest request) {
    	log.debug("REPUVE ***************************** TipoFormatoForm validate - INICIO");
        ActionErrors errores = new ActionErrors();
        if (cambiar) {            
            if (descripcion == null || descripcion.trim().length() < 1)
                errores.add("descripcion", new ActionMessage("errors.requerido", "descripcion"));
            if (formatoFolio == null || formatoFolio.trim().length() < 1)
                errores.add("formatoFolio", new ActionMessage("errors.requerido", "formato de Folio"));            
        }        
        log.debug("REPUVE ***************************** TipoFormatoForm validate - FIN");
        return errores;        
    }

	public String getClaveTipoFormato() {
		return claveTipoFormato;
	}

	public void setClaveTipoFormato(String claveTipoFormato) {
		this.claveTipoFormato = claveTipoFormato;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getFormatoFolio() {
		return formatoFolio;
	}

	public void setFormatoFolio(String formatoFolio) {
		this.formatoFolio = formatoFolio;
	}

	public long getIdTipoFormato() {
		return idTipoFormato;
	}

	public void setIdTipoFormato(long idTipoFormato) {
		this.idTipoFormato = idTipoFormato;
	}

    // Metodos    

}
