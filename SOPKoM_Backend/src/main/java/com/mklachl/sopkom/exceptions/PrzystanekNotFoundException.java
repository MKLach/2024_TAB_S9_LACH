package com.mklachl.sopkom.exceptions;

public class PrzystanekNotFoundException extends Exception {

	/**
	 * YES!
	 */
	private static final long serialVersionUID = 7349300203842523635L;

	public PrzystanekNotFoundException(Long liniaId, Long przystanekid) {
		super("cannot found przystane with id:" + przystanekid + " , that was meant to be for linia " + liniaId);
	}
	
	
	
}
