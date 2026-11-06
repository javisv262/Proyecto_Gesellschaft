package com.proyecto_gesellschaft.controler;

import com.proyecto_gesellschaft.DAO.FaustDAO;
import com.proyecto_gesellschaft.entity.Faust;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioController {
    FaustDAO faustDAO;
    private Faust faust;
    public InicioController(Faust faust){
        this.faust = faust;
    }

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
        Saludo.setText("Faust actual: "+faust.getUsuario().substring(0,Math.min(12,faust.getUsuario().length())));
    }
    @FXML
    protected void filtrar(){
        ObservableList<Faust> lista =
                FXCollections.observableArrayList(
                        faustDAO.listarFausts(cbFiltrado.getValue(),faust));
        tablaFausts.setItems(lista);

    }
    @FXML
    protected void cerrarSesion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/proyecto_gesellschaft/login-view.fxml")
        );

        Parent root = loader.load();


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

    }
    private void mostrarAviso(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(mensaje);
        //alerta.setContentText(mensaje);

        alerta.showAndWait();
    }

//    public void recibirFaust(Faust faust){
//        this.faust = faust;
//    }
}
