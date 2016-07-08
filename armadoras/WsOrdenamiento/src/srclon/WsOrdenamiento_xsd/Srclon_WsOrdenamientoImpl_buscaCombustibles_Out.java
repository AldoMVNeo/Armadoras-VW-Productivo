/**
 * Srclon_WsOrdenamientoImpl_buscaCombustibles_Out.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package srclon.WsOrdenamiento_xsd;

public class Srclon_WsOrdenamientoImpl_buscaCombustibles_Out  implements java.io.Serializable {
    private srclon.WsOrdenamiento_xsd.Srclon_CombustiblesVehiculosNt pcombustiblesOut;

    private java.math.BigDecimal presultadoOut;

    private java.lang.String pmensajeOut;

    public Srclon_WsOrdenamientoImpl_buscaCombustibles_Out() {
    }

    public Srclon_WsOrdenamientoImpl_buscaCombustibles_Out(
           srclon.WsOrdenamiento_xsd.Srclon_CombustiblesVehiculosNt pcombustiblesOut,
           java.math.BigDecimal presultadoOut,
           java.lang.String pmensajeOut) {
           this.pcombustiblesOut = pcombustiblesOut;
           this.presultadoOut = presultadoOut;
           this.pmensajeOut = pmensajeOut;
    }


    /**
     * Gets the pcombustiblesOut value for this Srclon_WsOrdenamientoImpl_buscaCombustibles_Out.
     * 
     * @return pcombustiblesOut
     */
    public srclon.WsOrdenamiento_xsd.Srclon_CombustiblesVehiculosNt getPcombustiblesOut() {
        return pcombustiblesOut;
    }


    /**
     * Sets the pcombustiblesOut value for this Srclon_WsOrdenamientoImpl_buscaCombustibles_Out.
     * 
     * @param pcombustiblesOut
     */
    public void setPcombustiblesOut(srclon.WsOrdenamiento_xsd.Srclon_CombustiblesVehiculosNt pcombustiblesOut) {
        this.pcombustiblesOut = pcombustiblesOut;
    }


    /**
     * Gets the presultadoOut value for this Srclon_WsOrdenamientoImpl_buscaCombustibles_Out.
     * 
     * @return presultadoOut
     */
    public java.math.BigDecimal getPresultadoOut() {
        return presultadoOut;
    }


    /**
     * Sets the presultadoOut value for this Srclon_WsOrdenamientoImpl_buscaCombustibles_Out.
     * 
     * @param presultadoOut
     */
    public void setPresultadoOut(java.math.BigDecimal presultadoOut) {
        this.presultadoOut = presultadoOut;
    }


    /**
     * Gets the pmensajeOut value for this Srclon_WsOrdenamientoImpl_buscaCombustibles_Out.
     * 
     * @return pmensajeOut
     */
    public java.lang.String getPmensajeOut() {
        return pmensajeOut;
    }


    /**
     * Sets the pmensajeOut value for this Srclon_WsOrdenamientoImpl_buscaCombustibles_Out.
     * 
     * @param pmensajeOut
     */
    public void setPmensajeOut(java.lang.String pmensajeOut) {
        this.pmensajeOut = pmensajeOut;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Srclon_WsOrdenamientoImpl_buscaCombustibles_Out)) return false;
        Srclon_WsOrdenamientoImpl_buscaCombustibles_Out other = (Srclon_WsOrdenamientoImpl_buscaCombustibles_Out) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pcombustiblesOut==null && other.getPcombustiblesOut()==null) || 
             (this.pcombustiblesOut!=null &&
              this.pcombustiblesOut.equals(other.getPcombustiblesOut()))) &&
            ((this.presultadoOut==null && other.getPresultadoOut()==null) || 
             (this.presultadoOut!=null &&
              this.presultadoOut.equals(other.getPresultadoOut()))) &&
            ((this.pmensajeOut==null && other.getPmensajeOut()==null) || 
             (this.pmensajeOut!=null &&
              this.pmensajeOut.equals(other.getPmensajeOut())));
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
        if (getPcombustiblesOut() != null) {
            _hashCode += getPcombustiblesOut().hashCode();
        }
        if (getPresultadoOut() != null) {
            _hashCode += getPresultadoOut().hashCode();
        }
        if (getPmensajeOut() != null) {
            _hashCode += getPmensajeOut().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Srclon_WsOrdenamientoImpl_buscaCombustibles_Out.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_WsOrdenamientoImpl_buscaCombustibles_Out"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pcombustiblesOut");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pcombustiblesOut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_CombustiblesVehiculosNt"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("presultadoOut");
        elemField.setXmlName(new javax.xml.namespace.QName("", "presultadoOut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pmensajeOut");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pmensajeOut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
