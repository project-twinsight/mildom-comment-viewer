package net.toshimichi.mildom.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        InitController controller = new InitController(primaryStage);

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("init.fxml"));
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Connect to");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        System.exit(0);
    }

    public static void main(String[] args) {
        MainApplication.launch(args);
    }
}
