package com.banking.teamone.security;


import com.banking.teamone.security.jwt.AuthEntryPointJwt;
import com.banking.teamone.security.jwt.AuthTokenFilter;
import com.banking.teamone.service.CustomerIbService;
import com.banking.teamone.service.auth.CustomerIbServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

 @Autowired
 CustomerIbServiceImpl customerIbService;


 @Autowired
 AuthEntryPointJwt unauthorizedHandler;

 @Autowired
 public AuthTokenFilter authJwtTokenFilter(){
  return new AuthTokenFilter();
 }

 @Bean
 public DaoAuthenticationProvider authenticationProvider(){
  DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
  daoAuthenticationProvider.setUserDetailsService(customerIbService);
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
  http.csrf(AbstractHttpConfigurer::disable).exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler)).
          sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
         authorizeRequests().antMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
          .antMatchers(HttpMethod.GET, "/api/auth/**").permitAll().
        anyRequest().authenticated();

  http.authenticationProvider(authenticationProvider());
  http.addFilterBefore(authJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
  return http.build();

 }


 }
