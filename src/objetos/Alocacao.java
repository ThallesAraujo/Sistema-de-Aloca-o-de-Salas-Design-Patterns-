package objetos;

/**
 * Classe de representa��o do objeto aloca��o.
 * @author Thalles
 *
 */
public class Alocacao {
	
	private String idSala;
	private String idEvento;
	
	/**
	 * Construtor do objeto aloca��o.
	 * @param idSala Identificador �nico da sala alocada.
	 * @param idEvento Identificador �nico do evento alocado.
	 */
	public Alocacao(String idSala, String idEvento) {
		this.idSala = idSala;
		this.idEvento = idEvento;
	}
	/**
	 * M�todo de resgate do identificador �nico da sala alocada.
	 * @return Identificador �nico da sala alocada.
	 */
	public String getIdSala() {
		return idSala;
	}
	/**
	 * M�todo de resgate do identificador �nico do evento alocado.
	 * @return Identificador �nico do evento alocado.
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
