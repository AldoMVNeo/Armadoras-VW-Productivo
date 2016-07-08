<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<jsp:useBean id="util" class="neology.util.Utilidades" scope="request"/>
<jsp:useBean id="fechaUtil" class="neology.util.FechaUtil" scope="request"/>
<%@ page import="org.joda.time.DateTime"%>

<html>
	<head>
    	<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
		<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
		<script src="<%=util.getContexto(request)%>/neology/javascript/emision/emision.js"></script>
	
		<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js" type="text/javascript" language="Javascript"></script>
		<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/ajax.js" type="text/javascript" language="Javascript"></script>
		<script language="javascript" type="text/javascript" src="<%=util.getContexto(request)%>/neology/javascript/utilidades/actb.js"></script><!-- External script -->
		<script language="javascript" type="text/javascript" src="<%=util.getContexto(request)%>/neology/javascript/utilidades/filtroTabla.js"></script>
<script type="text/javascript">     
	var i = 0;
</script>
    </head>

	<%-- Estilo para tabla --%>
	<style type="text/css" media="screen">
/*===================================================
	- Filtro para la tabla
=====================================================*/
		@import "<%=util.getContexto(request)%>/neology/estilos/tablaFiltro.css";
	</style>
	<body class="bodyContent">
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
			<html:hidden property="do" value="guardar"/>			
	    	<%-- Lista de Tipos de Formatos --%>
	    	
	        <logic:present property="datos" name="reImpresionesForm" >
    	    	<logic:notEmpty property="datos" name="reImpresionesForm" >    
    	    	<table width="81%" border="0" cellpadding="5" cellspacing="1">
    	    		<tr>
    	    			<td>
    	    				<bean:message key="texto.emision.totalVins"/>
		    	    	   <bean:write name="reImpresionesForm" property="totalVins"/>
		    	    	  </td>
    	    	   	</tr>
    	    	 </table>
		    		<table class="tablaFiltro" id=tablas width="100%" border="0" cellpadding="5" cellspacing="1">
		    			<tbody> <%-- cabezeras--%>
						<%-- cabezeras--%>
							<tr class="tableHead">								
								<td><bean:message key="texto.etiqueta.impresion.seleccionar"/></td>
								<td><bean:message key="texto.etiqueta.entidad.id"/></td>
								<td><bean:message key="texto.etiqueta.impresion.vin"/></td>
								<td><bean:message key="texto.etiqueta.impresion.barco"/></td>								
								<td><bean:message key="texto.etiqueta.impresion.oficina"/></td>								
								<td><bean:message key="texto.etiqueta.impresion.fechaRegistro"/></td>								
	    	    			</tr>
	    		     		<%-- Iteración de la Lista de Tipos de Formatos--%>
	    	    		  	<% boolean band=true;
	    	    		  	int i=0; %>
			    		<logic:iterate id="datos" property="datos" name="reImpresionesForm">
    			    		<% band=!band; %>                                
        			    	<tr <%= band?"class='row2'":"class=''"%>> 
        			    		<td>
									<input name="valor" type="hidden" value="<%=i %>"/>	      																										
									<html:multibox name="reImpresionesForm"  property="impresiones" value="<%=String.valueOf(i) %>"></html:multibox>
        			    		</td>
        			    		<td>
        			    			<bean:write name="datos" property="idOrdenImpresion"/>
        			    		</td>
        			    		<td>
        			    			<bean:write name="datos" property="vin"/>									
        			    		</td>
        			    		<td>
        			    			<bean:write name="datos" property="barco"/>
        			    		</td>
        			    		<td>
        			    			<bean:write name="datos" property="entidad"/>
        			    		</td>
        			    		<td>
        			    			<bean:define id="fecha" name="datos" property="fechaRegistro"/>
        			    			<%=fechaUtil.getFechaLarga((DateTime)fecha)%>
        			    		</td>
        			    		
	    	    		    </tr> 
	    	    		    <%i++;%>
	    	    			</logic:iterate>
				    	</tbody>	
						<html:hidden styleClass="textfield" name="reImpresionesForm"  property="cadena"/>
					</table>
				</logic:notEmpty>
			</logic:present>
					<html:button property="cargar" styleClass="submit"  onclick="activar();"><bean:message key="boton.seleccionarTodo"/>
					</html:button>		
			<div>
				<table>
					<tr>
						<td>
							<bean:message key="texto.etiqueta.impresion.vin"/>
							<html:text name="reImpresionesForm" property="vin" maxlength="17" onkeypress="return isAlphanumeric(event)"></html:text>
						</td>
					</tr>
				</table>
			</div>
			
			<div align="center">
            	<br>					
					<html:button property="cargar" styleClass="submit"  onclick="imprimir();" ><bean:message key="boton.imprimir"/>
					</html:button>					
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
		document.forms[0].submit();
	}
	
	
	function isAlphanumeric(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    
	    if ((charCode < 48 || charCode > 57)&& (charCode < 97 || charCode > 122) &&(charCode!=241)&&(charCode < 65 || charCode > 90)&&(charCode!=209)){
	        return false;
	        };
	}
</script>