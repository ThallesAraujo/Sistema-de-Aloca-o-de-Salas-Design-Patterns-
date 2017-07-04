package graphicalui.objetos;

import java.awt.Toolkit;

import javafx.scene.control.Alert;

/**
 * Classe dos alertas de erro exibidos pelo programa.
 * @author Thalles
 *
 */
public class AlertaDeErro extends Alert{

	public AlertaDeErro(String titulo, String cabecalho, String conteudo) {
		super(AlertType.ERROR);
		this.setTitle(titulo);
		this.setHeaderText(cabecalho);
		this.setContentText(conteudo);
		
		final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.hand");
		if(runnable!=null){
			runnable.run();
		}
		this.showAndWait();
		
	}
	

}
