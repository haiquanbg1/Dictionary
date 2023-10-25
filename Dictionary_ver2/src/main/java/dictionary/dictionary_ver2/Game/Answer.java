package dictionary.dictionary_ver2.Game;

public class Answer extends Wall{
    private String text;

    public Answer() {
    }

    public Answer(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
