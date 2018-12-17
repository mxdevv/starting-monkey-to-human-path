mysql -u root -h localhost -p -e \
	'DELETE FROM organization.employees;\
	 DELETE FROM organization.jobtitles;\
	 DELETE FROM organization.departments;'
