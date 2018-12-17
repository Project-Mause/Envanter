package sql_verilerim;

import java.sql.*;

public class Kullanici_Veri_Yolla
{
            public static final String TABLO_Kullanici="Kullanici";
            public static final String SUTUN_KullaniciId="KullaniciID";
            public static final String SUTUN_KullaniciIsim="KullaniciIsim";
            public static final String SUTUN_KullaniciSoyisim="KullaniciSoyisim";
            public static final String SUTUN_Kullanici_Adi="KullaniciAdi";
            public static final String SUTUN_Kullanici_Sifre="KullaniciSifre";
            private Connection conn;
            private Kullanici_Veri_Yolla(){conn = Sql_baglanma.getInstance().connection;}

            private static Kullanici_Veri_Yolla instance=new Kullanici_Veri_Yolla();

            public static Kullanici_Veri_Yolla getInstance(){return instance;}

    public boolean kullaniciekle(String isim,String soyisim,String kullaniciadi,String sifre)
    {

        String sql = "INSERT INTO "+TABLO_Kullanici+"("+SUTUN_KullaniciIsim+","+SUTUN_KullaniciSoyisim+","+SUTUN_Kullanici_Adi+","+SUTUN_Kullanici_Sifre+") VALUES(?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, isim);
            pstmt.setString(2, soyisim);
            pstmt.setString(3, kullaniciadi);
            pstmt.setString(4, sifre);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}
