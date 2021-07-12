package com.in.fmc.userservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.in.fmc.userservice.entities.Login;
import com.in.fmc.userservice.entities.Register;

import lombok.Data;

@Data
public class RegistrationDto {

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "email")
	private String emailId;

	@JsonProperty("password")
	private String password;

	@JsonProperty(value = "username")
	private String username;

	@JsonProperty(value = "mobile_no")
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
