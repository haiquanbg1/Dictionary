package dictionary.dictionary_ver2.Controllers;

import dictionary.dictionary_ver2.Database.DatabaseConnection;
import dictionary.dictionary_ver2.DictionarySources.Dictionary;
import dictionary.dictionary_ver2.DictionarySources.DictionaryManagement;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DictionaryController implements Initializable {
    @FXML
    private Tooltip tooltip1, tooltip2, tooltip3,tooltip4;

    @FXML
    private Button addWordBtn, translateBtn, searchWordBtn, closeBtn, gameBtn;

    @FXML
    private AnchorPane container;

    public static Dictionary dictionary = new Dictionary();
    public static DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public static final String path = "src/main/resources/Texts/dictionaries.txt";
    public static Connection conn;

    static {
        try {
            conn = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
        dictionaryManagement.insertFromFile(dictionary, path);
        dictionaryManagement.setTree(dictionary);

        tooltip1.setShowDelay(Duration.seconds(0.3));
        tooltip2.setShowDelay(Duration.seconds(0.3));
        tooltip3.setShowDelay(Duration.seconds(0.3));
        tooltip4.setShowDelay(Duration.seconds(0.3));
        try {
            showComponent("/Views/search.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        };
        closeBtn.setOnMouseClicked(e -> {
            System.exit(0);
        });

        addWordBtn.setOnMouseClicked(e -> {
            try {
                showComponent("/Views/add.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        translateBtn.setOnMouseClicked(e -> {
            try {
                showComponent("/Views/translate.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        searchWordBtn.setOnMouseClicked(e -> {
            try {
                showComponent("/Views/search.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        gameBtn.setOnMouseClicked(e -> {
            try {
                showComponent("/Views/game.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}