import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CommandLine {
    private DictionaryCommandline basic = new DictionaryCommandline();;

    public void dictionaryBasic() throws FileNotFoundException {
        basic.insertFromFile();
        basic.showAllWords();
    }

    public void dictionarySearcher() throws FileNotFoundException {
        basic.insertFromFile();
        basic.showContainWords();
    }

    public void dictionaryAdvanced() throws IOException {
        while (true) {
            System.out.print("\nWelcome to My Application!\n" +
                    "[0] Exit\n" +
                    "[1] Add\n" +
                    "[2] Remove\n" +
                    "[3] Update\n" +
                    "[4] Display\n" +
                    "[5] Lookup\n" +
                    "[6] Search\n" +
                    "[7] Game\n" +
                    "[8] Import from file\n" +
                    "[9] Export to file\n" +
                    "Your action: ");
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            switch (n) {
                case 0:
                    return;
                case 1:
                    System.out.println("Insert word and means ?");
                    basic.insert();
                    break;
                case 2:
                    System.out.println("Remove word ?");
                    basic.delete();
                    break;
                case 3:
                    System.out.println("Update word's means to means ?");
                    basic.update();
                    break;
                case 4:
                    System.out.println("The list words are:");
                    basic.showAllWords();
                    break;
                case 5:
                    System.out.println("The word's means you find:");
                    basic.dictionaryLookup();
                    break;
                case 6:
                    System.out.println("The chars you want to find ?");
                    basic.showContainWords();
                    break;
                case 7:
                    break;
                case 8:
                    basic.insertFromFile();
                    break;
                case 9:
                    basic.dictionaryExportToFile();
                    break;
                default:
                    System.out.println("Action not supported");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        CommandLine Main = new CommandLine();
//        Main.dictionarySearcher();
        Main.dictionaryAdvanced();
    }
}
