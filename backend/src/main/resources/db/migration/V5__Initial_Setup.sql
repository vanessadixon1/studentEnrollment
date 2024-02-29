ALTER TABLE student
ADD COLUMN gender varchar(255);

ALTER TABLE student
ALTER COLUMN gender SET not null;