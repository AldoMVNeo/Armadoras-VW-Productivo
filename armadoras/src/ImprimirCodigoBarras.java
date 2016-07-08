import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;

import javax.swing.JPanel;

import netscape.javascript.JSObject;

public class ImprimirCodigoBarras extends HiddenApplet implements Printable
{

	private static final long serialVersionUID = -2106231596176782121L;
	// Barcode b;
	JPanel panel;
	String serie = "";

	/*
	 * public void start() { System.out.println("Strat------"); Object[] args = { this };
	 * JSObject.getWindow(this).call("appletLoaded", args ); }
	 */

	private void jbInit()
	{

		panel = new JPanel();
		setBackground(Color.WHITE);
		this.getContentPane().setSize(20, 150);

		this.getContentPane().setBackground(Color.blue);
		panel.setLayout(null);
		panel.setBackground(Color.cyan);
		// panel1.setBackground(Color.WHITE);
		this.getContentPane().add(panel, BorderLayout.CENTER);

	}

	public int print(Graphics g, PageFormat pf, int page)
	{
		jbInit();
		if (page > 0)
		{ /* We have only one page, and 'page' is zero-based */
			return NO_SUCH_PAGE;
		}

		/*
		 * User (0,0) is typically outside the imageable area, so we must translate by the
		 * X and Y values in the PageFormat to avoid clipping
		 */
		Graphics2D g2d = (Graphics2D) g;
		Font fuente = new Font("c39hrp24dhtt", Font.BOLD, 45);

		g2d.setFont(fuente);

		g2d.drawString(serie, 200, 200);
		// g2d.translate(pf.getImageableX(), pf.getImageableY());
		/* Now we perform our rendering */
		// try{
		// b.setSize(40, 80);
		// b.setFont(new Font( "Times",Font.PLAIN,14 ));
		// panel.add(b);
		// b.setBounds(30, 80, 20, 100);
		// b.draw(g2d, 30, 60);
		// }catch(OutputException es){
		// System.out.println(es);
		// }
		/* tell the caller that this page is part of the printed document */
		return PAGE_EXISTS;

	}

	public void impresion(@SuppressWarnings("unused")
	String metodo, String arregloVins)
	{
		try
		{
			serie = arregloVins;
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintable(this);

			// job.setPrintable(b);

			/*
			 * try{ b = BarcodeFactory.create3of9(vins[j], false); // b.setSize(30, 150);
			 * }catch(BarcodeException be){ System.out.println(be); }
			 */
			job.print();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void sendMsgUsingCall(String method)
	{
		System.out.println("HelloWorld.sendMsgUsingCall()");
		Object[] args = { "Hoal mundo" };
		JSObject.getWindow(this).call(method, args);
	}

}
