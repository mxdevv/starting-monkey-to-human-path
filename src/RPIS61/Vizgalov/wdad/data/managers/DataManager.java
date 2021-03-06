package RPIS61.Vizgalov.wdad.data.managers;

import java.rmi.Remote;
import java.rmi.RemoteException;

import RPIS61.Vizgalov.wdad.learn.rmi.JobTitle;
import RPIS61.Vizgalov.wdad.learn.rmi.Employee;
import RPIS61.Vizgalov.wdad.learn.rmi.Department;

public interface DataManager extends Remote {
	public int salaryAverage() throws RemoteException;
	public int salaryAverage(String departmentName) throws RemoteException;
	public void setJobTitle(Employee employee, JobTitle newJobTitle)
			throws RemoteException;
	public void setSalary(Employee employee, int newSalary)
			throws RemoteException;
	public void fireEmployee(Employee employee) throws RemoteException;
	public void add(Department department) throws RemoteException;
}
