package dictionary.dictionary_ver2.DictionarySources;

import dictionary.dictionary_ver2.Tree.Tree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.List;

public class DictionaryManagement {
    private Tree tree = new Tree();

    /**
     * Đẩy từ trong path vào dictionary.
     */
    public void insertFromFile(Dictionary dictionary, String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String englishWord = bufferedReader.readLine();
            englishWord = englishWord.replace(".", "");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Word word = new Word();
                word.setWordTarget(englishWord.trim());
                String meaning = line + "\n";
                while ((line = bufferedReader.readLine()) != null)
                    if (!line.startsWith(".")) meaning += line + "\n";
                    else {
                        englishWord = line.replace(".", "");
                        break;
                    }
                word.setWordExplain(meaning.trim());
                dictionary.add(word);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    /**
     * Đẩy từ từ dictionary ra path.
     */
    public void exportToFile(Dictionary dictionary, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : dictionary) {
                bufferedWriter.write("." + word.getWordTarget() + "\n" + word.getWordExplain());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    /**
     * Tìm các từ chứa key trong dictionary.
     */
    public ObservableList<String> lookupWord(Dictionary dictionary, String key ) {
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            List<String> results = tree.autoComplete(key);
            if (results != null) {
                int length = Math.min(results.size(), 15); // Chỉ lấy tối đa 15 từ
                for (int i = 0; i < length; i++) list.add(results.get(i));
            }
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
        return list;
    }

    /**
     * Tìm keyWord trong dictionary bằng chặt nhị phân
     */
    public int searchWord(Dictionary dictionary, String keyWord) {
        try {
            dictionary.sort(new SortDictionary());
            int left = 0;
            int right = dictionary.size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int res = dictionary.get(mid).getWordTarget().compareTo(keyWord);
                if (res == 0) return mid;
                if (res <= 0) left = mid + 1;
                else right = mid - 1;
            }
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
        return -1;
    }

    /**
     * Cập nhật từ ở vị trí index rồi đẩy vào path
     */
    public void updateWord(Dictionary dictionary, int index, String meaning, String path) {
        try {
            dictionary.get(index).setWordExplain(meaning);
            exportToFile(dictionary, path);
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }

    /**
     * Xoá từ ở vị trí index, đẩy vào path và làm lại cây
     */
    public void deleteWord(Dictionary dictionary, int index, String path) {
        try {
            dictionary.remove(index);
            tree = new Tree();
            setTrie(dictionary);
            exportToFile(dictionary, path);
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }

    /**
     * Thêm từ mới vào path
     */
    public void addWord(Word word, String path) {
        try (FileWriter fileWriter = new FileWriter(path, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("." + word.getWordTarget() + "\n" + word.getWordExplain());
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("IOException.");
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }

    /**
     * Tạo ra thời gian delay
     */
    public void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }

    /**
     * Tạo cây mới từ dictionary
     */
    public void setTrie(Dictionary dictionary) {
        try {
            for (Word word : dictionary) tree.insert(word.getWordTarget());
        } catch (NullPointerException e) {
            System.out.println("Something went wrong: " + e);
        }
    }
}