
package com.googlecode.swtfinder.core.exception;
/**
 * special excpetion used when error occured in finding a widget<br>
 * @author Ben.Xu (xufengbing@gmail.com)
 */
public class FindException extends RuntimeException {
	private static final long serialVersionUID = -6819689699053830948L;

	public FindException(String message) {
		super(message);
	}

	public FindException(String message, Throwable cause) {
		super(message, cause);
	}
}
