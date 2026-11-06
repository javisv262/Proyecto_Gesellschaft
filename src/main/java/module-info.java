module com.example.proyecto_programadores {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires db4o;

    opens com.proyecto_gesellschaft to javafx.fxml;
    exports com.proyecto_gesellschaft;
    exports com.proyecto_gesellschaft.view;
    opens com.proyecto_gesellschaft.view to javafx.fxml;
    exports com.proyecto_gesellschaft.controler;
    opens com.proyecto_gesellschaft.controler to javafx.fxml;
    opens com.proyecto_gesellschaft.entity to db4o;
}