drop table if exists employee;
drop table if exists property;

create table employee (
	empl_id INTEGER PRIMARY KEY,
        status char(1),
	first_name char(20),
	last_name char(20),
        job_type char(1),
        hire_date date(10),
        license_no int(6)
	);

create table property (
        list_id INTEGER PRIMARY KEY,
        addr_street string(20),
        addr_city char(15),
        addr_state char(2),
        addr_zip int(5),
        sq_ft int(5),
        beds int(1),
        price int(7),
        listing_date date(10),
	license_no int(6),
        FOREIGN KEY(license_no) references employee(license_no)
        );


.separator ,
.import data/employee.txt employee
.import data/property.txt property 