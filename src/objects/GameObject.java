package objects;

import javafx.geometry.Point2D;
import javafx.scene.Node;

public class GameObject {
	
	private double x;
	private double y;
	private double velX = 0;
	private double velY = 0;
	
	protected Node view;
	private Point2D velocity = new Point2D(0,0);
	private boolean alive = true;
	 
	public GameObject(Node view) {
		this.view = view;
	}
	
	
	public void update() {
		this.view.setTranslateX(this.view.getTranslateX() );
		this.view.setTranslateY(this.view.getTranslateY());
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
	
	

	public void right() {
		this.view.setTranslateX(this.view.getTranslateX() + 5); 
		setVelocity(new Point2D(this.view.getTranslateX(), this.view.getTranslateY()));
		System.out.println(getVelocity());
	
		
		
	}
	
	public void left() {
		this.view.setTranslateX(this.view.getTranslateX() - 5); 
		setVelocity(new Point2D(this.view.getTranslateX(), this.view.getTranslateY()));
	}
	
	public void up() {
		
		this.view.setTranslateY(this.view.getTranslateY() - 5); 
		setVelocity(new Point2D(this.view.getTranslateX(), this.view.getTranslateY()));
		
		
	}
	
	public void down() {
		this.view.setTranslateY(this.view.getTranslateY() + 5); 
		setVelocity(new Point2D(this.view.getTranslateX(), this.view.getTranslateY()));
	}

	
}
