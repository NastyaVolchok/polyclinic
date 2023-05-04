package controllers;

import datebase.Datebase;
import message.Messages;
import message.RecordsStatus;
import models.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.List;

public class PatientMenu {
    public void Start(ObjectInputStream in, ObjectOutputStream out,User user) throws IOException, ClassNotFoundException, SQLException {
        initializedHistory(out, user);
        initializedComplaint(out);
        initializedDoctor(out,user);
        while (true){
            Messages messages=(Messages) in.readObject();
            switch (messages){
                case UPDATE_COMPLAIN:initializedComplaint(out);break;
                case UPDATE_HISTORY:initializedHistory(out,user);break;
                case CREATE_RECORDS:createRecords(in,user);break;
                case RECORD_MENU:recordsMenu(in,out);break;
                case TAKE_RATING:takeRatingDoctor(out,user,in);break;
                case CREATE_REVIEW:createReview(in,user);break;
                case ADD_COMPLAINT:addComplaint(in,user);break;
                case UPDATE_PESONAL_INFO:updatePersonalInfo(in,user);break;
                case EXIT:StartMenu startMenu=new StartMenu();startMenu.Start(in,out);break;
            }
        }
    }
    private void initializedHistory(ObjectOutputStream out ,User user) throws SQLException, ClassNotFoundException, IOException {
        Datebase datebase=new Datebase();
        List<History>history=datebase.full_HistoryById(user.getId());
        out.writeObject(history);
    }
    private void createRecords(ObjectInputStream in,User user) throws IOException, ClassNotFoundException, SQLException {
        Datebase datebase=new Datebase();
        Records records=(Records) in.readObject();
        records.setIdUser(user.getId());
        records.setStatus(RecordsStatus.wainting);
        datebase.createRecords(records);
    }
    private void recordsMenu(ObjectInputStream in,ObjectOutputStream out) throws IOException, ClassNotFoundException, SQLException {
        Datebase datebase=new Datebase();
        int id_user=(Integer)in.readObject();
        Doctor doctor= datebase.findDoctorByIdUser(id_user);
        Work_day work_day=datebase.findWorkDayByIdPAssportDoctor(doctor.getID_passport());
        out.writeObject(doctor);
        out.writeObject(work_day);
    }
    private void takeRatingDoctor(ObjectOutputStream outputStream, User user,ObjectInputStream in) throws SQLException, ClassNotFoundException, IOException {
        Datebase datebase=new Datebase();
        int id_doctor=(int)in.readObject();
        Doctor doctor=datebase.findDoctorByIdUser(id_doctor);
        outputStream.writeObject(doctor.getRating());
    }
    private void createReview(ObjectInputStream in,User user) throws IOException, ClassNotFoundException, SQLException {
        Review review=(Review)in.readObject();
        Datebase datebase=new Datebase();
        Doctor doctor=datebase.findDoctorByIdUser(review.getId_user());
        review.setId_user(user.getId());
        review.setId_doctor(doctor.getID_passport());
        datebase.createReview(review);
        List<Review>list=datebase.findAllReviwDoctorByIdDOctor(doctor.getID_passport());
            double rating=5;
            for (int i=0;i<list.size();i++){
                rating=list.get(i).getRating()+rating;
            }
            rating=rating/list.size();
            doctor.setRating(rating);
            datebase.updateRatingDoctor(doctor);

    }
    private void updatePersonalInfo(ObjectInputStream in,User user) throws IOException, ClassNotFoundException, SQLException {
        User user1=(User) in.readObject();
        Patient patient=(Patient)in.readObject();
        user1.setId(user.getId());
        user.setLogin(user1.getLogin());
        user.setPassword(user1.getPassword());
        patient.setId_user(user.getId());
        Datebase datebase=new Datebase();
        datebase.updatePatientAccount(user1,patient);
    }
    private void initializedDoctor(ObjectOutputStream outputStream,User user) throws SQLException, ClassNotFoundException, IOException {
        Datebase datebase=new Datebase();
        outputStream.writeObject(user);
        outputStream.writeObject(datebase.findPatientByIdUser(user.getId()));
        List<Doctor> listDoctor=datebase.full_doctor();
        outputStream.writeObject(listDoctor);
    }
    private void initializedComplaint(ObjectOutputStream out) throws SQLException, ClassNotFoundException, IOException {
        Datebase datebase=new Datebase();
        out.writeObject(datebase.full_complaint());
    }
    private  void addComplaint(ObjectInputStream in, User user) throws IOException, ClassNotFoundException, SQLException {
        Datebase datebase=new Datebase();
        Patient patientMenu=datebase.findPatientByIdUser(user.getId());
        ComplainBook complainBook=new ComplainBook();
        complainBook.setComplain((String) in.readObject());
        complainBook.setDate((String) in.readObject());
        complainBook.setId_user(user.getId());
        complainBook.setFIO(patientMenu.getName()+" "+patientMenu.getSur_name()+" "+patientMenu.getPatronymic());
        datebase.createComplain(complainBook);
    }
}
