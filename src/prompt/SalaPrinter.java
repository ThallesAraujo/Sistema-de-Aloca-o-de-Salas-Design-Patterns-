package prompt;

import objetos.Sala;
import javafx.collections.ObservableList;

/**
 * Interface de supertipo das classes impressoras da lista de salas.
 * @author Thalles
 *
 */
public interface SalaPrinter {
	
	/**
	 * M�todo de impress�o da lista de salas.
	 * @param lista Lista que cont�m as salas a serem impressas.
	 */
	public void print(ObservableList<Sala> lista);

}
