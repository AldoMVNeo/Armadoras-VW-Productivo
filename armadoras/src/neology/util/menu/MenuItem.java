package neology.util.menu;

import neology.util.StringPrintWriter;

import org.apache.commons.lang.StringEscapeUtils;
import org.jdom.Attribute;
import org.jdom.Element;

public class MenuItem extends AbstractMenuItem {
	private String link;

	/**
	 * @return Returns the link.
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link
	 *            The link to set.
	 */
	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public void renderToXML(StringPrintWriter sb) {
		sb.append("<item id=\"");
		sb.append("" + getIdMenu());
		sb.append("\" text=\"");
		sb.append(getTitulo());
		sb.append("\" img=\"");
		sb.append(getIcono() == null ? "" : getIcono());
		sb.append("\"><userdata name=\"");
		sb.append("url\"" + "><![CDATA[");
		sb.append(StringEscapeUtils.escapeHtml(getLink()));
		sb.append("]]></userdata></item>\n");
	}

	@Override
	public void generarArchivoXMLArbol(Element element) {
		Element child = new Element("item"); 
		child.setAttribute(new Attribute("text",getTitulo()));
		child.setAttribute(new Attribute("id",getIdMenu().toString()));
		child.setAttribute(new Attribute("open","0"));
		if(isActivo())
		child.setAttribute(new Attribute("checked",""+isActivo()));
		element.addContent(child);
		
	}
}
