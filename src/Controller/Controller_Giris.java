package Controller;

import Text_Check.JFXTextField_Check;
import Visualization.Screenbox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import sql_verilerim.Kullanici_Veri_Al;
import verisiniflarimiz.Kullanici_Veri;
import verisiniflarimiz.Veri_Aktarimi;

import java.io.IOException;
import java.util.ArrayList;

public class Controller_Giris
{
    //JFXTextField FXML ile classlar arası bağlantı için değişkene atandı
    @FXML
    private JFXTextField txt_kullanici;
    //JFXTextField FXML ile classlar arası bağlantı için değişkene atandı
    @FXML
    private JFXPasswordField txt_sifre;
    //Label FXML ile classlar arası bağlantı için değişkene atandı
    @FXML
    private Label lbl_bilgi;
    //FXML Index URL'si
    private String girisUrl="../design/proje1.fxml";
    //FXML Index URL'si
    private String kayitUrl="../design/proje1kayit.fxml";
    //JFXTextField üzerine gelince mesaj çıkmasını sağlıyor
    private Tooltip tooltip_k=new Tooltip();
    //JFXTextField üzerine gelince mesaj çıkmasını sağlıyor
    private Tooltip tooltip_s=new Tooltip();

    public void Tooltipfunction() {
          //Tooltip içindeki veriyi yazdırıyorum.
          tooltip_k.setText("Kullanici_Veri_Al Adınız Örnek:'example@hotmail.com' ");
          //Tooltip'i txt_kullanicimi içine atıyorum.
          txt_kullanici.setTooltip(tooltip_k);
          //Tooltip içindeki veriyi yazdırıyorum.
          tooltip_s.setText("Şifreniz Örnek:'Example.0'");
          //Tooltip'i txt_sifre içine atıyorum.
          txt_sifre.setTooltip(tooltip_s);

      }
    @FXML
    void btn_giris(ActionEvent event) throws IOException {
          //Kullanici TextFieldındaki verileri alıp kullanici değişkenine atandı.
          String kullanici;
          kullanici=txt_kullanici.getText();
          //Veri_Aktarimi sınıfımın içine verimi atıyorum ve Veri_Aktarimi sınıfı buffer gibi kullanıp classlar arası veri taşınıyor.
          Veri_Aktarimi.getInstance().setVeri(kullanici);
          //Sifre TextFieldındaki verileri alıp Sifre değişkenine atandı.
          String sifre;
          sifre=txt_sifre.getText();
          //TextFieldların boş olup olmadığını kontrol ediyor ve yanlıs olup olmadıgını
          boolean bosKontrol;
          bosKontrol = !(kullanici.isEmpty()&&sifre.isEmpty() && kullaniciadi() && sifre());
          //Kullanıcı adının ve sifreyi doğru olup olmadığını ve bosKonrol doğru olup olmadığını kontrol
        if ((kontrol(kullanici, sifre))&& bosKontrol) {
            //Herşey başarılı olduysa ekranımı aktif ediyor.
            Screenbox.getInstance().Screenboxingfirst(girisUrl,event,"Giriş Ekranı","/Resimler/ProjeLogoo.jpg");
        }
        else{//Kullanici adı ve sifre yanlıs girilmişse txt siliniyor ve bir hata msj veriyor
            txt_kullanici.clear();
            txt_sifre.clear();
            lbl_bilgi.setTextFill(Color.RED);
            lbl_bilgi.setText("Kullanıcı Adı veya Şifre Yanlış \nLütfen Tekrar Deneyiniz");

        }
    }
    //tıklandığında kayıt sayfasına yönleniyor
    @FXML
    void btn_kayıtol(ActionEvent event) throws IOException {
          Screenbox.getInstance().Screenboxing(kayitUrl,event,"Kayit Ekranı","/Resimler/ProjeLogoo.jpg");
    }
    //kullaniciadi doğruluğunu kontrol ediyor
    @FXML
    boolean kullaniciadi() {
        boolean check;
        check= JFXTextField_Check.getInstance().CheckMail(txt_kullanici);
        return check;
    }
    //sifrenin doğruğunu kontrol ediyor
    @FXML
    boolean sifre() {
        boolean check;
        check=JFXTextField_Check.getInstance().Check(txt_sifre);
        return check;
    }
    //kullanici adi ve sifreyi veritabanından kontrol yapıyor
    private boolean kontrol(String isim, String sifre)
    {
        ArrayList<Kullanici_Veri> verilerim= Kullanici_Veri_Al.getInstance().kullanicigetir();
        for (int i = 0; i < verilerim.size(); i++) {
                if (verilerim.get(i).kullaniciAdi.equals(isim) && verilerim.get(i).kullaniciSifresi.equals(sifre)) {
                    return true;

            }
        }
        return false;
    }
}
