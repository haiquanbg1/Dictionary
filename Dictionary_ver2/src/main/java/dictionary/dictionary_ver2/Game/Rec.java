package dictionary.dictionary_ver2.Game;

public class Rec {
    private double topLeftX;
    private double topLeftY;
    private double width;
    private double height;
    private String Image;
    
    public Rec() {
    }
    
    public Rec(double x, double y, double w, double h) {
        topLeftX = x;
        topLeftY = y;
        width = w;
        height = h;
    }

    public Rec(double x, double y, double w, double h, String image) {
        topLeftX = x;
        topLeftY = y;
        width = w;
        height = h;
        Image = image;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getTopLeftX() {
        return topLeftX;
    }

    public double getTopLeftY() {
        return topLeftY;
    }

    public String getImage() {
        return Image;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setTopLeftX(double topLeftX) {
        this.topLeftX = topLeftX;
    }

    public void setTopLeftY(double topLeftY) {
        this.topLeftY = topLeftY;
    }
    
    protected boolean checkCollision(Rec a) {
        if (a.getTopLeftX() + 5 > this.getTopLeftX() + this.getWidth()) {
            return false;
        }

        if (a.getTopLeftX() + a.getWidth() - 5 < this.getTopLeftX()) {
            return false;
        }

        if (a.getTopLeftY() + 5 > this.getTopLeftY() + this.getHeight()) {
            return false;
        }

        if (a.getTopLeftY() + a.getHeight() - 5 < this.getTopLeftY()) {
            return false;
        }

        return true;
    }
}
