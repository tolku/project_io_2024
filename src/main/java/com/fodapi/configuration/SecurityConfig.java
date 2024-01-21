package com.fodapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers("/login", "/register", "/loginn").permitAll()
                .anyRequest().authenticated())
                .rememberMe(rememebrMe -> rememebrMe.key("key").tokenValiditySeconds(300))
                .formLogin(formLogin -> formLogin.loginPage("/login")
//                        .loginProcessingUrl("/loginn")
                        .defaultSuccessUrl("/success")
                        .failureUrl("/failure")
                        .permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(withDefaults());


//        http.authorizeHttpRequests(authz -> authz.requestMatchers("/login", "/register", "/loginn").permitAll())
//
//                .formLogin(formLogin -> formLogin.loginPage("/login")
////                        .loginProcessingUrl("/loginn")
//                                .defaultSuccessUrl("/success")
//                                .failureUrl("/failure")
//                        .permitAll()
//                )
//                .rememberMe()
//                .tokenValiditySeconds(300)
//                .key("differentKey")
//                .and()
//                .csrf()
//                .disable()
//                .httpBasic(withDefaults());
    return http.build();
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("SELECT email, password_hash, is_enabled FROM users WHERE email = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT email, role FROM users WHERE email = ?");
        userDetailsManager.setEnableGroups(false);
        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
