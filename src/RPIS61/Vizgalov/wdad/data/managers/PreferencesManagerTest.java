package RPIS61.Vizgalov.wdad.data.managers;

import java.util.Properties;
import java.util.Set;

import RPIS61.Vizgalov.wdad.data.managers.PreferencesManager;

public class PreferencesManagerTest {
	public static void main(String[] args) {
		PreferencesManager preferencesManager = PreferencesManager.getInstance();

		//System.out.println(preferencesManager.getCreateRegistry());
		//System.out.println(preferencesManager.getRegistryAddress());
		//System.out.println(preferencesManager.getRegistryPort());
		//System.out.println(preferencesManager.getPolicyPath());
		//System.out.println(preferencesManager.getUseCodeBaseOnly());
		//System.out.println(preferencesManager.getClassProvider());

		//preferencesManager.setCreateRegistry("no");
		//preferencesManager.setRegistryAddress("123.123.123.123");
		//preferencesManager.setRegistryPort("1123");
		//preferencesManager.setPolicyPath("server.policy");
		//preferencesManager.setUseCodeBaseOnly("yes");
		//preferencesManager.setClassProvider("http://www.site.com");
	
		preferencesManager.setProperty(
				"appconfig.rmi.server.registry.registryport", "1060");
		
		System.out.println(preferencesManager.getProperty(
				"appconfig.rmi.server.registry.registryport"));

		Properties prop = new Properties();
		prop.setProperty(
				"appconfig.rmi.server.registry.createregistry", "no");
		prop.setProperty(
				"appconfig.rmi.server.registry.registryaddress", "100.100.100.100");
		prop.setProperty(
				"appconfig.rmi.server.registry.registryport", "1050");
		preferencesManager.setProperties(prop);

		// Work, but don't right
		prop = preferencesManager.getProperties();
		Set<String> keys = prop.stringPropertyNames();
		for(String key : keys)
			System.out.println(key + " = " + prop.getProperty(key));
	}
}
