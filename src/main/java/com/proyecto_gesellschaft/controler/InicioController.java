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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openpdf.text.Document;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
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
    private Button btnExcel;
    @FXML
    private Button btnPDF;
    @FXML
    private Button btnEditar;

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
    protected void exportarPDF(){
        FileChooser selector = new FileChooser();
        selector.setTitle("Guardar registros Gesellschaft");
        selector.setInitialFileName("Gesellschaft.pdf");

        selector.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF(*.pdf)", "*.pdf"));

        File archivo = selector.showSaveDialog(tablaFausts.getScene().getWindow());
        if(archivo == null){
            return;
        }
        if (!archivo.getName().toLowerCase().endsWith(".pdf")) {
            archivo = new File(archivo.getAbsolutePath()+".pdf");
        }
        try(FileOutputStream salida = new FileOutputStream(archivo)) {
            Document documento = new Document();
            try {
                PdfWriter.getInstance((documento),salida);
                documento.open();

                documento.add(new Paragraph("Registro Gesellschaft\n\n"));
                PdfPTable tabla = new PdfPTable(3);
                tabla.setWidthPercentage(100);

                tabla.addCell("Usuario");
                tabla.addCell("Permisos");
                tabla.addCell("Lenguaje");
                tabla.setHeaderRows(1);

                for (Faust f : tablaFausts.getItems()) {
                    tabla.addCell(f.getUsuario());
                    tabla.addCell(f.getPermisos());
                    tabla.addCell(f.getLenguaje());
                }
                documento.add(tabla);
            }finally {
                documento.close();
            }
            mostrarAviso("PDF exportado con exito");

        }catch (IOException e){
           mostrarError("Error al exportar PDF"+e.getMessage());
        }

    }
    @FXML
    protected void exportarExcel(){
        FileChooser selector = new FileChooser();
        selector.setTitle("Guardar registros Gesellschaft");
        selector.setInitialFileName("Gesellschaft.xlsx");

        selector.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel (*.xlsx)", "*.xlsx"));

        File archivo = selector.showSaveDialog(tablaFausts.getScene().getWindow());

        if (archivo == null) {
            return;
        }

        if (!archivo.getName().toLowerCase().endsWith(".xlsx")) {
            archivo = new File(archivo.getAbsolutePath() + ".xlsx");
        }
        try (Workbook libro = new XSSFWorkbook();
             FileOutputStream salida = new FileOutputStream(archivo);){
            Sheet hoja = libro.createSheet("Fausts");

            Row cabecera = hoja.createRow(0);
            cabecera.createCell(0).setCellValue("Usuario");
            cabecera.createCell(1).setCellValue("Permisos");
            cabecera.createCell(2).setCellValue("Lenguaje");
            int numerofila = 1;
            for (Faust f : tablaFausts.getItems()) {
                Row fila = hoja.createRow(numerofila);
                fila.createCell(0).setCellValue(f.getUsuario());
                fila.createCell(1).setCellValue(f.getPermisos());
                fila.createCell(2).setCellValue(f.getLenguaje());
                numerofila++;
            }
            for (int i = 0; i < 3; i++) {
                hoja.autoSizeColumn(i);
            }
            libro.write(salida);
            mostrarAviso("Excel exportado correctamente");

        }catch (IOException e){
            mostrarError("Error al exportar el archivo: "+e.getMessage());

        }
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
    @FXML
    protected void lanzarEditar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/proyecto_gesellschaft/editar-view.fxml")
        );
        loader.setControllerFactory(tipo -> new EditarController(faust));

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
    private void mostrarError(String mensaje) {

        Alert alerta = new Alert(Alert.AlertType.ERROR);

        alerta.setTitle("Error");
        alerta.setHeaderText("Error");
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }

//    public void recibirFaust(Faust faust){
//        this.faust = faust;
//    }
}
