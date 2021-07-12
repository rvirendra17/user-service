package com.in.fmc.userservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in.fmc.userservice.dtos.LoginDto;
import com.in.fmc.userservice.dtos.RegistrationDto;
import com.in.fmc.userservice.services.LoginAndRegisterService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/users")
@Slf4j
public class LoginAndRegisterController {

	@Autowired
	LoginAndRegisterService loginAndRegisterService;

	@PostMapping(path = "/login")
	public ResponseEntity<String> login(@RequestBody(required = true) LoginDto loginDto,
			@RequestHeader HttpHeaders headers) {

		log.debug("Request received for login with correlationId: {}", headers.getFirst("correlationid"));

		return loginAndRegisterService.login(LoginDto.mapToLogin(loginDto));

	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegistrationDto registrationDto,
			@RequestHeader HttpHeaders httpHeaders) {

		log.debug("Request for resgister with username: {}, and correlationId: {}", registrationDto.getUsername(),
				httpHeaders.getFirst("correlationid"));
		return loginAndRegisterService.register(RegistrationDto.mapToRegister(registrationDto));

	}

}
