<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
    <script src="<%=util.getContexto(request)%>/neology/javascript/transferencia/transferencia.js" type="text/javascript" language="Javascript"></script>
    <script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js" type="text/javascript" language="Javascript"></script>
    <title><bean:message key="texto.etiqueta.transferirFormatos.titulo"/></title>
  </head>
  <body class="bodyContent">
    <html:form action="/transferirFormatos.do" onsubmit="realizarTransferencia();">
         <html:hidden property="cambiar" value="true"/>
         <html:hidden property="do" value="cambiar"/>
         <%-- Encabezado de Pagina --%>
      <table width="100%" border="0" cellspacing="0" cellpadding="30">
		<tr>
			<td>
			<div class="bigText"><img src="<%=util.getContexto(request)%>/neology/recursos/imagenes/dotacionFormatos.gif"
				border="0" width="112" height="79" align="absmiddle"> <bean:message
				key="texto.etiqueta.transferirFormatos.titulo" /></div>
			<hr />
			</td>
		</tr>
	</table>	
	
	    <%-- Fin de Encabezado --%> 
      <table width="100%" border="0" cellspacing="1" cellpadding="5">
       <tr class="row2">
          <td align="right" width="50%">
          <strong>
            <bean:message key="texto.etiqueta.formato.tipoFormato"/>
            </strong>
          </td>
          <td colspan="2" align="left" width="50%">
            <html:select styleClass="textfield" property="idTipoFormato">
              <html:option value="-1">- Seleccione un Tipo -</html:option>
              <html:optionsCollection name="transferirFormatosForm" property="tipoFormatos"
                                      value="idTipoFormato" label="descripcionConFormato"/>
            </html:select>
          </td>
        </tr>    
        <tr>
          <td align="right">
          <strong>
            <bean:message key="texto.etiqueta.formato.entidadDestino"/>:
            </strong>
          </td>
           <td align="left"> 
            <html:select styleClass="textfield" property="idEntidad">
              <html:option value="-1">- Seleccione una Entidad -</html:option>
              <html:optionsCollection name="transferirFormatosForm" property="entidades"
                                      value="idEntidad" label="nombreEntidad"/>
            </html:select>
          </td>          
        </tr>                            
        <tr class="row2">
        <td align="right">
          <strong>
            <bean:message key="texto.etiqueta.formato.folio.inicial"/>:     
            </strong>       
          </td>
         <td align="left">
            <html:text styleClass="textfield" name="transferirFormatosForm" maxlength="20"
                       property="folioInicial"/>
          </td>
        </tr>
      <tr>
        <td align="right">
          <strong>
            <bean:message key="texto.etiqueta.formato.folio.final"/>:  
            </strong>          
          </td>
          <td align="left">
            <html:text styleClass="textfield" name="transferirFormatosForm" maxlength="20"
                       property="folioFinal"/>
          </td>
        </tr>
        <tr>
        <td colspan="2" align="center">
          <html:submit styleClass="submit"><bean:message key="boton.formato.transferencia"/></html:submit>
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