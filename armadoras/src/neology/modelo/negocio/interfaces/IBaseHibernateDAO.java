package neology.modelo.negocio.interfaces;

import org.hibernate.Session;


public interface IBaseHibernateDAO {
	public Session getSession();
	public Session getSessionResp();
}
