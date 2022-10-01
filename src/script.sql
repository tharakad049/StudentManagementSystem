DROP DATABASE IF EXISTS SipsIns;
CREATE DATABASE IF NOT EXISTS SipsIns;
SHOW DATABASES;
USE SipsIns;

#--------------------------------------------------------------------

DROP TABLE IF EXISTS Student;
CREATE TABLE Student (
    id VARCHAR (6),
    title VARCHAR (6),
    name VARCHAR (30),
    address VARCHAR (30),
    city VARCHAR (20),
    fee VARCHAR (20),
    age VARCHAR (9),
    CONSTRAINT PRIMARY KEY (id)
);

SHOW TABLES;
DESC Student;

DROP TABLE IF EXISTS Program;
CREATE TABLE Program (
    code VARCHAR(6) NOT NULL,
    description VARCHAR(50) DEFAULT NULL,
    duration VARCHAR(20),
    pFee decimal(6,2) DEFAULT NULL,
    freeSpace int(5) DEFAULT NULL,
    CONSTRAINT PRIMARY KEY (code)
);
SHOW TABLES;
DESC Program;

DROP TABLE IF EXISTS PlaceProgram;
CREATE TABLE IF NOT EXISTS PlaceProgram(
    PlaceProgramId VARCHAR (6),
    PlaceProgramDate DATE DEFAULT NULL,
    StudentId VARCHAR (6),
    CONSTRAINT PRIMARY KEY (PlaceProgramId),
    CONSTRAINT FOREIGN KEY (StudentId) REFERENCES Student (id) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE PlaceProgram;

DROP TABLE IF EXISTS PlaceProgramDetails;
CREATE TABLE PlaceProgramDetails (
    PlaceProgramId VARCHAR (6),
    code VARCHAR(6) NOT NULL,
    Qty int(11) DEFAULT NULL,
    ProgramFee decimal(6,2) DEFAULT NULL,
    CONSTRAINT PRIMARY KEY (PlaceProgramId,Code),
    CONSTRAINT FOREIGN KEY (PlaceProgramId) REFERENCES PlaceProgram (PlaceProgramId) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (code) REFERENCES Program (code) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESC PlaceProgramDetails;
