package com.oauth.springoauth2.client.oauth2;

import com.oauth.springoauth2.common.type.OAuth2Provider;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuth2LoginUserInfo {

	private OAuth2Provider provider;
	private String id;
	private String nickname;

	@Builder
	public OAuth2LoginUserInfo(OAuth2Provider provider, String id, String nickname) {
		this.provider = provider;
		this.id = id;
		this.nickname = nickname;
	}
}
