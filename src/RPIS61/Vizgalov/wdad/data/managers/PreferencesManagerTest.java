package RPIS61.Vizgalov.wdad.data.managers;

import RPIS61.Vizgalov.wdad.data.managers.PreferencesManagerTest;

public class PreferencesManagerTest {
	public static void main(String[] args) {
		PreferencesManager preferencesManager = PreferencesManager.getInstance();

		System.out.println(preferencesManager.get("createregistry"));
		System.out.println(preferencesManager.get("registryaddress"));
		System.out.println(preferencesManager.get("registryport"));
		System.out.println(preferencesManager.get("policypath"));
		System.out.println(preferencesManager.get("usecodebaseonly"));
		System.out.println(preferencesManager.get("classprovider"));

		preferencesManager.set("createregistry", "no");
		preferencesManager.set("registryaddress", "123.123.123.123");
		preferencesManager.set("registryport", "1123");
		preferencesManager.set("policypath", "server.policy");
		preferencesManager.set("usecodebaseonly", "yes");
		preferencesManager.set("classprovider", "http://www.site.com");
	}
}
