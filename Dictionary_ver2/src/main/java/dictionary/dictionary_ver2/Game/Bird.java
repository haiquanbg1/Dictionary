package dictionary.dictionary_ver2.Game;

public class Bird extends Rec {
    private final double speed = 70;
    private final double yDelta = 0.02;
    private final double jumpHeight = 25;
    private boolean isDead;
    private boolean isFly;
    private double countDistanceJump;
    private int time;

    public void fly() {
        if (!isFly) {
            time++;
            this.setTopLeftY(yDelta * time + this.getTopLeftY());
        } else {
            if (countDistanceJump < jumpHeight && this.getTopLeftY() > 0) {
                this.setTopLeftY(yDelta * (- speed) + this.getTopLeftY());
                countDistanceJump += yDelta * speed;
            } else {
                time = 0;
                isFly = false;
            }
        }
    }

    public void reset() {
        this.setDead(false);
        this.setTopLeftY(63);
        time = 0;
    }

    public Bird() {
    }

    public Bird(double x, double y, double w, double h) {
        super(x, y, w, h);
        setDead(false);
    }

    public Bird(double x, double y, double w, double h, String image) {
        super(x, y, w, h, image);
        setDead(false);
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setCountDistanceJump(double countDistanceJump) {
        this.countDistanceJump = countDistanceJump;
    }

    public void setFly(boolean fly) {
        isFly = fly;
    }

    public boolean isFly() {
        return isFly;
    }
}
