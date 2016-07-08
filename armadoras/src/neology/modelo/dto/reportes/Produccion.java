package neology.modelo.dto.reportes;

import neology.util.StringUtil;

import org.joda.time.DateTime;
import neology.util.FechaUtil;

public class Produccion implements java.io.Serializable {

	private String nombreUsuario;
	private String estado;
	private Integer totalRegistros;

	public String getEstado()
	{
		return estado;
	}

	public void setEstado(String estado)
	{
		this.estado = estado;
	}

	public String getNombreUsuario()
	{
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario)
	{
		this.nombreUsuario = nombreUsuario;
	}

	public Integer getTotalRegistros()
	{
		return totalRegistros;
	}

	public void setTotalRegistros(Integer totalRegistros)
	{
		this.totalRegistros = totalRegistros;
	}

	public Produccion()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param numerotramite
	 * @param fechaImpresion
	 * @param totalRegistros
	 */
	public Produccion(String nombreUsuario, String estado, Integer totalRegistros)
	{
		super();
		this.nombreUsuario = nombreUsuario;
		this.estado = estado;
		this.totalRegistros = totalRegistros;
	}
	
}
