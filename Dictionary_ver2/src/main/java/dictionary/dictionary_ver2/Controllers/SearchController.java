package dictionary.dictionary_ver2.Controllers;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import dictionary.dictionary_ver2.DictionarySources.Dictionary;
import dictionary.dictionary_ver2.DictionarySources.DictionaryManagement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
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
    private Button delete;
    @FXML
    private AnchorPane editTable;
    @FXML
    private Button confirm;
    @FXML
    private TextArea word_edit;

    @FXML
    private TextArea define_edit;
    private Dictionary dictionary = DictionaryController.dictionary;
    private DictionaryManagement dictionaryManagement = DictionaryController.dictionaryManagement;
    private final String path = DictionaryController.path;
    private ObservableList<String> list = FXCollections.observableArrayList();
    private int indexOfSelectedWord;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        dictionaryManagement.insertFromFile(dictionary, path);
//        dictionaryManagement.setTree(dictionary);
        editTable.setVisible(false);
        listWord.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        list.clear();
        for (int i = 0; i < 15; i++) {
            list.add(dictionary.get(i).getWordTarget());
        }
        listWord.setItems(list);

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
        List<String> listString = dictionaryManagement.lookupWord(dictionary, target);
        if (listString != null) {
            int length = Math.min(listString.size(), 15); // Chỉ lấy tối đa 15 từ

            for (int i = 0; i < length; i++) {
                list.add(listString.get(i));
            }
        }
        listWord.setItems(list);
        definition.setText("");
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
                List<String> listString = dictionaryManagement.lookupWord(dictionary, target);
                if (listString != null) {
                    int length = Math.min(listString.size(), 15); // Chỉ lấy tối đa 15 từ

                    for (int i = 0; i < length; i++) {
                        list.add(listString.get(i));
                    }
                }
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

    @FXML
    private void sound() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
            voice.speak(dictionary.get(indexOfSelectedWord).getWordTarget());
        } else throw new IllegalStateException("Cannot find voice: kevin16");
    }

    @FXML
    private void getSelectedWord() {
        selectedWord= listWord.getSelectionModel().getSelectedItem();
        word_edit.setText(selectedWord);
        define_edit.setText(dictionary.get(indexOfSelectedWord).getWordExplain());
    }
}
