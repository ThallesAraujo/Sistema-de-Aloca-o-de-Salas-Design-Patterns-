package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Classe  respons�vel por obter e retornar a conex�o com o banco de dados
 * @author Thalles
 *
 */
public class ConnectionDatabase {
	
	private static final String URL = "jdbc:derby:derby;create=true;user=derby;password=derby";
	private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	
	/**
	 * M�todo que retorna a conex�o com o banco de dados
	 * @return Objeto Connection, que � utilizado para as opera��es na database
	 */
	public static Connection getConexao(){
		try{
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return null;
	}

}
