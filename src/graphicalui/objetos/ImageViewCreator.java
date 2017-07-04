package graphicalui.objetos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Classe de cria��o das ImageViews (�cones) utilizados no programa.
 * @author Thalles
 *
 */
public class ImageViewCreator {

	/**
	 * M�todo de cria��o de �cones.
	 * @param address Endere�o (URL) da imagem a ser utilizada para a cria��o do �cone.
	 * @return �cone (ImageView) pronto para uso.
	 */
	public static ImageView create(String address){
		Image image = new Image(address.getClass().getResourceAsStream(address));
		ImageView imageView = new ImageView(image);
		return imageView;
		
	}
	
}
