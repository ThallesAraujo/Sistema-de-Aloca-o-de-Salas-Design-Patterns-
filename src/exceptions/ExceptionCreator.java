package exceptions;

/**
 * Adaptador de exce��es do banco de dados
 * @author Thalles
 *
 */
public class ExceptionCreator {
	
	/**
	 * M�todo de convers�o da exce��o
	 * @param message Mensagem da exce��o
	 * @return Uma exce��o compat�vel com o esperado pelo cliente.
	 */
	public static Exception createException(String message){
		if(message.contains("ID")){
			return new RoomsAllocationException("Identificacao Invalida");
		}else if(message.contains("TIPO")){
			return new RoomsAllocationException("Tipo invalido.");
		}else if(message.contains("SALAS")){
			return new RoomsAllocationException("Ja existe sala com esta identificacao.");
		}else if(message.contains("EVENTOS")){
			return new RoomsAllocationException("Ja existe evento com esta identificacao.");
		}else{
			return new RoomsAllocationException("Atributo invalido.");
		}
		
	}

}
