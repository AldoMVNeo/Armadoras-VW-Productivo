package neology.modelo.negocio.servicios;

import neology.modelo.negocio.daos.CambioEstadoDAO;
import neology.modelo.negocio.daos.ConfiguracionHistoricoDAO;
import neology.modelo.negocio.daos.EntidadDAO;
import neology.modelo.negocio.daos.EstadoDAO;
import neology.modelo.negocio.daos.FormatoDAO;
import neology.modelo.negocio.daos.FormatoHistoricoDAO;
import neology.modelo.negocio.daos.MenuDAO;
import neology.modelo.negocio.daos.OrdenImpresionDAO;
import neology.modelo.negocio.daos.OrdenesHistoricoDAO;
import neology.modelo.negocio.daos.ParametrosDAO;
import neology.modelo.negocio.daos.PerfilDAO;
import neology.modelo.negocio.daos.PermisosMenuDAO;
import neology.modelo.negocio.daos.ProcedenciaDAO;
import neology.modelo.negocio.daos.ReportesDAO;
import neology.modelo.negocio.daos.RespaldoHistoricoDAO;
import neology.modelo.negocio.daos.TipoFormatoDAO;
import neology.modelo.negocio.daos.UsuarioDAO;
import neology.modelo.negocio.daos.EstadoOIDAO;
import neology.modelo.negocio.daos.EstadoCatalogoDAO;

public class DAOFactory {

	public static EntidadDAO crearEntidadDAO() {
		return new EntidadDAO();
	}

	public static TipoFormatoDAO crearTipoFormatoDAO() {
		return new TipoFormatoDAO();
	}

	public static FormatoDAO crearFormatoDAO() {
		return new FormatoDAO();
	}

	public static UsuarioDAO crearUsuarioDAO() {
		return new UsuarioDAO();
	}

	public static EstadoDAO crearEstadoDAO() {
		return new EstadoDAO();
	}

	public static ParametrosDAO crearParametrosDAO() {
		return new ParametrosDAO();
	}

	public static OrdenImpresionDAO crearOrdenImpresionDAO() {
		return new OrdenImpresionDAO();
	}

	public static EstadoOIDAO crearEstadoOIDAO() {
		return new EstadoOIDAO();
	}

	public static PerfilDAO crearPerfilDAO() {
		return new PerfilDAO();
	}

	public static EstadoCatalogoDAO crearEstadoCatalogoDAO() {
		return new EstadoCatalogoDAO();
	}

	public static ProcedenciaDAO crearProcedenciaDAO() {
		return new ProcedenciaDAO();
	}

	public static ReportesDAO crearReportesDAO() {
		return new ReportesDAO();
	}

	public static CambioEstadoDAO crearCambioEstadoDAO() {
		return new CambioEstadoDAO();
	}

	public static FormatoHistoricoDAO crearFormatoHistoricoDAO() {
		return new FormatoHistoricoDAO();
	}
	
	public static MenuDAO crearMenuDAO(){
		return new MenuDAO();
	}
	
	public static PermisosMenuDAO crearPermisosMenuDAO(){
		return new PermisosMenuDAO();
	}
	
	public static OrdenesHistoricoDAO crearOrdenesHistoricoDAO(){
		return new OrdenesHistoricoDAO();
	}
	
	public static ConfiguracionHistoricoDAO crearConfiguracionHistoricoDAO(){
		return new ConfiguracionHistoricoDAO();
	}
	
	public static RespaldoHistoricoDAO crearRespaldoHistoricoDAO(){
		return new RespaldoHistoricoDAO();
	}
	
}
