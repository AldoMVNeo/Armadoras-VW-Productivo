package neology.modelo.negocio.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.modelo.dto.Entidad;
import neology.modelo.dto.Estado;
import neology.modelo.dto.Formato;
import neology.modelo.dto.FormatoHistorico;
import neology.modelo.dto.OrdenImpresion;
import neology.modelo.dto.OrdenesHistorico;
import neology.modelo.dto.id.FormatoId;
import neology.modelo.negocio.servicios.DAOFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import neology.util.VariablesGlobales;
import neology.vc.forms.reportes.ReporteGeneralDatosForm;

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

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class FormatoDAO extends BaseHibernateDAO {
    private int numeroMaximo;
    public static final String FOLIO = "folio";
    public FormatoDAO() {
    }

    /**Busca un Formato por Id**/
    public Formato buscarPorId(FormatoId id) {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO buscarPorId - INICIO"); 
        Session session = null;
        Transaction tx = null;
        Formato titulo = null;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            titulo = (Formato)session.get(Formato.class, id);
            tx.commit();

        } catch (HibernateException re) {
        	loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Formato ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();	
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Formato ",e1);
                    loggerInfoDAO.removeAllAppenders();
        			loggerErrorDAO.removeAllAppenders();	
                }
        } finally {
            try {
                if (session != null)
                    session.close();
            } catch (HibernateException e1) {
                e1.printStackTrace();
                loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Formato ",e1);
                loggerInfoDAO.removeAllAppenders();
    			loggerErrorDAO.removeAllAppenders();	
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO buscarPorId - FIN"); 
        loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();	
        return titulo;
    }

    /**Guarda un Formato**/
    public boolean guardar(Formato formato) {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO guardar - INICIO"); 
        Session session = null;
        Transaction tx = null;
        boolean exitoso;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            session.save(formato);
            tx.commit();
            exitoso = true;
        } catch (HibernateException re) {
            exitoso = false;
            loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar Constancia de Inscripcion ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();	
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    exitoso = false;
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar Constancia de Inscripcion ",e1);
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
                loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar Constancia de Inscripcion ",e1);
                loggerInfoDAO.removeAllAppenders();
    			loggerErrorDAO.removeAllAppenders();	
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO guardar - FIN"); 
        loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();	
        return exitoso;
    }

   /*
    * Actualiza un Formato
    */

    public boolean actualizar(Formato formato) {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO actualizar - INICIO"); 
        Session session = null;
        Transaction tx = null;
        boolean exitoso;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            session.update(formato);
            tx.commit();
            exitoso = true;
        } catch (HibernateException re) {
            exitoso = false;
            loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar Titulo por Propiedad ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
    		loggerErrorDAO.removeAllAppenders();	
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    exitoso = false;
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar Titulo por Propiedad ",e1);
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
                loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar Titulo por Propiedad ",e1);
                loggerInfoDAO.removeAllAppenders();
        		loggerErrorDAO.removeAllAppenders();	
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO actualizar - FIN");
        loggerInfoDAO.removeAllAppenders();
      	loggerErrorDAO.removeAllAppenders();	
        return exitoso;
    }

    /**Busca un Formato por algun otro campo**/

    private List buscarPorPropiedad(String propiedad, Object value) {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO buscarPorPropiedad - INICIO");
        Session session = null;
        Transaction tx = null;
        List titulos = null;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            String queryString = 
                "from Formato as model where model." + propiedad + "= ?";
            Query queryObject = session.createQuery(queryString);
            queryObject.setParameter(0, value);
            titulos = queryObject.list();
            tx.commit();
        } catch (HibernateException re) {
        	loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  Formato ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
          	loggerErrorDAO.removeAllAppenders();	
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  Formato ",e1);
                    loggerInfoDAO.removeAllAppenders();
                  	loggerErrorDAO.removeAllAppenders();	
                }
        } finally {
            try {
                if (session != null)
                    session.close();
            } catch (HibernateException e1) {

                e1.printStackTrace();
                loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  Formato ",e1);
                loggerInfoDAO.removeAllAppenders();
              	loggerErrorDAO.removeAllAppenders();	
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO buscarPorPropiedad - FIN");
        loggerInfoDAO.removeAllAppenders();
      	loggerErrorDAO.removeAllAppenders();	
        return titulos;
    }

public List buscarPorFolio(Object folio){
	return buscarPorPropiedad(FOLIO,folio);
}
    //Busca formatos en un rango de folios

    public List buscarPorRangoFolios(String folioInicial, String folioFinal, 
                                     long idTipoFormato) {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO buscarPorRangoFolios - INICIO");
        Session session = null;
        Transaction tx = null;
        List titulos = null;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            String queryString = 
                "from Formato as model where model.formatoId.folio between '" + 
                folioInicial + "' and '" + folioFinal + 
                "' and model.formatoId.idTipoFormato=" + idTipoFormato       +
                " and model.estado != '5' ";
            Query queryObject = session.createQuery(queryString);
            if (numeroMaximo > 0)
                queryObject.setMaxResults(numeroMaximo);
            titulos = queryObject.list();
            tx.commit();
        } catch (HibernateException re) {
            loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  Formato ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
          	loggerErrorDAO.removeAllAppenders();	
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  Formato ",e1);
                    loggerInfoDAO.removeAllAppenders();
                  	loggerErrorDAO.removeAllAppenders();	
                }
        } finally {
            try {
                if (session != null)
                    session.close();
            } catch (HibernateException e1) {

                e1.printStackTrace();
                loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  Formato ",e1);
                loggerInfoDAO.removeAllAppenders();
              	loggerErrorDAO.removeAllAppenders();	
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO buscarPorRangoFolios - FIN");
        loggerInfoDAO.removeAllAppenders();
      	loggerErrorDAO.removeAllAppenders();	
        return titulos;
    }
   

    public void setNumeroMaximo(int numeroMaximo) {
        this.numeroMaximo = numeroMaximo;
    }

    public int getNumeroMaximo() {
        return numeroMaximo;
    }

    //Busca formatos en un rango de folios, entidad y estado
    
    public List validaPorRangoFolios(String folioInicial, String folioFinal, 
                                     long idTipoFormato, long idEntidad, 
                                     String estado, int folios) {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO validaPorRangoFolios - INICIO");
        Session session = null;
        Transaction tx = null;
        List titulos = null;
        int cuentaFormatos = 0;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            String queryString = 
                "select count(*) from Formato as model where model.formatoId.folio between '" + 
                folioInicial + "' and '" + folioFinal + 
                "' and model.formatoId.idTipoFormato=" + idTipoFormato +
                " and model.entidad = '" + idEntidad +
                "' and model.estado = '" + estado + "'";
            List totalFormatos= session.createQuery(queryString).list();
            tx.commit();
            Iterator iter = totalFormatos.iterator();
            while (iter.hasNext())
            {
                cuentaFormatos= (Integer) iter.next();                                                
                break;
            }                                                          
            if (cuentaFormatos < folios){
                session = this.getSession();
                tx = session.beginTransaction();
                queryString = 
                    "from Formato as model where model.formatoId.folio between '" + 
                    folioInicial + "' and '" + folioFinal + 
                    "' and model.formatoId.idTipoFormato=" + idTipoFormato +
                    " and (model.entidad != " + idEntidad +
                    " OR model.estado != '" + estado + "')";
                titulos = session.createQuery(queryString).list();
                tx.commit();
                if(titulos.size()== 0){                    
                    titulos.add("error");
                }                    
            }
        

        } catch (HibernateException re) {
            loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  Formato ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
          	loggerErrorDAO.removeAllAppenders();	
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  Formato ",e1);
                    loggerInfoDAO.removeAllAppenders();
                  	loggerErrorDAO.removeAllAppenders();	
                }
        } finally {
            try {
                if (session != null)
                    session.close();
            } catch (HibernateException e1) {

            	 loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  Formato ",e1);
                e1.printStackTrace();
                loggerInfoDAO.removeAllAppenders();
              	loggerErrorDAO.removeAllAppenders();	
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO validaPorRangoFolios - FIN");
        loggerInfoDAO.removeAllAppenders();
      	loggerErrorDAO.removeAllAppenders();	
        return titulos;
    }
    
    /*
     * Actualiza un Rango de Formatos
     */

     public boolean cambiarEstadoRango(long idTipoFormato,String folioInicial, String folioFinal, String estadoNuevo,Long idUsuario) {
        
    	 Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
 		 Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	 
    	 loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO cambiarEstadoRango - INICIO");
    	 Session session = null;
         Transaction tx = null;
         boolean exitoso;         
         try {
             session = this.getSession();
             tx = session.beginTransaction();
             String consulta = 
                 "update Formato set estado = '" + estadoNuevo + "', id_usuario="+idUsuario +",fecha_registro=sysdate "+
                 " where id_Tipo_Formato=" + idTipoFormato +
                 " and folio between '" + folioInicial + "' and '" + folioFinal + "'";             
             int query = session.createQuery(consulta).executeUpdate();             
             tx.commit();
             exitoso = true;
         } catch (HibernateException re) {
             exitoso = false;
             loggerErrorDAO.error(FechaUtil.getHoraActual()+"_despues de execute Fallo al guardar Constancias de Inscripcion  ",re);
             re.printStackTrace();
             loggerInfoDAO.removeAllAppenders();
           	loggerErrorDAO.removeAllAppenders();	
             if (tx != null)
                 try {
                     tx.rollback();
                 } catch (HibernateException e1) {
                     exitoso = false;
                     e1.printStackTrace();
                     loggerErrorDAO.error(FechaUtil.getHoraActual()+"_despues de execute Fallo al guardar Constancias de Inscripcion  ",e1);
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
                 loggerErrorDAO.error(FechaUtil.getHoraActual()+"_despues de execute Fallo al guardar Constancias de Inscripcion  ",e1);
                 loggerInfoDAO.removeAllAppenders();
               	loggerErrorDAO.removeAllAppenders();	
             }
         }
         loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO cambiarEstadoRango - FIN");
         loggerInfoDAO.removeAllAppenders();
         loggerErrorDAO.removeAllAppenders();	
         return exitoso;
     }

     public boolean transferirEstadoRango(long idTipoFormato,String folioInicial, String folioFinal,
    		                              long entidadNueva,Long idUsuario) {
    	 
    	 
    	 Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
 		 Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	 
    	 loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO transferirEstadoRango - INICIO");
         Session session = null;
         Transaction tx = null;
         boolean exitoso;
         try {
             session = this.getSession();
             tx = session.beginTransaction();
             String consulta = 
                 "update Formato set id_entidad = '" + entidadNueva + "', id_usuario="+idUsuario +
                 " where id_Tipo_Formato=" + idTipoFormato +
                 " and folio between '" + folioInicial + "' and '" + folioFinal + "'";             
             int query = session.createQuery(consulta).executeUpdate();             
             tx.commit();
             exitoso = true;
         } catch (HibernateException re) {
             exitoso = false;
             loggerErrorDAO.error(FechaUtil.getHoraActual()+"_despues de execute Fallo al guardar Titulo por Propiedad ",re);
             re.printStackTrace();
             loggerInfoDAO.removeAllAppenders();
             loggerErrorDAO.removeAllAppenders();
             if (tx != null)
                 try {
                     tx.rollback();
                 } catch (HibernateException e1) {
                     exitoso = false;
                     e1.printStackTrace();
                     loggerErrorDAO.error(FechaUtil.getHoraActual()+"_despues de execute Fallo al guardar Titulo por Propiedad ",e1);
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
                 loggerErrorDAO.error(FechaUtil.getHoraActual()+"_despues de execute Fallo al guardar Titulo por Propiedad ",e1);
                 loggerInfoDAO.removeAllAppenders();
                 loggerErrorDAO.removeAllAppenders();
             }
         }
         loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO transferirEstadoRango - FIN");
         loggerInfoDAO.removeAllAppenders();
         loggerErrorDAO.removeAllAppenders();
         return exitoso;
     }

     public Integer totalFormatos(long idEntidad) {
    	 
    	 Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
 		 Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	 
    	 loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO totalFormatos - INICIO");
    	 Session session = null;
    	 Transaction tx = null;
    	 List titulos = null;
    	 int cuentaFormatos = 0;
    	 try {
    		 session = this.getSession();
    		 tx = session.beginTransaction();
    		 String queryString = 
    			 "select count(*) from Formato as model where model.estado = '0'" ;
    		 List totalFormatos= session.createQuery(queryString).list();
    		 tx.commit();
    		 Iterator iter = totalFormatos.iterator();
    		 while (iter.hasNext())
    		 {
    			 cuentaFormatos= (Integer) iter.next();                                                
    			 break;
    		 }                                                              		 	 
    	 } catch (HibernateException re) {
    		 loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  el total de formatos disponibles ",re);
    		 re.printStackTrace();
    		 loggerInfoDAO.removeAllAppenders();
             loggerErrorDAO.removeAllAppenders();
    		 if (tx != null)
    			 try {
    				 tx.rollback();
    			 } catch (HibernateException e1) {
    				 e1.printStackTrace();
    				 loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  el total de formatos disponibles ",e1);
    				 loggerInfoDAO.removeAllAppenders();
    		         loggerErrorDAO.removeAllAppenders();
    			 }
    	 } finally {
    		 try {
    			 if (session != null)
    				 session.close();
    		 } catch (HibernateException e1) {
    			 e1.printStackTrace();
    			 loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  el total de formatos disponibles ",e1);
    			 loggerInfoDAO.removeAllAppenders();
    	         loggerErrorDAO.removeAllAppenders();
    		 }
    	 }
    	 loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO totalFormatos - FIN");
    	 loggerInfoDAO.removeAllAppenders();
         loggerErrorDAO.removeAllAppenders();
    	 return cuentaFormatos;
     }
     /**
      * totalFormatosPorFecha generado para el reporte de totales
      * @param idEntidad
      * @return
      */
     public Integer totalFormatosPorFecha(String estado,String fechaInicial,String fechaFinal) {
    	 
    	 Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
 		 Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	 
    	 loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO totalFormatosPorFecha - INICIO");
    	 Session session = null;
    	 Transaction tx = null;    	
    	 int cuentaFormatos = 0;
    	 try {
    		 session = this.getSession();
    		 tx = session.beginTransaction();
    		 String queryString = 
    			 "select count(*) from Formato as model where model.estado ='"+estado +"' and to_date(to_char(model.fechaRegistro,'dd/MM/yyyy'),'dd/MM/yyyy') between to_date('"+fechaInicial+"','dd/MM/yyyy') and  to_date('"+fechaFinal+"','dd/MM/yyyy')";
    		 List totalFormatos= session.createQuery(queryString).list();
    		 tx.commit();
    		 Iterator iter = totalFormatos.iterator();
    		 while (iter.hasNext())
    		 {
    			 cuentaFormatos= (Integer) iter.next();                                                
    			 break;
    		 }                                                              		 	 
    	 } catch (HibernateException re) {
    		 loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  el total de formatos disponibles por fecha  ",re);
    		 re.printStackTrace();
    		 loggerInfoDAO.removeAllAppenders();
             loggerErrorDAO.removeAllAppenders();
    		 if (tx != null)
    			 try {
    				 tx.rollback();
    			 } catch (HibernateException e1) {
    				 e1.printStackTrace();
    				 loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  el total de formatos disponibles por fecha  ",e1);
    				 loggerInfoDAO.removeAllAppenders();
    		         loggerErrorDAO.removeAllAppenders();
    			 }
    	 } finally {
    		 try {
    			 if (session != null)
    				 session.close();
    		 } catch (HibernateException e1) {
    			 e1.printStackTrace();
    			 loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  el total de formatos disponibles por fecha  ",e1);
    			 loggerInfoDAO.removeAllAppenders();
    	         loggerErrorDAO.removeAllAppenders();
    		 }
    	 }
    	 loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO totalFormatosPorFecha - FIN");
    	 loggerInfoDAO.removeAllAppenders();
         loggerErrorDAO.removeAllAppenders();
    	 return cuentaFormatos;
     }
     
     public boolean ActualizarGrupo(String fechaInicial,String fechaFinal,int grupo, int estado, long idUsuario) {
       
    	 Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
 		 Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	 
    	 loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO ActualizarGrupo - INICIO");
    	 Session session = null;
         Transaction tx = null;
         boolean exitoso;
         try {
             session = this.getSession();
             tx = session.beginTransaction();
             String consulta ="update Formato set estado = '9', id_usuario ="+idUsuario 
                               + ", fecha_registro=sysdate"
                               + " where estado = '8' "
             					+ " and exists (select 1 from OrdenImpresion as oi "  
             					+ " where oi.estado =" + estado 
             					+ " and oi.grupo = " + grupo
             					+ " and oi.folio = folio "  
//             					+ " and oi.idTipoFormato = id_tipo_formato "
//             					+ " and to_date (to_char(oi.fechaRegistro,'dd/MM/yyyy'),'dd/MM/yyyy') between to_date('"+fechaInicial+"','dd/MM/yyyy') and  to_date('"+fechaFinal+"','dd/MM/yyyy'))"; 
               					+ " and oi.fechaRegistro between to_timestamp('"+fechaInicial+" 00:00:00', 'dd-mm-yyyy hh24:mi:ss') and  to_timestamp('"+fechaFinal+" 23:59:59', 'dd-mm-yyyy hh24:mi:ss'))";            					
                              
             int query = session.createQuery(consulta).executeUpdate();             
             tx.commit();
             exitoso = true;
         } catch (HibernateException re) {
             exitoso = false;
             loggerErrorDAO.error(FechaUtil.getHoraActual()+"_despues de execute Fallo al guardar Titulo por Propiedad ",re);
             re.printStackTrace();
             loggerInfoDAO.removeAllAppenders();
             loggerErrorDAO.removeAllAppenders();
             if (tx != null)
                 try {
                     tx.rollback();
                 } catch (HibernateException e1) {
                     exitoso = false;
                     e1.printStackTrace();
                     loggerErrorDAO.error(FechaUtil.getHoraActual()+"_despues de execute Fallo al guardar Titulo por Propiedad ",e1);
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
                 loggerErrorDAO.error(FechaUtil.getHoraActual()+"_despues de execute Fallo al guardar Titulo por Propiedad ",e1);
                 loggerInfoDAO.removeAllAppenders();
                 loggerErrorDAO.removeAllAppenders();
             }
         }
         loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO ActualizarGrupo - FIN");
         loggerInfoDAO.removeAllAppenders();
         loggerErrorDAO.removeAllAppenders();
         return exitoso;
     }
     
     
     public List buscarPorEstado(String fechaInicial, String fechaFinal, 
             String estado) {
    	 
    	 Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
 		 Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	 
    	 loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO buscarPorEstado - INICIO");
    	 Session session = null;
    	 Transaction tx = null;
    	 List formatos = null;
    	 try {
    		 session = this.getSession();
    		 tx = session.beginTransaction();
    		 String queryString = 
    			 "from Formato as model where model.estado =" + estado  
    			 + " and to_char(model.fechaRegistro,'dd/MM/yyyy') between '"+fechaInicial+"' and '"+fechaFinal+"' "; 
    			 formatos= session.createQuery(queryString).list();
        		 tx.commit();
    		 
    	 } catch (HibernateException re) {
    		 loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  Formatos ",re);
    		 re.printStackTrace();
    		 if (tx != null)
    			 try {
    				 tx.rollback();
    			 } catch (HibernateException e1) {
    				 e1.printStackTrace();
    				 loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  Formatos ",e1);
    				 loggerInfoDAO.removeAllAppenders();
    		         loggerErrorDAO.removeAllAppenders();
    			 }
    	 } finally {
    		 try {
    			 if (session != null)
    				 session.close();
    		 } catch (HibernateException e1) {
    			 e1.printStackTrace();
    			 loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  Formatos ",e1);
    			 loggerInfoDAO.removeAllAppenders();
    	         loggerErrorDAO.removeAllAppenders();
    		 }
    	 }
    	 loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO buscarPorEstado - FIN");
    	 loggerInfoDAO.removeAllAppenders();
         loggerErrorDAO.removeAllAppenders();
    	 return formatos;
     }

     public boolean actualizarPorFecha(String fechaInicial, String fechaFinal,String estadoActual, String estadoNuevo,Long idUsuario) {
    	
    	 Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
 		 Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	 
    	 loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO actualizarPorFecha - INICIO");
    	 Session session = null;
         Transaction tx = null;
         boolean exitoso;         
         try {
             session = this.getSession();
             tx = session.beginTransaction();
             String consulta = 
                 "update Formato set estado = '" + estadoNuevo + "', id_usuario="+idUsuario +",fecha_registro=sysdate "+
                 " where estado = '" + estadoActual + "' and to_date (to_char(fecha_registro,'dd/MM/yyyy'),'dd/MM/yyyy') between to_date('"+fechaInicial+"','dd/MM/yyyy') and  to_date('"+fechaFinal+"','dd/MM/yyyy')"; 
                          
             int query = session.createQuery(consulta).executeUpdate();             
             tx.commit();
             exitoso = true;
         } catch (HibernateException re) {
             exitoso = false;
             loggerErrorDAO.error(FechaUtil.getHoraActual()+"_despues de execute Fallo al guardar formatos  ",re);
             re.printStackTrace();
             loggerInfoDAO.removeAllAppenders();
             loggerErrorDAO.removeAllAppenders();
             if (tx != null)
                 try {
                     tx.rollback();
                 } catch (HibernateException e1) {
                     exitoso = false;
                     e1.printStackTrace();
                     loggerErrorDAO.error(FechaUtil.getHoraActual()+"_despues de execute Fallo al guardar formatos  ",e1);
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
                 loggerErrorDAO.error(FechaUtil.getHoraActual()+"_despues de execute Fallo al guardar formatos  ",e1);
                 loggerInfoDAO.removeAllAppenders();
                 loggerErrorDAO.removeAllAppenders();
             }
         }
         loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO actualizarPorFecha - FIN");
         loggerInfoDAO.removeAllAppenders();
         loggerErrorDAO.removeAllAppenders();
         return exitoso;
     }
     
     public List<ReporteGeneralDatosForm> listReporteGeneral(String strQuery) {
    	
    	 Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
 		 Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	 
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO listReporteGeneral - INICIO");
 		Session session = null;
 		Transaction tx = null;
 		int cuentaFormatos = 0;
 		List<ReporteGeneralDatosForm> listReporteGeneral = new ArrayList<ReporteGeneralDatosForm>();
 		
 		try {
 			// fechaInicial = fechaInicial + " 00:00:00";
 			// fechaFinal = fechaFinal + " 23:59:59";
 			
 			session = this.getSession();
 			tx = session.beginTransaction();
 			Connection conn=session.connection();
 			loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO listReporteGeneral "+strQuery);
 			try {
 				
 				PreparedStatement statement=conn.prepareStatement(strQuery);
 				ResultSet rs=statement.executeQuery();
 				
 				while (rs.next()) {
 					ReporteGeneralDatosForm datosForm = new ReporteGeneralDatosForm();
 					datosForm.setFolio(rs.getString("folio"));
 					datosForm.setFecha(rs.getString("fecha"));
 					datosForm.setNiv(rs.getString("niv"));
 					datosForm.setEstadoActual(rs.getString("estadoActual"));
 					
 					if(strQuery.contains("responsable")){
 					datosForm.setResponsable(rs.getString("responsable"));
 					}else{
 					datosForm.setResponsable(null);
 					}
 					
 					if(strQuery.contains("nombreEntidad")){
 						datosForm.setEntidad(rs.getString("nombreEntidad"));
 						}else{
 						datosForm.setEntidad(null);
 						}
 					
 					datosForm.setTagId(rs.getString("tagid"));
 					datosForm.setObservaciones(rs.getString("observaciones"));
 					listReporteGeneral.add(datosForm);
 				}
 				
 			System.out.println(FechaUtil.getHoraActual()+"_FormatoDAO Termino de consultar para reporte general");	
 				
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_FormatoDAO despues de execute Fallo listReporteGeneral formatos ", e);
 				 loggerInfoDAO.removeAllAppenders();
 		         loggerErrorDAO.removeAllAppenders();
 			}
 			
 			tx.commit();
 			conn.close();
 	
 		} catch (HibernateException re) {
 			loggerErrorDAO.error(FechaUtil.getHoraActual()+
 					"_FormatoDAO Fallo al buscar el total de formatos disponibles",
 					re);
 			re.printStackTrace();
 			 loggerInfoDAO.removeAllAppenders();
 	         loggerErrorDAO.removeAllAppenders();
 			if (tx != null)
 				try {
 					tx.rollback();
 				} catch (HibernateException e1) {
 					e1.printStackTrace();
 					loggerErrorDAO.error(FechaUtil.getHoraActual()+
 		 					"_FormatoDAO Fallo al buscar el total de formatos disponibles",
 		 					e1);
 					 loggerInfoDAO.removeAllAppenders();
 			         loggerErrorDAO.removeAllAppenders();
 				}
 		} catch (SQLException e) {
 			e.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+
	 					"_FormatoDAO Fallo al buscar el total de formatos disponibles",
	 					e);
				 loggerInfoDAO.removeAllAppenders();
		         loggerErrorDAO.removeAllAppenders();
		} finally {
 			try {
 				if (session != null)
 					session.close();
 			} catch (HibernateException e1) {
 				e1.printStackTrace();
 				loggerErrorDAO.error(FechaUtil.getHoraActual()+
		 					"_FormatoDAO Fallo al buscar el total de formatos disponibles",
		 					e1);
 				 loggerInfoDAO.removeAllAppenders();
 		         loggerErrorDAO.removeAllAppenders();
 			}
 		}
   	 loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO listReporteGeneral - FIN");
   	 loggerInfoDAO.removeAllAppenders();
     loggerErrorDAO.removeAllAppenders();
 		return listReporteGeneral;
 	}
     
     public Integer intReporteGeneral(ArrayList<String> strQuery, String estado) {
    	 
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
 		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	 
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO intReporteGeneral - INICIO");
 		Session session = null;
 		Transaction tx = null;
 		int cuentaFormatos = 0;
 		try {
 			// fechaInicial = fechaInicial + " 00:00:00";
 			// fechaFinal = fechaFinal + " 23:59:59";
 			session = this.getSession();
 			tx = session.beginTransaction();
 			Connection conn=session.connection();
 			String finalQuery="";
 			
 			for(int i=0;i<strQuery.size();i++){
 				
 			finalQuery=strQuery.get(i)+estado;
 			System.out.println("EMPIEZA CONSULTA GENERAL: "+ finalQuery);
 			//List totalFormatos = session.createQuery(finalQuery).list();
 			try {
 				
 				PreparedStatement statement=conn.prepareStatement(finalQuery);
 				ResultSet rs=statement.executeQuery();
 				
 				while (rs.next()) {
 					cuentaFormatos++;
 				}
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_FormatoDAO Fallo intReporteGeneral",e);
 				loggerInfoDAO.removeAllAppenders();
 			    loggerErrorDAO.removeAllAppenders();
 			}
 			
 			System.out.println("Termino de consultar para reporte general");	
 			}
 			tx.commit();
 			conn.close();
// 			cuentaFormatos=totalFormatos.size();
 			
 	
 		} catch (HibernateException re) {
 			loggerErrorDAO.error(FechaUtil.getHoraActual()+
 					"_FormatoDAO Fallo al buscar el total de formatos disponibles",
 					re);
 			re.printStackTrace();
 			loggerInfoDAO.removeAllAppenders();
 		     loggerErrorDAO.removeAllAppenders();
 			if (tx != null)
 				try {
 					tx.rollback();
 				} catch (HibernateException e1) {
 					e1.printStackTrace();
 					loggerErrorDAO.error(FechaUtil.getHoraActual()+
 		 					"_FormatoDAO Fallo al buscar el total de formatos disponibles",
 		 					e1);
 					loggerInfoDAO.removeAllAppenders();
 				     loggerErrorDAO.removeAllAppenders();
 				}
 		} catch (SQLException e) {
 			e.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+
	 					"_FormatoDAO Fallo al buscar el total de formatos disponibles",
	 					e);
				loggerInfoDAO.removeAllAppenders();
			     loggerErrorDAO.removeAllAppenders();
		} finally {
 			try {
 				if (session != null)
 					session.close();
 			} catch (HibernateException e1) {
 				e1.printStackTrace();
 				loggerErrorDAO.error(FechaUtil.getHoraActual()+
		 					"_FormatoDAO Fallo al buscar el total de formatos disponibles",
		 					e1);
 				loggerInfoDAO.removeAllAppenders();
 			     loggerErrorDAO.removeAllAppenders();
 			}
 		}
 		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_FormatoDAO FormatoDAO intReporteGeneral - FIN");
 		loggerInfoDAO.removeAllAppenders();
 	     loggerErrorDAO.removeAllAppenders();
 		return cuentaFormatos;
 	}
     
	public Formato listarFormatoResp(String folio) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO();
		EstadoDAO estadoDAO = DAOFactory.crearEstadoDAO();
		EntidadDAO entidadDAO = DAOFactory.crearEntidadDAO();
		OrdenImpresionDAO ordenDAO =DAOFactory.crearOrdenImpresionDAO();
		
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_FormatoDAO listarFormatoResp - INICIO***");
		Session session = null;
		Boolean boolFound=false;
		Formato formato = new Formato();
		String strEsquemaRespaldo= VariablesGlobales.getEsquemaRespaldo();
		
		try {
			session = this.getSessionResp();
			Connection conn = session.connection();

			// Primero obtener tablas existentes
			String consulta = "SELECT TABLE_NAME FROM ALL_TABLES WHERE TABLE_NAME LIKE '%FORMATOS_%'"
					+ " AND TABLE_NAME NOT LIKE '%FORMATOS_HISTORICO%' "
					+ " AND OWNER='"+strEsquemaRespaldo+"'";
			
			String consultaFolio ="";

			Statement stmt = conn.createStatement();
			Statement stmtFolio = conn.createStatement();
			ResultSet rsFormatoTabla = stmt.executeQuery(consulta);
			ResultSet rsFolio = null;
			
			Long IdUsuario=(long) 0;
			String strFolio="";
			Long strIdEntidad=(long) 0;
			String strEstado="";
			String idOrdenImpresion="";
			DateTime fechaRegistro = new DateTime();

			//Por cada una, buscar el registro
			while (rsFormatoTabla.next() && !boolFound) {

				consultaFolio = "SELECT      FOLIO,"
										   + "ID_ENTIDAD,"
										   + "ESTADO,"
										   + "ID_ENTIDAD,"
										   + "FECHA_REGISTRO,"
										   + "ID_USUARIO,"
										   + "ID_ORDEN_IMPRESION "
										   + "FROM "+rsFormatoTabla.getString("TABLE_NAME")
										   + " WHERE FOLIO='"+folio+"'";

				rsFolio=stmtFolio.executeQuery(consultaFolio);
				
				if (rsFolio.next()) {				
					
					IdUsuario=rsFolio.getLong("ID_USUARIO");
					strFolio=rsFolio.getString("FOLIO");
					strIdEntidad=rsFolio.getLong("ID_ENTIDAD");
					strEstado=rsFolio.getString("ESTADO");
					idOrdenImpresion=rsFolio.getString("ID_ORDEN_IMPRESION");
					fechaRegistro = new DateTime(rsFolio.getDate("FECHA_REGISTRO"));
					
					formato.setFormatoId(new FormatoId(strFolio, 1));
					formato.setEntidad(entidadDAO.buscarPorId(strIdEntidad));
					formato.setEstado(estadoDAO.buscarPorId(strEstado));
					formato.setFechaRegistro(fechaRegistro);
					formato.setUsuarioModifico(usuarioDAO.buscarPorId((long) IdUsuario));
					boolFound=true;
				}

			}

			//Buscar datos de Orden de impresion si existe
			if(idOrdenImpresion!="" && idOrdenImpresion!=null){
			ArrayList<OrdenImpresion> listOrdenes= ordenDAO.listarOrdenResp("", idOrdenImpresion);
			formato.setOrdenImpresion(listOrdenes.get(0));
			}else{
				formato.setOrdenImpresion(null);
			}
			
			conn.close();
		
		} catch (HibernateException re) {
			re.printStackTrace();
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_FormatoDAO listarFormatoResp Fallo al obtener Formato ", re);
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			return null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loggerErrorDAO.error(FechaUtil.getHoraActual() + "_FormatoDAO listarFormatoResp Fallo al obtener Formato ", e);
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			return null;
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {
				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual() + "_FormatoDAO listarFormatoResp Fallo al obtener Formato ", e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
				return null;

			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual() + "_FormatoDAO listarFormatoResp - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return formato;
	}
	
}
