package sql_verilerim;

import verisiniflarimiz.Veri_Aktarimi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Urun_Veri_Sil
{
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

    private Connection conn;
    private Urun_Veri_Sil(){conn = Sql_baglanma.getInstance().connection;}

    private static Urun_Veri_Sil instance=new Urun_Veri_Sil();
    public static Urun_Veri_Sil getInstance(){return instance;}

    public boolean veriSil(String id){
        try {
            PreparedStatement prestatement=conn.prepareStatement("DELETE FROM "+TABLO_Urun+" WHERE "+SUTUN_urun_Id+"="+"?");
            prestatement.setString(1, id);
            prestatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean veriSilK(String id){
        try {
            PreparedStatement prestatement=conn.prepareStatement("DELETE FROM "+TABLO_Urun+" WHERE "+SUTUN_urun_kategoriId+"="+"?");
            prestatement.setString(1, KategoriID(id));
            prestatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public String KategoriID(String id) throws SQLException {
        PreparedStatement statement= Sql_baglanma.getInstance().connection.prepareStatement("SELECT "+SUTUN_KategoriId+" FROM "+TABLO_Kategori+" WHERE "+SUTUN_KategoriAdi+"="+"?");
        statement.setString(1, id);
        ResultSet sonuc=statement.executeQuery();
        return sonuc.getString(SUTUN_KategoriId);
    }

}
