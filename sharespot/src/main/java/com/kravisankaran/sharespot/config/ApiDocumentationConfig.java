package com.kravisankaran.sharespot.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                description = "ShareSpot",
                version = "V12.0.12",
                title = "Share Spotify API",
                contact = @Contact(
                        name = "Kumud Ravisankaran",
                        email = "kr2647@nyu.edu",
                        url = ""
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        servers = {@Server(url = "")}
)
public interface ApiDocumentationConfig {

}
