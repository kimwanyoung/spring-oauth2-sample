package com.oauth.springoauth2.api.oauth2login.service;

import org.springframework.stereotype.Component;

import com.oauth.springoauth2.client.oauth2.OAuth2LoginUserInfo;
import com.oauth.springoauth2.common.type.OAuth2Provider;

@Component
public interface OAuth2Client {
	String generateLoginPageUrl();

	String getAccessToken(String authorizationCode);

	OAuth2LoginUserInfo getUserInfo(String accessToken);

	boolean supports(OAuth2Provider provider);
}
