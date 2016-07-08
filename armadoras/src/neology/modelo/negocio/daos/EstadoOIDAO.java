package neology.modelo.negocio.daos;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.modelo.dto.EstadoOI;
import neology.util.FechaUtil;
import neology.util.Utilidades;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EstadoOIDAO extends BaseHibernateDAO
{
	public EstadoOIDAO()
	{}

	/** Busca un Estado de Ordenes de Impresion por Id* */
	public EstadoOI buscarPorId(String estado)
	{
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EstadoOIDAO buscarPorId - INICIO***"); 
		Session session = null;
		Transaction tx = null;
		EstadoOI estadoOI = null;
		try
		{
			session = this.getSession();
			estadoOI = (EstadoOI) session.load(EstadoOI.class, estado);
			tx = session.beginTransaction();
			tx.commit();
		} catch (HibernateException re)
		{
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener EstadoOI por Id ",re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();	
			if (tx != null) try
			{
				tx.rollback();
			} catch (HibernateException e1)
			{
				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener EstadoOI por Id", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();	
			}
		} finally
		{
			try
			{
				if (session != null) session.close();
			} catch (HibernateException e1)
			{

				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener EstadoOI por Id", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();	
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_EstadoOIDAO buscarPorId - FIN***"); 
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();	
		return estadoOI;
	}
}
