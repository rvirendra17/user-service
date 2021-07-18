package com.in.fmc.userservice.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UtilsTest {

	private static String sampleString;
	private static String sampleStringHash256;

	@BeforeClass
	public static void setup() {

		sampleString = "Rvicky17$";
		sampleStringHash256 = "2c62e838e99c5179f118c3538520292179aa03d76f4113c35f598ded2a4ee518";

	}

	@Test
	public void secureHashTest() {
		assertEquals(sampleStringHash256, Utils.secureHash(sampleString));

	}
}
