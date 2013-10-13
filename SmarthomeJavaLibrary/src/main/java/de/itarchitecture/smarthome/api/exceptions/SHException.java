package de.itarchitecture.smarthome.api.exceptions;

public class SHException extends Exception {

	/**
	 * 
	 */
	public SHException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param detailMessage
	 * @param throwable
	 */
	public SHException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param detailMessage
	 */
	public SHException(String detailMessage) {
		super(detailMessage);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param throwable
	 */
	public SHException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}

}
