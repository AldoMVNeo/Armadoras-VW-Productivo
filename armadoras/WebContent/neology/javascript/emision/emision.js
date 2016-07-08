var request;
var contexto;
var my_applet=null;

function emision() {
	mostrarDiv(false, "divMensajesError");
	habilitarBotones("emisionForm", false);
}

/**
*Envia peticion al servidor para realizar el apartado del tramite
*/
function grabarChip(contextov, url) {
	contexto = contextov;
	//mostrarDiv(false, "mensajeError");
	//habilitarBotones("emisionForm", false);
	request = getRequest();
	request.open("GET", contexto + url, true);
	request.onreadystatechange = respuestaChip;
	request.send(null);
    //habilitarBotones();
	return false;
}
/**
* Respuesta del Servidor, verifica condiciones del formato a Grabar
*/
function respuestaChip() {
	if (request.readyState == 4) {
		if (request.status == 200) {
			var result = request.responseText;
			if (result == "-1") {
				mostrarDiv(true, "mensajeError");
				habilitarBotones("emisionForm", true);
			} else {
				var datos = result.split("||");
				var ordenId = datos[0];
				var imagen = datos[1];
				var tipo = datos[2];
				divFondo();
				divTipoFormato("emisionForm", imagen, tipo);
			}
		}
	}
}

/**
* Respuesta del Servidor, actualiza estado en caso de error en la impresión
*/
function respuestaFinalizar() {
	if (request.readyState == 4) {
		if (request.status == 200) {
			var result = request.responseText;
			if (result == "OK") {
			var id=obtenerElemento("idOrdenImpresion");	
			reemplazar(contexto + "/emisionFormatos.do?do=inicio&idOrden="+id.value);
			} else {
			reemplazar(contexto + "/emisionFormatos.do?do=inicio");
			}
		}
	}
}

/**
*Redirecciona para establacer valores del tramite que se aparto
**/
function establecerValores() {
	reemplazar(contexto + "/emisionFormatos.do?do=establecerValores");
}

/**
*Valida la selección de Estado de Impresion del Formato
**/
function verificaSeleccionEstado(ctx) {
	contexto=ctx;
	habilitarBotones("emisionForm", false);
	var estado = window.document.forms[0].estadoSel;
	var longitud = estado.length;
	var seleccion = -1;
	for (i = 0; i < longitud; i++) {
		if (estado[i].checked == true) {
			seleccion = estado[i].value;
		}
	}
	if (seleccion == -1) {
		alert("Debe seleccionar una opcion");
		habilitarBotones("emisionForm", true);
	} else {		
		if (seleccion == 0) {
			reemplazar(contexto + "/emisionFormatos.do?do=inicio");
		} else {
			request = getRequest();			
			request.open("GET", contexto + "/emisionFormatos.do?do=cancelarFormato&estadoSel="+seleccion, true);
			request.onreadystatechange = respuestaFinalizar;
			request.send(null);
		}
	}
}

/**
*Muestra el reporte para imprimir
**/
function mostrarReporteTP(ctx) {
	divMensajeEspera("emisionForm", "Generando Reporte para impresi\xf3n, espere un momento por favor...");
	habilitarBotones("emisionForm", false);	
	var id = obtenerElemento("idOrdenImpresion");
	var nr = obtenerElemento("nombreReporte");
	var src = ctx + "/impresionFormatos.do?nombreReporte=" + nr.value + "&ordenId=" + id.value;
	var iframe = crearElemento("iframe", ["id", "reporte"], ["src", src], ["scrolling", "no"], ["height", "100%"], ["width", "100%"], ["frameborder", "0"]);
	document.body.appendChild(iframe);	
	mostrarDiv(false, "divConfirm");
	cursorDefault();
	habilitarBotones("emisionForm", true);
}

function imprimirFormatos(ctx){
var iframe=obtenerElemento("appletImprimir");
alert(iframe);
iframe.sendMsgUsingCall( "updateResult" );

/*var campo=obtenerElemento("numeroImpresiones");
var i=0;
var src = ctx + "/emisionFormatos.do?do=imprimirCB";
for(i=0;i<campo.value;i++){
imprime(src);
}*/

}

function respuestaImpresion() {
var res = Thread.Http.get(contexto + "/emisionFormatos.do?do=imprimirCB");
alert(res);
//ajax();

//requesto = getRequesto();
	//requesto.open("GET",contexto + "/emisionFormatos.do?do=imprimirCB", true);
	
	/*requesto.onreadystatechange=function(){
	if (requesto.readyState == 4) {
		if (requesto.status == 200) {
			var result = requesto.responseText;			
				var niv = result
				alert(niv);
				//my_applet.imprimir("",niv);
			
		}
		else{
		alert("un error ocurrio");
		}
	}
	}*/
}
function imprimirCB(ctx){
var divApplet=obtenerElemento("divImprimir");
var iframe=obtenerElemento("iframeImprimir");
var numero=obtenerElemento("numeroImpresiones");
var src=ctx+"/neology/paginas/emision/impresion/imprimirCB.jsp?numeroImpresiones="+numero.value;
ajax(numero.value);
/*if(iframe!=null)
divApplet.removeChild(iframe);
iframe = crearElemento("iframe", ["id", "iframeImprimir"], ["src", src], ["scrolling", "no"], ["height", "100%"], ["width", "100%"], ["frameborder", "0"],["class", "border"]);
divApplet.appendChild(iframe);*/
}
function ajax(numero){
var requesto = getRequesto();
	requesto.open("GET",'/armadoras/emisionFormatos.do?do=imprimirCB&numeroImpresiones='+numero, true);	
	requesto.onreadystatechange=function(){
	if (requesto.readyState == 4) {
		if (requesto.status == 200) {
			var result = requesto.responseText;	
			alert(my_applet);					
				my_applet.imprimir("",result);			
		}
		
		else{
		alert(requesto.status);
		}
		}
	}
	
	requesto.send(null);
 
}


function imprime(src){
var divApplet=obtenerElemento("divApplet");
var iframe=obtenerElemento("appletImprimir");
alert(iframe);

if(iframe!=null)
divApplet.removeChild(iframe);
iframe = crearElemento("iframe", ["id", "imprimirIframe"], ["src", src], ["scrolling", "no"], ["height", "100%"], ["width", "100%"], ["frameborder", "0"],["class", "border"]);
divApplet.appendChild(iframe);
}


function appletLoaded( applet )
{
	my_applet = applet;
	var result = document.getElementById('resultadoApplet');
	while( result.childNodes.length > 0 )
		result.removeChild( result.childNodes[ 0 ] );
		
	try
	{			

	}catch( e )
	{
	
		
	}
}
