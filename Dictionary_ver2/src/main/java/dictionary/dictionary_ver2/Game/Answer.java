package dictionary.dictionary_ver2.Game;

public class Answer extends Wall {
    private String ans;

    public Answer() {
    }

    public Answer(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    public Answer(double x, double y, double w, double h, String image) {
        super(x, y, w, h, image);
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
