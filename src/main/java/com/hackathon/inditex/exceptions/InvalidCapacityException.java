package com.hackathon.inditex.exceptions;

public class InvalidCapacityException extends RuntimeException {

	private static final long serialVersionUID = -587620316808088171L;

	public InvalidCapacityException(String message) {
        super(message);
    }
}
