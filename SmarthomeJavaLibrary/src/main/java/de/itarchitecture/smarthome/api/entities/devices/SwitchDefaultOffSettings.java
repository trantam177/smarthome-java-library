/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api.entities.devices;

/**
 *
 * @author michael
 */
public class SwitchDefaultOffSettings {
    private String type = "";
    private String settingsId = "";
    private String actuatorId = "";
    private Boolean isOn = false;
    public final String TYPE_SWITCH_ACTUATOR_SETTINGS = "SwitchActuatorSettings";

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
     * @return the isOn
     */
    public Boolean getIsOn() {
        return isOn;
    }

    /**
     * @param isOn the isOn to set
     */
    public void setIsOn(Boolean isOn) {
        this.isOn = isOn;
    }
    
    
}
