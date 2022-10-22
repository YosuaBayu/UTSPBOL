/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package daftarperpus;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Yosua Octavianus
 */
public class InputAnggotaController implements Initializable {

     boolean editdata=false;
    
    @FXML
    private Button btnquit;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnsimpan;
    @FXML
    private TextField txtangkatan;
    @FXML
    private TextField txtalamat;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtnpm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void execute(AnggotaModel d){
        if(!d.getNpm().isEmpty()){
          editdata=true;
          txtnpm.setText(d.getNpm());
          txtnama.setText(d.getNama());
          txtalamat.setText(d.getAlamat());
          txtangkatan.setText(String.valueOf(d.getAngkatan()));          
          txtnpm.setEditable(false);
          txtnama.requestFocus();         
        }}
    
    @FXML
    private void quitklik(ActionEvent event) {
        
         btnquit.getScene().getWindow().hide();
        
    }

    @FXML
    private void batalklik(ActionEvent event) {
        
        txtnpm.setText("");        
        txtnama.setText("");
        txtalamat.setText("");       
        txtangkatan.setText("");  
        txtnpm.requestFocus();
        
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        
        AnggotaModel n=new AnggotaModel();        
        n.setNpm(txtnpm.getText());
        n.setNama(txtnama.getText());     
        n.setAlamat(txtalamat.getText());  
        n.setAngkatan(txtangkatan.getText());
        
        FXMLDocumentController.dtanggota.setAnggotaModel(n);
        if(editdata){
            if(FXMLDocumentController.dtanggota.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtnpm.setEditable(true);        
               batalklik(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
            }else if(FXMLDocumentController.dtanggota.validasi(n.getNpm())<=0){
            if(FXMLDocumentController.dtanggota.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtnpm.requestFocus();
        }
        
    }
       
}
