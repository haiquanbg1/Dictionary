package dictionary.dictionary_ver2.Game;

public class Wall extends Rec {
    private final double speed = 1;
    private final double xDelta = 0.02;

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
        this.setTopLeftX(674);
    }

    public Wall() {
    }

    public Wall(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public Wall(int x, int y, int w, int h, String image) {
        super(x, y, w, h, image);
    }
}
