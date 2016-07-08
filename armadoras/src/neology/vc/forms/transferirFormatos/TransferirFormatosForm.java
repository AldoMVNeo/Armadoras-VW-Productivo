package neology.vc.forms.transferirFormatos;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import neology.modelo.dto.Entidad;
import neology.modelo.dto.Estado;
import neology.modelo.dto.TipoFormato;
import neology.vc.actions.login.CerrarSesionAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

public class TransferirFormatosForm extends ActionForm {
    
   //Campos
    
    private long idEntidad;
    private String estado;
    private long idTipoFormato;
    private String folioInicial;
    private String folioFinal;    
    private List<Entidad> entidades;
    private List<Estado> estados;    
    private List<TipoFormato> tipoFormatos;
    private boolean cambiar;
    private static Logger log = Logger.getLogger(TransferirFormatosForm.class);

    /**Reset all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     */
   

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset( mapping, request);
        cambiar = false;
        folioInicial="";
        folioFinal="";        
        idEntidad=-1;
        idTipoFormato=-1;
        estado="Z";
    }

    /**Validate all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return ActionErrors A list of all errors found.
     */
    public ActionErrors validate(ActionMapping mapping, 
                                 HttpServletRequest request) {
    	
    	log.debug("REPUVE ***************************** TransferirFormatosForm validate - INICIO");
        ActionErrors errores = new ActionErrors();
        if (cambiar) {
            if (idTipoFormato <= 0)
                errores.add("tipoFormato", new ActionMessage("errors.requerido", "Tipo Formato"));
            if (idEntidad <= 0)
                errores.add("entidad", new ActionMessage("errors.requerido", "Entidad"));            
            if (folioInicial == null || folioInicial.trim().length() < 1)
                errores.add("folioInicial", new ActionMessage("errors.requerido", "Folio Inicial"));                         
        }
        log.debug("REPUVE ***************************** TransferirFormatosForm validate - FIN");
        return errores;
    }

    public void setIdEntidad(long idEntidad) {
        this.idEntidad = idEntidad;
    }

    public long getIdEntidad() {
        return idEntidad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setIdTipoFormato(long idTipoFormato) {
        this.idTipoFormato = idTipoFormato;
    }

    public long getIdTipoFormato() {
        return idTipoFormato;
    }

    public void setFolioInicial(String folioInicial) {
        this.folioInicial = folioInicial;
    }

    public String getFolioInicial() {
        return folioInicial;
    }

    public void setFolioFinal(String folioFinal) {
        this.folioFinal = folioFinal;
    }

    public String getFolioFinal() {
        return folioFinal;
    }

    public void setEntidades(List<Entidad> entidades) {
        this.entidades = entidades;
    }

    public List<Entidad> getEntidades() {
        return entidades;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setTipoFormatos(List<TipoFormato> tipoFormatos) {
        this.tipoFormatos = tipoFormatos;
    }

    public List<TipoFormato> getTipoFormatos() {
        return tipoFormatos;
    }

    public void setCambiar(boolean cambiar) {
        this.cambiar = cambiar;
    }

    public boolean isCambiar() {
        return cambiar;
    }
	
}
