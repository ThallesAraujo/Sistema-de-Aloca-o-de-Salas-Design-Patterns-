package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import objetos.Alocacao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe de CRUD (Create, Update, Rescue, Delete) para o objeto alocação
 * @author Thalles
 *
 */
public class AlocacoesDAO extends DataAccessObject{
	
	/**
	 * Método de resgate das alocações registradas na database
	 * @return Alocações registradas, em uma lista
	 * @throws Exception Uma exceção pode ser lançada caso ainda não haja alocações cadastradas ou a database ainda não tenha sido criada.
	 */
	public ObservableList<Alocacao> getAlocacoes() throws Exception{
		
		ObservableList<Alocacao> alocacoes = FXCollections.observableArrayList();
		try{
			String sql = "SELECT * FROM ALOCACOES";
			PreparedStatement statement = ConnectionDatabase.getConexao().prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			while(result.next()){
				Alocacao alocacao = new Alocacao(result.getString("id_sala"),result.getString("id_evento"));
				alocacoes.add(alocacao);
			}
			result.close();
			statement.close();
			return alocacoes;
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	/**
	 * Método de salvamento de uma alocação na database. 
	 * @param alocacao Alocação a ser salva
	 * @throws Exception Uma exceção pode ser lançada caso a alocação não esteja de acordo com as regras para alocação de salas ou caso a sala esteja indisponível para o horário solicitado.
	 */
	public void salvarAlocacao(Alocacao alocacao) throws Exception{
		
		try{
			String sql = "INSERT INTO ALOCACOES (id_sala, id_evento) values (?,?)";
			super.salvar(sql, alocacao.getIdSala(),alocacao.getIdEvento());
		}catch(Exception e){
			throw new Exception(e);
		}
		
	}
	
	/**
	 * Método de remoção de uma alocação a partir do identificador único de evento.
	 * @param idEvento Identificador único do evento alocado
	 * @throws Exception Uma exceção pode ser lançada caso o evento não tenha sido alocado ou caso o mesmo não exista.
	 */
	public void excluirAlocacao(String idEvento) throws Exception{
		try{
			String sql = "DELETE FROM ALOCACOES WHERE id_evento = (?)";
			super.excluir(sql, idEvento);
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	/**
	 * Método de remoção de uma alocação a partir do identificador único da sala.
	 * @param idSala Identificador único da sala alocada.
	 * @throws Exception Uma exceção pode ser lançada caso a sala não exita ou não tenha sido alocada.
	 */
	public void excluirAlocacaoPorSala(String idSala) throws Exception{
		try{
			String sql = "DELETE FROM ALOCACOES WHERE id_sala = (?)";
			super.excluir(sql, idSala);
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	

}
