package com.fantasy.controller;

import com.fantasy.domain.User;
import com.fantasy.dto.LoginDto;
import com.fantasy.enums.Role;
import com.fantasy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "/user-players";
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

    @GetMapping("/admin-player-details")
    public String adminPlayerDetails() {
        return "/admin-player-details";
    }

    @GetMapping("/admin-match-details")
    public String adminMatchDetails() {
        return "/admin-match-details";
    }
    
    @GetMapping("/admin-add-club")
    public String adminAddClub() {
        return "/admin-add-club";
    }

    @GetMapping("/admin-add-gameweek")
    public String adminAddGameweek() {
        return "/admin-add-gameweek";
    }

    @GetMapping("/admin-add-player")
    public String adminAddPlayer() {
        return "/admin-add-player";
    }
    
    @GetMapping("/admin-add-match")
    public String adminAddMatch() {
        return "/admin-add-match";
    }

    @GetMapping("/admin-add-goal")
    public String adminAddGoal() {
        return "/admin-add-goal";
    }

    @GetMapping("/user-players")
    public String userPlayers() {
        return "/user-players";
    }

    @GetMapping("/user-clubs")
    public String userClubs() {
        return "/user-clubs";
    }

    @GetMapping("/user-gameweeks")
    public String userGameweeks() {
        return "/user-gameweeks";
    }

    @GetMapping("/user-gameweek-details")
    public String userGameweekDetails(@RequestParam("id")Long id) {
        return "/user-gameweek-details";
    }

    @GetMapping("/user-player-details")
    public String userPlayerDetails() {
        return "/user-player-details";
    }

    @GetMapping("/user-match-details")
    public String userMatchDetails() {
        return "/user-match-details";
    }
}
