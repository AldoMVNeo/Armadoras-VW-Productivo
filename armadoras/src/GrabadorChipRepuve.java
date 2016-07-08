import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

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
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;


public class GrabadorChipRepuve extends JApplet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6760284130131079130L;
	//Atributos del applet
    private Style estiloError, estiloTexto, estiloDato;
    private JTextPane texto = creaEditor();
    private JPanel panel1 = new JPanel();  
    char cad[];
    String retorno;  
    private JScrollPane scroll = new JScrollPane(texto);
    private volatile Thread lookupThread; 
    
    private javax.swing.JLabel nivLabel;
    private javax.swing.JTextField nivTextField;
    private javax.swing.JPanel panelNiv; 
    private javax.swing.JPanel panelSemaforo;
    private org.jdesktop.swingx.JXImagePanel semaforo;
    
   
    //Contructor

    public GrabadorChipRepuve() {
        cad = new char[260];
    }
    private void ensureEventThread() {
        if (SwingUtilities.isEventDispatchThread()) {
          return;
        }

        //throw new RuntimeException("");
      }


   public void update(Graphics g){
	   paint(g);
   }

    /** Inicializa atributos y prepara GUI del applet */
    private void jbInit() throws Exception {    	
    	
        panelNiv = new javax.swing.JPanel();
        nivLabel = new javax.swing.JLabel();
        nivTextField = new javax.swing.JTextField();         
        panelSemaforo = new javax.swing.JPanel();
        semaforo = new org.jdesktop.swingx.JXImagePanel();
    	
        panelNiv.setBackground(new java.awt.Color(255, 255, 255));
        panelNiv.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        nivLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nivLabel.setText("Número de Identificación Vehicular:");
        panelNiv.add(nivLabel);
        
        nivTextField.setDocument(new LimitadorCaracteres(nivTextField,17));
        nivTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nivTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nivTextFieldActionPerformed(evt);
            }
        });
        
        nivTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nivTextFieldFocusGained(evt);
            }
        });
        
      /*  nivTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nivTextFieldFocusLost(evt);
            }
        });*/
        
        panelNiv.add(nivTextField);      
        

        this.getContentPane().add(panelNiv, java.awt.BorderLayout.NORTH);
        
        panelSemaforo.setBackground(new java.awt.Color(255, 255, 255));
        panelSemaforo.setLayout(new java.awt.GridLayout(1, 1));

        semaforo.setBackground(new java.awt.Color(255, 255, 255));
        semaforo.setImage(crearImageIcon("imagenes/gris.jpg").getImage());
        panelSemaforo.add(semaforo);
        
        this.getContentPane().add(panelSemaforo, java.awt.BorderLayout.WEST);
        
    	
        this.setSize(new Dimension(650, 223));
        setBackground(Color.WHITE);
        this.getContentPane().setBackground(Color.WHITE);
        panel1.setBackground(Color.WHITE);
        
        panel1.setLayout(new BorderLayout());
        panel1.add(scroll, BorderLayout.CENTER);
           
        texto.setEditable(false);
        this.getContentPane().add(panel1, BorderLayout.CENTER);    

    }

    private void errorGrabacion(String mensaje){
    	mensaje(true,mensaje);
    	semaforo.setImage(crearImageIcon("imagenes/rojo.jpg").getImage());
    	semaforo.updateUI();
    }
    
    private String getMensajeGrabacion(int codigo){
    	String mensaje="";
    	switch (codigo)
        {
            case 0:  mensaje = "Grabacion del Chip exitosa"; break;
            case 1: mensaje = "Longitud del parámetro NIV errónea"; break;
            case 2: mensaje = "Longitud del parámetro NRR errónea"; break;         
            case 3: mensaje = "Longitud del parámetro NCI errónea"; break;
            case 4: mensaje = "No fue posible conectarse con el lector, verifique que se encuentre conectada BlackTray"; break;
            case 9: mensaje = "Banco 11 – Memoria de Usuario Bloqueado, ya ha sido grabada la Constancia"; break;
            case 10:  mensaje = "Imposible Bloquear Access Password"; break;
            case 11:mensaje = "Imposible Bloquear Banco 01 – EPC, intentelo nuevamente"; break;
            case 12: mensaje = "Imposible Bloquear Banco 11 – Memoria de Usuario, intentelo nuevamente"; break;
            case 13: mensaje = "Se Requiere Mayor Potencia, Intento de Grabacion"; break;
            case 16:  mensaje = "Reintente leer la constancia"; break;
            case 17:  mensaje = "Reintente la escritura de la Constancia, posiblemente ya fue grabada"; break;
            case 19:  mensaje = "NIV Inconsistente en digito verificador"; break;
            case 20:  mensaje = "Lector incompatible con el componente RPV"; break;
            case 100:  mensaje = "Se han detectado mas de una Constancia de Inscripción, introduzca el folio de la que desea grabar."; break;
            default:  mensaje = "Error desconocido,intentelo nuevamente por favor"; break;
        }
    	return mensaje;
    }
    
    private void lookupAsync(String folio) {
    	final String f=folio;
        Runnable lookupRun = new Runnable() {
          public void run() {
            String bal = conectarGrabador(f,"L|");
            setDatos(bal);            
          }
        };

        lookupThread = new Thread(lookupRun, "lookupThread");
        lookupThread.start();
      }
    
    private void setDatos(String retorno) {
        final String datos= retorno;        
        Runnable r = new Runnable() {
          public void run() {
            try {
              actualizaDatos(datos);
            } catch (Exception x) {
              x.printStackTrace();
            }
          }
        };
        SwingUtilities.invokeLater(r);
    }
    
    private void actualizaDatos(String datos1) {
    		ensureEventThread();
    		String folio="";
        try{     
        int codigoGrabacion=Integer.parseInt(datos1.substring(0, datos1.indexOf("|")));
         if(codigoGrabacion==100){
    	  errorGrabacion(getMensajeGrabacion(codigoGrabacion));
    	  String ent=JOptionPane.showInputDialog(this,"Se han detectado más de una Constancia de Inscripción, por favor introduzca el folio de la Constancia:");
    	if(ent.trim().length()==0){
    		cancelar();    		
    		return;
    	}
    	else{
    		folio=ent;
    		  mensaje(false, "Comenzando la grabacion de la Constancia con folio "+folio);
    	}
      }   
      if(codigoGrabacion<21)
    	  errorGrabacion(getMensajeGrabacion(codigoGrabacion));
      else{
     	      if(folio.trim().length()==0)     	    	
    	    	  folio=datos1.substring(datos1.indexOf("|")+1,datos1.lastIndexOf("|"));
    	      String params[] = {"niv","folio"};
              String valores[] = {nivTextField.getText().trim().toUpperCase(),folio};
    		  URL url=new URL(getCodeBase()+"emisionFormatos.do?do=validarVinFormato");
              URLConnection conServlet = url.openConnection(); 
              String paramsCodificados = getParamsCodificados( params, valores, "iso-8859-1");
              enviarParams( conServlet, paramsCodificados );
              String respuestaAction=recibirRespuesta( conServlet );
    		  if(respuestaAction.startsWith("OK")){    			
    			String respuestaGrabacion=conectarGrabador(folio,"G|");    			
    			codigoGrabacion=Integer.parseInt(respuestaGrabacion.substring(0, respuestaGrabacion.indexOf("|")));
    			System.out.println("codigo "+codigoGrabacion);
    			if(codigoGrabacion==0){   	  
    		           mensaje(false,getMensajeGrabacion(codigoGrabacion));
    		    	   mensaje(false,"Actualizando datos...");
    		    	   semaforo.setImage(crearImageIcon("imagenes/verde.jpg").getImage());
    		    	   String paramsG[] = {"datosChip"};
    		           String valoresG[] = {respuestaGrabacion};    
    		            URL urlG=new URL(getCodeBase()+"grabacionChip.do?do=actualizaFormato");
    		           URLConnection conServletG = urlG.openConnection(); 
    		               String paramsCodificadosG = getParamsCodificados( paramsG, valoresG, "iso-8859-1");
    		               enviarParams( conServletG, paramsCodificadosG);
    		               String respuestaActionG=recibirRespuesta( conServletG );
    		               if(respuestaActionG.startsWith("OK")){
    		            	   mensaje(false,"Operacion finalizada exitosamente");
    		               }
    		               else{
    		            	   semaforo.setImage(crearImageIcon("imagenes/rojo.jpg").getImage());
    		            	   mensaje(true,"Ocurrio un error en la actualización en la Base de Datos");
    		            	   
    		               }
    		       }
    		       else{    		   	  
    		    	
    		    	   errorGrabacion(getMensajeGrabacion(codigoGrabacion));   
    		    	   
    		       }
    		  
    		  
    		  
    		  
    		  }
    		  else{
    			  if(respuestaAction.startsWith("<html>")){
    				mensajeError(this,"Inicio de sesión","Su sesion ha expirado por favor vuelva a introducir sus datos");
    				URL urlG=new URL(getCodeBase()+"index.jsp");
    				getAppletContext().showDocument(urlG, "_self");
    			  }   			
    			  
    			  errorGrabacion(respuestaAction);
    		  }
    	  
      }
       
      cancelar();
       
    }catch(Exception e){
    	
    	System.out.println(e);
    }
       
      }

    private void cancelar() {
        ensureEventThread();    

        if (lookupThread != null) {
          lookupThread.interrupt();
          nivTextField.setEditable(true);
          nivTextField.requestFocus();
          nivTextField.selectAll();
        }
      }

    private String conectarGrabador(String folio,String accion) {
    	String retorno1="-1";
        try {
          Thread.sleep(1);
          try{
          String niv=nivTextField.getText().trim().toUpperCase();
          String peticion=accion+niv;
          if(folio.length()>0)
        	  peticion=peticion+"|"+folio;    
      	
          //Se envian y reciben los datos al GrabadorRepubeX
          Socket s = new Socket("127.0.0.1", 8026);
          OutputStream outLectura = s.getOutputStream();
          byte bufLectura[] = peticion.getBytes();      
          String aux="leyendo datos";
          if(accion.startsWith("G"))
        	  aux="escribiendo datos";
          mensaje(false, "Espere un momento por favor, "+aux+"...");
          outLectura.write(bufLectura);
          InputStream entradaLectura = s.getInputStream();
          int i = 0;
          byte buf2[] = new byte[2048];
          int numcar = entradaLectura.read(buf2);
          for (i = 0; i < numcar; i++)
              cad[i] = (char)buf2[i];

           retorno1 = new String(cad, 0, i);
          // if(accion.startsWith("G"))
           //retorno1="0|30000026|3VWRV09M07M640275|13KIKSK39L|";
           //mensaje(false, "Datos recibidos "+retorno1);      
          s.close();
          }catch(Exception e){
        	  String mensajeC="Ocurrio un error inténtelo de nuevo por favor.";
     		 if(e.toString()!=null){
     			 if(e.toString().startsWith("java.net.ConnectException"))
     		 mensajeC="Verifique la ejecución del programa Grabador RepuveX";
     			 errorGrabacion(mensajeC);
     			 
     		 }
     		 cancelar();
        	  nivTextField.setEditable(true);
        	  nivTextField.selectAll();
          }
        } catch (InterruptedException x) {
          return "SEARCH CANCELLED";
        }
        return retorno1;
      }

    private void enviarGrabacion(String folio){
    	try{
    		 ensureEventThread();
    		 nivTextField.setEditable(false);
    		texto.removeAll();    		
    		texto.setText(""); 	
    		mensaje(false, "Inicializando..."); 
    		mensaje(false, "Grabando NIV  " + nivTextField.getText().toUpperCase());
    		semaforo.setImage(crearImageIcon("imagenes/gris.jpg").getImage());
    		lookupAsync(folio);
       
       //s.close();
    	 } catch (Exception e) {
    		 
             e.printStackTrace();
    	 }
    }
    public void init() {
       try{
            jbInit();
       }catch(Exception e){
    	   System.err.println(e);
    	   
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
    private void muestraErroresValidacion(String mensaje) throws BadLocationException{
        String[]errores=mensaje.split("~");       
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
                         String mensaje)  {
    	try{
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
        
    	}catch( BadLocationException be){}
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
    
    /** Muestra un mensaje de dialogo de error */
    public void mensajeError(Component componente, String titulo, 
                                   Object mensaje) {

        JOptionPane.showMessageDialog(componente, mensaje, titulo, 
                                      JOptionPane.INFORMATION_MESSAGE, 
                                      crearImageIcon("imagenes/error.jpg"));
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
    public String recibirRespuesta( URLConnection con ) throws IOException {
         //// Creamos un stream de entrada a partir de la conexión
    	String respuestaAction="";
         InputStreamReader streamEntrada = new InputStreamReader (con.getInputStream());
         //// Creamos un lector de buffer que tiene como entrada el stream
         BufferedReader bfEntrada = new BufferedReader( streamEntrada );

         //// Leemos el buffer linea a linea, cada una de ellas la enviamos al editor
         String linea;
         while ((linea = bfEntrada.readLine()) != null )
           respuestaAction=respuestaAction+linea;

         bfEntrada.close();
         return respuestaAction;
      }
    
    private void nivTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:   
    	nivTextField.setText(nivTextField.getText().toUpperCase());
    	if(this.nivTextField.getText().trim().length()==17)
    	enviarGrabacion("");
    	else{
    		texto.setText("");
    		errorGrabacion("No es un NIV válido");
    		nivTextField.selectAll();
    	}
}
    private void nivTextFieldFocusGained(java.awt.event.FocusEvent evt) {
        this.nivTextField.selectAll();
    }
    private void nivTextFieldFocusLost(java.awt.event.FocusEvent evt) {
        this.nivTextField.requestFocus();
    }


}
