package neology.vc.forms.catalogos.usuarios;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import neology.modelo.dto.Entidad;
import neology.modelo.dto.EstadoCatalogo;
import neology.modelo.dto.Perfil;
import neology.vc.forms.catalogos.tipoFormato.TipoFormatoForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


public class UsuariosForm extends ActionForm {
    
   //Campos
    
    private long idUsuario;
    private long idEntidad;
    private Long idPerfil;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;   
    private String fechaAlta;
    private String usuario;
    private String contrasena;
    private Integer estatus;
    private Integer numeroCaja;    
    private boolean cambiar;
    private String nombres;
    private List<Perfil> perfiles;
    private List<EstadoCatalogo> estadosCatalogos;
    private List<Entidad> entidades;
    private static Logger log = Logger.getLogger(UsuariosForm.class);

	public List<EstadoCatalogo> getEstadosCatalogos() {
		return estadosCatalogos;
	}

	public void setEstadosCatalogos(List<EstadoCatalogo> estadosCatalogos) {
		this.estadosCatalogos = estadosCatalogos;
	}

	/**Reset all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     */
   

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset( mapping, request);
        cambiar = false;
        usuario = "";
        contrasena ="";
        apellidoPaterno="";
        apellidoMaterno="";
        nombre="";
        idPerfil=null;
    }

    /**Validate all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return ActionErrors A list of all errors found.
     */
    public ActionErrors validate(ActionMapping mapping, 
                                 HttpServletRequest request) {
    	log.debug("REPUVE ***************************** UsuariosForm validate - INICIO");
        ActionErrors errores = new ActionErrors();
        if (cambiar) {  
        	String alfa = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz "; 
        	if (nombre == null || nombre.trim().length() < 1)
                errores.add("nombre", new ActionMessage("errors.requerido", "nombre"));
        	if (idPerfil == null || idPerfil<1)
                errores.add("nombre", new ActionMessage("errors.requerido", "Perfil"));
        	if (estatus == null || estatus<0)
                errores.add("estatus", new ActionMessage("errors.requerido", "estatus"));
        	
        	else
        	{
        		for (int i = 0; i < nombre.length(); i++)
        		{        			
        			if (alfa.indexOf(nombre.charAt(i)) == - 1)
        			{ 
        				errores.add("nombre", new ActionMessage("errors.noValido", nombre.charAt(i)));
        			}
        		} // end for
        	}
        	if (apellidoPaterno == null || apellidoPaterno.trim().length() < 1)
                errores.add("apellidoPaterno", new ActionMessage("errors.requerido", "apellidoPaterno"));
        	else
        	{
        		for (int i = 0; i < apellidoPaterno.length(); i++)
        		{        			
        			if (alfa.indexOf(apellidoPaterno.charAt(i)) == - 1)
        			{ 
        				errores.add("apellidoPaterno", new ActionMessage("errors.noValido", apellidoPaterno.charAt(i)));
        			}
        		} // end for
        	}
        	if (usuario == null || usuario.trim().length() < 1)
                errores.add("Usuario", new ActionMessage("errors.requerido", "Usuario"));
//            if (contrasena == null || contrasena.trim().length() < 1)
//                errores.add("contrasena", new ActionMessage("errors.requerido", "contraseña"));            
            
            
        }        
        log.debug("REPUVE ***************************** UsuariosForm validate - FIN");
        return errores;        
    }

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public long getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(long idEntidad) {
		this.idEntidad = idEntidad;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumeroCaja() {
		return numeroCaja;
	}

	public void setNumeroCaja(Integer numeroCaja) {
		this.numeroCaja = numeroCaja;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public boolean isCambiar() {
		return cambiar;
	}

	public void setCambiar(boolean cambiar) {
		this.cambiar = cambiar;
	}

	public List<Entidad> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<Entidad> entidades) {
		this.entidades = entidades;
	}

	
	
}
