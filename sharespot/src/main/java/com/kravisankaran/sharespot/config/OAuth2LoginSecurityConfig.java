package com.kravisankaran.sharespot.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import com.kravisankaran.sharespot.domain.model.Constants;

@Configuration
public class OAuth2LoginSecurityConfig {

	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		return new InMemoryClientRegistrationRepository(this.spotifyClientRegistration());
	}

	private ClientRegistration spotifyClientRegistration() {

		
		return ClientRegistration.withRegistrationId("spotify").clientId(Constants.CLIENT_ID)
				.clientSecret(Constants.CLIENT_SECRET)
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.redirectUri("http://localhost:8080/login/oauth2/code/spotify")
				.scope(Arrays.asList("playlist-modify-public", "playlist-modify-private", "user-library-read"))
				.authorizationUri("https://accounts.spotify.com/authorize")
				.tokenUri("https://accounts.spotify.com/api/token")
				.userInfoUri("https://api.spotify.com/v1/me")
				.userNameAttributeName("display_name").clientName("spotify").build();

	}
}
