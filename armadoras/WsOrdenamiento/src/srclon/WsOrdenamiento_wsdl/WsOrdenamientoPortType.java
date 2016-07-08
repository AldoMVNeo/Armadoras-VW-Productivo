/**
 * WsOrdenamientoPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package srclon.WsOrdenamiento_wsdl;

public interface WsOrdenamientoPortType extends java.rmi.Remote {
    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaRecaudadoras_Out buscaRecaudadoras(java.math.BigDecimal pCveRecaudadora) throws java.rmi.RemoteException;
    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaCombustibles_Out buscaCombustibles(java.math.BigDecimal pCveCombustible) throws java.rmi.RemoteException;
    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaSublineas_Out buscaSublineas(java.lang.String pCveMarca, java.lang.String pCveLinea, java.lang.String pCveSublinea, java.math.BigDecimal pCveModelo, java.lang.String pCveOrigen, java.lang.String pCveClase, java.math.BigDecimal pCveTipo) throws java.rmi.RemoteException;
    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLineas_Out buscaLineas(java.lang.String pCveMarca, java.lang.String pCveLinea, java.math.BigDecimal pCveModelo, java.lang.String pCveOrigen, java.lang.String pCveClase) throws java.rmi.RemoteException;
    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaColonias_Out buscaColonias(java.math.BigDecimal pCveMunicipio, java.math.BigDecimal pCveLocalidad, java.math.BigDecimal pCveColonia) throws java.rmi.RemoteException;
    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_abreviaDirecciones_Out abreviaDirecciones(java.lang.String pCveAbrev) throws java.rmi.RemoteException;
    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLocalidades_Out buscaLocalidades(java.math.BigDecimal pCveMunicipio, java.math.BigDecimal pCveLocalidad) throws java.rmi.RemoteException;
    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaServicios_Out buscaServicios(java.math.BigDecimal pCveServicio) throws java.rmi.RemoteException;
    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaUsos_Out buscaUsos(java.math.BigDecimal pCveUso) throws java.rmi.RemoteException;
    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaTipos_Out buscaTipos(java.lang.String pCveClase, java.math.BigDecimal pCveTipo) throws java.rmi.RemoteException;
    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMunicipios_Out buscaMunicipios(java.math.BigDecimal pCveMunicipio) throws java.rmi.RemoteException;
    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMarcas_Out buscaMarcas(java.lang.String pCveMarca) throws java.rmi.RemoteException;
    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaClases_Out buscaClases(java.lang.String pCveClase) throws java.rmi.RemoteException;
}
