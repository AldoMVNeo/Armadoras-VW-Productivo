<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252"></meta>
<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=util.getContexto(request)%>/neology/javascript/dotacion/dotacion.js"
	type="text/javascript" language="Javascript"></script>
<script
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js"
	type="text/javascript" language="Javascript"></script>
<title><bean:message key="texto.etiqueta.formato.dotarF" /></title>
</head>
<body class="bodyContent">

	<%-- Encabezado de Pagina  --%>
	<table width="100%" border="0" cellspacing="0" cellpadding="30">
		<tr>
			<td>
				<div class=\"bigText\">
					<img
						src="<%=util.getContexto(request)%>/neology/recursos/imagenes/dotacionFormatos.gif"
						border="0" width="112" height="79" align="absmiddle" />
					<bean:message key="texto.etiqueta.formato.dotarF" />
				</div>
				<hr />
			</td>
		</tr>
	</table>
	<%-- Fin de Encabezado --%>
	<html:form action="/dotacion.do" onsubmit="realizarDotacion();">
		<html:hidden property="dotar" value="true" />
		<html:hidden property="do" value="dotar" />
		<table width="100%" border="0" cellspacing="1" cellpadding="5">
			<tr class="row2">
				<td align="right" width="50%"><strong><bean:message
							key="texto.etiqueta.formato.entidad" />:</strong></td>
				<td colspan="2" align="left" width="50%"><bean:write
						name="dotacionForm" property="descripcionEntidad" /> <html:hidden
						property="idEntidad" name="dotacionForm" /></td>
			</tr>
			<tr>
				<td align="right"><strong> <bean:message
							key="texto.etiqueta.formato.tipoFormato" />:
				</strong></td>
				<td align="left">
<%-- 				<html:select property="idTipoFormato" --%>
<%-- 						name="dotacionForm" styleClass="textfield"> --%>
<%-- 						<html:option value="-1"> --%>
<%-- 							<bean:message key="combo.opcion.seleccionar" /> --%>
<%-- 						</html:option> --%>
<%-- 						<html:optionsCollection name="dotacionForm" --%>
<%-- 							property="tipoFormatos" value="idTipoFormato" --%>
<%-- 							label="descripcionConFormato" /> --%>
<%-- 					</html:select> --%>
<html:select property="idTipoFormato" name="dotacionForm" styleClass="textfield" value="1" >
            <html:optionsCollection name="dotacionForm" property="tipoFormatos" value="idTipoFormato" label="descripcionConFormato" />
            </html:select>   
					</td>
			</tr>
			<tr class="row2">
				<td align="right"><strong><bean:message
							key="texto.etiqueta.formato.folio.inicial" />:</strong></td>
				<td align="left"><html:text name="dotacionForm" maxlength="8"
						onblur="formatoFolioInicial();"
						onkeypress="return isNumber(event)" property="folioInicial"
						styleClass="textfield" /></td>
			</tr>
			<tr>
				<td align="right"><strong><bean:message
							key="texto.etiqueta.formato.folio.final" />:</strong></td>
				<td align="left"><html:text name="dotacionForm" maxlength="8"
						onblur="formatoFolioFinal();" onkeypress="return isNumber(event)"
						property="folioFinal" styleClass="textfield" /></td>
			</tr>
			<tr class="row2">
				<td align="right"><strong><bean:message
							key="texto.etiqueta.dotar.caja" />:</strong></td>
				<td align="left"><html:text name="dotacionForm" maxlength="8"
						onblur="formatoFolioFinal();" onkeypress="return isNumber(event)"
						property="rango" styleClass="textfield" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<%--           <html:button property="dotar" styleClass="submit"  onclick="dotar_formatos();"> --%>
					<%-- <bean:message key="boton.formato.dotar"/> --%> <%-- </html:button> --%>
					<button class="submit" onclick="dotar_formatos();">
						<bean:message key="boton.formato.dotar" />
					</button>
				</td>
			</tr>
		</table>
		<br>
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
	</html:form>
</body>


<script language="javascript" type="text/javascript">
function dotar_formatos()
{ 
	
	formatoFolioInicial();
	formatoFolioFinal();
	var rango="";
	rango = document.getElementById("rango").value;
	if(rango.length>5){
		rango=rango.slice(rango.indexOf("-")+1,rango.length);
		folIni = rango.slice(0,rango.indexOf("-"));
		document.getElementById("folioInicial").value=folIni;
		rango=rango.slice(rango.indexOf("-")+1,rango.length);
		document.getElementById("folioFinal").value=rango;	
	}
	realizarDotacion();
	document.dotacionForm.submit();
}

function formatoFolioInicial(){
	
	var folio=document.getElementsByName("folioInicial")[0].value;

	if(folio.length<8 && folio!=0){
	  
	  if(folio.length==7){
	  
	  document.getElementsByName("folioInicial")[0].value="0"+document.getElementsByName("folioInicial")[0].value;
	  
	  };
	  
	  if(folio.length==6&& folio!=0){
	  
	  document.getElementsByName("folioInicial")[0].value="00"+document.getElementsByName("folioInicial")[0].value;
	  
	  };
	  
	  if(folio.length==5&& folio!=0){
	  
	  document.getElementsByName("folioInicial")[0].value="000"+document.getElementsByName("folioInicial")[0].value;
	  
	  };
	  
	  if(folio.length==4&& folio!=0){
	  
	  document.getElementsByName("folioInicial")[0].value="0000"+document.getElementsByName("folioInicial")[0].value;
	  
	  };
	  
	  if(folio.length==3&& folio!=0){
	  
	  document.getElementsByName("folioInicial")[0].value="00000"+document.getElementsByName("folioInicial")[0].value;
	  
	  };
	  
	  if(folio.length==2&& folio!=0){
	  
	  document.getElementsByName("folioInicial")[0].value="000000"+document.getElementsByName("folioInicial")[0].value;
	  
	  };
	  
	  if(folio.length==1&& folio!=0){
	  
	  document.getElementsByName("folioInicial")[0].value="0000000"+document.getElementsByName("folioInicial")[0].value;
	  
	  };

	};};
	
	function formatoFolioFinal(){
		
		var folio=document.getElementsByName("folioFinal")[0].value;

		if(folio.length<8){
		  
		  if(folio.length==7&& folio!=0){
		  
		  document.getElementsByName("folioFinal")[0].value="0"+document.getElementsByName("folioFinal")[0].value;
		  
		  };
		  
		  if(folio.length==6&& folio!=0){
		  
		  document.getElementsByName("folioFinal")[0].value="00"+document.getElementsByName("folioFinal")[0].value;
		  
		  };
		  
		  if(folio.length==5&& folio!=0){
		  
		  document.getElementsByName("folioFinal")[0].value="000"+document.getElementsByName("folioFinal")[0].value;
		  
		  };
		  
		  if(folio.length==4&& folio!=0){
		  
		  document.getElementsByName("folioFinal")[0].value="0000"+document.getElementsByName("folioFinal")[0].value;
		  
		  };
		  
		  if(folio.length==3&& folio!=0){
		  
		  document.getElementsByName("folioFinal")[0].value="00000"+document.getElementsByName("folioFinal")[0].value;
		  
		  };
		  
		  if(folio.length==2&& folio!=0){
		  
		  document.getElementsByName("folioFinal")[0].value="000000"+document.getElementsByName("folioFinal")[0].value;
		  
		  };
		  
		  if(folio.length==1&& folio!=0){
		  
		  document.getElementsByName("folioFinal")[0].value="0000000"+document.getElementsByName("folioFinal")[0].value;
		  
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

</script>
</html>

