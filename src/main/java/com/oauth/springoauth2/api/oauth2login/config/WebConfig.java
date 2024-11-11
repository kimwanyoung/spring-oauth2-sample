package com.oauth.springoauth2.api.oauth2login.config;

import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.oauth.springoauth2.api.oauth2login.controller.converter.OAuth2ProviderConverter;

public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new OAuth2ProviderConverter());
	}
}
