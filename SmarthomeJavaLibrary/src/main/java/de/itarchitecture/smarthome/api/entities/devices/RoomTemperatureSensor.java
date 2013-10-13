/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api.entities.devices;

/**
 *
 * @author michael
 */
public class RoomTemperatureSensor extends LogicalDevice {

    private Double temperature = new Double(0.0);

    public RoomTemperatureSensor() {
        this.setType(LogicalDevice.Type_RoomTemperatureSensorState);
    }

    /**
     * @return the pointTemperature
     */
    public Double getTemperature() {
        return temperature;
    }

    /**
     * @param pointTemperature the pointTemperature to set
     */
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
