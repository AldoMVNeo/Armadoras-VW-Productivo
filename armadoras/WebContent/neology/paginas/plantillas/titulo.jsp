<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@page import="neology.modelo.dto.Usuario"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request" />
<!-- <table width="100%" border="0"> -->
<%-- 	<% --%>
<%-- 				Usuario usuario = (Usuario) request.getSession().getAttribute(
 				"usuarioLog");--%>
<%-- 	%> --%>
<!-- 	<tr> -->
<!-- 		<td align="center"> -->
<!-- 			<img src="../../recursos/imagenes/logo_sistema.gif" border="0" align="absmiddle" /> -->
<!-- 		</td> -->
<!-- 		</tr> -->
<!-- 	<tr> -->
<!-- 		<td align="right"> -->
			
<%-- 				<strong><%=usuario.getNombres()%> --%>
<!-- 				</strong> -->
			
<!-- 		</td> -->
<!-- 	</tr>	 -->
<!-- 	<tr> -->
<!-- 		<td align="right"> -->
			
<%-- 				<strong><%=usuario.getEntidad().getNombreEntidad()%> --%>
<!-- 				</strong> -->
			
<!-- 		</td> -->
<!-- 		<tr> -->
<!-- 		<td align="right"> -->
			
<%-- 				<strong><a href="/armadoras/cerrarSesion.do"><bean:message key="texto.etiqueta.sistema.cerrar"/></a> --%>
<!-- 				</strong> -->
			
<!-- 		</td> -->
<!-- 	</tr>	 -->
<!-- </table> -->
<table width="100%" border="0">
	<%
				Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLog");
	%>
	<tr>
	 <td align="center" width="84%">
	 <img src="../../recursos/imagenes/logo_sistema.gif" border="0" align="absmiddle" /> 
	 </td>
	 <td align="right" >
				<strong><%=usuario.getNombres()%></strong><br/>
				<strong><%=usuario.getPerfil().getNombrePerfil()%></strong><br/>
				<strong><%=usuario.getEntidad().getNombreEntidad()%></strong><br/>
				<strong><a href="<%=util.getContexto(request)%>/cerrarSesion.do"><bean:message key="texto.etiqueta.sistema.cerrar"/></a></strong>	
	</td>
	</tr>	
</table>