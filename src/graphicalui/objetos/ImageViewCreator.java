package graphicalui.objetos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Classe de criação das ImageViews (ícones) utilizados no programa.
 * @author Thalles
 *
 */
public class ImageViewCreator {

	/**
	 * Método de criação de ícones.
	 * @param address Endereço (URL) da imagem a ser utilizada para a criação do ícone.
	 * @return Ícone (ImageView) pronto para uso.
	 */
	public static ImageView create(String address){
		Image image = new Image(address.getClass().getResourceAsStream(address));
		ImageView imageView = new ImageView(image);
		return imageView;
		
	}
	
}
