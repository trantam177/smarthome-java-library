/**
 * 
 */
package de.itarchitecture.smarthome.api;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.itarchitecture.smarthome.api.SmartHomeSession;
import de.itarchitecture.smarthome.api.entities.SmartHomeLocation;
import de.itarchitecture.smarthome.api.entities.TemperatureHumidityDevice;
import de.itarchitecture.smarthome.api.entities.devices.BaseActuator;
import de.itarchitecture.smarthome.api.entities.devices.DimmerActuator;
import de.itarchitecture.smarthome.api.entities.devices.GenericActuator;
import de.itarchitecture.smarthome.api.entities.devices.LogicalDevice;
import de.itarchitecture.smarthome.api.entities.devices.RoomHumiditySensor;
import de.itarchitecture.smarthome.api.entities.devices.RoomTemperatureActuator;
import de.itarchitecture.smarthome.api.entities.devices.RoomTemperatureSensor;
import de.itarchitecture.smarthome.api.entities.devices.SwitchActuator;
import de.itarchitecture.smarthome.api.entities.devices.WindowDoorSensor;
import de.itarchitecture.smarthome.api.exceptions.LoginFailedException;
import de.itarchitecture.smarthome.api.exceptions.SHTechnicalException;
import de.itarchitecture.smarthome.api.exceptions.SmartHomeSessionExpiredException;

/**
 * @author michael, sammy98
 */
public class SmarthomeApiTest {
	String username = "username";
	String password = "password";
	String hostname = "192.168.1.124";
	SmartHomeSession smarthomeSession = new SmartHomeSession();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try {
			smarthomeSession.logon(username, password, hostname);
			smarthomeSession.refreshConfiguration();
		} catch (LoginFailedException | SmartHomeSessionExpiredException
				| SHTechnicalException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		smarthomeSession.destroy();
	}

//	@Test
//	public void internal() throws SmartHomeSessionExpiredException {
//		smarthomeSession.refreshLogicalDeviceState();
//	}
	
	@Test
	public void testDimmerActuator() throws SmartHomeSessionExpiredException {
		DimmerActuator dimmerActuator = (DimmerActuator) smarthomeSession
				.getLogicalDeviceByRoomNameAndDeviceName("WOHNZIMMER",
						"Dimmer-Unterputz");
		assertNotNull(dimmerActuator);
		int dmlvl = dimmerActuator.getDimLevel();
		try {
			smarthomeSession.switchDimmerState(
					dimmerActuator.getDeviceId(), 10);
			smarthomeSession.refreshLogicalDeviceState();
			dimmerActuator = (DimmerActuator) smarthomeSession
					.getLogicalDeviceByRoomNameAndDeviceName("WOHNZIMMER",
							"Dimmer-Unterputz");
			assertTrue(dimmerActuator.getDimLevel()!=dmlvl);
			
		} catch (SmartHomeSessionExpiredException e) {
			fail(e.getMessage());
		} finally {
			smarthomeSession.switchDimmerState(
					dimmerActuator.getDeviceId(), dmlvl);
		}
	}

	@Test
	public void getEMailActuator() {
		assertNotNull(smarthomeSession.getEmailActuator());
	}

	@Test
	public void getSMSActuator() {
		assertNotNull(smarthomeSession.getSmsActuator());
	}
	
	@Test
	public void getDaySensor() {
		assertNotNull(smarthomeSession.getDaySensor());
	}

	@Test
	public void getSmokeDetectorSensors() {
		assertTrue(smarthomeSession.getSmokeDetectorSensors().size()!=0);
	}

	@Test
	public void getPushButtonSensors() {
		assertTrue(smarthomeSession.getPushButtonSensors().size()!=0);
	}
	
	/**
	 * Test method for
	 * {@link de.itarchitecture.smarthome.api.SmartHomeSession#getWindowDoorSensors()}
	 * .
	 */
	@Test
	public void testGetWindowDoorSensors() {
		ConcurrentHashMap<String, ? extends LogicalDevice> windowDoorSensors = smarthomeSession
				.getWindowDoorSensors();
		assertTrue(windowDoorSensors.size() > 0);
		WindowDoorSensor windowDoorSensor = (WindowDoorSensor) windowDoorSensors
				.elements().nextElement();
		assertFalse("".equals(windowDoorSensor.getLogicalDeviceId()));
	}

	/**
	 * Test method for
	 * {@link de.itarchitecture.smarthome.api.SmartHomeSession#getLocations()}.
	 */
	@Test
	public void testGetLocations() {
		ConcurrentHashMap<String, SmartHomeLocation> locations = smarthomeSession
				.getLocations();
		assertTrue(locations.size() > 0);
		SmartHomeLocation location = locations.elements().nextElement();
		assertFalse("".equals(location.getLocationId()));
	}

	/**
	 * Test method for
	 * {@link de.itarchitecture.smarthome.api.SmartHomeSession#logon(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testLogon() {
		assertFalse("".equals(smarthomeSession.getSessionId()));
	}

	/**
	 * Test method for
	 * {@link de.itarchitecture.smarthome.api.SmartHomeSession#destroy()}.
	 */
	@Test
	public void testDestroy() {
		smarthomeSession.destroy();
		assertTrue("".equals(smarthomeSession.getSessionId()));
	}

	/**
	 * Test method for
	 * {@link de.itarchitecture.smarthome.api.SmartHomeSession#refreshLogicalDeviceState()}
	 * .
	 */
	@Test
	public void testRefreshLogicalDeviceState() {
		try {
			smarthomeSession.refreshLogicalDeviceState();
		} catch (SmartHomeSessionExpiredException e) {
			fail(e.getMessage());
		}
		assertTrue(true);
	}

	/**
	 * Test method for
	 * {@link de.itarchitecture.smarthome.api.SmartHomeSession#getTemperatureHumidityDevices()}
	 * .
	 */
	@Test
	public void testGetTemperatureHumidityDevices() {
		ConcurrentHashMap<String, TemperatureHumidityDevice> tempHumDevs = smarthomeSession
				.getTemperatureHumidityDevices();
		TemperatureHumidityDevice dev = tempHumDevs.elements().nextElement();
		try {
			smarthomeSession.refreshLogicalDeviceState();
			assertTrue(dev.getRoomHumidtySensor().getHumidity() > 0.0);
			assertTrue(dev.getTemperatureActuator().getPointTemperature() > 0.0);
		} catch (SmartHomeSessionExpiredException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link de.itarchitecture.smarthome.api.SmartHomeSession#getRoomTemperatureSensors()}
	 * .
	 */
	@Test
	public void testGetRoomTemperatureSensors() {
		ConcurrentHashMap<String, RoomTemperatureSensor> tempSensors = smarthomeSession
				.getRoomTemperatureSensors();
		try {
			smarthomeSession.refreshLogicalDeviceState();
			RoomTemperatureSensor tempSens = tempSensors.elements()
					.nextElement();
			assertFalse("".equals(tempSens.getDeviceName()));
			assertTrue(tempSens.getTemperature() > 0.0);
		} catch (SmartHomeSessionExpiredException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link de.itarchitecture.smarthome.api.SmartHomeSession#getRoomHumiditySensors()}
	 * .
	 * 
	 */
	@Test
	public void testGetRoomHumiditySensors() {
		try {
			smarthomeSession.refreshLogicalDeviceState();
			ConcurrentHashMap<String, RoomHumiditySensor> humSensors = smarthomeSession
					.getRoomHumiditySensors();
			RoomHumiditySensor humSens = humSensors.elements().nextElement();
			assertFalse("".equals(humSens.getDeviceName()));
			assertTrue(humSens.getHumidity() > 0.0);
		} catch (SmartHomeSessionExpiredException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link de.itarchitecture.smarthome.api.SmartHomeSession#refreshConfiguration()}
	 * .
	 */
	@Test
	public void testRefreshConfiguration() {
		try {
			smarthomeSession.refreshConfiguration();
		} catch (SmartHomeSessionExpiredException e) {
			fail(e.getMessage());
		}

	}

	/**
	 * Test method for
	 * {@link de.itarchitecture.smarthome.api.SmartHomeSession#refreshPhysicalDevicesState()}
	 * .
	 */
	@Test
	public void testRefreshPhysicalDevicesState() {
		try {
			smarthomeSession.refreshPhysicalDevicesState();
		} catch (SmartHomeSessionExpiredException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link de.itarchitecture.smarthome.api.SmartHomeSession#getSwitchActuators()}
	 * .
	 */
	@Test
	public void testGetSwitchActuators() {
		ConcurrentHashMap<String, ? extends LogicalDevice> switchActuators = smarthomeSession
				.getSwitchActuators();
		SwitchActuator switchAct = (SwitchActuator) switchActuators.elements()
				.nextElement();
		assertFalse("".equals(switchAct.getDeviceId()));
	}

	/**
	 * Test method for
	 * {@link de.itarchitecture.smarthome.api.SmartHomeSession#getGenericActuators()}
	 * .
	 */
	@Test
	public void testGetBaseActuators() {
		ConcurrentHashMap<String, ? extends LogicalDevice> genActuators = smarthomeSession
				.getGenericActuators();
		BaseActuator genAct = (BaseActuator) genActuators.elements()
				.nextElement();
		assertFalse("".equals(genAct.getDeviceId()));
	}

	/**
	 * Test method for
	 * {@link de.itarchitecture.smarthome.api.SmartHomeSession#getRoomTemperatureActuators()}
	 * .
	 */
	@Test
	public void testGetRoomTemperatureActuators() {
		ConcurrentHashMap<String, ? extends LogicalDevice> roomTempActuators = smarthomeSession
				.getRoomTemperatureActuators();
		RoomTemperatureActuator roomTempAct = (RoomTemperatureActuator) roomTempActuators
				.elements().nextElement();
		assertFalse("".equals(roomTempAct.getDeviceId()));
	}

	/**
	 * Test method for
	 * {@link de.itarchitecture.smarthome.api.SmartHomeSession#switchActuatorChangeState(java.lang.String, boolean)}
	 * .
	 */
	@Test
	public void testSwitchActuatorChangeState() {
		SwitchActuator switchActuator = (SwitchActuator) smarthomeSession
				.getLogicalDeviceByRoomNameAndDeviceName("KINDERZIMMER",
						"Nachtlampe");
		assertNotNull(switchActuator);
		boolean previousState = switchActuator.getIsOn();
		try {
			smarthomeSession.switchActuatorChangeState(
					switchActuator.getDeviceId(), !previousState);
			smarthomeSession.refreshLogicalDeviceState();
			switchActuator = (SwitchActuator) smarthomeSession
					.getLogicalDeviceByRoomNameAndDeviceName("KINDERZIMMER",
							"Nachtlampe");
			assertTrue(switchActuator.getIsOn().equals(!previousState));
			smarthomeSession.switchActuatorChangeState(
					switchActuator.getDeviceId(), previousState);
		} catch (SmartHomeSessionExpiredException e) {
			fail(e.getMessage());
		}

	}

	/**
	 * Test method for
	 * {@link de.itarchitecture.smarthome.api.SmartHomeSession#roomTemperatureActuatorChangeState(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testRoomTemperatureActuatorChangeState() {
		Double newValue = 16.0;
		Double previousValue;
		RoomTemperatureActuator roomTempActuator = (RoomTemperatureActuator) smarthomeSession
				.getLogicalDeviceByRoomNameAndDeviceName("STUDIO", "Heizung");
		try {
			smarthomeSession.refreshLogicalDeviceState();
			assertNotNull(roomTempActuator);
			previousValue = roomTempActuator.getPointTemperature();
			smarthomeSession.roomTemperatureActuatorChangeState(
					roomTempActuator.getDeviceId(), Double.toString(newValue));
			smarthomeSession.refreshLogicalDeviceState();
			roomTempActuator = (RoomTemperatureActuator) smarthomeSession
					.getLogicalDeviceByRoomNameAndDeviceName("STUDIO", "Heizung");
			assertTrue(roomTempActuator.getPointTemperature().equals(newValue));
			smarthomeSession.roomTemperatureActuatorChangeState(
					roomTempActuator.getDeviceId(),
					Double.toString(previousValue));
		} catch (SmartHomeSessionExpiredException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link de.itarchitecture.smarthome.api.SmartHomeSession#zustandsVariableChangeState(java.lang.String, boolean)}
	 * .
	 */
	@Test
	public void testZustandsVariableChangeStateStringBoolean() {
		GenericActuator genActuator = (GenericActuator) smarthomeSession
				.getLogicalDeviceByRoomNameAndDeviceName("ÜBERGREIFEND",
						"BinZuhause");
		assertNotNull(genActuator);
		try {
			smarthomeSession.refreshLogicalDeviceState();
			boolean previousState = genActuator.getIsOn();
			smarthomeSession.zustandsVariableChangeState(
					genActuator.getDeviceId(), !previousState);
			smarthomeSession.refreshLogicalDeviceState();
			genActuator = (GenericActuator) smarthomeSession
					.getLogicalDeviceByRoomNameAndDeviceName("ÜBERGREIFEND",
							"BinZuhause");
			assertTrue(genActuator.getIsOn().equals(!previousState));
			smarthomeSession.zustandsVariableChangeState(
					genActuator.getDeviceId(), previousState);
		} catch (SmartHomeSessionExpiredException e) {
			fail(e.getMessage());
		}
	}

}
