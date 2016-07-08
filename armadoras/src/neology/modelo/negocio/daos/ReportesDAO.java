package neology.modelo.negocio.daos;

import java.sql.PreparedStatement;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joda.time.DateTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.modelo.dto.reImpresion.Paquetes;
import neology.modelo.dto.reportes.Produccion;
import neology.util.FechaUtil;
import neology.util.Utilidades;

public class ReportesDAO extends BaseHibernateDAO {

	public ReportesDAO() {
	}

	/**
	 * Obtiene una lista de los Modulos
	 */
	public List ObtenerGlobalesProduccion() {

		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();

		loggerInfoDAO.info(FechaUtil.getHoraActual()
				+ "_ProcedenciaDAO ObtenerGlobalesProduccion - INICIO***");
		Session session = null;
		Transaction tx = null;
		List ordenes = new ArrayList();
		List<Produccion> ordenes2 = new ArrayList();

		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String queryString = " select concat(u.nombre,' ', u.apellidoPaterno,' ',u.apellidoMaterno), "
					+ " eo.descripcion, " + " count(*) " + " from OrdenImpresion as oi, Usuario as u, EstadoOI as eo "
					+ " where oi.usuario.idUsuario = u.idUsuario" + "  and oi.estado = eo.estado "
					+ " group by u.nombre, eo.descripcion"
					+ " order by concat(u.nombre,' ', u.apellidoPaterno,' ',u.apellidoMaterno), eo.descripcion";

			ordenes = session.createQuery(queryString).list();
			tx.commit();

			for (int i = 0; i < ordenes.size(); i++) {
				Produccion produccion = new Produccion();
				Object[] aux = (Object[]) ordenes.get(i);
				produccion.setNombreUsuario((String) aux[0]);
				produccion.setEstado((String) aux[1]);
				produccion.setTotalRegistros((Integer) aux[2]);

				ordenes2.add(produccion);
			}
		} catch (HibernateException re) {
			loggerErrorDAO.error("_Fallo al obtener los los grabales del reporte de produccion " ,re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()
							+ "_Fallo al obtener los los grabales del reporte de produccion "
							+ e1.getMessage());
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
						+ "_Fallo al obtener los los grabales del reporte de produccion "
						+ e1.getMessage());
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()
				+ "_ProcedenciaDAO ObtenerGlobalesProduccion - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return ordenes2;
	}

}
