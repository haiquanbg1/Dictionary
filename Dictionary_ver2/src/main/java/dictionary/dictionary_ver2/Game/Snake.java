package dictionary.dictionary_ver2.Game;

public class Snake extends Shape{
    public int moveU;
    public int moveV;

    public Snake() {
    }

    public Snake(double x, double y) {
        super(x, y);
        setMove(0, 0);
    }

    public void setMove(int u, int v) {
        moveU = u;
        moveV = v;
    }

    public void Move() {
        topLeftX += moveU;
        topLeftY += moveV;
    }
}
