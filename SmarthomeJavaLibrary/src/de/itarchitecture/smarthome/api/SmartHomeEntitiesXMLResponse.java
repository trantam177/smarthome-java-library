package de.itarchitecture.smarthome.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.itarchitecture.smarthome.api.entities.SmartHomeLocation;
import de.itarchitecture.smarthome.api.entities.TemperatureHumidityDevice;
import de.itarchitecture.smarthome.api.entities.devices.GenericActuator;
import de.itarchitecture.smarthome.api.entities.devices.LogicalDevice;
import de.itarchitecture.smarthome.api.entities.devices.RoomHumiditySensor;
import de.itarchitecture.smarthome.api.entities.devices.RoomTemperatureActuator;
import de.itarchitecture.smarthome.api.entities.devices.RoomTemperatureSensor;
import de.itarchitecture.smarthome.api.entities.devices.SwitchActuator;
import de.itarchitecture.smarthome.api.entities.devices.WindowDoorSensor;

public class SmartHomeEntitiesXMLResponse extends XMLResponse {
	public ConcurrentHashMap<String, SmartHomeLocation> getLocations() {
		return locations;
	}

	public ConcurrentHashMap<String, SwitchActuator> getSwitchActuators() {
		return switchActuators;
	}

	private String currentConfigurationVersion = "";
	private String correspondingRequestId = "";
	private String responseStatus = "";
	private ConcurrentHashMap<String, SmartHomeLocation> locations = null;
	private ConcurrentHashMap<String, SwitchActuator> switchActuators = null;
	private ConcurrentHashMap<String, GenericActuator> genericActuators = null;
	private ConcurrentHashMap<String, RoomTemperatureSensor> roomTemperatureSensors = null;
	private ConcurrentHashMap<String, RoomHumiditySensor> roomHumiditySensors = null;
	private ConcurrentHashMap<String, RoomTemperatureActuator> roomTemperatureActuators = null;
	private ConcurrentHashMap<String, TemperatureHumidityDevice> temperatureHumidityDevices = null;
	private ConcurrentHashMap<String, WindowDoorSensor> windowDoorSensors = null;
	private ConcurrentHashMap<String, String> mapRoomsToTemperatureActuators = null;
	private ConcurrentHashMap<String, String> mapRoomsToTemperatureSensors = null;
	private ConcurrentHashMap<String, String> mapRoomsToHumiditySensors = null;


	public SmartHomeEntitiesXMLResponse(InputStream is) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parse using builder to get DOM representation of the XML file
			Document dom = db.parse(is);
			// get the root element
			Element docEle = dom.getDocumentElement();
			// get a nodelist of elements
			//Locations
			NodeList nlLocations = docEle.getElementsByTagName("LC");
			locations = new ConcurrentHashMap<String, SmartHomeLocation>(5);
			if (nlLocations != null && nlLocations.getLength() > 0) {
				for (int i = 0; i < nlLocations.getLength(); i++) {
					Element locEl = (Element) nlLocations.item(i);
					SmartHomeLocation shl = getLocation(locEl);
					locations.put(shl.getLocationId(), shl);
				}
			}
			//LogicalDevices
			NodeList nlLogicalDevices = docEle
					.getElementsByTagName("LD");
			switchActuators = new ConcurrentHashMap<String, SwitchActuator>();
			genericActuators = new ConcurrentHashMap<String, GenericActuator>();
			roomTemperatureActuators = new ConcurrentHashMap<String, RoomTemperatureActuator>();
			roomHumiditySensors = new ConcurrentHashMap<String, RoomHumiditySensor>();
			roomTemperatureSensors = new ConcurrentHashMap<String, RoomTemperatureSensor>();
			temperatureHumidityDevices = new ConcurrentHashMap<String, TemperatureHumidityDevice>();
			windowDoorSensors = new ConcurrentHashMap<String, WindowDoorSensor>();
			mapRoomsToTemperatureActuators = new ConcurrentHashMap<String, String>();
			mapRoomsToHumiditySensors = new ConcurrentHashMap<String, String>();
			mapRoomsToTemperatureSensors = new ConcurrentHashMap<String, String>();
			if (nlLogicalDevices != null && nlLogicalDevices.getLength() > 0) {
				for (int i = 0; i < nlLogicalDevices.getLength(); i++) {
					Element logDevEl = (Element) nlLogicalDevices.item(i);
					LogicalDevice logDev = getLogicalDevice(logDevEl);
					logDev.setLocation(locations.get(logDev.getLocationId()));
				}
			}

		} catch (SAXException ex) {
			Logger.getLogger(LogicalDeviceXMLResponse.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(LogicalDeviceXMLResponse.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (ParserConfigurationException ex) {
			Logger.getLogger(LogicalDeviceXMLResponse.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}

	/**
	 * @return the temperatureHumidityDevices
	 */
	public ConcurrentHashMap<String, TemperatureHumidityDevice> getTemperatureHumidityDevices() {
		return temperatureHumidityDevices;
	}

	private SmartHomeLocation getLocation(Element devEl) {

		// for each <SmartHomeLocation> element get text or int values
		SmartHomeLocation location = new SmartHomeLocation();
		location.setLocationId(getTextValueFromElements(devEl, "Id"));
		location.setName(getTextValueFromElements(devEl, "Name"));
		location.setPosition(getTextValueFromElements(devEl, "Position"));
		return location;
	}

	private LogicalDevice getLogicalDevice(Element devEl) {
		LogicalDevice logicalDevice = null;
		String sType = getTextValueFromAttribute(devEl, "xsi:type");
		if (LogicalDevice.Type_RoomHumiditySensor.equals(sType)) {
			RoomHumiditySensor roomHumiditySensor = new RoomHumiditySensor();
			roomHumiditySensor.setLogicalDeviceId(getTextValueFromElements(
					devEl, "Id"));
			roomHumiditySensor.setDeviceName(getTextValueFromAttribute(devEl,
					"Name"));
			roomHumiditySensor.setLocationId(getTextValueFromAttribute(devEl,
					"LCID"));

			NodeList underlyingDevNodes = devEl
					.getElementsByTagName("UDvIds");
			if (underlyingDevNodes != null
					&& underlyingDevNodes.getLength() > 0) {
				Element el = (Element) underlyingDevNodes.item(0);
				NodeList guidNodes = el.getElementsByTagName("guid");
				if (guidNodes != null && guidNodes.getLength() > 0) {
					for (int i = 0; i <= guidNodes.getLength(); i++) {
						// String guid = guidNodes.item(i).getNodeValue();
						mapRoomsToHumiditySensors.put(
								roomHumiditySensor.getLocationId(),
								roomHumiditySensor.getLogicalDeviceId());
					}

				}
			}

			// roomHumiditySensor.setHumidity(getDoubleValueFromElements(devEl,"Humidity"));
			logicalDevice = roomHumiditySensor;
			roomHumiditySensors.put(roomHumiditySensor.getDeviceId(),
					roomHumiditySensor);
			mapRoomsToHumiditySensors.put(roomHumiditySensor.getLocationId(),
					roomHumiditySensor.getDeviceId());
			TemperatureHumidityDevice tempHumDev = temperatureHumidityDevices
					.get(roomHumiditySensor.getLocationId());
			logicalDevice.setLocation(locations.get(logicalDevice
					.getLocationId()));
			if (null == tempHumDev) {
				tempHumDev = new TemperatureHumidityDevice();
				tempHumDev.setLocation(roomHumiditySensor.getLocation());
				temperatureHumidityDevices.put(tempHumDev.getLocationId(),
						tempHumDev);
			}
			tempHumDev.setRoomHumidtySensor(roomHumiditySensor);
		} else if (LogicalDevice.Type_WindowDoorSensor.equals(sType)) {
			WindowDoorSensor windowDoorSensor = new WindowDoorSensor();
			windowDoorSensor.setLogicalDeviceId(getTextValueFromElements(devEl,
					"Id"));
			windowDoorSensor.setDeviceName(getTextValueFromAttribute(devEl,
					"Name"));
			windowDoorSensor.setLocationId(getTextValueFromAttribute(devEl,
					"LCID"));
			windowDoorSensors.put(windowDoorSensor.getDeviceId(), windowDoorSensor);
			logicalDevice = windowDoorSensor;
		} else if (LogicalDevice.Type_RoomTemperatureActuator.equals(sType)) {
			RoomTemperatureActuator roomTemperatureActuator = new RoomTemperatureActuator();
			/*
			 * roomTemperatureActuator.setOperationMode(getTextValueFromElements(
			 * devEl, "OperationMode"));
			 */
			roomTemperatureActuator.setDeviceName(getTextValueFromAttribute(
					devEl, "Name"));
			roomTemperatureActuator
					.setPointTemperature(getDoubleValueFromElements(devEl,
							"PtTmp"));
			roomTemperatureActuator
					.setWindowReductionActive(getTextValueFromElements(devEl,
							"WndRd"));
			roomTemperatureActuator
					.setLogicalDeviceId(getTextValueFromElements(devEl, "Id"));
			roomTemperatureActuator.setLocationId(getTextValueFromAttribute(
					devEl, "LCID"));
			roomTemperatureActuator
					.setMaxTemperature(getDoubleValueFromElements(devEl,
							"MxTp"));
			roomTemperatureActuator
					.setMinTemperature(getDoubleValueFromElements(devEl,
							"MnTp"));
			roomTemperatureActuator
					.setPreheatFactor(getDoubleValueFromElements(devEl,
							"PhFct"));
			roomTemperatureActuator.setIsLocked(getBooleanValueFromElements(
					devEl, "Lckd"));
			roomTemperatureActuator
					.setIsFreezeProtectionActivated(getBooleanValueFromElements(
							devEl, "FPrA"));
			roomTemperatureActuator
					.setFreezeProtection(getDoubleValueFromElements(devEl,
							"FPr"));
			roomTemperatureActuator
					.setIsMoldProtectionActivated(getBooleanValueFromElements(
							devEl, "MPrA"));
			roomTemperatureActuator
					.setHumidityMoldProtection(getDoubleValueFromElements(
							devEl, "HMPr"));
			roomTemperatureActuator
					.setWindowsOpenTemperature(getDoubleValueFromElements(
							devEl, "WOpTp"));
			logicalDevice = roomTemperatureActuator;
			mapRoomsToTemperatureActuators.put(
					roomTemperatureActuator.getLocationId(),
					roomTemperatureActuator.getDeviceId());
			roomTemperatureActuators.put(roomTemperatureActuator.getDeviceId(),
					roomTemperatureActuator);
			TemperatureHumidityDevice tempHumDev = temperatureHumidityDevices
					.get(roomTemperatureActuator.getLocationId());
			logicalDevice.setLocation(locations.get(logicalDevice
					.getLocationId()));
			if (null == tempHumDev) {
				tempHumDev = new TemperatureHumidityDevice();
				tempHumDev.setLocation(roomTemperatureActuator.getLocation());
				temperatureHumidityDevices.put(tempHumDev.getLocationId(),
						tempHumDev);
			}
			tempHumDev.setTemperatureActuator(roomTemperatureActuator);
		} else if (LogicalDevice.Type_RoomTemperatureSensor.equals(sType)) {
			RoomTemperatureSensor roomTemperatureSensor = new RoomTemperatureSensor();
			roomTemperatureSensor.setLogicalDeviceId(getTextValueFromElements(
					devEl, "Id"));
			roomTemperatureSensor.setDeviceName(getTextValueFromAttribute(devEl,
					"Name"));
			roomTemperatureSensor.setLocationId(getTextValueFromAttribute(devEl,
					"LCID"));
			NodeList underlyingDevNodes = devEl
					.getElementsByTagName("UDvIds");
			roomTemperatureSensors.put(roomTemperatureSensor.getDeviceId(),
					roomTemperatureSensor);
			mapRoomsToTemperatureSensors.put(
					roomTemperatureSensor.getLocationId(),
					roomTemperatureSensor.getDeviceId());
			logicalDevice = roomTemperatureSensor;
			TemperatureHumidityDevice tempHumDev = temperatureHumidityDevices
					.get(roomTemperatureSensor.getLocationId());
			logicalDevice.setLocation(locations.get(logicalDevice
					.getLocationId()));
			if (null == tempHumDev) {
				tempHumDev = new TemperatureHumidityDevice();
				tempHumDev.setLocation(roomTemperatureSensor.getLocation());
				temperatureHumidityDevices.put(tempHumDev.getLocationId(),
						tempHumDev);
			}
			tempHumDev.setTemperatureSensor(roomTemperatureSensor);
		} else if (LogicalDevice.Type_SwitchActuator.equals(sType)) {
			SwitchActuator switchActuator = new SwitchActuator();
			switchActuator
					.setLogicalDeviceType(LogicalDevice.Type_SwitchActuator);
			switchActuator.setLogicalDeviceId(getTextValueFromElements(devEl,
					"Id"));
			switchActuator.getDefaultOffSettings().setIsOn(
					getBooleanValueFromElements(devEl, "IsOn"));
			switchActuator
					.setDeviceName(getTextValueFromAttribute(devEl, "Name"));
			switchActuator.setLocationId(getTextValueFromAttribute(devEl,
					"LCID"));
			switchActuator.setBaseDeviceId(getTextValueFromElements(devEl,
					"BDId"));
			switchActuator.setActuatorClass(getTextValueFromElements(devEl,
					"ActCls"));
			switchActuators.put(switchActuator.getDeviceId(), switchActuator);
			logicalDevice = switchActuator;
		} else if (LogicalDevice.Type_GenericActuator.equals(sType)) {
			GenericActuator genericActuator = new GenericActuator();
			genericActuator
					.setLogicalDeviceType(LogicalDevice.Type_SwitchActuator);
			genericActuator.setLogicalDeviceId(getTextValueFromElements(devEl,
					"Id"));
			genericActuator.setDeviceName(getTextValueFromAttribute(devEl,
					"Name"));
			genericActuator.setLocationId(getTextValueFromAttribute(devEl,
					"LCID"));
			genericActuator.setBaseDeviceId(getTextValueFromElements(devEl,
					"BDId"));
			genericActuator.setActuatorClass(getTextValueFromElements(devEl,
					"ActCls"));
			genericActuators
					.put(genericActuator.getDeviceId(), genericActuator);
			logicalDevice = genericActuator;
		} else {
			logicalDevice = new LogicalDevice();
			logicalDevice.setLogicalDeviceType(LogicalDevice.Type_Generic);
		}

		logicalDevice.setLogicalDeviceId(getTextValueFromElements(devEl, "Id"));
		return logicalDevice;
	}

	/**
	 * @return the responseStatus
	 */
	public String getResponseStatus() {
		return responseStatus;
	}

	/**
	 * @return the roomTemperatureSensors
	 */
	public ConcurrentHashMap<String, RoomTemperatureSensor> getRoomTemperatureSensors() {
		return roomTemperatureSensors;
	}

	/**
	 * @return the roomHumiditySensors
	 */
	public ConcurrentHashMap<String, RoomHumiditySensor> getRoomHumiditySensors() {
		return roomHumiditySensors;
	}

	/**
	 * @return the mapRoomsToTemperatureActuators
	 */
	public ConcurrentHashMap<String, String> getMapRoomsToTemperatureActuators() {
		return mapRoomsToTemperatureActuators;
	}

	/**
	 * @return the mapRoomsToTemperatureSensors
	 */
	public ConcurrentHashMap<String, String> getMapRoomsToTemperatureSensors() {
		return mapRoomsToTemperatureSensors;
	}

	/**
	 * @return the mapRoomsToHumidtySensors
	 */
	public ConcurrentHashMap<String, String> getMapRoomsToHumiditySensors() {
		return mapRoomsToHumiditySensors;
	}

	public ConcurrentHashMap<String, ? extends LogicalDevice> getRoomTemperatureActuators() {
		return roomTemperatureActuators;
	}

	public ConcurrentHashMap<String, ? extends LogicalDevice> getGenericActuators() {
		return genericActuators;
	}

	public ConcurrentHashMap<String, ? extends LogicalDevice> getWindowDoorSensors() {
		return windowDoorSensors;
	}

}
