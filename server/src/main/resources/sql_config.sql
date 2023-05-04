create schema hospital;
insert into hospital.user( login, password, status, position) VALUES ('log','pas','active','admin');
insert into hospital.administration(ID_passport, seria_number, date_registration, id_user, name, surname, patronymic, phone) VALUES ('1234555A123AA1','MP1234567','10.10.2022 15:25', (SELECT MAX(user.id) FROM user),'Виолетта','Писакова','Олеговна','+375293530749');
create table hospital.user(
    `id` int auto_increment not null ,
    `login` varchar(45) not null ,
    `password` varchar(45) not null ,
    `status` varchar(45) not null ,
    `position` varchar(45) not null ,
    primary key (id)
);
create table hospital.administration(
    `ID_passport` varchar(25) not null ,
    `seria_number` varchar(15) not null ,
    `date_registration` varchar(25) not null ,
    `id_user` int not null ,
    `name` varchar(45) not null ,
    `surname` varchar(45) not null ,
    `patronymic` varchar(45) not null ,
    `phone` varchar(45) not null ,
    foreign key (`id_user`) references user(id)  on delete cascade,
    primary key (ID_passport)
);
create table hospital.doctor(
    `ID_passport` varchar(25) not null ,
    `seria_number` varchar(15) not null ,
    `date_registration` varchar(25) not null ,
    `id_user` int not null ,
    `name` varchar(45) not null ,
    `surname` varchar(45) not null ,
    `patronymic` varchar(45) not null ,
    `phone` varchar(45) not null ,
    `education` varchar(25) not null ,
    `speciality` varchar(50) not null ,
    `expiriense` varchar(20) not null ,
    `university` varchar(80) not null ,
    `rating` double not null ,
    foreign key (`id_user`) references user(id)  on delete cascade,
    primary key (ID_passport)
);
create table hospital.patient(
    `ID_passport` varchar(25) not null ,
    `seria_number` varchar(15) not null ,
    `date_registration` varchar(25) not null ,
    `id_user` int not null ,
    `name` varchar(45) not null ,
    `surname` varchar(45) not null ,
    `patronymic` varchar(45) not null ,
    `phone` varchar(45) not null ,
    `sex` varchar(15) not null ,
    `date_birth` varchar(16) not null ,
     foreign key (`id_user`) references user(id)  on delete cascade,
     primary key (ID_passport)
);
create table hospital.blockList(
    `id` int auto_increment not null ,
    `id_user` int not null ,
    `reason` varchar(500) not null ,
    `dateBlock` varchar(25) not null ,
    primary key (id),
    foreign key (`id_user`) references user(id)  on delete cascade
);
create table hospital.work_day(
    `id` int auto_increment not null ,
    `id_doctor_passport` varchar(45) not null ,
    `monday` boolean not null ,
    `tuesday` boolean not null ,
    `wednesday` boolean not null ,
    `thursday` boolean not null ,
    `friday` boolean not null ,
    primary key (id),
    foreign key (`id_doctor_passport`) references doctor(ID_passport)  on delete cascade
);
create table hospital.complaintBook(
    `id` int auto_increment,
    `id_user` int  ,
    `complaint` varchar(1000) not null ,
    `fio` varchar(100) not null ,
    `date` varchar(25),
    primary key (id),
    foreign key (id_user) references  user(id) ON DELETE SET NULL
);
create table hospital.review(
    `id` int auto_increment,
    `id_user` int  ,
    `id_doctor` varchar(45)  ,
    `review` varchar(1000) not null ,
    `rating` int not null ,
    `date` varchar(25) not null ,
    primary key (id),
    foreign key (id_user) references  user(id) ON DELETE SET NULL,
    foreign key (id_doctor) references doctor(ID_passport) ON DELETE SET NULL
);
create table hospital.ticket(
    `id` int auto_increment,
    `idUser` int ,
    `idDoctor` varchar(45),
    `status` varchar(45),
    `date` varchar(45) not null ,
    `dateRecords`  varchar(45) not null ,
    primary key (`id`),
    foreign key (idUser) references  hospital.user(id) on delete cascade,
    foreign key (idDoctor) references hospital.doctor(ID_passport) on delete cascade
);
create table hospital.history(
    `id` int auto_increment,
    `idUser` int ,
    `idDoctor` varchar(45),
    `status` varchar(45),
    `dateRecords`  varchar(45) not null ,
    `FIOdoctor` varchar(100) not null ,
    `speciality` varchar(100) not null ,
    primary key (`id`),
    foreign key (idUser) references  hospital.user(id) ON DELETE SET NULL,
    foreign key (idDoctor) references hospital.doctor(ID_passport) ON DELETE SET NULL
);