package com.fabio.exceptions;

public class BlackListException extends Exception {
	public BlackListException(String message) {
		super(message);
	}
	
	public BlackListException() {
		super();
	}
}
