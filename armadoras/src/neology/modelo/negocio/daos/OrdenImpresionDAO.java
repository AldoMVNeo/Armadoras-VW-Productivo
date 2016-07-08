package neology.modelo.negocio.daos;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.modelo.dto.EstadoOI;
import neology.modelo.dto.Formato;
import neology.modelo.dto.OrdenImpresion;
import neology.modelo.dto.reImpresion.Paquetes;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.util.VariablesGlobales;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.joda.time.DateTime;

public class OrdenImpresionDAO extends BaseHibernateDAO {
	public static final String ENTIDAD = "idEntidad";

	// Constantes
	public static final String FECHA = "fecha_registro";

	public static final Integer IMPRESO = 1;

	public static final String NIV = "vin";

	public OrdenImpresionDAO() {
	}

	public boolean actualizar(OrdenImpresion orden) {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO actualizar - INICIO***");
		Session session = null;
		Transaction tx = null;
		boolean exitoso;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			session.update(orden);
			tx.commit();
			exitoso = true;
		} catch (HibernateException re) {
			exitoso = false;
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al actualizar OrdenImpresion  ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					exitoso = false;
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al actualizar OrdenImpresion  ", e1);
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
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al actualizar OrdenImpresion  ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();

			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO actualizar - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return exitoso;
	}

	/**
	 * Eliminar un registro de ordenes de impresion
	 * 
	 * @param orden
	 * @return
	 */
	public boolean eliminar(OrdenImpresion orden) {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO eliminar - INICIO***");
		Session session = null;
		Transaction tx = null;
		boolean exitoso;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			session.delete(orden);
			tx.commit();
			exitoso = true;
		} catch (HibernateException re) {
			exitoso = false;
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al eliminar OrdenImpresion  ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					exitoso = false;
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al eliminar OrdenImpresion  ", e1);
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
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al eliminar OrdenImpresion  ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO eliminar - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return exitoso;
	}

	public boolean actualizarRango(Integer de, Integer hasta, long numImpresion, long idEntidad, Long idUsuario) {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO actualizarRango - INICIO***");
		Session session = null;
		Transaction tx = null;
		boolean exitoso;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = "update OrdenImpresion set numeroTramite = '" + numImpresion + "', id_usuario="
					+ idUsuario + ",estado=1, fechaImpresion=" + "sysdate"
					+ " where (numeroTramite = null or numeroTramite = 0)" + " and idOrdenImpresion between " + de
					+ " and " + hasta;
			int query = session.createQuery(consulta).executeUpdate();
			tx.commit();
			exitoso = true;
		} catch (HibernateException re) {
			exitoso = false;
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "  despues de execute Fallo al guardar rango de ordenes ",
					re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					exitoso = false;
					e1.printStackTrace();
					loggerErrorDAO.error(
							FechaUtil.getHoraActual() + "  despues de execute Fallo al guardar rango de ordenes ", e1);
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
				loggerErrorDAO.error(
						FechaUtil.getHoraActual() + "  despues de execute Fallo al guardar rango de ordenes ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO actualizarRango - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return exitoso;
	}

	/** Busca todas las Ordenes de Impresion* */
	public List buscarOrdenesImpresion() {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarOrdenesImpresion - INICIO***");
		Query query;
		Transaction tx = null;
		Session session = null;
		List ordenes = new ArrayList();
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from OrdenImpresion order by " + FECHA;
			query = session.createQuery(consulta);
			ordenes = query.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener lista de Ordenes de Impresion ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener lista de Ordenes de Impresion ",
							e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener lista de Ordenes de Impresion ",
						e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarOrdenesImpresion - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return ordenes;
	}

	public List buscarOrdenesImpresion(Long idOficina) {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(
				FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarOrdenesImpresion(Long idOficina) - INICIO***");
		Query query;
		Boolean activo = true;
		Transaction tx = null;
		Session session = null;
		List ordenes = new ArrayList();
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from OrdenImpresion as oi where oi.idEntidad =" + idOficina + " and oi.estado = "
					+ EstadoOI.CARGADO + " order by oi.idOrdenImpresion";
			query = session.createQuery(consulta);
			ordenes = query.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener lista de Ordenes de Impresion ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener lista de Ordenes de Impresion ",
							e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener lista de Ordenes de Impresion ",
						e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO
				.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarOrdenesImpresion(Long idOficina) - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return ordenes;

	}

	public List buscarOrdenesImpresionDotados() {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarOrdenesImpresionDotados - INICIO***");
		Query query;
		Boolean activo = true;
		Transaction tx = null;
		Session session = null;
		List ordenes = new ArrayList();
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from OrdenImpresion as oi where  oi.estado = 0 order by oi.idOrdenImpresion";
			query = session.createQuery(consulta);
			ordenes = query.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener lista de Ordenes de Impresion ", re);
			re.printStackTrace();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener lista de Ordenes de Impresion ",
							e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener lista de Ordenes de Impresion ",
						e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarOrdenesImpresionDotados - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return ordenes;

	}

	public List buscarOrdenImpresionDisponible(Integer numero) {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarOrdenImpresionDisponible - INICIO***");
		Query query;
		Transaction tx = null;
		Session session = null;
		List<OrdenImpresion> ordenesImpresion = null;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from OrdenImpresion as orden where orden.estado=null or orden.estado='D' order by orden.fechaRegistro asc";
			query = session.createQuery(consulta);
			query.setMaxResults(numero);
			ordenesImpresion = query.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener buscarOrdenImpresionDisponible() ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(
							FechaUtil.getHoraActual() + "_Fallo al obtener buscarOrdenImpresionDisponible() ", e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener buscarOrdenImpresionDisponible() ",
						e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();

			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarOrdenImpresionDisponible - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return ordenesImpresion;
	}

	public OrdenImpresion buscarOrdenImpresionSiguiente(Long idEntidad) {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarOrdenImpresionSiguiente - INICIO***");
		Query query;
		Transaction tx = null;
		Session session = null;
		OrdenImpresion ordenImpresion = null;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from OrdenImpresion as orden where orden.idEntidad=" + idEntidad
					+ " and to_char(orden.fechaRegistro,'dd/MM/yyyy')=to_char(sysdate,'dd/MM/yyyy') and orden.estado is null order by orden.fechaRegistro asc";
			query = session.createQuery(consulta);
			query.setMaxResults(1);
			List ord = query.list();
			tx.commit();
			if (ord != null && ord.size() > 0)
				ordenImpresion = (OrdenImpresion) ord.get(0);
		} catch (HibernateException re) {
			loggerInfoDAO.info(FechaUtil.getHoraActual() + "_Fallo al obtener lista de Ordenes de Impresion ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener lista de Ordenes de Impresion ",
							e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener lista de Ordenes de Impresion ",
						e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarOrdenImpresionSiguiente - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return ordenImpresion;
	}

	public List buscarPorEntidad(Object entidad) {
		return buscarPorPropiedad(ENTIDAD, entidad);
	}

	/** Busca una Orden de Impresion por Id* */
	public OrdenImpresion buscarPorId(Long id) {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarPorId - INICIO***");
		Session session = null;
		Transaction tx = null;
		OrdenImpresion orden = null;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			orden = (OrdenImpresion) session.get(OrdenImpresion.class, id);
			tx.commit();

		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener OrdenImpresion ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener OrdenImpresion ", e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {
				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener OrdenImpresion ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();

			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarPorId - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return orden;
	}

	/*
	 * Actualiza una Orden de Impresion
	 */

	public List buscarPorNIV(Object niv) {
		return buscarPorPropiedad(NIV, niv);
	}

	private List buscarPorPropiedad(String propiedad, Object value) {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarPorPropiedad - INICIO***");
		Session session = null;
		Transaction tx = null;
		List ordenes = null;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String query = "from OrdenImpresion as model where model." + propiedad + "= ? order by " + FECHA + " desc";

			Query queryObject = session.createQuery(query);
			queryObject.setParameter(0, value);
			ordenes = queryObject.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al buscar  OrdenImpresion ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al buscar  OrdenImpresion ", e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al buscar  OrdenImpresion ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();

			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarPorPropiedad - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return ordenes;
	}

	public OrdenImpresion buscarPorVin(String vin) {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarPorVin - INICIO***");
		Session session = null;
		Transaction tx = null;
		OrdenImpresion orden = null;
		Query query;
		try {
			session = this.getSession();
			// tx = session.beginTransaction();
			// orden= (OrdenImpresion) session.get(OrdenImpresion.class, vin);
			tx = session.beginTransaction();
			String consulta = " from OrdenImpresion as orden where orden.vin= '" + vin + "'";
			query = session.createQuery(consulta);
			query.setMaxResults(1);
			List ord = query.list();
			tx.commit();
			if (ord != null && ord.size() > 0)
				orden = (OrdenImpresion) ord.get(0);

		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener OrdenImpresion ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener OrdenImpresion ", e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {
				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener OrdenImpresion ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarPorVin - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return orden;
	}

	public boolean guardar(OrdenImpresion orden) {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO guardar - INICIO***");
		Session session = null;
		Transaction tx = null;
		boolean exitoso;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(orden);
			tx.commit();
			exitoso = true;
		} catch (HibernateException re) {
			exitoso = false;
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al guardar Oden de Impresion ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					exitoso = false;
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al guardar Oden de Impresion ", e1);
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
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al guardar Oden de Impresion ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO guardar - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return exitoso;
	}

	public Long ObtenerMaximoImpresion(Long idEntidad) {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO ObtenerMaximoImpresion - INICIO***");
		Session session = null;
		Transaction tx = null;
		List titulos = null;
		Long cuentaFormatos = new Long(0);
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String queryString = " select max(oi.numeroTramite) from OrdenImpresion as oi ";
			List totalFormatos = session.createQuery(queryString).list();
			tx.commit();
			Iterator iter = totalFormatos.iterator();
			while (iter.hasNext()) {
				cuentaFormatos = (Long) iter.next();
				break;
			}
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener el maximo id de impresion ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener el maximo id de impresion ",
							e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {
				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener el maximo id de impresion ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO ObtenerMaximoImpresion - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return cuentaFormatos;
	}

	public List ObtenerPaquetes(Long idEntidad) {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO ObtenerPaquetes - INICIO***");
		Session session = null;
		Transaction tx = null;
		List ordenes = new ArrayList();
		List<Paquetes> ordenes2 = new ArrayList();

		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String queryString = " select oi.numeroTramite as numeroTramite, "
					+ " max(oi.fechaImpresion) as fechaImpresion, " + " count(*) as totalRegistros"
					+ " from OrdenImpresion as oi " + " where oi.numeroTramite > 0 "
					+ " group by oi.numeroTramite order by oi.numeroTramite";
			ordenes = session.createQuery(queryString).list();
			tx.commit();

			for (int i = 0; i < ordenes.size(); i++) {
				Paquetes paquetes = new Paquetes();
				Object[] aux = (Object[]) ordenes.get(i);
				paquetes.setNumeroTramite((Long) aux[0]);
				paquetes.setFechaImpresion((DateTime) aux[1]);
				paquetes.setTotalRegistros((Integer) aux[2]);
				ordenes2.add(paquetes);
			}
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener los paquetes para la re-Impresion ",
					re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(
							FechaUtil.getHoraActual() + "_Fallo al obtener los paquetes para la re-Impresion ", e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {
				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener los paquetes para la re-Impresion ",
						e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO ObtenerPaquetes - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return ordenes2;
	}

	public List buscarOrdenesReImpresion(Long numeroTramite) {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarOrdenesReImpresion - INICIO***");
		Query query;
		Boolean activo = true;
		Transaction tx = null;
		Session session = null;
		List ordenes = new ArrayList();
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from OrdenImpresion as oi where oi.numeroTramite =" + numeroTramite
					+ " order by oi.idOrdenImpresion";
			query = session.createQuery(consulta);
			ordenes = query.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener lista de Ordenes de ReImpresion ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(
							FechaUtil.getHoraActual() + "_Fallo al obtener lista de Ordenes de ReImpresion ", e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener lista de Ordenes de ReImpresion ",
						e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarOrdenesReImpresion - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return ordenes;

	}

	/**
	 * Reporte por Grupo 1 o 2
	 * 
	 * @param fechaInicial,fechaFinal
	 * @return List ordenes
	 */

	public List buscarReportePorGrupo(String fechaInicial, String fechaFinal, int grupo, int estado) {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarReportePorGrupo - INICIO***");
		Query query;
		Transaction tx = null;
		Session session = null;
		List ordenes = null;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			// String consulta = " from OrdenImpresion as oi where
			// oi.grupo="+grupo+" and oi.estado="+estado+" and
			// to_date(to_char(oi.fechaRegistro,'dd/MM/yyyy'),'dd/MM/yyyy')
			// between to_date('"+fechaInicial+"','dd/MM/yyyy') and
			// to_date('"+fechaFinal+"','dd/MM/yyyy') order by
			// oi.idOrdenImpresion ";
			String consulta = " from OrdenImpresion as oi where oi.grupo=" + grupo + " and oi.estado=" + estado
					+ " and oi.fechaRegistro between to_timestamp('" + fechaInicial
					+ " 00:00:00','dd-mm-yyyy hh24:mi:ss') and to_timestamp('" + fechaFinal
					+ " 23:59:59', 'dd-mm-yyyy hh24:mi:ss') order by oi.idOrdenImpresion ";
			query = session.createQuery(consulta);
			ordenes = query.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener lista de  buscarReporteGrupo2  ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO
							.error(FechaUtil.getHoraActual() + "_Fallo al obtener lista de  buscarReporteGrupo2  ", e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener lista de  buscarReporteGrupo2  ",
						e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO buscarReportePorGrupo - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return ordenes;

	}

	public OrdenImpresion existeVinFolio(String vin, String folio) {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO existeVinFolio - INICIO***");
		Query query;
		Transaction tx = null;
		Session session = null;
		List<OrdenImpresion> ordenesImpresion = null;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from OrdenImpresion as orden where orden.vin='" + vin + "' and orden.folio='" + folio
					+ "'";
			query = session.createQuery(consulta);
			query.setMaxResults(1);
			ordenesImpresion = query.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(
					FechaUtil.getHoraActual() + "_Fallo al obtener existeVinFolio(" + vin + ", " + folio + ") ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(
							FechaUtil.getHoraActual() + "_Fallo al obtener existeVinFolio(" + vin + ", " + folio + ") ",
							e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {
				e1.printStackTrace();
				loggerErrorDAO.error(
						FechaUtil.getHoraActual() + "_Fallo al obtener existeVinFolio(" + vin + ", " + folio + ") ",
						e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO existeVinFolio - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();

		if (ordenesImpresion == null)
			return null;
		else if (ordenesImpresion.size() > 0)
			return ordenesImpresion.get(0);
		else
			return null;
	}

	public boolean guardarOrdenYFormato(OrdenImpresion orden, Formato formato) {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO guardarOrdenYFormato - INICIO***");
		Session session = null;
		Transaction tx = null;
		boolean exitoso;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(orden);
			session.saveOrUpdate(formato);
			tx.commit();
			exitoso = true;
		} catch (HibernateException re) {
			exitoso = false;
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al guardar Oden de Impresion ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					exitoso = false;
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al guardar Oden de Impresion ", e1);
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
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al guardar Oden de Impresion ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO guardarOrdenYFormato - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return exitoso;
	}

	public boolean existeVIN(String vin) {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO existeVIN - INICIO***");
		Query query;
		Transaction tx = null;
		Session session = null;
		List<OrdenImpresion> ordenesImpresion = null;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = " from OrdenImpresion as orden where orden.vin='" + vin + "'";
			query = session.createQuery(consulta);
			query.setMaxResults(1);
			ordenesImpresion = query.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener existeVIN(" + vin + ") ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener existeVIN(" + vin + ") ", e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {
				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener existeVIN(" + vin + ") ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO existeVIN - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();

		if (ordenesImpresion == null)
			return false;
		else
			return ordenesImpresion.size() > 0;
	}

	public boolean actualizarGrupo(String fechaInicial, String fechaFinal, int grupo, int estado) {

		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO actualizarGrupo - INICIO***");
		Session session = null;
		Transaction tx = null;
		boolean exitoso;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String consulta = "update OrdenImpresion set estado='9', fechaRegistro=sysdate" + " where grupo = " + grupo
					+ "and estado = 8 "
					// + " and
					// to_date(to_char(fechaRegistro,'dd/MM/yyyy'),'dd/MM/yyyy')
					// between to_date('"+fechaInicial+"','dd/MM/yyyy') and
					// to_date('"+fechaFinal+"','dd/MM/yyyy') order by
					// oi.idOrdenImpresion";
					// + " and fechaRegistro between '"+fechaInicial+"' and
					// '"+fechaFinal+"' order by oi.idOrdenImpresion";
					+ " and fechaRegistro between to_timestamp('" + fechaInicial
					+ " 00:00:00', 'dd-mm-yyyy hh24:mi:ss') and  to_timestamp('" + fechaFinal
					+ " 23:59:59', 'dd-mm-yyyy hh24:mi:ss') order by oi.idOrdenImpresion";
			int query = session.createQuery(consulta).executeUpdate();
			tx.commit();
			exitoso = true;
		} catch (HibernateException re) {
			exitoso = false;
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_despues de execute Fallo al guardar por grupo ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					exitoso = false;
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual() + "_despues de execute Fallo al guardar por grupo ",
							e1);
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
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_despues de execute Fallo al guardar por grupo ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();

			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO actualizarGrupo - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return exitoso;
	}

	public List listarVin(String vin) {
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO listarVin - INICIO***");
		Session session = null;
		Transaction tx = null;
		OrdenImpresion orden = null;
		Query query;
		List ord = null;
		try {
			session = this.getSession();
			// tx = session.beginTransaction();
			// orden= (OrdenImpresion) session.get(OrdenImpresion.class, vin);
			tx = session.beginTransaction();
			String consulta = " from OrdenImpresion as orden where orden.vin= trim('" + vin + "')";
			query = session.createQuery(consulta);
			query.setMaxResults(1);
			ord = query.list();
			tx.commit();

		} catch (HibernateException re) {
			re.printStackTrace();
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener OrdenImpresion ", re);
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener OrdenImpresion ", e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {
				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al obtener OrdenImpresion ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();

			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO listarVin - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return ord;
	}

	public ArrayList<OrdenImpresion> listarOrdenResp(String vin, String idOrden) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
		EstadoOIDAO estadoDAO = DAOFactory.crearEstadoOIDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO listarVinResp - INICIO***");
		Session session = null;
		ArrayList<OrdenImpresion> ord = new ArrayList<OrdenImpresion>();
		Boolean boolFound=false;
		String strEsquemaRespaldo= VariablesGlobales.getEsquemaRespaldo();
		
		try {
			session = this.getSessionResp();
			Connection connOrden = session.connection();
			

			// Primero obtener tablas existentes
			String consulta = "SELECT TABLE_NAME FROM ALL_TABLES WHERE TABLE_NAME LIKE '%ORDENES_IMPRESION_%'"
					+ " AND OWNER='"+strEsquemaRespaldo+"'";
			
			String consultaVin ="";

			Statement stmt = connOrden.createStatement();
			ResultSet rsTabla = stmt.executeQuery(consulta);
			ResultSet rsOrden = null;
			Statement stmtOrden = connOrden.createStatement();

			//Por cada una, buscar el registro
			while (rsTabla.next() && !boolFound) {

				consultaVin = "SELECT      ID_ORDEN_IMPRESION,"
										   + "ID_USUARIO,"
										   + "VIN,"
										   + "ENTIDAD,"
										   + "FECHA_REGISTRO,"
										   + "FOLIO,"
										   + "NUMERO_CHIP,"
										   + "ESTADO,"
										   + "ID_ENTIDAD FROM "+rsTabla.getString("TABLE_NAME")
										   ;
										   
				if(!vin.trim().equals("") && idOrden.trim().equals("")){
					
					consultaVin+= " WHERE VIN='"+vin+"'";
					
				}
				
				if(vin.trim().equals("") && !idOrden.trim().equals("")){
					
					consultaVin+= " WHERE ID_ORDEN_IMPRESION='"+idOrden+"'";
					
				}
										   				  

				rsOrden=stmtOrden.executeQuery(consultaVin);
				
				while (rsOrden.next()) {
					
					
					OrdenImpresion orden = new OrdenImpresion();
					orden.setIdOrdenImpresion(rsOrden.getLong("ID_ORDEN_IMPRESION"));
					orden.setUsuario(usuarioDAO.buscarPorId(rsOrden.getLong("ID_USUARIO")));
					orden.setVin(rsOrden.getString("VIN"));
					orden.setEstado(estadoDAO.buscarPorId(rsOrden.getString("ESTADO")));
					orden.setEntidad(rsOrden.getString("ENTIDAD"));
					orden.setFolio(rsOrden.getString("FOLIO"));
					orden.setNumeroChip(rsOrden.getString("NUMERO_CHIP"));
					DateTime fechaRegistro = new DateTime(rsOrden.getDate("FECHA_REGISTRO"));
					orden.setFechaRegistro(fechaRegistro);
					ord.add(orden);
					boolFound=true;
				}
			}
			
			connOrden.close();

		} catch (HibernateException re) {
			re.printStackTrace();
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_OrdenImpresionDAO Fallo al obtener OrdenImpresion ", re);
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			return null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_OrdenImpresionDAO Fallo al obtener OrdenImpresion ", e);
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			return null;
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {
				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_OrdenImpresionDAO Fallo al obtener OrdenImpresion ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
				return null;
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenImpresionDAO listarVinResp - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return ord;
	}

}
