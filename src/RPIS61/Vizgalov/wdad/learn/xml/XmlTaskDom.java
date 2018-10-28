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
			"/root/code/java/3kurs/starting-monkey-to-human-path/src/RPIS61/Vizgalov/wdad/learn/xml/organization.xml";

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
		//todo fixed employeesCounter + salaryTotal instead of list
		try {
			//todo use Element and his methods getElementByTagName("name"), getAttribute(), setAttribute()
			Element organization = document.getDocumentElement();
			NodeList departments = organization.getElementsByTagName("department");
			for(int i = 0; i < departments.getLength(); i++) {
				Element department = (Element) departments.item(i);
				NodeList employees = department.getElementsByTagName("employee");
				for(int j = 0; j < employees.getLength(); j++) {
					Element employee = (Element) employees.item(j);
				
					NodeList attrs = employee.getElementsByTagName("salary");
					salaryTotal += new Integer(attrs.item(0).getTextContent());

					// problem getNodeValue() return null
					//if (employee != null) {
					//	NodeList attrs = employee.getElementsByTagName("salary");
					//	if (attrs != null && attrs.item(0) != null)
					//		System.out.println(attrs
					//				.item(0).getNodeValue());
					//}
					
					// problem attr == null
					//if (employee != null) {
					//	Attr attr = employee.getAttributeNode("salary");
					//	if (attr != null) {
					//		System.out.println(attr
					//				.getValue());
					//	}
					//}

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
		//todo use Element and his methods getElementByTagName("name"), getAttribute(), setAttribute()
		try {
			Element organization = document.getDocumentElement();
			NodeList departments = organization.getElementsByTagName("department");
			for(int i = 0; i < departments.getLength(); i++) {
				Element department = (Element) departments.item(i);
				NamedNodeMap attributes = department.getAttributes();
				if (attributes != null) {
					Node attribute = attributes.getNamedItem("name");
					if (attribute != null
							&& attribute.getNodeValue().equals(departmentName)) {
						NodeList employees = department.getElementsByTagName("employee");
						for(int j = 0; j < employees.getLength(); j++) {
							Element employee = (Element) employees.item(j);
							NodeList employeeProperties = employee.getChildNodes();
							for(int k = 0; k < employeeProperties.getLength(); k++) {
								Node employeeProperty = employeeProperties.item(k);
								if (employeeProperty.getNodeName().equals("salary")) {
									salaryTotal += new Integer(employeeProperty.getTextContent());
									employeesCounter++;
								}
							}
						}
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
				NamedNodeMap attributes = employee.getAttributes();
				if (attributes != null) {
					Node attrFirstname = attributes.getNamedItem("firstname");
					Node attrSecondname = attributes.getNamedItem("secondname");
					if (attrFirstname != null && attrSecondname != null
							&& attrFirstname.getNodeValue().equals(firstName)
							&& attrSecondname.getNodeValue().equals(secondName)) {
								return employee;
							}
				}
			}
		}
		return null;
	}

	//todo fixed убери дублирование - сделай метод, возвращающий элемент - нужный employee
	//todo use Element and his methods getElementByTagName("name"), getAttribute(), setAttribute()
	public void setJobTitle(String firstName, String secondName,
			String newJobTitle) {
		try {
			Element employee = getEmployee(firstName, secondName);
			NodeList employeeProperties = employee.getChildNodes();
			for(int k = 0; k < employeeProperties.getLength(); k++) {
				Node employeeProperty = employeeProperties.item(k);
				if (employeeProperty.getNodeName().equals("jobtitle")) {
					NamedNodeMap attrsJobTitle = employeeProperty.getAttributes();
					if (attrsJobTitle != null) {
						Node attrJobTitle = attrsJobTitle.getNamedItem("value");
						if (attrJobTitle != null) {
							attrJobTitle.setNodeValue(newJobTitle);
						}
					}
				}
			}
			writeXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//todo use Element and his methods getElementByTagName("name"), getAttribute(), setAttribute()

	public void setSalary(String firstName, String secondName, int newSalary) {
		try {
			Element employee = getEmployee(firstName, secondName);
			NodeList employeeProperties = employee.getChildNodes();
			for(int k = 0; k < employeeProperties.getLength(); k++) {
				Node employeeProperty = employeeProperties.item(k);
				if (employeeProperty.getNodeName().equals("salary")) {
					employeeProperty.getFirstChild().setNodeValue(String.valueOf(newSalary));
				}
			}
			writeXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//todo use Element and his methods getElementByTagName("name"), getAttribute(), setAttribute()
	public void fireEmployee(String firstName, String secondName) {
		try {
			Element employee = getEmployee(filePath, secondName);
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
