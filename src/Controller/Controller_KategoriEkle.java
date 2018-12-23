package Controller;

import Text_Check.JFXTextField_Check;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import sql_verilerim.Kategori_Veri_Al;
import sql_verilerim.Kategori_Veri_Yolla;
import verisiniflarimiz.Kategori_Veri;

import java.util.ArrayList;


public class Controller_KategoriEkle
{

    @FXML
    private Label lbl_islem;

    @FXML
    private JFXTextField txt_kategori;

    @FXML
    void btn_kategoriekle(ActionEvent event) {
        //kategori TextField'daki veriyi kategori değişkenine atıyor.
        String kategori;
        kategori = txt_kategori.getText();
        ArrayList<Kategori_Veri> getkategoriler;
        getkategoriler = Kategori_Veri_Al.getInstance().KategoriGetir();
        boolean kontrol=true;
        //Veriler ObservableList atanıyor
        for (int i = 0; i < getkategoriler.size(); i++) {
           if (kategori == getkategoriler.get(i).getKategoriAdi()) {
               kontrol = false;
               break;
           }
        }
        if (kontrol) {
            //Kategori_veri_yolla sınıfımızdan kategoriEkle fonksiyonunu çağırarak kategori değişkenimizi eklemeyi sağlıyoruz.
            if (JFXTextField_Check.getInstance().Check(txt_kategori)) {
                //Kategori_Veri_Yolla txt_kategori TextField veri tabanına kategori ekleniyor
                Kategori_Veri_Yolla.getInstance().KategoriEkle(kategori);
                //lbl_islem Label rengini değiştirerek mesaj yazdırılıyor.
                lbl_islem.setTextFill(Color.GREEN);
                lbl_islem.setText("Kategori Başarıyla Eklendi");
            } else {
                //lbl_islem Label rengini değiştirerek mesaj yazdırılıyor.
                lbl_islem.setTextFill(Color.RED);
                lbl_islem.setText("Kategori Eklenemedi");
            }
        }else {
            lbl_islem.setTextFill(Color.RED);
            lbl_islem.setText("Kategori Bulunuyor.");
        }
    }

}
