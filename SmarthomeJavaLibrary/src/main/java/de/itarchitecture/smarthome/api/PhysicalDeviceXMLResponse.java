/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api;

import java.io.IOException;
import java.io.InputStream;
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

import de.itarchitecture.smarthome.api.entities.devices.PhysicalDevice;

/**
 *
 * @author michael
 */
public class PhysicalDeviceXMLResponse extends XMLResponse {

    private String correspondingRequestId = "";
    private ConcurrentHashMap<String,PhysicalDevice> physicalDevices = null;

    public PhysicalDeviceXMLResponse(InputStream is) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
            Document dom = db.parse(is);
            //get the root element
            Element docEle = dom.getDocumentElement();
            //get a nodelist of elements 
            NodeList nl = docEle.getElementsByTagName("PhysicalDeviceState");
            physicalDevices = new ConcurrentHashMap <String,PhysicalDevice>();
            if (nl != null && nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {

                    //get the employee element
                    Element el = (Element) nl.item(i);

                    //get the Employee object
                    PhysicalDevice d = getPhysicalDevice(el);

                    //add it to list
                    physicalDevices.put(d.getDeviceId(),d);
                }
            }

        } catch (SAXException ex) {
            Logger.getLogger(PhysicalDeviceXMLResponse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PhysicalDeviceXMLResponse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(PhysicalDeviceXMLResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private PhysicalDevice getPhysicalDevice(Element devEl) {

        //for each <employee> element get text or int values of
        //name ,id, age and name
        PhysicalDevice physicalDevice = new PhysicalDevice();
        correspondingRequestId=getTextValueFromAttribute(devEl, "CorrespondingRequestId");
        physicalDevice.setPhysicalDeviceId(getTextValueFromAttribute(devEl, "PhysicalDeviceId"));
        physicalDevice.setDeviceConfigurationState(getTextValueFromAttribute(devEl, "DeviceConfigurationState"));
        physicalDevice.setDeviceInclusionState(getTextValueFromAttribute(devEl, "DeviceInclusionState"));
        physicalDevice.setFirmwareVersion(getTextValueFromAttribute(devEl, "FirmwareVersion="));
        physicalDevice.setIsReachable(getBooleanValueFromAttribute(devEl, "IsReachable"));
        physicalDevice.setUpdateState(getTextValueFromAttribute(devEl, "UpdateState"));
        //Create a new Employee with the value read from the xml nodes
        return physicalDevice;
    }
    /**
     * @return the correspondingRequestId
     */
    public String getCorrespondingRequestId() {
        return correspondingRequestId;
    }

    /**
     * @return the logicalDevices
     */
    public ConcurrentHashMap<String,PhysicalDevice> getPhysicalDevices() {
        return physicalDevices;
    }
}
