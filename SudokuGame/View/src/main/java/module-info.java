module com.example.viewproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires Model;
    opens com.example.viewproject to javafx.fxml;
    exports com.example.viewproject;
}