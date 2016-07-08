package neology.modelo.negocio.daos;

import java.util.ArrayList;

import java.util.Iterator;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.modelo.dto.EstadoCatalogo;
import neology.modelo.dto.Usuario;
import neology.util.FechaUtil;
import neology.util.Utilidades;

import org.apache.commons.logging.Log;
import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

public class EstadoCatalogoDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(EstadoCatalogoDAO.class);
    public static final String DESCRIPCION = "descripcion";
    public EstadoCatalogoDAO() {
    }

    public EstadoCatalogo buscarPorId(Integer estado) {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO buscarPorId - INICIO***"); 
    	
    	Session session = null;
        Transaction tx=null;
        EstadoCatalogo estadoCatalogo = null;
        try {
            session = this.getSession();
            estadoCatalogo = (EstadoCatalogo)session.load(EstadoCatalogo.class, estado);
            tx = session.beginTransaction();
            tx.commit();
        } catch (HibernateException re) {
        	loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Estado del Catalogo por Id ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();	
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Estado del Catalogo por Id ",e1);
                    loggerInfoDAO.removeAllAppenders();
        			loggerErrorDAO.removeAllAppenders();	
                }
        } finally {
            try {
                if (session != null)
                    session.close();
            } catch (HibernateException e1) {
            	loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Estado del Catalogo por Id ",e1);
                e1.printStackTrace();
                loggerInfoDAO.removeAllAppenders();
    			loggerErrorDAO.removeAllAppenders();	
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EstadoCatalogoDAO buscarPorId - FIN***"); 
        loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();	
        return estadoCatalogo;
    }
    
    public List obtenerEstados(boolean obtenerEliminado) {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EstadoCatalogoDAO obtenerEstados - INICIO***"); 
		Query query;
		Transaction tx = null;
		Session session = null;
		List estados = new ArrayList();
		String condicion = "";
		if (!obtenerEliminado)
			condicion = "where estado in(" + EstadoCatalogo.ACTIVO + ","
					+ EstadoCatalogo.INACTIVO + ")";
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from EstadoCatalogo " + condicion
					+ " order by " + DESCRIPCION;
			query = session.createQuery(consulta);
			estados = query.list();
			tx.commit();
		} catch (HibernateException e1) {
			e1.printStackTrace();
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Lista de Estados del Catalogo ",e1);
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();	
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException err) {
					err.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Lista de Estados del Catalogo ",err);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();	
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException err) {
				err.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Lista de Estados del Catalogo ",err);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();	
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EstadoCatalogoDAO obtenerEstados - FIN***"); 
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();	
		return estados;
	}
    
    
    public List obtenerEstadosPantalla(Usuario us,boolean obtenerEliminado) {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EstadoCatalogoDAO obtenerEstadosPantalla - INICIO***"); 
		Query query;
		Transaction tx = null;
		Session session = null;
		List estados = new ArrayList();
		String condicion = "";

		if (!obtenerEliminado)
			condicion = "where estado in(" + EstadoCatalogo.ACTIVO + ","
					+ EstadoCatalogo.INACTIVO + ")";
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = "";	
			if(us.getIdUsuario()!=1){		
				consulta = " from EstadoCatalogo " + condicion +" where estado !="+EstadoCatalogo.ELIMINADO
						+ " order by " + DESCRIPCION;			
			}else{				
				consulta = " from EstadoCatalogo where estado="+0
						+ " order by " + DESCRIPCION;				
			}	
			query = session.createQuery(consulta);
			estados = query.list();
			tx.commit();
		} catch (HibernateException e1) {
			e1.printStackTrace();
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Lista de Estados del Catalogo ",e1);
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();	
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException err) {
					err.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Lista de Estados del Catalogo ",err);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();	
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException err) {
				err.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Lista de Estados del Catalogo ",err);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();	
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EstadoCatalogoDAO obtenerEstadosPantalla - FIN***"); 
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();	
		return estados;
	}
   
}
