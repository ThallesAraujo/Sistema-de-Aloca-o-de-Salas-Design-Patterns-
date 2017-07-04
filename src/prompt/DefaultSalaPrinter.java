package prompt;

import javafx.collections.ObservableList;
import objetos.Sala;

/**
 * Classe de impressora padrão das salas cadastradas na database.
 * @author Thalles
 *
 */
public class DefaultSalaPrinter implements SalaPrinter{

	/**
	 * Método de exibição da lista de salas.
	 */
	@Override
	public void print(ObservableList<Sala> lista) {
		
		if(lista.size()>0){
			for(Sala sala: lista){
				String isAberto = "indefinido";
				if(sala.getIsAberto()!=null && !sala.getIsAberto().equalsIgnoreCase("")){
					isAberto = sala.getIsAberto();
				}
				System.out.println("ID: "+sala.getId()+
						" | Apelido: "+sala.getApelido()+
						" | Finalidade: "+sala.getFinalidade()+
						" | Tipo: "+sala.getTipo()+
						" | Capacidade: "+sala.getCapacidade()+
						" | Aberta?: "+isAberto);
			}
		}else{
			System.out.println("Sem salas cadastradas");
		}
		
	}

}
