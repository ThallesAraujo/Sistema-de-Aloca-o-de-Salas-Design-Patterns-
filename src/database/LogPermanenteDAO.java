package database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import exceptions.RoomsAllocationException;
import objetos.Log;


public class LogPermanenteDAO extends DataAccessObject{
	
	private static FileWriter log;
	private static String arqLog = "Alocador - Log de Eventos.log";
	private static PrintWriter printer;
	
	public static void salvar(Log evento) throws RoomsAllocationException{
		
		if(!verificaExistencia()){
			try {
				log = new FileWriter(arqLog);
				printer = new PrintWriter(log);
				printer.printf("Evento: "+evento.getEvento()+" Horário: "+evento.getHorario()+"%n");
				printer.close();
				log.close();
			} catch (IOException e) {
				throw new RoomsAllocationException(e.getMessage());
			}
		}else{
			try {
				log = new FileWriter(arqLog,true);
				printer = new PrintWriter(log);
				printer.printf("Evento: "+evento.getEvento()+" Horário: "+evento.getHorario()+"%n");
				printer.close();
				log.close();
			} catch (IOException e) {
				throw new RoomsAllocationException(e.getMessage());
			}
		}
		
		
	}
	
	private static boolean verificaExistencia(){
		if(new File(arqLog).exists()){
			return true;
		}else{
			return false;
		}
		
	}
}
