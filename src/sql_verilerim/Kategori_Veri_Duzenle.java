package sql_verilerim;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Kategori_Veri_Duzenle {


    public static final String TABLO_Kategori="Kategori";
    public static final String SUTUN_KategoriId="KategoriID";
    public static final String SUTUN_KategoriAdi="KategoriAdi";

    Connection conn;
    private Kategori_Veri_Duzenle() {conn=Sql_baglanma.getInstance().connection;}
    private static Kategori_Veri_Duzenle instance=new Kategori_Veri_Duzenle();
    public static Kategori_Veri_Duzenle getInstance(){return instance;}

    public boolean veriDuzenle(String kategori,String kategoriyeni) {
        try {
            PreparedStatement prestatement = conn.prepareStatement("UPDATE "+TABLO_Kategori+" SET "+SUTUN_KategoriAdi+" = ? WHERE "+SUTUN_KategoriId+"=?");
            prestatement.setString(1, kategoriyeni);
            prestatement.setString(2, KategoriID(kategori));
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
