package neology.modelo.negocio.daos;

import neology.hibernate.sesion.HibernateSessionFactory;

import neology.modelo.negocio.interfaces.IBaseHibernateDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;


public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	private static Log log = LogFactory.getLog(BaseHibernateDAO.class);
	
	public Session getSession() {            
           return HibernateSessionFactory.getSession();          
		 
	}
	
	public Session getSessionResp() {            
        return HibernateSessionFactory.getSessionResp();          
		 
	}
	
}
