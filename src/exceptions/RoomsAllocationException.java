package exceptions;

/**
 * Exceção lançada em caso de erro na criação/remoção de objetos ou alocação/desalocação de eventos
 * @author Thalles
 *
 */
public class RoomsAllocationException extends Exception {


	/**
	 * UID de versão serial da exceção, gerado automaticamente pelo Eclipse.
	 */
	private static final long serialVersionUID = -5154402731094410399L;

	/**
	 * Método construtor da exceção
	 * @param msg Mensagem a ser mostrada na exceção.
	 */
	public RoomsAllocationException(String msg){
		
		super(msg);
	}
	
}
