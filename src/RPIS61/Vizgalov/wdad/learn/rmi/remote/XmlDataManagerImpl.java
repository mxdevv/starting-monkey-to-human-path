package RPIS61.Vizgalov.wdad.learn.rmi.remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import RPIS61.Vizgalov.wdad.learn.xml.XmlTaskDom;
import RPIS61.Vizgalov.wdad.learn.rmi.remote.XmlDataManager;

import RPIS61.Vizgalov.wdad.learn.rmi.JobTitle;
import RPIS61.Vizgalov.wdad.learn.rmi.Employee;
import RPIS61.Vizgalov.wdad.learn.rmi.Department;

public class XmlDataManagerImpl
		extends UnicastRemoteObject implements XmlDataManager {
	
	private XmlTaskDom xmlTaskDom;

	public XmlDataManagerImpl() throws RemoteException {
		super();
		xmlTaskDom = new XmlTaskDom();
	}

	///////////////////////////////////////////////////
	@Override
	public String foo() throws RemoteException {
		System.err.println("Client run foo()");
		return "foo() return String";
	}
	///////////////////////////////////////////////////
	
	@Override
	public int salaryAverage() throws RemoteException {
		return xmlTaskDom.salaryAverage();
	}

	@Override
	public int salaryAverage(String departmentName) throws RemoteException {
		return xmlTaskDom.salaryAverage(departmentName);
	}
	
	@Override
	public void setJobTitle(Employee employee, JobTitle newJobTitle)
			throws RemoteException {
		String stringJobTitle = new String();
		switch(newJobTitle) {
			case assistant:
				stringJobTitle = "assistant"; break;
			case secretary:
				stringJobTitle = "secretary"; break;
			case engineer:
				stringJobTitle = "engineer"; break;
			case manager:
				stringJobTitle = "manager"; break;
			case head:
				stringJobTitle = "head"; break;
		}
		xmlTaskDom.setJobTitle(employee.firstName, employee.secondName,
				stringJobTitle);
	}

	@Override	
	public void setSalary(Employee employee, int newSalary)
			throws RemoteException {
		xmlTaskDom.setSalary(employee.firstName, employee.secondName, newSalary);
	}
	
	@Override
	public void fireEmployee(Employee employee) throws RemoteException {
		xmlTaskDom.fireEmployee(employee.firstName, employee.secondName);
	}
	
	@Override
	public void add(Department department) throws RemoteException {
		xmlTaskDom.add(department.name);
	}
}
