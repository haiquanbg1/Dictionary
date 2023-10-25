package dictionary.dictionary_ver2.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    VBox vbox = new VBox();
    @FXML
    Canvas canvas = new Canvas();


    Scene scene = new Scene(vbox);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void run(GraphicsContext gc) {

    }
}
