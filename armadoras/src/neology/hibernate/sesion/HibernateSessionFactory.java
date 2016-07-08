package neology.hibernate.sesion;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import neology.util.FechaUtil;
import neology.util.Utilidades;

/**
 * Configures and provides access to Hibernate sessions, tied to the current
 * thread of execution. Follows the Thread Local Session pattern, see
 * {@link http://hibernate.org/42.html }.
 */
public class HibernateSessionFactory {

	/**
	 * Location of hibernate.cfg.xml file. Location should be on the classpath
	 * as Hibernate uses #resourceAsStream style lookup for its configuration
	 * file. The default classpath location of the hibernate config file is in
	 * the default package. Use #setConfigFile() to update the location of the
	 * configuration file for the current session.
	 */
	private static String CONFIG_FILE_LOCATION = "/neology/hibernate/configuracion/hibernate.cfg.xml";
	private static String CONFIG_FILE_LOCATIONRESP = "/neology/hibernate/configuracion/hibernateRespaldo.cfg.xml";
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private static final ThreadLocal<Session> threadLocalResp = new ThreadLocal<Session>();
	private static Configuration configuration = new Configuration();
	private static Configuration configurationResp = new Configuration();
	private static org.hibernate.SessionFactory sessionFactory;
	private static org.hibernate.SessionFactory sessionFactoryResp;
	private static String configFile = CONFIG_FILE_LOCATION;
	private static String configFileResp = CONFIG_FILE_LOCATIONRESP;
	public static String strFolderWarnLogs = "";
	public static String strFolderErrLogs = "";
	public static String strDebugLogs = "";

	private HibernateSessionFactory() {
	}

	/**
	 * Returns the ThreadLocal Session instance. Lazy initialize the
	 * <code>SessionFactory</code> if needed.
	 *
	 * @return Session
	 * @throws HibernateException
	 * @throws IOException 
	 */
	public static Session getSession() throws HibernateException {
		
		Session session = null;

		try {
		
			session = (Session) threadLocal.get();

			if (session == null || !session.isOpen()) {
				if (sessionFactory == null) {
					rebuildSessionFactory();
				}
				session = (sessionFactory != null) ? sessionFactory.openSession() : null;
				threadLocal.set(session);
			}
			
			return session;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return session;
	}
	
	public static Session getSessionResp() throws HibernateException {
		
		Session session = null;

		try {
		
			session = (Session) threadLocalResp.get();

			if (session == null || !session.isOpen()) {
				if (sessionFactoryResp == null) {
					rebuildSessionFactoryResp();
				}
				session = (sessionFactoryResp != null) ? sessionFactoryResp.openSession() : null;
				threadLocalResp.set(session);
			}
			
			return session;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return session;
	}

	/**
	 * Rebuild hibernate session factory
	 *
	 */
	public static void rebuildSessionFactory() {
		
		
		try {
			configuration.configure(configFile);
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			//log.info("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}
	
	public static void rebuildSessionFactoryResp() {
		
		
		try {
			configurationResp.configure(configFileResp);
			sessionFactoryResp = configurationResp.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactoryResp %%%%");
			//log.info("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}

	/**
	 * Close the single hibernate session instance.
	 *
	 * @throws HibernateException
	 */
	public static void closeSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);

		if (session != null) {
			session.close();
		}
	}
	
	public static void closeSessionResp() throws HibernateException {
		Session session = (Session) threadLocalResp.get();
		threadLocalResp.set(null);

		if (session != null) {
			session.close();
		}
	}

	/**
	 * return session factory
	 *
	 */
	public static org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static org.hibernate.SessionFactory getSessionFactoryResp() {
		return sessionFactoryResp;
	}

	/**
	 * return session factory
	 *
	 * session factory will be rebuilded in the next call
	 */
	public static void setConfigFile(String configFile) {
		HibernateSessionFactory.configFile = configFile;
		sessionFactory = null;
	}
	
	public static void setConfigFileResp(String configFileResp) {
		HibernateSessionFactory.configFileResp = configFileResp;
		sessionFactoryResp = null;
	}

	/**
	 * return hibernate configuration
	 *
	 */
	public static Configuration getConfiguration() {
		return configuration;
	}
	
	public static Configuration getConfigurationResp() {
		return configurationResp;
	}
	

}