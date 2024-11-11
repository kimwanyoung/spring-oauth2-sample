package com.oauth.springoauth2.api.oauth2login.controller.converter;

import org.springframework.core.convert.converter.Converter;

import com.oauth.springoauth2.common.type.OAuth2Provider;

public class OAuth2ProviderConverter implements Converter<String, OAuth2Provider> {
	@Override
	public OAuth2Provider convert(String source) {
		try {
			return OAuth2Provider.valueOf(source.toUpperCase());
		} catch (RuntimeException ex) {
			throw new IllegalArgumentException();
		}
	}
}
