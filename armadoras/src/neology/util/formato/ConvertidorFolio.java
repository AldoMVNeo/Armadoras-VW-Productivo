package neology.util.formato;

import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.util.FechaUtil;
import neology.util.Utilidades;

/**
 * Esta clase es utilizada para generar los folios consecutivos para la dotacion
 * de Titulos de Propiedad.
 * 
 * @param formatoFolio
 *            es un formato establecido en la base de datos con el cual se
 *            generaran los consecutivos.
 */
public class ConvertidorFolio {
	private String formato;
	private int[] arrayPos;
	private static final Log log = LogFactory.getLog(ConvertidorFolio.class);

	public ConvertidorFolio(String formatoFolio) {
		
		try {
			this.formato = formatoFolio;
			// Guarda en vector las potencias a multiplicar segun el formato del
			// folio
			Vector tmpArray = new Vector();
			for (int i = 0; i < formato.length(); i++) {
				if (formato.charAt(i) == '#')
					tmpArray.add(new Integer(10));
				else if (formato.charAt(i) == '@')
					tmpArray.add(new Integer(letra2Num('Z') + 1));

			}
			/*
			 * Se genera el arreglo con los factores por los que se tiene que
			 * multiplicar cada letra o dígito para formar el número
			 */

			arrayPos = new int[tmpArray.size()];

			// La última posición siempre son las unidades (aunque sea una
			// letra) y por eso se multiplica por 1
			arrayPos[tmpArray.size() - 1] = 1;
			for (int i = arrayPos.length - 2; i >= 0; i--) {
				Integer val = (Integer) tmpArray.get(i + 1);
				arrayPos[i] = val.intValue() * arrayPos[i + 1];
			}

			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * Metodo que regresa rango del folio
	 * 
	 * @param folio
	 *            el cual se va a obtener un rango entero
	 * @return
	 */

	public int getRango(String folio) {
		
	
		int res = 0;
		try {
			folio = folio.toUpperCase();

			// Quita los guiones del formato inicial
			StringBuffer folioSinGuiones = new StringBuffer();
			for (int x = 0; x < folio.length(); x++) {
				if (folio.charAt(x) != '-' && !Character.isLetter(formato.charAt(x))
						&& !Character.isDigit(formato.charAt(x)))
					folioSinGuiones.append(folio.charAt(x));
			}
			// sumar productos para el resultado
			res = 0;
			for (int x = 0; x < folioSinGuiones.length(); x++) {
				res += letra2Num(folioSinGuiones.charAt(x)) * arrayPos[x];
			}

			return res;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	private int letra2Num(char letra) {

		String letras = "ABCDEFGHJKLMNPRSTUVWXYZ";
		
		try {


			if (letra >= '0' && letra <= '9') {
 
				return letra - '0';
			} else {

				return letras.indexOf(letra);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return letra;

	}

	private char num2Letra(int num) {

		try {

			String letras = "ABCDEFGHJKLMNPRSTUVWXYZ";
			if (num >= 0 && num < letras.length()) {
	
				return letras.charAt(num);
			} else {
	
				throw new RuntimeException("num fuera de rango. num=" + num);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return 0;
	}

	/**
	 * Metodo que genera un folio valido de acuerdo a los rangos obtenidos
	 * 
	 * @param consecutivo
	 *            se convertira en un folio valido para guardar en la base de
	 *            datos
	 * @return
	 */
	public String getFolio(int consecutivo) {
		
		StringBuffer nuevo = new StringBuffer();
		
		try {
		
		int resto = consecutivo;
		int digito = 0;
		int posArray = 0;
		int n = 0;

		for (int c = 0; c < formato.length(); c++) {
			if (formato.charAt(c) == '-' || Character.isLetter(formato.charAt(c))
					|| Character.isDigit(formato.charAt(c)))
				nuevo.append(formato.charAt(c));
			else {
				for (n = c; posArray < arrayPos.length; posArray++, n++) {
					digito = resto / arrayPos[posArray];
					if (formato.charAt(n) == '#') {
						nuevo.append("" + digito);
						resto -= arrayPos[posArray] * digito;
						posArray++;
						break;
					} else if (formato.charAt(n) == '@') {
						nuevo.append(num2Letra(digito));
						resto -= arrayPos[posArray] * digito;
						posArray++;
						break;
					}
				}
			}
		}} catch (Exception e) {
			e.printStackTrace();

		}

		return nuevo.toString();
	}

}
