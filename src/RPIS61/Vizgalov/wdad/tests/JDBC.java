package RPIS61.Vizgalov.wdad.tests;

import java.rmi.RemoteException;

import java.text.SimpleDateFormat;
import java.text.DateFormat;

import RPIS61.Vizgalov.wdad.learn.rmi.JobTitle;
import RPIS61.Vizgalov.wdad.learn.rmi.Employee;
import RPIS61.Vizgalov.wdad.learn.rmi.Department;

import RPIS61.Vizgalov.wdad.data.managers.JDBCDataManager;

public class JDBC {
	public static void main(String[] args) throws Exception {
		JDBCDataManager jdbc = new JDBCDataManager();
		try {
			System.out.println("Salary average: " + jdbc.salaryAverage());
			System.out.println("Salary average in depar1: " + jdbc.salaryAverage("depar1"));

			Employee employee = new Employee();
			employee.firstName = "first2";
			employee.secondName = "second2";
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			employee.hiredate = dateFormat.parse("2001-02-02");
			employee.salary = 45000;
			employee.jobTitle = JobTitle.secretary;
			
			System.out.println("\t\"jdbc.setSalary(employee, 99999)\"");
			jdbc.setSalary(employee, 99999);
			
			System.out.println("Run db/showdb.sh to see the changes");

		} catch (RemoteException re) {
			re.printStackTrace();
		}
	}
}
