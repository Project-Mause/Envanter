package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sql_verilerim.Birim_Donustur;
import sql_verilerim.Birim_Veri_Al;
import sql_verilerim.Kategori_Veri_Al;
import sql_verilerim.Urun_Veri_Guncelle;
import verisiniflarimiz.Birim_Veri;
import verisiniflarimiz.Kategori_Veri;
import verisiniflarimiz.Veri_Aktarimiurun;

import java.io.IOException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Controller_UrunDuzenle {

    @FXML
    private JFXTextField txt_urunAdi;

    @FXML
    private JFXComboBox<String> combox_kategori;

    @FXML
    private JFXTextField txt_urunMiktarı;

    @FXML
    private JFXComboBox<String> combox_Birim;


    public void VeriYaz() throws SQLException {
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
        //veri tabanından gelen veriler ArrayList atanıyor
        ArrayList<Birim_Veri> getBirim;
        getBirim= Birim_Veri_Al.getInstance().BirimGetir();
        //JFXComboBox'larına verileri atayabilmemiz icin ArrayList ObservableList dönüstürülmek üzere ObservableList acılıyor
        ObservableList<String> birim=FXCollections.observableArrayList();
        //Veriler ObservableList atanıyor
        for (int i = 0; i < getBirim.size(); i++) {
            birim.add(getBirim.get(i).getBirimAdi());
        }
        //JFXComboBox atanıyor.
        combox_Birim.setItems(birim);
        txt_urunAdi.setText(Veri_Aktarimiurun.getInstance().getUrunAdi());
        txt_urunMiktarı.setText(Veri_Aktarimiurun.getInstance().getUrunMiktar());
        combox_Birim.setValue(Veri_Aktarimiurun.getInstance().getUrunBirim());
        combox_kategori.setValue(Veri_Aktarimiurun.getInstance().getUrunKategori());
    }

    @FXML
    void btn_urunduzenle(ActionEvent event) throws IOException {
        String urunadi;
        urunadi=txt_urunAdi.getText();

        String urunmiktari;
        urunmiktari=txt_urunMiktarı.getText();

        Format formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Buttona basıldığı andaki zamanı alıyor.
        Date today;
        today = Calendar.getInstance().getTime();
        //Bilgisayar zamanını veritabanına göndermek üzere zaman değişkenine atanıyor.
        String zaman;
        zaman = formatter.format(today);

        String urunKategori;
        urunKategori = combox_kategori.getSelectionModel().getSelectedItem();

        String urunBirim;
        urunBirim = combox_Birim.getSelectionModel().getSelectedItem();

        String urunID;
        urunID= Veri_Aktarimiurun.getInstance().getUrunId();

        Urun_Veri_Guncelle.getInstance().UrunGuncelle(urunID,urunadi,urunmiktari,zaman,urunKategori,urunBirim);

    }

}
