package com.proyecto_gesellschaft.controler;

import com.proyecto_gesellschaft.entity.Faust;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class EditarController {
    private Faust faust;
    @FXML
    private TextField txtNombreEditar;
    @FXML
    private TextField txtPasswordEditar;
    @FXML
    private ToggleGroup grupoPermisos;
    @FXML
    private RadioButton rbAdmin;
    @FXML
    private RadioButton rbEstandar;
    @FXML
    private ComboBox<String> cbLenguajeEditar;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    boolean password_valido = false;
    String password;


    public EditarController(Faust faust) {this.faust = faust;}

    @FXML
    protected void initialize(){
        cbLenguajeEditar.getItems().add("Java");
        cbLenguajeEditar.getItems().add("Python");
        cbLenguajeEditar.getItems().add("JavaScript");
        txtNombreEditar.setText(faust.getUsuario());
        txtPasswordEditar.setText(faust.getPassword());
        for (Toggle toggle:grupoPermisos.getToggles()){
            RadioButton rb = (RadioButton) toggle;
            if (rb.getText().equals(faust.getPermisos())){
                grupoPermisos.selectToggle(toggle);
                break;
            }
        }
        cbLenguajeEditar.setValue(faust.getLenguaje());

    }
    @FXML
    protected void leerPassword(){
        int mayus = 0;
        int minus = 0;
        password = txtPasswordEditar.getText();
        if (password.length() <8){
            mostrarError("La contraseña debe contener 8 caracteres como minimo");
            txtPasswordEditar.clear();
            txtPasswordEditar.setPromptText("Introduce tu contraseña ");
            password = null;
            password_valido = false;
            return;
        }
        for (int i = 0; i < password.length(); i++) {

            if (Character.isUpperCase(password.charAt(i))) {
                mayus++;
            }else if (Character.isLowerCase(password.charAt(i))) {
                minus++;
            }
        }
        if (mayus <1 ||minus <1){
            mostrarError("La contraseña debe contener como minimo \n 1 mayuscula y 1 minuscula");
            txtPasswordEditar.clear();
            txtPasswordEditar.setPromptText("Introduce tu contraseña ");
            password = null;
            password_valido = false;
            return;
        }
        password_valido = true;
    }
    @FXML
    protected void GuardarCambios(){
        if (password_valido){
            if (grupoPermisos.getSelectedToggle() != null){
                RadioButton rb = (RadioButton) grupoPermisos.getSelectedToggle();
                String permisos = rb.getText();
                String lenguaje = cbLenguajeEditar.getValue();
                faust.setPassword(password);
                faust.setPermisos(permisos);
                faust.setLenguaje(lenguaje);

            }else {
                mostrarError("Error, asegurate de que \n el apartado de permisos no este en blanco");
            }
        }else{
            mostrarError("Error en la contraseña, \n asegurate de que cumpla con los requisitos");
        }

    }
    @FXML
    protected void volverInicio(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/proyecto_gesellschaft/inicio-view.fxml")
        );
        loader.setControllerFactory(tipo -> new InicioController(faust));

        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.sizeToScene();
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
