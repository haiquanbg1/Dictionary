module dictionary.dictionary_ver2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires freetts;

    opens dictionary.dictionary_ver2 to javafx.fxml;
    exports dictionary.dictionary_ver2;
    exports dictionary.dictionary_ver2.Controllers;
    opens dictionary.dictionary_ver2.Controllers to javafx.fxml;
}