package de.itarchitecture.smarthome.api.entities.devices;

/**
 * Represents a sms actuator
 *
 * @author sammy98
 */
public class SMSActuator extends SpecialActuator {

	/** The SMS period. */
	private String SMSPeriod;
	
	/** The SMS max period. */
	private String SMSMaxPeriod;
	
	/** The SMS message. */
	private String SMSMessage;

	
    /**
     * Instantiates a new sMS actuator.
     */
    public SMSActuator() {
        this.setType(LogicalDevice.Type_SMSActuator);
    }

	/**
	 * Gets the sMS period.
	 *
	 * @return the sMS period
	 */
	public String getSMSPeriod() {
		return SMSPeriod;
	}

	/**
	 * Sets the sMS period.
	 *
	 * @param sMSPeriod the new sMS period
	 */
	public void setSMSPeriod(String sMSPeriod) {
		SMSPeriod = sMSPeriod;
	}

	/**
	 * Gets the sMS max period.
	 *
	 * @return the sMS max period
	 */
	public String getSMSMaxPeriod() {
		return SMSMaxPeriod;
	}

	/**
	 * Sets the sMS max period.
	 *
	 * @param sMSMaxPeriod the new sMS max period
	 */
	public void setSMSMaxPeriod(String sMSMaxPeriod) {
		SMSMaxPeriod = sMSMaxPeriod;
	}

	/**
	 * Gets the sMS message.
	 *
	 * @return the sMS message
	 */
	public String getSMSMessage() {
		return SMSMessage;
	}

	/**
	 * Sets the sMS message.
	 *
	 * @param sMSMessage the new sMS message
	 */
	public void setSMSMessage(String sMSMessage) {
		SMSMessage = sMSMessage;
	}
	
}
