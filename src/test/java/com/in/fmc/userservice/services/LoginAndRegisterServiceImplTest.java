package com.in.fmc.userservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.in.fmc.userservice.entities.Login;
import com.in.fmc.userservice.entities.Register;
import com.in.fmc.userservice.exceptions.InvalidCredentialsException;
import com.in.fmc.userservice.exceptions.UsernameOccupiedException;
import com.in.fmc.userservice.repositories.LoginRepository;
import com.in.fmc.userservice.repositories.RegisterRepository;
import com.in.fmc.userservice.utils.Utils;

@RunWith(value = SpringRunner.class)
public class LoginAndRegisterServiceImplTest {

	private static ResourceLoader resourceLoader;

	private static ObjectMapper mapper;
	private static Register register;
	private static Login login;

	private static String registerPath = "classpath:register.json";
	private static String loginPath = "login.json";

	@Mock
	LoginRepository loginRepository;

	@Mock
	RegisterRepository registerRepository;

	@InjectMocks
	LoginAndRegisterServiceImpl loginAndRegisterServiceImpl = new LoginAndRegisterServiceImpl();

	@BeforeClass
	public static void setup() throws IOException {

		resourceLoader = new DefaultResourceLoader();
		mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		register = mapper.readValue(resourceLoader.getResource(registerPath).getFile(), Register.class);
		login = mapper.readValue(resourceLoader.getResource(loginPath).getFile(), Login.class);
		System.out.println("Login - " + login.getUsername() + " " + login.getPassword());

	}

	/*
	 * @Test
	 * 
	 * @Order(value = 2) public void loginValidTest() {
	 * 
	 * }
	 */

	@org.junit.Test
	public void registerValidTest() {

		Optional<Login> optionalLogin = Optional.empty();
		when(loginRepository.findByUsername(register.getLogin().getUsername())).thenReturn(optionalLogin);
		when(registerRepository.save(register)).thenReturn(register);
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>("Registered successfully",
				HttpStatus.CREATED);
		assertEquals(expectedResponseEntity, loginAndRegisterServiceImpl.register(register));

	}

	@Test(expected = UsernameOccupiedException.class)
	public void registerInValidTest() {

		Optional<Login> optionalLogin = Optional.of(register.getLogin());
		when(loginRepository.findByUsername(register.getLogin().getUsername())).thenReturn(optionalLogin);
		loginAndRegisterServiceImpl.register(register);

	}

	@Test
	public void loginTest() {

		Login securedLogin = new Login();
		securedLogin.setUsername(login.getUsername());
		securedLogin.setPassword(Utils.secureHash(login.getPassword()));
		Optional<Login> loginOptional = Optional.of(securedLogin);

		when(loginRepository.findByUsernameAndPassword(login.getUsername(), securedLogin.getPassword()))
				.thenReturn(loginOptional);

		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>("Logged in successfully",
				HttpStatus.OK);

		assertEquals(expectedResponseEntity, loginAndRegisterServiceImpl.login(login));

	}

	@Test(expected = InvalidCredentialsException.class)
	public void loginInvalidTest() {

		Optional<Login> optionalLogin = Optional.empty();

		when(loginRepository.findByUsernameAndPassword(login.getUsername(), Utils.secureHash(login.getPassword())))
				.thenReturn(optionalLogin);

		loginAndRegisterServiceImpl.login(login);
	}

}
