package com.electricityBillingSystem.exceptions;

public class DuplicateEntryException extends Exception{
	private static final long serialVersionUID = 1L;
	private String message;

	public DuplicateEntryException() {
		this.message = "Entered id/email/Phone Number already exist in the system...";
		// Main.main(new String[0]);
	}

	public DuplicateEntryException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "DuplicateEntryException [message=" + message + "]";
	}

	@Override
	public String getMessage() {
		return "MESSAGE:: " + message;
	}
}
