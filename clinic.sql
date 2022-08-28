create database clinic;
use clinic;
create table Doctor(
jobID int,
dname varchar(20),
age int,
Salary int,
primary key(jobID)
);

create table Room(
rID int,
rtype varchar(20),
numberOfBeds int,
primary key(rID)
);

create table Patient(
pID int,
pname varchar(20),
age int,
DoB date,
rID int,
primary key(pID),
foreign key (rID) references Room(rID)
);


create table Nurse(
nID int,
nName varchar(20),
ranks int,
shift varchar(1),
primary key(nID)
);



create table Room_Nurse(
rID int,
nID int,
primary key(rID,nID),
foreign key(rID) references Room(rID),
foreign key(nID) references Nurse(nID)
);


create table Patient_Doctor(
dID int,
pID int,
primary key(dID,pID),
foreign key(dID) references Doctor(jobID),
foreign key(pID) references Patient(pID)
);

create table Payment(
prescID int,
price float,
pDate date,
dID int,
pID int,
primary key(prescID),
foreign key(dID) references Doctor(jobID),
foreign key(pID) references Patient(pID)
);

create table Inventory(
invID int,
TypeI varchar(20),
amount int,
primary key(invID)
);


create table Inventory_Nurse(
rID int,
nID int,
primary key(rID,nID),
foreign key(rID) references Inventory(invID),
foreign key(nID) references Nurse(nID)
);



create table Week(
ID int,
day varchar(20),
primary key(ID)
);

create table Schedule(
dID int,
timing int,
primary key(dID, timing),
foreign key(dID) references Doctor(jobID)
);


create table Week_Schedule(
dID int,
ID int,
timing int,
primary key(dID),
foreign key(dID,timing) references Schedule(dID,timing),
foreign key(dID) references Week(ID)
);
