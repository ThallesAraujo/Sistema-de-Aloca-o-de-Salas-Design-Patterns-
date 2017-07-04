package graphicalui.janelas;

import javafx.event.EventHandler;
import objetos.Sala;
import objetos.comandos.ComandoRemoverSala;
import objetos.comandos.Invoker;
import graphicalui.objetos.AlertaDeErro;
import graphicalui.objetos.AlertaDeInformacao;
import graphicalui.objetos.Espacador;
import graphicalui.objetos.SalaSelectionViewer;
import insidefx.undecorator.Undecorator;
import insidefx.undecorator.UndecoratorScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logica.OperadorSalas;

/**
 * Classe da janela de exclusão de salas.
 * @author Thalles
 *
 */
public class ExcluirSala {
	
	public ExcluirSala(){
		
		Pane noPrincipal = new Pane();
		noPrincipal.setMinWidth(300);
		noPrincipal.setMinHeight(580);
		VBox conteiner = new VBox(5);
		conteiner.setAlignment(Pos.CENTER);
		
		Label label = new Label("Selecione a sala que deseja excluir:");
		
		Espacador espacadorJanela = new Espacador(35,314);
		
		ListView lista = new ListView();
		lista.setPadding(new Insets(5,5,5,5));
		ObservableList<String> ids  = FXCollections.observableArrayList();
		boolean semSalas = true;
		try {
			semSalas = OperadorSalas.databaseVazia();
		} catch (Exception e1) {}
		try {
			ObservableList<Sala> salas = OperadorSalas.getSalas();
			for(Sala s: salas){
				ids.add(s.getId());
			}
		} catch (Exception e) {}
	
		if(!semSalas){
			lista.setItems(ids);
		}
		
		SalaSelectionViewer selection = new SalaSelectionViewer();
		
		lista.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				try {
					selection.setSala(OperadorSalas.getSala((String) lista.getSelectionModel().getSelectedItem()));
				} catch (Exception e) {}
			}
			
		});
		
		Button btExcluir = new Button("Excluir sala");
		HBox ctBotao = new HBox();
		ctBotao.setAlignment(Pos.CENTER_RIGHT);
		ctBotao.setPadding(new Insets(5,5,5,5));
		ctBotao.getChildren().add(btExcluir);
		
		conteiner.getChildren().addAll(espacadorJanela,label,lista,selection,ctBotao);
		noPrincipal.getChildren().add(conteiner);
		
		Stage mainStage = new Stage();
		mainStage.setTitle("Excluir sala");
		
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
		if(!semSalas){
			mainStage.show();
		}else{
			AlertaDeErro erro = new AlertaDeErro("Sem salas","Sem salas cadastradas","Não há nenhuma sala cadastrada na database");
		}
		
		btExcluir.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				try {
					Invoker.adicionarEExecutar(new ComandoRemoverSala((String) lista.getSelectionModel().getSelectedItem()));
					mainStage.close();
					JanelaPrincipal.atualizar();
					AlertaDeInformacao alerta = new AlertaDeInformacao("Excluir sala","Sala excluída","A sala foi excluída com sucesso");
				} catch (Exception e) {
					AlertaDeErro erro = new AlertaDeErro("Seleção nula","Nenhuma sala selecionada","Nenhuma sala foi selecionada");
				}
			}
			
		});
		
	}

}
