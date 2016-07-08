<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request"/>
<%@ page isThreadSafe="false"%>
<html> 
<head>
<title><bean:message key="texto.emision.grabarCI"/></title>
<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
 
</head>
<body class="bodyContent">
 <%-- Encabezado de Pagina  --%>
     <table width="100%" border="0" cellspacing="0" cellpadding="30">
        <tr>
        <td>       
        <div class=\"bigText\">
              <img src="<%=util.getContexto(request)%>/neology/recursos/imagenes/grabacionConstancias.jpg" border="0"  align="absmiddle"/><bean:message key="texto.emision.grabarCI"/>
        </div> 
        <hr />
        </td> 
        </tr>
        </table>
  <%-- Fin de Encabezado --%>
<APPLET ARCHIVE="GrabadorChipRepuve.jar" CODE="GrabadorChipRepuve.class" HEIGHT="305" WIDTH="650" ALIGN="bottom"  codebase="../../../"/>
        Si ve este mensaje su navegador no soporta applets, verifiquelo con el Administrador     
     </APPLET>	
  </body>
 </html>
 