/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api.entities.devices;

/**
 *
 * @author michael
 */
public class RoomHumiditySensor extends BaseSensor {

    private Double humidity = new Double(0.0);

    public RoomHumiditySensor() {
        {
            this.setType(LogicalDevice.Type_RoomHumiditySensorState);
        }

    }

    /**
     * @return the humidity
     */
    public Double getHumidity() {
        return humidity;
    }

    /**
     * @param humidity the humidity to set
     */
    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}
