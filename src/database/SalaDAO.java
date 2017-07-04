package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import exceptions.RoomsAllocationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objetos.Sala;

/**
 * Classe de CRUD (Create, Rescue, Update, Delete) para o objeto sala
 * @author Thalles
 *
 */
public class SalaDAO extends DataAccessObject{

	/**
	 * Método de salvamento de uma sala na database
	 * @param sala A sala a ser salva deve respeitar as regras de salvamento e criação de salas
	 * @throws Exception Uma exception pode ser lançada caso as regras citadas não sejam respeitadas
	 */
	public void salvar(Sala sala) throws Exception{

		String sql = "INSERT INTO SALAS (id, apelido, finalidade, capacidade, tipo, aberto) VALUES (?,?,?,?,?,?)";
		if(!sala.getId().equals("")){
			try{
				super.salvar(sql,sala.getId(),sala.getApelido(),sala.getFinalidade(),sala.getCapacidade(),sala.getTipo(),sala.getIsAberto());
			}catch(Exception e){
				throw new RoomsAllocationException(e.getMessage());
			}
		}else{
			throw new RoomsAllocationException("Identificacao Invalida");
		}
	}

	
	/**
	 * Método para exclusão de salas.
	 * @param idSala Identificador único da sala a ser excluída
	 * @throws Exception Uma exceção pode ser lançada caso a sala não exista
	 */
	public void excluir(String idSala) throws Exception{
		String sql = "DELETE FROM SALAS WHERE id = ?";
		super.excluir(sql, idSala);

	}


	/**
	 * Método de resgate das salas cadastrados na database
	 * @return Lista contendo objetos do tipo sala que estão cadastrados na database
	 * @throws Exception Uma exceção pode ser lançada caso a database não exista ou caso as tabelas tenham sido apagadas
	 */
	public ObservableList<Sala> getSalas() throws Exception{
		ObservableList<Sala> salas = FXCollections.observableArrayList();
		String sql = "SELECT * FROM SALAS";
		PreparedStatement statement = ConnectionDatabase.getConexao().prepareStatement(sql);
		ResultSet result = statement.executeQuery();

		while(result.next()){
			Sala sala = new Sala(
					result.getString("id"),
					result.getString("apelido"),
					result.getInt("capacidade"),
					result.getString("finalidade"),
					result.getString("tipo"),
					result.getString("aberto"));

			salas.add(sala);
		}

		result.close();
		statement.close();
		return salas;



	}


}
