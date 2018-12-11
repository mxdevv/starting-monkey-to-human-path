mysql -u root -h localhost -p -e \
	"SHOW TABLES FROM organization;\
	 SHOW COLUMNS FROM organization.employees;\
	 SHOW COLUMNS FROM organization.departments;\
	 SHOW COLUMNS FROM organization.jobtitles;\
	 SELECT * FROM organization.employees;\
	 SELECT * FROM organization.departments;\
	 SELECT * FROM organization.jobtitles;"
