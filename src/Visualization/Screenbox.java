package Visualization;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sql_verilerim.Kullanici_Veri_Al;
import java.io.IOException;

public class Screenbox
{
    private Screenbox(){}

    private static Screenbox instance=new Screenbox();
    public static Screenbox getInstance(){return instance;}

    public void Screenboxing(String url, ActionEvent event,String title) throws IOException {
        Kullanici_Veri_Al.getInstance().kullanicigetir();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    public void Screenboxing(String url,String title) throws IOException {
        Kullanici_Veri_Al.getInstance().kullanicigetir();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
