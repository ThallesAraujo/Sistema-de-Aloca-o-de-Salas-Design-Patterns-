package graphicalui.janelas;

import graphicalui.objetos.AlertaDeErro;
import graphicalui.objetos.AlertaDeInformacao;
import graphicalui.objetos.Espacador;
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
import objetos.Alocacao;
import objetos.Evento;
import objetos.Sala;
import objetos.comandos.ComandoDesalocarEvento;
import objetos.comandos.Invoker;

/**
 * Classe da janela de desalocação de eventos.
 * @author Thalles
 *
 */
public class DesalocarEvento {

	public DesalocarEvento(){

		Pane noPrincipal = new Pane();
		noPrincipal.setMinWidth(300);
		noPrincipal.setMinHeight(520);
		VBox conteiner = new VBox(5);
		conteiner.setAlignment(Pos.CENTER);

		Label label = new Label("Selecione qual evento deseja desalocar:");
		label.setPadding(new Insets(5,5,5,5));
		
		Espacador espacadorJanela = new Espacador(35,314);

		ListView lista = new ListView();
		lista.setPadding(new Insets(5,5,5,5));
		ObservableList<String> ids  = FXCollections.observableArrayList();
		boolean semAlocacoes  = true;
		try {
			semAlocacoes = OperadorAlocacoes.databaseVazia();
		} catch (Exception e1) {}
		try {
			ObservableList<Evento> eventos = OperadorEventos.getEventos();
			for(Evento e: eventos){
				if(OperadorAlocacoes.getAlocacao(e.getId())!=null){
					ids.add(e.getId());
				}
			}
		} catch (Exception e) {}

		if(!semAlocacoes){
			lista.setItems(ids);
		}

		Label labelSelect = new Label();

		lista.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				try {
					Alocacao alocacao = OperadorAlocacoes.getAlocacao((String) lista.getSelectionModel().getSelectedItem());
					labelSelect.setText("Alocado na sala "+alocacao.getIdSala());
				} catch (Exception e) {}
			}

		});

		Button btExcluir = new Button("Desalocar evento");
		HBox ctBotao = new HBox();
		ctBotao.setAlignment(Pos.CENTER_RIGHT);
		ctBotao.setPadding(new Insets(5,5,5,5));
		ctBotao.getChildren().add(btExcluir);

		conteiner.getChildren().addAll(espacadorJanela,label,lista,labelSelect,ctBotao);
		noPrincipal.getChildren().add(conteiner);

		Stage mainStage = new Stage();
		mainStage.setTitle("Desalocar evento");

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
		if(semAlocacoes==false){
			mainStage.show();
		}else{
			AlertaDeErro erro = new AlertaDeErro("Sem eventos","Sem eventos disponíveis","Não há alocações cadastradas na database");
		}

		btExcluir.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				try {
					Invoker.adicionarEExecutar(new ComandoDesalocarEvento((String) lista.getSelectionModel().getSelectedItem())); 
					mainStage.close();
					JanelaPrincipal.atualizar();
					AlertaDeInformacao alerta = new AlertaDeInformacao("Desalocar","Evento Desalocado","O evento foi desalocado com sucesso");
				} catch (Exception e) {
					AlertaDeErro erro = new AlertaDeErro("Seleção nula","Nenhum evento selecionado","Nenhum evento foi selecionado");
				}
			}

		});

	}


}

