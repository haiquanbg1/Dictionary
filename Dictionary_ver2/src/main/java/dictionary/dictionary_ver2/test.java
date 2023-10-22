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
import javafx.scene.image.Image;
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
    private final List<Snake> snake = new ArrayList<Snake>();
    private final List<Wall> block = new ArrayList<Wall>();
    private final List<Trap> trap = new ArrayList<Trap>();
    private boolean gameOver = false;
    private final boolean[] canMove = {true,true,true,true};
    private final int width = 20;
    private final int height = 20;
    private int currentDirection=0;
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    Apple apple=new Apple(1,1);
    private void setBlock(GraphicsContext gc) {
        block.add(new Wall(13, 14));
        block.add(new Wall(13, 15));
        block.add(new Wall(13, 16));
        for (Shape c : block) {
            gc.setFill(Color.BROWN);
            gc.fillRect(c.topLeftX * 25, c.topLeftY * 25, 25 , 25 );
        }
    }
    private void setTrap(GraphicsContext gc) {
        trap.add(new Trap(12, 14));
        trap.add(new Trap(12, 15));
        trap.add(new Trap(12, 16));
        for (Shape c : trap) {
            gc.setFill(Color.RED);
            gc.fillOval(c.topLeftX * 25, c.topLeftY * 25, 25 , 25 );
        }
    }
    public void start(Stage primaryStage) {
        try {
            VBox root = new VBox();
            Canvas c = new Canvas(width * 25, height * 25);
            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);
            Scene scene = new Scene(root, width * 25, height * 25);
            // control
            scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
                if(e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) {
                    //if(currentDirection != DOWN &&canMove[UP]) {
                        currentDirection = UP;
                        run(gc);
                   // }
                }
                if(e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) {
                    //if(currentDirection != LEFT&&canMove[RIGHT]) {
                        currentDirection = RIGHT;
                        run(gc);
                   // }
                }
                if(e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) {
                    //if(currentDirection != UP&&canMove[DOWN]) {
                        currentDirection = DOWN;
                        run(gc);
                    //}
                }
                if(e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) {
                    //if(currentDirection != RIGHT&&canMove[LEFT]) {
                        currentDirection = LEFT;
                        run(gc);
                    //}
                }
            });

            // add start snake parts
            snake.add(new Snake(width/2, height/2));
            snake.add(new Snake(width/2, height/2+1));
            snake.get(1).setMove(0, -1);
            snake.add(new Snake(width/2, height/2+2));
            snake.get(2).setMove(0, -1);
            //If you do not want to use css style, you can just delete the next line.

            run(gc);

            primaryStage.setScene(scene);
            primaryStage.setTitle("SNAKE GAME");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // tick
//    public void run(GraphicsContext gc) {
//        if(gameOver) {
//            gc.setFill(Color.RED);
//            gc.setFont(new Font("Digital-7", 70));
//            gc.fillText("Game Over", 500 / 3.5, 500 / 2);
//            return;
//        }
//        setBackground(gc);
//        setFoodIndex(gc);
//
//        for(int i=0;i<4;i++) {
//            canMove[i]= true;
//        }
//
//        for(int i=snake.size()-1; i>=1; i--) {
//            snake.get(i).topLeftX = snake.get(i-1).topLeftX;
//            snake.get(i).topLeftY = snake.get(i-1).topLeftY;
//        }
//        switch (currentDirection) {
//            case RIGHT:
//                snake.get(0).topLeftX ++;
//                break;
//            case LEFT:
//                snake.get(0).topLeftX --;
//                break;
//            case UP:
//                snake.get(0).topLeftY --;
//                break;
//            case DOWN:
//                snake.get(0).topLeftY ++;
//                break;
//            default:
//                break;
//        }
////        drawSnake(gc);
//        for (int i = 1; i < snake.size(); i++) {
//            if (snake.get(0).getTopLeftX() == snake.get(i).getTopLeftX()
//                    && snake.get(0).getTopLeftY()-1 == snake.get(i).topLeftY) {
//                canMove[UP]= false;
//            }
//            if (snake.get(0).getTopLeftX() == snake.get(i).getTopLeftX()
//                    && snake.get(0).getTopLeftY()+1 == snake.get(i).topLeftY) {
//                canMove[DOWN]= false;
//            }
//            if (snake.get(0).getTopLeftX()+1 == snake.get(i).getTopLeftX()
//                    && snake.get(0).getTopLeftY() == snake.get(i).topLeftY) {
//                canMove[RIGHT]= false;
//            }
//            if (snake.get(0).getTopLeftX()-1 == snake.get(i).getTopLeftX()
//                    && snake.get(0).getTopLeftY() == snake.get(i).topLeftY) {
//                canMove[LEFT]= false;
//            }
//        }
//        for (int i = 0; i < block.size(); i++) {
//            if (snake.get(0).getTopLeftX() == block.get(i).getTopLeftX()
//                    && snake.get(0).getTopLeftY()-1 == block.get(i).topLeftY) {
//                canMove[UP]= false;
//            }
//            if (snake.get(0).getTopLeftX() == block.get(i).getTopLeftX()
//                    && snake.get(0).getTopLeftY()+1 == block.get(i).topLeftY) {
//                canMove[DOWN]= false;
//            }
//            if (snake.get(0).getTopLeftX()+1 == block.get(i).getTopLeftX()
//                    && snake.get(0).getTopLeftY() == block.get(i).topLeftY) {
//                canMove[RIGHT]= false;
//            }
//            if (snake.get(0).getTopLeftX()-1 == block.get(i).getTopLeftX()
//                    && snake.get(0).getTopLeftY() == block.get(i).topLeftY) {
//                canMove[LEFT]= false;
//            }
//        }
//        setBlock(gc);
//        setTrap(gc);
//        setGameOver(gc);
//        // snake
//
//        drawSnake(gc);
//    }

    public void run(GraphicsContext gc) {
        if (!gameOver) {
            setBackground(gc);
            setFoodIndex(gc);

            switch (currentDirection) {
                case RIGHT:
                    snake.get(0).setMove(1, 0);
                    break;
                case LEFT:
                    snake.get(0).setMove(-1, 0);
                    break;
                case UP:
                    snake.get(0).setMove(0, -1);
                    break;
                case DOWN:
                    snake.get(0).setMove(0, 1);
                    break;
                default:
                    snake.get(0).setMove(0, 0);
                    break;
            }

            int status = checkMove(snake.get(0).moveU + snake.get(0).topLeftX,
                    snake.get(0).moveV + snake.get(0).topLeftY);

            switch (status) {
                case 0:
                    snake.get(0).setMove(0, 0);
                    break;
                case 2:
                    eatApple();
                    break;
                case 3:
                    gameOver = true;
                    break;
            }

            if (snake.get(0).moveU != 0 || snake.get(0).moveV != 0) {
                for (int i = 0; i < snake.size(); i++) { // Di chuyển
                    snake.get(i).Move();
                }

                for (int i = snake.size() - 1; i > 0; i--) { // Set lại di chuyển cho lần sau
                    snake.get(i).setMove(snake.get(i - 1).moveU, snake.get(i - 1).moveV);
                }
                snake.get(0).setMove(0, 0);
            }

            setBlock(gc);
            setTrap(gc);
            drawSnake(gc);
        }
        else {
            gc.setFill(Color.RED);
            gc.setFont(new Font("Digital-7", 70));
            gc.fillText("Game Over", 500 / 3.5, 500 / 2);
        }
    }

    private int checkMove(double x, double y) {
        for (int i = 0; i < block.size(); i++) {
            if (block.get(i).topLeftX == x
                    && block.get(i).topLeftY == y) {
                return 0; // Không đi được vì đâm tường
            }
        }

        for (int i = 1; i < snake.size(); i++) {
            if(snake.get(i).topLeftX == x
                    && snake.get(i).topLeftY == y) {
                return 0; // Không đi được vì đâm rắn
            }
        }

        if(apple.topLeftX == x
                && apple.topLeftY == y) {
            return 2; // Đi được và ăn táo
        }

        if (x < 0 || y < 0 || x >25 || y >25) {
            return 3; // Đi được nhưng ra ngoài
        }
        for (int i = 0; i < trap.size(); i++) {
            if (x == trap.get(i).topLeftX
                    && y == trap.get(i).topLeftY) {
                return 3; // Đi được nhưng đạp bẫy
            }
        }

        return 1; // Đi vào ô trống
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
            gc.fillRect(c.topLeftX * 25, c.topLeftY * 25, 25 , 25 );
            gc.setFill(Color.GREEN);
            gc.fillRect(c.topLeftX * 25, c.topLeftY * 25, 25 , 25 );
        }
    }
    private void eatApple() {
        double posX = snake.get(snake.size() - 1).topLeftX;
        double posY = snake.get(snake.size() - 1).topLeftY;
        snake.add(new Snake(posX, posY));
        apple.topLeftY = 10;
    }

//    public void setGameOver(GraphicsContext gc) {
//        if (snake.get(0).topLeftX < 0
//                || snake.get(0).topLeftY < 0
//                || snake.get(0).topLeftX >25
//                || snake.get(0).topLeftX >25) {
//            gameOver = true;
//        }
//        for (int i = 0; i < trap.size(); i++) {
//            if (snake.get(0).topLeftX == trap.get(i).topLeftX
//                    && snake.get(0).topLeftY == trap.get(i).topLeftY) {
//                gameOver = true;
//                gc.setFill(Color.RED);
//                gc.setFont(new Font("Digital-7", 70));
//                gc.fillText("Game Over", 500 / 3.5, 500 / 2);
//                break;
//            }
//        }
//    }
    public static void main(String[] args) {
        launch(args);
    }
}