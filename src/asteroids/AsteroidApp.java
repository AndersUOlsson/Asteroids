package asteroids;

import java.util.ArrayList;
import java.util.List;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AsteroidApp extends Application {
	
	private Pane root;
	private GameObject player;
	private List<GameObject> bullets = new ArrayList<>();
	private List<GameObject> enemies = new ArrayList<>(); 
	
	private Parent createContent() {
		this.root = new Pane();
		this.root.setPrefSize(600, 400);
		
		this.player = new Player();
		this.player.setVelocity(new Point2D(1,0));
		addGameObject(player, 300, 300);
		
		
		
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				
				onUpdate();
			}
		};
		timer.start();
		
		return this.root;
	}
	
	private void addBullet(GameObject bullet, double x, double y) {
		this.bullets.add(bullet);
		addGameObject(bullet, x, y);
	}
	
	private void addEnemy(GameObject enemy, double x, double y) {
		this.enemies.add(enemy);
		addGameObject(enemy, x, y);
	}
	
	private void addGameObject(GameObject object, double x, double y) {
		object.getView().setTranslateX(x);
		object.getView().setTranslateY(y);
		
		this.root.getChildren().add(object.getView());
	}
	
	private void onUpdate() {
		
		for(GameObject bullet : bullets) {
			for(GameObject enemy : enemies) {
				if(bullet.isCollidings(enemy)) {
					bullet.setAlive(false);
					enemy.setAlive(false);
					
					this.root.getChildren().removeAll(bullet.getView(), enemy.getView());
				}
			}
		}
		
		
		for(GameObject enemy : enemies) {
			if(enemy.isCollidings(this.player)) {
				this.player.setAlive(false);
				enemy.setAlive(false);
				
				this.root.getChildren().removeAll(this.player.getView(), enemy.getView());
			}
		}
		
		this.bullets.removeIf(GameObject::isDead);
		this.enemies .removeIf(GameObject::isDead);
		
		this.bullets.forEach(GameObject::update);
		this.enemies.forEach(GameObject::update);
		
		this.player.update();
		
		if(Math.random() < 0.02) {
			addEnemy(new Enemy(), Math.random()*this.root.getPrefWidth(), Math.random()*this.root.getPrefHeight());
		}
	}
	
	private static class Player extends GameObject {
		
		Player() {
			super(new Rectangle(40,20, Color.BLUE));
		}
	}
	
	private static class Enemy extends GameObject {
		
		Enemy() {
			super(new Circle(15,15,15, Color.RED));
		}
	}

	private static class Bullet extends GameObject {
	
		Bullet() {
		super(new Circle(5,5,5, Color.BROWN));
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setScene(new Scene(createContent()));
		stage.getScene().setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.LEFT) {
				this.player.rotateLeft();
			}
			else if(e.getCode() == KeyCode.RIGHT) {
				this.player.rotateRight();
			}
			else if(e.getCode() == KeyCode.SPACE) {
				Bullet bullet = new Bullet();
				bullet.setVelocity(this.player.getVelocity().normalize().multiply(5));
				addBullet(bullet,this.player.getView().getTranslateX(), this.player.getView().getTranslateY());
			}
		});
		stage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
