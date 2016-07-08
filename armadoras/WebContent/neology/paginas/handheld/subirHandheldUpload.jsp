<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request" />
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1252"></meta>
		<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet"
			type="text/css" />
		<script
			src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js"
			type="text/javascript" language="Javascript"></script>
		<script language="javascript" type="text/javascript">
		function subirHandheld() {   
		    mostrarDiv(false,"divMensajesError");
		    habilitarBotones("subirHandheldForm",false);
		    divMensajeEspera("subirHandheldForm","La subida del archivo puede tardar algunos minutos, sea paciente...");
		}
		</script>
		<title><bean:message key="texto.etiqueta.handheld.subir" />
		</title>
	</head>
	<body class="bodyContent">
		<%-- Encabezado de Pagina  --%>
		<table width="100%" border="0" cellspacing="0" cellpadding="30">
			<tr>
				<td>
					<div class="bigText">
						<img src="<%=util.getContexto(request)%>/neology/recursos/imagenes/handheld.gif" border="0"
							width="82" align="absmiddle" />
						<bean:message key="texto.etiqueta.handheld.subir" />
					</div>
					<hr />
				</td>
			</tr>
		</table>
		<%-- Fin de Encabezado --%>
		<html:form action="/subirHandheld.do" method="post" enctype="multipart/form-data"
			onsubmit="subirHandheld();">
			<html:hidden property="do" value="subir" />
			<html:hidden property="subir" value="true" />
			<table width="100%" border="0" cellspacing="1" cellpadding="5">
				<tr class="row2">
					<td align="right">
						<strong><bean:message key="texto.etiqueta.handheld.archivo" />:</strong>
					</td>
					<td align="left">
						<html:file name="subirHandheldForm" property="theFile" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<html:submit styleClass="submit">
							<bean:message key="boton.handheld.subir" />
						</html:submit>
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

