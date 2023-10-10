package dictionary.dictionary_ver2.DictionarySources;

import java.util.Comparator;

public class SortDictionary implements Comparator<Word> {
    @Override
    public int compare(Word a, Word b) {
        return a.getWordTarget().compareTo(b.getWordTarget());
    }
}