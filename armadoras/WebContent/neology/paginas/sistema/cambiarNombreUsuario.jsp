<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request" />
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
		<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
		<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js"
			type="text/javascript" language="Javascript"></script>
		<title><bean:message key="texto.etiqueta.sistema.cambiarUsuario" /></title>
	</head>
	<body class="bodyContent">
		<%-- Encabezado de Pagina  --%>
		<table width="100%" border="0" cellspacing="0" cellpadding="30">
			<tr>
				<td>
					<div class=\"bigText\">
						<img src="<%=util.getContexto(request)%>/neology/recursos/imagenes/usuarios.gif" border="0" width="112"
							height="79" align="absmiddle" />
						<bean:message key="texto.etiqueta.sistema.cambiarUsuario" />
					</div>
					<hr />
				</td>
			</tr>
		</table>
		<%-- Fin de Encabezado --%>
		<html:form action="/cambiarNombreUsuario.do">
		<html:hidden property="cambiar" value="true" />
		<html:hidden property="do" value="actualizar" />
		<table  width="100%" border="0" cellspacing="1" cellpadding="5">
				<tr class="row2">
					<td align="right" width="50%">
						<strong><bean:message key="texto.etiqueta.login.nombreUsuario" />:</strong>
					</td>
					<td align="left" width="50%">
					    <html:select styleClass="textfield" property="nombreUsuario">
										<html:optionsCollection name="cambiarNombreUsuarioForm" property="usuarios" value="usuario"
											label="usuario" />
						</html:select>
					
					</td>
				</tr>
				<tr style="display:none">
					<td align="right" >
						<strong><bean:message key="texto.etiqueta.usuario.contrasena.anterior" />:</strong>
					</td>
					<td align="left">
						<html:password styleClass="textfield" name="cambiarNombreUsuarioForm" maxlength="10" property="contrasenaAnterior"></html:password>
					</td>
				</tr>
				<tr class="row2">
					<td align="right">
						<strong><bean:message key="texto.etiqueta.usuario.contrasena.nueva" />:</strong>
					</td>
					<td align="left">
						<html:password  styleClass="textfield" name="cambiarNombreUsuarioForm" maxlength="10" property="contrasenaNueva" onkeypress="return isAlphanumericKeys(event)"></html:password>
					<bean:message key="texto.etiqueta.numero.maximo.contrasena" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong><bean:message key="texto.etiqueta.usuario.repetir.contrasena" />:</strong>
					</td>
					<td align="left">

						<html:password styleClass="textfield" name="cambiarNombreUsuarioForm" maxlength="10" property="repetirContrasena" onkeypress="return isAlphanumericKeys(event)"></html:password>
						<bean:message key="texto.etiqueta.numero.maximo.contrasena" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<html:submit styleClass="submit">
							<bean:message key="boton.actualizar" />
						</html:submit>
					</td>
					<td align="left">
						<html:cancel styleClass="submit">
							<bean:message key="boton.cancelar" />
						</html:cancel>
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
									<li>
										<bean:write name="errores" />
									</li>
									<br>
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
	
	function isAlphanumericKeys(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    
	    if ((charCode < 48 || charCode > 57)&& (charCode < 97 || charCode > 122) &&(charCode!=241)&&(charCode < 65 || charCode > 90)&&(charCode!=209)&&(charCode!=95)){
	           return false;
	           };  
	}

</script>    
