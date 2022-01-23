package net.toshimichi.mildom.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.toshimichi.mildom.core.MildomClient;
import net.toshimichi.mildom.core.MildomMessage;
import net.toshimichi.mildom.core.StreamListener;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainController controller = new MainController();

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Mildom Comment Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();

        MildomClient client = new MildomClient(11415385);
        client.start();
        client.addStreamListener(new StreamListener<>() {
            @Override
            public void onMessage(MildomMessage message) {
                controller.addMildomMessage(message);
            }
        });
    }

    public static void main(String[] args) {
        MainApplication.launch(args);
    }
}
