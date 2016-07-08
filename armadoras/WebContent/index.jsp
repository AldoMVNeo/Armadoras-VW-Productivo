<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>untitled</title>
</head>
<body>
    <html:form action="/emisionFormatos.do?do=inicio">
      <html:submit value="Ir a la Emision"/>
    </html:form>
    <html:form action="/dotacion.do?do=inicio">
      <html:submit value="Ir a la dotacion"/>
    </html:form>
     <html:form action="/cambiarEstado.do?do=cargar">
      <p>
        <html:submit value="Ir a cambio de estado de Formatos"/>
      </p>
    </html:form>  
    <html:form action="/transferirFormatos.do?do=cargar">
      <p>
        <html:submit value="Ir a Tansferencia de Formatos"/>
      </p>
    </html:form>  
    <html:form action="/parametros.do?do=cargar">
      <p>
        <html:submit value="Ir a Configuracion de Parametros"/>
      </p>
    </html:form>
    <html:form action="/grabacionChip.do?do=inicio">
      <p>
        <html:submit value="Ir a Grabacion chip"/>
      </p>
    </html:form>
    <%-- Mensajes de Error --%>
    <div id="divMensajesError" align="center">   
    <table border="0">
               <logic:messagesPresent>       
                <tr>
            <td>
             <html:messages id="errores" header="errors.header" footer="errors.footer">
                <li><bean:write name="errores" />  </li><br>
                    </html:messages>
                </td>             
                </tr>                 
                    </logic:messagesPresent>  
                </table>                               
         </div>
    <%-- Fin Mensajes de Error --%>     
  </body>
</html>
