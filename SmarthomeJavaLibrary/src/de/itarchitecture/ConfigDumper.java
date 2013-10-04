package de.itarchitecture;

import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import de.itarchitecture.smarthome.api.SmartHomeSession;

public class ConfigDumper {

	public static void main(String[] args) {
		System.out.print("IP address: ");
		Scanner sc = new Scanner(System.in);
		String hostName = sc.nextLine();
		System.out.print("User: ");
		String userName = sc.nextLine();
		Console c=System.console();
	    String password = "";
		if (c==null) {
			System.out.print("Passwort: ");
 			password = sc.nextLine();
		} else {
			password=  new String(c.readPassword("Password: "));
	    }
		try {
			SmartHomeSession s = new SmartHomeSession();
			System.out.println("Logging in...");
			s.logon(userName, password, hostName);
			System.out.println("Logged in.");
			System.out.println("Getting configuration...");
			String configuration = s.refreshConfiguration();
			System.out.println("Configuration received");
			System.out.println("Writing to file...");
			File file = new File("config.xml");
			FileWriter writer = new FileWriter(file , false);
			writer.write(configuration);
			writer.flush();
			writer.close();
			System.out.println("Written to file 'config.xml'...");
			System.out.println("Getting device states...");
			String deviceState = s.refreshLogicalDeviceState();
			System.out.println("Device states received");
			System.out.println("Writing to file...");
			file = new File("states.xml");
			writer = new FileWriter(file ,false);
			writer.write(deviceState);
			writer.flush();
			writer.close();
			System.out.println("Written to file 'states.xml'...");
			System.out.println("Send both xml-files to hackground@googlemail.com");
		} catch (Exception ex) {
			System.out.println("Error during communication to shc");
			ex.printStackTrace();
		}
		sc.close();
	}

}
