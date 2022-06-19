module com.project.password_manager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens com.project.password_manager to javafx.fxml;
    exports com.project.password_manager;
}