<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request" />
<html>


	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
		<link href="<%=util.getContexto(request)%>/neology/estilos/skin.css" rel="stylesheet" type="text/css" />
		<link href="<%=util.getContexto(request)%>/neology/estilos/dhtmlxtree.css" rel="stylesheet" type="text/css" />
		<script src="<%=util.getContexto(request)%>/neology/javascript/utilidades/utilidadesFormularios.js"
			type="text/javascript" language="Javascript"></script>
		<script src="<%=util.getContexto(request)%>/neology/javascript/arbol/dhtmlxcommon.js" type="text/javascript"
			language="Javascript"></script>
		<script src="<%=util.getContexto(request)%>/neology/javascript/arbol/dhtmlxtree.js" type="text/javascript"
			language="Javascript"></script>
		<title><bean:message key="texto.etiqueta.perfil.modificar" /></title>
	</head>
	<body class="bodyContent">
		<%-- Encabezado de Pagina  --%>
		<table width="100%" border="0" cellspacing="0" cellpadding="30">
			<tr>
				<td>
					<div class=\"bigText\">
						<img src="<%=util.getContexto(request)%>/neology/recursos/imagenes/perfiles.gif" border="0" width="112" height="79"
							align="absmiddle" />
						<bean:message key="texto.etiqueta.perfil.modificar" />
					</div>
					<hr />
				</td>
			</tr>
		</table>
		<%-- Fin de Encabezado --%>
		<html:form action="/permisosUsuario.do" onsubmit="guardarPerfil();">
			<html:hidden property="do" value="actualizarPerfil" />
			<html:hidden name="GenerarPermisosUsuarioForm" property="idPerfil" />
			<html:hidden name="GenerarPermisosUsuarioForm" property="menuSeleccionados" />
			<html:hidden name="GenerarPermisosUsuarioForm" property="menuActivos" />
			<html:hidden property="cambiar" value="true" />
			<table width="100%" border="0" cellspacing="1" cellpadding="5">
				<tr valign="topt">
					<td colspan="2" align="center">
						<table border="0" cellspacing="1" cellpadding="5">
							<tr>
								<td align="right" width="100%">
									<strong> <bean:message key="texto.etiqueta.nombre" />:</strong>
								</td>
								<td align="left">
									<html:text styleClass="textfield" name="GenerarPermisosUsuarioForm" maxlength="50" size="50" onkeypress="return isAlphanumeric(event)"
										property="nombrePerfil" />
								</td>
							</tr>
							<tr>
								<td align="right">
									<strong> <bean:message key="texto.etiqueta.estado.actual" />:</strong>
								</td>
								<td align="left">
									<html:select property="estado" name="GenerarPermisosUsuarioForm" styleClass="textfield">
										<html:option value="-1">
											<bean:message key="combo.opcion.seleccionar" />
										</html:option>
										<html:optionsCollection name="GenerarPermisosUsuarioForm" property="estados" value="estado"
											label="descripcion" />
									</html:select>
								</td>
							</tr>
							<tr>
							<td colspan="2">
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
							</td>
							</tr>
							<tr>
					<td align="right">
						<html:submit styleClass="submit">
							<bean:message key="boton.guardar" />
						</html:submit>
						</td>
						<td align="left">
						<html:cancel styleClass="submit">
							<bean:message key="boton.cancelar" />
						</html:cancel>
					</td>
				</tr>
			</table>
					</td>					 				
					<td align="left" height="100%">
						<table width="100%" height="100%" border="0" cellspacing="1" cellpadding="8">
							<tr>
								<td align="left">
									<strong> <bean:message key="texto.etiqueta.perfil.opciones.sistema" />:</strong>
								</td>
							</tr>
							<tr>
								<td align="left">
									<div id="div_arbol" style="width:100%; height:405px;overflow:scroll;"></div>
								</td>
							</tr>
						</table>
					</td>
				</tr>				
			</table>
			
		</html:form>
	</body>
</html>
<script language="javascript" type="text/javascript">
var idPermiso=obtenerElemento("idPerfil");
tree2 = new dhtmlXTreeObject("div_arbol", "100%", "100%", 0); 
tree2.setSkin('dhx_skyblue');
tree2.setImagePath("/armadoras/neology/recursos/imagenes/arbol/csh_vista/");
tree2.enableThreeStateCheckboxes(true);
tree2.enableCheckBoxes(1);
tree2.loadXML("/armadoras/neology/paginas/catalogos/permisos/Permiso"+idPermiso.value+".xml",marcaSeleccionados);

  
 function marcaSeleccionados(){
 var menuActivos=obtenerElemento("menuActivos");
 var activos=menuActivos.value.split(";");
  for ( i=0; i<activos.length; i++ ){ 
      tree2.setSubChecked(activos[i],true);
   }  
 }
    
function guardarPerfil(){
var seleccionados=obtenerElemento("menuSeleccionados");
seleccionados.value=tree2.getAllChecked();

}

function isAlphanumeric(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    
    if ((charCode < 48 || charCode > 57)&& (charCode < 97 || charCode > 122) &&(charCode!=241)&&(charCode < 65 || charCode > 90)&&(charCode!=209)&&(charCode!=32)){
        return false;
        };
}
</script>		
