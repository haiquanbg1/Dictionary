import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.Random;

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
        Arrays.sort(words, 0, wordCount, new Comparator<Word>() {
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
        for(int i=0; i<wordCount; i++) {
            if(words[i].getWord().equals(word)) {
                ans = words[i].getMeans();
                break;
            }
        }
        return ans;
    }

    public int findIndex(String word) {
        int count = -1;
        for(int i=0; i<wordCount; i++) {
            if(words[i].getWord().equals(word)) {
                count = i;
                break;
            }
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
        for(int i = id; i < wordCount-1; i++) {
            words[i] = new Word(words[i + 1]);
        }
        words[wordCount-1] = null;
        wordCount--;
    }

    public void searchCommandLine() {
        Scanner input = new Scanner(System.in); // Biến để nhập
        String s = input.nextLine(); // Nhập từ tiếng Anh

//        String demo = s.substring(0, 1);
//        String ans = demo.toUpperCase();
//        s = s.substring(1);
//        s = ans.concat(s);

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

    public Word[] getWords() {
        return words;
    }

    public int getWordCount() {
        return wordCount;
    }

    public String pickWordToGame() {
        Random generator = new Random();
        int value = generator.nextInt(1000000)%(wordCount+1);
        if(value == wordCount) {
            value = 0;
        }
        System.out.println(value);
        System.out.println(wordCount);
        if(words[value] != null)
            return words[value].getWord();
        return "a";
    }
}
