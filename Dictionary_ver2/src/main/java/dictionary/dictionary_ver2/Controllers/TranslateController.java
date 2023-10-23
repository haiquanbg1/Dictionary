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

import java.util.ResourceBundle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

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
        try {
            System.out.println(translate("vi", "ja", "Xin chào"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void yourLanguageChanged(ActionEvent event) {
        String previousLanguage = currentLanguage;
        currentLanguage = yourLanguage.getValue();
        if(currentLanguage == translateLanguage) {
            targetLanguage.setValue(previousLanguage);
        }
    }

    private static String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbzSurh80OaNcOk-xLeUyFPPpWfG99ADO_3f-B88YsSAstlOTokJ7iQ-bG7Fy9zTiAMB/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public void targetLanguageChanged(ActionEvent event) {
        translateLanguage = targetLanguage.getValue();
    }
}
