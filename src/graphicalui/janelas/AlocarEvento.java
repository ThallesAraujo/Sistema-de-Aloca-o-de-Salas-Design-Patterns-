package graphicalui.janelas;

import objetos.Evento;
import objetos.Sala;
import objetos.comandos.ComandoAlocarEvento;
import objetos.comandos.Invoker;
import graphicalui.objetos.AlertaDeErro;
import graphicalui.objetos.AlertaDeInformacao;
import graphicalui.objetos.Espacador;
import graphicalui.objetos.EventoSelectionViewer;
import graphicalui.objetos.SalaSelectionViewer;
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
import logica.OperadorAlocacoes;
import logica.OperadorEventos;
import logica.OperadorSalas;

/**
 * Classe da janela de alocação de eventos.
 * @author Thalles
 *
 */
public class AlocarEvento {
	
	public AlocarEvento(){
		
		Pane noPrincipal = new Pane();
		noPrincipal.setMinWidth(500);
		noPrincipal.setMinHeight(600);
		
		Espacador espacadorJanela = new Espacador(35,514);
		
		VBox conteinerJanela = new VBox();
		HBox conteinerGeral = new HBox(5);
		conteinerGeral.setPadding(new Insets(5,5,5,5));
		
		VBox conteinerEvento = new VBox(5);
		VBox conteinerSala = new VBox(5);
		
		Label selecioneEvento = new Label("Selecione o evento a ser alocado:");
		ListView lvEventos = new ListView();
		ObservableList<String> idsEventos = FXCollections.observableArrayList();
		boolean semEventos = false;
		try {
			semEventos = OperadorEventos.databaseVazia();
		} catch (Exception e1) {}
		try {
			ObservableList<Evento> eventos = OperadorEventos.getEventos();
			for(Evento e: eventos){
				idsEventos.add(e.getId());
			}
		} catch (Exception e) {}
		
		lvEventos.setItems(idsEventos);
		EventoSelectionViewer selectionEvento = new EventoSelectionViewer(250);
		
		lvEventos.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event arg0) {
				try {
					selectionEvento.setEvento(OperadorEventos.getEvento(((String) lvEventos.getSelectionModel().getSelectedItem())));
				} catch (Exception e) {}
			}
			
		});
		
		conteinerEvento.getChildren().addAll(selecioneEvento,lvEventos,selectionEvento);
		
		Label selecioneSala = new Label("Agora, selecione qual sala receberá o evento:");
		ListView lvSalas = new ListView();
		ObservableList<String> idsSalas = FXCollections.observableArrayList();
		boolean semSalas = true;
		try {
			semSalas = OperadorSalas.databaseVazia();
		} catch (Exception e1) {}
		try {
			ObservableList<Sala> salas = OperadorSalas.getSalas();
			for(Sala s: salas){
				idsSalas.add(s.getId());
			}
		} catch (Exception e) {
			semSalas = true;
		}
		
		lvSalas.setItems(idsSalas);
		SalaSelectionViewer selectionSala = new SalaSelectionViewer(250);
		
		lvSalas.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event arg0) {
				try {
					selectionSala.setSala(OperadorSalas.getSala((String) lvSalas.getSelectionModel().getSelectedItem()));
				} catch (Exception e) {}
			}
			
		});
		
		conteinerSala.getChildren().addAll(selecioneSala,lvSalas,selectionSala);
		
		Button btAlocar = new Button("Alocar evento");
		HBox conteinerBotao = new HBox();
		conteinerBotao.setPadding(new Insets(5,5,5,5));
		conteinerBotao.setAlignment(Pos.CENTER_RIGHT);
		conteinerBotao.getChildren().add(btAlocar);
		
		conteinerGeral.getChildren().addAll(conteinerEvento,conteinerSala);
		conteinerJanela.getChildren().addAll(espacadorJanela,conteinerGeral,conteinerBotao);
		noPrincipal.getChildren().add(conteinerJanela);
		
		Stage mainStage = new Stage();
		mainStage.setTitle("Alocar Evento");
		
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
		if(!semSalas && !semEventos){
			mainStage.show();
		}else{
			AlertaDeErro alerta = new AlertaDeErro("Alocar evento","Database vazia","Não há salas ou eventos cadastrados no sistema");
		}
		
		btAlocar.setOnAction(new EventHandler(){

			@Override
			public void handle(Event event) {
				try {
					Invoker.adicionarEExecutar(new ComandoAlocarEvento(lvSalas.getSelectionModel().getSelectedItem().toString(),lvEventos.getSelectionModel().getSelectedItem().toString()));
					mainStage.close();
					JanelaPrincipal.atualizar();
					AlertaDeInformacao alerta = new AlertaDeInformacao("Alocar Evento","Evento alocado","Evento alocado com sucesso");
				} catch (Exception e) {
					AlertaDeErro alerta = new AlertaDeErro("Alocar evento","Houve um erro:",e.getMessage());
				}
			}
			
		});
		
	}

}
