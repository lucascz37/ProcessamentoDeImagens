package br.com.edu.main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../layout/MainScreen.fxml"));
        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Processador de Imagens");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("../layout/resources/icon.png")));
        stage.setMinWidth(840.0);
        stage.setMinHeight(600.0);
        stage.show();
    }

    public static void main(String[] args){
        //ImageHandler a1 = new ImageHandler();
        //a1.Menu();
        launch(args);
    }
}
