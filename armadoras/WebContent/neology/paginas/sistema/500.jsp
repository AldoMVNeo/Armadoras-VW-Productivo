<%@ page contentType="text/html;charset=windows-1252"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request"/>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
	<head>
	 <link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
		<title>500</title>
	</head>
	<body>
		<div align="center">
			<div style="border: 3px dotted #FF0000;font-size:13pt;margin:50px auto;padding:5px;text-align:center;width:600px;">
				<table border="0" align="center" cellspacing="5">
					<tr>
						<td align="right">
							<img src="<%=util.getContexto(request)%>/neology/recursos/imagenes/error.jpg" border="0" width="50" height="50" align="absmiddle" />
						</td>
						<td>
							<strong> <bean:message key="errors.general.sistema.500" /> </strong>
						</td>
					</tr>
					<tr>
						<td colspan="2">
						<%StackTraceElement[] elementos =(StackTraceElement[])request.getAttribute("ServletException");  %>
							<%for(StackTraceElement elementoActual:elementos){%>
							<%= elementoActual.getClassName() + "\t"%>
							<%= elementoActual.getFileName() + "\t"%>
							<%= elementoActual.getLineNumber() + "\t" %>
							<%= elementoActual.getMethodName() + "\n"  %>
							<%} %>													
						</td>
					</tr>
				</table>
			</div>
		</div>
		<html:errors />
	</body>
</html>
