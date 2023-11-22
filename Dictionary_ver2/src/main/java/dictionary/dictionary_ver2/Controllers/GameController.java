package dictionary.dictionary_ver2.Controllers;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class GameController implements Initializable {
    private AnimationTimer gameLoop;

    @FXML
    private AnchorPane plane;
    @FXML
    private AnchorPane background;
    @FXML
    private Rectangle bird;
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
    private Rectangle ansA;
    @FXML
    private Rectangle ansB;
    @FXML
    private Rectangle ansC;
    @FXML
    private StackPane a;
    @FXML
    private StackPane b;
    @FXML
    private StackPane c;

    private final double yDelta = 0.02 ;
    private double time;
    private int jumpHeight;
    private boolean isFly;
    private double count;
    private List<Data> dataList = new ArrayList<>();
    private String ans;
    private boolean isCollide;

    public GameController() throws SQLException {
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
                moveBirdY(yDelta * 35 * -2);
                count += yDelta * 35 * 2;
            } else {
                time = 0;
                isFly = false;
            }
        }

        moveWallX(yDelta * 30);

        if (isOut()) {
            resetWall();
        }

        takeAnswer();

        if(isBirdDead()){
            resetBird();
            resetWall();
            takeQuestion();
        }
    }

    //Everything called once, at the game start
    private void load() throws SQLException {
        time = 0;
        jumpHeight = 25;
        isFly = false;
        count = 0;
        isCollide = false;

        Image imageBird = new Image(getClass().getResource("/Images/yellowbird-midflap.png").toString());
        bird.setFill(new ImagePattern(imageBird));

        Image imagePipe = new Image(getClass().getResource("/Images/dual-pipe.jpg").toString());
        Image imageTopPipe = new Image(getClass().getResource("/Images/bot-pipe.png").toString());
        Image imageBotPipe = new Image(getClass().getResource("/Images/top-pipe.png").toString());
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

    public void moveBirdY(double positionChange) {
        bird.setY(bird.getY() + positionChange);
    }

    public void moveWallX(double positionChange) {
        top.setX(top.getX() - positionChange);
        mid1.setX(mid1.getX() - positionChange);
        mid2.setX(mid2.getX() - positionChange);
        bot.setX(bot.getX() - positionChange);
        ansA.setX(ansA.getX() - positionChange);
        ansB.setX(ansB.getX() - positionChange);
        ansC.setX(ansC.getX() - positionChange);
        a.setLayoutX(a.getLayoutX() - positionChange);
        b.setLayoutX(b.getLayoutX() - positionChange);
        c.setLayoutX(c.getLayoutX() - positionChange);
    }

    private boolean isBirdDead() {
        double birdY = bird.getY() + bird.getWidth() + bird.getLayoutY();
        return birdY >= 355
                || checkCollision(bird, top)
                || checkCollision(bird, mid1)
                || checkCollision(bird, mid2)
                || checkCollision(bird, bot);
    }

    private boolean isOut() {
        if (top.getX() <= 0) {
            return true;
        }
        return false;
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
        top.setX(674);
        mid1.setX(674);
        mid2.setX(674);
        bot.setX(674);
        ansA.setX(674);
        ansB.setX(674);
        ansC.setX(674);
        a.setLayoutX(674);
        b.setLayoutX(674);
        c.setLayoutX(674);
    }

    private void takeQuestion() {
        Random random = new Random();
        int index = random.nextInt(100000) % dataList.size();
        question.setText(dataList.get(index).getQuestion()
                + "\n\n"
                + "    1 : " + dataList.get(index).getAnswerA()
                + "    2 : " + dataList.get(index).getAnswerB()
                + "    3 : " + dataList.get(index).getAnswerC());
        ans = dataList.get(index).getAnswer().trim();
        System.out.println(ans);
    }

    private void takeAnswer() {
        if (!isCollide && checkCollision(bird, ansA)) {
            if (!ans.equals("a")) {
                resetBird();
                resetWall();
            } else {
                isCollide = true;
            }
            takeQuestion();
        } else if (!isCollide && checkCollision(bird, ansB)) {
            if (!ans.equals("b")) {
                resetBird();
                resetWall();
            } else {
                isCollide = true;
            }
            takeQuestion();
        } else if (!isCollide && checkCollision(bird, ansC)) {
            if (!ans.equals("c")) {
                resetBird();
                resetWall();
            } else {
                isCollide = true;
            }
            takeQuestion();
        } else if (!checkCollision(bird, ansC) && !checkCollision(bird, ansB) && !checkCollision(bird, ansA)) {
            isCollide = false;
        }
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

