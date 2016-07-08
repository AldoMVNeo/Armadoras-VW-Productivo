package neology.modelo.negocio.daos;

import java.sql.PreparedStatement;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;



public class ProcedenciaDAO extends BaseHibernateDAO{
    private static final Log log = LogFactory.getLog(ProcedenciaDAO.class);
    public ProcedenciaDAO() {
    }
               
        /**
         Obtiene una lista de los Modulos
         */
    public List obtenerProcedencia() {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_ProcedenciaDAO obtenerProcedencia - INICIO***");
        Query query;
        Transaction tx=null;
        Session session=null;
        List allParametros = new ArrayList();
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            String consulta=" from Procedencia " ;
            query = session.createQuery(consulta);
            allParametros = query.list();
            tx.commit();
        } catch (HibernateException re) {
            loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de los Procedencia ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de los Procedencia ",e1);
                    loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
                }
        } finally {
            try {
                if (session != null)
                    session.close();
            } catch (HibernateException e1) {
                e1.printStackTrace();
                loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de los Procedencia ",e1);
                loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_ProcedenciaDAO obtenerProcedencia - FIN***");
        loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
        return allParametros;
    }
        
   
        
   
    
  
}
