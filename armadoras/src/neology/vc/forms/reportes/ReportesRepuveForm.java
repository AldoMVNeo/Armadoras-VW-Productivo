package neology.vc.forms.reportes;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import neology.modelo.dto.Entidad;
import neology.modelo.dto.EstadoOI;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class ReportesRepuveForm extends ActionForm {
    private String fechaInicial;
    private String fechaFinal;
    private Integer grupo;
    private Integer estado;
    private Boolean cambiar;
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset( mapping, request);
        cambiar = false;    
        grupo=1;
        estado=8;
    }
    
    
    public ActionErrors validate(ActionMapping mapping, 
            HttpServletRequest request) {
    	return super.validate(mapping, request);      
    }
    
	public String getFechaFinal()
	{
		return fechaFinal;
	}
	public void setFechaFinal(String fechaFinal)
	{
		this.fechaFinal = fechaFinal;
	}
	public String getFechaInicial()
	{
		return fechaInicial;
	}
	public void setFechaInicial(String fechaInicial)
	{
		this.fechaInicial = fechaInicial;
	}
	public Integer getGrupo()
	{
		return grupo;
	}
	public void setGrupo(Integer grupo)
	{
		this.grupo = grupo;
	}


	public Integer getEstado() {
		return estado;
	}


	public void setEstado(Integer estado) {
		this.estado = estado;
	}
                	
	
}
