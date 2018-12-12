CREATE TABLE employees (
	id INT,
	first_name VARCHAR(50),
	second_name VARCHAR(50),
	birth_date DATE,
	hire_date DATE,
	salary NUMERIC(15,5),
	jobtitles_id INT,
	departments_id INT,
	PRIMARY KEY(id)
);
CREATE TABLE departments (
	id INT,
	name VARCHAR(50),
	Description VARCHAR(1),
	PRIMARY KEY(id)
);
CREATE TABLE jobtitles (
	id INT,
	name VARCHAR(100),
	PRIMARY KEY(id)
);

ALTER TABLE employees
	ADD FOREIGN KEY (jobtitles_id)
	REFERENCES jobtitles (id);
ALTER TABLE employees
	ADD FOREIGN KEY (departments_id)
	REFERENCES departments (id);
