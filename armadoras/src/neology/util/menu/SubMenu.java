package neology.util.menu;

import java.util.Vector;

import org.jdom.Element;
import org.jdom.Attribute;

import neology.util.StringPrintWriter;

public class SubMenu extends AbstractMenuItem {
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
	public void addItem(AbstractMenuItem arg0) {
		items.addElement(arg0);
	}

	@Override
	public void renderToXML(StringPrintWriter sb) {
		sb.append("<item id=\"");
		sb.append("" + getIdMenu());
		sb.append("\" text=\"");
		sb.append("" + getTitulo());
		sb.append("\">\n");
		for (int i = 0; i < items.size(); i++) {
			AbstractMenuItem item = items.get(i);
			item.renderToXML(sb);
		}
		sb.append("</item>\n");
	}

	@Override
	public void generarArchivoXMLArbol(Element element) {
		Element child = new Element("item"); 
		child.setAttribute(new Attribute("text",getTitulo()));
		child.setAttribute(new Attribute("id",getIdMenu().toString()));
		child.setAttribute(new Attribute("open","0"));
		
		element.addContent(child);
		for (int i = 0; i < items.size(); i++) {
			AbstractMenuItem item = items.get(i);
			item.generarArchivoXMLArbol(child);
		}
		
	}

}
