package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import exceptions.RoomsAllocationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objetos.Evento;

/**
 * Classe de CRUD (Create, Rescue, Update, Delete) para o objeto evento
 * @author Thalles
 *
 */
public class EventoDAO extends DataAccessObject {

	/**
	 * Método de salvamento de um evento na database
	 * @param evento O evento a ser salvo na database. Deve respeitar as regras da database e da criação de eventos
	 * @throws Exception Uma exception pode ser lançada caso as regras citadas não sejam respeitadas
	 */
	public void salvar(Evento evento) throws Exception{
		String sql = "INSERT INTO EVENTOS (id, nome, inicio, fim, contato, area, repeticoes,tipo) VALUES (?,?,?,?,?,?,?,?)";
		try{
		super.salvar(sql, evento.getId(),evento.getNome(),evento.getInicio(),evento.getFim(),evento.getContato(),evento.getArea(),evento.getRepeticoes(),evento.getTipo());
		}catch(Exception e){
			throw new RoomsAllocationException(e.getMessage());
		}
	}
		


	/**
	 * Método para exclusão de eventos.
	 * @param idEvento Identificador único do evento a ser excluído
	 * @throws Exception Uma exceção pode ser lançada caso o evento não exista.
	 */
	public void excluir(String idEvento) throws Exception{
		String sql = "DELETE FROM EVENTOS WHERE id = ?";
		super.excluir(sql, idEvento);

	}


	/**
	 * Método de resgate dos eventos cadastrados na database
	 * @return Lista contendo objetos do tipo evento que estão cadastrados na database
	 * @throws Exception Uma exceção pode ser lançada caso a database não exista ou caso as tabelas tenham sido apagadas
	 */
	public ObservableList<Evento> getEventos() throws Exception{
		ObservableList<Evento> eventos = FXCollections.observableArrayList();
		String sql = "SELECT * FROM EVENTOS";

		PreparedStatement statement = ConnectionDatabase.getConexao().prepareStatement(sql);
		ResultSet result = statement.executeQuery();

		while(result.next()){
			Evento evento = new Evento(
					result.getString("id"),
					result.getString("nome"),
					result.getString("inicio"),
					result.getString("fim"),
					result.getString("contato"),
					result.getString("area"),
					result.getInt("repeticoes"),
					result.getString("tipo"));

			eventos.add(evento);

		}

		result.close();
		statement.close();
		return eventos;



	}


}
