CREATE TABLE IF NOT EXISTS ACCOUNT
(
    account_id integer not null,
    user_id    integer not null,
    user_name  varchar,
    sum        decimal
);

CREATE TABLE IF NOT EXISTS TRANSACTION
(
    transaction_id integer not null,
    account_id     integer not null,
    operation_name varchar(255),
    value          decimal ,
    start_time     datetime,
    end_time       datetime
);

insert into ACCOUNT (account_id, user_id, user_name, sum) values ( 1,1,'М. Горький',0.0 );
insert into ACCOUNT (account_id, user_id, user_name, sum) values ( 2,2,'А. Толстой',0.0 );
insert into ACCOUNT (account_id, user_id, user_name, sum) values ( 3,3,'А. Пушкин',0.0 );
insert into ACCOUNT (account_id, user_id, user_name, sum) values ( 4,4,'С. Сергеев',0.0 );
insert into ACCOUNT (account_id, user_id, user_name, sum) values ( 5,5,'А. Суриков',0.0 );
insert into ACCOUNT (account_id, user_id, user_name, sum) values ( 6,6,'А. Фадеев',0.0 );
insert into ACCOUNT (account_id, user_id, user_name, sum) values ( 7,7,'Г. Марков',0.0 );
insert into ACCOUNT (account_id, user_id, user_name, sum) values ( 8,8,'С. Есенин',0.0 );
insert into ACCOUNT (account_id, user_id, user_name, sum) values ( 9,9,'А. Азимов',0.0 );
