<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request" />
<head>
	<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
	<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js"
		type="text/javascript" language="Javascript"></script>
	<script src="<%=util.getContexto(request)%>/neology/javascript/emision/emision.js" type="text/javascript"
		language="Javascript"></script>
	<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/ajax.js"></script>
</head>
<body class="bodyContent" 
	onload="mostrarDiv(false, 'divMotivos');mostrarReporteTP('<%=util.getContexto(request)%>');">
	<html:form action="/grabacionChip.do" onsubmit="verificaSeleccionEstado();">
		<html:hidden property="idOrdenImpresion" />
		<html:hidden property="nombreReporte" />
		<div align="center">
			<div style="border: 3px dotted #006699;font-size:13pt;margin:50px auto;padding:5px;text-align:center;width:550px;">
				<p>
					<bean:message key="texto.etiqueta.impresion.correcta" />
				</p>
				<table>
					<logic:iterate id="estado" property="estadoImpresion" name="emisionForm">
						<tr>
							<td>
								<bean:define id="idEstado">
									<bean:write name="estado" property="idEstadoImpresion" />
								</bean:define>
								<input type="radio" id="estadoSel" name="estadoSel" value="" style="width:25px;height:25px" onclick="verificaEstado(this.value);"/>
								<bean:write name="estado" property="descripcionEstadoImpresion" />
								<logic:equal name="estado" property="idEstadoImpresion" value="0">
									<img src="<%=util.getContexto(request)%>/neology/recursos/imagenes/exitoImpresion.gif" border="0" width="40"
										height="40" align="absmiddle" />
								</logic:equal>
								<logic:notEqual name="estado" property="idEstadoImpresion" value="0">
									<img src="<%=util.getContexto(request)%>/neology/recursos/imagenes/errorImpresion.gif" border="0" width="40"
										height="40" align="absmiddle" />
								</logic:notEqual>

							</td>
						</tr>						
					</logic:iterate>					
				</table>
			</div>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<input type="button" class="submit" onclick="verificaSeleccionEstado('<%=util.getContexto(request)%>');"
							value="<bean:message key="boton.continuar"/>"></input>
					</td>
				</tr>
			</table>
		</div>
	</html:form>

</body>


