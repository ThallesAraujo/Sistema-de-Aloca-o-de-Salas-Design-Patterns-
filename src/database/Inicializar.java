package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe de inicialização, configuração e redefinição (exclusão de todos os dados) da database
 * @author Thalles
 *
 */
public class Inicializar {

	private static Connection conexao = ConnectionDatabase.getConexao();
	
	/**
	 * Método que inicializa a database, juntamente com as condições mínimas para que a mesma funcione
	 * @throws Exception Uma exceção pode ser lançada caso haja falha na configuração ou na conexão com a database 
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
	 * Define no sistema operacional a variável de ambiente necessária ao funcionamento do Apache Derby
	 */
	private static void configurarAmbiente(){
		System.setProperty("derby.system.home","C:/derby");
	}
	
	/**
	 * Apaga não só os dados, como também a database em si, destruindo as tabelas que a compõe.
	 * @throws Exception Uma exceção pode ser lançada caso a database não exista.
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
	 * Método de criação da tabela de salas
	 * @throws Exception Uma exceção pode ser lançada caso a tabela já conste na database
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
	 * Método de criação da tabela de eventos
	 * @throws Exception Uma exceção pode ser lançada caso a tabela já conste na database
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
	 * Método de criação da tabela de alocações
	 * @throws Exception Uma exceção pode ser lançada caso a tabela já tenha sido criada.
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
	 * Método de criação da tabela de logs.
	 * @throws Exception Uma exceção pode ser lançada caso a tabela já tenha sido criada.
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
	 * Método de reinicialização (destruição e recriação) da tabela de logs.
	 * @throws Exception Uma exceção pode ser lançada caso a database ou a tabela de logs não exista.
	 */
	public static void apagarDatabaseLogs() throws Exception{
		String sql = "DROP TABLE LOGS";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.execute();
		statement.close();
		criarTabelaLogs();
	}
	
	/**
	 * Método de reinicialização (destruição e recriação) da tabela de salas.
	 * @throws Exception Uma exceção pode ser lançada caso a database ou a tabela de salas não exista.
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
	 * Método de reinicialização (destruição e recriação) da tabela de eventos.
	 * @throws Exception Uma exceção pode ser lançada caso a database ou a tabela de eventos não exista.
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
