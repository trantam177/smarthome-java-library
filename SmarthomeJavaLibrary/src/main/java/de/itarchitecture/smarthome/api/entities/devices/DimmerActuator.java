package de.itarchitecture.smarthome.api.entities.devices;

/**
 * Represents a switch.
 *
 * @author sammy98
 */
public class DimmerActuator extends BaseActuator {

    /** The default off settings. */
    private SwitchDefaultOffSettings defaultOffSettings = new SwitchDefaultOffSettings();
	
	/** The min. */
	private int min; //SFon;
	
	/** The max. */
	private int max; //SFOf;
	
	/** The off timer. */
	private int offTimer; //OffTmr;
	
	/** The dim level. */
	private int dimLevel = -1;
    
    /**
     * Instantiates a new dimmer actuator.
     */
    public DimmerActuator() {
        this.setType(LogicalDevice.Type_DimmerActuator);
    }


    /**
     * Gets the default off settings.
     *
     * @return the defaultOffSettings
     */
    public SwitchDefaultOffSettings getDefaultOffSettings() {
        return defaultOffSettings;
    }

    /**
     * Sets the default off settings.
     *
     * @param defaultOffSettings the defaultOffSettings to set
     */
    public void setDefaultOffSettings(SwitchDefaultOffSettings defaultOffSettings) {
        this.defaultOffSettings = defaultOffSettings;
    }

	/**
	 * Gets the min.
	 *
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * Sets the min.
	 *
	 * @param min the new min
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * Gets the max.
	 *
	 * @return the max
	 */
	public int getMax() {
		return max;
	}

	/**
	 * Sets the max.
	 *
	 * @param max the new max
	 */
	public void setMax(int max) {
		this.max = max;
	}

	/**
	 * Gets the off timer.
	 *
	 * @return the off timer
	 */
	public int getOffTimer() {
		return offTimer;
	}

	/**
	 * Sets the off timer.
	 *
	 * @param offTimer the new off timer
	 */
	public void setOffTimer(int offTimer) {
		this.offTimer = offTimer;
	}

	/**
	 * Gets the dim level.
	 *
	 * @return the dim level
	 */
	public int getDimLevel() {
		return dimLevel;
	}

	/**
	 * Sets the dim level.
	 *
	 * @param dimLevel the new dim level
	 */
	public void setDimLevel(int dimLevel) {
		this.dimLevel = dimLevel;
	}
	
}
