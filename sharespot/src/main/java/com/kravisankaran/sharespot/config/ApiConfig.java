package com.kravisankaran.sharespot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.context.annotation.RequestScope;

import com.kravisankaran.sharespot.service.SpotifyApiService;

@Configuration
public class ApiConfig {
	@Bean
	@RequestScope
	public SpotifyApiService spotifyApiService(OAuth2AuthorizedClientService clientService) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String accessToken = null;
		if (authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
			OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
			String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
			if (clientRegistrationId.equals("spotify")) {
				OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(clientRegistrationId,
						oauthToken.getName());
				accessToken = client.getAccessToken().getTokenValue();
			}
		}

		return new SpotifyApiService(accessToken);
	}

}
