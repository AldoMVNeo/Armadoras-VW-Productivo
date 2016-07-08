<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request"/>
<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=windows-1252"></meta>
        <link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
       <script language="javascript" type="text/javascript" src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormulario.js"></script>
       <script language="javascript" type="text/javascript" src="<%=util.getContexto(request)%>/neology/javascript/utilidades/actb.js"></script><!-- External script -->
       <script language="javascript" type="text/javascript" src="<%=util.getContexto(request)%>/neology/javascript/utilidades/filtroTabla.js"></script>
        <title>
           <bean:message key="texto.etiqueta.configuracion.historico" />
        </title>
    </head>
       <body class="bodyContent">
   <%-- Encabezado de Pagina  --%>
     <table width="100%" border="0" cellspacing="0" cellpadding="30">
        <tr>
        <td>       
        <div class=\"bigText\">
             <img src="<%=util.getContexto(request)%>/neology/recursos/imagenes/dotacionFormatos.gif"
						border="0" width="112" height="79" align="absmiddle" />
              <bean:message key="texto.etiqueta.configuracion.historico" />
        </div> 
        <hr />
        </td> 
        </tr>
        </table>
  <%-- Fin de Encabezado --%>  
        
        <html:form action="/configuracionHistorico" onsubmit="return false">
            <html:hidden property="do" value="inicio" />  
             <DIV align="center">      
            <div style="border: 3px dotted #006699;font-size:13pt;margin:50px auto;padding:5px;text-align:center;width:580px;">
                    <p>
                      <strong>
                        <html:messages id="msg" name="mensaje">
                            <li>
                                <bean:write name="msg"/>
                            </li>
                        </html:messages>
                        </strong>
                    </p>
                </div>                                                                          
                
            </DIV>
            <div align="center">
                <br>
                 <input type="button"  onclick="submit('configuracionHistoricoForm');" class="submit" value="<bean:message key="boton.regresar"/>" ></input>
            </div>
        </html:form>
    </body>
</html>
