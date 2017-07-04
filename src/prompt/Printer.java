package prompt;
/**
 * Classe dos itens de exibi��o do cliente CommandLine
 * @author Thalles
 *
 */
public class Printer {
	
	/**
	 * Sauda��o mostrada ao se abrir o prompt
	 */
	public static void saudacao(){
		System.out.println("_______________________________________________________");
		System.out.println("Sistema de aloca��o de salas - vers�o Official 4.0");
		System.out.println("Outdated: este prompt � incapaz de adicionar eventos");
		System.out.println("mensais ou di�rios. Para os mesmos, utilize a interface");
		System.out.println("gr�fica.");
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
	 * Exibe a ajuda b�sica do Prompt
	 */
	public static void ajudaGeral(){
		
		System.out.println("_______________________________________________________");
		System.out.println("Ajuda geral - Sistema de aloca��o de salas");
		System.out.println("_______________________________________________________");
		System.out.println();
		System.out.println("O cliente de linha de comando funciona de modo parecido");
		System.out.println("a um prompt de comando convencional. Voc� deve digitar");
		System.out.println("o comando correspondente � a��o desejada + os atributos");
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
		System.out.println("Para mais informa��es, digite ajuda + o assunto");
		System.out.println("correspondente");
		System.out.println("________________________________________________________");
		
	}
	
	/**
	 * Exibe ajuda sobre um assunto espec�fico
	 * @param ajuda Assunto sobre o qual se deseja ajuda
	 */
	public static void ajuda(String ajuda){
		if(ajuda.equals("salas")){
			System.out.println("_________________________________________________________________________________");
			System.out.println("Ajuda - Salas");
			System.out.println();
			System.out.println("Adicionar uma sala---------------------------------------------------------------");
			System.out.println();
			System.out.println("O comando para adicionar uma sala �:");
			System.out.println();
			System.out.println("	------------------------------------------------------------------------");
			System.out.println("	| adicionar sala id apelido capacidade finalidade tipo disponibilidade |");
			System.out.println("	------------------------------------------------------------------------");
			System.out.println();
			System.out.println("sendo que:");
			System.out.println();
			System.out.println("#id -> (String) Identificador �nico da sala. A sala a ser cadastrada n�o pode ");
			System.out.println("ter um identificador igual ao de nenhuma outra sala j� cadastrada.");
			System.out.println();
			System.out.println("#apelido -> (String) Nome pelo qual a sala � conhecida.");
			System.out.println();
			System.out.println("#capacidade -> (Integer) Quantidade m�xima de pessoas que a sala comporta.");
			System.out.println();
			System.out.println("#finalidade -> (Integer) Tipo de sala. S� pode assumir os seguintes valores ");
			System.out.println("num�ricos:");
			System.out.println();
			System.out.println("	*0 = Laborat�rio		*2 = Sala de Confer�ncia");
			System.out.println("	*1 = Sala de Aula");
			System.out.println();
			System.out.println("#Tipo -> (Integer) Subtipo da sala. S� pode assumir os seguintes valores");
			System.out.println("num�ricos:");
			System.out.println();
			System.out.println("	*0 = Normal (Somente Salas de Aula e de Conferencia)");
			System.out.println("	*1 = Inteligente (Somente Salas de Aula)");
			System.out.println("	*2 = Videoconfer�ncia (Somente Salas de Confer�ncia)");
			System.out.println();
			System.out.println("	- Para laborat�rios, os valores s�o:");
			System.out.println();
			System.out.println("	*0 = Computa��o			*2 = F�sica");
			System.out.println("	*1 = Qu�mica			*3 = Biologia");
			System.out.println();
			System.out.println("#Disponibilidade -> (String) Restri��o de uso da sala. Caso n�o haja, digitar sim.");
			System.out.println("A disponibilidade dos laborat�rios � definida automaticamente com base na");
			System.out.println("disciplina. (Exceto laborat�rios de computa��o)");
			System.out.println();
			System.out.println("Remover uma sala-------------------------------------------------------------------");
			System.out.println();
			System.out.println("O comando para remover uma sala �:");
			System.out.println();
			System.out.println("	---------------------");
			System.out.println("	|excluir sala idSala|");
			System.out.println("	---------------------");
			System.out.println();
			System.out.println("sendo que:");
			System.out.println();
			System.out.println("#id -> (String) Identificador �nico da sala a ser exclu�da.");
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
			System.out.println("aloc�-la, digite:");
			System.out.println();
			System.out.println("	----------------------	");	
			System.out.println("	|ver salas - alocacao|");
			System.out.println("	----------------------");
			System.out.println("____________________________________________________________________________________");
		}else if(ajuda.equals("alocacao")){
			System.out.println("__________________________________________________________________________");
			System.out.println("Ajuda - Aloca��o");
			System.out.println();
			System.out.println("Alocar um evento em uma sala----------------------------------------------");
			System.out.println();
			System.out.println("Para alocar um evento em uma sala, o comando �:");
			System.out.println();
			System.out.println("	-------------------------------");
			System.out.println("	|alocar evento idEvento idSala|");
			System.out.println("	-------------------------------");
			System.out.println();
			System.out.println("Sendo que:");
			System.out.println();
			System.out.println("#idEvento ->(String) identificador �nico do evento a ser alocado");
			System.out.println();
			System.out.println("#idSala ->(String) identificador �nico da sala que receber� o evento");
			System.out.println();
			System.out.println("Desalocar um evento---------------------------------------------------------");
			System.out.println();
			System.out.println("Para desalocar um evento alocado, o comando �:");
			System.out.println();
			System.out.println("	---------------------------");
			System.out.println("	|desalocar evento idEvento|");
			System.out.println("	---------------------------");
			System.out.println();
			System.out.println("Sendo que:");
			System.out.println();
			System.out.println("#idEvento -> (String) identificador �nico do evento a ser desalocado");
			System.out.println();
			System.out.println("__________________________________________________________________________");
		}else if(ajuda.equals("localizar")){
			System.out.println("________________________________________________________");
			System.out.println("Ajuda - Localiza��o de atributos");
			System.out.println();
			System.out.println("Localizar um atributo de uma sala-----------------------");
			System.out.println();
			System.out.println("Para localizar algum dos itens de uma sala, o comando �:");
			System.out.println();
			System.out.println("	----------------------------------");
			System.out.println("	|localizar atr-sa atributo idSala|");
			System.out.println("	----------------------------------");
			System.out.println();
			System.out.println("Sendo que:");
			System.out.println();
			System.out.println("#Atributo -> (String) nome do atributo a ser pesquisado.");
			System.out.println("Os atributos v�lidos s�o:");
			System.out.println();
			System.out.println("	*apelido		*capacidade");
			System.out.println("	*finalidade		*aberto (disponibilidade)");
			System.out.println("	*tipo");	
			System.out.println();
			System.out.println("#idSala -> (String) identificador �nico da sala");
			System.out.println();
			System.out.println("Localizar um atributo de um evento-----------------------");
			System.out.println();
			System.out.println("Para localizar um dos itens de um evento, o comando �:");
			System.out.println();
			System.out.println("	------------------------------------");
			System.out.println("	|localizar atr-ev atributo idEvento|");
			System.out.println("	------------------------------------");
			System.out.println();
			System.out.println("Sendo que:");
			System.out.println();
			System.out.println("#Atributo -> (String) nome do atributo a ser pesquisado.");
			System.out.println("Os atributos v�lidos s�o:");
			System.out.println();
			System.out.println("	*nome			*contato");
			System.out.println("	*inicio			*area");
			System.out.println("	*fim			*repeticoes");
			System.out.println();
			System.out.println("#idEvento -> (String) identificador �nico do evento");
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
			System.out.println("O comando para adicionar eventos �:");
			System.out.println();
			System.out.println("	-------------------------------------------------------------------");
			System.out.println("	|adicionar evento idEvento nome inicio fim contato area repeticoes|");
			System.out.println("	-------------------------------------------------------------------");
			System.out.println();
			System.out.println("Sendo que:");
			System.out.println();
			System.out.println("#idEvento -> (String) identificador �nico do evento. O mesmo n�o pode ser igual");
			System.out.println("ao de nenhum outro evento j� cadastrado");
			System.out.println();
			System.out.println("#nome -> (String) nome do evento a ser cadastrado.");
			System.out.println();
			System.out.println("#inicio -> (String) data e hora de in�cio do evento. Deve seguir o formato ");
			System.out.println("dd/mm/aaaa hh:mm, onde dd corresponde ao dia (2 caracteres); mm corresponde ao");
			System.out.println("m�s (2 caracteres); aaaa corresponde ao ano (4 caracteres); hh corresponde a hora");
			System.out.println("(2 caracteres, formato 24 horas) e mm corresponde aos minutos (2 caracteres).");
			System.out.println();
			System.out.println("#fim -> (String) data e hora de t�rmino do evento. Deve seguir o formato ");
			System.out.println("dd/mm/aaaa hh:mm, onde dd corresponde ao dia (2 caracteres); mm corresponde ao");
			System.out.println("m�s (2 caracteres); aaaa corresponde ao ano (4 caracteres); hh corresponde a hora");
			System.out.println("(2 caracteres, formato 24 horas) e mm corresponde aos minutos (2 caracteres).");
			System.out.println();
			System.out.println("#contato -> (String) nome do respons�vel pela organiza��o do evento");
			System.out.println();
			System.out.println("#area -> (String) area ou disciplina de interesse do evento");
			System.out.println();
			System.out.println("#repeticoes -> (Integer) quantidade de vezes que o evento se repetir� naquele mesmo ");
			System.out.println("dia da semana");
			System.out.println();
			System.out.println("Excluir um evento----------------------------------------------------------------------");
			System.out.println();
			System.out.println("Para apagar um evento cadastrado, o comando �:");
			System.out.println();
			System.out.println("	-------------------------");
			System.out.println("	|excluir evento idEvento|");
			System.out.println("	-------------------------");
			System.out.println();
			System.out.println("Sendo que:");
			System.out.println();
			System.out.println("#idEvento: identificador �nico do evento a ser exclu�do");
			System.out.println();
			System.out.println("Lembrando que a exclus�o de um evento remove tamb�m a aloca��o atrelada a ele. Com isso, ");
			System.out.println("a sala ocupada por ele fica dispon�vel novamente.");
			System.out.println();
			System.out.println("Listar eventos-----------------------------------------------------------------------------");
			System.out.println();
			System.out.println("Para listar todos os eventos, com seus respectivos atributos, o comando �:");
			System.out.println();
			System.out.println("	-------------");
			System.out.println("	|ver eventos|");
			System.out.println("	-------------");
			System.out.println();
			System.out.println("Listagem dos eventos n�o realizados");
			System.out.println();
			System.out.println("Para ver somente os eventos que ainda n�o foram realizados, digite:");
			System.out.println();
			System.out.println("	------------------------");	
			System.out.println("	|ver eventos a realizar|");
			System.out.println("	------------------------");
			System.out.println();
			System.out.println("Listagem dos eventos realizados");
			System.out.println();
			System.out.println("Para ver somente os eventos que j� aconteceram, digite:");
			System.out.println();
			System.out.println("	------------------------");	
			System.out.println("	|ver eventos realizados|");
			System.out.println("	------------------------");
			System.out.println("____________________________________________________________________________________________");
		}else{
			System.out.println("Comando de ajuda inv�lido. Digite ajuda (sem atributos) para ver os comandos");
			System.out.println("de ajuda espec�ficos");
		}
	}

}
