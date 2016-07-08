<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request" />
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
		<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
		<title><bean:message key="texto.etiqueta.parametros.titulo" />
		</title>
	</head>
	<body class="bodyContent">
		<html:form action="/parametros.do">
			<html:hidden property="cambiar" value="true" />
			<html:hidden property="do" value="cambiar" />
			<%
			String tamanio = "65";
			%>
			<%-- Encabezado de Pagina 
            <table width="100%" border="0" cellspacing="0" cellpadding="30">
                <tr>
                    <td>
                        <div class="bigText"><img src="<%=util.getContexto(request)%>/neology/imagenes/dotacionFormatos.gif"
                            border="0" width="48" height="48" align="absmiddle"> <bean:message key="texto.etiqueta.parametros.titulo" />
                        </div>
                        <hr/>
                    </td>
                </tr>
            </table>	
             --%>
			<table width="100%" border="0" cellspacing="1" cellpadding="5">
				<tr class="tableHead">
					<td colspan="4" align="right">
						<bean:message key="texto.etiqueta.parametros" />
					</td>
				</tr>
				<tr class="row2">
					<td width="50%" align="right">
						<strong> <bean:write name="parametrosForm" property="horarioOficina" />: </strong>
					</td>

					<td width="50%" align="left">
						De
						<html:text styleClass="textfield" name="parametrosForm" maxlength="20" property="horaInicialOficina" size="10" />
						A
						<html:text styleClass="textfield" name="parametrosForm" maxlength="20" property="horaFinalOficina" size="10" />
						horas
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong> <bean:write name="parametrosForm" property="dotarMaximoOficina" />: </strong>
					</td>
					<td align="left">
						<html:text styleClass="textfield" name="parametrosForm" maxlength="20" property="valorDotarMaximoOficina"
							size="10" />
					</td>
				</tr>
				<tr class="row2">
					<td align="right">
						<strong> <bean:write name="parametrosForm" property="dotarMaximoFueraOficina" />: </strong>
					</td>
					<td align="left">
						<html:text styleClass="textfield" name="parametrosForm" maxlength="20" property="valorDotarMaximoFueraOficina"
							size="10" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong> <bean:write name="parametrosForm" property="transferirMaximoOficina" />: </strong>
					</td>
					<td align="left">
						<html:text styleClass="textfield" name="parametrosForm" maxlength="20" property="valorTransferirMaximoOficina"
							size="10" />
					</td>
				</tr>
				<tr class="row2">
					<td align="right">
						<strong> <bean:write name="parametrosForm" property="transferirMaximoFueraOficina" />: </strong>
					</td>
					<td align="left">
						<html:text styleClass="textfield" name="parametrosForm" maxlength="20"
							property="valorTransferirMaximoFueraOficina" size="10" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong> <bean:write name="parametrosForm" property="cambiarMaximoOficina" />: </strong>
					</td>
					<td align="left">
						<html:text styleClass="textfield" name="parametrosForm" maxlength="20" property="valorCambiarMaximoOficina"
							size="10" />
					</td>
				</tr>
				<tr class="row2">
					<td align="right">
						<strong> <bean:write name="parametrosForm" property="cambiarMaximoFueraOficina" />: </strong>
					</td>
					<td align="left">
						<html:text styleClass="textfield" name="parametrosForm" maxlength="20" property="valorCambiarMaximoFueraOficina"
							size="10" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong> <bean:write name="parametrosForm" property="tspMinimoBodega" />: </strong>
					</td>
					<td align="left">
						<html:text styleClass="textfield" name="parametrosForm" maxlength="20" property="valorTspMinimoBodega" size="10" />
					</td>
				</tr>
				<tr class="row2">
					<td align="right">
						<strong> <bean:write name="parametrosForm" property="tspMinimoPau" />: </strong>
					</td>
					<td align="left">
						<html:text styleClass="textfield" name="parametrosForm" maxlength="20" property="valorMinimoTspPau" size="10" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong> <bean:write name="parametrosForm" property="reimpresionMaximoDias" />: </strong>
					</td>
					<td align="left">
						<html:text styleClass="textfield" name="parametrosForm" maxlength="20" property="valorMaximoReimpresionDias"
							size="10" />
					</td>
				</tr>
				<tr class="row2">
					<td align="right">
						<strong> <bean:write name="parametrosForm" property="reimpresionMaximoTsp" />: </strong>
					</td>
					<td align="left">
						<html:text styleClass="textfield" name="parametrosForm" maxlength="20" property="valorMaximoReimpresionTsp"
							size="10" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<html:submit styleClass="submit">
							<bean:message key="boton.guardar" />
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



