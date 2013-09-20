/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api.entities;

/**
 *
 * @author michael
 */
public class SmartHomeLocation {
    public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	private String locationId = "";
    private String name = "";
    private String position = "";
    
}
