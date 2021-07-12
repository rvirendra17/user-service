package com.in.fmc.userservice.utils;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class Utils {

	public static String secureHash(String stringTobeHashed) {

		String hash = Hashing.sha256().hashString(stringTobeHashed, StandardCharsets.UTF_8).toString();
		return hash;
	}

}
