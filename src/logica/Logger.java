package logica;

import database.LogsDAO;
import objetos.Log;
import javafx.collections.ObservableList;

public class Logger {
	
	private static LogsDAO LDAO = new LogsDAO();
	
	public static ObservableList<Log> getLogs() throws Exception{
		return LDAO.getLogs();
	}

	public static void adicionarLog(Log log){
		LDAO.salvar(log);
	}
	
	public static boolean databaseVazia(){
		boolean retorno = true;
		try {
			ObservableList<Log> logs = getLogs();
			if(logs==null){
				retorno = true;
			}else{
				for(Log l: logs){
					if(!(l.getHorario().equals(null))){
						retorno =  false;
					}else{
						retorno = true;
					}
				}
			}
		} catch (Exception e) {}
		
		return retorno;
		
	}
}
