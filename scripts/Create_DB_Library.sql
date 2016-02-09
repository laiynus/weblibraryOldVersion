DROP DATABASE IF EXISTS E_library;
CREATE DATABASE E_library;

USE E_library;

Create table Publishing_Houses (
	ID_Publishing_House INT AUTO_INCREMENT NOT NULL,
	Name VARCHAR(50) ,
	Town VARCHAR(50) ,
	CONSTRAINT PK_PUBLISHING_HOUSES PRIMARY KEY (ID_Publishing_House)
);




Create table File_Types (
	Extension VARCHAR(5) NOT NULL,
    File_Type VARCHAR(50) NOT NULL,
	CONSTRAINT PK_FILE_TYPES PRIMARY KEY (Extension)
);



Create table Tegs (
	ID_Teg INT AUTO_INCREMENT NOT NULL,
	Name VARCHAR(100) NOT NULL,
	CONSTRAINT PK_Teg PRIMARY KEY (ID_Teg)
);

INSERT INTO Tegs (Name) VALUES ("fiction");



Create table Books (
	ID_Book INT NOT NULL AUTO_INCREMENT,
	FK_Publishing_House INT,
	FK_File_Type VARCHAR(5) NOT NULL,
	Date_of_Publication  DATETIME NOT NULL,
	Title VARCHAR(50) NOT NULL,
	Annotation VARCHAR(1000) ,
	FilePath VARCHAR(256),
	CONSTRAINT PK_BOOKS PRIMARY KEY (ID_Book),
	CONSTRAINT FK_Books_Publishing_Houses FOREIGN KEY (FK_Publishing_House) REFERENCES Publishing_Houses (ID_Publishing_House) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT FK_Books_File_Types FOREIGN KEY (FK_File_Type) REFERENCES File_Types (Extension) ON DELETE CASCADE ON UPDATE CASCADE
);



Create table Tegs_to_Books (
	ID_Tegs_to_Books INT AUTO_INCREMENT NOT NULL,
	FK_Book INT NOT NULL,
	FK_Teg INT NOT NULL,
	CONSTRAINT PK_TEGS_TO_BOOKS PRIMARY KEY (ID_Tegs_to_Books),
	CONSTRAINT FK_Tegs_Books FOREIGN KEY (FK_Book) REFERENCES Books (ID_Book) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT FK_Books_Tegs FOREIGN KEY (FK_Teg) REFERENCES Tegs (ID_Teg) ON DELETE CASCADE ON UPDATE CASCADE
);



Create table Users (
	Login VARCHAR(50) NOT NULL,
	Last_name VARCHAR(50) ,
	First_name VARCHAR(50) ,
	Patronymic VARCHAR(50),
	Password VARCHAR(100) NOT NULL,
	Email VARCHAR(50) NOT NULL,
	Date_of_Registration  DATETIME,
	Enabled TINYINT NOT NULL DEFAULT 1 ,
	Faculty VARCHAR(50) ,
	Chair VARCHAR(50) ,
	Specialty VARCHAR(50) ,
	CONSTRAINT PK_User PRIMARY KEY (Login)
);

Create table User_Roles (
	ID_User_Role INT AUTO_INCREMENT NOT NULL,
	Login VARCHAR(50) NOT NULL,
	Role VARCHAR(100) NOT NULL,
	UNIQUE KEY uni_login_role (Role,Login),
	KEY fk_username_idx (Login),
	CONSTRAINT User_Roles PRIMARY KEY (ID_User_Role),
	CONSTRAINT FK_User_Role_User FOREIGN KEY (Login) REFERENCES Users (Login) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Users (Login, Last_name, First_name, Patronymic, Password,Email,Date_of_Registration,Enabled, Faculty, Chair, Specialty) VALUES ('R.Khrapovitsky', "Храповицкий", "Роман", "Андреевич",  '$2a$10$N1IpLZhEL1XUBuBT5.hBkOB3PZqA81mLewXJlX8xvQ6iIpCm30BD2','laiynus@gmail.com','2014-12-12 16:43:44',true, "FIT","Programming Technology","IT");
INSERT INTO Users (Login, Last_name, First_name, Patronymic, Password,Email,Date_of_Registration,Enabled, Faculty, Chair, Specialty) VALUES ('M.Ivanov', "Ivanov", "Mihail", "Anatolevich", '$2a$10$N1IpLZhEL1XUBuBT5.hBkOB3PZqA81mLewXJlX8xvQ6iIpCm30BD2','you_last@mail.ru','2014-12-12 16:43:44',true, "FIT","Programming Technology","IT");
INSERT INTO Users (Login, Last_name, First_name, Patronymic, Password,Email,Date_of_Registration,Enabled, Faculty, Chair, Specialty) VALUES ('I.Medvedskiy', "Medvedskiy", "Ilia", "Mihailovich",  '$2a$10$N1IpLZhEL1XUBuBT5.hBkOB3PZqA81mLewXJlX8xvQ6iIpCm30BD2','iliy@gmail.com','2014-12-12 16:43:44',true, "FIT","Programming Technology","IT");
INSERT INTO Users (Login, Last_name, First_name, Patronymic, Password,Email,Date_of_Registration,Enabled, Faculty, Chair, Specialty) VALUES ('I.Goi', "Igor", "Goi", "Mihailovich",  '$2a$10$N1IpLZhEL1XUBuBT5.hBkOB3PZqA81mLewXJlX8xvQ6iIpCm30BD2','i.goi@gmail.com','2014-12-12 16:43:44',true, "FIT","Programming Technology","IT");
INSERT INTO Users (Login, Last_name, First_name, Patronymic, Password,Email,Date_of_Registration,Enabled, Faculty, Chair, Specialty) VALUES ('E.Amelkov', "Amelkov", "Evgeniy", "Andreyvich",  '$2a$10$N1IpLZhEL1XUBuBT5.hBkOB3PZqA81mLewXJlX8xvQ6iIpCm30BD2','ea@gmail.com','2014-12-12 16:43:44',true, "FIT","Programming Technology","IT");
INSERT INTO Users (Login, Last_name, First_name, Patronymic, Password,Email,Date_of_Registration,Enabled, Faculty, Chair, Specialty) VALUES ('E.Andrushko', "Andrushko", "Evgeniy", "Victorovich",  '$2a$10$N1IpLZhEL1XUBuBT5.hBkOB3PZqA81mLewXJlX8xvQ6iIpCm30BD2','eav@gmail.com','2014-12-12 16:43:44',true, "FIT","Programming Technology","IT");


INSERT INTO User_Roles (Login ,Role) VALUES ('R.Khrapovitsky','ROLE_ADMIN');
INSERT INTO User_Roles (Login ,Role) VALUES ('M.Ivanov','ROLE_USER');
INSERT INTO User_Roles (Login ,Role) VALUES ('I.Medvedskiy','ROLE_USER');
INSERT INTO User_Roles (Login ,Role) VALUES ('I.Goi','ROLE_USER');
INSERT INTO User_Roles (Login ,Role) VALUES ('E.Amelkov','ROLE_USER');
INSERT INTO User_Roles (Login ,Role) VALUES ('E.Andrushko','ROLE_USER');


Create table Authors (
	ID_Author INT AUTO_INCREMENT NOT NULL,
	Last_name VARCHAR(50) ,
	First_name VARCHAR(50) ,
	CONSTRAINT PK_AUTHORS PRIMARY KEY (ID_Author)
);


Create table Authors_to_Books (
	ID_Authors_to_Books INT AUTO_INCREMENT NOT NULL,
	FK_Book INT NOT NULL,
	FK_Author INT NOT NULL,
	CONSTRAINT PK_AUTHORS_TO_BOOKS PRIMARY KEY (ID_Authors_to_Books),
	CONSTRAINT FK_Authors_Books FOREIGN KEY (FK_Book) REFERENCES Books (ID_Book) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT FK_Books_Authors FOREIGN KEY (FK_Author) REFERENCES Authors (ID_Author) ON DELETE CASCADE ON UPDATE CASCADE
);



Create table File_Status (
	ID_Status INT AUTO_INCREMENT NOT NULL,
	FK_Book INT NOT NULL,
	Login VARCHAR(50) NOT NULL,
	Date_Uploaded  DATETIME NOT NULL,
	CONSTRAINT PK_FILE_STATUS PRIMARY KEY (ID_Status),
	CONSTRAINT FK_File_Status_Books FOREIGN KEY (FK_Book) REFERENCES Books (ID_Book) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT FK_File_Status_User FOREIGN KEY (Login) REFERENCES Users (Login) ON DELETE CASCADE ON UPDATE CASCADE
);



Create table Bookmarks (
	ID_Bookmark INT AUTO_INCREMENT NOT NULL,
	FK_Book INT NOT NULL,
	Login VARCHAR(50) NOT NULL,
	Last_Date_of_Reading  DATETIME NOT NULL,
	Chapter INT NOT NULL,
	CONSTRAINT PK_BOOKMARKS PRIMARY KEY (ID_Bookmark),
	CONSTRAINT FK_Bookmarks_Books FOREIGN KEY (FK_Book) REFERENCES Books (ID_Book) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT FK_Bookmarks_Users FOREIGN KEY (Login) REFERENCES Users (Login) ON DELETE CASCADE ON UPDATE CASCADE
);




CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);


