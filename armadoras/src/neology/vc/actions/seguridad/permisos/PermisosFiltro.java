package neology.vc.actions.seguridad.permisos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;

import neology.modelo.dto.PermisoMenu;
import neology.modelo.dto.Usuario;
import neology.util.FechaUtil;
import neology.vc.actions.reportes.GeneradorPDFAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.*;

public class PermisosFiltro implements Filter   {
	
	private String errorUrl;
	private static final Log log = LogFactory.getLog(PermisosFiltro.class);
	
	public void init(FilterConfig filterConfig)
    throws ServletException {
		 errorUrl = filterConfig.getInitParameter("logout"); 
	}
	 public void doFilter(ServletRequest request,   
             ServletResponse response, 
             FilterChain chain)
       throws IOException, ServletException {
		 HttpServletRequest req = (HttpServletRequest) request; 
		    HttpServletResponse res = (HttpServletResponse) response;    
		    HttpSession session = req.getSession(false);
		  
		    ActionMessages errores = new ActionMessages();
         //Verifica si el usuario ha iniciado sesion        
    if(session==null){
    	 errores.add(ActionMessages.GLOBAL_MESSAGE, 
                 new ActionMessage("error.general.sesion.invalida"));
    }
    else{
    	Usuario usuario=(Usuario)session.getAttribute("usuarioLog");
    	if(usuario!=null)
    	if(usuario.getEstatus().compareTo(new Integer(0))!=0 )
    		errores.add(ActionMessages.GLOBAL_MESSAGE, 
                    new ActionMessage("error.general.sesion.invalida"));
 	  /* String url=req.getContextPath().concat(req.getServletPath());
 	   Set permisos=usuario.getPerfil().getPermisosMenus();
 	   Iterator it=permisos.iterator();
 	   while(it.hasNext()){
 		   PermisoMenu permiso=(PermisoMenu)it.next();
 		   if(!permiso.getMenu().getUrl().equals(url))
 			   errorUrl="/permisos.jsp";
 	   }*/
    }
   if(errores.isEmpty())
    chain.doFilter(request, response);
   else{
   	req.setAttribute(Globals.ERROR_KEY, errores); 
     req.getRequestDispatcher(errorUrl).forward(req, res);
    }

	 }
	 public void destroy() {
	  }

}
