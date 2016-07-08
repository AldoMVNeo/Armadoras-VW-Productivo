package neology.modelo.negocio.daos;

import java.util.ArrayList;
import java.util.List;

import neology.hibernate.sesion.HibernateSessionFactory;
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

public class UsuarioDAO extends BaseHibernateDAO {
    private static final String LOGIN="usuario";
    private static final String PERFIL="perfil.idPerfil";
    public UsuarioDAO() {
    }

    public Usuario buscarPorId(Long idUsuario) {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_UsuarioDAO buscarPorId - INICIO***");
        Session session = null;
        Transaction tx=null;
        Usuario usuario = null;
        try {
            session = this.getSession();
            usuario = (Usuario)session.load(Usuario.class, idUsuario);
            tx = session.beginTransaction();
            tx.commit();
        } catch (HibernateException re) {
            loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Usuario Por Id ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Usuario Por Id ",e1);
                    loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
                }
        } finally {
            try {
                if (session != null)
                    session.close();
            } catch (HibernateException e1) {

                e1.printStackTrace();
                loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener Usuario Por Id ",e1);
                loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_UsuarioDAO buscarPorId - FIN***");
        loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
        return usuario;
    }
    
    public List buscarUsuarios() {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_UsuarioDAO buscarUsuarios - INICIO***");
        Query query;
        Transaction tx=null;
        Session session=null;
        List usuarios = new ArrayList();
        try {
            session = this.getSession();
            tx = session.beginTransaction();
//            String consulta = " from Usuario as u Where u.estatus ='T' order by nombre" ;
            String consulta = " from Usuario as u where u.estatus!='2' and u.idUsuario>1" ;
            query = session.createQuery(consulta);
            usuarios = query.list();
            tx.commit();
        } catch (HibernateException re) {
            loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Usuarios ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Usuarios ",e1);
                    loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
                }
        } finally {
            try {
                if (session != null)
                    session.close();
            } catch (HibernateException e1) {

                e1.printStackTrace();
                loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Usuarios ",e1);
                loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_UsuarioDAO buscarUsuarios - FIN***");
        loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
        return usuarios;
    }
    
    public boolean guardar(Usuario usuario) {
    
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_UsuarioDAO guardar - INICIO***");
        Session session = null;
        Transaction tx = null;
        boolean exitoso;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            session.save(usuario);
            tx.commit();
            exitoso = true;
        } catch (HibernateException re) {
            exitoso = false;
            loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar datos del nuevo Usuario ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    exitoso = false;
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar datos del nuevo Usuario ",e1);
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
                loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al guardar datos del nuevo Usuario ",e1);
                loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_UsuarioDAO guardar - FIN***");
        loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
        return exitoso;
    }
    public List buscarPorLogin(Object login){
    	return buscarPorPropiedad(LOGIN,login);
    }
    
    public List buscarPorPerfil(Object perfil){
    	return buscarPorPropiedad(PERFIL,perfil);
    }
    private List buscarPorPropiedad(String propiedad, Object value) {
    	
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_UsuarioDAO buscarPorPropiedad - INICIO***");
        Session session = null;
        Transaction tx = null;
        List usuarios = null;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            String queryString = 
                "from Usuario as model where model." + propiedad + "= ?";
            Query queryObject = session.createQuery(queryString);
            queryObject.setParameter(0, value);
            usuarios= queryObject.list();
            tx.commit();
        } catch (HibernateException re) {
            loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  Usuario ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al buscar  Usuario ",e1);
                    loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
                }
        } finally {
            try {
                if (session != null)
                    session.close();
            } catch (HibernateException e1) {

                e1.printStackTrace();
                loggerInfoDAO.info(FechaUtil.getHoraActual()+"_Fallo al buscar  Usuario ",e1);
                loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_UsuarioDAO buscarPorPropiedad - FIN***");
        loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
        return usuarios;
    }
    public boolean actualizar(Usuario usuario) {
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_UsuarioDAO actualizar - INICIO***");
        Session session = null;
        Transaction tx = null;
        boolean exitoso;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            session.update(usuario);
            tx.commit();
            exitoso = true;
        } catch (HibernateException re) {
            exitoso = false;
            loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al actualizar los datos Usuario  ",re);
            re.printStackTrace();
            loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    exitoso = false;
                    e1.printStackTrace();
                    loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al actualizar los datos Usuario  ",e1);
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
                loggerInfoDAO.info(FechaUtil.getHoraActual()+"_Fallo al actualizar los datos Usuario  ",e1);
                loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
            }
        }
        loggerInfoDAO.info(FechaUtil.getHoraActual()+"_UsuarioDAO actualizar - FIN***");
        loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
        return exitoso;
    }    
    
    
    public List buscarUsuariosPantallaConstrasena(Usuario us) {
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_UsuarioDAO buscarUsuariosPantallaConstrasena - INICIO***");
		Query query;
		Transaction tx = null;
		Session session = null;
		List usuarios = new ArrayList();
		String consulta ="";
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			// String consulta = " from Usuario as u Where u.estatus ='T' order
			// by nombre" ;
			
			if(us.getPerfil().getIdPerfil()==1){			
				 consulta =   " from Usuario as u where u.estatus!="+
				               EstadoCatalogo.ELIMINADO + " and u.idUsuario>0";	
				}
			
			if(us.getPerfil().getIdPerfil()==2){			
			 consulta =   " from Usuario as u where u.estatus!="+
					       EstadoCatalogo.ELIMINADO + " and u.entidad.idEntidad="+us.getEntidad().getIdEntidad()+" and u.idUsuario>1";	
			}
			
			if(us.getPerfil().getIdPerfil()!=1 && us.getPerfil().getIdPerfil()!=2){			
				 consulta =   " from Usuario as u where u.estatus!="+
						       EstadoCatalogo.ELIMINADO + " and u.idUsuario="+us.getIdUsuario();	
				}

			query = session.createQuery(consulta);
			usuarios = query.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Usuarios ",re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Usuarios ",e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {
				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Usuarios ",e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_UsuarioDAO buscarUsuariosPantallaConstrasena - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return usuarios;
	}
    
    public boolean actualizarEstado(Long idPerfil, Integer estadoNuevo) {
    	Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
    	loggerInfoDAO.info(FechaUtil.getHoraActual()+"_UsuarioDAO actualizarEstado - INICIO***");
    	Session session = null;
		Transaction tx = null;
		String consulta = "";
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			consulta = "update Usuario set estatus=" + estadoNuevo
					+ " where id_perfil=" + idPerfil + " and id_usuario!=1 and estatus in("+EstadoCatalogo.ACTIVO+","+EstadoCatalogo.INACTIVO+")";
			int query = session.createQuery(consulta).executeUpdate();
			tx.commit();
		} catch (Exception e1) {
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo actualizarEstado ",e1);
			e1.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			return false;
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {
				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo actualizarEstado ",e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
				return false;
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_UsuarioDAO actualizarEstado - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return true;
	}
    
	public List buscarUsuariosPantalla(Usuario us) {
		
		Logger loggerInfoDAO = Utilidades.loggerInfoDAO();
		Logger loggerErrorDAO = Utilidades.loggerErrorDAO();
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_UsuarioDAO buscarUsuariosPantalla - INICIO***");
		Query query;
		Transaction tx = null;
		Session session = null;
		List usuarios = new ArrayList();
		String consulta ="";
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			// String consulta = " from Usuario as u Where u.estatus ='T' order
			// by nombre" ;
			if(us.getIdUsuario()!=1){			
			 consulta =   " from Usuario as u where estatus!="+
					       EstadoCatalogo.ELIMINADO + " and u.entidad.idEntidad="+us.getEntidad().getIdEntidad()+" and u.idUsuario>1";	
			}else{			
			 consulta =   " from Usuario as u where estatus!="+
			               EstadoCatalogo.ELIMINADO + " and u.idUsuario>0";	
			}
			query = session.createQuery(consulta);
			usuarios = query.list();
			tx.commit();
		} catch (HibernateException re) {
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Usuarios ",re);
			re.printStackTrace();
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
			if (tx != null)
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Usuarios ",e1);
					loggerInfoDAO.removeAllAppenders();
					loggerErrorDAO.removeAllAppenders();
				}
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {
				e1.printStackTrace();
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo al obtener lista de Usuarios ",e1);
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
			}
		}
		loggerInfoDAO.info(FechaUtil.getHoraActual()+"_UsuarioDAO buscarUsuariosPantalla - FIN***");
		loggerInfoDAO.removeAllAppenders();
		loggerErrorDAO.removeAllAppenders();
		return usuarios;
	}
    
}
