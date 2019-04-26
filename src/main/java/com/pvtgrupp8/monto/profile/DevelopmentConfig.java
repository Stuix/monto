package com.pvtgrupp8.monto.profile;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@Configuration
@Profile("dev")
@PropertySources(value={@PropertySource("classpath:test.properties")})
public class DevelopmentConfig {
}
