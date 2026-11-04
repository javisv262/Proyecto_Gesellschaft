module com.example.proyecto_programadores {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.proyecto_programadores to javafx.fxml;
    exports com.example.proyecto_programadores;
}