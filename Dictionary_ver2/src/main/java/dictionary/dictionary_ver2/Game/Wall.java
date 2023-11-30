package dictionary.dictionary_ver2.Game;

public class Wall extends Rec {
    private final double speed = 30;
    private final double xDelta = 0.02;
    private final double start = 674;

    public void move() {
        this.setTopLeftX(xDelta * (- speed) + this.getTopLeftX());
    }

    public boolean checkOut() {
        if (getTopLeftX() <= 0) {
            return true;
        }
        return false;
    }

    public void reset() {
        this.setTopLeftX(start);
    }

    public Wall() {
    }

    public Wall(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    public Wall(double x, double y, double w, double h, String image) {
        super(x, y, w, h, image);
    }
}
