package testes;

import exceptions.RoomsAllocationException;

/***
 * Interface que contem os metodos para os testes de aceitacao.
 * @author Thalles
 *
 */

public interface IFacadeRoomsAllocation {

	/**
	 * Apaga todos os dados do sistema.
	 */
	public void zerarSistema();

	/**
	 * Adiciona uma sala ao sistema. O usuário pode adicionar diferentes tipos
	 * de salas. Cada sala tem uma capacidade física. Cada sala possui uma
	 * identificacao unica: abreviacao do prédio seguida de um numero (Exemplo:
	 * CN-216).
	 *
	 * @param id
	 *            Identificador (unico) da Sala.
	 * @param capacidade
	 *            Capacidade da Sala.
	 * @param finalidade
	 *            Finalidade da Sala.
	 * @param tipo
	 *            Tipo da Sala.
	 * 
	 * @throws RoomsAllocationException
	 */
	public void adicionarSala(String id, int capacidade, String finalidade,
			String tipo) throws RoomsAllocationException;

	/**
	 * 
	 * Adiciona uma sala ao sistema. O usuario pode adicionar diferentes tipos
	 * de salas. Cada sala tem uma capacidade fisica. Cada sala possui uma
	 * identificacao unica: abreviacao do predio seguida de um numero (Exemplo:
	 * CN-216). Algumas salas possuem apelidos (Exemplo: Auditorio Mario
	 * Toyotaro).
	 * 
	 * @param id
	 *            Identificador (unico) da Sala.
	 * @param capacidade
	 *            Capacidade da Sala.
	 * @param finalidade
	 *            Finalidade da Sala.
	 * @param tipo
	 *            Tipo da Sala.
	 * @param apelido
	 *            Apelido da sala.
	 * @throws RoomsAllocationException
	 */
	public void adicionarSala(String id, int capacidade, String finalidade,
			String tipo, String apelido) throws RoomsAllocationException;

	/**
	* Adiciona uma sala ao sistema. O usuario pode adicionar diferentes tipos
	 * de salas. Cada sala tem uma capacidade fisica. Cada sala possui uma
	 * identificacao unica: abreviacao do predio seguida de um numero (Exemplo:
	 * CN-216). Algumas salas possuem apelidos (Exemplo: Auditorio Mario
	 * Toyotaro).
	 *
	 * @param id
	 *            Identificador (unico) da Sala.
	 * @param capacidade
	 *            Capacidade da Sala.
	 * @param finalidade
	 *            Finalidade da Sala.
	 * @param tipo
	 *            Tipo da Sala.
	 * @param apelido
	 *            Apelido da sala.
	 * @param aberto
	 *            Indica se a sala esta aberta.
	 * 
	 * @throws RoomsAllocationException
	 */
	public void adicionarSala(String id, int capacidade, String finalidade,
			String tipo, String apelido, boolean aberto)
			throws RoomsAllocationException;

	/**
	 * Recupera e retorna um atributo da sala.
	 *
	 * @param idSala
	 *            Identificador da sala.
	 * @param atributo
	 *            Atributo a recuperar.
	 * @return O atributo buscado.
	 * 
	 * @throws RoomsAllocationException
	 */
	public String getAtributoSala(String idSala, String atributo)
			throws RoomsAllocationException;

	/**
	 * O usuario pode adicionar eventos ao sistema. Cada evento possui um nome,
	 * datas de inicio e fim, um nome para contato e um numero de repeticoes
	 * semanais
	 * 
	 * @param id
	 *            Identificador (unico) do evento.
	 * @param nome
	 *            Nome do evento.
	 * @param inicio
	 *            Data de inicio do evento.
	 * @param fim
	 *            Data de fim do evento.
	 * @param area
	 *            Area do conhecimento a qual o evento pertence.
	 * @param contato
	 *            Contato do responsavel pelo evento.
	 * @param repeticoes
	 *            Numero de repeticoes do evento.
	 * @throws RoomsAllocationException
	 */
	public void adicionarEvento(String id, String nome, String inicio,
			String fim, String area, String contato, int repeticoes)
			throws RoomsAllocationException;

	/**
	 * O usuario pode adicionar eventos ao sistema. Cada evento possui um nome,
	 * datas de inicio e fim e um nome para contato.
	 * 
	 * @param id
	 *            Identificador (unico) do evento.
	 * @param nome
	 *            Nome do evento.
	 * @param inicio
	 *            Data de inicio do evento.
	 * @param fim
	 *            Data de fim do evento.
	 * @param area
	 *            Area do conhecimento a qual o evento pertence.
	 * @param contato
	 *            Contato do responsavel pelo evento.
	 * 
	 * @throws RoomsAllocationException
	 */
	public void adicionarEvento(String id, String nome, String inicio,
			String fim, String area, String contato)
			throws RoomsAllocationException;

	/**
	 * 
	 * Recupera e retorna um atributo de um evento.
	 * 
	 * @param idEvento
	 *            Identificador do evento.
	 * @param atributo
	 *            Atributo procurado.
	 * @return O atributo.
	 * 
	 * @throws RoomsAllocationException
	 */
	public Object getAtributoEvento(String idEvento, String atributo)
			throws RoomsAllocationException;

	/**
	 * Deve-se alocar uma sala para um evento (repetitivo ou nï¿½o). O sistema
	 * deve informar as salas disponiveis que satisfacam as restricoes do
	 * evento.
	 * 
	 * @param idEvento
	 *            Identificador do evento.
	 * @param idSala
	 *            Identificador da sala onde o evento devera ocorrer.
	 * @throws RoomsAllocationException
	 */
	public void alocarEvento(String idEvento, String idSala)
			throws RoomsAllocationException;

	/**
	 * O usuario pode localizar um evento escalonado atraves do nome, contato,
	 * data etc. 
	 * Ao utilizar o contato como atributo de busca, deve ser retornado todos 
	 * os eventos que contenham o nome do contato inserido. (Exemplo: Prof Pedro e Pedro
	 * Silva contem Pedro) [Formato:LAB-03:EV-09, SA-01:EV-01]
	 * 
	 * @param atributo
	 *            Nome do atributo do evento (nome, contato, horario etc).
	 * @param valor
	 *            Valor do atributo do evento.
	 * @return O identificador do evento, se algum evento com esse atributo for
	 *         encontrado. Valores multiplos sao esperados quando mais de um 
	 * 	   evento for localizado [Formato:LAB-03:EV-09, SA-01:EV-01]
	 * @throws RoomsAllocationException
	 */
	public String localizarEvento(String atributo, String valor)
			throws RoomsAllocationException;

	/**
	 * Desaloca um evento previamente alocado para uma sala.
	 * 
	 * @param idEvento
	 *            Identificador do evento a desalocar.
	 * 
	 * @throws RoomsAllocationException
	 */
	public void desalocarEvento(String idEvento)
			throws RoomsAllocationException;

	/**
	 * O usuario pode cancelar um evento. Neste caso, o cancelamento remove o
	 * evento da base de dados e desvincula as possiveis alocacoes previamente
	 * computadas.
	 * 
	 * @param idEvento
	 *            Identificador do evento a cancelar.
	 * @throws RoomsAllocationException
	 */
	public void cancelarEvento(String idEvento) throws RoomsAllocationException;

	/**
	 * O usuario pode remover salas do sistema. A remocao de uma sala tambem
	 * remove as possiveis alocacoes que referem a mesma, mas nao exclui os
	 * respectivos eventos da base de dados.
	 * 
	 * @param idSala
	 *            Identificador da sala.
	 * @throws RoomsAllocationException
	 */
	public void removerSala(String idSala) throws RoomsAllocationException;

}
