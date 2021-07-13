package com.in.fmc.userservice.constants;

public class ValidationConstants {

	public static final String USERNAME_REGEX = "^[A-Za-z]\\w{7,32}$";
	public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^_~-])(?=\\S+$).{8,32}$";
	public static final String MOBILE_NO_REGEX = "^\\d{10}$";

	private ValidationConstants() {
		// TODO Auto-generated constructor stub
	}
}
