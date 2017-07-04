package prompt;

import javafx.collections.ObservableList;
import logica.OperadorAlocacoes;
import objetos.Evento;

/**
 * Impressora padrão dos eventos cadastrados na database.
 * @author Thalles
 *
 */
public class DefaultEventoPrinter implements EventoPrinter{

	/**
	 * Método de impressão de eventos.
	 */
	@Override
	public void print(ObservableList<Evento> lista) {

		if(lista.size()>0){
			for(Evento evento: lista){
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
			System.out.println("Sem eventos cadastrados");
		}

	}







}
