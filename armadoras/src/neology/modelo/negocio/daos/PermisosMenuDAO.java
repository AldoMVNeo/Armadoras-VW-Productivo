package neology.modelo.negocio.daos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;

public class PermisosMenuDAO extends BaseHibernateDAO {

	public PermisosMenuDAO() {
	}

	/**
	 * Actualizacion masiva de los PermisosMenu pertenecientes a un perfil para
	 * cambiar a activo o inactivo
	 * 
	 * @param idPerfil
	 * @param seleccionados
	 * @param activo
	 * @return boolean
	 */
	public boolean actualizarActivo(Long idPerfil, String seleccionados,
			boolean activo) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_PermisosMenuDAO actualizarActivo - INICIO***");
		Session session = null;
		Transaction tx = null;
		String consulta = "";
		String act = activo == true ? "t" : "f";
		String not = activo == false ? "not" : "";
		String condicion = " and id_menu " + not + " in(" + seleccionados + ")";
		if (!activo && seleccionados.length() == 0)
			condicion = "";
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			consulta = "update PermisoMenu set activo='" + act
					+ "' where id_perfil=" + idPerfil + condicion;
			int query = session.createQuery(consulta).executeUpdate();
			tx.commit();
		} catch (Exception e1) {
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo actualizarActivo ",e1);
			e1.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e2) {
					e2.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo actualizarActivo", e2);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
					return false;
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {
				
				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo actualizarActivo ",e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
				return false;
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_PermisosMenuDAO actualizarActivo - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return true;
	}
}
