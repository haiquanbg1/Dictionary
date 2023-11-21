package dictionary.dictionary_ver2.Controllers;

import dictionary.dictionary_ver2.Main;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
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

    private final double yDelta = 0.02 ;
    private double time;
    private int jumpHeight;
    private boolean isFly;
    private double count;

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
    private void update(){
        if (!isFly) {
            time++;
            moveBirdY(yDelta * time);
        } else {
            if (count < jumpHeight && bird.getY() + bird.getLayoutY() > 0) {
                moveBirdY(yDelta * jumpHeight * -2);
                count += yDelta * jumpHeight * 2;
            } else {
                time = 0;
                isFly = false;
            }
        }

        moveWallX(yDelta * 30);

        if (isOut()) {
            resetWall();
        }

        if(isBirdDead()){
            resetBird();
            resetWall();
        }
    }

    //Everything called once, at the game start
    private void load() {
        time = 0;
        jumpHeight = 40;
        isFly = false;
        count = 0;

        Image imageBird = new Image(getClass().getResource("/Images/yellowbird-midflap.png").toString());
        bird.setFill(new ImagePattern(imageBird));
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
        double birdY = bird.getY() + bird.getWidth() + bird.getLayoutY();
        return birdY >= 380
                || checkCollision(bird, top)
                || checkCollision(bird, mid)
                || checkCollision(bird, bot);
    }

    private boolean isOut() {
        if (top.getX() <= 0) {
            return true;
        }
        return false;
    }

    private boolean isEndGame() {
        return true;
    }

    private void resetBird() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        bird.setY(63);
        time = 0;
    }

    private void resetWall() {
        top.setX(672);
        mid.setX(672);
        bot.setX(672);
    }

    private boolean checkCollision(Rectangle shape1, Rectangle shape2) {
        if (shape1.getX() + 5 > shape2.getX() + shape2.getWidth()) {
            return false;
        }

        if (shape1.getX() + shape1.getWidth() - 5 < shape2.getX()) {
            return false;
        }

        if (shape1.getY() + 5 > shape2.getY() + shape2.getHeight()) {
            return false;
        }

        if (shape1.getY() + shape1.getHeight() - 5 < shape2.getY()) {
            return false;
        }

        return true;
    }
}

