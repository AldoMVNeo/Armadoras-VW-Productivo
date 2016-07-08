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
       <script language="javascript" type="text/javascript" src="<%=util.getContexto(request)%>/neology/javascript/utilidades/actb.js"></script><!-- External script -->
       <script language="javascript" type="text/javascript" src="<%=util.getContexto(request)%>/neology/javascript/utilidades/filtroTabla.js"></script>
        <title>
            <bean:message key="texto.etiqueta.cambiarEstado.titulo"/>
        </title>
    </head>
    <%-- Estilo para tabla --%>
<style type="text/css" media="screen">
/*====================================================
	- Filtro para la tabla
=====================================================*/
@import "<%=util.getContexto(request)%>/neology/estilos/tablaFiltro.css";
</style>
    <body class="bodyContent">
    <%-- Encabezado de Pagina 
        <table  border="0" cellspacing="0" cellpadding="30">
            <tr>
                <td>       
                    <div class=\"bigText\">
                        <img src="<%=util.getContexto(request)%>/neology/imagenes/dotacionFormatos.gif" border="0" width="48" height="48" align="absmiddle"/><bean:message key="texto.etiqueta.cambiarEstado.titulo"/>
                    </div> 
                    <hr />
                </td> 
            </tr>
        </table>        
        --%>
        
        <html:form action="/tipoFormato.do" onsubmit="return false">
            <html:hidden property="do" value="cargar" />  
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
                
            </DIV>
            <div align="center">
                <br>
                 <input type="button"  onclick="submit('cambiarEstadoForm');" class="submit" value="<bean:message key="boton.regresar"/>" ></input>
            </div>
        </html:form>
    </body>
</html>
