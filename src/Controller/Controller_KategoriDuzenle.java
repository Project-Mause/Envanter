package Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sql_verilerim.Kategori_Veri_Al;
import sql_verilerim.Kategori_Veri_Duzenle;
import verisiniflarimiz.Kategori_Veri;

import java.util.ArrayList;

public class Controller_KategoriDuzenle {

    @FXML
    private Label lbl_islem;

    @FXML
    private JFXComboBox<String> combox_kategori;

    @FXML
    private JFXTextField txt_kategoriyeni;

    public void ComboBoxAddCategory(String secilecekitem){
        //veri tabanından gelen veriler ArrayList atanıyor
        ArrayList<Kategori_Veri> getkategoriler;
        getkategoriler = Kategori_Veri_Al.getInstance().KategoriGetir();
        //JFXComboBox'larına verileri atayabilmemiz icin ArrayList ObservableList dönüstürülmek üzere ObservableList acılıyor
        ObservableList<String> kategoriler = FXCollections.observableArrayList();
        //Veriler ObservableList atanıyor
        for (int i = 0; i < getkategoriler.size(); i++) {
            kategoriler.add(getkategoriler.get(i).getKategoriAdi());
        }
        //JFXComboBox atanıyor
        combox_kategori.setItems(kategoriler);
        combox_kategori.setValue(secilecekitem);

    }

    @FXML
    void btn_kategoriduzenle(ActionEvent event) {
        String secilen=combox_kategori.getSelectionModel().getSelectedItem();
        Kategori_Veri_Duzenle.getInstance().veriDuzenle(secilen,txt_kategoriyeni.getText());
        ComboBoxAddCategory(txt_kategoriyeni.getText());
    }

}