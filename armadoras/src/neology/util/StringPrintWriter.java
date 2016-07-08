package neology.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import neology.hibernate.sesion.HibernateSessionFactory;

public class StringPrintWriter extends PrintWriter {
	private StringWriter buffer;
	private static final Log log = LogFactory.getLog(StringPrintWriter.class);
	private StringPrintWriter(Writer out) {
		super(out, true);
	}
	public static StringPrintWriter newInstance() {
		StringWriter sw = new StringWriter();
		StringPrintWriter instance = new StringPrintWriter(sw);
		instance.buffer = sw;
		return instance;
	}
	public String toString() {
		return buffer.getBuffer().toString();
	}
}
