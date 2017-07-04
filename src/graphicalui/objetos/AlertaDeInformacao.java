package graphicalui.objetos;

import java.awt.Toolkit;

import javafx.scene.control.Alert;

/**
 * Classe dos alertas de informação exibidos pelo programa.
 * @author Thalles
 *
 */
public class AlertaDeInformacao extends Alert{

	public AlertaDeInformacao(String titulo, String cabecalho, String conteudo) {
		super(AlertType.INFORMATION);
		this.setTitle(titulo);
		this.setHeaderText(cabecalho);
		this.setContentText(conteudo);
		final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.asterisk");
		if(runnable!=null){
			runnable.run();
		}
		this.showAndWait();
	}
	
	

}
