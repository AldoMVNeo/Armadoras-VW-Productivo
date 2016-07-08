var appletGrabacion=null;
function cargarAppletGrabacion( applet )
{
	appletGrabacion = applet;
	alert(appletGrabacion);
	var result = document.getElementById('resultadoApplet');
	while( result.childNodes.length > 0 )
		result.removeChild( result.childNodes[ 0 ] );		
	
}