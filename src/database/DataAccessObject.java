package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import exceptions.ExceptionCreator;

/**
 * Classe que contém as ações básicas de CRUD (Create, Rescue, Update, Delete) da database.
 * @author Thalles
 *
 */
public abstract class DataAccessObject {
	
	private Connection conexao;
	
	/**
	 * A classe não deve ser instanciada, somente estendida
	 */
	protected DataAccessObject(){
		this.conexao = ConnectionDatabase.getConexao();
	}
	
	/**
	 * A conexão não deve ser utilizada diretamente por um cliente. Isso deve ser feito pelas classes de DAO
	 * @return Objeto Connection utilizado pelas subclasses
	 */
	protected Connection getConexao(){
		return this.conexao;
	}
	
	/**
	 * Modelo básico para uma ação de salvar itens na database. 
	 * @param insertSQL Comando SQL a ser executado
	 * @param parametros O Apache Derby não aceita concatenação de strings, por isso os parâmetros devem ser passados separadamente
	 * @throws Exception Uma exception pode ser lançada em caso de violação de chave primária ou nulidade de atributos NOT NULL
	 */
	protected void salvar(String insertSQL, Object... parametros) throws Exception{
		try{
			PreparedStatement statement = getConexao().prepareStatement(insertSQL);
			for(int i = 0; i<parametros.length;i++){
				statement.setObject(i+1, parametros[i]);
			}
			statement.execute();
			statement.close();
		}catch(Exception e){
			throw ExceptionCreator.createException(e.getMessage());
		}
	}
	
	
	/**
	 * Modelo básico para uma ação de atualizar itens na database. 
	 * @param insertSQL Comando SQL a ser executado
	 * @param parametros O Apache Derby não aceita concatenação de strings, por isso os parâmetros devem ser passados separadamente
	 * @throws Exception Uma exception pode ser lançada em caso de violação de chave primária ou nulidade de atributos NOT NULL
	 */
	protected void atualizar(String updateSQL, Object... parametros) throws Exception{
		try{
			PreparedStatement statement = getConexao().prepareStatement(updateSQL);
			for(int i = 0; i<parametros.length;i++){
				statement.setObject(i+1, parametros[i]);
			}
			statement.executeUpdate();
			statement.close();
		}catch(SQLException e){
			throw new Exception(e);
		}
	}
	
	
	/**
	 * Modelo básico para uma ação de excluir itens na database. 
	 * @param insertSQL Comando SQL a ser executado
	 * @param parametros O Apache Derby não aceita concatenação de strings, por isso os parâmetros devem ser passados separadamente
	 * @throws Exception Uma exception pode ser lançada em caso de violação de chave primária ou nulidade de atributos NOT NULL
	 */
	protected void excluir(String deleteSQL, Object... parametros) throws Exception{
		try{
			PreparedStatement statement = getConexao().prepareStatement(deleteSQL);
			for(int i = 0; i<parametros.length;i++){
				statement.setObject(i+1, parametros[i]);
			}
			statement.execute();
			statement.close();
		}catch(SQLException e){
			throw new Exception(e);
		}
	}
	
	/**
	 * Comando de "desligamento" do banco de dados
	 * @throws SQLException Uma exception pode ser lançada caso o banco ainda não possa ser desligado.
	 */
	protected void shutdown() throws SQLException{
		getConexao().createStatement().executeUpdate("SHUTDOWN");
	}
}
