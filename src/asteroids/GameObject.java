package asteroids;

import javafx.geometry.Point2D;
import javafx.scene.Node;

public class GameObject {
	
	private Node view;
	private Point2D velocity = new Point2D(0,0);
	private boolean alive = true;
	
	public GameObject(Node view) {
		this.view = view;
	}
	
	public void update() {
		this.view.setTranslateX(this.view.getTranslateX() + this.velocity.getX());
		this.view.setTranslateY(this.view.getTranslateY() + this.velocity.getY());
	}
	
	public void setVelocity(Point2D velocity) {
		this.velocity = velocity;
	}
	
	public Node getView() {
		return this.view;
	}
	
	public Point2D getVelocity() {
		return this.velocity;
	}
	
	public boolean isAlive() {
		return this.alive;
	}
	
	public boolean isDead() {
		return !this.alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public double getRotate() {
		return this.view.getRotate();
	}
	
	public void rotateRight() {
		this.view.setRotate(this.view.getRotate() + 10);
		setVelocity(new Point2D(Math.toRadians(getRotate()),Math.sin(Math.toRadians(getRotate()))));
	}
	
	public void rotateLeft() {
		this.view.setRotate(this.view.getRotate() - 10);
		setVelocity(new Point2D(Math.toRadians(getRotate()),Math.sin(Math.toRadians(getRotate()))));
	}
	
	
	public boolean isCollidings(GameObject other) {
		
		return this.getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
	}
	
}
