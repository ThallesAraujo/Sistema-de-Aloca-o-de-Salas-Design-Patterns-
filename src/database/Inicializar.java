package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe de inicializa��o, configura��o e redefini��o (exclus�o de todos os dados) da database
 * @author Thalles
 *
 */
public class Inicializar {

	private static Connection conexao = ConnectionDatabase.getConexao();
	
	/**
	 * M�todo que inicializa a database, juntamente com as condi��es m�nimas para que a mesma funcione
	 * @throws Exception Uma exce��o pode ser lan�ada caso haja falha na configura��o ou na conex�o com a database 
	 */
	public static void inicializacao() throws Exception{
		try{
			configurarAmbiente();
			criarTabelaSalas();
			criarTabelaEventos();
			criarTabelaAlocacoes();
			criarTabelaLogs();
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	/**
	 * Define no sistema operacional a vari�vel de ambiente necess�ria ao funcionamento do Apache Derby
	 */
	private static void configurarAmbiente(){
		System.setProperty("derby.system.home","C:/derby");
	}
	
	/**
	 * Apaga n�o s� os dados, como tamb�m a database em si, destruindo as tabelas que a comp�e.
	 * @throws Exception Uma exce��o pode ser lan�ada caso a database n�o exista.
	 */
	public static void destruirDados() throws Exception{
		try{
			String sql = "DROP TABLE SALAS";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.execute();
			statement.close();
			sql = "DROP TABLE EVENTOS";
			statement = conexao.prepareStatement(sql);
			statement.execute();
			statement.close();
			sql = "DROP TABLE ALOCACOES";
			statement = conexao.prepareStatement(sql);
			statement.execute();
			statement.close();
			sql = "DROP TABLE LOGS";
			statement = conexao.prepareStatement(sql);
			statement.execute();
			statement.close();
		}catch(SQLException e){
			throw new Exception(e);
		}
	}
	
	
	/**
	 * M�todo de cria��o da tabela de salas
	 * @throws Exception Uma exce��o pode ser lan�ada caso a tabela j� conste na database
	 */
	private static void criarTabelaSalas() throws Exception{
		try{
			
			String sql = "CREATE TABLE SALAS( "+
			"id VARCHAR(50) NOT NULL "+
			"CONSTRAINT PK_SALAS PRIMARY KEY, "+
			"apelido VARCHAR(50), "+
			"finalidade VARCHAR(50) NOT NULL, "+
			"capacidade INTEGER, "+
			"tipo VARCHAR(50) NOT NULL, "+
			"aberto VARCHAR(50) NOT NULL"+
			")";
			
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.execute();
			statement.close();
			
		}catch(SQLException e){
			throw new Exception(e);
		}
		
	}
	
	/**
	 * M�todo de cria��o da tabela de eventos
	 * @throws Exception Uma exce��o pode ser lan�ada caso a tabela j� conste na database
	 */
	private static void criarTabelaEventos() throws Exception{
		
		try{
			
			String sql = "CREATE TABLE EVENTOS( "+
			"id VARCHAR(50) NOT NULL "+
			"CONSTRAINT PK_EVENTOS PRIMARY KEY, "+
			"nome VARCHAR(50) NOT NULL, "+
			"inicio VARCHAR(20) NOT NULL, "+
			"fim VARCHAR(20) NOT NULL, "+
			"contato VARCHAR(50), "+
			"area VARCHAR(50) NOT NULL, "+
			"repeticoes INTEGER, "+
			"TIPO VARCHAR(50) NOT NULL "+
			")";
			
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.execute();
			statement.close();
			
		}catch(SQLException e){
			throw new Exception(e);
		}

		
	}
	
	/**
	 * M�todo de cria��o da tabela de aloca��es
	 * @throws Exception Uma exce��o pode ser lan�ada caso a tabela j� tenha sido criada.
	 */
	private static void criarTabelaAlocacoes() throws Exception{
		try{
			String sql = "CREATE TABLE ALOCACOES( "+
					"id_evento VARCHAR(50) NOT NULL, "+
					"id_sala VARCHAR(50) NOT NULL "+
					")";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.execute();
			statement.close();
		}catch(SQLException e){
			throw new Exception(e);
		}
		
	}
	
	/**
	 * M�todo de cria��o da tabela de logs.
	 * @throws Exception Uma exce��o pode ser lan�ada caso a tabela j� tenha sido criada.
	 */
	private static void criarTabelaLogs() throws Exception{
		try{
		String sql = "CREATE TABLE LOGS( "+
					 "horario VARCHAR(50) NOT NULL, "+
				     "evento VARCHAR(50) NOT NULL "+
					 ")";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.execute();
		statement.close();
		}catch(SQLException e){
			throw new Exception(e);
		}
		
	}
	
	/**
	 * M�todo de reinicializa��o (destrui��o e recria��o) da tabela de logs.
	 * @throws Exception Uma exce��o pode ser lan�ada caso a database ou a tabela de logs n�o exista.
	 */
	public static void apagarDatabaseLogs() throws Exception{
		String sql = "DROP TABLE LOGS";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.execute();
		statement.close();
		criarTabelaLogs();
	}
	
	/**
	 * M�todo de reinicializa��o (destrui��o e recria��o) da tabela de salas.
	 * @throws Exception Uma exce��o pode ser lan�ada caso a database ou a tabela de salas n�o exista.
	 */
	public static void apagarDatabaseSalas() throws Exception{
		String sql = "DROP TABLE SALAS";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.execute();
		statement.close();
		sql = "DROP TABLE ALOCACOES";
		statement = conexao.prepareStatement(sql);
		statement.execute();
		statement.close();
		criarTabelaSalas();
		criarTabelaAlocacoes();
	}
	
	/**
	 * M�todo de reinicializa��o (destrui��o e recria��o) da tabela de eventos.
	 * @throws Exception Uma exce��o pode ser lan�ada caso a database ou a tabela de eventos n�o exista.
	 */
	public static void apagarDatabaseEventos() throws Exception{
		String sql = "DROP TABLE EVENTOS";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.execute();
		statement.close();
		sql = "DROP TABLE ALOCACOES";
		statement = conexao.prepareStatement(sql);
		statement.execute();
		statement.close();
		criarTabelaEventos();
		criarTabelaAlocacoes();
	}
	
}
