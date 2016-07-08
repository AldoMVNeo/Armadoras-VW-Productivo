package neology.modelo.negocio.daos;

import java.sql.Connection;
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
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.joda.time.DateTime;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.modelo.dto.FormatoHistorico;
import neology.modelo.dto.OrdenImpresion;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.util.VariablesGlobales;

public class FormatoHistoricoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(FormatoHistoricoDAO.class);
	public static final String FOLIO = "folio";

	/** Busca un FormatoHistorico por algun otro campo **/

	private List buscarPorPropiedad(String propiedad, Object value) {

		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();

		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_FormatoHistoricoDAO buscarPorPropiedad - INICIO***");
		Session session = null;
		Transaction tx = null;
		List titulos = null;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String queryString = "from FormatoHistorico as model where model." + propiedad
					+ "= ? order by model.fechaRegistro desc";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			titulos = queryObject.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al buscar  FormatoHistorico  ", re);
			re.printStackTrace();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al buscar  FormatoHistorico  ", e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al buscar  FormatoHistorico  ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_FormatoHistoricoDAO buscarPorPropiedad - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return titulos;
	}

	public List buscarPorFolio(Object folio) {
		return buscarPorPropiedad(FOLIO, folio);
	}

	/** Guarda historico **/
	public boolean guardar(FormatoHistorico formatoHistorico) {

		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();

		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_FormatoHistoricoDAO guardar - INICIO***");
		Session session = null;
		Transaction tx = null;
		boolean exitoso;

		try {
			session = this.getSession();
			tx = session.beginTransaction();
			session.save(formatoHistorico);
			tx.commit();
			exitoso = true;
		} catch (HibernateException re) {
			exitoso = false;
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al guardar historico ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					exitoso = false;
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al guardar historico ", e1);
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
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al guardar historico ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_FormatoHistoricoDAO guardar - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return exitoso;
	}

	public List buscarUltimoEvento(String folio) {

		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();

		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_FormatoHistoricoDAO buscarUltimoEvento - INICIO***");
		Session session = null;
		Transaction tx = null;
		List titulos = null;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String queryString = "from FormatoHistorico as model where model.folio=? order by model.fechaRegistro desc DESC LIMIT 1";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, folio);
			titulos = queryObject.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al buscar  FormatoHistorico  ", re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al buscar  FormatoHistorico  ", e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_Fallo al buscar  FormatoHistorico  ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_FormatoHistoricoDAO buscarUltimoEvento - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return titulos;
	}

	public ArrayList<FormatoHistorico> listarFormatoHistoricoResp(String folio) {

		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
		EstadoOIDAO estadoDAO = DAOFactory.crearEstadoOIDAO();
		OrdenImpresionDAO ordenDAO = DAOFactory.crearOrdenImpresionDAO();

		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenesHistoricoDAO listarVinResp - INICIO***");
		Session session = null;
		ArrayList<FormatoHistorico> formatosHist = new ArrayList<FormatoHistorico>();
		Boolean boolFound = false;
		String strEsquemaRespaldo= VariablesGlobales.getEsquemaRespaldo();

		try {
			session = this.getSessionResp();
			Connection connFormHist = session.connection();

			// Primero obtener tablas existentes
			String consulta = "SELECT TABLE_NAME FROM ALL_TABLES WHERE TABLE_NAME LIKE '%FORMATOS_HISTORICO%'"
					+ " AND OWNER='"+strEsquemaRespaldo+"'";

			String consultaFolio = "";

			Statement stmt = connFormHist.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			Statement stmtFolio = connFormHist.createStatement();
			ResultSet rsHistorico = null;

			// Por cada una, buscar el registro
			while (rs.next() && !boolFound) {

				consultaFolio = "SELECT       ID_USUARIO_MODIFICO," + "FOLIO," + "FECHA_REGISTRO," + "ESTADO,"
						+ "ID_ORDEN_IMPRESION," + "ID_ENTIDAD FROM " + rs.getString("TABLE_NAME") + " WHERE FOLIO='"
						+ folio + "'";

				rsHistorico = stmtFolio.executeQuery(consultaFolio);

				while (rsHistorico.next()) {

					FormatoHistorico formato = new FormatoHistorico();
					formato.setFolio(rsHistorico.getString("FOLIO"));
					formato.setIdUsuarioModifico(rsHistorico.getLong("ID_USUARIO_MODIFICO"));
					formato.setIdUsuarioActual(rsHistorico.getLong("ID_USUARIO_MODIFICO"));
					formato.setEstado(rsHistorico.getString("ESTADO"));
					formato.setIdEntidad(rsHistorico.getLong("ID_ENTIDAD"));
					formato.setIdOrdenImpresion(rsHistorico.getLong("ID_ORDEN_IMPRESION"));
					DateTime fechaRegistro = new DateTime(rsHistorico.getDate("FECHA_REGISTRO"));
					formato.setFechaRegistro(fechaRegistro);

					formatosHist.add(formato);
					boolFound = true;
				}

			}

			// Ordenes de impresion si existen

			// Buscar datos de Orden de impresion si existe
			ArrayList<OrdenImpresion> listOrdenes = new ArrayList<OrdenImpresion>();

			for (int i = 0; i < formatosHist.size(); i++) {

				if (formatosHist.get(i).getIdOrdenImpresion() != 0 && formatosHist.get(i).getIdOrdenImpresion()!=null) {
					listOrdenes = ordenDAO.listarOrdenResp("", formatosHist.get(i).getIdOrdenImpresion().toString());

					formatosHist.get(i).setNiv(listOrdenes.get(0).getVin());
					formatosHist.get(i).setNumeroChip(listOrdenes.get(0).getNumeroChip());
				} else {
					formatosHist.get(i).setIdOrdenImpresion(null);
				}
			}

			connFormHist.close();
		} catch (HibernateException re) {
			re.printStackTrace();
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_OrdenesHistoricoDAO Fallo al obtener OrdenesHistorico ", re);
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			return null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_OrdenesHistoricoDAO Fallo listarFormatoHistoricoResp al obtener OrdenesHistorico ", e);
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			return null;
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {
				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_OrdenesHistoricoDAO Fallo listarFormatoHistoricoResp al obtener OrdenesHistorico ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
				return null;

			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenesHistoricoDAO listarFormatoHistoricoResplistarVinResp - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return formatosHist;
	}

}
