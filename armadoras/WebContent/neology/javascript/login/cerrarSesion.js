function unloadMess() {
	var size = document.body.offsetWidth - 32;
	if (event && event.clientY) {
		if (event.clientY < 0 && event.clientX >= size) {	
			window.location.href = "/armadoras/cerrarSesion.do";
		}
	}
}
function setBunload(on) {
	window.onbeforeunload = (on) ? unloadMess: null;
}
if (navigator.appName.indexOf("Microsoft") != -1) {
	setBunload(true);
} else {
	setBunload(false);
	}