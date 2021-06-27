module nowy {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;
    exports spacegierka to javafx.graphics;
    opens spacegierka to javafx.fxml;
}