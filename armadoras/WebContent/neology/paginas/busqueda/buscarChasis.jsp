<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request" />
<jsp:useBean id="fechaUtil" class="neology.util.FechaUtil"
	scope="request" />
<jsp:useBean id="formBusquedaVin" class="neology.vc.actions.busqueda.BusquedaNivAction" scope="request" />	
<%@ page import="org.joda.time.DateTime"%>
<html>
<head>
<title><bean:message
		key="texto.etiqueta.reportes.constanciasInscripcion" /></title>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js"
	type="text/javascript" language="Javascript"></script>

</head>
<body class="bodyContent">
	<%-- Encabezado de Pagina  --%>
	<table width="100%" border="0" cellspacing="0" cellpadding="30">
		<tr>
			<td>
				<div class=\"bigText\">
					<img
						src="<%=util.getContexto(request)%>/neology/recursos/imagenes/busquedaVin.gif"
						border="0" width="121" height="79" align="absmiddle" />
					<bean:message key="texto.buscar.chasis" />
				</div>
				<hr />
			</td>
		</tr>
	</table>
	<%-- Fin de Encabezado --%>
	<html:form action="/busquedaNiv.do">
		<html:hidden property="do" value="buscar" />
		<html:hidden property="isAdmin" value="<%=formBusquedaVin.isAdmin(request)%>" name="isAdmin"/>
		<table width="100%" border="0" cellspacing="1" cellpadding="5">
			<tr>
				<td align="right" width="50%"><strong> <bean:message
							key="texto.etiqueta.vin" /> :
				</strong></td>
				<td align="left" width="10%"><html:text styleClass="textfield"
						name="busquedaNivForm" maxlength="17" property="vin" onkeyup="sub(event);"
						onkeypress="return isAlphanumeric(event)" /></td>
				<td align="left">
				    <html:submit styleClass="submit">
						<bean:message key="boton.general.buscar" />
					</html:submit></td>
			</tr>
		</table>
		<br>
		<table>
			<tr>
				<td></td>
				<td></td>
				<td align="left" width="23%" style="visibility:<%=formBusquedaVin.isAdmin(request)%> "><html:checkbox 
						property="consultaBD"/><strong><bean:message
							key="texto.etiqueta.consulta.respaldo" /></strong></td>
				<td></td>
			</tr>
		</table>
		<%-- Mensajes de Error --%>
		<div id="divMensajesError" align="center">
			<table border="0">
				<logic:messagesPresent>
					<tr>
						<td><html:messages id="errores" header="errors.header"
								footer="errors.footer">
								<li><bean:write name="errores" /></li>
								<br>
							</html:messages></td>
					</tr>
				</logic:messagesPresent>
			</table>
		</div>
		<%-- Fin Mensajes de Error --%>

		<%-- Lista de  Totales--%>
		<logic:present property="datos" name="busquedaNivForm">
			<logic:notEmpty property="datos" name="busquedaNivForm">
				<table width="100%" border="0" cellpadding="5" cellspacing="1">
					<tbody>
						<%-- cabezeras--%>
						<tr class="tableHead">
							<td><bean:message key="texto.etiqueta.impresion.vin" /></td>
							<td><bean:message key="texto.etiqueta.formato.estado.actual" /></td>
							<td><bean:message key="texto.etiqueta.formato.folio" /></td>
							<td><bean:message key="texto.buscar.noChip" /></td>
							<td><bean:message
									key="texto.etiqueta.impresion.fechaRegistro" /></td>
							<td><bean:message
									key="texto.etiqueta.formato.usuario.realizado" /></td>
						</tr>
						<%-- Iteración de los Totales--%>
						<logic:iterate id="datos" property="datos" name="busquedaNivForm">
							<tr>
								<td><bean:write name="datos" property="vin" /></td>
								<td><bean:write name="datos" property="estado.descripcion" />
								</td>
								<td><bean:write name="datos" property="folio" /></td>
								<td><bean:write name="datos" property="numeroChip" /></td>
								<td><bean:define id="fecha" name="datos"
										property="fechaRegistro" /> <%=fechaUtil.getFechaLarga((DateTime)fecha)%>
								</td>
								<td><bean:write name="datos" property="usuario.nombres" />
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</logic:notEmpty>
		</logic:present>
		<br />
		<%-- Historial Orden --%>
		<logic:present name="listaHistorial">
			<logic:notEmpty name="listaHistorial">
				<table width="100%" border="0" cellpadding="5" cellspacing="1">
					<tr>
						<td colspan="6"><strong><bean:message
									key="texto.etiqueta.historial" /></strong>
						<td>
					</tr>

					<%-- cabezeras--%>
					<tr class="tableHead">
						<td><bean:message key="texto.etiqueta.impresion.vin" /></td>
						<td><bean:message key="texto.etiqueta.formato.estado.actual" />
						</td>
						<td><bean:message key="texto.etiqueta.formato.folio" /></td>
						<td><bean:message key="texto.buscar.noChip" /></td>
						<td><bean:message key="texto.etiqueta.orden.fechaRegistro" />
						</td>
						<td><bean:message
								key="texto.etiqueta.formato.usuario.realizado" /></td>
					</tr>
					<logic:iterate id="ord" name="listaHistorial">

						<tr>
							<td><bean:write name="ord" property="vin" /></td>
							<td><bean:write name="ord" property="estado.descripcion" /></td>
							<td><bean:write name="ord" property="folio" /></td>
							<td><bean:write name="ord" property="numeroChip" /></td>
							<td><bean:define id="fecha" name="ord"
									property="fechaRegistro" /> <%=neology.util.FechaUtil.getFechaLarga((DateTime) fecha)%>
							</td>
							<td><bean:write name="ord"
									property="idUsuarioModifico.nombres" /></td>
						</tr>
					</logic:iterate>
				</table>
			</logic:notEmpty>
		</logic:present>
	</html:form>
</body>
</html>

<script type="text/javascript">

function isAlphanumeric(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    
	    if ((charCode < 48 || charCode > 57)&& (charCode < 97 || charCode > 122) &&(charCode!=241)&&(charCode < 65 || charCode > 90)&&(charCode!=209)){
	        return false;
	        };
	}
	
function sub(event){
	
	
	 if(event.which == 13)
	    {
		 document.forms[0].submit();
	    }
	
}	
</script>
