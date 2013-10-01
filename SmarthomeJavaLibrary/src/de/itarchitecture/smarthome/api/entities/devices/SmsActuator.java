package de.itarchitecture.smarthome.api.entities.devices;

/**
 * Represents a sms actuator
 *
 * @author sammy98
 */
public class SmsActuator extends SpecialActuator {

	/** The sms period. */
	private String smsPeriod;
	
	/** The sms max period. */
	private String smsMaxPeriod;
	
	/** The sms message. */
	private String smsMessage;

	/** The sms available. */
	private int smsAvailable;
	
    /**
     * Instantiates a new sMS actuator.
     */
    public SmsActuator() {
        this.setType(LogicalDevice.Type_SmsActuator);
    }

	/**
	 * Gets the sMS period.
	 *
	 * @return the sMS period
	 */
	public String getsmsPeriod() {
		return smsPeriod;
	}

	/**
	 * Sets the sMS period.
	 *
	 * @param sMSPeriod the new sMS period
	 */
	public void setSmsPeriod(String sMSPeriod) {
		smsPeriod = sMSPeriod;
	}

	/**
	 * Gets the sMS max period.
	 *
	 * @return the sMS max period
	 */
	public String getSmsMaxPeriod() {
		return smsMaxPeriod;
	}

	/**
	 * Sets the sMS max period.
	 *
	 * @param sMSMaxPeriod the new sMS max period
	 */
	public void setSmsMaxPeriod(String sMSMaxPeriod) {
		smsMaxPeriod = sMSMaxPeriod;
	}

	/**
	 * Gets the sMS message.
	 *
	 * @return the sMS message
	 */
	public String getSmsMessage() {
		return smsMessage;
	}

	/**
	 * Sets the sMS message.
	 *
	 * @param sMSMessage the new sMS message
	 */
	public void setSmsMessage(String sMSMessage) {
		smsMessage = sMSMessage;
	}

	/**
	 * Gets the sms available.
	 *
	 * @return the sms available
	 */
	public int getSmsAvailable() {
		return smsAvailable;
	}

	/**
	 * Sets the sms available.
	 *
	 * @param smsAvailable the new sms available
	 */
	public void setSmsAvailable(int smsAvailable) {
		this.smsAvailable = smsAvailable;
	}
	
}
