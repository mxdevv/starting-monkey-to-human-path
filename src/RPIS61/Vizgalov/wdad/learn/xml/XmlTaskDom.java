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
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

public class XmlTaskDom {
	DocumentBuilder documentBuilder;
	Document document;
	final String fileName = "organization.xml";

	public XmlTaskDom() {
		try {
			documentBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			document = documentBuilder.parse(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int salaryAverage() {
		ArrayList<Integer> salaries = new ArrayList<Integer>();
		//todo employeesCounter + salaryTotal instead of list
		try {
			//todo use Element and his methods getElementByTagName("name"), getAttribute(), setAttribute()
			Node root = document.getDocumentElement(); // <organization>
			NodeList departments = root.getChildNodes();
			for(int i = 0; i < departments.getLength(); i++) {
				Node department = departments.item(i);
				NodeList employees = department.getChildNodes();
				for(int j = 0; j < employees.getLength(); j++) {
					Node employee = employees.item(j);
					NodeList employeeProperties = employee.getChildNodes();
					for(int k = 0; k < employeeProperties.getLength(); k++) {
						Node employeeProperty = employeeProperties.item(k);
						if (employeeProperty.getNodeName().equals("salary"))
							salaries.add(new Integer(employeeProperty.getTextContent()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		int average = 0;
		for(int i : salaries)
			average += i;
		return average / salaries.size();
	}
	
	public int salaryAverage(String departmentName) {
		ArrayList<Integer> salaries = new ArrayList<Integer>();
		//todo use Element and his methods getElementByTagName("name"), getAttribute(), setAttribute()
		try {
			Node root = document.getDocumentElement(); // <organization>
			NodeList departments = root.getChildNodes();
			for(int i = 0; i < departments.getLength(); i++) {
				Node department = departments.item(i);
				NamedNodeMap attributes = department.getAttributes();
				if (attributes != null) {
					Node attribute = attributes.getNamedItem("name");
					if (attribute != null
							&& attribute.getNodeValue().equals(departmentName)) {
						NodeList employees = department.getChildNodes();
						for(int j = 0; j < employees.getLength(); j++) {
							Node employee = employees.item(j);
							NodeList employeeProperties = employee.getChildNodes();
							for(int k = 0; k < employeeProperties.getLength(); k++) {
								Node employeeProperty = employeeProperties.item(k);
								if (employeeProperty.getNodeName().equals("salary")) {
									salaries.add(new Integer(employeeProperty
											.getTextContent()));
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		int average = 0;
		for(int i : salaries)
			average += i;
		return average / salaries.size();
	}

	//todo убери дублирование - сделай метод, возвращающий элемент - нужный employee
	//todo use Element and his methods getElementByTagName("name"), getAttribute(), setAttribute()
	public void setJobTitle(String firstName, String secondName,
			String newJobTitle) {
		try {
			Node root = document.getDocumentElement(); // <organization>
			NodeList departments = root.getChildNodes();
			for(int i = 0; i < departments.getLength(); i++) {
				Node department = departments.item(i);
				NodeList employees = department.getChildNodes();
				for(int j = 0; j < employees.getLength(); j++) {
					Node employee = employees.item(j);
					NamedNodeMap attributes = employee.getAttributes();
					if (attributes != null) {
						Node attrFirstname = attributes.getNamedItem("firstname");
						Node attrSecondname = attributes.getNamedItem("secondname");
						if (attrFirstname != null && attrSecondname != null
								&& attrFirstname.getNodeValue().equals(firstName)
								&& attrSecondname.getNodeValue().equals(secondName)) {
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
			Node root = document.getDocumentElement(); // <organization>
			NodeList departments = root.getChildNodes();
			for(int i = 0; i < departments.getLength(); i++) {
				Node department = departments.item(i);
				NodeList employees = department.getChildNodes();
				for(int j = 0; j < employees.getLength(); j++) {
					Node employee = employees.item(j);
					NamedNodeMap attributes = employee.getAttributes();
					if (attributes != null) {
						Node attrFirstname = attributes.getNamedItem("firstname");
						Node attrSecondname = attributes.getNamedItem("secondname");
						if (attrFirstname != null && attrSecondname != null
								&& attrFirstname.getNodeValue().equals(firstName)
								&& attrSecondname.getNodeValue().equals(secondName)) {
							NodeList employeeProperties = employee.getChildNodes();
							for(int k = 0; k < employeeProperties.getLength(); k++) {
								Node employeeProperty = employeeProperties.item(k);
								if (employeeProperty.getNodeName().equals("salary")) {
									employeeProperty.getFirstChild().setNodeValue(String.valueOf(newSalary));
								}
							}
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
	public void fireEmployee(String firstName, String secondName) {
		try {
			Node root = document.getDocumentElement(); // <organization>
			NodeList departments = root.getChildNodes();
			for(int i = 0; i < departments.getLength(); i++) {
				Node department = departments.item(i);
				NodeList employees = department.getChildNodes();
				for(int j = 0; j < employees.getLength(); j++) {
					Node employee = employees.item(j);
					NamedNodeMap attributes = employee.getAttributes();
					if (attributes != null) {
						Node attrFirstname = attributes.getNamedItem("firstname");
						Node attrSecondname = attributes.getNamedItem("secondname");
						if (attrFirstname != null && attrSecondname != null
								&& attrFirstname.getNodeValue().equals(firstName)
								&& attrSecondname.getNodeValue().equals(secondName)) {
							department.removeChild(employee);
						}
					}
				}
			}
			writeXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeXml() {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(fileName));
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
