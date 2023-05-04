package controllers;

import Client.Client;
import config.DateConfigure;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import message.Messages;
import models.*;
import org.controlsfx.control.Rating;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserMenuController {

    private Stage stage;

    public void Show(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/templates/user menu/UserMenu.fxml"));
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
    private Label LsymboltextComplain;

    @FXML
    private AnchorPane acnchorHeaderComplain;

    @FXML
    private AnchorPane block_complaint_book;

    @FXML
    private Label block_complaint_book3;

    @FXML
    private Label block_complaint_book31;

    @FXML
    private Label block_complaint_book32;

    @FXML
    private AnchorPane block_doctors_list;

    @FXML
    private AnchorPane block_doctrors;

    @FXML
    private AnchorPane block_review;

    @FXML
    private AnchorPane block_settins;

    @FXML
    private Button butTool__complaint_book;

    @FXML
    private Button butTool_comment;

    @FXML
    private Button butTool_doctor;

    @FXML
    private Button butTool_history;

    @FXML
    private Button butTool_settins;

    @FXML
    private Button but_clear_complain;

    @FXML
    private Button but_clear_review;

    @FXML
    private Button but_complain;

    @FXML
    private Button but_exit;

    @FXML
    private Button but_savePersonalInfo;

    @FXML
    private Button but_send_review;

    @FXML
    private Rating choose_rating_reviw;

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
    private TableColumn<Doctor, String> col_review_educationDoctor;

    @FXML
    private TableColumn<Doctor, Integer> col_review_idDoctor;

    @FXML
    private TableColumn<Doctor, String> col_review_namDoctor;

    @FXML
    private TableColumn<Doctor, String> col_review_patronymicDoctor;

    @FXML
    private TableColumn<Doctor, String> col_review_specialityDoctor;

    @FXML
    private TableColumn<Doctor, String> col_review_surNameDoctor;

    @FXML
    private TextField inpSearchDoctor;

    @FXML
    private TextField input_personalBirth;

    @FXML
    private TextField input_personalDateRegistration;

    @FXML
    private TextField input_personalIDPassport;

    @FXML
    private TextField input_personalLogin;

    @FXML
    private TextField input_personalName;

    @FXML
    private TextField input_personalPassword;

    @FXML
    private TextField input_personalPatronymic;

    @FXML
    private TextField input_personalPhone;

    @FXML
    private TextField input_personalSeriaNumber;

    @FXML
    private TextField input_personalSex;

    @FXML
    private TextField input_personalStatus;

    @FXML
    private TextField input_personalSurname;

    @FXML
    private Label label_rating_doctor;

    @FXML
    private Label label_signature_review;

    @FXML
    private AnchorPane rating_block_review;

    @FXML
    private AnchorPane review_block_review;

    @FXML
    private TableView<Doctor> tableDoctor;

    @FXML
    private TableView<Doctor> table_review;

    @FXML
    private TextArea text_complain;

    @FXML
    private TextArea text_review;

    @FXML
    private AnchorPane acnhorHeaderHistory;

    @FXML
    private AnchorPane block_history;

    private FilteredList<Doctor> filterDoctor;
    private void initializedHistory() throws IOException, ClassNotFoundException {
        List<History>list=(List<History>) Client.getInstance().getIn().readObject();
        double x=4;
        double y=6;
        for (int i=0;i<list.size();i++){

            if(i!=0){
                y=y+100;
            }
            AnchorPane anchorPane=new AnchorPane();
            anchorPane.setLayoutY(y);
            anchorPane.setLayoutX(x);
            anchorPane.setPrefHeight(80);
            anchorPane.setPrefWidth(734);
            anchorPane.getStyleClass().add("box_groupe");

            Label labelD=new Label("Врач : ");
            labelD.setLayoutY(14);
            labelD.setLayoutX(14);
            labelD.setPrefHeight(Region.USE_COMPUTED_SIZE);
            labelD.setPrefWidth(Region.USE_COMPUTED_SIZE);
            labelD.getStyleClass().add("label_signature");

            Label labelS=new Label("Специальность : ");
            labelS.setLayoutY(50);
            labelS.setLayoutX(14);
            labelS.setPrefHeight(Region.USE_COMPUTED_SIZE);
            labelS.setPrefWidth(Region.USE_COMPUTED_SIZE);
            labelS.getStyleClass().add("label_signature");

            Label labelDate=new Label("Дата посящения : ");
            labelDate.setLayoutY(50);
            labelDate.setLayoutX(481);
            labelDate.setPrefHeight(Region.USE_COMPUTED_SIZE);
            labelDate.setPrefWidth(Region.USE_COMPUTED_SIZE);
            labelDate.getStyleClass().add("label_signature");

            Label labelStatus=new Label("Статус");
            labelStatus.setLayoutY(14);
            labelStatus.setLayoutX(481);
            labelStatus.setPrefHeight(Region.USE_COMPUTED_SIZE);
            labelStatus.setPrefWidth(Region.USE_COMPUTED_SIZE);
            labelStatus.getStyleClass().add("label_signature");

            Label date=new Label(list.get(i).getDate());
            date.setLayoutY(50);
            date.setLayoutX(600);
            date.setPrefHeight(Region.USE_COMPUTED_SIZE);
            date.setPrefWidth(Region.USE_COMPUTED_SIZE);
            date.getStyleClass().add("label_signature");

            Label fio=new Label(list.get(i).getFIOdoctor());
            fio.setLayoutY(14);
            fio.setLayoutX(58);
            fio.setPrefHeight(Region.USE_COMPUTED_SIZE);
            fio.setPrefWidth(Region.USE_COMPUTED_SIZE);
            fio.getStyleClass().add("label_signature");

            Label speciality=new Label(list.get(i).getSpeciality());
            speciality.setLayoutY(50);
            speciality.setLayoutX(124);
            speciality.setPrefHeight(Region.USE_COMPUTED_SIZE);
            speciality.setPrefWidth(Region.USE_COMPUTED_SIZE);
            speciality.getStyleClass().add("label_signature");

            Label status=new Label(list.get(i).getStatus());
            status.setLayoutY(14);
            status.setLayoutX(535);
            status.setPrefHeight(Region.USE_COMPUTED_SIZE);
            status.setPrefWidth(Region.USE_COMPUTED_SIZE);
            status.getStyleClass().add("label_signature");

            anchorPane.getChildren().add(labelD);
            anchorPane.getChildren().add(labelS);
            anchorPane.getChildren().add(labelStatus);
            anchorPane.getChildren().add(labelDate);
            anchorPane.getChildren().add(date);
            anchorPane.getChildren().add(fio);
            anchorPane.getChildren().add(speciality);
            anchorPane.getChildren().add(status);
            acnhorHeaderHistory.getChildren().add(anchorPane);
        }
    }
    private void initializedComplain(){
        try {
            double x=0;
            double y=0;
            List<ComplainBook> list=(List<ComplainBook>) Client.getInstance().getIn().readObject();
            for (int i=0;i<list.size();i++){
                AnchorPane anchorPane=new AnchorPane();
                anchorPane.getStyleClass().add("box_groupe_no_radius");
                if(i!=0){
                    y=y+120;
                }
                anchorPane.setLayoutX(x);
                anchorPane.setLayoutY(y);
                anchorPane.setPrefWidth(718);
                anchorPane.setPrefHeight(100);

                Label labelIdUser=new Label(Integer.toString(list.get(i).getId_user()));
                labelIdUser.setVisible(false);

                Label labelComplint=new Label(list.get(i).getComplain());
                labelComplint.setWrapText(true);
                labelComplint.setPrefWidth(520);
                labelComplint.setLayoutY(9);
                labelComplint.setLayoutX(10);
                labelComplint.setMaxHeight(100);
                labelComplint.getStyleClass().add("label_signature");
                labelComplint.setAlignment(Pos.TOP_LEFT);

                Line line =new Line();
                line.setStroke(Paint.valueOf("WHITE"));
                line.setStartX(26.39996337890625);
                line.setStartY(40);
                line.setEndX(26.39996337890625);
                line.setEndY(-37.59999465942383);
                line.setLayoutX(525);
                line.setLayoutY(48);

                Label label=new Label("Оценка");
                label.getStyleClass().add("label_signature");
                label.setLayoutX(564);
                label.setLayoutY(66);
                FontAwesomeIconView fontAwesomeIcon=new FontAwesomeIconView(FontAwesomeIcon.STAR);
                fontAwesomeIcon.setFill(Color.WHITE);
                fontAwesomeIcon.setSize("20");
                fontAwesomeIcon.setLayoutX(619);
                fontAwesomeIcon.setLayoutY(84);

                Label labelFIO=new Label(list.get(i).getFIO());
                labelFIO.setLayoutX(564);
                labelFIO.setLayoutY(10);
                labelFIO.getStyleClass().add("label_signature");
                labelFIO.setPrefWidth(128);
                labelFIO.setPrefHeight(43);
                labelFIO.setAlignment(Pos.CENTER_LEFT);
                labelFIO.setTextAlignment(TextAlignment.CENTER);
                labelFIO.setWrapText(true);

                anchorPane.setOnMouseClicked(mouseEvent -> {

                });

                anchorPane.getChildren().add(labelIdUser);
                anchorPane.getChildren().add(labelComplint);
                anchorPane.getChildren().add(labelFIO);
                anchorPane.getChildren().add(fontAwesomeIcon);
                anchorPane.getChildren().add(label);
                anchorPane.getChildren().add(line);
                acnchorHeaderComplain.getChildren().add(anchorPane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void initializedTableDoctor() throws IOException, ClassNotFoundException {
        col_review_idDoctor.setCellValueFactory(new PropertyValueFactory<Doctor,Integer>("id_user"));
        col_review_namDoctor.setCellValueFactory(new PropertyValueFactory<Doctor,String>("name"));
        col_review_surNameDoctor.setCellValueFactory(new PropertyValueFactory<Doctor,String>("sur_name"));
        col_review_patronymicDoctor.setCellValueFactory(new PropertyValueFactory<Doctor,String>("patronymic"));
        col_review_specialityDoctor.setCellValueFactory(new PropertyValueFactory<Doctor,String>("speciality"));
        col_review_educationDoctor.setCellValueFactory(new PropertyValueFactory<Doctor,String>("education"));

        col_doctor_Id_user.setCellValueFactory(new PropertyValueFactory<Doctor,Integer>("id_user"));
        col_doctor_name.setCellValueFactory(new PropertyValueFactory<Doctor,String>("name"));
        col_doctor_surName.setCellValueFactory(new PropertyValueFactory<Doctor,String>("sur_name"));
        col_doctor_patronymic.setCellValueFactory(new PropertyValueFactory<Doctor,String>("patronymic"));
        col_doctor_speciality.setCellValueFactory(new PropertyValueFactory<Doctor,String>("speciality"));
        col_doctor_education.setCellValueFactory(new PropertyValueFactory<Doctor,String>("education"));
        col_doctor_expirience.setCellValueFactory(new PropertyValueFactory<Doctor,String>("expiriense"));
        col_doctor_university.setCellValueFactory(new PropertyValueFactory<Doctor,String>("university"));
        User user=(User)Client.getInstance().getIn().readObject();
        Patient paint=(Patient)Client.getInstance().getIn().readObject();
        List<Doctor>list=(List<Doctor>) Client.getInstance().getIn().readObject();
        ObservableList<Doctor> tableListDoctor = FXCollections.observableArrayList(list);
        ObservableList<Doctor> tableReviewDoctor = FXCollections.observableArrayList(list);
        filterDoctor= new FilteredList<>(tableListDoctor, p -> true);
        tableDoctor.setItems(tableListDoctor);
        SortedList<Doctor> sort= new SortedList<>(filterDoctor);
        sort.comparatorProperty().bind(tableDoctor.comparatorProperty());
        tableDoctor.setItems(sort);
        table_review.setItems(tableReviewDoctor);
        input_personalLogin.setText(user.getLogin());
        input_personalPassword.setText(user.getPassword());
        input_personalStatus.setText(user.getStatus());
        input_personalName.setText(paint.getName());
        input_personalSurname.setText(paint.getSur_name());
        input_personalPatronymic.setText(paint.getPatronymic());
        input_personalPhone.setText(paint.getPhone());
        input_personalDateRegistration.setText(paint.getDate_registration());
        input_personalBirth.setText(paint.getDate_birth());
        input_personalSex.setText(paint.getSex());
        input_personalIDPassport.setText(paint.getID_passport());
        input_personalSeriaNumber.setText(paint.getSeria_number());
    }
    private void tools_action(Button button,AnchorPane anchorPane){
        but_complain.setStyle("");
        butTool_doctor.setStyle("");
        butTool_history.setStyle("");
        butTool_comment.setStyle("");
        butTool_settins.setStyle("");
        butTool__complaint_book.setStyle("");
        block_settins.setVisible(false);
        block_complaint_book.setVisible(false);
        block_review.setVisible(false);
        block_doctrors.setVisible(false);
        block_history.setVisible(false);
        anchorPane.setVisible(true);
        button.setStyle("-fx-background-color: #a63c46");
    }
    private void initialized() throws IOException, ClassNotFoundException {
        initializedHistory();
        initializedComplain();
        initializedTableDoctor();
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        initialized();
        tableDoctor.setRowFactory(doctorTableView -> {
            TableRow<Doctor>row=new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        Client.getInstance().getOut().writeObject(Messages.RECORD_MENU);
                        Client.getInstance().getOut().writeObject(row.getItem().getId_user());
                        RecordsDoctorController recordsDoctorController=new RecordsDoctorController();
                        recordsDoctorController.Show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
                return row;
        });
       inpSearchDoctor.textProperty().addListener(new ChangeListener<String>() {
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
       text_complain.textProperty().addListener(new ChangeListener<String>() {
           @Override
           public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
               text_complain.setText( t1.length() > 1000 ? t1.substring(0, 1000)  : t1);
               LsymboltextComplain.setText(Integer.toString(1000-text_complain.getText().length()));
               if(t1.length()==1001){
                   text_complain.setText(s);
               }
           }
       });
       but_complain.setOnAction(actionEvent -> {
            if(text_complain.getText().equals("")){
                FormValidator formValidator=new FormValidator();
                formValidator.validationInput(text_complain ,"Введите отзыв!");
                formValidator.disabledComponent(but_complain);
            }
            else {
                try {
                    Client.getInstance().getOut().writeObject(Messages.ADD_COMPLAINT);
                    Client.getInstance().getOut().writeObject(text_complain.getText());
                    Client.getInstance().getOut().writeObject(DateConfigure.getDateConfigure().nowDate());
                    text_complain.setText("");
                    SuccessfullyArletController successfullyArletController=new SuccessfullyArletController();
                    successfullyArletController.Show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
       });
       but_clear_complain.setOnAction(actionEvent -> {
           text_complain.setText("");
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
       butTool_history.setOnAction(actionEvent -> {
           tools_action(butTool_history,block_history);
           try {
               Client.getInstance().getOut().writeObject(Messages.UPDATE_HISTORY);
               initializedHistory();
           } catch (IOException | ClassNotFoundException e) {
               e.printStackTrace();
           }
       });
       butTool_comment.setOnAction(actionEvent -> {
            tools_action(butTool_comment,block_review);
        });
       butTool_doctor.setOnAction(actionEvent -> {
           tools_action(butTool_doctor,block_doctrors);
       });
       butTool_settins.setOnAction(actionEvent -> {
            tools_action(butTool_settins,block_settins);
        });
       butTool__complaint_book.setOnAction(actionEvent -> {
           tools_action(butTool__complaint_book,block_complaint_book);
           try {
               Client.getInstance().getOut().writeObject(Messages.UPDATE_COMPLAIN);
               initializedComplain();
           } catch (IOException  e) {
               e.printStackTrace();
           }
       });
       table_review.setRowFactory(doctorTableView -> {
           TableRow<Doctor> row=new TableRow();
           row.setOnMouseClicked(mouseEvent -> {
               if(mouseEvent.getClickCount()==1&&(!row.isEmpty())){
                   try {
                       review_block_review.setVisible(true);
                       rating_block_review.setVisible(true);
                       Client.getInstance().getOut().writeObject(Messages.TAKE_RATING);
                       Client.getInstance().getOut().writeObject(row.getItem().getId_user());
                       label_rating_doctor.setText(Double.toString((double)Client.getInstance().getIn().readObject()));
                   } catch (IOException e) {
                       e.printStackTrace();
                   } catch (ClassNotFoundException e) {
                       e.printStackTrace();
                   }
               }
               else {
                   review_block_review.setVisible(false);
                   rating_block_review.setVisible(false);
               }
           });
           return row;
       });
       but_send_review.setOnAction(actionEvent -> {
           boolean trigger=false;
           FormValidator formValidator=new FormValidator();
           if(text_review.getText().equals("")){formValidator.validationInput(text_review,"Заполните отзыв"); trigger=true;}
           if(trigger){
               formValidator.disabledComponent(but_send_review);
           }
           else {
               try {
                   Client.getInstance().getOut().writeObject(Messages.CREATE_REVIEW);
                   Review review=new Review();
                   review.setRating((int)choose_rating_reviw.getRating());
                   review.setId_user(table_review.getSelectionModel().getSelectedItem().getId_user());
                   review.setDate(DateConfigure.getDateConfigure().nowDate());
                   review.setReview(text_review.getText());
                   Client.getInstance().getOut().writeObject(review);
                   text_review.setText("");
                   SuccessfullyArletController successfullyArletController=new SuccessfullyArletController();
                   successfullyArletController.Show();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       });
       but_clear_review.setOnAction(actionEvent -> {
           text_review.setText("");
       });
       text_review.textProperty().addListener(new ChangeListener<String>() {
           @Override
           public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
               text_review.setText( t1.length() > 1000 ? t1.substring(0, 1000)  : t1);
               label_signature_review.setText(Integer.toString(1000-text_review.getText().length()));
               if(t1.length()==1001){
                   text_review.setText(s);
               }
           }
       });
       but_savePersonalInfo.setOnAction(actionEvent -> {
           boolean trigger=false;
           FormValidator formValidator=new FormValidator();
           if(!input_personalName.getText().matches("[ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэюбьтимсчяёЁ]{2,40}")){formValidator.validationInput(input_personalName,"Имя не может сожержать цифры и символы");trigger=true;}
           if(!input_personalSurname.getText().matches("[ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэюбьтимсчяёЁ]{2,40}")){formValidator.validationInput(input_personalSurname,"Фамилия не может сожержать цифры и символы");trigger=true;}
           if(!input_personalPatronymic.getText().matches("[ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэюбьтимсчяёЁ]{2,40}")){formValidator.validationInput(input_personalPatronymic,"Отчество не может сожержать цифры и символы");trigger=true;}
           if(input_personalPassword.getText().equals("")||input_personalPassword.getText().length()<2){formValidator.validationInput(input_personalPassword,"Пароль должен быть более 2 символов");trigger=true;}
           if(input_personalLogin.getText().equals("")||input_personalLogin.getText().length()<2){formValidator.validationInput(input_personalLogin,"Логин должен быть более 2 символов");trigger=true;}
           if(!input_personalPhone.getText().matches("(\\+375|80)\\d{9}")){
               formValidator.validationInput(input_personalPhone,"+375 или 80 (29,44,25..) xxxxxxx");trigger=true;}
           if(trigger==false){
               try {
                   Client.getInstance().getOut().writeObject(Messages.UPDATE_PESONAL_INFO);
                   User user=new User();
                   Patient patient=new Patient();
                   patient.setName(input_personalName.getText());
                   patient.setSur_name(input_personalSurname.getText());
                   patient.setPatronymic(input_personalPatronymic.getText());
                   patient.setPhone(input_personalPhone.getText());
                   user.setPassword(input_personalPassword.getText());
                   user.setLogin(input_personalLogin.getText());
                   Client.getInstance().getOut().writeObject(user);
                   Client.getInstance().getOut().writeObject(patient);
                   SuccessfullyArletController arletController=new SuccessfullyArletController();
                   arletController.Show();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           else {
               formValidator.disabledComponent(but_savePersonalInfo);
           }
       });
    }
}
