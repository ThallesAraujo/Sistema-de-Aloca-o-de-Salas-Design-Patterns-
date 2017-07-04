package graphicalui.objetos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import objetos.Log;
import javafx.collections.ObservableList;
import logica.Logger;

public class LogExporter {

	public LogExporter(){

		if(!Logger.databaseVazia()){
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter("Alocador - Log de Eventos.csv"));
				ObservableList<Log> logs = Logger.getLogs();
				writer.write("Horário;Evento\n");
				for(Log log:logs){
					writer.write(log.getHorario()+";"+log.getEvento()+"\n");
				}
				AlertaDeInformacao alerta = new AlertaDeInformacao("Log Exporter","Log exportado com sucesso","Acesse-o em sua Área de Trabalho");
				writer.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			AlertaDeErro alerta = new AlertaDeErro("Log Exporter","Sem logs","Não há logs no log de eventos");
		}

	}

}
