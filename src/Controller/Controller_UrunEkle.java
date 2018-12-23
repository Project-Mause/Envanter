package Controller;

import Text_Check.JFXTextField_Check;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sql_verilerim.Birim_Veri_Al;
import sql_verilerim.Kategori_Veri_Al;
import sql_verilerim.Urun_Veri_Yolla;
import verisiniflarimiz.Birim_Veri;
import verisiniflarimiz.Kategori_Veri;

import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Controller_UrunEkle
{

    @FXML
    private JFXComboBox<String> combox_kategori;
    @FXML
    private JFXComboBox<String> combox_Birim;

    @FXML
    private JFXTextField txt_urunMiktarı;

    @FXML
    private JFXTextField txt_urunAdi;

    @FXML
    private Label lbl_islem;

    //JFXComboBox'larımıza veritabanından gelen verilerin yazdırılma fonksiyonu
    public void VeriYaz(){
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
    }

    @FXML
    void btn_urunekle(ActionEvent event) throws SQLException {
        //txt_urunAdi TextFieldındaki verileri alıp urunAdi değişkenine atandı.
        String urunAdi;
        urunAdi=txt_urunAdi.getText();
        //txt_urunMiktarı TextFieldındaki verileri alıp urunMiktari değişkenine atandı.
        String urunMiktari;
        urunMiktari=txt_urunMiktarı.getText();
        //Zamanı yıl-ay-gun saat-dakika-saniye göre formatlanıyor.
        Format formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Buttona basıldığı andaki zamanı alıyor.
        Date today;
        today = Calendar.getInstance().getTime();
        //Bilgisayar zamanını veritabanına göndermek üzere zaman değişkenine atanıyor.
        String zaman;
        zaman = formatter.format(today);
        //combox_kategori JFXComboBox secilen veriyi alıp kategori değişkenine atandı.
        String kategori;
        kategori = combox_kategori.getSelectionModel().getSelectedItem();
        //combox_Birim JFXComboBox secilen veriyi alıp birim değişkenine atandı.
        String birim;
        birim = combox_Birim.getSelectionModel().getSelectedItem();
        //kontrol sağlanıyor ve şartlar eğer yerine geldiyse ic kısımdaki if giriyor karşılanmasıysa Label'a veri aktarılıyor
        if (txt_urunadi() && txt_urunmiktar() && cmb_urunkategori() && cmb_urunbirim()) {
            //Urun veriler veritabanına yollanıyor eğer veritabanına gidiş gerçekleşirse alttaki komutlar aktif oluyor.
        if (Urun_Veri_Yolla.getInstance().UrunEkle(urunAdi,urunMiktari,zaman,kategori,birim)){
            //lbl_islem Label renk yeşil yapılıyor
            lbl_islem.setTextFill(Color.GREEN);
            //lbl_islem Label mesaj dönderiliyor
            lbl_islem.setText("Ürün Başarıyla Eklendi");
        }
        else{
            //lbl_islem Label renk kırımızı yapılıyor
            lbl_islem.setTextFill(Color.RED);
            //lbl_islem Label mesaj gönderiliyor
            lbl_islem.setText("Ürün Eklenemedi");
        }}else{
            //lbl_islem Label renk yeşil yapılıyor
            lbl_islem.setTextFill(Color.RED);
            //lbl_islem Label mesaj gönderiliyor
            lbl_islem.setText("Ürün Eklenemedi");
        }
    }
    //txt_urunadi içinde 50 karakterden fazla isim icerip icermediğini kontrol ediyor
    @FXML
    boolean txt_urunadi() {
        boolean check;
        check= JFXTextField_Check.getInstance().Check(txt_urunAdi);
        return check;
    }
    //txt_urunmiktar içinde 50 karakterden fazla isim icerip icermediğini kontrol ediyor
    @FXML
    boolean txt_urunmiktar() {
        boolean check;
        check= JFXTextField_Check.getInstance().Check(txt_urunMiktarı);
        return check;
    }
    //cmb_urunbirim seçilip seçilmediğini kontrol ediyor.
    @FXML
    boolean cmb_urunbirim() {
        boolean check;
        check=JFXTextField_Check.getInstance().CheckCombo(combox_Birim);
        return check;
    }
    //cmb_urunbirim seçilip seçilmediğini kontrol ediyor.
    @FXML
    boolean cmb_urunkategori() {
        boolean check;
        check=JFXTextField_Check.getInstance().CheckCombo(combox_kategori);
        return check;
    }
}
