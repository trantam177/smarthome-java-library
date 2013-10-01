/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.itarchitecture.smarthome.api.entities.devices.AlarmActuator;
import de.itarchitecture.smarthome.api.entities.devices.BaseActuator;
import de.itarchitecture.smarthome.api.entities.devices.DaySensor;
import de.itarchitecture.smarthome.api.entities.devices.DimmerActuator;
import de.itarchitecture.smarthome.api.entities.devices.EMailActuator;
import de.itarchitecture.smarthome.api.entities.devices.GenericActuator;
import de.itarchitecture.smarthome.api.entities.devices.GenericSensor;
import de.itarchitecture.smarthome.api.entities.devices.LogicalDevice;
import de.itarchitecture.smarthome.api.entities.devices.RoomHumiditySensor;
import de.itarchitecture.smarthome.api.entities.devices.RoomTemperatureActuator;
import de.itarchitecture.smarthome.api.entities.devices.RoomTemperatureSensor;
import de.itarchitecture.smarthome.api.entities.devices.SMSActuator;
import de.itarchitecture.smarthome.api.entities.devices.SwitchActuator;
import de.itarchitecture.smarthome.api.entities.devices.WindowDoorSensor;

/**
 *
 * @author michael
 */
public class LogicalDeviceXMLResponse extends XMLResponse {

	private String currentConfigurationVersion = "";
	private String correspondingRequestId = "";
	private String responseStatus = "";
	public void refreshLogicalDevices(InputStream is, ConcurrentHashMap<String, ? extends LogicalDevice> logicalDevices)
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		if (null == logicalDevices)
			return;
		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			Document dom = db.parse(is);
			//get the root element
			Element docEle = dom.getDocumentElement();
			//get a nodelist of elements 
			NodeList nl = docEle.getElementsByTagName("LogicalDeviceState");
			if (nl != null && nl.getLength() > 0) {
				for (int i = 0; i < nl.getLength(); i++) {

					Element el = (Element) nl.item(i);
					String sId = getTextValueFromAttribute(el, "LID");
					if (logicalDevices.containsKey(sId))
					{
						LogicalDevice d = logicalDevices.get(sId);
						refreshLogicalDevice(el, d);
					}
				}
			}

		} catch (SAXException ex) {
			Logger.getLogger(LogicalDeviceXMLResponse.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(LogicalDeviceXMLResponse.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ParserConfigurationException ex) {
			Logger.getLogger(LogicalDeviceXMLResponse.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private LogicalDevice refreshLogicalDevice(Element devEl, LogicalDevice logicalDevice) {

		String sType = getTextValueFromAttribute(devEl, "xsi:type");
		if (LogicalDevice.Type_RoomHumiditySensorState.equals(sType)) {
			RoomHumiditySensor roomHumiditySensor = (RoomHumiditySensor) logicalDevice;
			roomHumiditySensor.setLogicalDeviceType(LogicalDevice.Type_RoomHumiditySensor);
			roomHumiditySensor.setHumidity(getDoubleValueFromAttribute(devEl,"Humidity"));
			logicalDevice = roomHumiditySensor;
		} else if (LogicalDevice.Type_RoomTemperatureActuatorState.equals(sType)) {
			RoomTemperatureActuator roomTemperatureActuator = (RoomTemperatureActuator) logicalDevice;
			roomTemperatureActuator.setLogicalDeviceType(LogicalDevice.Type_RoomTemperatureActuator);
			roomTemperatureActuator.setOperationMode(getTextValueFromAttribute(devEl, "OpnMd"));
			roomTemperatureActuator.setPointTemperature(getDoubleValueFromAttribute(devEl, "PtTmp"));
			roomTemperatureActuator.setWindowReductionActive(getTextValueFromAttribute(devEl, "WRAc"));
			logicalDevice = roomTemperatureActuator;
		} else if (LogicalDevice.Type_RoomTemperatureSensorState.equals(sType)) {
			RoomTemperatureSensor roomTemperatureSensor = (RoomTemperatureSensor) logicalDevice;
			roomTemperatureSensor.setLogicalDeviceType(LogicalDevice.Type_RoomTemperatureSensor);
			roomTemperatureSensor.setTemperature(getDoubleValueFromAttribute(devEl, "Temperature"));
			logicalDevice = roomTemperatureSensor;
		} else if (LogicalDevice.Type_SwitchActuatorState.equals(sType)) {
			SwitchActuator switchActuator = (SwitchActuator) logicalDevice;
			switchActuator.setLogicalDeviceType(LogicalDevice.Type_SwitchActuator);
			switchActuator.setIsOn(getBooleanValueFromAttribute(devEl, "IsOn"));
			//            switchActuator.setSensingBehavior(getBooleanValueFromAttribute(devEl, "SensingBehavior"));
			logicalDevice = switchActuator;
		} else if (LogicalDevice.Type_WindowDoorSensorState.equals(sType)) {
			WindowDoorSensor windowDoorSensor = (WindowDoorSensor) logicalDevice;
			windowDoorSensor.setLogicalDeviceType(LogicalDevice.Type_WindowDoorSensor);
			windowDoorSensor.setOpen(getBooleanValueFromElements(devEl, "IsOpen"));
			logicalDevice = windowDoorSensor;
		} else if (LogicalDevice.Type_AlarmActuator.equals(sType)) {
			AlarmActuator alarmActuator = (AlarmActuator) logicalDevice;
			alarmActuator.setLogicalDeviceType(LogicalDevice.Type_AlarmActuator);
			alarmActuator.setOn(getBooleanValueFromElements(devEl, "IsOn"));
			alarmActuator.setAlarmDuration(getIntValueFromElements(devEl, "AlarmDuration"));
			logicalDevice = alarmActuator;
		} else if (LogicalDevice.Type_DimmerActuator.equals(sType)) {
			DimmerActuator dimmerActuator = (DimmerActuator) logicalDevice;
			dimmerActuator.setLogicalDeviceType(LogicalDevice.Type_SmokeDetectorSensor);
			dimmerActuator.setMax(getIntValueFromElements(devEl, "TMxV"));
			dimmerActuator.setMin(getIntValueFromElements(devEl, "TMnV"));
			dimmerActuator.setDimLevel(getIntValueFromElements(devEl,"DmLvl"));
			logicalDevice = dimmerActuator;
			
		} else if (LogicalDevice.Type_GenericActuator.equals(sType)) {
				NodeList nodes = devEl.getElementsByTagName("Ppt");
				if (nodes.getLength()!=1) {
					// Puffern
					HashMap<String,String> cache = new HashMap<String,String>();
					for (int i=0; i<nodes.getLength();i++) {
						String name = getTextValueFromAttribute((Element)nodes.item(i), "Name");
						String value = getTextValueFromAttribute((Element)nodes.item(i), "Value");
						cache.put(name,value);
					}
					// Auswerten und Objekte anlegen
					if (cache.containsKey("EmailPeriod")) {
						// E-Mail Actuator
						EMailActuator emailActuator = new EMailActuator();
						emailActuator.setLogicalDeviceType(LogicalDevice.Type_EMailActuator);
						emailActuator.setEmailMaxPeriod(cache.get("EmailMaxPeriod"));
						emailActuator.setEmailPeriod(cache.get("EmailPeriod"));
						emailActuator.setEmailMessage("EmailMessage");
						// TODO kohlm: receivers
						logicalDevice = emailActuator;
					}
					if (cache.containsKey("SMSPeriod")) {
						// SMSActuator
						SMSActuator smsActuator = new SMSActuator();
						smsActuator.setLogicalDeviceType(LogicalDevice.Type_SMSActuator);
						smsActuator.setSMSMaxPeriod(cache.get("SMSMaxPeriod"));
						smsActuator.setSMSPeriod(cache.get("SMSPeriod"));
						smsActuator.setSMSMessage("SMSMessage");
						// TODO kohlm: receivers
						logicalDevice = smsActuator;
					}
					cache.clear();
					return logicalDevice;
				}
				GenericActuator genericActuator = new GenericActuator();
				genericActuator.setLogicalDeviceType(LogicalDevice.Type_GenericActuator);
				logicalDevice = genericActuator;
			} else if (LogicalDevice.Type_GenericSensor.equals(sType)) {
				NodeList nodes = devEl.getElementsByTagName("Ppt");
				if (nodes.getLength()!=1) {
					// Puffern
					HashMap<String,String> cache = new HashMap<String,String>();
					for (int i=0; i<nodes.getLength();i++) {
						String name = getTextValueFromAttribute((Element)nodes.item(i), "Name");
						String value = getTextValueFromAttribute((Element)nodes.item(i), "Value");
						cache.put(name,value);
					}
					// Auswerten und Objekte anlegen
					if (cache.containsKey("Latitude")) {
						DaySensor dayActuator = new DaySensor();
						dayActuator.setLatitude(cache.get("Latitude"));
						dayActuator.setLongitude(cache.get("Longitude"));
						logicalDevice = dayActuator;
					}
					cache.clear();
					return logicalDevice;
				}
				GenericSensor genericSensor = new GenericSensor();
				genericSensor.setLogicalDeviceType(LogicalDevice.Type_GenericSensor);
				logicalDevice = genericSensor;
			} else {
				Logger.getLogger(LogicalDeviceXMLResponse.class.getName()).log(Level.FINEST,"-1-----------new/unknown sensor/actuator state: "+sType);
			}

			return logicalDevice;
		}

		/**
		 * @return the currentConfigurationVersion
		 */
		public String getCurrentConfigurationVersion() {
			return currentConfigurationVersion;
		}

		/**
		 * @return the correspondingRequestId
		 */
		public String getCorrespondingRequestId() {
			return correspondingRequestId;
		}

		/**
		 * @return the responseStatus
		 */
		public String getResponseStatus() {
			return responseStatus;
		}

	}
