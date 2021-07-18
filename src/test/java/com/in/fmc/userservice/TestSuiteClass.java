package com.in.fmc.userservice;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.in.fmc.userservice.services.LoginAndRegisterServiceImplTest;
import com.in.fmc.userservice.utils.UtilsTest;

@RunWith(Suite.class)
@SuiteClasses(value = { LoginAndRegisterServiceImplTest.class, UtilsTest.class, UserServiceApplicationTests.class })
public class TestSuiteClass {

	
}
