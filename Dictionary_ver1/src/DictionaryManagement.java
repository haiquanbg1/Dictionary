import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

/** Thêm từ vào từ điển. */
public class DictionaryManagement extends Dictionary {
    /** Thêm word từ commandline. */
    public void insertFromCommandline() {
        Scanner input = new Scanner(System.in); // Biến để nhập

        int wordCount = input.nextInt(); // Nhập tổng số từ
        input.nextLine(); // Lấy khoảng trắng ra
        for(int i=1; i<=wordCount; i++) {
            String word = input.nextLine(); // Nhập từ tiếng Anh
            String means = input.nextLine(); // Nhập từ tiếng Việt
            super.addWord(word, means);
        }
    }

    public void insertFromFile() throws FileNotFoundException {
        File file = new File("FileWord\\words.txt");
        Scanner input = new Scanner(file);

        while(input.hasNextLine()) {
            String[] a = input.nextLine().split(" . ");
            String word, means;
            word = a[0];
            means = a[1];
            super.addWord(word, means);
        }
    }

    public String dictionaryLookup() {
        Scanner input = new Scanner(System.in); // Biến để nhập
        String word = input.nextLine();
        return super.findWord(word);
    }

    public void insert() {
        Word ans = super.insertCommandLine();
        super.addWord(ans.getWord(), ans.getMeans());
    }

    public void update() {
        Word ans = super.insertCommandLine();
        int id = super.findIndex(ans.getWord());
        super.updateCommandLine(id, ans);
    }

    public void delete() {
        Scanner input = new Scanner(System.in);
        String ans = input.nextLine();
        int id = super.findIndex(ans);
        super.deleteCommandLine(id);
    }

    public void dictionaryExportToFile() throws IOException {
        File file = new File("FileWord\\words.txt");
        file.delete();
        super.writeToFile("FileWord\\words.txt");
    }
}
