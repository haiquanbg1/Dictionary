package dictionary.dictionary_ver2.Controllers;

import dictionary.dictionary_ver2.Alerts.Alerts;
import dictionary.dictionary_ver2.DictionarySources.Dictionary;
import dictionary.dictionary_ver2.DictionarySources.DictionaryManagement;
import dictionary.dictionary_ver2.DictionarySources.Word;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    @FXML
    private TextField search;
    @FXML
    private ListView<String> listWord;

    @FXML
    private TextArea definition;

    private Dictionary dictionary = new Dictionary();
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    private final String path = "src/main/resources/Texts/dictionaries.txt";
    private ObservableList<String> list = FXCollections.observableArrayList();
    private Alerts alerts = new Alerts();
    private int indexOfSelectedWord;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dictionaryManagement.insertFromFile(dictionary, path);
        dictionaryManagement.setTree(dictionary);
        setListDefault(0);

        listWord.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listWord.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String selectedWord= listWord.getSelectionModel().getSelectedItem();
                if (selectedWord != null) {
                    indexOfSelectedWord = dictionaryManagement.searchWord(dictionary, selectedWord);
                    if (indexOfSelectedWord == -1) {
                        return;
                    }
                    definition.setText(dictionary.get(indexOfSelectedWord).getWordExplain());
                }

            }
        });

    }

    @FXML
    private void search(KeyEvent event) {
        String target = search.getText().trim();
        list.clear();
        list = dictionaryManagement.lookupWord(dictionary, target);
        listWord.setItems(list);
    }

    public void sound() {

    }

    private void setListDefault(int index) {
        list.clear();
        for (int i = index; i < index + 15; i++) {
            list.add(dictionary.get(i).getWordTarget());
        }
        listWord.setItems(list);
    }
}
