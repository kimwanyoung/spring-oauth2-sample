package com.oauth.springoauth2.client.oauth2.kakao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClient;

import com.oauth.springoauth2.api.oauth2login.service.OAuth2Client;
import com.oauth.springoauth2.client.oauth2.OAuth2LoginUserInfo;
import com.oauth.springoauth2.client.oauth2.kakao.dto.KakaoLoginUserInfoResponse;
import com.oauth.springoauth2.client.oauth2.kakao.dto.KakaoTokenResponse;
import com.oauth.springoauth2.common.type.OAuth2Provider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KakaoOAuth2Client implements OAuth2Client {

	private final static String AUTH_SERVER_BASE_URL = "https://kauth.kakao.com";
	private final static String RESOURCE_SERVER_BASE_URL = "https://kapi.kakao.com";

	@Value("${oauth2.kakao.client_id}")
	private String clientId;
	@Value("${oauth2.kakao.redirect_url}")
	private String redirectUrl;

	private final RestClient restClient;

	@Override
	public String generateLoginPageUrl() {
		return AUTH_SERVER_BASE_URL
			+ "/oauth/authorize"
			+ "?client_id=" + clientId
			+ "&redirect_uri=" + redirectUrl
			+ "&response_type=" + "code";
	}

	@Override
	public String getAccessToken(String authorizationCode) {
		var body = new LinkedMultiValueMap<String, String>();
		body.add("grant_type", "authorization_code");
		body.add("client_id", clientId);
		body.add("code", authorizationCode);

		return Optional.ofNullable(
				restClient.post()
					.uri(AUTH_SERVER_BASE_URL + "/oauth/token")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.body(body)
					.retrieve()
					.onStatus(HttpStatusCode::isError, (req, resp) -> {
						throw new RuntimeException("카카오 AccessToken 조회 실패");
					})
					.body(KakaoTokenResponse.class)
			)
			.map(KakaoTokenResponse::getAccessToken)
			.orElseThrow(() -> new RuntimeException("카카오 AccessToken 조회 실패"));
	}

	@Override
	public OAuth2LoginUserInfo getUserInfo(String accessToken) {
		return Optional.ofNullable(
				restClient.get()
					.uri(RESOURCE_SERVER_BASE_URL + "/v2/user/me")
					.header("Authorization", "Bearer " + accessToken)
					.retrieve()
					.onStatus(HttpStatusCode::isError, (req, resp) -> {
						throw new RuntimeException("카카오 UserInfo 조회 실패");
					})
					.body(KakaoLoginUserInfoResponse.class))
			.map(response -> OAuth2LoginUserInfo.builder()
				.id(response.getId().toString())
				.provider(OAuth2Provider.KAKAO)
				.nickname(response.getProperties().getNickname())
				.build())
			.orElseThrow(() -> new RuntimeException("카카오 UserInfo 조회 실패"));
	}

	@Override
	public boolean supports(OAuth2Provider provider) {
		return provider == OAuth2Provider.KAKAO;
	}
}
