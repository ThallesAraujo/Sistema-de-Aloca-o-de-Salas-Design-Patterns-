package prompt;

import java.util.Scanner;

import database.FachadaZerarSistema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objetos.Evento;
import objetos.Sala;
import logica.OperadorAlocacoes;
import logica.OperadorEventos;
import logica.OperadorSalas;

/**
 * Classe principal do cliente CommandLine
 * @author Thalles
 *
 */
public class Prompt {

	private static FachadaZerarSistema limparDados;
	private static SalaPrinter printerSalas;
	private static EventoPrinter printerEventos;

	public static void main(String[] args) {

		try {
			database.Inicializar.inicializacao();
			System.out.println("Banco de dados criado");
		} catch (Exception e) {
			System.out.println("Banco de dados detectado");
		}

		boolean continua = true;
		Printer.saudacao();
		while (continua){
			Printer.waiting();
			Scanner leitor = new Scanner(System.in);
			String comando = leitor.nextLine();
			if(comando.startsWith("adicionar sala")){
				adicionarSala(comando.substring(15));
			}else if(comando.startsWith("adicionar evento")){
				adicionarEvento(comando.substring(17));
			}else if(comando.startsWith("excluir sala")){
				excluirSala(comando.substring(13));
			}else if(comando.startsWith("excluir evento")){
				excluirEvento(comando.substring(15));
			}else if(comando.startsWith("alocar evento")){
				alocar(comando.substring(14));
			}else if(comando.startsWith("desalocar evento")){
				desalocar(comando.substring(17));
			}else if(comando.startsWith("localizar atr-sa")){
				getAtributoSala(comando.substring(17));
			}else if(comando.startsWith("localizar atr-ev")){
				getAtributoEvento(comando.substring(17));
			}else if(comando.equals("ver eventos")){
				verEventos();
			}else if(comando.equals("ver salas")){
				verSalas();
			}else if(comando.equals("ver salas - alocacao")){
				verSalasAlocacao();
			}else if(comando.equals("ver eventos realizados")){
				verEventosRealizados();
			}else if(comando.equals("ver eventos a realizar")){
				verEventosARealizar();
			}else if(comando.equals("zerar sistema")){
				zerarSistema();
			}else if(comando.startsWith("ajuda")){
				if(comando.length()==5){
					Printer.ajudaGeral();
				}else{
					Printer.ajuda(comando.substring(6));
				}
			}else if(comando.equals("sair")){
				System.out.println("Sistema encerrado");
				continua = false;
				leitor.close();
			}else{
				System.out.println("Comando invalido. Tente novamente.");
			}
		}

	}

	/**
	 * M�todo de exibi��o dos eventos que mostra somente os eventos a serem realizados.
	 */
	private static void verEventosARealizar() {
		printerEventos = new ARealizarEventoPrinter();
		try {
			printerEventos.print(logica.OperadorEventos.getEventos());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	/**
	 * M�todo de exibi��o dos eventos que mostra somente os eventos j� realizados.
	 */
	private static void verEventosRealizados() {
		printerEventos = new RealizadosEventoPrinter();
		try {
			printerEventos.print(logica.OperadorEventos.getEventos());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	/**
	 * M�todo de exibi��o de salas somente com os atributos importantes para a aloca��o.
	 */
	private static void verSalasAlocacao() {
		try {
			printerSalas = new AllocationReferenceSalaPrinter();
			printerSalas.print(logica.OperadorSalas.getSalas());
		} catch (Exception e) {
			System.out.println("Houve um erro: "+e.getMessage());
		}
	}

	/**
	 * M�todo chamado para solicitar a redefini��o da database
	 */
	private static void zerarSistema() {
		limparDados = new FachadaZerarSistema();
		try {
			limparDados.databaseExistente();
			System.out.println("Os dados foram apagados.");
		} catch (Exception e) {
			System.out.println("Houve um erro: "+e.getMessage());
		}
	}


	/**
	 * M�todo chamado para solicitar a exibi��o padr�o da lista de salas no Prompt.
	 */
	private static void verSalas() {
		try {
			printerSalas = new DefaultSalaPrinter();
			printerSalas.print(logica.OperadorSalas.getSalas());
		} catch (Exception e) {
			System.out.println("Houve um erro: "+e.getMessage());
		}

	}

	/**
	 * M�todo chamado para solicitar a exibi��o padr�o da lista de eventos no Prompt.
	 */
	private static void verEventos() {
		try {
			printerEventos = new DefaultEventoPrinter();
			printerEventos.print(logica.OperadorEventos.getEventos());
		} catch (Exception e) {
			System.out.println("Houve um erro: "+e.getMessage());
		}

	}

	/**
	 * M�todo chamado para solicitar a exibi��o de um atributo de um evento espec�fico
	 * @param comando instru��o contendo os detalhes do evento
	 */
	private static void getAtributoEvento(String comando) {
		String[] atributos = getAtributos(comando);
		//System.out.println("Nome do atributo "+atributos[0]+" id do evento: "+atributos[1]);
		try {
			String atributo = OperadorEventos.getAtributoEvento(atributos[1], atributos[0]);
			System.out.println("O atributo "+atributos[0]+" do evento "+atributos[1]+" �: "+atributo);
		} catch (Exception e) {
			System.out.println("Houve um erro: "+e.getMessage());
		}

	}

	/**
	 * M�todo chamado para solicitar a exibi��o de um atributo de uma sala espec�fica
	 * @param comando Instru��o contendo os detalhes da sala
	 */
	private static void getAtributoSala(String comando) {
		String[] atributos = getAtributos(comando);
		try {
			String atributo = OperadorSalas.getAtributoSala(atributos[1], atributos[0]);
			System.out.println("O atributo "+atributos[0]+" da sala "+atributos[1]+" e: "+atributo);
		} catch (Exception e) {
			System.out.println("Houve um erro: "+e.getMessage());
		}
	}

	/**
	 * M�todo chamado para solicitar a desaloca��o de um evento
	 * @param comando Instru��o contendo o evento a ser desalocado
	 */
	private static void desalocar(String comando) {
		String[] atributos = getAtributos(comando);
		try {
			OperadorAlocacoes.desalocar(atributos[0]);
			System.out.println("O evento "+atributos[0]+" foi desalocado.");
		} catch (Exception e) {
			System.out.println("Houve um erro: "+e.getMessage());
		}


	}


	/**
	 * M�todo chamado para solicitar a aloca��o de um evento em uma sala
	 * @param comando Instru��o contendo o evento e a sala onde ele ser� alocado
	 */
	private static void alocar(String comando) {
		String[] atributos = getAtributos(comando);
		try {
			OperadorAlocacoes.alocar(atributos[0], atributos[1]);
			System.out.println("O evento "+atributos[0]+" foi alocado na sala "+atributos[1]);
		} catch (Exception e) {
			System.out.println("Houve um erro: "+e.getMessage());
		}


	}

	/**
	 * M�todo chamado para solicitar a exclus�o de um evento
	 * @param comando Instru��o contendo o evento a ser exclu�do
	 */
	private static void excluirEvento(String comando) {
		String[] atributos = getAtributos(comando);
		try {
			OperadorEventos.excluir(atributos[0]);
			System.out.println("O evento "+atributos[0]+" foi removido da database");
		} catch (Exception e) {
			System.out.println("Houve um erro: "+e.getMessage());
		}

	}

	/**
	 * M�todo chamado para solicitar a exclus�o de uma sala
	 * @param comando Instru��o contendo a sala a ser exclu�da
	 */
	private static void excluirSala(String comando) {
		String[] atributos = getAtributos(comando);
		try {
			OperadorSalas.excluir(atributos[0]);
			System.out.println("A sala "+atributos[0]+" foi removida da database");
		} catch (Exception e) {
			System.out.println("Houve um erro: "+e.getMessage());
		}

	}

	/**
	 * M�todo chamado para solicitar a adi��o de um evento na database
	 * @param comando Instru��o contendo os atributos do evento a ser salvo
	 */
	private static void adicionarEvento(String comando) {
		String[] atributos = getAtributos(comando);
		try {
			logica.OperadorEventos.salvar(new Evento(atributos[0],atributos[1],atributos[2]+" "+atributos[3],atributos[4]+" "+atributos[5],atributos[6],atributos[7],Integer.parseInt(atributos[8]),"semanal"));
			System.out.println("O evento foi salvo da seguinte forma: | id "+atributos[0]+
					" | nome: "+atributos[1]+
					" | inicio: "+atributos[2]+" "+atributos[3]+
					" | fim: "+atributos[4]+" "+atributos[5]+
					" | contato: "+atributos[6]+
					" | area: "+atributos[7]+
					" | repeticoes: "+atributos[8]);
		} catch (NumberFormatException e) {
			System.out.println("Numero de repeticoes invalido");
		} catch (Exception e) {
			System.out.println("Houve um erro: "+e.getMessage());
		}


	}

	/**
	 * M�todo chamado para solicitar a adi��o de uma sala na database
	 * @param comando Instru��o contendo os atributos da sala a ser salva
	 */
	private static void adicionarSala(String comando) {
		String[] atributos = getAtributos(comando);

		ObservableList<String> finalidades = FXCollections.observableArrayList("Laboratorio","Sala de Aula","Sala de Conferencia");
		ObservableList<String> tipos = FXCollections.observableArrayList("Normal","Inteligente","Videoconferencia");
		ObservableList<String> disciplinas = FXCollections.observableArrayList("Computacao","Fisica","Quimica","Biologia");

		try {
			String finalidade = finalidades.get(Integer.parseInt(atributos[3]));
			String tipo = null;
			String isAberto = atributos[5];
			if(finalidade.equals("Laboratorio")){
				tipo = disciplinas.get(Integer.parseInt(atributos[4]));
			}else{
				tipo = tipos.get(Integer.parseInt(atributos[4]));
			}
			if(!tipos.contains(tipo)){
				if(!tipo.equals("Computacao")){
					isAberto = "Somente para a disciplina "+tipo;
				}
			}
			if(finalidade.equals("Sala de Aula")){
				if(tipo.equals("Videoconferencia")){
					throw new Exception("Salas de aula nao podem ser do tipo Videoconferencia");
				}
			}else if(finalidade.equals("Sala de Conferencia")){
				if(tipo.equals("Inteligente")){
					throw new Exception("Salas de Conferencia nao podem ser do tipo Inteligente");
				}
			}
			logica.OperadorSalas.salvar(new Sala(atributos[0],atributos[1],Integer.parseInt(atributos[2]),finalidade,tipo,isAberto));
			System.out.println("A sala foi salva da seguinte forma: | id "+atributos[0]+
					" | apelido: "+atributos[1]+
					" | capacidade "+atributos[2]+
					" | finalidade: "+finalidade+
					" | tipo: "+tipo+
					" | disponivel? "+isAberto);
		} catch (NumberFormatException e) {
			System.out.println("A capacidade digitada e invalida");
		} catch (Exception e) {
			System.out.println("Houve um erro: "+e.getMessage());
		}

	}

	/**
	 * M�todo para obten��o dos atributos passados nas instru��es
	 * @param comando Instru��o passada pelo usu�rio
	 * @return Atributos contidos na instru��o
	 */
	private static String[] getAtributos(String comando){
		String[] atributos = comando.split(" ");
		return atributos;
	}

}
