package graphicalui.janelas;

import graphicalui.objetos.AlertaDeErro;
import graphicalui.objetos.AlertaDeInformacao;
import logica.OperadorSalas;
import objetos.Sala;
import objetos.comandos.ComandoAdicionarSala;
import objetos.comandos.Invoker;

/**
 * Classe da janela de cadastro de salas de videoconfer�ncia.
 * (Padr�o Strategy: classe concreta de estrat�gia)
 * (Padr�o Template Method: implementa��o do m�todo gabarito)
 * @author Thalles
 *
 */
public class AdicionarSalaVideoconferencia extends AdicionarSala{

	public AdicionarSalaVideoconferencia() {
		super("Videoconfer�ncia");
	}

	@Override
	public void salvamento() {
		try {
			Invoker.adicionarEExecutar(new ComandoAdicionarSala(new Sala(super.tfCodigo.getText(),super.tfApelido.getText(),Integer.parseInt(super.tfCapacidade.getText()),"Sala de Conferencia","Videoconferencia","sim")));
			super.mainStage.close();
			JanelaPrincipal.atualizar();
			AlertaDeInformacao alerta = new AlertaDeInformacao("Salvar sala","Sala salva com sucesso","A sala foi salva com sucesso");
		} catch (NumberFormatException e) {
			AlertaDeErro alerta = new AlertaDeErro("Formato incorreto","Capacidade inv�lida","A capacidade deve ser do tipo num�rico");
		} catch (Exception e) {
			AlertaDeErro alerta = new AlertaDeErro("Salvar sala","Houve um erro:",e.getMessage());
		}
		
	}
	
	
	
	

}
