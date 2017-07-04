package database;

/**
 * Fachada com os métodos para exclusão da database, bem como os dados nela contidos.
 * @author Thalles
 *
 */

public class FachadaZerarSistema {
	
	/**
	 * Método de exclusão de uma database já inicializada
	 * @throws Exception Uma exceção pode ser lançada em caso de falha de comunicação com o banco de dados ou caso a database não exista.
	 */
	public void databaseExistente() throws Exception{
		database.Inicializar.destruirDados();
		database.Inicializar.inicializacao();
	}
	
	/**
	 * Método de exclusão de uma database não inicializada.
	 * @throws Exception Uma exceção pode ser lançada em caso de falha de comunicação com o banco de dados
	 */
	public void databaseInexistente() throws Exception{
		database.Inicializar.inicializacao();
		database.Inicializar.destruirDados();
		database.Inicializar.inicializacao();
	}

}
