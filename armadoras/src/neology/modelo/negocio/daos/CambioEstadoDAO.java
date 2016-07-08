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

import java.util.List;



public class CambioEstadoDAO extends BaseHibernateDAO{
	    
    public CambioEstadoDAO() {
    }
    
    public List BuscarEstados(String de)
    {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_CambioEstadoDAO BuscarEstados - INICIO***"); 
    	Session session = null;
    	Transaction tx = null;
    	List estados = null;
    	try {
    		session = this.getSession();
    		tx = session.beginTransaction();
    		String queryString = 
    			"select e from Estado as e, CambioEstado as ce "
    			+ " where ce.de = " + de
    			+ " and ce.a = e.estado " 
    			+ " order by e.descripcion" ;
    		Query queryObject = session.createQuery(queryString);    		
    		estados = queryObject.list();
    		tx.commit();
    	} catch (HibernateException re) {
    		
    		loggerErrorDAO.error("_Fallo al buscar los cambios de estado permitidos",re);
    		loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();	
    		re.printStackTrace();
    		if (tx != null)
    			try {
    				tx.rollback();
    			} catch (HibernateException e1) {
    				e1.printStackTrace();
    			 	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_Fallo al buscar los cambios de estado permitidos ",e1);
    			 	loggerInfoDAO.removeAllAppenders();
    				loggerErrorDAO.removeAllAppenders();	
    			}
    	} finally {
    		try {
    			if (session != null)
    				session.close();
    		} catch (HibernateException e1) {    			
    			e1.printStackTrace();
    			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar los cambios de estado permitidos ",e1);
    			loggerInfoDAO.removeAllAppenders();
    			loggerErrorDAO.removeAllAppenders();	
    		}
    	}
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_CambioEstadoDAO BuscarEstados - FIN***"); 
    	loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
    	return estados;
    }
    
    public List BuscarCambioEstadosTodos()
    {
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_CambioEstadoDAO BuscarCambioEstadosTodos - INICIO***"); 
    	Session session = null;
    	Transaction tx = null;
    	List estados = null;
    	try {
    		session = this.getSession();
    		tx = session.beginTransaction();
    		String queryString = 
    			"from CambioEstado as ce " 
    			+ " order by ce.de" ;
    		Query queryObject = session.createQuery(queryString);    		
    		estados = queryObject.list();
    		tx.commit();
    	} catch (HibernateException re) {
    		loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar los cambios de estado permitidos ",re);
    		re.printStackTrace();
    		loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			
    		if (tx != null)
    			try {
    				tx.rollback();
    			} catch (HibernateException e1) {
    				e1.printStackTrace();
    				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar los cambios de estado permitidos ",e1);
    				loggerInfoDAO.removeAllAppenders();
        			loggerErrorDAO.removeAllAppenders();
    			}
    	} finally {
    		try {
    			if (session != null)
    				session.close();
    		} catch (HibernateException e1) {    			
    			e1.printStackTrace();
    			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar los cambios de estado permitidos ",e1);
    			loggerInfoDAO.removeAllAppenders();
    			loggerErrorDAO.removeAllAppenders();
    		}
    	}
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_CambioEstadoDAO BuscarCambioEstadosTodos - FIN***"); 
    	loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
    	return estados;
    }
    
}
