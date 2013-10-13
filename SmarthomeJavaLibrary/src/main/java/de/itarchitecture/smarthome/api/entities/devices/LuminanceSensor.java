package de.itarchitecture.smarthome.api.entities.devices;

/**
 * The Class LuminanceSensor.
 *
 * @author sammy98
 */
public class LuminanceSensor extends BaseSensor {
 
	private int Luminance;

	public int getLuminance() {
		return Luminance;
	}

	public void setLuminance(int luminance) {
		Luminance = luminance;
	}
}
