package neology.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import neology.hibernate.sesion.HibernateSessionFactory;

/**
 * @author Osvaldo
 *
 */
public class StringUtil {
	
	private static final Log log = LogFactory.getLog(StringUtil.class);
	
	/**
	 * primeraMayuscula cambia la primera letra en mayuscula
	 * @param String in
	 * @return String 
	 */
	
	public static String primeraMayuscula(String in) {
        if (in == null || in.length() == 0) {
            return in;
        //
        }
        boolean mayuscula = true;
        char[] data = in.toCharArray();
        for (int i = 0; i < data.length; i++) {
            if (data[i] == ' ' || Character.isWhitespace(data[i])) {
                mayuscula = true;
            } else if (mayuscula) {
                data[i] = Character.toUpperCase(data[i]);
               mayuscula = false;
            } else {
                data[i] = Character.toLowerCase(data[i]);
            }
        }
        return new String(data);
    }
	
	
}
