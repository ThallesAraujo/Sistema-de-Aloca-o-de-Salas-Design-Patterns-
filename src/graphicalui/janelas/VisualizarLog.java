package graphicalui.janelas;

import insidefx.undecorator.Undecorator;
import insidefx.undecorator.UndecoratorScene;
import graphicalui.objetos.AlertaDeErro;
import graphicalui.objetos.Espacador;
import objetos.Log;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logica.Logger;

public class VisualizarLog {
	
	public VisualizarLog(){
		
		Pane noPrincipal = new Pane();
		noPrincipal.setMinWidth(500);
		noPrincipal.setMinHeight(430);
		
		Espacador espacadorJanela = new Espacador(35,514);
		
		VBox conteinerJanela = new VBox();
		HBox conteinerGeral = new HBox(5);
		conteinerGeral.setPadding(new Insets(5,5,5,5));
		
		VBox conteinerEvento = new VBox(5);
		VBox conteinerSala = new VBox(5);
		
		TableView<Log> tbvLogs = new TableView<Log>();
		TableColumn<Log,String> tbcHorario = new TableColumn<Log,String>("Horário");
		tbcHorario.setCellValueFactory(new PropertyValueFactory<Log,String>("horario"));
		
		TableColumn <Log,String>tbcEvento = new TableColumn<Log,String>("Evento");
		tbcEvento.setCellValueFactory(new PropertyValueFactory<Log,String>("evento"));
		
		tbvLogs.getColumns().addAll(tbcHorario,tbcEvento);
		
		try {
			tbvLogs.setItems(Logger.getLogs());
		} catch (Exception e) {}
		
		Stage mainStage = new Stage();
		mainStage.setTitle("Log de eventos");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("stagedecoration.fxml"));
		loader.setController(this);
		Region root = (Region) noPrincipal;
		
		final UndecoratorScene cena = new UndecoratorScene(mainStage,root);
		cena.setAsStageDraggable(mainStage, noPrincipal);
		
		conteinerGeral.getChildren().add(tbvLogs);
		conteinerGeral.setHgrow(tbvLogs, Priority.ALWAYS);
		conteinerJanela.setVgrow(conteinerGeral,Priority.ALWAYS);
		conteinerJanela.getChildren().addAll(espacadorJanela,conteinerGeral);
		noPrincipal.getChildren().add(conteinerJanela);
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
		tbvLogs.setStyle("/graphicalui/design/TabPaneStyle.css");
		cena.getStylesheets().add("/graphicalui/design/TabPaneStyle.css");
		if(!Logger.databaseVazia()){
			mainStage.show();
		}else{
			AlertaDeErro alerta = new AlertaDeErro("Visualizar Log","Sem logs","Não há eventos no log de eventos");
		}
		
		
	}

}
