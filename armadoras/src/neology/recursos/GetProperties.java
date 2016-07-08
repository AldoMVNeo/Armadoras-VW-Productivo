package neology.recursos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;
import net.sourceforge.barbecue.Main;

public class GetProperties {
		
	public String strMessage(String strKey){
			
		String strMessage="";
		InputStream inputStream = null;
		
		try {
			Properties prop = new Properties();	
			inputStream = getClass().getResourceAsStream("ApplicationResources.properties");
			if (inputStream != null) {
				
				prop.load(inputStream);
				strMessage=prop.getProperty(strKey);
				
			} else {
				throw new FileNotFoundException("Archivo de propiedades no encontrado");
				
			}
		} catch (Exception e) {
			
			System.out.println("Exception: " + e);

		} finally {
			
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}

		return strMessage;
		
	};

}
