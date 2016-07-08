package neology.util.menu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.util.StringPrintWriter;

public class Menu {
	private static final String yui = "yui/build";
	private static Log log = LogFactory.getLog(Menu.class);

	private String titulo;

	private Vector<AbstractMenuItem> items;

	/**
	 * @return Returns the items.
	 */
	public Vector<AbstractMenuItem> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            The items to set.
	 */
	public void setItems(Vector<AbstractMenuItem> items) {
		this.items = items;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Vector#addElement(E)
	 */
	public void addItem(SubMenu arg0) {
		items.addElement(arg0);
	}

	public String renderToXML() {
		log.debug("REPUVE ***************************** Menu renderToXML - INICIO"); 
		StringPrintWriter writer = StringPrintWriter.newInstance();
		// writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		writer.append("<menu>\n");
		for (int i = 0; i < items.size(); i++) {
			AbstractMenuItem subMenu = items.get(i);
			subMenu.renderToXML(writer);
		}
		writer.append("</menu>\n");
		log.debug("REPUVE ***************************** Menu renderToXML - FIN"); 
		return writer.toString();

	}
	
	public void generarArchivoXMLArbol(String path,String nombreArchivo){
		log.debug("REPUVE ***************************** Menu generarArchivoXMLArbol - INICIO"); 
		Document document = new Document();			
		Element root = new Element("tree"); 
		root.setAttribute("id", "0");
		Element inicio = new Element("item"); 
		inicio.setAttribute(new Attribute("text",getTitulo()));
		inicio.setAttribute(new Attribute("id","1"));
		inicio.setAttribute(new Attribute("open","1"));
		root.addContent(inicio);
		for (int i = 0; i < items.size(); i++) {
			AbstractMenuItem subMenu = items.get(i);			
			subMenu.generarArchivoXMLArbol(inicio);			
		}
		document.setContent(root);	
		 try {
	            FileWriter writer = new FileWriter(path+"/"+nombreArchivo);
	            XMLOutputter outputter = new XMLOutputter();
	            outputter.setFormat(Format.getPrettyFormat().setEncoding("iso-8859-1"));	            
	            outputter.output(document, writer);	      
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

		 log.debug("REPUVE ***************************** Menu generarArchivoXMLArbol - FIN"); 
	}

	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
