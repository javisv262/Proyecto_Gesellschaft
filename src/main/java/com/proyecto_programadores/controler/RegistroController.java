package com.proyecto_programadores.controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroController {
    boolean usuario_valido = false;
    boolean password_valido = false;
    String usuario;
    String password;

    @FXML
    private Button btnIrLogin;
    @FXML
    private ComboBox<String> cbLenguajeRegistro;
    @FXML
    private TextField txtNombreRegistro;
    @FXML
    private TextField txtPasswordRegistro;
    @FXML
    private ToggleGroup grupoGenero;

    @FXML
    protected void initialize(){
        cbLenguajeRegistro.getItems().add("Java");
        cbLenguajeRegistro.getItems().add("Python");
        cbLenguajeRegistro.getItems().add("JavaScript");
    }
    @FXML
    protected void leerUsuario(){
        usuario = txtNombreRegistro.getText();
        if (usuario.length() <5){
            mostrarError("El usuario debe contener al menos 5 caracteres");
            txtNombreRegistro.clear();
            txtNombreRegistro.setPromptText("Introduce tu usuario(min 5 caracteres) ");
            usuario = null;
            usuario_valido = false;
        }
        else{
            usuario_valido = true;
        }
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
    private void mostrarError(String mensaje) {

        Alert alerta = new Alert(Alert.AlertType.ERROR);

        alerta.setTitle("Error");
        alerta.setHeaderText("Dato incorrecto");
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}
