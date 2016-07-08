<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request" />
<head>
	<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
	<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js"
		type="text/javascript" language="Javascript"></script>
	<script src="<%=util.getContexto(request)%>/neology/javascript/emision/impresion/impresion.js" 
		type="text/javascript" language="Javascript"></script>
	<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/ajax.js"></script>
</head>
	
	<body class="bodyContent" id="imp">
	<%-- Encabezado de Pagina  --%>
     <table width="100%" border="0" cellspacing="0" cellpadding="30">
        <tr>
        <td>       
        <div class=\"bigText\">
              <img src="<%=util.getContexto(request)%>/neology/recursos/imagenes/imprimirFormatos.gif" border="0"  align="absmiddle"/><bean:message key="texto.emision.reImprimirTP"/>
        </div> 
        <hr />
        </td> 
        </tr>
        </table>        
  <%-- Fin de Encabezado --%>   
		<html:form action="/reimpresion.do" > 
			<html:hidden property="do" value="cargar"/>	
			<html:button property="cargar" styleClass="submit"  onclick="submit('impresionesForm');" ><bean:message key="boton.regresar"/>
					</html:button>
						 
			<div id="mensaje">
			</div>
		</html:form>		
	</body>
<script type="text/javascript">
document.body.onload = function(){mostrarReImpReporteTP("<%=util.getContexto(request)%>")}; 
</script>

