package com.oauth.springoauth2.client.oauth2.kakao.dto;

import lombok.Getter;

@Getter
public class KakaoLoginUserInfoResponse {
	private Long id;
	private KakaoUserPropertiesResponse properties;
}
