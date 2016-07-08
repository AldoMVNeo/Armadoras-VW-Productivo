<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request" />
<html>
	<head>
		<title><bean:message key="texto.etiqueta.reportes.repuve" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
		<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
		<link href="<%=util.getContexto(request)%>/neology/estilos/tcal.css"
	rel="stylesheet" type="text/css" />
		<script src="<%=util.getContexto(request)%>/neology/javascript/reportes/reportes.js" type="text/javascript"
			language="Javascript"></script>
		<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js"
			type="text/javascript" language="Javascript"></script>
		<script language="javascript" type="text/javascript"
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/tcal.js"></script>
	</head>
	<body class="bodyContent">
		<%-- Encabezado de Pagina  --%>
		<table width="100%" border="0" cellspacing="0" cellpadding="30">
			<tr>
				<td>
					<div class=\"bigText\">
						<img src="<%=util.getContexto(request)%>/neology/recursos/imagenes/Reportes.gif" border="0" width="98" height="97"
							align="absmiddle" />
						<bean:message key="texto.etiqueta.reportes.repuve" />
					</div>
					<hr />
				</td>
			</tr>
		</table>
		<%-- Fin de Encabezado --%>
		<html:form action="/reportesRepuve.do" onsubmit="generarReporte();">
			<html:hidden property="cambiar" value="true" />
			<html:hidden property="do" value="generarReporteGrupos" />
<!-- 			<script language="JavaScript" id="jscal1x"> 
// 	var cal1= new CalendarPopup();
// 	cal1.showNavigationDropdowns();
// 	cal1.setTodayText("Hoy");
<!-- </script> -->


			<table width="100%" border="0" cellspacing="1" cellpadding="5">
				<tr class="row2">
					<td align="right" width="50%">
						<strong> <bean:message key="texto.reportes.fechaInicial" /> : </strong>
					</td>
					<td align="left">
<%-- 						<html:text property="fechaInicial" readonly="true" /> --%>
<!-- 						<a onclick="cal1.select(document.forms[0].fechaInicial,'anchor1x','dd/MM/yyyy'); return false;" -->
<!-- 							title="Fecha de Inicio para generar el reporte" name="anchor1x" id="anchor1x" class="linkBody"> <img -->
<%-- 								src="<%=util.getContexto(request)%>/neology/img/icoCalendar.gif" align="absmiddle" /> </a> --%>
					<html:text property="fechaInicial"  onfocus="changeFlagDate();"
						readonly="true" styleClass="tcal" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong> <bean:message key="texto.reportes.fechaFinal" /> : </strong>
					</td>
					<td>
<%-- 						<html:text property="fechaFinal" readonly="true" /> --%>
<!-- 						<a onclick="cal1.select(document.forms[0].fechaFinal,'anchor2x','dd/MM/yyyy'); return false;" -->
<!-- 							title="Fecha de Final para generar el reporte" name="anchor2x" id="anchor2x" class="linkBody"> <img -->
<%-- 								src="<%=util.getContexto(request)%>/neology/img/icoCalendar.gif" align="absmiddle" /> </a> --%>
					<html:text property="fechaFinal" readonly="true" onfocus="fechaFinalAuto();"
						styleClass="tcal" />
					</td>
				</tr>
				<tr class="row2">
					<td align="right">
						<strong> <bean:message key="texto.etiqueta.formato.estado" /> : </strong>
					</td>
					<td>
						<html:select styleClass="textfield" property="estado">
							<html:option value="8">
								<bean:message key="texto.etiqueta.formato.estado.revisado" />
							</html:option>
							<html:option value="9">
								<bean:message key="texto.etiqueta.formato.estado.informadoVerificado" />
							</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong> <bean:message key="texto.reportes.grupo" /> : </strong>
					</td>
					<td>
						<html:select styleClass="textfield" property="grupo">
							<html:option value="1">
								<bean:message key="texto.reportes.grupo1" />
							</html:option>
							<html:option value="2">
								<bean:message key="texto.reportes.grupo2" />
							</html:option>
						</html:select>
					</td>
				</tr>

				<tr>
					<td colspan="2" align="center">
						<html:submit styleClass="submit">
							<bean:message key="boton.reportes.generar" />
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
<script type="text/javascript">

var flagDate = 0
var inicialDate=0;

function fechaFinalAuto() {

	if(flagDate==0 && document.getElementsByName("fechaFinal")[0].disabled == false && (inicialDate!=document.getElementsByName("fechaInicial")[0].value)){
	    flagDate=1;
		document.getElementsByName("fechaFinal")[0].value = document
				.getElementsByName("fechaInicial")[0].value;
		inicialDate=document
		.getElementsByName("fechaInicial")[0].value;
	}
};

function changeFlagDate() {

	  flagDate=0;
	  
};

</script>