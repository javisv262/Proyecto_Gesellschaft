module com.example.proyecto_programadores {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires db4o;

    opens com.proyecto_programadores to javafx.fxml;
    exports com.proyecto_programadores;
    exports com.proyecto_programadores.view;
    opens com.proyecto_programadores.view to javafx.fxml;
    exports com.proyecto_programadores.controler;
    opens com.proyecto_programadores.controler to javafx.fxml;
    opens com.proyecto_programadores.entity to db4o;
}