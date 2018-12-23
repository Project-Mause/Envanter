package sql_verilerim;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Kategori_Veri_Yolla {

    public static final String TABLO_Kategori="Kategori";
    public static final String SUTUN_KategoriId="KategoriID";
    public static final String SUTUN_KategoriAdi="KategoriAdi";

    private Connection conn;
    private Kategori_Veri_Yolla(){conn = Sql_baglanma.getInstance().connection;}

    private static Kategori_Veri_Yolla instance=new Kategori_Veri_Yolla();

    public static Kategori_Veri_Yolla getInstance(){return instance;}

    public boolean KategoriEkle(String Kategori_Adi)
    {

        String sql = "INSERT INTO "+TABLO_Kategori+"("+SUTUN_KategoriAdi+") VALUES(?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Kategori_Adi);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}
