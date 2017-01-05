PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS courses(id integer primary key not null, course_code text, course_name text);
INSERT INTO "courses" VALUES(1,'JAVA-101','Programming with Java');
INSERT INTO "courses" VALUES(2,'JAVA-102','More programming with Java');
INSERT INTO "courses" VALUES(3,'DB-101','Introduction to Databases');
INSERT INTO "courses" VALUES(4,'Bash-101','Introduction to Bash');
INSERT INTO "courses" VALUES(5,'C-101','Programming with C');
COMMIT;
