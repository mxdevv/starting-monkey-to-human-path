package wdad.learn.xml;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHendler;

public class XmlTaskSax {
	final String fileName = "organization.xml";
	SAXParserFactory factory;
	SAXParser saxParser;
	DefaultHendler testHandler;
	
	public XmlTaskSax() {
		try {
			factory = SAXParserFactory.newInstance();
			saxParser = factory.newSAXParser();

			testHandler = new DefaultHendler() {
				boolean organization = false;

				@Override
				public void startElement(String uri, String localName, String qName,
						Attributes attributes) throws SAXException {
					if (qName.equalsIgnoreCase("organization")) {
						organization = true;
					}
				}

				@Override
				public void characters(char ch[], int start, int length)
						throws SAXException {
					if (organization) {
						System.out.println("organization: " + new String(ch, start, length));
						organization = false;
					} 
				}
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void test() {
		saxParser.parse(fileName, testHandler);
	}

	public int salaryAverage() {
		
		return -1;
	}
	
	public int salaryAverage(String departmentName) {
		
		return -1;
	}
	
	public void setJobTitle(String firstName, String secondName,
			String newJobTitle) {
		System.out.println("Not supported!");
	}
	
	public void setSalary(String firstName, String secondName, int newSalary) {
		System.out.println("Not supported!");
	}
	
	public void fireEmployee(String firstName, String secondName) {
		;
	}
}
