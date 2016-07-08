package neology.vc.forms.sistema;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import neology.modelo.dto.Usuario;
import neology.seguridad.Cipher;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class CambiarNombreUsuarioForm extends  ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6073787335477863627L;
	private String nombreUsuario;
	private String contrasenaNueva;
	private String contrasenaAnterior;
	private String repetirContrasena;
	private List<Usuario> usuarios;
	private boolean cambiar;
	
	/**Reset all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
       contrasenaNueva="";
       contrasenaAnterior="";
       repetirContrasena="";
       cambiar=false;
    }
    /**Validate all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return ActionErrors A list of all errors found.
     */
    public ActionErrors validate(ActionMapping mapping, 
                                 HttpServletRequest request) {
  
    	ActionErrors errores = new ActionErrors();   
//    	if(cambiar){
//    		
//    		
//    	if (nombreUsuario == null || nombreUsuario.trim().length() < 1)
//            errores.add("Usuario", new ActionMessage("errors.requerido", "Usuario"));
//    	 else{
//        	 Pattern patron=Pattern.compile("[^A-Za-z0-9\\.\\_\\-]+");
//        	Matcher m=patron.matcher(nombreUsuario);
//        	if(m.find()){
//        		errores.add("usuario", new ActionMessage("errors.campo.caracteres.invalidos","nombre de usuario"));
//        		
//        	}
//    	 }
//    	
//    	
////    	if (contrasenaAnterior == null || contrasenaAnterior.trim().length() < 1)
////            errores.add("contrasenaAnterior", new ActionMessage("errors.requerido", "contraseña anterior"));  
////    	else{
//    		Usuario usuario=(Usuario)request.getSession().getAttribute("usuarioLog");
//    		Cipher cipher=new Cipher(usuario.getContrasena(),"NEOLOGY$XPERTO&RFID");
//    		
//    		
//    		//Se retira validacón de contrasena anterior
//    		/*if(!cipher.desencriptarPass(usuario.getContrasena()).equals(contrasenaAnterior)){
//    			errores.add("contrasenaAnterior", new ActionMessage("errors.catalogos.usuario.contrasena.invalida"));  
//    		contrasenaAnterior="";
//    		contrasenaNueva="";
//    		repetirContrasena="";
//    		}*/
//    		
//    		//else{
//    			
//    			
//    		if (contrasenaNueva == null || contrasenaNueva.trim().length() < 1)
//                errores.add("contrasena", new ActionMessage("errors.requerido", "contraseña nueva"));  
//            else{
//            	 Pattern patron=Pattern.compile("[^A-Za-z0-9\\.\\_\\-]+");
//            	Matcher m=patron.matcher(contrasenaNueva);
//            	if(m.find()){
//            		errores.add("contrasena", new ActionMessage("errors.campo.caracteres.invalidos","contraseña nueva"));
//            		contrasenaNueva="";
//            		repetirContrasena="";
//            	}
//            	else
//            		if(!contrasenaNueva.equals(repetirContrasena)){
//            	errores.add("contrasena", new ActionMessage("errors.catalogos.usuario.repetir.contrasena"));
//            	contrasenaNueva="";
//        		repetirContrasena="";
//            		}
//            }
//    		
//    		
//           // }
//    	//}
//    	
//    }
    	return errores;
    }
	/**
	 * @return the contraenaNueva
	 */
	public String getContrasenaNueva() {
		return contrasenaNueva;
	}
	/**
	 * @param contraenaNueva the contraenaNueva to set
	 */
	public void setContrasenaNueva(String contrasenaNueva) {
		this.contrasenaNueva = contrasenaNueva;
	}
	/**
	 * @return the contrasenaAnterior
	 */
	public String getContrasenaAnterior() {
		return contrasenaAnterior;
	}
	/**
	 * @param contrasenaAnterior the contrasenaAnterior to set
	 */
	public void setContrasenaAnterior(String contrasenaAnterior) {
		this.contrasenaAnterior = contrasenaAnterior;
	}
	/**
	 * @return the nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	/**
	 * @param nombreUsuario the nombreUsuario to set
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	/**
	 * @return the repetirContrasena
	 */
	public String getRepetirContrasena() {
		return repetirContrasena;
	}
	/**
	 * @param repetirContrasena the repetirContrasena to set
	 */
	public void setRepetirContrasena(String repetirContrasena) {
		this.repetirContrasena = repetirContrasena;
	}
	/**
	 * @return the cambiar
	 */
	public boolean isCambiar() {
		return cambiar;
	}
	/**
	 * @param cambiar the cambiar to set
	 */
	public void setCambiar(boolean cambiar) {
		this.cambiar = cambiar;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	
	
}
