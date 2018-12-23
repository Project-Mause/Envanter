package sql_verilerim;

import verisiniflarimiz.Veri_Aktarimi;

import java.sql.*;

public class Urun_Veri_Yolla {

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

    public static final String TABLO_Kullanici="Kullanici";
    public static final String SUTUN_KullaniciId="KullaniciID";
    public static final String SUTUN_KullaniciIsim="KullaniciIsim";
    public static final String SUTUN_KullaniciSoyisim="KullaniciSoyisim";
    public static final String SUTUN_Kullanici_Adi="KullaniciAdi";
    public static final String SUTUN_Kullanici_Sifre="KullaniciSifre";


    private Connection conn;
    private Urun_Veri_Yolla(){conn = Sql_baglanma.getInstance().connection;}

    private static Urun_Veri_Yolla instance=new Urun_Veri_Yolla();

    public static Urun_Veri_Yolla getInstance(){return instance;}

    public boolean UrunEkle(String UrunAdi,String UrunMiktari, String UrunEklenmeTarihi,String Kategori, String Birim) throws SQLException {
        String KategoriId=KategoriId(Kategori);
        String BirimId=BirimId(Birim);
        int UrunKullaniciID = KullaniciId();

        String sql1 = "INSERT INTO "+TABLO_Urun+"("+SUTUN_urun_Adi+","+SUTUN_urun_Miktari+","+SUTUN_urun_Tarih+","+SUTUN_urun_kategoriId+","+SUTUN_urun_BirimId+","+SUTUN_urun_KullaniciId+") VALUES(?,?,?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql1)) {
            pstmt.setString(1, UrunAdi);
            pstmt.setString(2, UrunMiktari);
            pstmt.setString(3, UrunEklenmeTarihi);
            pstmt.setString(4, KategoriId);
            pstmt.setString(5, BirimId);
            pstmt.setInt(6, UrunKullaniciID);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public int KullaniciId() throws SQLException {
        PreparedStatement statement= Sql_baglanma.getInstance().connection.prepareStatement("SELECT "+SUTUN_KullaniciId+" FROM "+TABLO_Kullanici+" WHERE "+SUTUN_Kullanici_Adi+"="+"?");
        statement.setString(1, Veri_Aktarimi.getInstance().getVeri());
        ResultSet sonuc=statement.executeQuery();
        return sonuc.getInt(SUTUN_KullaniciId);
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
