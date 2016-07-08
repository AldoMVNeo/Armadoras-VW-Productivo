<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request" />
<jsp:useBean id="formularioReportes"
	class="neology.vc.actions.reportes.ReporteConstanciasIAction"
	scope="request" />
<html>
<head>
<title><bean:message
		key="texto.etiqueta.reportes.constanciasInscripcion" /></title>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css"
	rel="stylesheet" type="text/css" />
<link href="<%=util.getContexto(request)%>/neology/estilos/tcal.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=util.getContexto(request)%>/neology/javascript/reportes/reportes.js"
	type="text/javascript" language="Javascript"></script>
<script
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js"
	type="text/javascript" language="Javascript"></script>
<!-- <script language="javascript" type="text/javascript" -->
<%-- 	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/calendario.js"></script> --%>
<script language="javascript" type="text/javascript"
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/tcal.js"></script>
<script language="javascript" type="text/javascript"
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidades2016.js"></script>
<script language="javascript" type="text/javascript"
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/jquery-download.js"></script>
<script language="javascript" type="text/javascript"
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/spin.js"></script>
</head>
<body class="bodyContent">
	<%-- Encabezado de Pagina  --%>
	<table width="100%" border="0" cellspacing="0" cellpadding="30">
		<tr>
			<td>
				<div class=\"bigText\">
					<img
						src="<%=util.getContexto(request)%>/neology/recursos/imagenes/Reportes.gif"
						border="0" width="98" height="97" align="absmiddle" />
					<bean:message key="texto.etiqueta.reportes.constanciasInscripcion" />
				</div>
				<hr />
			</td>
		</tr>
	</table>
	<%-- Fin de Encabezado --%>

	<html:form action="/reporteCInscripcion.do">
		<html:hidden property="cambiar" value="true" />
		<html:hidden property="do" value="generarTotalesConstancias" />
		<html:hidden property="isAdmin"
			value="<%=formularioReportes.isAdmin(request)%>" name="isAdmin" />
		<!-- 		<script language="JavaScript" id="jscal1x"> -->

		<!-- // 	var cal1= new CalendarPopup(); -->
		<!-- // 	cal1.showNavigationDropdowns(); -->
		<!-- // 	cal1.setTodayText("Hoy"); -->
		<!-- </script> -->
		<table width="100%" border="0" cellspacing="1" cellpadding="5">
			<tr>
				<td></td>
				<td><strong><bean:message
							key="etiqueta.campos.obligatorios" /></strong></td>
				<td></td>
				<td><strong><bean:message
							key="etiqueta.campos.opcionales" /></strong></td>
			</tr>
			<tr class="row2">
				<td align="right" width="23%"><html:checkbox
						property="strOptionHidden1" /><strong><bean:message
							key="texto.reportes.fechaInicial" />* : </strong></td>
				<td align="left"><html:text property="fechaInicial"
						readonly="true" styleClass="tcal" /> <!-- 				<a --> <!-- 					onclick="cal1.select(document.forms[0].fechaInicial,'anchor1x','dd/MM/yyyy'); return false;" -->
					<!-- 					title="Fecha de Inicio para generar el reporte" name="anchor1x" -->
					<!-- 					id="anchor1x" class="linkBody"> <img --> <%-- 						src="<%=util.getContexto(request)%>/neology/img/icoCalendar.gif" --%>
					<!-- 						align="absmiddle" /> --> <!-- 				</a> --></td>
				<td align="right"><html:checkbox property="strOptionHidden4" />
					<strong><bean:message
							key="texto.etiqueta.formato.estado.importados" />:</strong></td>
				<td><html:select styleClass="textfield"
						property="estadoImportados">
						<html:option value="0">
							<bean:message key="combo.opcion.ImportadosImportados" />
						</html:option>
						<html:option value="1">
							<bean:message key="combo.opcion.ImportadosNacional" />
						</html:option>
					</html:select></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td align="right"><strong> <bean:message
							key="texto.reportes.fechaFinal" />* :
				</strong></td>
				<td><html:text property="fechaFinal" readonly="true"
						styleClass="tcal" /> <!-- 				<a --> <!-- 					onclick="cal1.select(document.forms[0].fechaFinal,'anchor2x','dd/MM/yyyy'); return false;" -->
					<!-- 					title="Fecha de Final para generar el reporte" name="anchor2x" -->
					<!-- 					id="anchor2x" class="linkBody"> <img --> <%-- 						src="<%=util.getContexto(request)%>/neology/img/icoCalendar.gif" --%>
					<!-- 						align="absmiddle" /> --> <!-- 				</a> --></td>
				<td align="right"><html:checkbox property="strOptionHidden5" />
					<strong><bean:message
							key="texto.etiqueta.formato.estado.resposable" />:</strong></td>
				<td><html:select styleClass="textfield"
						property="strResponsable">
						<html:option value="-1">
							<bean:message key="combo.opcion.todos" />
						</html:option>
						<html:optionsCollection name="reporteConstanciasForm"
							property="usuariosView" value="idUsuario"
							label="strNombreCompleto" />
					</html:select></td>
			</tr>
			<tr class="row2">
				<td align="right"><html:checkbox property="strOptionHidden2" /><strong>
						<bean:message key="texto.etiqueta.formato.estado" />* :
				</strong></td>
				<td><html:select styleClass="textfield" property="estado">
						<html:option value="Z">
							<bean:message key="combo.opcion.todos" />
						</html:option>
						<html:optionsCollection name="reporteConstanciasForm"
							property="estados" value="estado" label="descripcion" />
					</html:select></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td align="right"><html:checkbox property="strOptionHidden3" /><strong><bean:message
							key="texto.etiqueta.formato.folio.inicial" />*:</strong></td>
				<td><html:text maxlength="8" property="folioInicial"
						onblur="formatoFolioInicial();"
						onkeypress="return isNumber(event);" styleClass="textfield" /></td>
				<td></td>
				<td align="left"><center>
						<strong><bean:message
								key="texto.etiqueta.reportes.columnas.seleccion" />*</strong>
					</center></td>
			</tr>
			<tr class="row2">
				<td align="right"><strong><bean:message
							key="texto.etiqueta.formato.folio.final" />*:</strong></td>
				<td><html:text maxlength="8" property="folioFinal"
						onblur="formatoFolioFinal();" styleClass="textfield"
						onkeypress="return isNumber(event)" /></td>
				<td style="width: 10%">
					<div id="divFolio">
						<center>
							<html:checkbox property="strColumnFolio" />
						</center>
						<center>
							<bean:message key="texto.etiqueta.bitacora.folio" />
						</center>
					</div>
				</td>
				<td style="width: 10%">
					<div id="divFecha">
						<center>
							<html:checkbox property="strColumnFecha" />
						</center>
						<center>
							<bean:message key="texto.etiqueta.bitacora.fecha" />
						</center>
					</div>
				</td>
				<td style="width: 10%">
					<div id="divTID">
						<center>
							<html:checkbox property="strColumnTID" />
						</center>
						<center>
							<bean:message key="texto.reportes.TID" />
						</center>
					</div>
				</td>
				<td
					style="width:10%; visibility: <%=formularioReportes.isAdmin(request)%>">
					<div id="divBD">
						<center>
							<html:checkbox property="consultaBD" />
						</center>
						<center>
							<bean:message key="texto.etiqueta.consulta.respaldo" />
						</center>
					</div>
				</td>
				<td></td>
			</tr>
			<tr>
				<td align="right"><html:checkbox property="strOptionHidden6" /><strong>
						<bean:message key="texto.etiqueta.formato.entidad" /> :
				</strong></td>
				<td><html:select styleClass="textfield" property="strEntidad">
						<html:option value="-1">
							<bean:message key="combo.opcion.todas.entidades" />
						</html:option>
						<html:optionsCollection name="reporteConstanciasForm"
							property="entidades" value="idEntidad" label="nombreEntidad" />
					</html:select></td>
				<td>
					<div id="divObservaciones">
						<center>
							<html:checkbox property="strColumnObservaciones" />
						</center>
						<center>
							<bean:message key="texto.etiqueta.formato.observaciones" />
						</center>
					</div>
				</td>
				<td>
					<div id="divEstadoActual">
						<center>
							<html:checkbox property="strColumnEstadoActual" />
						</center>
						<center>
							<bean:message key="texto.etiqueta.estado.actual" />
						</center>
					</div>
				</td>
				<td>
					<div id="divTipo">
						<center>
							<html:checkbox property="strColumnTipo" />
						</center>
						<center>
							<bean:message key="texto.etiqueta.tipo" />
						</center>
					</div>
				</td>
				<td>
					<div id="divEntidad">
						<center>
							<html:checkbox property="strColumnEntidad" />
						</center>
						<center>
							<bean:message key="texto.etiqueta.formato.entidad" />
						</center>
					</div>
				</td>
			</tr>
			<tr class="row2">
				<td align="right"><strong><bean:message
							key="texto.etiqueta.formato.reporte" />*:</strong></td>
				<td><html:select styleClass="textfield" property="isFullReport">
						<html:option value="0">
							<bean:message key="combo.opcion.reporteEstados" />
						</html:option>
						<html:option value="1">
							<bean:message key="combo.opcion.reporteCompleto" />
						</html:option>
					</html:select></td>
				<td>
					<div id="divResponsable">
						<center>
							<html:checkbox property="strColumnResponsable" />
						</center>
						<center>
							<bean:message key="texto.etiqueta.formato.estado.resposable" />
						</center>
					</div>
				</td>
				<td>
					<div id="divEvento">
						<center>
							<html:checkbox property="strColumnEvento" />
						</center>
						<center>
							<bean:message key="etiqueta.constancias.reporte.evento" />
						</center>
					</div>
				</td>
				<td>
					<div id="divEventoUltimo">
						<center>
							<html:checkbox property="strColumnUltimo" />
						</center>
						<center>
							<bean:message key="texto.etiqueta.reportes.ultimoEvento" />
						</center>
					</div>
				</td>
				<td>
					<div id="divChasis">
						<center>
							<html:checkbox property="strColumnChasis" />
						</center>
						<center>
							<bean:message key="texto.etiqueta.chasis" />
						</center>
					</div>
				</td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>
			<input type="button"
						class="submit" id="reiniciaColumnas"
						value="Reiniciar Parametros" />
			</td>
			</tr>
		</table>
		<br></br>
		<table>
			<tr>
				<td>
				<td></td>
				<td></td>
				<td><strong> <label id="ordenColumnasHeader">
							<bean:message key="texto.etiqueta.reportes.ordenColumnas" />
					</label>:
				</strong></td>
				<td><strong> <label id="ordenColumnas"></label></strong></td>
				<td></td>
			</tr>
			<tr>
				<td><html:text property="strOrdenColumnas" /></td>
			</tr>
		</table>
		<br />
		<table>
			<tbody>
				<tr>
					<td align="right" width="100%"><input type="submit"
						class="submit" id="reportButton" onclick="buttonClick();"
						value="Generar" /> <%-- 							<bean:message key="boton.reportes.generar" /> --%>
					</td>
				</tr>
			</tbody>
		</table>
		<br>
		<table>
			<tbody>
				<tr>
					<td><strong> <label id="mensajeFiltros"><bean:message
									key="texto.reportes.filtros" /></label>
					</strong></td>
					<td><strong> <label id="finalMessage"></label>
					</strong></td>
				</tr>
			</tbody>
		</table>
		<br>
		<table>
			<tr>
				<td><strong><label id="messageServlet"
						style="color: red;"></label> </strong></td>
			<tr>
		</table>
		<br>
		<table>
			<tbody>
				<tr>
					<td align="center" width="100%">
						<div id="divImg"></div>
					</td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td align="right" width="100%"><strong><label
							id="waitMessage"><bean:message
									key="etiqueta.generando.reporte" /></label></strong></td>
				</tr>
			</tbody>
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

			<%-- Fin Mensajes de Error --%>
			<html:messages id="msg" name="mensaje">
				<li><bean:write name="msg" /></li>
			</html:messages>
			<html:messages id="msg" name="mensajeFecha">
				<li><bean:write name="msg" /></li>
			</html:messages>
		</div>
		<%-- Lista de  Totales--%>
		<div id="resultTable">
			<logic:present name="totales">
				<logic:notEmpty name="totales">
					<table width="50%" border="0" cellpadding="5" cellspacing="1">
						<tbody>
							<%-- cabezeras--%>
							<tr class="tableHead">
								<td><bean:message key="texto.etiqueta.formato.estado" /></td>
								<td><bean:message key="texto.reportes.total" /></td>
								<td><bean:message key="texto.etiqueta.accion" /></td>
							</tr>
							<%-- Iteración de los Totales--%>
							<%
								boolean band = true;
							%>
							<logic:iterate id="formato" name="totales">
								<%
									band = !band;
								%>
								<%-- Totales --%>
								<tr <%=band ? "class='row2'" : "class=''"%>>
									<td><strong><bean:write name="formato"
												property="estado.descripcion" />(s)</strong></td>
									<td><logic:equal value="0" name="formato" property="total">
											<strong><bean:write name="formato" property="total" /></strong>
										</logic:equal> <logic:notEqual value="0" name="formato" property="total">
											<a
												href="javascript:sendRequest(<bean:write name="formato" property="estado.estado"/>);"><bean:write
													name="formato" property="total" /></a>
										</logic:notEqual></td>
									<td><logic:equal value="6" name="formato"
											property="estado.estado">
											<logic:notEqual value="0" name="formato" property="total">
												<a
													onclick="sendRequest(<bean:write name="formato" property="estado.estado"/>);"
													href="javascript:sendRequest(<bean:write name="formato" property="estado.estado"/>);"><bean:message
														key="texto.reporte.repuve" /> </a>
											</logic:notEqual>
										</logic:equal></td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</logic:notEmpty>
			</logic:present>
		</div>
	</html:form>
	<input value="<%=formularioReportes.todasEntidades(request)%>"
		id="inputTodasEntidades">
	<br />
</body>
</html>
<script language="javascript" type="text/javascript">
	function generar() {
		mostrarReporteTotales('<%=util.getContexto(request)%>');
	}
	
	function sendRequest(estado) {
		var param="/reporteCInscripcion.do?do=genDetalleTotales&strOptionHidden5="+document.getElementsByName("strOptionHidden5")[0].value+"&estado="+estado;
		location.href ="<%=util.getContexto(request)%>"+param;
	}
	
	function buttonClick(){
		
		var value=$('select[name=isFullReport]').val();
	
		if(value=="0"){
			document.getElementById("reportButton").style.visibility = "hidden";
			document.getElementById("waitMessage").style.visibility = "visible";
			document.getElementById("divImg").style.visibility = "visible";
			document.getElementById("divMensajesError").style.visibility = "hidden";
			document.getElementById("reportButton").disabled = true;
			document.getElementById("finalMessage").innerHTML = "";
			document.getElementById("mensajeFiltros").style.visibility = "visible";
			document.getElementById("messageServlet").innerHTML = "";

			// strOptionHidden1
			if (document.getElementsByName("strOptionHidden1")[0].checked) {

				document.getElementById("finalMessage").innerHTML += " - Fecha";

			}
			;

			// strOptionHidden2
			if (document.getElementsByName("strOptionHidden2")[0].checked) {

				document.getElementById("finalMessage").innerHTML += " - Estado";

			}
			;

			// strOptionHidden3
			if (document.getElementsByName("strOptionHidden3")[0].checked) {

				document.getElementById("finalMessage").innerHTML += " - Folio";

			}
			;

			// strOptionHidden5
			if (document.getElementsByName("strOptionHidden4")[0].checked) {

				document.getElementById("finalMessage").innerHTML += " - Estado de importados";

			}
			;

			// strOptionHidden6
			if (document.getElementsByName("strOptionHidden5")[0].checked) {

				document.getElementById("finalMessage").innerHTML += " - Responsable";

			}
			;

			var opts = {
				lines : 13 // The number of lines to draw
				,
				length : 10 // The length of each line
				,
				width : 14 // The line thickness
				,
				radius : 25 // The radius of the inner circle
				,
				scale : .20 // Scales overall size of the spinner
				,
				corners : 1 // Corner roundness (0..1)
				,
				color : '#000' // #rgb or #rrggbb or array of colors
				,
				opacity : 0.18 // Opacity of the lines
				,
				rotate : 0 // The rotation offset
				,
				direction : 1 // 1: clockwise, -1: counterclockwise
				,
				speed : 1 // Rounds per second
				,
				trail : 60 // Afterglow percentage
				,
				fps : 20 // Frames per second when using setTimeout() as a fallback
				// for CSS
				,
				zIndex : 2e9 // The z-index (defaults to 2000000000)
				,
				className : 'spinner' // The CSS class to assign to the spinner
				,
				top : '50%' // Top position relative to parent
				,
				left : '3%' // Left position relative to parent
				,
				shadow : false // Whether to render a shadow
				,
				hwaccel : false // Whether to use hardware acceleration
				,
				position : 'relative' // Element positioning
			};

			var targetDiv = document.getElementById("divImg");
			var divSpinner = document.createElement('div');
			divSpinner.id = 'divSpinner';
			targetDiv.appendChild(divSpinner);

			var spinner = new Spinner(opts).spin(divSpinner);
			document.forms[0].submit();
		}else{
			generaArchivo();
		}
		
	}
	
	function generaArchivo() {
		
		var param="/reporte";
		param ="<%=util.getContexto(request)%>"+param;
		$("#resultTable").hide();
		
		var strOptionHidden1=0;
		var strOptionHidden2=0;
		var strOptionHidden3=0;
		var strOptionHidden4=0;
		var strOptionHidden5=0;
		var strOptionHidden6=0;
		
		var strColumnFolio=0;
		var strColumnFecha=0;
		var strColumnTID=0;
		var strColumnObservaciones=0;
		var strColumnEstadoActual=0;
		var strColumnEntidad=0;
		var strColumnEvento=0;
		var strColumnTipo=0;
		var strColumnChasis=0;
		var strColumnResponsable=0;
		var consultaBD;
		
		var strEstado = "";
		var fechaInicial = "";
		var fechaFinal = "";
		var folioInicial = "";
		var folioFinal = "";
		var strResponsable = "";
		var strEstadoImportados = "";
		var strEntidad = "";
		var ultimoEvento="";
		
		var data = {};
		var datos = {};
		
		document.getElementById("waitMessage").style.visibility = "visible";
		document.getElementById("divImg").style.visibility = "visible";
		document.getElementById("divMensajesError").style.visibility = "hidden";
		document.getElementById("reportButton").style.visibility = "hidden";
		document.getElementById("finalMessage").innerHTML = "";
		document.getElementById("mensajeFiltros").style.visibility = "visible";
		document.getElementById("messageServlet").innerHTML = "";

		// strOptionHidden1
		if (document.getElementsByName("strOptionHidden1")[0].checked) {

			document.getElementById("finalMessage").innerHTML += " - Fecha";

		}
		;

		// strOptionHidden2
		if (document.getElementsByName("strOptionHidden2")[0].checked) {

			document.getElementById("finalMessage").innerHTML += " - Estado";

		}
		;

		// strOptionHidden3
		if (document.getElementsByName("strOptionHidden3")[0].checked) {

			document.getElementById("finalMessage").innerHTML += " - Folio";

		}
		;

		// strOptionHidden5
		if (document.getElementsByName("strOptionHidden4")[0].checked) {

			document.getElementById("finalMessage").innerHTML += " - Estado de importados";

		}
		;

		// strOptionHidden6
		if (document.getElementsByName("strOptionHidden5")[0].checked) {

			document.getElementById("finalMessage").innerHTML += " - Responsable";

		}
		;

		var opts = {
			lines : 13 // The number of lines to draw
			,
			length : 10 // The length of each line
			,
			width : 14 // The line thickness
			,
			radius : 25 // The radius of the inner circle
			,
			scale : .20 // Scales overall size of the spinner
			,
			corners : 1 // Corner roundness (0..1)
			,
			color : '#000' // #rgb or #rrggbb or array of colors
			,
			opacity : 0.18 // Opacity of the lines
			,
			rotate : 0 // The rotation offset
			,
			direction : 1 // 1: clockwise, -1: counterclockwise
			,
			speed : 1 // Rounds per second
			,
			trail : 60 // Afterglow percentage
			,
			fps : 20 // Frames per second when using setTimeout() as a fallback
			// for CSS
			,
			zIndex : 2e9 // The z-index (defaults to 2000000000)
			,
			className : 'spinner' // The CSS class to assign to the spinner
			,
			top : '50%' // Top position relative to parent
			,
			left : '3%' // Left position relative to parent
			,
			shadow : false // Whether to render a shadow
			,
			hwaccel : false // Whether to use hardware acceleration
			,
			position : 'relative' // Element positioning
		};

		var targetDiv = document.getElementById("divImg");
		var divSpinner = document.createElement('div');
		divSpinner.id = 'divSpinner';
		targetDiv.appendChild(divSpinner);

		var spinner = new Spinner(opts).spin(divSpinner);
		
		if (document.getElementsByName("strOptionHidden1")[0].checked) {
			strOptionHidden1='on';
		}else{
			strOptionHidden1=0;
		};
		
		
		if (document.getElementsByName("strOptionHidden2")[0].checked) {
			strOptionHidden2='on';
		}else{
			strOptionHidden2=0;
		};

		
		if (document.getElementsByName("strOptionHidden3")[0].checked) {
			strOptionHidden3='on';
		}else{
			strOptionHidden3=0;
		};
		
		
		if (document.getElementsByName("strOptionHidden4")[0].checked) {
			strOptionHidden4='on';
		}else{
			strOptionHidden4=0;
		};
		
		
		if (document.getElementsByName("strOptionHidden5")[0].checked) {
			strOptionHidden5=1;
		}else{
			strOptionHidden5=0;
		};
		
		if (document.getElementsByName("strOptionHidden6")[0].checked) {
			strOptionHidden6='on';
		}else{
			strOptionHidden6=0;
		};
		
		if (document.getElementsByName("strColumnFolio")[0].checked) {
			strColumnFolio=1;
		}else{
			strColumnFolio=0;
		};
		
		if (document.getElementsByName("strColumnFecha")[0].checked) {
			strColumnFecha=1;
		}else{
			strColumnFecha=0;
		};
		
		if (document.getElementsByName("strColumnTID")[0].checked) {
			strColumnTID=1;
		}else{
			strColumnTID=0;
		};
		
		if (document.getElementsByName("strColumnObservaciones")[0].checked) {
			strColumnObservaciones=1;
		}else{
			strColumnObservaciones=0;
		};
		
		if (document.getElementsByName("strColumnEstadoActual")[0].checked) {
			strColumnEstadoActual=1;
		}else{
			strColumnEstadoActual=0;
		};
		
		
		if (document.getElementsByName("strColumnEntidad")[0].checked) {
			strColumnEntidad=1;
		}else{
			strColumnEntidad=0;
		};
		
		if (document.getElementsByName("strColumnEvento")[0].checked) {
			strColumnEvento=1;
		}else{
			strColumnEvento=0;
		};
		
		if (document.getElementsByName("strColumnTipo")[0].checked) {
			strColumnTipo=1;
		}else{
			strColumnTipo=0;
		};
		
		if (document.getElementsByName("strColumnChasis")[0].checked) {
			strColumnChasis=1;
		}else{
			strColumnChasis=0;
		};
		
		
		if (document.getElementsByName("strColumnResponsable")[0].checked) {
			strColumnResponsable=1;
		}else{
			strColumnResponsable=0;
		};

		
		if (document.getElementsByName("consultaBD")[0].checked) {
			consultaBD=1;
		}else{
			consultaBD=0;
		};

		
        strEstado=document.getElementsByName("estado")[0].value;
        fechaInicial=document.getElementsByName("fechaInicial")[0].value;
	    fechaFinal=document.getElementsByName("fechaFinal")[0].value;	
	    folioInicial=document.getElementsByName("folioInicial")[0].value;	
	    folioFinal=document.getElementsByName("folioFinal")[0].value;	
		strResponsable=document.getElementsByName("strResponsable")[0].value;
		strEstadoImportados=document.getElementsByName("estadoImportados")[0].value;
		strEntidad=document.getElementsByName("strEntidad")[0].value;
		ultimoEvento=document.getElementsByName("strColumnUltimo")[0].checked;
		
		data = $.extend(true, data, {
			
			strOptionHidden1 : strOptionHidden1,
			strOptionHidden2 : strOptionHidden2,
			strOptionHidden3 : strOptionHidden3,
			strOptionHidden4 : strOptionHidden4,
			strOptionHidden5 : strOptionHidden5,
			strOptionHidden6 : strOptionHidden6,
			strColumnFolio : strColumnFolio,
			strColumnFecha : strColumnFecha,
			strColumnTID : strColumnTID,
			strColumnObservaciones : strColumnObservaciones,
			strColumnEstadoActual : strColumnEstadoActual,
			strColumnEntidad : strColumnEntidad,
			strColumnEvento : strColumnEvento,
			strColumnTipo : strColumnTipo,
			strColumnChasis : strColumnChasis,
			strColumnResponsable : strColumnResponsable,
			consultaBD : consultaBD,
			strEstado : strEstado,
			fechaInicial : fechaInicial,
			fechaFinal : fechaFinal,
			folioFinal : folioFinal,
			folioInicial : folioInicial,
			strResponsable : strResponsable,
			strEstadoImportados : strEstadoImportados,
			strEntidad : strEntidad,
			ultimoEvento:ultimoEvento
		});
		
		$.ajax({
		    url: param,
		    type:'GET',
		    data: data,
		    dataType:'json',
		    contentType: "application/json",
		    success: function(result) {
		    	document.getElementById("messageServlet").innerHTML = result.strMessage;
		    	document.getElementById("waitMessage").style.visibility = "hidden";
				document.getElementById("divImg").style.visibility = "hidden";
				document.getElementById("reportButton").style.visibility = "visible";
				var del = document.getElementById("divSpinner");
				del.parentNode.removeChild(del);
				document.getElementsByName("strOptionHidden6")[0].style.visibility = "hidden";
				
// 				if(result.listRegistros.length>0){
					

// 					datos = $.extend(true, datos, {
// 						strNombreArchivo: result.strNombreArchivo,
// 						listRegistros : JSON.stringify(result.listRegistros),
// 						strTituloFiltros : result.strTituloFiltros,
// 						strTitulo : result.strTitulo,
// 						strTituloFechas : result.strTituloFechas,
// 						strColumn1 : result.strColumn1,
// 						strColumn2 : result.strColumn2,
// 						strColumn3 : result.strColumn3,
// 						strColumn4 : result.strColumn4,
// 						strColumn5 : result.strColumn5,
// 						strColumn6 : result.strColumn6,
// 						strColumn7 : result.strColumn7,
// 						strColumn8 : result.strColumn8,
// 						strColumn9 : result.strColumn9,
// 						strColumn10 : result.strColumn10
// 					});
					
// 			 		$.download(
// 	 				param,
// 	 				datos,
// 	 				'POST'
// 	 			);	
					
//				}
				
		    }
		});
		
	}
	
blockComponents();
</script>