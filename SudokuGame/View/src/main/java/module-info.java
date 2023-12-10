module com.example.viewproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;
    requires Model;
    opens com.example.viewproject to javafx.fxml;
    exports com.example.viewproject;
}