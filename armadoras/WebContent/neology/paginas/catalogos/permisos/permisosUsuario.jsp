<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request"/>
<jsp:useBean id="permisosForm" class="neology.vc.actions.permisos.GestionPermisosDeUsuarioAction" scope="request"/>


<html>
	<head>
    	<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
		<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="<%=util.getContexto(request)%>/neology/javascript/utilidades/actb.js"></script><!-- External script -->
		<script language="javascript" type="text/javascript" src="<%=util.getContexto(request)%>/neology/javascript/utilidades/filtroTabla.js"></script>
		<script src="<%=util.getContexto(request)%>/neology/javascript/arbol/dhtmlxcommon.js" type="text/javascript" language="Javascript"></script>
       <script src="<%=util.getContexto(request)%>/neology/javascript/arbol/dhtmlxtree.js" type="text/javascript" language="Javascript"></script>
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
              <img src="<%=util.getContexto(request)%>/neology/recursos/imagenes/perfiles.gif" border="0" width="113" height="79" align="absmiddle"/><bean:message key="texto.etiqueta.permisos.usuario"/>
        </div> 
        <hr />
        </td> 
        </tr>
        </table>
  <%-- Fin de Encabezado --%>  
	
		<html:form  action="/altaPerfilPermisos.do"> 
			<html:hidden property="do" value="altaPerfil"/>	
	    	<%-- Lista de Perfiles --%>
	        <logic:present name="perfiles">
    	    	<logic:notEmpty name="perfiles">    
		    		<table class="tablaFiltro" id="tabla" width="100%" border="0" cellpadding="5" cellspacing="1">
		    			<tbody> <%-- cabezeras--%>
						<%-- cabezeras--%>
							<tr class="tableHead">
								<td><bean:message key="texto.etiqueta.identificador"/></td>								
								<td><bean:message key="texto.etiqueta.nombre"/></td>	
								<td><bean:message key="texto.etiqueta.estado.actual"/></td>							
                				<td><bean:message key="texto.etiqueta.accion"/></td>		            	                              
	    	    			</tr>
	    		     		<%-- Iteración de la Lista de Perfiles--%>
	    	    		  	<% boolean band=true; %>
			    		<logic:iterate id="perfil" name="perfiles">
    			    		<% band=!band; %>                                
        			    	<tr <%= band?"class='row2'":"class=''"%>>
            					<td>
        			        		<bean:write name="perfil" property="idPerfil"/>
	            			    </td>
    			            	<td>
        			        		<bean:write name="perfil" property="nombrePerfil"/>
	            			    </td>
    	            			<td>
    	            			     <bean:write name="perfil" property="estatus"/>				  
	    		            	</td>
	    		            	<td>
									<html:link action="/permisosUsuario.do?do=modificarPerfil" paramName="perfil" paramProperty="idPerfil"  paramId="idPerfil"> <img src="<%=util.getContexto(request)%>/neology/img/icoEdit.gif" alt="Modificar Permisos del Perfil" border="0" /> </html:link> 
									&nbsp; 
									<html:link onclick="if(confirm('Est&aacute; seguro de que desea eliminar este Perfil?, Los usuarios asociados estarán inactivos.')== false){return false;}" action="/permisosUsuario.do?do=eliminarPerfil" paramName="perfil" paramProperty="idPerfil"  paramId="idPerfil"> <img src="<%=util.getContexto(request)%>/neology/img/icoDelete.gif" alt="Eliminar"  border="0" /> </html:link> 
	    		            	</td>
	    		            </tr> 
				    	</logic:iterate>
				    	</tbody>
					</table>
				</logic:notEmpty>
			</logic:present>
			<div align="center">
            	<br>			       	
	        </div>
		</html:form>
		
			<button class="submit"  onclick="location.href=regresar('/permisosUsuario.do?do=altaPerfil');"> 
					<bean:message key="boton.perfil.nuevo"/>
					</button>
		
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

	function regresar(parametro) {                                   		
            direccion = "<%=util.getContexto(request)%>" + parametro ;
			return direccion;          
    }


</script>  