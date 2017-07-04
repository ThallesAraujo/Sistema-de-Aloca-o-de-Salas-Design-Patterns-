package objetos;

/**
 * Classe de representação do objeto sala
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
	 * @param id Identificador único da sala
	 * @param apelido Nome informal da sala
	 * @param capacidade Quantidade máxima de pessoas comportada pela sala
	 * @param finalidade Destinação da sala
	 * @param tipo Disciplina ou subtipo da sala
	 * @param isAberto Restrição de disponibilidade da sala
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
	 * Método de recuperação do identificador único da sala
	 * @return Identificador único da sala
	 */
	public String getId() {
		return id;
	}

	/**
	 * Método de recuperação do nome informal da sala
	 * @return Apelido da sala
	 */
	public String getApelido() {
		return apelido;
	}

	/**
	 * Método de recuperação da utilidade da sala
	 * @return Destinação da sala
	 */
	public String getFinalidade() {
		return finalidade;
	}

	/**
	 * Método de recuperação da quantidade máxima comportada pela sala
	 * @return Capacidade da sala
	 */
	public int getCapacidade() {
		return capacidade;
	}

	/**
	 * Método de recuperação da disciplina ou subtipo da sala
	 * @return Tipo da sala
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Método de recuperação da restrição de uso da sala
	 * @return Disponibilidade da sala
	 */
	public String getIsAberto() {
		return isAberto;
	}



}


