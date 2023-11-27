CREATE TABLE student (
    id uuid primary key,
    first_name varchar(255),
    last_name varchar(255),
    email TEXT unique,
    phone_number varchar(255),
    age INT
);