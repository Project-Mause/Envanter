package sql_verilerim;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Birim_Donustur {
    public static final String TABLO_Kategori="Kategori";
    public static final String SUTUN_KategoriId="KategoriID";
    public static final String SUTUN_KategoriAdi="KategoriAdi";

    public static final String TABLO_Birim="Birim";
    public static final String SUTUN_BirimId="BirimID";
    public static final String SUTUN_BirimAdi="BirimAdi";

    private Connection conn;
    private Birim_Donustur(){conn = Sql_baglanma.getInstance().connection;}

    private static Birim_Donustur instance=new Birim_Donustur();

    public static Birim_Donustur getInstance(){return instance;}

    public String KategoriId(String Kategori) throws SQLException {
        PreparedStatement statement=Sql_baglanma.getInstance().connection.prepareStatement("SELECT "+SUTUN_KategoriAdi+" FROM "+TABLO_Kategori+" WHERE "+SUTUN_KategoriId+"="+"?");
        statement.setString(1,Kategori);
        ResultSet sonuc=statement.executeQuery();
        return sonuc.getString(SUTUN_KategoriAdi);
    }
    public String BirimId(String Birim) throws SQLException {
        PreparedStatement statement=Sql_baglanma.getInstance().connection.prepareStatement("SELECT "+SUTUN_BirimAdi+" FROM "+TABLO_Birim+" WHERE "+SUTUN_BirimId+"="+"?");
        statement.setString(1,Birim);
        ResultSet sonuc=statement.executeQuery();
        return sonuc.getString(SUTUN_BirimAdi);
    }

}
