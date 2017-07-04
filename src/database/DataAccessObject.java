package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import exceptions.ExceptionCreator;

/**
 * Classe que cont�m as a��es b�sicas de CRUD (Create, Rescue, Update, Delete) da database.
 * @author Thalles
 *
 */
public abstract class DataAccessObject {
	
	private Connection conexao;
	
	/**
	 * A classe n�o deve ser instanciada, somente estendida
	 */
	protected DataAccessObject(){
		this.conexao = ConnectionDatabase.getConexao();
	}
	
	/**
	 * A conex�o n�o deve ser utilizada diretamente por um cliente. Isso deve ser feito pelas classes de DAO
	 * @return Objeto Connection utilizado pelas subclasses
	 */
	protected Connection getConexao(){
		return this.conexao;
	}
	
	/**
	 * Modelo b�sico para uma a��o de salvar itens na database. 
	 * @param insertSQL Comando SQL a ser executado
	 * @param parametros O Apache Derby n�o aceita concatena��o de strings, por isso os par�metros devem ser passados separadamente
	 * @throws Exception Uma exception pode ser lan�ada em caso de viola��o de chave prim�ria ou nulidade de atributos NOT NULL
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
	 * Modelo b�sico para uma a��o de atualizar itens na database. 
	 * @param insertSQL Comando SQL a ser executado
	 * @param parametros O Apache Derby n�o aceita concatena��o de strings, por isso os par�metros devem ser passados separadamente
	 * @throws Exception Uma exception pode ser lan�ada em caso de viola��o de chave prim�ria ou nulidade de atributos NOT NULL
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
	 * Modelo b�sico para uma a��o de excluir itens na database. 
	 * @param insertSQL Comando SQL a ser executado
	 * @param parametros O Apache Derby n�o aceita concatena��o de strings, por isso os par�metros devem ser passados separadamente
	 * @throws Exception Uma exception pode ser lan�ada em caso de viola��o de chave prim�ria ou nulidade de atributos NOT NULL
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
	 * @throws SQLException Uma exception pode ser lan�ada caso o banco ainda n�o possa ser desligado.
	 */
	protected void shutdown() throws SQLException{
		getConexao().createStatement().executeUpdate("SHUTDOWN");
	}
}
