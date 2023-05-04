package controllers;

import Client.Client;
import config.FormValidator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import message.Messages;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartMenuController {

    private Stage stage;

    public void Show(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/templates/StartMenu.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = loader.getRoot();
        Scene scene = new Scene(parent);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label arlet_autorization;

    @FXML
    private Button but_enter;

    @FXML
    private Button but_exit;

    @FXML
    private Button but_registration;

    @FXML
    private TextField input_login;

    @FXML
    private PasswordField input_password;

    @FXML
    void initialize() {
        but_exit.setOnAction(actionEvent -> {
            but_exit.getScene().getWindow().hide();
        });
        but_registration.setOnAction(actionEvent -> {
            but_registration.getScene().getWindow().hide();
            RegistratironParientController registratironParientController=new RegistratironParientController();
            registratironParientController.Show();
        });
        but_enter.setOnAction(actionEvent -> {
            FormValidator formValidator=new FormValidator();
            boolean trigger=false;
            if(input_password.getText().equals("")||input_password.getText().length()<2){formValidator.validationInput(input_password,"");trigger=true;}
            if(input_login.getText().equals("")||input_login.getText().length()<2){formValidator.validationInput(input_login,"Введите логин");trigger=true;}
            if(trigger==false){
                try {
                    Client.getInstance().getOut().writeObject(Messages.AUTORIZATION);
                    Client.getInstance().getOut().writeObject(input_login.getText());
                    Client.getInstance().getOut().writeObject(input_password.getText());
                    Messages messages=(Messages) Client.getInstance().getIn().readObject();
                    if(messages==Messages.AUTORIZATION_NOT_FOUND_ACCOUNT){
                        formValidator.viewArlet(arlet_autorization,"Ваш аккаунт не был найден");
                    }
                    else {
                        messages=(Messages) Client.getInstance().getIn().readObject();
                        if(messages!=Messages.AUTORIZATION_BLOCK_ACCOUNT){
                            if(messages==Messages.AUTORIZATION_ADMIN){
                                but_enter.getScene().getWindow().hide();
                                AdminMenuController adminMenuController=new AdminMenuController();
                                adminMenuController.Show();
                            }
                            if(messages==Messages.AUTORIZATION_PATIENT){
                                but_enter.getScene().getWindow().hide();
                                UserMenuController userMenuController=new UserMenuController();
                                userMenuController.Show();
                            }
                            if(messages==Messages.AUTORIZATION_DOCTOR){
                                but_enter.getScene().getWindow().hide();
                                DoctorMenuController doctorMenuController=new DoctorMenuController();
                                doctorMenuController.Show();
                            }
                        }
                        else {
                            formValidator.viewArlet(arlet_autorization,"Ваш аккаунт был заблокирован");
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else {
                formValidator.disabledComponent(but_enter);
            }
        });

    }
}
