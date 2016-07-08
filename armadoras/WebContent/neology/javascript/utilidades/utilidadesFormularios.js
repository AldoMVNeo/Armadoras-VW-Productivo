
/**
* Deshabilita Botones de un formulario
*/
function habilitarBotones(nombreFormulario, accion) {
	var formulario = getFormulario(nombreFormulario);	
	for (i = 0; i < formulario.length; i++) {
		var elemento = formulario.elements[i];	
		if (elemento.type == "submit") {
			elemento.disabled = !accion;
		} else {
			if (elemento.type == "reset") {
				elemento.disabled = !accion;
			} else {
				if (elemento.type == "button") {
					elemento.disabled = !accion;
				}						
			}
		}
	}
}
/**
* Obtiene un formulario
*/
function getFormulario(nombre) {
	return document.forms[nombre];
}
/**
Regresa un elemto HTML con sus atributos
*/
function crearElemento(elm) {
	var el = document.createElement(elm);		
	if (arguments.length > 1) {
		for (var i = 0; i < arguments.length; i++) {
			var argtype = typeof arguments[i];
			switch (argtype.toLowerCase()) {
			  case "object":
				if (arguments[i].length == 2) {
					el.setAttribute(arguments[i][0], arguments[i][1]);
				}
				break;
			}
		}
	}
	return el;
}
/**
Funcion muestra un DIV en pantalla
*/
function mostrarDiv(mostrar, id) {
	var visibility = mostrar ? "visible" : "hidden";
	var obj = obtenerElemento(id);
	if (obj) {
		obj.style.visibility = visibility;
	}
}
/**
 * Funcion que crea DIV  para notificar el Tipo de Formato en la Emision
 */
function divTipoFormato(form, imagen, tipoFormato) {
	var nombre = "divTipoReporte";
	var tipo = "div";
	var estilo = "MensajeDiv";
	var div = crearDiv(nombre, tipo, estilo);
	var divWidth = 450;
	var divHeight = 250;
	var objeto = obtenerElemento("imprimir");
	var h=obtenerDimensiones(objeto).h;
	var w=obtenerDimensiones(objeto).w;
	var xTop = h / 2;
	var xLeft = w / 2;
	var zIndex = 11;
	setObjetoEnPantalla(div, divWidth, divHeight, xTop,xLeft, zIndex);
	obtenerElemento("divTipoReporte").innerHTML = generarDivTipoFormato(imagen, tipoFormato);
	insertarFlash(imagen, "animacion");
}
/**
 * DIV Tipo de Formato 
 */
function generarDivTipoFormato(imagen, tipoFormato) {
	var nombreImagen = new String(imagen);
	var enter = "<br>";
	var html = "<div align='center'><strong><font color='#ff0000' size='4'>" + tipoFormato + "</font></strong></div>";
	var divFlash = "<div  id='animacion'>Necesita instalar Flash para poder ver la animacion.</div>";
	var divPie = "<div align='center' id='pie'><table><tr><td><input type='button' class='submit' value='Continuar' onclick='establecerValores();'/></td><td><input type='button' class='submit' value='Cancelar' onclick='cancelarImpresion();'/></td></tr></div>";
	html += divFlash;
	html += enter;
	html += divPie;
	return html;
}

function cancelarImpresion(){
var divReporte=obtenerElemento('divTipoReporte');
var divFondo=obtenerElemento('divDefault');
document.body.removeChild(divReporte);
document.body.removeChild(divFondo);
}
/**
 * Inserta pelicula flash en un DIV 
 */
function insertarFlash(nombre, div) {
	var pelicula = new FlashObject("/armadoras/neology/recursos/flash/" + nombre, "pelicula", "450", "180", "6", "#ffffff");
	pelicula.write(div);
}
/**
 * Funcion que crea DIV  de un mensaje de espera
 */
function divMensajeEspera(form, mensaje) {
	var nombre = "divConfirm";
	var tipo = "div";
	var estilo = "divMensajeEspera";
	var div = obtenerElemento(nombre);	
	if (!div) {
		div = crearDiv(nombre, tipo, estilo);
	}
	cursorEnEspera();	
	var divWidth = 350;
	var divHeight = 80;
	var xTop = ((screen.availHeight - 600) / 2) - (divHeight / 2);
	var xLeft = ((screen.availWidth - 200) / 2) - (divWidth / 2);
	var zIndex = 11;	
	setObjetoEnPantalla(div, divWidth, divHeight, xTop, xLeft, zIndex);
	divConfirm.innerHTML = divEspera(mensaje);
}
/**
 * Obtiene Dimensiones de un componente 
 */
function obtenerDimensiones(componente) {
	var x = 0, y = 0, w = 0, h = 0;
	if (document.getBoxObjectFor) { // Mozilla   
		var oBox = document.getBoxObjectFor(componente);
		x = oBox.x - 1;
		w = oBox.width;
		y = oBox.y - 1;
		h = oBox.height;
	} else {
		if (componente.getBoundingClientRect) {//IE
			var oRect = componente.getBoundingClientRect();
			x = oRect.left - 2;
			w = componente.clientWidth;
			y = oRect.top - 2;
			h = componente.clientHeight;
		}
	}
	return {x:x, y:y, w:w, h:h};
}
/**
*Div mensaje de Espera
*/
function divEspera(msg) {
	return "<div align='center' ><table border='0' cellspacing='0' cellpadding='10'><tr><td><img src='/armadoras/neology/recursos/imagenes/cargando.gif' width='32' height='32'/></td><td align='left'>" + msg + "</td></tr></table></div>";
}

/**
 * Cambia el cursor del mouse a default
 */
function cursorDefault() {
	document.body.style.cursor = "default";
}

/**
 * Cambia el cursor del mouse a espera
 */
function cursorEnEspera() {
	document.body.style.cursor = "wait";
}
/**
* Funcion para indicar la posicion de el Objeto
*
*/
function setObjetoEnPantalla(divDefault, winWitdh, winHeight, jsTop, jsLeft, jsZIndex) {
	divDefault.style.top = jsTop + "px";
	divDefault.style.left = jsLeft + "px";
	divDefault.style.width = winWitdh + "px";
	divDefault.style.height = winHeight + "px";
	divDefault.style.zIndex = jsZIndex;
	divDefault.style.display = "";
}
/**
 *Es solo un atajo para document.getElementById
 */
function obtenerElemento(id) {
var elm=document.getElementById(id);
if(elm==null)
	elm=document.all(id);
return elm;
}
/**
 *Es solo un atajo para document.createTextNode
 */
function crearTexto(nodo) {
	return document.createTextNode(nodo);
}
/**
Funcion que crea un Div Como Fondo
*/
function divFondo() {
	var nombre = "divDefault";
	var tipo = "div";
	var estilo = "divFondo";
	var div = document.getElementById(nombre);
	if (!div) {
		div = crearDiv(nombre, tipo, estilo);
	}
	// Colocar en Pantalla	
	var divWidth = screen.availWidth;
	var divHeight = screen.availHeight;
	var xTop = 0;
	var xLeft = 0;
	var zIndex = 11;
	setObjetoEnPantalla(div, divWidth, divHeight, xTop, xLeft, zIndex);
}
/*
 * Funcion crea un Div
 */
function crearDiv(nombre, tipo, estilo) {
	var divDefault;
	divDefault = document.createElement(tipo);
	divDefault.className = estilo;
	divDefault.id = nombre;
	document.body.appendChild(divDefault);
	return divDefault;
}
/**
 * Realiza un submit al formulario
 */
function submit(form, url) {
	var formulario = getFormulario(form);
	formulario.onsubmit = "";
	formulario.action = url;
	formulario.submit();	
}
/**
 * Reemplaza la pagina actual con la nueva url
 */
function reemplazar(url){
window.location.replace(url);
}

function verificaJava(){
var result = document.getElementById('resultadoApplet');
result.removeChild( result.childNodes[ 0 ] );
result.appendChild( document.createTextNode( navigator.javaEnabled() ?
											 "Cargando..." : 
											 "Debe tener java instalado para la ejecucion del sistema." ) );
}
