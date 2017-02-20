PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE job_experience(exp_id integer primary key not null, employer text, title text, start_date datetime, end_date datetime, description text);
INSERT INTO "job_experience" VALUES(1,'Yrgo','Java teacher','2017-01-01 00:00:00','2017-06-30 00:00:00','Teacher for a Java course');
INSERT INTO "job_experience" VALUES(2,'ITHS','Database teacher','2016-01-01 00:00:00','2016-02-28 00:00:00','Teacher for a Database course');
INSERT INTO "job_experience" VALUES(3,'GU','Database teacher','2016-06-01 00:00:00','2016-12-31 00:00:00','Lecturer for an introductory Java course etc');
COMMIT;
