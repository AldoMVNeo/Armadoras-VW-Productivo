package neology.modelo.negocio.daos;

import java.util.ArrayList;
import java.util.List;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.modelo.dto.Entidad;
import neology.modelo.dto.EstadoCatalogo;
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

public class EntidadDAO extends BaseHibernateDAO {

	public EntidadDAO() {

	}

	// Constantes
	public static final String NOMBREENTIDAD = "nombreEntidad";

	public static final String NUMDOCUMENTO = "numDocumento";

	public static final String ESTATUS = "estatus";

	public static final String CERTIFICADOPROPIEDAD = "certificadoPropiedad";

	public static final String IDENTIDAD = "idEntidad";
	
	/**
	 * Obtiene una lista de Entidades disponibles
	 */
	public List buscarEntidades() {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO buscarEntidades - INICIO***"); 
		Query query;
		Transaction tx = null;
		Session session = null;
		List entidades = new ArrayList();
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from Entidad where estatus = '0' order by "
					+ IDENTIDAD+" asc";
			query = session.createQuery(consulta);
			entidades = query.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de entidades ",re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();	
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de entidades " ,e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();	
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de entidades " ,e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();	
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO buscarEntidades - FIN******"); 
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();	
		
		return entidades;
	}

	public Entidad buscarPorId(Long idEntidad) {
	
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO buscarPorId - INICIO***"); 

		Session session = null;
		Transaction tx = null;
		Entidad entidad = null;
		try {
			session = this.getSession();
			entidad = (Entidad) session.load(Entidad.class, idEntidad);
			tx = session.beginTransaction();
			tx.commit();	
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Entidad por Id ",re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();	
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Entidad por Id ",e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();	
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Entidad por Id ",e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();	
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO buscarPorId - FIN******"); 
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();	
		return entidad;
	}

	/** Busca una Entidad por algun otro campo* */

	private List buscarPorPropiedad(String propiedad, Object value) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO buscarPorPropiedad - INICIO***"); 

		Session session = null;
		Transaction tx = null;
		List titulos = null;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String queryString = "from Entidad as model where model."
					+ propiedad + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			titulos = queryObject.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  Entidad por: " + propiedad + " valor: "
					+ value, re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();	
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  Entidad por: " + propiedad + " valor: "
							+ value, e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();	
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  Entidad por: " + propiedad + " valor: "
						+ value, e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();	
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO buscarPorPropiedad - FIN******"); 
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();	
		return titulos;
	}

	

	/**
	 * Actualiza una Entidad
	 */
	public boolean actualizar(Entidad entidad) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO actualizar - INICIO***"); 

		Session session = null;
		Transaction tx = null;
		boolean exitoso;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			session.update(entidad);
			tx.commit();
			exitoso = true;
		} catch (HibernateException re) {
			exitoso = false;
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al actualizar los datos de la Oficina ",re);
			re.printStackTrace();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					exitoso = false;
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al actualizar los datos de la Oficina ",e1);
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
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al actualizar los datos de la Oficina ",e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();	
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO actualizar - FIN******"); 
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();	
		return exitoso;
	}

	/**
	 * Guarfa una Entidad
	 */
	public boolean guardar(Entidad entidad) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO guardar - INICIO***"); 

		Session session = null;
		Transaction tx = null;
		boolean exitoso;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			session.save(entidad);
			tx.commit();
			exitoso = true;
		} catch (HibernateException re) {
			exitoso = false;
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar datos de la Nueva Oficina ",re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();	
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					exitoso = false;
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar datos de la Nueva Oficina ",e1);
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
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar datos de la Nueva Oficina ",e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();	
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO guardar - FIN******"); 
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();	
		return exitoso;
	}
	
	public List buscarEntidadesReportesConstancias() {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		Query query;
		Transaction tx = null;
		Session session = null;
		List entidades = new ArrayList();
		
		try {
			
			loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO buscarEntidadesReportesConstancias - INICIO***"); 
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from Entidad where estatus = '"+EstadoCatalogo.ACTIVO+"' order by "
					+ NOMBREENTIDAD;
			query = session.createQuery(consulta);
			entidades = query.list();
			tx.commit();
		} catch (HibernateException re) {
			
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de entidades " ,re);	
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de entidades " ,e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de entidades " ,e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO buscarEntidadesReportesConstancias - FIN***"); 
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return entidades;
	}
	
	public List buscarEntidadesPantallaAlta(Usuario us) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO buscarEntidadesPantallaAlta - INICIO***"); 

		Query query;
		Transaction tx = null;
		Session session = null;
		List entidades = new ArrayList();
		String consulta ="";
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			
				
			consulta = " from Entidad where idEntidad="+us.getEntidad().getIdEntidad()+" and  estatus = "+EstadoCatalogo.ACTIVO+" order by "
						+ NOMBREENTIDAD;	

			query = session.createQuery(consulta);
			entidades = query.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de entidades " ,re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();	
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de entidades " ,e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();	
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de entidades " ,e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();	
			 }
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO buscarEntidadesPantallaAlta - FIN***"); 
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();	
		return entidades;
	}
	
	public boolean existeEntidad(Long idEntidad,String nombreEntidad) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO existeEntidad - INICIO***"); 

		Session session = null;
		Transaction tx = null;
		Query query;
		Entidad entidad = null;
		boolean existe = false;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from Entidad as entidad where lower(entidad.nombreEntidad)= '"
					+ nombreEntidad + "' and entidad.idEntidad!="+idEntidad+" and estatus in('"+EstadoCatalogo.ACTIVO+"','"+EstadoCatalogo.INACTIVO+"')";
			query = session.createQuery(consulta);
			entidad = (Entidad) query.uniqueResult();
			tx.commit();
		} catch (HibernateException re) {
			System.out.println(re);
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener existeEntidad " ,re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();	
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener existeEntidad " ,e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();	
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener existeEntidad " ,e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();	
			}
		}
		if (entidad != null)
			existe = true;
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO existeEntidad - FIN***"); 
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();	
		return existe;
	}
	
	public boolean existeEntidad(String nombreEntidad) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+" EntidadDAO existeEntidad(String) - INICIO***"); 

		Session session = null;
		Transaction tx = null;
		Query query;
		Entidad entidad = null;
		boolean existe = false;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from Entidad as entidad where lower(entidad.nombreEntidad)= '"
					+ nombreEntidad + "' and estatus in('"+EstadoCatalogo.ACTIVO+"','"+EstadoCatalogo.INACTIVO+"')";
			query = session.createQuery(consulta);
			entidad = (Entidad) query.uniqueResult();
			tx.commit();
		} catch (HibernateException re) {
			System.out.println(re);
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener existeEntidad " ,re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();	
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener existeEntidad ",e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();	
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener existeEntidad " ,e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();	
			}
		}
		if (entidad != null)
			existe = true;
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO existeEntidad(String) - FIN***"); 
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();	
		return existe;
	}

	public List buscarEntidadesPantalla(Usuario us) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO buscarEntidadesPantalla - INICIO***"); 

		Query query;
		Transaction tx = null;
		Session session = null;
		List entidades = new ArrayList();
		String consulta ="";
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			
//			if(us.getPerfil().getIdPerfil()!=1){
//			consulta = " from Entidad where idEntidad!="+Entidad.BODEGACENTRAL+" and  estatus = "+EstadoCatalogo.ACTIVO+" order by "
//					+ NOMBREENTIDAD;
//			}
//			else{
//				
//			consulta = " from Entidad where idEntidad="+Entidad.BODEGACENTRAL+" and  estatus = "+EstadoCatalogo.ACTIVO+" order by "
//						+ NOMBREENTIDAD;	
//			}				
				consulta = " from Entidad where idEntidad="+Entidad.BODEGACENTRAL+" and  estatus = "+EstadoCatalogo.ACTIVO+" order by "
							+ NOMBREENTIDAD;	
			
			query = session.createQuery(consulta);
			entidades = query.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de entidades " ,re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();	
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de entidades " ,e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();	
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de entidades " ,e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();	
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EntidadDAO buscarEntidadesPantalla - FIN***"); 
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();	
		return entidades;
	}
	
}
