package com.in.fmc.userservice.constants;

public class ExceptionConstants {
	
	public static final String RESPONSE_TYPE_DEFINITION = "https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html";

	public static final String INVALID_CREDENTIALS_EXCEPTION_MESSAGE = "Invalid username or password";
	public static final String USERNAME_OCCUPIED_EXCEPTION_MESSAGE = "Username not available, kindly try another username :)";

	public static final String MAX_NAME_LIMIT_ERROR_MESSAGE = "Name too long - maximum name length should be 256 characters";

	public static final String INVALID_NAME = "name";
	public static final String INVALID_EMAIL = "email";
	public static final String INVALID_MOBILE = "mobile_no";
	public static final String INVALID_USERNAME = "username";
	public static final String INVALID_PASSWORD = "password";

	public static final String INVALID_PASSWORD_CRITERIA = "Password did not match the given criteria";
	public static final String INVALID_USERNAME_CRITERIA = "Username did not match the given criteria";

	public static final String VALIDATION_ERROR_MESSAGE = "Invalid or missing parameters - ";

	private ExceptionConstants() {
		// TODO Auto-generated constructor stub
	}
}
