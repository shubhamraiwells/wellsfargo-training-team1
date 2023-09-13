package com.banking.teamone.security.jwt;

import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.service.CustomerIbService;
import com.banking.teamone.service.auth.CustomerIbServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import static org.springframework.util.ObjectUtils.isEmpty;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomerIbServiceImpl customerIbService;

    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         try{
//             String jwt =  parseJwt(request);
////             System.out.println(jwt);
//             if(jwt!=null && jwtUtils.validateJwtToken(jwt)){
////                 System.out.println(jwt);
//                 String username= jwtUtils.getUserNameFromJwt(jwt);
////                   System.out.println(username);
//                 UserDetails userDetails= customerIbService.loadUserByUsername(username);
//                 UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(
//                         userDetails,
//                         null,
//                         userDetails.getAuthorities()
//                 );
//                 authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                 SecurityContextHolder.getContext().setAuthentication(authenticationToken);
////new checking begins////
             final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
             if (isEmpty(header) || !header.startsWith("Bearer ")) {
                 filterChain.doFilter(request, response);
                 return;
             }
             final String token = header.split(" ")[1].trim();
             if (!jwtUtils.validateJwtToken(token)) {
                 filterChain.doFilter(request, response);
                 return;
             }

             UserDetails userDetails= customerIbService.loadUserByUsername(jwtUtils.getUserNameFromJwt(token));
             UsernamePasswordAuthenticationToken
                     authentication = new UsernamePasswordAuthenticationToken(
                     userDetails, null,
                     userDetails == null ?
                             List.of() : userDetails.getAuthorities()
             );

             authentication.setDetails(
                     new WebAuthenticationDetailsSource().buildDetails(request)
             );

             SecurityContextHolder.getContext().setAuthentication(authentication);
             filterChain.doFilter(request, response);

         }catch (Exception e){
             logger.error(e.getMessage());
         }
         filterChain.doFilter(request,response);
    }

    private String parseJwt(HttpServletRequest request){
        String headerAuth= request.getHeader("Authorization");
        if(StringUtils.hasText(headerAuth)&&headerAuth.startsWith("Bearer ")){
//            System.out.println(headerAuth);
            return headerAuth.substring(7);
        }
        return null;
    }
}
