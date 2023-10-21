package dictionary.dictionary_ver2.Game;

public class Shape {
    static final double side = 25;
    public double topLeftX;
    public double topLeftY;

    public Shape() {
    }

    public Shape(double x, double y) {
        topLeftX = x;
        topLeftY = y;
    }

    public double getTopLeftX() {
        return topLeftX;
    }

    public void setTopLeftX(double topLeftX) {
        this.topLeftX = topLeftX;
    }

    public double getTopLeftY() {
        return topLeftY;
    }

    public void setTopLeftY(double topLeftY) {
        this.topLeftY = topLeftY;
    }

    public boolean collider(Shape shape) {
        if (this.topLeftX == shape.topLeftX || this.topLeftY == shape.topLeftY) {
            return true;
        }
        return false;
    }
}
