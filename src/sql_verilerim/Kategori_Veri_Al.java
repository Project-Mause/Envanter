package sql_verilerim;

import verisiniflarimiz.Kategori_Veri;

import java.sql.*;
import java.util.ArrayList;


public class Kategori_Veri_Al {
    public static final String TABLO_Kategori="Kategori";
    public static final String SUTUN_KategoriId="KategoriID";
    public static final String SUTUN_KategoriAdi="KategoriAdi";

    private Kategori_Veri_Al() {}
    private static Kategori_Veri_Al instance=new Kategori_Veri_Al();
    public static Kategori_Veri_Al getInstance(){return instance;}

    public ArrayList<Kategori_Veri> KategoriGetir()
    {
        try(Statement statement= Sql_baglanma.getInstance().connection.createStatement())
        {
            ResultSet sonuc=statement.executeQuery("SELECT "+SUTUN_KategoriAdi+" FROM "+TABLO_Kategori);
            ArrayList<Kategori_Veri> tumKategori=new ArrayList<>();
            while(sonuc.next())
            {
                Kategori_Veri KategoriVeri=new Kategori_Veri();

                KategoriVeri.setKategoriAdi(sonuc.getString(SUTUN_KategoriAdi));
                tumKategori.add(KategoriVeri);
            }
            return tumKategori;
        } catch (SQLException e) {
            System.out.println("Sorgu Başarısız");
            e.printStackTrace();
            return null;
        }
    }
}
