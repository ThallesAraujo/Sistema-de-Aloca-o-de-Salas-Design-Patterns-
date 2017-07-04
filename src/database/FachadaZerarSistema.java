package database;

/**
 * Fachada com os m�todos para exclus�o da database, bem como os dados nela contidos.
 * @author Thalles
 *
 */

public class FachadaZerarSistema {
	
	/**
	 * M�todo de exclus�o de uma database j� inicializada
	 * @throws Exception Uma exce��o pode ser lan�ada em caso de falha de comunica��o com o banco de dados ou caso a database n�o exista.
	 */
	public void databaseExistente() throws Exception{
		database.Inicializar.destruirDados();
		database.Inicializar.inicializacao();
	}
	
	/**
	 * M�todo de exclus�o de uma database n�o inicializada.
	 * @throws Exception Uma exce��o pode ser lan�ada em caso de falha de comunica��o com o banco de dados
	 */
	public void databaseInexistente() throws Exception{
		database.Inicializar.inicializacao();
		database.Inicializar.destruirDados();
		database.Inicializar.inicializacao();
	}

}
