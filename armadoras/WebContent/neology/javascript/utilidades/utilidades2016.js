var flagDate = 0
var inicialDate = 0;

function blockComponents() {

	document.getElementById("inputTodasEntidades").style.visibility = "hidden";
	document.getElementById("reportButton").disabled = true;
	document.getElementById("reportButton").style.color = "#808080";
	document.getElementById("waitMessage").style.visibility = "hidden";
	document.getElementsByName("strOptionHidden6")[0].checked = true;
	document.getElementsByName("strOptionHidden6")[0].style.visibility = "hidden";
	document.getElementById("divFolio").style.visibility = "hidden";
	document.getElementById("divFecha").style.visibility = "hidden";
	document.getElementById("divTID").style.visibility = "hidden";
	document.getElementById("divObservaciones").style.visibility = "hidden";
	document.getElementById("divEstadoActual").style.visibility = "hidden";
	document.getElementById("divTipo").style.visibility = "hidden";
	document.getElementById("divEntidad").style.visibility = "hidden";
	document.getElementById("divResponsable").style.visibility = "hidden";
	document.getElementById("divEvento").style.visibility = "hidden";
	document.getElementById("divEventoUltimo").style.visibility = "hidden";
	document.getElementById("divChasis").style.visibility = "hidden";

	document.getElementsByName("strEntidad")[0].value = "-1";
	document.getElementsByName("strColumnFolio")[0].disabled = true;
	document.getElementsByName("strColumnFecha")[0].disabled = true;
	document.getElementsByName("strColumnTID")[0].disabled = true;
	document.getElementsByName("strColumnObservaciones")[0].disabled = true;
	document.getElementsByName("strColumnEstadoActual")[0].disabled = true;
	document.getElementsByName("strColumnTipo")[0].disabled = true;
	document.getElementsByName("strColumnResponsable")[0].disabled = true;
	document.getElementsByName("strColumnChasis")[0].disabled = true;
	document.getElementsByName("strColumnEntidad")[0].disabled = true;
	document.getElementsByName("strColumnEvento")[0].disabled = true;

	document.getElementById("divResponsable").style.visibility = "hidden";
	document.getElementById("divEvento").style.visibility = "hidden";
	document.getElementById("divEventoUltimo").style.visibility = "hidden";
	document.getElementsByName("strOrdenColumnas")[0].style.visibility = "hidden";
	document.getElementById("ordenColumnas").innerHTML = document
			.getElementsByName("strOrdenColumnas")[0].value;

	if (document.getElementById("inputTodasEntidades").value == 0) {

		document.getElementsByName("strEntidad")[0].remove(0);
	}
	;

	// // strOptionHidden1
	// if (document.getElementsByName("strOptionHidden1")[0].checked
	// || (document.getElementsByName("strOptionHidden2")[0].checked || document
	// .getElementsByName("strOptionHidden3")[0].checked)) {
	//
	// document.getElementById("reportButton").disabled = false;
	// document.getElementById("reportButton").style.color = "#ffffff";
	//
	// } else {
	//
	// document.getElementById("reportButton").disabled = true;
	// document.getElementById("reportButton").style.color = "#808080";
	// }
	// ;

	// strOptionHidden1
	if (document.getElementsByName("strOptionHidden1")[0].checked) {

		document.getElementsByName("fechaInicial")[0].disabled = false;
		document.getElementsByName("fechaFinal")[0].disabled = false;
		document.getElementsByName("strColumnFecha")[0].disabled = false;
		document.getElementsByName("strColumnFolio")[0].disabled = false;
		document.getElementById("divFolio").style.visibility = "visible";
		document.getElementById("divFecha").style.visibility = "visible";
		document.getElementById("divTID").style.visibility = "visible";
		document.getElementById("divObservaciones").style.visibility = "visible";
		document.getElementById("divEstadoActual").style.visibility = "visible";
		document.getElementById("divTipo").style.visibility = "visible";
		document.getElementById("divEntidad").style.visibility = "visible";
		document.getElementById("divChasis").style.visibility = "visible";

		document.getElementsByName("strColumnTID")[0].disabled = false;
		document.getElementsByName("strColumnObservaciones")[0].disabled = false;
		document.getElementsByName("strColumnEstadoActual")[0].disabled = false;
		document.getElementsByName("strColumnTipo")[0].disabled = false;
		document.getElementsByName("strColumnResponsable")[0].disabled = false;
		document.getElementsByName("strColumnChasis")[0].disabled = false;
		document.getElementsByName("strColumnEntidad")[0].disabled = false;
		document.getElementsByName("strColumnEvento")[0].disabled = false;

		document.getElementById("finalMessage").innerHTML += " - Fecha";

		if (document.getElementsByName("strColumnFolio")[0].checked) {

			document.getElementsByName("strColumnFolio")[0].checked = true;

		} else {

			document.getElementsByName("strColumnFolio")[0].checked = false;
		}
		;

		if (document.getElementsByName("strColumnFecha")[0].checked) {

			document.getElementsByName("strColumnFecha")[0].checked = true;

		} else {

			document.getElementsByName("strColumnFecha")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnTID")[0].checked) {

			document.getElementsByName("strColumnTID")[0].checked = true;

		} else {

			document.getElementsByName("strColumnTID")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnObservaciones")[0].checked) {

			document.getElementsByName("strColumnObservaciones")[0].checked = true;

		} else {

			document.getElementsByName("strColumnObservaciones")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnEstadoActual")[0].checked) {

			document.getElementsByName("strColumnEstadoActual")[0].checked = true;

		} else {

			document.getElementsByName("strColumnEstadoActual")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnTipo")[0].checked) {

			document.getElementsByName("strColumnTipo")[0].checked = true;

		} else {

			document.getElementsByName("strColumnTipo")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnResponsable")[0].checked) {

			document.getElementsByName("strColumnResponsable")[0].checked = true;

		} else {

			document.getElementsByName("strColumnResponsable")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnChasis")[0].checked) {

			document.getElementsByName("strColumnChasis")[0].checked = true;

		} else {

			document.getElementsByName("strColumnChasis")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnEntidad")[0].checked) {

			document.getElementsByName("strColumnEntidad")[0].checked = true;

		} else {

			document.getElementsByName("strColumnEntidad")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnEvento")[0].checked) {

			document.getElementsByName("strColumnEvento")[0].checked = true;

		} else {

			document.getElementsByName("strColumnEvento")[0].checked = false;

		}
		;

	} else {

		document.getElementsByName("fechaInicial")[0].disabled = true;
		document.getElementsByName("fechaFinal")[0].disabled = true;

	}
	// ;

	// strOptionHidden2
	// if (document.getElementsByName("strOptionHidden2")[0].checked
	// || (document.getElementsByName("strOptionHidden1")[0].checked || document
	// .getElementsByName("strOptionHidden3")[0].checked)) {
	//
	// document.getElementById("reportButton").disabled = false;
	// document.getElementById("reportButton").style.color = "#ffffff";
	//
	// } else {
	//
	// document.getElementById("reportButton").disabled = true;
	// document.getElementById("reportButton").style.color = "#808080";
	// }
	// ;

	// strOptionHidden2
	if (document.getElementsByName("strOptionHidden2")[0].checked) {

		document.getElementsByName("estado")[0].disabled = false;
		document.getElementById("finalMessage").innerHTML += " - Estado";
		document.getElementsByName("strColumnFecha")[0].disabled = false;
		document.getElementsByName("strColumnFolio")[0].disabled = false;
		document.getElementsByName("strColumnTID")[0].disabled = false;
		document.getElementsByName("strColumnObservaciones")[0].disabled = false;
		document.getElementsByName("strColumnEstadoActual")[0].disabled = false;
		document.getElementsByName("strColumnTipo")[0].disabled = false;
		document.getElementsByName("strColumnResponsable")[0].disabled = false;
		document.getElementsByName("strColumnChasis")[0].disabled = false;
		document.getElementsByName("strColumnEntidad")[0].disabled = false;
		document.getElementsByName("strColumnEvento")[0].disabled = false;
		document.getElementById("divFolio").style.visibility = "visible";
		document.getElementById("divFecha").style.visibility = "visible";
		document.getElementById("divTID").style.visibility = "visible";
		document.getElementById("divObservaciones").style.visibility = "visible";
		document.getElementById("divEstadoActual").style.visibility = "visible";
		document.getElementById("divTipo").style.visibility = "visible";
		document.getElementById("divEntidad").style.visibility = "visible";
		document.getElementById("divChasis").style.visibility = "visible";

		if (document.getElementsByName("strColumnFolio")[0].checked) {

			document.getElementsByName("strColumnFolio")[0].checked = true;
			document.getElementsByName("strColumnFolio")[0].disabled = false;

		} else {

			document.getElementsByName("strColumnFolio")[0].checked = false;
		}
		;

		if (document.getElementsByName("strColumnFecha")[0].checked) {

			document.getElementsByName("strColumnFecha")[0].checked = true;

		} else {

			document.getElementsByName("strColumnFecha")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnTID")[0].checked) {

			document.getElementsByName("strColumnTID")[0].checked = true;

		} else {

			document.getElementsByName("strColumnTID")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnObservaciones")[0].checked) {

			document.getElementsByName("strColumnObservaciones")[0].checked = true;

		} else {

			document.getElementsByName("strColumnObservaciones")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnEstadoActual")[0].checked) {

			document.getElementsByName("strColumnEstadoActual")[0].checked = true;

		} else {

			document.getElementsByName("strColumnEstadoActual")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnTipo")[0].checked) {

			document.getElementsByName("strColumnTipo")[0].checked = true;
		} else {

			document.getElementsByName("strColumnTipo")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnResponsable")[0].checked) {

			document.getElementsByName("strColumnResponsable")[0].checked = true;

		} else {

			document.getElementsByName("strColumnResponsable")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnChasis")[0].checked) {

			document.getElementsByName("strColumnChasis")[0].checked = true;

		} else {

			document.getElementsByName("strColumnChasis")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnEntidad")[0].checked) {

			document.getElementsByName("strColumnEntidad")[0].checked = true;

		} else {

			document.getElementsByName("strColumnEntidad")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnEvento")[0].checked) {

			document.getElementsByName("strColumnEvento")[0].checked = true;

		} else {

			document.getElementsByName("strColumnEvento")[0].checked = false;

		}
		;

	} else {

		document.getElementsByName("estado")[0].disabled = true;

	}
	;

	// strOptionHidden3
	// if (document.getElementsByName("strOptionHidden3")[0].checked
	// || (document.getElementsByName("strOptionHidden1")[0].checked || document
	// .getElementsByName("strOptionHidden2")[0].checked)) {
	//
	// document.getElementById("reportButton").disabled = false;
	// document.getElementById("reportButton").style.color = "#ffffff";
	//
	// } else {
	//
	// document.getElementById("reportButton").disabled = true;
	// document.getElementById("reportButton").style.color = "#808080";
	// }
	// ;

	if (document.getElementsByName("strColumnFolio")[0].checked
			|| document.getElementsByName("strColumnFecha")[0].checked
			|| document.getElementsByName("strColumnTID")[0].checked
			|| document.getElementsByName("strColumnObservaciones")[0].checked
			|| document.getElementsByName("strColumnEstadoActual")[0].checked
			|| document.getElementsByName("strColumnTipo")[0].checked
			|| document.getElementsByName("strColumnResponsable")[0].checked
			|| document.getElementsByName("strColumnChasis")[0].checked
			|| document.getElementsByName("strColumnEntidad")[0].checked
			|| document.getElementsByName("strColumnUltimo")[0].checked
			|| document.getElementsByName("strColumnEvento")[0].checked) {

		document.getElementById("reportButton").disabled = false;
		document.getElementById("reportButton").style.color = "#ffffff";

	} else {

		document.getElementById("reportButton").disabled = true;
		document.getElementById("reportButton").style.color = "#808080";

	}
	;

	// strOptionHidden3
	if (document.getElementsByName("strOptionHidden3")[0].checked) {

		document.getElementsByName("folioInicial")[0].disabled = false;
		document.getElementsByName("folioFinal")[0].disabled = false;
		document.getElementById("finalMessage").innerHTML += " - Folio";
		document.getElementsByName("strColumnFecha")[0].disabled = false;
		document.getElementsByName("strColumnFolio")[0].disabled = false;
		document.getElementsByName("strColumnTID")[0].disabled = false;
		document.getElementsByName("strColumnObservaciones")[0].disabled = false;
		document.getElementsByName("strColumnEstadoActual")[0].disabled = false;
		document.getElementsByName("strColumnTipo")[0].disabled = false;
		document.getElementsByName("strColumnResponsable")[0].disabled = false;
		document.getElementsByName("strColumnChasis")[0].disabled = false;
		document.getElementsByName("strColumnEntidad")[0].disabled = false;
		document.getElementsByName("strColumnEvento")[0].disabled = false;

		document.getElementById("divFolio").style.visibility = "visible";
		document.getElementById("divFecha").style.visibility = "visible";
		document.getElementById("divTID").style.visibility = "visible";
		document.getElementById("divObservaciones").style.visibility = "visible";
		document.getElementById("divEstadoActual").style.visibility = "visible";
		document.getElementById("divTipo").style.visibility = "visible";
		document.getElementById("divEntidad").style.visibility = "visible";
		document.getElementById("divChasis").style.visibility = "visible";

		if (document.getElementsByName("strColumnFolio")[0].checked) {

			document.getElementsByName("strColumnFolio")[0].checked = true;

		} else {

			document.getElementsByName("strColumnFolio")[0].checked = false;
		}
		;

		if (document.getElementsByName("strColumnFecha")[0].checked) {

			document.getElementsByName("strColumnFecha")[0].checked = true;

		} else {

			document.getElementsByName("strColumnFecha")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnTID")[0].checked) {

			document.getElementsByName("strColumnTID")[0].checked = true;

		} else {

			document.getElementsByName("strColumnTID")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnObservaciones")[0].checked) {

			document.getElementsByName("strColumnObservaciones")[0].checked = true;

		} else {

			document.getElementsByName("strColumnObservaciones")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnEstadoActual")[0].checked) {

			document.getElementsByName("strColumnEstadoActual")[0].checked = true;

		} else {

			document.getElementsByName("strColumnEstadoActual")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnTipo")[0].checked) {

			document.getElementsByName("strColumnTipo")[0].checked = true;
		} else {

			document.getElementsByName("strColumnTipo")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnResponsable")[0].checked) {

			document.getElementsByName("strColumnResponsable")[0].checked = true;

		} else {

			document.getElementsByName("strColumnResponsable")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnChasis")[0].checked) {

			document.getElementsByName("strColumnChasis")[0].checked = true;

		} else {

			document.getElementsByName("strColumnChasis")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnEntidad")[0].checked) {

			document.getElementsByName("strColumnEntidad")[0].checked = true;

		} else {

			document.getElementsByName("strColumnEntidad")[0].checked = false;

		}
		;

		if (document.getElementsByName("strColumnEvento")[0].checked) {

			document.getElementsByName("strColumnEvento")[0].checked = true;

		} else {

			document.getElementsByName("strColumnEvento")[0].checked = false;

		}
		;

	} else {

		document.getElementsByName("folioInicial")[0].disabled = true;
		document.getElementsByName("folioFinal")[0].disabled = true;

	}
	;

	// strOptionHidden4
	if (document.getElementsByName("strOptionHidden4")[0].checked) {

		document.getElementsByName("estadoImportados")[0].disabled = false;
		document.getElementById("finalMessage").innerHTML += " - Estado de importados";

	} else {

		document.getElementsByName("estadoImportados")[0].disabled = true;
	}
	;

	// strOptionHidden5
	if (document.getElementsByName("strOptionHidden5")[0].checked) {

		document.getElementsByName("strResponsable")[0].disabled = false;
		document.getElementsByName("strOptionHidden5")[0].value = 1;
		document.getElementById("finalMessage").innerHTML += " - Responsable";
		document.getElementsByName("strColumnResponsable")[0].disabled = false;
		document.getElementsByName("strColumnEvento")[0].disabled = false;
		document.getElementsByName("strColumnUltimo")[0].disabled = false;
		document.getElementById("divResponsable").style.visibility = "visible";
		document.getElementById("divEvento").style.visibility = "visible";
		document.getElementById("divEventoUltimo").style.visibility = "visible";

	} else {

		document.getElementsByName("strResponsable")[0].disabled = true;
		document.getElementsByName("strOptionHidden5")[0].value = 0;
		document.getElementsByName("strColumnResponsable")[0].disabled = true;
		document.getElementsByName("strColumnEvento")[0].disabled = true;
		document.getElementsByName("strColumnUltimo")[0].disabled = true;
		document.getElementById("divResponsable").style.visibility = "hidden";
		document.getElementById("divEvento").style.visibility = "hidden";
		document.getElementById("divEventoUltimo").style.visibility = "hidden";

	}
	;

	// strOptionHidden6
	if (document.getElementsByName("strOptionHidden6")[0].checked) {

		document.getElementsByName("strEntidad")[0].disabled = false;

	} else {

		document.getElementsByName("strEntidad")[0].disabled = true;

	}
	;

	if (document.getElementById("finalMessage").innerHTML != "") {
		document.getElementById("mensajeFiltros").style.visibility = "visible";

	} else {
		document.getElementById("mensajeFiltros").style.visibility = "hidden";

	}

	document.getElementsByName("strOptionHidden1")[0].onclick = optionHidden1Check;
	document.getElementsByName("strOptionHidden2")[0].onclick = optionHidden2Check;
	document.getElementsByName("strOptionHidden3")[0].onclick = optionHidden3Check;
	document.getElementsByName("strOptionHidden4")[0].onclick = optionHidden4Check;
	document.getElementsByName("strOptionHidden5")[0].onclick = optionHidden5Check;
	document.getElementsByName("strOptionHidden6")[0].onclick = optionHidden6Check;
	document.getElementsByName("strColumnFolio")[0].onclick = optionFolioCheck;
	document.getElementsByName("strColumnFecha")[0].onclick = optionFechaCheck;
	document.getElementsByName("strColumnTID")[0].onclick = optionTIDCheck;
	document.getElementsByName("strColumnObservaciones")[0].onclick = optionObsCheck;
	document.getElementsByName("strColumnEstadoActual")[0].onclick = optionEstadoActCheck;
	document.getElementsByName("strColumnTipo")[0].onclick = optionTipoCheck;
	document.getElementsByName("strColumnChasis")[0].onclick = optionChasisCheck;
	document.getElementsByName("strColumnEntidad")[0].onclick = optionEntidadCheck;
	document.getElementsByName("strColumnResponsable")[0].onclick = optionRespCheck;
	document.getElementsByName("strColumnEvento")[0].onclick = optionEventoCheck;
	document.getElementsByName("strColumnUltimo")[0].onclick = optionUltimoEventoCheck;
	document.getElementsByName("fechaFinal")[0].onfocus = fechaFinalAuto;
	document.getElementsByName("fechaInicial")[0].onfocus = changeFlagDate;
	document.getElementById("reiniciaColumnas").onclick = reiniciaColumnas;
	// document.getElementById("reportButton").onclick = actionButton;

};

function optionHidden1Check() {

	// strOptionHidden1
	if (document.getElementsByName("strOptionHidden1")[0].checked
			|| (document.getElementsByName("strOptionHidden2")[0].checked || document
					.getElementsByName("strOptionHidden3")[0].checked)) {

		// document.getElementById("reportButton").disabled = false;
		// document.getElementById("reportButton").style.color = "#ffffff";

		document.getElementsByName("strColumnFolio")[0].disabled = false;
		document.getElementById("divFolio").style.visibility = "visible";
		document.getElementById("divFecha").style.visibility = "visible";
		document.getElementById("divTID").style.visibility = "visible";
		document.getElementById("divObservaciones").style.visibility = "visible";
		document.getElementById("divEstadoActual").style.visibility = "visible";
		document.getElementById("divTipo").style.visibility = "visible";
		document.getElementById("divEntidad").style.visibility = "visible";
		document.getElementById("divChasis").style.visibility = "visible";
		document.getElementsByName("strColumnFecha")[0].disabled = false;
		document.getElementsByName("strColumnTID")[0].disabled = false;
		document.getElementsByName("strColumnObservaciones")[0].disabled = false;
		document.getElementsByName("strColumnEstadoActual")[0].disabled = false;
		document.getElementsByName("strColumnTipo")[0].disabled = false;
		document.getElementsByName("strColumnChasis")[0].disabled = false;
		document.getElementsByName("strColumnEntidad")[0].disabled = false;
		document.getElementsByName("strColumnResponsable")[0].disabled = false;
		document.getElementsByName("strColumnEvento")[0].disabled = false;
		document.getElementsByName("strColumnUltimo")[0].disabled = false;

	} else {

		document.getElementById("reportButton").disabled = true;
		document.getElementById("reportButton").style.color = "#808080";
		document.getElementById("divFolio").style.visibility = "hidden";
		document.getElementById("divFecha").style.visibility = "hidden";
		document.getElementById("divTID").style.visibility = "hidden";
		document.getElementById("divObservaciones").style.visibility = "hidden";
		document.getElementById("divEstadoActual").style.visibility = "hidden";
		document.getElementById("divTipo").style.visibility = "hidden";
		document.getElementById("divEntidad").style.visibility = "hidden";
		document.getElementById("divChasis").style.visibility = "hidden";
		document.getElementsByName("strColumnFolio")[0].disabled = true;
		document.getElementsByName("strColumnFecha")[0].disabled = true;
		document.getElementsByName("strColumnTID")[0].disabled = true;
		document.getElementsByName("strColumnObservaciones")[0].disabled = true;
		document.getElementsByName("strColumnEstadoActual")[0].disabled = true;
		document.getElementsByName("strColumnTipo")[0].disabled = true;
		document.getElementsByName("strColumnChasis")[0].disabled = true;
		document.getElementsByName("strColumnEntidad")[0].disabled = true;
		document.getElementsByName("strColumnResponsable")[0].disabled = true;
		document.getElementsByName("strColumnEvento")[0].disabled = true;
		document.getElementsByName("strColumnUltimo")[0].disabled = true;

		document.getElementsByName("strColumnFolio")[0].checked = false;
		document.getElementsByName("strColumnFecha")[0].checked = false;
		document.getElementsByName("strColumnTID")[0].checked = false;
		document.getElementsByName("strColumnObservaciones")[0].checked = false;
		document.getElementsByName("strColumnEstadoActual")[0].checked = false;
		document.getElementsByName("strColumnTipo")[0].checked = false;
		document.getElementsByName("strColumnChasis")[0].checked = false;
		document.getElementsByName("strColumnEntidad")[0].checked = false;
		document.getElementsByName("strColumnResponsable")[0].checked = false;
		document.getElementsByName("strColumnEvento")[0].checked = false;
		document.getElementsByName("strColumnUltimo")[0].checked = false;

		document.getElementById("ordenColumnas").innerHTML = "";
		document.getElementsByName("strOrdenColumnas")[0].value = "";

	}
	;

	// strOptionHidden1
	if (document.getElementsByName("strOptionHidden1")[0].checked) {

		document.getElementsByName("fechaInicial")[0].disabled = false;
		document.getElementsByName("fechaFinal")[0].disabled = false;

	} else {

		document.getElementsByName("fechaInicial")[0].disabled = true;
		document.getElementsByName("fechaFinal")[0].disabled = true;
	}
	;

};

function optionHidden2Check() {

	// strOptionHidden2
	if (document.getElementsByName("strOptionHidden2")[0].checked
			|| (document.getElementsByName("strOptionHidden1")[0].checked || document
					.getElementsByName("strOptionHidden3")[0].checked)) {

		// document.getElementById("reportButton").disabled = false;
		// document.getElementById("reportButton").style.color = "#ffffff";

		document.getElementsByName("strColumnFolio")[0].disabled = false;
		document.getElementById("divFolio").style.visibility = "visible";
		document.getElementById("divFecha").style.visibility = "visible";
		document.getElementById("divTID").style.visibility = "visible";
		document.getElementById("divObservaciones").style.visibility = "visible";
		document.getElementById("divEstadoActual").style.visibility = "visible";
		document.getElementById("divTipo").style.visibility = "visible";
		document.getElementById("divEntidad").style.visibility = "visible";
		document.getElementById("divChasis").style.visibility = "visible";
		document.getElementsByName("strColumnFecha")[0].disabled = false;
		document.getElementsByName("strColumnTID")[0].disabled = false;
		document.getElementsByName("strColumnObservaciones")[0].disabled = false;
		document.getElementsByName("strColumnEstadoActual")[0].disabled = false;
		document.getElementsByName("strColumnTipo")[0].disabled = false;
		document.getElementsByName("strColumnChasis")[0].disabled = false;
		document.getElementsByName("strColumnEntidad")[0].disabled = false;
		document.getElementsByName("strColumnResponsable")[0].disabled = false;
		document.getElementsByName("strColumnEvento")[0].disabled = false;
		document.getElementsByName("strColumnUltimo")[0].disabled = false;

	} else {

		document.getElementById("reportButton").disabled = true;
		document.getElementById("reportButton").style.color = "#808080";

		document.getElementsByName("strColumnFolio")[0].disabled = true;
		document.getElementById("divFolio").style.visibility = "hidden";
		document.getElementById("divFecha").style.visibility = "hidden";
		document.getElementById("divTID").style.visibility = "hidden";
		document.getElementById("divObservaciones").style.visibility = "hidden";
		document.getElementById("divEstadoActual").style.visibility = "hidden";
		document.getElementById("divTipo").style.visibility = "hidden";
		document.getElementById("divEntidad").style.visibility = "hidden";
		document.getElementById("divChasis").style.visibility = "hidden";
		document.getElementsByName("strColumnFecha")[0].disabled = true;
		document.getElementsByName("strColumnTID")[0].disabled = true;
		document.getElementsByName("strColumnObservaciones")[0].disabled = true;
		document.getElementsByName("strColumnEstadoActual")[0].disabled = true;
		document.getElementsByName("strColumnTipo")[0].disabled = true;
		document.getElementsByName("strColumnChasis")[0].disabled = true;
		document.getElementsByName("strColumnEntidad")[0].disabled = true;
		document.getElementsByName("strColumnResponsable")[0].disabled = true;
		document.getElementsByName("strColumnEvento")[0].disabled = true;
		document.getElementsByName("strColumnUltimo")[0].disabled = true;

		document.getElementsByName("strColumnFolio")[0].checked = false;
		document.getElementsByName("strColumnFecha")[0].checked = false;
		document.getElementsByName("strColumnTID")[0].checked = false;
		document.getElementsByName("strColumnObservaciones")[0].checked = false;
		document.getElementsByName("strColumnEstadoActual")[0].checked = false;
		document.getElementsByName("strColumnTipo")[0].checked = false;
		document.getElementsByName("strColumnChasis")[0].checked = false;
		document.getElementsByName("strColumnEntidad")[0].checked = false;
		document.getElementsByName("strColumnResponsable")[0].checked = false;
		document.getElementsByName("strColumnEvento")[0].checked = false;
		document.getElementsByName("strColumnUltimo")[0].checked = false;
		document.getElementById("ordenColumnas").innerHTML = "";
		document.getElementsByName("strOrdenColumnas")[0].value = "";

	}
	;

	// strOptionHidden2
	if (document.getElementsByName("strOptionHidden2")[0].checked) {

		document.getElementsByName("estado")[0].disabled = false;

	} else {

		document.getElementsByName("estado")[0].disabled = true;
	}
	;

};

function optionHidden3Check() {

	// strOptionHidden3
	if (document.getElementsByName("strOptionHidden3")[0].checked
			|| (document.getElementsByName("strOptionHidden1")[0].checked || document
					.getElementsByName("strOptionHidden2")[0].checked)) {

		// document.getElementById("reportButton").disabled = false;
		// document.getElementById("reportButton").style.color = "#ffffff";

		document.getElementsByName("strColumnFolio")[0].disabled = false;
		document.getElementsByName("strColumnFecha")[0].disabled = false;
		document.getElementsByName("strColumnTID")[0].disabled = false;
		document.getElementsByName("strColumnObservaciones")[0].disabled = false;
		document.getElementsByName("strColumnEstadoActual")[0].disabled = false;
		document.getElementsByName("strColumnTipo")[0].disabled = false;
		document.getElementsByName("strColumnChasis")[0].disabled = false;
		document.getElementsByName("strColumnEntidad")[0].disabled = false;
		document.getElementsByName("strColumnResponsable")[0].disabled = false;
		document.getElementsByName("strColumnEvento")[0].disabled = false;
		document.getElementsByName("strColumnUltimo")[0].disabled = false;

		document.getElementById("divFolio").style.visibility = "visible";
		document.getElementById("divFecha").style.visibility = "visible";
		document.getElementById("divTID").style.visibility = "visible";
		document.getElementById("divObservaciones").style.visibility = "visible";
		document.getElementById("divEstadoActual").style.visibility = "visible";
		document.getElementById("divTipo").style.visibility = "visible";
		document.getElementById("divEntidad").style.visibility = "visible";
		document.getElementById("divChasis").style.visibility = "visible";

	} else {

		document.getElementById("reportButton").disabled = true;
		document.getElementById("reportButton").style.color = "#808080";

		document.getElementsByName("strColumnFolio")[0].disabled = true;

		document.getElementById("divFolio").style.visibility = "hidden";
		document.getElementById("divFecha").style.visibility = "hidden";
		document.getElementById("divTID").style.visibility = "hidden";
		document.getElementById("divObservaciones").style.visibility = "hidden";
		document.getElementById("divEstadoActual").style.visibility = "hidden";
		document.getElementById("divTipo").style.visibility = "hidden";
		document.getElementById("divEntidad").style.visibility = "hidden";
		document.getElementById("divChasis").style.visibility = "hidden";
		document.getElementsByName("strColumnFecha")[0].disabled = true;
		document.getElementsByName("strColumnTID")[0].disabled = true;
		document.getElementsByName("strColumnObservaciones")[0].disabled = true;
		document.getElementsByName("strColumnEstadoActual")[0].disabled = true;
		document.getElementsByName("strColumnTipo")[0].disabled = true;
		document.getElementsByName("strColumnChasis")[0].disabled = true;
		document.getElementsByName("strColumnEntidad")[0].disabled = true;
		document.getElementsByName("strColumnResponsable")[0].disabled = true;
		document.getElementsByName("strColumnEvento")[0].disabled = true;
		document.getElementsByName("strColumnUltimo")[0].disabled = true;

		document.getElementsByName("strColumnFolio")[0].checked = false;
		document.getElementsByName("strColumnFecha")[0].checked = false;
		document.getElementsByName("strColumnTID")[0].checked = false;
		document.getElementsByName("strColumnObservaciones")[0].checked = false;
		document.getElementsByName("strColumnEstadoActual")[0].checked = false;
		document.getElementsByName("strColumnTipo")[0].checked = false;
		document.getElementsByName("strColumnChasis")[0].checked = false;
		document.getElementsByName("strColumnEntidad")[0].checked = false;
		document.getElementsByName("strColumnResponsable")[0].checked = false;
		document.getElementsByName("strColumnEvento")[0].checked = false;
		document.getElementsByName("strColumnUltimo")[0].checked = false;
		document.getElementById("ordenColumnas").innerHTML = "";
		document.getElementsByName("strOrdenColumnas")[0].value = "";

	}
	;

	// strOptionHidden3
	if (document.getElementsByName("strOptionHidden3")[0].checked) {

		document.getElementsByName("folioInicial")[0].disabled = false;
		document.getElementsByName("folioFinal")[0].disabled = false;

	} else {

		document.getElementsByName("folioInicial")[0].disabled = true;
		document.getElementsByName("folioFinal")[0].disabled = true;

	}
	;

};

function optionHidden4Check() {

	if (document.getElementsByName("strOptionHidden4")[0].checked) {

		document.getElementsByName("estadoImportados")[0].disabled = false;

	} else {

		document.getElementsByName("estadoImportados")[0].disabled = true;
	}
	;

};

function optionHidden5Check() {

	if (document.getElementsByName("strOptionHidden5")[0].checked) {

		document.getElementsByName("strResponsable")[0].disabled = false;
		document.getElementsByName("strOptionHidden5")[0].value = 1;
		document.getElementsByName("strColumnResponsable")[0].disabled = false;
		document.getElementsByName("strColumnEvento")[0].disabled = false;
		document.getElementsByName("strColumnUltimo")[0].disabled = false;
		document.getElementById("divResponsable").style.visibility = "visible";
		document.getElementById("divEvento").style.visibility = "visible";
		document.getElementById("divEventoUltimo").style.visibility = "visible";

	} else {

		document.getElementsByName("strResponsable")[0].disabled = true;
		document.getElementsByName("strOptionHidden5")[0].value = 0;
		document.getElementsByName("strColumnResponsable")[0].disabled = true;
		document.getElementsByName("strColumnResponsable")[0].checked = false;
		document.getElementsByName("strColumnEvento")[0].disabled = true;
		document.getElementsByName("strColumnEvento")[0].checked = false;
		document.getElementsByName("strColumnUltimo")[0].disabled = true;
		document.getElementsByName("strColumnUltimo")[0].checked = false;
		document.getElementById("divResponsable").style.visibility = "hidden";
		document.getElementById("divEvento").style.visibility = "hidden";
		document.getElementById("divEventoUltimo").style.visibility = "hidden";

		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;

		ordenColumnas = ordenColumnas.replace("|Responsable", "");
		ordenColumnas = ordenColumnas.replace("|Evento", "");

		document.getElementById("ordenColumnas").innerHTML = ordenColumnas;
		document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas;

	}
	;

};

function optionHidden6Check() {

	if (document.getElementsByName("strOptionHidden6")[0].checked) {

		document.getElementsByName("strEntidad")[0].disabled = false;

	} else {

		document.getElementsByName("strEntidad")[0].disabled = true;

	}
	;

};

function optionFolioCheck() {

	if (document.getElementsByName("strColumnFolio")[0].checked
			|| document.getElementsByName("strColumnFecha")[0].checked
			|| document.getElementsByName("strColumnTID")[0].checked
			|| document.getElementsByName("strColumnObservaciones")[0].checked
			|| document.getElementsByName("strColumnEstadoActual")[0].checked
			|| document.getElementsByName("strColumnTipo")[0].checked
			|| document.getElementsByName("strColumnResponsable")[0].checked
			|| document.getElementsByName("strColumnChasis")[0].checked
			|| document.getElementsByName("strColumnEntidad")[0].checked
			|| document.getElementsByName("strColumnUltimo")[0].checked
			|| document.getElementsByName("strColumnEvento")[0].checked) {

		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;

		if (ordenColumnas.indexOf("|Folio") == -1) {
			document.getElementById("ordenColumnas").innerHTML = ordenColumnas
					+ "|Folio";
			document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas
					+ "|Folio";
		}

		if (document.getElementsByName("strOptionHidden1")[0].checked
				|| document.getElementsByName("strOptionHidden2")[0].checked
				|| document.getElementsByName("strOptionHidden3")[0].checked) {

			document.getElementById("reportButton").disabled = false;
			document.getElementById("reportButton").style.color = "#ffffff";

		}

	} else {

		document.getElementById("reportButton").disabled = true;
		document.getElementById("reportButton").style.color = "#808080";

	}
	;

	if (!document.getElementsByName("strColumnFolio")[0].checked) {
		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;
		ordenColumnas = ordenColumnas.replace("|Folio", "");
		document.getElementById("ordenColumnas").innerHTML = ordenColumnas;
		document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas;
	}

};

function optionFechaCheck() {

	if (document.getElementsByName("strColumnFolio")[0].checked
			|| document.getElementsByName("strColumnFecha")[0].checked
			|| document.getElementsByName("strColumnTID")[0].checked
			|| document.getElementsByName("strColumnObservaciones")[0].checked
			|| document.getElementsByName("strColumnEstadoActual")[0].checked
			|| document.getElementsByName("strColumnTipo")[0].checked
			|| document.getElementsByName("strColumnResponsable")[0].checked
			|| document.getElementsByName("strColumnChasis")[0].checked
			|| document.getElementsByName("strColumnEntidad")[0].checked
			|| document.getElementsByName("strColumnEvento")[0].checked
			|| document.getElementsByName("strColumnUltimo")[0].checked
			&& (document.getElementsByName("strOptionHidden1")[0].checked
					|| document.getElementsByName("strOptionHidden2")[0].checked || document
					.getElementsByName("strOptionHidden3")[0].checked)) {

		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;

		if (ordenColumnas.indexOf("|Fecha") == -1) {
			document.getElementById("ordenColumnas").innerHTML = ordenColumnas
					+ "|Fecha";
			document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas
					+ "|Fecha";
		}

		if (document.getElementsByName("strOptionHidden1")[0].checked
				|| document.getElementsByName("strOptionHidden2")[0].checked
				|| document.getElementsByName("strOptionHidden3")[0].checked) {

			document.getElementById("reportButton").disabled = false;
			document.getElementById("reportButton").style.color = "#ffffff";

		}

	} else {

		document.getElementById("reportButton").disabled = true;
		document.getElementById("reportButton").style.color = "#808080";

	}
	;

	if (!document.getElementsByName("strColumnFecha")[0].checked) {
		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;
		ordenColumnas = ordenColumnas.replace("|Fecha", "");
		document.getElementById("ordenColumnas").innerHTML = ordenColumnas;
		document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas;
	}

};

function optionTIDCheck() {

	if (document.getElementsByName("strColumnFolio")[0].checked
			|| document.getElementsByName("strColumnFecha")[0].checked
			|| document.getElementsByName("strColumnTID")[0].checked
			|| document.getElementsByName("strColumnObservaciones")[0].checked
			|| document.getElementsByName("strColumnEstadoActual")[0].checked
			|| document.getElementsByName("strColumnTipo")[0].checked
			|| document.getElementsByName("strColumnResponsable")[0].checked
			|| document.getElementsByName("strColumnChasis")[0].checked
			|| document.getElementsByName("strColumnEntidad")[0].checked
			|| document.getElementsByName("strColumnEvento")[0].checked
			|| document.getElementsByName("strColumnUltimo")[0].checked
			&& (document.getElementsByName("strOptionHidden1")[0].checked
					|| document.getElementsByName("strOptionHidden2")[0].checked || document
					.getElementsByName("strOptionHidden3")[0].checked)) {

		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;

		if (ordenColumnas.indexOf("|TID") == -1) {
			document.getElementById("ordenColumnas").innerHTML = ordenColumnas
					+ "|TID";
			document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas
					+ "|TID";
		}

		if (document.getElementsByName("strOptionHidden1")[0].checked
				|| document.getElementsByName("strOptionHidden2")[0].checked
				|| document.getElementsByName("strOptionHidden3")[0].checked) {

			document.getElementById("reportButton").disabled = false;
			document.getElementById("reportButton").style.color = "#ffffff";

		}

	} else {

		document.getElementById("reportButton").disabled = true;
		document.getElementById("reportButton").style.color = "#808080";

	}
	;

	if (!document.getElementsByName("strColumnTID")[0].checked) {
		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;
		ordenColumnas = ordenColumnas.replace("|TID", "");
		document.getElementById("ordenColumnas").innerHTML = ordenColumnas;
		document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas;
	}

};

function optionObsCheck() {

	if (document.getElementsByName("strColumnFolio")[0].checked
			|| document.getElementsByName("strColumnFecha")[0].checked
			|| document.getElementsByName("strColumnTID")[0].checked
			|| document.getElementsByName("strColumnObservaciones")[0].checked
			|| document.getElementsByName("strColumnEstadoActual")[0].checked
			|| document.getElementsByName("strColumnTipo")[0].checked
			|| document.getElementsByName("strColumnResponsable")[0].checked
			|| document.getElementsByName("strColumnChasis")[0].checked
			|| document.getElementsByName("strColumnEntidad")[0].checked
			|| document.getElementsByName("strColumnEvento")[0].checked
			|| document.getElementsByName("strColumnUltimo")[0].checked
			&& (document.getElementsByName("strOptionHidden1")[0].checked
					|| document.getElementsByName("strOptionHidden2")[0].checked || document
					.getElementsByName("strOptionHidden3")[0].checked)) {

		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;

		if (ordenColumnas.indexOf("|Observaciones") == -1) {
			document.getElementById("ordenColumnas").innerHTML = ordenColumnas
					+ "|Observaciones";
			document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas
					+ "|Observaciones";
		}

		if (document.getElementsByName("strOptionHidden1")[0].checked
				|| document.getElementsByName("strOptionHidden2")[0].checked
				|| document.getElementsByName("strOptionHidden3")[0].checked) {

			document.getElementById("reportButton").disabled = false;
			document.getElementById("reportButton").style.color = "#ffffff";

		}

	} else {

		document.getElementById("reportButton").disabled = true;
		document.getElementById("reportButton").style.color = "#808080";

	}
	;

	if (!document.getElementsByName("strColumnObservaciones")[0].checked) {
		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;
		ordenColumnas = ordenColumnas.replace("|Observaciones", "");
		document.getElementById("ordenColumnas").innerHTML = ordenColumnas;
		document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas;
	}

};

function optionEstadoActCheck() {

	if (document.getElementsByName("strColumnFolio")[0].checked
			|| document.getElementsByName("strColumnFecha")[0].checked
			|| document.getElementsByName("strColumnTID")[0].checked
			|| document.getElementsByName("strColumnObservaciones")[0].checked
			|| document.getElementsByName("strColumnEstadoActual")[0].checked
			|| document.getElementsByName("strColumnTipo")[0].checked
			|| document.getElementsByName("strColumnResponsable")[0].checked
			|| document.getElementsByName("strColumnChasis")[0].checked
			|| document.getElementsByName("strColumnEntidad")[0].checked
			|| document.getElementsByName("strColumnEvento")[0].checked
			|| document.getElementsByName("strColumnUltimo")[0].checked
			&& (document.getElementsByName("strOptionHidden1")[0].checked
					|| document.getElementsByName("strOptionHidden2")[0].checked || document
					.getElementsByName("strOptionHidden3")[0].checked)) {

		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;

		if (ordenColumnas.indexOf("|Estado Actual") == -1) {
			document.getElementById("ordenColumnas").innerHTML = ordenColumnas
					+ "|Estado Actual";
			document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas
					+ "|Estado Actual";
		}

		if (document.getElementsByName("strOptionHidden1")[0].checked
				|| document.getElementsByName("strOptionHidden2")[0].checked
				|| document.getElementsByName("strOptionHidden3")[0].checked) {

			document.getElementById("reportButton").disabled = false;
			document.getElementById("reportButton").style.color = "#ffffff";

		}

	} else {

		document.getElementById("reportButton").disabled = true;
		document.getElementById("reportButton").style.color = "#808080";

	}
	;

	if (!document.getElementsByName("strColumnEstadoActual")[0].checked) {
		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;
		ordenColumnas = ordenColumnas.replace("|Estado Actual", "");
		document.getElementById("ordenColumnas").innerHTML = ordenColumnas;
		document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas;
	}

};

function optionTipoCheck() {

	if (document.getElementsByName("strColumnFolio")[0].checked
			|| document.getElementsByName("strColumnFecha")[0].checked
			|| document.getElementsByName("strColumnTID")[0].checked
			|| document.getElementsByName("strColumnObservaciones")[0].checked
			|| document.getElementsByName("strColumnEstadoActual")[0].checked
			|| document.getElementsByName("strColumnTipo")[0].checked
			|| document.getElementsByName("strColumnResponsable")[0].checked
			|| document.getElementsByName("strColumnChasis")[0].checked
			|| document.getElementsByName("strColumnEntidad")[0].checked
			|| document.getElementsByName("strColumnEvento")[0].checked
			|| document.getElementsByName("strColumnUltimo")[0].checked
			&& (document.getElementsByName("strOptionHidden1")[0].checked
					|| document.getElementsByName("strOptionHidden2")[0].checked || document
					.getElementsByName("strOptionHidden3")[0].checked)) {

		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;

		if (ordenColumnas.indexOf("|Tipo") == -1) {
			document.getElementById("ordenColumnas").innerHTML = ordenColumnas
					+ "|Tipo";
			document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas
					+ "|Tipo";
		}

		if (document.getElementsByName("strOptionHidden1")[0].checked
				|| document.getElementsByName("strOptionHidden2")[0].checked
				|| document.getElementsByName("strOptionHidden3")[0].checked) {

			document.getElementById("reportButton").disabled = false;
			document.getElementById("reportButton").style.color = "#ffffff";

		}

	} else {

		document.getElementById("reportButton").disabled = true;
		document.getElementById("reportButton").style.color = "#808080";

	}
	;

	if (!document.getElementsByName("strColumnTipo")[0].checked) {
		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;
		ordenColumnas = ordenColumnas.replace("|Tipo", "");
		document.getElementById("ordenColumnas").innerHTML = ordenColumnas;
		document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas;
	}

};

function optionRespCheck() {

	if (document.getElementsByName("strColumnFolio")[0].checked
			|| document.getElementsByName("strColumnFecha")[0].checked
			|| document.getElementsByName("strColumnTID")[0].checked
			|| document.getElementsByName("strColumnObservaciones")[0].checked
			|| document.getElementsByName("strColumnEstadoActual")[0].checked
			|| document.getElementsByName("strColumnTipo")[0].checked
			|| document.getElementsByName("strColumnResponsable")[0].checked
			|| document.getElementsByName("strColumnChasis")[0].checked
			|| document.getElementsByName("strColumnEntidad")[0].checked
			|| document.getElementsByName("strColumnEvento")[0].checked
			|| document.getElementsByName("strColumnUltimo")[0].checked
			&& (document.getElementsByName("strOptionHidden1")[0].checked
					|| document.getElementsByName("strOptionHidden2")[0].checked || document
					.getElementsByName("strOptionHidden3")[0].checked)) {

		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;

		if (ordenColumnas.indexOf("|Responsable") == -1) {
			document.getElementById("ordenColumnas").innerHTML = ordenColumnas
					+ "|Responsable";
			document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas
					+ "|Responsable";
		}

		if (document.getElementsByName("strOptionHidden1")[0].checked
				|| document.getElementsByName("strOptionHidden2")[0].checked
				|| document.getElementsByName("strOptionHidden3")[0].checked) {

			document.getElementById("reportButton").disabled = false;
			document.getElementById("reportButton").style.color = "#ffffff";

		}

	} else {

		document.getElementById("reportButton").disabled = true;
		document.getElementById("reportButton").style.color = "#808080";

	}
	;

	if (!document.getElementsByName("strColumnResponsable")[0].checked) {
		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;
		ordenColumnas = ordenColumnas.replace("|Responsable", "");
		document.getElementById("ordenColumnas").innerHTML = ordenColumnas;
		document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas;
	}

};

function optionChasisCheck() {

	if (document.getElementsByName("strColumnFolio")[0].checked
			|| document.getElementsByName("strColumnFecha")[0].checked
			|| document.getElementsByName("strColumnTID")[0].checked
			|| document.getElementsByName("strColumnObservaciones")[0].checked
			|| document.getElementsByName("strColumnEstadoActual")[0].checked
			|| document.getElementsByName("strColumnTipo")[0].checked
			|| document.getElementsByName("strColumnResponsable")[0].checked
			|| document.getElementsByName("strColumnChasis")[0].checked
			|| document.getElementsByName("strColumnEntidad")[0].checked
			|| document.getElementsByName("strColumnEvento")[0].checked
			|| document.getElementsByName("strColumnUltimo")[0].checked
			&& (document.getElementsByName("strOptionHidden1")[0].checked
					|| document.getElementsByName("strOptionHidden2")[0].checked || document
					.getElementsByName("strOptionHidden3")[0].checked)) {

		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;

		if (ordenColumnas.indexOf("|Chasis") == -1) {
			document.getElementById("ordenColumnas").innerHTML = ordenColumnas
					+ "|Chasis";
			document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas
					+ "|Chasis";
		}

		if (document.getElementsByName("strOptionHidden1")[0].checked
				|| document.getElementsByName("strOptionHidden2")[0].checked
				|| document.getElementsByName("strOptionHidden3")[0].checked) {

			document.getElementById("reportButton").disabled = false;
			document.getElementById("reportButton").style.color = "#ffffff";

		}

	} else {

		document.getElementById("reportButton").disabled = true;
		document.getElementById("reportButton").style.color = "#808080";

	}
	;

	if (!document.getElementsByName("strColumnChasis")[0].checked) {
		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;
		ordenColumnas = ordenColumnas.replace("|Chasis", "");
		document.getElementById("ordenColumnas").innerHTML = ordenColumnas;
		document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas;
	}

};

function optionEntidadCheck() {

	if (document.getElementsByName("strColumnFolio")[0].checked
			|| document.getElementsByName("strColumnFecha")[0].checked
			|| document.getElementsByName("strColumnTID")[0].checked
			|| document.getElementsByName("strColumnObservaciones")[0].checked
			|| document.getElementsByName("strColumnEstadoActual")[0].checked
			|| document.getElementsByName("strColumnTipo")[0].checked
			|| document.getElementsByName("strColumnResponsable")[0].checked
			|| document.getElementsByName("strColumnChasis")[0].checked
			|| document.getElementsByName("strColumnEntidad")[0].checked
			|| document.getElementsByName("strColumnEvento")[0].checked
			|| document.getElementsByName("strColumnUltimo")[0].checked
			&& (document.getElementsByName("strOptionHidden1")[0].checked
					|| document.getElementsByName("strOptionHidden2")[0].checked || document
					.getElementsByName("strOptionHidden3")[0].checked)) {

		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;

		if (ordenColumnas.indexOf("|Entidad") == -1) {
			document.getElementById("ordenColumnas").innerHTML = ordenColumnas
					+ "|Entidad";
			document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas
					+ "|Entidad";
		}

		if (document.getElementsByName("strOptionHidden1")[0].checked
				|| document.getElementsByName("strOptionHidden2")[0].checked
				|| document.getElementsByName("strOptionHidden3")[0].checked) {

			document.getElementById("reportButton").disabled = false;
			document.getElementById("reportButton").style.color = "#ffffff";

		}

	} else {

		document.getElementById("reportButton").disabled = true;
		document.getElementById("reportButton").style.color = "#808080";

	}
	;

	if (!document.getElementsByName("strColumnEntidad")[0].checked) {
		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;
		ordenColumnas = ordenColumnas.replace("|Entidad", "");
		document.getElementById("ordenColumnas").innerHTML = ordenColumnas;
		document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas;
	}

};

function optionEventoCheck() {

	if (document.getElementsByName("strColumnFolio")[0].checked
			|| document.getElementsByName("strColumnFecha")[0].checked
			|| document.getElementsByName("strColumnTID")[0].checked
			|| document.getElementsByName("strColumnObservaciones")[0].checked
			|| document.getElementsByName("strColumnEstadoActual")[0].checked
			|| document.getElementsByName("strColumnTipo")[0].checked
			|| document.getElementsByName("strColumnResponsable")[0].checked
			|| document.getElementsByName("strColumnChasis")[0].checked
			|| document.getElementsByName("strColumnEntidad")[0].checked
			|| document.getElementsByName("strColumnEvento")[0].checked
			|| document.getElementsByName("strColumnUltimo")[0].checked
			&& (document.getElementsByName("strOptionHidden1")[0].checked
					|| document.getElementsByName("strOptionHidden2")[0].checked || document
					.getElementsByName("strOptionHidden3")[0].checked)) {

		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;

		if (ordenColumnas.indexOf("|Evento") == -1) {
			document.getElementById("ordenColumnas").innerHTML = ordenColumnas
					+ "|Evento";
			document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas
					+ "|Evento";
		}

		if (document.getElementsByName("strOptionHidden1")[0].checked
				|| document.getElementsByName("strOptionHidden2")[0].checked
				|| document.getElementsByName("strOptionHidden3")[0].checked) {

			document.getElementById("reportButton").disabled = false;
			document.getElementById("reportButton").style.color = "#ffffff";

		}

	} else {

		document.getElementById("reportButton").disabled = true;
		document.getElementById("reportButton").style.color = "#808080";

	}
	;

	if (!document.getElementsByName("strColumnEvento")[0].checked) {
		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;
		ordenColumnas = ordenColumnas.replace("|Evento", "");
		document.getElementById("ordenColumnas").innerHTML = ordenColumnas;
		document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas;

		document.getElementsByName("strColumnUltimo")[0].disabled = false;
		document.getElementById("divEventoUltimo").style.visibility = "visible";

	}

	if (document.getElementsByName("strColumnEvento")[0].checked) {

		document.getElementsByName("strColumnUltimo")[0].disabled = true;
		document.getElementById("divEventoUltimo").style.visibility = "hidden";

	}

};

function optionUltimoEventoCheck() {

	if (document.getElementsByName("strColumnFolio")[0].checked
			|| document.getElementsByName("strColumnFecha")[0].checked
			|| document.getElementsByName("strColumnTID")[0].checked
			|| document.getElementsByName("strColumnObservaciones")[0].checked
			|| document.getElementsByName("strColumnEstadoActual")[0].checked
			|| document.getElementsByName("strColumnTipo")[0].checked
			|| document.getElementsByName("strColumnResponsable")[0].checked
			|| document.getElementsByName("strColumnChasis")[0].checked
			|| document.getElementsByName("strColumnEntidad")[0].checked
			|| document.getElementsByName("strColumnEvento")[0].checked
			|| document.getElementsByName("strColumnUltimo")[0].checked
			&& (document.getElementsByName("strOptionHidden1")[0].checked
					|| document.getElementsByName("strOptionHidden2")[0].checked || document
					.getElementsByName("strOptionHidden3")[0].checked)) {

		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;

		if (ordenColumnas.indexOf("|Evento") == -1) {
			document.getElementById("ordenColumnas").innerHTML = ordenColumnas
					+ "|Evento";
			document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas
					+ "|Evento";
		}

		if (document.getElementsByName("strOptionHidden1")[0].checked
				|| document.getElementsByName("strOptionHidden2")[0].checked
				|| document.getElementsByName("strOptionHidden3")[0].checked) {

			document.getElementById("reportButton").disabled = false;
			document.getElementById("reportButton").style.color = "#ffffff";

		}

	} else {

		document.getElementById("reportButton").disabled = true;
		document.getElementById("reportButton").style.color = "#808080";

	}
	;

	if (!document.getElementsByName("strColumnUltimo")[0].checked) {
		var ordenColumnas = document.getElementById("ordenColumnas").innerHTML;
		ordenColumnas = ordenColumnas.replace("|Evento", "");
		document.getElementById("ordenColumnas").innerHTML = ordenColumnas;
		document.getElementsByName("strOrdenColumnas")[0].value = ordenColumnas;

		document.getElementsByName("strColumnEvento")[0].disabled = false;
		document.getElementById("divEvento").style.visibility = "visible";

	}

	if (document.getElementsByName("strColumnUltimo")[0].checked) {

		document.getElementsByName("strColumnEvento")[0].disabled = true;
		document.getElementById("divEvento").style.visibility = "hidden";

	}

};

function actionButton() {

	var isFullReport = document.getElementsByName("isFullReport")[0].value;

	if (isFullReport = "0") {

		formatoFolioInicial();
		formatoFolioFinal();
		document.getElementById("waitMessage").style.visibility = "visible";
		document.getElementById("divImg").style.visibility = "visible";
		document.getElementById("divMensajesError").style.visibility = "hidden";
		document.getElementById("reportButton").disabled = true;
		document.getElementById("finalMessage").innerHTML = "";
		document.getElementById("mensajeFiltros").style.visibility = "visible";

		// strOptionHidden1
		if (document.getElementsByName("strOptionHidden1")[0].checked) {

			document.getElementById("finalMessage").innerHTML += " - Fecha";

		}
		;

		// strOptionHidden2
		if (document.getElementsByName("strOptionHidden2")[0].checked) {

			document.getElementById("finalMessage").innerHTML += " - Estado";

		}
		;

		// strOptionHidden3
		if (document.getElementsByName("strOptionHidden3")[0].checked) {

			document.getElementById("finalMessage").innerHTML += " - Folio";

		}
		;

		// strOptionHidden5
		if (document.getElementsByName("strOptionHidden4")[0].checked) {

			document.getElementById("finalMessage").innerHTML += " - Estado de importados";

		}
		;

		// strOptionHidden6
		if (document.getElementsByName("strOptionHidden5")[0].checked) {

			document.getElementById("finalMessage").innerHTML += " - Responsable";

		}
		;

		var opts = {
			lines : 13 // The number of lines to draw
			,
			length : 10 // The length of each line
			,
			width : 14 // The line thickness
			,
			radius : 25 // The radius of the inner circle
			,
			scale : .20 // Scales overall size of the spinner
			,
			corners : 1 // Corner roundness (0..1)
			,
			color : '#000' // #rgb or #rrggbb or array of colors
			,
			opacity : 0.18 // Opacity of the lines
			,
			rotate : 0 // The rotation offset
			,
			direction : 1 // 1: clockwise, -1: counterclockwise
			,
			speed : 1 // Rounds per second
			,
			trail : 60 // Afterglow percentage
			,
			fps : 20 // Frames per second when using setTimeout() as a
						// fallback
			// for CSS
			,
			zIndex : 2e9 // The z-index (defaults to 2000000000)
			,
			className : 'spinner' // The CSS class to assign to the spinner
			,
			top : '50%' // Top position relative to parent
			,
			left : '3%' // Left position relative to parent
			,
			shadow : false // Whether to render a shadow
			,
			hwaccel : false // Whether to use hardware acceleration
			,
			position : 'relative' // Element positioning
		};

		var targetDiv = document.getElementById("divImg");
		var divSpinner = document.createElement('div');
		divSpinner.id = 'divSpinner';
		targetDiv.appendChild(divSpinner);

		var spinner = new Spinner(opts).spin(divSpinner);

		setTimeout(
				function() {
					document.getElementById("waitMessage").style.visibility = "hidden";
					document.getElementById("divImg").style.visibility = "hidden";
					document.getElementById("reportButton").disabled = false;
					var del = document.getElementById("divSpinner");
					del.parentNode.removeChild(del);
					document.getElementsByName("strOptionHidden6")[0].style.visibility = "hidden";

				}, 8000);
	}
};

function formatoFolioInicial() {

	var folio = document.getElementsByName("folioInicial")[0].value;

	if (folio.length < 8 && folio != 0) {

		if (folio.length == 7) {

			document.getElementsByName("folioInicial")[0].value = "0"
					+ document.getElementsByName("folioInicial")[0].value;

		}
		;

		if (folio.length == 6 && folio != 0) {

			document.getElementsByName("folioInicial")[0].value = "00"
					+ document.getElementsByName("folioInicial")[0].value;

		}
		;

		if (folio.length == 5 && folio != 0) {

			document.getElementsByName("folioInicial")[0].value = "000"
					+ document.getElementsByName("folioInicial")[0].value;

		}
		;

		if (folio.length == 4 && folio != 0) {

			document.getElementsByName("folioInicial")[0].value = "0000"
					+ document.getElementsByName("folioInicial")[0].value;

		}
		;

		if (folio.length == 3 && folio != 0) {

			document.getElementsByName("folioInicial")[0].value = "00000"
					+ document.getElementsByName("folioInicial")[0].value;

		}
		;

		if (folio.length == 2 && folio != 0) {

			document.getElementsByName("folioInicial")[0].value = "000000"
					+ document.getElementsByName("folioInicial")[0].value;

		}
		;

		if (folio.length == 1 && folio != 0) {

			document.getElementsByName("folioInicial")[0].value = "0000000"
					+ document.getElementsByName("folioInicial")[0].value;

		}
		;

	}
	;
};

function formatoFolioFinal() {

	var folio = document.getElementsByName("folioFinal")[0].value;

	if (folio.length < 8) {

		if (folio.length == 7 && folio != 0) {

			document.getElementsByName("folioFinal")[0].value = "0"
					+ document.getElementsByName("folioFinal")[0].value;

		}
		;

		if (folio.length == 6 && folio != 0) {

			document.getElementsByName("folioFinal")[0].value = "00"
					+ document.getElementsByName("folioFinal")[0].value;

		}
		;

		if (folio.length == 5 && folio != 0) {

			document.getElementsByName("folioFinal")[0].value = "000"
					+ document.getElementsByName("folioFinal")[0].value;

		}
		;

		if (folio.length == 4 && folio != 0) {

			document.getElementsByName("folioFinal")[0].value = "0000"
					+ document.getElementsByName("folioFinal")[0].value;

		}
		;

		if (folio.length == 3 && folio != 0) {

			document.getElementsByName("folioFinal")[0].value = "00000"
					+ document.getElementsByName("folioFinal")[0].value;

		}
		;

		if (folio.length == 2 && folio != 0) {

			document.getElementsByName("folioFinal")[0].value = "000000"
					+ document.getElementsByName("folioFinal")[0].value;

		}
		;

		if (folio.length == 1 && folio != 0) {

			document.getElementsByName("folioFinal")[0].value = "0000000"
					+ document.getElementsByName("folioFinal")[0].value;

		}
		;

	}
	;
};

function isNumber(evt) {
	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	}
	return true;
};

function fechaFinalAuto() {

	if (flagDate == 0
			&& document.getElementsByName("fechaFinal")[0].disabled == false
			&& (inicialDate != document.getElementsByName("fechaInicial")[0].value)) {
		flagDate = 1;
		document.getElementsByName("fechaFinal")[0].value = document
				.getElementsByName("fechaInicial")[0].value;
		inicialDate = document.getElementsByName("fechaInicial")[0].value;
	}
};

function changeFlagDate() {

	flagDate = 0;

};

function reiniciaColumnas() {

	document.getElementById("divFolio").style.visibility = "hidden";
	document.getElementById("divFecha").style.visibility = "hidden";
	document.getElementById("divTID").style.visibility = "hidden";
	document.getElementById("divObservaciones").style.visibility = "hidden";
	document.getElementById("divEstadoActual").style.visibility = "hidden";
	document.getElementById("divTipo").style.visibility = "hidden";
	document.getElementById("divEntidad").style.visibility = "hidden";
	document.getElementById("divResponsable").style.visibility = "hidden";
	document.getElementById("divEvento").style.visibility = "hidden";
	document.getElementById("divEventoUltimo").style.visibility = "hidden";
	document.getElementById("divChasis").style.visibility = "hidden";
	document.getElementById("ordenColumnas").innerHTML = "";
	document.getElementsByName("strColumnFolio")[0].disabled = true;
	document.getElementsByName("strColumnFecha")[0].disabled = true;
	document.getElementsByName("strColumnTID")[0].disabled = true;
	document.getElementsByName("strColumnObservaciones")[0].disabled = true;
	document.getElementsByName("strColumnEstadoActual")[0].disabled = true;
	document.getElementsByName("strColumnTipo")[0].disabled = true;
	document.getElementsByName("strColumnResponsable")[0].disabled = true;
	document.getElementsByName("strColumnChasis")[0].disabled = true;
	document.getElementsByName("strColumnEntidad")[0].disabled = true;
	document.getElementsByName("strColumnEvento")[0].disabled = true;
	document.getElementsByName("strOptionHidden1")[0].checked = false;
	document.getElementsByName("strOptionHidden2")[0].checked = false;
	document.getElementsByName("strOptionHidden3")[0].checked = false;
	document.getElementsByName("strOptionHidden4")[0].checked = false;
	document.getElementsByName("strOptionHidden5")[0].checked = false;
	document.getElementsByName("strOptionHidden6")[0].checked = false;
	
	document.getElementsByName("strColumnFolio")[0].checked = false;
	document.getElementsByName("strColumnFecha")[0].checked = false;
	document.getElementsByName("strColumnTID")[0].checked = false;
	document.getElementsByName("strColumnObservaciones")[0].checked = false;
	document.getElementsByName("strColumnEstadoActual")[0].checked = false;
	document.getElementsByName("strColumnTipo")[0].checked = false;
	document.getElementsByName("strColumnResponsable")[0].checked = false;
	document.getElementsByName("strColumnChasis")[0].checked = false;
	document.getElementsByName("strColumnEntidad")[0].checked = false;
	document.getElementsByName("strColumnEvento")[0].checked = false;
	
	document.getElementById("reportButton").disabled = true;
	document.getElementById("reportButton").style.color = "#808080";
	document.getElementById("waitMessage").style.visibility = "hidden";

};