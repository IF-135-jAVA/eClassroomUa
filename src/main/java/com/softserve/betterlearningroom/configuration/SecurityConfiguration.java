package com.softserve.betterlearningroom.configuration;

import com.softserve.betterlearningroom.configuration.jwt.JwtFilter;
import com.softserve.betterlearningroom.security.HttpCookieOAuth2AuthorizationRequestRepository;
import com.softserve.betterlearningroom.security.oauth2.handler.OAuth2AuthenticationFailureHandler;
import com.softserve.betterlearningroom.security.oauth2.handler.OAuth2AuthenticationSuccessHandler;
import com.softserve.betterlearningroom.service.impl.CustomOAuth2UserService;
import com.softserve.betterlearningroom.service.impl.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;
    
    private final JwtFilter jwtFilter;
    
    private final CustomOAuth2UserService customOAuth2UserService;
    
    private final OAuth2AuthenticationSuccessHandler successHandler;
    
    private final OAuth2AuthenticationFailureHandler failureHandler;
    
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .csrf()
                    .disable()
                .httpBasic()
                    .disable()
                .formLogin()
                    .disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .antMatchers("/api/auth/login", "/api/auth/signup", "/api/auth/request/password", "/api/auth/password",
                            "/api/auth/confirm", "/api/auth/request/confirm", "/","/webjars/**","/swagger-ui/**","/swagger-ui.html","/v3/api-docs/**",
                            "/swagger-resources/**","/swagger.json", "/oauth2/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .oauth2Login()
                    .authorizationEndpoint()
                        .baseUri("/oauth2/authorize")
                        .authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository)
                        .and()
                    .redirectionEndpoint()
                        .baseUri("/oauth2/callback/*")        
                        .and()
                    .userInfoEndpoint()
                        .userService(customOAuth2UserService)
                        .and()
                    .successHandler(successHandler)
                    .failureHandler(failureHandler)
                    .and()
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
