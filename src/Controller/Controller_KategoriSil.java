package Controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sql_verilerim.Kategori_Veri_Al;
import sql_verilerim.Kategori_Veri_Sil;
import sql_verilerim.Urun_Veri_Sil;
import verisiniflarimiz.Kategori_Veri;

import java.util.ArrayList;

public class Controller_KategoriSil {

    @FXML
    private JFXComboBox<String> combox_kategori;

    public void ComboBoxAddCategory(){
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
    }

    @FXML
    void btn_kategorisil(ActionEvent event) {
        String veri = combox_kategori.getSelectionModel().getSelectedItem();
        Urun_Veri_Sil.getInstance().veriSilK(veri);
        Kategori_Veri_Sil.getInstance().veriSil(veri);
        ComboBoxAddCategory();
    }

}
