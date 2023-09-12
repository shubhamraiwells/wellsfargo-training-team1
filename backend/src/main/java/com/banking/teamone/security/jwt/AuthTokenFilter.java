package com.banking.teamone.security.jwt;

import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.service.CustomerIbService;
import com.banking.teamone.service.auth.CustomerIbServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomerIbServiceImpl customerIbService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         try{
             String jwt =  parseJwt(request);
             if(jwt!=null && jwtUtils.validateJwtToken(jwt)){
                 String username= jwtUtils.getUserNameFromJwt(jwt);

                 UserDetails userDetails= customerIbService.loadUserByUsername(username);
                 UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(
                         userDetails,
                         null,
                         userDetails.getAuthorities()
                 );
                 authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                 SecurityContextHolder.getContext().setAuthentication(authenticationToken);

             }
         }catch (Exception e){
             logger.error("Cannot set user authentication: {}",e);
         }
         filterChain.doFilter(request,response);
    }

    private String parseJwt(HttpServletRequest request){
        String headerAuth= request.getHeader("Authorization");
        if(StringUtils.hasText(headerAuth)&&headerAuth.startsWith("Bearer ")){
            return headerAuth.substring(7);
        }
        return null;
    }
}
