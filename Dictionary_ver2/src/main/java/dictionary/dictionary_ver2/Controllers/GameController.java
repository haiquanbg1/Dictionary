package dictionary.dictionary_ver2.Controllers;

import dictionary.dictionary_ver2.Game.Apple;
import dictionary.dictionary_ver2.Game.Shape;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.skin.TextInputControlSkin;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private List<Shape> snake = new ArrayList<Shape>();
    private boolean gameOver = false;
    private boolean canMove = true;
    private int width = 20;
    private int height = 20;
    private int currentDirection;
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    Apple apple=new Apple(1,1);

    @FXML
    VBox vbox = new VBox();
    @FXML
    Canvas canvas = new Canvas();


    Scene scene = new Scene(vbox);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GraphicsContext gc=canvas.getGraphicsContext2D();
        snake.add(new Shape(width/2, height/2));
        snake.add(new Shape(width/2, height/2));
        snake.add(new Shape(width/2, height/2));
        vbox.addEventHandler( KeyEvent.KEY_PRESSED,e->{
            if(e.getCode() == KeyCode.W) {
                if(currentDirection != DOWN) {
                    currentDirection = UP;
                    run(gc);
                }
            }
            if(e.getCode() == KeyCode.D) {
                if(currentDirection != LEFT) {
                    currentDirection = RIGHT;
                    run(gc);
                }
            }
            if(e.getCode() == KeyCode.S) {
                if(currentDirection != UP) {
                    currentDirection = DOWN;
                    run(gc);
                }
            }
            if(e.getCode() == KeyCode.A) {
                if(currentDirection != RIGHT) {
                    currentDirection = LEFT;
                    run(gc);
                }
            }
        });
        /*Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300), e -> run(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();*/
        run(gc);

    }

   /* @FXML
    public void move(KeyEvent e) {
        if(e.getCode() == KeyCode.W) {
            if(currentDirection != DOWN) {
                currentDirection = UP;
            }
        }
        if(e.getCode() == KeyCode.D) {
            if(currentDirection != LEFT) {
                currentDirection = RIGHT;
            }
        }
        if(e.getCode() == KeyCode.S) {
            if(currentDirection != UP) {
                currentDirection = DOWN;
            }
        }
        if(e.getCode() == KeyCode.A) {
            if(currentDirection != RIGHT) {
                currentDirection = LEFT;
            }
        }
    }*/
    public void run(GraphicsContext gc) {
        if(gameOver) {
            return;
        }
        for(int i=snake.size()-1; i>=1; i--) {
            snake.get(i).topLeftX = snake.get(i-1).topLeftX;
            snake.get(i).topLeftY = snake.get(i-1).topLeftY;
        }
        switch (currentDirection) {
            case RIGHT:
                snake.get(0).topLeftX ++;
                break;
            case LEFT:
                snake.get(0).topLeftX --;
                break;
            case UP:
                snake.get(0).topLeftY --;
                break;
            case DOWN:
                snake.get(0).topLeftY ++;
                break;
        }
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width * 25, height * 25);

        Color cc = Color.WHITE;
        gc.setFill(cc);
        gc.fillOval(apple.topLeftX * 25, apple.topLeftY * 25, 25, 25);

        // snake
        for (Shape c : snake) {
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(c.topLeftX * 25, c.topLeftY * 25, 25 - 1, 25 - 1);
            gc.setFill(Color.GREEN);
            gc.fillRect(c.topLeftX * 25, c.topLeftY * 25, 25 - 2, 25 - 2);
        }
    }
}
