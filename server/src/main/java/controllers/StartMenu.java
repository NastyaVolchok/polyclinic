package controllers;

import datebase.Datebase;
import message.Messages;
import message.UserStaus;
import models.Patient;
import models.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StartMenu {
    public void Start(ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException, SQLException {
        while (true){
            Messages messages=(Messages) in.readObject();
            switch (messages){
                case AUTORIZATION:Autorization(in,out);break;
                case REGISTRATION_USER:Registration(in,out);break;
            }
        }
    }
    public void Autorization(ObjectInputStream in,ObjectOutputStream out) throws IOException, ClassNotFoundException, SQLException {
        Datebase datebase=new Datebase();
        User user=new User();
        user.setLogin((String) in.readObject());
        user.setPassword((String) in.readObject());
        ResultSet set=datebase.autorization_request(user);
        user=datebase.SerialazebleUser(set);
        if(user.getPosition()==null){
            out.writeObject(Messages.AUTORIZATION_NOT_FOUND_ACCOUNT);
        }
        else {
            out.writeObject(Messages.AUTORIZATION_SEC_FOUND);
            if(user.getStatus().equals(UserStaus.ban)){
                out.writeObject(Messages.AUTORIZATION_BLOCK_ACCOUNT);
            }
            else {
                if(user.getPosition().equals(UserStaus.admin)){
                    out.writeObject(Messages.AUTORIZATION_ADMIN);
                    AdminMenu adminMenu=new AdminMenu();
                    adminMenu.Start(in,out,user);
                }
                if(user.getPosition().equals(UserStaus.patient)){
                    out.writeObject(Messages.AUTORIZATION_PATIENT);
                    PatientMenu patientMenu=new PatientMenu();
                    patientMenu.Start(in,out,user);
                }
                if(user.getPosition().equals(UserStaus.doctor)){
                    out.writeObject(Messages.AUTORIZATION_DOCTOR);
                    DoctorMenu doctorMenu=new DoctorMenu();
                    doctorMenu.Start(in,out,user);
                }
            }
        }
    }
    public void Registration(ObjectInputStream in ,ObjectOutputStream out) throws IOException, ClassNotFoundException, SQLException {
        Datebase datebase=new Datebase();
        User user=(User) in.readObject();
        Patient patient=(Patient) in.readObject();
        user.setPosition(UserStaus.patient);
        datebase.registrationPatientAccount(user,patient);
    }
}
