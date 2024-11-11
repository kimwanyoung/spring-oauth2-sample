package com.oauth.springoauth2.domain.socialmember.entity;

import com.oauth.springoauth2.common.type.OAuth2Provider;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SocialMember {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "social_member_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	private OAuth2Provider provider;

	private String providerId;
	private String nickname;

	@Builder
	public SocialMember(Long id, OAuth2Provider provider, String providerId, String nickname) {
		this.id = id;
		this.provider = provider;
		this.providerId = providerId;
		this.nickname = nickname;
	}
}
