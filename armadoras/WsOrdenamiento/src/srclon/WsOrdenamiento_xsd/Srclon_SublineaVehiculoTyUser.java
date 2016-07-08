/**
 * Srclon_SublineaVehiculoTyUser.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package srclon.WsOrdenamiento_xsd;

public class Srclon_SublineaVehiculoTyUser  implements java.io.Serializable {
    private java.lang.String cveMarca;

    private java.lang.String cveLinea;

    private java.lang.String cveSublinea;

    private java.math.BigDecimal modelo;

    private java.lang.String origen;

    private java.lang.String claseVehiculo;

    private java.math.BigDecimal tipoVehiculo;

    private java.lang.String descripcion;

    private java.math.BigDecimal puertas;

    public Srclon_SublineaVehiculoTyUser() {
    }

    public Srclon_SublineaVehiculoTyUser(
           java.lang.String cveMarca,
           java.lang.String cveLinea,
           java.lang.String cveSublinea,
           java.math.BigDecimal modelo,
           java.lang.String origen,
           java.lang.String claseVehiculo,
           java.math.BigDecimal tipoVehiculo,
           java.lang.String descripcion,
           java.math.BigDecimal puertas) {
           this.cveMarca = cveMarca;
           this.cveLinea = cveLinea;
           this.cveSublinea = cveSublinea;
           this.modelo = modelo;
           this.origen = origen;
           this.claseVehiculo = claseVehiculo;
           this.tipoVehiculo = tipoVehiculo;
           this.descripcion = descripcion;
           this.puertas = puertas;
    }


    /**
     * Gets the cveMarca value for this Srclon_SublineaVehiculoTyUser.
     * 
     * @return cveMarca
     */
    public java.lang.String getCveMarca() {
        return cveMarca;
    }


    /**
     * Sets the cveMarca value for this Srclon_SublineaVehiculoTyUser.
     * 
     * @param cveMarca
     */
    public void setCveMarca(java.lang.String cveMarca) {
        this.cveMarca = cveMarca;
    }


    /**
     * Gets the cveLinea value for this Srclon_SublineaVehiculoTyUser.
     * 
     * @return cveLinea
     */
    public java.lang.String getCveLinea() {
        return cveLinea;
    }


    /**
     * Sets the cveLinea value for this Srclon_SublineaVehiculoTyUser.
     * 
     * @param cveLinea
     */
    public void setCveLinea(java.lang.String cveLinea) {
        this.cveLinea = cveLinea;
    }


    /**
     * Gets the cveSublinea value for this Srclon_SublineaVehiculoTyUser.
     * 
     * @return cveSublinea
     */
    public java.lang.String getCveSublinea() {
        return cveSublinea;
    }


    /**
     * Sets the cveSublinea value for this Srclon_SublineaVehiculoTyUser.
     * 
     * @param cveSublinea
     */
    public void setCveSublinea(java.lang.String cveSublinea) {
        this.cveSublinea = cveSublinea;
    }


    /**
     * Gets the modelo value for this Srclon_SublineaVehiculoTyUser.
     * 
     * @return modelo
     */
    public java.math.BigDecimal getModelo() {
        return modelo;
    }


    /**
     * Sets the modelo value for this Srclon_SublineaVehiculoTyUser.
     * 
     * @param modelo
     */
    public void setModelo(java.math.BigDecimal modelo) {
        this.modelo = modelo;
    }


    /**
     * Gets the origen value for this Srclon_SublineaVehiculoTyUser.
     * 
     * @return origen
     */
    public java.lang.String getOrigen() {
        return origen;
    }


    /**
     * Sets the origen value for this Srclon_SublineaVehiculoTyUser.
     * 
     * @param origen
     */
    public void setOrigen(java.lang.String origen) {
        this.origen = origen;
    }


    /**
     * Gets the claseVehiculo value for this Srclon_SublineaVehiculoTyUser.
     * 
     * @return claseVehiculo
     */
    public java.lang.String getClaseVehiculo() {
        return claseVehiculo;
    }


    /**
     * Sets the claseVehiculo value for this Srclon_SublineaVehiculoTyUser.
     * 
     * @param claseVehiculo
     */
    public void setClaseVehiculo(java.lang.String claseVehiculo) {
        this.claseVehiculo = claseVehiculo;
    }


    /**
     * Gets the tipoVehiculo value for this Srclon_SublineaVehiculoTyUser.
     * 
     * @return tipoVehiculo
     */
    public java.math.BigDecimal getTipoVehiculo() {
        return tipoVehiculo;
    }


    /**
     * Sets the tipoVehiculo value for this Srclon_SublineaVehiculoTyUser.
     * 
     * @param tipoVehiculo
     */
    public void setTipoVehiculo(java.math.BigDecimal tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }


    /**
     * Gets the descripcion value for this Srclon_SublineaVehiculoTyUser.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this Srclon_SublineaVehiculoTyUser.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the puertas value for this Srclon_SublineaVehiculoTyUser.
     * 
     * @return puertas
     */
    public java.math.BigDecimal getPuertas() {
        return puertas;
    }


    /**
     * Sets the puertas value for this Srclon_SublineaVehiculoTyUser.
     * 
     * @param puertas
     */
    public void setPuertas(java.math.BigDecimal puertas) {
        this.puertas = puertas;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Srclon_SublineaVehiculoTyUser)) return false;
        Srclon_SublineaVehiculoTyUser other = (Srclon_SublineaVehiculoTyUser) obj;
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
            ((this.cveSublinea==null && other.getCveSublinea()==null) || 
             (this.cveSublinea!=null &&
              this.cveSublinea.equals(other.getCveSublinea()))) &&
            ((this.modelo==null && other.getModelo()==null) || 
             (this.modelo!=null &&
              this.modelo.equals(other.getModelo()))) &&
            ((this.origen==null && other.getOrigen()==null) || 
             (this.origen!=null &&
              this.origen.equals(other.getOrigen()))) &&
            ((this.claseVehiculo==null && other.getClaseVehiculo()==null) || 
             (this.claseVehiculo!=null &&
              this.claseVehiculo.equals(other.getClaseVehiculo()))) &&
            ((this.tipoVehiculo==null && other.getTipoVehiculo()==null) || 
             (this.tipoVehiculo!=null &&
              this.tipoVehiculo.equals(other.getTipoVehiculo()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.puertas==null && other.getPuertas()==null) || 
             (this.puertas!=null &&
              this.puertas.equals(other.getPuertas())));
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
        if (getCveSublinea() != null) {
            _hashCode += getCveSublinea().hashCode();
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
        if (getTipoVehiculo() != null) {
            _hashCode += getTipoVehiculo().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getPuertas() != null) {
            _hashCode += getPuertas().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Srclon_SublineaVehiculoTyUser.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://srclon/WsOrdenamiento.xsd", "srclon_SublineaVehiculoTyUser"));
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
        elemField.setFieldName("cveSublinea");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cveSublinea"));
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
        elemField.setFieldName("tipoVehiculo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoVehiculo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("puertas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "puertas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
