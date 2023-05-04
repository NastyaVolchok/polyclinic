package datebase;

import message.UserStaus;
import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datebase extends  Config {
    private static Connection getDbconnection()throws ClassNotFoundException, SQLException {
        Connection dbconnection;
        String ConectionString ="jdbc:mysql://"+dbHost+":"+
                dbport+"/"+dbName+"?serverTimezone=Europe/Moscow";
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println(ConectionString);
        dbconnection= DriverManager.getConnection(ConectionString, dbUsers,dbPass);
        return dbconnection;
    }
    public User SerialazebleUser(ResultSet res) throws SQLException {
        User user=new User();
        while (res.next()){
            user.setId(res.getInt(SqlInfo.USER_ID));
            user.setStatus(res.getString(SqlInfo.USER_STATUS));
            user.setPosition(res.getString(SqlInfo.USER_POSITION));
            user.setLogin(res.getString(SqlInfo.USER_LOGIN));
            user.setPassword(res.getString(SqlInfo.USER_PASSWORD));
        }
        return user;
    }
    public void registrationDoctorAccount(User user, Doctor doctor,Work_day work_day) throws SQLException, ClassNotFoundException {
        String insert_user="INSERT INTO hospital."+SqlInfo.USER_TABLE+"("+SqlInfo.USER_LOGIN+","+SqlInfo.USER_PASSWORD+","
                +SqlInfo.USER_POSITION+","+SqlInfo.USER_STATUS+")"+"VALUES(?,?,?,?)";

        String insert_personal_info="INSERT INTO hospital."+SqlInfo.DOCTOR_TABLE+"("+SqlInfo.DOCTOR_NAME+","
                +SqlInfo.DOCTOR_SURNAME+","+SqlInfo.DOCTOR_PATRONYMIC+","+SqlInfo.DOCTOR_PHONE+","
                +SqlInfo.DOCTOR_ID_PASSPORT+","+SqlInfo.DOCTOR_SERIA_NUMBER+","+SqlInfo.DOCTOR_ID_USER+","+SqlInfo.DOCTOR_DATE_REGISTRATION
                +","+SqlInfo.DOCTOR_EDUCATION+","+SqlInfo.DOCTOR_EXPIRIENCE+","+SqlInfo.DOCTOR_SPECIALITY+","+SqlInfo.DOCTOR_RATING+","+SqlInfo.DOCTOR_UNIVERSITY
                +")"+"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String insertDay="INSERT INTO hospital."+SqlInfo.WORDK_DAY_TABLE+"("+SqlInfo.WORK_DAY_IDPASSPORT+","+SqlInfo.WORK_DAY_MON+","
                +SqlInfo.WORK_DAY_TUE+","+SqlInfo.WORK_DAY_WEN+","+SqlInfo.WORK_DAY_THU+","+SqlInfo.WORK_DAY_FRI+")"+"VALUES(?,?,?,?,?,?)";
        PreparedStatement statement_user=getDbconnection().prepareStatement(insert_user,PreparedStatement.RETURN_GENERATED_KEYS);
        statement_user.setString(1,user.getLogin());
        statement_user.setString(2,user.getPassword());
        statement_user.setString(3,user.getPosition());
        statement_user.setString(4,user.getStatus());
        statement_user.executeUpdate();
        ResultSet res=statement_user.getGeneratedKeys();
        res.next();
        doctor.setId_user(res.getInt(1));
        PreparedStatement statement_personal_info=getDbconnection().prepareStatement(insert_personal_info);
        statement_personal_info.setString(1,doctor.getName());
        statement_personal_info.setString(2,doctor.getSur_name());
        statement_personal_info.setString(3,doctor.getPatronymic());
        statement_personal_info.setString(4,doctor.getPhone());
        statement_personal_info.setString(5,doctor.getID_passport());
        statement_personal_info.setString(6,doctor.getSeria_number());
        statement_personal_info.setInt(7,doctor.getId_user());
        statement_personal_info.setString(8,doctor.getDate_registration());
        statement_personal_info.setString(9,doctor.getEducation());
        statement_personal_info.setString(10,doctor.getExpiriense());
        statement_personal_info.setString(11,doctor.getSpeciality());
        statement_personal_info.setDouble(12,doctor.getRating());
        statement_personal_info.setString(13,doctor.getUniversity());
        statement_personal_info.executeUpdate();
        PreparedStatement statementDay=getDbconnection().prepareStatement(insertDay);
        statementDay.setString(1,doctor.getID_passport());
        statementDay.setBoolean(2,work_day.getMonday());
        statementDay.setBoolean(3,work_day.getTuesday());
        statementDay.setBoolean(4,work_day.getWednesday());
        statementDay.setBoolean(5,work_day.getThursday());
        statementDay.setBoolean(6,work_day.getFriday());
        statementDay.executeUpdate();
    }
    public void registrationAdminAccount(User user , Admin admin) throws SQLException, ClassNotFoundException {
        String insert_user="INSERT INTO hospital."+SqlInfo.USER_TABLE+"("+SqlInfo.USER_LOGIN+","+SqlInfo.USER_PASSWORD+","
                +SqlInfo.USER_POSITION+","+SqlInfo.USER_STATUS+")"+"VALUES(?,?,?,?)";

        String insert_personal_info="INSERT INTO hospital."+SqlInfo.ADMIN_TABLE+"("+SqlInfo.ADMIN_NAME+","
                +SqlInfo.ADMIN_SURNAME+","+SqlInfo.ADMIN_PATRONYMIC+","+SqlInfo.ADMIN_PHONE+","
                +SqlInfo.ADMIN_ID_PASSPORT+","+SqlInfo.ADMIN_SERIA_NUMBER+","+SqlInfo.ADMIN_ID_USER+","+SqlInfo.ADMIN_DATE_REGISTRATION
                +")"+"VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement statement_user=getDbconnection().prepareStatement(insert_user,PreparedStatement.RETURN_GENERATED_KEYS);
        statement_user.setString(1,user.getLogin());
        statement_user.setString(2,user.getPassword());
        statement_user.setString(3,user.getPosition());
        statement_user.setString(4,user.getStatus());
        statement_user.executeUpdate();
        ResultSet res=statement_user.getGeneratedKeys();
        res.next();
        admin.setId_user(res.getInt(1));
        PreparedStatement statement_personal_info=getDbconnection().prepareStatement(insert_personal_info);
        statement_personal_info.setString(1,admin.getName());
        statement_personal_info.setString(2,admin.getSur_name());
        statement_personal_info.setString(3,admin.getPatronymic());
        statement_personal_info.setString(4,admin.getPhone());
        statement_personal_info.setString(5,admin.getID_passport());
        statement_personal_info.setString(6,admin.getSeria_number());
        statement_personal_info.setInt(7,admin.getId_user());
        statement_personal_info.setString(8,admin.getDate_registration());
        statement_personal_info.executeUpdate();
    }
    public void registrationPatientAccount(User user, Patient patient) throws SQLException, ClassNotFoundException {
        String insert_user="INSERT INTO hospital."+SqlInfo.USER_TABLE+"("+SqlInfo.USER_LOGIN+","+SqlInfo.USER_PASSWORD+","
                +SqlInfo.USER_POSITION+","+SqlInfo.USER_STATUS+")"+"VALUES(?,?,?,?)";

        String insert_personal_info="INSERT INTO hospital."+SqlInfo.PATIEN_TABLE+"("+SqlInfo.PATIEN_NAME+","
                +SqlInfo.PATIEN_SURNAME+","+SqlInfo.PATIEN_PATRONYMIC+","+SqlInfo.PATIEN_PHONE+","
                +SqlInfo.PATIEN_ID_PASSPORT+","+SqlInfo.PATIEN_SERIA_NUMBER+","+SqlInfo.PATIEN_ID_USER+","+SqlInfo.PATIEN_DATE_REGISTRATION
                +","+SqlInfo.PATIEN_ID_SEX+","+SqlInfo.PATIEN_ID_BIRTH
                +")"+"VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement_user=getDbconnection().prepareStatement(insert_user,PreparedStatement.RETURN_GENERATED_KEYS);
        statement_user.setString(1,user.getLogin());
        statement_user.setString(2,user.getPassword());
        statement_user.setString(3,user.getPosition());
        statement_user.setString(4,user.getStatus());
        statement_user.executeUpdate();
        ResultSet res=statement_user.getGeneratedKeys();
        res.next();
        patient.setId_user(res.getInt(1));
        PreparedStatement statement_personal_info=getDbconnection().prepareStatement(insert_personal_info);
        statement_personal_info.setString(1,patient.getName());
        statement_personal_info.setString(2,patient.getSur_name());
        statement_personal_info.setString(3,patient.getPatronymic());
        statement_personal_info.setString(4,patient.getPhone());
        statement_personal_info.setString(5,patient.getID_passport());
        statement_personal_info.setString(6,patient.getSeria_number());
        statement_personal_info.setInt(7,patient.getId_user());
        statement_personal_info.setString(8,patient.getDate_registration());
        statement_personal_info.setString(9,patient.getSex());
        statement_personal_info.setString(10,patient.getDate_birth());
        statement_personal_info.executeUpdate();
    }
    public void createComplain(ComplainBook complainBook) throws SQLException, ClassNotFoundException {
        String insert_user="INSERT INTO hospital."+SqlInfo.COMPLAIN_TABLE+"("+SqlInfo.COMPLAIN_ID_USER+","+SqlInfo.COMPLAIN_COMPLAIN+","
                +SqlInfo.COMPLAIN_DATE+","+SqlInfo.COMPLAIN_FIO+")"+"VALUES(?,?,?,?)";
        PreparedStatement statement_user=getDbconnection().prepareStatement(insert_user,PreparedStatement.RETURN_GENERATED_KEYS);
        statement_user.setInt(1,complainBook.getId_user());
        statement_user.setString(2,complainBook.getComplain());
        statement_user.setString(3,complainBook.getDate());
        statement_user.setString(4,complainBook.getFIO());
        statement_user.executeUpdate();
    }
    public void createReview(Review review) throws SQLException, ClassNotFoundException {
        String insert_user="INSERT INTO hospital."+SqlInfo.REVIEW_TABLE+"("+SqlInfo.REVIEW_ID_USER+","+SqlInfo.REVIEW_ID_DOCTOR+","
                +SqlInfo.REVIEW_REVIEW+","+SqlInfo.REVIEW_DATE+","+SqlInfo.REVIEW_RATING+")"+"VALUES(?,?,?,?,?)";
        PreparedStatement statement_user=getDbconnection().prepareStatement(insert_user,PreparedStatement.RETURN_GENERATED_KEYS);
        statement_user.setInt(1,review.getId_user());
        statement_user.setString(2,review.getId_doctor());
        statement_user.setString(3,review.getReview());
        statement_user.setString(4,review.getDate());
        statement_user.setInt(5,review.getRating());
        statement_user.executeUpdate();
    }
    public void createRecords(Records records) throws SQLException, ClassNotFoundException {
        String insert_user="INSERT INTO hospital.ticket( idUser, idDoctor, status, date, dateRecords) VALUES (?,?,?,?,?)";
        PreparedStatement statement_user=getDbconnection().prepareStatement(insert_user,PreparedStatement.RETURN_GENERATED_KEYS);
        statement_user.setInt(1,records.getIdUser());
        statement_user.setString(2,records.getIdDoctor());
        statement_user.setString(3,records.getStatus());
        statement_user.setString(4,records.getDate());
        statement_user.setString(5,records.getDateRecords());
        statement_user.executeUpdate();
    }
    public void createHistory(History history) throws SQLException, ClassNotFoundException {
        String insert_user="INSERT INTO hospital.history( idUser, idDoctor, status, dateRecords, FIOdoctor, speciality) VALUES (?,?,?,?,?,?)";
        PreparedStatement statement_user=getDbconnection().prepareStatement(insert_user,PreparedStatement.RETURN_GENERATED_KEYS);
        statement_user.setInt(1,history.getId_user());
        statement_user.setString(2,history.getId_doctor());
        statement_user.setString(3,history.getStatus());
        statement_user.setString(4,history.getDate());
        statement_user.setString(5,history.getFIOdoctor());
        statement_user.setString(6,history.getSpeciality());
        statement_user.executeUpdate();
    }
    public List<Doctor> full_doctor() throws SQLException, ClassNotFoundException {
        String find_user="SELECT * FROM hospital."+SqlInfo.DOCTOR_TABLE;
        PreparedStatement preparedStatement=getDbconnection().prepareStatement(find_user);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Doctor>list=new ArrayList<>();
        while (resultSet.next()){
            Doctor doctor=new Doctor();
            doctor.setId_user((resultSet.getInt(SqlInfo.DOCTOR_ID_USER)));
            doctor.setID_passport(resultSet.getString(SqlInfo.DOCTOR_ID_PASSPORT));
            doctor.setSeria_number(resultSet.getString(SqlInfo.DOCTOR_SERIA_NUMBER));
            doctor.setName(resultSet.getString(SqlInfo.DOCTOR_NAME));
            doctor.setSur_name(resultSet.getString(SqlInfo.DOCTOR_SURNAME));
            doctor.setPatronymic(resultSet.getString(SqlInfo.DOCTOR_PATRONYMIC));
            doctor.setPhone(resultSet.getString(SqlInfo.DOCTOR_PHONE));
            doctor.setDate_registration(resultSet.getString(SqlInfo.DOCTOR_DATE_REGISTRATION));
            doctor.setSpeciality(resultSet.getString(SqlInfo.DOCTOR_SPECIALITY));
            doctor.setEducation(resultSet.getString(SqlInfo.DOCTOR_EDUCATION));
            doctor.setExpiriense(resultSet.getString(SqlInfo.DOCTOR_EXPIRIENCE));
            doctor.setRating(resultSet.getDouble(SqlInfo.DOCTOR_RATING));
            doctor.setUniversity(resultSet.getString(SqlInfo.DOCTOR_UNIVERSITY));
            list.add(doctor);
        }
        return list;
    }
    public List<Patient> full_patient() throws SQLException, ClassNotFoundException {
        String find_user="SELECT * FROM hospital."+SqlInfo.PATIEN_TABLE;
        PreparedStatement preparedStatement=getDbconnection().prepareStatement(find_user);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Patient>list=new ArrayList<>();
        while (resultSet.next()){
            Patient patient=new Patient();
            patient.setId_user((resultSet.getInt(SqlInfo.PATIEN_ID_USER)));
            patient.setID_passport(resultSet.getString(SqlInfo.PATIEN_ID_PASSPORT));
            patient.setSeria_number(resultSet.getString(SqlInfo.PATIEN_SERIA_NUMBER));
            patient.setName(resultSet.getString(SqlInfo.PATIEN_NAME));
            patient.setSur_name(resultSet.getString(SqlInfo.PATIEN_SURNAME));
            patient.setPatronymic(resultSet.getString(SqlInfo.PATIEN_PATRONYMIC));
            patient.setPhone(resultSet.getString(SqlInfo.PATIEN_PHONE));
            patient.setSex(resultSet.getString(SqlInfo.PATIEN_ID_SEX));
            patient.setDate_birth(resultSet.getString(SqlInfo.PATIEN_ID_BIRTH));
            patient.setDate_registration(resultSet.getString(SqlInfo.PATIEN_DATE_REGISTRATION));
            list.add(patient);
        }
        return list;
    }
    public List<Admin> full_admin() throws SQLException, ClassNotFoundException {
        String find_user="SELECT * FROM hospital."+SqlInfo.ADMIN_TABLE;
        PreparedStatement preparedStatement=getDbconnection().prepareStatement(find_user);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Admin>list=new ArrayList<>();
        while (resultSet.next()){
            Admin admin=new Admin();
            admin.setId_user((resultSet.getInt(SqlInfo.ADMIN_ID_USER)));
            admin.setID_passport(resultSet.getString(SqlInfo.ADMIN_ID_PASSPORT));
            admin.setSeria_number(resultSet.getString(SqlInfo.ADMIN_SERIA_NUMBER));
            admin.setName(resultSet.getString(SqlInfo.ADMIN_NAME));
            admin.setSur_name(resultSet.getString(SqlInfo.ADMIN_SURNAME));
            admin.setPatronymic(resultSet.getString(SqlInfo.ADMIN_PATRONYMIC));
            admin.setPhone(resultSet.getString(SqlInfo.ADMIN_PHONE));
            admin.setDate_registration(resultSet.getString(SqlInfo.ADMIN_DATE_REGISTRATION));
            list.add(admin);
        }
        return list;
    }
    public List<Work_day> full_workDate() throws SQLException, ClassNotFoundException {
        String find_user="SELECT * FROM hospital."+SqlInfo.WORDK_DAY_TABLE;
        PreparedStatement preparedStatement=getDbconnection().prepareStatement(find_user);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Work_day>list=new ArrayList<>();
        while (resultSet.next()){
           Work_day work_day=new Work_day();
           work_day.setId_passport(resultSet.getString(SqlInfo.WORK_DAY_IDPASSPORT));
           work_day.setMonday(resultSet.getBoolean(SqlInfo.WORK_DAY_MON));
           work_day.setTuesday(resultSet.getBoolean(SqlInfo.WORK_DAY_TUE));
           work_day.setWednesday(resultSet.getBoolean(SqlInfo.WORK_DAY_WEN));
           work_day.setThursday(resultSet.getBoolean(SqlInfo.WORK_DAY_THU));
           work_day.setFriday(resultSet.getBoolean(SqlInfo.WORK_DAY_FRI));
           list.add(work_day);
        }
        return list;
    }
    public List<ComplainBook> full_complaint() throws SQLException, ClassNotFoundException {
        String find_user="SELECT * FROM hospital."+SqlInfo.COMPLAIN_TABLE;
        PreparedStatement preparedStatement=getDbconnection().prepareStatement(find_user);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<ComplainBook>list=new ArrayList<>();
        while (resultSet.next()){
            ComplainBook complainBook=new ComplainBook();
            complainBook.setDate(resultSet.getString(SqlInfo.COMPLAIN_DATE));
            complainBook.setComplain(resultSet.getString(SqlInfo.COMPLAIN_COMPLAIN));
            complainBook.setFIO(resultSet.getString(SqlInfo.COMPLAIN_FIO));
            complainBook.setId(resultSet.getInt(SqlInfo.COMPLAIN_ID));
            complainBook.setId_user(resultSet.getInt(SqlInfo.COMPLAIN_ID_USER));
            list.add(complainBook);
        }
        return list;
    }
    public List<BlackList> full_blackList() throws SQLException, ClassNotFoundException {
        String find_user="SELECT * FROM hospital."+SqlInfo.BAN_TABLE;
        PreparedStatement preparedStatement=getDbconnection().prepareStatement(find_user);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<BlackList>list=new ArrayList<>();
        while (resultSet.next()){
            BlackList blackList=new BlackList();
            blackList.setDate(resultSet.getString(SqlInfo.BAN_DATE_BLOCK));
            blackList.setReason(resultSet.getString(SqlInfo.BAN_REASON));
            blackList.setId_user(resultSet.getInt(SqlInfo.BAN_ID_USER));
            list.add(blackList);
        }
        return list;
    }
    public List<History>full_HistoryById(int id_user) throws SQLException, ClassNotFoundException {
        String find_user="SELECT * FROM hospital.history where hospital.history.idUser = "+id_user;
        PreparedStatement preparedStatement=getDbconnection().prepareStatement(find_user);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<History>list=new ArrayList<>();
        while (resultSet.next()){
            History history=new History();
            history.setId(resultSet.getInt("id"));
            history.setId_doctor(resultSet.getString("idDoctor"));
            history.setId_user(resultSet.getInt("idUser"));
            history.setFIOdoctor(resultSet.getString("FIOdoctor"));
            history.setDate(resultSet.getString("dateRecords"));
            history.setStatus(resultSet.getString("status"));
            history.setSpeciality(resultSet.getString("speciality"));
            list.add(history);
        }
        return list;
    }
    public List<Records>full_Records() throws SQLException, ClassNotFoundException {
        String find_user = "SELECT * FROM hospital.ticket ";
        PreparedStatement preparedStatement = getDbconnection().prepareStatement(find_user);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Records> list = new ArrayList<>();
        while (resultSet.next()) {
            Records records = new Records();
            records.setId(resultSet.getInt("id"));
            records.setDateRecords(resultSet.getString("dateRecords"));
            records.setStatus(resultSet.getString("status"));
            records.setDate(resultSet.getString("date"));
            records.setIdUser(resultSet.getInt("idUser"));
            records.setIdDoctor(resultSet.getString("idDoctor"));
            list.add(records);
        }
        return  list;
    }
    public List<Records>full_Records(String id_doctor) throws SQLException, ClassNotFoundException {
        String find_user = "SELECT * FROM hospital.ticket where hospital.ticket.idDoctor = '" + id_doctor+"'";
        PreparedStatement preparedStatement = getDbconnection().prepareStatement(find_user);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Records> list = new ArrayList<>();
        while (resultSet.next()) {
            Records records = new Records();
            records.setId(resultSet.getInt("id"));
            records.setDateRecords(resultSet.getString("dateRecords"));
            records.setStatus(resultSet.getString("status"));
            records.setDate(resultSet.getString("date"));
            records.setIdUser(resultSet.getInt("idUser"));
            records.setIdDoctor(resultSet.getString("idDoctor"));
            list.add(records);
        }
        return  list;
    }
    public Admin findAdminByIdUser(int id_user) throws SQLException, ClassNotFoundException {
        String find_admin="SELECT * FROM hospital."+SqlInfo.ADMIN_TABLE +" WHERE "+SqlInfo.ADMIN_ID_USER+"=?";
        PreparedStatement preparedStatement=getDbconnection().prepareStatement(find_admin);
        preparedStatement.setInt(1,id_user);
        ResultSet resultSet = preparedStatement.executeQuery();
        Admin admin=new Admin();
        while (resultSet.next()){
            admin.setId_user((resultSet.getInt(SqlInfo.ADMIN_ID_USER)));
            admin.setID_passport(resultSet.getString(SqlInfo.ADMIN_ID_PASSPORT));
            admin.setSeria_number(resultSet.getString(SqlInfo.ADMIN_SERIA_NUMBER));
            admin.setName(resultSet.getString(SqlInfo.ADMIN_NAME));
            admin.setSur_name(resultSet.getString(SqlInfo.ADMIN_SURNAME));
            admin.setPatronymic(resultSet.getString(SqlInfo.ADMIN_PATRONYMIC));
            admin.setPhone(resultSet.getString(SqlInfo.ADMIN_PHONE));
            admin.setDate_registration(resultSet.getString(SqlInfo.ADMIN_DATE_REGISTRATION));
        }
        return admin;
    }
    public Patient findPatientByIdUser(int id_user) throws SQLException, ClassNotFoundException {
        String find_admin="SELECT * FROM hospital."+SqlInfo.PATIEN_TABLE +" WHERE "+SqlInfo.PATIEN_ID_USER+"=?";
        PreparedStatement preparedStatement=getDbconnection().prepareStatement(find_admin);
        preparedStatement.setInt(1,id_user);
        ResultSet resultSet = preparedStatement.executeQuery();
        Patient patient=new Patient();
        while (resultSet.next()){
            patient.setId_user((resultSet.getInt(SqlInfo.PATIEN_ID_USER)));
            patient.setID_passport(resultSet.getString(SqlInfo.PATIEN_ID_PASSPORT));
            patient.setSeria_number(resultSet.getString(SqlInfo.PATIEN_SERIA_NUMBER));
            patient.setName(resultSet.getString(SqlInfo.PATIEN_NAME));
            patient.setSur_name(resultSet.getString(SqlInfo.PATIEN_SURNAME));
            patient.setPatronymic(resultSet.getString(SqlInfo.PATIEN_PATRONYMIC));
            patient.setPhone(resultSet.getString(SqlInfo.PATIEN_PHONE));
            patient.setSex(resultSet.getString(SqlInfo.PATIEN_ID_SEX));
            patient.setDate_birth(resultSet.getString(SqlInfo.PATIEN_ID_BIRTH));
            patient.setDate_registration(resultSet.getString(SqlInfo.PATIEN_DATE_REGISTRATION));
        }
        return patient;
    }
    public Records findRecordsByid(int id) throws SQLException, ClassNotFoundException {
        String find_admin="SELECT * FROM hospital.ticket where ticket.id = "+id;
        PreparedStatement preparedStatement=getDbconnection().prepareStatement(find_admin);
        ResultSet resultSet = preparedStatement.executeQuery();
        Records records = new Records();
        while (resultSet.next()) {
            records.setId(resultSet.getInt("id"));
            records.setDateRecords(resultSet.getString("dateRecords"));
            records.setStatus(resultSet.getString("status"));
            records.setDate(resultSet.getString("date"));
            records.setIdUser(resultSet.getInt("idUser"));
            records.setIdDoctor(resultSet.getString("idDoctor"));
        }
        return  records;
    }
    public Doctor findDoctorByIdUser(int id_user) throws SQLException, ClassNotFoundException {
        String find_admin="SELECT * FROM hospital."+SqlInfo.DOCTOR_TABLE +" WHERE "+SqlInfo.DOCTOR_ID_USER+"=?";
        PreparedStatement preparedStatement=getDbconnection().prepareStatement(find_admin);
        preparedStatement.setInt(1,id_user);
        ResultSet resultSet = preparedStatement.executeQuery();
        Doctor doctor=new Doctor();
        while (resultSet.next()){
            doctor.setId_user((resultSet.getInt(SqlInfo.DOCTOR_ID_USER)));
            doctor.setID_passport(resultSet.getString(SqlInfo.DOCTOR_ID_PASSPORT));
            doctor.setSeria_number(resultSet.getString(SqlInfo.DOCTOR_SERIA_NUMBER));
            doctor.setName(resultSet.getString(SqlInfo.DOCTOR_NAME));
            doctor.setSur_name(resultSet.getString(SqlInfo.DOCTOR_SURNAME));
            doctor.setPatronymic(resultSet.getString(SqlInfo.DOCTOR_PATRONYMIC));
            doctor.setPhone(resultSet.getString(SqlInfo.DOCTOR_PHONE));
            doctor.setDate_registration(resultSet.getString(SqlInfo.DOCTOR_DATE_REGISTRATION));
            doctor.setSpeciality(resultSet.getString(SqlInfo.DOCTOR_SPECIALITY));
            doctor.setEducation(resultSet.getString(SqlInfo.DOCTOR_EDUCATION));
            doctor.setExpiriense(resultSet.getString(SqlInfo.DOCTOR_EXPIRIENCE));
            doctor.setRating(resultSet.getDouble(SqlInfo.DOCTOR_RATING));
            doctor.setUniversity(resultSet.getString(SqlInfo.DOCTOR_UNIVERSITY));
        }
        return doctor;
    }
    public Work_day findWorkDayByIdPAssportDoctor(String idPassport) throws SQLException, ClassNotFoundException {
        String find_admin="SELECT * FROM hospital."+SqlInfo.WORDK_DAY_TABLE +" WHERE "+SqlInfo.WORK_DAY_IDPASSPORT+"=?";
        PreparedStatement preparedStatement=getDbconnection().prepareStatement(find_admin);
        preparedStatement.setString(1,idPassport);
        ResultSet resultSet = preparedStatement.executeQuery();
        Work_day work_day=new Work_day();
        while (resultSet.next()){
            work_day.setId_passport(resultSet.getString(SqlInfo.WORK_DAY_IDPASSPORT));
            work_day.setMonday(resultSet.getBoolean(SqlInfo.WORK_DAY_MON));
            work_day.setTuesday(resultSet.getBoolean(SqlInfo.WORK_DAY_TUE));
            work_day.setWednesday(resultSet.getBoolean(SqlInfo.WORK_DAY_WEN));
            work_day.setThursday(resultSet.getBoolean(SqlInfo.WORK_DAY_THU));
            work_day.setFriday(resultSet.getBoolean(SqlInfo.WORK_DAY_FRI));
        }
        return work_day;
    }
    public List<Review> findAllReviwDoctorByIdDOctor(String id_doctor) throws SQLException, ClassNotFoundException {
        String find_admin="SELECT * FROM hospital."+SqlInfo.REVIEW_TABLE +" WHERE "+SqlInfo.REVIEW_ID_DOCTOR+"=?";
        PreparedStatement preparedStatement=getDbconnection().prepareStatement(find_admin);
        preparedStatement.setString(1,id_doctor);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Review>list=new ArrayList<>();
        while (resultSet.next()){
            Review review=new Review();
            review.setReview(resultSet.getString(SqlInfo.REVIEW_REVIEW));
            review.setDate(resultSet.getString(SqlInfo.REVIEW_DATE));
            review.setId_doctor(resultSet.getString(SqlInfo.REVIEW_ID_DOCTOR));
            review.setId_user(resultSet.getInt(SqlInfo.REVIEW_ID_USER));
            review.setRating(resultSet.getInt(SqlInfo.REVIEW_RATING));
            list.add(review);
        }
        return list;
    }
    public Doctor findDoctorByIDPassport(String id_passport) throws SQLException, ClassNotFoundException {
        String find_admin="SELECT * FROM hospital."+SqlInfo.DOCTOR_TABLE +" WHERE "+SqlInfo.DOCTOR_ID_PASSPORT+"=?";
        PreparedStatement preparedStatement=getDbconnection().prepareStatement(find_admin);
        preparedStatement.setString(1,id_passport);
        ResultSet resultSet = preparedStatement.executeQuery();
        Doctor doctor=new Doctor();
        while (resultSet.next()){
            doctor.setId_user((resultSet.getInt(SqlInfo.DOCTOR_ID_USER)));
            doctor.setID_passport(resultSet.getString(SqlInfo.DOCTOR_ID_PASSPORT));
            doctor.setSeria_number(resultSet.getString(SqlInfo.DOCTOR_SERIA_NUMBER));
            doctor.setName(resultSet.getString(SqlInfo.DOCTOR_NAME));
            doctor.setSur_name(resultSet.getString(SqlInfo.DOCTOR_SURNAME));
            doctor.setPatronymic(resultSet.getString(SqlInfo.DOCTOR_PATRONYMIC));
            doctor.setPhone(resultSet.getString(SqlInfo.DOCTOR_PHONE));
            doctor.setDate_registration(resultSet.getString(SqlInfo.DOCTOR_DATE_REGISTRATION));
            doctor.setSpeciality(resultSet.getString(SqlInfo.DOCTOR_SPECIALITY));
            doctor.setEducation(resultSet.getString(SqlInfo.DOCTOR_EDUCATION));
            doctor.setExpiriense(resultSet.getString(SqlInfo.DOCTOR_EXPIRIENCE));
            doctor.setRating(resultSet.getDouble(SqlInfo.DOCTOR_RATING));
            doctor.setUniversity(resultSet.getString(SqlInfo.DOCTOR_UNIVERSITY));
        }
        return doctor;
    }
    public User findUserByID(int id) throws SQLException, ClassNotFoundException {
        String find_admin="SELECT * FROM hospital."+SqlInfo.USER_TABLE +" WHERE "+SqlInfo.USER_ID+"=?";
        PreparedStatement preparedStatement=getDbconnection().prepareStatement(find_admin);
        preparedStatement.setInt(1,id);
        ResultSet res = preparedStatement.executeQuery();
        User user=new User();
        while (res.next()){
            user.setId(res.getInt(SqlInfo.USER_ID));
            user.setStatus(res.getString(SqlInfo.USER_STATUS));
            user.setPosition(res.getString(SqlInfo.USER_POSITION));
            user.setLogin(res.getString(SqlInfo.USER_LOGIN));
            user.setPassword(res.getString(SqlInfo.USER_PASSWORD));
        }
        return user;
    }
    public ResultSet autorization_request(User user) throws SQLException, ClassNotFoundException {
        String find_user="SELECT * FROM hospital."+SqlInfo.USER_TABLE+" WHERE "+SqlInfo.USER_LOGIN+"="+"?"+" AND "+
                SqlInfo.USER_PASSWORD+"="+"?";
        PreparedStatement preparedStatement=getDbconnection().prepareStatement(find_user);
        preparedStatement.setString(1,user.getLogin());
        preparedStatement.setString(2,user.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
    public void updatePatientAccount(User user, Patient patient) throws SQLException, ClassNotFoundException {
        String query="UPDATE hospital."+SqlInfo.USER_TABLE+" SET "+SqlInfo.USER_LOGIN+"=?"+","+SqlInfo.USER_PASSWORD+"=?"
                +" WHERE "
                +SqlInfo.USER_ID+"=?";
        PreparedStatement statement=getDbconnection().prepareStatement(query);
        statement.setString(1,user.getLogin());
        statement.setString(2,user.getPassword());
        statement.setInt(3,user.getId());
        statement.executeUpdate();
        String queryPatient="UPDATE hospital."+SqlInfo.PATIEN_TABLE+" SET "+SqlInfo.PATIEN_NAME+"=?"+","+SqlInfo.PATIEN_SURNAME+"=?"
                +","+SqlInfo.PATIEN_PATRONYMIC+"=?"+","+SqlInfo.PATIEN_PHONE+"=?"
                +" WHERE "
                +SqlInfo.PATIEN_ID_USER+"=?";
        PreparedStatement statementPatient=getDbconnection().prepareStatement(queryPatient);
        statementPatient.setString(1, patient.getName());
        statementPatient.setString(2,patient.getSur_name());
        statementPatient.setString(3,patient.getPatronymic());
        statementPatient.setString(4,patient.getPhone());
        statementPatient.setInt(5,user.getId());
        statementPatient.executeUpdate();
    }
    public void updateDoctorAccount(User user ,Doctor doctor,Work_day work_day) throws SQLException, ClassNotFoundException {
        String query="UPDATE hospital."+SqlInfo.USER_TABLE+" SET "+SqlInfo.USER_LOGIN+"=?"+","+SqlInfo.USER_PASSWORD+"=?"
                +" WHERE "
                +SqlInfo.USER_ID+"=?";
        PreparedStatement statement=getDbconnection().prepareStatement(query);
        statement.setString(1,user.getLogin());
        statement.setString(2,user.getPassword());
        statement.setInt(3,user.getId());
        statement.executeUpdate();
        String queryDoctor="UPDATE hospital."+SqlInfo.DOCTOR_TABLE+" SET "+SqlInfo.DOCTOR_NAME+"=?"+","+SqlInfo.DOCTOR_SURNAME+"=?"
                +","+SqlInfo.DOCTOR_PATRONYMIC+"=?"+","+SqlInfo.DOCTOR_PHONE+"=?"
                +" WHERE "
                +SqlInfo.PATIEN_ID_USER+"=?";
        PreparedStatement statementPatient=getDbconnection().prepareStatement(queryDoctor);
        statementPatient.setString(1, doctor.getName());
        statementPatient.setString(2,doctor.getSur_name());
        statementPatient.setString(3,doctor.getPatronymic());
        statementPatient.setString(4,doctor.getPhone());
        statementPatient.setInt(5,user.getId());
        statementPatient.executeUpdate();
        String queryWorkDay = "UPDATE hospital."+SqlInfo.WORDK_DAY_TABLE+" SET "+SqlInfo.WORK_DAY_MON+"=?"+","+SqlInfo.WORK_DAY_TUE+"=?"+","
                +SqlInfo.WORK_DAY_WEN+"=?"+","+SqlInfo.WORK_DAY_THU+"=?"+","+SqlInfo.WORK_DAY_FRI+"=?"+" WHERE "+SqlInfo.WORK_DAY_IDPASSPORT+"=?";
        PreparedStatement statementWork=getDbconnection().prepareStatement(queryWorkDay);
        statementWork.setBoolean(1,work_day.getMonday());
        statementWork.setBoolean(1,work_day.getTuesday());
        statementWork.setBoolean(1,work_day.getWednesday());
        statementWork.setBoolean(1,work_day.getThursday());
        statementWork.setBoolean(1,work_day.getFriday());
        statementWork.setString(1,work_day.getId_passport());
        statementWork.executeUpdate();
    }
    public void updateRatingDoctor(Doctor doctor) throws SQLException, ClassNotFoundException {
        String query="UPDATE hospital."+SqlInfo.DOCTOR_TABLE+" SET "+SqlInfo.DOCTOR_RATING+"=?"+" WHERE "
                +SqlInfo.DOCTOR_ID_USER+"=?";
        PreparedStatement statement=getDbconnection().prepareStatement(query);
        statement.setDouble(1,doctor.getRating());
        statement.setInt(2,doctor.getId_user());
        statement.executeUpdate();
    }
    public void updateStatusAccount(User user, String status) throws SQLException, ClassNotFoundException {
    }
    public void addAccountOnBlackList(BlackList blackList) throws SQLException, ClassNotFoundException {
        String insert_user="INSERT INTO hospital."+SqlInfo.BAN_TABLE+"("+SqlInfo.BAN_REASON+","+SqlInfo.BAN_ID_USER+","
                +SqlInfo.BAN_DATE_BLOCK+")"+"VALUES(?,?,?)";
        PreparedStatement statement_user=getDbconnection().prepareStatement(insert_user,PreparedStatement.RETURN_GENERATED_KEYS);
        statement_user.setString(1,blackList.getReason());
        statement_user.setInt(2,blackList.getId_user());
        statement_user.setString(3,blackList.getDate());
        statement_user.executeUpdate();
    }
    public void removeAccountOnBlackList(BlackList blackList) throws SQLException, ClassNotFoundException {
        String query="DELETE FROM hospital."+SqlInfo.BAN_TABLE+" WHERE "+SqlInfo.BAN_ID_USER+"="+blackList.getId_user();
        PreparedStatement statement =getDbconnection().prepareStatement(query);
        statement.executeUpdate(query);
    }
    public void removeRecords(Records records) throws SQLException, ClassNotFoundException {
        String query="DELETE FROM hospital.ticket WHERE hospital.ticket.id = "+records.getId();
        PreparedStatement statement =getDbconnection().prepareStatement(query);
        statement.executeUpdate(query);
    }
    public void removeDoctor(String id) throws SQLException, ClassNotFoundException {
        String query="DELETE FROM hospital.doctor WHERE hospital.doctor.ID_passport= '"+id+"'";
        PreparedStatement statement =getDbconnection().prepareStatement(query);
        statement.executeUpdate(query);
    }
    public List<Doctor> sortDoctorRating() throws SQLException, ClassNotFoundException {
        String find_user="SELECT * FROM hospital."+SqlInfo.DOCTOR_TABLE+" ORDER BY "+SqlInfo.DOCTOR_RATING+" DESC ";
        PreparedStatement preparedStatement=getDbconnection().prepareStatement(find_user);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Doctor>list=new ArrayList<>();
        while (resultSet.next()){
            Doctor doctor=new Doctor();
            doctor.setId_user((resultSet.getInt(SqlInfo.DOCTOR_ID_USER)));
            doctor.setID_passport(resultSet.getString(SqlInfo.DOCTOR_ID_PASSPORT));
            doctor.setSeria_number(resultSet.getString(SqlInfo.DOCTOR_SERIA_NUMBER));
            doctor.setName(resultSet.getString(SqlInfo.DOCTOR_NAME));
            doctor.setSur_name(resultSet.getString(SqlInfo.DOCTOR_SURNAME));
            doctor.setPatronymic(resultSet.getString(SqlInfo.DOCTOR_PATRONYMIC));
            doctor.setPhone(resultSet.getString(SqlInfo.DOCTOR_PHONE));
            doctor.setDate_registration(resultSet.getString(SqlInfo.DOCTOR_DATE_REGISTRATION));
            doctor.setSpeciality(resultSet.getString(SqlInfo.DOCTOR_SPECIALITY));
            doctor.setEducation(resultSet.getString(SqlInfo.DOCTOR_EDUCATION));
            doctor.setExpiriense(resultSet.getString(SqlInfo.DOCTOR_EXPIRIENCE));
            doctor.setRating(resultSet.getDouble(SqlInfo.DOCTOR_RATING));
            doctor.setUniversity(resultSet.getString(SqlInfo.DOCTOR_UNIVERSITY));
            list.add(doctor);
        }
        return list;
    }
}
