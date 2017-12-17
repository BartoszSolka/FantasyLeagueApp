package com.fantasy.controller;

import com.fantasy.domain.User;
import com.fantasy.dto.LoginDto;
import com.fantasy.enums.Role;
import com.fantasy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class TemplateController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login", new LoginDto());
        return "/login";
    }

    @GetMapping("/")
    public String index() {
        User user = userService.readCurrent();
        if (Role.ADMIN.equals(user.getRole())) {
            return "/admin";
        }
        return "/index";
    }
}
