package controllers;

import Client.Client;
import config.DateConfigure;
import config.FormValidator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import message.Messages;
import message.UserStaus;
import models.BlackList;
import models.Doctor;
import models.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonalMenuDoctorController {

    private Stage stage;

    public  void Show(){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/templates/doctor menu/PersonalMenuDoctor.fxml"));
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
    private Button but_block_doctor;

    @FXML
    private Button but_del_doctor;

    @FXML
    private Button but_exit;

    @FXML
    private TextField inp_dateRagistration;

    @FXML
    private TextField inp_education;

    @FXML
    private TextField inp_expirience;

    @FXML
    private TextField inp_idPassport;

    @FXML
    private TextField inp_name;

    @FXML
    private TextField inp_patronymic;

    @FXML
    private TextField inp_phone;

    @FXML
    private TextField inp_seriaNumber;

    @FXML
    private TextField inp_special;

    @FXML
    private TextField inp_status;

    @FXML
    private TextField inp_surname;

    @FXML
    private TextField inp_university;

    @FXML
    private Label label_indexDoctor;

    private void initialized() throws IOException, ClassNotFoundException {
        Doctor doctor=(Doctor) Client.getInstance().getIn().readObject();
        User user=(User)Client.getInstance().getIn().readObject();
        inp_name.setText(doctor.getName());
        inp_status.setText(user.getStatus());
        inp_surname.setText(doctor.getSur_name());
        inp_patronymic.setText(doctor.getPatronymic());
        inp_phone.setText(doctor.getPhone());
        inp_idPassport.setText(doctor.getID_passport());
        inp_seriaNumber.setText(doctor.getSeria_number());
        inp_education.setText(doctor.getEducation());
        inp_special.setText(doctor.getSpeciality());
        inp_dateRagistration.setText(doctor.getDate_registration());
        inp_university.setText(doctor.getUniversity());
        label_indexDoctor.setText(Double.toString(doctor.getRating()).substring(0,Double.toString(doctor.getRating()).indexOf('.')+1));
        if(user.getStatus().equals(UserStaus.ban)){
            but_block_doctor.setText("Разблокировать");
        }
        else {
            but_block_doctor.setText("Заблокировать");
        }
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        initialized();
        but_exit.setOnAction(actionEvent -> {
            but_exit.getScene().getWindow().hide();
        });
        but_block_doctor.setOnAction(actionEvent -> {
            if(but_block_doctor.getText().equals("Разблокировать")){
                but_block_doctor.setText("Заблокировать");
                FormValidator formValidator=new FormValidator();
                formValidator.statusSec(inp_status);
                try {
                    Client.getInstance().getOut().writeObject(Messages.UNBLOCK_ACCOUNT);
                    inp_status.setText(UserStaus.active);
                    try {
                        Client.getInstance().getOut().writeObject(inp_idPassport.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                but_block_doctor.setText("Разблокировать");
                try {
                    FormValidator formValidator=new FormValidator();
                    formValidator.statusDange(inp_status);
                    Client.getInstance().getOut().writeObject(Messages.BLOCK_ACCOUNT);
                    inp_status.setText(UserStaus.ban);
                    Client.getInstance().getOut().writeObject(inp_idPassport.getText());
                    BlackList blackList=new BlackList();
                    blackList.setDate(DateConfigure.getDateConfigure().nowDate());
                    Client.getInstance().getOut().writeObject(blackList);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        but_del_doctor.setOnAction(actionEvent -> {
            try {
                Client.getInstance().getOut().writeObject(Messages.DELETED_DOTCTOR);
                Client.getInstance().getOut().writeObject(inp_idPassport.getText());
                inp_status.setText("Аккаунт удален");
                but_block_doctor.setDisable(true);
                but_del_doctor.setDisable(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
