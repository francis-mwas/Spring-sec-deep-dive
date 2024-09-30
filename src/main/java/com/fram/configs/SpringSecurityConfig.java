package com.fram.configs;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity // This annotation tells spring security to use this for secuirty configuartion and avoid using the deafult configs
public class SpringSecurityConfig {
}
