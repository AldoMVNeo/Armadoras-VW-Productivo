var imprime_applet=null;

function imprimirCB(ctx){
var divApplet=obtenerElemento("divImprimir");
var iframe=obtenerElemento("iframeImprimir");
var cadena=obtenerElemento("cadena");
var src=ctx+"/neology/paginas/emision/impresion/imprimirCB.jsp?cadena="+cadena.value;

if(iframe!=null)
divApplet.removeChild(iframe);
iframe = crearElemento("iframe", ["id", "iframeImprimir"], ["src", src], ["scrolling", "no"], ["height", "100%"], ["width", "100%"], ["frameborder", "0"],["class", "border"]);
divApplet.appendChild(iframe);
}

function verificaJava(){
var result = document.getElementById('resultadoApplet');
result.removeChild( result.childNodes[ 0 ] );
result.appendChild( document.createTextNode( navigator.javaEnabled() ?
											 "Cargando" : 
											 "Sorry, Java must be enabled for this demonstration" ) );
}

function appletLoaded( applet )
{
	 imprime_applet= applet;
	
	var result = document.getElementById('resultadoApplet');
	while( result.childNodes.length > 0 )
		result.removeChild( result.childNodes[ 0 ] );
		
	try
	{			
	
	}catch( e )
	{
	
		
	}
}



/**
*Muestra el reporte para imprimir
**/
function mostrarReporteTP(ctx) {
	divMensajeEspera("impresionesForm", "Generando Reporte para impresi\xf3n, espere un momento por favor...");
//	habilitarBotones("emisionForm", false);	
//	var id = obtenerElemento("idOrdenImpresion");
//	var nr = obtenerElemento("nombreReporte");
	var src = ctx + "/impresiones.do?do=imprimePDF";	
	var iframe = crearElemento("iframe", ["id", "reporte"], ["src", src], ["scrolling", "no"], ["height", "100%"], ["width", "100%"], ["frameborder", "0"]);
	document.body.appendChild(iframe);	
	mostrarDiv(false, "divConfirm");
	cursorDefault();
//	habilitarBotones("emisionForm", true);
}


/**
*Muestra el reporte para imprimir
**/
function mostrarReImpReporteTP(ctx) {
	divMensajeEspera("reImpresionesForm", "Generando Reporte para impresi\xf3n, espere un momento por favor...");
//	habilitarBotones("emisionForm", false);	
//	var id = obtenerElemento("idOrdenImpresion");
//	var nr = obtenerElemento("nombreReporte");
	var src = ctx + "/reimpresion.do?do=imprimePDF";	
	var iframe = crearElemento("iframe", ["id", "reporte"], ["src", src], ["scrolling", "no"], ["height", "100%"], ["width", "100%"], ["frameborder", "0"]);
	document.body.appendChild(iframe);	
	mostrarDiv(false, "divConfirm");
	cursorDefault();
//	habilitarBotones("emisionForm", true);
}