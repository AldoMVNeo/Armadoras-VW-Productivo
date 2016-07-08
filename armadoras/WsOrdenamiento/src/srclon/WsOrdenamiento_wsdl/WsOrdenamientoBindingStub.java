/**
 * WsOrdenamientoBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package srclon.WsOrdenamiento_wsdl;

public class WsOrdenamientoBindingStub extends org.apache.axis.client.Stub implements srclon.WsOrdenamiento_wsdl.WsOrdenamientoPortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[13];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscaRecaudadoras");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveRecaudadora"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaRecaudadoras_Out"));
        oper.setReturnClass(srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaRecaudadoras_Out.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscaCombustibles");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveCombustible"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaCombustibles_Out"));
        oper.setReturnClass(srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaCombustibles_Out.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscaSublineas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveMarca"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveLinea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveSublinea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveModelo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveOrigen"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveClase"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveTipo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaSublineas_Out"));
        oper.setReturnClass(srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaSublineas_Out.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscaLineas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveMarca"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveLinea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveModelo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveOrigen"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveClase"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaLineas_Out"));
        oper.setReturnClass(srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLineas_Out.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscaColonias");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveMunicipio"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveLocalidad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveColonia"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaColonias_Out"));
        oper.setReturnClass(srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaColonias_Out.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("abreviaDirecciones");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveAbrev"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_abreviaDirecciones_Out"));
        oper.setReturnClass(srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_abreviaDirecciones_Out.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscaLocalidades");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveMunicipio"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveLocalidad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaLocalidades_Out"));
        oper.setReturnClass(srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLocalidades_Out.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscaServicios");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveServicio"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaServicios_Out"));
        oper.setReturnClass(srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaServicios_Out.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscaUsos");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveUso"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaUsos_Out"));
        oper.setReturnClass(srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaUsos_Out.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscaTipos");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveClase"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveTipo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaTipos_Out"));
        oper.setReturnClass(srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaTipos_Out.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscaMunicipios");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveMunicipio"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaMunicipios_Out"));
        oper.setReturnClass(srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMunicipios_Out.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscaMarcas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveMarca"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaMarcas_Out"));
        oper.setReturnClass(srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMarcas_Out.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscaClases");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pCveClase"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaClases_Out"));
        oper.setReturnClass(srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaClases_Out.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[12] = oper;

    }

    public WsOrdenamientoBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public WsOrdenamientoBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public WsOrdenamientoBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "ArrayOfsrclon_AbreviaturaTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_AbreviaturaTyUser[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_AbreviaturaTyUser");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "ArrayOfsrclon_ClaseTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_ClaseTyUser[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_ClaseTyUser");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "ArrayOfsrclon_ColoniaTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_ColoniaTyUser[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_ColoniaTyUser");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "ArrayOfsrclon_CombustibleVehiculoTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_CombustibleVehiculoTyUser[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_CombustibleVehiculoTyUser");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "ArrayOfsrclon_LineaVehiculoTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_LineaVehiculoTyUser[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_LineaVehiculoTyUser");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "ArrayOfsrclon_LocalidadTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_LocalidadTyUser[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_LocalidadTyUser");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "ArrayOfsrclon_MarcaVehiculoTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_MarcaVehiculoTyUser[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_MarcaVehiculoTyUser");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "ArrayOfsrclon_MunicipiosTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_MunicipiosTyUser[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_MunicipiosTyUser");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "ArrayOfsrclon_RecaudadoraTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_RecaudadoraTyUser[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_RecaudadoraTyUser");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "ArrayOfsrclon_ServicioVehiculoTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_ServicioVehiculoTyUser[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_ServicioVehiculoTyUser");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "ArrayOfsrclon_SublineaVehiculoTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_SublineaVehiculoTyUser[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_SublineaVehiculoTyUser");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "ArrayOfsrclon_TipoVehiculoTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_TipoVehiculoTyUser[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_TipoVehiculoTyUser");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "ArrayOfsrclon_UsoTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_UsoTyUser[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_UsoTyUser");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_AbreviaturasNt");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_AbreviaturasNt.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_AbreviaturaTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_AbreviaturaTyUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_ClasesNt");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_ClasesNt.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_ClaseTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_ClaseTyUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_ColoniasNt");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_ColoniasNt.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_ColoniaTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_ColoniaTyUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_CombustiblesVehiculosNt");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_CombustiblesVehiculosNt.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_CombustibleVehiculoTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_CombustibleVehiculoTyUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_LineasVehiculosNt");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_LineasVehiculosNt.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_LineaVehiculoTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_LineaVehiculoTyUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_LocalidadesNt");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_LocalidadesNt.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_LocalidadTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_LocalidadTyUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_MarcasVehiculosNt");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_MarcasVehiculosNt.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_MarcaVehiculoTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_MarcaVehiculoTyUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_MunicipiosNt");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_MunicipiosNt.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_MunicipiosTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_MunicipiosTyUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_RecaudadorasNt");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_RecaudadorasNt.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_RecaudadoraTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_RecaudadoraTyUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_ServiciosVehiculosNt");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_ServiciosVehiculosNt.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_ServicioVehiculoTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_ServicioVehiculoTyUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_SublineasVehiculosNt");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_SublineasVehiculosNt.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_SublineaVehiculoTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_SublineaVehiculoTyUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_TiposVehiculoNt");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_TiposVehiculoNt.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_TipoVehiculoTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_TipoVehiculoTyUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_UsosNt");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_UsosNt.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_UsoTyUser");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_UsoTyUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_abreviaDirecciones_Out");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_abreviaDirecciones_Out.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaClases_Out");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaClases_Out.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaColonias_Out");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaColonias_Out.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaCombustibles_Out");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaCombustibles_Out.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaLineas_Out");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLineas_Out.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaLocalidades_Out");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLocalidades_Out.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaMarcas_Out");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMarcas_Out.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaMunicipios_Out");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMunicipios_Out.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaRecaudadoras_Out");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaRecaudadoras_Out.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaServicios_Out");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaServicios_Out.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaSublineas_Out");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaSublineas_Out.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaTipos_Out");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaTipos_Out.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaUsos_Out");
            cachedSerQNames.add(qName);
            cls = srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaUsos_Out.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaRecaudadoras_Out buscaRecaudadoras(java.math.BigDecimal pCveRecaudadora) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaRecaudadoras"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pCveRecaudadora});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaRecaudadoras_Out) _resp;
            } catch (java.lang.Exception _exception) {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaRecaudadoras_Out) org.apache.axis.utils.JavaUtils.convert(_resp, srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaRecaudadoras_Out.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaCombustibles_Out buscaCombustibles(java.math.BigDecimal pCveCombustible) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaCombustibles"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pCveCombustible});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaCombustibles_Out) _resp;
            } catch (java.lang.Exception _exception) {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaCombustibles_Out) org.apache.axis.utils.JavaUtils.convert(_resp, srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaCombustibles_Out.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaSublineas_Out buscaSublineas(java.lang.String pCveMarca, java.lang.String pCveLinea, java.lang.String pCveSublinea, java.math.BigDecimal pCveModelo, java.lang.String pCveOrigen, java.lang.String pCveClase, java.math.BigDecimal pCveTipo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaSublineas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pCveMarca, pCveLinea, pCveSublinea, pCveModelo, pCveOrigen, pCveClase, pCveTipo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaSublineas_Out) _resp;
            } catch (java.lang.Exception _exception) {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaSublineas_Out) org.apache.axis.utils.JavaUtils.convert(_resp, srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaSublineas_Out.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLineas_Out buscaLineas(java.lang.String pCveMarca, java.lang.String pCveLinea, java.math.BigDecimal pCveModelo, java.lang.String pCveOrigen, java.lang.String pCveClase) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaLineas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pCveMarca, pCveLinea, pCveModelo, pCveOrigen, pCveClase});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLineas_Out) _resp;
            } catch (java.lang.Exception _exception) {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLineas_Out) org.apache.axis.utils.JavaUtils.convert(_resp, srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLineas_Out.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaColonias_Out buscaColonias(java.math.BigDecimal pCveMunicipio, java.math.BigDecimal pCveLocalidad, java.math.BigDecimal pCveColonia) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaColonias"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pCveMunicipio, pCveLocalidad, pCveColonia});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaColonias_Out) _resp;
            } catch (java.lang.Exception _exception) {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaColonias_Out) org.apache.axis.utils.JavaUtils.convert(_resp, srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaColonias_Out.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_abreviaDirecciones_Out abreviaDirecciones(java.lang.String pCveAbrev) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("WsOrdenamiento", "abreviaDirecciones"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pCveAbrev});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_abreviaDirecciones_Out) _resp;
            } catch (java.lang.Exception _exception) {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_abreviaDirecciones_Out) org.apache.axis.utils.JavaUtils.convert(_resp, srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_abreviaDirecciones_Out.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLocalidades_Out buscaLocalidades(java.math.BigDecimal pCveMunicipio, java.math.BigDecimal pCveLocalidad) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaLocalidades"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pCveMunicipio, pCveLocalidad});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLocalidades_Out) _resp;
            } catch (java.lang.Exception _exception) {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLocalidades_Out) org.apache.axis.utils.JavaUtils.convert(_resp, srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaLocalidades_Out.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaServicios_Out buscaServicios(java.math.BigDecimal pCveServicio) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaServicios"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pCveServicio});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaServicios_Out) _resp;
            } catch (java.lang.Exception _exception) {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaServicios_Out) org.apache.axis.utils.JavaUtils.convert(_resp, srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaServicios_Out.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaUsos_Out buscaUsos(java.math.BigDecimal pCveUso) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaUsos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pCveUso});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaUsos_Out) _resp;
            } catch (java.lang.Exception _exception) {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaUsos_Out) org.apache.axis.utils.JavaUtils.convert(_resp, srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaUsos_Out.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaTipos_Out buscaTipos(java.lang.String pCveClase, java.math.BigDecimal pCveTipo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaTipos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pCveClase, pCveTipo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaTipos_Out) _resp;
            } catch (java.lang.Exception _exception) {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaTipos_Out) org.apache.axis.utils.JavaUtils.convert(_resp, srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaTipos_Out.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMunicipios_Out buscaMunicipios(java.math.BigDecimal pCveMunicipio) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaMunicipios"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pCveMunicipio});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMunicipios_Out) _resp;
            } catch (java.lang.Exception _exception) {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMunicipios_Out) org.apache.axis.utils.JavaUtils.convert(_resp, srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMunicipios_Out.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMarcas_Out buscaMarcas(java.lang.String pCveMarca) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaMarcas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pCveMarca});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMarcas_Out) _resp;
            } catch (java.lang.Exception _exception) {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMarcas_Out) org.apache.axis.utils.JavaUtils.convert(_resp, srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaMarcas_Out.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaClases_Out buscaClases(java.lang.String pCveClase) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("WsOrdenamiento", "buscaClases"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pCveClase});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaClases_Out) _resp;
            } catch (java.lang.Exception _exception) {
                return (srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaClases_Out) org.apache.axis.utils.JavaUtils.convert(_resp, srclon.WsOrdenamiento_xsd.Srclon_WsOrdenamientoImpl_buscaClases_Out.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
