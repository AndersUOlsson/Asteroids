package objects;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Enemy extends GameObject {

	public Enemy(Node view) {
		super(new Circle(15,15,15, Color.RED));

	}
}
