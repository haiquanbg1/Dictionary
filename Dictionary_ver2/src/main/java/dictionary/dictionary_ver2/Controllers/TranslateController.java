package dictionary.dictionary_ver2.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TranslateController implements Initializable {
    @FXML
    private TextArea from;
    @FXML
    private TextArea to;
    @FXML
    private ComboBox<String> yourLanguage;
    @FXML
    private ComboBox<String> targetLanguage;
    @FXML
    private Button transBtn;
    private String currentLanguage;
    private String translateLanguage;
    private ArrayList<String> languages= new ArrayList<>();
    private ArrayList<String> code= new ArrayList<>();
    ObservableList<String> listLanguages = FXCollections.observableArrayList("Vietnamese","English","Chinese","Thailand","German","Japanese","Korean");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        yourLanguage.setItems(listLanguages);
        targetLanguage.setItems(listLanguages);
        /*try {
            System.out.println(translate("vi", "th", "xin chào"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        transBtn.setDisable(true);
        from.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(from.getText().trim().isEmpty()){
                    transBtn.setDisable(true);
                }else {
                    transBtn.setDisable(false);
                }
            }
        });

        languages.add("Vietnamese");languages.add("English");languages.add("Chinese");languages.add("Thailand");languages.add("German");languages.add("Japanese");languages.add("Korean");
        code.add("vi");code.add("en");code.add("zh");code.add("th");code.add("de");code.add("ja");code.add("ko");
    }

    public void yourLanguageChanged(ActionEvent event) {
        String previousLanguage = currentLanguage;
        currentLanguage = yourLanguage.getValue();
        if(currentLanguage == translateLanguage) {
            targetLanguage.setValue(previousLanguage);
        }
    }

    @FXML
    private void translateAct(ActionEvent e) throws IOException {
        currentLanguage=yourLanguage.getValue();
        translateLanguage=targetLanguage.getValue();
        String text = from.getText();
        to.setText(translate(code.get(languages.indexOf(currentLanguage)).toString(), code.get(languages.indexOf(translateLanguage)).toString(), text));
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
