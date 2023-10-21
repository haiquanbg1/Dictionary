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
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    private  String selectedWord;
    @FXML
    private TextField search;
    @FXML
    private ListView<String> listWord;

    @FXML
    private TextArea definition;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    @FXML
    private AnchorPane editTable;
    @FXML
    private Button confirm;
    @FXML
    private TextArea word_edit;

    @FXML
    private TextArea define_edit;
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
        editTable.setVisible(false);
        listWord.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listWord.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                selectedWord = listWord.getSelectionModel().getSelectedItem();
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
        definition.setText("");
        // selectedWord = listWord.get;
    }

    @FXML
    private void editAction() {
        if(editTable.isVisible()) {
            editTable.setVisible(false);
        } else {
            editTable.setVisible(true);

        }
    }
    @FXML
    private void saveAction() {
        confirm.setOnAction(event -> {
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Bạn chắc chắn muốn sửa?");

            ButtonType buttonTypeYes= new ButtonType("Yes",ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo= new ButtonType("NO",ButtonBar.ButtonData.NO);
            ButtonType buttonTypeCancel= new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo,buttonTypeCancel);
            Optional<ButtonType> result=alert.showAndWait();

            String word = word_edit.getText().trim();
            String meaning = define_edit.getText().trim();

            //code for yes button
            if(result.get().getButtonData() == ButtonBar.ButtonData.YES) {
                int indexWord = dictionaryManagement.searchWord(dictionary, word);
                dictionaryManagement.updateWord(dictionary, indexWord, meaning, path);
                definition.setText(meaning);

                Alert alert1= new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Notifications");
                alert1.setContentText("Thay đổi thành công");
                alert1.show();
            }
            //code for no button
            else {
                Alert alert1= new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Notifications");
                alert1.setContentText("Thay đổi không thành công");
                alert1.show();
            }
            editTable.setVisible(false);
        });
    }
    @FXML
    private void deleteAction() {
        delete.setOnAction(event -> {
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Bạn chắc chắn muốn xóa?");

            ButtonType buttonTypeYes= new ButtonType("Yes",ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo= new ButtonType("NO",ButtonBar.ButtonData.NO);
            ButtonType buttonTypeCancel= new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo,buttonTypeCancel);
            Optional<ButtonType> result=alert.showAndWait();

            int indexWord = dictionaryManagement.searchWord(dictionary, selectedWord);

            //code for yes button
            if(result.get().getButtonData() == ButtonBar.ButtonData.YES) {
                dictionaryManagement.deleteWord(dictionary, indexWord, path);
                selectedWord = "";
                String target = search.getText().trim();
                list.clear();
                list = dictionaryManagement.lookupWord(dictionary, target);
                listWord.setItems(list);
                definition.setText("");

                Alert alert1= new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Notifications");
                alert1.setContentText("Xóa thành công");
                alert1.show();
            }
            //code for no button
            else {
                Alert alert1= new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Notifications");
                alert1.setContentText("Xóa thất bại");
                alert1.show();
            }
        });
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

    @FXML
    private void getSelectedWord() {
        selectedWord= listWord.getSelectionModel().getSelectedItem();
        word_edit.setText(selectedWord);
        define_edit.setText(dictionary.get(indexOfSelectedWord).getWordExplain());
    }
}
