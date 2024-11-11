package com.oauth.springoauth2.domain.socialmember.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oauth.springoauth2.common.type.OAuth2Provider;
import com.oauth.springoauth2.domain.socialmember.entity.SocialMember;

@Repository
public interface SocialMemberRepository extends JpaRepository<SocialMember, Long> {

	Optional<SocialMember> findByProviderAndProviderId(OAuth2Provider provider, String providerId);
}
