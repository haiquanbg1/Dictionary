package dictionary.dictionary_ver2.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController implements Initializable {
    private String currentLanguage="vietnamese";
    @FXML
    private Label Vietnamese;
    @FXML
    private Label english;
    @FXML
    private TextField from;
    @FXML
    private TextArea to;
    @FXML
    private ImageView VNFlag;
    @FXML
    private ImageView EFlag;
    public void switchLanguage (ActionEvent event) {
        if(currentLanguage.equals("vietnamese")) {
            Vietnamese.setLayoutX(519.0);
            english.setLayoutX(148.0);
            currentLanguage="english";
            VNFlag.setLayoutX(482.0);
            EFlag.setLayoutX(111.0);
        } else {
            Vietnamese.setLayoutX(148.0);
            english.setLayoutX(519.0);
            currentLanguage="vietnamese";
            VNFlag.setLayoutX(111.0);
            EFlag.setLayoutX(482.0);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
