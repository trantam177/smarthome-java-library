/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api.entities;

import de.itarchitecture.smarthome.api.entities.devices.RoomHumiditySensor;
import de.itarchitecture.smarthome.api.entities.devices.RoomTemperatureActuator;
import de.itarchitecture.smarthome.api.entities.devices.RoomTemperatureSensor;

/**
 *
 * @author michael
 */
public class TemperatureHumidityDevice {
	private SmartHomeLocation location = null;
	private RoomTemperatureSensor temperatureSensor = null;
	private RoomTemperatureActuator temperatureActuator = null;
	private RoomHumiditySensor roomHumidtySensor = null;
	public TemperatureHumidityDevice() {
		super();
	}
    public String getLocationId() {
		return location.getLocationId();
	}
	public void setLocationId(String locationId) {
		this.location.setLocationId(locationId);
	}
	/**
	 * @return the location
	 */
	public SmartHomeLocation getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(SmartHomeLocation location) {
		this.location = location;
	}
	/**
	 * @return the temperatureSensor
	 */
	public RoomTemperatureSensor getTemperatureSensor() {
		return temperatureSensor;
	}
	/**
	 * @param temperatureSensor the temperatureSensor to set
	 */
	public void setTemperatureSensor(RoomTemperatureSensor temperatureSensor) {
		this.temperatureSensor = temperatureSensor;
	}
	/**
	 * @return the temperatureActuator
	 */
	public RoomTemperatureActuator getTemperatureActuator() {
		return temperatureActuator;
	}
	/**
	 * @param temperatureActuator the temperatureActuator to set
	 */
	public void setTemperatureActuator(RoomTemperatureActuator temperatureActuator) {
		this.temperatureActuator = temperatureActuator;
	}
	/**
	 * @return the roomHumidtySensor
	 */
	public RoomHumiditySensor getRoomHumidtySensor() {
		return roomHumidtySensor;
	}
	/**
	 * @param roomHumidtySensor the roomHumidtySensor to set
	 */
	public void setRoomHumidtySensor(RoomHumiditySensor roomHumidtySensor) {
		this.roomHumidtySensor = roomHumidtySensor;
	}    
}
