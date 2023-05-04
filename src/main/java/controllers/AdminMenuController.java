package controllers;

import Client.Client;
import config.DateConfigure;
import config.FormValidator;
import config.RandomGenerator;
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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import message.Messages;
import message.UserStaus;
import models.*;
import org.controlsfx.control.Rating;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminMenuController {

    private Stage stage;

    public void Show(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/templates/admin menu/AdminMenu.fxml"));
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
    private AnchorPane block_black_list;

    @FXML
    private AnchorPane block_client;

    @FXML
    private AnchorPane block_complaint_book;

    @FXML
    private AnchorPane block_doctors_list;

    @FXML
    private AnchorPane block_doctors_registration;

    @FXML
    private AnchorPane block_doctrors;

    @FXML
    private AnchorPane block_registration_admin;

    @FXML
    private AnchorPane block_report;

    @FXML
    private AnchorPane block_settins;

    @FXML
    private AnchorPane block_statistic;

    @FXML
    private Button butTool__complaint_book;

    @FXML
    private Button butTool_addAdmin;

    @FXML
    private Button butTool_blackList;

    @FXML
    private Button butTool_doctor;

    @FXML
    private Button butTool_exit;

    @FXML
    private Button butTool_patient;

    @FXML
    private Button butTool_report;

    @FXML
    private Button butTool_settins;

    @FXML
    private Button butTool_statistic;

    @FXML
    private Button but_list_doctor;

    @FXML
    private Button but_regAdmin;

    @FXML
    private Button but_reg_doctor;

    @FXML
    private TextField in_regAdminIDpassport;

    @FXML
    private TextField in_regAdminLogin;

    @FXML
    private TextField in_regAdminName;

    @FXML
    private TextField in_regAdminPassword;

    @FXML
    private TextField in_regAdminPatronymic;

    @FXML
    private TextField in_regAdminPhone;

    @FXML
    private TextField in_regAdminSeriaNumber;

    @FXML
    private TextField in_regAdminSurname;

    @FXML
    private ComboBox<String> regDoctor_CBoxEducation;

    @FXML
    private ComboBox<String> regDoctor_CBoxSpeciality;

    @FXML
    private TextField regDoctor_IDpassport;

    @FXML
    private TextField regDoctor_name;

    @FXML
    private TextField regDoctor_patronymic;

    @FXML
    private TextField input_search_doctor;

    @FXML
    private TableColumn<Doctor, String> col_education_doctor;

    @FXML
    private TableColumn<Doctor, String> col_id_doctor;

    @FXML
    private TableColumn<Doctor, String> col_name_doctor;

    @FXML
    private TableColumn<Doctor, String> col_patronymic_doctor;

    @FXML
    private TableColumn<Doctor, String> col_phone_doctor;

    @FXML
    private TableColumn<Doctor, String> col_spetiality_doctor;

    @FXML
    private TableColumn<Doctor, String> col_sur_doctor;

    @FXML
    private TableColumn<Doctor, String> col_university_doctor;

    @FXML
    private TableView<Doctor> table_doctor;

    @FXML
    private TextField regDoctor_phone;

    @FXML
    private Rating regDoctor_rating;

    @FXML
    private TextField regDoctor_seriaNumber;

    @FXML
    private TextField regDoctor_surName;

    @FXML
    private TextField regDoctor_university;

    @FXML
    private TextField reg_doctor_login;

    @FXML
    private TableColumn<Patient, String> col_dateBirth_patient;

    @FXML
    private TableColumn<Patient, String> col_name_patient;

    @FXML
    private TableColumn<Patient, String> col_patronymic_patient;

    @FXML
    private TableColumn<Patient, String> col_phone_patient;

    @FXML
    private TableColumn<Patient, String> col_seriaNumber_patient;

    @FXML
    private TableColumn<Patient, String> col_sex_patient;

    @FXML
    private TableColumn<Patient, String> col_surName_patient;

    @FXML
    private Label label_indexAdmin;

    @FXML
    private Label label_indexPatient;

    @FXML
    private TextField input_personaPhone;

    @FXML
    private TextField input_personalDateRegistration;

    @FXML
    private TextField input_personalLogin;

    @FXML
    private TextField input_personalName;

    @FXML
    private TextField input_personalPassword;

    @FXML
    private TextField input_personalPatronymic;

    @FXML
    private TextField input_personalPosition;

    @FXML
    private TextField input_personalStatus;

    @FXML
    private TextField input_personalSeriaNumber;

    @FXML
    private TextField input_personalIDPassport;

    @FXML
    private TextField input_personalSurname;

    @FXML
    private Label label_indexDoctor;

    @FXML
    private TableView<Patient> table_patirent;

    @FXML
    private TextField input_searchPatient;

    @FXML
    private TableColumn<Patient, String> col_idPassport_patient;

    @FXML
    private PieChart pieChart;

    @FXML
    private AnchorPane acnchorHeaderComplain;

    @FXML
    private LineChart<?, ?> line_chart;

    @FXML
    private Button but_reg_doctorFull;

    @FXML
    private Label label_midle_rating_policlinic;

    @FXML
    private TableColumn<BlackList, String> col_block_DateBlock;

    @FXML
    private TableColumn<BlackList, Integer> col_block_IdAccount;

    @FXML
    private TableColumn<BlackList, String> col_block_button;

    @FXML
    private TableColumn<BlackList, String> col_block_reason;

    @FXML
    private  TableView<BlackList>table_block;

    @FXML
    private Button but_create_report;

    @FXML
    private Button but_clear_report;

    @FXML
    private Button but_save_report;

    @FXML
    private TextArea text_report;

    @FXML
    private TextField reg_doctor_password;

    private FilteredList<Doctor> filterDoctor;
    private FilteredList<Patient> filterPatien;
    private void initializedBlackList() throws IOException, ClassNotFoundException {
        List<BlackList>list=(List<BlackList>) Client.getInstance().getIn().readObject();
        ObservableList<BlackList>blackLists=FXCollections.observableArrayList(list);
        col_block_IdAccount.setCellValueFactory(new PropertyValueFactory<BlackList,Integer>("id_user"));
        col_block_reason.setCellValueFactory(new PropertyValueFactory<BlackList,String>("reason"));
        col_block_DateBlock.setCellValueFactory(new PropertyValueFactory<BlackList,String>("date"));
        Callback<TableColumn<BlackList, String>, TableCell<BlackList, String>> cellFactory=new Callback<TableColumn<BlackList, String>, TableCell<BlackList, String>>() {
            @Override
            public TableCell<BlackList, String> call(TableColumn<BlackList, String> blackListStringTableColumn) {
                final TableCell<BlackList,String>cell=new TableCell<BlackList,String>(){
                    final Button button=new Button("Разблокировать");
                    @Override
                    public void updateItem(String item,boolean empty){
                        button.setOnAction(actionEvent -> {
                            try {
                                Client.getInstance().getOut().writeObject(Messages.UNBLOCK_ACCOUNT_TABLE);
                                TableRow<BlackList>row=(TableRow)button.getParent().getParent();
                                Client.getInstance().getOut().writeObject(row.getItem().getId_user());
                                table_block.getItems().remove(row.getItem());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        super.updateItem(item,empty);
                        if(empty){
                            setGraphic(null);
                            setText(null);
                        }
                        else {
                            setGraphic(button);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        col_block_button.setCellFactory(cellFactory);
        table_block.setItems(blackLists);
    }
    private void initializedStatistic() throws IOException, ClassNotFoundException {
        label_indexAdmin.setText(Integer.toString((int) Client.getInstance().getIn().readObject()));
        label_indexDoctor.setText(Integer.toString((int) Client.getInstance().getIn().readObject()));
        label_indexPatient.setText(Integer.toString((int) Client.getInstance().getIn().readObject()));
        label_midle_rating_policlinic.setText((String) Client.getInstance().getIn().readObject());

        ObservableList<PieChart.Data>pieChartDate= FXCollections.observableArrayList();
        for(int i=0;i<regDoctor_CBoxSpeciality.getItems().size();i++){
            pieChartDate.add(new PieChart.Data(regDoctor_CBoxSpeciality.getItems().get(i),(int)Client.getInstance().getIn().readObject()));

        }
        pieChart.setData(pieChartDate);

        int mon=(int)Client.getInstance().getIn().readObject();
        int tue=(int)Client.getInstance().getIn().readObject();
        int wen=(int)Client.getInstance().getIn().readObject();
        int th=(int)Client.getInstance().getIn().readObject();
        int fr=(int)Client.getInstance().getIn().readObject();
        XYChart.Series series=new XYChart.Series();
        series.getData().add(new XYChart.Data("Понедельник",mon));
        series.getData().add(new XYChart.Data("Вторник",tue));
        series.getData().add(new XYChart.Data("Среда",wen));
        series.getData().add(new XYChart.Data("Четверг",th));
        series.getData().add(new XYChart.Data("Пятница",fr));
        line_chart.getData().add(series);
        line_chart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");
        series.getNode().setStyle("-fx-stroke: #FFD6DC");
    }
    private void initializedComplain(){
        try {
            double x=0;
            double y=0;
            List<ComplainBook>list=(List<ComplainBook>) Client.getInstance().getIn().readObject();
            for (int i=0;i<list.size();i++){
                AnchorPane anchorPane=new AnchorPane();
                anchorPane.getStyleClass().add("box_groupe_no_radius");
                if(i!=0){
                    y=y+102;
                }
                anchorPane.setLayoutX(x);
                anchorPane.setLayoutY(y);
                anchorPane.setPrefWidth(750);
                anchorPane.setPrefHeight(100);

                Label labelIdUser=new Label(Integer.toString(list.get(i).getId_user()));
                labelIdUser.setVisible(false);

                Label labelComplint=new Label(list.get(i).getComplain());
                labelComplint.setWrapText(true);
                labelComplint.setPrefWidth(581);
                labelComplint.setPrefHeight(79);
                labelComplint.setLayoutY(9);
                labelComplint.setLayoutX(10);
                labelComplint.getStyleClass().add("label_signature");
                labelComplint.setAlignment(Pos.TOP_LEFT);

                Line line =new Line();
                line.setStroke(Paint.valueOf("WHITE"));
                line.setStartX(26.39996337890625);
                line.setStartY(40);
                line.setEndX(26.39996337890625);
                line.setEndY(-37.59999465942383);
                line.setLayoutX(574);
                line.setLayoutY(48);

                Label label=new Label("Оценка");
                label.getStyleClass().add("label_signature");
                label.setLayoutX(612);
                label.setLayoutY(69);
                FontAwesomeIconView fontAwesomeIcon=new FontAwesomeIconView(FontAwesomeIcon.STAR);
                fontAwesomeIcon.setFill(Color.WHITE);
                fontAwesomeIcon.setSize("20");
                fontAwesomeIcon.setLayoutX(667);
                fontAwesomeIcon.setLayoutY(87);

                Label labelFIO=new Label(list.get(i).getFIO());
                labelFIO.setLayoutX(612);
                labelFIO.setLayoutY(10);
                labelFIO.getStyleClass().add("label_signature");
                labelFIO.setPrefWidth(128);
                labelFIO.setPrefHeight(43);
                labelFIO.setAlignment(Pos.CENTER_LEFT);
                labelFIO.setTextAlignment(TextAlignment.CENTER);
                labelFIO.setWrapText(true);

                anchorPane.setOnMouseClicked(mouseEvent -> {
                    if(mouseEvent.getClickCount()==2){
                    }
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
    private void initializedTable() throws IOException, ClassNotFoundException {
        User user=(User)Client.getInstance().getIn().readObject();
        Admin admin=(Admin)Client.getInstance().getIn().readObject();
        input_personalName.setText(admin.getName());
        input_personalSurname.setText(admin.getSur_name());
        input_personalPatronymic.setText(admin.getPatronymic());
        input_personaPhone.setText(admin.getPhone());
        input_personalIDPassport.setText(admin.getID_passport());
        input_personalSeriaNumber.setText(admin.getSeria_number());
        input_personalStatus.setText(user.getStatus());
        input_personalPosition.setText(user.getPosition());
        input_personalLogin.setText(user.getLogin());
        input_personalPassword.setText(user.getPassword());
        input_personalDateRegistration.setText(admin.getDate_registration());


        List<Doctor> listDoctor=(List<Doctor>) Client.getInstance().getIn().readObject();
        ObservableList<Doctor> tableListDoctor = FXCollections.observableArrayList(listDoctor);
        filterDoctor= new FilteredList<>(tableListDoctor, p -> true);
        table_doctor.setItems(tableListDoctor);
        SortedList<Doctor> sort= new SortedList<>(filterDoctor);
        sort.comparatorProperty().bind(table_doctor.comparatorProperty());
        table_doctor.setItems(sort);
        List<Patient>listPatient=(List<Patient>) Client.getInstance().getIn().readObject();
        ObservableList<Patient>tableListPatient=FXCollections.observableArrayList(listPatient);
        filterPatien=new FilteredList<>(tableListPatient,p->true);
        table_patirent.setItems(tableListPatient);
        SortedList<Patient> sort1= new SortedList<>(filterPatien);
        sort1.comparatorProperty().bind(table_patirent.comparatorProperty());
        table_patirent.setItems(sort1);
    }
    private void initializedComponent(){
        but_list_doctor.setStyle("-fx-background-color: #a63c46");
        regDoctor_CBoxEducation.getItems().addAll("Высшее","Среднее","Среднее специальное");
        regDoctor_CBoxSpeciality.getItems().addAll("Терапевт","Хирург","Уролог","Невролог","Травматолог","Гастроэнтеролог","Стомотолог");
        col_id_doctor.setCellValueFactory(new PropertyValueFactory<Doctor,String>("ID_passport"));
        col_name_doctor.setCellValueFactory(new PropertyValueFactory<Doctor,String>("name"));
        col_sur_doctor.setCellValueFactory(new PropertyValueFactory<Doctor,String>("sur_name"));
        col_patronymic_doctor.setCellValueFactory(new PropertyValueFactory<Doctor,String>("patronymic"));
        col_phone_doctor.setCellValueFactory(new PropertyValueFactory<Doctor,String>("phone"));
        col_spetiality_doctor.setCellValueFactory(new PropertyValueFactory<Doctor,String>("speciality"));
        col_education_doctor.setCellValueFactory(new PropertyValueFactory<Doctor,String>("education"));
        col_university_doctor.setCellValueFactory(new PropertyValueFactory<Doctor,String>("university"));

        col_idPassport_patient.setCellValueFactory(new PropertyValueFactory<Patient,String>("ID_passport"));
        col_name_patient.setCellValueFactory(new PropertyValueFactory<Patient,String>("name"));
        col_surName_patient.setCellValueFactory(new PropertyValueFactory<Patient,String>("sur_name"));
        col_patronymic_patient.setCellValueFactory(new PropertyValueFactory<Patient,String>("patronymic"));
        col_phone_patient.setCellValueFactory(new PropertyValueFactory<Patient,String>("phone"));
        col_seriaNumber_patient.setCellValueFactory(new PropertyValueFactory<Patient,String>("seria_number"));
        col_sex_patient.setCellValueFactory(new PropertyValueFactory<Patient,String>("sex"));
        col_dateBirth_patient.setCellValueFactory(new PropertyValueFactory<Patient,String>("date_birth"));

    }
    private void initialized() throws IOException, ClassNotFoundException {
        initializedBlackList();
        initializedComponent();
        initializedTable();
        initializedStatistic();
        initializedComplain();
    }
    private void tools_action(Button button,AnchorPane anchorPane){
        butTool_settins.setStyle("");
        butTool_statistic.setStyle("");
        butTool_patient.setStyle("");
        butTool_report.setStyle("");
        butTool_doctor.setStyle("");
        butTool__complaint_book.setStyle("");
        butTool_addAdmin.setStyle("");
        butTool_blackList.setStyle("");
        block_settins.setVisible(false);
        block_statistic.setVisible(false);
        block_doctrors.setVisible(false);
        block_client.setVisible(false);
        block_black_list.setVisible(false);
        block_complaint_book.setVisible(false);
        block_registration_admin.setVisible(false);
        block_report.setVisible(false);
        anchorPane.setVisible(true);
        button.setStyle("-fx-background-color: #a63c46");
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        initialized();
        butTool_exit.setOnAction(actionEvent -> {
            try {
                Client.getInstance().getOut().writeObject(Messages.EXIT);
                butTool_exit.getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
            StartMenuController startMenuController=new StartMenuController();
                startMenuController.Show();
        });
        table_doctor.setRowFactory(doctorTableView -> {
            TableRow<Doctor>row=new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getClickCount()==2&&(!row.isEmpty())){
                    try {
                        Client.getInstance().getOut().writeObject(Messages.PERSONAL_DOCTOR_PAGE);
                        Client.getInstance().getOut().writeObject(row.getItem().getID_passport());
                        PersonalMenuDoctorController personalMenuDoctorController=new PersonalMenuDoctorController();
                        personalMenuDoctorController.Show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
        input_search_doctor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                filterDoctor.setPredicate(myObject -> {
                    if (t1 == null || t1.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = t1.toLowerCase();
                    if (String.valueOf(myObject.getID_passport()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(myObject.getName()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }else if(String.valueOf(myObject.getPhone()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }if(String.valueOf(myObject.getSur_name()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }if(String.valueOf(myObject.getPatronymic()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }if(String.valueOf(myObject.getUniversity()).toLowerCase().contains(lowerCaseFilter)){
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
        input_searchPatient.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                filterPatien.setPredicate(myObject -> {
                    if (t1 == null || t1.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = t1.toLowerCase();
                    if (String.valueOf(myObject.getID_passport()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(myObject.getName()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }else if(String.valueOf(myObject.getPhone()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }if(String.valueOf(myObject.getSur_name()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }if(String.valueOf(myObject.getPatronymic()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }if(String.valueOf(myObject.getSeria_number()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }if(String.valueOf(myObject.getSex()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }if(String.valueOf(myObject.getDate_birth()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false; // Does not match.
                });
            }
        });
        butTool_settins.setOnAction(actionEvent ->{
            tools_action(butTool_settins, block_settins);
                });
        butTool_statistic.setOnAction(actionEvent -> {
            tools_action(butTool_statistic,block_statistic);
            try {
                Client.getInstance().getOut().writeObject(Messages.UPDATE_STATISTIC);
                initializedStatistic();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        butTool_doctor.setOnAction(actionEvent -> {
            tools_action(butTool_doctor,block_doctrors);
        });
        but_create_report.setOnAction(actionEvent -> {
            try {
                Client.getInstance().getOut().writeObject(Messages.CREATE_REPORT);
                String report=(String) Client.getInstance().getIn().readObject();
                text_report.setText(report);
                but_save_report.setDisable(false);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        but_save_report.setOnAction(actionEvent -> {
            File output = new File("Отчет.txt");
            FileWriter writer = null;
            try {
                writer = new FileWriter(output);
                writer.write(text_report.getText());
                writer.flush();
                writer.close();
                SuccessfullyArletController successfullyArletController=new SuccessfullyArletController();
                successfullyArletController.Show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        but_clear_report.setOnAction(actionEvent -> {
            but_save_report.setDisable(true);
            text_report.setText("");

        });
        butTool__complaint_book.setOnAction(actionEvent -> {
            tools_action(butTool__complaint_book,block_complaint_book);
            try {
                Client.getInstance().getOut().writeObject(Messages.UPDATE_COMPLAIN);
            } catch (IOException e) {
                e.printStackTrace();
            }
            initializedComplain();
        });
        butTool_blackList.setOnAction(actionEvent -> {
            tools_action(butTool_blackList,block_black_list);
            try {
                Client.getInstance().getOut().writeObject(Messages.UPDATE_BLACK_LIST);
                initializedBlackList();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        butTool_addAdmin.setOnAction(actionEvent -> {
            tools_action(butTool_addAdmin,block_registration_admin);
            in_regAdminLogin.setText("Admin - "+RandomGenerator.getRandomGenerato().genaratedLogin());
            in_regAdminPassword.setText(RandomGenerator.getRandomGenerato().genaratedPassowrd());
        });
        butTool_report.setOnAction(actionEvent -> {
            tools_action(butTool_report,block_report);
        });
        butTool_patient.setOnAction(actionEvent -> {
            tools_action(butTool_patient,block_client);
        });
        but_reg_doctor.setOnAction(actionEvent -> {
            but_reg_doctor.setStyle("-fx-background-color: #a63c46");
            but_list_doctor.setStyle("");
            block_doctors_registration.setVisible(true);
            block_doctors_list.setVisible(false);
            reg_doctor_login.setText("Doctor - "+RandomGenerator.getRandomGenerato().genaratedLogin());
            reg_doctor_password.setText(RandomGenerator.getRandomGenerato().genaratedPassowrd());
        });
        but_list_doctor.setOnAction(actionEvent -> {
            but_list_doctor.setStyle("-fx-background-color: #a63c46");
            but_reg_doctor.setStyle("");
            block_doctors_list.setVisible(true);
            block_doctors_registration.setVisible(false);
            try {
                Client.getInstance().getOut().writeObject(Messages.UPDATE_DOCTOR);
                initializedTable();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        but_regAdmin.setOnAction(actionEvent -> {
            boolean trigger=false;
            FormValidator formValidator=new FormValidator();
            if(!in_regAdminIDpassport.getText().matches("\\d{7}[A-Z]{1}\\d{3}[A-Z]{2}\\d{1}")){formValidator.validationInput(in_regAdminIDpassport,"Шаблон: 1232222A123AA1");trigger=true;}
            if(!in_regAdminSeriaNumber.getText().matches("[A-Z]{2}\\d{7}")){formValidator.validationInput(in_regAdminSeriaNumber,"Шаблон : MP1234567");trigger=true;}
            if(!in_regAdminName.getText().matches("[ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэюбьтимсчяёЁ]{2,40}")){formValidator.validationInput(in_regAdminName,"Имя не может сожержать цифры и символы");trigger=true;}
            if(!in_regAdminSurname.getText().matches("[ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэюбьтимсчяёЁ]{2,40}")){formValidator.validationInput(in_regAdminSurname,"Фамилия не может сожержать цифры и символы");trigger=true;}
            if(!in_regAdminPatronymic.getText().matches("[ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэюбьтимсчяёЁ]{2,40}")){formValidator.validationInput(in_regAdminPatronymic,"Отчество не может сожержать цифры и символы");trigger=true;}
            if(!in_regAdminPhone.getText().matches("(\\+375|80)\\d{9}")){
                formValidator.validationInput(in_regAdminPhone,"+375 или 80 (29,44,25..) xxxxxxx");trigger=true;}
            if(trigger==false){
                try {
                    Admin admin=new Admin();
                    User user = new User();
                    admin.setName(in_regAdminName.getText());
                    admin.setSur_name(in_regAdminSurname.getText());
                    admin.setPatronymic(in_regAdminPatronymic.getText());
                    admin.setPhone(in_regAdminPhone.getText());
                    admin.setDate_registration(DateConfigure.getDateConfigure().nowDate());
                    admin.setSeria_number(in_regAdminSeriaNumber.getText());
                    admin.setID_passport(in_regAdminIDpassport.getText());
                    user.setPassword(in_regAdminPassword.getText());
                    user.setLogin(in_regAdminLogin.getText());
                    user.setStatus(UserStaus.active);
                    Client.getInstance().getOut().writeObject(Messages.REGISTRATION_ADMIN);
                    Client.getInstance().getOut().writeObject(user);
                    Client.getInstance().getOut().writeObject(admin);
                    in_regAdminIDpassport.setText("");
                    in_regAdminSeriaNumber.setText("");
                    in_regAdminName.setText("");
                    in_regAdminSurname.setText("");
                    in_regAdminPatronymic.setText("");
                    in_regAdminPhone.setText("");
                    in_regAdminLogin.setText("");
                    in_regAdminPassword.setText("");
                    SuccessfullyArletController successfullyArletController=new SuccessfullyArletController();
                    successfullyArletController.Show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                formValidator.disabledComponent(but_regAdmin);
            }
        });
        but_reg_doctorFull.setOnAction(actionEvent -> {
            boolean trigger=false;
            FormValidator formValidator=new FormValidator();
            if(!regDoctor_IDpassport.getText().matches("\\d{7}[A-Z]{1}\\d{3}[A-Z]{2}\\d{1}")){formValidator.validationInput(regDoctor_IDpassport,"Шаблон: 1232222A123AA1");trigger=true;}
            if(!regDoctor_seriaNumber.getText().matches("[A-Z]{2}\\d{7}")){formValidator.validationInput(regDoctor_seriaNumber,"Шаблон : MP1234567");trigger=true;}
            if(!regDoctor_name.getText().matches("[ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэюбьтимсчяёЁ]{2,40}")){formValidator.validationInput(regDoctor_name,"Имя не может сожержать цифры и символы");trigger=true;}
            if(!regDoctor_surName.getText().matches("[ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэюбьтимсчяёЁ]{2,40}")){formValidator.validationInput(regDoctor_surName,"Фамилия не может сожержать цифры и символы");trigger=true;}
            if(!regDoctor_patronymic.getText().matches("[ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэюбьтимсчяёЁ]{2,40}")){formValidator.validationInput(regDoctor_patronymic,"Отчество не может сожержать цифры и символы");trigger=true;}
            if(!regDoctor_phone.getText().matches("(\\+375|80)\\d{9}")){
                formValidator.validationInput(regDoctor_phone,"+375 или 80 (29,44,25..) xxxxxxx");trigger=true;}
            if(regDoctor_CBoxSpeciality.getValue()==null){formValidator.validatorComboBox(regDoctor_CBoxSpeciality);trigger=true;}
            if(regDoctor_CBoxEducation.getValue()==null){formValidator.validatorComboBox(regDoctor_CBoxEducation);trigger=true;}
            if(!regDoctor_university.getText().matches("[ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэюбьтимсчяёЁ]{2,40}")){formValidator.validationInput(regDoctor_university,"Введите название вуза");trigger=true;}
            if(trigger==false){
                try {
                    User user=new User();
                    user.setLogin(reg_doctor_login.getText());
                    user.setPassword(reg_doctor_password.getText());
                    user.setStatus(UserStaus.active);
                    Doctor doctor=new Doctor();
                    doctor.setName(regDoctor_name.getText());
                    doctor.setSur_name(regDoctor_surName.getText());
                    doctor.setPatronymic(regDoctor_patronymic.getText());
                    doctor.setPhone(regDoctor_phone.getText());
                    doctor.setUniversity(regDoctor_university.getText());
                    doctor.setExpiriense("5 лет");
                    doctor.setEducation(regDoctor_CBoxEducation.getValue());
                    doctor.setID_passport(regDoctor_IDpassport.getText());
                    doctor.setSeria_number(regDoctor_seriaNumber.getText());
                    doctor.setSpeciality(regDoctor_CBoxSpeciality.getValue());
                    doctor.setRating(regDoctor_rating.getRating());
                    doctor.setDate_registration(DateConfigure.getDateConfigure().nowDate());
                    Client.getInstance().getOut().writeObject(Messages.REGISTRATION_DOCTOR);
                    Client.getInstance().getOut().writeObject(user);
                    Client.getInstance().getOut().writeObject(doctor);
                    SuccessfullyArletController successfullyArletController=new SuccessfullyArletController();
                    successfullyArletController.Show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                formValidator.disabledComponent(but_reg_doctorFull);
            }
        });
    }

}
