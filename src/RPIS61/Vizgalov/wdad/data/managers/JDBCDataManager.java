package RPIS61.Vizgalov.wdad.data.managers;

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
	private Connection connection;
	private ResultSet resultSet;
	private PreparedStatement statement;
	
	public JDBCDataManager() {
		try {
			dataSource = DataSourceFactory.createDataSource();
			connection = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* non-interface methods */
	public int getJobTitleId(JobTitle jobTitle) {
		//todo select to Jobtitles
		if (jobTitle.name().equals("assistant"))
			return 1;
		else if (jobTitle.name().equals("secretary"))
			return 2;
		else if (jobTitle.name().equals("engineer"))
			return 3;
		else if (jobTitle.name().equals("manager"))
			return 4;
		else if (jobTitle.name().equals("head"))
			return 5;
		return -1;
	}
	private String sql_WHERE(Employee employee) {
		//todo юзай StringBuilder
		return
			"WHERE ( " +
			"  first_name = "       + '\'' + employee.firstName  + "\' " +
			"  AND second_name = "  + '\'' + employee.secondName + "\' " +
			"  AND hire_date = "    + '\'' +
				new SimpleDateFormat("yyyy-MM-dd").format(employee.hiredate) +
																														 "\' " +
			"  AND salary = "              + employee.salary     + " "   +
			"  AND jobtitles_id = " + '\'' +
													getJobTitleId(employee.jobTitle) + "\' " +
			");"
		;
	}

	/* interface realization */
	public int salaryAverage() throws RemoteException {
		int i = 0, average = 0;
		try {
			statement = connection.prepareStatement(
				"SELECT AVG(salary) FROM employees"); //todo используй агрегатные функции
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				average += resultSet.getInt(1);
				++i;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return average / i;
	}
  public int salaryAverage(String departmentName) throws RemoteException {
		int i = 0, average = 0;
		try {
			//todo используй агрегатные функции
			//todo используй preparedStatement как PreparedStatement, а не как Statement
			statement = connection.prepareStatement(
 				"SELECT salary FROM employees " +
				"WHERE departments_id = ANY( " +
				"  SELECT id FROM departments " +
				"  WHERE name = ?"		);
			statement.setString(1, departmentName);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				average += resultSet.getInt(1);
				++i;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return average / i;
	}
  public void setJobTitle(Employee employee, JobTitle newJobTitle)
      throws RemoteException {
		try {
			//todo используй preparedStatement как PreparedStatement, а не как Statement
			statement = connection.prepareStatement(
				"UPDATE employees SET jobtitles_id = "
					+ getJobTitleId(newJobTitle) + " " +
				sql_WHERE(employee) + ";"
			);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  public void setSalary(Employee employee, int newSalary)
      throws RemoteException {
		try {
			//todo используй preparedStatement как PreparedStatement, а не как Statement
			statement = connection.prepareStatement(
				"UPDATE employees SET salary = " + newSalary + " " +
				sql_WHERE(employee) + ";"
			);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  public void fireEmployee(Employee employee) throws RemoteException {
		try {
			//todo используй preparedStatement как PreparedStatement, а не как Statement
			statement = connection.prepareStatement(
				"DELETE FROM employees " +
				sql_WHERE(employee) + ";"
			);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  public void add(Department department) throws RemoteException {
		try {
			int id = 0;
			//todo автоинкремент для первичных ключей
			statement = connection.prepareStatement(
				"SELECT MAX(id) FROM departments;"
			);
			resultSet = statement.executeQuery();
			resultSet.next();
			id = resultSet.getInt(1);
			//todo используй preparedStatement как PreparedStatement, а не как Statement

			statement = connection.prepareStatement(
				"INSERT INTO departments VALUES ( " +
				id + ", " +
				"\'" + department.name + "\' " +
				");"
			);
			statement.executeUpdate();
			for(int i = 0; i < department.employees.size(); i++) {
				//todo используй preparedStatement как PreparedStatement, а не как Statement

				statement = connection.prepareStatement(
					"INSERT INTO employees VALUES ( " +
					  (i + 1) + ", " +
						"\'" + department.employees.get(i).firstName + "\', " +
						"\'" + department.employees.get(i).secondName + "\', " +
						"NULL" + ", " +
						"\'" + new SimpleDateFormat("yyyy-MM-dd").format(
							department.employees.get(i).hiredate) + "\', " +
						department.employees.get(i).salary + ", " +
						"\'" + getJobTitleId(department.employees.get(i).jobTitle) + "\', " +
						id + " " +
					");"
				);
				statement.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
