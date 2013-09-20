package de.itarchitecture.smarthome.api.exceptions;

public class SmartHomeSessionExpiredException extends SHFunctionalException {

	/**
	 * 
	 */
	public SmartHomeSessionExpiredException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param detailMessage
	 * @param throwable
	 */
	public SmartHomeSessionExpiredException(String detailMessage,
			Throwable throwable) {
		super(detailMessage, throwable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param detailMessage
	 */
	public SmartHomeSessionExpiredException(String detailMessage) {
		super(detailMessage);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param throwable
	 */
	public SmartHomeSessionExpiredException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}

}
