package graphicalui.objetos;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

/**
 * Classe da barra superior (barra de tÝtulo e controles) das janelas.
 * @author Thalles
 *
 */
public class Espacador extends HBox{
	
	/**
	 * Primeiro construtor da classe de espašador de janelas.
	 * @param height Altura do espašador.
	 */
	public Espacador(int height){
		this.setStyle("-fx-background-color: #42c6a5; -fx-border-color: transparent transparent transparent transparent;");
		this.setPadding(new Insets(-5,0,-5,0));
		this.setMinHeight(height);
		this.setMaxHeight(height);
	}
	
	/**
	 * Segundo construtor da classe de espašador de janelas.
	 * @param height Altura do espašador.
	 * @param width Largura do espašador.
	 */
	public Espacador(int height, int width){
		this.setStyle("-fx-background-color: #42c6a5; -fx-border-color: transparent transparent transparent transparent;");
		this.setPadding(new Insets(-5,0,-5,0));
		this.setMinHeight(35);
		this.setMaxHeight(35);
		this.setMinWidth(width);
		this.setMaxWidth(width);
	}

}
