package neology.modelo.negocio.daos;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;

public class MenuDAO extends BaseHibernateDAO {

	public MenuDAO() {
	}

	/**
	 * Obtiene toda la lista de Menus de la base de datos
	 * 
	 * @return List
	 */
	public List buscarMenus() {

		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO
				.info(FechaUtil.getHoraActual() + "_MenuDAO buscarMenus - INICIO***");
		Query query;
		Transaction tx = null;
		Session session = null;
		List perfiles = null;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from Menu order by idMenu";
			query = session.createQuery(consulta);
			perfiles = query.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error("Fallo al obtener lista de Menus  " ,re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error("Fallo al obtener lista de Menus  " ,e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()
						+ "_Fallo al obtener lista de Menus  " ,e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO
				.info(FechaUtil.getHoraActual() + "_MenuDAO buscarMenus - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return perfiles;
	}
}
