package neology.vc.forms.busqueda;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import neology.modelo.dto.EstadoCatalogo;
import neology.modelo.dto.EstadoOI;
import neology.modelo.dto.OrdenImpresion;
import neology.modelo.dto.Perfil;
import neology.modelo.dto.Usuario;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


public class BusquedaNivForm extends ActionForm {
    
   //Campos        
    private String vin;   
    private List datos= new ArrayList();
    private OrdenImpresion orden;
    private Boolean consultaBD;
    

	
	/**Reset all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     */
   

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset( mapping, request);
        vin = "";
        orden = null;
        datos = null;
        consultaBD=false;
        }

    /**Validate all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return ActionErrors A list of all errors found.
     */
    public ActionErrors validate(ActionMapping mapping, 
                                 HttpServletRequest request) {
    	return super.validate(mapping, request);       
    }

	





	

	public List getDatos()
	{
		return datos;
	}

	public void setDatos(List datos)
	{
		this.datos = datos;
	}

	public String getVin()
	{
		return vin;
	}

	public void setVin(String vin)
	{
		this.vin = vin;
	}

	public OrdenImpresion getOrden()
	{
		return orden;
	}

	public void setOrden(OrdenImpresion orden)
	{
		this.orden = orden;
	}

	public Boolean getConsultaBD() {
		return consultaBD;
	}

	public void setConsultaBD(Boolean consultaBD) {
		this.consultaBD = consultaBD;
	}
	
}
