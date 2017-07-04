package objetos.comandos;

import exceptions.RoomsAllocationException;
import graphicalui.janelas.JanelaPrincipal;
import graphicalui.objetos.AlertaDeErro;
import graphicalui.objetos.AlertaDeInformacao;
import logica.Logger;
import logica.OperadorSalas;
import objetos.Log;
import objetos.Sala;

public class ComandoAdicionarSala implements Comando{

	private Sala sala;

	public ComandoAdicionarSala(Sala sala){
		this.sala = sala;
	}

	@Override
	public void executar() throws Exception {
		try{
			OperadorSalas.salvar(sala);
			Logger.adicionarLog(new Log("Sala "+sala.getId()+" salva com sucesso."));
		}catch(Exception e){
			this.sala = null;
			Logger.adicionarLog(new Log("ERRO: "+e.getMessage()));
			throw new RoomsAllocationException(e.getMessage());
		}
	}

	@Override
	public void desfazer() throws Exception {
		if(sala!=null){
			try{
				OperadorSalas.excluir(sala.getId());
				JanelaPrincipal.atualizar();
				AlertaDeInformacao alerta = new AlertaDeInformacao("Desfazer a��o","A��o desfeita","Sala "+sala.getId()+" apagada com sucesso.");
				Logger.adicionarLog(new Log("A��O DESFEITA: Sala "+sala.getId()+" apagada com sucesso."));
			}catch(Exception e){
				Logger.adicionarLog(new Log("ERRO: "+e.getMessage()));
				throw new RoomsAllocationException(e.getMessage());
			}
		}else{
			AlertaDeErro alerta = new AlertaDeErro("Desfazer a��o","Comando vazio","N�o h� nada h� ser desfeito porque o comando � nulo");
		}
	}



}
