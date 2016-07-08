<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request" />
<%
String resultado = (String) request.getAttribute("resultado");
%>
<html>
	<head>
		<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet"
			type="text/css" />
	</head>
	<body class="bodyContent">
		<html:form action="/subirHandheld.do" onsubmit="return false">
			<div align="center"
				style="border: 3px dotted #006699;font-size:13pt;margin:50px auto;padding:5px;text-align:center;width:550px;">
				<%=resultado%>
			</div>
			<div align="center">
				<br>
				<input type="button" onclick="document.location.href='<%=util.getContexto(request)%>/subirHandheld.do?do=inicio';"
					class="submit" value="<bean:message key="boton.regresar"/>"></input>
			</div>
		</html:form>
	</body>
</html>
