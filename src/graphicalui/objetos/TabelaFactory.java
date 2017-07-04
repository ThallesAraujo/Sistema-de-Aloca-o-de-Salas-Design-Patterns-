package graphicalui.objetos;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logica.OperadorAlocacoes;
import logica.OperadorEventos;
import logica.OperadorSalas;
import objetos.Alocacao;
import objetos.Evento;
import objetos.Sala;

/**
 * Classe de criação das TableViews (tabelas) exibidas na interface principal do programa.
 * @author Thalles
 *
 */
public class TabelaFactory {
	
	private String style = "/graphicalui/design/TabPaneStyle.css";
	
	/**
	 * Método de criação da tabela de alocações exibida na janela principal.
	 * @return TableView contendo todas as alocações cadastradas na database.
	 */
	public TableView<Alocacao> criarTabelaAlocacoes(){
		
		TableView<Alocacao> alocacoes = new TableView<Alocacao>();
		TableColumn<Alocacao,String> clIdSala = new TableColumn<Alocacao,String>("Código da Sala");
		clIdSala.setCellValueFactory(new PropertyValueFactory<Alocacao,String>("idSala"));
		TableColumn<Alocacao,String> clIdEvento = new TableColumn<Alocacao,String>("Código do Evento");
		clIdEvento.setCellValueFactory(new PropertyValueFactory<Alocacao,String>("idEvento"));
		alocacoes.getColumns().addAll(clIdSala,clIdEvento);
		alocacoes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		try{
			ObservableList<Alocacao> alocacoesDuplicadas = OperadorAlocacoes.getAlocacoes();
			for(int i = 0; i<alocacoesDuplicadas.size();i++){
				if(alocacoesDuplicadas.get(i).equals(alocacoesDuplicadas.get(i+1))){
					alocacoesDuplicadas.remove(i+1);
				}
			}
			alocacoes.setItems(alocacoesDuplicadas);
		}catch(Exception exc){
			alocacoes.setItems(null);
		}
		return alocacoes;
		
		
	}

	/**
	 * Método de criação da tabela de salas exibida na janela principal.
	 * @return TableView contendo todas as salas cadastradas na database.
	 */
	public TableView<Sala> criarTabelaSalas(){
		
		TableView<Sala> listaSalas = new TableView<Sala>();
		TableColumn<Sala,String> clCodigo = new TableColumn<Sala,String>("Código");
		clCodigo.setCellValueFactory(new PropertyValueFactory<Sala,String>("id"));
		TableColumn <Sala,String>clApelido = new TableColumn<Sala,String>("Apelido");
		clApelido.setCellValueFactory(new PropertyValueFactory<Sala,String>("apelido"));
		TableColumn <Sala,String>clFinalidade = new TableColumn<Sala,String>("Finalidade");
		clFinalidade.setCellValueFactory(new PropertyValueFactory<Sala,String>("finalidade"));
		TableColumn <Sala,String>clTipo = new TableColumn<Sala,String>("Tipo");
		clTipo.setCellValueFactory(new PropertyValueFactory<Sala,String>("tipo"));
		TableColumn <Sala,Integer>clCapacidade = new TableColumn<Sala,Integer>("Capacidade");
		clCapacidade.setCellValueFactory(new PropertyValueFactory<Sala,Integer>("capacidade"));
		listaSalas.getColumns().addAll(clCodigo,clApelido,clFinalidade,clTipo,clCapacidade);
		ObservableList<Sala> salas;
		try {
			salas = OperadorSalas.getSalas();
			ObservableList<Sala> salasEdit = FXCollections.observableArrayList();
			for(Sala s: salas){
				if(s.getApelido().equals(null) || s.getApelido().equalsIgnoreCase("")){
					salasEdit.add(new Sala(s.getId(),"<<Sem Apelido>>",s.getCapacidade(),s.getFinalidade(),s.getTipo(),s.getIsAberto()));
				}else{
					salasEdit.add(s);
				}
			}
			listaSalas.setItems(salasEdit);
		} catch (Exception e) {
			listaSalas.setItems(null);
		}
		listaSalas.setStyle(style);
		return listaSalas;
		
	}
	
	/**
	 * Método de criação da tabela de eventos exibida na janela principal.
	 * @return TableView contendo todos os eventos cadastrados na database.
	 */
	public TableView<Evento> criarTabelaEventos(){
		
		TableView<Evento> listaEventos = new TableView<Evento>();
		TableColumn<Evento,String> clCodigo = new TableColumn<Evento,String>("Código");
		clCodigo.setCellValueFactory(new PropertyValueFactory<Evento,String>("id"));
		TableColumn <Evento,String>clNome = new TableColumn<Evento,String>("Nome");
		clNome.setCellValueFactory(new PropertyValueFactory<Evento,String>("nome"));
		TableColumn <Evento,String>clInicio = new TableColumn<Evento,String>("Data de Início");
		clInicio.setCellValueFactory(new PropertyValueFactory<Evento,String>("inicio"));
		TableColumn <Evento,String>clFim = new TableColumn<Evento,String>("Data de Término");
		clFim.setCellValueFactory(new PropertyValueFactory<Evento,String>("fim"));
		TableColumn <Evento,String>clResponsavel = new TableColumn<Evento,String>("Responsável");
		clResponsavel.setCellValueFactory(new PropertyValueFactory<Evento,String>("contato"));
		TableColumn <Evento,String>clArea = new TableColumn<Evento,String>("Área");
		clArea.setCellValueFactory(new PropertyValueFactory<Evento,String>("area"));
		TableColumn <Evento,Integer>clRepeticoes = new TableColumn<Evento,Integer>("Rep. Semanais");
		clRepeticoes.setCellValueFactory(new PropertyValueFactory<Evento,Integer>("repeticoes"));
		TableColumn <Evento,String>clTipo = new TableColumn<Evento,String>("Tipo");
		clTipo.setCellValueFactory(new PropertyValueFactory<Evento,String>("tipo"));
		listaEventos.getColumns().addAll(clCodigo,clNome,clInicio,clFim,clResponsavel,clArea,clRepeticoes,clTipo);
		try {
			listaEventos.setItems(OperadorEventos.getEventos());
		} catch (Exception e) {
			listaEventos.setItems(null);
		}
		listaEventos.setStyle(style);
		return listaEventos;
	}
}
