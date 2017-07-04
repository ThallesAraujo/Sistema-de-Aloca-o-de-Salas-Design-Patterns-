package graphicalui.objetos;

import objetos.Evento;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Classe do objeto de exibição de detalhes de eventos em listas (ListViews).
 * @author Thalles
 *
 */
public class EventoSelectionViewer extends VBox{

	private Label nome;
	private Label inicio;
	private Label fim;
	
	public EventoSelectionViewer(){
		
		HBox cabecalho = new HBox();
		cabecalho.setStyle("-fx-background-color: #a0ddce; -fx-border-color: transparent transparent transparent transparent;");
		Label lbCabecalho = new Label("Evento selecionado:");
		nome = new Label();
		inicio = new Label();
		fim = new Label();
		this.setSpacing(5);
		cabecalho.getChildren().add(lbCabecalho);
		VBox conteiner = new VBox();
		conteiner.setPadding(new Insets(5,5,5,5));
		conteiner.getChildren().addAll(nome,inicio,fim);
		this.getChildren().addAll(cabecalho,conteiner);
	}
	
	public EventoSelectionViewer(int maxSize){
		HBox cabecalho = new HBox();
		cabecalho.setStyle("-fx-background-color: #a0ddce; -fx-border-color: transparent transparent transparent transparent;");
		Label lbCabecalho = new Label("Evento selecionado:");
		nome = new Label();
		inicio = new Label();
		fim = new Label();
		this.setSpacing(5);
		cabecalho.getChildren().add(lbCabecalho);
		VBox conteiner = new VBox();
		conteiner.setPadding(new Insets(5,5,5,5));
		conteiner.getChildren().addAll(nome,inicio,fim);
		this.setMaxWidth(maxSize);
		this.getChildren().addAll(cabecalho,conteiner);
	}
	
	/**
	 * Método de definição do evento a ser exibido pelo Selection Viewer.
	 * @param evento Evento a ser exibido.
	 */
	public void setEvento(Evento evento){
		nome.setText(evento.getNome());
		inicio.setText("Começa "+evento.getInicio());
		fim.setText("Termina "+evento.getFim());
	}
	
}
