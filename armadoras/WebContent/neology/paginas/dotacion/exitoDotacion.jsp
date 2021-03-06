<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request"/>
<jsp:useBean id="fechaUtil" class="neology.util.FechaUtil" scope="request"/>
<%@ page import="org.joda.time.DateTime"%>
<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=windows-1252"></meta>
               <link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
             <script language="javascript" type="text/javascript" src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormulario.js"></script>
       <script language="javascript" type="text/javascript" src="<%=util.getContexto(request)%>/neology/javascript/utilidades/actb.js"></script>
<script language="javascript" type="text/javascript" src="<%=util.getContexto(request)%>/neology/javascript/utilidades/filtroTabla.js"></script>
<%-- Estilo para tabla --%>
<style type="text/css" media="screen">
/*====================================================
	- Filtro para la tabla
=====================================================*/
@import "<%=util.getContexto(request)%>/neology/estilos/tablaFiltro.css";
</style>
<%-- Fin Estilo para tabla --%>
        <title>
            <bean:message key="texto.etiqueta.formato.dotarF"/>
        </title>
    </head>
    <body class="bodyContent">
     <%-- Encabezado de Pagina  --%>
     <table width="100%" border="0" cellspacing="0" cellpadding="30">
        <tr>
        <td>       
        <div class=\"bigText\">
              <img src="<%=util.getContexto(request)%>/neology/recursos/imagenes/dotacionFormatos.gif" border="0" width="112" height="79" align="absmiddle"/><bean:message key="texto.etiqueta.formato.dotarF"/>
        </div> 
        <hr />
        </td> 
        </tr>
        </table>
  <%-- Fin de Encabezado --%>  
        <html:form action="/dotacion.do" onsubmit="return false">
        <html:hidden property="do" value="inicio" />        
 <DIV align="center">
      <div style="border: 3px dotted #006699;font-size:13pt;margin:50px auto;padding:5px;text-align:center;width:550px;">
                    <p>
                        <html:messages id="msg" name="mensaje">
                            <li>
                                <bean:write name="msg"/>
                            </li>
                        </html:messages>
                    </p>
                </div>  
                <div>
                <html:errors property="erroneo"/>
                </div>
                 <p> </p>
            <logic:present name="erroneos">
                <logic:notEmpty name="erroneos">               
                    <table  class="tablaFiltro" id="tabla" border="0" cellpadding="5" cellspacing="1">
                        <tbody>
                            <%-- cabezeras--%>
                            <tr class="tableHead">
                            <td><bean:message key="texto.etiqueta.formato.folio"/></td>
                            <td><bean:message key="texto.etiqueta.formato.tipoFormato"/></td>
                            <td><bean:message key="texto.etiqueta.formato.entidad"/></td>                                                   
                            </tr>
                            <%-- Iteración de los Formatos no guardados--%>
                              <% boolean band=true; %>
                            <logic:iterate id="formato" name="erroneos">
                              <% band=!band; %>
                                <%-- Titulos no guardados--%>
                                <tr <%= band?"class='row2'":"class=''"%>>
                                    <td> <bean:write name="formato" property="formatoId.folio"/></td>
                                    <td><bean:write name="formato" property="tipoFormato.descripcion"/></td>
                                    <td><bean:write name="formato" property="entidad.nombreEntidad"/></td>                                                                
                                </tr>
                            </logic:iterate>
                        </tbody>
                    </table>
                </logic:notEmpty>
            </logic:present>
            <div>
        <html:errors property="repetidos"/>
        </div>
         <p></p>
            <logic:present name="repetidos">
                <logic:notEmpty name="repetidos">               
                     <table class="tablaFiltro" id="tabla"  border="0" cellpadding="5" cellspacing="1">
                      
                            <%-- cabezeras--%>
                            <tr class="tableHead">
                            <td><bean:message key="texto.etiqueta.formato.folio"/></td>
                            <td><bean:message key="texto.etiqueta.formato.tipoFormato"/></td>
                            <td><bean:message key="texto.etiqueta.formato.entidad"/></td>
                            <td><bean:message key="texto.etiqueta.formato.estado.actual"/></td>
                            <td><bean:message key="texto.etiqueta.formato.fecha.dotacion"/></td>                            
                            </tr>
                             <% boolean band=true; %>
                            <%-- Iteración de los Formatos repetidos--%>
                            <logic:iterate id="formato" name="repetidos">
                            <% band=!band; %>
                                <%-- Titulos repetidos--%>
                                <tr <%= band?"class='row2'":"class=''"%>>
                                    <td> <bean:write name="formato" property="formatoId.folio"/></td>
                                    <td><bean:write name="formato" property="tipoFormato.descripcion"/></td>
                                    <td><bean:write name="formato" property="entidad.nombreEntidad"/></td>
                                    <td><bean:write name="formato" property="estado.descripcion"/></td>
                                    <bean:define id="fecha" name="formato"  property="fechaRegistro"/>
                                    <td><%=fechaUtil.getFechaLarga((DateTime)fecha)%></td>                                     
                                </tr>
                            </logic:iterate>                      
                    </table>
                </logic:notEmpty>
            </logic:present>
            </div>          
        <div align="center">
        <br>
           <input type="button"  onclick="submit('dotacionForm');" class="submit" value="<bean:message key="boton.regresar"/>"></input>
        </div>
    </html:form>
</body>
</html>
<script language="javascript" type="text/javascript">
        var paginacion ={
					paging: true,
					paging_length: 15,
					rows_counter: true,
					btn_reset: true,
					loader:true,
					loader_text: "Buscando..."
				};
	setFilterGrid( "tabla",paginacion );	
</script>