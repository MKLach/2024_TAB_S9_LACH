package com.mklachl.sopkom.exceptions;

public class LiniaNotFoundException extends Exception {

	public LiniaNotFoundException(Long id) {
		super("could nto found linia s" + id);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5933687264500607949L;

}
