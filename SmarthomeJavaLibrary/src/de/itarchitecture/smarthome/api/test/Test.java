package de.itarchitecture.smarthome.api.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Map;

import de.itarchitecture.smarthome.api.SmartHomeSession;
import de.itarchitecture.smarthome.api.entities.devices.SmokeDetectorSensor;

public class Test {

	public static void main(String[] args) {
		SmartHomeSession smarthomeSession = new SmartHomeSession();
		try {
			File file = new File(
					"E:\\workspace\\smarthome-java-library\\SmarthomeJavaLibrary\\src\\test.xml");
			FileInputStream fis = null;

			fis = new FileInputStream(file);

			smarthomeSession.refreshConfigurationFromInputStream(fis);

			SmokeDetectorSensor switchActuator = (SmokeDetectorSensor) smarthomeSession
					.getLogicalDeviceByRoomNameAndDeviceName("FLUR",
							"Rauchmelder Flur");
			
			smarthomeSession.getDimmerActuators();
			smarthomeSession.getSmokeDetectorSensors();
			smarthomeSession.getPushButtonSensors();
			smarthomeSession.getDaySensor();

			smarthomeSession.getSMSActuator();
			smarthomeSession.getEMailActuator();
			
			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
