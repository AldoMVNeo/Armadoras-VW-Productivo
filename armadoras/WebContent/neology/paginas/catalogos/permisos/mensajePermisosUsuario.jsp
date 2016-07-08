<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request"/>
<html>


    <head>
  <link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
  <title><bean:message key="texto.etiqueta.perfil.modificar"/></title>
    </head>
    <body class="bodyContent">
    <%-- Encabezado de Pagina  --%>
     <table width="100%" border="0" cellspacing="0" cellpadding="30">
        <tr>
        <td>       
        <div class=\"bigText\">
              <img src="<%=util.getContexto(request)%>/neology/recursos/imagenes/perfiles.gif" border="0" width="112" height="79" align="absmiddle"/><bean:message key="texto.etiqueta.perfil.modificar"/>
        </div> 
        <hr />
        </td> 
        </tr>
        </table>
  <%-- Fin de Encabezado --%> 
        <html:form action="/permisosUsuario.do">
        <html:hidden property="do" value="inicio" />        
 <div align="center">
      <div style="border: 3px dotted #006699;font-size:13pt;margin:50px auto;padding:5px;text-align:center;width:550px;">
                   
                    <p>
                     <strong>
                        <html:messages id="msg" name="mensaje">
                            <li>
                                <bean:write name="msg"/>
                            </li>
                        </html:messages>
                         </strong>
                    </p>                   
                </div>  
                <table>
                <tr>
    <td align="right">
    <html:submit styleClass="submit"> <bean:message key="boton.aceptar"/></html:submit>
    </td>
    </tr>
                </table>
                <div>               
    </html:form>
</body>
</html>