package objetos.comandos;

import exceptions.RoomsAllocationException;
import graphicalui.janelas.JanelaPrincipal;
import graphicalui.objetos.AlertaDeInformacao;
import logica.Logger;
import logica.OperadorSalas;
import objetos.Log;
import objetos.Sala;

public class ComandoRemoverSala implements Comando{
	
	private String idSala;
	private Sala sala;

	public ComandoRemoverSala(String idSala){
		this.idSala = idSala;
	}
	
	@Override
	public void executar() throws Exception {
		
		try{
			sala = OperadorSalas.getSala(idSala);
			OperadorSalas.excluir(idSala);
			Logger.adicionarLog(new Log("Sala "+idSala+" apagada com sucesso."));
		}catch(Exception e){
			Logger.adicionarLog(new Log("ERRO: "+e.getMessage()));
			throw new RoomsAllocationException(e.getMessage());
		}
	}

	@Override
	public void desfazer() throws Exception {
		
		try{
			OperadorSalas.salvar(sala);
			JanelaPrincipal.atualizar();
			AlertaDeInformacao alerta = new AlertaDeInformacao("Desfazer a��o","A��o desfeita","Sala "+idSala+" reintegrada com sucesso.");
			Logger.adicionarLog(new Log("A��O DESFEITA: Sala "+idSala+" reintegrada com sucesso."));
		}catch(Exception e){
			Logger.adicionarLog(new Log("ERRO: "+e.getMessage()));
			throw new RoomsAllocationException(e.getMessage());
		}
		
	}
	

}
