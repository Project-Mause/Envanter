package verisiniflarimiz;

public class Veri_Aktarimi {

    private String Veri;

    private Veri_Aktarimi() {}
    private static Veri_Aktarimi instance=new Veri_Aktarimi();
    public static Veri_Aktarimi getInstance(){return instance;}

    public String getVeri() {
        return Veri;
    }

    public void setVeri(String veri) {
        Veri = veri;
    }
}