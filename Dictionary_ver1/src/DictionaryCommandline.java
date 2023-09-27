import java.util.Arrays;

public class DictionaryCommandline extends DictionaryManagement {
    /** Xuất ra commandline */
    public void showAllWords() {
        super.sortWords(); // Sắp xếp
        super.printWord(); // Xuất
    }

    public void showContainWords() {
        super.searchCommandLine();
    }
}
