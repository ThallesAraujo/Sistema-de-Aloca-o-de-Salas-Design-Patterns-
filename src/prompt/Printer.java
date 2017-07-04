package prompt;
/**
 * Classe dos itens de exibição do cliente CommandLine
 * @author Thalles
 *
 */
public class Printer {
	
	/**
	 * Saudação mostrada ao se abrir o prompt
	 */
	public static void saudacao(){
		System.out.println("_______________________________________________________");
		System.out.println("Sistema de alocação de salas - versão Official 4.0");
		System.out.println("Outdated: este prompt é incapaz de adicionar eventos");
		System.out.println("mensais ou diários. Para os mesmos, utilize a interface");
		System.out.println("gráfica.");
		System.out.println("_______________________________________________________");
		System.out.println("Digite ajuda a qualquer momento para visualizar a ajuda");
		System.out.println("Digite sair a qualquer momento para encerrar o programa");
		System.out.println();

	}
	
	/**
	 * Indicador de modo de espera do prompt de comando.
	 */
	public static void waiting(){
		System.out.println();
		System.out.println("Comando:");
	}
	
	
	
	
	/**	
	 * Exibe a ajuda básica do Prompt
	 */
	public static void ajudaGeral(){
		
		System.out.println("_______________________________________________________");
		System.out.println("Ajuda geral - Sistema de alocação de salas");
		System.out.println("_______________________________________________________");
		System.out.println();
		System.out.println("O cliente de linha de comando funciona de modo parecido");
		System.out.println("a um prompt de comando convencional. Você deve digitar");
		System.out.println("o comando correspondente à ação desejada + os atributos");
		System.out.println("correspondentes ao mesmo (se houver)");
		System.out.println();
		System.out.println("Lista de comandos--------------------------------------");
		System.out.println();
		System.out.println("-adicionar sala			-alocar evento");
		System.out.println("-adicionar evento		-desalocar evento");
		System.out.println("-ver salas			-localizar atr-sa");
		System.out.println("-ver eventos			-localizar atr-ev");
		System.out.println("-excluir sala			-zerar sistema");
		System.out.println("-excluir evento			-sair");
		System.out.println();
		System.out.println("Comandos de ajuda:--------------------------------------");
		System.out.println();
		System.out.println("-ajuda				-ajuda alocacao");
		System.out.println("-ajuda salas			-ajuda localizar");
		System.out.println("-ajuda eventos			-ajuda database");
		System.out.println();
		System.out.println("Para mais informações, digite ajuda + o assunto");
		System.out.println("correspondente");
		System.out.println("________________________________________________________");
		
	}
	
	/**
	 * Exibe ajuda sobre um assunto específico
	 * @param ajuda Assunto sobre o qual se deseja ajuda
	 */
	public static void ajuda(String ajuda){
		if(ajuda.equals("salas")){
			System.out.println("_________________________________________________________________________________");
			System.out.println("Ajuda - Salas");
			System.out.println();
			System.out.println("Adicionar uma sala---------------------------------------------------------------");
			System.out.println();
			System.out.println("O comando para adicionar uma sala é:");
			System.out.println();
			System.out.println("	------------------------------------------------------------------------");
			System.out.println("	| adicionar sala id apelido capacidade finalidade tipo disponibilidade |");
			System.out.println("	------------------------------------------------------------------------");
			System.out.println();
			System.out.println("sendo que:");
			System.out.println();
			System.out.println("#id -> (String) Identificador único da sala. A sala a ser cadastrada não pode ");
			System.out.println("ter um identificador igual ao de nenhuma outra sala já cadastrada.");
			System.out.println();
			System.out.println("#apelido -> (String) Nome pelo qual a sala é conhecida.");
			System.out.println();
			System.out.println("#capacidade -> (Integer) Quantidade máxima de pessoas que a sala comporta.");
			System.out.println();
			System.out.println("#finalidade -> (Integer) Tipo de sala. Só pode assumir os seguintes valores ");
			System.out.println("numéricos:");
			System.out.println();
			System.out.println("	*0 = Laboratório		*2 = Sala de Conferência");
			System.out.println("	*1 = Sala de Aula");
			System.out.println();
			System.out.println("#Tipo -> (Integer) Subtipo da sala. Só pode assumir os seguintes valores");
			System.out.println("numéricos:");
			System.out.println();
			System.out.println("	*0 = Normal (Somente Salas de Aula e de Conferencia)");
			System.out.println("	*1 = Inteligente (Somente Salas de Aula)");
			System.out.println("	*2 = Videoconferência (Somente Salas de Conferência)");
			System.out.println();
			System.out.println("	- Para laboratórios, os valores são:");
			System.out.println();
			System.out.println("	*0 = Computação			*2 = Física");
			System.out.println("	*1 = Química			*3 = Biologia");
			System.out.println();
			System.out.println("#Disponibilidade -> (String) Restrição de uso da sala. Caso não haja, digitar sim.");
			System.out.println("A disponibilidade dos laboratórios é definida automaticamente com base na");
			System.out.println("disciplina. (Exceto laboratórios de computação)");
			System.out.println();
			System.out.println("Remover uma sala-------------------------------------------------------------------");
			System.out.println();
			System.out.println("O comando para remover uma sala é:");
			System.out.println();
			System.out.println("	---------------------");
			System.out.println("	|excluir sala idSala|");
			System.out.println("	---------------------");
			System.out.println();
			System.out.println("sendo que:");
			System.out.println();
			System.out.println("#id -> (String) Identificador único da sala a ser excluída.");
			System.out.println();
			System.out.println("Listar as salas--------------------------------------------------------------------");
			System.out.println();
			System.out.println("Para ver todas as salas cadastradas, com cada um de seus atributos, basta digitar");
			System.out.println();
			System.out.println("	-----------	");	
			System.out.println("	|ver salas|");
			System.out.println("	-----------");
			System.out.println("Listagem especial para alocacao------------------------------------------------------");
			System.out.println();
			System.out.println("Para ver todas as salas cadastradas exibindo somente os atributos importantes para");
			System.out.println("alocá-la, digite:");
			System.out.println();
			System.out.println("	----------------------	");	
			System.out.println("	|ver salas - alocacao|");
			System.out.println("	----------------------");
			System.out.println("____________________________________________________________________________________");
		}else if(ajuda.equals("alocacao")){
			System.out.println("__________________________________________________________________________");
			System.out.println("Ajuda - Alocação");
			System.out.println();
			System.out.println("Alocar um evento em uma sala----------------------------------------------");
			System.out.println();
			System.out.println("Para alocar um evento em uma sala, o comando é:");
			System.out.println();
			System.out.println("	-------------------------------");
			System.out.println("	|alocar evento idEvento idSala|");
			System.out.println("	-------------------------------");
			System.out.println();
			System.out.println("Sendo que:");
			System.out.println();
			System.out.println("#idEvento ->(String) identificador único do evento a ser alocado");
			System.out.println();
			System.out.println("#idSala ->(String) identificador único da sala que receberá o evento");
			System.out.println();
			System.out.println("Desalocar um evento---------------------------------------------------------");
			System.out.println();
			System.out.println("Para desalocar um evento alocado, o comando é:");
			System.out.println();
			System.out.println("	---------------------------");
			System.out.println("	|desalocar evento idEvento|");
			System.out.println("	---------------------------");
			System.out.println();
			System.out.println("Sendo que:");
			System.out.println();
			System.out.println("#idEvento -> (String) identificador único do evento a ser desalocado");
			System.out.println();
			System.out.println("__________________________________________________________________________");
		}else if(ajuda.equals("localizar")){
			System.out.println("________________________________________________________");
			System.out.println("Ajuda - Localização de atributos");
			System.out.println();
			System.out.println("Localizar um atributo de uma sala-----------------------");
			System.out.println();
			System.out.println("Para localizar algum dos itens de uma sala, o comando é:");
			System.out.println();
			System.out.println("	----------------------------------");
			System.out.println("	|localizar atr-sa atributo idSala|");
			System.out.println("	----------------------------------");
			System.out.println();
			System.out.println("Sendo que:");
			System.out.println();
			System.out.println("#Atributo -> (String) nome do atributo a ser pesquisado.");
			System.out.println("Os atributos válidos são:");
			System.out.println();
			System.out.println("	*apelido		*capacidade");
			System.out.println("	*finalidade		*aberto (disponibilidade)");
			System.out.println("	*tipo");	
			System.out.println();
			System.out.println("#idSala -> (String) identificador único da sala");
			System.out.println();
			System.out.println("Localizar um atributo de um evento-----------------------");
			System.out.println();
			System.out.println("Para localizar um dos itens de um evento, o comando é:");
			System.out.println();
			System.out.println("	------------------------------------");
			System.out.println("	|localizar atr-ev atributo idEvento|");
			System.out.println("	------------------------------------");
			System.out.println();
			System.out.println("Sendo que:");
			System.out.println();
			System.out.println("#Atributo -> (String) nome do atributo a ser pesquisado.");
			System.out.println("Os atributos válidos são:");
			System.out.println();
			System.out.println("	*nome			*contato");
			System.out.println("	*inicio			*area");
			System.out.println("	*fim			*repeticoes");
			System.out.println();
			System.out.println("#idEvento -> (String) identificador único do evento");
			System.out.println("________________________________________________________");
		}else if(ajuda.equals("database")){
			System.out.println("________________________________________________________");
			System.out.println("Ajuda - Apagar database");
			System.out.println();
			System.out.println("O comando a seguir exclui tudo o que houver na database,");
			System.out.println("tanto as salas como os eventos cadastrados:");
			System.out.println();
			System.out.println("	---------------");
			System.out.println("	|zerar sistema|");
			System.out.println("	---------------");
			System.out.println();
			System.out.println("________________________________________________________");
		}else if(ajuda.equals("eventos")){
			System.out.println("___________________________________________________________________________________");
			System.out.println("Ajuda - Eventos");
			System.out.println();
			System.out.println("Adicionar um evento----------------------------------------------------------------");
			System.out.println();
			System.out.println("O comando para adicionar eventos é:");
			System.out.println();
			System.out.println("	-------------------------------------------------------------------");
			System.out.println("	|adicionar evento idEvento nome inicio fim contato area repeticoes|");
			System.out.println("	-------------------------------------------------------------------");
			System.out.println();
			System.out.println("Sendo que:");
			System.out.println();
			System.out.println("#idEvento -> (String) identificador único do evento. O mesmo não pode ser igual");
			System.out.println("ao de nenhum outro evento já cadastrado");
			System.out.println();
			System.out.println("#nome -> (String) nome do evento a ser cadastrado.");
			System.out.println();
			System.out.println("#inicio -> (String) data e hora de início do evento. Deve seguir o formato ");
			System.out.println("dd/mm/aaaa hh:mm, onde dd corresponde ao dia (2 caracteres); mm corresponde ao");
			System.out.println("mês (2 caracteres); aaaa corresponde ao ano (4 caracteres); hh corresponde a hora");
			System.out.println("(2 caracteres, formato 24 horas) e mm corresponde aos minutos (2 caracteres).");
			System.out.println();
			System.out.println("#fim -> (String) data e hora de término do evento. Deve seguir o formato ");
			System.out.println("dd/mm/aaaa hh:mm, onde dd corresponde ao dia (2 caracteres); mm corresponde ao");
			System.out.println("mês (2 caracteres); aaaa corresponde ao ano (4 caracteres); hh corresponde a hora");
			System.out.println("(2 caracteres, formato 24 horas) e mm corresponde aos minutos (2 caracteres).");
			System.out.println();
			System.out.println("#contato -> (String) nome do responsável pela organização do evento");
			System.out.println();
			System.out.println("#area -> (String) area ou disciplina de interesse do evento");
			System.out.println();
			System.out.println("#repeticoes -> (Integer) quantidade de vezes que o evento se repetirá naquele mesmo ");
			System.out.println("dia da semana");
			System.out.println();
			System.out.println("Excluir um evento----------------------------------------------------------------------");
			System.out.println();
			System.out.println("Para apagar um evento cadastrado, o comando é:");
			System.out.println();
			System.out.println("	-------------------------");
			System.out.println("	|excluir evento idEvento|");
			System.out.println("	-------------------------");
			System.out.println();
			System.out.println("Sendo que:");
			System.out.println();
			System.out.println("#idEvento: identificador único do evento a ser excluído");
			System.out.println();
			System.out.println("Lembrando que a exclusão de um evento remove também a alocação atrelada a ele. Com isso, ");
			System.out.println("a sala ocupada por ele fica disponível novamente.");
			System.out.println();
			System.out.println("Listar eventos-----------------------------------------------------------------------------");
			System.out.println();
			System.out.println("Para listar todos os eventos, com seus respectivos atributos, o comando é:");
			System.out.println();
			System.out.println("	-------------");
			System.out.println("	|ver eventos|");
			System.out.println("	-------------");
			System.out.println();
			System.out.println("Listagem dos eventos não realizados");
			System.out.println();
			System.out.println("Para ver somente os eventos que ainda não foram realizados, digite:");
			System.out.println();
			System.out.println("	------------------------");	
			System.out.println("	|ver eventos a realizar|");
			System.out.println("	------------------------");
			System.out.println();
			System.out.println("Listagem dos eventos realizados");
			System.out.println();
			System.out.println("Para ver somente os eventos que já aconteceram, digite:");
			System.out.println();
			System.out.println("	------------------------");	
			System.out.println("	|ver eventos realizados|");
			System.out.println("	------------------------");
			System.out.println("____________________________________________________________________________________________");
		}else{
			System.out.println("Comando de ajuda inválido. Digite ajuda (sem atributos) para ver os comandos");
			System.out.println("de ajuda específicos");
		}
	}

}
