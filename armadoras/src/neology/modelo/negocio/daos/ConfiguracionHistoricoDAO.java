package neology.modelo.negocio.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.modelo.dto.ConfigJob;
import neology.modelo.dto.Usuario;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.vc.actions.sistema.ConfigJobForm;
import neology.vc.forms.reportes.ReporteGeneralDatosForm;

public class ConfiguracionHistoricoDAO extends BaseHibernateDAO {

	public static final String IDPARAMETRO = "idParametro";

	public static final String ESTATUS = "estatus";

	public List buscarPorParametro(Object idParametro) {
		return buscarPorPropiedad(IDPARAMETRO, idParametro);
	}

	public List buscarPorEstatus(Object estatus) {
		return buscarPorPropiedad(ESTATUS, estatus);
	}

	public List buscarPorPropiedad(String propiedad, Object value) {

		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();

		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_ConfiguracionHistoricoDAO buscarPorPropiedad - INICIO***");
		Session session = null;
		Transaction tx = null;
		List params = null;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String queryString = "from ConfigJob as model where model." + propiedad + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			params = queryObject.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual()
					+ "_ConfiguracionHistoricoDAO buscarPorPropiedad Fallo al buscar configuracion ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(
							FechaUtil.getHoraActual()
									+ "_ConfiguracionHistoricoDAO buscarPorPropiedad Fallo al buscar configuracion",
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
				loggerErrorDAO.error(FechaUtil.getHoraActual()
						+ "_ConfiguracionHistoricoDAO buscarPorPropiedad Fallo al buscar configuracion ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_ConfiguracionHistoricoDAO buscarPorPropiedad - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return params;
	}

	public boolean guardar(ConfigJob configJob) {

		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();

		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_ConfiguracionHistoricoDAO guardar ConfigJob- INICIO***");
		Session session = null;
		Transaction tx = null;
		boolean exitoso;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			session.save(configJob);
			tx.commit();
			exitoso = true;
		} catch (HibernateException re) {
			exitoso = false;
			loggerErrorDAO.error(FechaUtil.getHoraActual()
					+ "_ConfiguracionHistoricoDAO guardar Fallo al guardar configuracion de historico ", re);
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
							FechaUtil.getHoraActual()
									+ "_ConfiguracionHistoricoDAO guardar Fallo al guardar configuracion de historico ",
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
				loggerErrorDAO.error(
						FechaUtil.getHoraActual()
								+ "_ConfiguracionHistoricoDAO guardar Fallo al guardar configuracion de historico ",
						e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_ConfiguracionHistoricoDAO guardar ConfigJob- FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return exitoso;
	}

	public boolean eliminar(ConfigJob configJob) {

		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();

		Session session = null;
		Transaction tx = null;
		boolean exitoso;
		try {
			loggerInfoDAO.info(FechaUtil.getHoraActual() + "_ConfiguracionHistoricoDAO eliminar - INICIO***");
			session = this.getSession();
			tx = session.beginTransaction();
			session.delete(configJob);
			tx.commit();
			exitoso = true;
		} catch (HibernateException re) {
			exitoso = false;
			loggerErrorDAO.error(FechaUtil.getHoraActual()
					+ "_ConfiguracionHistoricoDAO eliminar Fallo al guardar configuracion de historico ", re);
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
							FechaUtil.getHoraActual()
									+ "_ConfiguracionHistoricoDAO eliminar Fallo al guardar configuracion de historico ",
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
				loggerErrorDAO.error(FechaUtil.getHoraActual()
						+ "_ConfiguracionHistoricoDAO eliminar al guardar configuracion de historico ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_ConfiguracionHistoricoDAO eliminar - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return exitoso;
	}

	public List buscarUltimo() {

		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();

		Session session = null;
		Transaction tx = null;
		List params = null;
		try {
			loggerInfoDAO.info(FechaUtil.getHoraActual() + "_ConfiguracionHistoricoDAO buscarUltimo - INICIO***");
			session = this.getSession();
			tx = session.beginTransaction();
			String queryString = "FROM ConfigJob where idParametro=(select max(idParametro) from ConfigJob)";
			Query queryObject = session.createQuery(queryString);
			params = queryObject.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual()
					+ "_ConfiguracionHistoricoDAO buscarUltimo Fallo al buscar configuracion ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(
							FechaUtil.getHoraActual()
									+ "_ConfiguracionHistoricoDAO buscarUltimo al guardar configuracion de historico ",
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
				loggerErrorDAO.error(FechaUtil.getHoraActual()
						+ "_ConfiguracionHistoricoDAO buscarUltimo al guardar configuracion de historico ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_ConfiguracionHistoricoDAO buscarUltimo - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return params;
	}

	public List listConfig() {

		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();

		Session session = null;
		Transaction tx = null;
		List params = null;
		Connection conn=null;
		List<ConfigJobForm> listReporteGeneral = new ArrayList<ConfigJobForm>();
		UsuarioDAO usuariosDAO = DAOFactory.crearUsuarioDAO();
		
		try {
			loggerInfoDAO.info(FechaUtil.getHoraActual() + "_ConfiguracionHistoricoDAO buscarUltimo - INICIO***");
			session = this.getSession();
			tx = session.beginTransaction();
			conn=session.connection();
			
			String queryString = "SELECT "+ 
							     "configJob.ESTATUS estatus, "+ 
							     "configJob.FECHACONFIG fechaConfig, "+ 
							     "configJob.HORACONFIG horaConfig, "+ 
							     "configJob.ID_PARAMETRO idParam, "+ 
							     "configJob.ID_USUARIO idUsuario, "+ 
							     "configJob.MINUTOSCONFIG minutosConfig, "+ 
							     "configJob.NO_DIAS noDias, "+ 
							     "rveResp.CANTIDAD_TOTAL cantTotal "+   
							     "FROM ConfigJob configJob "+  
							     "LEFT JOIN RVE_RESPALDOS rveResp "+  
							     "ON configJob.ID_PARAMETRO=rveResp.RESPALDO " + 
							     "ORDER BY configJob.ID_PARAMETRO";
			
			PreparedStatement statement=conn.prepareStatement(queryString);
			ResultSet rs=statement.executeQuery();
			Usuario us=null;
			
			while (rs.next()) {
				
					ConfigJobForm cjForm = new ConfigJobForm();
					cjForm.setEstatus(rs.getLong("estatus"));
					cjForm.setFechaConfig(rs.getDate("fechaConfig"));
					cjForm.setHoraConfig(rs.getString("horaConfig"));
					cjForm.setIdParametro(rs.getLong("idParam"));
					cjForm.setIntCantidadTotal(rs.getInt("cantTotal"));
					cjForm.setMinutosConfig(rs.getString("minutosConfig"));
					cjForm.setNoDias(rs.getLong("noDias"));
					cjForm.setIdUsuario(rs.getLong("idUsuario"));
					listReporteGeneral.add(cjForm);
					
				}
			
			tx.commit();
			conn.close();
			
		} catch (HibernateException re) {
			loggerErrorDAO
					.error(FechaUtil.getHoraActual() + "_ConfiguracionHistoricoDAO Fallo al buscar configuracion ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()
							+ "_ConfiguracionHistoricoDAO Fallo al guardar configuracion de historico ", e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (session != null)
					session.close();
				    
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()
						+ "_ConfiguracionHistoricoDAO Fallo al guardar configuracion de historico ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_ConfiguracionHistoricoDAO buscarUltimo - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return listReporteGeneral;
	}

//	public boolean ejecutaJob() throws SQLException {
//
//		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
//		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
//
//		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_ConfiguracionHistoricoDAO ejecutaJob - INICIO***");
//		String query = "";
//		boolean exitoso = false;
//		Session session = null;
//		
//		try {
//
//			session = this.getSession();
//			Connection conn = session.connection();
//			query = "begin dbms_scheduler.run_job('REPUVE.JOB_REVISION_HISTORICO'); commit; end;";
//			
//			Statement stmt = conn.createStatement();
//			stmt.executeUpdate(query);
//			stmt.close();
//			exitoso = true;
//
//			conn.close();
//
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//			loggerErrorDAO.error(
//					FechaUtil.getHoraActual() + "_ConfiguracionHistoricoDAO Fallo al ejecutaJob  de historico ", e);
//			loggerInfoDAO.removeAllAppenders();
//			loggerErrorDAO.removeAllAppenders();
//		}
//		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_ConfiguracionHistoricoDAO ejecutaJob - FIN***");
//		loggerInfoDAO.removeAllAppenders();
//		loggerErrorDAO.removeAllAppenders();
//		return exitoso;
//	}
	
	public boolean ejecutaJob() throws SQLException {

		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();

		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_ConfiguracionHistoricoDAO ejecutaJob - INICIO***");
		String query = "";
		boolean exitoso = false;
		Session session = null;
		
		try {

			// query = "begin
			// dbms_scheduler.run_job('ARMADORA_VW.JOB_REVISION_HISTORICO');
			// commit; end;";
			// Connection conn =
			// DriverManager.getConnection("jdbc:oracle:thin:@192.168.31.85:1521:xe",
			// "ARMADORA_VW", "neology");
			session = this.getSession();
			Connection conn = session.connection();
			query =   " begin "
					+ " dbms_scheduler.run_job('REPUVE.JOB_ELIMINA_JOBS'); "
					+ " dbms_scheduler.run_job('REPUVE.JOB_CREA_JOBS'); "
					+ " end;";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			exitoso = true;

			conn.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
			loggerErrorDAO.error(
					FechaUtil.getHoraActual() + "_ConfiguracionHistoricoDAO Fallo al ejecutaJob  de historico ", e);
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_ConfiguracionHistoricoDAO ejecutaJob - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return exitoso;
	}

}
