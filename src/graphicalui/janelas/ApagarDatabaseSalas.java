package graphicalui.janelas;

import objetos.Log;
import graphicalui.objetos.AlertaDeErro;
import graphicalui.objetos.AlertaDeInformacao;
import graphicalui.objetos.Espacador;
import insidefx.undecorator.Undecorator;
import insidefx.undecorator.UndecoratorScene;
import javafx.event.Event;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logica.Logger;
import logica.OperadorEventos;
import logica.OperadorSalas;

/**
 * Classe da janela de redefinição (exclusão e recriação da tabela) das salas cadastradas na database.
 * @author Thalles
 *
 */
public class ApagarDatabaseSalas {
	
	public ApagarDatabaseSalas(){
	
		Pane noPrincipal = new Pane();
		noPrincipal.setMinWidth(360);
		noPrincipal.setMinHeight(160);
		VBox conteiner = new VBox(5);
		conteiner.setAlignment(Pos.CENTER_LEFT);
		
		Espacador espacadorJanela = new Espacador(35,374);

		Label aviso = new Label("Essa ação apagará todas as salas registradas no banco de dados.");
		Label contAviso  = new Label("Antes de prosseguir, digite a senha no campo abaixo:");
		aviso.setAlignment(Pos.CENTER_LEFT);
		aviso.setWrapText(true);
		aviso.setPadding(new Insets(5,5,5,5));
		contAviso.setPadding(new Insets(5,5,5,5));
		
		HBox conteiner2 = new HBox();
		conteiner2.setAlignment(Pos.CENTER);
		conteiner2.prefHeight(100);
		conteiner2.prefWidth(200);
		conteiner2.setSpacing(5);
		conteiner.setMargin(conteiner2, new Insets(10));
		
		TextField senha = new TextField();
		senha.prefHeight(25);
		senha.prefWidth(260);
		HBox.setHgrow(senha, Priority.ALWAYS);
		
		Button apagarTudo = new Button("Apagar tudo");
		apagarTudo.setMnemonicParsing(false);
		
		conteiner2.getChildren().addAll(senha,apagarTudo);
		conteiner.getChildren().addAll(espacadorJanela,aviso,contAviso,conteiner2);
		noPrincipal.getChildren().add(conteiner);
		
		Stage mainStage = new Stage();
		mainStage.setTitle("Apagar database");
		
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
		} catch (Exception e2) {}
		
		try {
			if(!semSalas){
				mainStage.show();
			}else{
				AlertaDeErro alerta = new AlertaDeErro("Apagar database","Database vazia","Não há salas cadastradas");
			}
		} catch (Exception e1) {}
		
		apagarTudo.setOnAction(new EventHandler(){

			@Override
			public void handle(Event event) {
				if(senha.getText().equals("1234")){
					try {
						database.Inicializar.apagarDatabaseSalas();
						mainStage.close();
						JanelaPrincipal.atualizar();
						AlertaDeInformacao alerta = new AlertaDeInformacao("Apagar database","Database apagada","As salas cadastradas no sistema foram apagadas com sucesso");
						Logger.adicionarLog(new Log("REDEFINIÇÃO DE DATABASE: SALAS"));
					} catch (Exception e) {
						mainStage.close();
						if(!e.getMessage().contains("SALAS")){
							AlertaDeErro alert = new AlertaDeErro("Apagar database","Houve um erro:",e.getMessage());
							Logger.adicionarLog(new Log("ERRO: "+e.getMessage()));
						}else{
							AlertaDeInformacao alerta = new AlertaDeInformacao("Apagar database","Database apagada","As salas cadastradas no sistema foram apagadas com sucesso");
							Logger.adicionarLog(new Log("REDEFINIÇÃO DE DATABASE: SALAS"));
						}
					}
				}else{
					AlertaDeErro alerta = new AlertaDeErro("Apagar database","Senha incorreta","A senha digitada está incorreta. Tente novamente.");
					Logger.adicionarLog(new Log("ERRO DE SEGURANÇA - REDEFINIR DATABASE SALAS: Senha incorreta"));
				}
				
			}
			
		});
		
	}

}
