package com.hackathon.inditex.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7646836674476942487L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
