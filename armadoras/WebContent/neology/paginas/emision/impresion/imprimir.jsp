<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request" />
<html>
  <head>    
    <title><bean:message key="texto.emision.imprimirCI"/></title>  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script src="<%=util.getContexto(request)%>/neology/javascript/emision/emision.js"></script>
	<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js" type="text/javascript" language="Javascript"></script>
	<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/ajax.js" type="text/javascript" language="Javascript"></script>
	<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/jquery.js" type="text/javascript" language="Javascript"></script>
	<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/ajax_queue.js" type="text/javascript" language="Javascript"></script>
	<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/ConcurrentThread.js" type="text/javascript" language="Javascript"></script>
	<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/prueba.js" type="text/x-script.multithreaded-js" language="Javascript"></script>
	
<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
</head> 
  <body class="bodyContent" onload="verificaJava();">
  <%-- Encabezado de Pagina  --%>
     <table width="100%" border="0" cellspacing="0" cellpadding="30">
        <tr>
        <td>       
        <div class=\"bigText\">
              <img src="<%=util.getContexto(request)%>/neology/recursos/imagenes/imprimirFormatos.gif" border="0" width="112" height="79" align="absmiddle"/><bean:message key="texto.emision.imprimirCI"/>
        </div> 
        <hr />
        </td> 
        </tr>
        </table>
  <%-- Fin de Encabezado --%>  
   <html:form  action="/emisionFormatos.do" onsubmit="return false;"> 
     <table width="100%" border="0" cellspacing="1" cellpadding="5">
      <tr class="row2">
          <td align="right" width="50%"><strong><bean:message key="texto.etiqueta.emision.numeroImpresiones"/>:</strong>
    </td>
    <td align="left"  width="50">        
            <html:text name="emisionForm" maxlength="5"
                       property="numeroImpresiones" styleClass="textfield"/>                      
          </td>
          <td align="left" width="50%">        
            <input type="button"   onclick="imprimirCB('<%=util.getContexto(request)%>');" class="submit" value="<bean:message key="boton.emision.imprimir"/>"></input>
          </td>
    </tr>
     </table>
<div id="divImprimir" style="float: left;">
      <!--[if !IE]> Firefox and others will use outer object -->
      <object id="appletImprimir"
      		  classid="java:ImprimirCodigoBarras.class" 
      		  type="application/x-java-applet"
              style="width: 0; height: 0;">
          <param name="mayscript" value="true" />

          <param name="id" value="test" />
          <param name="onstart" value="appletLoaded" />
         
      <!--<![endif]-->
      <!-- MSIE (Microsoft Internet Explorer) will use inner object --> 
      <object id="appletImprimir"
        		classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93" 
                codebase="http://java.sun.com/update/1.5.0/jinstall-1_5_0-windows-i586.cab"
                style="width: 1px; height: 1px;"> 
          <param name="code" value="ImprimirCodigoBarras.class" />
          <param name="id" value="test_ie" />
          <param name="mayscript" value="true" />
          <param name="onstart" value="appletLoaded" />     
      </object> 
      <!--[if !IE]> close outer object -->
      </object>
      <!--<![endif]-->   
      <p id="resultadoApplet"></p>  
</div>
 
   
   </html:form>
  </body>
</html>
