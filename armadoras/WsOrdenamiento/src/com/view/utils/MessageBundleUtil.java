/**
 * @author Axel Galicia
 * @version 1.0.0
 * 
 * Description: This class allows application to be i18n. And obtain messages bundles for different languages
 */

package com.view.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageBundleUtil {
	
	private static ResourceBundle objResourceBundle;
	private static Locale objCurrentLocale;
	private static String strLanguage;
	private static String strCountry;
	
	public MessageBundleUtil(){}
	
	/**
	 * This method load MessageBundle
	 * */
	public static void loadMessageBundle(){
		
		objCurrentLocale = new Locale(strLanguage,strCountry);		
		objResourceBundle = ResourceBundle.getBundle("com/view/message/MessageBundle", objCurrentLocale);
	}

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * @param strKeyMessage
	 * This method return a message from MessageBundle
	 * 
	 * */
	
	public static String getStrMessageFromBundle(String strKeyMessage){
		
		return objResourceBundle.getString(strKeyMessage);
	}
	
	

	public static ResourceBundle getObjResourceBundle() {
		return objResourceBundle;
	}

	public static void setObjResourceBundle(ResourceBundle objResourceBundle) {
		MessageBundleUtil.objResourceBundle = objResourceBundle;
	}

	public static Locale getObjCurrentLocale() {
		return objCurrentLocale;
	}

	public static void setObjCurrentLocale(Locale objCurrentLocale) {
		MessageBundleUtil.objCurrentLocale = objCurrentLocale;
	}

	public static String getStrLanguage() {
		return strLanguage;
	}

	public static void setStrLanguage(String strLanguage) {
		MessageBundleUtil.strLanguage = strLanguage;
	}

	public static String getStrCountry() {
		return strCountry;
	}

	public static void setStrCountry(String strCountry) {
		MessageBundleUtil.strCountry = strCountry;
	}


	

}
