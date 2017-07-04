package graphicalui.janelas;

import graphicalui.objetos.AlertaDeErro;
import graphicalui.objetos.AlertaDeInformacao;
import logica.OperadorSalas;
import objetos.Sala;
import objetos.comandos.ComandoAdicionarSala;
import objetos.comandos.Invoker;

public class AdicionarEscritorio extends AdicionarSala{

	public AdicionarEscritorio() {
		super("Escrit�rio");
	}

	@Override
	public void salvamento() {
		try {
			Invoker.adicionarEExecutar(new ComandoAdicionarSala(new Sala(super.tfCodigo.getText(),super.tfApelido.getText(),Integer.parseInt(super.tfCapacidade.getText()),"Escritorio","Sem tipo","nao")));
			super.mainStage.close();
			JanelaPrincipal.atualizar();
			AlertaDeInformacao alerta = new AlertaDeInformacao("Salvar escrit�rio","Escrit�rio salvo","O escritorio foi salvo com sucesso");
		} catch (NumberFormatException e) {
			AlertaDeErro alerta = new AlertaDeErro("Formato incorreto","Capacidade inv�lida","A capacidade deve ser do tipo num�rico");
		} catch (Exception e) {
			AlertaDeErro alerta = new AlertaDeErro("Salvar escritorio","Houve um erro:",e.getMessage());
		}
		
	}
	

}
