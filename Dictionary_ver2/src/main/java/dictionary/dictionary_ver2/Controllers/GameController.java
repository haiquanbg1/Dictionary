package dictionary.dictionary_ver2.Controllers;


import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;


import java.net.URL;
import java.util.ResourceBundle;


public class GameController implements Initializable {
    @FXML
    private Rectangle bird=new Rectangle();
    @FXML
    private TextArea question = new TextArea();
    @FXML
    private Label answer1 ;
    @FXML
    private Label answer2 ;
    @FXML
    private Label answer3 ;
    private double gravity=0.05;
    int jumpHeight=80;
    private double timer=0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bird.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode()==KeyCode.SPACE || event.getCode()==KeyCode.W) {
                    updateBirdIndex(-jumpHeight);
                    timer= 0;
                }
            }
        });
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                freeFall();
            }
        };
        gameLoop.start();
        question.setText("The youth international conference _______ by a lot of young people from around the world ");
        answer1.setText("was attending");
        answer2.setText("was attended");
        answer3.setText("attends");

    }

    private void flew() {
        /*if(bird.getLayoutY() + bird.getY() <= jumpHeight){
            updateBirdIndex(-(bird.getLayoutY() + bird.getY()));
            timer = 0;
            return;
        }*/
        updateBirdIndex(-jumpHeight);
        timer= 0;
    }
    private void freeFall() {
        timer++;
        updateBirdIndex(timer*gravity);

    }
    private void updateBirdIndex(double changed) {
        bird.setY(bird.getY()+changed);
    }
}