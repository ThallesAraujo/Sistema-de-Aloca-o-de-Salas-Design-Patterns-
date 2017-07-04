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
	 * M�todo de salvamento de um evento na database
	 * @param evento O evento a ser salvo na database. Deve respeitar as regras da database e da cria��o de eventos
	 * @throws Exception Uma exception pode ser lan�ada caso as regras citadas n�o sejam respeitadas
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
	 * M�todo para exclus�o de eventos.
	 * @param idEvento Identificador �nico do evento a ser exclu�do
	 * @throws Exception Uma exce��o pode ser lan�ada caso o evento n�o exista.
	 */
	public void excluir(String idEvento) throws Exception{
		String sql = "DELETE FROM EVENTOS WHERE id = ?";
		super.excluir(sql, idEvento);

	}


	/**
	 * M�todo de resgate dos eventos cadastrados na database
	 * @return Lista contendo objetos do tipo evento que est�o cadastrados na database
	 * @throws Exception Uma exce��o pode ser lan�ada caso a database n�o exista ou caso as tabelas tenham sido apagadas
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
