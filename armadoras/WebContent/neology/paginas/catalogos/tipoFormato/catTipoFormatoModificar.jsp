<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
        <link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
    	<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js" type="text/javascript" language="Javascript"></script>
        <title><bean:message key="texto.etiqueta.cambiarEstado.titulo"/></title>
    </head>
    <body class="bodyContent">
        <html:form action="/tipoFormato2.do" >
        	<html:hidden property="cambiar" value="true"/>
            <html:hidden property="do" value="actualizar"/>
            <%-- Encabezado de Pagina 
            
            <table width="100%" border="0" cellspacing="0" cellpadding="30">
                <tr>
                    <td>
                        <div class="bigText"><img src="<%=util.getContexto(request)%>/neology/imagenes/dotacionFormatos.gif"
                             border="0" width="48" height="48" align="absmiddle"> <bean:message
                             key="texto.etiqueta.cambiarEstado.titulo" />
                        </div>
                        <hr />
                    </td>
		</tr>
            </table>	
            --%>
             <table width="100%" border="0" cellspacing="1" cellpadding="5">
                 <tr class="row2">
                    <td align="right" width="50%">
                       <strong> <bean:message key="texto.etiqueta.formato.tipoFormatodescripcion"/>:</strong>
                    </td>
                    <td align="left">
                        <html:text styleClass="textfield" name="tipoFormatoForm" maxlength="20" property="descripcion"/>
                    </td>
                </tr>                                                                     
                <tr class="row">
                    <td align="right">
                    <strong><bean:message key="texto.etiqueta.formato.tipoFormatoMascara"/>:</strong>            
                    </td>
                    <td align="left">
                        <html:text styleClass="textfield" name="tipoFormatoForm" maxlength="20" property="formatoFolio"/>
                    </td>
                </tr>
                <tr>
        <td colspan="2" align="center">
          <html:submit styleClass="submit"><bean:message key="boton.actualizar"/></html:submit>
          </td>
        </tr>
            </table>
      <br>
           <%-- Mensajes de Error --%>
    <div id="divMensajesError" align="center">   
    <table border="0">
               <logic:messagesPresent>       
                <tr>
            <td>
             <html:messages id="errores" header="errors.header" footer="errors.footer">
                <li><bean:write name="errores" />  </li><br>
                    </html:messages>
                </td>             
                </tr>                 
                    </logic:messagesPresent>  
                </table>                               
         </div>
    <%-- Fin Mensajes de Error --%>   
        </html:form>
    </body>
</html>