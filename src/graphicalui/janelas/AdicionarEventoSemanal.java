package graphicalui.janelas;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import insidefx.undecorator.Undecorator;
import insidefx.undecorator.UndecoratorScene;
import graphicalui.objetos.AlertaDeErro;
import graphicalui.objetos.AlertaDeInformacao;
import graphicalui.objetos.Espacador;
import graphicalui.objetos.TimePicker;
import objetos.Evento;
import objetos.comandos.ComandoAdicionarEvento;
import objetos.comandos.Invoker;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logica.OperadorEventos;

/**
 * Classe da janela de cadastro de eventos.
 * @author Thalles
 *
 */
public class AdicionarEventoSemanal {
	
	public AdicionarEventoSemanal(){
		
		VBox raiz = new VBox();
		
		Pane noPrincipal = new Pane();
		noPrincipal.setMinWidth(465);
		noPrincipal.setMinHeight(350);
			
		Espacador espacadorJanela = new Espacador(35,479);
		
		Label rotuloCod = new Label("Código:   ");
		
		TextField codigo = new TextField();
		codigo.setMinWidth(140);
		codigo.setMaxWidth(140);
		
		
		HBox conteinerCod = new HBox(5);
		conteinerCod.setAlignment(Pos.CENTER_LEFT);
		conteinerCod.getChildren().addAll(rotuloCod,codigo);
		conteinerCod.setPadding(new Insets(5,5,5,5));
		
		Label rotuloNome = new Label("Nome:");
		
		TextField nome = new TextField();
		HBox.setHgrow(nome, Priority.ALWAYS);
		
		HBox conteinerNome = new HBox(5);
		conteinerNome.setAlignment(Pos.CENTER_LEFT);
		conteinerNome.getChildren().addAll(rotuloNome,nome);
		conteinerNome.setPadding(new Insets(5,5,5,5));
		
		Label rotuloDtInicio = new Label("Data e Hora de Início:    ");
		
		DatePicker dpDataInicio = new DatePicker();
		HBox.setHgrow(dpDataInicio, Priority.ALWAYS);
		TimePicker tpDataInicio = new TimePicker();
		
		HBox conteinerDTI = new HBox(5);
		conteinerDTI.setAlignment(Pos.CENTER_LEFT);
		conteinerDTI.getChildren().addAll(rotuloDtInicio,dpDataInicio,tpDataInicio);
		conteinerDTI.setPadding(new Insets(5,5,5,5));
		
		Label rotuloDtTermino = new Label("Data e Hora de Término:");
		
		DatePicker dpDataTermino = new DatePicker();
		HBox.setHgrow(dpDataTermino, Priority.ALWAYS);
		
		TimePicker tpDataTermino = new TimePicker();
		
		HBox conteinerDTT = new HBox(5);
		conteinerDTT.setAlignment(Pos.CENTER_LEFT);
		conteinerDTT.getChildren().addAll(rotuloDtTermino,dpDataTermino,tpDataTermino);
		conteinerDTT.setPadding(new Insets(5,5,5,5));
		
		Label rotuloResponsavel = new Label("Responsável:");
		
		TextField responsavel = new TextField();
		HBox.setHgrow(responsavel, Priority.ALWAYS);

		
		HBox conteinerResp = new HBox(5);
		conteinerResp.setAlignment(Pos.CENTER_LEFT);
		conteinerResp.getChildren().addAll(rotuloResponsavel,responsavel);
		conteinerResp.setPadding(new Insets(5,5,5,5));
		
		Label rotuloRepSemanais = new Label("Repetições Semanais (opc):");
		
		TextField repeticoes = new TextField();
		repeticoes.setMinWidth(60);
		repeticoes.setMaxWidth(60);
		
		HBox conteinerRep = new HBox(5);
		conteinerRep.setAlignment(Pos.CENTER_LEFT);
		conteinerRep.getChildren().addAll(rotuloRepSemanais,repeticoes);
		conteinerRep.setPadding(new Insets(5,5,5,5));
		
		Label rotuloArea = new Label("Área:");
		
		TextField area = new TextField();
		HBox.setHgrow(area, Priority.ALWAYS);
		
		HBox conteinerArea = new HBox(5);
		conteinerArea.setAlignment(Pos.CENTER_LEFT);
		conteinerArea.getChildren().addAll(rotuloArea,area);
		conteinerArea.setPadding(new Insets(5,5,5,5));
		
		Button botao = new Button("Salvar Evento");
		botao.setMnemonicParsing(false);
		
		HBox conteinerBotao = new HBox();
		conteinerBotao.setAlignment(Pos.CENTER_RIGHT);
		conteinerBotao.setPadding(new Insets(5,5,5,5));
		conteinerBotao.getChildren().add(botao);
		
		raiz.getChildren().addAll(espacadorJanela,conteinerCod,conteinerNome,conteinerDTI,conteinerDTT,conteinerResp,conteinerRep,conteinerArea,conteinerBotao);
		noPrincipal.getChildren().add(raiz);
		
		Stage mainStage = new Stage();
		mainStage.setTitle("Evento Semanal");
		
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
		
		botao.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				try {
					NumberFormat formater = new DecimalFormat("00");
					String dataInicio = formater.format(dpDataInicio.getValue().getDayOfMonth())+"/"+formater.format(dpDataInicio.getValue().getMonthValue())+"/"+dpDataInicio.getValue().getYear();
					String dataFim = formater.format(dpDataTermino.getValue().getDayOfMonth())+"/"+formater.format(dpDataTermino.getValue().getMonthValue())+"/"+dpDataTermino.getValue().getYear();
					if(codigo.getText().contains(" ")){
						AlertaDeErro alerta = new AlertaDeErro("Salvar evento","Identificação Inválida","O código do evento não deve conter espaços");
					}else if(Integer.parseInt(repeticoes.getText())<0){
						AlertaDeErro alerta = new AlertaDeErro("Salvar evento","Valor inválido","Repetições não podem ser menores que 0");
					}else{
						Invoker.adicionarEExecutar(new ComandoAdicionarEvento(new Evento(codigo.getText(),nome.getText(),dataInicio+" "+tpDataInicio.getTime(),dataFim+" "+tpDataTermino.getTime(),responsavel.getText(),area.getText(),Integer.parseInt(repeticoes.getText()),"semanal")));
						mainStage.close();
						JanelaPrincipal.atualizar();
						AlertaDeInformacao alerta = new AlertaDeInformacao("Salvar evento","Evento salvo","Evento salvo com sucesso");
					}
				} catch (NumberFormatException e) {
					AlertaDeErro alerta = new AlertaDeErro("Salvar evento","Formato incorreto","Repetições deve ser do tipo numérico");
				} catch (Exception e) {
					AlertaDeErro alerta = new AlertaDeErro("Salvar evento","Houve um erro:",e.getMessage());
				}
				
			}
			
		});
		
	}

}
