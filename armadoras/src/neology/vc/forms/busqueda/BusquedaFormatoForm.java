package neology.vc.forms.busqueda;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import neology.modelo.dto.Formato;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class BusquedaFormatoForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2612224558226979611L;
	private String folio;
	private List formatoActual;
	private Boolean consultaBD;

	public List getFormatoActual() {
		return formatoActual;
	}

	public void setFormatoActual(List formatoActual) {
		this.formatoActual = formatoActual;
	}

	//Metodos
	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public Boolean getConsultaBD() {
		return consultaBD;
	}

	public void setConsultaBD(Boolean consultaBD) {
		this.consultaBD = consultaBD;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset( mapping, request);
        consultaBD = false;    
    }
    

}
