package exceptions;

/**
 * Exce��o lan�ada em caso de erro na cria��o/remo��o de objetos ou aloca��o/desaloca��o de eventos
 * @author Thalles
 *
 */
public class RoomsAllocationException extends Exception {


	/**
	 * UID de vers�o serial da exce��o, gerado automaticamente pelo Eclipse.
	 */
	private static final long serialVersionUID = -5154402731094410399L;

	/**
	 * M�todo construtor da exce��o
	 * @param msg Mensagem a ser mostrada na exce��o.
	 */
	public RoomsAllocationException(String msg){
		
		super(msg);
	}
	
}
