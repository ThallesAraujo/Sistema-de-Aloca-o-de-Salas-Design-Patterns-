package graphicalui.janelas;

import graphicalui.objetos.AlertaDeErro;
import graphicalui.objetos.AlertaDeInformacao;
import logica.OperadorSalas;
import objetos.Sala;
import objetos.comandos.ComandoAdicionarSala;
import objetos.comandos.Invoker;

public class AdicionarEscritorio extends AdicionarSala{

	public AdicionarEscritorio() {
		super("Escritório");
	}

	@Override
	public void salvamento() {
		try {
			Invoker.adicionarEExecutar(new ComandoAdicionarSala(new Sala(super.tfCodigo.getText(),super.tfApelido.getText(),Integer.parseInt(super.tfCapacidade.getText()),"Escritorio","Sem tipo","nao")));
			super.mainStage.close();
			JanelaPrincipal.atualizar();
			AlertaDeInformacao alerta = new AlertaDeInformacao("Salvar escritório","Escritório salvo","O escritorio foi salvo com sucesso");
		} catch (NumberFormatException e) {
			AlertaDeErro alerta = new AlertaDeErro("Formato incorreto","Capacidade inválida","A capacidade deve ser do tipo numérico");
		} catch (Exception e) {
			AlertaDeErro alerta = new AlertaDeErro("Salvar escritorio","Houve um erro:",e.getMessage());
		}
		
	}
	

}
