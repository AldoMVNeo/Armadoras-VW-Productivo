/**
 * WsOrdenamientoBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package srclon.WsOrdenamiento_wsdl;

import java.math.BigDecimal;

import javax.jws.WebService;

import com.model.dao.IVehicleDataDao;
import com.model.dao.impl.VehicleDataDaoImpl;
import com.view.utils.ConstantVariables;
import com.view.utils.MessageBundleUtil;

import srclon.WsOrdenamiento_xsd.Srclon_LineaVehiculoTyUser;
import srclon.WsOrdenamiento_xsd.Srclon_LineasVehiculosNt;
import srclon.WsOrdenamiento_xsd.Srclon_MarcaVehiculoTyUser;
import srclon.WsOrdenamiento_xsd.Srclon_MarcasVehiculosNt;
import srclon.WsOrdenamiento_xsd.Srclon_SublineaVehiculoTyUser;
import srclon.WsOrdenamiento_xsd.Srclon_SublineasVehiculosNt;
import srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLineas_Out;
import srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMarcas_Out;
import srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaRecaudadoras_Out;
import srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaSublineas_Out;

@WebService
public class WsOrdenamientoBindingImpl implements srclon.WsOrdenamiento_wsdl.WsOrdenamientoPortType{
    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaRecaudadoras_Out buscaRecaudadoras(java.math.BigDecimal pCveRecaudadora) throws java.rmi.RemoteException {
        return null;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaCombustibles_Out buscaCombustibles(java.math.BigDecimal pCveCombustible) throws java.rmi.RemoteException {
        return null;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaSublineas_Out buscaSublineas(java.lang.String pCveMarca, java.lang.String pCveLinea, java.lang.String pCveSublinea, java.math.BigDecimal pCveModelo, java.lang.String pCveOrigen, java.lang.String pCveClase, java.math.BigDecimal pCveTipo) throws java.rmi.RemoteException {
    	Srclon_WsOrdenamientoImpl_buscaSublineas_Out out= new Srclon_WsOrdenamientoImpl_buscaSublineas_Out();
    	out.setPmensajeOut(MessageBundleUtil.getStrMessageFromBundle("vehicle_not_exist_in_database"));
		//Status Key
    	out.setPresultadoOut(new BigDecimal(ConstantVariables.VEHICLE_NOT_EXIST_IN_DATABASE));
    	out.setPsublineasOut(new Srclon_SublineasVehiculosNt(new Srclon_SublineaVehiculoTyUser[]{}));

        IVehicleDataDao objVehicleDao = new VehicleDataDaoImpl();
        pCveMarca=pCveMarca.trim();
        if(pCveMarca==null||pCveMarca.length()>2){
        	return out;
        }
        if(pCveLinea==null||pCveLinea.length()>2){
        	return out;
        }
        if(pCveOrigen==null||pCveOrigen.length()>2){
        	return out;
        }
        if(pCveClase==null||pCveClase.length()>2){
        	return out;
        }
        if(pCveModelo==null){
        	return out;
        }
        if(pCveTipo==null){
        	return out;
        }
        out=objVehicleDao.buscaSublineas(pCveMarca,  pCveLinea,  pCveSublinea,  pCveModelo,  pCveOrigen,  pCveClase,  pCveTipo);
        
        return out;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLineas_Out buscaLineas(java.lang.String pCveMarca, java.lang.String pCveLinea, java.math.BigDecimal pCveModelo, java.lang.String pCveOrigen, java.lang.String pCveClase) throws java.rmi.RemoteException {
    	Srclon_WsOrdenamientoImpl_buscaLineas_Out out= new Srclon_WsOrdenamientoImpl_buscaLineas_Out();
    	out.setPmensajeOut(MessageBundleUtil.getStrMessageFromBundle("vehicle_not_exist_in_database"));
		//Status Key
    	out.setPresultadoOut(new BigDecimal(ConstantVariables.VEHICLE_NOT_EXIST_IN_DATABASE));
    	out.setPlineasOut(new Srclon_LineasVehiculosNt(new Srclon_LineaVehiculoTyUser[]{}));
        IVehicleDataDao objVehicleDao = new VehicleDataDaoImpl();
        pCveMarca=pCveMarca.trim();
        if(pCveMarca==null||pCveMarca.length()>2){
        	return out;
        }
        if(pCveLinea==null||pCveLinea.length()>2){
        	return out;
        }
        if(pCveOrigen==null||pCveOrigen.length()>2){
        	return out;
        }
        if(pCveClase==null||pCveClase.length()>2){
        	return out;
        }
        out=objVehicleDao.buscaLineas(pCveMarca, pCveLinea, pCveModelo,  pCveOrigen,  pCveClase);
        
        return out;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaColonias_Out buscaColonias(java.math.BigDecimal pCveMunicipio, java.math.BigDecimal pCveLocalidad, java.math.BigDecimal pCveColonia) throws java.rmi.RemoteException {
        return null;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_abreviaDirecciones_Out abreviaDirecciones(java.lang.String pCveAbrev) throws java.rmi.RemoteException {
        return null;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLocalidades_Out buscaLocalidades(java.math.BigDecimal pCveMunicipio, java.math.BigDecimal pCveLocalidad) throws java.rmi.RemoteException {
        return null;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaServicios_Out buscaServicios(java.math.BigDecimal pCveServicio) throws java.rmi.RemoteException {
        return null;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaUsos_Out buscaUsos(java.math.BigDecimal pCveUso) throws java.rmi.RemoteException {
        return null;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaTipos_Out buscaTipos(java.lang.String pCveClase, java.math.BigDecimal pCveTipo) throws java.rmi.RemoteException {
        return null;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMunicipios_Out buscaMunicipios(java.math.BigDecimal pCveMunicipio) throws java.rmi.RemoteException {
        return null;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMarcas_Out buscaMarcas(java.lang.String pCveMarca) throws java.rmi.RemoteException {
        Srclon_WsOrdenamientoImpl_buscaMarcas_Out out= new Srclon_WsOrdenamientoImpl_buscaMarcas_Out();
        /*out.setPmensajeOut("Mensaje");
        out.setPresultadoOut(new BigDecimal(1.0));
        Srclon_MarcasVehiculosNt marcasV=new Srclon_MarcasVehiculosNt();
        Srclon_MarcaVehiculoTyUser marcas[]=new Srclon_MarcaVehiculoTyUser[1];
        marcas[0]=new Srclon_MarcaVehiculoTyUser();
        marcas[0].setClave("1");
        marcas[0].setDescripcion("Marca");
        marcasV.setArray(marcas);
        out.setPmarcasOut(marcasV);*/
        IVehicleDataDao objVehicleDao = new VehicleDataDaoImpl();
        pCveMarca=pCveMarca.trim();
        if(pCveMarca.length()>2){
        	return out;
        }
        out=objVehicleDao.buscaMarcas(pCveMarca);
        
        return out;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaClases_Out buscaClases(java.lang.String pCveClase) throws java.rmi.RemoteException {
        return null;
    }

}
