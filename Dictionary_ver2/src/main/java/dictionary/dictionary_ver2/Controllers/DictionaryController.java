package dictionary.dictionary_ver2.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DictionaryController implements Initializable {
    @FXML
    private Tooltip tooltip1, tooltip2, tooltip3,tooltip4;

    @FXML
    private Button addWordBtn, translateBtn, searchWordBtn, closeBtn, gameBtn;

    @FXML
    private AnchorPane container;

    @FXML
    private void setNode(Node node) {
        container.getChildren().clear();
        container.getChildren().add(node);
    }

    private void showComponent(String path) throws IOException {
        AnchorPane component = FXMLLoader.load(getClass().getResource(path));
        setNode(component);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tooltip1.setShowDelay(Duration.seconds(0.3));
        tooltip2.setShowDelay(Duration.seconds(0.3));
        tooltip3.setShowDelay(Duration.seconds(0.3));
        tooltip4.setShowDelay(Duration.seconds(0.3));

        closeBtn.setOnMouseClicked(e -> {
            System.exit(0);
        });
    }

    public void searchView(ActionEvent event) throws IOException {
        showComponent("/Views/search.fxml");
    }
    public void addView(ActionEvent event) throws IOException {
        showComponent("/Views/add.fxml");
    }
    public void gameView(ActionEvent event) throws IOException {
        showComponent("/Views/game.fxml");
    }
    public void translateView(ActionEvent event) throws IOException {
        showComponent("/Views/translate.fxml");
    }
}