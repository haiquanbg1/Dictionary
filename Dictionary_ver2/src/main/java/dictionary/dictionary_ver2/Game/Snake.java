package dictionary.dictionary_ver2.Game;

public class Snake extends Shape{
    public int moveU;
    public int moveV;

    public Snake() {
    }

    public Snake(double x, double y) {
        super(x, y);
    }

    public Snake(Snake snake) {
        super(snake.topLeftX, snake.topLeftY);
        setMove(snake.moveU, snake.moveV);
    }

    public void setMove(int u, int v) {
        moveU = u;
        moveV = v;
    }

    public void Move(int u, int v) {
        topLeftX += u * side;
        topLeftY += v * side;
    }
}
