/**
 * @author Axel Galicia
 * @version 1.0.0
 * 
 * Description: This class has all Web Service Methods Implementations to SQLServer 
 */

package com.model.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import srclon.WsOrdenamiento_xsd.Srclon_LineaVehiculoTyUser;
import srclon.WsOrdenamiento_xsd.Srclon_LineasVehiculosNt;
import srclon.WsOrdenamiento_xsd.Srclon_MarcaVehiculoTyUser;
import srclon.WsOrdenamiento_xsd.Srclon_MarcasVehiculosNt;
import srclon.WsOrdenamiento_xsd.Srclon_SublineaVehiculoTyUser;
import srclon.WsOrdenamiento_xsd.Srclon_SublineasVehiculosNt;
import srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLineas_Out;
import srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMarcas_Out;
import srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaSublineas_Out;

import com.model.dao.IVehicleDataDao;
import com.model.db.ConnectionPool;
//import com.view.bean.ClsVehicleDataBean;
import com.view.utils.ConstantVariables;
import com.view.utils.MessageBundleUtil;

public class VehicleDataDaoImpl implements IVehicleDataDao{

	

	
	/**
	 * @param ClsVehicleData
	 * @param intParameter
	 * 
	 * @author Indigo
	 * 
	 * */
	/*@Override
	public ClsVehicleDataBean getVehicleData(ClsVehicleDataBean objVehicleDataBean,int intParameter) {
		
		ClsVehicleDataBean objVehicleDataResultBean = null;
		Connection con = null;
		ResultSet rs = null;
		
		try{
			con = ConnectionPool.getConnectionPoolSQLServer();
			String sqlVehicleData = "SELECT V.strPlateNumber,V.intIdentificationNumber,V.strVehicleMake,V.strVehicleType" +
					",V.strChasisNumber,V.strPurpose,V.strTitle,V.strName,V.strMiddlename,V.strSurname" +
					",V.strEstate,V.strCity,V.strStreet,V.strNumber,V.intPMB,V.strSpecificationVin,V.strPreviousLicPlateNum" +
					",V.strLicencingArea,V.strModel,V.intModelYear,V.strColour,V.strEngineNumber,V.strRegistrationType,V.intStatus,V.strObservations " +
					" FROM tblDatosVehiculo V WHERE (V.strPlateNumber=? or V.intIdentificationNumber=?)";
			
			PreparedStatement psVehicleData = con.prepareStatement(sqlVehicleData);
			
			//Validating parameters
			if(intParameter==1){
				objVehicleDataBean.setIntIdentificationNumber(-1);
			}
			if(intParameter==2){
				objVehicleDataBean.setStrPlateNumber("-1");
			}
			
			psVehicleData.setString(1,objVehicleDataBean.getStrPlateNumber());
			psVehicleData.setInt(2,objVehicleDataBean.getIntIdentificationNumber());
			
			rs = psVehicleData.executeQuery();
			
			
			
			while(rs.next()){
				
				//Setting values
				
				objVehicleDataResultBean = new ClsVehicleDataBean();
				//Plate Number
				objVehicleDataResultBean.setStrPlateNumber(rs.getString("strPlateNumber"));
				//Identification Number
				objVehicleDataResultBean.setIntIdentificationNumber(rs.getInt("intIdentificationNumber"));
				//Vehicle Make
				objVehicleDataResultBean.setStrVehicleMake(rs.getString("strVehicleMake"));
				//Vehicle Type
				objVehicleDataResultBean.setStrVehicleType(rs.getString("strVehicleType"));
				//Chasis Number
				objVehicleDataResultBean.setStrChasisNumber(rs.getString("strChasisNumber"));
				//Purpose
				objVehicleDataResultBean.setStrPurpose(rs.getString("strPurpose"));
				//Title
				objVehicleDataResultBean.setStrTitle(rs.getString("strTitle"));
				//Name
				objVehicleDataResultBean.setStrName(rs.getString("strName"));
				//Middle Name
				objVehicleDataResultBean.setStrMiddlename(rs.getString("strMiddlename"));
				//Surname
				objVehicleDataResultBean.setStrSurname(rs.getString("strSurname"));
				//Estate
				objVehicleDataResultBean.setStrEstate(rs.getString("strEstate"));
				//City
				objVehicleDataResultBean.setStrCity(rs.getString("strCity"));
				//Street
				objVehicleDataResultBean.setStrStreet(rs.getString("strStreet"));
				//Number
				objVehicleDataResultBean.setStrNumber(rs.getString("strNumber"));
				//PMB
				objVehicleDataResultBean.setIntPMB(rs.getInt("intPMB"));
				//Specification Vin
				objVehicleDataResultBean.setStrSpecificationVin(rs.getString("strSpecificationVin"));
				//Previous License Plate Number
				objVehicleDataResultBean.setStrPreviousLicPlateNum(rs.getString("strPreviousLicPlateNum"));
				//Licensing Area
				objVehicleDataResultBean.setStrLicencingArea(rs.getString("strLicencingArea"));
				//Model
				objVehicleDataResultBean.setStrModel(rs.getString("strModel"));
				//Model Year
				objVehicleDataResultBean.setIntModelYear(rs.getInt("intModelYear"));
				//Color
				objVehicleDataResultBean.setStrColour(rs.getString("strColour"));
				//Engine Number
				objVehicleDataResultBean.setStrEngineNumber(rs.getString("strEngineNumber"));
				//Registration Type
				objVehicleDataResultBean.setStrRegistrationType(rs.getString("strRegistrationType"));
				//Status
				objVehicleDataResultBean.setIntStatus(rs.getInt("intStatus"));
				//Observations
				objVehicleDataResultBean.setStrObservations(rs.getString("strObservations"));
				
				//Message
				objVehicleDataResultBean.setStrMessage("");		
				
				//Status Key
				objVehicleDataResultBean.setIntKeyStatusResult(1);
				
			}
			
			if(objVehicleDataResultBean==null){
				objVehicleDataResultBean = new ClsVehicleDataBean();
				objVehicleDataResultBean.setStrMessage(MessageBundleUtil.getStrMessageFromBundle("vehicle_not_exist_in_database"));
				//Status Key
				objVehicleDataResultBean.setIntKeyStatusResult(ConstantVariables.VEHICLE_NOT_EXIST_IN_DATABASE);
			}
			
			//Close Connection
			ConnectionPool.closeConnection(con);
			
		}
		
		catch(Exception e){
			String strMessageError = MessageBundleUtil.getStrMessageFromBundle("system_error");
			objVehicleDataResultBean = new ClsVehicleDataBean();
			objVehicleDataResultBean.setStrMessage(strMessageError);
			//Status Key
			objVehicleDataResultBean.setIntKeyStatusResult(ConstantVariables.SYSTEM_ERROR);
			System.out.println("*** ERROR: No se pudo consultar los datos del vehículo");
			System.out.println(e.getMessage());
		}
		
		finally{
			ConnectionPool.closeConnection(con);
		}
		
		return objVehicleDataResultBean;
	}
	*/
	
	/**
	 * @param strUser
	 * @param strPassword
	 * @ return intIdUser
	 * 
	 * Verify user and password for authenticate
	 * */
	/*
	public Integer getAuthenticationValid(ClsVehicleDataBean objVehicleData){

		Integer intIdUser = -1;
		Connection con = null;
		ResultSet rs = null;
		
		
		try{
			con = ConnectionPool.getConnectionPoolSQLServer();
			String sqlAuthentication = "SELECT U.intIdUser from tblUsuarios U where U.strUser=? and U.strPassword=?";
			PreparedStatement psUserAuth = con.prepareStatement(sqlAuthentication);
			psUserAuth.setString(1,objVehicleData.getStrUser());
			psUserAuth.setString(2,objVehicleData.getStrPassword());
			rs = psUserAuth.executeQuery();
			while(rs.next()){
				intIdUser = rs.getInt("intIdUser");
			}
			ConnectionPool.closeConnection(con);
		}
		
		catch(Exception e){
			
			intIdUser = ConstantVariables.SYSTEM_ERROR;
			System.out.println("*** ERROR: No se pudo consultar al usuario del Web Service");
			System.out.println(e.getMessage());
		}
		
		finally{
			ConnectionPool.closeConnection(con);
		}
		
		
		return intIdUser;
	}
	*/
	
	
	@SuppressWarnings("null")
	@Override
	public Srclon_WsOrdenamientoImpl_buscaMarcas_Out buscaMarcas(
			String pCveMarca) {
		// TODO Auto-generated method stub
		Srclon_WsOrdenamientoImpl_buscaMarcas_Out objVehicleDataResultBean = null;
		Connection con = null;
		ResultSet rs = null;
		
		try{
			con = ConnectionPool.getConnectionPoolSQLServer();
			String sqlVehicleData = "select * "+
			"from tblMarcas M ";
			
			PreparedStatement psVehicleData = null;
			if(pCveMarca.equals("")){
				sqlVehicleData+=";";
				psVehicleData=con.prepareStatement(sqlVehicleData);
			}
			else
			{
				sqlVehicleData+="where M.strClave=?;";
				psVehicleData=con.prepareStatement(sqlVehicleData);
				psVehicleData.setString(1,pCveMarca);
				//psVehicleData.setInt(2,objVehicleDataBean.getIntIdentificationNumber());
			}
			rs = psVehicleData.executeQuery();
			
			Vector<Srclon_MarcaVehiculoTyUser> marcasV=new Vector<Srclon_MarcaVehiculoTyUser>();
			
			
			while(rs.next()){
				
				//Setting values
				
				Srclon_MarcaVehiculoTyUser marca=new Srclon_MarcaVehiculoTyUser();
				
				marca.setClave(rs.getString("strClave"));
				marca.setDescripcion(rs.getString("strNombreMarca"));
				
				marcasV.add(marca);
			}
			if(marcasV.size()>0){
				objVehicleDataResultBean= new Srclon_WsOrdenamientoImpl_buscaMarcas_Out();
				//Message
				objVehicleDataResultBean.setPmensajeOut("");		
				
				//Status Key
				objVehicleDataResultBean.setPresultadoOut(new BigDecimal(1));
				Srclon_MarcaVehiculoTyUser[] marcasA=new Srclon_MarcaVehiculoTyUser[]{};
				marcasA=marcasV.toArray(marcasA);
				objVehicleDataResultBean.setPmarcasOut(new Srclon_MarcasVehiculosNt( marcasA));
			}
			if(objVehicleDataResultBean==null){
				objVehicleDataResultBean = new Srclon_WsOrdenamientoImpl_buscaMarcas_Out();
				objVehicleDataResultBean.setPmensajeOut(MessageBundleUtil.getStrMessageFromBundle("vehicle_not_exist_in_database"));
				//Status Key
				objVehicleDataResultBean.setPresultadoOut(new BigDecimal(ConstantVariables.VEHICLE_NOT_EXIST_IN_DATABASE));
				objVehicleDataResultBean.setPmarcasOut(new Srclon_MarcasVehiculosNt(new Srclon_MarcaVehiculoTyUser[]{}));
			}
			
			//Close Connection
			ConnectionPool.closeConnection(con);
			
		}
		
		catch(Exception e){
			String strMessageError = MessageBundleUtil.getStrMessageFromBundle("system_error");
			objVehicleDataResultBean = new Srclon_WsOrdenamientoImpl_buscaMarcas_Out();
			objVehicleDataResultBean.setPmensajeOut(strMessageError);
			//Status Key
			objVehicleDataResultBean.setPresultadoOut(new BigDecimal(ConstantVariables.SYSTEM_ERROR));
			objVehicleDataResultBean.setPmarcasOut(new Srclon_MarcasVehiculosNt(new Srclon_MarcaVehiculoTyUser[]{}));
			System.out.println("*** ERROR: No se pudo consultar los datos del vehículo");
			System.out.println(e.getMessage());
		}
		
		finally{
			ConnectionPool.closeConnection(con);
		}
		
		return objVehicleDataResultBean;
	}

	@Override
	public Srclon_WsOrdenamientoImpl_buscaLineas_Out buscaLineas(
			String pCveMarca, String pCveLinea, BigDecimal pCveModelo,
			String pCveOrigen, String pCveClase) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Srclon_WsOrdenamientoImpl_buscaLineas_Out objVehicleDataResultBean = null;
		Connection con = null;
		ResultSet rs = null;
		
		try{
			con = ConnectionPool.getConnectionPoolSQLServer();
			String sqlVehicleData = "select * "+
				"from tblLineas L "+
				"where L.strClaveMarca=? "+
				"and L.intModelo=? "+
				"and L.strOrigen=? "+
				"and L.strClaveClaseVehiculo=?";
			
			PreparedStatement psVehicleData = null;
			if(pCveLinea.equals("")){
				sqlVehicleData+=";";
				psVehicleData=con.prepareStatement(sqlVehicleData);
				psVehicleData.setString(1,pCveMarca);
				psVehicleData.setInt(2,pCveModelo.intValue());
				psVehicleData.setString(3,pCveOrigen);
				psVehicleData.setString(4,pCveClase);
			}
			else
			{
				sqlVehicleData+="and L.strLinea=?;";
				psVehicleData=con.prepareStatement(sqlVehicleData);
				psVehicleData.setString(1,pCveMarca);
				psVehicleData.setInt(2,pCveModelo.intValue());
				psVehicleData.setString(3,pCveOrigen);
				psVehicleData.setString(4,pCveClase);
				psVehicleData.setString(5,pCveLinea);
				//psVehicleData.setInt(2,objVehicleDataBean.getIntIdentificationNumber());
			}
			rs = psVehicleData.executeQuery();
			
			Vector<Srclon_LineaVehiculoTyUser> marcasV=new Vector<Srclon_LineaVehiculoTyUser>();
			
			
			while(rs.next()){
				
				//Setting values
				
				Srclon_LineaVehiculoTyUser marca=new Srclon_LineaVehiculoTyUser();
				
				marca.setClaseVehiculo(rs.getString("strClaveClaseVehiculo"));
				marca.setCveLinea(rs.getString("strLinea"));
				marca.setCveMarca(rs.getString("strClaveMarca"));
				marca.setDescripcion(rs.getString("strDescripcionLinea"));
				marca.setModelo(rs.getBigDecimal("intModelo"));
				marca.setOrigen(rs.getString("strOrigen"));
				
				marcasV.add(marca);
			}
			if(marcasV.size()>0){
				objVehicleDataResultBean= new Srclon_WsOrdenamientoImpl_buscaLineas_Out();
				//Message
				objVehicleDataResultBean.setPmensajeOut("");		
				
				//Status Key
				objVehicleDataResultBean.setPresultadoOut(new BigDecimal(1));
				Srclon_LineaVehiculoTyUser[] marcasA=new Srclon_LineaVehiculoTyUser[]{};
				marcasA=marcasV.toArray(marcasA);
				objVehicleDataResultBean.setPlineasOut(new Srclon_LineasVehiculosNt( marcasA));
			}
			if(objVehicleDataResultBean==null){
				objVehicleDataResultBean = new Srclon_WsOrdenamientoImpl_buscaLineas_Out();
				objVehicleDataResultBean.setPmensajeOut(MessageBundleUtil.getStrMessageFromBundle("vehicle_not_exist_in_database"));
				//Status Key
				objVehicleDataResultBean.setPresultadoOut(new BigDecimal(ConstantVariables.VEHICLE_NOT_EXIST_IN_DATABASE));
				objVehicleDataResultBean.setPlineasOut(new Srclon_LineasVehiculosNt(new Srclon_LineaVehiculoTyUser[]{}));
			}
			
			//Close Connection
			ConnectionPool.closeConnection(con);
			
		}
		
		catch(Exception e){
			String strMessageError = MessageBundleUtil.getStrMessageFromBundle("system_error");
			objVehicleDataResultBean = new Srclon_WsOrdenamientoImpl_buscaLineas_Out();
			objVehicleDataResultBean.setPmensajeOut(strMessageError);
			//Status Key
			objVehicleDataResultBean.setPresultadoOut(new BigDecimal(ConstantVariables.SYSTEM_ERROR));
			objVehicleDataResultBean.setPlineasOut(new Srclon_LineasVehiculosNt(new Srclon_LineaVehiculoTyUser[]{}));
			System.out.println("*** ERROR: No se pudo consultar los datos del vehículo");
			System.out.println(e.getMessage());
		}
		
		finally{
			ConnectionPool.closeConnection(con);
		}
		
		return objVehicleDataResultBean;
	}

	@Override
	public Srclon_WsOrdenamientoImpl_buscaSublineas_Out buscaSublineas(
			String pCveMarca, String pCveLinea, String pCveSublinea,
			BigDecimal pCveModelo, String pCveOrigen, String pCveClase,
			BigDecimal pCveTipo) {
		// TODO Auto-generated method stub
		Srclon_WsOrdenamientoImpl_buscaSublineas_Out objVehicleDataResultBean = null;
		Connection con = null;
		ResultSet rs = null;
		
		try{
			con = ConnectionPool.getConnectionPoolSQLServer();
			String sqlVehicleData = "select * "+ 
				"from tblSubLineas L "+
				"where L.strClaveMarca=? "+
				"and L.intModelo=? "+
				"and L.strOrigen=? "+
				"and L.strClaveClaseVehiculo=? "+
				"and L.strLinea=? "+
				"and L.intClaveTipoVehiculo=?";
			
			PreparedStatement psVehicleData = null;
			if(pCveSublinea.equals("")){
				sqlVehicleData+=";";
				psVehicleData=con.prepareStatement(sqlVehicleData);
				psVehicleData.setString(1,pCveMarca);
				psVehicleData.setInt(2,pCveModelo.intValue());
				psVehicleData.setString(3,pCveOrigen);
				psVehicleData.setString(4,pCveClase);
				psVehicleData.setString(5,pCveLinea);
				psVehicleData.setInt(6,pCveTipo.intValue());
			}
			else
			{
				sqlVehicleData+="L.strSubLinea=?;";
				psVehicleData=con.prepareStatement(sqlVehicleData);
				psVehicleData.setString(1,pCveMarca);
				psVehicleData.setInt(2,pCveModelo.intValue());
				psVehicleData.setString(3,pCveOrigen);
				psVehicleData.setString(4,pCveClase);
				psVehicleData.setString(5,pCveLinea);
				psVehicleData.setInt(6,pCveTipo.intValue());
				psVehicleData.setString(7,pCveSublinea);
				//psVehicleData.setInt(2,objVehicleDataBean.getIntIdentificationNumber());
			}
			rs = psVehicleData.executeQuery();
			
			Vector<Srclon_SublineaVehiculoTyUser> marcasV=new Vector<Srclon_SublineaVehiculoTyUser>();
			
			
			while(rs.next()){
				
				//Setting values
				
				Srclon_SublineaVehiculoTyUser marca=new Srclon_SublineaVehiculoTyUser();
				
				marca.setClaseVehiculo(rs.getString("strClaveClaseVehiculo"));
				marca.setCveLinea(rs.getString("strLinea"));
				marca.setCveMarca(rs.getString("strClaveMarca"));
				marca.setCveSublinea(rs.getString("strSubLinea"));
				marca.setDescripcion(rs.getString("strDescripcionSublinea"));
				marca.setModelo(rs.getBigDecimal("intModelo"));
				marca.setOrigen(rs.getString("strOrigen"));
				marca.setPuertas(new BigDecimal( rs.getString("strNumeroPuertas")));
				marca.setTipoVehiculo(new BigDecimal( rs.getInt("intClaveTipoVehiculo")));
				
				
				marcasV.add(marca);
			}
			if(marcasV.size()>0){
				objVehicleDataResultBean= new Srclon_WsOrdenamientoImpl_buscaSublineas_Out();
				//Message
				objVehicleDataResultBean.setPmensajeOut("");		
				
				//Status Key
				objVehicleDataResultBean.setPresultadoOut(new BigDecimal(1));
				Srclon_SublineaVehiculoTyUser[] marcasA=new Srclon_SublineaVehiculoTyUser[]{};
				marcasA=marcasV.toArray(marcasA);
				objVehicleDataResultBean.setPsublineasOut(new Srclon_SublineasVehiculosNt( marcasA));
			}
			if(objVehicleDataResultBean==null){
				objVehicleDataResultBean = new Srclon_WsOrdenamientoImpl_buscaSublineas_Out();
				objVehicleDataResultBean.setPmensajeOut(MessageBundleUtil.getStrMessageFromBundle("vehicle_not_exist_in_database"));
				//Status Key
				objVehicleDataResultBean.setPresultadoOut(new BigDecimal(ConstantVariables.VEHICLE_NOT_EXIST_IN_DATABASE));
				objVehicleDataResultBean.setPsublineasOut(new Srclon_SublineasVehiculosNt(new Srclon_SublineaVehiculoTyUser[]{}));
			}
			
			//Close Connection
			ConnectionPool.closeConnection(con);
			
		}
		
		catch(Exception e){
			String strMessageError = MessageBundleUtil.getStrMessageFromBundle("system_error");
			objVehicleDataResultBean = new Srclon_WsOrdenamientoImpl_buscaSublineas_Out();
			objVehicleDataResultBean.setPmensajeOut(strMessageError);
			//Status Key
			objVehicleDataResultBean.setPresultadoOut(new BigDecimal(ConstantVariables.SYSTEM_ERROR));
			objVehicleDataResultBean.setPsublineasOut(new Srclon_SublineasVehiculosNt(new Srclon_SublineaVehiculoTyUser[]{}));
			System.out.println("*** ERROR: No se pudo consultar los datos del vehículo");
			System.out.println(e.getMessage());
		}
		
		finally{
			ConnectionPool.closeConnection(con);
		}
		
		return objVehicleDataResultBean;
	}

}
