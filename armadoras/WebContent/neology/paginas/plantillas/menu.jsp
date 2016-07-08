<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="util" class="neology.util.Utilidades" scope="request"/>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title></title> 
   <meta name="author" content="Neology"> 
    <link rel="STYLESHEET" type="text/css" href="<%=util.getContexto(request)%>/neology/javascript/menu/skins/dhtmlxmenu_modern_blue.css">
   <script language="JavaScript" src="<%=util.getContexto(request)%>/neology/javascript/menu/dhtmlxcommon.js"></script>
   <script language="JavaScript" src="<%=util.getContexto(request)%>/neology/javascript/menu/dhtmlxmenu.js"></script> 
  </head>
    <body onload="cargaMenu('<%=util.getContexto(request)%>');">
    <div id="menuObj"></div>
	<script>	
	var menu;
	function cargaMenu(contexto) {	
		menu = new dhtmlXMenuObject("menuObj","modern_blue");		
		menu.loadXML(contexto+"/neology/paginas/sistema/generadorMenu.jsp?e=" + new Date().getTime());
		menu.setImagePath(contexto+"/neology/javascript/menu/imgs/");
		menu.setIconsPath(contexto+"/neology/recursos/imagenes/");
		 menu.attachEvent("onClick", function(itemId){
        if (menu.getUserData(itemId, "url") != null) { document.location.href = menu.getUserData(itemId, "url"); }
    })		
	}		
	</script>
	</body>
	</html>
	
