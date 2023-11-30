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
    
    public boolean checkCollision(Rec a) {
        if (this.getTopLeftX() + 5 > a.getTopLeftX() + a.getWidth()) {
            return false;
        }

        if (this.getTopLeftX() + this.getWidth() - 5 < a.getTopLeftX()) {
            return false;
        }

        if (this.getTopLeftY() + 5 > a.getTopLeftY() + a.getHeight()) {
            return false;
        }

        if (this.getTopLeftY() + this.getHeight() - 5 < a.getTopLeftY()) {
            return false;
        }

        return true;
    }
}
