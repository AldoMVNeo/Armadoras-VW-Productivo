package neology.modelo.dto;

import java.util.Date;

import neology.modelo.dto.id.FormatoId;

import org.joda.time.DateTime;

public class Formato implements java.io.Serializable
{

	public static long FORMA_REPUVE = 1;
	private static final long serialVersionUID = 2154870302383967815L;

	// Campos
	private Entidad entidad;
	private Estado estado;
	private DateTime fechaRegistro;
	private FormatoId formatoId;
	private long noActivacion;
	private String observaciones;
	private OrdenImpresion ordenImpresion;
	private TipoFormato tipoFormato;
	private Usuario usuarioModifico;
	private Integer total;
	private String fechaInicial;
	private String fechaFinal;
	// Constructores

	

	
	/** constructor por default */
	public Formato()
	{}

	/** constructor minimo */
	public Formato(FormatoId formatoId, Entidad entidad, Usuario usuario,
			TipoFormato tipoFormato, Estado estado)
	{
		this.formatoId = formatoId;
		this.entidad = entidad;
		this.usuarioModifico = usuario;
		this.tipoFormato = tipoFormato;
		this.estado = estado;
	}
	
	/** constructor para determinar totales */
	public Formato(Estado estado,Integer total, String fechaInicial,String fechaFinal)
	{		
		this.estado = estado;
		this.total=total;
		this.fechaInicial=fechaInicial;
		this.fechaFinal=fechaFinal;
	}

	

	// Metodos

	public Entidad getEntidad()
	{
		return entidad;
	}

	public Estado getEstado()
	{
		return estado;
	}

	

	public FormatoId getFormatoId()
	{
		return formatoId;
	}

	public long getNoActivacion()
	{
		return noActivacion;
	}

	public String getObservaciones()
	{
		return this.observaciones;
	}

	public OrdenImpresion getOrdenImpresion()
	{
		return ordenImpresion;
	}

	public TipoFormato getTipoFormato()
	{
		return this.tipoFormato;
	}	

	public DateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(DateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Usuario getUsuarioModifico() {
		return usuarioModifico;
	}

	public void setUsuarioModifico(Usuario usuarioModifico) {
		this.usuarioModifico = usuarioModifico;
	}

	public void setEntidad(Entidad entidad)
	{
		this.entidad = entidad;
	}

	public void setEstado(Estado estado)
	{
		this.estado = estado;
	}

	
	public void setFormatoId(FormatoId formatoId)
	{
		this.formatoId = formatoId;
	}

	public void setNoActivacion(long noActivacion)
	{
		this.noActivacion = noActivacion;
	}
	

	public void setObservaciones(String observaciones)
	{
		this.observaciones = observaciones;
	}

	public void setOrdenImpresion(OrdenImpresion ordenImpresion)
	{
		this.ordenImpresion = ordenImpresion;
	}

	public void setTipoFormato(TipoFormato tipoFormato)
	{
		this.tipoFormato = tipoFormato;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public String getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	
}
