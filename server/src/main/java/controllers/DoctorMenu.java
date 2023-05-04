package controllers;

import datebase.Datebase;
import message.Messages;
import message.RecordsStatus;
import models.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DoctorMenu {
    public void Start(ObjectInputStream in, ObjectOutputStream out ,User user) throws IOException, ClassNotFoundException, SQLException {
        initializedRecords(in,out, user);
        initializedSortDoctor(out);
        initializedReview(out,user,in);
        initializedDoctor(out);
        initilizedPersonalAccount(out,user);
        while (true){
            Messages messages=(Messages) in.readObject();
            switch (messages){
                case СANCEL_PATIENT:cancelPatient(in, user, out);break;
                case ACCEPT_PATIENT:acceptPatient(in, user,out);break;
                case RECORDS_ALL:initializedRecords(in,out,user);break;
                case RECORDS_TODAY:recordsToday(out, user,in);break;
                case UPDATE_PESONAL_INFO:updatePersonalInfo(in,user);break;
                case EXIT:StartMenu startMenu=new StartMenu();startMenu.Start(in,out);break;
            }
        }
    }

    private void cancelPatient(ObjectInputStream in ,User user,ObjectOutputStream out) throws SQLException, IOException, ClassNotFoundException {
        int id_records=(int)in.readObject();
        Datebase datebase=new Datebase();
        Doctor doctor=datebase.findDoctorByIdUser(user.getId());
        Records records=datebase.findRecordsByid(id_records);
        History history=new History();
        history.setStatus(RecordsStatus.cancel);
        history.setId_doctor(records.getIdDoctor());
        history.setId_user(records.getIdUser());
        history.setSpeciality(doctor.getSpeciality());
        history.setFIOdoctor(doctor.getName()+" "+doctor.getSur_name()+" "+doctor.getPatronymic());
        history.setDate(records.getDateRecords());
        datebase.createHistory(history);
        datebase.removeRecords(records);
        initializedRecords(in,out,user);
    }
    private void acceptPatient(ObjectInputStream in ,User user,ObjectOutputStream out) throws IOException, ClassNotFoundException, SQLException {
            int id_records=(int)in.readObject();
            Datebase datebase=new Datebase();
            Doctor doctor=datebase.findDoctorByIdUser(user.getId());
            Records records=datebase.findRecordsByid(id_records);
            History history=new History();
            history.setStatus(RecordsStatus.serviced);
            history.setId_doctor(records.getIdDoctor());
            history.setId_user(records.getIdUser());
            history.setSpeciality(doctor.getSpeciality());
            history.setFIOdoctor(doctor.getName()+" "+doctor.getSur_name()+" "+doctor.getPatronymic());
            history.setDate(records.getDateRecords());
            datebase.createHistory(history);
            datebase.removeRecords(records);
            initializedRecords(in,out,user);

    }
    private void recordsToday(ObjectOutputStream out,User user,ObjectInputStream in) throws SQLException, ClassNotFoundException, IOException {
        Datebase datebase=new Datebase();
        LocalDate localDate=LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Doctor doctor=datebase.findDoctorByIdUser(user.getId());
        List<Records>list=datebase.full_Records(doctor.getID_passport());
        for(int i=list.size()-1;i>=0;i--){
            LocalDate localDateRecord=LocalDate.parse(list.get(i).getDateRecords().substring(0,list.get(i).getDateRecords().indexOf(' '))
,formatter);
            if(localDate.isBefore(localDateRecord)||localDate.isAfter(localDateRecord)){
                list.remove(i);
            }
        }
        out.writeObject(list);
        for (int i=0;i<list.size();i++){
            if((Messages)in.readObject()==Messages.FIND_PATIENT_RECORDS){
                findpatientRecords(in,out);
            }
        }
    }
    private void findpatientRecords(ObjectInputStream in,ObjectOutputStream out) throws IOException, ClassNotFoundException, SQLException {
        int id=(int) in.readObject();
        Datebase datebase=new Datebase();
        Patient patient=datebase.findPatientByIdUser(id);
        out.writeObject(patient);
    }
    private void initializedRecords(ObjectInputStream in,ObjectOutputStream out,User user) throws SQLException, ClassNotFoundException, IOException {
        Datebase datebase=new Datebase();
        Doctor doctor=datebase.findDoctorByIdUser(user.getId());
        List<Records>ListRecrods=datebase.full_Records(doctor.getID_passport());
        out.writeObject(ListRecrods);
        for (int i=0;i<ListRecrods.size();i++){
            if((Messages)in.readObject()==Messages.FIND_PATIENT_RECORDS){
                findpatientRecords(in,out);
            }
        }
    }
    private void findPatient(ObjectOutputStream outputStream,ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        Datebase datebase=new Datebase();
        Patient patient=datebase.findPatientByIdUser((int)in.readObject());
        outputStream.writeObject(patient);
    }
    private void initializedReview(ObjectOutputStream outputStream, User user,ObjectInputStream in) throws SQLException, ClassNotFoundException, IOException {
        Datebase datebase=new Datebase();
        Doctor doctor=datebase.findDoctorByIdUser(user.getId());
        List<Review>list=datebase.findAllReviwDoctorByIdDOctor(doctor.getID_passport());
        outputStream.writeObject(list);
        for (int i=0;i<list.size();i++){
            if((Messages)in.readObject()==Messages.FIND_PATIEN){
                findPatient(outputStream,in);
            }
        }
    }
    private void initializedSortDoctor(ObjectOutputStream outputStream) throws SQLException, ClassNotFoundException, IOException {
        Datebase datebase=new Datebase();
        List<Doctor>list=datebase.sortDoctorRating();
        outputStream.writeObject(list);
    }
    private void initializedDoctor(ObjectOutputStream outputStream ) throws IOException, SQLException, ClassNotFoundException {
        Datebase datebase=new Datebase();
        List<Doctor> listDoctor=datebase.full_doctor();
        outputStream.writeObject(listDoctor);
    }
    private void updatePersonalInfo(ObjectInputStream in ,User user) throws IOException, ClassNotFoundException, SQLException {
        Datebase datebase=new Datebase();
        User user1=(User) in.readObject();
        Work_day work_day=(Work_day)in.readObject();
        Doctor doctor=(Doctor) in.readObject();
        doctor.setId_user(user.getId());
        work_day.setId_passport(doctor.getID_passport());
        user1.setId(user.getId());
        user.setPassword(user1.getPassword());
        user.setLogin(user1.getLogin());
        datebase.updateDoctorAccount(user,doctor,work_day);
    }
    private void initilizedPersonalAccount(ObjectOutputStream out, User user) throws SQLException, ClassNotFoundException, IOException {
        Datebase datebase=new Datebase();
        Doctor doctor=datebase.findDoctorByIdUser(user.getId());
        Work_day work_day=datebase.findWorkDayByIdPAssportDoctor(doctor.getID_passport());
        out.writeObject(user);
        out.writeObject(doctor);
        out.writeObject(work_day);
    }
}
