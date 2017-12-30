package com.fantasy.service;

import com.fantasy.domain.Team;
import com.fantasy.domain.User;
import com.fantasy.dto.LoginDto;
import com.fantasy.enums.Role;
import com.fantasy.repository.TeamRepository;
import com.fantasy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User register(LoginDto loginDto) {
        User user = new User();
        user.setRole(Role.USER);
        user.setUsername(loginDto.getUsername());
        user.setPassword(passwordEncoder.encode(loginDto.getPassword()));

        Team team = new Team();
        team.setOwner(user);
        user.setFantasyTeam(team);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User readCurrent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName()).orElse(null);
    }

    public User getUser(Long id) {
        return userRepository.findOne(id);
    }
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
