package objetos.comandos;

import exceptions.RoomsAllocationException;
import graphicalui.janelas.JanelaPrincipal;
import graphicalui.objetos.AlertaDeInformacao;
import logica.Logger;
import logica.OperadorEventos;
import objetos.Evento;
import objetos.Log;

public class ComandoRemoverEvento implements Comando{
	
	private String idEvento;
	private Evento evento;
	
	public ComandoRemoverEvento(String idEvento){
		this.idEvento = idEvento;
	}

	@Override
	public void executar() throws Exception {
		
		try{
			evento = OperadorEventos.getEvento(idEvento);
			OperadorEventos.excluir(idEvento);
			Logger.adicionarLog(new Log("Evento "+evento.getId()+" apagado com sucesso."));
		}catch(Exception e){
			Logger.adicionarLog(new Log("ERRO: "+e.getMessage()));
			throw new RoomsAllocationException(e.getMessage());
		}
		
	}

	@Override
	public void desfazer() throws Exception {
		
		try{
			OperadorEventos.salvar(evento);
			JanelaPrincipal.atualizar();
			AlertaDeInformacao alerta = new AlertaDeInformacao("Desfazer Ação","Ação desfeita","Evento "+evento.getId()+" reintegrado com sucesso.");
			Logger.adicionarLog(new Log("AÇÃO DESFEITA: Evento "+evento.getId()+" reintegrado com sucesso."));
		}catch(Exception e){
			Logger.adicionarLog(new Log("ERRO: "+e.getMessage()));
			throw new RoomsAllocationException(e.getMessage());
		}
		
	}
	
	

}
