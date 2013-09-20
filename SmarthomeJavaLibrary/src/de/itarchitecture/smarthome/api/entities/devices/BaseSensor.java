/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api.entities.devices;

/**
 *
 * @author michael
 */
public class BaseSensor extends LogicalDevice {
        private String baseDeviceId = "";

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
