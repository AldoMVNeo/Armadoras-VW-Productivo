import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.io.PrintWriter;

import java.net.Socket;
import java.net.URL;

import java.net.URLConnection;
import java.net.URLDecoder;

import java.net.URLEncoder;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;


public class Grabador extends JApplet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6760284130131079130L;
	//Atributos del applet
    private Style estiloError, estiloTexto, estiloDato;
    private JTextPane texto = creaEditor();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JButton regresar = new JButton("Regresar");
    char cad[];
    String retorno;
    String respuestaAction="";
    private JScrollPane scroll = new JScrollPane(texto);
   
    //Contructor

    public Grabador() {
        cad = new char[260];
    }

    /** Inicializa atributos y prepara GUI del applet */
    private void jbInit() throws Exception {
        this.setSize(new Dimension(552, 223));
        setBackground(Color.WHITE);
        this.getContentPane().setBackground(Color.WHITE);
        panel1.setBackground(Color.WHITE);
        panel2.setBackground(Color.WHITE);
        regresar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        regresaActionPerformed(e);
                    }
                });
        panel1.setLayout(new BorderLayout());
        panel1.add(scroll, BorderLayout.CENTER);
        panel2.add(regresar);       
        texto.setEditable(false);
        this.getContentPane().add(panel1, BorderLayout.CENTER);
        this.getContentPane().add(panel2, BorderLayout.SOUTH);

    }

    public void init() {
        try {
            jbInit();
            mensaje(false, "Inicializando...");

            //Parametros de entrada para realizar validaciones y/o escritura del chip
          
            String cadena = 
                URLDecoder.decode(getParameter("accion"), "iso-8859-1");

            //Se envian y reciben los datos al DISPADMIN
            Socket s = new Socket("127.0.0.1", 8026);
            OutputStream out = s.getOutputStream();
            byte buf[] = cadena.getBytes();
            mensaje(false, "Enviando peticion..." + cadena);
            out.write(buf);
            InputStream entrada = s.getInputStream();
            int i = 0;
            byte buf2[] = new byte[2048];
            int numcar = entrada.read(buf2);
            for (i = 0; i < numcar; i++)
                cad[i] = (char)buf2[i];

            retorno = new String(cad, 0, i);
            retorno = retorno.trim();
            mensaje(false, "Recibiendo datos..." + retorno);
            s.close();

            //Se obtiene el folio del Formato que se encuentra en la charola y se envia para su validacion


            String folio = "M-001";          
            //Si se va a realizar una validacion se envia al action correspondiente
            if (cadena.startsWith("|L|")) {             
            /*URL url = 
                    new URL(getCodeBase()+"grabacionChip.do?do=validarFormato&folioChip=" + 
                            folio + "&ordenId=" + ordenId + "&tipoFormatoId=" + 
                            tipoFormatoId + "&usuarioId=" + usuarioId);
                getAppletContext().showDocument(url, "_self");*/
            String params[] = {"folioChip"};
            String valores[] = {folio};
       
                URL url=new URL(getCodeBase()+"grabacionChip.do?do=validarFormato");
            URLConnection conServlet = url.openConnection(); 
                String paramsCodificados = getParamsCodificados( params, valores, "iso-8859-1");

                enviarParams( conServlet, paramsCodificados );   // Envio los parámetros

                recibirRespuesta( conServlet ); // Parámetros recibidos
                
             if(respuestaAction.startsWith("EXITOSO:")){ 
                	regresar.setEnabled(false);
                        URL url2 = 
                            new URL(getCodeBase()+"grabacionChip.do?do=actualizaValores");
                        getAppletContext().showDocument(url2, "_self");
                }
                else{                	
                	muestraErroresValidacion();
                }
        
            }         
           
        } catch (Exception e) {
            e.printStackTrace();
            try {            	
            	String params[] = {"comunicacion"};
                String valores[] = {e.toString()};
            	URL url=new URL(getCodeBase()+"grabacionChip.do?do=validarFormato");
                URLConnection conServlet = url.openConnection(); 
                    String paramsCodificados = getParamsCodificados( params, valores, "iso-8859-1");
                    enviarParams( conServlet, paramsCodificados );   // Envio los parámetros
                    recibirRespuesta( conServlet ); 
                    muestraErroresValidacion();
            	}catch(Exception e1){
            		System.out.println(e1);
            	}
           
        }
        repaint();
    }
    static {
        try {
        } catch (Exception e) {
            System.out.println(e);
        }
    }
   
    /** Recorre errores de la validacion para ser mostrados */
    private void muestraErroresValidacion() throws BadLocationException{
        String[]errores=respuestaAction.split("~");       
        for(int i=0;i<errores.length;i++){
        	System.out.println(errores[i]);
        	mensaje(true,errores[i]);
        }
        
    }

    /** Crea el editor de salida estandar del applet */
    private JTextPane creaEditor() {
        StyleContext sc = creaEstilos();
        DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        texto = new JTextPane(doc);
        return texto;
    }

    /** Crea diferentes estilos para los diferentes tipos de mensajes en la salida del applet */
    private StyleContext creaEstilos() {
        StyleContext sc = new StyleContext();

        estiloError = sc.addStyle(null, null);
        StyleConstants.setForeground(estiloError, Color.red);
        StyleConstants.setFontSize(estiloError, 12);
        StyleConstants.setBold(estiloError, true);

        estiloTexto = sc.addStyle(null, null);
        StyleConstants.setForeground(estiloTexto, Color.black);
        StyleConstants.setFontSize(estiloTexto, 12);
        StyleConstants.setBold(estiloTexto, true);

        estiloDato = sc.addStyle(null, null);
        StyleConstants.setForeground(estiloDato, Color.GRAY);    
        StyleConstants.setFontFamily(estiloDato,"Tahoma");
        StyleConstants.setFontSize(estiloDato, 13);      
        StyleConstants.setBold(estiloDato, true);
        return (sc);
    }


    /** Muestra un mensaje en la salida del applet, informacion relacionada con la lectura/escritura en el chip */
    private void mensaje(boolean error, 
                         String mensaje) throws BadLocationException {
        Style estilo = null;
        Document doc = texto.getDocument();
        String retornoDeCarro = System.getProperty("line.separator");
        if (error) {
            estilo = estiloError;
            texto.setCaretPosition(texto.getStyledDocument().getLength());
            javax.swing.JLabel label=new javax.swing.JLabel();
            label.setIcon(crearImageIcon("error.gif"));
            label.setText(mensaje);
            label.setForeground(Color.red);
            texto.insertComponent(label);
            doc.insertString(doc.getLength(), "" + retornoDeCarro, estilo);
        }

        else{
            estilo = estiloTexto;
            texto.setCaretPosition(texto.getStyledDocument().getLength());
            javax.swing.JLabel label=new javax.swing.JLabel();
            label.setIcon(crearImageIcon("correcto.gif"));
            label.setText(mensaje);            
            texto.insertComponent(label);
        doc.insertString(doc.getLength(), "" + retornoDeCarro, estilo);
        }
        

    }


    /** Muestra un mensaje para cada uno de los datos del tramite que se obtengan del chip */
    private void mensajeDato(String mensaje) throws BadLocationException {
        Style estilo = null;
        Document doc = texto.getDocument();
        String retornoDeCarro = System.getProperty("line.separator");
        estilo = estiloDato;
        doc.insertString(doc.getLength(), "\t" + mensaje + retornoDeCarro, 
                         estilo);

    }

    /** Muestra un mensaje de dialogo de información */
    public void mensajeInformacion(Component componente, String titulo, 
                                   Object mensaje) {

        JOptionPane.showMessageDialog(componente, mensaje, titulo, 
                                      JOptionPane.INFORMATION_MESSAGE, 
                                      crearImageIcon("imagenes/informacion.png"));
    }

    /** Obiene una imagen de una url */
    public ImageIcon crearImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("No se encontro el archivo: " + path);
            return null;
        }
    }

    /** Regresa a la lista de Tramites */
    private void regresaActionPerformed(ActionEvent e) {
        try {
            URL url = new URL(getCodeBase() + "listaTramites.do?do=inicio");
            getAppletContext().showDocument(url, "_self");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    static public String getParamsCodificados( String parametros[], String valores[], String codificacion ) {
          try {

             StringBuffer paramsCodificados = new StringBuffer();

             //// Codifica cada param y cada valor, los almacena en StringBuffer
             for ( int i = 0; i<parametros.length; i++)
                paramsCodificados.append( (i==0?"":"&") + URLEncoder.encode(parametros[i], codificacion) + "=" +
                                          URLEncoder.encode(valores[i], codificacion) );

             return paramsCodificados.toString();  // Devuelve cadena con params codificados

          } catch( Exception e) {
             return null;
          }
       }
  
     public void enviarParams( URLConnection con, String params ) throws IOException {
        con.setDoOutput( true);                         // Conexión para salida
        con.setUseCaches( false );                      // El navegador no usa caché
        PrintWriter salida = new PrintWriter( con.getOutputStream() );
        salida.print( params );
        salida.close();
     }
    public void recibirRespuesta( URLConnection con ) throws IOException {
         //// Creamos un stream de entrada a partir de la conexión
         InputStreamReader streamEntrada = new InputStreamReader (con.getInputStream());
         //// Creamos un lector de buffer que tiene como entrada el stream
         BufferedReader bfEntrada = new BufferedReader( streamEntrada );

         //// Leemos el buffer linea a linea, cada una de ellas la enviamos al editor
         String linea;
         while ((linea = bfEntrada.readLine()) != null )
           respuestaAction=respuestaAction+linea;

         bfEntrada.close();
      }


}
