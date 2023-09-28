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

    public void runGame() throws InterruptedException {
        char[] data = super.pickWordToGame().toCharArray();
        Game game = new Game(data);
        while(!game.checkGame()) {
            char guess = game.insertWord();
            game.renderFigure(guess);
        }
    }
}
