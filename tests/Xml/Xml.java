import RPIS61.Vizgalov.wdad.learn.xml.XmlTaskSax;
import RPIS61.Vizgalov.wdad.learn.xml.XmlTaskDom;

public class Xml {
	public static void main(String[] args) {
		System.out.println("XmlTest is run.");

		System.out.println("   SAX:");
		XmlTaskSax xmlTaskSax = new XmlTaskSax();
		System.out.println("Salary average: " + xmlTaskSax.salaryAverage());
		
		System.out.println("Salary average in \"Department 1.2\": "
				+ xmlTaskSax.salaryAverage("Department 1.2"));
		
		// Throw exception: java.lang.UsupportedOperationException: SAX can't write.
		//xmlTaskSax.setJobTitle("firstname 1.1.4", "secondname 1.1.4", "engineer");
		
		// Throw exception: java.lang.UsupportedOperationException: SAX can't write.
		//xmlTaskSax.setSalary("firstname 1.1.4", "secondname 1.1.4", 85034);
	
		// Throw exception: java.lang.UsupportedOperationException: SAX can't write.
		//xmlTaskSax.fireEmployee("firstname 1.1.4", "secondname 1.1.4");
		
		
		System.out.println("   DOM:");
		XmlTaskDom xmlTaskDom = new XmlTaskDom();
		System.out.println("Salary average: " + xmlTaskDom.salaryAverage());
		
		System.out.println("Salary average in \"Department 1.2\": "
				+ xmlTaskDom.salaryAverage("Department 1.2"));

		xmlTaskDom.setJobTitle("firstname 1.3.2", "secondname 1.3.2", "assistant");
		
		xmlTaskDom.setSalary("firstname 1.3.2", "secondname 1.3.2", 333333);
	
		xmlTaskDom.fireEmployee("firstname 1.1.2", "secondname 1.1.2");
	}
}
