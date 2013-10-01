package de.itarchitecture.smarthome.api.entities.devices;

/**
 * The Class PushButtonSensor.
 *
 * @author sammy98
 */
public class PushButtonSensor extends BaseSensor {
    
    /** The button count. */
    private int buttonCount = 0;

	/**
	 * Gets the button count.
	 *
	 * @return the button count
	 */
	public int getButtonCount() {
		return buttonCount;
	}

	/**
	 * Sets the button count.
	 *
	 * @param buttonCount the new button count
	 */
	public void setButtonCount(int buttonCount) {
		this.buttonCount = buttonCount;
	}

}