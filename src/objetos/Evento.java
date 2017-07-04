package objetos;

/**
 * Classe de representa��o do objeto evento
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
	 * @param id Identificador �nico do evento
	 * @param nome Nome do evento
	 * @param inicio Data de in�cio do evento
	 * @param fim Data de t�rmino do evento
	 * @param contato Nome do respons�vel pelo evento
	 * @param area �rea de interesse do evento
	 * @param repeticoes Quantidade de vezes que o evento se repete entre seu in�cio e seu t�rmino
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
	 * M�todo de recupera��o do identificador �nico do evento
	 * @return Identificador �nico do evento
	 */
	public String getId() {
		return id;
	}

	/**
	 * M�todo de recupera��o do nome do evento
	 * @return Nome do evento
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * M�todo de recupera��o da data de in�cio do evento
	 * @return Data de in�cio do evento
	 */
	public String getInicio() {
		return inicio;
	}

	/**
	 * M�todo de recupera��o da data de t�rmino do evento
	 * @return Data em que o evento termina
	 */
	public String getFim() {
		return fim;
	}

	/**
	 * M�todo de recupera��o do respons�vel pelo evento
	 * @return Nome do respons�vel pelo evento
	 */
	public String getContato() {
		return contato;
	}

	/**
	 * M�todo de recupera��o da �rea de interesse do evento
	 * @return �rea do evento
	 */
	public String getArea() {
		return area;
	}

	/**
	 * M�todo de recupera��o da quantidade de vezes que o evento se repete
	 * @return N�mero de repeti��es do evento
	 */
	public int getRepeticoes() {
		return repeticoes;
	}
	
	public String getTipo(){
		return this.tipo;
	}
	
	public String toString(){
		String repeticoes = this.repeticoes>1? " - Se repete "+this.repeticoes+" vezes":" - N�o se repete";
		return "C�digo: "+this.id+" - Nome: "+this.nome+" - Come�a em: "+this.inicio+" - Termina em: "+this.fim+" - Organiza��o: "+this.contato+repeticoes+" Tipo: "+this.tipo;
	}



}
