import java.util.Arrays;
import java.util.Comparator;

/** Lưu trữ mảng Word. */
public class Dictionary {
    private Word[] words; // Mảng Word
    private int wordCount = 0; // Số từ trong mảng

    /** Thêm từ vào mảng Word. */
    public void addWord(String word, String means) {
        words[wordCount++] = new Word(word, means);
    }

    /** Sắp xếp mảng Word. */
    public void sortWords() {
        Arrays.sort(words, new Comparator<Word>() {
            @Override
            public int compare(Word s1, Word s2) {
                return s1.getWord().compareTo(s2.getWord());
            }
        });
    }

    /** Xuất ra mảng Word */
    public void printWord() {
        int count = 1;
        System.out.println("No   | English | Vietnamese");
        for(Word word : words) {
            System.out.println(count + "    | " + word.getWord() + " | " + word.getMeans());
        }
    }
}
