package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import objetos.Alocacao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe de CRUD (Create, Update, Rescue, Delete) para o objeto aloca��o
 * @author Thalles
 *
 */
public class AlocacoesDAO extends DataAccessObject{
	
	/**
	 * M�todo de resgate das aloca��es registradas na database
	 * @return Aloca��es registradas, em uma lista
	 * @throws Exception Uma exce��o pode ser lan�ada caso ainda n�o haja aloca��es cadastradas ou a database ainda n�o tenha sido criada.
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
	 * M�todo de salvamento de uma aloca��o na database. 
	 * @param alocacao Aloca��o a ser salva
	 * @throws Exception Uma exce��o pode ser lan�ada caso a aloca��o n�o esteja de acordo com as regras para aloca��o de salas ou caso a sala esteja indispon�vel para o hor�rio solicitado.
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
	 * M�todo de remo��o de uma aloca��o a partir do identificador �nico de evento.
	 * @param idEvento Identificador �nico do evento alocado
	 * @throws Exception Uma exce��o pode ser lan�ada caso o evento n�o tenha sido alocado ou caso o mesmo n�o exista.
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
	 * M�todo de remo��o de uma aloca��o a partir do identificador �nico da sala.
	 * @param idSala Identificador �nico da sala alocada.
	 * @throws Exception Uma exce��o pode ser lan�ada caso a sala n�o exita ou n�o tenha sido alocada.
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
