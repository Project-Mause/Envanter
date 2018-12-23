package sql_verilerim;

import verisiniflarimiz.Kategori_Veri;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Kategori_Veri_Sil {

    public static final String TABLO_Kategori="Kategori";
    public static final String SUTUN_KategoriId="KategoriID";
    public static final String SUTUN_KategoriAdi="KategoriAdi";

    Connection conn;
    private Kategori_Veri_Sil() {conn=Sql_baglanma.getInstance().connection;}
    private static Kategori_Veri_Sil instance=new Kategori_Veri_Sil();
    public static Kategori_Veri_Sil getInstance(){return instance;}

    public boolean veriSil(String kategori) {
        try {
            PreparedStatement prestatement = conn.prepareStatement("DELETE FROM " + TABLO_Kategori + " WHERE " + SUTUN_KategoriAdi + "=" + "?");
            prestatement.setString(1, kategori);
            prestatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
