package objetos;

/**
 * Classe de representação do objeto evento
 * @author Thalles
 *
 */
public class Evento {

	private String id;
	private String nome;
	private String inicio;
	private String fim;
	private String contato;
	private String area;
	private int repeticoes;
	private String tipo;

	/**
	 * Construtor do objeto evento
	 * @param id Identificador único do evento
	 * @param nome Nome do evento
	 * @param inicio Data de início do evento
	 * @param fim Data de término do evento
	 * @param contato Nome do responsável pelo evento
	 * @param area Área de interesse do evento
	 * @param repeticoes Quantidade de vezes que o evento se repete entre seu início e seu término
	 */
	public Evento(String id, String nome, String inicio, String fim, String contato, String area, int repeticoes,String tipo) {

		this.id =  id;
		this.nome = nome;
		this.inicio = inicio;
		this.fim = fim;
		this.contato = contato;
		this.area = area;
		this.repeticoes = repeticoes;
		this.tipo = tipo;

	}
	
	/**
	 * Método de recuperação do identificador único do evento
	 * @return Identificador único do evento
	 */
	public String getId() {
		return id;
	}

	/**
	 * Método de recuperação do nome do evento
	 * @return Nome do evento
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Método de recuperação da data de início do evento
	 * @return Data de início do evento
	 */
	public String getInicio() {
		return inicio;
	}

	/**
	 * Método de recuperação da data de término do evento
	 * @return Data em que o evento termina
	 */
	public String getFim() {
		return fim;
	}

	/**
	 * Método de recuperação do responsável pelo evento
	 * @return Nome do responsável pelo evento
	 */
	public String getContato() {
		return contato;
	}

	/**
	 * Método de recuperação da área de interesse do evento
	 * @return Área do evento
	 */
	public String getArea() {
		return area;
	}

	/**
	 * Método de recuperação da quantidade de vezes que o evento se repete
	 * @return Número de repetições do evento
	 */
	public int getRepeticoes() {
		return repeticoes;
	}
	
	public String getTipo(){
		return this.tipo;
	}
	
	public String toString(){
		String repeticoes = this.repeticoes>1? " - Se repete "+this.repeticoes+" vezes":" - Não se repete";
		return "Código: "+this.id+" - Nome: "+this.nome+" - Começa em: "+this.inicio+" - Termina em: "+this.fim+" - Organização: "+this.contato+repeticoes+" Tipo: "+this.tipo;
	}



}
