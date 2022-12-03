package com.example.golfbook.security.config;

import com.example.golfbook.security.service.JwtTokenService;
import com.example.golfbook.security.service.JwtUserDetailsService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtTokenService jwtTokenService;

    public JwtRequestFilter(JwtUserDetailsService jwtUserDetailsService,
                            JwtTokenService jwtTokenService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenService = jwtTokenService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

//        if (CorsUtils.isPreFlightRequest(request)) {
//            response.setStatus(HttpServletResponse.SC_OK);
//            return;
//        }
        final String requestTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }
        final String jwtToken = requestTokenHeader.substring(7);
        final String username = jwtTokenService.validateTokenAndGetUsername(jwtToken);
        if (username == null) {
            chain.doFilter(request, response);
            return;
        }

        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

}