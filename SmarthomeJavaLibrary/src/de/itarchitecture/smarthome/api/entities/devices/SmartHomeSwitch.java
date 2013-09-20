package de.itarchitecture.smarthome.api.entities.devices;

public interface SmartHomeSwitch {

	/**
	 * @return the isOn
	 */
	public abstract Boolean getIsOn();

	/**
	 * @param isOn the isOn to set
	 */
	public abstract void setIsOn(Boolean isOn);

}