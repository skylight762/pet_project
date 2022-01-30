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

insert into ACCOUNT (account_id, user_id, user_name, sum) values ( 1,1,'ivanov',100 );
insert into ACCOUNT (account_id, user_id, user_name, sum) values ( 2,2,'ivanov',100 );
