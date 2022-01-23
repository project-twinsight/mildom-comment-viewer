package net.toshimichi.mildom.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.toshimichi.mildom.core.MildomClient;
import net.toshimichi.mildom.core.MildomMessage;
import net.toshimichi.mildom.core.StreamListener;

public class InitController {

    private final Stage primaryStage;
    @FXML
    private TextField roomId;

    public InitController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void connect() throws Exception {
        int id;
        try {
            id = Integer.parseInt(roomId.getText());
        } catch (NumberFormatException e) {
            roomId.setText("不正なルームIDです");
            return;
        }

        MainController controller = new MainController();

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Mildom Comment Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();

        MildomClient client = new MildomClient(id);
        client.start();
        client.addStreamListener(new StreamListener<>() {
            @Override
            public void onMessage(MildomMessage message) {
                controller.addMildomMessage(message);
            }
        });
    }
}
