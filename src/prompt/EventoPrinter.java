package prompt;

import javafx.collections.ObservableList;
import objetos.Evento;

/**
 * Interface de supertipo das impressoras de listas de eventos.
 * @author Thalles
 *
 */
public interface EventoPrinter {
	
	/**
	 * M�todo de impress�o da lista de eventos.
	 * @param lista Lista de eventos a ser exibida.
	 */
	public void print(ObservableList<Evento> lista);

}
