/**
 * Srclon_LineasVehiculosNt.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package srclon.WsOrdenamiento_xsd;

public class Srclon_LineasVehiculosNt  implements java.io.Serializable {
    private srclon.WsOrdenamiento_xsd.Srclon_LineaVehiculoTyUser[] array;

    public Srclon_LineasVehiculosNt() {
    }

    public Srclon_LineasVehiculosNt(
           srclon.WsOrdenamiento_xsd.Srclon_LineaVehiculoTyUser[] array) {
           this.array = array;
    }


    /**
     * Gets the array value for this Srclon_LineasVehiculosNt.
     * 
     * @return array
     */
    public srclon.WsOrdenamiento_xsd.Srclon_LineaVehiculoTyUser[] getArray() {
        return array;
    }


    /**
     * Sets the array value for this Srclon_LineasVehiculosNt.
     * 
     * @param array
     */
    public void setArray(srclon.WsOrdenamiento_xsd.Srclon_LineaVehiculoTyUser[] array) {
        this.array = array;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Srclon_LineasVehiculosNt)) return false;
        Srclon_LineasVehiculosNt other = (Srclon_LineasVehiculosNt) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.array==null && other.getArray()==null) || 
             (this.array!=null &&
              java.util.Arrays.equals(this.array, other.getArray())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getArray() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArray());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArray(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Srclon_LineasVehiculosNt.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_LineasVehiculosNt"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("array");
        elemField.setXmlName(new javax.xml.namespace.QName("", "array"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_LineaVehiculoTyUser"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
