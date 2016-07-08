<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<jsp:useBean id="util" class="neology.util.Utilidades" scope="request"/>

<html>
	<head>
    	<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
		<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
		<script src="<%=util.getContexto(request)%>/neology/javascript/emision/emision.js"></script>	
		<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js" type="text/javascript" language="Javascript"></script>
		<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/ajax.js" type="text/javascript" language="Javascript"></script>
		<script language="javascript" type="text/javascript" src="<%=util.getContexto(request)%>/neology/javascript/utilidades/actb.js"></script><!-- External script -->
    </head>


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
			<html:hidden property="do" value="mostrar"/>			
	    	<%-- Lista las impresiones realizadas --%>
	    	<table width="100%" border="0" cellspacing="1" cellpadding="5">
                 <tr class="row2">
                    <td align="right" width="50%">
                       <strong> <bean:message key="texto.reImpresion.bloque"/>:</strong>
                    </td>
                    <td colspan="2" align="left" width="50%"> 
                        <html:select styleClass="textfield" property="numeroTramite">
                            <html:option value="-1">- Seleccione un Bloque -</html:option>
                            <html:optionsCollection name="reImpresionesForm" property="bloques"
                                                    value="numeroTramite" label="descripcion"/>
                        </html:select>
                    </td>
                </tr>    
                </table>
	        
					<html:button property="cargar" styleClass="submit"  onclick="submit('reImpresionesForm');"><bean:message key="boton.seleccionar"/>
					</html:button>		
			
			
		</html:form>		
	</body>
</html>	

