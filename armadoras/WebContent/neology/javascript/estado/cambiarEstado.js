function realizarCambioEstado(){
	mostrarDiv(false,"divMensajesError");
    habilitarBotones("cambiarEstadoForm",false);
    divMensajeEspera("cambiarEstadoForm","El proceso del Cambio de Estado puede tardar algunos minutos, sea paciente...");

}

/**
*Envia peticion al servidor para realizar el apartado del tramite
*/
function estadoNuevo(contextov, url) {
	contexto = contextov;
//	alert("contexto " + contexto);
//	alert("url " + url);
	
	request = getRequest();
	request.open("GET", contexto + url, true);
	request.onreadystatechange = respuestaChip;
	request.send(null);

	return false;
}
/**
* Respuesta del Servidor, verifica condiciones del formato a Grabar
*/
function respuestaChip() {
	if (request.readyState == 4) {
		if (request.status == 200) {
			var result = request.responseText;
			parseResults(result);
		}
	}
}


function parseResults(pintaDatos) {

//            var responseText = document.createTextNode(pintaDatos);
document.getElementById('estadoNuevo').options.length=0;
            var returnElements=pintaDatos.split(";");     
          //Process each of the elements 	

          for ( var i=0; i<returnElements.length; i++ ){
             if(returnElements[i]!="" && returnElements[i].length>1)
            {           
             valueLabelPair = returnElements[i].split("|")

             document.getElementById('estadoNuevo').options.length= returnElements.length;  
              document.getElementById('estadoNuevo').options[i] = new Option(valueLabelPair[1],valueLabelPair[0]);
            } 
        }
}
