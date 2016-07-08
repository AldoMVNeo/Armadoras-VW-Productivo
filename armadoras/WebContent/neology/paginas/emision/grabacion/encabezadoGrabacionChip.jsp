<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request"/>
<html>
  <head>

    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"></meta>
    <link href="<%=util.getContexto(request)%>/neology/estilos/skin.css"
          rel="stylesheet" type="text/css"/>
    <title>
      <bean:message key="texto.emision.gImprimirTP"/>
    </title>
  </head>
  <body class="bodyContent">    
  <html:form action="/emisionFormatos.do">
          <table width="100%" border="0" cellspacing="1" cellpadding="5">          
            <tr class="row2">
              <td width="25%" align="right">
                <strong><bean:message key="texto.etiqueta.vin"/>:</strong>
              </td>
              <td width="25%" align="left"><bean:write name="emisionForm" property="vin"/></td>
              <td width="25%" align="right">
                <strong><bean:message key="texto.etiqueta.placa"/>:</strong>
              </td>
              <td width="25%" align="left"><bean:write name="emisionForm" property="placa"/></td>
            </tr>
           <tr>
              <td width="25%" align="right">
                <strong><bean:message key="texto.etiqueta.tipo"/>:</strong>
              </td>
              <td width="25%" align="left"><bean:write name="emisionForm" property="tipo"/></td>
              <td width="25%" align="right">
                <strong><bean:message key="texto.etiqueta.annoModelo"/>:</strong>
              </td>
              <td width="25%" align="left"><bean:write name="emisionForm" property="annoModelo"/></td>
            </tr>
            <tr class="row2">
              <td width="25%" align="right">
                <strong><bean:message key="texto.etiqueta.marca"/>:</strong>
              </td>
              <td width="25%" align="left"><bean:write name="emisionForm" property="marca"/></td>
              <td width="25%" align="right">
                <strong><bean:message key="texto.etiqueta.modelo"/>:</strong>
              </td>
              <td width="25%" align="left"><bean:write name="emisionForm" property="modelo"/></td>
            </tr>
            <tr>
              <td width="25%" align="right">
                <strong><bean:message key="texto.etiqueta.numeroTramite"/>:</strong>
              </td>
              <td width="25%" align="left"><bean:write name="emisionForm" property="numeroTramite"/></td>
              <td width="25%" align="right">
                <strong><bean:message key="texto.etiqueta.propietario"/>:</strong>
              </td>
              <td width="25%" align="left"><bean:write name="emisionForm" property="propietario"/></td>
            </tr>
          </table>   
          </html:form>
  </body>
</html>