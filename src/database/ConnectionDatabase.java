package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Classe  responsável por obter e retornar a conexão com o banco de dados
 * @author Thalles
 *
 */
public class ConnectionDatabase {
	
	private static final String URL = "jdbc:derby:derby;create=true;user=derby;password=derby";
	private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	
	/**
	 * Método que retorna a conexão com o banco de dados
	 * @return Objeto Connection, que é utilizado para as operações na database
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
