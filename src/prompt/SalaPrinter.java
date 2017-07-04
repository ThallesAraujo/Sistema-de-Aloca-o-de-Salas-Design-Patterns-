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
	 * Método de impressão da lista de salas.
	 * @param lista Lista que contém as salas a serem impressas.
	 */
	public void print(ObservableList<Sala> lista);

}
