package RPIS61.Vizgalov.wdad.learn.xml;

import java.util.ArrayList;

import java.lang.UnsupportedOperationException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlTaskSax {
	final String fileName = "organization.xml";
	SAXParserFactory factory;
	SAXParser saxParser;

	public XmlTaskSax() {
		try {
			factory = SAXParserFactory.newInstance();
			saxParser = factory.newSAXParser();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int salaryAverage() {	
		DefaultHandler salaryAverageHandler;
		ArrayList<Integer> salaries = new ArrayList<Integer>();

		try {
			salaryAverageHandler = new DefaultHandler() {
				boolean salary = false;

				@Override
				public void startElement(String uri, String localName, String qName,
						Attributes attributes) throws SAXException {
					if (qName.equals("salary")) {
						salary = true;
					}
				}

				@Override
				public void characters(char ch[], int start, int length)
						throws SAXException {
					if (salary) {
						salaries.add(new Integer(new String(ch, start, length)));
					} 
				}
				
				@Override
				public void endElement(String uri, String localName, String qName)
						throws SAXException {
					if (qName.equals("salary")) {
						salary = false;
					}
				}
			};
			saxParser.parse(fileName, salaryAverageHandler);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int average = 0;
		for(int i : salaries)
			average += i;
		return average / salaries.size();
	}
	
	public int salaryAverage(String departmentName) {
		DefaultHandler salaryAverageHandler;
		ArrayList<Integer> salaries = new ArrayList<Integer>();

		try {
			salaryAverageHandler = new DefaultHandler() {
				boolean salary = false;
				boolean neccessaryDepartment = false;

				@Override
				public void startElement(String uri, String localName, String qName,
						Attributes attributes) throws SAXException {
					if (qName.equals("salary")) {
						salary = true;
					}
					else if (qName.equals("department")) {
						for(int i = 0; i < attributes.getLength(); i++)
							if (attributes.getQName(i).equals("name")
									&& attributes.getValue(i).equals(departmentName))
								neccessaryDepartment = true;
					}
				}

				@Override
				public void characters(char ch[], int start, int length)
						throws SAXException {
					if (salary && neccessaryDepartment) {
						salaries.add(new Integer(new String(ch, start, length)));
					} 
				}

				@Override
				public void endElement(String uri, String localName, String qName)
						throws SAXException {
					if (qName.equals("department")) {
						neccessaryDepartment = false;
					}
					else if (qName.equals("salary")) {
						salary = false;
					}
				}
			};
			saxParser.parse(fileName, salaryAverageHandler);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int average = 0;
		for(int i : salaries)
			average += i;
		return average / salaries.size();
	}
	
	public void setJobTitle(String firstName, String secondName,
			String newJobTitle) {
		throw new UnsupportedOperationException("SAX can't write.");
	}
	
	public void setSalary(String firstName, String secondName, int newSalary) {
		throw new UnsupportedOperationException("SAX can't write.");
	}
	
	public void fireEmployee(String firstName, String secondName) {
		throw new UnsupportedOperationException("SAX can't write.");
	}
}
