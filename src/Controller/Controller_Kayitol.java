package Controller;

import Text_Check.JFXTextField_Check;
import Visualization.Screenbox;
import com.jfoenix.controls.JFXPasswordField;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sql_verilerim.Kullanici_Veri_Yolla;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class Controller_Kayitol
{
    @FXML
    private JFXTextField txt_kisim;

    @FXML
    private JFXTextField txt_ksoyisim;

    @FXML
    private JFXTextField txt_kmail;

    @FXML
    private JFXPasswordField txt_ksifre;

    private String girisUrl="../design/proje1giris.fxml";

    @FXML
    void btn_kayitolundu(ActionEvent event) throws IOException {
        //İsim TextFieldındaki verileri alıp isim değişkenine atandı.
        String isim;
        isim = txt_kisim.getText();
        //Soyisim TextFieldındaki verileri alıp soyisim değişkenine atandı.
        String soyisim;
        soyisim = txt_ksoyisim.getText();
        //Mail TextFieldındaki verileri alıp mail değişkenine atandı.
        String mail;
        mail = txt_kmail.getText();
        //sifre TextPasswordFieldındaki verileri alıp sifre değişkenine atandı.
        String sifre;
        sifre = txt_ksifre.getText();
        //degerKontrol degerinin icine txt bos olup olmadıgını atandı
        boolean degerKontrol;
        degerKontrol=isimkontrol() && soyisimkontrol() && emailkontrol() && sifrekontrol();
        //degerKontrol if sartına sokarak boslugunu kontrolunu saglandı
        if (degerKontrol) {
            //kontrol icine verilerimi atayarak kullanıcının eklenip eklenmediğini kontrol ediyor
            boolean kontrol;
            kontrol = Kullanici_Veri_Yolla.getInstance().kullaniciekle(isim,soyisim,mail,sifre);
            //kontrol if şartına sokarak islemin saglanıp sağlanamadığını kontrol ediyor
            if(kontrol) {
                //Herşey doğru ise giriş ekranına döner.
                Screenbox.getInstance().Screenboxing(girisUrl,event,"Giriş Ekranı","/Resimler/ProjeLogoo.jpg");
            }
        }
        //degerKontrol eğer boşsa ekrana mesaj cıkarmasını saglandı
        else{
            //Ekrana mesaj çıkartma parametreleri.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Hata");
            alert.setHeaderText(null);
            alert.setContentText("Kayıt Olunamadı \nLütfen Bilgilerinizin Doğruluğunu Kontrol Ediniz");
            alert.showAndWait();
        }
    }

    //isim textfield içinde 50 karakterden fazla isim icerip icermediğini kontrol ediyor
    @FXML
    boolean isimkontrol() {
       boolean check;
        check = JFXTextField_Check.getInstance().Check(txt_kisim);
        return check;
    }
    //soyisim textfield içinde 50 karakterden fazla isim icerip icermediğini kontrol ediyor
    @FXML
    boolean soyisimkontrol() {
       boolean check;
       check=JFXTextField_Check.getInstance().Check(txt_ksoyisim);
       return check;
    }
    //mail textfield içinde 50 karakterden fazla isim icerip icermediğini ve @ isaretini kontrol ediyor
    @FXML
    boolean emailkontrol() {
        boolean check;
        check=JFXTextField_Check.getInstance().CheckMail(txt_kmail);
        return check;
    }
    //Sifre JFXPasswordField içinde 50 karakterden fazla isim icerip icermediğini kontrol ediyor
    @FXML
    boolean sifrekontrol() {
        boolean check;
        check=JFXTextField_Check.getInstance().Check(txt_ksifre);
        return check;
    }

}
