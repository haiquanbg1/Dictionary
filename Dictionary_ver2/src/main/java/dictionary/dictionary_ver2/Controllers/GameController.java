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
    private Rectangle bird;

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
                moveBirdY(yDelta * jumpHeight * -5);
                count += yDelta * jumpHeight * 5;
            } else {
                time = 0;
                isFly = false;
            }
        }

        if(isBirdDead()){
            resetBird();
        }
    }

    //Everything called once, at the game start
    private void load(){
        System.out.println("Game starting");

    }

    private void moveBirdY(double positionChange){
        bird.setY(bird.getY() + positionChange);
    }

    private boolean isBirdDead(){
        double birdY = bird.getLayoutY() + bird.getY();
        return birdY >= plane.getHeight();
    }

    private void resetBird(){
        bird.setY(0);
        time = 0;
    }
}
