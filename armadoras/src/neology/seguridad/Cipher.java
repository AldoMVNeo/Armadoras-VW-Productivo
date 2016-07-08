/*
 * Cipher.java
 *
 * Created on 22/09/2007, 12:29:45 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neology.seguridad;



/**
 *
 * @author Osvaldo Sanchez
 */
public class Cipher {

	private static String valmask = "9876543210zZyYxXwWvVuUtTsSrRqQpPoOÒ—nNmMlLkKjJiIhHgGfFeEdDcCbBaA@=*~";
	private static String codmask = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNÒ—oOpPqQrRsStTuUvVwWxXyYzZ0123456789| -:";

public static String encriptar(String Src) {    	
        StringBuffer cadout=new StringBuffer("");        
        for(int i =0;i<Src.length();i++)
        { for(int j=0;j<codmask.length();j++)
             {
              char ci=Src.charAt(i);
              char cd=codmask.charAt(j);
              if(ci==cd){cadout.append(valmask.charAt(j));}
              continue; 
             }
        }
       
       return cadout.toString();
    }

   
    public static String desencriptar(String Src) {    	
        StringBuffer cadout=new StringBuffer("");        
        for(int i =0;i<Src.length();i++)
        { for(int j=0;j<valmask.length();j++)
             {
              char ci=Src.charAt(i);
              char cd=valmask.charAt(j);
              if(ci==cd){cadout.append(codmask.charAt(j));}
              continue; 
             }
        }
       
       return cadout.toString();
    }

  
}
