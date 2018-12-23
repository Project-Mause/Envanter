package Controller;

    import Visualization.Screenbox;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.scene.control.Alert;
    import javafx.scene.control.ButtonType;
    import javafx.scene.control.TableColumn;
    import javafx.scene.control.TableView;
    import javafx.scene.control.cell.PropertyValueFactory;
    import sql_verilerim.Urun_Veri_Al;
    import sql_verilerim.Urun_Veri_Sil;
    import verisiniflarimiz.Urun_Veri;
    import verisiniflarimiz.Veri_Aktarimi;
    import verisiniflarimiz.Veri_Aktarimiurun;

    import java.io.IOException;
    import java.sql.SQLException;
    import java.util.Optional;

public class Controller_Index {
        //Tableview'ı FXML ile classlar arası bağlantı için değişkene atandı
        @FXML
        private TableView<Urun_Veri> tableview;
        //TableColumn'ı FXML ile classlar arası bağlantı için değişkene atandı
        @FXML
        private TableColumn<Urun_Veri, String> tablec_urunadi;
        //TableColumn'ı FXML ile classlar arası bağlantı için değişkene atandı
        @FXML
        private TableColumn<Urun_Veri, String> tablec_kategori;
        //TableColumn'ı FXML ile classlar arası bağlantı için değişkene atandı
        @FXML
        private TableColumn<Urun_Veri, String> tablec_miktar;
        //TableColumn'ı FXML ile classlar arası bağlantı için değişkene atandı
        @FXML
        private TableColumn<Urun_Veri, String> tablec_birim;
    //TableColumn'ı FXML ile classlar arası bağlantı için değişkene atandı
        @FXML
        private TableColumn<Urun_Veri, String> tablec_tarih;

        //FXML Kayıt Urunekleme URL'si
        private String ekleme="../design/proje1ekleme.fxml";
        //FXML Kayıt Kategoriekleme URL'si
        private String kategori="../design/proje1kategori.fxml";
        private String kategorisil="../design/proje1kategorisil.fxml";
        private String kategoriduzenle="../design/proje1kategoriduzenle.fxml";
        private String urunduzenle="../design/proje1duzenleme.fxml";
        public void TableviewAddData(){
            //TableView başlangıçta temizliyor.
            tableview.getItems().clear();
            //TableView atanabilmesi için ObservableList denişgeni oluşturuluyor;
            ObservableList<Urun_Veri> urun= FXCollections.observableArrayList();
            //ObservableList Urun_Veri_Al veriler çekiliyor
            urun.addAll(Urun_Veri_Al.getInstance().UrunGetir());
            /*Urun_Veri degen degisken isimlerini her bir TableColumn atıyorum.
            * Bu sayede hangi verinin hangi kısma gidecegi belirleniyor*/
            tablec_urunadi.setCellValueFactory(new PropertyValueFactory<>("UrunAdi"));
            tablec_kategori.setCellValueFactory(new PropertyValueFactory<>("kategori"));
            tablec_miktar.setCellValueFactory(new PropertyValueFactory<>("miktar"));
            tablec_birim.setCellValueFactory(new PropertyValueFactory<>("birim"));
            tablec_tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
            //TableView'a veri aktarılıyor.
            tableview.setItems(urun);
        }
        @FXML
        void menuItem_res(ActionEvent event) {
            //İşlemler MenuBar Menu'sündeki MenuItem'daki Tabloyu Güncelleniyor
            TableviewAddData();
        }
        @FXML
        void menuitm_urunekle(ActionEvent event) throws IOException{
                //Urun ekleme formunu açıyor
            Screenbox.getInstance().Screenboxingfirst1(ekleme,"Ürün ekle","/Resimler/ProjeLogoo.jpg");

        }

        @FXML
        void menuitm_katduzenle(ActionEvent event) throws IOException {

            Screenbox.getInstance().Screenboxingfirst3(kategoriduzenle,event,"Kategori Düzenle","/Resimler/ProjeLogoo.jpg");
        }

        @FXML
        void menuitm_katekle(ActionEvent event) throws IOException
        {
            //Kategori Ekleme formunu açıyor
            Screenbox.getInstance().Screenboxing(kategori,"Kategori Ekle","/Resimler/ProjeLogoo.jpg");
        }

        @FXML
        void menuitm_katesil(ActionEvent event) throws IOException {
            Screenbox.getInstance().Screenboxingfirst2(kategorisil,event,"Kategori Sil","/Resimler/ProjeLogoo.jpg");
        }

        @FXML
        void conmen_Sil(ActionEvent event) {
            Urun_Veri id=tableview.getSelectionModel().getSelectedItem();
            if (kontrol()) {
                Urun_Veri_Sil.getInstance().veriSil(id.getUrunId());
                TableviewAddData();
            }
        }
        @FXML
        void conmen_Ekle(ActionEvent event) throws IOException {
            Screenbox.getInstance().Screenboxingfirst1(ekleme,"Ürün ekle","/Resimler/ProjeLogoo.jpg");
        }
        @FXML
        void conmen_Duzenle(ActionEvent event) throws IOException, SQLException {
            Urun_Veri id=tableview.getSelectionModel().getSelectedItem();
            Veri_Aktarimiurun.getInstance().setUrunId(id.getUrunId());
            Veri_Aktarimiurun.getInstance().setUrunAdi(id.getUrunAdi());
            Veri_Aktarimiurun.getInstance().setUrunMiktar(id.getMiktar());
            Veri_Aktarimiurun.getInstance().setUrunBirim(id.getBirim());
            Veri_Aktarimiurun.getInstance().setUrunKategori(id.getKategori());
            Screenbox.getInstance().Screenboxingfirst4(urunduzenle,event,"Ürün Düzenle","/Resimler/ProjeLogoo.jpg");
         }
        @FXML
        void conmen_Guncelle(ActionEvent event) throws IOException {
            TableviewAddData();
        }

         public boolean kontrol()
         {
             String ad="Silmek istediğinize Emin misiniz";
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Silme İşlemi");
             alert.setHeaderText("Silmek istediğinize");
             alert.setContentText("Emin misiniz ?");
             Optional<ButtonType> button= alert.showAndWait();
             if (button.get()==ButtonType.OK) {
                 return true;
             }else{
                 return false;
             }
         }

}
