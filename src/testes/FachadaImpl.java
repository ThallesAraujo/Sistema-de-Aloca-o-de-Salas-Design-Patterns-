package testes;

import java.util.ArrayList;
import java.util.List;

import database.FachadaZerarSistema;
import easyaccept.EasyAcceptFacade;
import exceptions.RoomsAllocationException;
import objetos.Evento;
import objetos.Sala;
import logica.OperadorAlocacoes;
import logica.OperadorEventos;
import logica.OperadorSalas;

/**
 * Implementa��o da fachada de testes do EasyAccept
 * @author Thalles
 *
 */
public class FachadaImpl implements IFacadeRoomsAllocation{
	
	public static void main(String[] args) {

		
		List<String> arquivos = new ArrayList<String>();
		arquivos.add("US1.txt");
		arquivos.add("US2.txt");
		arquivos.add("US3.txt");
		arquivos.add("US4.txt");
		arquivos.add("US5.txt");
		arquivos.add("US6.txt");
		arquivos.add("US7.txt");

		FachadaImpl fachada = new FachadaImpl();
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(fachada, arquivos);
		System.out.println("Conectando ao banco de dados (isso pode demorar)");
		eaFacade.executeTests();
		System.out.println(eaFacade.getCompleteResults());
		//Imprimir: N�mero dos testes aprovados/N�mero total de testes
		System.out.println(eaFacade.getTotalNumberOfPassedTests()+"/"+eaFacade.getTotalNumberOfTests());
		
	}

	/**
	 * M�todo de redefini��o da database
	 */
	@Override
	public void zerarSistema() {
		FachadaZerarSistema limparDados = new FachadaZerarSistema();
		try{
			limparDados.databaseExistente();
		}catch(Exception e){
			try{
				limparDados.databaseInexistente();
			}catch(Exception exc){
				exc.printStackTrace();
			}
		}
		
	}

	/**
	 * Primeiro m�todo para adi��o de salas
	 */
	@Override
	public void adicionarSala(String id, int capacidade, String finalidade,
			String tipo) throws RoomsAllocationException {
		try {
			OperadorSalas.salvar(new Sala(id,"",capacidade,finalidade,tipo,""));
		} catch (Exception e) {
			throw new RoomsAllocationException(e.getMessage());
		}
		
	}

	/**
	 * Segundo m�todo para adi��o de salas
	 */
	@Override
	public void adicionarSala(String id, int capacidade, String finalidade,
			String tipo, String apelido) throws RoomsAllocationException {
		try {
			OperadorSalas.salvar(new Sala(id,apelido,capacidade,finalidade,tipo,""));
		} catch (Exception e) {
			throw new RoomsAllocationException(e.getMessage());
		}
		
	}

	/**
	 * Terceiro m�todo para adi��o de salas
	 */
	@Override
	public void adicionarSala(String id, int capacidade, String finalidade,
			String tipo, String apelido, boolean aberto)
			throws RoomsAllocationException {
		String isAberto = aberto?"sim":"nao";
		try {
			OperadorSalas.salvar(new Sala(id,apelido,capacidade,finalidade,tipo,isAberto));
		} catch (Exception e) {
			throw new RoomsAllocationException(e.getMessage());
		}
		
	}

	/**
	 * M�todo de resgate de um atributo espec�fico de uma sala mediante id e nome do atributo
	 */
	@Override
	public String getAtributoSala(String idSala, String atributo)
			throws RoomsAllocationException {
		try {
			return OperadorSalas.getAtributoSala(idSala, atributo);
		} catch (Exception e) {
			throw new RoomsAllocationException(e.getMessage());
		}
	}

	/**
	 * Primeiro m�todo para adi��o de eventos
	 */
	@Override
	public void adicionarEvento(String id, String nome, String inicio,
			String fim, String area, String contato, int repeticoes)
			throws RoomsAllocationException {
		try {
			OperadorEventos.salvar(new Evento(id,nome,inicio,fim,contato,area,repeticoes,"semanal"));
		} catch (Exception e) {
			throw new RoomsAllocationException(e.getMessage());
		}
		
	}

	/**
	 * Segundo m�todo para adi��o de eventos
	 */
	@Override
	public void adicionarEvento(String id, String nome, String inicio,
			String fim, String area, String contato)
			throws RoomsAllocationException {
		try {
			OperadorEventos.salvar(new Evento(id,nome,inicio,fim,contato,area,0,"semanal"));
		} catch (Exception e) {
			throw new RoomsAllocationException(e.getMessage());
		}
		
	}

	/**
	 * M�todo de resgate de um atributo de um evento mediante id e nome do atributo
	 */
	@Override
	public Object getAtributoEvento(String idEvento, String atributo)
			throws RoomsAllocationException {
		try {
			return OperadorEventos.getAtributoEvento(idEvento, atributo);
		} catch (Exception e) {
			throw new RoomsAllocationException(e.getMessage());
		}
	}

	/**
	 * M�todo de aloca��o de um evento em uma sala
	 */
	@Override
	public void alocarEvento(String idEvento, String idSala)
			throws RoomsAllocationException {
		try {
			OperadorAlocacoes.alocar(idEvento, idSala);
		} catch (Exception e) {
			throw new RoomsAllocationException(e.getMessage());
		}
		
	}

	/**
	 * M�todo de localiza��o de aloca��es de eventos
	 */
	@Override
	public String localizarEvento(String atributo, String valor) throws RoomsAllocationException{
		try {
			return OperadorAlocacoes.localizarAlocacao(atributo, valor);
		} catch (Exception e) {
			throw new RoomsAllocationException(e.getMessage());
		}
	}

	/**
	 * M�todo de desaloca��o de eventos
	 */
	@Override
	public void desalocarEvento(String idEvento)
			throws RoomsAllocationException {
		try {
			OperadorAlocacoes.desalocar(idEvento);
		} catch (Exception e) {
			throw new RoomsAllocationException(e.getMessage());
		}
		
	}

	/**
	 * M�todo de exclus�o de eventos
	 */
	@Override
	public void cancelarEvento(String idEvento) throws RoomsAllocationException {
		try {
			OperadorEventos.excluir(idEvento);
		} catch (Exception e) {
			throw new RoomsAllocationException(e.getMessage());
		}
		
	}

	/**
	 * M�todo de exclus�o de salas
	 */
	@Override
	public void removerSala(String idSala) throws RoomsAllocationException {
		try {
			OperadorSalas.excluir(idSala);
		} catch (Exception e) {
			throw new RoomsAllocationException(e.getMessage());
		}
		
	}
	
	

}
