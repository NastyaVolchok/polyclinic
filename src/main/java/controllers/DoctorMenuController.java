package controllers;

import Client.Client;
import config.FormValidator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import message.Messages;
import models.*;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorMenuController {

    private Stage stage;

    public void Show(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/templates/doctor menu/DoctorMenu.fxml"));
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
    private RadioButton FDayOff;

    @FXML
    private RadioButton FWorking;

    @FXML
    private RadioButton MDayOff;

    @FXML
    private RadioButton MWorking;

    @FXML
    private RadioButton TDayOff;

    @FXML
    private RadioButton THWorking;

    @FXML
    private RadioButton TWorking;

    @FXML
    private RadioButton ThDayOff;

    @FXML
    private RadioButton WDayOff;

    @FXML
    private RadioButton WWorking;

    @FXML
    private Label block_complaint_book;

    @FXML
    private Label block_complaint_book1;

    @FXML
    private Label block_complaint_book11;

    @FXML
    private Label block_complaint_book2;

    @FXML
    private Label block_complaint_book3;

    @FXML
    private AnchorPane block_doctor;

    @FXML
    private AnchorPane block_doctors_list;

    @FXML
    private AnchorPane block_rating;

    @FXML
    private AnchorPane block_reviews;

    @FXML
    private AnchorPane block_settins;

    @FXML
    private Button but_save_personalInfo;

    @FXML
    private TextField inpPersonalEducation;

    @FXML
    private TextField inpPersonalIDPassport;

    @FXML
    private TextField inpPersonalName;

    @FXML
    private TextField inpPersonalPassword;

    @FXML
    private TextField inpPersonalPatronymic;

    @FXML
    private TextField inpPersonalPhone;

    @FXML
    private TextField inpPersonalSeriaNumber;

    @FXML
    private TextField inpPersonalSpecility;

    @FXML
    private TextField inpPersonalSurName;

    @FXML
    private TextField inpPersonalUniversity;

    @FXML
    private Button but_exit;

    @FXML
    private Button but_records;

    @FXML
    private HBox acnhorHStar;

    @FXML
    private Button but_doctor;

    @FXML
    private Button but_comment;

    @FXML
    private Button but_rating;


    @FXML
    private TableColumn<Doctor, Integer> col_doctor_Id_user;

    @FXML
    private TableColumn<Doctor, String> col_doctor_education;

    @FXML
    private TableColumn<Doctor, String> col_doctor_expirience;

    @FXML
    private TableColumn<Doctor, String> col_doctor_name;

    @FXML
    private TableColumn<Doctor, String> col_doctor_patronymic;

    @FXML
    private TableColumn<Doctor, String> col_doctor_speciality;

    @FXML
    private TableColumn<Doctor, String> col_doctor_surName;

    @FXML
    private TableColumn<Doctor, String> col_doctor_university;

    @FXML
    private AnchorPane anchorHeaderReview;

    @FXML
    private TableView<Doctor> tableDoctor;

    @FXML
    private TextField inputSearchDoctor;

    @FXML
    private TextField inpPersonallogin;

    @FXML
    private Button but_settins;

    @FXML
    private AnchorPane block_records;

    @FXML
    private AnchorPane block_infoDoctorRating;

    @FXML
    private Label label_firstRating;

    @FXML
    private Label label_secondRating;

    @FXML
    private TextField inp_ratingFIO;

    @FXML
    private AnchorPane anchor_records;

    @FXML
    private TextField inp_ratingSpeciality;

    @FXML
    private TextField inp_ratingExpirience;

    @FXML
    private TextField inp_ratingEducation;

    @FXML
    private TextField inp_ratingUniversity;

    @FXML
    private Button but_records_Today;

    @FXML
    private Button but_records_All;

    @FXML
    private Label label_indexDoctor;

    @FXML
    private AnchorPane acnRecordsHeader;

    @FXML
    private Label label_thirstRating;

    private FilteredList<Doctor> filterDoctor;


    private void initializedRatingDoctor() throws IOException, ClassNotFoundException {
        List<Doctor>list=(List<Doctor>) Client.getInstance().getIn().readObject();
        if(list.size()>0){
            label_firstRating.setText(list.get(0).getName()+" "+list.get(0).getSur_name()+" "+list.get(0).getPatronymic());
            if(list.size()>=2) label_secondRating.setText(list.get(1).getName()+" "+list.get(1).getSur_name()+" "+list.get(1).getPatronymic());
            if(list.size()>=3)label_thirstRating.setText(list.get(2).getName()+" "+list.get(2).getSur_name()+" "+list.get(2).getPatronymic());
        }
        label_firstRating.setOnMouseClicked(mouseEvent -> {
            if(list.size()>=1){
                block_infoDoctorRating.setVisible(true);
                inp_ratingFIO.setText(list.get(0).getName()+" "+list.get(0).getSur_name()+" "+list.get(0).getPatronymic());
                inp_ratingSpeciality.setText(list.get(0).getSpeciality());
                inp_ratingEducation.setText(list.get(0).getEducation());
                inp_ratingExpirience.setText(list.get(0).getExpiriense());
                inp_ratingUniversity.setText(list.get(0).getUniversity());
                label_indexDoctor.setText(Double.toString(list.get(0).getRating()));
            }
        });
        label_secondRating.setOnMouseClicked(mouseEvent -> {
            if(list.size()>=2){
                block_infoDoctorRating.setVisible(true);
                inp_ratingFIO.setText(list.get(1).getName()+" "+list.get(1).getSur_name()+" "+list.get(1).getPatronymic());
                inp_ratingSpeciality.setText(list.get(1).getSpeciality());
                inp_ratingEducation.setText(list.get(1).getEducation());
                inp_ratingExpirience.setText(list.get(1).getExpiriense());
                inp_ratingUniversity.setText(list.get(1).getUniversity());
                label_indexDoctor.setText(Double.toString(list.get(1).getRating()));
            }
        });
        label_thirstRating.setOnMouseClicked(mouseEvent -> {
            if(list.size()>=3){
                block_infoDoctorRating.setVisible(true);
                inp_ratingFIO.setText(list.get(2).getName()+" "+list.get(2).getSur_name()+" "+list.get(2).getPatronymic());
                inp_ratingSpeciality.setText(list.get(2).getSpeciality());
                inp_ratingEducation.setText(list.get(2).getEducation());
                inp_ratingExpirience.setText(list.get(2).getExpiriense());
                inp_ratingUniversity.setText(list.get(2).getUniversity());
                label_indexDoctor.setText(Double.toString(list.get(2).getRating()));
            }
        });
    }
    private void initializedRecords() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Records>listRecords=(List<Records>) Client.getInstance().getIn().readObject();
        double y=14;
        double x=14;
        for(int i=0;i<listRecords.size();i++){
            if(i!=0){
                y=y+162;
            }
            Client.getInstance().getOut().writeObject(Messages.FIND_PATIENT_RECORDS);
            Client.getInstance().getOut().writeObject(listRecords.get(i).getIdUser());
            Patient patient=(Patient)Client.getInstance().getIn().readObject();

            Label labelIDPAtient=new Label(Integer.toString(listRecords.get(i).getId()));
            labelIDPAtient.setVisible(false);

            AnchorPane anchorPane=new AnchorPane();
            anchorPane.setPrefWidth(710);
            anchorPane.setPrefHeight(150);
            anchorPane.setLayoutX(x);
            anchorPane.setLayoutY(y);
            anchorPane.getStyleClass().add("box_groupe");

            TextField textFio=new TextField();
            textFio.setText(patient.getName()+" "+patient.getSur_name()+" "+patient.getPatronymic());
            textFio.setPrefWidth(250);
            textFio.setPrefHeight(27);
            textFio.setLayoutX(118);
            textFio.setLayoutY(10);

            TextField textPhone=new TextField();
            textPhone.setText(patient.getPhone());
            textPhone.setPrefWidth(156);
            textPhone.setPrefHeight(27);
            textPhone.setLayoutX(212);
            textPhone.setLayoutY(39);

            TextField textIdPassport=new TextField();
            textIdPassport.setText(patient.getID_passport());
            textIdPassport.setPrefWidth(270);
            textIdPassport.setPrefHeight(27);
            textIdPassport.setLayoutX(98);
            textIdPassport.setLayoutY(74);

            Label LabelFIO=new Label("ФИО пациента");
            LabelFIO.setPrefWidth(Region.USE_COMPUTED_SIZE);
            LabelFIO.setPrefHeight(Region.USE_COMPUTED_SIZE);
            LabelFIO.setLayoutX(14);
            LabelFIO.setLayoutY(19);
            LabelFIO.getStyleClass().add("label_signature");

            Label LabelPhone=new Label("Контакнтый номер телефона");
            LabelPhone.setPrefWidth(Region.USE_COMPUTED_SIZE);
            LabelPhone.setPrefHeight(Region.USE_COMPUTED_SIZE);
            LabelPhone.setLayoutX(14);
            LabelPhone.setLayoutY(48);
            LabelPhone.getStyleClass().add("label_signature");

            Label LabelIDPassport=new Label("ID паспорта");
            LabelIDPassport.setPrefWidth(Region.USE_COMPUTED_SIZE);
            LabelIDPassport.setPrefHeight(Region.USE_COMPUTED_SIZE);
            LabelIDPassport.setLayoutX(14);
            LabelIDPassport.setLayoutY(81);
            LabelIDPassport.getStyleClass().add("label_signature");

            Label label=new Label("Запись сформирована на ");
            label.setPrefWidth(Region.USE_COMPUTED_SIZE);
            label.setPrefHeight(Region.USE_COMPUTED_SIZE);
            label.setLayoutX(14);
            label.setLayoutY(121);
            label.getStyleClass().add("label_signature");

            Label LabelDate=new Label(listRecords.get(i).getDateRecords());
            LabelDate.setPrefWidth(Region.USE_COMPUTED_SIZE);
            LabelDate.setPrefHeight(Region.USE_COMPUTED_SIZE);
            LabelDate.setLayoutX(191);
            LabelDate.setLayoutY(121);
            LabelDate.getStyleClass().add("label_signature");

            Button acceptPatient=new Button("Принять пациента");
            acceptPatient.setPrefWidth(185);
            acceptPatient.setPrefHeight(31);
            acceptPatient.setLayoutX(510);
            acceptPatient.setLayoutY(60);

            Button cancelPatient=new Button("Отменить запись");
            cancelPatient.setPrefWidth(185);
            cancelPatient.setPrefHeight(31);
            cancelPatient.setLayoutX(510);
            cancelPatient.setLayoutY(90);

            FontAwesomeIconView fontAccept= new FontAwesomeIconView(FontAwesomeIcon.USER_PLUS);
            fontAccept.setSize("18");
            fontAccept.setFill(Color.WHITE);
            FontAwesomeIconView fontCancel= new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            fontCancel.setSize("18");
            fontCancel.setFill(Color.WHITE);

            int finalI = i;
            acceptPatient.setOnAction(actionEvent -> {
                try {
                    Client.getInstance().getOut().writeObject(Messages.ACCEPT_PATIENT);
                    Client.getInstance().getOut().writeObject(Integer.parseInt(labelIDPAtient.getText()));
                    acnRecordsHeader.getChildren().clear();
                    initializedRecords();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            });
            cancelPatient.setOnAction(actionEvent -> {
                try {
                    Client.getInstance().getOut().writeObject(Messages.СANCEL_PATIENT);
                    Client.getInstance().getOut().writeObject(Integer.parseInt(labelIDPAtient.getText()));
                    acnRecordsHeader.getChildren().clear();
                    initializedRecords();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });


            anchorPane.getChildren().add(textFio);
            anchorPane.getChildren().add(labelIDPAtient);
            anchorPane.getChildren().add(textPhone);
            anchorPane.getChildren().add(textIdPassport);
            anchorPane.getChildren().add(label);
            anchorPane.getChildren().add(LabelDate);
            anchorPane.getChildren().add(LabelPhone);
            anchorPane.getChildren().add(LabelFIO);
            anchorPane.getChildren().add(LabelIDPassport);
            anchorPane.getChildren().add(acceptPatient);
            anchorPane.getChildren().add(cancelPatient);
            acnRecordsHeader.getChildren().add(anchorPane);
        }
    }
    private void initializedReview() throws IOException, ClassNotFoundException {
        List<Review>list=(List<Review>) Client.getInstance().getIn().readObject();
        double y=10;
        double x=20;
        for (int i=0;i<list.size();i++){
            if(i!=0){
                y=y+145;
            }
            AnchorPane anchorPaneH=new AnchorPane();
            anchorPaneH.setPrefHeight(135);
            anchorPaneH.setPrefWidth(702);
            anchorPaneH.getStyleClass().add("box_groupe");
            anchorPaneH.setLayoutY(y);
            anchorPaneH.setLayoutX(x);

            TextArea textArea=new TextArea(list.get(i).getReview());
            textArea.setWrapText(true);
            textArea.setPrefHeight(106);
            textArea.setPrefWidth(502);
            textArea.setLayoutY(14);
            textArea.setLayoutX(14);
            textArea.setEditable(false);
            textArea.setCursor(Cursor.DEFAULT);
            textArea.getStyleClass().add("textDefaulr");

            Line line=new Line();
            line.setEndY(-49);
            line.setEndX(26.39996337890625);
            line.setStartY(70.19999694824219);
            line.setStartX(26.39996337890625);
            line.setLayoutY(57);
            line.setLayoutX(501);
            line.setStroke(Paint.valueOf("WHITE"));

            HBox hBox=new HBox();
            hBox.setPrefHeight(31);
            hBox.setPrefWidth(156);
            hBox.setLayoutY(78);
            hBox.setLayoutX(536);

            Label labelRating=new Label("Оценка ");
            labelRating.setPrefHeight(20);
            labelRating.setPrefWidth(58);
            labelRating.getStyleClass().add("label_signature");

            Label labelDate=new Label(list.get(i).getDate());
            labelDate.setPrefHeight(Region.USE_COMPUTED_SIZE);
            labelDate.setPrefWidth(Region.USE_COMPUTED_SIZE);
            labelDate.setLayoutY(110);
            labelDate.setLayoutX(536);
            labelDate.getStyleClass().add("label_signature");

            Label labelFIO=new Label();
            labelFIO.setPrefHeight(43);
            labelFIO.setPrefWidth(128);
            labelFIO.setLayoutY(19);
            labelFIO.setLayoutX(539);
            labelFIO.setWrapText(true);
            labelFIO.getStyleClass().add("label_signature");
            labelFIO.setAlignment(Pos.CENTER_LEFT);
            hBox.getChildren().add(labelRating);
            for (int j=0;j<list.get(i).getRating();j++){
                FontAwesomeIconView fontAwesomeIconView=new FontAwesomeIconView(FontAwesomeIcon.STAR);
                fontAwesomeIconView.setFill(Paint.valueOf("White"));
                fontAwesomeIconView.setSize("20");
                hBox.getChildren().add(fontAwesomeIconView);
            }

            Client.getInstance().getOut().writeObject(Messages.FIND_PATIEN);
            Client.getInstance().getOut().writeObject(list.get(i).getId_user());
            Patient paint=(Patient) Client.getInstance().getIn().readObject();
            if(paint.getName()==null){
                labelFIO.setText("Пользователь был удален");
            }
            else {
                labelFIO.setText(paint.getName()+" "+paint.getSur_name()+" "+paint.getPatronymic());
            }

            anchorPaneH.getChildren().add(labelDate);
            anchorPaneH.getChildren().add(textArea);
            anchorPaneH.getChildren().add(labelFIO);
            anchorPaneH.getChildren().add(hBox);
            anchorPaneH.getChildren().add(line);
            anchorHeaderReview.getChildren().add(anchorPaneH);

        }
    }
    private void initializedDoctor() throws IOException, ClassNotFoundException {
        col_doctor_Id_user.setCellValueFactory(new PropertyValueFactory<Doctor,Integer>("id_user"));
        col_doctor_name.setCellValueFactory(new PropertyValueFactory<Doctor,String>("name"));
        col_doctor_surName.setCellValueFactory(new PropertyValueFactory<Doctor,String>("sur_name"));
        col_doctor_patronymic.setCellValueFactory(new PropertyValueFactory<Doctor,String>("patronymic"));
        col_doctor_speciality.setCellValueFactory(new PropertyValueFactory<Doctor,String>("speciality"));
        col_doctor_education.setCellValueFactory(new PropertyValueFactory<Doctor,String>("education"));
        col_doctor_expirience.setCellValueFactory(new PropertyValueFactory<Doctor,String>("expiriense"));
        col_doctor_university.setCellValueFactory(new PropertyValueFactory<Doctor,String>("university"));
        List<Doctor> list=(List<Doctor>) Client.getInstance().getIn().readObject();
        ObservableList<Doctor> tableListDoctor = FXCollections.observableArrayList(list);
        filterDoctor= new FilteredList<>(tableListDoctor, p -> true);
        tableDoctor.setItems(tableListDoctor);
        SortedList<Doctor> sort= new SortedList<>(filterDoctor);
        sort.comparatorProperty().bind(tableDoctor.comparatorProperty());
        tableDoctor.setItems(sort);
    }
    private void intializedPersonalAccount() throws IOException, ClassNotFoundException {
        User user=(User) Client.getInstance().getIn().readObject();
        Doctor doctor=(Doctor) Client.getInstance().getIn().readObject();
        Work_day work_day=(Work_day) Client.getInstance().getIn().readObject();
        inpPersonalName.setText(doctor.getName());
        inpPersonalSurName.setText(doctor.getSur_name());
        inpPersonalPatronymic.setText(doctor.getPatronymic());
        inpPersonalPhone.setText(doctor.getPhone());
        inpPersonallogin.setText(user.getLogin());
        inpPersonalPassword.setText(user.getPassword());
        inpPersonalIDPassport.setText(doctor.getID_passport());
        inpPersonalSeriaNumber.setText(doctor.getSeria_number());
        inpPersonalUniversity.setText(doctor.getUniversity());
        inpPersonalSpecility.setText(doctor.getSpeciality());
        inpPersonalEducation.setText(doctor.getEducation());
        int rating=(int)doctor.getRating();
        for(int i=0;i<rating;i++){
            FontAwesomeIconView fontAwesomeIcon=new FontAwesomeIconView(FontAwesomeIcon.STAR);
            fontAwesomeIcon.setFill(Color.YELLOW);
            fontAwesomeIcon.setSize("25");
            acnhorHStar.getChildren().add(fontAwesomeIcon);
        }
        if(!work_day.getMonday()){MDayOff.setSelected(false);}
           else{ MWorking.setSelected(true);}
        if(!work_day.getTuesday()) {TDayOff.setSelected(false);}
            else {TWorking.setSelected(true);}
        if(!work_day.getWednesday()){WDayOff.setSelected(false);}
            else {WWorking.setSelected(true);}
        if(!work_day.getThursday()) {ThDayOff.setSelected(false);}
            else {THWorking.setSelected(true);}
        if(!work_day.getFriday()) {FDayOff.setSelected(false);}
            else {FWorking.setSelected(true);}

    }
    private void tools_action(Button button,AnchorPane anchorPane){
        but_records.setStyle("");
        but_settins.setStyle("");
        but_doctor.setStyle("");
        but_comment.setStyle("");
        but_rating.setStyle("");
        block_doctor.setVisible(false);
        block_settins.setVisible(false);
        block_rating.setVisible(false);
        block_reviews.setVisible(false);
        block_records.setVisible(false);
        anchorPane.setVisible(true);
        button.setStyle("-fx-background-color: #a63c46");
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        but_records_All.setStyle("-fx-background-color: #a63c46");
        but_records_All.setOnAction(actionEvent -> {
            try {
                but_records_Today.setStyle("");
                but_records_All.setStyle("-fx-background-color: #a63c46");
                acnRecordsHeader.getChildren().clear();
                Client.getInstance().getOut().writeObject(Messages.RECORDS_ALL);
                initializedRecords();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        but_records_Today.setOnAction(actionEvent -> {
            try {
                but_records_All.setStyle("");
                but_records_Today.setStyle("-fx-background-color: #a63c46");
                acnRecordsHeader.getChildren().clear();
                Client.getInstance().getOut().writeObject(Messages.RECORDS_TODAY);
                initializedRecords();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        initializedRecords();
        initializedRatingDoctor();
        initializedReview();
        initializedDoctor();
        intializedPersonalAccount();
        but_settins.setOnAction(actionEvent -> {
            tools_action(but_settins,block_settins);
        });
        but_rating.setOnAction(actionEvent -> {
            tools_action(but_rating,block_rating);
        });
        but_comment.setOnAction(actionEvent -> {
            tools_action(but_comment,block_reviews);
        });
        but_doctor.setOnAction(actionEvent -> {
            tools_action(but_doctor,block_doctor);
        });
        but_records.setOnAction(actionEvent -> {
            tools_action(but_records,block_records);
        });
        inputSearchDoctor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                filterDoctor.setPredicate(myObject -> {
                    if (t1 == null || t1.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = t1.toLowerCase();
                    if (String.valueOf(myObject.getId_user()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(myObject.getName()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }if(String.valueOf(myObject.getSur_name()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }if(String.valueOf(myObject.getPatronymic()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }if(String.valueOf(myObject.getUniversity()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }if(String.valueOf(myObject.getExpiriense()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }if(String.valueOf(myObject.getEducation()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }if(String.valueOf(myObject.getSpeciality()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false; // Does not match.
                });
            }
        });
        but_exit.setOnAction(actionEvent -> {
            try {
                Client.getInstance().getOut().writeObject(Messages.EXIT);
                but_exit.getScene().getWindow().hide();
                StartMenuController startMenuController=new StartMenuController();
                startMenuController.Show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        MWorking.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(t1==true){
                    MDayOff.selectedProperty().setValue(false);
                }
                else {
                    MDayOff.selectedProperty().setValue(true);
                }
            }
        });
        TWorking.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(t1==true){
                    TDayOff.selectedProperty().setValue(false);
                }
                else {
                    TDayOff.selectedProperty().setValue(true);
                }
            }
        });
        WWorking.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(t1==true){
                    WDayOff.selectedProperty().setValue(false);
                }
                else {
                    WDayOff.selectedProperty().setValue(true);
                }
            }
        });
        THWorking.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(t1==true){
                    ThDayOff.selectedProperty().setValue(false);
                }
                else {
                    ThDayOff.selectedProperty().setValue(true);
                }
            }
        });
        FWorking.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(t1==true){
                    FDayOff.selectedProperty().setValue(false);
                }
                else {
                    FDayOff.selectedProperty().setValue(true);
                }
            }
        });
        MDayOff.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(t1==true){
                    MWorking.selectedProperty().setValue(false);
                }
                else {
                    MWorking.selectedProperty().setValue(true);
                }
            }
        });
        TDayOff.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(t1==true){
                    TWorking.selectedProperty().setValue(false);
                }
                else {
                    TWorking.selectedProperty().setValue(true);
                }
            }
        });
        WDayOff.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(t1==true){
                    WWorking.selectedProperty().setValue(false);
                }
                else {
                    WWorking.selectedProperty().setValue(true);
                }
            }
        });
        ThDayOff.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(t1==true){
                    THWorking.selectedProperty().setValue(false);
                }
                else {
                    THWorking.selectedProperty().setValue(true);
                }
            }
        });
        FDayOff.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(t1==true){
                    FWorking.selectedProperty().setValue(false);
                }
                else {
                    FWorking.selectedProperty().setValue(true);
                }
            }
        });
        but_save_personalInfo.setOnAction(actionEvent -> {
            FormValidator formValidator=new FormValidator();
            boolean trigger=false;
            if(!inpPersonalName.getText().matches("[ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэюбьтимсчяёЁ]{2,40}")){formValidator.validationInput(inpPersonalName,"Имя не может сожержать цифры и символы");trigger=true;}
            if(!inpPersonalSurName.getText().matches("[ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэюбьтимсчяёЁ]{2,40}")){formValidator.validationInput(inpPersonalSurName,"Фамилия не может сожержать цифры и символы");trigger=true;}
            if(!inpPersonalPatronymic.getText().matches("[ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэюбьтимсчяёЁ]{2,40}")){formValidator.validationInput(inpPersonalPatronymic,"Отчество не может сожержать цифры и символы");trigger=true;}
            if(inpPersonalPassword.getText().equals("")||inpPersonalPassword.getText().length()<2){formValidator.validationInput(inpPersonalPassword,"Пароль должен быть более 2 символов");trigger=true;}
            if(inpPersonallogin.getText().equals("")||inpPersonallogin.getText().length()<2){formValidator.validationInput(inpPersonallogin,"Логин должен быть более 2 символов");trigger=true;}
            if(!inpPersonalPhone.getText().matches("(\\+375|80)\\d{9}")){
                formValidator.validationInput(inpPersonalPhone,"+375 или 80 (29,44,25..) xxxxxxx");trigger=true;}
            if(trigger==false){
                User user=new User();
                user.setLogin(inpPersonallogin.getText());
                user.setPassword(inpPersonalPassword.getText());
                Work_day work_day=new Work_day();
                if(MDayOff.isSelected()){work_day.setMonday(false);}
                else {work_day.setMonday(false);}
                if(ThDayOff.isSelected()){work_day.setThursday(false);}
                else {work_day.setTuesday(false);}
                if(WDayOff.isSelected()){work_day.setWednesday(false);}
                else {work_day.setWednesday(false);}
                if(ThDayOff.isSelected()){work_day.setThursday(false);}
                else {work_day.setThursday(false);}
                if(FDayOff.isSelected()){work_day.setFriday(false);}
                else {work_day.setFriday(false);}
                Doctor doctor=new Doctor();
                doctor.setName(inpPersonalName.getText());
                doctor.setSur_name(inpPersonalSurName.getText());
                doctor.setPatronymic(inpPersonalPatronymic.getText());
                doctor.setPhone(inpPersonalPhone.getText());
                try {
                    Client.getInstance().getOut().writeObject(Messages.UPDATE_PESONAL_INFO);
                    Client.getInstance().getOut().writeObject(user);
                    Client.getInstance().getOut().writeObject(work_day);
                    Client.getInstance().getOut().writeObject(doctor);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                SuccessfullyArletController successfullyArletController=new SuccessfullyArletController();
                successfullyArletController.Show();
            }
            else {
                formValidator.disabledComponent(but_save_personalInfo);
            }
        });
    }
}
