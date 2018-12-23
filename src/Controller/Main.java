package Controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sql_verilerim.Sql_baglanma;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../design/proje1giris.fxml"));
        Parent root=loader.load();

        Controller_Giris cnt_Giris=loader.getController();
        cnt_Giris.Tooltipfunction();
        primaryStage.getIcons().add(new Image("/Resimler/ProjeLogoo.jpg"));
        primaryStage.setTitle("Giriş Ekranı");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void init(){

        if (Sql_baglanma.getInstance().veritabaninaBaglanma()) {
            System.out.println("Veri Tabanına Bağlandım");
        }
        else{
            Platform.exit();
        }
    }

    @Override
    public void stop() throws Exception {
        Sql_baglanma.getInstance().veritabaniniKapat();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
