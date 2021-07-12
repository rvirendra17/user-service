package com.in.fmc.userservice.services;

import org.springframework.http.ResponseEntity;

import com.in.fmc.userservice.entities.Login;
import com.in.fmc.userservice.entities.Register;

public interface LoginAndRegisterService {

	ResponseEntity<String> login(Login login);

	ResponseEntity<String> register(Register register);

}
