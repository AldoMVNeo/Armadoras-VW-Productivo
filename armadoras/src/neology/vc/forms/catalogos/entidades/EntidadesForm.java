package neology.vc.forms.catalogos.entidades;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import neology.modelo.dto.Entidad;
import neology.vc.actions.login.CerrarSesionAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

public class EntidadesForm extends ActionForm {
    
   //Campos
    
	private long idEntidad;
	private String nombreEntidad;
	private String numDocumento;
	private String estatus;	
	private boolean cambiar;

	private static Logger log = Logger.getLogger(EntidadesForm.class);

	/**Reset all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     */
   

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset( mapping, request);
        
        nombreEntidad = "";
        numDocumento ="";
        estatus="";
       
    }

    /**Validate all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return ActionErrors A list of all errors found.
     */
    public ActionErrors validate(ActionMapping mapping, 
                                 HttpServletRequest request) {
    	
    	log.debug("REPUVE ***************************** EntidadesForm validate - INICIO");
        ActionErrors errores = new ActionErrors();
        if (cambiar) {  
        	String alfa = "ABCDEFGHIJKLMNOPQRSTUWXYZabcdefghijklmnopqrstuvxyz "; 
        	if (nombreEntidad == null || nombreEntidad.trim().length() < 1)
                errores.add("nombreEntidad", new ActionMessage("errors.requerido", "nombreEntidad"));
        	else
        	{
        		for (int i = 0; i < nombreEntidad.length(); i++)
        		{        			
        			if (alfa.indexOf(nombreEntidad.charAt(i)) == - 1)
        			{ 
        				errores.add("nombreEntidad", new ActionMessage("errors.noValido", nombreEntidad.charAt(i)));
        			}
        		} // end for
        	}        	        	            
        }        
        log.debug("REPUVE ***************************** EntidadesForm validate - FIN");
        return errores;        
    }

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public long getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(long idEntidad) {
		this.idEntidad = idEntidad;
	}

	public String getNombreEntidad() {
		return nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

		
}
