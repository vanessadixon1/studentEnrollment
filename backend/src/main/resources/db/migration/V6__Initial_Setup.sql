ALTER TABLE student
DROP COLUMN gender;

ALTER TABLE student
ADD COLUMN gender TEXT;

ALTER TABLE student
ALTER COLUMN gender SET not null;