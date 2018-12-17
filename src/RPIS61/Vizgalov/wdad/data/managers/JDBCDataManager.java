package RPIS61.Vizgalov.wdad.data.managers;

import java.lang.StringBuilder;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

import RPIS61.Vizgalov.wdad.learn.rmi.JobTitle;
import RPIS61.Vizgalov.wdad.learn.rmi.Employee;
import RPIS61.Vizgalov.wdad.learn.rmi.Department;

import RPIS61.Vizgalov.wdad.data.managers.DataManager;
import RPIS61.Vizgalov.wdad.data.storage.DataSourceFactory;

public class JDBCDataManager implements DataManager {
	private DataSource dataSource;
	
	public JDBCDataManager() {
		try {
			dataSource = DataSourceFactory.createDataSource();
			//todo new connection per method
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* non-interface methods */
	private String sql_WHERE(Employee employee) {
		//todo юзай StringBuilder
    StringBuilder strBuild = new StringBuilder(150);
		strBuild.append("WHERE ( ")
		        .append("  first_name = \'")
		        	.append(employee.firstName)
		        		.append("\' ")
		        .append("  AND second_name = \'")
		        	.append(employee.secondName)
		        		.append("\' ")
		        .append("  AND hire_date = \'")
		        	.append(new SimpleDateFormat("yyyy-MM-dd").format(employee.hiredate))
		        		.append("\' ")
		        .append("  AND salary = ")
		        	.append(employee.salary)
		        		.append(" ")
		        .append("  AND jobtitles_id = \'")
		        	.append(getJobTitleId(employee.jobTitle))
		        		.append("\' ")
		        .append(");");
		return strBuild.toString();
	}
	public int getJobTitleId(JobTitle jobTitle) {
		//todo select to Jobtitles
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int id = -1;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(
				"SELECT id FROM jobtitles WHERE name = ?;");
			statement.setString(1, jobTitle.name());
			resultSet = statement.executeQuery();
			resultSet.next();
			id = resultSet.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { connection.close(); } catch (Exception e) {}
		}
		return id;
	}
  public int getDepartmentsId(Department department) throws RemoteException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int id = 0;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(
				"SELECT id FROM departments WHERE name = ?;");
			statement.setString(1, department.name);
			resultSet = statement.executeQuery();
			resultSet.next();
			id = resultSet.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { connection.close(); } catch (Exception e) {}
		}
		return id;
	}
	public void add(Employee employee, int departmentsId) throws RemoteException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			//todo используй preparedStatement как PreparedStatement, а не как Statement
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(
				"INSERT INTO employees ( " +
					"id, first_name, second_name, birth_date, hire_date, salary, " +
					"jobtitles_id, departments_id ) VALUES ( " +
					"NULL, ?, ?, NULL, ?, ?, ?, ? );"
			);
			statement.setString(1, employee.firstName);
			statement.setString(2, employee.secondName);
			statement.setString(3,
				new SimpleDateFormat("yyyy-MM-dd").format(
					employee.hiredate));
			statement.setInt(4, employee.salary);
			statement.setInt(5, getJobTitleId(employee.jobTitle));
			statement.setInt(6, departmentsId);
			
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { connection.close(); } catch (Exception e) {}
		}
	}

	/* interface realization */
	public int salaryAverage() throws RemoteException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int average = -1;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(
				"SELECT AVG(salary) FROM employees"); //todo используй агрегатные функции
			resultSet = statement.executeQuery();
			resultSet.next();
			average = resultSet.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { connection.close(); } catch (Exception e) {}
		}
		return average;
	}
  public int salaryAverage(String departmentName) throws RemoteException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int average = -1;
		try {
			//todo используй агрегатные функции
			//todo используй preparedStatement как PreparedStatement, а не как Statement
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(
 				"SELECT AVG(salary) FROM employees " +
				"WHERE departments_id = ANY( "       +
				"  SELECT id FROM departments "      +
				"  WHERE name = ? " +
        ")"
      );
			statement.setString(1, departmentName);
			resultSet = statement.executeQuery();
		  resultSet.next();
			average = resultSet.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { connection.close(); } catch (Exception e) {}
		}
		return average;
	}
  public void setJobTitle(Employee employee, JobTitle newJobTitle)
      throws RemoteException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			//todo используй preparedStatement как PreparedStatement, а не как Statement
      int id = getJobTitleId(newJobTitle);
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(
				"UPDATE employees SET jobtitles_id = ? " +
				sql_WHERE(employee) + ";"
			);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { connection.close(); } catch (Exception e) {}
		}
	}
  public void setSalary(Employee employee, int newSalary)
      throws RemoteException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			//todo используй preparedStatement как PreparedStatement, а не как Statement
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(
				"UPDATE employees SET salary = ? " +
				sql_WHERE(employee) + ";"
			);
			statement.setInt(1, newSalary);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { connection.close(); } catch (Exception e) {}
		}
	}
  public void fireEmployee(Employee employee) throws RemoteException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			//todo используй preparedStatement как PreparedStatement, а не как Statement
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(
				"DELETE FROM employees " +
				sql_WHERE(employee) + ";"
			);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { connection.close(); } catch (Exception e) {}
		}
	}

  public void add(Department department) throws RemoteException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			//todo автоинкремент для первичных ключей
			connection = dataSource.getConnection();
			//todo используй preparedStatement как PreparedStatement, а не как Statement
			statement = connection.prepareStatement(
				"INSERT INTO departments (id, name, Description) VALUES ( NULL, ?, 'x' );"
			);
			statement.setString(1, department.name);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { connection.close(); } catch (Exception e) {}
		}
		int id = getDepartmentsId(department);
		for(int i = 0; i < department.employees.size(); i++) {
			add(department.employees.get(i), id);
		}
	}
}
