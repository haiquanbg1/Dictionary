package dictionary.dictionary_ver2.Game;

public class Shape {
    private double topLeftX;
    private double topLeftY;
    private double width;
    private double height;

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Shape() {
    }

    public Shape(double x, double y, double w, double h) {
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
        if (this.topLeftX + this.width < shape.getTopLeftY()) {
            return false;
        }

        if(this.topLeftX > shape.getTopLeftX() + shape.getWidth()) {
            return false;
        }

        if(this.topLeftY + this.height < shape.getTopLeftY()) {
            return false;
        }

        if(this.topLeftY > shape.getTopLeftY() + shape.getHeight()) {
            return false;
        }
        return true;
    }
}
