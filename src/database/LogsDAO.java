package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objetos.Log;

public class LogsDAO extends DataAccessObject{
	
	public void salvar(Log log){
		String sql = "INSERT INTO LOGS (horario, evento) VALUES(?,?)";
		try{
			super.salvar(sql,log.getHorario(),log.getEvento());
			LogPermanenteDAO.salvar(log);
		}catch(Exception e){}
	}
	
	public ObservableList<Log> getLogs() throws SQLException{
		
		ObservableList<Log> logs = FXCollections.observableArrayList();
		String sql = "SELECT * FROM LOGS";
		PreparedStatement statement = ConnectionDatabase.getConexao().prepareStatement(sql);
		ResultSet result = statement.executeQuery();

		while(result.next()){
			Log log = new Log(result.getString("evento"),result.getString("horario"));
			logs.add(log);
		}
		
		result.close();
		statement.close();
		return logs;
	}
}
