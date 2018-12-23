package verisiniflarimiz;

public class Veri_Aktarimiurun {
    private String urunId;
    private String urunAdi;
    private String urunMiktar;
    private String urunKategori;
    private String urunBirim;

    private Veri_Aktarimiurun() {}
    private static Veri_Aktarimiurun instance=new Veri_Aktarimiurun();
    public static Veri_Aktarimiurun getInstance(){return instance;}

    public String getUrunId() {
        return urunId;
    }

    public void setUrunId(String urunId) {
        this.urunId = urunId;
    }

    public String getUrunAdi() {
        return urunAdi;
    }

    public void setUrunAdi(String urunAdi) {
        this.urunAdi = urunAdi;
    }

    public String getUrunMiktar() {
        return urunMiktar;
    }

    public void setUrunMiktar(String urunMiktar) {
        this.urunMiktar = urunMiktar;
    }

    public String getUrunKategori() {
        return urunKategori;
    }

    public void setUrunKategori(String urunKategori) {
        this.urunKategori = urunKategori;
    }

    public String getUrunBirim() {
        return urunBirim;
    }

    public void setUrunBirim(String urunBirim) {
        this.urunBirim = urunBirim;
    }
}
