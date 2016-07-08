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
import neology.modelo.dto.OrdenesHistorico;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.util.VariablesGlobales;

public class OrdenesHistoricoDAO extends BaseHibernateDAO {

	public static final String NIV = "vin";

	/** Guarda historico **/
	public boolean guardar(OrdenesHistorico ordenHistorico) {

		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();

		loggerErrorDAO.info(FechaUtil.getHoraActual()
				+ "_OrdenesHistoricoDAO guardar - INICIO***");
		Session session = null;
		Transaction tx = null;
		boolean exitoso;

		try {
			session = this.getSession();
			tx = session.beginTransaction();
			session.save(ordenHistorico);
			tx.commit();
			exitoso = true;
		} catch (HibernateException re) {
			exitoso = false;
			loggerErrorDAO.error(FechaUtil.getHoraActual()
					+ "_Fallo al guardar historico " ,re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					exitoso = false;
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()
							+ "_Fallo al guardar historico " ,e1);
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
						+ "_Fallo al guardar historico " ,e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerErrorDAO.info(
				FechaUtil.getHoraActual() + "_OrdenesHistoricoDAO guardar - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return exitoso;
	}

	public List buscarPorNiv(Object niv) {
		return buscarPorPropiedad(NIV, niv);
	}

	private List buscarPorPropiedad(String propiedad, Object value) {

		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();

		loggerInfoDAO.info(FechaUtil.getHoraActual()
				+ "_OrdenesHistoricoDAO buscarPorPropiedad - INICIO***");
		Session session = null;
		Transaction tx = null;
		List ordenesHist = null;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			String queryString = "from OrdenesHistorico as model where model." + propiedad
					+ "= ? order by model.fechaRegistro desc";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			ordenesHist = queryObject.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual()
					+ "_Fallo al buscar  OrdenesHistorico  " ,re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()
							+ "_Fallo al guardar historico " ,e1);
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
						+ "_Fallo al guardar historico " ,e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
				
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()
				+ "_OrdenesHistoricoDAO buscarPorPropiedad - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return ordenesHist;
	}
	
	
	public ArrayList<OrdenesHistorico> listarVinHistoricoResp(String vin) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
		EstadoOIDAO estadoDAO = DAOFactory.crearEstadoOIDAO();
		String strEsquemaRespaldo= VariablesGlobales.getEsquemaRespaldo();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenesHistoricoDAO listarVinResp - INICIO***");
		Session session = null;
		ArrayList<OrdenesHistorico> ord = new ArrayList<OrdenesHistorico>();
		Boolean boolFound=false;
		
		try {
			session = this.getSessionResp();
			Connection connOrdenHist = session.connection();

			// Primero obtener tablas existentes
			String consulta = "SELECT TABLE_NAME FROM ALL_TABLES WHERE TABLE_NAME LIKE '%ORDENES_IMP_HIST_%'"
					+ " AND OWNER='"+strEsquemaRespaldo+"'";
			
			String consultaVin ="";

			Statement stmt = connOrdenHist.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			ResultSet rsVin = null;
			Statement stmtOrden = connOrdenHist.createStatement();

			//Por cada una, buscar el registro
			while (rs.next() && !boolFound) {

				consultaVin = "SELECT      ID_ORDEN_IMPRESION,"
										   + "ID_USUARIO_MODIFICO,"
										   + "VIN,"
										   + "ENTIDAD,"
										   + "FECHA_REGISTRO,"
										   + "FOLIO,"
										   + "NUMERO_CHIP,"
										   + "ESTADO,"
										   + "ID_ENTIDAD FROM "+rs.getString("TABLE_NAME")
										   + " WHERE VIN='"+vin+"'";

				rsVin=stmtOrden.executeQuery(consultaVin);
				
				while (rsVin.next()) {
					
					
					OrdenesHistorico orden = new OrdenesHistorico();
					orden.setIdOrdenImpresion(rsVin.getLong("ID_ORDEN_IMPRESION"));
					orden.setIdUsuarioModifico(usuarioDAO.buscarPorId(rsVin.getLong("ID_USUARIO_MODIFICO")));
					orden.setVin(rsVin.getString("VIN"));
					orden.setEstado(estadoDAO.buscarPorId(rsVin.getString("ESTADO")));
					orden.setEntidad(rsVin.getString("ENTIDAD"));
					orden.setFolio(rsVin.getString("FOLIO"));
					orden.setNumeroChip(rsVin.getString("NUMERO_CHIP"));
					DateTime fechaRegistro = new DateTime(rsVin.getDate("FECHA_REGISTRO"));
					orden.setFechaRegistro(fechaRegistro);
					ord.add(orden);
					boolFound=true;
				}
			}
			
			connOrdenHist.close();

		} catch (HibernateException re) {
			re.printStackTrace();
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_OrdenesHistoricoDAO listarVinHistoricoResp Fallo al obtener OrdenesHistorico ", re);
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			return null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_OrdenesHistoricoDAO listarVinHistoricoResp Fallo al obtener OrdenesHistorico ", e);
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			return null;
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {
				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_OrdenesHistoricoDAO listarVinHistoricoResp Fallo al obtener OrdenesHistorico ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
				return null;

			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_OrdenesHistoricoDAO listarVinResp - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return ord;
	}

}
