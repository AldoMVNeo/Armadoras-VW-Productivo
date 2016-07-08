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
    <%-- Encabezado de Pagina  --%>
     <table width="100%" border="0" cellspacing="0" cellpadding="30">
        <tr>
        <td>       
        <div class=\"bigText\">
              <img src="<%=util.getContexto(request)%>/neology/recursos/imagenes/usuarios.gif" border="0" width="112" height="79" align="absmiddle"/><bean:message key="texto.etiqueta.catalogo.usuarios"/>
        </div> 
        <hr />
        </td> 
        </tr>
        </table>
  <%-- Fin de Encabezado --%>  
        <html:form action="/usuarios2.do" >
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
                       <strong> <bean:message key="texto.etiqueta.usuario.nombre"/>:</strong>
                    </td>
                    <td align="left">
                        <html:text styleClass="textfield" name="usuariosForm" maxlength="20" property="nombre" onkeypress="return isAlphanumeric(event)"/>
                    </td>
                </tr>
                <tr class="row">
                    <td align="right" width="50%">
                       <strong> <bean:message key="texto.etiqueta.usuario.apPaterno"/>:</strong>
                    </td>
                    <td align="left">
                        <html:text styleClass="textfield" name="usuariosForm" maxlength="20" property="apellidoPaterno" onkeypress="return isAlphanumeric(event)"/>
                    </td>
                </tr>
                <tr class="row2">
                    <td align="right" width="50%">
                       <strong> <bean:message key="texto.etiqueta.usuario.apMaterno"/>:</strong>
                    </td>
                    <td align="left">
                        <html:text styleClass="textfield" name="usuariosForm" maxlength="20" property="apellidoMaterno" onkeypress="return isAlphanumeric(event)"/>
                    </td>
                </tr>
                <tr>
          <td align="right"><strong> <bean:message key="texto.etiqueta.perfil"/>:</strong></td>
          <td align="left">         
            <html:select property="idPerfil" name="usuariosForm" styleClass="textfield">
            <html:optionsCollection name="usuariosForm" property="perfiles" value="idPerfil" label="nombrePerfil"/>
            </html:select>            
          </td>
        </tr>  
              <tr class="row2">
					<td align="right">
						<strong> <bean:message key="texto.etiqueta.entidad.nombreEntidad" />:</strong>
					</td>
					<td align="left">
						<html:select property="idEntidad" name="usuariosForm" styleClass="textfield">
							<html:optionsCollection name="usuariosForm" property="entidades" value="idEntidad" label="nombreEntidad" />
						</html:select>
					</td>
				</tr>  
        <tr class="row2">
        	<td align="right"><strong> <bean:message key="texto.etiqueta.formato.estado.actual"/>:</strong></td>
        	<td align="left">         
            <html:select property="estatus" name="usuariosForm" styleClass="textfield">
            	<html:optionsCollection name="usuariosForm" property="estadosCatalogos" value="estado" label="descripcion"/>
            </html:select>            
          </td>
        </tr>
        
                <tr >
                    <td align="right">
                    <strong><bean:message key="texto.etiqueta.login.nombreUsuario"/>:</strong>            
                    </td>
                    <td align="left">
                        <html:text styleClass="textfield" name="usuariosForm" maxlength="8" property="usuario" onkeypress="return isAlphanumericKeys(event)"/>
                    </td>
                </tr>
                <tr>
        <td colspan="2" align="center">
          <html:submit styleClass="submit"><bean:message key="boton.actualizar"/></html:submit>
           <html:button property="cargar" styleClass="submit"  onclick="location.href=regresar('/usuarios.do?do=cargar');" ><bean:message key="boton.regresar"/>
		  </html:button>
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

<script type="text/javascript"> 
	function regresar(parametro) {                                   		
            direccion = "<%=util.getContexto(request)%>" + parametro ;
			return direccion;          
    }
    
	function isAlphanumeric(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    
	    if ((charCode < 48 || charCode > 57)&& (charCode < 97 || charCode > 122) &&(charCode!=241)&&(charCode < 65 || charCode > 90)&&(charCode!=209)&&(charCode!=32)){
	        return false;
	        };
	}
	
	function isAlphanumericKeys(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    
	    if ((charCode < 48 || charCode > 57)&& (charCode < 97 || charCode > 122) &&(charCode!=241)&&(charCode < 65 || charCode > 90)&&(charCode!=209)&&(charCode!=95)){
	           return false;
	           };  
	}

</script>    