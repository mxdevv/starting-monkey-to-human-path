package RPIS61.Vizgalov.wdad.data.managers;

import java.io.File;
import java.io.IOException;

import java.util.Properties;
import java.util.Set;
import java.util.Queue;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import org.xml.sax.SAXException;

import RPIS61.Vizgalov.wdad.utils.PathConstants;

public class PreferencesManager {
	static PreferencesManager instance;
	Document document;
	DocumentBuilder documentBuilder;
	final String filePath = PathConstants.APPCONFIG;

	private PreferencesManager() {
		try {
			this.documentBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			this.document = documentBuilder.parse(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static PreferencesManager getInstance() {
		if (instance == null) {
			instance = new PreferencesManager();
		}
		return instance;
	}

	public void setProperty(String key, String value) {
		String[] nameNodes = key.split("\\.");
		
		Node node = document.getDocumentElement();
		if (!node.getNodeName().equals(nameNodes[0]))
			return;

	cont:
	 	for(int i = 1; i < nameNodes.length; i++) {	
			NodeList nodeList = node.getChildNodes();
			for(int j = 0; j < nodeList.getLength(); j++) {
				if (nodeList.item(j).getNodeName().equals(nameNodes[i])) {
					node = nodeList.item(j);
					continue cont;
				}
			}
			return;
		}
		node.getFirstChild().setNodeValue(value);
		writeXml();
	}

	public String getProperty(String key) {
		String[] nameNodes = key.split("\\.");
		
		Node node = document.getDocumentElement();
		if (!node.getNodeName().equals(nameNodes[0]))
			return null;

	cont:
	 	for(int i = 1; i < nameNodes.length; i++) {	
			NodeList nodeList = node.getChildNodes();
			for(int j = 0; j < nodeList.getLength(); j++) {
				if (nodeList.item(j).getNodeName().equals(nameNodes[i])) {
					node = nodeList.item(j);
					continue cont;
				}
			}
			return null;
		}
		return node.getTextContent();
	}

	public void setProperties(Properties prop) {
		Set<String> keys = prop.stringPropertyNames();
		for(String key : keys)
			setProperty(key, prop.getProperty(key));
	}

	public Properties getProperties() {
		Node node = document.getDocumentElement();
		String name;
		NodeList nodeList;
		Properties prop = new Properties();

		Queue<Node> qNodes = new LinkedList<>();
		Queue<String> qNames = new LinkedList<>();
		qNodes.offer(node);
		qNames.offer(node.getNodeName());
		while(qNodes.size() > 0) {
			node = qNodes.poll();
			name = qNames.poll();
			if (node.getFirstChild() != null
					&& node.getFirstChild().getNextSibling() == null)
				prop.setProperty(name, node.getFirstChild().getNodeValue());
			nodeList = node.getChildNodes();
			for(int i = 0; i < nodeList.getLength(); i++) {
				qNodes.offer(nodeList.item(i));
				qNames.offer(name + "." + nodeList.item(i).getNodeName());
			}
		}
		return prop;
	}

	public void addBindedObject(String name, String className) {
		Element element = document.createElement("bindedobject");
		element.setAttribute("name", name);
		element.setAttribute("class", className);
		document.getElementsByTagName("server").item(0).appendChild(element);
	}

	public void removeBindedObject(String name) {
		NodeList nodeList = document.getElementsByTagName("bindedobject");
		for(int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element)nodeList.item(i);
			if (element.getAttribute("name").equals(name)) {
				element.getParentNode().removeChild(element);
			}
		}
		writeXml();
	}

	@Deprecated
	public void setCreateRegistry(String createRegistry) {
		document.getElementsByTagName("createregistry")
				.item(0).setTextContent(createRegistry);
		writeXml();
	}

	@Deprecated
	public void setRegistryAddress(String registryAddress) {
		document.getElementsByTagName("registryaddress")
				.item(0).setTextContent(registryAddress);
		writeXml();
	}

	@Deprecated
	public void setRegistryPort(String registryPort) {
		document.getElementsByTagName("registryport").item(0)
				.setTextContent(registryPort);
		writeXml();
	}

	@Deprecated
	public void setPolicyPath(String policyPath) {
		document.getElementsByTagName("policypath")
				.item(0).setTextContent(policyPath);
		writeXml();
	}

	@Deprecated
	public void setUseCodeBaseOnly(String useCodeBaseOnly) {
		document.getElementsByTagName("usecodebaseonly")
				.item(0).setTextContent(useCodeBaseOnly);
		writeXml();
	}

	@Deprecated
	public void setClassProvider(String classProvider) {
		document.getElementsByTagName("classprovider")
				.item(0).setTextContent(classProvider);
		writeXml();
	}

	@Deprecated
	public String getCreateRegistry() {
		return document.getElementsByTagName("createregistry")
				.item(0).getTextContent();
	}

	@Deprecated
	public String getRegistryAddress() {
		return document.getElementsByTagName("registryaddress")
				.item(0).getTextContent();
	}

	@Deprecated
	public String getRegistryPort() {
		return document.getElementsByTagName("registryport")
				.item(0).getTextContent();
	}

	@Deprecated
	public String getPolicyPath() {
		return document.getElementsByTagName("policypath")
				.item(0).getTextContent();
	}

	@Deprecated
	public String getUseCodeBaseOnly() {
		return document.getElementsByTagName("usecodebaseonly")
				.item(0).getTextContent();
	}

	@Deprecated
	public String getClassProvider() {
		return document.getElementsByTagName("classprovider")
				.item(0).getTextContent();
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
