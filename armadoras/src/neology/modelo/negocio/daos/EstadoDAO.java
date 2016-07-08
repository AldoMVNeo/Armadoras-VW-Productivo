package neology.modelo.negocio.daos;

import java.util.ArrayList;

import java.util.Iterator;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.modelo.dto.Estado;
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

public class EstadoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(EstadoDAO.class);
	public static final String DESCRIPCION = "descripcion";

	public EstadoDAO() {
	}

	public List buscarPorDescripcion(Object login) {
		return buscarPorPropiedad(DESCRIPCION, login);
	}

	public Estado buscarPorId(String estado) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_EstadoDAO buscarPorId - INICIO***");
		Session session = null;
		Transaction tx = null;
		Estado estadoI = null;
		try {
			session = this.getSession();
			estadoI = (Estado) session.load(Estado.class, estado);
			tx = session.beginTransaction();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(
					FechaUtil.getHoraActual() + "_Fallo al obtener Estado por Id"+
					re.getMessage());
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();	
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()
							+ "_Fallo al obtener Estado por Id ",e1);
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
						+ "_Fallo al obtener Estado por Id ",e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();	
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_EstadoDAO buscarPorId - FIN***");
		return estadoI;
	}

	public List obtenerEstados() {

		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		Query query;
		Transaction tx = null;
		Session session = null;
		List estados = new ArrayList();
		try {
			
			loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EstadoDAO inicio obtenerEstados - INICIO***");
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from Estado order by " + DESCRIPCION;
			query = session.createQuery(consulta);
			estados = query.list();
			tx.commit();
		} catch (HibernateException e1) {
			e1.printStackTrace();
			loggerErrorDAO.error(FechaUtil.getHoraActual()
					+ "_Fallo al obtener Lista de Estados " ,e1);
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();	
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException err) {
					err.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()
							+ "_Fallo al obtener Lista de Estados " ,err);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();	
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException err) {
				err.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()
						+ "_Fallo al obtener Lista de Estados " ,err);
				
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();	
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EstadoDAO inicio obtenerEstados - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return estados;
	}

	public List obtenerEstados(String estadoFormato) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()
				+ "_EstadoDAO obtenerEstados(String estadoFormato) - INICIO***");
		Query query;
		Transaction tx = null;
		Session session = null;
		List estados = new ArrayList();
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from Estado as estfor Where estfor.estado !=" + "'" + estadoFormato + "'" + " order by "
					+ DESCRIPCION;
			query = session.createQuery(consulta);
			estados = query.list();
			tx.commit();
		} catch (HibernateException e1) {
			e1.printStackTrace();
			loggerErrorDAO.error(FechaUtil.getHoraActual()
					+ "_Fallo al obtener Lista de Estados ",e1);
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException err) {
					err.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()
							+ "_Fallo al obtener Lista de Estados ",err);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException err) {
				err.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()
						+ "_Fallo al obtener Lista de Estados ",err);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()
				+ "_EstadoDAO obtenerEstados(String estadoFormato) - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return estados;
	}

	public Estado obtenerEstadosPorEstado(String estadoFormato) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()
				+ "_EstadoDAO obtenerEstadosPorEstado - INICIO***");
		Query query;
		Transaction tx = null;
		Session session = null;
		List estados = new ArrayList();
		Estado est = new Estado();
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from Estado as estfor Where estfor.estado =" + "'" + estadoFormato + "'";
			query = session.createQuery(consulta);
			estados = query.list();
			tx.commit();
			est = (Estado) estados.get(0);

		} catch (HibernateException e1) {
			e1.printStackTrace();
			loggerErrorDAO.error(FechaUtil.getHoraActual()
					+ "_Fallo al obtener Lista de Estados ",e1);
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException err) {
					err.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()
							+ "_Fallo al obtener Lista de Estados ",err);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException err) {
				err.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()
						+ "_Fallo al obtener Lista de Estados ",err);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()
				+ "_EstadoDAO obtenerEstadosPorEstado - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return est;
	}

	private List buscarPorPropiedad(String propiedad, Object value) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()
				+ "_EstadoDAO buscarPorPropiedad - INICIO***");
		Session session = null;
		Transaction tx = null;
		List usuarios = null;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String queryString = "from Estado as model where model." + propiedad + "= ? ";
			System.out.println(propiedad);
			System.out.println("-----> " + queryString);
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			usuarios = queryObject.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "Fallo al buscar  Usuario ",re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()
							+ "_Fallo al buscar  Usuario ",e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al buscar  Usuario ",e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(
				FechaUtil.getHoraActual() + "_EstadoDAO buscarPorPropiedad - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return usuarios;
	}

}
