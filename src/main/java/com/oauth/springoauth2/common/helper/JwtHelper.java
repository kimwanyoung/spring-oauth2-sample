package com.oauth.springoauth2.common.helper;

import org.springframework.stereotype.Component;

@Component
public class JwtHelper {

	public String generateAccessToken(Long id) {
		return "SAMPLE_ACCESS_TOKEN";
	}
}
