/** Lưu từng từ. */
public class Word {
    private String word_target = ""; // Từ tiếng Anh
    private String word_explain = ""; // Từ tiếng Việt

    /** Khởi tạo từ mới. */
    public Word(String word, String means) {
        word_explain = means;
        word_target = word;
    }

    public Word(Word newWord) {
        this.word_target = newWord.word_target;
        this.word_explain = newWord.word_explain;
    }

    /** Lấy từ ra */
    public String getWord() {
        return word_target;
    }

    public String getMeans() {
        return word_explain;
    }
}
