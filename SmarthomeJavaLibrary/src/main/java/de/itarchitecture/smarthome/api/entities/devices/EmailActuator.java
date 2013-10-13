package de.itarchitecture.smarthome.api.entities.devices;

/**
 * Represents an email actuator.
 *
 * @author sammy98
 */
public class EmailActuator extends SpecialActuator {

	/** The Email period. */
	private String emailPeriod;
	
	/** The Email max period. */
	private String emailMaxPeriod;
	
	/** The Email message. */
	private String emailMessage;
		
	/** The email available. */
	private int emailAvailable;
    
	/**
     * Instantiates a new sMS actuator.
     */
    public EmailActuator() {
        this.setType(LogicalDevice.Type_EmailActuator);
    }
	
	/**
	 * Gets the email period.
	 *
	 * @return the email period
	 */
	public String getEmailPeriod() {
		return emailPeriod;
	}

	/**
	 * Sets the email period.
	 *
	 * @param emailPeriod the new email period
	 */
	public void setEmailPeriod(String emailPeriod) {
		this.emailPeriod = emailPeriod;
	}

	/**
	 * Gets the email max period.
	 *
	 * @return the email max period
	 */
	public String getEmailMaxPeriod() {
		return emailMaxPeriod;
	}

	/**
	 * Sets the email max period.
	 *
	 * @param emailMaxPeriod the new email max period
	 */
	public void setEmailMaxPeriod(String emailMaxPeriod) {
		this.emailMaxPeriod = emailMaxPeriod;
	}

	/**
	 * Gets the email message.
	 *
	 * @return the email message
	 */
	public String getEmailMessage() {
		return emailMessage;
	}

	/**
	 * Sets the email message.
	 *
	 * @param emailMessage the new email message
	 */
	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
	}

	/**
	 * Gets the email available.
	 *
	 * @return the email available
	 */
	public int getEmailAvailable() {
		return emailAvailable;
	}

	/**
	 * Sets the email available.
	 *
	 * @param emailAvailable the new email available
	 */
	public void setEmailAvailable(int emailAvailable) {
		this.emailAvailable = emailAvailable;
	}

}
