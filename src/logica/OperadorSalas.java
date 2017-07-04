package logica;

import javafx.collections.ObservableList;
import objetos.Sala;
import database.SalaDAO;
import exceptions.RoomsAllocationException;

public class OperadorSalas {

	private static SalaDAO SDao = new SalaDAO();

	/**
	 * Método de salvamento de uma sala na database.
	 * @param sala Sala a ser salva.
	 * @throws Exception Uma exceção pode ser lançada caso uma sala com o mesmo identificador já tenha sido cadastrada na database.
	 */
	public static void salvar(Sala sala) throws Exception{
		if(sala.getId()==null || sala.getId().equals(null) || sala.getId().equalsIgnoreCase("") || sala.getId().contains(" ")){
			throw new RoomsAllocationException("Identificacao Invalida");
		}else if(sala.getTipo()==null || sala.getTipo().equals(null) || sala.getTipo().equalsIgnoreCase("")){
			throw new RoomsAllocationException("Tipo invalido.");
		}else if(sala.getFinalidade().equalsIgnoreCase("Escritorio") && sala.getTipo().equalsIgnoreCase("Normal")){
			throw new RoomsAllocationException("Tipo invalido.");
		}else if(sala.getFinalidade().equalsIgnoreCase("Sala de Aula") && sala.getTipo().equalsIgnoreCase("Biologia")){
			throw new RoomsAllocationException("Tipo invalido.");
		}else if(sala.getFinalidade().equalsIgnoreCase("Sala de Aula") && sala.getTipo().equalsIgnoreCase("Quimica")){
			throw new RoomsAllocationException("Tipo invalido.");
		}else if(sala.getFinalidade().equalsIgnoreCase("Sala de Aula") && sala.getTipo().equalsIgnoreCase("Fisica")){
			throw new RoomsAllocationException("Tipo invalido.");
		}else if(sala.getFinalidade().equalsIgnoreCase("Sala de Aula") && sala.getTipo().equalsIgnoreCase("Computacao")){
			throw new RoomsAllocationException("Tipo invalido.");
		}else if(sala.getFinalidade().equalsIgnoreCase("Laboratorio") && sala.getTipo().equalsIgnoreCase("Normal")){
			throw new RoomsAllocationException("Tipo invalido.");
		}else{
			try{
				SDao.salvar(sala);
			}catch(Exception e){
				throw new RoomsAllocationException(e.getMessage());
			}
		}
	}

	/**
	 * Método de exclusão de uma sala da database.
	 * @param idSala Identificador único da sala a ser excluída.
	 * @throws Exception Uma exceção pode ser lançada caso a sala não exista.
	 */
	public static void excluir(String idSala) throws Exception{
		if(verificaExistencia(idSala)){
			if(OperadorAlocacoes.isAlocada(idSala)){
				OperadorAlocacoes.desalocarSala(idSala);
				SDao.excluir(idSala);
			}else{
				SDao.excluir(idSala);
			}
		}else{
			throw new RoomsAllocationException("Sala nao existe.");
		}
	}


	/**
	 * Pesquisa um atributo específico de uma sala, com base no seu identificador único.
	 * @param idSala Identificador único da sala que possui o atributo pesquisado.
	 * @param atributo Nome do atributo a ser pesquisado.
	 * @return O valor do atributo pesquisado
	 * @throws Exception Uma exceção pode ser lançada caso o atributo seja inválido ou a sala não exista.
	 */
	public static String getAtributoSala(String idSala, String atributo) throws Exception{

		Sala sala = getSala(idSala);

		if(sala!=null){
			if(atributo.equals("apelido")){
				return sala.getApelido();
			}else if(atributo.equals("finalidade")){
				return sala.getFinalidade();
			}else if(atributo.equals("capacidade")){
				return String.valueOf(sala.getCapacidade());
			}else if(atributo.equals("tipo")){
				if(sala.getFinalidade().equalsIgnoreCase("Escritorio")){
					throw new RoomsAllocationException("Atributo invalido");
				}else{
					return sala.getTipo();
				}
			}else if(atributo.equals("aberto")){
				return sala.getIsAberto();
			}else{
				throw new Exception("Atributo invalido");
			}
		}else{
			throw new Exception("Sala nao existe.");
		}

	}

	/**
	 * Verifica se uma sala existe ou não na database.
	 * @param idSala Identificador único da sala a ser pesquisada.
	 * @return Verdadeiro caso a sala exista, falso caso não.
	 * @throws Exception Uma exceção pode ser lançada caso a database ainda não tenha sido criada.
	 */
	public static boolean verificaExistencia(String idSala) throws Exception{
		boolean existe = false;

		ObservableList<Sala> salas = getSalas();

		for(Sala s: salas){
			if(s.getId().equals(idSala)){
				existe = true;
			}
		}

		return existe;

	}

	/**
	 * Método de resgate das salas cadastradas na database.
	 * @return Lista com as salas cadastradas.
	 * @throws Exception Uma exceção pode ser lançada caso a database ainda não tenha sido criada.
	 */
	public static ObservableList<Sala> getSalas() throws Exception{
		return SDao.getSalas();
	}


	/**
	 * Método de resgate de uma sala específica.
	 * @param idSala Identificador único da sala a ser resgatada.
	 * @return Objeto sala que corresponde ao identifcador solicitado.
	 * @throws Exception Uma exceção pode ser lançada caso a database ainda não tenha sido criada.
	 */
	public static Sala getSala(String idSala) throws Exception{
		ObservableList<Sala> salas = getSalas();
		Sala sala = null;
		for(Sala s: salas){
			if(s.getId().equals(idSala)){
				sala = s;
			}
		}

		return sala;
	}

	/**
	 * Verificador de nulidade da database de salas.
	 * @return Verdadeiro caso não existam salas cadastradas, falso caso contrário.
	 * @throws Exception Uma exceção pode ser lançada caso a database não exista.
	 */
	public static boolean databaseVazia() throws Exception{
		ObservableList<Sala> salas = getSalas();
		boolean retorno = true;
		
		if(salas!=null){
			for(Sala e: salas){
				if(e.getId().equals(null) || e.getId().equals("")){
					retorno =  true;
				}else{
					retorno = false;
				}
			}
		}else{
			retorno = true;
		}
		return retorno;
	}

}
