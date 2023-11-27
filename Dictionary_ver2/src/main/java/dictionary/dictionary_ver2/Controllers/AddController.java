package dictionary.dictionary_ver2.Controllers;


import dictionary.dictionary_ver2.DictionarySources.Dictionary;
import dictionary.dictionary_ver2.DictionarySources.DictionaryManagement;
import dictionary.dictionary_ver2.DictionarySources.Word;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    private Dictionary dictionary = DictionaryController.dictionary;
    private DictionaryManagement dictionaryManagement = DictionaryController.dictionaryManagement;
    private final String path = DictionaryController.path;


    @FXML
    private Button add;

    @FXML
    private TextField wordTargetInput;

    @FXML
    private TextArea explanationInput;

    @FXML
    private TextField wordDetailInput;

    @FXML
    private Label successAlert;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        dictionaryManagement.insertFromFile(dictionary, path);
        wordDetailInput.setText("//");

        /**
         * Chưa có gì không cho ấn add
         */
        if (explanationInput.getText().isEmpty() || wordTargetInput.getText().isEmpty()) {
            add.setDisable(true);
        }

        /**
         * Nhập từ và kiểm tra đã nhập
         */
        wordTargetInput.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (explanationInput.getText().isEmpty()
                        || wordTargetInput.getText().isEmpty()
                        || wordDetailInput.getText().isEmpty()) {
                    add.setDisable(true);
                } else add.setDisable(false);
            }
        });

        /**
         * Nhập nghĩa và kiểm tra đã nhập
         */
        explanationInput.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (explanationInput.getText().isEmpty()
                        || wordTargetInput.getText().isEmpty()
                        || wordDetailInput.getText().isEmpty()) {
                    add.setDisable(true);
                } else add.setDisable(false);
            }
        });

        /**
         * Nhập phiên âm và kiểm tra đã nhập
         */
        wordDetailInput.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (explanationInput.getText().isEmpty()
                        || wordTargetInput.getText().isEmpty()
                        || wordDetailInput.getText().isEmpty()) {
                    add.setDisable(true);
                } else add.setDisable(false);
            }
        });

        successAlert.setVisible(false);
    }

    /**
     * Xử lý khi ấn add
     */
    @FXML
    private void handleClickAddBtn() {
//        Alert alertConfirmation = alerts.alertConfirmation("Add word", "Bạn chắc chắn muốn thêm từ này?");
//        Optional<ButtonType> option = alertConfirmation.showAndWait();

        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add word");
        alert.setHeaderText("Bạn chắc chắn muốn thêm?");

        ButtonType buttonTypeYes= new ButtonType("Yes",ButtonBar.ButtonData.YES);
        ButtonType buttonTypeCancel= new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeCancel);
        Optional<ButtonType> result=alert.showAndWait();

        String englishWord = wordTargetInput.getText().trim().toLowerCase();
        String transcription = wordDetailInput.getText().trim().toLowerCase();
        String meaning = explanationInput.getText().trim().toLowerCase();

        if (result.get().getButtonData() == ButtonBar.ButtonData.YES) {
            int wordIndex = dictionaryManagement.searchWord(dictionary, englishWord);
            Word word = new Word(englishWord, transcription + "\n" + meaning);

            /** Nếu đã có từ vừa thêm */
            if (wordIndex != -1) {
                ButtonType replaceBtn = new ButtonType("Thay thế");
                ButtonType insertBtn = new ButtonType("Bổ sung");

                Alert alert1= new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("This word already exists");
                alert1.setHeaderText("Từ này đã tồn tại.\nThay thế hoặc bổ sung nghĩa vừa nhập cho nghĩa cũ.");

                alert1.getButtonTypes().setAll(replaceBtn,insertBtn,buttonTypeCancel);
                Optional<ButtonType> result1=alert1.showAndWait();

                if (result1.get() == replaceBtn) { // Nếu ấn thay thế
                    dictionary.get(wordIndex).setWordExplain(transcription + "\n" + meaning);
                    dictionaryManagement.exportToFile(dictionary, path);
                    showSuccessAlert();
                }
                if (result1.get() == insertBtn) { // Nếu ấn bổ sung
                    String oldMeaning = dictionary.get(wordIndex).getWordExplain();
                    dictionary.get(wordIndex).setWordExplain(oldMeaning + "\n= " + meaning);
                    dictionaryManagement.exportToFile(dictionary, path);
                    showSuccessAlert();
                }
                if (result1.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) { // Nếu ấn tắt
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("Information");
                    infoAlert.setHeaderText("Thay đổi không được công nhận.");
                    infoAlert.showAndWait();
                }
            } else { // Từ vừa nhập chưa có trong dic
                dictionary.add(word);
                dictionaryManagement.sortDictionary(dictionary);
                dictionaryManagement.addWord(word, path);
                showSuccessAlert();
            }
            add.setDisable(true);
            resetInput();
        } else if (result.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Information");
            infoAlert.setHeaderText("Thay đổi không được công nhận.");
            infoAlert.showAndWait();
        }
    }

    private void resetInput() {
        wordTargetInput.setText("");
        explanationInput.setText("");
        wordDetailInput.setText("//");
    }

    private void showSuccessAlert() {
        successAlert.setVisible(true);
        dictionaryManagement.setTimeout(() -> successAlert.setVisible(false), 1000);
    }
}