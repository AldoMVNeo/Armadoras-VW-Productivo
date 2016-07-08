<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request" />
<jsp:useBean id="fechaUtil" class="neology.util.FechaUtil"
	scope="request" />
<%@ page import="org.joda.time.DateTime"%>

<html>
<head>
<title><bean:message key="texto.emision.imprimirCI" /></title>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=util.getContexto(request)%>/neology/javascript/emision/emision.js"></script>

<script
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js"
	type="text/javascript" language="Javascript"></script>
<script
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/ajax.js"
	type="text/javascript" language="Javascript"></script>
<script language="javascript" type="text/javascript"
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/actb.js"></script>
<!-- External script -->
<script language="javascript" type="text/javascript"
	src="<%=util.getContexto(request)%>/neology/javascript/utilidades/filtroTabla.js"></script>
<script type="text/javascript">     
	var i = 0;
</script>
</head>

<%-- Estilo para tabla --%>
<style type="text/css" media="screen">
/*===================================================
	- Filtro para la tabla
=====================================================*/
@import "<%=util.getContexto(request)%>/neology/estilos/tablaFiltro.css"
	;
</style>
<body class="bodyContent">
	<%-- Encabezado de Pagina  --%>
	<table width="100%" border="0" cellspacing="0" cellpadding="30">
		<tr>
			<td>
				<div class=\"bigText\">
					<img
						src="<%=util.getContexto(request)%>/neology/recursos/imagenes/imprimirFormatos.gif"
						border="0" align="absmiddle" />
					<bean:message key="texto.emision.imprimirCI" />
				</div>
				<hr />
			</td>
		</tr>
	</table>
	<%-- Fin de Encabezado --%>
	<html:form action="/impresiones.do">
		<html:hidden property="cambiar" value="true" />
		<html:hidden property="do" value="guardar" />
		<%-- Lista de Tipos de Formatos --%>
		<logic:present property="datos" name="impresionesForm">
			<logic:notEmpty property="datos" name="impresionesForm">
				<table width="81%" border="0" cellpadding="5" cellspacing="1">
					<tr>
						<td><bean:message key="texto.emision.totalVins" /> <bean:write
								name="impresionesForm" property="totalVins" /></td>
					</tr>
				</table>
				<table class="tablaFiltro" id=tablas width="100%" border="0"
					cellpadding="5" cellspacing="1">
					<tbody>
						<%-- cabezeras--%>
						<%-- cabezeras--%>
						<tr class="tableHead">
							<td><bean:message key="texto.etiqueta.impresion.seleccionar" /></td>
							<td><bean:message key="texto.etiqueta.entidad.id" /></td>
							<td><bean:message key="texto.etiqueta.impresion.vin" /></td>
							<td><bean:message key="texto.etiqueta.impresion.barco" /></td>
							<td><bean:message key="texto.etiqueta.impresion.oficina" /></td>
							<td><bean:message
									key="texto.etiqueta.impresion.fechaRegistro" /></td>
						</tr>
						<%-- Iteración de la Lista de Tipos de Formatos--%>
						<% boolean band=true;
	    	    		  	int i=0; %>
						<logic:iterate id="datos" property="datos" name="impresionesForm">
							<% band=!band; %>
							<tr <%= band?"class='row2'":"class=''"%>>
								<td><input name="valor" type="hidden" value="<%=i %>" /> <html:multibox
										name="impresionesForm" property="impresiones"
										value="<%=String.valueOf(i) %>"></html:multibox></td>
								<td><bean:write name="datos" property="idOrdenImpresion" />
								</td>
								<td><bean:write name="datos" property="vin" /></td>
								<td><bean:write name="datos" property="barco" /></td>
								<td><bean:write name="datos" property="entidad" /></td>
								<td><bean:define id="fecha" name="datos"
										property="fechaRegistro" /> <%=fechaUtil.getFechaLarga((DateTime)fecha)%>
								</td>

							</tr>
							<%i++;%>
						</logic:iterate>
					</tbody>
					<html:hidden styleClass="textfield" name="impresionesForm"
						property="cadena" />
				</table>
			</logic:notEmpty>
		</logic:present>
		<html:button property="cargar" styleClass="submit"
			onclick="activar();">
			<bean:message key="boton.seleccionarTodo" />
		</html:button>
		<div id="rango">
			<table width="40%" border="0" cellpadding="5" cellspacing="1">
				<tr>
					<td class="row2" align="center" colspan="2"><bean:message
							key="texto.etiqueta.formato.foliosDisponibles" /> <bean:write
							name="impresionesForm" property="totalFormatos" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><bean:message
							key="texto.emision.imprimirporRango" /></td>
				</tr>
				<tr class="row2">
					<td><bean:message key="texto.emision.de" /> <html:text onkeypress="return isNumber(event)"
							styleClass="textfield" name="impresionesForm" property="de" /></td>
					<td><bean:message key="texto.emision.hasta" /> <html:text onkeypress="return isNumber(event)"
							styleClass="textfield" name="impresionesForm" property="hasta" />
					</td>
				</tr>
			</table>
		</div>
		<div align="center">
			<br>
			<%-- 					<html:button property="cargar" styleClass="submit"  onclick="imprimir();" ><bean:message key="boton.imprimir"/> --%>
			<%-- 					</html:button>					 --%>
			<button class="submit" onclick="imprimir();">
				<bean:message key="boton.imprimir" />
			</button>

		</div>
		<br>

	</html:form>
</body>
</html>

<script language="javascript" type="text/javascript">     
	 var paginacion ={
					paging: true,
					paging_length: 10,
					rows_counter: true,
					btn_reset: true,
					loader: true,
					loader_text: "Buscando..."
				};
	setFilterGrid( "tablas",paginacion );	
	
	function ocultadiv()
	{	
		mostrarDiv(obtenerElemento("activo").checked,"rango");
	}				
	
	function activar(){
		var valor=true       
        if(document.getElementsByName("impresiones")[0].checked==true) 
			valor = false
		else
			valor = true
		for (i=0;i<document.getElementsByName("impresiones").length;i++)
        	document.getElementsByName("impresiones")[i].checked=valor;
	}
	
	function imprimir(){
		var numero = "0123456789";
		var de = document.getElementById("de").value;
		var hasta = document.getElementById("hasta").value
		
		if(de!=0 && de.length>=1)
		{
			for ( i = 0; i < de.length; i++)			
        		{        			
        			if (numero.indexOf(de.charAt(i)) == - 1)
        			{ 
        				alert("el campo 'de' solo debe contener números, este caracter es incorrecto: " + de.charAt(i))        				
        			}
        		} 
		}
		if(hasta!=0 && hasta.length>=1)
		{
			for ( i = 0; i < hasta.length; i++)			
        		{        			
        			if (numero.indexOf(hasta.charAt(i)) == - 1)
        			{ 
        				alert("el campo 'hasta' solo debe contener números, este caracter es incorrecto: " + hasta.charAt(i))        				
        			}
        		} 
		}
if(document.getElementById("de").value!=0 && document.getElementById("hasta").value==0)
			document.getElementById("hasta").value=document.getElementById("de").value;
		document.forms[0].submit();
	}
	
	function isNumber(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	        return false;
	    }
	    return true;
	}
	
</script>