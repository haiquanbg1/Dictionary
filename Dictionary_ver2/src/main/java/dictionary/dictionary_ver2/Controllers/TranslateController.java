package dictionary.dictionary_ver2.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController implements Initializable {
    @FXML
    private TextField from;
    @FXML
    private TextArea to;
    @FXML
    private ComboBox<String> yourLanguage;
    @FXML
    private ComboBox<String> targetLanguage;

    private String currentLanguage;
    private String translateLanguage;
    ObservableList<String> listLanguages = FXCollections.observableArrayList("Vietnamese","English","Chinese","Thailand");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        yourLanguage.setItems(listLanguages);
        targetLanguage.setItems(listLanguages);
    }

    public void yourLanguageChanged(ActionEvent event) {
        currentLanguage = yourLanguage.getValue();
    }

    public void targetLanguageChanged(ActionEvent event) {
        translateLanguage = targetLanguage.getValue();
    }

}
