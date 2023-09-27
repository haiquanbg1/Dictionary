import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.io.FileWriter;

/** Lưu trữ mảng Word. */
public class Dictionary {
    private Word[] words = new Word[1000000]; // Mảng Word
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

        System.out.print("No   | English");
        for(int i = 7; i <= maxLength; i++) {
            System.out.print(" ");
        }
        System.out.println("| Vietnamese");
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

    public String findWord(String word) {
        String ans = "";
        for(Word i : words) {
            if(i.getWord().equals(word)) {
                ans = i.getMeans();
                break;
            }
        }
        return ans;
    }

    public int findIndex(String word) {
        int count = 0;
        for(int i=0; i<wordCount; i++) {
            if(words[i].getWord().equals(word)) {
                break;
            }
            count++;
        }
        return count;
    }

    public Word insertCommandLine() {
        Scanner input = new Scanner(System.in); // Biến để nhập

        String word = input.nextLine(); // Nhập từ tiếng Anh
        String means = input.nextLine(); // Nhập từ tiếng Việt
        return new Word(word, means);
    }

    public void updateCommandLine(int id, Word word) {
        words[id] = null;
        words[id] = new Word(word);
    }

    public void deleteCommandLine(int id) {
        words[id] = null;
        for(int i = id; i < wordCount; i++) {
            words[i] = new Word(words[i + 1]);
        }
        words[wordCount] = null;
        wordCount--;
    }

    public void searchCommandLine() {
        Scanner input = new Scanner(System.in); // Biến để nhập
        String s = input.nextLine(); // Nhập từ tiếng Anh

        String demo = s.substring(0, 1);
        String ans = demo.toUpperCase();
        s = s.substring(1);
        s = ans.concat(s);

        for(int i=0; i<wordCount; i++) {
            if(words[i].getWord().contains(s)) {
                System.out.println(words[i].getWord());
            }
        }
    }

    public void writeToFile(String s) throws IOException {
        FileWriter fw = new FileWriter(s);
        for(int i=0; i<wordCount; i++) {
            fw.write(words[i].getWord() + " . " + words[i].getMeans() + "\n");
        }
        fw.close();
    }
}
