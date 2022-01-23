package net.toshimichi.mildom.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.toshimichi.mildom.core.MildomMessage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableView<MildomMessage> messageTable;
    @FXML
    private TableColumn<MildomMessage, Integer> level;
    @FXML
    private TableColumn<MildomMessage, String> userName;
    @FXML
    private TableColumn<MildomMessage, String> message;
    private ObservableList<MildomMessage> messages;

    public void addMildomMessage(MildomMessage message) {
        messages.add(message);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messages = FXCollections.observableArrayList();
        messageTable.itemsProperty().setValue(messages);
        level.setCellValueFactory(new PropertyValueFactory<>("level"));
        userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        message.setCellValueFactory(new PropertyValueFactory<>("message"));
    }
}
