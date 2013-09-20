package de.itarchitecture.smarthome.api.exceptions;

public class LoginFailedException extends SHFunctionalException {

	/**
	 * 
	 */
	public LoginFailedException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param detailMessage
	 * @param throwable
	 */
	public LoginFailedException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param detailMessage
	 */
	public LoginFailedException(String detailMessage) {
		super(detailMessage);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param throwable
	 */
	public LoginFailedException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8665156058522420461L;

}
