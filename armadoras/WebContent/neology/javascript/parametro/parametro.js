var request;
var ordenId;
var tipoFormato;
/**
* Graba el chip
*/
function leerDatos(url) {
    //ordenId = id;
    //  habilitarBotones("listaTramitesForm", false);    
    request = getRequest();
    request.open("GET", url, true);
    request.onreadystatechange = respuestaParametros;
    request.send(null);
    //habilitarBotones();
    return false;
}
/**
* Respuesta del Servidor, verifica condiciones del formato a Grabar
*/

function respuestaParametros() {
    if (request.readyState == 4) {
        if (request.status == 200) {
            var result = request.responseText.split(";");                        
            varvalor=new String(result[0]);
            hIni=new String(result[1]);            
            hFin=new String(result[2]);            
            document.getElementById('valor').value=varvalor;  
            document.getElementById('horaInicial').value=hIni;
            document.getElementById('horaFinal').value=hFin;                          
        }
    }
}

function decrementaHora(varHora) {   
    var hora = document.getElementById(varHora);            
    if(hora.value > 0)
        hora.value--;
    else
        hora.value=0;
}
    
function incrementaHora(varHora) {
    var hora = document.getElementById(varHora);            
    if(hora.value < 23)
        hora.value++;
    else
        hora.value=23;
}
    
function decrementaMinutos(varMinutos) {
    var minutos = document.getElementById(varMinutos);            
    if(minutos.value > 0)
        minutos.value--;
    else
        minutos.value=0;
}
    
function incrementaMinutos(varMinutos) {
    var minutos = document.getElementById(varMinutos);            
    if(minutos.value < 59)
        minutos.value++;
    else
        minutos.value=59;
}



