package Visualization;

import Controller.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Screenbox
{
    private Screenbox(){}

    private static Screenbox instance=new Screenbox();
    public static Screenbox getInstance(){return instance;}



    public void Screenboxing(String url, ActionEvent event,String title,String image) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.getIcons().add(new Image(image));
        stage.setScene(new Scene(root));
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    public void Screenboxingfirst(String url, ActionEvent event, String title,String image) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();

        Controller_Index cnt_firs=loader.getController();
        cnt_firs.TableviewAddData();

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.getIcons().add(new Image(image));
        stage.setScene(new Scene(root));
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void Screenboxingfirst1(String url,String title,String image) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();

        Controller_UrunEkle cnt_firs2=loader.getController();
        cnt_firs2.VeriYaz();

        Stage stage = new Stage();
        stage.getIcons().add(new Image(image));
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void Screenboxingfirst2(String url, ActionEvent event, String title,String image) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();

        Controller_KategoriSil cnt_firs=loader.getController();
        cnt_firs.ComboBoxAddCategory();

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.getIcons().add(new Image(image));
        stage.setScene(new Scene(root));
        stage.show();

    }
    public void Screenboxingfirst3(String url, ActionEvent event, String title,String image) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();

        Controller_KategoriDuzenle cnt_firs=loader.getController();
        cnt_firs.ComboBoxAddCategory("");

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.getIcons().add(new Image(image));
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void Screenboxingfirst4(String url, ActionEvent event, String title,String image) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();

        Controller_UrunDuzenle cnt_firs=loader.getController();
        cnt_firs.VeriYaz();

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.getIcons().add(new Image(image));
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void Screenboxing(String url,String title,String image) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.getIcons().add(new Image(image));
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
