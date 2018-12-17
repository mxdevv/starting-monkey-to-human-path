import java.util.List;
import java.util.ArrayList;

import java.rmi.RemoteException;

import java.text.SimpleDateFormat;
import java.text.DateFormat;

import RPIS61.Vizgalov.wdad.learn.rmi.JobTitle;
import RPIS61.Vizgalov.wdad.learn.rmi.Employee;
import RPIS61.Vizgalov.wdad.learn.rmi.Department;

import RPIS61.Vizgalov.wdad.data.managers.JDBCDataManager;

public class JDBC {
	public static void main(String[] args) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		JDBCDataManager jdbc = new JDBCDataManager();
		try {
			System.out.println("Salary average: " + jdbc.salaryAverage());
			System.out.println("Salary average in depar1: " + jdbc.salaryAverage("depar1"));
			
			Employee employee = new Employee();
			employee.firstName = "first2";
			employee.secondName = "second2";
			employee.hiredate = dateFormat.parse("2001-02-02");
			employee.salary = 45000;
			employee.jobTitle = JobTitle.secretary;
			
			System.out.println("\t\"jdbc.setSalary(employee, 99999)\"");
			jdbc.setSalary(employee, 99999);

			System.out.println("\t\"jdbc.setJobTitle(employee, JobTitle.engineer)\"");
			jdbc.setJobTitle(employee, JobTitle.engineer);

			Employee employee2 = new Employee();
			employee2.firstName = "first5";
			employee2.secondName = "second5";
			employee2.hiredate = dateFormat.parse("2004-05-05");
			employee2.salary = 30000;
			employee2.jobTitle = JobTitle.head;

			System.out.println("\t\"jdbc.fireEmployee(employee2)\"");
			jdbc.fireEmployee(employee2);
		
			Employee employee3 = new Employee();
			employee3.firstName = "first-n3";
			employee3.secondName = "second-n3";
			employee3.hiredate = dateFormat.parse("2010-05-05");
			employee3.salary = 30000;
			employee3.jobTitle = JobTitle.engineer;
			
			Employee employee4 = new Employee();
			employee4.firstName = "first-n4";
			employee4.secondName = "second-n4";
			employee4.hiredate = dateFormat.parse("2011-05-05");
			employee4.salary = 30000;
			employee4.jobTitle = JobTitle.engineer;

			Department department = new Department();
			department.name = "depar-n1";
			department.employees = new ArrayList<Employee>();
			department.employees.add(employee3);
			department.employees.add(employee4);

			System.out.println("\t\'jdbc.add(department)\'");
			jdbc.add(department);

			System.out.println("Run db/showdb.sh to see the changes");

		} catch (RemoteException re) {
			re.printStackTrace();
		}
	}
}

