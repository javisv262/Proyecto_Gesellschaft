package com.proyecto_programadores.controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroController {

    @FXML
    private Button btnIrLogin;
    @FXML
    private ComboBox<String> cbLenguajeRegistro;

    @FXML
    protected void initialize(){
        cbLenguajeRegistro.getItems().add("Java");
        cbLenguajeRegistro.getItems().add("Python");
        cbLenguajeRegistro.getItems().add("JavaScript");
    }
    @FXML
    protected void lanzarLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/proyecto_programadores/login-view.fxml")
        );

        Parent root = loader.load();


        Stage stage = (Stage) ((Node) event.getSource())
                .getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }
}
