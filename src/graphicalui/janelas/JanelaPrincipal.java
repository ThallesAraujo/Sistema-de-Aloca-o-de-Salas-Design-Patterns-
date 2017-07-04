package graphicalui.janelas;
import graphicalui.objetos.AlertaDeErro;
import graphicalui.objetos.Espacador;
import graphicalui.objetos.Grupo;
import graphicalui.objetos.ImageViewCreator;
import graphicalui.objetos.LogExporter;
import graphicalui.objetos.TabelaFactory;
import insidefx.undecorator.Undecorator;
import insidefx.undecorator.UndecoratorScene;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logica.OperadorAlocacoes;
import logica.OperadorEventos;
import logica.OperadorSalas;
import objetos.Alocacao;
import objetos.Evento;
import objetos.Sala;
import objetos.comandos.Invoker;

/**
 * Classe da janela principal de toda a interface do programa
 * (Padrão Strategy: classe de contexto)
 * @author Thalles
 *
 */
public class JanelaPrincipal extends Application {
	
	private final double ESPACAMENTO = 5;
	public static TableView<Sala> listaSalas;
	public static TableView<Evento> listaEventos;
	public static TableView<Alocacao> listaAlocacoes;
	public static TabelaFactory fabrica;
	private AdicionarSala janelaAdicionarSala;

	@Override
	public void start(final Stage stage) throws Exception {
		fabrica = new TabelaFactory();
		
		VBox noPrincipal = new VBox();
		noPrincipal.setMinHeight(600);
		noPrincipal.setMinWidth(640);
		
		Espacador espacadorJanela = new Espacador(28);
		
		HBox conteinerListas = new HBox();
		conteinerListas.setSpacing(ESPACAMENTO);
		VBox.setVgrow(conteinerListas, Priority.ALWAYS);
		
		VBox conteinerLVEventos = new VBox();
		conteinerLVEventos.setPrefHeight(272);
		conteinerLVEventos.setPrefWidth(395);
		conteinerLVEventos.setSpacing(ESPACAMENTO);
		HBox.setHgrow(conteinerLVEventos, Priority.ALWAYS);
		
		Label lbEventos = new Label("Todos os Eventos");
		lbEventos.setAlignment(Pos.BOTTOM_LEFT);
		lbEventos.setFont(new Font("System Bold",14));
		
		listaEventos = fabrica.criarTabelaEventos();
		
		Label lbAlocacoes = new Label("Alocações:");
		lbAlocacoes.setAlignment(Pos.BOTTOM_LEFT);
		lbAlocacoes.setFont(new Font("System Bold",14));
		
		listaAlocacoes = fabrica.criarTabelaAlocacoes();
		
		VBox conteinerLVSalas = new VBox();
		conteinerLVSalas.setSpacing(ESPACAMENTO);
		HBox.setHgrow(conteinerLVSalas, Priority.ALWAYS);
		
		Label lbSalas = new Label("Todas as Salas:");
		lbSalas.setAlignment(Pos.BOTTOM_LEFT);
		lbSalas.prefHeight(40);
		lbSalas.prefWidth(142);
		lbSalas.setFont(new Font("System Bold",14));
		
		listaSalas = fabrica.criarTabelaSalas();
		VBox.setVgrow(listaSalas, Priority.ALWAYS);
		
		
		//#Guias:############################################################################################
		
		StackPane stackPane = new StackPane();
		stackPane.setPickOnBounds(false);
	
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabPane.setStyle("/graphicalui/design/TabPaneStyle.css");
		
		Tab sala = new Tab  ("      Sala      ");
		Tab evento = new Tab("     Evento     ");
		Tab extras = new Tab("     Extras     ");
		
		ToolBar tbSala = new ToolBar();
		ToolBar tbEvento = new ToolBar();
		ToolBar tbExtras = new ToolBar();
		tbEvento.setMinHeight(96);
		tbExtras.setMinHeight(96);

		HBox conteinerGA = new HBox();
		conteinerGA.setAlignment(Pos.BOTTOM_CENTER);
		conteinerGA.setSpacing(ESPACAMENTO);
		
		MenuButton adicionarSala = new MenuButton("Sala de Aula");
		adicionarSala.setAlignment(Pos.BOTTOM_CENTER);
		adicionarSala.setContentDisplay(ContentDisplay.TOP);
		adicionarSala.setMnemonicParsing(false);
		
		MenuItem aulaConv = new MenuItem("Aula Convencional");
		aulaConv.setMnemonicParsing(false);
		
		aulaConv.setOnAction(new EventHandler(){

			@Override
			public void handle(Event evento) {
				janelaAdicionarSala = new AdicionarSalaDeAula();
			}
		});
		
		MenuItem aulaInt = new MenuItem("Aula Inteligente");
		aulaInt.setMnemonicParsing(false);
		
		aulaInt.setOnAction(new EventHandler() {

			@Override
			public void handle(Event arg0) {
				janelaAdicionarSala = new AdicionarSalaInteligente();
				
			}
			
		});
		
		adicionarSala.getItems().addAll(aulaConv,aulaInt);
		adicionarSala.setGraphic(ImageViewCreator.create("/graphicalui/imagens/adicionar.png"));
		
		VBox conteinerGA2 = new VBox();
		conteinerGA2.setAlignment(Pos.BOTTOM_LEFT);
		conteinerGA2.setSpacing(ESPACAMENTO);
		
		MenuButton salaConf = new MenuButton("Conferência");
		salaConf.setMnemonicParsing(false);
		salaConf.setGraphic(ImageViewCreator.create("/graphicalui/imagens/conferencia.png"));
		
		MenuItem CC = new MenuItem("Sala de Conferência Convencional");
		CC.setMnemonicParsing(false);
		
		CC.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				janelaAdicionarSala = new AdicionarSalaConferencia();	
			}
			
		});
		
		MenuItem CV = new MenuItem("Sala de Videoconferência");
		CV.setMnemonicParsing(false);
		
		CV.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				janelaAdicionarSala = new AdicionarSalaVideoconferencia();
			}
			
		});
		
		salaConf.getItems().addAll(CC,CV);
		
		MenuButton btLaboratorio = new MenuButton("Outros tipos");
		btLaboratorio.setAlignment(Pos.CENTER_LEFT);
		btLaboratorio.setMnemonicParsing(false);
		btLaboratorio.setGraphic(ImageViewCreator.create("/graphicalui/imagens/lab.png"));
		
		MenuItem miLaboratorio = new MenuItem("Laboratório");
		MenuItem miEscritorio = new MenuItem("Escritório");
		
		miLaboratorio.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				AdicionarLaboratorio janela = new AdicionarLaboratorio();
			}
			
		});
		
		miEscritorio.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				janelaAdicionarSala = new AdicionarEscritorio();
			}
			
		});
		
		btLaboratorio.getItems().addAll(miLaboratorio,miEscritorio);
		
		
		Grupo grupoAdicionar = new Grupo(conteinerGA,"Adicionar");
		
		Separator sp1 = new Separator();
		sp1.setOrientation(Orientation.VERTICAL);
		
		Button btRemoverSala = new Button("Remover uma sala");
		btRemoverSala.setAlignment(Pos.BASELINE_LEFT);
		btRemoverSala.setMnemonicParsing(false);
		btRemoverSala.prefHeight(28);
		btRemoverSala.setPrefWidth(169);
		
		btRemoverSala.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				ExcluirSala excluir = new ExcluirSala();
			}
			
		});
		
		btRemoverSala.setGraphic(ImageViewCreator.create("/graphicalui/imagens/remover.PNG"));
		
		Button btApagarDatabaseS = new Button("Apagar todas as salas");
		btApagarDatabaseS.setAlignment(Pos.BASELINE_LEFT);
		btApagarDatabaseS.setMnemonicParsing(false);
		btApagarDatabaseS.prefHeight(26);
		btApagarDatabaseS.setPrefWidth(169);
		btApagarDatabaseS.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				ApagarDatabaseSalas apagarDados = new ApagarDatabaseSalas();
			}
		});
		
		btApagarDatabaseS.setGraphic(ImageViewCreator.create("/graphicalui/imagens/limpar_database.PNG"));
		
		Grupo databaseSalas = new Grupo("Database",btRemoverSala,btApagarDatabaseS);
		
		Separator sp2 = new Separator();
		sp2.setOrientation(Orientation.VERTICAL);
		
		Button btPesquisaS = new Button("Pesquisar por uma sala");
		btPesquisaS.setMnemonicParsing(false);
		btPesquisaS.setAlignment(Pos.BOTTOM_CENTER);
		btPesquisaS.setContentDisplay(ContentDisplay.TOP);
		
		btPesquisaS.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				PesquisarSala pesquisa = new PesquisarSala();
			}
			
		});
		
		btPesquisaS.setGraphic(ImageViewCreator.create("/graphicalui/imagens/pesquisa.png"));
		
		Grupo pesquisaSalas = new Grupo("Pesquisa",btPesquisaS);
		
		//Guia Evento
		
		HBox conteinerGD = new HBox();
		conteinerGD.setAlignment(Pos.BOTTOM_CENTER);
		conteinerGD.setSpacing(ESPACAMENTO);
		
		MenuButton btNovoEvento = new MenuButton("Novo Evento");
		btNovoEvento.setAlignment(Pos.BOTTOM_CENTER);
		btNovoEvento.setContentDisplay(ContentDisplay.TOP);
		btNovoEvento.setMnemonicParsing(false);
		
		MenuItem mi_evSemanal = new MenuItem("Evento Semanal");
		MenuItem mi_evMensal = new MenuItem("Evento Mensal");
		MenuItem mi_evDiario = new MenuItem("Evento Diário");
		
		
		mi_evSemanal.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				AdicionarEventoSemanal janela = new AdicionarEventoSemanal();
			}
			
		});
		
		mi_evMensal.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				AdicionarEventoMensal janela = new AdicionarEventoMensal();
			}
			
		});
		
		mi_evDiario.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				AdicionarEventoDiario janela = new AdicionarEventoDiario();
			}
			
		});
		
		btNovoEvento.getItems().addAll(mi_evSemanal,mi_evMensal,mi_evDiario);
		
		btNovoEvento.setGraphic(ImageViewCreator.create("/graphicalui/imagens/evento.png"));
		
		VBox conteinerGD2 = new VBox();
		conteinerGD2.setAlignment(Pos.BOTTOM_CENTER);
		conteinerGD2.setSpacing(ESPACAMENTO);
		
		Button RemoverEvento = new Button("Remover um evento");
		RemoverEvento.setAlignment(Pos.BASELINE_LEFT);
		RemoverEvento.setMnemonicParsing(false);
		RemoverEvento.prefHeight(28);
		RemoverEvento.setPrefWidth(169);
		
		RemoverEvento.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				ExcluirEvento excluirEvento = new ExcluirEvento();
			}
			
		});
			
		RemoverEvento.setGraphic(ImageViewCreator.create("/graphicalui/imagens/remover.PNG"));
		
		Button btApagarDatabaseE = new Button("Apagar todos os eventos");
		btApagarDatabaseE.setMnemonicParsing(false);
		
		btApagarDatabaseE.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				ApagarDatabaseEventos apagarDatabase = new ApagarDatabaseEventos();
			}
			
		});
		
		btApagarDatabaseE.setGraphic(ImageViewCreator.create("/graphicalui/imagens/limpar_database.PNG"));
		
		conteinerGD2.getChildren().addAll(RemoverEvento,btApagarDatabaseE);
		conteinerGD.getChildren().addAll(btNovoEvento,conteinerGD2);
		
		Grupo databaseEventos = new Grupo(conteinerGD,"Database");
		
		//
		Separator sp3 = new Separator();
		sp3.setOrientation(Orientation.VERTICAL);
		
		Button btAlocarEvento = new Button("Alocar evento");
		btAlocarEvento.setAlignment(Pos.BASELINE_LEFT);
		btAlocarEvento.setMnemonicParsing(false);
		btAlocarEvento.prefHeight(30);
		btAlocarEvento.setPrefWidth(144);
		btAlocarEvento.setGraphic(ImageViewCreator.create("/graphicalui/imagens/alocar.png"));
		
		btAlocarEvento.setOnAction(new EventHandler(){
			@Override
			public void handle(Event arg0) {
				AlocarEvento janelaAlocar = new AlocarEvento();
				
			}
			
		});
		
		
		Button btDesalocarEvento = new Button("Desalocar eventos");
		btDesalocarEvento.setAlignment(Pos.BASELINE_LEFT);
		btDesalocarEvento.setMnemonicParsing(false);
		btDesalocarEvento.prefHeight(26);
		btDesalocarEvento.setPrefWidth(144);
		
		btDesalocarEvento.setGraphic(ImageViewCreator.create("/graphicalui/imagens/desalocar.png"));
		
		btDesalocarEvento.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				DesalocarEvento desalocar = new DesalocarEvento();
			}
			
		});
		
		Grupo alocacao = new Grupo("Alocação",btAlocarEvento,btDesalocarEvento);
		
		Separator sp4 = new Separator();
		sp4.setOrientation(Orientation.VERTICAL);
		
		Label lbPesquisaE = new Label("Pesquisa");
		
		Button btPesquisaE = new Button("Pesquisar evento alocado");
		btPesquisaE.setMnemonicParsing(false);
		btPesquisaE.setAlignment(Pos.BOTTOM_CENTER);
		btPesquisaE.setContentDisplay(ContentDisplay.TOP);
		btPesquisaE.setGraphic(ImageViewCreator.create("/graphicalui/imagens/pesquisa.png"));
		
		btPesquisaE.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				PesquisarEventos pesquisa = new PesquisarEventos();
			}
			
		});
		
		Grupo pesquisaEventos = new Grupo("Pesquisa",btPesquisaE);
		
		//Guia Extras############################################################################
		
		Button btDesfazer = new Button("Desfazer últ. ação");
		btDesfazer.setMnemonicParsing(false);
		btDesfazer.setAlignment(Pos.BOTTOM_CENTER);
		btDesfazer.setContentDisplay(ContentDisplay.TOP);
		btDesfazer.setGraphic(ImageViewCreator.create("/graphicalui/imagens/undo.png"));
		
		btDesfazer.setOnAction(new EventHandler(){
			@Override
			public void handle(Event arg0) {
				if(Invoker.getQtdComandos()>0){
					
					try {
						Invoker.desfazer();
					} catch (Exception e) {
						AlertaDeErro alerta = new AlertaDeErro("Desfazer ação","Houve um erro: ",e.getMessage());
						e.printStackTrace();
					}
				}else{
					AlertaDeErro alerta = new AlertaDeErro("Desfazer ação","Sem comandos","Não há comandos a serem desfeitos");
				}
			}
			
		});
		
		Separator separatorExtras = new Separator();
		sp4.setOrientation(Orientation.VERTICAL);
		
		HBox conteinerGrupoLog = new HBox();
		VBox csGrupoLog = new VBox(5);
		
		Button btVerLogs = new Button("Visualizar log");
		Button btExportarLog = new Button("Exp. logs para planilha");
		Button btApagarLogs = new Button("Apagar log de eventos");
		
		btVerLogs.setMnemonicParsing(false);
		btVerLogs.setAlignment(Pos.BOTTOM_CENTER);
		btVerLogs.setContentDisplay(ContentDisplay.TOP);
		btVerLogs.setGraphic(ImageViewCreator.create("/graphicalui/imagens/log.png"));
		
		btExportarLog.setAlignment(Pos.BASELINE_LEFT);
		btExportarLog.setMnemonicParsing(false);
		btExportarLog.setGraphic(ImageViewCreator.create("/graphicalui/imagens/exportar.png"));
		
		btApagarLogs.setAlignment(Pos.BASELINE_LEFT);
		btApagarLogs.setMnemonicParsing(false);
		btApagarLogs.setGraphic(ImageViewCreator.create("/graphicalui/imagens/limpar_database.PNG"));
		
		btVerLogs.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				VisualizarLog listaLogs = new VisualizarLog();
			}
			
		});
		
		btExportarLog.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
			LogExporter exporter = new LogExporter();	
			}
			
		});
		
		btApagarLogs.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
			ApagarDatabaseLogs apagarLogs = new ApagarDatabaseLogs();	
			}
		});
		
		csGrupoLog.getChildren().addAll(btExportarLog,btApagarLogs);
		conteinerGrupoLog.getChildren().addAll(btVerLogs,csGrupoLog);
		Grupo grupoLog = new Grupo(conteinerGrupoLog,"Log");		
		Grupo grupoAcoes = new Grupo("Ações",btDesfazer);
		
		//Organização Hierárquica
		
		conteinerLVEventos.getChildren().addAll(lbEventos,listaEventos,lbAlocacoes,listaAlocacoes);
		conteinerLVSalas.getChildren().addAll(lbSalas,listaSalas);
		conteinerListas.getChildren().addAll(conteinerLVEventos,conteinerLVSalas);
		
		conteinerGA2.getChildren().addAll(salaConf,btLaboratorio);
		conteinerGA.getChildren().addAll(adicionarSala,conteinerGA2);
		
		tbSala.getItems().addAll(grupoAdicionar,sp1,databaseSalas,sp2,pesquisaSalas);
		sala.setContent(tbSala);
		
		tbEvento.getItems().addAll(databaseEventos,sp3,alocacao,sp4,pesquisaEventos);
		evento.setContent(tbEvento);
		
		tbExtras.getItems().addAll(grupoAcoes,separatorExtras,grupoLog);
		extras.setContent(tbExtras);
		
		tabPane.getTabs().addAll(sala,evento,extras);
		
		stackPane.getChildren().add(tabPane);
		
		noPrincipal.getChildren().addAll(espacadorJanela,stackPane,conteinerListas);
		
		final Stage mainStage = stage;
		Label label = new Label("Sistema de Alocação de Salas");
		mainStage.setTitle(label.getText());
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("stagedecoration.fxml"));
		loader.setController(this);
		Region root = (Region) noPrincipal;
		
		final UndecoratorScene cena = new UndecoratorScene(mainStage,root);
		cena.addStylesheet("/graphicalui/design/TabPaneStyle.css");
		cena.setAsStageDraggable(mainStage, noPrincipal);
		
		mainStage.setScene(cena);
		mainStage.sizeToScene();
		mainStage.toFront();
		
		Undecorator undecorator = cena.getUndecorator();
		mainStage.setMinWidth(undecorator.getMinWidth());
		mainStage.setMinHeight(undecorator.getMinHeight());
		mainStage.getIcons().add(ImageViewCreator.create("/graphicalui/imagens/icon.png").getImage());
		mainStage.show();
		
	}
	
	
	/**
	 * Método de atualização das TableViews (tabelas) exibidas na interface principal.
	 * @throws Exception Por lidar com a database, uma exceção pode ser lançada caso a mesma não seja encontrada.
	 */
	public static void atualizar() throws Exception{
		ObservableList<Sala> salas = OperadorSalas.getSalas();
		ObservableList<Sala> salasEdit = FXCollections.observableArrayList();
		for(Sala s: salas){
			if(s.getApelido().equals(null) || s.getApelido().equalsIgnoreCase("")){
				salasEdit.add(new Sala(s.getId(),"<<Sem Apelido>>",s.getCapacidade(),s.getFinalidade(),s.getTipo(),s.getIsAberto()));
			}else{
				salasEdit.add(s);
			}
		}
		listaSalas.setItems(null);
		listaSalas.setItems(salasEdit);
		listaAlocacoes.setItems(null);
		ObservableList<Alocacao> alocacoesDuplicadas = OperadorAlocacoes.getAlocacoes();
		for(int i = 0;i<alocacoesDuplicadas.size();i++){
			try{
				if(alocacoesDuplicadas.get(i).equals(alocacoesDuplicadas.get(i+1))){
					alocacoesDuplicadas.remove(i+1);
				}
			}catch(IndexOutOfBoundsException e){}
		}
		listaAlocacoes.setItems(alocacoesDuplicadas);
		listaEventos.setItems(null);
		listaEventos.setItems(OperadorEventos.getEventos());

	}
	
	
	public static void main(String[] args) {
		try{
			database.Inicializar.inicializacao();
		}catch(Exception e){}
		launch();
	}

}
