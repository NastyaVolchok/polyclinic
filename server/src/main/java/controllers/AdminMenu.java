package controllers;

import datebase.Datebase;
import message.Messages;
import message.UserStaus;
import models.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminMenu {
    public void Start(ObjectInputStream in, ObjectOutputStream out, User user) throws IOException, ClassNotFoundException, SQLException {
        update(out,in,user);
        while (true){
            Messages messages=(Messages) in.readObject();
            switch (messages){
                case CREATE_REPORT:createReport(out, user);break;
                case DELETED_DOTCTOR:deletedDoctor(in);break;
                case UPDATE_STATISTIC:initializedStatistic(out);break;
                case UPDATE_BLACK_LIST:initializedBlackListUser(out);break;
                case UPDATE_COMPLAIN:initializedComplaint(out);break;
                case UPDATE_DOCTOR:initializedAccount(out,user);break;
                case UNBLOCK_ACCOUNT_TABLE:unblockAccountDoctorTable(in);break;
                case UNBLOCK_ACCOUNT:unblockAccountDoctor(in);break;
                case BLOCK_ACCOUNT:blockAccountDoctor(in,user);break;
                case EXIT:StartMenu startMenu=new StartMenu();startMenu.Start(in,out);break;
                case REGISTRATION_DOCTOR:registrationDoctor(in);break;
                case REGISTRATION_ADMIN:registrationAdmin(in);break;
                case PERSONAL_DOCTOR_PAGE:personalDoctorPage(in,out);break;
            }
        }
    }
    private void createReport(ObjectOutputStream out,User user) throws SQLException, ClassNotFoundException, IOException {
        Datebase datebasea=new Datebase();
        List<Doctor> doctorList=datebasea.full_doctor();
        double rating = 0;
        int terapevt=0,hiryrg=0,stomotolog=0,gastro=0,travmtolog=0,nevrolog=0,yrolog=0;
        for (int i=0;i<doctorList.size();i++){
            if(doctorList.get(i).getSpeciality().equals("Терапевт"))terapevt++;
            if(doctorList.get(i).getSpeciality().equals("Хирург"))hiryrg++;
            if(doctorList.get(i).getSpeciality().equals("Стомотолог"))stomotolog++;
            if(doctorList.get(i).getSpeciality().equals("Гастроэнтеролог"))gastro++;
            if(doctorList.get(i).getSpeciality().equals("Травматолог"))travmtolog++;
            if(doctorList.get(i).getSpeciality().equals("Невролог"))nevrolog++;
            if(doctorList.get(i).getSpeciality().equals("Уролог"))yrolog++;
            rating=rating+doctorList.get(i).getRating();
        }
        rating=rating/doctorList.size();
        String report="Отчет по поликлинике\n";
        report=report+"Раздел 'Аккаунты'\n";
        report=report+"Администраци : "+datebasea.full_admin().size()+" Врачи : "+datebasea.full_doctor().size()+" Пациенты = "+datebasea.full_patient().size()+"\n";
        report=report+"Черный список : "+datebasea.full_blackList().size()+"\n";
        report=report+"\n";
        report=report+"Раздел 'Отзывы и Жалобная книга' \n";
        report=report+"Кол-во жалоб : "+datebasea.full_complaint().size()+" Средняя оценка "+rating+"\n";
        report=report+"Врачи поликлиники *** \n";
        report=report+"Невролог : "+nevrolog+"\n";
        report=report+"Уролог : "+yrolog+"\n";
        report=report+"Травматолог : "+travmtolog+"\n";
        report=report+"Гастроэнтеролог : "+gastro+"\n";
        report=report+"Стомотолог : "+stomotolog+"\n";
        report=report+"Хирург : "+hiryrg+"\n";
        report=report+"Терапевт : "+terapevt+"\n";
        List<Records>List=datebasea.full_Records();
        int mon=0,tue=0,wen=0,th=0,fri=0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        for (int i=0;i<List.size();i++){
            LocalDate localDate=LocalDate.parse(List.get(i).getDateRecords(),formatter);
            if(localDate.getDayOfWeek()== DayOfWeek.MONDAY)mon++;
            if(localDate.getDayOfWeek()== DayOfWeek.TUESDAY)tue++;
            if(localDate.getDayOfWeek()== DayOfWeek.WEDNESDAY)wen++;
            if(localDate.getDayOfWeek()== DayOfWeek.THURSDAY)th++;
            if(localDate.getDayOfWeek()== DayOfWeek.FRIDAY)fri++;
        }
        List<Integer>mas=new ArrayList<Integer>();
        mas.add(mon);
        mas.add(tue);
        mas.add(wen);
        mas.add(th);
        mas.add(fri);
        int max=0;
        String day="";
        for(int i=0;i<mas.size();i++){
            if(max<mas.get(i)){
                max=mas.get(i);
                if(i==0)day="Понедельник";
                if(i==1)day="Вторник";
                if(i==2)day="Среда";
                if(i==3)day="Четверг";
                if(i==4)day="Пятница";
            }
        }
        Date date = new Date();
        SimpleDateFormat simpl = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String dat = simpl.format(date);
        report=report+"Самый загрежнный день на данный момент : "+day+"Кол-во талонов в день : "+max+"\n";
        report=report+"Дата создания отчет : "+dat+"\n";
        Admin admin=datebasea.findAdminByIdUser(user.getId());
        report=report+"Собран : "+admin.getName()+" "+admin.getSur_name()+" "+admin.getPatronymic();
        out.writeObject(report);
    }
    private void deletedDoctor(ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        String id_doctor=(String) in.readObject();
        Datebase datebase=new Datebase();
        datebase.removeDoctor(id_doctor);
    }
    private void unblockAccountDoctorTable(ObjectInputStream in) throws SQLException, ClassNotFoundException, IOException {
        Datebase datebase=new Datebase();
        Doctor doctor=datebase.findDoctorByIdUser((int) in.readObject());
        User user=datebase.findUserByID(doctor.getId_user());
        user.setStatus(UserStaus.active);
        datebase.updateStatusAccount(user,UserStaus.active);
        BlackList blackList=new BlackList();
        blackList.setId_user(user.getId());
        datebase.removeAccountOnBlackList(blackList);
    }
    private void unblockAccountDoctor(ObjectInputStream in) throws SQLException, ClassNotFoundException, IOException {
        Datebase datebase=new Datebase();
        Doctor doctor=datebase.findDoctorByIDPassport((String) in.readObject());
        User user=datebase.findUserByID(doctor.getId_user());
        user.setStatus(UserStaus.active);
        datebase.updateStatusAccount(user,UserStaus.active);
        BlackList blackList=new BlackList();
        blackList.setId_user(user.getId());
        datebase.removeAccountOnBlackList(blackList);
    }
    private void blockAccountDoctor(ObjectInputStream in,User administator) throws IOException, ClassNotFoundException, SQLException {
        Datebase datebase=new Datebase();
        Admin admin=datebase.findAdminByIdUser(administator.getId());
        Doctor doctor=datebase.findDoctorByIDPassport((String) in.readObject());
        BlackList blackList=(BlackList)in.readObject();
        User user=datebase.findUserByID(doctor.getId_user());
        user.setStatus(UserStaus.ban);
        blackList.setId_user(user.getId());
        blackList.setReason("Заблокирован администратором : "+admin.getName()+" "+admin.getSur_name()+" "+admin.getPatronymic());
        datebase.updateStatusAccount(user,UserStaus.ban);
        datebase.addAccountOnBlackList(blackList);
    }
    private void update(ObjectOutputStream outputStream, ObjectInputStream inputStream ,User user) throws SQLException, IOException, ClassNotFoundException {
        initializedBlackListUser(outputStream);
        initializedAccount(outputStream,user);
        initializedStatistic(outputStream);
        initializedComplaint(outputStream);
    }
    private void personalDoctorPage(ObjectInputStream in ,ObjectOutputStream out) throws IOException, ClassNotFoundException, SQLException {
        Datebase datebase=new Datebase();
        Doctor doctor=datebase.findDoctorByIDPassport((String) in.readObject());
        User user=datebase.findUserByID(doctor.getId_user());
        out.writeObject(doctor);
        out.writeObject(user);
    }
    private void initializedComplaint(ObjectOutputStream out) throws SQLException, ClassNotFoundException, IOException {
        Datebase datebase=new Datebase();
        out.writeObject(datebase.full_complaint());
    }
    private void initializedStatistic(ObjectOutputStream out) throws SQLException, ClassNotFoundException, IOException {
        Datebase datebase=new Datebase();
        List<Doctor> doctorList=datebase.full_doctor();
        List<Patient> patientList=datebase.full_patient();
        List<Admin>listAdmin=datebase.full_admin();
        out.writeObject(listAdmin.size());
        out.writeObject(doctorList.size());
        out.writeObject(patientList.size());
        double rating = 0;
        int terapevt=0,hiryrg=0,stomotolog=0,gastro=0,travmtolog=0,nevrolog=0,yrolog=0;
        for (int i=0;i<doctorList.size();i++){
            if(doctorList.get(i).getSpeciality().equals("Терапевт"))terapevt++;
            if(doctorList.get(i).getSpeciality().equals("Хирург"))hiryrg++;
            if(doctorList.get(i).getSpeciality().equals("Стомотолог"))stomotolog++;
            if(doctorList.get(i).getSpeciality().equals("Гастроэнтеролог"))gastro++;
            if(doctorList.get(i).getSpeciality().equals("Травматолог"))travmtolog++;
            if(doctorList.get(i).getSpeciality().equals("Невролог"))nevrolog++;
            if(doctorList.get(i).getSpeciality().equals("Уролог"))yrolog++;
            rating=rating+doctorList.get(i).getRating();
        }
        rating=rating/doctorList.size();
        out.writeObject(Double.toString(rating).substring(0,Double.toString(rating).indexOf('.')+2));
        out.writeObject(terapevt);
        out.writeObject(hiryrg);
        out.writeObject(yrolog);
        out.writeObject(nevrolog);
        out.writeObject(travmtolog);
        out.writeObject(gastro);
        out.writeObject(stomotolog);

        List<Records>List=datebase.full_Records();
        int mon=0,tue=0,wen=0,th=0,fri=0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        for (int i=0;i<List.size();i++){
            LocalDate localDate=LocalDate.parse(List.get(i).getDateRecords(),formatter);
            if(localDate.getDayOfWeek()== DayOfWeek.MONDAY)mon++;
            if(localDate.getDayOfWeek()== DayOfWeek.TUESDAY)tue++;
            if(localDate.getDayOfWeek()== DayOfWeek.WEDNESDAY)wen++;
            if(localDate.getDayOfWeek()== DayOfWeek.THURSDAY)th++;
            if(localDate.getDayOfWeek()== DayOfWeek.FRIDAY)fri++;
        }
        out.writeObject(mon);
        out.writeObject(tue);
        out.writeObject(wen);
        out.writeObject(th);
        out.writeObject(fri);
    }
    private void initializedBlackListUser(ObjectOutputStream outputStream) throws SQLException, ClassNotFoundException, IOException {
        Datebase datebase=new Datebase();
        List<BlackList> blackLists=datebase.full_blackList();
        outputStream.writeObject(blackLists);
    }
    private void initializedAccount(ObjectOutputStream out,User user) throws SQLException, ClassNotFoundException, IOException {
        Datebase datebase=new Datebase();
        Admin admin=datebase.findAdminByIdUser(user.getId());
        List<Doctor> listDoctor=datebase.full_doctor();
        List<Patient> listPatient=datebase.full_patient();
        out.writeObject(user);
        out.writeObject(admin);
        out.writeObject(listDoctor);
        out.writeObject(listPatient);
    }
    private void registrationDoctor(ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        User user=(User) in.readObject();
        Doctor doctor=(Doctor) in.readObject();
        Work_day work_day=new Work_day();
        work_day.setId_passport(doctor.getID_passport());
        user.setPosition(UserStaus.doctor);
        Datebase datebase=new Datebase();
        datebase.registrationDoctorAccount(user,doctor,work_day);
    }
    private void registrationAdmin(ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        User user=(User) in.readObject();
        Admin admin=(Admin) in.readObject();
        user.setPosition(UserStaus.admin);
        Datebase datebase=new Datebase();
        datebase.registrationAdminAccount(user,admin);
    }
    private void updateStatistic(ObjectOutputStream outputStream){

    }
}
