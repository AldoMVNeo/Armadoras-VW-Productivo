package neology.vc.forms.handheld;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

public class SubirHandheldForm extends ValidatorForm
{

	private static Logger log = Logger.getLogger(SubirHandheldForm.class);

	private static final boolean overwriteFile = true;
	private static final long serialVersionUID = 4869555688756228684L;

	private static String getExtension(String s)
	{
		String ext = null;
		if (s == null) return null;
		int i = s.lastIndexOf('.');
		if (i > 0 && i < s.length() - 1) ext = s.substring(i + 1).toLowerCase();
		return ext;
	}

	// Campos
	private boolean subir;
	private FormFile theFile;

	// Metodos
	public FormFile getTheFile()
	{
		return theFile;
	}

	public boolean isSubir()
	{
		return subir;
	}

	/**
	 * Reset all properties to their default values.
	 * 
	 * @param mapping The ActionMapping used to select this instance.
	 * @param request The HTTP Request we are processing.
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
		theFile = null;
		subir = false;
	}

	public void setSubir(boolean subir)
	{
		this.subir = subir;
	}

	public void setTheFile(FormFile theFile)
	{
		this.theFile = theFile;
	}

	/**
	 * Validate all properties to their default values.
	 * 
	 * @param mapping The ActionMapping used to select this instance.
	 * @param request The HTTP Request we are processing.
	 * @return ActionErrors A list of all errors found.
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		log.debug("REPUVE ***************************** SubirHandheldForm validate - INICIO");
		ActionErrors errores = new ActionErrors();
		if (subir)
		{
			if (theFile == null) errores.add("theFile", new ActionMessage(
					"errors.requerido", "Especificar la ubicación del archivo"));
			else
			{
				String fileName = theFile.getFileName();
				if (fileName.equals("")) errores.add("theFile", new ActionMessage(
						"errors.requerido", "Especificar la ubicación del archivo"));
				else
				{
					log.info("Archivo a subir: " + fileName);
					String s = getExtension(fileName);
					if ("txt".equals(s))
					{
						File file = new File(fileName);
						if (!overwriteFile && file.exists())
						{
							s = "No se pudo subir el archivo " + fileName
									+ " porque ya existe uno con ese nombre.";
							errores.add("theFile", new ActionMessage(s, false));
						}
					}
					else
					{
						s = "El archivo " + fileName + " debe tener una extensión "
								+ ".txt para ser procesado.";
						errores.add("theFile", new ActionMessage(s, false));
					}
				}
			}
		}
		log.debug("REPUVE ***************************** SubirHandheldForm validate - FIN");
		return errores;
	}

}
