/**
 * Srclon_LineaVehiculoTyUser.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package srclon.WsOrdenamiento_xsd;

public class Srclon_LineaVehiculoTyUser  implements java.io.Serializable {
    private java.lang.String cveMarca;

    private java.lang.String cveLinea;

    private java.math.BigDecimal modelo;

    private java.lang.String origen;

    private java.lang.String claseVehiculo;

    private java.lang.String descripcion;

    public Srclon_LineaVehiculoTyUser() {
    }

    public Srclon_LineaVehiculoTyUser(
           java.lang.String cveMarca,
           java.lang.String cveLinea,
           java.math.BigDecimal modelo,
           java.lang.String origen,
           java.lang.String claseVehiculo,
           java.lang.String descripcion) {
           this.cveMarca = cveMarca;
           this.cveLinea = cveLinea;
           this.modelo = modelo;
           this.origen = origen;
           this.claseVehiculo = claseVehiculo;
           this.descripcion = descripcion;
    }


    /**
     * Gets the cveMarca value for this Srclon_LineaVehiculoTyUser.
     * 
     * @return cveMarca
     */
    public java.lang.String getCveMarca() {
        return cveMarca;
    }


    /**
     * Sets the cveMarca value for this Srclon_LineaVehiculoTyUser.
     * 
     * @param cveMarca
     */
    public void setCveMarca(java.lang.String cveMarca) {
        this.cveMarca = cveMarca;
    }


    /**
     * Gets the cveLinea value for this Srclon_LineaVehiculoTyUser.
     * 
     * @return cveLinea
     */
    public java.lang.String getCveLinea() {
        return cveLinea;
    }


    /**
     * Sets the cveLinea value for this Srclon_LineaVehiculoTyUser.
     * 
     * @param cveLinea
     */
    public void setCveLinea(java.lang.String cveLinea) {
        this.cveLinea = cveLinea;
    }


    /**
     * Gets the modelo value for this Srclon_LineaVehiculoTyUser.
     * 
     * @return modelo
     */
    public java.math.BigDecimal getModelo() {
        return modelo;
    }


    /**
     * Sets the modelo value for this Srclon_LineaVehiculoTyUser.
     * 
     * @param modelo
     */
    public void setModelo(java.math.BigDecimal modelo) {
        this.modelo = modelo;
    }


    /**
     * Gets the origen value for this Srclon_LineaVehiculoTyUser.
     * 
     * @return origen
     */
    public java.lang.String getOrigen() {
        return origen;
    }


    /**
     * Sets the origen value for this Srclon_LineaVehiculoTyUser.
     * 
     * @param origen
     */
    public void setOrigen(java.lang.String origen) {
        this.origen = origen;
    }


    /**
     * Gets the claseVehiculo value for this Srclon_LineaVehiculoTyUser.
     * 
     * @return claseVehiculo
     */
    public java.lang.String getClaseVehiculo() {
        return claseVehiculo;
    }


    /**
     * Sets the claseVehiculo value for this Srclon_LineaVehiculoTyUser.
     * 
     * @param claseVehiculo
     */
    public void setClaseVehiculo(java.lang.String claseVehiculo) {
        this.claseVehiculo = claseVehiculo;
    }


    /**
     * Gets the descripcion value for this Srclon_LineaVehiculoTyUser.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this Srclon_LineaVehiculoTyUser.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Srclon_LineaVehiculoTyUser)) return false;
        Srclon_LineaVehiculoTyUser other = (Srclon_LineaVehiculoTyUser) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cveMarca==null && other.getCveMarca()==null) || 
             (this.cveMarca!=null &&
              this.cveMarca.equals(other.getCveMarca()))) &&
            ((this.cveLinea==null && other.getCveLinea()==null) || 
             (this.cveLinea!=null &&
              this.cveLinea.equals(other.getCveLinea()))) &&
            ((this.modelo==null && other.getModelo()==null) || 
             (this.modelo!=null &&
              this.modelo.equals(other.getModelo()))) &&
            ((this.origen==null && other.getOrigen()==null) || 
             (this.origen!=null &&
              this.origen.equals(other.getOrigen()))) &&
            ((this.claseVehiculo==null && other.getClaseVehiculo()==null) || 
             (this.claseVehiculo!=null &&
              this.claseVehiculo.equals(other.getClaseVehiculo()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion())));
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
        if (getCveMarca() != null) {
            _hashCode += getCveMarca().hashCode();
        }
        if (getCveLinea() != null) {
            _hashCode += getCveLinea().hashCode();
        }
        if (getModelo() != null) {
            _hashCode += getModelo().hashCode();
        }
        if (getOrigen() != null) {
            _hashCode += getOrigen().hashCode();
        }
        if (getClaseVehiculo() != null) {
            _hashCode += getClaseVehiculo().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Srclon_LineaVehiculoTyUser.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_LineaVehiculoTyUser"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cveMarca");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cveMarca"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cveLinea");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cveLinea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "modelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("origen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "origen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("claseVehiculo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "claseVehiculo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcion"));
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
