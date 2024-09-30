package com.fram.configs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // This annotation tells spring security to use this for security configuration and avoid using the default configs
public class SpringSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(predicate -> predicate.disable() );
        httpSecurity.authorizeHttpRequests(req -> req
                .requestMatchers("create", "sign-in")
                .permitAll()
                .anyRequest().authenticated());
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

     /* Enable getting users from the database for authentication,
     * For us to be able to utilise the UserDetailService which is an interface, we have to create a class that implements it, or just use inbuilt
     * implementations
     * */
    //Using in memory users to login
 /*   @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1 = User
                .withDefaultPasswordEncoder()
                .username("user1")
                .password("user12345")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user1);
    }*/
    //using users coming from the database, using the DAO Authentication Provider

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
