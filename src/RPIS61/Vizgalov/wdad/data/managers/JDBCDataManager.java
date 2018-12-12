package RPIS61.Vizgalov.wdad.data.managers;

import javax.sql.DataSource;
import java.rmi.RemoteException;

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int salaryAverage() throws RemoteException {
		return -1;
	}
  public int salaryAverage(String departmentName) throws RemoteException {
		return -1;
	}
  public void setJobTitle(Employee employee, JobTitle newJobTitle)
      throws RemoteException {
		;
	}
  public void setSalary(Employee employee, int newSalary)
      throws RemoteException {
		;
	}
  public void fireEmployee(Employee employee) throws RemoteException {
		;
	}
  public void add(Department department) throws RemoteException {
		;
	}
}
