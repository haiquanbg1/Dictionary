package dictionary.dictionary_ver2.Controllers;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    AnimationTimer gameLoop;

    @FXML
    private AnchorPane plane;
    @FXML
    private AnchorPane background;
    @FXML
    private Rectangle bird;
    @FXML
    private Rectangle top;
    @FXML
    private Rectangle mid;
    @FXML
    private Rectangle bot;

    double yDelta = 0.02 ;
    double time = 0;
    int jumpHeight = 50;
    boolean isFly = false;
    int count = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        load();

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };
        plane.setOnMouseClicked(event -> {
            count = 0;
            isFly = true;
        });
        gameLoop.start();
    }

    //Called every game frame
    private void update() {
        if (!isFly) {
            time++;
            moveBirdY(yDelta * time * 5);
        } else {
            if (count < jumpHeight) {
                moveBirdY(yDelta * jumpHeight * -3);
                count += yDelta * jumpHeight * 3;
            } else {
                time = 0;
                isFly = false;
            }
        }

        moveWallX(yDelta * 100);

        if (isOut()) {
            resetWall();
        }

        System.out.println(top.getX());

        if(isBirdDead()){
            resetBird();
        }
    }

    //Everything called once, at the game start
    private void load() {
        System.out.println("Game starting");
//        createWall();
    }

    public void moveBirdY(double positionChange) {
        bird.setY(bird.getY() + positionChange);
    }

    public void moveWallX(double positionChange) {
        top.setX(top.getX() - positionChange);
        mid.setX(mid.getX() - positionChange);
        bot.setX(bot.getX() - positionChange);
    }

    private boolean isBirdDead() {
        double birdY = bird.getLayoutY() + bird.getY();
        return birdY >= background.getLayoutY() + background.getHeight();
    }

    private boolean isOut() {
        if (top.getX() == 0) {
            return true;
        }
        return false;
    }

    private void resetBird() {
        bird.setY(63);
        time = 0;
    }

    private void resetWall() {
        top.setX(672);
        mid.setX(672);
        bot.setX(672);
    }

    private boolean checkCollision(Rectangle shape1, Rectangle shape2) {
        if (shape1.getX() > shape2.getX() + shape2.getWidth()) {
            return false;
        }

        if (shape1.getX() + shape1.getWidth() < shape2.getX()) {
            return false;
        }

        if (shape1.getY() > shape2.getY() + shape2.getHeight()) {
            return false;
        }

        if (shape1.getY() + shape1.getHeight() < shape2.getY()) {
            return false;
        }

        return true;
    }
}

