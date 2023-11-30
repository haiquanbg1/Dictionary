package dictionary.dictionary_ver2.Controllers;

import dictionary.dictionary_ver2.Game.*;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import java.sql.*;
import dictionary.dictionary_ver2.Database.*;

public class GameDemo implements Initializable {
    private AnimationTimer gameLoop;

    @FXML
    private AnchorPane plane;
    @FXML
    private Rectangle birdRec;
    @FXML
    private Rectangle top;
    @FXML
    private Rectangle mid1;
    @FXML
    private Rectangle mid2;
    @FXML
    private Rectangle bot;
    @FXML
    private TextArea question;
    @FXML
    private StackPane a;
    @FXML
    private StackPane b;
    @FXML
    private StackPane c;
    @FXML
    private Label alert;
    
    private Bird bird;
    private Wall wall1;
    private Wall wall2;
    private Wall wall3;
    private Wall wall4;
    private Answer ans1;
    private Answer ans2;
    private Answer ans3;

    private List<Data> dataList = new ArrayList<>();
    private String ans;
    private boolean isCollide;
    private boolean isBirdDie;

    public GameDemo() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            load();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };
        plane.setOnMouseClicked(event -> {
            bird.setCountDistanceJump(0);
            bird.setFly(true);
        });
        gameLoop.start();
    }

    //Called every game frame
    private void update(){
        if (isBirdDie) {
            isBirdDie = false;
            resetBird();
            resetWall();
            takeQuestion();
            alert.setText("");
        }

        takeAnswer();

        bird.fly();
        birdRec.setY(bird.getTopLeftY());

        wall1.move();
        wall2.move();
        wall3.move();
        wall4.move();
        ans1.move();
        ans2.move();
        ans3.move();

        if (wall1.checkOut()) {
            resetWall();
        }

        moveWallX(wall1.getTopLeftX());

        checkBirdDead();

        if(bird.isDead()){
            alert.setText("bird Dead");
            isBirdDie = true;
        } else {
            if (isCollide) {
                alert.setText("Correct!");
            } else {
                if (!isBirdDie) {
                    alert.setText("");
                }
            }
        }
    }

    //Everything called once, at the game start
    private void load() throws SQLException {
        isCollide = false;
        isBirdDie = false;
        
        bird = new Bird(birdRec.getX(), birdRec.getY(), birdRec.getWidth(), birdRec.getHeight(),
                "/Images/yellowbird-midflap.png");

        Image imageBird = new Image(getClass().getResource(bird.getImage()).toString());
        birdRec.setFill(new ImagePattern(imageBird));

        wall1 = new Wall(top.getX(), top.getY(), top.getWidth(), top.getHeight(),
                "/Images/bot-pipe.png");
        wall2 = new Wall(mid1.getX(), mid1.getY(), mid1.getWidth(), mid1.getHeight(),
                "/Images/dual-pipe.jpg");
        wall3 = new Wall(mid2.getX(), mid2.getY(), mid2.getWidth(), mid2.getHeight(),
                "/Images/dual-pipe.jpg");
        wall4 = new Wall(bot.getX(), bot.getY(), bot.getWidth(), bot.getHeight(),
                "/Images/top-pipe.png");
        ans1 = new Answer(a.getLayoutX(), a.getLayoutY(), 40.4, 55.2);
        ans1.setAns("a");
        ans2 = new Answer(b.getLayoutX(), b.getLayoutY(), 40.4, 55.2);
        ans2.setAns("b");
        ans3 = new Answer(c.getLayoutX(), c.getLayoutY(), 40.4, 50.4);
        ans3.setAns("c");

        Image imagePipe = new Image(getClass().getResource(wall2.getImage()).toString());
        Image imageTopPipe = new Image(getClass().getResource(wall1.getImage()).toString());
        Image imageBotPipe = new Image(getClass().getResource(wall4.getImage()).toString());
        top.setFill(new ImagePattern(imageTopPipe));
        mid1.setFill(new ImagePattern(imagePipe));
        mid2.setFill(new ImagePattern(imagePipe));
        bot.setFill(new ImagePattern(imageBotPipe));

        Connection conn = DictionaryController.conn;
        PreparedStatement preparedStatement = conn.prepareStatement("Select * from bocauhoi");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            dataList.add(new Data(resultSet.getString("cauHoi"),
                    resultSet.getString("a"),
                    resultSet.getString("b"),
                    resultSet.getString("c"),
                    resultSet.getString("dapAn")));
        }

        takeQuestion();
    }

    public void moveWallX(double positionChange) {
        top.setX(positionChange);
        mid1.setX(positionChange);
        mid2.setX(positionChange);
        bot.setX(positionChange);
        a.setLayoutX(positionChange);
        b.setLayoutX(positionChange);
        c.setLayoutX(positionChange);
    }

    private void resetBird() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        bird.reset();
        birdRec.setY(63);
    }

    private void resetWall() {
        wall1.reset();
        wall2.reset();
        wall3.reset();
        wall4.reset();
        ans1.reset();
        ans2.reset();
        ans3.reset();
    }

    private void takeQuestion() {
        Random random = new Random();
        int index = random.nextInt(100000) % dataList.size();
        question.setText(dataList.get(index).getQuestion()
                + "\n\n"
                + "    A : " + dataList.get(index).getAnswerA()
                + "    B : " + dataList.get(index).getAnswerB()
                + "    C : " + dataList.get(index).getAnswerC());
        ans = dataList.get(index).getAnswer().trim();
        System.out.println(ans);
    }

    private void takeAnswer() {
        if (!isCollide && bird.checkCollision(ans1)) {
            if (ans.equals("a")) {
                isCollide = true;
                takeQuestion();
            } else {
                alert.setText("Incorrect! bird Dead");
                isBirdDie = true;
            }
        } else if (!isCollide && bird.checkCollision(ans2)) {
            if (ans.equals("b")) {
                isCollide = true;
                takeQuestion();
            } else {
                alert.setText("Incorrect! bird Dead");
                isBirdDie = true;
            }
        } else if (!isCollide && bird.checkCollision(ans3)) {
            if (ans.equals("c")) {
                isCollide = true;
                takeQuestion();
            } else {
                alert.setText("Incorrect! bird Dead");
                isBirdDie = true;
            }
        } else if (!bird.checkCollision(ans1) && !bird.checkCollision(ans2) && !bird.checkCollision(ans3)) {
            isCollide = false;
        }
    }

    private void checkBirdDead() {
        if (bird.checkOut()
                || bird.checkCollision(wall1)
                || bird.checkCollision(wall2)
                || bird.checkCollision(wall3)
                || bird.checkCollision(wall4)) {
            bird.setDead(true);
        }
    }
}

