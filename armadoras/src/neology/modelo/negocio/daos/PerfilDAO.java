package neology.modelo.negocio.daos;

import java.util.ArrayList;
import java.util.List;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.modelo.dto.EstadoCatalogo;
import neology.modelo.dto.Perfil;
import neology.modelo.dto.Usuario;
import neology.util.FechaUtil;
import neology.util.Utilidades;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PerfilDAO extends BaseHibernateDAO {

	private static final String NOMBREPERFIL = "perfil.nombrePerfil";

	/**
	 * Obtiene Perfil por Id
	 */
	public Perfil buscarPorId(Long idPerfil) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_PerfilDAO buscarPorId - INICIO***");
		Session session = null;
		Transaction tx = null;
		Perfil perfil = null;
		try {
			session = this.getSession();
			perfil = (Perfil) session.load(Perfil.class, idPerfil);
			tx = session.beginTransaction();
			tx.commit();
		} catch (HibernateException re) {
			System.out.println(re);
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Perfil por Id ",re);
			re.printStackTrace();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Perfil por Id ",e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Perfil por Id ",e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_PerfilDAO buscarPorId - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return perfil;
	}

	/**
	 * Obtiene una lista de Perfiles de Usuarios disponibles
	 */
	public List buscarPerfiles() {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_PerfilDAO buscarPerfiles - INICIO***");
		Query query;
		Transaction tx = null;
		Session session = null;
		List perfiles = new ArrayList();
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from Perfil as perfil where perfil.idPerfil!=1 and perfil.estatus!="
//					+ EstadoCatalogo.ELIMINADO + " order by perfil." + NOMBREPERFIL;
//					+ EstadoCatalogo.INACTIVO + " order by perfil." + NOMBREPERFIL;
					+ EstadoCatalogo.INACTIVO + " and perfil.estatus!="+ EstadoCatalogo.ELIMINADO +" order by " + NOMBREPERFIL;
			query = session.createQuery(consulta);
			perfiles = query.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Perfiles ",re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Perfiles ",e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Perfiles ",e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_PerfilDAO buscarPerfiles - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return perfiles;
	}
	
	public List buscarPerfilesPantalla(Usuario us) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_PerfilDAO buscarPerfilesPantalla - INICIO***");
		Query query;
		Transaction tx = null;
		Session session = null;
		List perfiles = null;
		String consulta = "";
		
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			
			if(us.getIdUsuario()!=1){
			consulta = " from Perfil as perfil where perfil.idPerfil!=1 and perfil.estatus!="
					+ EstadoCatalogo.INACTIVO + " and perfil.estatus!="+ EstadoCatalogo.ELIMINADO +" order by " + NOMBREPERFIL;
			}else{
				
				consulta = " from Perfil as perfil where perfil.idPerfil=1 and perfil.estatus!="
						+ EstadoCatalogo.INACTIVO + " and perfil.estatus!="+ EstadoCatalogo.ELIMINADO +" order by " + NOMBREPERFIL;
				
			}
			
			query = session.createQuery(consulta);
			perfiles = query.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Perfiles  ",re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Perfiles  ",e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Perfiles  ",e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_PerfilDAO buscarPerfilesPantalla - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return perfiles;
	}
	
	public List buscarPerfilesParaEditar() {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_PerfilDAO buscarPerfilesParaEditar - INICIO***");
		Query query;
		Transaction tx = null;
		Session session = null;
		List perfiles = null;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from Perfil as perfil where perfil.idPerfil!=1 and perfil.estatus!="
					+ EstadoCatalogo.ELIMINADO + " order by " + NOMBREPERFIL;
//					+ EstadoCatalogo.INACTIVO + " order by perfil." + NOMBREPERFIL;
			query = session.createQuery(consulta);
			perfiles = query.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Perfiles  ",re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Perfiles  ",e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Perfiles  ",e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_PerfilDAO buscarPerfilesParaEditar - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return perfiles;
	}

	/**
	 * Verifica si existe un Perfil con ese nombre en la base de datos
	 * 
	 * @param idPerfil
	 * @param nombrePerfil
	 * @return boolean
	 */
	public boolean existePerfil(String nombrePerfil) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_PerfilDAO existePerfil - INICIO***");
		Session session = null;
		Transaction tx = null;
		Query query;
		Perfil perfil = null;
		boolean existe = false;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from Perfil as perfil where lower(perfil.nombrePerfil)= '"
					+ nombrePerfil + "' and estatus in("+EstadoCatalogo.ACTIVO+","+EstadoCatalogo.INACTIVO+")";
			query = session.createQuery(consulta);
			perfil = (Perfil) query.uniqueResult();
			tx.commit();
		} catch (HibernateException re) {
			System.out.println(re);
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener existePerfil  ",re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener existePerfil  ",e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener existePerfil  ",e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		if (perfil != null)
			existe = true;
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_PerfilDAO existePerfil - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return existe;
	}
	/**
	 * Verifica si existe un Perfil con ese nombre en la base de datos
	 * 
	 * @param idPerfil
	 * @param nombrePerfil
	 * @return boolean
	 */
	public boolean existePerfil(Long idPerfil, String nombrePerfil) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_PerfilDAO existePerfil(Long idPerfil, String nombrePerfil) - INICIO***");
		Session session = null;
		Transaction tx = null;
		Query query;
		Perfil perfil = null;
		boolean existe = false;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from Perfil as perfil where lower(perfil.nombrePerfil)= '"
					+ nombrePerfil + "' and perfil.idPerfil!=" + idPerfil+" and estatus in("+EstadoCatalogo.ACTIVO+","+EstadoCatalogo.INACTIVO+")";
			query = session.createQuery(consulta);
			perfil = (Perfil) query.uniqueResult();
			tx.commit();
		} catch (HibernateException re) {
			existe = true;
			System.out.println(re);
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener existePerfil  ",re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					existe = true;
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener existePerfil  ",e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {
				existe = true;
				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener existePerfil  ",e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		if (perfil != null)
			existe = true;
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_PerfilDAO existePerfil(Long idPerfil, String nombrePerfil) - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return existe;
	}

	/**
	 * Actualiza un perfil en la base de datos
	 * 
	 * @param perfil
	 * @return boolean
	 */
	public boolean actualizar(Perfil perfil) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_PerfilDAO actualizar - INICIO***");
		Session session = null;
		Transaction tx = null;
		boolean exitoso;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			session.update(perfil);
			tx.commit();
			exitoso = true;
		} catch (HibernateException re) {
			exitoso = false;
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al actualizar los datos del Perfil  ",re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					exitoso = false;
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al actualizar los datos del Perfil  ",e1);
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
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al actualizar los datos del Perfil  ",e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_PerfilDAO actualizar - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return exitoso;
	}

	/**
	 * Agrega un perfil en la base de datos
	 * 
	 * @param perfil
	 * @return boolean
	 */
	public boolean agregarPerfil(Perfil perfil) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_PerfilDAO agregarPerfil - INICIO***");
		Session session = null;
		Transaction tx = null;
		boolean exitoso;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			session.save(perfil);
			tx.commit();
			exitoso = true;
		} catch (HibernateException re) {
			exitoso = false;
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar Perfil  ",re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					exitoso = false;
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar Perfil  ",e1);
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
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar Perfil  ",e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_PerfilDAO agregarPerfil - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return exitoso;
	}
	
}
