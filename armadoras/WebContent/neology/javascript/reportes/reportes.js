function generarReporte(contexto){   
mostrarDiv(false,"divMensajesError");
}

/**
*Muestra el reporte de totales de Constancias de Inscripcion
**/
function mostrarReporteTotales(ctx) {
	divMensajeEspera("reporteConstanciasForm", "Generando Reporte para impresi\xf3n, espere un momento por favor...");
	habilitarBotones("reporteConstanciasForm", false);	
	var fi = obtenerElemento("fechaInicial");
	var ff = obtenerElemento("fechaFinal");	
	var estado = obtenerElemento("estado");
	var src = ctx + "/reporteConstanciasInscripcion.do?do=generarTotalesConstancias&fechaInicial="+fi.value+"&fechaFinal="+ff.value+"&estado="+estado.value;
	var iframe = crearElemento("iframe", ["id", "reporteTotales"], ["src", src], ["scrolling", "no"], ["height", "100%"], ["width", "100%"], ["frameborder", "0"]);
	document.body.appendChild(iframe);	
	mostrarDiv(false, "divConfirm");
	cursorDefault();
	habilitarBotones("reporteConstanciasForm", true);
}

   