package com.proyecto_programadores.controler;

import com.proyecto_programadores.DAO.ProgramadorDAO;
import com.proyecto_programadores.entity.Programador;
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
    ProgramadorDAO programadorDAO;
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
    private ToggleGroup grupoGenero;

    @FXML
    protected void initialize(){
        cbLenguajeRegistro.getItems().add("Java");
        cbLenguajeRegistro.getItems().add("Python");
        cbLenguajeRegistro.getItems().add("JavaScript");
    }
    @FXML
    protected void registrarProgramador(){
        programadorDAO = new ProgramadorDAO();
        if (usuario_valido && password_valido){
            if (grupoGenero.getSelectedToggle() != null){
                RadioButton rb = (RadioButton) grupoGenero.getSelectedToggle();
                String genero = rb.getText();
                if (cbLenguajeRegistro.getValue() != null){
                    String lenguaje = cbLenguajeRegistro.getValue();
                    Programador programador = new Programador(usuario,password,genero,lenguaje);
                   programadorDAO.altaProgramador(programador);
                   mostrarAviso("Programador registrado con exito");
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
        programadorDAO = new ProgramadorDAO();
        usuario = txtNombreRegistro.getText();
        if (usuario.length() <5){
            mostrarError("El usuario debe contener al menos 5 caracteres");
            txtNombreRegistro.clear();
            txtNombreRegistro.setPromptText("Introduce tu usuario(min 5 caracteres) ");
            usuario = null;
            usuario_valido = false;
        }
        else{
            Programador programador =  new Programador();
            programador.setUsuario(usuario);
            Programador p = programadorDAO.comprobarUsuario(programador);
            if (p != null){
                mostrarError("El usuario ya existe");
                txtNombreRegistro.clear();
                txtNombreRegistro.setPromptText("Introduce tu usuario(min 5 caracteres) ");
                usuario = null;
                usuario_valido = false;
            }else {
                usuario_valido = true;
            }

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
    private void mostrarAviso(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(mensaje);
        //alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}
