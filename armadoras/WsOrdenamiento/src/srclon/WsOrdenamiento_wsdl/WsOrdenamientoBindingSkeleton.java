/**
 * WsOrdenamientoBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package srclon.WsOrdenamiento_wsdl;

public class WsOrdenamientoBindingSkeleton implements srclon.WsOrdenamiento_wsdl.WsOrdenamientoPortType, org.apache.axis.wsdl.Skeleton {
    private srclon.WsOrdenamiento_wsdl.WsOrdenamientoPortType impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveRecaudadora"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("buscaRecaudadoras", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaRecaudadoras_Out"));
        _oper.setElementQName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaRecaudadoras"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("buscaRecaudadoras") == null) {
            _myOperations.put("buscaRecaudadoras", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("buscaRecaudadoras")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveCombustible"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("buscaCombustibles", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaCombustibles_Out"));
        _oper.setElementQName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaCombustibles"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("buscaCombustibles") == null) {
            _myOperations.put("buscaCombustibles", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("buscaCombustibles")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveMarca"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveLinea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveSublinea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveModelo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveOrigen"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveClase"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveTipo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("buscaSublineas", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaSublineas_Out"));
        _oper.setElementQName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaSublineas"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("buscaSublineas") == null) {
            _myOperations.put("buscaSublineas", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("buscaSublineas")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveMarca"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveLinea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveModelo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveOrigen"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveClase"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("buscaLineas", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaLineas_Out"));
        _oper.setElementQName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaLineas"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("buscaLineas") == null) {
            _myOperations.put("buscaLineas", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("buscaLineas")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveMunicipio"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveLocalidad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveColonia"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("buscaColonias", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaColonias_Out"));
        _oper.setElementQName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaColonias"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("buscaColonias") == null) {
            _myOperations.put("buscaColonias", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("buscaColonias")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveAbrev"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("abreviaDirecciones", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_abreviaDirecciones_Out"));
        _oper.setElementQName(new javax.xml.namespace.QName("WsOrdenamiento", "abreviaDirecciones"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("abreviaDirecciones") == null) {
            _myOperations.put("abreviaDirecciones", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("abreviaDirecciones")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveMunicipio"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveLocalidad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("buscaLocalidades", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaLocalidades_Out"));
        _oper.setElementQName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaLocalidades"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("buscaLocalidades") == null) {
            _myOperations.put("buscaLocalidades", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("buscaLocalidades")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveServicio"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("buscaServicios", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaServicios_Out"));
        _oper.setElementQName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaServicios"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("buscaServicios") == null) {
            _myOperations.put("buscaServicios", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("buscaServicios")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveUso"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("buscaUsos", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaUsos_Out"));
        _oper.setElementQName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaUsos"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("buscaUsos") == null) {
            _myOperations.put("buscaUsos", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("buscaUsos")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveClase"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveTipo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("buscaTipos", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaTipos_Out"));
        _oper.setElementQName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaTipos"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("buscaTipos") == null) {
            _myOperations.put("buscaTipos", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("buscaTipos")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveMunicipio"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("buscaMunicipios", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaMunicipios_Out"));
        _oper.setElementQName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaMunicipios"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("buscaMunicipios") == null) {
            _myOperations.put("buscaMunicipios", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("buscaMunicipios")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveMarca"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("buscaMarcas", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaMarcas_Out"));
        _oper.setElementQName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaMarcas"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("buscaMarcas") == null) {
            _myOperations.put("buscaMarcas", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("buscaMarcas")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveClase"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("buscaClases", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaClases_Out"));
        _oper.setElementQName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaClases"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("buscaClases") == null) {
            _myOperations.put("buscaClases", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("buscaClases")).add(_oper);
    }

    public WsOrdenamientoBindingSkeleton() {
        this.impl = new srclon.WsOrdenamiento_wsdl.WsOrdenamientoBindingImpl();
    }

    public WsOrdenamientoBindingSkeleton(srclon.WsOrdenamiento_wsdl.WsOrdenamientoPortType impl) {
        this.impl = impl;
    }
    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaRecaudadoras_Out buscaRecaudadoras(java.math.BigDecimal pCveRecaudadora) throws java.rmi.RemoteException
    {
        srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaRecaudadoras_Out ret = impl.buscaRecaudadoras(pCveRecaudadora);
        return ret;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaCombustibles_Out buscaCombustibles(java.math.BigDecimal pCveCombustible) throws java.rmi.RemoteException
    {
        srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaCombustibles_Out ret = impl.buscaCombustibles(pCveCombustible);
        return ret;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaSublineas_Out buscaSublineas(java.lang.String pCveMarca, java.lang.String pCveLinea, java.lang.String pCveSublinea, java.math.BigDecimal pCveModelo, java.lang.String pCveOrigen, java.lang.String pCveClase, java.math.BigDecimal pCveTipo) throws java.rmi.RemoteException
    {
        srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaSublineas_Out ret = impl.buscaSublineas(pCveMarca, pCveLinea, pCveSublinea, pCveModelo, pCveOrigen, pCveClase, pCveTipo);
        return ret;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLineas_Out buscaLineas(java.lang.String pCveMarca, java.lang.String pCveLinea, java.math.BigDecimal pCveModelo, java.lang.String pCveOrigen, java.lang.String pCveClase) throws java.rmi.RemoteException
    {
        srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLineas_Out ret = impl.buscaLineas(pCveMarca, pCveLinea, pCveModelo, pCveOrigen, pCveClase);
        return ret;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaColonias_Out buscaColonias(java.math.BigDecimal pCveMunicipio, java.math.BigDecimal pCveLocalidad, java.math.BigDecimal pCveColonia) throws java.rmi.RemoteException
    {
        srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaColonias_Out ret = impl.buscaColonias(pCveMunicipio, pCveLocalidad, pCveColonia);
        return ret;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_abreviaDirecciones_Out abreviaDirecciones(java.lang.String pCveAbrev) throws java.rmi.RemoteException
    {
        srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_abreviaDirecciones_Out ret = impl.abreviaDirecciones(pCveAbrev);
        return ret;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLocalidades_Out buscaLocalidades(java.math.BigDecimal pCveMunicipio, java.math.BigDecimal pCveLocalidad) throws java.rmi.RemoteException
    {
        srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLocalidades_Out ret = impl.buscaLocalidades(pCveMunicipio, pCveLocalidad);
        return ret;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaServicios_Out buscaServicios(java.math.BigDecimal pCveServicio) throws java.rmi.RemoteException
    {
        srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaServicios_Out ret = impl.buscaServicios(pCveServicio);
        return ret;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaUsos_Out buscaUsos(java.math.BigDecimal pCveUso) throws java.rmi.RemoteException
    {
        srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaUsos_Out ret = impl.buscaUsos(pCveUso);
        return ret;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaTipos_Out buscaTipos(java.lang.String pCveClase, java.math.BigDecimal pCveTipo) throws java.rmi.RemoteException
    {
        srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaTipos_Out ret = impl.buscaTipos(pCveClase, pCveTipo);
        return ret;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMunicipios_Out buscaMunicipios(java.math.BigDecimal pCveMunicipio) throws java.rmi.RemoteException
    {
        srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMunicipios_Out ret = impl.buscaMunicipios(pCveMunicipio);
        return ret;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMarcas_Out buscaMarcas(java.lang.String pCveMarca) throws java.rmi.RemoteException
    {
        srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMarcas_Out ret = impl.buscaMarcas(pCveMarca);
        return ret;
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaClases_Out buscaClases(java.lang.String pCveClase) throws java.rmi.RemoteException
    {
        srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaClases_Out ret = impl.buscaClases(pCveClase);
        return ret;
    }

}
