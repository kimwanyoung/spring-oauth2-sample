package com.oauth.springoauth2.client.oauth2.naver;

import org.springframework.stereotype.Component;

import com.oauth.springoauth2.api.oauth2login.service.OAuth2Client;
import com.oauth.springoauth2.client.oauth2.OAuth2LoginUserInfo;
import com.oauth.springoauth2.common.type.OAuth2Provider;

public class NaverOAuth2Client implements OAuth2Client {
	@Override
	public String generateLoginPageUrl() {
		return null;
	}

	@Override
	public String getAccessToken(String authorizationCode) {
		return null;
	}

	@Override
	public OAuth2LoginUserInfo getUserInfo(String accessToken) {
		return null;
	}

	@Override
	public boolean supports(OAuth2Provider provider) {
		return provider == OAuth2Provider.NAVER;
	}
}
