/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

//import android.util.Base64;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.xml.sax.SAXException;

import de.itarchitecture.smarthome.api.entities.SmartHomeLocation;
import de.itarchitecture.smarthome.api.entities.TemperatureHumidityDevice;
import de.itarchitecture.smarthome.api.entities.devices.DaySensor;
import de.itarchitecture.smarthome.api.entities.devices.EmailActuator;
import de.itarchitecture.smarthome.api.entities.devices.GenericActuator;
import de.itarchitecture.smarthome.api.entities.devices.LogicalDevice;
import de.itarchitecture.smarthome.api.entities.devices.PhysicalDevice;
import de.itarchitecture.smarthome.api.entities.devices.PushButtonSensor;
import de.itarchitecture.smarthome.api.entities.devices.RoomHumiditySensor;
import de.itarchitecture.smarthome.api.entities.devices.RoomTemperatureSensor;
import de.itarchitecture.smarthome.api.entities.devices.SmokeDetectorSensor;
import de.itarchitecture.smarthome.api.entities.devices.SmsActuator;
import de.itarchitecture.smarthome.api.entities.devices.SwitchActuator;
import de.itarchitecture.smarthome.api.exceptions.LoginFailedException;
import de.itarchitecture.smarthome.api.exceptions.SHTechnicalException;
import de.itarchitecture.smarthome.api.exceptions.SmartHomeSessionExpiredException;
import de.itarchitecture.util.net.HttpComponentsHelper;
import de.itarchitecture.util.string.InputStream2String;
import de.itarchitecture.util.string.XMLUtil;

/**
 *
 * @author michael
 */
public class SmartHomeSession implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -796412849725398896L;
	private String sessionId = "";
    private String userName = "";
    private String passWord = "";
    private String passWordEncrypted = "";
    private String hostName = "";
    private String clientId = "";
    private String requestId = "";
    private String currentConfigurationVersion = "";
    public ConcurrentHashMap<String, ? extends LogicalDevice> getWindowDoorSensors() {
		return windowDoorSensors;
	}

//	private ConcurrentHashMap<String,? extends LogicalDevice> logicalDevices = null;
    private ConcurrentHashMap<String,PhysicalDevice> physicalDevices = null;
    private ConcurrentHashMap<String,SmartHomeLocation> locations = null;
    public ConcurrentHashMap<String, SmartHomeLocation> getLocations() {
		return locations;
	}

	private ConcurrentHashMap<String,? extends LogicalDevice> switchActuators = null;
	private ConcurrentHashMap<String,? extends LogicalDevice> dimmerActuators = null;
	private ConcurrentHashMap<String,? extends LogicalDevice> baseActuators = null;
	private ConcurrentHashMap<String,? extends LogicalDevice> baseSensors = null;
	private ConcurrentHashMap<String,? extends LogicalDevice> roomTemperatureActuators = null;
    HttpComponentsHelper httpHelper = new HttpComponentsHelper();
	private ConcurrentHashMap<String, SmokeDetectorSensor> smokeDetectorSensors;
	private ConcurrentHashMap<String, PushButtonSensor> pushButtonSensors;
	private ConcurrentHashMap<String, RoomTemperatureSensor> roomTemperatureSensors;
	private ConcurrentHashMap<String, RoomHumiditySensor> roomHumiditySensors;
	private ConcurrentHashMap<String, TemperatureHumidityDevice> temperatureHumidityDevices;
	private ConcurrentHashMap<String,? extends LogicalDevice> windowDoorSensors = null;
	private ConcurrentHashMap<String, String> mapRoomsToHumiditySensors;
	private ConcurrentHashMap<String, String> mapRoomsToTemperatureActuator;
	private ConcurrentHashMap<String, String> mapRoomsToTemperatureSensors;

    public static void main(String[] args) {
    }

    public SmartHomeSession() {
    }
    public void logon(String userName, String passWord, String hostName) throws SHTechnicalException, LoginFailedException, SmartHomeSessionExpiredException
    {
        this.userName = userName;
        this.passWord = passWord;
        this.hostName = hostName;
        initialize();
    }
    public void initialize() throws SHTechnicalException, LoginFailedException, SmartHomeSessionExpiredException {
        clientId = UUID.randomUUID().toString();
        requestId = generateRequestId();
        passWordEncrypted = generateHashFromPassword(passWord);
        String sResponse = "";
        String loginRequest = "<BaseRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"LoginRequest\" Version=\"1.60\" RequestId=\"" + requestId + "\" UserName=\"" + getUserName() + "\" Password=\"" + passWordEncrypted + "\" />";
        try {

            sResponse = executeRequest(loginRequest, "/cmd");
            sessionId = XMLUtil.XPathValueFromString(sResponse, "/BaseResponse/@SessionId");
            if (sessionId == null || "".equals(sessionId))
            	throw new LoginFailedException("LoginFailed: Authentication with user:" + userName + " was not possible. Session ID is empty.");
            currentConfigurationVersion = XMLUtil.XPathValueFromString(sResponse, "/BaseResponse/@CurrentConfigurationVersion");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SmartHomeSession.class.getName()).log(Level.SEVERE, null, ex);
            throw new SHTechnicalException("ParserConfigurationException:" + ex.getMessage(), ex);
        } catch (SAXException ex) {
            Logger.getLogger(SmartHomeSession.class.getName()).log(Level.SEVERE, null, ex);
            throw new SHTechnicalException("SAXException:" + ex.getMessage(), ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(SmartHomeSession.class.getName()).log(Level.SEVERE, null, ex);
            throw new SHTechnicalException("XPathExpressionException:" + ex.getMessage(), ex);
        } catch (IOException ex) {
            Logger.getLogger(SmartHomeSession.class.getName()).log(Level.SEVERE, null, ex);
            throw new SHTechnicalException("IOException. Communication with host " + hostName + " was not possiblte or interrupted. " + ex.getMessage(), ex);
        }
    }

    public void destroy() {
        String logoutrequest = "";
        logoutrequest = "<BaseRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"LogoutRequest\" Version=\"1.60\" RequestId=\"" + requestId + "\" SessionId=\"" + getSessionId() + "\" />";
        try {
//			String sResponse = 
			executeRequest(logoutrequest, "/cmd");
		} catch (SmartHomeSessionExpiredException e) {
			// Ignore expired session for logout
		}
        sessionId = "";

    }

    public String refreshLogicalDeviceState() throws SmartHomeSessionExpiredException {
        String getLogicalDevicesRequest;
        String sResponse = "";
        getLogicalDevicesRequest = "<BaseRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"GetAllLogicalDeviceStatesRequest\" Version=\"1.60\" RequestId=\"" + requestId + "\" SessionId=\"" + this.getSessionId() + "\" BasedOnConfigVersion=\"" + currentConfigurationVersion + "\" />";
        sResponse = executeRequest(getLogicalDevicesRequest, "/cmd");
		Logger.getLogger(SmartHomeSession.class.getName()).log(Level.INFO,sResponse);
        LogicalDeviceXMLResponse logDevXmlRes = new LogicalDeviceXMLResponse();
        logDevXmlRes.refreshLogicalDevices(IOUtils.toInputStream(sResponse), switchActuators);
        logDevXmlRes.refreshLogicalDevices(IOUtils.toInputStream(sResponse), smokeDetectorSensors);
        logDevXmlRes.refreshLogicalDevices(IOUtils.toInputStream(sResponse), pushButtonSensors);
        logDevXmlRes.refreshLogicalDevices(IOUtils.toInputStream(sResponse), dimmerActuators);
        logDevXmlRes.refreshLogicalDevices(IOUtils.toInputStream(sResponse), roomTemperatureActuators);
        logDevXmlRes.refreshLogicalDevices(IOUtils.toInputStream(sResponse), roomTemperatureSensors);
        logDevXmlRes.refreshLogicalDevices(IOUtils.toInputStream(sResponse), roomHumiditySensors);
        logDevXmlRes.refreshLogicalDevices(IOUtils.toInputStream(sResponse), windowDoorSensors);
        logDevXmlRes.refreshLogicalDevices(IOUtils.toInputStream(sResponse), baseActuators);
        logDevXmlRes.refreshLogicalDevices(IOUtils.toInputStream(sResponse), baseSensors);
        return sResponse;
    }
    
    public void refreshConfigurationFromInputStream(InputStream is){
    SmartHomeEntitiesXMLResponse smartHomeEntitiesXMLRes = new SmartHomeEntitiesXMLResponse(is);
    this.switchActuators = smartHomeEntitiesXMLRes.getSwitchActuators();
    this.dimmerActuators = smartHomeEntitiesXMLRes.getDimmerActuators();
    this.locations = smartHomeEntitiesXMLRes.getLocations();
    this.baseActuators = smartHomeEntitiesXMLRes.getBaseActuators(); 
    this.baseSensors = smartHomeEntitiesXMLRes.getBaseSensors(); 
    this.roomTemperatureActuators = smartHomeEntitiesXMLRes.getRoomTemperatureActuators();
    this.roomTemperatureSensors  = smartHomeEntitiesXMLRes.getRoomTemperatureSensors();
    this.smokeDetectorSensors = smartHomeEntitiesXMLRes.getSmokeDetectorSensors();
    this.pushButtonSensors = smartHomeEntitiesXMLRes.getPushButtonSensors();
    this.roomHumiditySensors  = smartHomeEntitiesXMLRes.getRoomHumiditySensors();
    this.mapRoomsToHumiditySensors = smartHomeEntitiesXMLRes.getMapRoomsToHumiditySensors();
    this.mapRoomsToTemperatureActuator = smartHomeEntitiesXMLRes.getMapRoomsToTemperatureActuators();
    this.mapRoomsToTemperatureSensors = smartHomeEntitiesXMLRes.getMapRoomsToTemperatureSensors();
    this.temperatureHumidityDevices = smartHomeEntitiesXMLRes.getTemperatureHumidityDevices();
    this.windowDoorSensors = smartHomeEntitiesXMLRes.getWindowDoorSensors();
    }

	/**
	 * @return the smokeDetectorSensors
	 */
	public ConcurrentHashMap<String, SmokeDetectorSensor> getSmokeDetectorSensors() {
		return smokeDetectorSensors;
	}

	/**
	 * @return the smokeDetectorSensors
	 */
	public ConcurrentHashMap<String, PushButtonSensor> getPushButtonSensors() {
		return pushButtonSensors;
	}
	
	/**
	 * @return the temperatureHumidityDevices
	 */
	public ConcurrentHashMap<String, TemperatureHumidityDevice> getTemperatureHumidityDevices() {
		return temperatureHumidityDevices;
	}
	/**
	 * @return the mapRoomsToHumiditySensors
	 */
	public ConcurrentHashMap<String, String> getMapRoomsToHumiditySensors() {
		return mapRoomsToHumiditySensors;
	}

	/**
	 * @return the mapRoomsToTemperatureActuator
	 */
	public ConcurrentHashMap<String, String> getMapRoomsToTemperatureActuator() {
		return mapRoomsToTemperatureActuator;
	}

	/**
	 * @return the mapRoomsToTemperatureSensors
	 */
	public ConcurrentHashMap<String, String> getMapRoomsToTemperatureSensors() {
		return mapRoomsToTemperatureSensors;
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

	public String refreshConfiguration() throws SmartHomeSessionExpiredException{
    	
        String getConfigurationRequest;
        String sResponse = "";
        getConfigurationRequest = "<BaseRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"GetEntitiesRequest\" Version=\"1.60\" RequestId=\"" + requestId + "\" SessionId=\""+ sessionId + "\">\n" + "<EntityType>Configuration</EntityType></BaseRequest>";
        sResponse = executeRequest(getConfigurationRequest, "/cmd");
		Logger.getLogger(SmartHomeSession.class.getName()).log(Level.SEVERE,sResponse);
        try {
			refreshConfigurationFromInputStream(IOUtils.toInputStream(sResponse,"UTF8"));
		} catch (IOException e) {
			throw new SmartHomeSessionExpiredException(e);
		}
        return sResponse;
    }

    public ConcurrentHashMap<String,? extends PhysicalDevice> refreshPhysicalDevicesState() throws SmartHomeSessionExpiredException {
        String getPhysicalDevicesRequest;
        String sResponse = "";
        getPhysicalDevicesRequest = "<BaseRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"GetAllPhysicalDeviceStatesRequest\" Version=\"1.60\" RequestId=\"" + requestId + "\" SessionId=\"" + getSessionId() + "\" />";
        sResponse = executeRequest(getPhysicalDevicesRequest, "/cmd");
        PhysicalDeviceXMLResponse physDevXmlRes = new PhysicalDeviceXMLResponse(IOUtils.toInputStream(sResponse));
        physicalDevices = physDevXmlRes.getPhysicalDevices();
        return physicalDevices;
    }
    public ConcurrentHashMap<String,? extends LogicalDevice> getSwitchActuators(){
        return this.switchActuators;
    }
    public ConcurrentHashMap<String,? extends LogicalDevice> getDimmerActuators(){
        return this.dimmerActuators;
    }
    public ConcurrentHashMap<String, ? extends LogicalDevice> getGenericActuators() {
		return baseActuators;
	}

	public ConcurrentHashMap<String,? extends LogicalDevice> getRoomTemperatureActuators(){
        return this.roomTemperatureActuators;
    }
    public void getNotifications() throws SmartHomeSessionExpiredException {
        String getNotificationsRequest;
//        String sResponse = "";
        getNotificationsRequest = "";
//        sResponse = 
		executeRequest(getNotificationsRequest, "/upd");
    }

    public void switchActuatorChangeState(String deviceId, boolean bOn) throws SmartHomeSessionExpiredException {
        String switchOnRequest;
//        String sResponse = "";
        switchOnRequest = "<BaseRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"SetActuatorStatesRequest\" Version=\"1.60\" RequestId=\"" + requestId + "\" SessionId=\"" + getSessionId() + "\" BasedOnConfigVersion=\"" + currentConfigurationVersion + "\"><ActuatorStates><LogicalDeviceState xsi:type=\"SwitchActuatorState\" LID=\"" + deviceId + "\" IsOn=\"" + bOn + "\" /></ActuatorStates></BaseRequest>";
        Logger.getLogger(SmartHomeSession.class.getName()).log(Level.FINE, "Switching: " + switchOnRequest);
//        sResponse = 
        executeRequest(switchOnRequest, "/cmd");
    }
    
    public void switchDimmerState(String deviceId, int currentValue) throws SmartHomeSessionExpiredException {
        String switchOnRequest = "<BaseRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"SetActuatorStatesRequest\" Version=\"1.60\" RequestId=\"" + requestId + "\" SessionId=\"" + getSessionId() + "\" BasedOnConfigVersion=\"" + currentConfigurationVersion + "\"><ActuatorStates><LogicalDeviceState xsi:type=\"DimmerActuatorState\" LID=\"" + deviceId + "\" DmLvl=\"" + currentValue + "\" /></ActuatorStates></BaseRequest>";
        Logger.getLogger(SmartHomeSession.class.getName()).log(Level.FINE, "Set dimmer state: " + switchOnRequest);
        String sResponse = executeRequest(switchOnRequest, "/cmd");
        Logger.getLogger(SmartHomeSession.class.getName()).log(Level.INFO, "Response: " + sResponse);
    }
    
    public void roomTemperatureActuatorChangeState(String deviceId, String temperature) throws SmartHomeSessionExpiredException {
        String temperatureChangeRequest;
//        String sResponse = "";
        temperatureChangeRequest = "<BaseRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"SetActuatorStatesRequest\" Version=\"1.60\" RequestId=\"" + requestId + "\" SessionId=\"" + getSessionId() + "\" BasedOnConfigVersion=\"" + currentConfigurationVersion + "\"><ActuatorStates><LogicalDeviceState xsi:type=\"RoomTemperatureActuatorState\" LID=\"" + deviceId + "\" PtTmp=\"" + temperature + "\" OpnMd=\"" + "Auto" + "\" WRAc=\"False\" /></ActuatorStates></BaseRequest>";
        Logger.getLogger(SmartHomeSession.class.getName()).log(Level.FINE, "ChangingTemperature: " + temperatureChangeRequest);
//        sResponse = 
        executeRequest(temperatureChangeRequest, "/cmd");
        
    }
    public void switchState(SwitchActuator switchActuator, boolean bOn) throws SmartHomeSessionExpiredException {
        switchActuatorChangeState(switchActuator.getLogicalDeviceId(),bOn);
    }

    private String executeRequest(String loginRequest, String sCmd) throws SmartHomeSessionExpiredException {
        String sReturn = "";
        HttpClient httpclient = httpHelper.getNewHttpClient();
        HttpPost httpPost = new HttpPost("https://" + getHostName() + sCmd);
        try {
            httpPost = new HttpPost("https://" + getHostName() + "/cmd");
            httpPost.addHeader("ClientId", clientId);
            httpPost.addHeader("Connection", "Keep-Alive");
            HttpResponse response1;
            StringEntity se = new StringEntity(loginRequest, HTTP.UTF_8);
            se.setContentType("text/xml");
            httpPost.setEntity(se);
            response1 = httpclient.execute(httpPost);
            HttpEntity entity1 = response1.getEntity();
            InputStream in = entity1.getContent();
            sReturn = InputStream2String.copyFromInputStream(in, "UTF-8");
            if (sReturn.contains("IllegalSessionId"))
            	throw new SmartHomeSessionExpiredException(sReturn);
            Logger
                    .getLogger(SmartHomeSession.class
                    .getName()).log(Level.FINE, "XMLResponse:{0}", sReturn);
            //EntityUtils.consume(entity1);
        } catch (ClientProtocolException ex) {
            Logger.getLogger(SmartHomeSession.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SmartHomeSession.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            //httpPost.releaseConnection();
        }
        return sReturn;

    }

    private String generateRequestId() {
        return UUID.randomUUID().toString();
    }

    private String generateHashFromPassword(String plainPassword) {
        String sReturn = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(plainPassword.getBytes());

            byte byteData[] = md.digest();
            //sReturn = new BASE64Encoder().encode(byteData);
            //sReturn = Base64.encodeToString(byteData, Base64.DEFAULT);
            sReturn = new String(Base64.encodeBase64(byteData));

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SmartHomeSession.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return sReturn;
    }

    /**
     * Gets the session id.
     *
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Gets the user name.
     *
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the host name.
     *
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }

	public void zustandsVariableChangeState(String deviceId, boolean bIsOn) throws SmartHomeSessionExpiredException {
//        String sResponse = "";
        String switchOnRequest = "<BaseRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"SetActuatorStatesRequest\" Version=\"1.60\" RequestId=\"" + requestId + "\" SessionId=\"" + getSessionId() + "\" BasedOnConfigVersion=\"" + currentConfigurationVersion + "\"><ActuatorStates><LogicalDeviceState xsi:type=\"GenericDeviceState\" LID=\"" + deviceId + "\"><Ppts><Ppt xsi:type=\"BooleanProperty\" Name=\"Value\" Value=\"" + bIsOn + "\" /></Ppts></LogicalDeviceState></ActuatorStates></BaseRequest>";
        Logger.getLogger(SmartHomeSession.class.getName()).log(Level.FINE, "Switching: " + switchOnRequest);
//        sResponse = 
		executeRequest(switchOnRequest, "/cmd");		
	}

	public void zustandsVariableChangeState(GenericActuator genericActuator,
			boolean newState) throws SmartHomeSessionExpiredException {
		zustandsVariableChangeState(genericActuator.getDeviceId(), newState);
	}

	public LogicalDevice getLogicalDeviceByRoomNameAndDeviceName(String roomName, String deviceName) {
		LogicalDevice foundLogDev = getLogicalDeviceByRoomNameAndDeviceName(roomName, deviceName, switchActuators);
		if (foundLogDev != null) {
			return foundLogDev;
		}
		foundLogDev = getLogicalDeviceByRoomNameAndDeviceName(roomName, deviceName, baseActuators);
		if (foundLogDev != null) {
			return foundLogDev;
		}
		foundLogDev = getLogicalDeviceByRoomNameAndDeviceName(roomName, deviceName, this.roomTemperatureActuators);
		if (foundLogDev != null) {
			return foundLogDev;
		}
		foundLogDev = getLogicalDeviceByRoomNameAndDeviceName(roomName, deviceName, this.smokeDetectorSensors);
		if (foundLogDev != null) {
			return foundLogDev;
		}
		foundLogDev = getLogicalDeviceByRoomNameAndDeviceName(roomName, deviceName, this.dimmerActuators);
		return foundLogDev;
	}
	
	private LogicalDevice getLogicalDeviceByRoomNameAndDeviceName(String roomName, String deviceName, ConcurrentHashMap<String,? extends LogicalDevice> logicalDevices) {
		LogicalDevice foundLogDev = null;
		for (Iterator iterator = logicalDevices.values().iterator(); iterator.hasNext();) {
			LogicalDevice dev = (LogicalDevice) iterator.next();
			if (roomName.equals(dev.getLocation().getName()) && deviceName.equals(dev.getDeviceName()))
			{
				foundLogDev = dev;
				return foundLogDev;
			}
		}
		return foundLogDev;
	}
	
	/**
	 * Gets the sMS actuator.
	 *
	 * @return the sMS actuator
	 */
	public SmsActuator getSmsActuator() {
		
		Iterator it = baseActuators.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        if(baseActuators.get(pairs.getKey()) instanceof SmsActuator) {
	        	return (SmsActuator) baseActuators.get(pairs.getKey());
	        }
	    }
	    return null;
	}

	/**
	 * Gets the e mail actuator.
	 *
	 * @return the e mail actuator
	 */
	public EmailActuator getEmailActuator() {
		Iterator it = baseActuators.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        if(baseActuators.get(pairs.getKey()) instanceof EmailActuator) {
	        	return (EmailActuator) baseActuators.get(pairs.getKey());
	        }
	    }
	    return null;
	}

	/**
	 * Gets the day sensor.
	 *
	 * @return the day sensor
	 */
	public DaySensor getDaySensor() {
		Iterator it = baseSensors.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        if(baseSensors.get(pairs.getKey()) instanceof DaySensor) {
	        	return (DaySensor) baseSensors.get(pairs.getKey());
	        }
	    }
	    return null;
	}

	}