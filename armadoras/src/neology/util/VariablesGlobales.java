package neology.util;

public class VariablesGlobales {
    //Variables
    private static boolean pdfCached ;
    private static String reporteTituloPropiedadConChip;
    private static String reporteConstanciaInscripcionREPUVE;
    private static String reporteDetalleConstanciaREPUVE;
    private static String pdfDir;
    private static String pdfPath;
    private static String reporteDetalleConstanciaFoliosREPUVE;
    private static String camposVaciosReporte;
    private static String strRangoFechas;
    private static String dirLogs;
    private static String esquemaProd;
    private static String esquemaRespaldo;
    private static String diasRespaldar;

    public static void setPdfCached(boolean pdfCached) {
        VariablesGlobales.pdfCached = pdfCached;
    }

    public static boolean isPdfCached() {
        return pdfCached;
    }

    public static void setReporteTituloPropiedadConChip(String reporteTituloPropiedadConChip) {
        VariablesGlobales.reporteTituloPropiedadConChip = reporteTituloPropiedadConChip;
    }

    public static String getReporteTituloPropiedadConChip() {
        return reporteTituloPropiedadConChip;
    }
    


    public static void setPdfDir(String pdfDir) {
        VariablesGlobales.pdfDir = pdfDir;
    }

    public static String getPdfDir() {
        return pdfDir;
    }

    public static void setPdfPath(String pdfPath) {
        VariablesGlobales.pdfPath = pdfPath;
    }

    public static String getPdfPath() {
        return pdfPath;
    }

	public static String getReporteConstanciaInscripcionREPUVE() {
		return reporteConstanciaInscripcionREPUVE;
	}

	public static void setReporteConstanciaInscripcionREPUVE(
			String reporteConstanciaInscripcionREPUVE) {
		VariablesGlobales.reporteConstanciaInscripcionREPUVE = reporteConstanciaInscripcionREPUVE;
	}

	public static String getReporteDetalleConstanciaREPUVE() {
		return reporteDetalleConstanciaREPUVE;
	}

	public static void setReporteDetalleConstanciaREPUVE(
			String reporteDetalleConstanciaREPUVE) {
		VariablesGlobales.reporteDetalleConstanciaREPUVE = reporteDetalleConstanciaREPUVE;
	}

	public static String getReporteDetalleConstanciaFoliosREPUVE() {
		return reporteDetalleConstanciaFoliosREPUVE;
	}

	public static void setReporteDetalleConstanciaFoliosREPUVE(String reporteDetalleConstanciaFoliosREPUVE) {
		VariablesGlobales.reporteDetalleConstanciaFoliosREPUVE = reporteDetalleConstanciaFoliosREPUVE;
	}

	public static String getCamposVaciosReporte() {
		return camposVaciosReporte;
	}

	public static void setCamposVaciosReporte(String camposVaciosReporte) {
		VariablesGlobales.camposVaciosReporte = camposVaciosReporte;
	}

	public static String getStrRangoFechas() {
		return strRangoFechas;
	}

	public static void setStrRangoFechas(String strRangoFechas) {
		VariablesGlobales.strRangoFechas = strRangoFechas;
	}

	public static String getDirLogs() {
		return dirLogs;
	}

	public static void setDirLogs(String dirLogs) {
		VariablesGlobales.dirLogs = dirLogs;
	}

	public static String getEsquemaProd() {
		return esquemaProd;
	}

	public static void setEsquemaProd(String esquemaProd) {
		VariablesGlobales.esquemaProd = esquemaProd;
	}

	public static String getEsquemaRespaldo() {
		return esquemaRespaldo;
	}

	public static void setEsquemaRespaldo(String esquemaRespaldo) {
		VariablesGlobales.esquemaRespaldo = esquemaRespaldo;
	}

	public static String getDiasRespaldar() {
		return diasRespaldar;
	}

	public static void setDiasRespaldar(String diasRespaldar) {
		VariablesGlobales.diasRespaldar = diasRespaldar;
	}
	
}
