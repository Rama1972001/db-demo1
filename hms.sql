use hms ;
    create table Admin(
    id int PRIMARY KEY,
    Aname varchar(30) NOT NULL, 
    phoneNo varchar(30), 
    email varchar(30) NOT NULL, 
 Apassword varchar(30) NOT NULL
    );
       
 CREATE TABLE  Doctors ( id int PRIMARY KEY, 
                 Dname VARCHAR(55) NOT NULL, phoneNo VARCHAR(55), email VARCHAR(55) NOT NULL, Dpassword VARCHAR(55) NOT NULL,
specialization VARCHAR(55), timeFrom TIME NOT NULL, timeTo TIME NOT NULL);
        
CREATE TABLE  Patients ( id INT PRIMARY KEY, 
Pname VARCHAR(55) NOT NULL,  age INT, phoneNo VARCHAR(55), address VARCHAR(55), symptoms VARCHAR(55), 
prescription VARCHAR(1024), bill DECIMAL, checked BOOLEAN);
       
CREATE TABLE  Receptionists ( id INT PRIMARY KEY, 
	Rname VARCHAR(55) NOT NULL, phoneNo VARCHAR(55), email VARCHAR(55) NOT NULL, Rpassword VARCHAR(55) NOT NULL);
       
   
       CREATE TABLE  Staff ( id INT PRIMARY KEY, 
	Sname VARCHAR(55) NOT NULL, phoneNo VARCHAR(55), Sstatus VARCHAR(55) NOT NULL);
      
