package com.fantasy.controller;

import com.fantasy.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class TemplateController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login", new LoginDto());
        return "/login";
    }

    @GetMapping("/")
    public String index() {
        return "/index";
    }
}
