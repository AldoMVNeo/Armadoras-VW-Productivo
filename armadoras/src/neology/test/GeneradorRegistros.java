package neology.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;

public class GeneradorRegistros {
	
	public static void main(String[] args) {
		
		Integer intRegistros=500;
		String strVin="3VWAB21351A";
		String strTipo="N";
		Integer InicioFolio=5001;
		Integer InicioHistorico=0;
		Integer InicioOrdenes=0;
		Integer InicioOrdenesHist=0;
		
		String strFolio="";
		
		String strInsertaFoliosOriginal="INSERT INTO FORMATOS (FOLIO,ID_TIPO_FORMATO,ID_ENTIDAD,"
				+ "ESTADO,FECHA_REGISTRO,ID_USUARIO) VALUES ";
		
		String strInsertaFoliosHistOriginal="INSERT INTO FORMATOS_HISTORICO (ID_FORMATO_HISTORICO,"
				+ "ID_USUARIO_MODIFICO,FECHA_HISTORICO,"
				+ "FOLIO,ID_TIPO_FORMATO,ID_ENTIDAD,ID_USUARIO_ACTUAL,ESTADO,FECHA_REGISTRO) VALUES ";
		
		String strInsertaOrdenesOriginal="INSERT INTO ORDENES_IMPRESION (ID_ORDEN_IMPRESION,ID_USUARIO,"
				+ "VIN,PLACA,PROPIETARIO,MARCA,MODELO,ANNO_MODELO,TIPO,ENTIDAD,FECHA_REGISTRO,ESTADO,"
				+ "ID_ENTIDAD,ID_TIPO_FORMATO,FECHA_IMPRESION,GRUPO) VALUES ";
		
		String strInsertaOrdenesHistOriginal="INSERT INTO ORDENES_IMP_HIST (ID_ORDENES_HISTORICO,ID_USUARIO_MODIFICO,"
				+ "ID_ORDEN_IMPRESION,ID_USUARIO,VIN,PLACA,PROPIETARIO,MARCA,MODELO,ANNO_MODELO,TIPO,"
				+ "ENTIDAD,FECHA_REGISTRO,ESTADO,ID_ENTIDAD,ID_TIPO_FORMATO,GRUPO) VALUES ";
		
		String strInsertaFoliosNuevo="";
		String strInsertaFoliosHistNuevo="";
		
		String strInsertaOrdenesNuevo="";
		String strInsertaOrdenesHistNuevo="";
		
		Random r = new Random();
		int InicioAnnio = 2014;
		int FinAnnio = 2017;
		int ResultadoAnnio = 0;
		
		int InicioMes = 1;
		int FinMes = 12;
		int ResultadoMes = 0;
		
		int InicioDia = 1;
		int FinDias = 28;
		int ResultadoDias = 0;
		
		String dtFecha="";
		PreparedStatement preparedStatementFolios = null;
		PreparedStatement preparedStatementFoliosHist = null;
		PreparedStatement preparedStatementOrd = null;
		PreparedStatement preparedStatementOrdHist = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;

		try {

			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.31.33:1521:xe", "repuve",
					"neology");
			
			Integer InicioFolioResp=InicioFolio;
			
			//Obtener Ultimos
			
			String strUltimo="SELECT formatoHist.ID_FORMATO_HISTORICO ultimoFolioHist, "
					+ "orden.ID_ORDEN_IMPRESION ultimoOrden, ordenHist.ID_ORDENES_HISTORICO ultimoOrdenHist "+
					  "FROM repuve.FORMATOS_HISTORICO formatoHist,  repuve.ORDENES_IMPRESION orden, "
					+ "repuve.ORDENES_IMP_HIST ordenHist WHERE formatoHist.ID_FORMATO_HISTORICO=(select max(ID_FORMATO_HISTORICO) from repuve.FORMATOS_HISTORICO) "
					+ "AND orden.ID_ORDEN_IMPRESION=(select max(ID_ORDEN_IMPRESION) from repuve.ORDENES_IMPRESION) "
					+ "AND ordenHist.ID_ORDENES_HISTORICO=(select max(ID_ORDENES_HISTORICO) from repuve.ORDENES_IMP_HIST)";
			
			Statement stmt =conn.createStatement();
			ResultSet rs = stmt.executeQuery(strUltimo);
			
			while(rs.next()){
				
				InicioHistorico=rs.getInt("ULTIMOFOLIOHIST")+1;
				InicioOrdenes=rs.getInt("ULTIMOORDEN")+1;
				InicioOrdenesHist=rs.getInt("ULTIMOORDENHIST")+1;
				
			}
			
			if(InicioHistorico==0){
				InicioHistorico=1;
			}
			
			if(InicioOrdenes==0){
				InicioOrdenes=1;
			}
			
			if(InicioOrdenesHist==0){
				InicioOrdenesHist=1;
			}
			
			//Inserta Folios
			for(int i=0;i<intRegistros;i++){
				
				System.out.println("INSERTA FOLIOS: "+ i);
				r = new Random();
				ResultadoAnnio = r.nextInt(FinAnnio-InicioAnnio) + InicioAnnio;
				
				InicioMes = 1;
				FinMes = 12;
				ResultadoMes = r.nextInt(FinMes-InicioMes) + InicioMes;
				
				InicioDia = 1;
				FinDias = 29;
				ResultadoDias = r.nextInt(FinDias-InicioDia) + InicioDia;
				
				dtFecha=String.valueOf(ResultadoDias)+"/"+String.valueOf(ResultadoMes)+"/"+ResultadoAnnio;
				
					  if(InicioFolioResp.toString().length()==7&& InicioFolioResp!=0){	
						  strFolio+="0"+String.valueOf(InicioFolioResp);				  
					  };				  
					  if(InicioFolioResp.toString().length()==6&& InicioFolioResp!=0){			  
						  strFolio+="00"+String.valueOf(InicioFolioResp);		  
					  };				  
					  if(InicioFolioResp.toString().length()==5&& InicioFolioResp!=0){					  
						  strFolio+="000"+String.valueOf(InicioFolioResp);					  
					  };					  
					  if(InicioFolioResp.toString().length()==4&& InicioFolioResp!=0){					  
						  strFolio+="0000"+String.valueOf(InicioFolioResp);					  
					  };					  
					  if(InicioFolioResp.toString().length()==3&& InicioFolioResp!=0){					  
						  strFolio+="00000"+String.valueOf(InicioFolioResp);					  
					  };					  
					  if(InicioFolioResp.toString().length()==2&& InicioFolioResp!=0){					  
						  strFolio+="000000"+String.valueOf(InicioFolioResp);									  
					  };					  
					  if(InicioFolioResp.toString().length()==1&& InicioFolioResp!=0){				  
						  strFolio+="0000000"+String.valueOf(InicioFolioResp);								  
					  };
				
				strInsertaFoliosNuevo="('"+strFolio+"','1','1','0',to_timestamp('" + dtFecha+ " 00:00:00', 'dd-mm-yyyy hh24:mi:ss'),"
						+ "1)";
				
				strInsertaFoliosHistNuevo="("+InicioHistorico+",1,to_timestamp('" + dtFecha+ " 00:00:00', 'dd-mm-yyyy hh24:mi:ss'),"
						+ "'"+strFolio+"',1,1,1,0,to_timestamp('" + dtFecha+ " 00:00:00', 'dd-mm-yyyy hh24:mi:ss'))";
				strInsertaFoliosNuevo=strInsertaFoliosOriginal+strInsertaFoliosNuevo;
				strInsertaFoliosHistNuevo=strInsertaFoliosHistOriginal+strInsertaFoliosHistNuevo;
				
				preparedStatementFolios = conn.prepareStatement(strInsertaFoliosNuevo);
				preparedStatementFolios.executeUpdate();
				preparedStatementFolios.close();
				
				preparedStatementFoliosHist = conn.prepareStatement(strInsertaFoliosHistNuevo);
				preparedStatementFoliosHist.executeUpdate();
				preparedStatementFoliosHist.close();
				
				InicioFolioResp++;
				InicioHistorico++;
				strFolio="";
			}

			String strVinResp=strVin;
			
			//Inserta Ordenes
			for(int i=0;i<intRegistros;i++){
				
				System.out.println("INSERTA ORDENES: "+ i);
				r = new Random();
				ResultadoAnnio = r.nextInt(FinAnnio-InicioAnnio) + InicioAnnio;
				
				InicioMes = 1;
				FinMes = 12;
				ResultadoMes = r.nextInt(FinMes-InicioMes) + InicioMes;
				
				InicioDia = 1;
				FinDias = 29;
				ResultadoDias = r.nextInt(FinDias-InicioDia) + InicioDia;
				
				dtFecha=String.valueOf(ResultadoDias)+"/"+String.valueOf(ResultadoMes)+"/"+ResultadoAnnio;
				
				String iCount=String.valueOf(i);
								  
				  if(iCount.toString().length()==5){			  
					  strVin=strVinResp+"0"+String.valueOf(iCount);		  
				  };				  
				  if(iCount.toString().length()==4){					  
					  strVin=strVinResp+"00"+String.valueOf(iCount);					  
				  };					  
				  if(iCount.toString().length()==3){					  
					  strVin=strVinResp+"000"+String.valueOf(iCount);					  
				  };	
				  if(iCount.toString().length()==2){					  
					  strVin=strVinResp+"0000"+String.valueOf(iCount);					  
				  };
				  if(iCount.toString().length()==1){					  
					  strVin=strVinResp+"00000"+String.valueOf(iCount);					  
				  };
				
				 strInsertaOrdenesNuevo="("+InicioOrdenes+",1,'"+strVin+"',"
				  		+ "'AAN17AA','04 4 6061,162VJ3,B4B4JM,02.07.2014,66,CBP 433665','VW','VW','2016','"+strTipo+"','Puebla de Zaragoza',to_timestamp('" + dtFecha+ " 00:00:00', 'dd-mm-yyyy hh24:mi:ss'),"
				  				+ "0,1,1,to_timestamp('" + dtFecha+ " 00:00:00', 'dd-mm-yyyy hh24:mi:ss'),1)";
				 
				 strInsertaOrdenesHistNuevo="("+InicioOrdenesHist+",1,"+InicioOrdenes+",1,'"+strVin+"',"
					  		+ "'AAN17AA','04 4 6061,162VJ3,B4B4JM,02.07.2014,66,CBP 433665','VW','VW','2016','"+strTipo+"','Puebla de Zaragoza',to_timestamp('" + dtFecha+ " 00:00:00', 'dd-mm-yyyy hh24:mi:ss'),"
					  				+ "0,1,1,1)";
				 
				strInsertaOrdenesNuevo=strInsertaOrdenesOriginal+strInsertaOrdenesNuevo;
				strInsertaOrdenesHistNuevo=strInsertaOrdenesHistOriginal+strInsertaOrdenesHistNuevo;
				
				preparedStatementOrd = conn.prepareStatement(strInsertaOrdenesNuevo);
				preparedStatementOrd.executeUpdate();
				preparedStatementOrd.close();
				
				preparedStatementOrdHist = conn.prepareStatement(strInsertaOrdenesHistNuevo);
				preparedStatementOrdHist.executeUpdate();
				preparedStatementOrdHist.close();
	
				InicioOrdenes++;
				InicioOrdenesHist++;
				strVin="";
			}
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (conn != null) {
			System.out.println("OK");
		} else {
			System.out.println("Fallo");
		}
	}
		
		
	}
