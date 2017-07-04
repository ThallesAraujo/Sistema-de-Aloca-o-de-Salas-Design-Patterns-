package objetos.comandos;

import exceptions.RoomsAllocationException;
import graphicalui.janelas.JanelaPrincipal;
import graphicalui.objetos.AlertaDeInformacao;
import objetos.Log;
import logica.Logger;
import logica.OperadorAlocacoes;

public class ComandoAlocarEvento implements Comando{
	
	private String idSala;
	private String idEvento;
	
	public ComandoAlocarEvento(String idSala, String idEvento){
		this.idSala = idSala;
		this.idEvento = idEvento;
	}

	@Override
	public void executar() throws Exception {
		try{
			OperadorAlocacoes.alocarGUI(idEvento,idSala);
			Logger.adicionarLog(new Log("Evento "+idEvento+" alocado com sucesso na sala "+idSala+"."));
		}catch(Exception e){
			Logger.adicionarLog(new Log("ERRO: "+e.getMessage()));
			throw new RoomsAllocationException(e.getMessage());
		}
		
	}

	@Override
	public void desfazer() throws Exception {
		try{
			OperadorAlocacoes.desalocar(idEvento);
			JanelaPrincipal.atualizar();
			AlertaDeInformacao alerta = new AlertaDeInformacao("Desfazer ação","Ação desfeita","Evento "+idEvento+" desalocado com sucesso.");
			Logger.adicionarLog(new Log("AÇÃO DESFEITA: Evento "+idEvento+" desalocado com sucesso."));
		}catch(Exception e){
			Logger.adicionarLog(new Log("ERRO: "+e.getMessage()));
			throw new RoomsAllocationException(e.getMessage());
		}
		
	}

}
