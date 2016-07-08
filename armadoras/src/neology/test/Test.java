package neology.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.SimpleLayout;

import neology.modelo.negocio.daos.ConfiguracionHistoricoDAO;

public class Test {
	
	private static final Log logger = LogFactory.getLog(Test.class);
	private static final Logger log = Logger.getLogger(Test.class);
	private static final Logger logger1 = Logger.getLogger("infoLog");
    private static final Logger logger2 = Logger.getLogger("errorLog"); 
	
	public static void main(String[] args) {
		
////		BasicConfigurator.configure(); 
////        Logger.getLogger("info").setLevel(Level.INFO);
////        Logger.getLogger("error").setLevel(Level.ERROR);
////       
//        File theDir = new File("C:\\apache-tomcat-7.0.68\\logs\\logs_repuve\\info");
//
//     // if the directory does not exist, create it
//     if (!theDir.exists()) {
//         System.out.println("creating directory: " + theDir.getName());
//         boolean result = false;
//
//         try{
//             theDir.mkdirs();
//             result = true;
//         } 
//         catch(SecurityException se){
//             //handle it
//         }        
//         if(result) {    
//             System.out.println("DIR created");  
//         }
//     }
////
//     String strDir=theDir.getAbsolutePath().replace("\\", "//");
//     
//        try {
//        	logger1.addAppender(new FileAppender(new SimpleLayout(),strDir+"//logs.log"));
//        	logger2.addAppender(new FileAppender(new SimpleLayout(),strDir+"//error.log"));
//        } catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//      
//        logger2.error("Critical message, almost fatal");
//        logger1.info("Information");
//
//	}
		
		 // creates pattern layout
		File theDir = new File("C:\\apache-tomcat-7.0.68\\logs\\logs_repuve\\info");
		String strDir=theDir.getAbsolutePath().replace("\\", "//");
        PatternLayout layout = new PatternLayout();
        String conversionPattern = "%-7p %d [%t] %c %x - %m%n";
        layout.setConversionPattern(conversionPattern);
 
        // creates console appender
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(layout);
        consoleAppender.activateOptions();
 
        // creates file appender
        RollingFileAppender fileAppender = new RollingFileAppender();
        fileAppender.setFile("C://apache-tomcat-7.0.68//logs//logs//logs_repuve//info//2016//info.log");
        fileAppender.setMaxFileSize("1KB");
        fileAppender.setMaxBackupIndex(100);
        fileAppender.setLayout(layout);
        fileAppender.activateOptions();
 
        // configures the root logger
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);
        rootLogger.addAppender(consoleAppender);
        rootLogger.addAppender(fileAppender);
 
        // creates a custom logger and log messages
        Logger logger = Logger.getLogger(Test.class);
        logger.debug("this is a debug log message");
        logger.info("this is a information log message");
        logger.warn("this is a warning log message");

}}
