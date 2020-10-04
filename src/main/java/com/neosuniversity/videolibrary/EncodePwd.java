package com.neosuniversity.videolibrary;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodePwd {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		System.out.println(encoder.encode("user"));
		System.out.println(encoder.encode("admin"));

	}

}
