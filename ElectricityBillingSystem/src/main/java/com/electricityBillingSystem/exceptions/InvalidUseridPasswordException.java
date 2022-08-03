package com.electricityBillingSystem.exceptions;

public class InvalidUseridPasswordException extends Exception{
	private static final long serialVersionUID = 1L;

	private String message;

	public InvalidUseridPasswordException() {
		this.message = "Invalid Id and Password...";
	}

	public InvalidUseridPasswordException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "InvalidUsernamePasswordException [message=" + message + "]";
	}

	@Override
	public String getMessage() {
		return "MESSAGE:: " + message;
	}

}
