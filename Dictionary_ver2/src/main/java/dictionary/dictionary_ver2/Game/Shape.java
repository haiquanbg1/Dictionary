package dictionary.dictionary_ver2.Game;

public class Shape {
    static final double side = 10;
    protected double topLeftX;
    protected double topLeftY;

    public Shape() {
    }

    public Shape(double x, double y) {
        topLeftX = x;
        topLeftY = y;
    }

    public boolean collider(Shape shape) {
        if (this.topLeftX == shape.topLeftX || this.topLeftY == shape.topLeftY) {
            return true;
        }
        return false;
    }
}
