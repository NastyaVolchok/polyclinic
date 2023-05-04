package controllers;

import Client.Client;
import config.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import message.Messages;
import message.UserStaus;
import models.Patient;
import models.User;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistratironParientController {

    private Stage stage;

    public void Show(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/templates/RegistratironParient.fxml"));
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
    private Button but_registration;

    @FXML
    private Button but_returnComeBack;

    @FXML
    private TextField input_dateBirth;

    @FXML
    private TextField input_date_registration;

    @FXML
    private TextField input_idPassport;

    @FXML
    private TextField input_login;

    @FXML
    private TextField input_name;

    @FXML
    private TextField input_password;

    @FXML
    private TextField input_patronymic;

    @FXML
    private TextField input_phone;

    @FXML
    private TextField input_serianumber;

    @FXML
    private TextField input_surnamr;

    @FXML
    private RadioButton radio_men;

    @FXML
    private RadioButton radio_women;


    @FXML
    void initialize() {
        input_date_registration.setText(DateConfigure.getDateConfigure().nowDate());
        radio_men.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(t1==true){
                    radio_women.selectedProperty().setValue(false);
                }
                else {
                    radio_women.selectedProperty().setValue(true);
                }
            }
        });
        radio_women.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(t1==true){
                    radio_men.selectedProperty().setValue(false);
                }
                else {
                    radio_men.selectedProperty().setValue(true);
                }
            }
        });
        but_returnComeBack.setOnAction(actionEvent -> {
            but_returnComeBack.getScene().getWindow().hide();
            StartMenuController startMenuController=new StartMenuController();
            startMenuController.Show();
        });
        but_registration.setOnAction(actionEvent -> {
            FormValidator formValidator=new FormValidator();
            Boolean trigger=false;
            if(!input_idPassport.getText().matches("\\d{7}[A-Z]{1}\\d{3}[A-Z]{2}\\d{1}")){formValidator.validationInput(input_idPassport,"Шаблон: 1232222A123AA1");trigger=true;}
            if(!input_serianumber.getText().matches("[A-Z]{2}\\d{7}")){formValidator.validationInput(input_serianumber,"Шаблон : MP1234567");trigger=true;}
            if(!input_name.getText().matches("[ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэюбьтимсчяёЁ]{2,40}")){formValidator.validationInput(input_name,"Имя не может сожержать цифры и символы");trigger=true;}
            if(!input_surnamr.getText().matches("[ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэюбьтимсчяёЁ]{2,40}")){formValidator.validationInput(input_surnamr,"Фамилия не может сожержать цифры и символы");trigger=true;}
            if(!input_patronymic.getText().matches("[ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэюбьтимсчяёЁ]{2,40}")){formValidator.validationInput(input_patronymic,"Отчество не может сожержать цифры и символы");trigger=true;}
            if(input_password.getText().equals("")||input_password.getText().length()<2){formValidator.validationInput(input_password,"Пароль должен быть более 2 символов");trigger=true;}
            if(input_login.getText().equals("")||input_login.getText().length()<2){formValidator.validationInput(input_login,"Логин должен быть более 2 символов");trigger=true;}
            if(!input_phone.getText().matches("(\\+375|80)\\d{9}")){
                formValidator.validationInput(input_phone,"+375 или 80 (29,44,25..) xxxxxxx");trigger=true;}
            if(!input_dateBirth.getText().matches("\\d{2}-\\d{2}-\\d{4}")){formValidator.validationInput(input_dateBirth,"Шаблон даты : dd-MM-yyyy");trigger=true;}
            if(trigger==false){
                try {
                    Client.getInstance().getOut().writeObject(Messages.REGISTRATION_USER);
                    User user=new User();
                    user.setLogin(input_login.getText());
                    user.setPassword(input_password.getText());
                    user.setStatus(UserStaus.active);
                    Patient patient=new Patient();
                    patient.setName(input_name.getText());
                    patient.setSur_name(input_surnamr.getText());
                    patient.setPatronymic(input_patronymic.getText());
                    patient.setPhone(input_phone.getText());
                    patient.setSeria_number(input_serianumber.getText());
                    patient.setID_passport(input_idPassport.getText());
                    patient.setDate_birth(input_dateBirth.getText());
                    patient.setDate_registration(DateConfigure.getDateConfigure().nowDate());
                    if(radio_men.isSelected()){
                        patient.setSex("Мужчина");
                    }
                    else {
                        patient.setSex("Женщина");
                    }
                    Client.getInstance().getOut().writeObject(user);
                    Client.getInstance().getOut().writeObject(patient);
                    SuccessfullyArletController successfullyArletController=new SuccessfullyArletController();
                    successfullyArletController.Show();
                    input_name.setText("");
                    input_surnamr.setText("");
                    input_patronymic.setText("");
                    input_phone.setText("");
                    input_password.setText("");
                    input_login.setText("");
                    input_dateBirth.setText("");
                    input_idPassport.setText("");
                    input_serianumber.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                formValidator.disabledComponent(but_registration);
            }
        });
    }
}
