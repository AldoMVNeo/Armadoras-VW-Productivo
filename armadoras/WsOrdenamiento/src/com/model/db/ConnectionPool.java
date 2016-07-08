/**
 * @author Axel Galicia
 * @version 1.0.0
 * 
 * Description: This class obtain a connection from JNDI DataSource
 * 
 */

package com.model.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ConnectionPool {
	
	public static DataSource dataSource;
	
	

	/**
	 * @return Connection
	 *  This method allow to get a Connection from a Datasource of SQLServer
	 */
	public static Connection getConnectionPoolSQLServer(){
		Connection con = null;
		
		try {
				if(dataSource==null){
					dataSource = ConnectionPool.getDataSource();
						con = dataSource.getConnection();
					
				}
				else{
					con = dataSource.getConnection();
				}
				
		   }
		
		catch(SQLException e){
			System.out.println("*** Error al obtener conexión del DataSource:");
			System.out.println(e.getMessage());
			
			
		}
		
		
		
		return con;
	}
	
	/**
	 * @return DataSource
	 *  This method allow to get a DataSource from initial context
	 */
	private static DataSource getDataSource(){
		
	
		Context objContext = null;
		DataSource objDataSource = null;
		try {
			objContext = new InitialContext();
			objDataSource = (DataSource) objContext.lookup("java:comp/env/jdbc/TestDBNigeriaPadron");
			//System.out.println( objContext.lookup("java:comp/env/jdbc/TestDBNigeriaPadron"));
		} 
		
		catch (NamingException e) {
			
			System.out.println("*** Error al obtener referencia JNDI del DataSource");
			System.out.println(e.getMessage());
		}
	
		return objDataSource;	

	}
	
	
//	public static DataSource getDataSourceTwo(){
//		DataSource ds = null;
//		 Context ctx = null;
//		 try{
//			 
//			 ctx = new InitialContext();  
//		    Context envctx =  (Context) ctx.lookup("java:comp/env"); 
//		     ds =  (DataSource) envctx.lookup("jdbc/TestDBNigeriaPadron");
//	}
//		    catch (NamingException e) {
//				e.printStackTrace();
//			}
//		    
//		    return ds;
//	}
//	
	
	/**
	 * @param Connection
	 * This method close connection
	 * */
	public static void closeConnection(Connection con){
		try{
				if(con!=null){
					 con.close();
				}
			}
			catch(Exception e){
					
					System.out.println("*** Error cerrar la conexión del DataSource");
					System.out.println(e.getMessage());
			}
			
	}
	
	
	
	

}
