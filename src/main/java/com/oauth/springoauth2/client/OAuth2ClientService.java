package com.oauth.springoauth2.client;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oauth.springoauth2.api.oauth2login.service.OAuth2Client;
import com.oauth.springoauth2.client.oauth2.OAuth2LoginUserInfo;
import com.oauth.springoauth2.common.type.OAuth2Provider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuth2ClientService {

	private final List<OAuth2Client> clients;

	public String generateLoginPageUrl(OAuth2Provider provider) {
		return this.selectClient(provider).generateLoginPageUrl();
	}

	public OAuth2LoginUserInfo login(OAuth2Provider provider, String authorizationCode) {
		OAuth2Client oAuth2Client = this.selectClient(provider);
		String accessToken = oAuth2Client.getAccessToken(authorizationCode);
		return oAuth2Client.getUserInfo(accessToken);
	}

	private OAuth2Client selectClient(OAuth2Provider provider) {
		return clients.stream()
			.filter(clients -> clients.supports(provider))
			.findFirst()
			.orElseThrow();
	}
}
