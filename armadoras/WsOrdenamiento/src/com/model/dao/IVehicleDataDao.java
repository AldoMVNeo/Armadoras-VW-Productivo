package com.model.dao;

import java.math.BigDecimal;

import srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLineas_Out;
import srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMarcas_Out;
import srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaSublineas_Out;

//import com.view.bean.ClsVehicleDataBean;

public interface IVehicleDataDao {

	Srclon_WsOrdenamientoImpl_buscaMarcas_Out buscaMarcas(String pCveMarca);
	/**
	 * @param ClsVehicleDataBean
	 * @param intParameter
	 * @return ClsVehicleData
	 * 
	 * Obtain vehicle data from one parameter.
	 * */
	//public ClsVehicleDataBean getVehicleData(ClsVehicleDataBean objVehicleData,int intParameter);

	Srclon_WsOrdenamientoImpl_buscaLineas_Out buscaLineas(String pCveMarca,
			String pCveLinea, BigDecimal pCveModelo, String pCveOrigen,
			String pCveClase);
	Srclon_WsOrdenamientoImpl_buscaSublineas_Out buscaSublineas(
			String pCveMarca, String pCveLinea, String pCveSublinea,
			BigDecimal pCveModelo, String pCveOrigen, String pCveClase,
			BigDecimal pCveTipo);

	/**
	 * @param strUser
	 * @param strPassword
	 * @ return intIdUser
	 * 
	 * Verify user and password for authenticate
	 * */
	//public Integer getAuthenticationValid(ClsVehicleDataBean objVehicleData);
	
}
