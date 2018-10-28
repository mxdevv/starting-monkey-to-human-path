package RPIS61.Vizgalov.wdad.learn.rmi.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import RPIS61.Vizgalov.wdad.data.managers.PreferencesManager;
import RPIS61.Vizgalov.wdad.learn.rmi.remote.XmlDataManagerImpl;

public class Server extends UnicastRemoteObject {
	PreferencesManager preferencesManager;

	protected Server() throws RemoteException {
		super();
		preferencesManager = new PreferencesManager();
	}

	public static void main(String[] args) {
		try {
			Naming.rebind("//localhost/MyServer", new XmlDataManagerImpl());            
			System.out.println("Server start");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
