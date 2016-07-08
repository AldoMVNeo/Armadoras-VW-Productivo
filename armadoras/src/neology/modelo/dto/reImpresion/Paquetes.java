package neology.modelo.dto.reImpresion;

import neology.util.StringUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.util.FechaUtil;

public class Paquetes implements java.io.Serializable {

	private Long numeroTramite;
	private DateTime fechaImpresion;
	private Integer totalRegistros;
	private String descripcion;
	private static Log log = LogFactory.getLog(Paquetes.class);
	
	public String getDescripcion() throws Exception
	{
	    
		log.debug("REPUVE ***************************** Paquetes descripcion - INICIO");
		FechaUtil fechaUtil = new FechaUtil();
		descripcion = numeroTramite + "---" +fechaUtil.getFechaLarga(fechaImpresion) + "---" + totalRegistros;
		log.debug("REPUVE ***************************** Paquetes fin - INICIO");
		
		return descripcion;
	}


	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}


	public Paquetes()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public DateTime getFechaImpresion()
	{
		return fechaImpresion;
	}
	public void setFechaImpresion(DateTime fechaImpresion)
	{
		this.fechaImpresion = fechaImpresion;
	}
	
	public Integer getTotalRegistros()
	{
		return totalRegistros;
	}
	public void setTotalRegistros(Integer totalRegistros)
	{
		this.totalRegistros = totalRegistros;
	}


	/**
	 * @param numerotramite
	 * @param fechaImpresion
	 * @param totalRegistros
	 */
	public Paquetes(Long numeroTramite, DateTime fechaImpresion, Integer totalRegistros)
	{
		super();
		this.numeroTramite = numeroTramite;
		this.fechaImpresion = fechaImpresion;
		this.totalRegistros = totalRegistros;
	}


	public Long getNumeroTramite()
	{
		return numeroTramite;
	}


	public void setNumeroTramite(Long numeroTramite)
	{
		this.numeroTramite = numeroTramite;
	}
	
	
}
