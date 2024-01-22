package com.fodapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;

import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@CrossOrigin
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers("/login", "/register").permitAll()
                .anyRequest().authenticated())
                .rememberMe(rememberMe -> rememberMe.rememberMeServices(rememberMeServices(userDetailsManager())))
                .formLogin(formLogin -> formLogin.loginPage("/login")
                        .defaultSuccessUrl("/success")
                        .failureHandler(authenticationFailureHandler())
//                        .failureUrl("/failure")
                        .permitAll())
                .cors(cors -> corsFilter())
//                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(withDefaults());
    return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:4000/");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        source.setCorsConfigurations(Map.of("/**", config));

//        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }


    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("SELECT email, password_hash, is_enabled FROM public.users WHERE email = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT email, role FROM users WHERE email = ?");
        userDetailsManager.setEnableGroups(false);
        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
        TokenBasedRememberMeServices.RememberMeTokenAlgorithm encodingAlgorithm = TokenBasedRememberMeServices.RememberMeTokenAlgorithm.SHA256;
        TokenBasedRememberMeServices rememberMe = new TokenBasedRememberMeServices("springRocks", userDetailsService, encodingAlgorithm);
        rememberMe.setMatchingAlgorithm(TokenBasedRememberMeServices.RememberMeTokenAlgorithm.MD5);
        rememberMe.setTokenValiditySeconds(300);
        return rememberMe;
    }
}
