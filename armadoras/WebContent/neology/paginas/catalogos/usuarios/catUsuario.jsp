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
		<script language="javascript" type="text/javascript" src="<%=util.getContexto(request)%>/neology/javascript/utilidades/filtroTabla.js"></script>
    </head>

	<%-- Estilo para tabla --%>
	<style type="text/css" media="screen">
/*====================================================
	- Filtro para la tabla
=====================================================*/
		@import "<%=util.getContexto(request)%>/neology/estilos/tablaFiltro.css";
	</style>
        
	<body class="bodyContent" >
	<%-- Encabezado de Pagina  --%>
     <table width="100%" border="0" cellspacing="0" cellpadding="30">
        <tr>
        <td>       
        <div class=\"bigText\">
              <img src="<%=util.getContexto(request)%>/neology/recursos/imagenes/usuarios.gif" border="0" width="112" height="79" align="absmiddle"/><bean:message key="texto.etiqueta.catalogo.usuarios"/>
        </div> 
        <hr />
        </td> 
        </tr>
        </table>
  <%-- Fin de Encabezado --%>  
	
		<html:form  action="/usuarios.do"> 
			<html:hidden property="do" value="nuevo"/>
	
	    	<%-- Lista de Usuarios --%>
	        <logic:present name="usuarios">
    	    	<logic:notEmpty name="usuarios">    
		    		<table class="tablaFiltro" id=tablas width="100%" border="0" cellpadding="5" cellspacing="1">
		    			<tbody> <%-- cabezeras--%>
						<%-- cabezeras--%>
							<tr class="tableHead">
								<td style="width: 5%"><bean:message key="texto.etiqueta.usuario.id"/></td>								
								<td><bean:message key="texto.etiqueta.usuario.nombre"/></td>
								<td style="width: 13%"><bean:message key="texto.etiqueta.login.nombreUsuario"/></td>
								<td><bean:message key="texto.etiqueta.formato.entidad"/></td>
								<td><bean:message key="texto.etiqueta.perfil"/></td>
                				<td style="width: 10%"><bean:message key="texto.etiqueta.accion"/></td>		            	                              
	    	    			</tr>
	    		     		<%-- Iteración de la Lista de Usuarios--%>
	    	    		  	<% boolean band=true; %>
			    		<logic:iterate id="usuarios" name="usuarios">
    			    		<% band=!band; %>                                
        			    	<tr <%= band?"class='row2'":"class=''"%>> 
            					<td>
                					<html:link action="/usuarios.do?do=modificar" paramName="usuarios" paramProperty="idUsuario"  paramId="idUsuario"> <bean:write name="usuarios" property="idUsuario"/> </html:link>
			            	    </td>
    			            	<td>
        			        		<bean:write name="usuarios" property="nombres"/>
	            			    </td>
    	            			<td>
	    	            			<bean:write name="usuarios" property="usuario"/>
	    		            	</td>
	    		            	<td>
	    	            			<bean:write name="usuarios" property="entidad.nombreEntidad"/>
	    		            	</td>
	    		            	<td>
	    	            			<bean:write name="usuarios" property="perfil.nombrePerfil"/>
	    		            	</td>
	    		            	<td>
									<html:link action="/usuarios.do?do=modificar" paramName="usuarios" paramProperty="idUsuario"  paramId="idUsuario"> <img src="<%=util.getContexto(request)%>/neology/img/icoEdit.gif" alt="Editar" border="0" /> </html:link> 
									&nbsp; 
									<html:link onclick="if(confirm('Est&aacute; seguro de que desea eliminar este registro?')== false){return false;}" action="/usuarios.do?do=eliminar" paramName="usuarios" paramProperty="idUsuario"  paramId="idUsuario"> <img src="<%=util.getContexto(request)%>/neology/img/icoDelete.gif" alt="Eliminar"  border="0" /> </html:link> 
	    		            	</td>
	    	    		    </tr> 
				    	</logic:iterate>
				    	</tbody>
					</table>
				</logic:notEmpty>
			</logic:present>
			<div align="center">
            	<br>					
					<html:button property="cargar" styleClass="submit"  onclick="submit('usuariosForm');"> <bean:message key="boton.usuario.nuevo"/>
</html:button>
	        </div>
		</html:form>
		
	</body>
</html>

<script language="javascript" type="text/javascript">

        var paginacion ={
					paging: true,
					paging_length: 25,
					rows_counter: true,
					btn_reset: true,
					loader: true,
					loader_text: "Buscando..."
				};
	setFilterGrid( "tablas",paginacion );	
</script>