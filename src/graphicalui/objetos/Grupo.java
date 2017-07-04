package graphicalui.objetos;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Classe do objeto de grupo Ribbon das guias.
 * @author Thalles
 *
 */
public class Grupo extends VBox{
	
	private HBox conteudo;
	private Label nome;
	
	/**
	 * Primeiro construtor da classe de grupo.
	 * @param conteudo Conteiner de caixa horizontal com os elementos do grupo.
	 * @param nome Nome do grupo.
	 */
	public Grupo(HBox conteudo, String nome){
		this.conteudo = conteudo;
		this.nome = new Label(nome);
		definirDesign();
		this.getChildren().addAll(conteudo,this.nome);
	}
	
	
	
	/**
	 * Segundo construtor da classe de grupo.
	 * @param nome Nome do grupo.
	 * @param itens Elementos do grupo. Podem ou não estarem dentro de conteiners.
	 */
	public Grupo(String nome, Object...itens){
		for(Object o: itens){
			this.getChildren().add((Node) o);
		}
		definirDesign();
		this.nome = new Label(nome);
		this.getChildren().add(this.nome);
	}
	
	
	/**
	 * Método de definição de design do grupo.
	 */
	private void definirDesign(){
		this.setAlignment(Pos.BOTTOM_CENTER);
		if(this.conteudo!=null){
			this.conteudo.setAlignment(Pos.BOTTOM_CENTER);
		}
		this.setSpacing(5);
	}
}
