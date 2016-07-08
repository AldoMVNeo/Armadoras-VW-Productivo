package neology.modelo.negocio.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.util.VariablesGlobales;

public class RespaldoHistoricoDAO extends BaseHibernateDAO {

	public ArrayList <String> existenTablasFormatos(String strIsFecha,String fechaInicial,String fechaFinal,String strResponsable) {

		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();

		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_RespaldoHistoricoDAO existenTablas - INICIO***");
		Session session = null;
		String consulta ="";
		String strEsquemaRespaldo= VariablesGlobales.getEsquemaRespaldo();
		ArrayList <String> listTablasResp = new ArrayList<>();

		try {

			session = this.getSession();
			Connection connRespTablas = session.connection();
			Statement stmt =null;
			ResultSet rs =null;

			// Primero obtener tablas existentes			
				consulta = "SELECT TABLE_NAME FROM ALL_TABLES WHERE TABLE_NAME LIKE '%FORMATOS_%' AND TABLE_NAME NOT LIKE '%FORMATOS_HISTORICO%'"
						+ " AND OWNER='"+strEsquemaRespaldo+"'";		
				
				stmt = connRespTablas.createStatement();
				rs = stmt.executeQuery(consulta);
				
				while (rs.next()) {
					listTablasResp.add(rs.getString("TABLE_NAME"));
				}
			
			connRespTablas.close();

		} catch (HibernateException re) {
			
			re.printStackTrace();
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_RespaldoHistoricoDAO Fallo al obtener existenTablas ", re);
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_RespaldoHistoricoDAO existenTablas - FIN***");
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_RespaldoHistoricoDAO Fallo al obtener existenTablas ", e);
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_RespaldoHistoricoDAO existenTablas - FIN***");
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			return null;
		}finally {
 			try {
 				if (session != null)
 					session.close();
 			} catch (HibernateException e1) {
 				e1.printStackTrace();
 				loggerErrorDAO.error(FechaUtil.getHoraActual()+
		 					"_Fallo al obtener existenTablas",
		 					e1);
 				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_RespaldoHistoricoDAO Fallo al obtener existenTablas ", e1);
 				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_RespaldoHistoricoDAO existenTablas - FIN***");
 				loggerInfoDAO.removeAllAppenders();
 			    loggerErrorDAO.removeAllAppenders();
 			   return null;
 			}
 		}
		
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_RespaldoHistoricoDAO existenTablas - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return listTablasResp;
	}

	public Boolean existeTabla(String strNombreTabla) {

		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();

		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_RespaldoHistoricoDAO existeTabla - INICIO***");
		Session session = null;
		String consulta ="";
		Boolean existe=false;
		String strEsquemaRespaldo= VariablesGlobales.getEsquemaRespaldo();

		try {

			session = this.getSession();
			Connection connRespTablas = session.connection();
			Statement stmt =null;
			ResultSet rs =null;

			// Primero obtener tablas existentes			
				consulta = "SELECT TABLE_NAME FROM ALL_TABLES WHERE TABLE_NAME LIKE '%"+strNombreTabla+"%' "
						+ " AND OWNER='"+strEsquemaRespaldo+"'";		
				
				stmt = connRespTablas.createStatement();
				rs = stmt.executeQuery(consulta);
				
				while (rs.next()) {
					existe=true;
				}
			
			connRespTablas.close();

		} catch (HibernateException re) {
			
			re.printStackTrace();
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_RespaldoHistoricoDAO Fallo al obtener existeTabla ", re);
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_RespaldoHistoricoDAO existeTabla - FIN***");
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_RespaldoHistoricoDAO Fallo al obtener existeTabla ", e);
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_RespaldoHistoricoDAO existeTabla - FIN***");
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			return false;
		}finally {
 			try {
 				if (session != null)
 					session.close();
 			} catch (HibernateException e1) {
 				e1.printStackTrace();
 				loggerErrorDAO.error(FechaUtil.getHoraActual()+
		 					"_RespaldoHistoricoDAO Fallo al obtener existenTablas",
		 					e1);
 				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_RespaldoHistoricoDAO Fallo al obtener existeTabla ", e1);
 				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_RespaldoHistoricoDAO existeTabla - FIN***");
 				loggerInfoDAO.removeAllAppenders();
 			    loggerErrorDAO.removeAllAppenders();
 			   return false;
 			}
 		}
		
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_RespaldoHistoricoDAO existeTabla - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return existe;
	}
	
	
}
