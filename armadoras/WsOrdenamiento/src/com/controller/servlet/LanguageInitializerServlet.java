package com.controller.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.view.utils.MessageBundleUtil;

public class LanguageInitializerServlet extends HttpServlet {


	/**
	 * Initialization of the language settings. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		String strLanguage = this.getInitParameter("language");
		String strCountry = this.getInitParameter("country");
		MessageBundleUtil.setStrLanguage(strLanguage);
		MessageBundleUtil.setStrCountry(strCountry);
		System.out.println("------------------ WEB SERVICE CONSULTA PADRON -------------------");
		System.out.println("Inicializando Message Bundle en:"+strLanguage+"_"+strCountry);
		MessageBundleUtil.loadMessageBundle();
		
	}

}
