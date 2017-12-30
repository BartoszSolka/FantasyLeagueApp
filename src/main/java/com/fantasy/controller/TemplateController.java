package com.fantasy.controller;

import com.fantasy.domain.User;
import com.fantasy.dto.ClubDto;
import com.fantasy.dto.LoginDto;
import com.fantasy.enums.Role;
import com.fantasy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            return "/admin-players";
        }
        return "/index";
    }

    @GetMapping("/admin-players")
    public String adminPlayers() {
        return "/admin-players";
    }

    @GetMapping("/admin-users")
    public String adminUsers() {
        return "/admin-users";
    }

    @GetMapping("/admin-clubs")
    public String adminClubs() {
        return "/admin-clubs";
    }

    @GetMapping("/admin-gameweeks")
    public String adminGameweeks() {
        return "/admin-gameweeks";
    }

    @GetMapping("/admin-gameweek-details")
    public String adminGameweekDetails(@RequestParam("id")Long id) {
        return "/admin-gameweek-details";
    }

    @GetMapping("/admin-add-club")
    public String adminAddClubs(Model model) {
        return "/admin-add-club";
    }
}
