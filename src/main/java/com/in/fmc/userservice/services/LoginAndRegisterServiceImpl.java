package com.in.fmc.userservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.in.fmc.userservice.entities.Login;
import com.in.fmc.userservice.entities.Register;
import com.in.fmc.userservice.repositories.LoginRepository;
import com.in.fmc.userservice.repositories.RegisterRepository;
import com.in.fmc.userservice.utils.Utils;

@Service
public class LoginAndRegisterServiceImpl implements LoginAndRegisterService {

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	RegisterRepository registerRepository;

	@Override
	public ResponseEntity<String> login(Login login) {

		login.setPassword(Utils.secureHash(login.getPassword()));

		Optional<Login> optionalLogin = loginRepository.findByUsernameAndPassword(login.getUsername(),
				login.getPassword());
		if (optionalLogin.isPresent()) {

			return new ResponseEntity<String>("Logged in successfully", HttpStatus.OK);
		}

		return new ResponseEntity<String>("Invalid username or password", HttpStatus.UNAUTHORIZED);
	}

	@Override
	public ResponseEntity<String> register(Register register) {
		
		Optional<Login> optionalLogin=loginRepository.findByUsername(register.getLogin().getUsername());
		
		if(!optionalLogin.isPresent()) {

			Login login=register.getLogin();
			login.setRegister(register);
			
			login.setPassword(Utils.secureHash(login.getPassword()));
			
			registerRepository.save(register);
			
			return new ResponseEntity<String>("Registered successfully", HttpStatus.CREATED);
		}

		return new ResponseEntity<String>("Username not available, kindly try another username :)", HttpStatus.NOT_ACCEPTABLE);
	}

}
