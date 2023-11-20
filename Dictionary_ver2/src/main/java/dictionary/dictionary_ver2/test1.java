package dictionary.dictionary_ver2;

import java.util.ArrayList;

public class test1 {
    public static void main(String[] args) {
        ArrayList<String> languages= new ArrayList<>();
        languages.add("Vietnamese");languages.add("English");languages.add("Chinese");languages.add("Thailand");languages.add("German");languages.add("Japanese");languages.add("Korean");
        System.out.println(languages.indexOf("English"));
    }
}
