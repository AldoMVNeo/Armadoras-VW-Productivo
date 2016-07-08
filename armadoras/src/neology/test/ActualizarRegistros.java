package neology.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class ActualizarRegistros {
	
	public static void main(String[] args) {
		
		Integer intRegistros=10;
		Integer InicioFolio=1;
		Integer InicioOrdenes=1;
		PreparedStatement preparedStatementFolios = null;
		String strFolio="";

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;

		try {

			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.31.85:1521:xe", "REPUVE_HIST",
					"NEOLOGY");
			
			//Obtener Ultimos
			
			String strQuery="";
			String insertFoliosQuery="";
			
			Statement stmt =conn.createStatement();
			Integer InicioFolioResp=InicioFolio;
			
			for(int i=0;i<intRegistros;i++){
				
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
			
				strQuery="UPDATE REPUVE_HIST.FORMATOS_15 SET ESTADO =0, ID_ORDEN_IMPRESION="+InicioOrdenes+" WHERE FOLIO='"+strFolio+"'";
				int rs = stmt.executeUpdate(strQuery);

				
				InicioOrdenes++;
				InicioFolioResp++;
				strFolio="";
			
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
