package com.springboot.lms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //<- This ensures that this class gets called during every API call
public class SecurityConfig {

//    @Bean
//    UserDetailsService users() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{noop}user123")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{noop}admin123")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    /*
    This is the In memory user password login where we defined the username and password explicitly
     */

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/api/user/signup").permitAll()
                        .requestMatchers("/api/learner/add").permitAll()
                        .requestMatchers("/api/author/add").permitAll()
                        .requestMatchers("/api/course/getAll").permitAll()
                        .requestMatchers("/api/user/token").authenticated()
                        .requestMatchers("/api/user/details").authenticated()
                        .requestMatchers("/api/course/getCoursesByAuthor").hasAuthority("AUTHOR")
                        .requestMatchers("/api/module/add").hasAuthority("AUTHOR")
                        .requestMatchers("/api/learner/getLearner").hasAuthority("LEARNER")
                        .requestMatchers("/api/video/add/{moduleId}").hasAuthority("AUTHOR")
                        .requestMatchers("/api/course/add").hasAnyAuthority("AUTHOR", "EXECUTIVE")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults()); //<- this activated http basic technique
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {  //<- Bean saves this object in spring's context
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager getAuthManager(AuthenticationConfiguration auth)
            throws Exception {
        return auth.getAuthenticationManager();
    }



}





