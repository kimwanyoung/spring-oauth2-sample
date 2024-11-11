package com.oauth.springoauth2.api.oauth2login.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.springoauth2.api.oauth2login.service.OAuth2LoginService;
import com.oauth.springoauth2.common.type.OAuth2Provider;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OAuth2LoginController {

	private final OAuth2LoginService oAuth2LoginService;

	@GetMapping("/oauth2/login/{provider}")
	public void redirectLoginPage(
		@PathVariable OAuth2Provider provider,
		HttpServletResponse response
	) throws IOException {
		String loginPageUrl = oAuth2LoginService.generateLoginPageUrl(provider);
		response.sendRedirect(loginPageUrl);
	}

	// 2. Callback 메서드 (authorizationCode를 받아서 로그인 처리해주는)
	@GetMapping("/oauth2/callback/{provider}")
	public ResponseEntity<String> callback(
		@PathVariable OAuth2Provider provider,
		@RequestParam(name = "code") String authorizationCode
	) {
		String accessToken = oAuth2LoginService.login(provider, authorizationCode);
		return ResponseEntity.ok(accessToken);
	}
}
