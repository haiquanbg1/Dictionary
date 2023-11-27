package dictionary.dictionary_ver2.Game;

public class Answer extends Wall {
    private final String ansA = "a";
    private final String ansB = "b";
    private final String ansC = "c";
    public Answer() {
    }

    public Answer(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public Answer(int x, int y, int w, int h, String image) {
        super(x, y, w, h, image);
    }
}
