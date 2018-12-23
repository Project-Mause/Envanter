package sql_verilerim;

import verisiniflarimiz.Birim_Veri;
import verisiniflarimiz.Kategori_Veri;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Birim_Veri_Al
{
    public static final String TABLO_Birim="Birim";
    public static final String SUTUN_BirimId="BirimID";
    public static final String SUTUN_Adi="BirimAdi";

    private Birim_Veri_Al(){}
    private static Birim_Veri_Al instance=new Birim_Veri_Al();
    public static Birim_Veri_Al getInstance(){return instance;}

    public ArrayList<Birim_Veri> BirimGetir() {
        try(Statement statement= Sql_baglanma.getInstance().connection.createStatement()) {

            ResultSet sonuc=statement.executeQuery("SELECT "+SUTUN_Adi+" FROM "+TABLO_Birim);
            ArrayList<Birim_Veri> tumBirim=new ArrayList<>();

            while(sonuc.next()) {
                Birim_Veri KategoriVeri=new Birim_Veri();
                KategoriVeri.setBirimAdi(sonuc.getString(SUTUN_Adi));
                tumBirim.add(KategoriVeri);
            }

            return tumBirim;
        } catch (SQLException e) {

            System.out.println("Sorgu Başarısız");
            e.printStackTrace();

            return null;

        }
    }
}
