/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package daftarperpus;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yosua Octavianus
 */
public class DataAnggotaController implements Initializable {

    private TableView<AnggotaModel> tbvpendaftaran;
    @FXML
    private Button btnquit;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnawal;
    private TextField searchanggota;
    @FXML
    private TableView<AnggotaModel> tbvanggota;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showdata();

    }

    public void showdata() {
        ObservableList<AnggotaModel> data = FXMLDocumentController.dtanggota.Load();
        if (data != null) {
            tbvanggota.getColumns().clear();
            tbvanggota.getItems().clear();
            TableColumn col = new TableColumn("NPM");
            col.setCellValueFactory(new PropertyValueFactory<AnggotaModel, String>("npm"));
            tbvanggota.getColumns().addAll(col);
            col = new TableColumn("Nama");
            col.setCellValueFactory(new PropertyValueFactory<AnggotaModel, String>("Nama"));
            tbvanggota.getColumns().addAll(col);
            col = new TableColumn("Alamat");
            col.setCellValueFactory(new PropertyValueFactory<AnggotaModel, String>("Alamat"));
            tbvanggota.getColumns().addAll(col);
            col = new TableColumn("Angkatan");
            col.setCellValueFactory(new PropertyValueFactory<AnggotaModel, String>("Angkatan"));
            tbvanggota.getColumns().addAll(col);

            col = new TableColumn("status");
            col.setCellValueFactory(new PropertyValueFactory<AnggotaModel, String>("status"));
            tbvanggota.getColumns().addAll(col);
            tbvanggota.setItems(data);
        }
    }

    @FXML
    private void quitklik(ActionEvent event) {

        btnquit.getScene().getWindow().hide();

    }

    @FXML
    private void hapusklik(ActionEvent event) {

     AnggotaModel s= new AnggotaModel();       
        s=tbvanggota.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtanggota.delete(s.getNpm())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,"Data berhasil dihapus", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"Data gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();           
           awalklik(event);       
        }

    }

    @FXML
    private void updateklik(ActionEvent event) {

        AnggotaModel s = new AnggotaModel();
        s = (AnggotaModel) tbvanggota.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InputAnggota.fxml"));
            Parent root = (Parent) loader.load();
            InputAnggotaController isidt = (InputAnggotaController) loader.getController();
            isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdata();
        awalklik(event);

    }

    @FXML
    private void tambahklik(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InputAnggota.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdata();
        awalklik(event);

    }

    @FXML
    private void akhirklik(ActionEvent event) {

        tbvanggota.getSelectionModel().selectLast();
        tbvanggota.requestFocus();

    }

    @FXML
    private void sebelumklik(ActionEvent event) {

        tbvanggota.getSelectionModel().selectAboveCell();
        tbvanggota.requestFocus();

    }

    @FXML
    private void sesudahklik(ActionEvent event) {

        tbvanggota.getSelectionModel().selectBelowCell();
        tbvanggota.requestFocus();

    }

    @FXML
    private void awalklik(ActionEvent event) {

        tbvanggota.getSelectionModel().selectFirst();
        tbvanggota.requestFocus();

    }

}
