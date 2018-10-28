package RPIS61.Vizgalov.wdad.learn.rmi.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import RPIS61.Vizgalov.wdad.utils.PreferencesManagerConstants;
import RPIS61.Vizgalov.wdad.data.managers.PreferencesManager;
import RPIS61.Vizgalov.wdad.learn.rmi.remote.XmlDataManagerImpl;

public class Server extends UnicastRemoteObject {
	static PreferencesManager preferencesManager;
	static String bindName = "XmlDataManager";

	protected Server() throws RemoteException {
		super();
	}

	public static void main(String[] args) {
		try {
			preferencesManager = PreferencesManager.getInstance();

			int port = new Integer(preferencesManager.getProperty(
					PreferencesManagerConstants.REGISTRY_PORT));
			String address = preferencesManager.getProperty(
					PreferencesManagerConstants.REGISTRY_ADDRESS);
			String policy = preferencesManager.getProperty(
					PreferencesManagerConstants.POLICY_PATH);
			String codeBase = preferencesManager.getProperty(
					PreferencesManagerConstants.USE_CODE_BASE_ONLY);
			String createRegistry = preferencesManager.getProperty(
					PreferencesManagerConstants.CREATE_REGISTRY);
			
			//System.setProperty("java.security.policy", policy);
			System.setProperty("java.rmi.sever.useCodeBaseOnly", codeBase);
			System.setProperty("java.rmi.hostname", address);

			Registry registry;
			if (createRegistry.equals("yes"))
				registry = LocateRegistry.createRegistry(port);
			else
				registry = LocateRegistry.getRegistry(port);

			XmlDataManagerImpl xmlDataManagerImpl = new XmlDataManagerImpl();
			preferencesManager.addBindedObject(bindName, "XmlDataManager");

			System.out.println("Server start");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void finalize() {
		preferencesManager.removeBindedObject(bindName);
	}
}
