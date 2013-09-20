package de.itarchitecture.smarthome.api.exceptions;

public class OperationFailedException extends SHFunctionalException {

	/**
	 * 
	 */
	public OperationFailedException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param detailMessage
	 * @param throwable
	 */
	public OperationFailedException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param detailMessage
	 */
	public OperationFailedException(String detailMessage) {
		super(detailMessage);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param throwable
	 */
	public OperationFailedException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3828697335654182705L;

}
