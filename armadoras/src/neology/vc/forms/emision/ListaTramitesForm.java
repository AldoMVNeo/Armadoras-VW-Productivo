package neology.vc.forms.emision;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListaTramitesForm extends ActionForm {      
    private List ordenes;

    /**Reset all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset( mapping, request);
    }

    /**Validate all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return ActionErrors A list of all errors found.
     */
    public ActionErrors validate(ActionMapping mapping, 
                                 HttpServletRequest request) {
        return super.validate( mapping, request);
    }


    public void setOrdenes(List ordenes) {
        this.ordenes = ordenes;
    }

    public List getOrdenes() {
        return ordenes;
    }
}

