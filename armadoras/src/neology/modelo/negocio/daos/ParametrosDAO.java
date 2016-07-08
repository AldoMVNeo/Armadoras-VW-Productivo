package neology.modelo.negocio.daos;

import java.sql.PreparedStatement;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import neology.modelo.dto.Parametros;
import neology.util.FechaUtil;
import neology.util.Utilidades;



public class ParametrosDAO extends BaseHibernateDAO{
    public ParametrosDAO() {
    }
    
    public long buscarPorModulo(String modulo){
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_ParametrosDAO buscarPorModulo - INICIO***");
        long respuesta= 0, horaOfna = 0;                
        String consulta = null;
        Session session = null;
        Transaction tx=null;
        Parametros parametros=null;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            consulta =  " Select par.valorTransferirMaximoOficina" +
                        " from Parametros as par  " +
                        " Where par.idParametro = 1 " +
                        " AND par.horaInicialOficina <= to_number(to_char(sysdate,'hh24')) " +
                        " AND par.horaFinalOficina >= to_number(to_char(sysdate,'hh24')) " ;
            List listParametros = session.createQuery(consulta).list();
            Iterator iter = listParametros.iterator();
            while (iter.hasNext())
            {
                horaOfna= (Long) iter.next();                                                
                break;
            }                                               
            tx.commit();
            if (modulo == "TRANSFERIR_FORMATOS" ) {
                session = this.getSession();
                parametros = (Parametros)session.load(Parametros.class, new Long(1));
                tx = session.beginTransaction();
                tx.commit();
                if (horaOfna > 0)                                     
                    respuesta = parametros.getValorTransferirMaximoOficina();
                else
                    respuesta = parametros.getValorTransferirMaximoFueraOficina();
            }                                                                       
            if (modulo == "CAMBIAR_ESTADO" ) {
                session = this.getSession();
                parametros = (Parametros)session.load(Parametros.class, new Long(1));
                tx = session.beginTransaction();
                tx.commit();
                if (horaOfna > 0)                                     
                    respuesta = parametros.getValorCambiarMaximoOficina();
                else
                    respuesta = parametros.getValorCambiarMaximoFueraOficina();
            }
            if (modulo == "DOTAR" ) {
                session = this.getSession();
                parametros = (Parametros)session.load(Parametros.class, new Long(1));
                tx = session.beginTransaction();
                tx.commit();
                if (horaOfna > 0)                                     
                    respuesta = parametros.getValorDotarMaximoOficina();
                else
                    respuesta = parametros.getValorDotarMaximoFueraOficina();
            }        
        } catch (HibernateException e1) {
            loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener la validacion por modulo ",e1);
            e1.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e2) {
                    e2.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener la validacion por modulo", e2);
                    loggerInfoDAO.removeAllAppenders();
        			loggerErrorDAO.removeAllAppenders();
                }
        } finally {
            try {
                if (session != null)
                    session.close();
            } catch (HibernateException e1) {
                e1.printStackTrace();
                loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener la validacion por modulo ",e1);
                loggerInfoDAO.removeAllAppenders();
    			loggerErrorDAO.removeAllAppenders();
            }
        }       
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_ParametrosDAO buscarPorModulo - FIN***");
        loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
        return respuesta;
    }
        
        /**
         Obtiene una lista de los Modulos
         */
    public List obtenerParametros() {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_ParametrosDAO obtenerParametros - INICIO***");
        Query query;
        Transaction tx=null;
        Session session=null;
        List allParametros = new ArrayList();
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            String consulta=" from Parametros order by parametro" ;
            query = session.createQuery(consulta);
            allParametros = query.list();
            tx.commit();
        } catch (HibernateException re) {
            loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de los Parametros ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de los Parametros ",e1);
                    loggerInfoDAO.removeAllAppenders();
    				loggerErrorDAO.removeAllAppenders();
                }
        } finally {
            try {
                if (session != null)
                    session.close();
            } catch (HibernateException e1) {
                e1.printStackTrace();
                loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de los Parametros ",e1);
                loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_ParametrosDAO obtenerParametros - FIN***");
        loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
        return allParametros;
    }
        
    public Parametros buscarPorId(Long idParametro) {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_ParametrosDAO buscarPorId - INICIO***");
        Session session = null;
        Transaction tx=null;
        Parametros parametros = null;
        try {
            session = this.getSession();
            parametros = (Parametros)session.load(Parametros.class, idParametro);
            tx = session.beginTransaction();
            tx.commit();                
        } catch (HibernateException re) {
            loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Parametro por Id ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Parametro por Id ",e1);
                    loggerInfoDAO.removeAllAppenders();
        			loggerErrorDAO.removeAllAppenders();
                }
        } finally {
            try {
                if (session != null)
                    session.close();
            } catch (HibernateException e1) {
                e1.printStackTrace();
                loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Parametro por Id ",e1);
                loggerInfoDAO.removeAllAppenders();
    			loggerErrorDAO.removeAllAppenders();
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_ParametrosDAO buscarPorId - FIN***");
        loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
        return parametros;
    }        
        
    public boolean guardar(Parametros idParametro) {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_ParametrosDAO guardar - INICIO***");
        Session session = null;
        Transaction tx = null;
        boolean exitoso;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            session.update(idParametro);
            tx.commit();
            exitoso = true;
        } catch (HibernateException re) {
            exitoso = false;
            loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar los parametros  ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    exitoso = false;
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar los parametros  ",e1);
                    loggerInfoDAO.removeAllAppenders();
        			loggerErrorDAO.removeAllAppenders();
                }
        } finally {
            try {
                if (session != null)
                    session.close();
            } catch (HibernateException e1) {
                exitoso = false;
                e1.printStackTrace();
                loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar los parametros  ",e1);
                loggerInfoDAO.removeAllAppenders();
    			loggerErrorDAO.removeAllAppenders();
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_ParametrosDAO guardar - FIN***");
        loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
        return exitoso;
    }
    
    
    public long prueba(String modulo){
        long respuesta= 0, horaOfna = 0;                
        String consulta = null;
        Session session = null;
        Transaction tx=null;
        Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
        //Parametros parametros=null;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            
// FUNCIONA            
/*            consulta = "select {par.*} from Parametros par";            
   List sad = session.createSQLQuery(consulta).addEntity("par",Parametros.class).list();
*/    
/*FUNCIONA
   consulta = "from Parametros as par where par.idParametro = ?";            
                 Query query = session.createQuery(consulta);
            query.setLong(0,1);
            List uno = query.list();
*/
            
            consulta ="update Parametros set valorTransferirMaximoOficina = 1";            
//            consulta ="update parametros set hora_inicial_ofna = 1";            
            
            int query = session.createQuery(consulta).executeUpdate();                          
            tx.commit();            
        } catch (Exception e1) {
            loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener la validacion por modulo ",e1);
            e1.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e2) {
                    e2.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener la validacion por modulo", e2);
                    loggerInfoDAO.removeAllAppenders();
        			loggerErrorDAO.removeAllAppenders();
                }
        } finally {
            try {
                if (session != null)
                    session.close();
            } catch (HibernateException e1) {
                e1.printStackTrace();
                loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener la validacion por modulo ",e1);
                loggerInfoDAO.removeAllAppenders();
    			loggerErrorDAO.removeAllAppenders();
            }
        }     
        loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
        return respuesta;
    }
}
