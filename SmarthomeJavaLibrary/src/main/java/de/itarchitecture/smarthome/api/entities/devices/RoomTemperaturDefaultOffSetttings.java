/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api.entities.devices;

/**
 *
 * @author michael
 */
public class RoomTemperaturDefaultOffSetttings {
    public final static String TYPE_SWITCH_ACTUATOR_SETTINGS = "RoomTemperatureActuatorSettings";
    private String type = TYPE_SWITCH_ACTUATOR_SETTINGS;
    private String settingsId = "";
    private String actuatorId = "";
    private double pointTemperatur = 0.0;
    private Boolean isWindowsReduction = false;
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the settingsId
     */
    public String getSettingsId() {
        return settingsId;
    }

    /**
     * @param settingsId the settingsId to set
     */
    public void setSettingsId(String settingsId) {
        this.settingsId = settingsId;
    }

    /**
     * @return the actuatorId
     */
    public String getActuatorId() {
        return actuatorId;
    }

    /**
     * @param actuatorId the actuatorId to set
     */
    public void setActuatorId(String actuatorId) {
        this.actuatorId = actuatorId;
    }

    /**
     * @return the pointTemperatur
     */
    public double getPointTemperatur() {
        return pointTemperatur;
    }

    /**
     * @param pointTemperatur the pointTemperatur to set
     */
    public void setPointTemperatur(double pointTemperatur) {
        this.pointTemperatur = pointTemperatur;
    }

    /**
     * @return the isWindowsReduction
     */
    public Boolean getIsWindowsReduction() {
        return isWindowsReduction;
    }

    /**
     * @param isWindowsReduction the isWindowsReduction to set
     */
    public void setIsWindowsReduction(Boolean isWindowsReduction) {
        this.isWindowsReduction = isWindowsReduction;
    }

}
