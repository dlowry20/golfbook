package com.example.golfbook.security.service;

import com.example.golfbook.security.dto.UserRequestDto;
import com.example.golfbook.security.model.GolfBookUser;
import com.example.golfbook.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public JwtUserDetailsService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public GolfBookUser getUserModel(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("user"));
    }

    public GolfBookUser addUser(UserRequestDto userRequestDto) {
        String password = passwordEncoder.encode(userRequestDto.getPassword());
        return userRepository.save(new GolfBookUser(userRequestDto, password));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUser(username);
    }

    private UserDetails loadUser(String userName) {
        return userRepository.findById(userName).orElseThrow(() -> new UsernameNotFoundException("User: " + userName + "was not found"));
    }
}