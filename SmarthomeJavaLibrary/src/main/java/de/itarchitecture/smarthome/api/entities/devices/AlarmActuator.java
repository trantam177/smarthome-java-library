/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api.entities.devices;

/**
 * The Class AlarmActuator.
 *
 * @author sammy98
 */
public class AlarmActuator extends BaseActuator {
    
    /** The is on. */
    private boolean isOn = true;
    
    /** The alarm duration. */
    private int alarmDuration = 121;
	
    /**
     * Checks if is on.
     *
     * @return true, if is on
     */
    public boolean isOn() {
		return isOn;
	}
	
	/**
	 * Sets the on.
	 *
	 * @param isOn the new on
	 */
	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}
	
	/**
	 * Gets the alarm duration.
	 *
	 * @return the alarm duration
	 */
	public int getAlarmDuration() {
		return alarmDuration;
	}
	
	/**
	 * Sets the alarm duration.
	 *
	 * @param alarmDuration the new alarm duration
	 */
	public void setAlarmDuration(int alarmDuration) {
		this.alarmDuration = alarmDuration;
	}
}
