package com.in.fmc.userservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.in.fmc.userservice.entities.Login;

import lombok.Data;

@Data
public class LoginDto {

	@JsonProperty(value = "username")
	private String username;
	@JsonProperty(value = "password")
	private String password;

	public static Login mapToLogin(LoginDto loginDto) {

		Login login = new Login();
		login.setUsername(loginDto.getUsername());
		login.setPassword(loginDto.getPassword());
		return login;
	}

}
