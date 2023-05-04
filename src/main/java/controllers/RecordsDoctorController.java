package controllers;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import Client.Client;
import config.DateConfigure;
import config.FormValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import message.Messages;
import models.Doctor;
import models.Records;
import models.Work_day;

public class RecordsDoctorController {

    private Stage stage;

    public  void Show(){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/templates/user menu/RecordsDoctor.fxml"));
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
    private DatePicker DPRecords;

    @FXML
    private Label LDateTIme;

    @FXML
    private Label LFIODoctor;

    @FXML
    private Label LSpeciality;

    @FXML
    private Spinner<Integer> SHour;

    @FXML
    private Spinner<Integer> SMinute;

    @FXML
    private Label arlet_sec;

    @FXML
    private Button but_close;

    @FXML
    private Button but_creatReacords;

    @FXML
    private Label ID_doctor;

    @FXML
    private Label arlet_dang;

    @FXML
    private Label LWorkDay;

    private Doctor doctor;

    private Work_day work_day;

    void initialized() throws IOException, ClassNotFoundException {
        doctor=(Doctor) Client.getInstance().getIn().readObject();
        work_day=(Work_day)Client.getInstance().getIn().readObject();
        LFIODoctor.setText(doctor.getName()+" "+doctor.getSur_name()+" "+doctor.getPatronymic());
        LSpeciality.setText(doctor.getSpeciality());
        SpinnerValueFactory<Integer> Shour =new SpinnerValueFactory.IntegerSpinnerValueFactory(9, 19, 1);
        SHour.setValueFactory(Shour);
        SpinnerValueFactory<Integer> SMinut =new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 1);
        SMinute.setValueFactory(SMinut);
        if(work_day.getMonday())LWorkDay.setText(LWorkDay.getText()+"Пн ");
        if(work_day.getTuesday())LWorkDay.setText(LWorkDay.getText()+"Вт ");
        if(work_day.getTuesday())LWorkDay.setText(LWorkDay.getText()+"Ср ");
        if(work_day.getTuesday())LWorkDay.setText(LWorkDay.getText()+"Чт ");
        if(work_day.getTuesday())LWorkDay.setText(LWorkDay.getText()+"Пт ");
        LWorkDay.setText(LWorkDay.getText()+"9:00 - 19:00");
        final Callback<DatePicker, DateCell> dayCellFactory = (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                LocalDate localDate=LocalDate.now();
                if(item.isBefore(localDate)){
                    setDisable(true);
                    setStyle("-fx-background-color: #4f0109;");
                }
                if(item.isAfter(localDate.plusDays(16))){
                    setDisable(true);
                    setStyle("-fx-background-color: #4f0109;");
                }
                if(!work_day.getMonday()){
                    if (item.getDayOfWeek() == DayOfWeek.MONDAY) {
                        setDisable(true);
                        setStyle("-fx-background-color: #4f0109;");
                    }
                }
                if(!work_day.getTuesday()){
                    if (item.getDayOfWeek() == DayOfWeek.TUESDAY) {
                        setDisable(true);
                        setStyle("-fx-background-color: #4f0109;");
                    }
                }
                if(!work_day.getWednesday()){
                    if (item.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                        setDisable(true);
                        setStyle("-fx-background-color: #4f0109;");
                    }
                }
                if(!work_day.getThursday()){
                    if (item.getDayOfWeek() == DayOfWeek.THURSDAY) {
                        setDisable(true);
                        setStyle("-fx-background-color: #4f0109;");
                    }
                }
                if(!work_day.getFriday()){
                    if (item.getDayOfWeek() == DayOfWeek.FRIDAY) {
                        setDisable(true);
                        setStyle("-fx-background-color: #4f0109;");
                    }
                }
                if (item.getDayOfWeek() == DayOfWeek.SATURDAY) {
                    setDisable(true);
                    setStyle("-fx-background-color: #4f0109;");
                }
                if (item.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    setDisable(true);
                    setStyle("-fx-background-color: #4f0109;");
                }
            }
        };
        DPRecords.setDayCellFactory(dayCellFactory);
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        initialized();
        DPRecords.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String minute=Integer.toString(SMinute.getValue());
                String hour=Integer.toString(SHour.getValue());
                if(minute.length()==1)minute="0"+minute;
                if(hour.length()==1)hour="0"+hour;
                LDateTIme.setText(DPRecords.getEditor().getText()+" "+hour+":"+minute);
                LDateTIme.setText(LDateTIme.getText().replace(".","-"));
                System.out.println(DateConfigure.getDateConfigure().nowDate());
                System.out.println(LDateTIme.getText());
            }
        });
        but_close.setOnAction(actionEvent -> {
            but_close.getScene().getWindow().hide();
        });
        but_creatReacords.setOnAction(actionEvent -> {
            try {
                Records records=new Records();
                records.setDateRecords(LDateTIme.getText());
                records.setDate(DateConfigure.getDateConfigure().nowDate());
                records.setIdDoctor(doctor.getID_passport());
                Client.getInstance().getOut().writeObject(Messages.CREATE_RECORDS);
                Client.getInstance().getOut().writeObject(records);
                FormValidator formValidator=new FormValidator();
                formValidator.visableArlet(arlet_sec);
                formValidator.disabledComponent(but_creatReacords);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
