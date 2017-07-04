package graphicalui.janelas;

import objetos.Log;
import graphicalui.objetos.AlertaDeErro;
import graphicalui.objetos.AlertaDeInformacao;
import graphicalui.objetos.Espacador;
import graphicalui.objetos.ImageViewCreator;
import insidefx.undecorator.Undecorator;
import insidefx.undecorator.UndecoratorScene;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logica.Logger;
import logica.OperadorSalas;

/**
 * Classe da janela de pesquisa de salas.
 * @author Thalles
 *
 */
public class PesquisarSala {
	
	public PesquisarSala(){
		
		Pane noPrincipal = new Pane();
		noPrincipal.setMinWidth(360);
		noPrincipal.setMinHeight(160);
		VBox conteiner = new VBox(5);
		conteiner.setAlignment(Pos.CENTER_LEFT);
		
		Espacador espacadorJanela = new Espacador(35,374);
		
		Label rotulo = new Label("Digite o código da sala e o nome do atributo que deseja saber:");
		rotulo.prefHeight(39);
		rotulo.prefWidth(357);
		rotulo.setWrapText(true);
		conteiner.setMargin(rotulo, new Insets(5));
		
		HBox conteiner1 = new HBox(5);
		conteiner1.setAlignment(Pos.CENTER);
		conteiner.setMargin(conteiner1, new Insets(5));
		
		Label lbid = new Label("Código da Sala:");
		
		TextField idSala = new TextField();
		idSala.prefHeight(29);
		idSala.prefWidth(246);
		HBox.setHgrow(idSala, Priority.ALWAYS);
		
		HBox conteiner2 = new HBox(5);
		conteiner2.setAlignment(Pos.CENTER);
		conteiner2.prefHeight(56);
		conteiner2.prefWidth(357);
		conteiner.setMargin(conteiner2, new Insets(5));
		
		TextField tfAtributo = new TextField();
		tfAtributo.prefHeight(29);
		tfAtributo.prefWidth(164);
		HBox.setHgrow(tfAtributo, Priority.ALWAYS);
		
		Label labat = new Label("Nome do Atributo:");
		
		Button search = new Button();
		search.setMnemonicParsing(false);
		search.prefHeight(25);
		search.prefWidth(32);
		
		search.setGraphic(ImageViewCreator.create("/graphicalui/imagens/pesquisa_small.png"));
		
		conteiner2.getChildren().addAll(labat,tfAtributo,search);
		conteiner1.getChildren().addAll(lbid,idSala);
		conteiner.getChildren().addAll(espacadorJanela,rotulo,conteiner1,conteiner2);
		noPrincipal.getChildren().add(conteiner);
		
		Stage mainStage = new Stage();
		mainStage.setTitle("Pesquisar salas");
		
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
		
		boolean semSalas = true;
		try {
			semSalas = OperadorSalas.databaseVazia();
		} catch (Exception e1) {}
		
		if(!semSalas){
			mainStage.show();
		}else{
			AlertaDeErro alerta = new AlertaDeErro("Pesquisar salas","Database vazia","Não há salas cadastradas");
		}
			
		
		search.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				String atributo;
				try {
					atributo = OperadorSalas.getAtributoSala(idSala.getText(), tfAtributo.getText());
					mainStage.close();
					AlertaDeInformacao alerta = new AlertaDeInformacao("Atributo encontrado:","O atributo "+ tfAtributo.getText()+ " da sala "+ idSala.getText()+" é:",atributo);
					Logger.adicionarLog(new Log("Pesquisa de Sala: Atributo: "+tfAtributo.getText()+" Sala: "+idSala.getText()));
				} catch (Exception e) {
					AlertaDeErro erro = new AlertaDeErro("Pesquisar sala","Houve um erro:",e.getMessage());
					Logger.adicionarLog(new Log("ERRO: "+e.getMessage()));
				}
				
			}
			
		});
		
		
	}

}
