package sql_verilerim;

import verisiniflarimiz.Urun_Veri;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Urun_Veri_Al {
    public static final String TABLO_Urun="Urun";
    public static final String SUTUN_urun_Id="UrunID";
    public static final String SUTUN_urun_Adi="UrunAdi";
    public static final String SUTUN_urun_Miktari="UrunMiktari";
    public static final String SUTUN_urun_kategoriId="UrunKategoriId";
    public static final String SUTUN_urun_Tarih="UrunEklenmeTarihi";
    public static final String SUTUN_urun_BirimId="UrunBirimID";
    public static final String SUTUN_urun_KullaniciId="UrunKullaniciID";

    public static final String TABLO_Kategori="Kategori";
    public static final String SUTUN_KategoriId="KategoriID";
    public static final String SUTUN_KategoriAdi="KategoriAdi";

    public static final String TABLO_Birim="Birim";
    public static final String SUTUN_BirimId="BirimID";
    public static final String SUTUN_BirimAdi="BirimAdi";

    private Urun_Veri_Al() {}
    private static Urun_Veri_Al instance=new Urun_Veri_Al();
    public static Urun_Veri_Al getInstance(){return instance;}

    public ArrayList<Urun_Veri> UrunGetir()
    {
        try(Statement statement= Sql_baglanma.getInstance().connection.createStatement())
        {
            ResultSet sonuc=statement.executeQuery("SELECT * FROM "+TABLO_Urun);

            ArrayList<Urun_Veri> tumVeri=new ArrayList<>();
            while(sonuc.next())
            {
                Urun_Veri urunVeri=new Urun_Veri();
                urunVeri.setUrunId(sonuc.getString(SUTUN_urun_Id));
                urunVeri.setUrunAdi(sonuc.getString(SUTUN_urun_Adi));
                urunVeri.setKategori(KategoriID(sonuc.getString(SUTUN_urun_kategoriId)));
                urunVeri.setMiktar(sonuc.getString(SUTUN_urun_Miktari));
                urunVeri.setBirim(BirimID(sonuc.getString(SUTUN_urun_BirimId)));
                urunVeri.setTarih(sonuc.getString(SUTUN_urun_Tarih));
                tumVeri.add(urunVeri);
            }
            return tumVeri;
        } catch (SQLException e) {
            System.out.println("Sorgu Başarısız");
            e.printStackTrace();
            return null;
        }
    }

    public String KategoriID(String id) throws SQLException {
        PreparedStatement statement= Sql_baglanma.getInstance().connection.prepareStatement("SELECT "+SUTUN_KategoriAdi+" FROM "+TABLO_Kategori+" WHERE "+SUTUN_KategoriId+"="+"?");
        statement.setString(1, id);
        ResultSet sonuc=statement.executeQuery();
        return sonuc.getString(SUTUN_KategoriAdi);
    }
    public String BirimID(String id) throws SQLException {
        PreparedStatement statement= Sql_baglanma.getInstance().connection.prepareStatement("SELECT "+SUTUN_BirimAdi+" FROM "+TABLO_Birim+" WHERE "+SUTUN_BirimId+"="+"?");
        statement.setString(1, id);
        ResultSet sonuc=statement.executeQuery();
        return sonuc.getString(SUTUN_BirimAdi);
    }

}
