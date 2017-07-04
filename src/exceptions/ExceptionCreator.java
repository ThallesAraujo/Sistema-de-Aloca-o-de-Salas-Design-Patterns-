package exceptions;

/**
 * Adaptador de exceções do banco de dados
 * @author Thalles
 *
 */
public class ExceptionCreator {
	
	/**
	 * Método de conversão da exceção
	 * @param message Mensagem da exceção
	 * @return Uma exceção compatível com o esperado pelo cliente.
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
