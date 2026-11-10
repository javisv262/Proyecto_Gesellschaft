package com.proyecto_gesellschaft.controler;

import com.proyecto_gesellschaft.DAO.FaustDAO;
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

public class RegistroController {
    FaustDAO faustDAO;
    boolean usuario_valido = false;
    boolean password_valido = false;
    String usuario;
    String password;

    @FXML
    private Button btnIrLogin;
    @FXML
    private Button btnRegistrar;
    @FXML
    private ComboBox<String> cbLenguajeRegistro;
    @FXML
    private TextField txtNombreRegistro;
    @FXML
    private TextField txtPasswordRegistro;
    @FXML
    private ToggleGroup grupoPermisos;

    @FXML
    protected void initialize(){
        cbLenguajeRegistro.getItems().add("Java");
        cbLenguajeRegistro.getItems().add("Python");
        cbLenguajeRegistro.getItems().add("JavaScript");
    }
    @FXML
    protected void registrarProgramador(){
        faustDAO = new FaustDAO();
        if (usuario_valido && password_valido){
            if (grupoPermisos.getSelectedToggle() != null){
                RadioButton rb = (RadioButton) grupoPermisos.getSelectedToggle();
                String permisos = rb.getText();
                if (cbLenguajeRegistro.getValue() != null){
                    String lenguaje = cbLenguajeRegistro.getValue();
                    Faust faust = new Faust(usuario,password,permisos,lenguaje);
                    faustDAO.altaFaust(faust);
                   mostrarAviso("Faust registrada con exito");
                   txtNombreRegistro.clear();
                   txtPasswordRegistro.clear();
                   usuario_valido = false;
                   password_valido = false;
                }else {
                    mostrarError("Campos vacios");
                }
            }else  {
                mostrarError("Campos vacios");
            }

        }else {
            mostrarError("Usuario o Contraseña Invalido");
        }

    }
    @FXML
    protected void leerPassword(){
        int mayus = 0;
        int minus = 0;
        password = txtPasswordRegistro.getText();
        if (password.length() <8){
            mostrarError("La contraseña debe contener 8 caracteres como minimo");
            txtPasswordRegistro.clear();
            txtPasswordRegistro.setPromptText("Introduce tu contraseña ");
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
            txtPasswordRegistro.clear();
            txtPasswordRegistro.setPromptText("Introduce tu contraseña ");
            password = null;
            password_valido = false;
            return;
        }
        password_valido = true;
    }
    @FXML
    protected void leerUsuario(){
        faustDAO = new FaustDAO();
        usuario = txtNombreRegistro.getText();
        if (usuario.length() <5){
            mostrarError("El usuario debe contener al menos 5 caracteres");
            txtNombreRegistro.clear();
            txtNombreRegistro.setPromptText("Introduce tu usuario(min 5 caracteres) ");
            usuario = null;
            usuario_valido = false;
        }
        else{
            Faust faust =  new Faust();
            faust.setUsuario(usuario);
            Faust p = faustDAO.comprobarUsuario(faust);
            if (p != null){
                mostrarError("El usuario ya pertenece a otra Faust");
                txtNombreRegistro.clear();
                txtNombreRegistro.setPromptText("Introduce tu usuario(min 5 caracteres) ");
                usuario = null;
                usuario_valido = false;
            }else {
                usuario_valido = true;
                txtPasswordRegistro.requestFocus();
            }

        }
    }
    @FXML
    protected void lanzarLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/proyecto_gesellschaft/login-view.fxml")
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
    private void mostrarAviso(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(mensaje);
        //alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}
