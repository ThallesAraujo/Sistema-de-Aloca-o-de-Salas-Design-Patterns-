package objetos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	
	private String horario;
	private String evento;
	
	public Log(String evento){
		this.evento = evento;
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		this.horario = formater.format(new Date());
	}
	
	public Log(String evento, String horario){
		this.evento = evento;
		this.horario = horario;
	}

	public String getHorario(){
		return this.horario;
	}
	
	public String getEvento(){
		return this.evento;
	}
	
}
