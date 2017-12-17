package com.fantasy.controller;

import com.fantasy.dto.LoginDto;
import com.fantasy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(@ModelAttribute LoginDto loginDto) {
        userService.register(loginDto);
        return "/";
    }
}
