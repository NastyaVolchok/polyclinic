package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SuccessfullyArletController {

    private Stage stage;

    public  void Show(){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/templates/arlet/SuccessfullyArlet.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = loader.getRoot();
        Scene scene=new Scene(parent);
        stage=new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button but_close;

    @FXML
    void initialize() {
       but_close.setOnAction(actionEvent -> {
           but_close.getScene().getWindow().hide();
       });

    }

}
