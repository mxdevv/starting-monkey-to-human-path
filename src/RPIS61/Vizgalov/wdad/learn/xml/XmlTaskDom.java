package RPIS61.Vizgalov.wdad.learn.xml;

import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

public class XmlTaskDom {
	DocumentBuilder documentBuilder;
	Document document;
	final String filePath =
			"./organization.xml";

	public XmlTaskDom() {
		try {
			documentBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			document = documentBuilder.parse(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int salaryAverage() {
		int salaryTotal = 0, employeesCounter = 0;
		try {
			//fixed use Element and his methods getElementByTagName("name"), getAttribute(), setAttribute()
			Element organization = document.getDocumentElement();
			NodeList departments = organization.getElementsByTagName("department");
			for(int i = 0; i < departments.getLength(); i++) {
				Element department = (Element) departments.item(i);
				NodeList employees = department.getElementsByTagName("employee");
				for(int j = 0; j < employees.getLength(); j++) {
					Element employee = (Element) employees.item(j);
					salaryTotal += new Integer(employee.getElementsByTagName("salary")
							.item(0).getTextContent());
					employeesCounter++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return salaryTotal / employeesCounter;
	}
	
	public int salaryAverage(String departmentName) {
		int salaryTotal = 0, employeesCounter = 0;
		try {
			Element organization = document.getDocumentElement();
			NodeList departments = organization.getElementsByTagName("department");
			for(int i = 0; i < departments.getLength(); i++) {
				Element department = (Element) departments.item(i);
				if(department.getAttribute("name").equals(departmentName)) {
						NodeList employees = department.getElementsByTagName("employee");
						for(int j = 0; j < employees.getLength(); j++) {
							Element employee = (Element) employees.item(j);
							Element salaryNode = (Element) employee
								.getElementsByTagName("salary").item(0);
							salaryTotal += new Integer(salaryNode.getTextContent());
						    employeesCounter++;
					    }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return salaryTotal / employeesCounter;
	}

	private Element getEmployee(String firstName, String secondName) {
		Element organization = document.getDocumentElement();
		NodeList departments = organization.getElementsByTagName("department");
		for(int i = 0; i < departments.getLength(); i++) {
			Element department = (Element) departments.item(i);
			NodeList employees = department.getElementsByTagName("employee");
			for(int j = 0; j < employees.getLength(); j++) {
				Element employee = (Element) employees.item(j);
				//fixed same as salaryAverage()
				if (employee.getAttribute("firstname").equals(firstName)
						&& employee.getAttribute("secondname").equals(secondName))
					return employee;
			}
		}
		return null;
	}

	public void setJobTitle(String firstName, String secondName,
			String newJobTitle) {
		try {
			Element employee = getEmployee(firstName, secondName);
			Element jobTitleNode = (Element) employee.getElementsByTagName("Jobtitle").item(0);
			jobTitleNode.setAttribute("value", newJobTitle);
			writeXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//fixed use Element and his methods getElementByTagName("name"), getAttribute(), setAttribute()
    //аналогично setJobTitle()
	public void setSalary(String firstName, String secondName, int newSalary) {
		try {
			Element employee = getEmployee(firstName, secondName);
			Element salaryNode = (Element) employee
				.getElementsByTagName("salary").item(0);
			salaryNode.setAttribute("salary", Integer.toString(newSalary));
			writeXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//fixed use Element and his methods getElementByTagName("name"), getAttribute(), setAttribute()
	public void fireEmployee(String firstName, String secondName) {
		try {
			Element employee = getEmployee(firstName, secondName);
			if (employee != null)
				employee.getParentNode().removeChild(employee);
			writeXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add(String departmentName) {
		Element organization = document.getDocumentElement();
		Element department = document.createElement("department");
		department.setAttribute("name", departmentName);
		organization.appendChild(department);
		writeXml();
	}

	private void writeXml() {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
