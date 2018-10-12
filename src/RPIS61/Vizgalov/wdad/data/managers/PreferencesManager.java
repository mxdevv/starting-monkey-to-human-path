package RPIS61.Vizgalov.wdad.data.managers;

import java.io.File;
import java.io.IOException;

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
import org.xml.sax.SAXException;

public class PreferencesManager {
	static PreferencesManager instance;
	Document document;
	DocumentBuilder documentBuilder;
	final String fileName =  "../../resources/configuration/appconfig.xml";

	private PreferencesManager() {
		try {
			this.documentBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			this.document = documentBuilder.parse(fileName);
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

	public void set(String nodeName, String newValue) {
		try {
			document.getElementsByTagName(nodeName).item(0).setTextContent(newValue);
			writeXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String get(String nodeName) {
		try {
			return document.getElementsByTagName(nodeName).item(0).getTextContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*public void setCreateRegistry(String createRegistry) {
		document.getElementsByTagName("createregistry")
				.item(0).setTextContent(createRegistry);
		writeXml();
	}

	public void setRegistryAddress(String registryAddress) {
		document.getElementsByTagName("registryaddress")
				.item(0).setTextContent(registryAddress);
		writeXml();
	}

	public void setRegistryPort(String registryPort) {
		document.getElementsByTagName("registryport").item(0)
				.setTextContent(registryPort);
		writeXml();
	}

	public void setPolicyPath(String policyPath) {
		document.getElementsByTagName("policypath").
				.item(0).setTextContent(policyPath);
		writeXml();
	}

	public void setUseCodeBaseOnly(String useCodeBaseOnly) {
		document.getElementsByTagName("usecodebaseonly")
				.item(0).setTextContent(useCodeBaseOnly);
		writeXml();
	}

	public void setClassProvider(String classProvider) {
		document.getElementsByTagName("classprovider")
				.item(0).setTextContent(classProvider);
		writeXml();
	}

	public String getCreateRegistry() {
		return document.getElementsByTagName("createregistry")
				.item(0).getTextContent();
	}

	public String getRegistryAddress() {
		return document.getElementsByTagName("registryaddress")
				.item(0).getTextContent();
	}

	public String getRegistryPort() {
		return document.getElementsByTagName("registryport")
				.item(0).getTextContent();
	}

	public String getPolicyPath() {
		return document.getElementsByTagName("policypath")
				.item(0).getTextContent();
	}

	public String getUseCodeBaseOnly() {
		return document.getElementsByTagName("usecodebaseonly")
				.item(0).getTextContent();
	}

	public String getClassProvider() {
		return document.getElementsByTagName("classprovider")
				.item(0).getTextContent();
	}*/

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
