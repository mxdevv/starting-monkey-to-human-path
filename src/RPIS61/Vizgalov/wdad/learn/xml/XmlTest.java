package RPIS61.Vizgalov.wdad.learn.xml;

import RPIS61.Vizgalov.wdad.learn.xml.XmlTaskSax;

public class XmlTest {
	public static void main(String[] args) {
		System.out.println("XmlTest is run.");

		XmlTaskSax xmlTaskSax = new XmlTaskSax();
		System.out.println("Salary average: " + xmlTaskSax.salaryAverage());
		System.out.println("Salary average in \"Department 1.2\": "
				+ xmlTaskSax.salaryAverage("Department 1.2"));
	}
}
