package graphicalui.janelas;

import graphicalui.objetos.AlertaDeErro;
import graphicalui.objetos.Espacador;
import insidefx.undecorator.Undecorator;
import insidefx.undecorator.UndecoratorScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Classe de janela de adição de salas. 
 * (Padrão Strategy: componente de estratégia abstrata)
 * (Padrão Template Method: classe de método gabarito)
 * @author Thalles
 *
 */
public abstract class AdicionarSala {

	protected TextField tfCodigo;
	protected TextField tfApelido;
	protected TextField tfCapacidade;
	protected final Stage mainStage;
	
	public AdicionarSala(String titulo){
		
		Pane noPrincipal = new Pane();
		noPrincipal.setMinWidth(300);
		noPrincipal.setMinHeight(200);
		VBox conteiner = new VBox(5);
		conteiner.setAlignment(Pos.CENTER);
		
		Espacador espacadorJanela = new Espacador(35,314);
		
		Label lbCodigo = new Label("Código:        ");
		tfCodigo = new TextField();
		HBox ctCodigo = new HBox(5);
		ctCodigo.setHgrow(tfCodigo, Priority.ALWAYS);
		ctCodigo.setAlignment(Pos.CENTER_LEFT);
		ctCodigo.setPadding(new Insets(5,5,5,5));
		
		Label lbApelido = new Label("Apelido:       ");
		tfApelido = new TextField();
		HBox ctApelido = new HBox(5);
		ctApelido.setHgrow(tfApelido, Priority.ALWAYS);
		ctApelido.setAlignment(Pos.CENTER_LEFT);
		ctApelido.setPadding(new Insets(5,5,5,5));
		
		Label lbCapacidade = new Label("Capacidade: ");
		tfCapacidade = new TextField();
		tfCapacidade.setMinWidth(60);
		tfCapacidade.setMaxWidth(60);
		HBox ctCapacidade = new HBox(5);
		ctCapacidade.setAlignment(Pos.CENTER_LEFT);
		ctCapacidade.setPadding(new Insets(5,5,5,5));
		
		HBox ctBotao = new HBox();
		ctBotao.setAlignment(Pos.CENTER_RIGHT);
		ctBotao.setPadding(new Insets(5,5,5,5));
		Button btSalvar = new Button("Salvar");
		btSalvar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(Integer.parseInt(tfCapacidade.getText())>0){
					salvamento();
				}else{
					AlertaDeErro alerta = new AlertaDeErro("Salvar sala","Valor inválido","A capacidade deve ser maior que 0");
				}
			}
		});
		
		ctCodigo.getChildren().addAll(lbCodigo,tfCodigo);
		ctApelido.getChildren().addAll(lbApelido,tfApelido);
		ctCapacidade.getChildren().addAll(lbCapacidade,tfCapacidade);
		ctBotao.getChildren().add(btSalvar);
		conteiner.getChildren().addAll(espacadorJanela,ctCodigo,ctApelido,ctCapacidade,ctBotao);
		noPrincipal.getChildren().add(conteiner);
		
		mainStage = new Stage();
		mainStage.setTitle(titulo);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("stagedecoration.fxml"));
		loader.setController(this);
		Region root = (Region) noPrincipal;
		
		final UndecoratorScene cena = new UndecoratorScene(mainStage,root);
		cena.setAsStageDraggable(mainStage, noPrincipal);
		
		mainStage.setScene(cena);
		mainStage.sizeToScene();
		mainStage.toFront();
		
		Undecorator undecorator = cena.getUndecorator();
		undecorator.simpleWindow();
		mainStage.setMinWidth(undecorator.getMinWidth());
		mainStage.setMinHeight(undecorator.getMinHeight());
		mainStage.setResizable(false);
		noPrincipal.setStyle("/graphicalui/design/WindowDesign.css");
		cena.getStylesheets().add("/graphicalui/design/WindowDesign.css");
		mainStage.show();
	}
	
	/**
	 * Método de salvamento de sala.
	 * (Padrão Template Method: método gabarito a ser implementado pelas subclasses)
	 */
	public abstract void salvamento();

}
