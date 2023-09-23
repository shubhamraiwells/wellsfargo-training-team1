package com.banking.teamone.security;


import com.banking.teamone.service.CustomerIbServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {


// @Autowired
// private CustomLoginFailureHandler loginFailureHandler;
//
// @Autowired
// private CustomLoginSuccessHandler loginSuccessHandler;
 @Autowired
 private CustomerIbServiceImpl customerIbServiceImpl;


 @Autowired
 private AuthEntryPointJwt unauthorizedHandler;

 @Autowired
 private AuthTokenFilter authJwtTokenFilter;

 @Bean
 public DaoAuthenticationProvider authenticationProvider(){
  DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
  daoAuthenticationProvider.setUserDetailsService(customerIbServiceImpl);
  daoAuthenticationProvider.setPasswordEncoder(passWordEncoder());
  return daoAuthenticationProvider;
 }
 @Bean
 public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
  return authConfig.getAuthenticationManager();
 }


 @Bean
 public PasswordEncoder passWordEncoder(){
  return new BCryptPasswordEncoder();
 }
 @Bean
 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
  http.cors().configurationSource(option->corsConfigurationSource()).and().csrf(AbstractHttpConfigurer::disable).exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler)).
          sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
         authorizeRequests().antMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
          .antMatchers(HttpMethod.GET, "/api/auth/**").permitAll().antMatchers(HttpMethod.POST,"/api/savingAccount/**").permitAll().antMatchers("/api/savingAccount/createSavingsAccount").permitAll().
        anyRequest().authenticated();

  http.authenticationProvider(authenticationProvider());
  http.addFilterBefore(authJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
  return http.build();

 }


 public CorsConfiguration corsConfigurationSource(){
  CorsConfiguration corsConfig=new CorsConfiguration();
  corsConfig.addAllowedOrigin("*");
  corsConfig.addAllowedMethod("*");
  corsConfig.addAllowedHeader("*");
//  corsConfig.setAllowCredentials(true);
corsConfig.setExposedHeaders(List.of("Authorization"));
corsConfig.setMaxAge(3600L);

//  UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();
//  source.registerCorsConfiguration("/**",corsConfig);
  return corsConfig;
 }


 }
