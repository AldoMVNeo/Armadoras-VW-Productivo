<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request"/>
<%@ page isThreadSafe="false"%>
<html> 
<body bgcolor="#FFFFFF">
  <APPLET CODE="ImprimirCodigoBarras.class" HEIGHT="305" WIDTH="650" ALIGN="bottom"  codebase="../../../"/>
        <br><PARAM NAME="contexto" VALUE="<%=util.getContexto(request)%>">       
         <PARAM NAME="accion" VALUE="<%=request.getAttribute("accion")%>">  
         Si ve este mensaje su navegador no soporta applets, verifiquelo con el Administrador     
     </APPLET>	    
  </body>
 </html>
 