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
import javafx.stage.Stage;
import sql_verilerim.Kullanici_Veri_Al;
import verisiniflarimiz.Kullanici_Veri;

import java.io.IOException;
import java.util.ArrayList;

public class Controller_Giris
{

    @FXML
    private JFXTextField txt_kullanici;

    @FXML
    private JFXPasswordField txt_sifre;

    @FXML
    private Label lbl_bilgi;

    private String girisUrl="../design/proje1.fxml";
    private String kayitUrl="../design/proje1kayit.fxml";

    private Tooltip tooltip_k=new Tooltip();
    private Tooltip tooltip_s=new Tooltip();

      public void firstfuntion()
      {
          tooltip_k.setText("Kullanici_Veri_Al Adınız Örnek:'example@hotmail.com' ");
          txt_kullanici.setTooltip(tooltip_k);

          tooltip_s.setText("Şifreniz Örnek:'Example.0'");
          txt_sifre.setTooltip(tooltip_s);

      }

    @FXML
    void btn_giris(ActionEvent event) throws IOException, InterruptedException {
          //Kullanici TextFieldındaki verileri alıp kullanici değişkenine atandı.
          String kullanici;
          kullanici=txt_kullanici.getText();
          //Sifre TextFieldındaki verileri alıp Sifre değişkenine atandı.
          String sifre;
          sifre=txt_sifre.getText();
          //TextFieldların boş olup olmadığını kontrol ediyor ve yanlıs olup olmadıgını
          boolean bosKontrol;
          bosKontrol = !(kullanici.isEmpty()&&sifre.isEmpty() && kullaniciadi() && sifre());
          //Kullanıcı adının ve sifreyi doğru olup olmadığını ve bosKonrol doğru olup olmadığını kontrol
        if ((kontrol(kullanici, sifre))&& bosKontrol) {
            //Herşey başarılı olduysa ekranımı aktif ediyor.
            Screenbox.getInstance().Screenboxing(girisUrl,event,"Giriş Ekranı");
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
          Screenbox.getInstance().Screenboxing(kayitUrl,event,"Kayit Ekranı");
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
            for (int j = 0; j < verilerim.size(); j++) {
                if (verilerim.get(i).kullaniciAdi.equals(isim) && verilerim.get(j).kullaniciSifresi.equals(sifre)) {
                    return true;
                }
            }
        }
        return false;
    }
}
