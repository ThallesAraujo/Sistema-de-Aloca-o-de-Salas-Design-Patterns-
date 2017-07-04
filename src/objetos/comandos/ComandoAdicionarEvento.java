package objetos.comandos;

import exceptions.RoomsAllocationException;
import graphicalui.janelas.JanelaPrincipal;
import graphicalui.objetos.AlertaDeErro;
import graphicalui.objetos.AlertaDeInformacao;
import logica.Logger;
import logica.OperadorEventos;
import objetos.Evento;
import objetos.Log;

public class ComandoAdicionarEvento implements Comando{

	private Evento evento;

	public ComandoAdicionarEvento(Evento evento){
		this.evento = evento;
	}

	@Override
	public void executar() throws Exception{
		try{
			OperadorEventos.salvar(evento);
			Logger.adicionarLog(new Log("Evento "+evento.getId()+" salvo com sucesso."));
		}catch(Exception e){
			this.evento = null;
			Logger.adicionarLog(new Log("ERRO: "+e.getMessage()));
			throw new RoomsAllocationException(e.getMessage());
		}

	}

	@Override
	public void desfazer() throws Exception {
		if(this.evento!=null){
			try{
				OperadorEventos.excluir(evento.getId());
				JanelaPrincipal.atualizar();
				AlertaDeInformacao alerta = new AlertaDeInformacao("Desfazer ação","Ação desfeita","Evento "+evento.getId()+" apagado com sucesso.");
				Logger.adicionarLog(new Log("AÇÃO DESFEITA: Evento "+evento.getId()+" apagado com sucesso."));
			}catch(Exception e){
				Logger.adicionarLog(new Log("ERRO: "+e.getMessage()));
				throw new RoomsAllocationException(e.getMessage());
			}
		}else{
			AlertaDeErro alerta = new AlertaDeErro("Desfazer ação","Comando vazio","Não há nada há ser desfeito porque o comando é nulo");
		}

	}



}
