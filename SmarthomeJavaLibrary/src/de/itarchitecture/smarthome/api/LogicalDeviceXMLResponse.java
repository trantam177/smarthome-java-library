/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api;

import de.itarchitecture.smarthome.api.entities.devices.GenericActuator;
import de.itarchitecture.smarthome.api.entities.devices.LogicalDevice;
import de.itarchitecture.smarthome.api.entities.devices.RoomHumiditySensor;
import de.itarchitecture.smarthome.api.entities.devices.RoomTemperatureActuator;
import de.itarchitecture.smarthome.api.entities.devices.RoomTemperatureSensor;
import de.itarchitecture.smarthome.api.entities.devices.SwitchActuator;
import de.itarchitecture.smarthome.api.entities.devices.WindowDoorSensor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
            logicalDevice = switchActuator;
        } else if (LogicalDevice.Type_WindowDoorSensorState.equals(sType)) {
            WindowDoorSensor windowDoorSensor = (WindowDoorSensor) logicalDevice;
            windowDoorSensor.setLogicalDeviceType(LogicalDevice.Type_WindowDoorSensor);
            windowDoorSensor.setOpen(getBooleanValueFromElements(devEl, "IsOpen"));
            logicalDevice = windowDoorSensor;
        } else if (LogicalDevice.Type_GenericActuatorState.equals(sType)) {
            GenericActuator genericActuator = (GenericActuator) logicalDevice;
            genericActuator.setLogicalDeviceType(LogicalDevice.Type_GenericActuator);
            Element propertyEl = (Element) devEl.getElementsByTagName("Ppt").item(0);
            Boolean value = getBooleanValueFromAttribute(propertyEl, "Value");
            genericActuator.setIsOn(value);
            logicalDevice = genericActuator;
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
