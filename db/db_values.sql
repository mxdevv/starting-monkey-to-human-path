INSERT INTO jobtitles (id, name) VALUES
	(NULL, 'assistant'),
	(NULL, 'secretary'),
	(NULL, 'engineer'),
	(NULL, 'manager'),
	(NULL, 'head');

INSERT INTO departments (id, name, Description) VALUES
	(NULL, 'depar1', 'a'),
	(NULL, 'depar2', 'b'),
	(NULL, 'depar3', 'c'),
	(NULL, 'depar4', 'd'),
	(NULL, 'depar5', 'e'),
	(NULL, 'depar6', 'f');

INSERT INTO employees (id, first_name, second_name, birth_date, hire_date,
                       salary, jobtitles_id, departments_id) VALUES
	(NULL, 'first1', 'second1', '1970-01-01', '2000-01-01', 50000, 1, 1),
	(NULL, 'first2', 'second2', '1975-02-02', '2001-02-02', 45000, 2, 1),
	(NULL, 'first3', 'second3', '1980-03-03', '2002-03-03', 40000, 3, 1),
	(NULL, 'first4', 'second4', '1985-04-04', '2003-04-04', 35000, 4, 2),
	(NULL, 'first5', 'second5', '1990-04-04', '2004-05-05', 30000, 5, 2),
	(NULL, 'first6', 'second6', '1995-05-05', '2005-05-05', 25000, 1, 1);
