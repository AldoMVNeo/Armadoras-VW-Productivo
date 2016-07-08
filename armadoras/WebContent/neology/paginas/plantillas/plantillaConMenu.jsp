<%@ page language="java"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request"/>
<html:html locale="true">
  <head>
  <link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
   <script language="javascript" type="text/javascript" src="<%=util.getContexto(request)%>/neology/javascript/login/cerrarSesion.js"></script>
    <html:base/>
  </head>
  <body class="bodyContent">
    <table width="100%" border="0"  cellspacing="5" align="center">
      <tbody>
        <tr align="center">
          <td>
            <tiles:insert attribute="titulo"/>
          </td>
        </tr>        
        <tr>
          <td>
            <tiles:insert attribute="cabeceraMenu"/>
          </td>  
          </tr>
 <tr align="center">
 <td>
            <tiles:insert attribute="cuerpo"/>
          </td>  
        </tr>
        <tr>
 <td>
            <tiles:insert attribute="pie"/>
          </td>  
        </tr>
      </tbody>
    </table>    
   </body>
</html:html>