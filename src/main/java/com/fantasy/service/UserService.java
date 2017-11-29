package com.fantasy.service;

import com.fantasy.domain.User;
import com.fantasy.dto.LoginDto;
import com.fantasy.enums.Role;
import com.fantasy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(LoginDto loginDto) {
        User user = new User();
        user.setRole(Role.USER);
        user.setUsername(loginDto.getUsername());
        user.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        return userRepository.save(user);
    }
}
