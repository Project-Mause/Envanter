package sql_verilerim;

import verisiniflarimiz.Kullanici_Veri;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Kullanici_Veri_Al
{
    public static final String TABLO_Kullanici="Kullanici";
    public static final String SUTUN_KullaniciId="KullaniciID";
    public static final String SUTUN_Kullanici_Adi="KullaniciAdi";
    public static final String SUTUN_Kullanici_Sifre="KullaniciSifre";

    private Kullanici_Veri_Al(){}

    private static Kullanici_Veri_Al instance=new Kullanici_Veri_Al();

    public static Kullanici_Veri_Al getInstance(){return instance;}

    public ArrayList<Kullanici_Veri> kullanicigetir()
    {
        try(Statement statement= Sql_baglanma.getInstance().connection.createStatement())
        {
            ResultSet sonuc=statement.executeQuery("SELECT "+SUTUN_Kullanici_Adi+","+SUTUN_Kullanici_Sifre+" FROM "+TABLO_Kullanici);
            ArrayList<Kullanici_Veri> tumkullanicilar=new ArrayList<>();
            while(sonuc.next())
            {
                Kullanici_Veri kullaniciveri=new Kullanici_Veri();

                kullaniciveri.setKullaniciAdi(sonuc.getString(SUTUN_Kullanici_Adi));
                kullaniciveri.setKullaniciSifresi(sonuc.getString(SUTUN_Kullanici_Sifre));
                tumkullanicilar.add(kullaniciveri);
            }
            return tumkullanicilar;
        } catch (SQLException e) {
            System.out.println("Sorgu Başarısız");
            e.printStackTrace();
            return null;
        }
    }
}
