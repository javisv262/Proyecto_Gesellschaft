package com.proyecto_gesellschaft.controler;

import com.proyecto_gesellschaft.DAO.FaustDAO;
import com.proyecto_gesellschaft.entity.Faust;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class InicioController {
    FaustDAO faustDAO;
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
    protected void initialize(){
        faustDAO = new FaustDAO();
        cbFiltrado.getItems().addAll("Todos","Java","Python","JavaScript");
        cbFiltrado.getSelectionModel().select(0);
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colPermisos.setCellValueFactory(new PropertyValueFactory<>("permisos"));
        colLenguaje.setCellValueFactory(new PropertyValueFactory<>("lenguaje"));

        ObservableList<Faust> lista =
                FXCollections.observableArrayList(
                        faustDAO.listarFausts(cbFiltrado.getValue(),faust));
        tablaFausts.setItems(lista);
    }
    @FXML
    protected void cerrarSesion(){

    }

    public void recibirProgramador(Faust faust){
        this.faust = faust;
    }
}
