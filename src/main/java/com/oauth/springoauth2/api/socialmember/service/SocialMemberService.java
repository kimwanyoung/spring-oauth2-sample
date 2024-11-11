package com.oauth.springoauth2.api.socialmember.service;

import org.springframework.stereotype.Service;

import com.oauth.springoauth2.client.oauth2.OAuth2LoginUserInfo;
import com.oauth.springoauth2.common.type.OAuth2Provider;
import com.oauth.springoauth2.domain.socialmember.entity.SocialMember;
import com.oauth.springoauth2.domain.socialmember.repository.SocialMemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SocialMemberService {

	private final SocialMemberRepository socialMemberRepository;

	public SocialMember registerIfAbsent(OAuth2Provider provider, OAuth2LoginUserInfo userInfo) {
		return socialMemberRepository.findByProviderAndProviderId(provider, userInfo.getId())
			.orElseGet(() -> {
				var newSocialMember = SocialMember.builder()
					.provider(provider)
					.providerId(userInfo.getId())
					.nickname(userInfo.getNickname())
					.build();
				return socialMemberRepository.save(newSocialMember);
			});
	}
}
