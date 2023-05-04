create schema comtech;
create table comtech.account(
    `id` int auto_increment not null ,
    `login` varchar(45) not null ,
    `password` varchar(45) not null ,
    `statusAcc` varchar(45) not null ,
    `roleAcc` varchar(45) not null ,
    `dateRegistration` varchar(45) not null ,
    primary key (id)
);
create table comtech.customer(
    `id` int auto_increment,
    `id_account` int not null ,
    `name` varchar(45) not null ,
    `surname` varchar(45) not null ,
    `patronymic` varchar(45) not null ,
    `phone` varchar(45),
    `instagramm` varchar(45),
    `Gmail` varchar(45),
    primary key (`id`),
    foreign key (`id_account`) references account(id)  on delete cascade
);
create  table comtech.technique(
    `id` int auto_increment,
    `nameTechnique` varchar(60) not null ,
    `typeTechnique` varchar(60) not null ,
    `status` varchar(20) not null ,
    `price` double not null ,
    `amount` int not null ,
    `dateRegistration` varchar(45) not null ,
    primary key (`id`)
);
create  table comtech.config(
    `id` int auto_increment,
    `id_account` int not null ,
    `port` int not null ,
    `ip` varchar(45) not null ,
    foreign key (`id_account`) references technique(id)  on delete cascade,
    primary key (`id`)
);
create  table  comtech.history(
    `id` int auto_increment,
    `id_account` int ,
    `id_customer` int,
    `id_technique` int,
    `price` double not null ,
    `amount` int not null ,
    `date_purchase` varchar(45) not null ,
    `status` varchar(45) not null,
    primary key (`id`),
    foreign key (`id_account`) references account(id)  on delete cascade,
    foreign key (`id_technique`) references technique(id)  on delete cascade,
    foreign key (`id_customer`) references customer(id)  on delete cascade
)
create table comtech.order(
    `id` int auto_increment,
    `id_accoun` int ,
    `id_technique` int,
    `date` varchar(45) not null ,
    `status` varchar(45),
    primary key (`id`),
    foreign key (`id_technique`) references technique(id)  on delete cascade,
    foreign key (`id_accoun`) references account(id)  on delete cascade
)
