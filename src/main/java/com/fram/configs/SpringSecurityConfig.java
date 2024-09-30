package com.fram.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // This annotation tells spring security to use this for security configuration and avoid using the default configs
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(predicate -> predicate.disable() );
        httpSecurity.authorizeHttpRequests(req -> req.anyRequest().authenticated());
       // httpSecurity.formLogin(Customizer.withDefaults()); // this will allow form login, this will only work on browsers
        //To enable on post man or rest clients
        httpSecurity.httpBasic(Customizer.withDefaults());
       /* To make http stateless, when we do this you cant login from the browser form,
       since we expect in every request you pass credentials, so after login in, you request for a different resource and in that case we expect credentials
       in every resource request, after applying stateless http requests, form login will not work unless we comment or remove this line:
       httpSecurity.formLogin(Customizer.withDefaults());
       */

        httpSecurity.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();

    }
}
