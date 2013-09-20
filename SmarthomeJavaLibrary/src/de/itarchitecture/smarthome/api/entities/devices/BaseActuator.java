/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api.entities.devices;

/**
 *
 * @author michael
 */
public class BaseActuator extends LogicalDevice {
    protected String actuatorClass = "";
    protected String baseDeviceId = "";

    public BaseActuator() {
    }

    /**
     * @return the actuatorClass
     */
    public String getActuatorClass() {
        return actuatorClass;
    }

    /**
     * @param actuatorClass the actuatorClass to set
     */
    public void setActuatorClass(String actuatorClass) {
        this.actuatorClass = actuatorClass;
    }
        /**
     * @return the baseDeviceId
     */
    public String getBaseDeviceId() {
        return baseDeviceId;
    }

    /**
     * @param baseDeviceId the baseDeviceId to set
     */
    public void setBaseDeviceId(String baseDeviceId) {
        this.baseDeviceId = baseDeviceId;
    }
    
}
