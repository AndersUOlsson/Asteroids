package objects;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends GameObject {


	
	public Player(Node view) {
		super(new Rectangle(40,20, Color.BLUE));
	}
	
	public boolean isCollidingWithPlayer(Enemy enemy) {
		
		return this.getView().getBoundsInParent().intersects(enemy.getView().getBoundsInParent());
	}
}
