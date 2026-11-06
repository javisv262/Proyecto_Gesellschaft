package com.proyecto_gesellschaft.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FaustApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FaustApplication.class.getResource("/com/proyecto_gesellschaft/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 323, 465);
        stage.setTitle("The Gesellschaft");
        stage.setScene(scene);
        stage.show();
    }
}
