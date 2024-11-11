package com.oauth.springoauth2.api.oauth2login.service;

import org.springframework.stereotype.Service;

import com.oauth.springoauth2.api.socialmember.service.SocialMemberService;
import com.oauth.springoauth2.client.OAuth2ClientService;
import com.oauth.springoauth2.client.oauth2.OAuth2LoginUserInfo;
import com.oauth.springoauth2.common.helper.JwtHelper;
import com.oauth.springoauth2.common.type.OAuth2Provider;
import com.oauth.springoauth2.domain.socialmember.entity.SocialMember;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuth2LoginService {

	private final OAuth2ClientService oAuth2ClientService;
	private final SocialMemberService socialMemberService;
	private final JwtHelper jwtHelper;

	public String generateLoginPageUrl(OAuth2Provider provider) {
		return oAuth2ClientService.generateLoginPageUrl(provider);
	}

	public String login(OAuth2Provider provider, String authorizationCode) {
		OAuth2LoginUserInfo userInfo = oAuth2ClientService.login(provider, authorizationCode);
		SocialMember socialMember = socialMemberService.registerIfAbsent(provider, userInfo);
		return jwtHelper.generateAccessToken(socialMember.getId());
	}
}
