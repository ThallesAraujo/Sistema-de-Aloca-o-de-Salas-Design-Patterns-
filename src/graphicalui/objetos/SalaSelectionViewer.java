package graphicalui.objetos;

import objetos.Sala;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Classe do objeto de exibição de detalhes de salas em listas (ListViews).
 * @author Thalles
 *
 */
public class SalaSelectionViewer extends VBox{
	
	private Label apelido;
	private Label tipo;
	private Label capacidade;
	
	public SalaSelectionViewer(){
		
		HBox cabecalho = new HBox();
		cabecalho.setStyle("-fx-background-color: #a0ddce; -fx-border-color: transparent transparent transparent transparent;");
		Label lbCabecalho = new Label("Sala selecionada:");
		apelido = new Label();
		tipo = new Label();
		capacidade = new Label();
		this.setSpacing(5);
		cabecalho.getChildren().add(lbCabecalho);
		VBox conteiner = new VBox();
		conteiner.setPadding(new Insets(5,5,5,5));
		conteiner.getChildren().addAll(apelido,tipo,capacidade);
		this.getChildren().addAll(cabecalho,conteiner);
		
	}
	
	public SalaSelectionViewer(int maxSize){
		HBox cabecalho = new HBox();
		cabecalho.setStyle("-fx-background-color: #a0ddce; -fx-border-color: transparent transparent transparent transparent;");
		Label lbCabecalho = new Label("Sala selecionada:");
		apelido = new Label();
		tipo = new Label();
		capacidade = new Label();
		this.setSpacing(5);
		cabecalho.getChildren().add(lbCabecalho);
		VBox conteiner = new VBox();
		conteiner.setPadding(new Insets(5,5,5,5));
		conteiner.getChildren().addAll(apelido,tipo,capacidade);
		this.setMaxWidth(maxSize);
		this.getChildren().addAll(cabecalho,conteiner);
	}
	
	/**
	 * Método de definição da sala a ser exibida pelo Selection Viewer.
	 * @param sala Sala a ser exibida.
	 */
	public void setSala(Sala sala){
		if(sala.getApelido().equals(null) || sala.getApelido().equalsIgnoreCase("")){
			apelido.setText("<<Sem Apelido>>");
		}else{
			apelido.setText(sala.getApelido());
		}
		tipo.setText(sala.getFinalidade()+" - "+sala.getTipo());
		capacidade.setText("Capacidade: "+sala.getCapacidade());
	}

}
