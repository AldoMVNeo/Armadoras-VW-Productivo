<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=util.getContexto(request)%>/neology/javascript/estado/cambiarEstado.js"
	type="text/javascript" language="Javascript"></script>
<script
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js"
	type="text/javascript" language="Javascript"></script>
<script
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/ajax.js"
	type="text/javascript" language="Javascript"></script>
<title><bean:message key="texto.etiqueta.cambiarEstado.titulo" /></title>
</head>
<body class="bodyContent">
	<html:form action="/cambiarEstado.do"
		onsubmit="realizarCambioEstado();">
		<html:hidden property="cambiar" value="true" />
		<html:hidden property="do" value="cambiar" />
		<html:hidden property="strCombinaciones" value="strCombinaciones" />
		<%-- Encabezado de Pagina  --%>

		<table width="100%" border="0" cellspacing="0" cellpadding="30">
			<tr>
				<td>
					<div class="bigText">
						<img
							src="<%=util.getContexto(request)%>/neology/recursos/imagenes/dotacionFormatos.gif"
							border="0" width="112" height="79" align="absmiddle">
						<bean:message key="texto.etiqueta.cambiarEstado.titulo" />
					</div>
					<hr />
				</td>
			</tr>
		</table>

		<table width="100%" border="0" cellspacing="1" cellpadding="5">
			<tr class="row2">
				<td align="right" width="50%"><strong> <bean:message
							key="texto.etiqueta.formato.tipoFormato" />:
				</strong></td>
				<td colspan="2" align="left" width="50%"><html:select
						styleClass="textfield" property="idTipoFormato">
						<html:optionsCollection name="cambiarEstadoForm"
							property="tipoFormatos" value="idTipoFormato"
							label="descripcionConFormato" />
					</html:select></td>
			</tr>
			<!--                  <tr>
                    <td align="right">
                       <strong> <bean:message key="texto.etiqueta.formato.entidad"/>:</strong>
                    </td>
                    <td align="left"> 
                        <html:select styleClass="textfield" property="idEntidad">
                            <html:option value="-1">- Seleccione una Entidad -</html:option>
                            <html:optionsCollection name="cambiarEstadoForm" property="entidades"
                                                    value="idEntidad" label="nombreEntidad"/>
                        </html:select>
                    </td>          
                </tr> 
-->
			<tr>
				<td align="right"><strong> <bean:message
							key="texto.etiqueta.formato.estado.actual" />:
				</strong></td>
<%-- 				<td align="left"><html:select styleClass="textfield" --%>
<%-- 						property="estado" onchange="enviarPagina();"> --%>
<td align="left"><html:select styleClass="textfield"
						property="estado" onchange="cambiaEstadoActual();">
						<html:option value="-1">- Seleccione el Estado Actual-</html:option>
						<html:optionsCollection name="cambiarEstadoForm"
							property="estados2" value="estado" label="descripcion" />
					</html:select>
				<td />
			</tr>
			<tr class="row2">
				<td align="right"><strong> <bean:message
							key="texto.etiqueta.formato.cambio.estado" />:
				</strong></td>
				<td align="left"><html:select styleClass="textfield"
						property="estadoNuevo">
						<html:option value="-1">- Seleccione un nuevo Estado -</html:option>
						<html:optionsCollection name="cambiarEstadoForm"
							property="estados" value="estado" label="descripcion" />
					</html:select>
				<td />
				<td align="left"><html:select styleClass="textfield"
						property="strNone" style="display:none">
						<html:optionsCollection name="cambiarEstadoForm"
							property="estados3" value="estado" label="descripcion" />
					</html:select>
				<td />
			</tr>
			<tr>
				<td align="right"><strong> <bean:message
							key="texto.etiqueta.formato.folio.inicial" />:
				</strong></td>
				<td align="left"><html:text styleClass="textfield"
						name="cambiarEstadoForm" maxlength="8" property="folioInicial"
						onblur="formatoFolioInicial();"
						onkeypress="return isNumber(event)" /></td>
			</tr>
			<tr class="row2">
				<td align="right"><strong><bean:message
							key="texto.etiqueta.formato.folio.final" />:</strong></td>
				<td align="left"><html:text styleClass="textfield"
						name="cambiarEstadoForm" maxlength="8" property="folioFinal"
						onblur="formatoFolioFinal();" onkeypress="return isNumber(event)" />
				</td>
			</tr>
			<tr>
				<%-- 				<td colspan="2" align="center"><html:submit styleClass="submit"> --%>
				<%-- 						<bean:message key="boton.estado.cambio" /> --%>
				<%-- 					</html:submit></td> --%>
				<td colspan="2" align="center">
					<button type="submit" class="submit" onclick="cambiarEstado();">
						<bean:message key="boton.estado.cambio" />
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
</html>

<script type="text/javascript">

document.getElementsByName("estadoNuevo")[0].disabled = true;
document.getElementsByName("estado")[0].value = -1;
document.getElementsByName("estadoNuevo")[0].value = -1;

var estadosLength=document.getElementsByName("estadoNuevo")[0].length;

for(var i=1;i<estadosLength;i++){
	
document.getElementsByName("estadoNuevo")[0][i].style.visibility = "hidden";
document.getElementsByName("estadoNuevo")[0][i].disabled = true;	


}

//alert("estados " + document.getElementById('estadoNuevo').length);
//document.getElementById('estadoNuevo').options.length=0;
//	var url="/cambiarEstado.do?do=mostrarCambiosPermitidos";
//	console.debug(url);
//	url=url + "&estado=" + document.getElementsByName("estado")[0].value + "&totalA="+document.getElementsByName("estadoNuevo")[0].length;	
<%--  return estadoNuevo('<%=util.getContexto(request)%>',url); --%>


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
		
		function cambiaEstadoActual(){ 
			
			document.getElementsByName("estadoNuevo")[0].selectedIndex = 0;
			
			if(document.getElementsByName("estado")[0].value==-1){
				document.getElementsByName("estadoNuevo")[0].disabled = true;
			}

			if(document.getElementsByName("estado")[0].value!=-1){
				document.getElementsByName("estadoNuevo")[0].disabled = false;		
			}
			
			
			var estadosLength=document.getElementsByName("strNone")[0].length;
			var estadoNuevoLength=document.getElementsByName("estadoNuevo")[0].length;
			var estadoActual=document.getElementsByName("estado")[0].value;
			var compare="";
			var alreadyCompared="";
			
			//Ocultar todo
			
			for(var z=1;z<estadoNuevoLength;z++){
				
				document.getElementsByName("estadoNuevo")[0][z].disabled = true;	
				document.getElementsByName("estadoNuevo")[0][z].style.visibility = "hidden";
				
				
			}
			
			for(var j=1;j<estadoNuevoLength;j++){
				
				compare="";
				compare=estadoActual+"-"+document.getElementsByName("estadoNuevo")[0][j].value;
				 
				for(var i=0;i<estadosLength;i++){	
				if(document.getElementsByName("strNone")[0][i].value==compare)
				{	
					if(document.getElementsByName("estadoNuevo")[0][j].style.visibility = "hidden"){
						
					document.getElementsByName("estadoNuevo")[0][j].disabled = false;	
					document.getElementsByName("estadoNuevo")[0][j].style.visibility = "visible";
					}
				}
				
				}
			}
			
		};
		
		function isNumber(evt) {
		    evt = (evt) ? evt : window.event;
		    var charCode = (evt.which) ? evt.which : evt.keyCode;
		    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		        return false;
		    }
		    return true;
		}
		
		function cambiarEstado()
		{ 
			 
			formatoFolioInicial();
			formatoFolioFinal();
		}

		//alert("estados " + document.getElementById('estadoNuevo').length);
		//document.getElementById('estadoNuevo').options.length=0;
//		 	var url="/cambiarEstado.do?do=mostrarCambiosPermitidos";
//		 	url=url + "&estado=" + document.getElementById("estado").value + "&totalA="+document.getElementById("estadoNuevo").length;	
		<%--    	return estadoNuevo('<%=util.getContexto(request)%>',url); --%>
		// }		
		
</script>