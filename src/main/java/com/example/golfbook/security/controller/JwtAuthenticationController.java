package com.example.golfbook.security.controller;

import com.example.golfbook.security.dto.JwtRequest;
import com.example.golfbook.security.dto.JwtResponse;
import com.example.golfbook.security.dto.UserRequestDto;
import com.example.golfbook.security.model.GolfBookUser;
import com.example.golfbook.security.service.JwtTokenService;
import com.example.golfbook.security.service.JwtUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class JwtAuthenticationController {
    private final JwtUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public JwtAuthenticationController(
            JwtUserDetailsService userDetailsService,
            AuthenticationManager authenticationManager,
            JwtTokenService jwtTokenService) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()
            ));
        } catch (BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        GolfBookUser golfBookUser = userDetailsService.getUserModel(authenticationRequest.getUsername());

        final String token = jwtTokenService.generateToken(golfBookUser);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping(value = "/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserRequestDto userRequestDto) {

        GolfBookUser golfBookUser = userDetailsService.addUser(userRequestDto);

        return createAuthenticationToken(new JwtRequest(golfBookUser.getUsername(), userRequestDto.getPassword()));
    }
}

