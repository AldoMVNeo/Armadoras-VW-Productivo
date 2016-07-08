package neology.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import neology.hibernate.sesion.HibernateSessionFactory;

public class FechaUtil {
    private static final String LUNES = "Lunes";
    private static final String MARTES = "Martes";
    private static final String MIERCOLES = "Miércoles";
    private static final String JUEVES = "Jueves";
    private static final String VIERNES = "Viernes";
    private static final String SABADO = "Sábado";
    private static final String DOMINGO = "Domingo";

    public FechaUtil() {
    }

//Obtiene nombre del dia de la Semana
    public static String getDescripcionDia(int mes) {

        String desMes = "";
        switch (mes) {
        case 0:
            desMes = DOMINGO;
            break;
        case 1:
            desMes = LUNES;
            break;
        case 2:
            desMes = MARTES;
            break;
        case 3:
            desMes = MIERCOLES;
            break;
        case 4:
            desMes = JUEVES;
            break;
        case 5:
            desMes = VIERNES;
            break;
        case 6:
            desMes = SABADO;
            break;
        case 7:
            desMes = DOMINGO;
            break;
        default:
            break;
        }
        return desMes;
    }

//Convierte una fecha y hora en cadena con un formato Dia de la Semana, dd/MM/yyyy  HH:mm:ss
    public static String getFechaLarga(DateTime fecha)throws Exception {
        return getDescripcionDia(fecha.getDayOfWeek()) + ", " + 
            fecha.toString("dd/MM/yyyy HH:mm:ss");
        
    }
    
//  Obtiene la fecha actual en formato, dd/MM/yyyy
    public static String getFechaActual()throws Exception {
        return new DateTime().toString("dd/MM/yyyy");
        
    }    
    
//  Obtiene la fecha actual en formato, dd/MM/yyyy
    public static String getHoraActual(){
        return new DateTime().toString("HH:mm:ss")+"_"; 
    }  
    
    //Obtiene la fecha y hora actual
    public static DateTime getFechaYHoraActual(){    	  	
        return  new DateTime();
    }
    
    //Obtiene la fecha y hora actual con formato
    public static String getFechaYHoraActual(String formato){    	  	
        return  new DateTime().toString(formato);
    }
    
    public static String getFechaCorta(DateTime fecha)throws Exception {
        return fecha.toString("dd/MM/yyyy");
        
    }
    
    public static String getYear() {
        return new DateTime().toString("yyyy");
        
    }
    
    public static String getDateLog() {
        return new DateTime().toString("yyMMdd");
        
    }
}
