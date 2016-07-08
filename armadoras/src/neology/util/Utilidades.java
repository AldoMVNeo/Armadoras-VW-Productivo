package neology.util;

import java.io.File;
import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.SimpleLayout;
import org.joda.time.DateTime;

import neology.hibernate.sesion.HibernateSessionFactory;

public class Utilidades {

	public Utilidades() {
	}

	// Metodo devuelve el Contexto de la Aplicacion

	public String getContexto(HttpServletRequest rqt) {
		return rqt.getContextPath().equals("/") ? "" : rqt.getContextPath();
	}

	// Valida un folio

	public static boolean isFolioValido(String formatoFolio, String folio) {

		Matcher m = null;

		try {

			String gato = "[0-9]";
			String X = "[ABCDEFGHJKLMNPRSTUVWXYZ]";
			String guion = "[-]";
			String Exp = "^";
			for (int i = 0; i < formatoFolio.length(); i++) {
				if (formatoFolio.charAt(i) == '#')
					Exp = Exp + gato;
				else if (Character.isDigit(formatoFolio.charAt(i)))
					Exp = Exp + formatoFolio.charAt(i);
				else if (Character.isLetter(formatoFolio.charAt(i)))
					Exp = Exp + formatoFolio.charAt(i);
				else if (formatoFolio.charAt(i) == '@')
					Exp = Exp + X;
				else if (formatoFolio.charAt(i) == '-')
					Exp = Exp + guion;
			}

			Pattern p = Pattern.compile(Exp + "$");

			m = p.matcher(folio);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return m.find();
	}

	/**
	 * Envía una respuesta xml
	 * 
	 * @param response
	 * @param respuesta
	 */

	public static void escribirXML(HttpServletResponse response, String contenido) {
		
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("ISO-8859-1");
		try {
			
			response.getWriter().write(contenido);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isParametroValido(String varParametro) {

		String gato = "[0-9]";
		Boolean digito = true;

		for (int i = 0; i < varParametro.length(); i++) {
			if (!Character.isDigit(varParametro.charAt(i)))
				digito = false;
		}

		return digito;
	}

	public static String folderLogInfo() {

		File logsDir = new File(VariablesGlobales.getDirLogs() + "/logs/logs_repuve/info/2016");

		if (!logsDir.exists()) {
			System.out.println("Creando directorio de logs INFO");
			boolean boolResultado = false;

			try {
				logsDir.mkdirs();
				boolResultado = true;
			} catch (SecurityException se) {
				se.printStackTrace();
			}
			if (boolResultado) {
				System.out.println("Directorio creado");
			}
		}

		return logsDir.getAbsolutePath().replace("\\", "//");
	}

	public static String folderLogWarning() {

		File logsDir = new File(VariablesGlobales.getDirLogs() + "/logs/logs_repuve/warning/" + FechaUtil.getYear());

		if (!logsDir.exists()) {
			System.out.println("Creando directorio de logs WARN");
			boolean boolResultado = false;

			try {
				logsDir.mkdirs();
				boolResultado = true;
			} catch (SecurityException se) {
				se.printStackTrace();
			}
			if (boolResultado) {
				System.out.println("Directorio creado");
			}
		}

		return logsDir.getAbsolutePath().replace("\\", "//");
	}

	public static String folderLogError() {

		File logsDir = new File(VariablesGlobales.getDirLogs() + "/logs/logs_repuve/error/" + FechaUtil.getYear());

		if (!logsDir.exists()) {
			System.out.println("Creando directorio de logs ERROR");
			boolean boolResultado = false;

			try {
				logsDir.mkdirs();
				boolResultado = true;
			} catch (SecurityException se) {
				se.printStackTrace();
			}
			if (boolResultado) {
				System.out.println("Directorio creado");
			}
		}

		return logsDir.getAbsolutePath().replace("\\", "//");
	}

	public static String folderLogDebug() {

		File logsDir = new File(VariablesGlobales.getDirLogs() + "/logs/logs_repuve/debug/" + FechaUtil.getYear());

		if (!logsDir.exists()) {
			System.out.println("Creando directorio de logs DEBUG");
			boolean boolResultado = false;

			try {
				logsDir.mkdirs();
				boolResultado = true;
			} catch (SecurityException se) {
				se.printStackTrace();
			}
			if (boolResultado) {
				System.out.println("Directorio creado");
			}
		}

		return logsDir.getAbsolutePath().replace("\\", "//");
	}

	public static Logger loggerInfo() {

		BasicConfigurator.configure();
		Logger loggerInfo = Logger.getLogger("infoLog");
		
		loggerInfo.setLevel(Level.INFO);
		loggerInfo.setAdditivity(false);
		
		String strInfoLogs=Utilidades.folderLogInfo();
		
		
		RollingFileAppender appender = new RollingFileAppender();
		
		try {
			
			appender = new RollingFileAppender(new SimpleLayout(), strInfoLogs + "//repuve_info_"+FechaUtil.getDateLog()+".log");
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		appender.setAppend(true);
		appender.setMaxBackupIndex(1000);
		appender.setMaxFileSize("5000KB");
		loggerInfo.addAppender(appender);

		return loggerInfo;
	}
	
	public static Logger loggerError() {

		BasicConfigurator.configure();
		Logger loggerError = Logger.getLogger("errorLog");
		loggerError.setAdditivity(false);
		
		loggerError.setLevel(Level.ERROR);
		
		String strErrorLogs=Utilidades.folderLogError();
		
		RollingFileAppender appender = new RollingFileAppender();
		
		try {
			
			appender = new RollingFileAppender(new SimpleLayout(), strErrorLogs + "//repuve_error_"+FechaUtil.getDateLog()+".log");
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		appender.setMaxBackupIndex(1000);
		appender.setMaxFileSize("5000KB");
		loggerError.addAppender(appender);

		return loggerError;
	}
	
	
	public static Logger loggerInfoDAO() {

		BasicConfigurator.configure();
		Logger loggerInfoDAO = Logger.getLogger("infoLogDAO");
		
		loggerInfoDAO.setLevel(Level.INFO);
		loggerInfoDAO.setAdditivity(false);
		
		String strInfoLogs=Utilidades.folderLogInfo();
		
		RollingFileAppender appender = new RollingFileAppender();
		
		try {
			
			appender = new RollingFileAppender(new SimpleLayout(), strInfoLogs + "//repuve_info_"+FechaUtil.getDateLog()+".log");
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		appender.setAppend(true);
		appender.setMaxBackupIndex(100);
		appender.setMaxFileSize("5000KB");
		loggerInfoDAO.addAppender(appender);

		return loggerInfoDAO;
	}
	
	
	public static Logger loggerErrorDAO() {

		BasicConfigurator.configure();
		Logger loggerErrorDAO = Logger.getLogger("errorLogDAO");
		
		loggerErrorDAO.setLevel(Level.ERROR);
		loggerErrorDAO.setAdditivity(false);
		
		String strErrorLogs=Utilidades.folderLogError();
		
		RollingFileAppender appender = new RollingFileAppender();
		
		try {
			
			appender = new RollingFileAppender(new SimpleLayout(), strErrorLogs + "//repuve_error_"+FechaUtil.getDateLog()+".log");
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		appender.setAppend(true);
		appender.setMaxBackupIndex(100);
		appender.setMaxFileSize("5000KB");
		loggerErrorDAO.addAppender(appender);

		return loggerErrorDAO;
	}
	
	

}
