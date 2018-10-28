package RPIS61.Vizgalov.wdad.learn.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import RPIS61.Vizgalov.wdad.learn.rmi.remote.XmlDataManager;
import RPIS61.Vizgalov.wdad.data.managers.PreferencesManager;
import RPIS61.Vizgalov.wdad.utils.PreferencesManagerConstants;

public class Client {
	private static XmlDataManager xmlDataManager;
	private static Registry registry;

	public static void main(String[] args) 
			throws MalformedURLException, RemoteException, NotBoundException {
	
		try {
			PreferencesManager preferencesManager = PreferencesManager.getInstance();

			int port = new Integer(preferencesManager.getProperty(
					PreferencesManagerConstants.REGISTRY_PORT));
			String address = preferencesManager.getProperty(
					PreferencesManagerConstants.REGISTRY_ADDRESS);
			String policy = preferencesManager.getProperty(
					PreferencesManagerConstants.POLICY_PATH);
			String codeBase = preferencesManager.getProperty(
					PreferencesManagerConstants.USE_CODE_BASE_ONLY);
			
			System.setProperty("java.security.policy", policy);
			System.setProperty("java.rmi.sever.useCodeBaseOnly", codeBase);

			registry = LocateRegistry.getRegistry(address, port);
			XmlDataManager xmlDataManager = (XmlDataManager) registry.lookup(
					"XmlDataManager");

			System.out.println(xmlDataManager.foo());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
