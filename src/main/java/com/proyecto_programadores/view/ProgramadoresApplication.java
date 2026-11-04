package com.proyecto_programadores.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProgramadoresApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProgramadoresApplication.class.getResource("/com/proyecto_programadores/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 323, 465);
        stage.setTitle("Comunidad de Programadores");
        stage.setScene(scene);
        stage.show();
    }
}
