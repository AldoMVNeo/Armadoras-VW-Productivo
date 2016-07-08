package neology.servlets.log;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;

/**
 * @version 1.0
 * @author
 */
@SuppressWarnings("serial")
public class Log4jInit extends HttpServlet
{
	public void init(ServletConfig config)
	{
		
		Logger loggerInfoDAO = Utilidades.loggerInfo();
		Logger loggerErrorDAO = Utilidades.loggerError();
		loggerInfoDAO.info(FechaUtil.getHoraActual()+
				"_Log4jInit - INICIO***");
		
		try
		{
			super.init(config);
			// Lee el directorio donde va a ser colocado el archivo de logs
			// String directory = getInitParameter("log-directory");
			// Adiciona el parametro del directorio como un Property del sistema
			// para que pueda ser utilizado dentro del archivo de configuración
			// del Log4J
			// System.setProperty("log.directory",directory);
			// Extrae el path donde se encuentra el contexto
			// Asume que el archivo de configuración se encuentra en este
			// directorio

			// Lee el nombre del archivo de configuración de Log4J
			String file = getServletContext().getRealPath(
					getInitParameter("log4j-init-file"));
			// file = "\\" + file;

			if (file == null || file.length() == 0 || !(new File(file)).isFile())
			{
				System.err.println("ERROR: No puede leer el archivo de configuracion. ");
				loggerErrorDAO.error(FechaUtil.getHoraActual()+"_  No puede leer el archivo de configuracion.");
				loggerInfoDAO.removeAllAppenders();
				loggerErrorDAO.removeAllAppenders();
				throw new ServletException();
			}

			String prefixLogFile = getServletContext().getRealPath("/WEB-INF/log");
			System.setProperty("app.root", prefixLogFile);

			// Revisa otra parametro de configuración que le indica
			// si debe revisar el archivo de log por cambios.
			String watch = config.getInitParameter("watch");

			// Extrae el parámetro que le indica cada que tiempo debe revisar
			// el archivo de configuración
			String timeWatch = config.getInitParameter("time-watch");

			// Revisa como debe realizar la configuración de Log4J y llama al
			// método adecuado
			if (watch != null && watch.equalsIgnoreCase("true"))
			{
				if (timeWatch != null)
				{
					PropertyConfigurator.configureAndWatch(file, Long
							.parseLong(timeWatch));
				}
				else
				{
					PropertyConfigurator.configureAndWatch(file);
				}
			}
			else
			{

				PropertyConfigurator.configure(file);
			}
			loggerInfoDAO.info("_Log4jInit - FIN***");
			loggerInfoDAO.info("********************    I N I C I A N D O  E L  S I S T E M A    ********************");
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
		} catch (Exception e)
		{

			e.printStackTrace();
			loggerErrorDAO.error(FechaUtil.getHoraActual()+"_Fallo Log4jInit ",e);
			loggerInfoDAO.removeAllAppenders();
			loggerErrorDAO.removeAllAppenders();
		}

	}

	/**
	 * Destruye el servlet.
	 */
	public void destroy()
	{
		super.destroy();
	}

	/**
	 * @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{

	}

	/**
	 * @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{

	}

}
