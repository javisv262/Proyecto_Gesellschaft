package com.proyecto_gesellschaft.controler;

import com.proyecto_gesellschaft.entity.Faust;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class InicioController {
    private Faust faust;

    @FXML
    private Label Saludo;
    @FXML
    private Button btnLogout;
    @FXML
    private ComboBox<String> cbFiltrado;
    @FXML
    private TableView<Faust> tablaFausts;
    @FXML
    private TableColumn<Faust,String> colUsuario;
    @FXML
    private TableColumn<Faust, String> colPermisos;
    @FXML
    private TableColumn<Faust,String> colLenguaje;

    @FXML
    protected void cerrarSesion(){

    }

    public void recibirProgramador(Faust faust){
        this.faust = faust;
    }
}
