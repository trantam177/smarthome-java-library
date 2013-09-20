/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api.entities.devices;

/**
 * Represents a switch
 *
 * @author michael
 */
public class SwitchActuator extends BaseActuator implements SmartHomeSwitch {

    private Boolean isOn = Boolean.FALSE;
    private SwitchDefaultOffSettings defaultOffSettings = new SwitchDefaultOffSettings();
    
    public final String TYPE_LIGHT = "Light";

    public SwitchActuator() {
        this.setType(LogicalDevice.Type_SwitchActuator);
    }

    /* (non-Javadoc)
	 * @see de.itarchitecture.smarthome.api.entities.devices.SmartHomeSwitch#getIsOn()
	 */
    @Override
	public Boolean getIsOn() {
        return isOn;
    }

    /* (non-Javadoc)
	 * @see de.itarchitecture.smarthome.api.entities.devices.SmartHomeSwitch#setIsOn(java.lang.Boolean)
	 */
    @Override
	public void setIsOn(Boolean isOn) {
        this.isOn = isOn;
    }

    /**
     * @return the defaultOffSettings
     */
    public SwitchDefaultOffSettings getDefaultOffSettings() {
        return defaultOffSettings;
    }

    /**
     * @param defaultOffSettings the defaultOffSettings to set
     */
    public void setDefaultOffSettings(SwitchDefaultOffSettings defaultOffSettings) {
        this.defaultOffSettings = defaultOffSettings;
    }
}
