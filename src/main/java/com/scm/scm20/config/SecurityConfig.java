package com.scm.scm20.config;

import com.scm.scm20.service.impl.CustomUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**", "/logout").permitAll() // Publicly accessible paths
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/static/**").permitAll() // Allow static resources
                        .anyRequest().authenticated() // All other paths require authentication
                )
                // form default login
                // if we need to change anything then we will come here related to form login
                //     .formLogin(Customizer.withDefaults()); // use this if you want spring security pre design login page
                .formLogin(formLogin -> {
                    formLogin
                            .loginPage("/public/login")
                            .loginProcessingUrl("/authenticate")
                            .defaultSuccessUrl("/user/dashboard", true)
                            .failureForwardUrl("/public/login?error=true")
                            .usernameParameter("email")
                            .passwordParameter("password");
                })
                .logout(logoutForm -> {
                    logoutForm
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/public/login?logout=true")
                            .invalidateHttpSession(true) // Invalidate session
                            .clearAuthentication(true); // Clear authentication
                })

                // OAuth2 Configuration
                .oauth2Login(Customizer.withDefaults());
        ;

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}