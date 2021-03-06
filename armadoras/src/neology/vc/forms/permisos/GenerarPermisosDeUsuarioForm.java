/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package neology.vc.forms.permisos;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import neology.modelo.dto.EstadoCatalogo;
import neology.modelo.dto.Perfil;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/** 
 * MyEclipse Struts
 * Creation date: 05-19-2008
 * 
 * XDoclet definition:
 * @struts.form name="GenerarPermisosUsuarioForm"
 */
public class GenerarPermisosDeUsuarioForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5305965663419982368L;
	private String nombrePerfil;
	private Long idPerfil;
	private List perfiles;
	private List estados;
	private Integer estado;
	private String menuSeleccionados;
	private String menuActivos;
	private boolean cambiar;
	/*
	 * Generated Methods
	 */



	

	public boolean getCambiar() {
		return cambiar;
	}

	public void setCambiar(boolean cambiar) {
		this.cambiar = cambiar;
	}

	public String getMenuSeleccionados() {
		return menuSeleccionados;
	}

	public void setMenuSeleccionados(String menuSeleccionados) {
		this.menuSeleccionados = menuSeleccionados;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNombrePerfil() {
		return nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}
	

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		 ActionErrors errores = new ActionErrors();		
//		 if(cambiar){
//			 if(estado==null || estado==-1)
//				 errores.add("nombre", new ActionMessage("errors.requerido", "Estado Actual"));
//			 if(nombrePerfil==null || nombrePerfil.trim().length()==0)
//				 errores.add("nombre", new ActionMessage("errors.requerido", "Nombre del Perfil"));
//		 }
		return errores;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		cambiar=false;
		idPerfil=null;
		estado=null;
		nombrePerfil="";
		menuSeleccionados="";
		menuActivos="";
	}

	

	public List getEstados() {
		return estados;
	}

	public void setEstados(List estados) {
		this.estados = estados;
	}

	public List getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List perfiles) {
		this.perfiles = perfiles;
	}

	public String getMenuActivos() {
		return menuActivos;
	}

	public void setMenuActivos(String menuActivos) {
		this.menuActivos = menuActivos;
	}
}