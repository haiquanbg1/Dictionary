package dictionary.dictionary_ver2.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.net.URL;
import java.util.ResourceBundle;

public class ListController implements Initializable {
    @FXML
    private ListView<String> listView;

    @FXML
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] word={"hello","goodbye","boy","girl","dog","cat","duck","friend"};
        listView.getItems().addAll(word);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String targetWord;
                ObservableList<String> selectedWord= listView.getSelectionModel().getSelectedItems();
                if(selectedWord.isEmpty()){
                    targetWord="";
                } else {
                    targetWord=selectedWord.toString();
                }
                label.setText(targetWord);
            }
        });
    }
}
