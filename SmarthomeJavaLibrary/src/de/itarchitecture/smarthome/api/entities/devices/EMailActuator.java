package de.itarchitecture.smarthome.api.entities.devices;

/**
 * Represents an email actuator.
 *
 * @author sammy98
 */
public class EMailActuator extends SpecialActuator {

	/** The Email period. */
	private String EmailPeriod;
	
	/** The Email max period. */
	private String EmailMaxPeriod;
	
	/** The Email message. */
	private String EmailMessage;
		
    /**
     * Instantiates a new sMS actuator.
     */
    public EMailActuator() {
        this.setType(LogicalDevice.Type_SMSActuator);
    }
	
	/**
	 * Gets the email period.
	 *
	 * @return the email period
	 */
	public String getEmailPeriod() {
		return EmailPeriod;
	}

	/**
	 * Sets the email period.
	 *
	 * @param emailPeriod the new email period
	 */
	public void setEmailPeriod(String emailPeriod) {
		EmailPeriod = emailPeriod;
	}

	/**
	 * Gets the email max period.
	 *
	 * @return the email max period
	 */
	public String getEmailMaxPeriod() {
		return EmailMaxPeriod;
	}

	/**
	 * Sets the email max period.
	 *
	 * @param emailMaxPeriod the new email max period
	 */
	public void setEmailMaxPeriod(String emailMaxPeriod) {
		EmailMaxPeriod = emailMaxPeriod;
	}

	/**
	 * Gets the email message.
	 *
	 * @return the email message
	 */
	public String getEmailMessage() {
		return EmailMessage;
	}

	/**
	 * Sets the email message.
	 *
	 * @param emailMessage the new email message
	 */
	public void setEmailMessage(String emailMessage) {
		EmailMessage = emailMessage;
	}

}
