package prompt;

import javafx.collections.ObservableList;
import objetos.Sala;

/**
 * Classe de impressora que, para cada sala cadastrada na database, mostra apenas os atributos importantes para a alocação.
 * @author Thalles
 *
 */
public class AllocationReferenceSalaPrinter implements SalaPrinter{

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
						" | Finalidade: "+sala.getFinalidade()+
						" | Tipo: "+sala.getTipo()+
						" | Aberta?: "+isAberto);
			}
		}else{
			System.out.println("Sem salas cadastradas");
		}

	}


}


