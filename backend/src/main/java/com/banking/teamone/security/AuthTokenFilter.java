package com.banking.teamone.security;

import com.banking.teamone.repository.CustomerIbRepository;
import com.banking.teamone.service.CustomerIbService;
import com.banking.teamone.service.CustomerIbServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
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

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {
private static final Logger logger= LoggerFactory.getLogger(AuthTokenFilter.class);
//    @Autowired
//    private final JwtUtils jwtUtils;
//
////    @Autowired
//    private final CustomerIbServiceImpl customerIbService;
//
//    @Autowired
//    private CustomerIbRepository customerIbRepository;
//
//    @Override
//    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//         try{
//
//
//             final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//
//             if (isEmpty(header) || !header.startsWith("Bearer ")) {
//                 filterChain.doFilter(request, response);
//                 return;
//             }
//             final String token = header.split(" ")[1].trim();
//
//             if (!jwtUtils.validateJwtToken(token)) {
//                 filterChain.doFilter(request, response);
//                 return;
//             }
////             logger.error("Authtokenfilter: "+" "+token);
//             UserDetails userDetails= customerIbService.loadUserByUsername(jwtUtils.getUserNameFromJwt(token));
//             UsernamePasswordAuthenticationToken
//                     authentication = new UsernamePasswordAuthenticationToken(
//                     userDetails, null,
//                     userDetails == null ?
//                             List.of() : userDetails.getAuthorities()
//             );
//
//             authentication.setDetails(
//                     new WebAuthenticationDetailsSource().buildDetails(request)
//             );
//
//             SecurityContextHolder.getContext().setAuthentication(authentication);
//
//
//         }catch (Exception e){
//             logger.error(e.getMessage());
//         }
//         filterChain.doFilter(request,response);
//    }
    @Autowired
    private JwtUtils jwtUtil;
    @Autowired
    private CustomerIbServiceImpl service;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String token = null;
        String userName = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            userName = jwtUtil.getUserNameFromJwt(token);
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = service.loadUserByUsername(userName);

            if (jwtUtil.validateJwtToken(token)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String parseJwt(HttpServletRequest request){
        String headerAuth= request.getHeader("Authorization");
        if(StringUtils.hasText(headerAuth)&&headerAuth.startsWith("Bearer ")){

            return headerAuth.substring(7);
        }
        return null;
    }
}
