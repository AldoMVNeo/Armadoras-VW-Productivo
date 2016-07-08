package neology.hibernate.tipos;

import org.hibernate.type.CharBooleanType;

public class TipoBoolean extends CharBooleanType {
    protected final String getTrueString() {
        return "t";
    }
    protected final String getFalseString() {
        return "f";    
    }
}
