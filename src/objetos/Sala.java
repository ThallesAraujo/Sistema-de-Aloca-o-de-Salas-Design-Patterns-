package objetos;

/**
 * Classe de representa��o do objeto sala
 * @author Thalles
 *
 */
public class Sala {

	private String id;
	private String apelido;
	private String finalidade;
	private int capacidade;
	private String tipo;
	private String isAberto;

	/**
	 * Construtor do objeto sala
	 * @param id Identificador �nico da sala
	 * @param apelido Nome informal da sala
	 * @param capacidade Quantidade m�xima de pessoas comportada pela sala
	 * @param finalidade Destina��o da sala
	 * @param tipo Disciplina ou subtipo da sala
	 * @param isAberto Restri��o de disponibilidade da sala
	 */
	public Sala(String id, String apelido, int capacidade, String finalidade, String tipo, String isAberto){
		this.id =id;
		this.apelido = apelido;
		this.capacidade = capacidade;
		this.finalidade = finalidade;
		this.tipo = tipo;
		this.isAberto = isAberto;
	}

	/**
	 * M�todo de recupera��o do identificador �nico da sala
	 * @return Identificador �nico da sala
	 */
	public String getId() {
		return id;
	}

	/**
	 * M�todo de recupera��o do nome informal da sala
	 * @return Apelido da sala
	 */
	public String getApelido() {
		return apelido;
	}

	/**
	 * M�todo de recupera��o da utilidade da sala
	 * @return Destina��o da sala
	 */
	public String getFinalidade() {
		return finalidade;
	}

	/**
	 * M�todo de recupera��o da quantidade m�xima comportada pela sala
	 * @return Capacidade da sala
	 */
	public int getCapacidade() {
		return capacidade;
	}

	/**
	 * M�todo de recupera��o da disciplina ou subtipo da sala
	 * @return Tipo da sala
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * M�todo de recupera��o da restri��o de uso da sala
	 * @return Disponibilidade da sala
	 */
	public String getIsAberto() {
		return isAberto;
	}



}


