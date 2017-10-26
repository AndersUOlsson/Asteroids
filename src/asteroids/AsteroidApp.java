package asteroids;

import java.util.ArrayList;
import java.util.List;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import objects.*;
import view.View;

public class AsteroidApp extends Application {
	
	private Pane root;
	
	
	String backgroundFile ="file:///C://Users//andyw//eclipse-workspace//asteroids//images//space.jpg";
	String gameOverImage = "file:///C://Users//andyw//eclipse-workspace//asteroids//images//spaceGameOver.jpg";
	Image backgroundImage = new Image(backgroundFile);
	View view = new View();
	ImageView backgroudImageView = new ImageView(backgroundImage);
	
	
	Player player = new Player(new Rectangle(40,20, Color.BLUE));
	private List<Bullet> bullets = new ArrayList<>();
	private List<Enemy> enemies = new ArrayList<>(); 
	
	private Parent createContent() {
		
		this.root = new Pane();
		this.root.setPrefSize(1280, 720);
		
		this.root.getChildren().addAll( view.backgroudImageView( backgroundFile));
		
		
		this.player.setVelocity(new Point2D(1,0));
		addGameObjectPlayer(this.player, 300, 300);
		
		AnimationTimer timer = new AnimationTimer() {
			
			@Override
			public void handle(long arg0) {
				
				onUpdate();
			}
		};
		timer.start();
		
		return this.root;
	}
	
	
	private void addBullet(Bullet bullet, double x, double y) {
		this.bullets.add(bullet);
		addGameObject(bullet, x, y);
	}
	
	private void addEnemy(Enemy enemy, double x, double y, double PlayerX, double PlayerY) {
		if(x != PlayerX && y != PlayerY) {
			this.enemies.add(enemy);
			addGameObject(enemy, x, y);
		}
	}
	
	private void addGameObject(GameObject object, double x, double y) {
		object.getView().setTranslateX(x);
		object.getView().setTranslateY(y);
		
		this.root.getChildren().add(object.getView());
	}
	
	
	private void addGameObjectPlayer(Player object, double x, double y) {
		object.getView().setTranslateX(x);
		object.getView().setTranslateY(y);
		
		this.root.getChildren().add(object.getView());
	}
	
	private void onUpdate() {
		
		for(Bullet bullet : bullets) {
			for(Enemy enemy : enemies) {
				if(bullet.isCollidingWithEnemy(enemy)) {
					bullet.setAlive(false);
					enemy.setAlive(false);
					
					this.root.getChildren().removeAll(bullet.getView(), enemy.getView());
				}
			}
		}
		
		
		for(Enemy enemy : enemies) {
			if(this.player.isCollidingWithPlayer(enemy)) {
				player.setAlive(false);
				enemy.setAlive(false);
				
				this.root.getChildren().removeAll(player.getView(), enemy.getView());
				this.root.getChildren().addAll(view.backgroudImageView(gameOverImage));
				Thread tread = new Thread();
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.exit(1);

			}
		}
		
		this.bullets.removeIf(Bullet::isDead);
		this.enemies .removeIf(Enemy::isDead);
		
		this.bullets.forEach(Bullet::update);
		this.enemies.forEach(Enemy::update);
		
		this.player.update();
		
		if(Math.random() < 0.01) {
			addEnemy(new Enemy(new Circle(15,15,15, Color.RED)), Math.random()*this.root.getPrefWidth(), Math.random()*this.root.getPrefHeight(),
					this.player.getView().getLayoutX()+20, this.player.getView().getLayoutY()+20);
		}
	}
	

	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setScene(new Scene(createContent()));
		stage.getScene().setOnKeyPressed(e -> {
			
			if(e.getCode() == KeyCode.LEFT) {
				this.player.left();
			}
			else if(e.getCode() == KeyCode.RIGHT) {
				this.player.right();
			}
			else if(e.getCode() == KeyCode.UP) {
				this.player.up();
				
			}
			else if(e.getCode() == KeyCode.DOWN) {
				this.player.down();

			}
			else if(e.getCode() == KeyCode.SPACE) {
				
				Bullet bullet = new Bullet(new Circle(5,5,5, Color.BROWN));
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
