package dictionary.dictionary_ver2.Controllers;

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

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    @FXML
    private TextField search;
    @FXML
    private ListView<String> listWord;

    @FXML
    private TextArea definition;
    String[] word={"hello","goodbye","boy","girl","dog","cat","duck","friend"};

    private ObservableList<String> list=FXCollections.observableArrayList();
    String targetWord;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        list.addAll(word);
        listWord.setItems(list);

        listWord.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listWord.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                ObservableList<String> selectedWord= listWord.getSelectionModel().getSelectedItems();
                if(selectedWord.isEmpty()){
                    targetWord="";
                } else {
                    targetWord=selectedWord.toString();
                }
                definition.setText(targetWord);
            }
        });

    }

    @FXML
    private void search1(KeyEvent event) {
        String target = search.getText();
        if (event.getCode() != KeyCode.BACK_SPACE) {
            target += event.getText();
        }
        searchWord(target);
    }
    public void searchWord(String target) {
        list.clear();
        for (String s : word) {
            if (s.contains(target)) {
                list.add(s);
            }
        }
        listWord.setItems(list);

    }

    public void sound() {

    }


}
