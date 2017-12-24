package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application {

    public static Window stage;

    @Override
    public void start(Stage Window) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Window.setResizable(false);
        Window.setTitle("Converter Marker");
        Window.setScene(new Scene(root));
        Window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
