<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request"/>
<html>
	<head>
		<title><bean:message key="producto"/>
		</title>
		<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js" type="text/javascript" language="Javascript"></script>
		<script src="<%=util.getContexto(request)%>/neology/javascript/login/login.js" type="text/javascript" language="Javascript"></script>
		 <link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
	</head>
	<body class="bodyContent">
		<div align="center" style="padding-top:20px;">
			<img src="neology/recursos/imagenes/logo_sistema.gif" border="0" title="Logo Volkswagen Repuve">
		</div>
		<html:form action="/login.do" onsubmit="entrarSistema();">
	<div id="login" align="center">
			<table width="50%" border="0" cellspacing="0" cellpadding="5">
				<tr>
					<td colspan="2" align="center">
						<img src="neology/recursos/imagenes/login.gif" border="0" title="Login"/>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong><bean:message key="texto.etiqueta.login.nombreUsuario"/>:
						</strong>
					</td>
					
					<td align="left">
						<html:text name="loginForm" property="login" styleClass="textfield" />
						<br>
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong><bean:message key="texto.etiqueta.login.contrasena" />:
						</strong>
					</td>					
					<td align="left">
						<html:password name="loginForm" property="contrasena" styleClass="textfield" />
					</td>
				</tr>				
				<tr>
					<td colspan="2" align="center">
						<html:submit styleClass="submit">
							<bean:message key="texto.etiqueta.login.entrar" />
						</html:submit>
					</td>
				</tr>
			</table>
		</div>

			<br>
		</html:form>
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
	</body>
</html>
