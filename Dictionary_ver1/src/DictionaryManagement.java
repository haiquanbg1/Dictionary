import java.util.Scanner;

/** Thêm từ vào từ điển. */
public class DictionaryManagement {
    public static Dictionary dictionary = new Dictionary();
    /** Thêm word từ commandline. */
    public void insertFromCommandline() {
        Scanner input = new Scanner(System.in); // Biến để nhập

        int wordCount = input.nextInt(); // Nhập tổng số từ
        input.nextLine(); // Lấy khoảng trắng ra
        for(int i=1; i<=wordCount; i++) {
            String word = input.nextLine(); // Nhập từ tiếng Anh
            String means = input.nextLine(); // Nhập từ tiếng Việt
            dictionary.addWord(word, means);
        }
    }
}
