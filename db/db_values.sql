INSERT INTO jobtitles (id, name) VALUES
	(1, 'assistant'),
	(2, 'secretary'),
	(3, 'engineer'),
	(4, 'manager'),
	(5, 'head');

INSERT INTO departments (id, name, Description) VALUES
	(1, 'depar1', 'a'),
	(2, 'depar2', 'b'),
	(3, 'depar3', 'c'),
	(4, 'depar4', 'd'),
	(5, 'depar5', 'e'),
	(6, 'depar6', 'f');

INSERT INTO employees (id, first_name, second_name, birth_date, hire_date,
                       salary, jobtitles_id, departments_id) VALUES
	(1, 'first1', 'second1', '1970-01-01', '2000-01-01', 50000, 1, 1),
	(2, 'first2', 'second2', '1975-02-02', '2001-02-02', 45000, 2, 1),
	(3, 'first3', 'second3', '1980-03-03', '2002-03-03', 40000, 3, 1),
	(4, 'first4', 'second4', '1985-04-04', '2003-04-04', 35000, 4, 2),
	(5, 'first5', 'second5', '1990-04-04', '2004-05-05', 30000, 5, 2),
	(6, 'first6', 'second6', '1995-05-05', '2005-05-05', 25000, 1, 1);
