package graphicalui.janelas;

import graphicalui.objetos.AlertaDeErro;
import graphicalui.objetos.AlertaDeInformacao;
import graphicalui.objetos.Espacador;
import graphicalui.objetos.EventoSelectionViewer;
import insidefx.undecorator.Undecorator;
import insidefx.undecorator.UndecoratorScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import logica.OperadorEventos;
import objetos.Evento;
import objetos.comandos.ComandoRemoverEvento;
import objetos.comandos.Invoker;

/**
 * Classe da janela de exclusão de eventos.
 * @author Thalles
 *
 */
public class ExcluirEvento {
	
public ExcluirEvento(){
		
		Pane noPrincipal = new Pane();
		noPrincipal.setMinWidth(300);
		noPrincipal.setMinHeight(580);
		VBox conteiner = new VBox(5);
		conteiner.setAlignment(Pos.CENTER);
		
		Label label = new Label("Selecione o evento que deseja excluir:");
		
		Espacador espacadorJanela = new Espacador(35,314);
		
		ListView lista = new ListView();
		lista.setPadding(new Insets(5,5,5,5));
		ObservableList<String> ids  = FXCollections.observableArrayList();
		boolean semEventos = false;
		try {
			semEventos = OperadorEventos.databaseVazia();
		} catch (Exception e1) {}
		try {
			ObservableList<Evento> eventos = OperadorEventos.getEventos();
			for(Evento e: eventos){
				ids.add(e.getId());
			}
		} catch (Exception e) {
			semEventos = true;
		}
	
		if(!semEventos){
			lista.setItems(ids);
		}else{}
		
		EventoSelectionViewer selection = new EventoSelectionViewer();
		
		lista.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				try {
					selection.setEvento(OperadorEventos.getEvento((String) lista.getSelectionModel().getSelectedItem()));
				} catch (Exception e) {}
			}
			
		});
		
		Button btExcluir = new Button("Excluir evento");
		HBox ctBotao = new HBox();
		ctBotao.setAlignment(Pos.CENTER_RIGHT);
		ctBotao.setPadding(new Insets(5,5,5,5));
		ctBotao.getChildren().add(btExcluir);
		
		conteiner.getChildren().addAll(espacadorJanela,label,lista,selection,ctBotao);
		noPrincipal.getChildren().add(conteiner);
		
		Stage mainStage = new Stage();
		mainStage.setTitle("Excluir evento");
		
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
		if(!semEventos){
			mainStage.show();
		}else{
			AlertaDeErro erro = new AlertaDeErro("Sem eventos","Sem eventos cadastrados","Não há nenhum evento cadastrado na database");
		}
		
		btExcluir.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				try {
					Invoker.adicionarEExecutar(new ComandoRemoverEvento((String) lista.getSelectionModel().getSelectedItem()));
					mainStage.close();
					JanelaPrincipal.atualizar();
					AlertaDeInformacao alerta = new AlertaDeInformacao("Excluir evento","Evento excluído","O evento foi excluído com sucesso");
				} catch (Exception e) {
					AlertaDeErro erro = new AlertaDeErro("Seleção nula","Nenhum evento selecionado","Nenhum evento foi selecionado");
				}
			}
			
		});
		
	}

}
