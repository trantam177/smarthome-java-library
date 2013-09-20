/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api.entities.devices;

import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class RoomTemperatureActuator extends LogicalDevice {

    private Double pointTemperature = new Double(0.0);
    private String operationMode = "";
    private String windowReductionActive = "";
    private ArrayList<String> underlyingDevicesIds = new ArrayList<String>(2);
    private Double maxTemperature = new Double (0.0);
    private Double minTemperature = new Double (0.0);
    private Double preheatFactor = new Double (0.0);
    private Boolean isLocked = false;
    private Boolean isFreezeProtectionActivated = false;
    private Double freezeProtection = new Double (0.0);
    private Boolean isMoldProtectionActivated = false;
    private Double humidityMoldProtection = new Double (0.0);
    private Double windowsOpenTemperature = new Double (0.0);
    private RoomHumiditySensor roomHumiditySensor = null;
    private RoomTemperatureSensor roomTemperatureSensor = null;
    public RoomHumiditySensor getRoomHumiditySensor() {
		return roomHumiditySensor;
	}

	public void setRoomHumiditySensor(RoomHumiditySensor roomHumiditySensor) {
		this.roomHumiditySensor = roomHumiditySensor;
	}

	public RoomTemperatureSensor getRoomTemperatureSensor() {
		return roomTemperatureSensor;
	}

	public void setRoomTemperatureSensor(RoomTemperatureSensor roomTemperatureSensor) {
		this.roomTemperatureSensor = roomTemperatureSensor;
	}

	public final static String OPERATION_MODE_AUTO = "Auto";
    public final static String OPERATION_MODE_MANU = "Manu";

    public RoomTemperatureActuator() {
        this.setType(LogicalDevice.Type_RoomTemperatureActuatorState);
    }

    /**
     * @return the pointTemperature
     */
    public Double getPointTemperature() {
        return pointTemperature;
    }

    /**
     * @param pointTemperature the pointTemperature to set
     */
    public void setPointTemperature(Double pointTemperature) {
        this.pointTemperature = pointTemperature;
    }

    /**
     * @return the operationMode
     */
    public String getOperationMode() {
        return operationMode;
    }

    /**
     * @param operationMode the operationMode to set
     */
    public void setOperationMode(String operationMode) {
        this.operationMode = operationMode;
    }

    /**
     * @return the windowReductionActive
     */
    public String getWindowReductionActive() {
        return windowReductionActive;
    }

    /**
     * @param windowReductionActive the windowReductionActive to set
     */
    public void setWindowReductionActive(String windowReductionActive) {
        this.windowReductionActive = windowReductionActive;
    }

	public void setMaxTemperature(double doubleValueFromElements) {
		this.maxTemperature = doubleValueFromElements;
	
	}

	public void setMinTemperature(double doubleValueFromElements) {
		this.minTemperature = doubleValueFromElements;
	
	}

	public Double getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(Double maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	public Double getMinTemperature() {
		return minTemperature;
	}

	public void setMinTemperature(Double minTemperature) {
		this.minTemperature = minTemperature;
	}

	public Double getPreheatFactor() {
		return preheatFactor;
	}

	public void setPreheatFactor(Double preheatFactor) {
		this.preheatFactor = preheatFactor;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Boolean getIsFreezeProtectionActivated() {
		return isFreezeProtectionActivated;
	}

	public void setIsFreezeProtectionActivated(Boolean isFreezeProtectionActivated) {
		this.isFreezeProtectionActivated = isFreezeProtectionActivated;
	}

	public Double getFreezeProtection() {
		return freezeProtection;
	}

	public void setFreezeProtection(Double freezeProtection) {
		this.freezeProtection = freezeProtection;
	}

	public Boolean getIsMoldProtectionActivated() {
		return isMoldProtectionActivated;
	}

	public void setIsMoldProtectionActivated(Boolean isMoldProtectionActivated) {
		this.isMoldProtectionActivated = isMoldProtectionActivated;
	}

	public Double getHumidityMoldProtection() {
		return humidityMoldProtection;
	}

	public void setHumidityMoldProtection(Double humidityMoldProtection) {
		this.humidityMoldProtection = humidityMoldProtection;
	}

	public Double getWindowsOpenTemperature() {
		return windowsOpenTemperature;
	}

	public void setWindowsOpenTemperature(Double windowsOpenTemperature) {
		this.windowsOpenTemperature = windowsOpenTemperature;
	}
}
