package dictionary.dictionary_ver2.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    @FXML
    private Button switchBtn;

    private Stage stage;
    private Scene scene;

    public void switchAct(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/dictionary.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Dictionary App");
        scene = new Scene(root);
        scene.getRoot().requestFocus();
        stage.setScene(scene);
        stage.show();
    }

    public void exitAct(ActionEvent event) throws IOException {
        System.exit(0);
    }
}
