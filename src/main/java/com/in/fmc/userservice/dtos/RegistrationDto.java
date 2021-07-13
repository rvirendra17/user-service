package com.in.fmc.userservice.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.in.fmc.userservice.constants.ExceptionConstants;
import com.in.fmc.userservice.constants.ValidationConstants;
import com.in.fmc.userservice.entities.Login;
import com.in.fmc.userservice.entities.Register;

import lombok.Data;

@Data
public class RegistrationDto {

	@JsonProperty(value = "name")
	@Size(max = 256, message = ExceptionConstants.MAX_NAME_LIMIT_ERROR_MESSAGE)
	@NotBlank(message = ExceptionConstants.INVALID_NAME)
	private String name;

	@JsonProperty(value = "email")
	@NotBlank(message = ExceptionConstants.INVALID_EMAIL)
	@Email(message = ExceptionConstants.INVALID_EMAIL)
	private String emailId;

	@JsonProperty("password")
	@NotBlank(message = ExceptionConstants.INVALID_PASSWORD)
	@Pattern(regexp = ValidationConstants.PASSWORD_REGEX, message = ExceptionConstants.INVALID_PASSWORD_CRITERIA)
	private String password;

	@JsonProperty(value = "username")
	@NotBlank(message = ExceptionConstants.INVALID_USERNAME)
	@Pattern(regexp = ValidationConstants.USERNAME_REGEX, message = ExceptionConstants.INVALID_USERNAME_CRITERIA)
	private String username;

	@JsonProperty(value = "mobile_no")
	@NotNull(message = ExceptionConstants.INVALID_MOBILE)
	/*
	 * @Min(value = 10, message = ExceptionConstants.INVALID_MOBILE)
	 * 
	 * @Max(value = 10, message = ExceptionConstants.INVALID_MOBILE)
	 */
	private Long mobileNo;

	public static Register mapToRegister(RegistrationDto registrationDto) {

		Register register = new Register();

		register.setName(registrationDto.getName());
		register.setEmailId(registrationDto.getEmailId());
		register.setMobileNo(registrationDto.getMobileNo());

		Login login = new Login();

		login.setUsername(registrationDto.getUsername());
		login.setPassword(registrationDto.getPassword());

		register.setLogin(login);

		return register;
	}
}
