package prompt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.OperadorAlocacoes;
import objetos.Evento;

/**
 * Impressora de eventos que exibe somente os eventos já realizados
 * @author Thalles
 *
 */
public class RealizadosEventoPrinter implements EventoPrinter{

	/**
	 * Método de impressão da lista de eventos.
	 */
	@Override
	public void print(ObservableList<Evento> lista) {
		System.out.println();
		System.out.println("Eventos já realizados:");
		System.out.println();
		ObservableList<Evento> realizados = FXCollections.observableArrayList();
		Calendar clAtual = Calendar.getInstance();
		Calendar clComp = Calendar.getInstance();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy HH:mm");
		for(Evento e: lista){
			try {
				Date dataEvento = formato.parse(e.getInicio());
				clComp.setTime(dataEvento);
				if(clComp.before(clAtual)){
					realizados.add(e);
				}
				
			} catch (ParseException e1) {
				System.out.println(e1.getMessage());
			}
		}
		
		if(realizados.size()>0){
			for(Evento evento: realizados){
				String repeticoes = null;
				String idSala = null;
				if(evento.getRepeticoes()>0){
					repeticoes = "Se repete por "+evento.getRepeticoes()+" semanas";
				}else{
					repeticoes = "Nao se repete";
				}

				try {
					if(OperadorAlocacoes.isAlocado(evento.getId())){
						idSala = "Alocado na sala "+OperadorAlocacoes.getAlocacao(evento.getId()).getIdSala();
					}else{
						idSala = "Nao foi alocado ainda";
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				System.out.println("ID: "+evento.getId()+
						" | Nome: "+evento.getNome()+
						" | Comeca em "+evento.getInicio()+
						" | Termina em "+evento.getFim()+
						" | Responsavel: "+evento.getContato()+
						" | Area: "+evento.getArea()+
						" | "+repeticoes+
						" | "+idSala);
			}
		}else{
			System.out.println("Não há eventos já realizados");
		}
		
	}
	
	

}
