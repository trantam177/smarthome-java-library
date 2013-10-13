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
public class GenericActuator extends BaseActuator implements SmartHomeSwitch{

    private Boolean isOn = Boolean.FALSE;
    private SwitchDefaultOffSettings defaultOffSettings = new SwitchDefaultOffSettings();
    
    public final String TYPE_LIGHT = "Light";
    

    public GenericActuator() {
        this.setType(LogicalDevice.Type_GenericActuator);
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
