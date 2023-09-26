import java.util.Arrays;
import java.util.Comparator;

/** Lưu trữ mảng Word. */
public class Dictionary {
    private Word[] words = new Word[100]; // Mảng Word
    private int wordCount = 0; // Số từ trong mảng
    private int maxLength = 7; // Độ dài từ dài nhất

    /** Thêm từ vào mảng Word. */
    public void addWord(String word, String means) {
        words[wordCount++] = new Word(word, means); // thêm từ
        maxLength = maxLength > word.length() ? maxLength : word.length(); // Lấy max
    }

    /** Sắp xếp mảng Word. */
    public void sortWords() {
        Arrays.sort(words, 0, wordCount-1, new Comparator<Word>() {
            @Override
            public int compare(Word s1, Word s2) {
                return s1.getWord().compareTo(s2.getWord());
            }
        });
    }

    /** Xuất ra mảng Word */
    public void printWord() {
        int count = 5; // Độ dài lớn nhất của số đếm

        System.out.println("No   | English | Vietnamese");
        for(int i = 0; i < wordCount; i++) {
            Integer j = i+1;
            String s = j.toString();
            System.out.print(s);
            for(int k = 1; k <= count - s.length(); k++) {
                System.out.print(" ");
            }
            System.out.print("| " + words[i].getWord());
            for(int k = 1; k <= maxLength - words[i].getWord().length(); k++) {
                System.out.print(" ");
            }
            System.out.print(" | " + words[i].getMeans() + "\n");
        }
    }
}
