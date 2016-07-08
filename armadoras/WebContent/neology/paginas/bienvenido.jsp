<jsp:useBean id="util" class="neology.util.Utilidades" scope="request"/>
<html>
	<head>
		<title>Bienvenido</title>
	</head>
	<body>
		<div align="center" style="padding-top:20px;">			
			<img src="<%=util.getContexto(request)%>/neology/recursos/imagenes/bienvenido_vw.gif" border="0" title="Bienvenido"/>
		</div>
	</body>
</html>
