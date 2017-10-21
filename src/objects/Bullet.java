package objects;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet extends GameObject{

	public Bullet(Node view) {
		super(new Circle(5,5,5, Color.BROWN));
	}

	public boolean isCollidingWithEnemy(Enemy enemy) {
		
		return this.getView().getBoundsInParent().intersects(enemy.getView().getBoundsInParent());
	}

}
