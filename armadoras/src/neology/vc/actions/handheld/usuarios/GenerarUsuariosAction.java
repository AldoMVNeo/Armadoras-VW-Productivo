package neology.vc.actions.handheld.usuarios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neology.modelo.dto.Perfil;
import neology.modelo.dto.Usuario;
import neology.modelo.negocio.daos.UsuarioDAO;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.seguridad.Cipher;
import neology.util.FechaUtil;
import neology.util.Utilidades;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GenerarUsuariosAction extends Action {
		
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		Logger loggerInfo = Utilidades.loggerInfo();
		Logger loggerError = Utilidades.loggerError();
		
		loggerInfo.info(FechaUtil.getHoraActual()+"_GenerarUsuariosAction execute - INICIO***");
		try{
		UsuarioDAO usuarioDAO=DAOFactory.crearUsuarioDAO();
		//Se obtienen usuarios con perfil de HANDHELD
		List<Usuario>usuarios=usuarioDAO.buscarPorPerfil(Perfil.HANDHELD);
		if(usuarios!=null && usuarios.size()>0){
		String nombreArchivo = "usuarios.txt";
		File archivo = new File(nombreArchivo);
		BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
		
		String temp1="";
		//Se genera y se escribe en el archivo de texto
		for (Usuario usuario:usuarios){
			if(usuario.getEstatus().compareTo(new Integer(0))==0){
				System.out.println(usuario.getUsuario());
			temp1=Cipher.encriptar(usuario.getIdUsuario()+"|"+usuario.getUsuario()+"|"+usuario.getContrasena()+"|"+usuario.getNombres());
			bw.write(temp1+"\r\n");
			}
		}
		bw.close();
		if(archivo.length()>0){
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition",
				"attachment;filename=usuarios.txt");
		OutputStream out = response.getOutputStream();
		byte[] bytes = new byte[1000];
		int read = 0;
		FileInputStream fis = new FileInputStream(archivo);
		while ((read = fis.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
		}
		else{
			loggerInfo.info(FechaUtil.getHoraActual()+"_GenerarUsuariosAction execute - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("error");
			}
		}
		else{
			loggerInfo.info(FechaUtil.getHoraActual()+"_GenerarUsuariosAction execute - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("error");
			}
		loggerInfo.info(FechaUtil.getHoraActual()+"_GenerarUsuariosAction execute - FIN***");
		loggerInfo.removeAllAppenders();
		loggerError.removeAllAppenders();
		return null;
		}catch(Exception e){
			loggerError.error(FechaUtil.getHoraActual()+"_Fallo GenerarUsuariosAction execute", e);
			loggerError.error(FechaUtil.getHoraActual()+"_GenerarUsuariosAction execute - FIN***");
			loggerInfo.removeAllAppenders();
			loggerError.removeAllAppenders();
			return mapping.findForward("error");
		}
	}
}
