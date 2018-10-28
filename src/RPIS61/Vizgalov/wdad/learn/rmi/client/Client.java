package RPIS61.Vizgalov.wdad.learn.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import RPIS61.Vizgalov.wdad.learn.rmi.remote.XmlDataManager;

public class Client {
	private static XmlDataManager xmlDataManager;

	public static void main(String[] args) 
			throws MalformedURLException, RemoteException, NotBoundException {
		xmlDataManager = (XmlDataManager) Naming.lookup("//localhost/MyServer");
		System.out.println(xmlDataManager.foo());
	}
}
