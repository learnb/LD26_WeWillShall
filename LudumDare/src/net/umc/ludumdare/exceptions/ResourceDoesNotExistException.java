package net.umc.ludumdare.exceptions;

@SuppressWarnings("serial")
public class ResourceDoesNotExistException extends Exception {
	
	public ResourceDoesNotExistException(String message) {
		super(message);
	}
}
