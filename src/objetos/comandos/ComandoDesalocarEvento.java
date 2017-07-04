package objetos.comandos;

import exceptions.RoomsAllocationException;
import graphicalui.janelas.JanelaPrincipal;
import graphicalui.objetos.AlertaDeInformacao;
import logica.Logger;
import logica.OperadorAlocacoes;
import objetos.Alocacao;
import objetos.Log;

public class ComandoDesalocarEvento implements Comando{
	
	private String idEvento;
	private Alocacao alocacao;

	public ComandoDesalocarEvento(String idEvento){
		this.idEvento = idEvento;
	}
	
	@Override
	public void executar() throws Exception {
		
		try{
			alocacao = OperadorAlocacoes.getAlocacao(idEvento);
			OperadorAlocacoes.desalocar(idEvento);
			Logger.adicionarLog(new Log("Evento "+idEvento+" desalocado com sucesso."));
		}catch(Exception e){
			Logger.adicionarLog(new Log("ERRO: "+e.getMessage()));
			throw new RoomsAllocationException(e.getMessage());
		}
		
	}

	@Override
	public void desfazer() throws Exception {
		
		try{
			OperadorAlocacoes.alocar(alocacao.getIdEvento(), alocacao.getIdSala());
			JanelaPrincipal.atualizar();
			AlertaDeInformacao alerta = new AlertaDeInformacao("Desfazer ação","Ação desfeita","Evento "+idEvento+" realocado com sucesso na sala "+alocacao.getIdSala()+".");
			Logger.adicionarLog(new Log("AÇÃO DESFEITA: Evento "+idEvento+" realocado com sucesso na sala "+alocacao.getIdSala()+"."));
		}catch(Exception e){
			Logger.adicionarLog(new Log("ERRO: "+e.getMessage()));
			throw new RoomsAllocationException(e.getMessage());
		}
		
	}
	
	

}
