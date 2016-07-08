function getRequest() {
    var request;
	if (window.XMLHttpRequest) {
		request = new XMLHttpRequest();
	} else {
		try {
			request = new ActiveXObject("Msxml2.XMLHTTP");                         
		}
		catch (e1) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");                               
			}
			catch (e2) {
				request = null;
				alert("El sistema de ejecutarse en un navegador compatible Internet Explorer o Mozilla Firefox.");
			}
		}
	}
       
        return request;
}

/**Función genera un mensaje de acuerdo al estado de XMLHttpRequest
 **/

function getMensajeRequestAjax(request, response, tipo) {
  
	return function () {	
		switch (request.readyState) {
		  case 0:
			mensajeDiv("Sin inicializar");
			break;
		  case 1:
			mensajeDiv("Cargando...");
			break;
		  case 2:
			mensajeDiv("Cargado Terminado");
			break;
		  case 3:
			mensajeDiv("Procesando...");
			break;
		  case 4:
			mensajeDiv("Terminado");
			switch (request.status) {
			  case 200:
				if (tipo == "xml") {
					responseXmlHandler(req.responseXML);
				} else {
					if (tipo == "txt") {
						responseXmlHandler(request.responseText);
					} else {
						alert("Problemas con parametro Tipo no es reconocido(" + type + ")");
						return false;
					}
				}
				break;
			  case 404:
				alert("Problemas al tratar de encontrar la pagina.");
				break;
			
			  case 12152:
				alert("Se ha superado el tiempo de espera, inténtelo nuevamente por favor.");
				break;
			  case 400:
				alert(" Problemas con el request");
				break;
			  case 403:
				alert(" Acceso denegado");
				break;
			  case 404:
				alert(" Acceso encontrado");
				break;
			  case 500:
				alert(" Error interno del servidor");
				break;
			  case 505:
				alert(" HTTP VERSION NO SOPORTADA");
				break;
			  default:
				alert("Favor de darle este mensaje al administrador \"HTTP error: " + request.status + "\" \n");
				break;
			}
			break;
		}
	};
}

/**Muestra mensaje de petición AJAX en pantalla
 **/
function mensajeDiv(mensaje) {
	var nombreDiv = "divMsg";
	var tipo = "div";
	var nombreClase = "msg";
	var div = document.getElementById(nombreDiv);        
	if (!div) {
		div = crearElemento(nombreDiv,tipo, nombreClase);
	}
	div.innerHTML = mensaje;
	/**
	* Colocar en Pantalla
	*/      
	var divWidth = 200;
	var divHeight = 30;
	var xTop = 20;
	var xLeft = 5;
	var zIndex = 200;
	setLocationProperties(div, divWidth, divHeight, xTop, xLeft, zIndex);
	setTimeout(function () {
		div.style.display = "none";
	}, 1000);
}

