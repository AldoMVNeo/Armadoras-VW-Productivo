<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request" />
<jsp:useBean id="configuracionHistoricoAction" class="neology.vc.actions.sistema.ConfiguracionHistoricoAction"
	scope="request" />	
<%@ page import="java.text.SimpleDateFormat"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252"></meta>
<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css"
	rel="stylesheet" type="text/css" />
<link href="<%=util.getContexto(request)%>/neology/estilos/tcal.css"
	rel="stylesheet" type="text/css" />	
<link
	href="<%=util.getContexto(request)%>/neology/estilos/timepicki.css"
	rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/tcal.js"></script>	
<script
	src="<%=util.getContexto(request)%>/neology/javascript/dotacion/dotacion.js"
	type="text/javascript" language="Javascript"></script>
<script
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/jquery.js"
	type="text/javascript" language="Javascript"></script>
<script
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/jquery.min.js"
	type="text/javascript" language="Javascript"></script>
<script
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/timepicki.js"
	type="text/javascript" language="Javascript"></script>
<script
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js"
	type="text/javascript" language="Javascript"></script>
<title><bean:message
		key="texto.etiqueta.configuracion.historico" /></title>
</head>
<body class="bodyContent">

	<%-- Encabezado de Pagina  --%>
	<table width="100%" border="0" cellspacing="1" cellpadding="5">
		<tr>
			<td>
				<div class=\"bigText\">
					<img
						src="<%=util.getContexto(request)%>/neology/recursos/imagenes/dotacionFormatos.gif"
						border="0" width="112" height="79" align="absmiddle" />
					<bean:message key="texto.etiqueta.configuracion.historico" />
				</div>
				<hr />
			</td>
		</tr>
	</table>
	<%-- Fin de Encabezado --%>
	<html:form action="/configuracionHistorico">
		<html:hidden property="do" value="configuracionHistoricoGuardar" />
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<!-- 			<tr class="row2"> -->
			<%-- 				<td align="right" width="55%"><strong><bean:message --%>
			<%-- 							key="texto.etiqueta.configuracion.meses" />:</strong></td> --%>
			<%-- 				<td align="left" width="55%">&nbsp;&nbsp;&nbsp;<html:select styleClass="textfield" property="noMeses"> --%>
			<%-- 						<html:optionsCollection name="configuracionHistoricoForm" --%>
			<%-- 							property="noMesesList" value="noMesesResp" label="noMesesResp" /> --%>
			<%-- 					</html:select></td> --%>
			<!-- 			</tr> -->
			<tr class="row2">
				<td align="right" width="55%"><strong><bean:message
							key="texto.etiqueta.configuracion.fecha.minima" />:</strong></td>
				<td align="left" width="55%">&nbsp;<html:text readonly="true"
						styleClass="tcal" property="strFechaCalculo">
					</html:text></td>
			</tr>
			<tr>
			<td align="right" width="55%"><strong><bean:message
							key="texto.etiqueta.historico.fechaConfig" />:</strong></td>
			<td align="left"><html:text property="strFechaConfig" 
						readonly="true" styleClass="tcal" /> <!-- 				<a --> <!-- 					onclick="cal1.select(document.forms[0].fechaInicial,'anchor1x','dd/MM/yyyy'); return false;" -->
			</tr>
			<tr class="row2">
				<td align="right"><strong><bean:message
							key="texto.etiqueta.configuracion.horaConfig" />:</strong></td>
				<td><html:text property="horaConfig" readonly="true" /></td>
			</tr>
			<tr>
			<td align="right" id="tdEjecutaJob"><html:checkbox property="ejecutaJob" /><strong>
						<bean:message key="texto.etiqueta.configuracion.ejecutar" />
				</strong></td>
			<td></td>	
			</tr>
			<tr>
				<td align="right">
				   <button class="submit" onclick="showMessage();" type="button" id="buttonShowMessage">
						<bean:message key="boton.guardar" />
					</button></td>
				<td></td>
			</tr>	
			<tr>
				<td align="right"><label id="labelConfirm" hidden="true"><strong><bean:message
							key="texto.etiqueta.configuracion.confirm" /></strong></label></td>
				<td><strong><label id="labelNoDias"></label></strong></td>
				<td><strong><html:text property="noDias" readonly="true" /></strong></td>				
			</tr>
			<tr class="row2">
				<td align="right">
				    <button class="submit" type="submit" id="buttonConfirm">
						<bean:message key="boton.guardar" />
					</button>
				</td>
				<td>
				 <button class="submit" type="button" id="buttonCancel" onclick="cancel();">
						<bean:message key="boton.cancelar" />
					</button>
				</td>
			</tr>	
		</table>
		<br/>
		<logic:present name="listaConfigs">
    	    	<logic:notEmpty name="listaConfigs">    
		    		<table class="tablaFiltro" id=tablas width="100%" border="0" cellpadding="5" cellspacing="1">
		    			<tbody> <%-- cabezeras--%>
						<%-- cabezeras--%>
							<tr class="tableHead">
								<td style="width: 5%"><bean:message key="texto.etiqueta.usuario.id"/></td>								
								<td><bean:message key="texto.etiqueta.historico.fechaConfig"/></td>
								<td><bean:message key="texto.etiqueta.historico.diasResp"/></td>
								<td><bean:message key="texto.etiqueta.historico.fechaCalculo"/></td>
								<td style="width: 13%"><bean:message key="texto.etiqueta.historico.diastotales"/></td>
								<td style="width: 13%"><bean:message key="texto.etiqueta.historico.usuario"/></td>
								<td><bean:message key="texto.etiqueta.historico.estatus"/></td>	  
								<td><bean:message key="texto.etiqueta.historico.activo"/></td>	
								<td></td>          	                              
	    	    			</tr>
	    		     		<%-- Iteración de la Lista de Configuraciones--%>
	    	    		  	<% boolean band=true; %>
			    		<logic:iterate id="listaConfigs" name="listaConfigs">
    			    		<% band=!band; %>                                
        			    	<tr <%= band?"class='row2'":"class=''"%>> 
    			            	<td>
        			        		<bean:write name="listaConfigs" property="idParametro"/>
	            			    </td>
    	            			<td>
	    	            			<bean:define id="fecha" name="listaConfigs"
										property="fechaConfig" /> <%=new SimpleDateFormat("dd/MM/yyyy").format(fecha)%>
								</td>
								<td>
	    	            			<bean:write name="listaConfigs" property="noDias"/>
	    		            	</td>
	    		            	<td>
	    		            	<bean:define id="fecha" name="listaConfigs"
										property="fechaConfig" /> 
								<bean:define id="diasResp" name="listaConfigs"
										property="noDias" /> 		
									<%=configuracionHistoricoAction.strCalculoFecha(new SimpleDateFormat("MM/dd/yyyy").format(fecha),diasResp.toString())%>
	    		            	</td>
	    		            	<td>
	    	            			<bean:write name="listaConfigs" property="intCantidadTotal"/>
	    		            	</td>
	    		            	<td>
	    	            			<bean:define name="listaConfigs" property="idUsuario" id="idUsuario"/>
	    	            			<%=configuracionHistoricoAction.strNombreUsuario(idUsuario.toString())%>
	    		            	</td>
	    		            	<td>
	    	            			<bean:define id="estatus" name="listaConfigs"
										property="estatus" /> <%=configuracionHistoricoAction.strEstatus(estatus.toString())%>
	    		            	</td>
	    		            	<td>
	    		            	<bean:define id="estatus" name="listaConfigs"
										property="estatus" />
	    		            	<html:link action="/configuracionHistorico.do?do=configuracionHistoricoEliminar"
	    		            	paramName="listaConfigs" paramProperty="idParametro"  paramId="idParametro" 
	    		            	> 
	    		            	<bean:message key="texto.etiqueta.historico.eliminar" />
	    		            	</html:link>
	    		            	</td>
	    	    		    </tr> 
				    	</logic:iterate>
				    	</tbody>
					</table>
				</logic:notEmpty>
			</logic:present>
	</html:form>
</body>
</html>

<script type='text/javascript'>

document.getElementById("buttonConfirm").style.visibility = "hidden";
document.getElementById("buttonCancel").style.visibility = "hidden";
$('input[name=noDias]').hide();
$("#labelNoDias").hide();

	$("[name='horaConfig']").timepicki({
		show_meridian:false,
		increase_direction:'up',
		max_hour_value:23,
		min_hour_value:0});

	function showMessage() {
		
		
		$("#buttonShowMessage").hide();
		$("#labelConfirm").show();
		document.getElementById("buttonConfirm").style.visibility = "visible";
		document.getElementById("buttonCancel").style.visibility = "visible";
		document.getElementById("tdEjecutaJob").style.visibility = "hidden";
		
		$("#labelNoDias").hide();
		$('input[name=strFechaConfig]').hide();
		$('input[name=horaConfig]').hide();
		$('input[name=strFechaCalculo]').hide();
		
		//Calcular días
		var fechaCalculo=$('input[name=strFechaCalculo]').val();
		var fecha = $('input[name=strFechaConfig]').val();
		
		var arrFecha = fecha.split("/");
		var arrFechaCalculo = fechaCalculo.split("/");
		var fechaFormato=arrFecha[1]+"/"+arrFecha[0]+"/"+arrFecha[2];
		var fechaCalculoFormato=arrFechaCalculo[1]+"/"+arrFechaCalculo[0]+"/"+arrFechaCalculo[2];
		
		var newdate = new Date(fechaFormato);
		var newdateCalc = new Date(fechaCalculoFormato);
		
		var dif = newdate - newdateCalc;
		var dias = Math.floor(dif / (1000 * 60 * 60 * 24)); 
		
		$('input[name=noDias]').val(dias);
		$("#labelNoDias").text(dias);

	};
	
	function cancel() {
		
		$("#buttonShowMessage").show();
		$("#labelConfirm").hide();
		document.getElementById("buttonConfirm").style.visibility = "hidden";
		document.getElementById("buttonCancel").style.visibility = "hidden";
		document.getElementById("tdEjecutaJob").style.visibility = "visible";
		$('input[name=horaConfig]').show();
		$('input[name=strFechaCalculo]').show();
		$('input[name=strFechaConfig]').show();
		$("#labelNoDias").hide();
		
	};
	
	function isNumber(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	        return false;
	    }
	    return true;
	}
	
</script>

