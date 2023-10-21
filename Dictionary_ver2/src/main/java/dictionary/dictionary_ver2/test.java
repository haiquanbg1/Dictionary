package dictionary.dictionary_ver2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dictionary.dictionary_ver2.Game.*;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class test extends Application {
    private List<Snake> snake = new ArrayList<Snake>();
    private List<Shape> block = new ArrayList<Shape>();
    private boolean gameOver = false;
    private boolean[] canMove = {true,true,true,true};
    private int width = 20;
    private int height = 20;
    private int currentDirection=0;
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    Apple apple=new Apple(1,1);

    public void start(Stage primaryStage) {
        try {
            VBox root = new VBox();
            Canvas c = new Canvas(width * 25, height * 25);
            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);
            Scene scene = new Scene(root, width * 25, height * 25);
            // control
            scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
                if(e.getCode() == KeyCode.W) {
                    if(currentDirection != DOWN &&canMove[UP]) {
                        currentDirection = UP;
                        run(gc);
                    }
                }
                if(e.getCode() == KeyCode.D) {
                    if(currentDirection != LEFT&&canMove[RIGHT]) {
                        currentDirection = RIGHT;
                        run(gc);
                    }
                }
                if(e.getCode() == KeyCode.S) {
                    if(currentDirection != UP&&canMove[DOWN]) {
                        currentDirection = DOWN;
                        run(gc);
                    }
                }
                if(e.getCode() == KeyCode.A) {
                    if(currentDirection != RIGHT&&canMove[LEFT]) {
                        currentDirection = LEFT;
                        run(gc);
                    }
                }
            });

            // add start snake parts
            snake.add(new Snake(width/2, height/2));
            snake.add(new Snake(width/2, height/2-1));
            snake.add(new Snake(width/2, height/2-2));
            //If you do not want to use css style, you can just delete the next line.
            run(gc);
            run(gc);
            primaryStage.setScene(scene);
            primaryStage.setTitle("SNAKE GAME");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // tick
    public void run(GraphicsContext gc) {
        if(gameOver) {
            return;
        }
        setBackground(gc);
        setFoodIndex(gc);

        for(int i=0;i<4;i++) {
            canMove[i]= true;
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
            case 4:
                break;
        }
        drawSnake(gc);
        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(0).getTopLeftX() == snake.get(i).getTopLeftX()
                    && snake.get(0).getTopLeftY()-1 == snake.get(i).topLeftY) {
                canMove[UP]= false;
            }
            if (snake.get(0).getTopLeftX() == snake.get(i).getTopLeftX()
                    && snake.get(0).getTopLeftY()+1 == snake.get(i).topLeftY) {
                canMove[DOWN]= false;
            }
            if (snake.get(0).getTopLeftX()+1 == snake.get(i).getTopLeftX()
                    && snake.get(0).getTopLeftY() == snake.get(i).topLeftY) {
                canMove[RIGHT]= false;
            }
            if (snake.get(0).getTopLeftX()-1 == snake.get(i).getTopLeftX()
                    && snake.get(0).getTopLeftY() == snake.get(i).topLeftY) {
                canMove[LEFT]= false;
            }
        }

        eatApple();
        // snake

    }

    private void setBackground(GraphicsContext gc) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("AAD751"));
                } else {
                    gc.setFill(Color.web("A2D149"));
                }
                gc.fillRect(i * 25, j * 25, 25, 25);
            }
        }
    }

    private void setFoodIndex(GraphicsContext gc) {
        Color cc = Color.WHITE;
        gc.setFill(cc);
        gc.fillOval(apple.topLeftX * 25, apple.topLeftY * 25, 25, 25);
    }

    private void drawSnake(GraphicsContext gc) {
        for (Shape c : snake) {
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(c.topLeftX * 25, c.topLeftY * 25, 25 - 1, 25 - 1);
            gc.setFill(Color.GREEN);
            gc.fillRect(c.topLeftX * 25, c.topLeftY * 25, 25 - 2, 25 - 2);
        }
    }
    private boolean eatApple() {
        if (apple.topLeftX == snake.get(0).topLeftX && apple.topLeftY == snake.get(0).topLeftY) {
            snake.add(new Snake(-1, -1));
            apple.topLeftY=10;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}