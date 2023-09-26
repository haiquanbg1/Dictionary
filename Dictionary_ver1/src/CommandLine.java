public class CommandLine {
    public static void dictionaryBasic() {
        DictionaryCommandline basic = new DictionaryCommandline();

        basic.insertFromCommandline();
        basic.showAllWords();
    }

    public static void main(String[] args) {

        dictionaryBasic();
    }
}
