<?xml version='1.0' encoding='iso-8859-1'?>
<%@page import="neology.modelo.dto.Usuario"%>
<%@page import="neology.util.menu.Menu"%>
<%@page import="neology.util.menu.GeneradorMenu"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set" %>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/xml; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Menu menu = null;
	if (session.getAttribute("usuarioLog") != null) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLog");
		
		menu = GeneradorMenu.getMenu(usuario.getPerfil()
				.getPermisosMenus());
%>
<%=menu.renderToXML()%>
<%
	}
%>
