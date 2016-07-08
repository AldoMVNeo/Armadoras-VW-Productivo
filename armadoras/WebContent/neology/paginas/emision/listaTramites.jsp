<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request"/>
<%@ page import="java.util.Map,java.util.HashMap"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
<script src="<%=util.getContexto(request)%>/neology/javascript/emision/emision.js"></script>
<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js" type="text/javascript" language="Javascript"></script>
<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/ajax.js" type="text/javascript" language="Javascript"></script>
<script language="javascript" type="text/javascript" src="<%=util.getContexto(request)%>/neology/javascript/utilidades/actb.js"></script><!-- External script -->
<script language="javascript" type="text/javascript" src="<%=util.getContexto(request)%>/neology/javascript/utilidades/filtroTabla.js"></script>
<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/swfobject.js"></script>
<jsp:useBean id="fechaUtil" class="neology.util.FechaUtil" scope="request"/>
<%@ page import="org.joda.time.DateTime"%>
<%-- Estilo para tabla --%>
<style type="text/css" media="screen">
/*====================================================
	- Filtro para la tabla
=====================================================*/
@import "<%=util.getContexto(request)%>/neology/estilos/tablaFiltro.css";
</style>
        
    </head>
<body class="bodyContent"  >
<html:form  action="/listaTramites.do" > 
<div id="imprimir">
    <%-- Mensajes de Error --%>
       <logic:messagesPresent>
            <html:messages id="errores" header="errors.header" footer="errors.footer">
                <li><bean:write name="errores" /></li>
            </html:messages>
        </logic:messagesPresent>
         <%--  Fin Mensajes de Error --%>
            <p>
               
            </p>
            
            <%-- Lista de Tramites realizados --%>
            <logic:present property="ordenes" name="listaTramitesForm">
                <logic:notEmpty property="ordenes" name="listaTramitesForm">    
    <table class="tablaFiltro" id="tablaTramites" width="100%" border="0" cellpadding="5" cellspacing="1">
	 <%-- cabezeras--%>
	<tr class="tableHead">
	<td><bean:message key="texto.etiqueta.vin"/></td>    
		<td><bean:message key="texto.etiqueta.formato.estado.actual"/></td>            
         <td><bean:message key="texto.etiqueta.orden.fechaRegistro"/></td>                         
        </tr>
         <%-- Iteración de la Lista de Tramites--%>
          <% boolean band=true; %>
    <logic:iterate id="orden" property="ordenes" name="listaTramitesForm">
      <% band=!band; %>
            <tr <%= band?"class='row2'":"class=''"%> >
            <td><bean:write name="orden" property="vin"/></td>   
                    <logic:notEmpty  name="orden" property="estado" >
                    <logic:equal value="I" name="orden" property="estado.estado" >
                        <td><bean:message key="texto.etiqueta.estado.terminado"/></td>
                    </logic:equal>
                    <logic:notEqual value="I" name="orden" property="estado.estado">
                         <td> <a href="javascript:enviarGrabado('<bean:write name="orden" property="idOrdenImpresion"/>');"><bean:message key="texto.emision.grabarImprimir"/></a></td>
                    </logic:notEqual>
                </logic:notEmpty>
                <logic:empty name="orden" property="estado" >
                  <td><a href="javascript:enviarGrabado('<bean:write name="orden" property="idOrdenImpresion"/>');"><bean:message key="texto.emision.grabarImprimir"/></a></td>
                </logic:empty>                 
                 <bean:define id="fecha" name="orden"  property="fechaRegistro"/>
                 <td><%=fechaUtil.getFechaLarga((DateTime)fecha)%></td>              
            </tr>
    </logic:iterate>
</table>
</logic:notEmpty>
</logic:present>
</div>
</html:form>
</body>
</html>
<script language="javascript" type="text/javascript">
function enviarGrabado(id){
var url="/emisionFormatos.do?do=asignarTramite";
if(id!='null')
	url=url+"&idOrden="+id;	
 grabarChip('<%=util.getContexto(request)%>',url);
}
        var paginacion ={
					paging: true,
					paging_length: 25,
					rows_counter: true,
					btn_reset: true,
					loader: true,
					loader_text: "Buscando..."
				};
	setFilterGrid( "tablaTramites",paginacion );	
</script>