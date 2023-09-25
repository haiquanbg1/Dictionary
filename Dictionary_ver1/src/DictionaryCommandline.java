import java.util.Arrays;

public class DictionaryCommandline extends DictionaryManagement {
    /** Xuất ra commandline */
    public void showAllWords() {
        dictionary.sortWords(); // Sắp xếp
        dictionary.printWord(); // Xuất
    }
}
