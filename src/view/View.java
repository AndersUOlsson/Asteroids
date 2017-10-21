package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class View {
	
	
	public ImageView backgroudImageView(String backgroundFile) {
		Image backgroundImage = new Image(backgroundFile);
		ImageView backgroudImageView = new ImageView(backgroundImage);
		
		return backgroudImageView;
	}
	

}
