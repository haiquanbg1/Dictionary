package dictionary.dictionary_ver2.Game;

public class Bird extends Shape{
    private static final double speed = 1;

    public Bird() {
    }

    public Bird(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    public void fly() {
        this.setTopLeftY(this.getTopLeftY() + speed);
    }

    public void fall() {
        this.setTopLeftY(this.getTopLeftY() - speed);
    }
}
