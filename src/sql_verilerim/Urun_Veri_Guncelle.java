package sql_verilerim;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Urun_Veri_Guncelle {

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



    private Connection conn;
    private Urun_Veri_Guncelle(){conn = Sql_baglanma.getInstance().connection;}

    private static Urun_Veri_Guncelle instance=new Urun_Veri_Guncelle();

    public static Urun_Veri_Guncelle getInstance(){return instance;}

    public boolean UrunGuncelle(String id, String UrunAdi, String UrunMiktar, String Tarih, String Kategori, String Birim)
    {

        String sql="UPDATE "+TABLO_Urun+" SET "+SUTUN_urun_Adi+" = ? ,"+SUTUN_urun_Miktari+" = ? ,"+SUTUN_urun_Tarih+" = ? ,"+SUTUN_urun_kategoriId+" = ? ,"+SUTUN_urun_BirimId+" = ? WHERE "+SUTUN_urun_Id+" = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,UrunAdi);
            pstmt.setString(2,UrunMiktar);
            pstmt.setString(3,Tarih);
            pstmt.setString(4,KategoriId(Kategori));
            pstmt.setString(5,BirimId(Birim));
            pstmt.setString(6,id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
    public String KategoriId(String Kategori) throws SQLException {
        PreparedStatement statement=Sql_baglanma.getInstance().connection.prepareStatement("SELECT "+SUTUN_KategoriId+" FROM "+TABLO_Kategori+" WHERE "+SUTUN_KategoriAdi+"="+"?");
        statement.setString(1,Kategori);
        ResultSet sonuc=statement.executeQuery();
        return sonuc.getString(SUTUN_KategoriId);
    }
    public String BirimId(String Birim) throws SQLException {
        PreparedStatement statement=Sql_baglanma.getInstance().connection.prepareStatement("SELECT "+SUTUN_BirimId+" FROM "+TABLO_Birim+" WHERE "+SUTUN_BirimAdi+"="+"?");
        statement.setString(1,Birim);
        ResultSet sonuc=statement.executeQuery();
        return sonuc.getString(SUTUN_BirimId);
    }

}
