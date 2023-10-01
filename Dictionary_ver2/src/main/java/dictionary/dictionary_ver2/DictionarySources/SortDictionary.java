package dictionary.dictionary_ver2.DictionarySources;

import java.util.Comparator;

public class SortDictionary implements Comparator<Word> {
    @Override
    public int compare(Word word1, Word word2) {
        return word1.getWordTarget().compareTo(word2.getWordTarget());
    }
}