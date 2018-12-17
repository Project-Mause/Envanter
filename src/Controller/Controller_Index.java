package Controller;

    import Visualization.Screenbox;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.stage.Stage;
    import java.io.IOException;

public class Controller_Index {

    private String ekleme="../design/proje1ekleme.fxml";
    private String kategori="../design/proje1kategori.fxml";

    @FXML
    void btn_kayitekrani(ActionEvent event) throws IOException {
        Screenbox.getInstance().Screenboxing(ekleme,"");
    }
    @FXML
    void menuitm_urunekle(ActionEvent event) throws IOException{
        btn_kayitekrani(event);
    }
    @FXML
    void menuitm_katekle(ActionEvent event) throws IOException
    {
        Screenbox.getInstance().Screenboxing(kategori,"");
    }

}
