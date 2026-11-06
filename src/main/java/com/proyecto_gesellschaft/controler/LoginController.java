package com.proyecto_gesellschaft.controler;

import com.proyecto_gesellschaft.DAO.FaustDAO;
import com.proyecto_gesellschaft.entity.Faust;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    FaustDAO faustDAO;
    String usuario;
    String password;
    boolean usuarioValido = false;
    boolean passwordValido = false;

    @FXML
    private Button btnRegistro;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtUsuarioLogin;
    @FXML
    private TextField txtPasswordLogin;

    @FXML
    protected void login(ActionEvent event) throws IOException {
        faustDAO = new FaustDAO();
        if(usuarioValido && passwordValido){
            Faust faust =  new Faust();
            faust.setUsuario(usuario);
           Faust p = faustDAO.comprobarUsuario(faust);
           if(p == null){
               mostrarError("Esta Faust no existe");
               return;
           }
           if(password.equals(p.getPassword())){
               lanzarInicio(event,p);

           }else {
               mostrarError("Contraseña incorrecta");
           }

        }else {
            mostrarError("Error: el usuario de Faust o la contraseña \n estan vacios asegurate de pulsar enter tras introducirlos");
        }
    }

    @FXML
    protected void leerUsuario(){
        usuario = txtUsuarioLogin.getText();
        if (usuario.length() <5){
            mostrarError("El usuario de Faust debe contener al menos 5 caracteres");
            txtUsuarioLogin.clear();
            txtUsuarioLogin.setPromptText("Introduce tu usuario(min 5 caracteres) ");
            usuario = null;
            usuarioValido = false;
        }
        else{
            usuarioValido = true;
        }
    }
    @FXML
    protected void leerPassword(){
        int mayus = 0;
        int minus = 0;
        password = txtPasswordLogin.getText();
        if (password.length() <8){
            mostrarError("La contraseña debe contener 8 caracteres como minimo");
            txtPasswordLogin.clear();
            txtPasswordLogin.setPromptText("Introduce tu contraseña ");
            password = null;
            passwordValido = false;
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
            txtPasswordLogin.clear();
            txtPasswordLogin.setPromptText("Introduce tu contraseña ");
            password = null;
            passwordValido = false;
            return;
        }
        passwordValido = true;

    }

    @FXML
    protected void lanzarRegistrar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/proyecto_gesellschaft/registro-view.fxml")
        );

        Parent root = loader.load();


        Stage stage = (Stage) ((Node) event.getSource())
                .getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }
    @FXML
    protected void lanzarInicio(ActionEvent event, Faust faust) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/proyecto_gesellschaft/inicio-view.fxml")
        );

        Parent root = loader.load();
        InicioController controlador = loader.getController();
        controlador.recibirFaust(faust);


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setWidth(464);
        stage.setHeight(435);
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
