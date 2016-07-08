<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request" />
<jsp:useBean id="formBusquedaFormato" class="neology.vc.actions.busqueda.BusquedaFormatoAction" scope="request" />	
<%@ page import="org.joda.time.DateTime"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1252"></meta>
		<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
		<title><bean:message key="texto.etiqueta.busqueda.formato" /></title>
	</head>
	<body class="bodyContent">

		<%-- Encabezado de Pagina  --%>
		<table width="100%" border="0" cellspacing="0" cellpadding="30">
			<tr>
				<td>
					<div class=\"bigText\">
						<img src="<%=util.getContexto(request)%>/neology/recursos/imagenes/busquedaFormato.gif" border="0" width="121"
							height="79" align="absmiddle" />
						<bean:message key="texto.etiqueta.busqueda.formato" />
					</div>
					<hr />
				</td>
			</tr>
		</table>
		<%-- Fin de Encabezado --%>
		<html:form action="/busquedaFormato.do">
			<html:hidden property="do" value="buscar" />
			<table width="100%" border="0" cellspacing="1" cellpadding="5">
				<tr>
					<td align="right" width="50%">
						<strong><bean:message key="texto.etiqueta.formato.folio" />:</strong>
					</td>
					<td align="left" width="10%">
						<html:text name="busquedaFormatoForm" property="folio" styleClass="textfield" onblur="formatoFolio();" onkeypress="return isNumber(event)" maxlength="8"/>
					</td>
					<td align="left">
						<html:submit property="buscar" styleClass="submit" onclick="busquedaFormatos();"> 
							<bean:message key="boton.general.buscar" />
						</html:submit>
					</td>
				</tr>
			</table>
			<br>
		<table>
			<tr>
				<td></td>
				<td></td>
				<td align="left" width="23%" style="visibility:<%=formBusquedaFormato.isAdmin(request)%> "><html:checkbox 
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
			<%-- Situacion actual del Formato --%>
			<logic:present name="formatoActual">
				<logic:notEmpty name="formatoActual">					
					<logic:iterate id="formato" name="formatoActual">
						<table width="70%" border="0" cellspacing="1" cellpadding="5">
							<tr>
							<td colspan="6">
							<strong><bean:message key="texto.etiqueta.formato.situacion.actual" /></strong>
							<td>
							</tr>
							
							<%-- cabezeras--%>
							<tr class="tableHead">
								<td>
									<bean:message key="texto.etiqueta.formato.folio" />
								</td>
								<td>
									<bean:message key="texto.etiqueta.formato.estado.actual" />
								</td>
								<td>
									<bean:message key="texto.etiqueta.impresion.vin" />
								</td>
								<td>
									<bean:message key="texto.buscar.noChip" />
								</td>
								<td>
									<bean:message key="texto.etiqueta.orden.fechaRegistro" />
								</td>
								<td>
									<bean:message key="texto.etiqueta.formato.usuario.realizado" />
								</td>
							</tr>

							<tr>
								<td>
									<bean:write name="formato" property="formatoId.folio" />
								</td>
								<td>
									<bean:write name="formato" property="estado.descripcion" />
								</td>
								<td>
									<bean:write name="formato" property="ordenImpresion.vin" />
								</td>
								<td>
									<bean:write name="formato" property="ordenImpresion.numeroChip" />
								</td>
								<td>
									<bean:define id="fecha" name="formato" property="fechaRegistro" />
									<%=neology.util.FechaUtil.getFechaLarga((DateTime) fecha)%>
								</td>
								<td>
									<bean:write name="formato" property="usuarioModifico.nombres" />
								</td>
							</tr>
						</table>
					</logic:iterate>
				</logic:notEmpty>
			</logic:present>
			<br>
			<%-- Historial Formato --%>
			<logic:present name="formatoHistorial">
				<logic:notEmpty name="formatoHistorial">		
				<table width="70%" border="0" cellspacing="1" cellpadding="5">
							<tr>
							<td colspan="6">
							<strong><bean:message key="texto.etiqueta.historial" /></strong>
							<td>
							</tr>
							
							<%-- cabezeras--%>
							<tr class="tableHead">
								<td>
									<bean:message key="texto.etiqueta.formato.folio" />
								</td>
								<td>
									<bean:message key="texto.etiqueta.formato.estado.actual" />
								</td>
								<td>
									<bean:message key="texto.etiqueta.impresion.vin" />
								</td>
								<td>
									<bean:message key="texto.buscar.noChip" />
								</td>
								<td>
									<bean:message key="texto.etiqueta.orden.fechaRegistro" />
								</td>
								<td>
									<bean:message key="texto.etiqueta.formato.usuario.realizado" />
								</td>
							</tr>			
					<logic:iterate id="formato" name="formatoHistorial">	

							<tr>
								<td>
									<bean:write name="formato" property="folio" />
								</td>
								<td>
									<bean:write name="formato" property="descripcionEstado" />
								</td>
								<td>
									<bean:write name="formato" property="niv" />
								</td>
								<td>
									<bean:write name="formato" property="numeroChip" />
								</td>
								<td>
									<bean:define id="fecha" name="formato" property="fechaRegistro" />
									<%=neology.util.FechaUtil.getFechaLarga((DateTime) fecha)%>
								</td>
								<td>
									<bean:write name="formato" property="nombreUsuario" />
								</td>
							</tr>
							</logic:iterate>
						</table>					
				</logic:notEmpty>
			</logic:present>
		</html:form>
</html>

<script type="text/javascript">
function formatoFolio(){
	
	var folio=document.getElementsByName("folio")[0].value;

	if(folio.length<8 && folio!=0){
	  
	  if(folio.length==7){
	  
	  document.getElementsByName("folio")[0].value="0"+document.getElementsByName("folio")[0].value;
	  
	  };
	  
	  if(folio.length==6&& folio!=0){
	  
	  document.getElementsByName("folio")[0].value="00"+document.getElementsByName("folio")[0].value;
	  
	  };
	  
	  if(folio.length==5&& folio!=0){
	  
	  document.getElementsByName("folio")[0].value="000"+document.getElementsByName("folio")[0].value;
	  
	  };
	  
	  if(folio.length==4&& folio!=0){
	  
	  document.getElementsByName("folio")[0].value="0000"+document.getElementsByName("folio")[0].value;
	  
	  };
	  
	  if(folio.length==3&& folio!=0){
	  
	  document.getElementsByName("folio")[0].value="00000"+document.getElementsByName("folio")[0].value;
	  
	  };
	  
	  if(folio.length==2&& folio!=0){
	  
	  document.getElementsByName("folio")[0].value="000000"+document.getElementsByName("folio")[0].value;
	  
	  };
	  
	  if(folio.length==1&& folio!=0){
	  
	  document.getElementsByName("folio")[0].value="0000000"+document.getElementsByName("folio")[0].value;
	  
	  };

	};};
	
	function isNumber(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	        return false;
	    }
	    return true;
	}
	
	function busquedaFormatos()
	{ 		 
		formatoFolio();
	}
	
</script>