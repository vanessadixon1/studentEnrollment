CREATE TABLE student (
                         id uuid primary key,
                         first_name varchar(255),
                         last_name varchar(255),
                         email TEXT NOT NULL unique,
                         password TEXT NOT NULL,
                         phone_number varchar(255),
                         age INT,
                         gender TEXT NOT NULL
);
