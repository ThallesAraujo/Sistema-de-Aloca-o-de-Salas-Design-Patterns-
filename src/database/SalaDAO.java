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
	 * M�todo de salvamento de uma sala na database
	 * @param sala A sala a ser salva deve respeitar as regras de salvamento e cria��o de salas
	 * @throws Exception Uma exception pode ser lan�ada caso as regras citadas n�o sejam respeitadas
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
	 * M�todo para exclus�o de salas.
	 * @param idSala Identificador �nico da sala a ser exclu�da
	 * @throws Exception Uma exce��o pode ser lan�ada caso a sala n�o exista
	 */
	public void excluir(String idSala) throws Exception{
		String sql = "DELETE FROM SALAS WHERE id = ?";
		super.excluir(sql, idSala);

	}


	/**
	 * M�todo de resgate das salas cadastrados na database
	 * @return Lista contendo objetos do tipo sala que est�o cadastrados na database
	 * @throws Exception Uma exce��o pode ser lan�ada caso a database n�o exista ou caso as tabelas tenham sido apagadas
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
