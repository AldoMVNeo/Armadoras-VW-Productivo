<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request"/>
<html> 
<head>
	<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/ConcurrentThread.js" type="text/javascript" language="Javascript"></script>
<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js" type="text/javascript" language="Javascript"></script>
		<script src="<%=util.getContexto(request)%>/neology/javascript/emision/impresion/impresion.js"></script>
</head>
<body>
 

<input id="cadena" type="hidden" value="<%=request.getParameter("cadena")%>"/>
<script type="text/javascript" language="Javascript">
var cadena=obtenerElemento('cadena').value;
</script>
<script type="text/x-script.multithreaded-js" language="Javascript">

var req = Concurrent.Thread.Http.post('/armadoras/emisionFormatos.do?do=imprimirCB','vin='+cadena );
   if (req.status == 200) {
//         alert("uno " + req.responseText);
     } else {
      
	}




</script>  
</body>
 </html>
 