package neology.modelo.negocio.daos;

import java.util.ArrayList;
import java.util.List;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.modelo.dto.TipoFormato;
import neology.util.FechaUtil;
import neology.util.Utilidades;

import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TipoFormatoDAO extends BaseHibernateDAO {
    // Constantes
    public static final String CLAVE_TIPO_FORMATO = "claveTipoFormato";
    public static final String DESCRIPCION = "descripcion";
    public static final String FORMATO_FOLIO = "formatoFolio";
    public static final String ESTATUS = "estatus";

    public TipoFormatoDAO() {
    }

    /**
     Obtiene una lista de Tipo de Formatos disponibles
     */
    public List buscarTipoFormatos() {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_ProcedenciaDAO buscarTipoFormatos - INICIO***");
        Query query;
        Transaction tx=null;
        Session session=null;
        List tipos = new ArrayList();
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            String consulta = " from TipoFormato as tf Where tf.estatus ='T' order by " + DESCRIPCION;
            query = session.createQuery(consulta);
            tipos = query.list();
            tx.commit();
        } catch (HibernateException re) {
            loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Tipo Formatos ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Tipo Formatos ",e1);
                    loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
                }
        } finally {
            try {
                if (session != null)
                    session.close();
            } catch (HibernateException e1) {

                e1.printStackTrace();
                loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Tipo Formatos ",e1);
                loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_ProcedenciaDAO buscarTipoFormatos - FIN***");
        loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
        return tipos;
    }

    public TipoFormato buscarPorId(Long idTipoFormato) {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_ProcedenciaDAO buscarPorId - INICIO***");
        Session session = null;
        Transaction tx=null;
        TipoFormato tipoFormato = null;
        try {
            session = this.getSession();
            tipoFormato = 
                    (TipoFormato)session.load(TipoFormato.class, idTipoFormato);
            tx = session.beginTransaction();
            tx.commit();
        } catch (HibernateException re) {
            loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Tipo Formato Por Id ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Tipo Formato Por Id ",e1);
                    loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
                }
        } finally {
            try {
                if (session != null)
                    session.close();
            } catch (HibernateException e1) {

                e1.printStackTrace();
                loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Tipo Formato Por Id ",e1);
                loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_ProcedenciaDAO buscarPorId - FIN***");
        loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
        return tipoFormato;
    }
    public boolean actualizar(TipoFormato tipoFormato) {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_ProcedenciaDAO actualizar - INICIO***");
        Session session = null;
        Transaction tx = null;
        boolean exitoso;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            session.update(tipoFormato);
            tx.commit();
            exitoso = true;
        } catch (HibernateException re) {
            exitoso = false;
            loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al actualizar los datos del Tipo de Formato  ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    exitoso = false;
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al actualizar los datos del Tipo de Formato  ",e1);
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
                loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al actualizar los datos del Tipo de Formato  ",e1);
                loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_ProcedenciaDAO actualizar - FIN***");
        loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
        return exitoso;
    }    
    
    public boolean guardar(TipoFormato tipoFormato) {
    
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_ProcedenciaDAO guardar - INICIO***");
        Session session = null;
        Transaction tx = null;
        boolean exitoso;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            session.save(tipoFormato);
            tx.commit();
            exitoso = true;
        } catch (HibernateException re) {
            exitoso = false;
            loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar datos del nuevo Tipo Formato ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    exitoso = false;
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar datos del nuevo Tipo Formato ",e1);
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
                loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar datos del nuevo Tipo Formato ",e1);
                loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_ProcedenciaDAO guardar - FIN***");
        loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
        return exitoso;
    }
}
