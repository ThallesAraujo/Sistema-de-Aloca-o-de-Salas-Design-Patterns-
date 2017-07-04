package objetos;

/**
 * Classe de representação do objeto alocação.
 * @author Thalles
 *
 */
public class Alocacao {
	
	private String idSala;
	private String idEvento;
	
	/**
	 * Construtor do objeto alocação.
	 * @param idSala Identificador único da sala alocada.
	 * @param idEvento Identificador único do evento alocado.
	 */
	public Alocacao(String idSala, String idEvento) {
		this.idSala = idSala;
		this.idEvento = idEvento;
	}
	/**
	 * Método de resgate do identificador único da sala alocada.
	 * @return Identificador único da sala alocada.
	 */
	public String getIdSala() {
		return idSala;
	}
	/**
	 * Método de resgate do identificador único do evento alocado.
	 * @return Identificador único do evento alocado.
	 */
	public String getIdEvento() {
		return idEvento;
	}
	
	public boolean equals(Alocacao a){
		if(a.getIdEvento().equals(this.idEvento) && a.getIdSala().equals(this.getIdSala())){
			return true;
		}else{
			return false;
		}
	}
	

}
